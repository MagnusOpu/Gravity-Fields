package net.magnusopu.gravityfields.tileentity;

import net.magnusopu.gravityfields.GravityFields;
import net.magnusopu.gravityfields.container.ContainerBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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

public class TileEntityBase extends TileEntityLockable implements ITickable, ISidedInventory {

    protected boolean shouldRefresh = false;
    protected ItemStack[] itemStackArray;
    protected String name;
    protected String guiID;

    /**
     * TileEntityBase is a basic tile entity constructor class for Gravity Field tile entities.
     *
     * @param shouldRefresh If the tile entity should refresh every tick.
     * @param itemStackArray A list of itemstacks to start in the inventory. Also determines inventory size.
     * @param name The unlocalized name of the tile entity.
     * @param guiID The gui reference string.
     */
    public TileEntityBase(boolean shouldRefresh, ItemStack[] itemStackArray, String name, String guiID){
        this.shouldRefresh = shouldRefresh;
        this.itemStackArray = itemStackArray;
        this.name = name;
        this.guiID = guiID;
        if(itemStackArray == null)
            this.itemStackArray = new ItemStack[0];
    }

    /**
     * Called from Chunk.setBlockIDWithMetadata and Chunk.fillChunk, determines if this tile entity should be re-created when the ID, or Metadata changes.
     * Use with caution as this will leave straggler TileEntities, or create conflicts with other TileEntities if not used properly.
     *
     * @param worldIn Current world
     * @param pos Tile's world position
     * @param oldState The old ID of the block
     * @param newState The new ID of the block (May be the same)
     * @return true forcing the invalidation of the existing TE, false not to invalidate the existing TE
     */
    @Override
    public boolean shouldRefresh(World worldIn, BlockPos pos, IBlockState oldState, IBlockState newState){
        return shouldRefresh;
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
     * Gets the unlocalized name of the tile entity.
     *
     * @return
     */
    @Override
    public String getName() {
        return "container."+name;
    }

    /**
     * Gets the unlocalized guiID of the tile entity.
     *
     * @return
     */
    public String getGuiIDUnlocalized(){
        return guiID;
    }

    /**
     * Determines whether or not the tile entity has a custom name.
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
     * Gets the guiID of the tile entity.
     *
     * @return modId + guiID
     */
    @Override
    public String getGuiID(){
        return GravityFields.modId+":"+guiID;
    }

    /**
     * Determines whether or not an item can be extracted.
     *
     * @param index The index of the slot attempting to be extracted.
     * @param stack The item residing in the slot.
     * @param facing The current direction the block is facing.. I guess?
     * @return Whether or not the item can be extracted.
     */
    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing facing) {
        return true;
    }

    /**
     * Determines whether or not an item can be inserted.
     *
     * @param index The index of the slot an item is attempting to be inserted in to.
     * @param stack The stack attempting to be inserted into the slot.
     * @param facing The current direction the block is facing.. I guess?
     * @return Whether or not the item can be inserted.
     */
    @Override
    public boolean canInsertItem(int index, ItemStack stack, EnumFacing facing) {
        return isItemValidForSlot(index, stack);
    }

    /**
     * Determines whether or not the player can interact with this tile entity
     *
     * @param playerIn The player attempting to interact with the tile entity
     * @return Whether or not the player can interact with the tile entity
     */
    @Override
    public boolean isUseableByPlayer(EntityPlayer playerIn){
        return worldObj.getTileEntity(pos) != this ? false : playerIn.getDistanceSq(pos.getX()+0.5D, pos.getY()+0.5D, pos.getZ()+0.5D) < 64.0D;
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
        return new ContainerBase(playerInventory, this);
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
        return false;
    }

    /**
     * Called when the inventory is closed.
     *
     * @param player The player closing the inventory.
     */
    @Override
    public void closeInventory(EntityPlayer player) {}

    /**
     * Called every tick.
     */
    @Override
    public void update(){}

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
     * Empty method stub
     */
    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        int[] a = {0};
        return a;
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
