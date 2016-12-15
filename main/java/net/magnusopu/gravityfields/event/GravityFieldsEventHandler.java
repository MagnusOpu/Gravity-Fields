package net.magnusopu.gravityfields.event;

import net.magnusopu.gravityfields.GravityFields;
import net.magnusopu.gravityfields.container.GFGContainer;
import net.magnusopu.gravityfields.gui.CGui;
import net.magnusopu.gravityfields.item.MItems;
import net.magnusopu.gravityfields.player.PlayerGravity;
import net.magnusopu.gravityfields.tileentity.GFGTileEntity;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.GuiScreenEvent.MouseInputEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Copyright (C) 2016 MagnusOpu.
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * <p>
 * Contact me at zacharydsturtz@gmail.com
 */

public class GravityFieldsEventHandler {

    /**
     * This event listener is called before any mouse input on a gui screen
     *
     * @param e A mouse input event
     */
    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public void onMouseClick(MouseInputEvent.Pre e){
        GuiScreen gui = e.getGui();
        if(gui instanceof CGui){
            CGui cGui = (CGui)gui;
            cGui.mouseInput();
        }
    }

    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public void playerTickEvent(TickEvent.PlayerTickEvent e) {
        if(e.player.capabilities.isFlying){ return; } // Don't have gravity affect flying players
        if(e.phase != TickEvent.Phase.START){ return; } // Only run once per tick

        boolean isAffected = false;
        boolean stateChanged = false;
        boolean changedStrength = false;

        int x = e.player.getPosition().getX();
        int y = e.player.getPosition().getY();
        int z = e.player.getPosition().getZ();

        double defaultGrav = -0.0784000015258789;
        double mY = e.player.motionY;
        System.out.println("CurrentMotionY:"+mY);
        System.out.println("JumpMovementFactor:"+e.player.jumpMovementFactor);

        for(GFGTileEntity gen : GravityFields.gravityFieldGenerators){
            if(gen.isActive()){

                int xDiff = Math.abs(x - gen.getPos().getX());
                int yDiff = Math.abs(y - gen.getPos().getY());
                int zDiff = Math.abs(z - gen.getPos().getZ());

                int maxRange = MItems.gravityRangeStone.getLimit();

                // Instantly weeding entities out of the max limit of range to reduce the load on the server
                if(!(xDiff > maxRange || yDiff > maxRange || zDiff > maxRange)) {

                    int range = MItems.gravityRangeStone.getCurrentCount();
                    int strength = MItems.gravityStrengthStone.getCurrentCount();
                    ItemStack slot1 = gen.getStackInSlot(GFGContainer.stoneSlotIndex1);
                    ItemStack slot2 = gen.getStackInSlot(GFGContainer.stoneSlotIndex2);

                    if (slot1 != null){
                        if (slot1.getItem() == MItems.gravityRangeStone){
                            range = getTagVal(slot1);
                        } else if (slot1.getItem() == MItems.gravityStrengthStone){
                            strength = getTagVal(slot1);
                        }
                    } else if (slot2 != null){
                        if (slot2.getItem() == MItems.gravityRangeStone){
                            range = getTagVal(slot2);
                        } else if (slot2.getItem() == MItems.gravityStrengthStone) {
                            strength = getTagVal(slot2);
                        }
                    }

                    if(!(xDiff > range || yDiff > range || zDiff > range)){
                        isAffected = true;
                        PlayerGravity pGrav = PlayerGravity.findByPlayer(e.player, GravityFields.playerGravities);
                        if(pGrav != null){
                            if(pGrav.getStrength() != strength){
                                stateChanged = true;
                                changedStrength = true;
                            }
                            pGrav.setStrength(strength);
                            pGrav.setRange(range);
                        } else {
                            GravityFields.playerGravities.add(new PlayerGravity(e.player, strength, range));
                            stateChanged = true;
                        }
                    } else {
                        PlayerGravity pGrav = PlayerGravity.findByPlayer(e.player, GravityFields.playerGravities);
                        if(pGrav != null){
                            GravityFields.playerGravities.remove(pGrav);
                            //e.player.motionY = pGrav.getInitY();
                            System.out.println("Player walked out of gravity range");
                        }
                    }
                }
            }
        }

        PlayerGravity pGrav = PlayerGravity.findByPlayer(e.player, GravityFields.playerGravities);

        if(pGrav != null){
        }
        if(pGrav != null) {
            if (stateChanged) {
                if (mY != defaultGrav) {
                    if (!changedStrength) {
                        pGrav.setInitY(mY); // Added player to grav list therefore need to set initY
                        System.out.println("Added player to grav list therefore need to set initY to "+mY);
                    }
                    //e.player.motionY = pGrav.getInitY() * getMultiplier(pGrav.getStrength());
                    System.out.println(e.player.motionY+":pmy = "+pGrav.getInitY()+":piy * "+getMultiplier(pGrav.getStrength())+":gm");
                    isAffected = true;
                }
            }

            if (pGrav.isPendingChange()) {
                if (mY != 0) {
                    pGrav.setInitY(mY);
                    //e.player.motionY = pGrav.getInitY() * getMultiplier(pGrav.getStrength());
                    System.out.println(e.player.motionY+":pmy = "+pGrav.getInitY()+":piy * "+getMultiplier(pGrav.getStrength())+":gm");
                    isAffected = true;
                }
            }

            if (mY == 0 && isAffected) {
                pGrav.setInitY(0);
                pGrav.setPendingChange(true);
                System.out.println("Set init to 0 and pending change to true");
                isAffected = true;
            }

            if(!isAffected){
                GravityFields.playerGravities.remove(pGrav);
                System.out.println("isAffected is false therefore removed player from gravList");
            }
        }
    }

    private Double getMultiplier(int strength){
        switch(strength){
            case 1:
                return .25;
            case 2:
                return .50;
            case 3:
                return 1D;
            case 4:
                return 2D;
            case 5:
                return 4D;
            default:
                return 1D;
        }
    }

    private int getTagVal(ItemStack stack){
        int ret = 0;
        if(stack.getItem() == MItems.gravityRangeStone){
            if(stack.hasTagCompound()) {
                ret = stack.getTagCompound().getInteger("count");
            } else {
                System.out.println("No compound?? Wtf??");
                stack.setTagCompound(new NBTTagCompound());
                ret = MItems.gravityRangeStone.getCurrentCount();
                stack.getTagCompound().setInteger("count", ret);
                stack.getTagCompound().setInteger("limit", MItems.gravityRangeStone.getLimit());
            }
        } else if(stack.getItem() == MItems.gravityStrengthStone) {
            if(stack.hasTagCompound()) {
                ret = stack.getTagCompound().getInteger("count");
            } else {
                System.out.println("No compound?? Wtf??");
                stack.setTagCompound(new NBTTagCompound());
                ret = MItems.gravityStrengthStone.getCurrentCount();
                stack.getTagCompound().setInteger("count", ret);
                stack.getTagCompound().setInteger("limit", MItems.gravityStrengthStone.getLimit());
            }
        }
        return ret;
    }


}
