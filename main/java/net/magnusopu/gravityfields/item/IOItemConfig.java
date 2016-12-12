package net.magnusopu.gravityfields.item;

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

public enum IOItemConfig {
    GRAVITY_ORE_TO_ESSENCE(new IOItem(MItems.gravityOre, MItems.gravityEssence, 150));

    private IOItem config;

    /**
     * A nice wrapper referencing IOItem configurations so I don't have to constalty write new IOItem()
     *
     * @param config The IOItem config in question
     */
    IOItemConfig(IOItem config){
        this.config = config;
    }

    /**
     * Gets the configuration of IOItem
     *
     * @return config
     */
    public IOItem getConfig(){
        return config;
    }
}
