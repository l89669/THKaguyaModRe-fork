package thKaguyaMod.item;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
//import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockDivineSpirit extends ItemBlock
{
	public static final String[] iconName = {
		"thkaguyamod:divinespirit_red",
		"thkaguyamod:divinespirit_blue",
		"thkaguyamod:divinespirit_green",
		"thkaguyamod:divinespirit_yellow",
		"thkaguyamod:divinespirit_purple",
		"thkaguyamod:divinespirit_aqua",
		"thkaguyamod:divinespirit_orange",
		"thkaguyamod:divinespirit_white"};
	
	public static final String[] name = {
		"red", "blue", "green", "yellow", "purple", "aqua", "orange", "white"
	};
	
	private IIcon iconArray[];
	
	public ItemBlockDivineSpirit(Block block)
	{
		super(block);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}
	
	@Override
	public int getMetadata(int damage)
	{
		return damage;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		return super.getUnlocalizedName() + "." + name[itemStack.getItemDamage()];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.iconArray = new IIcon[iconName.length];

        for (int i = 0; i < iconName.length; ++i)
        {
            this.iconArray[i] = par1IconRegister.registerIcon(iconName[i]);
        }
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	//ダメージ値によってアイテムアイコンを変える
    public IIcon getIconFromDamage(int damage)
    {
        int i = MathHelper.clamp_int(damage, 0, 8);
        return this.iconArray[i];
    }
}
