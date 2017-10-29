package thKaguyaMod.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import thKaguyaMod.DanmakuConstants;
import thKaguyaMod.init.THKaguyaBlocks;
import thKaguyaMod.init.THKaguyaConfig;
import thKaguyaMod.init.THKaguyaItems;
import thKaguyaMod.item.ItemTHShot;

public class ContainerDanmakuCraftingLaser  extends Container
{
	public IInventory craftMatrix = new InventoryCrafting(this, 3, 3);
	public IInventory craftMaterial = new InventoryCrafting(this, 1, 1);
	public IInventory craftSpeed = new InventoryCrafting(this, 1, 1);
	public IInventory craftWay = new InventoryCrafting(this, 1, 1);
	public IInventory craftCopy = new InventoryCrafting(this, 1, 1);
	public IInventory craftColor = new InventoryCrafting(this, 1, 1);
	//public IInventory craftBound = new InventoryCrafting(this, 1, 1);
	//public IInventory craftGravity = new InventoryCrafting(this, 1, 1);
	//public IInventory craftSpecial = new InventoryCrafting(this, 1, 1);
	public IInventory craftResult = new InventoryCraftResult();
	private World world;
	private int xCoord;
	private int yCoord;
	private int zCoord;
	
	ItemStack newShot = null;
	
 
	public ContainerDanmakuCraftingLaser(InventoryPlayer inventoryPlayer, World world, int x, int y, int z)
	{
		this.world  = world;
		this.xCoord = x;
		this.yCoord = y;
		this.zCoord = z;
		this.addSlotToContainer(new SlotLaserResult(inventoryPlayer.player, this.craftMaterial, this.craftMatrix, this.craftWay, this.craftSpeed, this.craftCopy, this.craftResult, 0, 228, 35));
		this.addSlotToContainer(new SlotDanmakuCraftingA(this.craftMaterial, 0, 8, 17, 5));
		this.addSlotToContainer(new SlotDanmakuCraftingA(this.craftWay, 0, 8, 35, 0));
		this.addSlotToContainer(new SlotDanmakuCraftingA(this.craftSpeed, 0, 8, 53, 0));
		this.addSlotToContainer(new SlotDanmakuCraftingA(this.craftCopy, 0, 192, 17, 1));
		this.addSlotToContainer(new SlotDanmakuCraftingA(this.craftColor, 0, 69, 17, 3));
		//this.addSlotToContainer(new SlotDanmakuCraftingA(this.craftGravity, 0, 69, 35, 0));
		//this.addSlotToContainer(new SlotDanmakuCraftingA(this.craftBound, 0, 69, 53, 4));
		//this.addSlotToContainer(new SlotDanmakuCraftingA(this.craftSpecial, 0, 69, 35, 0));
		
		int l, i1;
		
        for (l = 0; l < 3; ++l)
        {
            for (i1 = 0; i1 < 3; ++i1)
            {
                this.addSlotToContainer(new SlotDanmakuCraftingA(this.craftMatrix, i1 + l * 3, 134 + i1 * 18, 17 + l * 18, 0));
            }
        }
		
        for (l = 0; l < 3; ++l)
        {
            for (i1 = 0; i1 < 9; ++i1)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, i1 + l * 9 + 9, 48 + i1 * 18, 84 + l * 18));
            }
        }
        
        for (l = 0; l < 9; ++l)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, l, 48 + l * 18, 142));
        }
        
        this.onCraftMatrixChanged(this.craftMatrix);
	}
 
	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer)
	{
		//return this.world.getBlockId(this.xCoord, this.yCoord, this.zCoord) != mod_thKaguya.danmakuCraftingTable.blockID/*GuiSampleCore.blockNoop.blockID*/ ? false 
		//	: entityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
		
		return this.world.getBlock(this.xCoord, this.yCoord, this.zCoord) != THKaguyaBlocks.danmaku_crafting_table/*GuiSampleCore.blockNoop.blockID*/ ? false 
				: entityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}
	
    /**
     * Callback for when the crafting matrix is changed.
     */
    public void onCraftMatrixChanged(IInventory par1IInventory)
    {	
    	//this.craftResult.setInventorySlotContents(0, null);
    	
    	ItemStack shot = craftMaterial.getStackInSlot(0);
    	//ItemStack power = new ItemStack(THKaguyaItems.power_item);
    	short way = 1;
    	byte form = 0;
    	byte addSpeed = 0;
    	byte color = 8;
    	//byte gravity = 0;
    	//byte special = 0;
    	
    	if(shot != null && shot.getTagCompound() != null)
    	{
    		NBTTagCompound nbt = shot.getTagCompound();
    		way = nbt.getShort("Number");
    		form = nbt.getByte("DanmakuForm");
    		addSpeed = nbt.getByte("Speed");
    		//special = nbt.getByte("special");
    		//gravity = nbt.getByte("gravity");
    		color = nbt.getByte("Color");
    	}
    	
    	
    	if(craftSpeed.getStackInSlot(0) != null)
    	{
    		addSpeed += craftSpeed.getStackInSlot(0).stackSize;
    		if(addSpeed > 64 || addSpeed < 0)
    		{
    			addSpeed = 64;
    		}
    	}
    	
    	if(craftWay.getStackInSlot(0) != null)
    	{
    		way += craftWay.getStackInSlot(0).stackSize;
    		if(way > THKaguyaConfig.laserMaxNumber || way < 0)
    		{
    			way = (short)THKaguyaConfig.laserMaxNumber;
    		}
    	}
    	
    	/*if(craftGravity.getStackInSlot(0) != null)
    	{
    		gravity += craftGravity.getStackInSlot(0).stackSize;
    		if(gravity > 16 || gravity < 0)
    		{
    			gravity = 16;
    		}
    	}*/
    	
    	/*if(craftBound.getStackInSlot(0) != null)
    	{
    		int num = craftBound.getStackInSlot(0).stackSize;
    		if(special >= thShotLib.BOUND01 && special <= thShotLib.BOUND01 + 3)//specialが既に跳ね返りなら処理しない
    		{
    			special = (byte)(special + num);
    		}
    		else//跳ね返りでないなら
    		{
    			special = thShotLib.BOUND01;//specialを跳ね返り１回に変更
    			num --;
    			special += num;
    		}
    		if(special > thShotLib.BOUND01 + 3)
    		{
    			//num = thShotLib.BOUND01 + 9 - special;
    			special = thShotLib.BOUND01 + 3;
    		}
    		
    		//special = (byte)(thShotLib.BOUND01 + craftBound.getStackInSlot(0).stackSize);
    	}*/
    	
    	//一点
    	if(	craftMatrix.getStackInSlot(0) == null &&
    		craftMatrix.getStackInSlot(1) == null &&	
    		craftMatrix.getStackInSlot(2) == null &&
    		craftMatrix.getStackInSlot(3) == null &&
    		craftMatrix.getStackInSlot(4) != null &&
    		craftMatrix.getStackInSlot(5) == null &&
    		craftMatrix.getStackInSlot(6) == null &&
    		craftMatrix.getStackInSlot(7) == null &&
    		craftMatrix.getStackInSlot(8) == null)
    	{
    		form = 0;
    	}
    	//前方ランダム
    	else if
    	(	craftMatrix.getStackInSlot(0) == null &&
			craftMatrix.getStackInSlot(1) != null &&	
			craftMatrix.getStackInSlot(2) == null &&
			craftMatrix.getStackInSlot(3) == null &&
			craftMatrix.getStackInSlot(4) != null &&
			craftMatrix.getStackInSlot(5) == null &&
			craftMatrix.getStackInSlot(6) == null &&
			craftMatrix.getStackInSlot(7) == null &&
			craftMatrix.getStackInSlot(8) == null)
    	{
    		form = 1;
    	}
    	//扇状
    	else if
    	(	craftMatrix.getStackInSlot(0) != null &&
			craftMatrix.getStackInSlot(1) != null &&	
			craftMatrix.getStackInSlot(2) != null &&
			craftMatrix.getStackInSlot(3) == null &&
			craftMatrix.getStackInSlot(4) == null &&
			craftMatrix.getStackInSlot(5) == null &&
			craftMatrix.getStackInSlot(6) == null &&
			craftMatrix.getStackInSlot(7) == null &&
			craftMatrix.getStackInSlot(8) == null)
    	{
    		form = 2;
    	}
    	//全方位
    	else if
    	(	craftMatrix.getStackInSlot(0) != null &&
			craftMatrix.getStackInSlot(1) != null &&	
			craftMatrix.getStackInSlot(2) != null &&
			craftMatrix.getStackInSlot(3) != null &&
			craftMatrix.getStackInSlot(4) == null &&
			craftMatrix.getStackInSlot(5) != null &&
			craftMatrix.getStackInSlot(6) != null &&
			craftMatrix.getStackInSlot(7) != null &&
			craftMatrix.getStackInSlot(8) != null)
    	{
    		form = 3;
    	}
    	//球状
    	else if
    	(	craftMatrix.getStackInSlot(0) != null &&
			craftMatrix.getStackInSlot(1) != null &&	
			craftMatrix.getStackInSlot(2) != null &&
			craftMatrix.getStackInSlot(3) != null &&
			craftMatrix.getStackInSlot(4) != null &&
			craftMatrix.getStackInSlot(5) != null &&
			craftMatrix.getStackInSlot(6) != null &&
			craftMatrix.getStackInSlot(7) != null &&
			craftMatrix.getStackInSlot(8) != null)
    	{
    		if(way > 2)
    		{
    			form = 4;
    		}
    	}
    	//中心に隣接した４つに入っている場合はリング
    	else if
    	(	craftMatrix.getStackInSlot(0) == null &&
			craftMatrix.getStackInSlot(1) != null &&	
			craftMatrix.getStackInSlot(2) == null &&
			craftMatrix.getStackInSlot(3) != null &&
			craftMatrix.getStackInSlot(4) == null &&
			craftMatrix.getStackInSlot(5) != null &&
			craftMatrix.getStackInSlot(6) == null &&
			craftMatrix.getStackInSlot(7) != null &&
			craftMatrix.getStackInSlot(8) == null)
    	{
    		if(way > 2)
    		{
    			form = 5;
    		}
    	}
    	else if
    	(	craftMatrix.getStackInSlot(0) == null &&
			craftMatrix.getStackInSlot(1) == null &&	
			craftMatrix.getStackInSlot(2) == null &&
			craftMatrix.getStackInSlot(3) == null &&
			craftMatrix.getStackInSlot(4) == null &&
			craftMatrix.getStackInSlot(5) == null &&
			craftMatrix.getStackInSlot(6) == null &&
			craftMatrix.getStackInSlot(7) == null &&
			craftMatrix.getStackInSlot(8) == null)
    	{
    		//変化なし
    	}
    	else
    	{
    		form = -1;
    	}
    	
    	if(craftColor.getStackInSlot(0) != null)
    	{
    		if(craftColor.getStackInSlot(0).getItem() == Items.dye)
    		{
    			color = (byte)ItemTHShot.sellectbleColor[craftColor.getStackInSlot(0).getItemDamage() % 16];
    		}
    		else if(craftColor.getStackInSlot(0).getItem() == Items.gunpowder)
    		{
    			//color = (byte)( (DanmakuConstants.RANDOM & DanmakuConstants.MASK_FORM) >> 12 );
    			color = DanmakuConstants.RANDOM;
    		}
    		else if(craftColor.getStackInSlot(0).getItem() == Items.diamond)
    		{
    			//color = (byte)( (DanmakuConstants.RAINBOW & DanmakuConstants.MASK_FORM) >> 12 );
    			color = DanmakuConstants.RAINBOW;
    		}
    	}

    	if(shot != null && !(form == 4 && way > 32) && form >= 0)
    	{
    		/*ItemStack*/ newShot = new ItemStack(THKaguyaItems.laser_item);// craftMaterial.getStackInSlot(0);
    		newShot.setItemDamage(craftMaterial.getStackInSlot(0).getItemDamage());
    		NBTTagCompound nbt2 = newShot.getTagCompound();
    		if(nbt2 == null)
    		{
    			nbt2 = new NBTTagCompound();
		    	newShot.setTagCompound(nbt2);
        	}
    		nbt2.setShort("Number", (short)(way));
		    nbt2.setByte("DanmakuForm", form);
		    nbt2.setByte("Speed", addSpeed);
		    nbt2.setByte("Color", color);
		    //nbt2.setByte("special", special);
		    //nbt2.setByte("gravity", gravity);
    		
    		int copyNum = 1;
    		if(craftCopy.getStackInSlot(0) != null)
    		{
    			int oriNum = shot.stackSize;
    			copyNum = craftCopy.getStackInSlot(0).stackSize + 1;
    			
    			if(copyNum > oriNum)
    			{
    				copyNum = oriNum;
    			}
    			
    			newShot.stackSize = copyNum;
    		}
    		
    		this.craftResult.setInventorySlotContents(0, newShot);
    		//this.craftResult.onInventoryChanged();
    	}
    	else
    	{
    		this.craftResult.setInventorySlotContents(0, null);
    	}
    	
    	this.detectAndSendChanges();
    }
    
    //閉じるときの処理（乗ってたアイテム放り投げるとかさ）
    public void onContainerClosed(EntityPlayer player)
    {
        super.onContainerClosed(player);

        if (!this.world.isRemote)
        {
        	ItemStack itemStack;
            for (int i = 0; i < 9; ++i)
            {
                itemStack = this.craftMatrix.getStackInSlotOnClosing(i);

                if (itemStack != null)
                {
                    player.dropPlayerItemWithRandomChoice(itemStack, false);
                }
            }
            itemStack = this.craftWay.getStackInSlotOnClosing(0);
            if(itemStack != null)
            {
            	player.dropPlayerItemWithRandomChoice(itemStack, false);
            }
            itemStack = this.craftSpeed.getStackInSlotOnClosing(0);
            if(itemStack != null)
            {
            	player.dropPlayerItemWithRandomChoice(itemStack, false);
            }
            itemStack = this.craftCopy.getStackInSlotOnClosing(0);
            if(itemStack != null)
            {
            	player.dropPlayerItemWithRandomChoice(itemStack, false);
            }
            itemStack = this.craftColor.getStackInSlotOnClosing(0);
            if(itemStack != null)
            {
            	player.dropPlayerItemWithRandomChoice(itemStack, false);
            }
            itemStack = this.craftMaterial.getStackInSlotOnClosing(0);
            if(itemStack != null)
            {
            	player.dropPlayerItemWithRandomChoice(itemStack, false);
            }
        }
    }
    
    //閉じるときの処理（乗ってたアイテム放り投げるとかさ）
    /*public void onContainerClosed(EntityPlayer player)
    {
        super.onContainerClosed(player);

        if (!this.world.isRemote)
        {
            for (int i = 0; i < 9; ++i)
            {
                Item item = this.craftMatrix.getStackInSlotOnClosing(i).getItem();

                if (item != null)
                {
                    player.dropItem(item, 1);
                }
            }
            Item item = this.craftWay.getStackInSlotOnClosing(0).getItem();
            if(item != null)
            {
            	player.dropItem(item, 1);
            }
            item = this.craftSpeed.getStackInSlotOnClosing(0).getItem();
            if(item != null)
            {
            	player.dropItem(item, 1);
            }
            item = this.craftCopy.getStackInSlotOnClosing(0).getItem();
            if(item != null)
            {
            	player.dropItem(item, 1);
            }
            item = this.craftColor.getStackInSlotOnClosing(0).getItem();
            if(item != null)
            {
            	player.dropItem(item, 1);
            }
            item = this.craftMaterial.getStackInSlotOnClosing(0).getItem();
            if(item != null)
            {
            	player.dropItem(item, 1);
            }
        }
    }*/
    
    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 == 0)
            {
                if (!this.mergeItemStack(itemstack1, 10, 46, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (par2 >= 10 && par2 < 37)
            {
                if (!this.mergeItemStack(itemstack1, 37, 46, false))
                {
                    return null;
                }
            }
            else if (par2 >= 37 && par2 < 46)
            {
                if (!this.mergeItemStack(itemstack1, 10, 37, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 10, 46, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }

        return itemstack;
    }
    
    /*public ItemStack detectAndSendChanges2()
    {
        ItemStack itemstack = ((Slot)this.inventorySlots.get(0)).getStack();
        ItemStack itemstack1 = (ItemStack)this.inventoryItemStacks.get(0);

        if (!ItemStack.areItemStacksEqual(itemstack1, itemstack))
        {
            itemstack1 = itemstack == null ? null : itemstack.copy();
            this.inventoryItemStacks.set(0, itemstack1);

            for (int j = 0; j < this.crafters.size(); ++j)
            {
                ((ICrafting)this.crafters.get(j)).sendSlotContents(this, 0, itemstack1);
            }
        }
        
        return itemstack1;
    }*/
    
    //@Oveerride
    public ItemStack slotClick(int par1, int par2, int par3, EntityPlayer par4EntityPlayer)
    {
    	super.slotClick(par1, par2, par3, par4EntityPlayer);
    	
    	//this.detectAndSendChanges();
    	this.onCraftMatrixChanged(null);
    	
    	
    	return newShot;
    }

    public boolean func_94530_a(ItemStack par1ItemStack, Slot par2Slot)
    {
        return par2Slot.inventory != this.craftResult && super.func_94530_a(par1ItemStack, par2Slot);
    }
}
