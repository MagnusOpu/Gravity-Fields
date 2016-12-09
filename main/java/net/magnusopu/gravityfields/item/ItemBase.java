package net.magnusopu.gravityfields.item;

import net.magnusopu.gravityfields.GravityFields;
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

public class ItemBase extends Item {

    protected String name;

    /**
     * ItemBase is a basic constructor class for Gravity Field Items.
     *
     * @param name The unlocalized name of the item.
     */
    public ItemBase(String name){
        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(GravityFields.gravityTab);
    }

    /**
     * Registers the item's model.
     */
    public void registerItemModel() {
        GravityFields.proxy.registerItemRenderer(this, 0, name);
    }

    /**
     * Sets the items's creative tab.
     *
     * @param tab The CreativeTab to set the Item's tab to.
     * @return The item who's tab was set.
     */
    @Override
    public ItemBase setCreativeTab(CreativeTabs tab){
        super.setCreativeTab(tab);
        return this;
    }
}
