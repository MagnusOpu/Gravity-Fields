package net.magnusopu.gravityfields.proxy;

import net.magnusopu.gravityfields.GravityFields;
import net.magnusopu.gravityfields.event.GravityFieldsEventHandler;
import net.magnusopu.gravityfields.gui.GuiHandler;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.NetworkRegistry;

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

public class CommonProxy {

    /**
     * Registers an item with the game by setting it's model resource location.
     *
     * @param item Item to be registered.
     * @param meta Meta of item to be registered.
     * @param id unlocalized name of item.
     */
    public void registerItemRenderer(Item item, int meta, String id){}

    /**
     * Initializing special classes and registering other things
     */
    public void init(){
        NetworkRegistry.INSTANCE.registerGuiHandler(GravityFields.instance, new GuiHandler());
        registerEventListeners();
    }

    /**
     * Registers all event listeners for the gravity fields mod
     */
    public void registerEventListeners(){
        System.out.println("Registering event listeners for mod "+GravityFields.name);
        MinecraftForge.EVENT_BUS.register(new GravityFieldsEventHandler());
    }

}
