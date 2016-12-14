package net.magnusopu.gravityfields.image;

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

public class ImageInfo {

    private boolean addInvAsOffsetH = false;
    private boolean addInvAsOffsetV = false;
    private int x, y, textureX, textureY, sizeX, sizeY;

    /**
     * ImageInfo is a class that holds information about an image to be cut from a larger image (loading arrow in a container or sprite in a spritemap)
     *
     * @param addInvAsOffsetH Whether or not to add the width of the inv to the textureX position
     * @param addInvAsOffsetV Whether or not to add the height of the inv to the textureY position
     * @param textureX The position to draw the image
     * @param textureY The position to draw the image
     * @param textureX The textureX position of the image
     * @param textureY The textureY position of the image
     * @param sizeX The textureX size of the image
     * @param sizeY The textureY size of the image
     */
    ImageInfo(boolean addInvAsOffsetH, boolean addInvAsOffsetV, int x, int y, int textureX, int textureY, int sizeX, int sizeY){
        this.addInvAsOffsetH = addInvAsOffsetH;
        this.addInvAsOffsetV = addInvAsOffsetV;
        this.x = x;
        this.y = y;
        this.textureX = textureX;
        this.textureY = textureY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    /**
     * Getter for addInvAsOffsetH
     *
     * @return addInvAsOffsetH
     */
    public boolean addInvAsOffsetH() {
        return addInvAsOffsetH;
    }

    /**
     * Setter for addInvAsOffsetH
     *
     * @param addInvAsOffsetH The value to set addInvAsOffsetH to
     */
    public void setAddInvAsOffsetH(boolean addInvAsOffsetH) {
        this.addInvAsOffsetH = addInvAsOffsetH;
    }

    /**
     * Getter for addInvAsOffsetV
     *
     * @return addInvAsOffsetV
     */
    public boolean addInvAsOffsetV() {
        return addInvAsOffsetV;
    }

    /**
     * Setter for addInvAsOffsetH
     *
     * @param addInvAsOffsetV The value to set addInvAsOffsetV to
     */
    public void setAddInvAsOffsetV(boolean addInvAsOffsetV) {
        this.addInvAsOffsetV = addInvAsOffsetV;
    }

    /**
     * Getter for x position to draw the image
     *
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Setter for x position to draw the image
     *
     * @param x The value to set x to
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Getter for y position to draw the image
     *
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * Setter for y position to draw the image
     *
     * @param y The value to set y to
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Getter for textureX size of image
     *
     * @return sizeX
     */
    public int getSizeX() {
        return sizeX;
    }

    /**
     * Setter for the size textureX size of image
     *
     * @param sizeX The value to set sizeX to
     */
    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    /**
     * Getter for textureY size of image
     *
     * @return sizeY
     */
    public int getSizeY() {
        return sizeY;
    }

    /**
     * Setter for the size textureY of image
     *
     * @param sizeY The value to set sizeY to
     */
    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    /**
     * Getter for textureX position of image
     *
     * @return textureX
     */
    public int getTextureX() {
        return textureX;
    }

    /**
     * Setter for the textureX position of image
     *
     * @param textureX The value to set textureX to
     */
    public void setTextureX(int textureX) {
        this.textureX = textureX;
    }

    /**
     * Getter for textureY position of image
     *
     * @return textureY
     */
    public int getTextureY() {
        return textureY;
    }

    /**
     * Setter for the textureY position of image
     *
     * @param textureY The value to set textureY to
     */
    public void setTextureY(int textureY) {
        this.textureY = textureY;
    }
}
