package net.magnusopu.gravityfields.recipe;

import net.magnusopu.gravityfields.block.ModBlocks;
import net.magnusopu.gravityfields.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

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
public class ModRecipes {

    public static void init(){
        // Adding recipe for Gravity Block
        GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.gravityBlock), ModItems.gravityOre, ModItems.gravityOre, ModItems.gravityOre, ModItems.gravityOre);
    }

}
