package net.magnusopu.gravityfields.block;

import net.magnusopu.gravityfields.block.machine.GravityEssenceGenerator;
import net.magnusopu.gravityfields.block.machine.GravityFieldGenerator;
import net.magnusopu.gravityfields.block.machine.Machine;
import net.magnusopu.gravityfields.item.MItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;

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

public class MBlocks {

    public static BlockBase gravityBlock;
    public static BlockOre gravityOreBlock;
    public static GravityEssenceGenerator gravityEssenceGenerator;
    public static GravityFieldGenerator gravityFieldGenerator;

    /**
     * Initializes all registered blocks for the Gravity Fields mod.
     */
    public static void init(){
        gravityBlock = register(new BlockBase(Material.ROCK, "gravityBlock"));

        ArrayList<DropStruct> gravityOreDrops = new ArrayList<DropStruct>();
            // Adding drops for gravityOre
            gravityOreDrops.add(new DropStruct(MItems.gravityOre, 1, 4, 100));
            gravityOreDrops.add(new DropStruct(MItems.gravityRangeStone, 1, 1, 10));
            gravityOreDrops.add(new DropStruct(MItems.gravityStrengthStone, 1, 1, 10));
        gravityOreBlock = register(new BlockOre("gravityOreBlock", gravityOreDrops));

        gravityEssenceGenerator = register(new GravityEssenceGenerator());
        gravityFieldGenerator = register(new GravityFieldGenerator());
    }

    /**
     * Registers the block and itemBlock with the GameRegistry.
     *
     * @param block Block to be registered.
     * @param itemBlock ItemBlock to be registered.
     * @param <T> Anything extending Block.
     * @return The registered block.
     */
    private static <T extends Block> T register(T block, ItemBlock itemBlock){
        GameRegistry.register(block);
        GameRegistry.register(itemBlock);

        if(block instanceof BlockBase) {
            ((BlockBase) block).registerItemModel(itemBlock);
        }

        if(block instanceof Machine) {
            ((Machine) block).registerItemModel(itemBlock);
        }

        return block;
    }

    /**
     * Registers the block with the GameRegistry.
     *
     * @param block Block to be registered.
     * @param <T> Anything extending Block.
     * @return The registered block.
     */
    private static <T extends Block> T register(T block){
        ItemBlock itemBlock = new ItemBlock(block);

        itemBlock.setRegistryName(block.getRegistryName());

        return register(block, itemBlock);
    }
}
