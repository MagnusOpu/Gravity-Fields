package net.magnusopu.gravityfields.slot;

import net.magnusopu.gravityfields.container.GFGContainer;
import net.magnusopu.gravityfields.item.MItems;
import net.minecraft.inventory.IInventory;
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

public class GFGStoneSlot extends InputSlot {

    private GFGContainer container = null;

    /**
     * InputSlot is a class that extends Slot built for the entire purpose of only allowing a select few items to be placed in it.
     *
     * @param inventory The inventory that the slot resides in.
     * @param slotIndex The index of the slot.
     * @param xDisplayPos The x position to display the slot at.
     * @param yDisplayPos The y position to display the slot at.
     */
    public GFGStoneSlot(IInventory inventory, int slotIndex, int xDisplayPos, int yDisplayPos, GFGContainer container){
        super(inventory, slotIndex, xDisplayPos, yDisplayPos, 1, MItems.gravityRangeStone, MItems.gravityStrengthStone);
        this.container = container;
    }

    /**
     * Overrides isItemValid to make sure only the allowedItems are placed in the slot and the same stone can't be in the slot at the same time.
     *
     * @param stack The stack attempting to be placed in the slot.
     * @return Whether or not the stack is allowed in the slot.
     */
    @Override
    public boolean isItemValid(ItemStack stack){
        boolean isStone = super.isItemValid(stack);
        int ss1 = GFGContainer.stoneSlotIndex1;
        int ss2 = GFGContainer.stoneSlotIndex2;
        if(isStone){
            if(isHere(container.getInv(), ss1)){
                if(stack.isItemEqual(container.getStoneSlot2().getStack())){
                    return false;
                } else {
                    return true;
                }
            } else if(isHere(container.getInv(), ss2)){
                if(stack.isItemEqual(container.getStoneSlot1().getStack())){
                    return false;
                } else {
                    return true;
                }
            }
            System.out.println("What the fuck? In GFGStoneSlot isStone is true but it's not in slot ss1 or ss2??");
            return false;
        }
        return true;
    }
}
