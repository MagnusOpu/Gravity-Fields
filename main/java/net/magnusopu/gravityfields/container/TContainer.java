package net.magnusopu.gravityfields.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;

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
public class TContainer extends ContainerBase {

    protected int currentTicks;
    protected int currentTickMax;

    /**
     * TContainer is a container class that adds ticking functionality and extends ContainerBase
     *
     * @param inventoryPlayer The inventory of the player interacting with the container.
     * @param inventory The inventory of the container.
     */
    public TContainer(InventoryPlayer inventoryPlayer, IInventory inventory){
        super(inventoryPlayer, inventory);
    }

    /**
     * Called on any changes happening within the container and updates the progress par constantly.
     */
    @Override
    public void detectAndSendChanges(){
        super.detectAndSendChanges();

        for(int i=0;i<listeners.size(); i++){
            IContainerListener iListener = (IContainerListener)listeners.get(i);

            if(currentTicks != tileBase.getField(0)){
                iListener.sendProgressBarUpdate(this, 0, tileBase.getField(0));
            }

            if(currentTickMax != tileBase.getField(1)){
                iListener.sendProgressBarUpdate(this, 1, tileBase.getField(1));
            }
        }

        currentTicks = tileBase.getField(0);
        currentTickMax = tileBase.getField(1);
    }

}
