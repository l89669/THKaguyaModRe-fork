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

public class SlotMiracleMalletResult extends Slot
{
    /** The beacon this slot belongs to. */
    //final ContainerDanmakuCrafting danmaku;
	private final IInventory craftMaterial;
	private EntityPlayer thePlayer;
	
	private int amountCrafted;
	
    public SlotMiracleMalletResult(EntityPlayer player, IInventory material, IInventory iInventoryResult, int index, int x, int y)
    {
        super(iInventoryResult, index, x, y);
        this.thePlayer = player;
        craftMaterial = material;
        
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

    /**
     * スロットにアイテムを入れられるかを返す
     * @param itemStack	: スロットに入れるアイテム
     * @return true	: 入れられる、 false	: 入れられない
     */
    public boolean isItemValid(ItemStack itemStack)
    {
    	// 結果のスロットのためアイテムを入れられない
    	return false;
    }

    //スロットから出した時の処理
    public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack itemStack)
    {
        //GameRegistry.onItemCrafted(par1EntityPlayer, par2ItemStack, craftMatrix);
        this.onCrafting(itemStack);
        
        
        slotUseProcess(this.craftMaterial, 1);
        

        
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
