package net.magnusopu.gravityfields.tileentity;

import net.magnusopu.gravityfields.container.ContainerSingleInput;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

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
public class TileEntitySingleInput extends TileEntityTicking {

    protected int inputSlot = 0;
    //protected boolean cookingSomething = false;
    protected ItemTick[] tickableItems;

    public TileEntitySingleInput(ItemStack[] itemStackArray, String name, int inputSlot, String guiID, ItemTick... tickableItems){
        super(itemStackArray, name, guiID);
        if(inputSlot < itemStackArray.length){
            this.inputSlot = inputSlot;
        }
        this.tickableItems = tickableItems;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack){
        boolean isSameStackAlreadyInSlot = stack != null && stack.isItemEqual(itemStackArray[index]) && ItemStack.areItemStackTagsEqual(stack, itemStackArray[index]);
        itemStackArray[index] = stack;
        if(stack != null && stack.stackSize > getInventoryStackLimit()){
            stack.stackSize = getInventoryStackLimit();
        }

        if(index == inputSlot && !isSameStackAlreadyInSlot && stack != null){
            currentTickMax = ItemTick.findTicksOfItem(stack, tickableItems);
            currentTicks = 0;
            markDirty();
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound){
        super.readFromNBT(compound);

        NBTTagList nbtTagList = compound.getTagList("Items", 10);
        itemStackArray = new ItemStack[getSizeInventory()];

        for(int i=0;i<nbtTagList.tagCount();++i){
            NBTTagCompound nbtTagCompound = nbtTagList.getCompoundTagAt(i);
            byte b0 = nbtTagCompound.getByte("Slot");

            if(b0 >= 0 && b0 < itemStackArray.length){
                itemStackArray[b0] = ItemStack.loadItemStackFromNBT(nbtTagCompound);
            }
        }

        currentTicks = compound.getShort("CookTime");
        currentTickMax = compound.getShort("CookTimeTotal");

        if(compound.hasKey("CustomName", 8)){
            name = compound.getString("CustomName");
        }

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound){
        super.writeToNBT(compound);

        compound.setShort("CookTime", (short)currentTicks);
        compound.setShort("CookTimeTotal", (short)currentTickMax);
        NBTTagList nbtTagList = new NBTTagList();

        for(int i=0;i<itemStackArray.length;i++){
            if(itemStackArray[i] != null){
                NBTTagCompound nbtTagCompound = new NBTTagCompound();
                nbtTagCompound.setByte("Slot", (byte)i);
                itemStackArray[i].writeToNBT(nbtTagCompound);
                nbtTagList.appendTag(nbtTagCompound);
            }
        }

        compound.setTag("Items", nbtTagList);

        if(hasCustomName()){
            compound.setString("CustomName", name);
        }

        return compound;
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn){
        System.out.println("TileEntityBase createContainer()");
        return new ContainerSingleInput(playerInventory, this, inputSlot);
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack){
        return index == inputSlot ? ItemTick.validItem(stack, tickableItems) : true;
    }

    @Override
    public void update(){
        if(!worldObj.isRemote) {
            if (itemStackArray[inputSlot] != null) {
                //      if()
            }
        }
    }

    public ItemTick[] getTickableItems() {
        return tickableItems;
    }

    public int getInputSlot(){
        return inputSlot;
    }
}
