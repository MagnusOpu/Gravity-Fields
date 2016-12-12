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

    /**
     * A struct containing all data related to an item to be dropped.
     *
     * @param itemDropped The item to be dropped.
     * @param minDropped The minimum amount of the item to be dropped if it is dropped.
     * @param maxDropped The maximum amount of the item to be dropped if it is dropped.
     * @param chanceDropped The chance for the item to be dropped.
     */
    public DropStruct(Item itemDropped, int minDropped, int maxDropped, int chanceDropped){
        this.itemDropped = itemDropped;
        this.minDropped = minDropped;
        this.maxDropped = maxDropped;
        this.chanceDropped = chanceDropped;
    }

    /**
     * A getter for the item to be dropped.
     *
     * @return The Item to be dropped.
     */
    public Item getItemDropped() {
        return itemDropped;
    }

    /**
     * A setter for the item to be dropped.
     *
     * @param itemDropped The item to be dropped.
     */
    public void setItemDropped(Item itemDropped) {
        this.itemDropped = itemDropped;
    }

    /**
     * A getter for the minimum amount of the item to be dropped.
     *
     * @return The minimum amount of the item to be dropped.
     */
    public int getMinDropped() {
        return minDropped;
    }

    /**
     * A setter for the minimum amount of the item to be dropped.
     *
     * @param minDropped The minimum amount of item to be dropped.
     */
    public void setMinDropped(int minDropped) {
        this.minDropped = minDropped;
    }

    /**
     * A setter for the maximum amount of the item to be dropped.
     *
     * @return maxDropped The maximum amount of item to be dropped.
     */
    public int getMaxDropped() {
        return maxDropped;
    }

    /**
     * A setter for the maximum amount of the item to be dropped.
     *
     * @param maxDropped
     */
    public void setMaxDropped(int maxDropped) {
        this.maxDropped = maxDropped;
    }

    /**
     * A getter for the chance that the item will be dropped.
     *
     * @return The chance that the item will be dropped.
     */
    public int getChanceDropped() {
        return chanceDropped;
    }

    /**
     * A setter for the chance that the item will be dropped.
     *
     * @param chanceDropped The chance that the item will be dropped.
     */
    public void setChanceDropped(int chanceDropped) {
        this.chanceDropped = chanceDropped;
    }
}
