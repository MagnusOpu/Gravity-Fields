package net.magnusopu.gravityfields.tileentity;

import net.magnusopu.gravityfields.container.IOContainer;
import net.magnusopu.gravityfields.item.IOItem;
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

public class IOTileEntity extends TTileEntity {

    protected int inputSlot = 0;
    protected int outputSlot = 1;
    protected IOItem[] allowedItems;

    public IOTileEntity(ItemStack[] itemStackArray, String name, int inputSlot, int outputSlot, String guiID, IOItem... allowedItems){
        super(itemStackArray, name, guiID);
        if(inputSlot < itemStackArray.length){
            this.inputSlot = inputSlot;
            this.outputSlot = outputSlot;
        }
        this.allowedItems = allowedItems;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack){
        boolean isSameStackAlreadyInSlot = stack != null && stack.isItemEqual(itemStackArray[index]) && ItemStack.areItemStackTagsEqual(stack, itemStackArray[index]);
        itemStackArray[index] = stack;
        if(stack != null && stack.stackSize > getInventoryStackLimit()){
            stack.stackSize = getInventoryStackLimit();
        }

        if(index == inputSlot && !isSameStackAlreadyInSlot && stack != null){
            currentTickMax = IOItem.findTicks(stack.getItem(), allowedItems);
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
        return new IOContainer(playerInventory, this, inputSlot, outputSlot, allowedItems);
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack){
        return index == inputSlot ? IOItem.validInput(stack.getItem(), allowedItems) : true;
    }

    @Override
    public void update(){
        if(!worldObj.isRemote) {
            if (itemStackArray[inputSlot] != null) {
                currentTickMax = IOItem.findTicks(itemStackArray[inputSlot].getItem(), allowedItems);
                boolean outputMatchesInput = false;
                boolean willOverflow = false;
                if(itemStackArray[outputSlot] != null) {
                    outputMatchesInput = (itemStackArray[outputSlot].getItem() == IOItem.getOutputFromInput(itemStackArray[inputSlot].getItem(), allowedItems));
                    if(outputMatchesInput) {
                        willOverflow = (IOItem.getOutputAmountFromInput(itemStackArray[inputSlot].getItem(), allowedItems)
                                        + itemStackArray[outputSlot].stackSize
                                        > itemStackArray[outputSlot].getMaxStackSize());
                    }
                }
                if(itemStackArray[outputSlot] == null || (outputMatchesInput && !willOverflow)){
                    currentTicks++;
                    if (currentTicks >= currentTickMax) {
                        currentTicks = 0;
                        if(itemStackArray[outputSlot] != null){
                            itemStackArray[outputSlot].stackSize += IOItem.getOutputAmountFromInput(itemStackArray[inputSlot].getItem(), allowedItems);
                        } else {
                            itemStackArray[outputSlot] = new ItemStack(IOItem.getOutputFromInput(itemStackArray[inputSlot].getItem(), allowedItems),
                                                                        IOItem.getOutputAmountFromInput(itemStackArray[inputSlot].getItem(), allowedItems));
                        }
                        if(itemStackArray[inputSlot].stackSize == 1){
                            itemStackArray[inputSlot] = null;
                        } else {
                            itemStackArray[inputSlot].stackSize--;
                        }
                    }
                }
            } else {
                currentTicks = 0;
                currentTickMax = 0;
            }
        }
    }

    public IOItem[] getAllowedItems() {
        return allowedItems;
    }

    public int getInputSlot(){
        return inputSlot;
    }

    public int getOutputSlot(){
        return outputSlot;
    }
}
