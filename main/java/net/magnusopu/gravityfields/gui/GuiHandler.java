package net.magnusopu.gravityfields.gui;

import net.magnusopu.gravityfields.container.ContainerBase;
import net.magnusopu.gravityfields.container.IOContainer;
import net.magnusopu.gravityfields.image.CImage;
import net.magnusopu.gravityfields.inventory.CInventory;
import net.magnusopu.gravityfields.item.IOItemConfig;
import net.magnusopu.gravityfields.item.MItems;
import net.magnusopu.gravityfields.tileentity.GEGTileEntity;
import net.magnusopu.gravityfields.tileentity.TileEntityBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

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

public class GuiHandler implements IGuiHandler {

    /**
     * Displays a container to the server dependent on ID input
     *
     * @param ID The GUI to display
     * @param player The player to display the container to
     * @param world The world to display the container in
     * @param x The x position of the container
     * @param y The y position of the container
     * @param z The z position of the container
     * @return A container dependent on the ID input
     */
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x,y,z));

        if(ID == EnumGUI.GRAVITY_GENERATOR.ordinal()){
            if(tileEntity != null){
                GEGTileEntity tileEntityG = (GEGTileEntity)tileEntity;
                return new IOContainer(player.inventory, tileEntityG, tileEntityG.getInputSlot(), tileEntityG.getOutputSlot(), IOItemConfig.GRAVITY_ORE_TO_ESSENCE.getConfig());
            }
        } else if(ID == EnumGUI.GRAVITY_RANGE_STONE.ordinal()){
            if(player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() == MItems.gravityRangeStone) {
                CInventory inv = new CInventory(new ItemStack[2], "gravityRangeStoneCounter", player.getHeldItemMainhand());
                return new ContainerBase(player.inventory, inv);
            } else if(player.getHeldItemOffhand() != null && player.getHeldItemOffhand().getItem() == MItems.gravityRangeStone) {
                CInventory inv = new CInventory(new ItemStack[2], "gravityRangeStoneCounter", player.getHeldItemOffhand());
                return new ContainerBase(player.inventory, inv);
            }
        } else if(ID == EnumGUI.GRAVITY_STRENGTH_STONE.ordinal()){
            if(player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() == MItems.gravityStrengthStone) {
                CInventory inv = new CInventory(new ItemStack[2], "gravityStrengthStoneCounter", player.getHeldItemMainhand());
                return new ContainerBase(player.inventory, inv);
            } else if(player.getHeldItemOffhand() != null && player.getHeldItemOffhand().getItem() == MItems.gravityStrengthStone) {
                CInventory inv = new CInventory(new ItemStack[2], "gravityStrengthStoneCounter", player.getHeldItemOffhand());
                return new ContainerBase(player.inventory, inv);
            }
        }

        return null;
    }

    /**
     * Displays a GUI element to the client dependent on ID input
     *
     * @param ID The GUI to display
     * @param player The player to display the GUI to
     * @param world The world to display the GUI in
     * @param x The x position of the GUI
     * @param y The y position of the GUI
     * @param z The z position of the GUI
     * @return A GUI dependent on the ID input
     */
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x,y,z));
        if (ID == EnumGUI.GRAVITY_GENERATOR.ordinal()) {
            if(tileEntity != null){
                if(tileEntity instanceof TileEntityBase) {
                        if(tileEntity instanceof GEGTileEntity){
                            GEGTileEntity tileEntityG = (GEGTileEntity)tileEntity;
                            return new IOGui(new IOContainer(player.inventory, tileEntityG, tileEntityG.getInputSlot(), tileEntityG.getOutputSlot(), IOItemConfig.GRAVITY_ORE_TO_ESSENCE.getConfig()), player.inventory, tileEntityG, tileEntityG.getGuiIDUnlocalized());
                        }
                }
            }
        } else if(ID == EnumGUI.GRAVITY_RANGE_STONE.ordinal()){
            if(player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() == MItems.gravityRangeStone) {
                CInventory inv = new CInventory(new ItemStack[2], "gravityRangeStoneCounter", player.getHeldItemMainhand());
                return new CGui(new ContainerBase(player.inventory, inv), player.inventory, inv, CImage.GRAVITY_STONE.getInfo(), CImage.RANGE_TEXT.getInfo());
            } else if(player.getHeldItemOffhand() != null && player.getHeldItemOffhand().getItem() == MItems.gravityRangeStone) {
                CInventory inv = new CInventory(new ItemStack[2], "gravityRangeStoneCounter", player.getHeldItemOffhand());
                return new CGui(new ContainerBase(player.inventory, inv), player.inventory, inv, CImage.GRAVITY_STONE.getInfo(), CImage.RANGE_TEXT.getInfo());
            }
        } else if(ID == EnumGUI.GRAVITY_STRENGTH_STONE.ordinal()){
            if(player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() == MItems.gravityStrengthStone) {
                CInventory inv = new CInventory(new ItemStack[2], "gravityStrengthStoneCounter", player.getHeldItemMainhand());
                return new CGui(new ContainerBase(player.inventory, inv), player.inventory, inv, CImage.GRAVITY_STONE.getInfo(), CImage.STRENGTH_TEXT.getInfo());
            } else if(player.getHeldItemOffhand() != null && player.getHeldItemOffhand().getItem() == MItems.gravityStrengthStone) {
                CInventory inv = new CInventory(new ItemStack[2], "gravityStrengthStoneCounter", player.getHeldItemOffhand());
                return new CGui(new ContainerBase(player.inventory, inv), player.inventory, inv, CImage.GRAVITY_STONE.getInfo(), CImage.STRENGTH_TEXT.getInfo());
            }
        }

        return null;
    }

}
