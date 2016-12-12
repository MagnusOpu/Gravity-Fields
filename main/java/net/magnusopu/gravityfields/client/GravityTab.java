package net.magnusopu.gravityfields.client;

import net.magnusopu.gravityfields.GravityFields;
import net.magnusopu.gravityfields.item.MItems;
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

    /**
     * Gravity Tab is the customized Creative Tab for the mod Gravity Fields.
     */
    public GravityTab(){
        super(GravityFields.modId);
        setBackgroundImageName("gravityfields.png");
    }

    /**
     * Returns the icon to be set for the creative tab.
     *
     * @return The icon to be set for the creative tab.
     */
    @Override
    public Item getTabIconItem() {
        return MItems.gravityRangeStone;
    }

    /**
     * Returns whether or not the creative tab has a search bar.
     *
     * @return true
     */
    @Override
    public boolean hasSearchBar(){
        return true;
    }
}
