package net.magnusopu.gravityfields.tileentity;

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

    public TileEntityBase(boolean shouldRefresh, ItemStack[] itemStackArray, String name, String guiID){
        this.shouldRefresh = shouldRefresh;
        this.itemStackArray = itemStackArray;
        this.name = name;
        this.guiID = guiID;
        if(itemStackArray == null)
            this.itemStackArray = new ItemStack[0];
    }

    @Override
    public boolean shouldRefresh(World worldIn, BlockPos pos, IBlockState oldState, IBlockState newState){
        return shouldRefresh;
    }

    @Override
    public int getSizeInventory(){
        return itemStackArray.length;
    }

    @Override
    public ItemStack getStackInSlot(int index){
        return itemStackArray[index];
    }

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

    @Override
    public String getName() {
        return hasCustomName() ? name : "container.gravityGenerator";
    }

    @Override
    public boolean hasCustomName() {
        return name != null && name.length() > 0;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }


    @Override
    public String getGuiID(){
        return "gravityfields:"+guiID;
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing facing) {
        return true;
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return isItemValidForSlot(index, itemStackIn);
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer playerIn){
        return worldObj.getTileEntity(pos) != this ? false : playerIn.getDistanceSq(pos.getX()+0.5D, pos.getY()+0.5D, pos.getZ()+0.5D) < 64.0D;
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn){
        System.out.println("TileEntityBase createContainer()");
        return new ContainerBase(playerInventory, this);
    }

    @Override
    public ItemStack removeStackFromSlot(int index){
        if(index < itemStackArray.length){
            return itemStackArray[index];
        }

        return null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack){
        if(index < itemStackArray.length){
            itemStackArray[index] = null;
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < itemStackArray.length; ++i)
        {
            itemStackArray[i] = null;
        }
    }

    @Override
    public void openInventory(EntityPlayer player) {}

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return false;
    }

    @Override
    public void closeInventory(EntityPlayer playerIn) {}

    @Override
    public void update(){}

    @Override
    public void setField(int id, int value){}

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        return new int[0];
    }

    public void setCustomInventoryName(String customName){
        name = customName;
    }
}
