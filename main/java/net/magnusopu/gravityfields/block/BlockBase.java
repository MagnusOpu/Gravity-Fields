package net.magnusopu.gravityfields.block;

import net.magnusopu.gravityfields.GravityFields;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;

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
public class BlockBase extends Block {

    protected String name;

    /**
     * BlockBase is a basic constructor class for Gravity Field Blocks.
     *
     * @param material The material to make the block.
     * @param name The unlocalized name for the block.
     */
    public BlockBase(Material material, String name){
        super(material);
        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(GravityFields.gravityTab);
    }

    /**
     * Registers an ItemBlock with this ItemModel.
     *
     * @param itemBlock The ItemBlock to register.
     */
    public void registerItemModel(ItemBlock itemBlock){
        GravityFields.proxy.registerItemRenderer(itemBlock, 0, name);
    }

    /**
     * Sets the block's creative tab.
     *
     * @param tab The CreativeTab to set the Block's tab to.
     * @return The block who's tab was set.
     */
    @Override
    public BlockBase setCreativeTab(CreativeTabs tab){
        super.setCreativeTab(tab);
        return this;
    }
}
