package net.magnusopu.gravityfields.inventory;

import net.magnusopu.gravityfields.GravityFields;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

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

public class InventoryBase implements IInventory {

    protected ItemStack[] itemStackArray;
    protected String name;
    protected String guiID;

    /**
     * InventoryBase is a basic inventory constructor class for Gravity Field inventories.
     *
     * @param itemStackArray A list of itemstacks to start in the inventory. Also determines inventory size.
     * @param name The unlocalized name of the inventory.
     * @param guiID The gui reference string.
     */
    public InventoryBase(ItemStack[] itemStackArray, String name, String guiID){
        this.itemStackArray = itemStackArray;
        this.name = name;
        this.guiID = guiID;
        if(itemStackArray == null)
            this.itemStackArray = new ItemStack[0];
    }

    /**
     * For tile entities, ensures the chunk containing the tile entity is saved to disk later - the game won't think it
     * hasn't changed and skip it.
     */
    @Override
    public void markDirty(){}

    /**
     * Gets the name of the inventory shown to players
     *
     * @return The localized name
     */
    @Override
    public ITextComponent getDisplayName() {
        return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
    }

    /**
     * Getter for the size of the inventory.
     *
     * @return The size of the inventory.
     */
    @Override
    public int getSizeInventory(){
        return itemStackArray.length;
    }

    /**
     * Gets the stack residing in the slot at index.
     *
     * @param index The slot's index to get.
     * @return The stack residing in the slot at index.
     */
    @Override
    public ItemStack getStackInSlot(int index){
        return itemStackArray[index];
    }

    /**
     * Reduces the size of the stack in slot index by count.
     *
     * @param index The slot to reduce the contents of.
     * @param count The amount to reduce the contents by.
     * @return An ItemStack containing what was removed.
     */
    @Override
    public ItemStack decrStackSize(int index, int count){
        if(itemStackArray[index] != null){
            ItemStack itemStack;

            if(itemStackArray[index].stackSize <= count){
                itemStack = itemStackArray[index];
                itemStackArray[index] = null;
                return itemStack;
            } else {
                itemStack = itemStackArray[index].splitStack(count);

                if(itemStackArray[index].stackSize == 0){
                    itemStackArray[index] = null;
                }

                return itemStack;
            }
        } else {
            return null;
        }
    }

    /**
     * Gets the unlocalized name of the inventory.
     *
     * @return
     */
    @Override
    public String getName() {
        return "container."+name;
    }

    /**
     * Gets the unlocalized guiID of the inventory.
     *
     * @return
     */
    public String getGuiIDUnlocalized(){
        return guiID;
    }

    /**
     * Determines whether or not the inventory has a custom name.
     *
     * @return false
     */
    @Override
    public boolean hasCustomName() {
        return false;
    }

    /**
     * Gets the amount of items allowed in each slot.
     *
     * @return 64
     */
    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    /**
     * Gets the guiID of the inventory.
     *
     * @return modId + guiID
     */
    public String getGuiID(){
        return GravityFields.modId+":"+guiID;
    }

    /**
     * Determines whether or not the player can interact with this inventory
     *
     * @param playerIn The player attempting to interact with the inventory
     * @return Whether or not the player can interact with the inventory
     */
    @Override
    public boolean isUseableByPlayer(EntityPlayer playerIn){
        return true;
    }

    /**
     * Removes an item from the slot at index if it contains an item
     *
     * @param index The slot to remove the item from
     * @return The removed item
     */
    @Override
    public ItemStack removeStackFromSlot(int index){
        if(index < itemStackArray.length){
            ItemStack removed = itemStackArray[index];
            itemStackArray[index] = null;
            return removed;
        }

        return null;
    }

    /**
     * Sets the inventory contents of the specified slot
     *
     * @param index The slot to set the contents at
     * @param stack The contents to set the slot to
     */
    @Override
    public void setInventorySlotContents(int index, ItemStack stack){
        if(index < itemStackArray.length){
            itemStackArray[index] = stack;
        }
    }

    /**
     * Clears the inventory of items.
     */
    @Override
    public void clear() {
        for (int i = 0; i < itemStackArray.length; ++i)
        {
            itemStackArray[i] = null;
        }
    }

    /**
     * Called when the inventory is opened.
     *
     * @param player The player opening the inventory.
     */
    @Override
    public void openInventory(EntityPlayer player) {}

    /**
     * Determines if the item is valid for the slot.
     *
     * @param index The slot to check if the item can be put in.
     * @param stack The item to check if it fits in the slot.
     * @return Whether or not the item is valid for the slot.
     */
    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }
    
    /**
     * Called when the inventory is closed.
     *
     * @param player The player closing the inventory.
     */
    @Override
    public void closeInventory(EntityPlayer player) {}

    /**
     * Sets the value of a field
     *
     * @param id The id of the field to set
     * @param value The value to set the field to set
     */
    @Override
    public void setField(int id, int value){}

    /**
     * Gets the value of a field
     *
     * @param id The field to get the value of
     * @return The value of the field
     */
    @Override
    public int getField(int id) {
        return 0;
    }

    /**
     * Gets the number of fields
     *
     * @return The number of fields
     */
    @Override
    public int getFieldCount() {
        return 0;
    }
    
    /**
     * Sets the inventory's name
     *
     * @param customName The name to set the inventory's name to
     */
    public void setCustomInventoryName(String customName){
        name = customName;
    }
}
