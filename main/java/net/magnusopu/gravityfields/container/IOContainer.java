package net.magnusopu.gravityfields.container;

import net.magnusopu.gravityfields.item.IOItem;
import net.magnusopu.gravityfields.slot.InputSlot;
import net.magnusopu.gravityfields.slot.OutputSlot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
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
public class IOContainer extends TContainer {

    protected int inputSlot;
    protected int outputSlot;

    public IOContainer(InventoryPlayer inventoryPlayer, IInventory inventory, int inputIndex, int outputIndex, IOItem... inputItems){
        super(inventoryPlayer, inventory);
        this.inputSlot = inputIndex;
        this.outputSlot = outputIndex;

        addSlotToContainer(new InputSlot(tileBase, inputIndex, 62, 35, inputItems));
        addSlotToContainer(new OutputSlot(tileBase, outputIndex, 98, 35));
    }

    /**@Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int slotIndex){
        ItemStack itemStack1 = null;
        Slot slot = inventorySlots.get(slotIndex);

        if(slot != null && slot.getHasStack()){
            ItemStack itemStack2 = slot.getStack();
            itemStack1 = itemStack2.copy();

            if(slotIndex == outputSlot){
                if(!mergeItemStack(itemStack2, sizeInventory, sizeInventory+36, true)){
                    return null;
                }
                slot.onSlotChange(itemStack2, itemStack1);
            } else if(slotIndex == inputSlot){
                if(IOItem.validItem(itemStack2.getItem(), ((IOTileEntity)tileBase).getAllowedItems())){
                    if(!mergeItemStack(itemStack2, 0, 1, false)){
                        return null;
                    }
                } else if(slotIndex >= sizeInventory && slotIndex < sizeInventory+27){
                    if(!mergeItemStack(itemStack2, sizeInventory+27, sizeInventory+36, false)){
                        return null;
                    }
                } else if(slotIndex >= sizeInventory+27 && slotIndex < sizeInventory+36 &&
                        !mergeItemStack(itemStack2, sizeInventory+1, sizeInventory+27, false)){
                    return null;
                }
            } else if(!mergeItemStack(itemStack2, sizeInventory, sizeInventory+36, false)){
                return null;
            }

            if(itemStack2.stackSize == 0){
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }

            if(itemStack2.stackSize == itemStack1.stackSize){
                return null;
            }

            slot.onPickupFromSlot(playerIn, itemStack2);
        }

        return itemStack1;
    }*/

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int slotIndex){
        ItemStack stack1 = null;
        Slot slot = inventorySlots.get(slotIndex);

        if(slot != null && slot.getHasStack()){
            
        }
        return null;
    }

}
