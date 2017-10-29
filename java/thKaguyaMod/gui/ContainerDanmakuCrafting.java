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
import thKaguyaMod.THShotLib;
import thKaguyaMod.init.THKaguyaBlocks;
import thKaguyaMod.init.THKaguyaConfig;
import thKaguyaMod.init.THKaguyaItems;
import thKaguyaMod.item.ItemTHShot;

public class ContainerDanmakuCrafting  extends Container
{
	public IInventory craftMatrix = new InventoryCrafting(this, 3, 3);
	public IInventory craftMaterial = new InventoryCrafting(this, 1, 1);
	public IInventory craftSpeed = new InventoryCrafting(this, 1, 1);
	public IInventory craftWay = new InventoryCrafting(this, 1, 1);
	public IInventory craftCopy = new InventoryCrafting(this, 1, 1);
	public IInventory craftColor = new InventoryCrafting(this, 1, 1);
	public IInventory craftBound = new InventoryCrafting(this, 1, 1);
	public IInventory craftGravity = new InventoryCrafting(this, 1, 1);
	public IInventory craftSpecial = new InventoryCrafting(this, 1, 1);
	public IInventory craftResult = new InventoryCraftResult();
	private World world;
	private int xCoord;
	private int yCoord;
	private int zCoord;
	
	ItemStack newShot = null;
	
 
	public ContainerDanmakuCrafting(InventoryPlayer inventoryPlayer, World world, int x, int y, int z)
	{
		this.world  = world;
		this.xCoord = x;
		this.yCoord = y;
		this.zCoord = z;
		this.addSlotToContainer(new SlotDanmakuResult(inventoryPlayer.player, this.craftMaterial, this.craftMatrix, this.craftWay, this.craftSpeed, this.craftCopy, this.craftGravity, this.craftBound, this.craftResult, 0, 228, 35));
		this.addSlotToContainer(new SlotDanmakuCraftingA(this.craftMaterial, 0, 8, 17, 2));
		this.addSlotToContainer(new SlotDanmakuCraftingA(this.craftWay, 0, 8, 35, 0));
		this.addSlotToContainer(new SlotDanmakuCraftingA(this.craftSpeed, 0, 8, 53, 0));
		this.addSlotToContainer(new SlotDanmakuCraftingA(this.craftCopy, 0, 192, 17, 1));
		this.addSlotToContainer(new SlotDanmakuCraftingA(this.craftColor, 0, 69, 17, 3));
		this.addSlotToContainer(new SlotDanmakuCraftingA(this.craftGravity, 0, 69, 35, 0));
		this.addSlotToContainer(new SlotDanmakuCraftingA(this.craftBound, 0, 69, 53, 4));
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
			//: entityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
		
		return this.world.getBlock(this.xCoord, this.yCoord, this.zCoord) != THKaguyaBlocks.danmaku_crafting_table/*GuiSampleCore.blockNoop.blockID*/ ? false 
				: entityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}
	
    //中身に変更があったときに呼ばれる
    public void onCraftMatrixChanged(IInventory par1IInventory)
    {	
    	//this.craftResult.setInventorySlotContents(0, null);
    	
    	//素材に入っている弾を取得
    	ItemStack shot = craftMaterial.getStackInSlot(0);
    	//パワーアップアイテム
    	ItemStack power = new ItemStack(THKaguyaItems.power_item);
    	//初期ステータスを設定
    	short way = 1;
    	byte form = 0;
    	byte addSpeed = 0;
    	byte color = 8;
    	byte gravity = 0;
    	int special = 0;
    	
    	//素材の弾があり、かつ、NBTがあるなら
    	if(shot != null && shot.getTagCompound() != null)
    	{
    		//NBTを取得
    		NBTTagCompound nbt = shot.getTagCompound();
    		//各々のステータスをNBTから取得
    		way = nbt.getShort("Number");
    		form = nbt.getByte("DanmakuForm");
    		addSpeed = nbt.getByte("Speed");
    		special = nbt.getInteger("Special");
    		gravity = nbt.getByte("Gravity");
    		color = nbt.getByte("Color");
    	}
    	
    	//速度のスロットにアイテムが入っているなら
    	if(craftSpeed.getStackInSlot(0) != null)
    	{
    		//現在の速度に入っているアイテム数分性能を上げる
    		addSpeed += craftSpeed.getStackInSlot(0).stackSize;
    		//性能が64個分より多いなら64個分にする
    		if(addSpeed > 64 || addSpeed < 0)
    		{
    			addSpeed = 64;
    		}
    	}
    	
    	//弾数のスロットにアイテムが入っているなら
    	if(craftWay.getStackInSlot(0) != null)
    	{
    		//現在の弾数に入っているアイテム数分弾数を増やす
    		way += craftWay.getStackInSlot(0).stackSize;
    		//弾数がconfigで設定できる数より多いなら、configの最大数にする
    		if(way > THKaguyaConfig.shotMaxNumber || way < 0)
    		{
    			way = (short)THKaguyaConfig.shotMaxNumber;
    		}
    	}
    	
    	//重力加速度のスロットにアイテムが入っているなら
    	if(craftGravity.getStackInSlot(0) != null)
    	{
    		//重力加速度を入ってる数だけ性能を上げる
    		gravity += craftGravity.getStackInSlot(0).stackSize;
    		//性能が１６個分より高いなら１６個分にする
    		if(gravity > 16 || gravity < 0)
    		{
    			gravity = 16;
    		}
    	}
    	
    	//跳ね返り数のスロットにアイテムが入っているなら
    	if(craftBound.getStackInSlot(0) != null)
    	{
    		//跳ね返り数を入ってるアイテム数分増やす
    		int num = craftBound.getStackInSlot(0).stackSize;
    		if(special >= THShotLib.BOUND01 && special <= THShotLib.BOUND01 + 3)//specialが既に跳ね返りなら処理しない
    		{
    			special = (byte)(special + num);
    		}
    		else//跳ね返りでないなら
    		{
    			special = THShotLib.BOUND01;//specialを跳ね返り１回に変更
    			num --;
    			special += num;
    		}
    		if(special > THShotLib.BOUND01 + 3)
    		{
    			//num = thShotLib.BOUND01 + 9 - special;
    			special = THShotLib.BOUND01 + 3;
    		}
    		
    		//special = (byte)(thShotLib.BOUND01 + craftBound.getStackInSlot(0).stackSize);
    	}
    	
    	/*形状判定
    	 * ９つのスロットに入っているアイテムの有無を判定し、
    	 * 一致した場合はそれにあった形状にする
    	 */
    	
    	//真ん中一つだけなら一点
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
    	//真ん中上２つなら前方ランダム
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
    	//上３つなら扇状
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
    	//真ん中以外入っているなら全方位
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
    	//全て入っているなら球状、ただし、３発以上の弾がない場合は無効
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
    	//何も入っていないなら変化なし
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
    	//それ以外は無効
    	else
    	{
    		form = -1;
    	}
    	
    	//色のスロットにアイテムが入っているなら
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

    	//素材があり、形状が正しいなら
    	if(shot != null && !(form == 4 && way > 32) && form >= 0)
    	{
    		//新しくできる弾を設定
    		newShot = new ItemStack(THKaguyaItems.shot_item);
    		//ダメージ値を素材と同じにする
    		newShot.setItemDamage(craftMaterial.getStackInSlot(0).getItemDamage());
    		
    		//新しい弾のNBTを設定
    		NBTTagCompound nbt2 = newShot.getTagCompound();
    		//
    		if(nbt2 == null)
    		{
    			nbt2 = new NBTTagCompound();
		    	newShot.setTagCompound(nbt2);
        	}
    		//NBTに決定した弾幕情報を書き込む
    		nbt2.setShort("Number", (short)(way));
		    nbt2.setByte("DanmakuForm", form);
		    nbt2.setByte("Speed", addSpeed);
		    nbt2.setByte("Color", color);
		    nbt2.setInteger("Special", special);
		    nbt2.setByte("Gravity", gravity);
		    
    		
		    //新しい弾幕の性能を、スタックしているいくとかの弾にも複製する
    		int copyNum = 1;//複製する数の初期値
    		//複製のスロットにアイテムが入っているなら
    		if(craftCopy.getStackInSlot(0) != null)
    		{
    			//素材として入っている弾のスタック数を取得
    			int oriNum = shot.stackSize;
    			//複製する数を、複製スロットに入っている数＋１個にする
    			copyNum = craftCopy.getStackInSlot(0).stackSize + 1;
    			
    			//複製する数が素材のスタック数より多いなら、素材のスタック数にする
    			if(copyNum > oriNum)
    			{
    				copyNum = oriNum;
    			}
    			//新しい弾幕のスタック数を決定
    			newShot.stackSize = copyNum;
    		}
    		
    		//制作結果のスロットに新しい弾を置く
    		this.craftResult.setInventorySlotContents(0, newShot);
    	}
    	else
    	{
    		this.craftResult.setInventorySlotContents(0, null);
    	}
    	
    	this.detectAndSendChanges();
    }
    
    //閉じるときの処理（乗ってたアイテム放り投げるとかさ）
    @Override
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
            itemStack = this.craftGravity.getStackInSlotOnClosing(0);
            if(itemStack != null)
            {
            	player.dropPlayerItemWithRandomChoice(itemStack, false);
            }
            itemStack = this.craftBound.getStackInSlotOnClosing(0);
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
