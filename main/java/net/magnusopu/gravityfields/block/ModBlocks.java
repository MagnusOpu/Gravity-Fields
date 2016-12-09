package net.magnusopu.gravityfields.block;

import net.magnusopu.gravityfields.item.ModItems;
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
public class ModBlocks {

    public static BlockBase gravityBlock;
    public static BlockOre gravityOreBlock;

    public static void init(){
        gravityBlock = register(new BlockBase(Material.ROCK, "gravityBlock"));
        ArrayList<DropStruct> gravityOreDrops = new ArrayList<DropStruct>();
        gravityOreDrops.add(new DropStruct(ModItems.gravityOre, 1, 4, 100));
        gravityOreDrops.add(new DropStruct(ModItems.gravityRangeStone, 1, 1, 10));
        gravityOreDrops.add(new DropStruct(ModItems.gravityStrengthStone, 1, 1, 10));
        gravityOreBlock = register(new BlockOre("gravityOreBlock", gravityOreDrops));
    }

    private static <T extends Block> T register(T block, ItemBlock itemBlock){
        GameRegistry.register(block);
        GameRegistry.register(itemBlock);

        if(block instanceof BlockBase)
            ((BlockBase)block).registerItemModel(itemBlock);

        return block;
    }

    private static <T extends Block> T register(T block){
        ItemBlock itemBlock = new ItemBlock(block);

        itemBlock.setRegistryName(block.getRegistryName());

        return register(block, itemBlock);
    }
}
