package net.magnusopu.gravityfields.gui;

import net.magnusopu.gravityfields.container.ContainerGravityGenerator;
import net.magnusopu.gravityfields.tileentity.TileEntityBase;
import net.magnusopu.gravityfields.tileentity.TileEntityGravityGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

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
public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x,y,z));

        if(tileEntity != null){
            if(ID == EnumGUI.GRAVITY_GENERATOR.ordinal()){
                TileEntityGravityGenerator tileEntityG = (TileEntityGravityGenerator)tileEntity;
                return new ContainerGravityGenerator(player.inventory, tileEntityG, tileEntityG.getInputSlot());
            }
            // TODO: Add remaining ID recognition on opening GUI
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x,y,z));

        if(tileEntity != null){
            if(tileEntity instanceof TileEntityBase) {
                TileEntityBase tileEntityB = (TileEntityBase)tileEntity;
                if (ID == EnumGUI.GRAVITY_GENERATOR.ordinal()) {
                    if(tileEntity instanceof TileEntityGravityGenerator){
                        TileEntityGravityGenerator tileEntityG = (TileEntityGravityGenerator)tileEntity;
                        return new GuiGravityGenerator(new ContainerGravityGenerator(player.inventory, tileEntityG, tileEntityG.getInputSlot()), player.inventory, tileEntityG, tileEntityG.getName());
                    }
                }
                // TODO: Add remaining ID recognition on opening GUI
            }
        }

        return null;
    }

}
