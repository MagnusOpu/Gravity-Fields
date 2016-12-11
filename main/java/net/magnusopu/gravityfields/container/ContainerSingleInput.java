package net.magnusopu.gravityfields.container;

import net.magnusopu.gravityfields.tileentity.ItemTick;
import net.magnusopu.gravityfields.tileentity.TileEntitySingleInput;
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
public class ContainerSingleInput extends ContainerTicking {

    protected int inputSlot;

    public ContainerSingleInput(InventoryPlayer inventoryPlayer, IInventory inventory, int input, boolean shouldAddSlot){
        super(inventoryPlayer, inventory);

        this.inputSlot = input;

        if(shouldAddSlot) {
            addSlotToContainer(new Slot(tileBase, input, 56, 53));
        }
    }

    public ContainerSingleInput(InventoryPlayer inventoryPlayer, IInventory inventory, int input){
        super(inventoryPlayer, inventory);

        this.inputSlot = input;

        addSlotToContainer(new Slot(tileBase, input, 56, 53));
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int slotIndex){
        ItemStack itemStack1 = null;
        Slot slot = inventorySlots.get(slotIndex);

        if(slot != null && slot.getHasStack()){
            ItemStack itemStack2 = slot.getStack();
            itemStack1 = itemStack2.copy();

            if(slotIndex == inputSlot){
                if(ItemTick.validItem(itemStack2.getItem(), ((TileEntitySingleInput)tileBase).getTickableItems())){
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
    }

}
