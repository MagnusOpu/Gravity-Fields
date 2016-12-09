package net.magnusopu.gravityfields.world;

import net.magnusopu.gravityfields.block.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

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
public class ModWorldGenerator implements IWorldGenerator {

    /**
     * Entrance into the default world generation
     *
     * @param random A random integer generator.
     * @param chunkX The current chunk's position on the X axis
     * @param chunkZ The current chunk'z position on the Z axis
     * @param world The world to generate chunks in
     * @param chunkGenerator The class to generate chunks with
     * @param chunkProvider The class that provides chunks
     */
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider){
        if(world.provider.getDimension() == 0){
            generateOverworld(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
        }
    }

    /**
     * Custom chunk generation for the overworld
     *
     * @param random A random integer generator.
     * @param chunkX The current chunk's position on the X axis
     * @param chunkZ The current chunk's position on the Y axis
     * @param world The world to generate chunks in
     * @param chunkGenerator The class to generate chunks with
     * @param chunkProvider The class that provides chunks
     */
    public void generateOverworld(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider){
        generateOre(ModBlocks.gravityOreBlock.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 16, 64, 2 + random.nextInt(3), 3);
    }

    /**
     *
     * @param ore The ore to generate
     * @param world The world to generate ore in
     * @param random A random integer generator.
     * @param x The x position to generate the ore in.
     * @param z The z position to generate the ore in.
     * @param minY The minimum y position to generate the ore in.
     * @param maxY The maximum y position to generate the ore in.
     * @param size The size of the ore vein.
     * @param chances The amount of times it attempts to generate the ore.
     */
    private void generateOre(IBlockState ore, World world,Random random, int x, int z, int minY, int maxY, int size, int chances){
        int deltaY = maxY - minY;

        for (int i=0; i < chances; i++){
            BlockPos pos = new BlockPos(x + random.nextInt(16), minY + random.nextInt(deltaY), z + random.nextInt(16));

            WorldGenMinable generator = new WorldGenMinable(ore, size);
            generator.generate(world, random, pos);
        }
    }
}
