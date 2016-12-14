package net.magnusopu.gravityfields.event;

import net.magnusopu.gravityfields.gui.CGui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiScreenEvent.MouseInputEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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

public class GravityFieldsEventHandler {

    /**
     * This event listener is called before any mouse input on a gui screen
     *
     * @param e A mouse input event
     */
    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public void onMouseClick(MouseInputEvent.Pre e){
        GuiScreen gui = e.getGui();
        if(gui instanceof CGui){
            CGui cGui = (CGui)gui;
            cGui.mouseInput();
        }
    }

}
