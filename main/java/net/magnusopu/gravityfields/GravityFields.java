package net.magnusopu.gravityfields;

import net.magnusopu.gravityfields.block.ModBlocks;
import net.magnusopu.gravityfields.client.GravityTab;
import net.magnusopu.gravityfields.item.ModItems;
import net.magnusopu.gravityfields.proxy.CommonProxy;
import net.magnusopu.gravityfields.recipe.ModRecipes;
import net.magnusopu.gravityfields.tileentity.ModTileEntities;
import net.magnusopu.gravityfields.world.ModWorldGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Copyright (C) 2016 MagnusOpu.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact me at zacharydsturtz@gmail.com
 */

/**
 * The main class of the Gravity Fields mod.
 */
@Mod(modid = GravityFields.modId, name = GravityFields.name, version = GravityFields.version, acceptedMinecraftVersions = "[1.10.2]")
public class GravityFields {

    public static final String modId = "gravityfields";
    public static final String name = "Gravity Fields";
    public static final String version = "1.0.0";

    @SidedProxy(serverSide = "net.magnusopu.gravityfields.proxy.CommonProxy", clientSide = "net.magnusopu.gravityfields.proxy.ClientProxy")
    public static CommonProxy proxy;

    @Mod.Instance(modId)
    public static GravityFields instance;

    public static final GravityTab gravityTab = new GravityTab();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        System.out.println(name + " is loading!");
        ModItems.init();
        ModBlocks.init();
        ModRecipes.init();
        ModTileEntities.init();
        GameRegistry.registerWorldGenerator(new ModWorldGenerator(), 3);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){

    }
}
