package thKaguyaMod.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 悔悟の棒
 * 相手の体力を3/4にする
 * 体力が3/4より低い時は無効
 */
public class ItemKaigo extends ItemSword
{	
	public static final String iconName[] =
	{
		"kaigoStick_0", "kaigoStick_1"
	};
	@SideOnly(Side.CLIENT)
    private IIcon[] icon;
    
    public ItemKaigo(ToolMaterial toolMaterial)
    {
        super(toolMaterial);
        this.setTextureName("thkaguyamod:kaigoStick_0");//テクスチャの指定
    	setHasSubtypes(true);
        setMaxDamage(0);
    }
	
	//Entityに当たったときに呼び出される
	@Override
    public boolean hitEntity(ItemStack itemStack, EntityLivingBase living_hit, EntityLivingBase living_used)
    {
		//文字が書かれている状態で、当てた相手の体力が７５％以上なら
    	if((double)living_hit.getMaxHealth() * 0.75D < (double)living_hit.getHealth() && itemStack.getItemDamage() == 0)
    	{
    		//当てた相手の体力を７５％にする
    		living_hit.setHealth((int)((double)living_hit.getMaxHealth() * 0.75D));
    		//文字が消える
    		itemStack.setItemDamage(1);
    		return true;
    	}
        return false;
    }
	
	/** 右クリックを押した瞬間の処理
	 *  @param itemStack : 右クリックを押したItemStack
	 *  @param world     : ワールド
	 *  @param player    : 右クリックを押したプレイヤー
	 *  @return 右クリックを押したItemStackを返す
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
    	int ikachan = -1;
    	for(int i = 0; i < player.inventory.mainInventory.length; i++)
    	{
    		if (player.inventory.mainInventory[i] != null && 
    			player.inventory.mainInventory[i].getItem() == Items.dye &&//染料かつ
    			player.inventory.mainInventory[i].getItemDamage() == 0)//それが黒なら
            {
                ikachan = i;
            	break;
            }
    	}
    	//イカスミを消費して耐久を回復
    	if (ikachan >= 0 && itemStack.getItemDamage() == 1)
        {
        	player.inventory.mainInventory[ikachan].stackSize--;//イカスミを消費
        	if(player.inventory.mainInventory[ikachan].stackSize <= 0)//マルチ化の影響か、最後の一つがうまく消えないから後処理
        	{
        		player.inventory.mainInventory[ikachan] = null;
        	}
        	itemStack.setItemDamage(0);//効果回復
        }
    	player.setItemInUse(itemStack, getMaxItemUseDuration(itemStack));
        return itemStack;
    }

	/*@Override
    public int getDamageVsEntity(Entity entity)
    {
    	if(entity instanceof EntityLivingBase)
    	{
    		EntityLivingBase EntityLivingBase = (EntityLivingBase)entity;
    		if((double)EntityLivingBase.getMaxHealth() * 0.75D >= (double)EntityLivingBase.getHealth())
    		{
    			return 0;
    		}
    		
    	}
        return 1;
    }*/
	
	//Forgeの追加メソッド
	//武器のダメージを返す
	/*@Override
    @Deprecated
    public float getDamageVsEntity(Entity hitEntity, ItemStack itemStack)
    {
    	if(hitEntity instanceof EntityLivingBase)
    	{
    		EntityLivingBase EntityLivingBase = (EntityLivingBase)hitEntity;
    		if((double)EntityLivingBase.func_110138_aP() * 0.75D >= (double)EntityLivingBase.func_110143_aJ())
    		{
    			return 0.0F;
    		}
    		
    	}
        return 1.0F;
    }*/

	@Override
    public boolean isFull3D()
    {
        return false;
    }

	/*@Override
    public boolean canHarvestBlock(Block block)
    {
    	return false;
    }*/

	//エンチャントはできない
	@Override
    public int getItemEnchantability()
    {
        return 0;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	//ダメージ値によってアイテムアイコンを変える
    public IIcon getIconFromDamage(int damage)
    {
        int i = MathHelper.clamp_int(damage, 0, 1);
        return this.icon[i];
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        this.icon = new IIcon[iconName.length];

        for (int i = 0; i < iconName.length; ++i)
        {
            this.icon[i] = iconRegister.registerIcon("thkaguyamod:" + iconName[i]);
        }
    }
	
	//アイテムを発光させる。 trueなら発光
	@Override
	public boolean hasEffect(ItemStack itemStack, int pass)
	{   
		return itemStack.getItemDamage() == 0;
    }
	
	//アイテム名の下に情報を付加する
    /*@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean bool)
	{
    	super.addInformation(itemStack, player, list, bool);
		//list.add(StatCollector.translateToLocal("thKaguya.item.kaigo") + ";");
	}*/

	//Forgeの追加メソッド　エンチャントブックの使用を許可するか
	@Override
	public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2)
    {
        return false;
    }
}
