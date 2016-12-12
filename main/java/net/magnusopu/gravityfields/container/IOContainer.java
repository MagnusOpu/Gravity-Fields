package net.magnusopu.gravityfields.container;

import net.magnusopu.gravityfields.item.IOItem;
import net.magnusopu.gravityfields.slot.InputSlot;
import net.magnusopu.gravityfields.slot.OutputSlot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

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

    /**
     * IOContainer is a container extending TContainer that essentially has one input slot, performs some action on that input, then sends the result into an output slot.
     *
     * @param inventoryPlayer The inventory of the player who is interacting with the container.
     * @param inventory The inventory of the container.
     * @param inputIndex The input slot pos.
     * @param outputIndex The output slot pos.
     * @param inputItems The items valid for input and their corresponding output.
     */
    public IOContainer(InventoryPlayer inventoryPlayer, IInventory inventory, int inputIndex, int outputIndex, IOItem... inputItems){
        super(inventoryPlayer, inventory);
        this.inputSlot = inputIndex;
        this.outputSlot = outputIndex;

        addSlotToContainer(new InputSlot(tileBase, inputIndex, 62, 35, inputItems));
        addSlotToContainer(new OutputSlot(tileBase, outputIndex, 98, 35));
    }

    /**
     * Called when a player shift click's an item in the container's inventory.
     *
     * @param playerIn The player interacting with the inventory.
     * @param slotIndex The slot pos that was shift clicked.
     * @return null
     */
    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int slotIndex){
        Slot slot = inventorySlots.get(slotIndex);
        Slot firstEmptySlot = null;
        ItemStack stack1 = null;
        ArrayList<ItemStack> stack2 = new ArrayList<ItemStack>();
        boolean itemAlreadyExists = false;

        if(slot != null && slot.getHasStack()){
            stack1 = slot.getStack();

            for(int i=0;i<getInventory().size();i++){
                ItemStack s = getInventory().get(i);
                if(s != null) {
                    if (s.getItem() == stack1.getItem()) {
                        stack2.add(s);
                        itemAlreadyExists = true;
                    }
                } else {
                    if(firstEmptySlot == null){
                        firstEmptySlot = inventorySlots.get(i);
                    }
                }
            }

            if(itemAlreadyExists){
                slot.putStack(null);
                mergeItemStack(stack1, sizeInventory, sizeInventory+36, false);
            } else if(firstEmptySlot != null){
                slot.putStack(null);
                firstEmptySlot.putStack(stack1);
            }
        }
        return null;
    }

}
