package net.magnusopu.gravityfields.tileentity;

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
public class ItemTick {

    private Item item;
    private int tick;

    public ItemTick(Item item, int tick){
        this.item = item;
        this.tick = tick;
    }

    public static int findTicksOfItem(ItemStack stack, ItemTick[] reference){
        if(stack == null) return 0;
        Item item = stack.getItem();
        for(ItemTick itemTick : reference){
            if(itemTick.item == item){
                return itemTick.tick;
            }
        }
        return 0;
    }

    public static int findTicksOfItem(Item item, ItemTick[] reference){
        if(item == null) return 0;
        for(ItemTick itemTick : reference){
            if(itemTick.item == item){
                return itemTick.tick;
            }
        }
        return 0;
    }

    public static boolean validItem(ItemStack stack, ItemTick[] reference){
        Item item = stack.getItem();
        for(ItemTick itemTick : reference){
            if(itemTick.item == item){
                return true;
            }
        }
        return false;
    }

    public static boolean validItem(Item item, ItemTick[] reference){
        for(ItemTick itemTick : reference){
            if(itemTick.item == item){
                return true;
            }
        }
        return false;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getTick() {
        return tick;
    }

    public void setTick(int tick) {
        this.tick = tick;
    }
}
