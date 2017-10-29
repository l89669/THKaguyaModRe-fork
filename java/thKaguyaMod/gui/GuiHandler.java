package thKaguyaMod.gui;

import thKaguyaMod.THKaguyaCore;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

/** GUIハンドラー */
public class GuiHandler implements IGuiHandler
{
	
	/** クライアント側のGUI登録 */
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch( ID )
		{
			case THKaguyaCore.guiDanmakuCraftingID:
				return new GuiDanmakuCrafting( player, world, x, y, z );
			case THKaguyaCore.guiDanmakuCraftingLaserID:
				return new GuiDanmakuCraftingLaser( player, world, x, y, z );
			case THKaguyaCore.guiMerchantSanaeID:
				return new GuiMerchantSanae( player, world, "Sanae" );
			case THKaguyaCore.guiMiracleMalletID:
				return new GuiMiracleMallet( player, world, x, y, z );
			default:
				return null;
		}
	}
 
	/** サーバー側のGUI登録 */
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch( ID )
		{
			case THKaguyaCore.guiDanmakuCraftingID:
				return new ContainerDanmakuCrafting( player.inventory, world, x, y, z );
			case THKaguyaCore.guiDanmakuCraftingLaserID:
				return new ContainerDanmakuCraftingLaser( player.inventory, world, x, y, z );
			case THKaguyaCore.guiMerchantSanaeID:
				return new ContainerMerchantSanae( player, world );
			case THKaguyaCore.guiMiracleMalletID:
				return new ContainerMiracleMallet( player.inventory, world, x, y, z );
			default:
				return null;
		}
	}
	
}
