package net.magnusopu.gravityfields.client;

import net.magnusopu.gravityfields.GravityFields;
import net.magnusopu.gravityfields.item.ModItems;
import net.minecraft.creativetab.CreativeTabs;
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
public class GravityTab extends CreativeTabs {

    public GravityTab(){
        super(GravityFields.modId);
        setBackgroundImageName("gravityfields.png");
    }

    @Override
    public Item getTabIconItem() {
        return ModItems.gravityRangeStone;
    }

    @Override
    public boolean hasSearchBar(){
        return true;
    }
}