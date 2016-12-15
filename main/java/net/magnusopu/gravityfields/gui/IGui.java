package net.magnusopu.gravityfields.gui;

import net.magnusopu.gravityfields.image.ImageInfo;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
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
public class IGui extends GuiBase {

    protected ImageInfo progressBar;
    protected boolean isReverse;

    /**
     * IGui is a class that extends GuiBase mainly for showing a GUI that has a single input which is consumed over time.
     *
     * @param container The container for the gui to show.
     * @param inventoryPlayer The inventory of the player to show.
     * @param inv The TileEntity inventory to show.
     * @param name The name of the gui resource location.
     * @param progressBar The progression bar to display a portion of
     * @param backgroundImages A list of background ImageInfo(s) to draw during the drawContainerBackgroundLayer() method
     */
    public IGui(Container container, InventoryPlayer inventoryPlayer, IInventory inv, String name, ImageInfo progressBar, boolean isReverse, ImageInfo... backgroundImages){
        super(container, inventoryPlayer, inv, name, backgroundImages);
        this.progressBar = progressBar;
        this.isReverse = isReverse;
    }

    /**
     * IGui is a class that extends GuiBase mainly for showing a GUI that has a single input which is consumed over time.
     *
     * @param container The container for the gui to show.
     * @param inventoryPlayer The inventory of the player to show.
     * @param inv The TileEntity inventory to show.
     * @param name The name of the gui resource location.
     */
    public IGui(Container container, InventoryPlayer inventoryPlayer, IInventory inv, String name){
        super(container, inventoryPlayer, inv, name, null);
    }

    /**
     * Draws the container's background layer.
     *
     * @param partialTicks I don't actually know what this does.
     * @param mouseX The current mouseX pos.
     * @param mouseY The current mouseY pos.
     */
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY){
        super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);

        if(progressBar != null) {
            int progressLevel = getProgressLevel(progressBar.getSizeX());
            int xOffset = 0; int yOffset = 0;
            if(progressBar.addInvAsOffsetH()){
                xOffset = xSize;
            }
            if(progressBar.addInvAsOffsetV()){
                yOffset = ySize;
            }
            if(!isReverse) {
                drawTexturedModalRect(marginHorizontal + progressBar.getX(), marginVertical + progressBar.getY(),
                        xOffset + progressBar.getTextureX(), yOffset + progressBar.getTextureY(),
                        progressLevel, progressBar.getSizeY());
            } else {
                if(inv.getField(0) != 0) {
                    drawTexturedModalRect(marginHorizontal + progressBar.getX(), marginVertical + progressBar.getY(),
                            xOffset + progressBar.getTextureX(), yOffset + progressBar.getTextureY(),
                            progressLevel, progressBar.getSizeY());
                }
            }
        }
    }

    /**
     * Returns the current progress of whatever action is currently happening.
     *
     * @param progressIndicatorPixelWidth The length of the total progress bar.
     * @return The amount of pixels of the progress bar to show.
     */
    private int getProgressLevel(int progressIndicatorPixelWidth){
        int currentTicks = inv.getField(0);
        int currentTickMax = inv.getField(1);
        return currentTickMax != 0 && currentTicks != 0 ? currentTicks * progressIndicatorPixelWidth / currentTickMax : 0;
    }

}
