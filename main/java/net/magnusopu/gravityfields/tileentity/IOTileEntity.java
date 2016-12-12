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

    /**
     * IOTileEntity is a class extending TTIleEntity buiilt for the sole purpose of housing a container that will have an inventory with one input slot and one output slot.
     *
     * @param itemStackArray The starting itemstacks in the inventory.
     * @param name The name of the tile entity.
     * @param inputSlot The index of the input slot.
     * @param outputSlot The index of the output slot.
     * @param guiID The ID string of the GUI to display.
     * @param allowedItems The allowedItems in the input/output slots.
     */
    public IOTileEntity(ItemStack[] itemStackArray, String name, int inputSlot, int outputSlot, String guiID, IOItem... allowedItems){
        super(itemStackArray, name, guiID);
        if(inputSlot < itemStackArray.length){
            this.inputSlot = inputSlot;
            this.outputSlot = outputSlot;
        }
        this.allowedItems = allowedItems;
    }

    /**
     * Adds special parameters to the NBTTagList when something attempts to read it.
     *
     * @param compound The nbt tag contents.
     */
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

    /**
     * Adds special parameters to the NBTTagList when something attempts to write to it.
     *
     * @param compound The nbt tag contents.
     */
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

    /**
     * Called when someone interacts with the tile entity and creates a container to show them.
     *
     * @param playerInventory The inventory of the player interacting with the tile entity.
     * @param playerIn The player interacting with the tile entity.
     * @return The container created.
     */
    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn){
        System.out.println("IOEntityBase createContainer()");
        return new IOContainer(playerInventory, this, inputSlot, outputSlot, allowedItems);
    }

    /**
     * Determines if the item is valid for the slot.
     *
     * @param index The slot the item is attempting to be placed in.
     * @param stack The item that is attempting to be placed in the slot.
     * @return Whether or not the item is valid for the slot.
     */
    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack){
        if(index == inputSlot){
            return IOItem.validInput(stack.getItem(), allowedItems);
        } else
            return index != outputSlot;
    }

    /**
     * Called on every tick to update the container.
     */
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

    /**
     * Getter for allowedItems.
     *
     * @return allowedItems
     */
    public IOItem[] getAllowedItems() {
        return allowedItems;
    }

    /**
     * Getter for inputSlot.
     *
     * @return inputSlot.
     */
    public int getInputSlot(){
        return inputSlot;
    }

    /**
     * Getter for outputSlot.
     *
     * @return outputSlot.
     */
    public int getOutputSlot(){
        return outputSlot;
    }
}
