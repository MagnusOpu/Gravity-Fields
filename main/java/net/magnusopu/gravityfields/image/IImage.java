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

public enum IImage {
    ARROW(new ImageInfo(true, false, 80, 35, 0, 0, 18, 16)),
    BAR(new ImageInfo(false, true, 26, 61, 0, 0, 126, 5)),
    STONE_1(new ImageInfo(true, false, 37, 47, 0, 0, 27, 7)),
    STONE_2(new ImageInfo(true, false, 109, 47, 0, 0, 27, 7));

    private ImageInfo info;

    /**
     * IImage is a basic enum that holds a list of all ImageInfos applicable to IGui classes
     * @param info An ImageInfo that's applicable to IGui classes
     */
    IImage(ImageInfo info){
        this.info = info;
    }

    /**
     * Getter for info
     * @return info
     */
    public ImageInfo getInfo(){
        return info;
    }

    /**
     * Converts a list of IImage[] into their respective infos
     * @param infos The list of IImage[] to convert
     * @return The IImage[] converted into ImageInfo[]
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
