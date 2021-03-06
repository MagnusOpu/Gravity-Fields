package net.magnusopu.gravityfields.gui;

import net.magnusopu.gravityfields.image.IImage;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
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
public class GFGGui extends IGui{

    /**
     * GFGGui is a class that extends GuiBase built for the block gravityFieldGenerator
     *
     * @param container The container for the gui to show.
     * @param inventoryPlayer The inventory of the player to show.
     * @param inv The TileEntity inventory to show.
     * @param name The name of the gui resource location.
     */
    public GFGGui(Container container, InventoryPlayer inventoryPlayer, IInventory inv, String name){
        super(container, inventoryPlayer, inv, name, IImage.BAR.getInfo(), true, IImage.STONE_1.getInfo(), IImage.STONE_2.getInfo());
    }
}
