package net.magnusopu.gravityfields.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

public class BlockOre extends BlockBase {

    private ArrayList<DropStruct> drops;

    /**
     * BlockOre is a basic class for Blocks that act like Ores.
     *
     * @param name The unlocalized name to set the BlockOre.
     * @param drops An ArrayList of DropStructs containing all data on what to drop, the chance for it to drop, and how many to drop.
     */
    public BlockOre(String name, ArrayList<DropStruct> drops){
        super(Material.ROCK, name);
        this.setHardness(10.0f);
        this.setResistance(20.0f);
        this.setHarvestLevel("pickaxe", 2);
        this.drops = drops;
    }

    /**
     * BlockOre is a basic class for Blocks that act like Ores.
     *
     * @param name The unlocalized name to set the BlockOre.
     */
    public BlockOre(String name){
        super(Material.ROCK, name);
        this.setHardness(10.0f);
        this.setResistance(20.0f);
        this.setHarvestLevel("pickaxe", 2);
    }

    /**
     * This returns a complete list of items dropped from this block based on the "drops" ArrayList.
     *
     * @param world The current world
     * @param pos Block position in world
     * @param state Current state
     * @param fortune Breakers fortune level
     * @return An ArrayList containing all items this block drops
     */
    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {

        List<ItemStack> ret = new java.util.ArrayList<ItemStack>();
        boolean worked = false;
        Random rand = world instanceof World ? ((World) world).rand : RANDOM;

        if(drops != null) { // Checking if custom drops is true
            if(!drops.isEmpty()) { // Checking for funny business
                boolean addedItem = false;
                for (DropStruct drop : drops) {
                    int dropChance = drop.getChanceDropped();
                    if(rand.nextInt(100) <= dropChance) {
                        int count = 1;
                        int maxDropped = drop.getMaxDropped();
                        int minDropped = drop.getMinDropped();

                        // Getting amount to drop
                        if (maxDropped > 1 && minDropped < maxDropped) {
                            count = rand.nextInt(maxDropped - minDropped) + minDropped;
                        }

                        for (int i = 0; i < count; i++) {
                            Item item = drop.getItemDropped();
                            if (item != null) {
                                ret.add(new ItemStack(item, 1, this.damageDropped(state)));
                                addedItem = true;
                            }
                        }
                    }
                }
                worked = addedItem; // Contingency in the case something goes wrong
            }
        }

        // Adding a default drop value if no custom drops or something messed up
        if(!worked)
            ret.add(new ItemStack(getItemDropped(state, rand, fortune)));

        return ret;
    }



    /**
     *
     * @param tab The CreativeTab to set the Block's tab to.
     * @return The block who's tab was set.
     */
    @Override
    public BlockOre setCreativeTab(CreativeTabs tab){
        super.setCreativeTab(tab);
        return this;
    }


}
