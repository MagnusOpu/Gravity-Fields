package net.magnusopu.gravityfields.tileentity;

import net.magnusopu.gravityfields.container.TContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

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
public class TTileEntity extends TileEntityBase {

    protected int currentTickMax = 0;
    protected int currentTicks = 0;
    private boolean isTicking = false;

    /**
     * TTileEntity is a class that adds ticking functionality and extends TileEntityBase
     *
     * @param itemStackArray The starting itemstacks in the inventory.
     * @param name The name of the tile entity.
     * @param guiID The ID string of the GUI to display.
     */
    public TTileEntity(ItemStack[] itemStackArray, String name, String guiID){
        super(true, itemStackArray, name, guiID);
    }

    /**
     * Called on every tick, updates the current timer if it needs to be updated.
     */
    @Override
    public void update(){
        boolean stateChanged = false;

        if(currentTicks < currentTickMax){
            currentTicks++;
            if(!isTicking){
                stateChanged = true;
                isTicking = true;
            }
        }
        if(!isTicking()){
            currentTicks = 0;
            currentTickMax = 0;
            if(isTicking){
                stateChanged = true;
                isTicking = false;
            }
        }

        if(stateChanged){
            markDirty();
        }
    }

    /**
     * Sets the value of a field with given id
     *
     * @param id The id of the field to set
     * @param value The value to set the field to
     */
    @Override
    public void setField(int id, int value) {
        switch (id) {
            case 0:
                currentTicks = value;
                break;
            case 1:
                currentTickMax = value;
                break;
            default:
                break;
        }
    }

    /**
     * Gets the value of a field with given id
     *
     * @param id The field to get the value of
     * @return The value of the field at id
     */
    @Override
    public int getField(int id){
        switch(id){
            case 0:
                return currentTicks;
            case 1:
                return currentTickMax;
            default:
                return 0;
        }
    }

    /**
     * Gets the amount of fields
     *
     * @return The amount of fields
     */
    @Override
    public int getFieldCount() {
        return 2;
    }

    /**
     * Called when someone interacts with the tile entity and creates a container to show them.
     *
     * @param playerInventory The inventory of the player interacting with the tile entity.
     * @param playerIn The player interacting with the tile entity.
     * @return The container created.
     */
    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn){
        System.out.println("TileEntityBase createContainer()");
        return new TContainer(playerInventory, this);
    }

    /**
     * Determines if the tile entity is currently counting ticks
     *
     * @return Whether or not the tile entity is currently ticking
     */
    public boolean isTicking(){
        return currentTicks != currentTickMax;
    }
}
