package thKaguyaMod.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import thKaguyaMod.init.THKaguyaItems;

/** 打ち出の小槌のコンテナ */
public class ContainerMiracleMallet extends Container
{
	/** 素材スロット */
	public IInventory craftMaterial = new InventoryCrafting(this, 1, 1);
	/** 結果スロット */
	public IInventory craftResult = new InventoryCraftResult();
	private World world;
	private int xCoord;
	private int yCoord;
	private int zCoord;
	
	private ItemStack resultItem;
	
 
	/** 打ち出の小槌のコンストラクタ */
	public ContainerMiracleMallet(InventoryPlayer inventoryPlayer, World world, int x, int y, int z)
	{
		this.world  = world;
		this.xCoord = x;
		this.yCoord = y;
		this.zCoord = z;
		// 結果スロットを追加
		this.addSlotToContainer(new SlotMiracleMalletResult(inventoryPlayer.player, this.craftMaterial, this.craftResult, 0, 228, 35));
		// 素材スロットを追加
		this.addSlotToContainer(new Slot(this.craftMaterial, 0, 8, 17));
		
		int l, i1;
		
		// プレイヤーインベントリのスロットを追加
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
        
        //this.onCraftMatrixChanged(this.craftMatrix);
	}
	
	/**
	 * GUIを開いていられるかを返す
	 * @param player : GUIを開いているプレイヤー
	 * @return [true : GUIを開いたままにできる] [false : GUIを終了させる]
	 */
	@Override
	public boolean canInteractWith(EntityPlayer player)
	{	
		return true;
	}
	
    /**
     * 中身に変化があったときの処理
     * @param iInventory	: プレイヤーのインベントリ
     */
    public void onCraftMatrixChanged(IInventory iInventory)
    {	
    	ItemStack 	originItem	= iInventory.getStackInSlot(0);
    	int			originID	= -1;
    	
    	
    	// アイテムが入れられている場合
    	if( originItem != null )
    	{
    		boolean isCommonDamage = false;	// 変化前、変化後でダメージ値を共有するアイテムかどうか
    		
    		// 素材のアイテムが陰陽玉の場合
    		if( originItem.getItem() == THKaguyaItems.yin_yang_orb )
    		{
    			// 血に餓えた陰陽玉に変化させる
    			resultItem = new ItemStack( THKaguyaItems.bloodthirsty_yin_yang_orb );
    		}
    		// 素材のアイテムがスキマの場合
    		else if( originItem.getItem() == THKaguyaItems.gap )
    		{
    			// 隙間の折りたたみ傘に変化させる
    			resultItem = new ItemStack( THKaguyaItems.gapFoldingUmbrella );
    		}
    		// 素材のアイテムが素体人形（アリスの人形MODのアイテム）の場合
    		else if(originItem.getUnlocalizedName().equals("item.alicedoll.bare"))
    		//else if(originItem.getUnlocalizedName().equals("item.icicleSword"))
    		{
    			// 呪いのデコイ人形に変化させる
    			resultItem = new ItemStack( THKaguyaItems.cursedDecoyDoll );
    			originID = originItem.getItem().getIdFromItem( originItem.getItem() );	// 素体人形のアイテムIDを取得
    		}
    		// 素材のアイテムが楼観剣の場合
    		else if(originItem.getItem() == THKaguyaItems.roukanken )
    		{
    			// 楼観旋風刃に変化させる
    			resultItem = new ItemStack( THKaguyaItems.roukanSenpuuzin );
    			resultItem.setItemDamage( originItem.getItemDamage() );
    			isCommonDamage = true;
    		}
    		else
    		{
    			resultItem = null;
    		}
    		
    		if( ( resultItem != null ) && !isCommonDamage )
    		{
    			NBTTagCompound	nbt = new NBTTagCompound();
    			
    			nbt.setInteger( "OriginalDamage", originItem.getItemDamage() );
    			if( originID >= 0 )
    			{
    				nbt.setInteger( "OriginalItemID", originID );
    			}
    			resultItem.setTagCompound( nbt );
    		}
    		
    	}
    	else
    	{
    		resultItem = null;
    	}
    	
		// 制作結果のスロットに新しいアイテムを置く
		this.craftResult.setInventorySlotContents( 0, resultItem );
    	
    	this.detectAndSendChanges();
    }
    
    /**
     * 閉じるときの処理（乗ってたアイテム放り投げるとかさ）
     * @param player	: GUIを開いているプレイヤー
     */
    @Override
    public void onContainerClosed(EntityPlayer player)
    {
        super.onContainerClosed(player);

        if (!this.world.isRemote)
        {
        	ItemStack itemStack;
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
    
    @Override
    public ItemStack slotClick(int par1, int par2, int par3, EntityPlayer player)
    {
    	super.slotClick(par1, par2, par3, player);
    	
    	//this.detectAndSendChanges();
    	if( player.experienceLevel >= 30 )
    	{
    		this.onCraftMatrixChanged(craftMaterial);
    	}
    	
    	
    	return resultItem;
    }

    public boolean func_94530_a(ItemStack par1ItemStack, Slot par2Slot)
    {
        return par2Slot.inventory != this.craftResult && super.func_94530_a(par1ItemStack, par2Slot);
    }
}

