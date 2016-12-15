package net.magnusopu.gravityfields.player;

import net.minecraft.entity.player.EntityPlayer;

import java.util.ArrayList;

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

public class PlayerGravity {

    private EntityPlayer player;
    private int strength;
    private int range;
    private double initY;
    private boolean pendingChange;

    /**
     * PlayerGravity is a struct class containing information related to players affected by custom gravity
     *
     * @param player The player affected
     * @param strength The current strength multiplier of gravity
     * @param range The current effective range the player is under
     */
    public PlayerGravity(EntityPlayer player, int strength, int range){
        this.player = player;
        this.strength = strength;
        this.range = range;
    }

    /**
     * Getter for player
     *
     * @return player
     */
    public EntityPlayer getPlayer(){
        return player;
    }

    /**
     * Setter for player
     *
     * @param player Player to set
     */
    public void setPlayer(EntityPlayer player){
        this.player = player;
    }

    /**
     * Getter for strength
     *
     * @return strength
     */
    public int getStrength(){
        return strength;
    }
    /**
     * Setter for strength
     *
     * @param strength Strength to set
     */
    public void setStrength(int strength){
        this.strength = strength;
    }

    /**
     * Getter for range
     *
     * @return range
     */
    public int getRange(){
        return range;
    }

    /**
     * Setter for range
     *
     * @param range Range to set
     */
    public void setRange(int range){
        this.range = range;
    }

    /**
     * Getter for initY
     *
     * @return inity
     */
    public double getInitY() {
        return initY;
    }

    /**
     * Setter for initY
     *
     * @param initY InitY to set
     */
    public void setInitY(double initY) {
        this.initY = initY;
    }

    /**
     * Getter for pendingChange
     *
     * @return pendingChange
     */
    public boolean isPendingChange() {
        return pendingChange;
    }

    /**
     * Setter for pendingChange
     *
     * @param pendingChange PendingChange to set
     */
    public void setPendingChange(boolean pendingChange) {
        this.pendingChange = pendingChange;
    }

    /**
     * Finds a playerGrav within the arg list dependent on arg player
     *
     * @param player The player to search through the list for
     * @param list The list to search for (Normally GravityFields.playerGravities)
     * @return PlayerGravity with an entityPlayer matching player arg
     */
    public static PlayerGravity findByPlayer(EntityPlayer player, ArrayList<PlayerGravity> list){
        if(list == null){ return null; }
        for(PlayerGravity grav : list){
            if(grav.player == player){
                return grav;
            }
        }
        return null;
    }
}
