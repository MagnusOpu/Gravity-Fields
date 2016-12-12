package net.magnusopu.gravityfields.block.machine;

import net.magnusopu.gravityfields.GravityFields;
import net.magnusopu.gravityfields.gui.EnumGUI;
import net.magnusopu.gravityfields.tileentity.GEGTileEntity;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

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

public class GravityEssenceGenerator extends Machine {

    public GravityEssenceGenerator(){
        super("gravityEssenceGenerator");
    }

    /**
     * Overrides default onBlockActivated method and opens a GUI on activation
     */
    @Override
    public boolean onBlockActivated(World parWorld, BlockPos parBlockPos, IBlockState state, EntityPlayer parPlayer, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ){
        if(!parWorld.isRemote){
            parPlayer.openGui(GravityFields.instance, EnumGUI.GRAVITY_GENERATOR.ordinal(), parWorld, parBlockPos.getX(), parBlockPos.getY(), parBlockPos.getZ());
        }

        return true;
    }

    /**
     * Returns a new instance of a gravity generator's tile entity class. Called on placing the block.
     */
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        System.out.println("GravityEssenceGenerator createNewTileEntity()");
        hasTileEntity = true;
        return new GEGTileEntity();
    }

}
