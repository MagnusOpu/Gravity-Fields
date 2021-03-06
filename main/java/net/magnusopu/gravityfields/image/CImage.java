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

public enum CImage {
    GRAVITY_STONE(new ImageInfo(false, true, 113, 30, 0, 0, 32, 33)),
    STRENGTH_TEXT(new ImageInfo(false, true, 14, 44, 32, 0, 91, 10)),
    RANGE_TEXT(new ImageInfo(false, true, 23, 45, 32, 10, 79, 10));

    private ImageInfo info;

    /**
     * CImage is a basic enum that holds a list of all ImageInfos applicable to CGui classes
     * @param info An ImageInfo that's applicable to IGui classes
     */
    CImage(ImageInfo info){
        this.info = info;
    }

    /**
     * Getter for info
     * @return info
     */
    public ImageInfo getInfo(){ return info; }

    /**
     * Converts a list of CImage[] into their respective infos
     * @param infos The list of CImage[] to convert
     * @return The CImage[] converted into ImageInfo[]
     */
    public static ImageInfo[] getInfoList(CImage[] infos){
        if(infos == null)
            return new ImageInfo[0];
        ImageInfo[] infoList = new ImageInfo[infos.length];
        for(int i=0;i<infos.length;i++){
            infoList[i] = infos[i].getInfo();
        }
        return infoList;
    }
}