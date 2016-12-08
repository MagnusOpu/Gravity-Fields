package net.magnusopus.mod_001;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Copyright (C) 2016 MagnusOpus.
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

@Mod(modid = Mod001.modId, name = Mod001.name, version = Mod001.version, acceptedMinecraftVersions = "[1.10.2]")
public class Mod001 {

    public static final String modId = "mod001";
    public static final String name = "Mod 001";
    public static final String version = "1.0.0";

    @Mod.Instance(modId)
    public static Mod001 instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        System.out.println(name + " is loading!");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){

    }
}
