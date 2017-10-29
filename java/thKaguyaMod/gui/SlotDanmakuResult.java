package thKaguyaMod.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import thKaguyaMod.THShotLib;
import thKaguyaMod.init.THKaguyaConfig;

public class SlotDanmakuResult extends Slot
{
    /** The beacon this slot belongs to. */
    //final ContainerDanmakuCrafting danmaku;
	private final IInventory craftMaterial;
	private final IInventory craftMatrix;
	private final IInventory craftNum;
	private final IInventory craftSpeed;
	private final IInventory craftCopy;
	private final IInventory craftGravity;
	private final IInventory craftSpecial;
	private EntityPlayer thePlayer;
	
	private int amountCrafted;
	
    public SlotDanmakuResult(EntityPlayer player, IInventory material, IInventory formInventory, IInventory numInventory, IInventory speed, IInventory copy, IInventory gravity, IInventory special, IInventory iInventoryResult, int index, int x, int y)
    {
        super(iInventoryResult, index, x, y);
        this.thePlayer = player;
        craftMaterial = material;
        craftMatrix = formInventory;
        craftNum = numInventory;
        craftSpeed = speed;
        craftCopy = copy;
        craftGravity = gravity;
        craftSpecial = special;
        
        //this.danmaku = containerDanmakuCrafting;
    }
    
    /**
     * Decrease the size of the stack in slot (first int arg) by the amount of the second int arg. Returns the new
     * stack.
     */
    public ItemStack decrStackSize(int par1)
    {
        if (this.getHasStack())
        {
            this.amountCrafted += Math.min(par1, this.getStack().stackSize);
        }

        return super.decrStackSize(par1);
    }

    //スロットにアイテムを入れられるか。　falseでは入れられない
    public boolean isItemValid(ItemStack itemStack)
    {
    	return false;
    	/*
    	switch(slotType)
    	{
    		case 0:
    			return itemStack == null ? false : itemStack.itemID == mod_thKaguya.powerItem.itemID;
    		case 1:
    			return itemStack == null ? false : itemStack.itemID == mod_thKaguya.pointItem.itemID;
    		case 2:
    			return itemStack == null ? false : itemStack.itemID == mod_thKaguya.thShotItem.itemID;
    		default:
    			return false;
    	}*/
    }

    //スロットから出した時の処理
    public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack)
    {
        //GameRegistry.onItemCrafted(par1EntityPlayer, par2ItemStack, craftMatrix);
        this.onCrafting(par2ItemStack);
        
        int copyNum = 1;
        int oriNum = 1;
        
        if(craftCopy.getStackInSlot(0) != null)
        {
        	copyNum += craftCopy.getStackInSlot(0).stackSize;
        }
        
        if(craftMaterial.getStackInSlot(0) != null)
        {
        	oriNum = craftMaterial.getStackInSlot(0).stackSize;
        }
        
        if(copyNum > oriNum)
        {
        	copyNum = oriNum;
        }
        
        //クリエイティブなら無限に使用できるチートアイテムにする
        
        /*if(par1EntityPlayer.capabilities.isCreativeMode)
        {
        	if(craftMaterial.getStackInSlot(0) != null)
        	{
	        	NBTTagCompound nbt = craftMaterial.getStackInSlot(0).getTagCompound();
	        	if(nbt == null)
	        	{
	        		nbt = new NBTTagCompound();
	        	}
	        	nbt.setBoolean("Infinity", true);
        	}
        }*/

        for (int i = 0; i < this.craftMatrix.getSizeInventory(); ++i)
        {
            ItemStack itemstack1 = this.craftMatrix.getStackInSlot(i);

            if (itemstack1 != null)
            {
                this.craftMatrix.decrStackSize(i, 1);

                if (itemstack1.getItem().hasContainerItem())
                {
                    ItemStack itemstack2 = itemstack1;//.getItem().getContainerItemStack(itemstack1);

                    if (itemstack2.isItemStackDamageable() && itemstack2.getItemDamage() > itemstack2.getMaxDamage())
                    {
                        MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(thePlayer, itemstack2));
                        itemstack2 = null;
                    }

                    if (itemstack2 != null && (!itemstack1.getItem().doesContainerItemLeaveCraftingGrid(itemstack1) || !this.thePlayer.inventory.addItemStackToInventory(itemstack2)))
                    {
                        if (this.craftMatrix.getStackInSlot(i) == null)
                        {
                            this.craftMatrix.setInventorySlotContents(i, itemstack2);
                        }
                        else
                        {
                            //this.thePlayer.dropPlayerItem(itemstack2);
                            this.thePlayer.dropItem(itemstack2.getItem(), itemstack2.getItemDamage());//?
                        }
                    }
                }
            }
        }
        
        int usePoint = THKaguyaConfig.shotMaxNumber;
    	
        if(craftMaterial.getStackInSlot(0) != null)
    	{
    		NBTTagCompound nbt = craftMaterial.getStackInSlot(0).getTagCompound();
        	if(nbt != null)
        	{
        		int baseNum;

        		baseNum = (int)nbt.getShort("Number");
        		
        		
        		if(craftNum.getStackInSlot(0) != null)
        		{
        			if(craftNum.getStackInSlot(0).stackSize > usePoint - baseNum)
        			{
        				usePoint = usePoint - baseNum;
        			}
        		}
        	}
        	else
        	{
          		if(craftNum.getStackInSlot(0) != null)
        		{
          			int numNum = craftNum.getStackInSlot(0).stackSize;
        			if(numNum > usePoint)
        			{
        				usePoint--;
        			}
        			else
        			{
        				usePoint = numNum;
        			}
        		}
        	}
    	}
        slotUseProcess(this.craftNum, usePoint);
        
       	usePoint = 64;
    	
       	if(craftMaterial.getStackInSlot(0) != null)
    	{
    		NBTTagCompound nbt = craftMaterial.getStackInSlot(0).getTagCompound();
        	if(nbt != null)
        	{
        		int baseSpeed = 0;
        		baseSpeed = (int)nbt.getByte("Speed");
        		
        		if(craftSpeed.getStackInSlot(0) != null)
        		{
        			if(craftSpeed.getStackInSlot(0).stackSize > 64 - baseSpeed)
        			{
        				usePoint = 64 - baseSpeed;
        			}
        		}
        	}
    	}
        slotUseProcess(this.craftSpeed, usePoint);
        
       	usePoint = 16;
    	
       	if(craftMaterial.getStackInSlot(0) != null)
    	{
    		NBTTagCompound nbt = craftMaterial.getStackInSlot(0).getTagCompound();
        	if(nbt != null)
        	{
        		int baseGravity = 0;
        		baseGravity = (int)nbt.getByte("Gravity");
        		
        		if(craftGravity.getStackInSlot(0) != null)
        		{
        			if(craftGravity.getStackInSlot(0).stackSize > 16 - baseGravity)
        			{
        				usePoint = 16 - baseGravity;
        			}
        		}
        	}
    	}
        slotUseProcess(this.craftGravity, usePoint);
        
       	usePoint = 4;
    	
       	if(craftMaterial.getStackInSlot(0) != null)
    	{
    		NBTTagCompound nbt = craftMaterial.getStackInSlot(0).getTagCompound();
        	if(nbt != null)
        	{
        		int baseBound = 0;
        		baseBound = (int)nbt.getInteger("Special") - THShotLib.BOUND01 + 1;
        		
        		if(craftSpecial.getStackInSlot(0) != null)
        		{
        			if(craftSpecial.getStackInSlot(0).stackSize > 4 - baseBound)
        			{
        				usePoint = 4 - baseBound;
        			}
        		}
        	}
    	}
        slotUseProcess(this.craftSpecial, usePoint);
        //slotUseProcess(this.craftSpecial, 1);
        slotUseProcess(this.craftMaterial, copyNum);
        slotUseProcess(this.craftCopy, copyNum - 1);
        

        
    }
    
    //完成したアイテムを取り出したときの、各インベントリでの処理
    public void slotUseProcess(IInventory inventory, int useNum)
    {
        ItemStack itemstack1 = inventory.getStackInSlot(0);
        
        if (itemstack1 != null)
        {
            inventory.decrStackSize(0, useNum);

            if (itemstack1.getItem().hasContainerItem())
            {
                //ItemStack itemstack2 = itemstack1.getItem().getContainerItemStack(itemstack1);
                ItemStack itemstack2 = itemstack1.getItem().getContainerItem(itemstack1);

                if (itemstack2.isItemStackDamageable() && itemstack2.getItemDamage() > itemstack2.getMaxDamage())
                {
                    MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(thePlayer, itemstack2));
                    itemstack2 = null;
                }

                if (itemstack2 != null && (!itemstack1.getItem().doesContainerItemLeaveCraftingGrid(itemstack1) || !this.thePlayer.inventory.addItemStackToInventory(itemstack2)))
                {
                    if (inventory.getStackInSlot(0) == null)
                    {
                        inventory.setInventorySlotContents(0, itemstack2);
                    }
                    else
                    {
                        //this.thePlayer.dropPlayerItem(itemstack2);
                        this.thePlayer.dropItem(itemstack2.getItem(), itemstack2.getItemDamage());//?
                    }
                }
            }
        }
    }
    
    //スロットの最大スタック数を取得
    public int getSlotStackLimit()
    {
    	return 64;
    }
}
