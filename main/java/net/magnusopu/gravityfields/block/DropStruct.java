package net.magnusopu.gravityfields.block;

import net.minecraft.item.Item;

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
public class DropStruct {

    private Item itemDropped;
    private int minDropped;
    private int maxDropped;
    private int chanceDropped;

    public DropStruct(Item itemDropped, int minDropped, int maxDropped, int chanceDropped){
        this.itemDropped = itemDropped;
        this.minDropped = minDropped;
        this.maxDropped = maxDropped;
        this.chanceDropped = chanceDropped;
    }

    public DropStruct(Item itemDropped, int chanceDropped){
        this.itemDropped = itemDropped;
        this.minDropped = 1;
        this.maxDropped = 1;
        this.chanceDropped = chanceDropped;
    }

    public DropStruct(Item itemDropped, int maxDropped, int chanceDropped){
        this.itemDropped = itemDropped;
        this.minDropped = 1;
        this.maxDropped = maxDropped;
        this.chanceDropped = chanceDropped;
    }

    public DropStruct(Item itemDropped){
        this.itemDropped = itemDropped;
        this.minDropped = 1;
        this.maxDropped = 1;
        this.chanceDropped = 100;
    }

    public Item getItemDropped() {
        return itemDropped;
    }

    public void setItemDropped(Item itemDropped) {
        this.itemDropped = itemDropped;
    }

    public int getMinDropped() {
        return minDropped;
    }

    public void setMinDropped(int minDropped) {
        this.minDropped = minDropped;
    }

    public int getMaxDropped() {
        return maxDropped;
    }

    public void setMaxDropped(int maxDropped) {
        this.maxDropped = maxDropped;
    }

    public int getChanceDropped() {
        return chanceDropped;
    }

    public void setChanceDropped(int chanceDropped) {
        this.chanceDropped = chanceDropped;
    }
}
