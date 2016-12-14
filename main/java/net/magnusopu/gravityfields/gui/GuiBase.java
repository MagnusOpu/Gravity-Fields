package net.magnusopu.gravityfields.gui;

import net.magnusopu.gravityfields.GravityFields;
import net.magnusopu.gravityfields.image.ImageInfo;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

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
public class GuiBase extends GuiContainer {


    protected ResourceLocation guiTextures;
    protected InventoryPlayer inventoryPlayer;
    protected IInventory inv;
    protected ImageInfo[] backgroundImages;
    protected int inventoryNameXOffset;
    protected int inventoryNameYOffset;
    protected int marginHorizontal = (width - xSize) / 2;
    protected int marginVertical = (height - ySize) / 2;

    /**
     * GuiBase is a basic gui constructor class for Gravity Field guis.
     *
     * @param container The container for the gui to show.
     * @param inventoryPlayer The inventory of the player to show.
     * @param inv The TileEntity inventory to show.
     * @param name The name of the gui resource location.
     * @param backgroundImages A list of background ImageInfo(s) to draw during the drawContainerBackgroundLayer() method
     */
    public GuiBase(Container container, InventoryPlayer inventoryPlayer, IInventory inv, String name, ImageInfo... backgroundImages){
        super(container);

        guiTextures = new ResourceLocation(GravityFields.modId + ":textures/gui/container/"+name+".png");

        this.inventoryPlayer = inventoryPlayer;
        this.inv = inv;

        if(backgroundImages == null){
            this.backgroundImages = new ImageInfo[0];
        } else {
            this.backgroundImages = backgroundImages;
        }
    }

    public GuiBase(Container container, InventoryPlayer inventoryPlayer, IInventory inv, String name, int offsetInventoryNameX, int offsetInventoryNameY, ImageInfo... backgroundImages){
        super(container);

        guiTextures = new ResourceLocation(GravityFields.modId + ":textures/gui/container/"+name+".png");

        this.inventoryPlayer = inventoryPlayer;
        this.inv = inv;

        if(backgroundImages == null){
            this.backgroundImages = new ImageInfo[0];
        } else {
            this.backgroundImages = backgroundImages;
        }

        this.inventoryNameXOffset = offsetInventoryNameX;
        this.inventoryNameYOffset = offsetInventoryNameY;
    }


    /**
     * Draws the container's foreground layer.
     *
     * @param mouseX The current mouseX pos.
     * @param mouseY The current mouseY pos.
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
        String s = inv.getDisplayName().getUnformattedText();
        fontRendererObj.drawString(s, xSize/2-fontRendererObj.getStringWidth(s)/2, 6, 4210752);
        fontRendererObj.drawString(inventoryPlayer.getDisplayName().getUnformattedText(), 8+inventoryNameXOffset, ySize - 94+inventoryNameYOffset, 4210752);
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

        marginHorizontal = (width - xSize) / 2;
        marginVertical = (height - ySize) / 2;

        drawTexturedModalRect(marginHorizontal, marginVertical, 0, 0, xSize, ySize);

        for(ImageInfo backgroundImage : backgroundImages){
            int offsetX = 0;
            int offsetY = 0;
            if(backgroundImage.addInvAsOffsetH())
                offsetX = xSize;
            if(backgroundImage.addInvAsOffsetV())
                offsetY = ySize;
            drawTexturedModalRect(marginHorizontal+backgroundImage.getX(),
                    marginVertical+backgroundImage.getY(),
                    offsetX+backgroundImage.getTextureX(),
                    offsetY+backgroundImage.getTextureY(),
                    backgroundImage.getSizeX(),
                    backgroundImage.getSizeY());
        }
    }

    /**
     * Getter for inventoryPlayer
     *
     * @return inventoryPlayer
     */
    public InventoryPlayer getInventoryPlayer(){
        return inventoryPlayer;
    }

    /**
     * Getter for inv
     *
     * @return inv
     */
    public IInventory getInventory(){
        return inv;
    }
}
