package net.magnusopu.gravityfields.item;

import net.magnusopu.gravityfields.GravityFields;
import net.magnusopu.gravityfields.gui.EnumGUI;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

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

public class CItem extends ItemBase {

    protected EnumGUI gui;
    protected int limit;
    protected int currentCount;

    /**
     * CItem is a class constructor for all items which could be modifications for a Gravity Field Generator.
     *
     * @param name The unlocalized name of the item.
     */
    public CItem(String name, EnumGUI gui, int limit){
        super(name);
        this.gui = gui;
        this.limit = limit;
    }

    /**
     * CItem is a class constructor for all items which could be modifications for a Gravity Field Generator.
     *
     * @param name The unlocalized name of the item.
     */
    public CItem(String name, EnumGUI gui, int limit, int startCount){
        super(name);
        this.gui = gui;
        this.limit = limit;
        this.currentCount = startCount;
    }

    /**
     * Sets the items's creative tab.
     *
     * @param tab The CreativeTab to set the Item's tab to.
     * @return The item who's tab was set.
     */
    @Override
    public CItem setCreativeTab(CreativeTabs tab){
        super.setCreativeTab(tab);
        return this;
    }

    /**
     * Called when the player right clicks holding this item
     *
     * @param itemStackIn The stack the player is holding
     * @param worldIn The world the player is in
     * @param playerIn The player who right clicked while holding this item
     * @param hand The hand the player is holding the item in
     * @return An ActionResult containing the stack and whether or not the action passed and such
     */
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand){
        if(worldIn.isRemote){
            playerIn.openGui(GravityFields.instance, gui.ordinal(), worldIn, 0, 0, 0);
            return new ActionResult(EnumActionResult.PASS, itemStackIn);
        }
        return new ActionResult(EnumActionResult.FAIL, itemStackIn);
    }

    /**
     * Getter for the limit of the gravity stone's counter values.
     *
     * @return limit
     */
    public int getLimit(){
        return limit;
    }

    /**
     * Getter for the start of the gravity stone's counter values.
     *
     * @return currentCount
     */
    public int getCurrentCount(){
        return currentCount;
    }
}
