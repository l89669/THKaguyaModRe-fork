package thKaguyaMod.gui;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import thKaguyaMod.init.THKaguyaItems;

public class SlotDanmakuCraftingA extends Slot
{
    /** The beacon this slot belongs to. */
    //final ContainerDanmakuCrafting danmaku;
	protected int slotType;
	//private final IInventory craftMatrix;
	
    public SlotDanmakuCraftingA(IInventory iInventory, int index, int x, int y, int type)
    {
        super(iInventory, index, x, y);
        slotType = type;
        //craftMatrix = iInventory;
        
        //this.danmaku = containerDanmakuCrafting;
    }

    /**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
    /*public boolean isItemValid(ItemStack itemStack)
    {
    	switch(slotType)
    	{
    		case 0:
    			return itemStack == null ? false : (itemStack.itemID == mod_thKaguya.powerItem.itemID && itemStack.getItemDamage() == 0);
    		case 1:
    			return itemStack == null ? false : itemStack.itemID == mod_thKaguya.pointItem.itemID;
    		case 2:
    			return itemStack == null ? false : itemStack.itemID == mod_thKaguya.thShotItem.itemID;
    		case 3:
    			return itemStack == null ? false : itemStack.itemID == Item.dyePowder.itemID;
    		case 4:
    			return itemStack == null ? false : (itemStack.itemID == mod_thKaguya.powerItem.itemID && itemStack.getItemDamage() == 1);
    		case 5:
    			return itemStack == null ? false : itemStack.itemID == mod_thKaguya.thLaserItem.itemID;
    		default:
    			return false;
    	}
    }*/
    
    /**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
    public boolean isItemValid(ItemStack itemStack)
    {
    	switch(slotType)
    	{
    		case 0:
    			return itemStack == null ? false : (itemStack.getItem() == THKaguyaItems.power_item && itemStack.getItemDamage() == 0);
    		case 1:
    			return itemStack == null ? false : itemStack.getItem() == THKaguyaItems.point_item;
    		case 2:
    			return itemStack == null ? false : itemStack.getItem() == THKaguyaItems.shot_item;
    		case 3:
    			return itemStack == null ? false : 	itemStack.getItem() == Items.dye || //染料
    													itemStack.getItem() == Items.gunpowder ||
    													itemStack.getItem() == Items.diamond;
    		case 4:
    			return itemStack == null ? false : (itemStack.getItem() == THKaguyaItems.power_item && itemStack.getItemDamage() == 1);
    		case 5:
    			return itemStack == null ? false : itemStack.getItem() == THKaguyaItems.laser_item;
    		default:
    			return false;
    	}
    }

    /**
     * Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1 in the case
     * of armor slots)
     */
    public int getSlotStackLimit()
    {
    	return 64;
    }
}
