package net.magnusopu.gravityfields.slot;

import net.minecraft.inventory.IInventory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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

public class InvisibleSlot extends OutputSlot {

    /**
     * InputSlot is a class that extends OutputSlot built for the entire purpose of registering a clicking area in an inventory, not a regular slot.
     *
     * @param inventory The inventory that the slot resides in.
     * @param slotIndex The index of the slot.
     * @param xDisplayPos The x position to display the slot at.
     * @param yDisplayPos The y position to display the slot at.
     */
    public InvisibleSlot(IInventory inventory, int slotIndex, int xDisplayPos, int yDisplayPos){
        super(inventory, slotIndex, xDisplayPos, yDisplayPos);
    }

    /**
     * We don't want players being able to hover over an invisible slot
     * @return false
     */
    @Override
    @SideOnly(Side.CLIENT)
    public boolean canBeHovered() {
        return false;
    }


}
