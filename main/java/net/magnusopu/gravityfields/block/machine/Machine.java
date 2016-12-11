package net.magnusopu.gravityfields.block.machine;

import net.magnusopu.gravityfields.GravityFields;
import net.magnusopu.gravityfields.block.ModBlocks;
import net.magnusopu.gravityfields.tileentity.TileEntityBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

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

public class Machine extends BlockContainer {

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    private static boolean hasTileEntity;
    protected static String name;

    public Machine(String name){
        super(Material.ROCK);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(GravityFields.gravityTab);
        setHardness(2.0f);
        setResistance(6.0f);
        setHarvestLevel("pickaxe", 2);
        this.name = name;
    }

    /**
     * Registers an ItemBlock with this ItemModel.
     *
     * @param itemBlock The ItemBlock to register.
     */
    public void registerItemModel(ItemBlock itemBlock){
        GravityFields.proxy.registerItemRenderer(itemBlock, 0, name);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state){
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random ran, int fortune){
        return Item.getItemFromBlock(ModBlocks.gravityGenerator);
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state){
        if (!worldIn.isRemote){

            EnumFacing enumfacing = (EnumFacing)(state.getValue(FACING));

            if(enumfacing == EnumFacing.NORTH
                    && isSolid(worldIn, pos, EnumFacing.NORTH)
                    && !isSolid(worldIn, pos, EnumFacing.SOUTH)){
                enumfacing = EnumFacing.SOUTH;
            } else if(enumfacing == EnumFacing.SOUTH
                    && isSolid(worldIn, pos, EnumFacing.SOUTH)
                    && !isSolid(worldIn, pos, EnumFacing.NORTH)) {
                enumfacing = EnumFacing.NORTH;
            } else if(enumfacing == EnumFacing.EAST
                    && isSolid(worldIn, pos, EnumFacing.EAST)
                    && !isSolid(worldIn, pos, EnumFacing.WEST)) {
                enumfacing = EnumFacing.WEST;
            } else if(enumfacing == EnumFacing.WEST
                    && isSolid(worldIn, pos, EnumFacing.WEST)
                    && !isSolid(worldIn, pos, EnumFacing.EAST)) {
                enumfacing = EnumFacing.EAST;
            }

            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) { return null; }

    /**
     * Gets the {@link IBlockState} to place
     * @param worldIn The world the block is being placed in
     * @param pos The position the block is being placed at
     * @param facing The side the block is being placed on
     * @param hitX The X coordinate of the hit vector
     * @param hitY The Y coordinate of the hit vector
     * @param hitZ The Z coordinate of the hit vector
     * @param meta The metadata of {@link ItemStack} as processed by {@link Item#getMetadata(int)}
     * @param placer The entity placing the block
     * @param stack The stack being used to place this block
     * @return The state to be placed in the world
     */
    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, ItemStack stack){
        return getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack){
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()));
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state){
        if(!hasTileEntity){
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if(tileEntity instanceof TileEntityBase){
                InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityBase)tileEntity);
                worldIn.updateComparatorOutputLevel(pos, this);
            }
        }
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player){
        return new ItemStack(Item.getItemFromBlock(ModBlocks.gravityBlock));
    }

    @Override
    public int getMetaFromState(IBlockState state){
        return state.getValue(FACING).getIndex();
    }

    @Override
    protected BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }

    @Override
    @Deprecated
    public IBlockState getStateFromMeta(int meta){
        EnumFacing enumFacing = EnumFacing.getFront(meta);
        if(enumFacing.getAxis() == EnumFacing.Axis.Y){
            enumFacing = EnumFacing.NORTH;
        }
        return getDefaultState().withProperty(FACING, enumFacing);
    }

    @Override
    @Deprecated
    public boolean isOpaqueCube(IBlockState state){
        return false;
    }

    @Override
    @Deprecated
    public boolean isFullCube(IBlockState state){
        return false;
    }

    public boolean isSolid(World worldIn, BlockPos pos, EnumFacing facing){
        BlockPos offset = pos.offset(facing);
        Block block = worldIn.getBlockState(offset).getBlock();
        EnumFacing reverse = facing.getOpposite();
        return block.isBlockSolid(worldIn, offset, reverse);
    }


}
