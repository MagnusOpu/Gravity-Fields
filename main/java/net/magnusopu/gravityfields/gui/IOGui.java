package net.magnusopu.gravityfields.gui;

import net.magnusopu.gravityfields.GravityFields;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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

@SideOnly(Side.CLIENT)
public class IOGui extends GuiContainer {

    private ResourceLocation guiTextures;
    private InventoryPlayer inventoryPlayer;
    private IInventory tileBase;

    /**
     * IOGui is a class that extends GuiContainer mainly for showing a GUI that has a single input, performs some action on it then sends that into a single output slot.
     *
     * @param container The container for the gui to show.
     * @param inventoryPlayer The inventory of the player to show.
     * @param tileBase The TileEntity inventory to show.
     * @param name The name of the gui resource location.
     */
    public IOGui(Container container, InventoryPlayer inventoryPlayer, IInventory tileBase, String name){
        super(container);

        guiTextures = new ResourceLocation(GravityFields.modId + ":textures/gui/container/"+name+".png");

        this.inventoryPlayer = inventoryPlayer;
        this.tileBase = tileBase;
    }

    /**
     * Draws the container's foreground layer.
     *
     * @param mouseX The current mouseX pos.
     * @param mouseY The current mouseY pos.
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
        String s = tileBase.getDisplayName().getUnformattedText();
        fontRendererObj.drawString(s, xSize/2-fontRendererObj.getStringWidth(s)/2, 6, 4210752);
        fontRendererObj.drawString(inventoryPlayer.getDisplayName().getUnformattedText(), 8, ySize - 96 + 2, 4210752);
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
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(guiTextures);
        int marginHorizontal = (width - xSize) / 2;
        int marginVertical = (height - ySize) / 2;
        drawTexturedModalRect(marginHorizontal, marginVertical, 0, 0, xSize, ySize);

        int progressLevel = getProgressLevel(18);
        drawTexturedModalRect(marginHorizontal + 80, marginVertical + 35, xSize, 0, progressLevel, 16);
    }

    /**
     * Returns the current progress of whatever action is currently happening.
     *
     * @param progressIndicatorPixelWidth The length of the total progress bar.
     * @return The amount of pixels of the progress bar to show.
     */
    private int getProgressLevel(int progressIndicatorPixelWidth){
        int currentTicks = tileBase.getField(0);
        int currentTickMax = tileBase.getField(1);
        return currentTickMax != 0 && currentTicks != 0 ? currentTicks*progressIndicatorPixelWidth/currentTickMax : 0;
    }
}
