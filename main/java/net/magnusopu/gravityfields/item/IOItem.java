package net.magnusopu.gravityfields.item;

import net.minecraft.item.Item;

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
public class IOItem {

    private Item input;
    private Item output;
    private int ticks = 100; // default
    private int outputAmount = 1; // default

    public IOItem(Item input, Item output){
        this.input = input;
        this.output = output;
    }

    public IOItem(Item input, Item output, int ticks){
        this.input = input;
        this.output = output;
        this.ticks = ticks;
    }

    public IOItem(Item input, Item output, int ticks, int outputAmount){
        this.input = input;
        this.output = output;
        this.ticks = ticks;
        this.outputAmount = outputAmount;
    }

    public static Item getOutputFromInput(Item input, IOItem[] ioItems){
        if(validInput(input, ioItems)){
            return getIOItem(input, ioItems, true).getOutput();
        }
        return null;
    }

    public static Item getInputFromOutput(Item output, IOItem[] ioItems){
        if(validOutput(output, ioItems)){
            return getIOItem(output, ioItems, true).getInput();
        }
        return null;
    }

    public static int findTicks(Item input, IOItem[] ioItems){
        if(validInput(input, ioItems)) {
            return getIOItem(input, ioItems, true).getTicks();
        }
        return 0;
    }

    public static boolean validInput(Item input, IOItem[] ioItems){
        for(IOItem ioItem : ioItems){
            if(input == ioItem.getInput()){
                return true;
            }
        }
        return false;
    }

    public static boolean validItem(Item item, IOItem[] ioItems){
        for(IOItem ioItem : ioItems){
            if(item == ioItem.getInput() || item == ioItem.getOutput()){
                return true;
            }
        }
        return false;
    }

    public static int getOutputAmountFromInput(Item input, IOItem[] ioItems){
        if(validInput(input, ioItems)){
            return getIOItem(input, ioItems, true).getOutputAmount();
        }
        return 1;
    }

    public static IOItem getIOItem(Item item, IOItem[] ioItems, boolean validated){
        if(!validated){ validated = validInput(item, ioItems); }
        if(validated){
            for(IOItem ioItem : ioItems) {
                if (item == ioItem.getInput() || item == ioItem.getOutput()) {
                    return ioItem;
                }
            }
        }
        return null;
    }

    public static boolean validOutput(Item output, IOItem[] ioItems){
        for(IOItem ioItem : ioItems){
            if(output == ioItem.getOutput()){
                return true;
            }
        }
        return false;
    }

    public Item getInput() {
        return input;
    }

    public void setInput(Item input) {
        this.input = input;
    }

    public Item getOutput() {
        return output;
    }

    public void setOutput(Item output) {
        this.output = output;
    }

    public int getTicks() {
        return ticks;
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
    }

    public int getOutputAmount() {
        return outputAmount;
    }

    public void setOutputAmount(int outputAmount) {
        this.outputAmount = outputAmount;
    }
}
