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
public class CGui extends GuiBase {

    private int limit = 0;
    private int currentCount = 0;
    private boolean clicked = false;
    private boolean checkClicked = false;
    private boolean setPrev = false;
    private int ticksSinceClick = 0;
    private int prevMouseX = -99;
    private int prevMouseY = -99;
    private int upArrowX;
    private int upArrowY;
    private int downArrowX;
    private int downArrowY;

    /**
     * CGui is a class that extends GuiBase mainly for showing a GUI that counting functionality with buttons.
     *
     * @param container The container for the gui to show.
     * @param inventoryPlayer The inventory of the player to show.
     * @param inv The TileEntity inventory to show.
     * @param backgroundImages A list of background ImageInfo(s) to draw during the drawContainerBackgroundLayer() method
     */
    public CGui(Container container, InventoryPlayer inventoryPlayer, IInventory inv, ImageInfo... backgroundImages){
        super(container, inventoryPlayer, inv, "counter", backgroundImages);
        this.limit = inv.getField(0);
        this.currentCount = inv.getField(1);
        this.upArrowX = inv.getField(2);
        this.upArrowY = inv.getField(3);
        this.downArrowX = inv.getField(4);
        this.downArrowY = inv.getField(5);
    }

    // P.S. I'm aware it's stupid to do the ticking and clicking logic in the UI class but I'm too lazy to fix it.. Shut up..

    /**
     * Draws the container's background layer and does logic for mouse clicking.
     *
     * @param partialTicks I don't actually know what this does.
     * @param mouseX The current mouseX pos.
     * @param mouseY The current mouseY pos.
     */
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY){
        super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);

        currentCount = inv.getField(1);

        // Incrementing click cooldown timer if necessary
        if(ticksSinceClick < 20){
            ticksSinceClick++;
        }

        // Initializing the prev mouseX and mouseY values on first mouseInput() iteration
        if(setPrev){
            setPrev(mouseX, mouseY);
        }

        // Checking if mouse input was registered and mouse stayed in the same position, leaving only mousewheel and mouseclick left as options the player could have taken
        if(checkClicked){
            clicked = prevMouseX == mouseX && prevMouseY == mouseY;
            setPrev(mouseX, mouseY);
            checkClicked = false;
        }

        if(clicked){
            // Making sure it doesn't register multiple clicks every time the player clicks once
            if(ticksSinceClick >= 20) {
                ticksSinceClick = 0;

                // Checking if it's in bounds of up arrow
                if (mouseX >= upArrowX + marginHorizontal && mouseX <= upArrowX + marginHorizontal + 18) {
                    if (mouseY >= upArrowY + marginVertical && mouseY <= upArrowY + marginVertical + 18) {
                        if(currentCount < limit) {
                            currentCount++;
                            inv.setField(1, currentCount);
                        }
                    }
                }

                // Checking if it's in bounds of down arrow
                if (mouseX >= downArrowX + marginHorizontal && mouseX <= downArrowX + marginHorizontal + 18) {
                    if (mouseY >= downArrowY + marginVertical && mouseY <= downArrowY + marginVertical + 18) {
                        if(currentCount > 0) {
                            currentCount--;
                            inv.setField(1, currentCount);
                        }
                    }
                }
            }
            clicked = false;
        }

        String cc = Integer.toString(currentCount);
        int c1 = 0; int c2 = 0;
        int texture1Y; int texture2Y;
        int texture1X = xSize; int texture2X = xSize+9;
        int pos1X = marginHorizontal+upArrowX-1; int pos2X = marginHorizontal+upArrowX+9-1;
        int posY = marginVertical+upArrowY+17;
        int sizeX = 9;
        int sizeY = 18;

        if(cc.length() >= 1){
            c1 = Character.getNumericValue(cc.charAt(0));
        }
        if(cc.length() >= 2){
            c2 = Character.getNumericValue(cc.charAt(1));
        }

        c1 *= 18;
        c2 *= 18;

        texture1Y = c1;
        texture2Y = c2;

        // Flipping first and second pos to make 5 become 05 instead of 50 for example
        if(currentCount < 10) {
            drawTexturedModalRect(pos1X, posY, texture1X, texture2Y, sizeX, sizeY);
            drawTexturedModalRect(pos2X, posY, texture2X, texture1Y, sizeX, sizeY);
        } else {
            drawTexturedModalRect(pos1X, posY, texture1X, texture1Y, sizeX, sizeY);
            drawTexturedModalRect(pos2X, posY, texture2X, texture2Y, sizeX, sizeY);
        }

    }

    /**
     * Called to save the current mouseX, mouseY positions to the prev positions
     *
     * @param mouseX The current mouseX
     * @param mouseY The current mouseY
     */
    private void setPrev(int mouseX, int mouseY){
        prevMouseX = mouseX;
        prevMouseY = mouseY;
        setPrev = false;
    }

    /**
     * Called on every mouse input from entity opening this gui (Mouse movement, scroll wheel, or click for example)
     */
    public void mouseInput(){
        if(prevMouseX == -99 && prevMouseY == -99){
            setPrev = true;
        } else {
            checkClicked = true;
        }
    }
}
