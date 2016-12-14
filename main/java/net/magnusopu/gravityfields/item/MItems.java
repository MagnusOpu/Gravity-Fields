package net.magnusopu.gravityfields.item;

import net.magnusopu.gravityfields.gui.EnumGUI;
import net.minecraft.item.Item;
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

public final class MItems {

    public static ItemBase gravityOre;
    public static ItemBase gravityEssence;
    public static CItem gravityRangeStone;
    public static CItem gravityStrengthStone;

    /**
     * Initializes all registered items for the Gravity Fields mod.
     */
    public static void init(){
        gravityOre = register(new ItemBase("gravityOre"));
        gravityEssence = register(new ItemBase("gravityEssence"));
        gravityRangeStone = register(new CItem("gravityRangeStone", EnumGUI.GRAVITY_RANGE_STONE, 20, 10));
        gravityStrengthStone = register(new CItem("gravityStrengthStone", EnumGUI.GRAVITY_STRENGTH_STONE, 5, 3));
    }

    /**
     * Registers the item with the GameRegistry.
     *
     * @param item Item to be registered.
     * @param <T> Anything extending Item.
     * @return The registered item.
     */
    private static <T extends Item> T register(T item) {
        GameRegistry.register(item);

        if(item instanceof ItemBase)
            ((ItemBase)item).registerItemModel();

        return item;
    }
}
