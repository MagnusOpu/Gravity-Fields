package net.magnusopu.gravityfields.slot;

import net.magnusopu.gravityfields.item.IOItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
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
public class InputSlot extends Slot {

    private Item[] allowedItems;

    public InputSlot(IInventory inventory, int slotIndex, int xDisplayPos, int yDisplayPos, IOItem... allowedItems){
        super(inventory, slotIndex, xDisplayPos, yDisplayPos);
        this.allowedItems = new Item[allowedItems.length];
        for(int i=0;i<allowedItems.length;i++){
            this.allowedItems[i] = allowedItems[i].getInput();
        }
    }

    @Override
    public boolean isItemValid(ItemStack stack){
        Item item = stack.getItem();
        for(Item allowedItem : allowedItems){
            if(allowedItem == item){
                return true;
            }
        }
        return false;
    }


}
