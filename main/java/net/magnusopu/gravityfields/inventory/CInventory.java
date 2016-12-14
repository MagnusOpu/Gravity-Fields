package net.magnusopu.gravityfields.inventory;

import net.magnusopu.gravityfields.item.CItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

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

public class CInventory extends InventoryBase {

    // The item that owns this inventory essentially
    private ItemStack master;

    private int limit;
    private int currentCount;
    private int upArrowX = 134;
    private int upArrowY = 23;
    private int downArrowX = 134;
    private int downArrowY = 59;

    /**
     * CInventory is a class extending InventoryBase that is built to manage items with an inventory that has counting functionality
     *
     * @param itemStackArray A list of itemstacks to start in the inventory. Also determines inventory size.
     * @param name The unlocalized name of the inventory.
     * @param master The master itemstack that essentially "owns" this inventory
     */
    public CInventory(ItemStack[] itemStackArray, String name, ItemStack master){
        super(itemStackArray, name, "counter");
        this.master = master;
        if(master.hasTagCompound()) {
            this.limit = master.getTagCompound().getInteger("limit");
            this.currentCount = master.getTagCompound().getInteger("count");
        } else {
            master.setTagCompound(new NBTTagCompound());
            CItem masterItem = (CItem)master.getItem();
            master.getTagCompound().setInteger("limit", masterItem.getLimit());
            master.getTagCompound().setInteger("count", masterItem.getCurrentCount());
            this.limit = masterItem.getLimit();
            this.currentCount = masterItem.getCurrentCount();
        }
    }

    /**
     * Sets the inventory contents of the specified slot. Overriding this method to make it so the contents of this slot can not be set.
     *
     * @param index The slot to set the contents at
     * @param stack The contents to set the slot to
     */
    @Override
    public void setInventorySlotContents(int index, ItemStack stack){}

    /**
     * Gets the stack residing in the slot at index. Overriding this method so it always returns null.
     *
     * @param index The slot's index to get.
     * @return null
     */
    @Override
    public ItemStack getStackInSlot(int index){
        return null;
    }

    /**
     * Gets the amount of items allowed in each slot. Overriding this method so it always returns 0.
     *
     * @return 0
     */
    @Override
    public int getInventoryStackLimit() {
        return 0;
    }

    /**
     * Sets the value of a field
     *
     * @param id The id of the field to set
     * @param value The value to set the field to set
     */
    @Override
    public void setField(int id, int value){
        switch(id){
            case 0:
                master.getTagCompound().setInteger("limit", value);
                limit = value;
            break;
            case 1:
                master.getTagCompound().setInteger("count", value);
                currentCount = value;
            break;
            case 2:
                upArrowX = value;
            break;
            case 3:
                upArrowY = value;
            break;
            case 4:
                downArrowX = value;
            break;
            case 5:
                downArrowY = value;
            break;
        }
    }

    /**
     * Gets the value of a field
     *
     * @param id The field to get the value of
     * @return The value of the field
     */
    @Override
    public int getField(int id) {
        switch(id){
            case 0:
                limit = master.getTagCompound().getInteger("limit");
                return limit;
            case 1:
                currentCount = master.getTagCompound().getInteger("count");
                return currentCount;
            case 2:
                return upArrowX;
            case 3:
                return upArrowY;
            case 4:
                return downArrowX;
            case 5:
                return downArrowY;
            default:
                return 0;
        }
    }

    /**
     * Gets the number of fields
     *
     * @return The number of fields
     */
    @Override
    public int getFieldCount() {
        return 6;
    }

    /**
     * Getter for the item type of master
     *
     * @return CItem of master
     */
    public CItem getMasterItem(){
        return (CItem)master.getItem();
    }


}
