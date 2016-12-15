package net.magnusopu.gravityfields.container;

import net.magnusopu.gravityfields.item.IOItemConfig;
import net.magnusopu.gravityfields.slot.GFGStoneSlot;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;

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

public class GFGContainer extends IContainer {

    public static final int stoneSlotIndex1 = 1;
    public static final int stoneSlotIndex2 = 2;

    private GFGStoneSlot stoneSlot1;
    private GFGStoneSlot stoneSlot2;

    /**
     * GFGContainer is a type of container that extends IContainer and defines a type of container built for the gravityFieldGenerator.
     *
     * @param inventoryPlayer The inventory of the player who is interacting with the container.
     * @param inventory The inventory of the container.
     */
    public GFGContainer(InventoryPlayer inventoryPlayer, IInventory inventory){
        super(inventoryPlayer, inventory, 0, true, IOItemConfig.GRAVITY_ORE_TO_GRAVITY.getConfig());

        stoneSlot1 = new GFGStoneSlot(inv, stoneSlotIndex1, 44, 25, this);
        stoneSlot2 = new GFGStoneSlot(inv, stoneSlotIndex2, 116, 25, this);

        addSlotToContainer(stoneSlot1);
        addSlotToContainer(stoneSlot2);

    }

    /**
     * Getter for stoneSlot1
     *
     * @return stoneSlot1
     */
    public GFGStoneSlot getStoneSlot1() {
        return stoneSlot1;
    }

    /**
     * Getter for stoneSlot2
     *
     * @return stoneSlot2
     */
    public GFGStoneSlot getStoneSlot2() {
        return stoneSlot2;
    }
}
