package thKaguyaMod.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import thKaguyaMod.THKaguyaCore;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class RenderDivineSpiritBlock implements ISimpleBlockRenderingHandler
{
	
	protected float colorR[] = { 224F/255F,   0F/255F,   0F/255F, 224F/255F, 224F/255F,   0F/255F, 255F/255F, 255F/255F};
	protected float colorG[] = {   0F/255F,   0F/255F, 224F/255F, 224F/255F,   0F/255F, 224F/255F, 128F/255F, 255F/255F};
	protected float colorB[] = {   0F/255F, 224F/255F,   0F/255F,   0F/255F, 224F/255F, 224F/255F,   0F/255F, 255F/255F};

	public void renderInventoryBlock(Block block, int metadata, int renderType, RenderBlocks renderer)
	{
	}
	
	//ワールドでのレンダー
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		return false;
	}
	
	
	//インベントリのレンダーが面倒くさいなら、ココをfalseに。テクスチャだけ表示されるようになる
	public boolean shouldRender3DInInventory()
	{
		//Tessellator tessellator = Tessellator.instance;
		//renderLaser(tessellator, 0, 0, 0, 1.0D, 1.0F, 1.0D, 0, 0.9F, 0);
		return false;
	}
	 
	//レンダーIDを返す
	public int getRenderId()
	{
		return THKaguyaCore.blockRenderId;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
}
