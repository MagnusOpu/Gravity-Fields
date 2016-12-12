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

    /**
     * IOItem is a wrapper class for the purpose of pairing an input item with an output item, ticks taken to transform it, and amount given out.
     *
     * This constructor defaults ticks to 100 and outputAmount to 1.
     * @param input The input item.
     * @param output The output item.
     */
    public IOItem(Item input, Item output){
        this.input = input;
        this.output = output;
    }

    /**
     * IOItem is a wrapper class for the purpose of pairing an input item with an output item, ticks taken to transform it, and amount given out.
     * This constructor defaults outputAmount to 1.
     *
     * @param input The input item.
     * @param output The output item.
     * @param ticks The ticks required to transform the item.
     */
    public IOItem(Item input, Item output, int ticks){
        this.input = input;
        this.output = output;
        this.ticks = ticks;
    }

    /**
     * IOItem is a wrapper class for the purpose of pairing an input item with an output item, ticks taken to transform it, and amount given out.
     *
     * @param input The input item.
     * @param output The output item.
     * @param ticks The ticks required to transform the item.
     * @param outputAmount The amount of the item given after transformation.
     */
    public IOItem(Item input, Item output, int ticks, int outputAmount){
        this.input = input;
        this.output = output;
        this.ticks = ticks;
        this.outputAmount = outputAmount;
    }

    /**
     * Returns an output item dependent on input item via searching through ioItems
     *
     * @param input The input item to search for an output item based on.
     * @param ioItems The array of IOItem to search through as reference.
     * @return The output item dependent on the input item.
     */
    public static Item getOutputFromInput(Item input, IOItem[] ioItems){
        if(validInput(input, ioItems)){
            return getIOItem(input, ioItems, true).getOutput();
        }
        return null;
    }

    /**
     * Returns an input item dependent on output item via searching through ioItems
     *
     * @param output The output item to search for an input item based on.
     * @param ioItems The array of IOItem to search through as reference.
     * @return The input item dependent on the output item.
     */
    public static Item getInputFromOutput(Item output, IOItem[] ioItems){
        if(validOutput(output, ioItems)){
            return getIOItem(output, ioItems, true).getInput();
        }
        return null;
    }

    /**
     * Finds the amount of ticks required to transform the input item.
     *
     * @param input The input item to find the ticks for.
     * @param ioItems The array of IOItem to search through as reference.
     * @return The ticks required to transform the input item.
     */
    public static int findTicks(Item input, IOItem[] ioItems){
        if(validInput(input, ioItems)) {
            return getIOItem(input, ioItems, true).getTicks();
        }
        return 0;
    }

    /**
     * Finds whether or not the input item exists in ioItems.
     *
     * @param input The input item to validate.
     * @param ioItems The array of IOItem to search through as reference.
     * @return True if the input item was found within ioItems, false otherwise.
     */
    public static boolean validInput(Item input, IOItem[] ioItems){
        for(IOItem ioItem : ioItems){
            if(input == ioItem.getInput()){
                return true;
            }
        }
        return false;
    }

    /**
     * Finds whether or not the item exists in ioItems.
     *
     * @param item The item to validate.
     * @param ioItems The array of IOItem to search through as reference.
     * @return True if the item was found within ioItems, false otherwise.
     */
    public static boolean validItem(Item item, IOItem[] ioItems){
        for(IOItem ioItem : ioItems){
            if(item == ioItem.getInput() || item == ioItem.getOutput()){
                return true;
            }
        }
        return false;
    }

    /**
     * Finds the output amount of an input item.
     *
     * @param input The input item to find the output amount for.
     * @param ioItems The array of IOItem to search through as reference.
     * @return The output amount of the input item given.
     */
    public static int getOutputAmountFromInput(Item input, IOItem[] ioItems){
        if(validInput(input, ioItems)){
            return getIOItem(input, ioItems, true).getOutputAmount();
        }
        return 1;
    }

    /**
     * Returns a specific IOItem after searching through ioItems for the Item.
     *
     * @param item The item to search for.
     * @param ioItems the array of IOItem to search through.
     * @param validated Whether or not it needs to check if the item does indeed exist in the ioItems array.
     * @return The IOItem containing the item.
     */
    public static IOItem getIOItem(Item item, IOItem[] ioItems, boolean validated){
        if(!validated){ validated = validItem(item, ioItems); }
        if(validated){
            for(IOItem ioItem : ioItems) {
                if (item == ioItem.getInput() || item == ioItem.getOutput()) {
                    return ioItem;
                }
            }
        }
        return null;
    }

    /**
     * Finds whether or not the output item exists in ioItems.
     *
     * @param output The output item to validate.
     * @param ioItems The array of IOItem to search through as reference.
     * @return True if the output item was found within ioItems, false otherwise.
     */
    public static boolean validOutput(Item output, IOItem[] ioItems){
        for(IOItem ioItem : ioItems){
            if(output == ioItem.getOutput()){
                return true;
            }
        }
        return false;
    }

    /**
     * A getter for input.
     *
     * @return input
     */
    public Item getInput() {
        return input;
    }

    /**
     * A setter for input.
     *
     * @param input The input to set.
     */
    public void setInput(Item input) {
        this.input = input;
    }

    /**
     * A getter for output.
     *
     * @return output
     */
    public Item getOutput() {
        return output;
    }

    /**
     * A setter for output.
     *
     * @param output The output to set.
     */
    public void setOutput(Item output) {
        this.output = output;
    }

    /**
     * A getter for ticks.
     *
     * @return ticks
     */
    public int getTicks() {
        return ticks;
    }

    /**
     * A setter for ticks.
     *
     * @param ticks The ticks to set.
     */
    public void setTicks(int ticks) {
        this.ticks = ticks;
    }

    /**
     * A getter for outputAmount.
     *
     * @return outputAmount.
     */
    public int getOutputAmount() {
        return outputAmount;
    }

    /**
     * A setter for outputAmount.
     *
     * @param outputAmount  the outputAmount to set.
     */
    public void setOutputAmount(int outputAmount) {
        this.outputAmount = outputAmount;
    }
}
