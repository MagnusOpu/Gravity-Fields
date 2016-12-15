package net.magnusopu.gravityfields.tileentity;

import net.magnusopu.gravityfields.GravityFields;
import net.magnusopu.gravityfields.item.IOItem;
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
public class GFGTileEntity extends ITileEntity {

    /**
     * GFGTileEntity is a class that extends ITileEntity that defines a type of tile entity built for the block gravityFieldGenerator.
     */
    public GFGTileEntity(){
        super(new ItemStack[3], "gravityFieldGenerator", 0, "gravityFieldGenerator", IOItemConfig.GRAVITY_ORE_TO_GRAVITY.getConfig());
    }

    @Override
    public void update(){
        if(!worldObj.isRemote) {
            if(itemStackArray[inputSlot] != null) {
                currentTickMax = IOItem.findTicks(itemStackArray[inputSlot].getItem(), allowedItems);
            }
            if (currentTicks > 0) {
                currentTicks--;
            } else if (itemStackArray[inputSlot] != null) {
                currentTicks = currentTickMax;
                decrStackSize(inputSlot, 1);
            }
        }

        if(currentTicks > 0){
            if(!GravityFields.gravityFieldGenerators.contains(this)){
                GravityFields.gravityFieldGenerators.add(this);
            }
        } else if(itemStackArray[inputSlot] == null){
            if(GravityFields.gravityFieldGenerators.contains(this)){
                GravityFields.gravityFieldGenerators.remove(this);
            }
        }
    }

    /**
     * Determines whether or not this field generator is emitting a gravity field
     *
     * @return If ticking true, else false
     */
    public boolean isActive(){
        return currentTicks > 0;
    }
}
