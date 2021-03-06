package net.magnusopu.gravityfields.tileentity;

import net.magnusopu.gravityfields.item.IOItemConfig;
import net.minecraft.item.ItemStack;

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

public class GEGTileEntity extends IOTileEntity {

    /**
     * GEGTileEntity is a class that extends IOTileEntity that defines a type of tile entity built for the block gravityEssenceGenerator.
     */
    public GEGTileEntity(){
        super(new ItemStack[2], "gravityEssenceGenerator", 0, 1, "gravityEssenceGenerator", IOItemConfig.GRAVITY_ORE_TO_ESSENCE.getConfig());
    }

}
