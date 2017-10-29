package thKaguyaMod.client.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thKaguyaMod.client.THKaguyaRenderLib;
import thKaguyaMod.entity.EntityTHItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTHItem extends Render
{
	
	//各種東方アイテムの描画
	
	private ResourceLocation smallPower = new ResourceLocation("thkaguyamod", "textures/items/material/SmallPowerUpItem.png");
	private ResourceLocation bigPower = new ResourceLocation("thkaguyamod", "textures/items/material/BigPowerUpItem.png");
	private ResourceLocation point = new ResourceLocation("thkaguyamod", "textures/items/material/THPointItem.png");
	private ResourceLocation bomb = new ResourceLocation("thkaguyamod", "textures/items/material/BombItem.png");
	private ResourceLocation extend = new ResourceLocation("thkaguyamod", "textures/items/material/ExtendItem.png");
	
    public RenderTHItem()
    {
    }
    
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
        renderTHItem((EntityTHItem)entity, x, y, z, yaw, pitch);
    }


    public void renderTHItem(EntityTHItem item, double x, double y, double z, float yaw, float pitch)
    {
        GL11.glPushMatrix();
        bindEntityTexture(item);
        GL11.glTranslatef((float)x, (float)y, (float)z);
    	GL11.glDisable(GL11.GL_LIGHTING);
        float sizeRate = 2.0F;
        GL11.glScalef(sizeRate, sizeRate, sizeRate);
        Tessellator tessellator = Tessellator.instance;
    	int color2 = 0;
    	float rvl = 0.0F;
    	float rul = 0.0F;
    	float rvr = 1.00F;
    	float rur = 1.00F;
    	// プレイヤーに常に正面を向けるようにする
        THKaguyaRenderLib.getGLRotatefByTherdPersonView(renderManager);
    	
    	//表面
    	tessellator.startDrawingQuads();
    	tessellator.setNormal(0.0F, 1.0F, 0.0F);
    	tessellator.addVertexWithUV(  0.2F,  0.2F, 0.0D, rvr, rul);
        tessellator.addVertexWithUV( -0.2F,  0.2F, 0.0D, rvl, rul);
        tessellator.addVertexWithUV( -0.2F, -0.2F, 0.0D, rvl, rur);
        tessellator.addVertexWithUV(  0.2F, -0.2F, 0.0D, rvr, rur);
        
        tessellator.draw();

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
    


 
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return getEntityTexture((EntityTHItem)entity);
	}
	
    protected ResourceLocation getEntityTexture(EntityTHItem item)
    {
    	switch(item.getItemType())
    	{
    		case 1:
    			return smallPower;
    		case 2:
    			return bigPower;
    		case 4:
    			return point;
    		case 10:
    			return bomb;
    		case 11:
    			return extend;
    		default:
    			return smallPower;
    	}
    }
}