package thKaguyaMod.client.render.living;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;

import org.lwjgl.opengl.GL11;

import thKaguyaMod.THShotLib;
import thKaguyaMod.entity.living.EntityDanmakuMob;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class RenderTHBoss extends RenderLiving
{
	//ボスの描画の共通処理
	
	ResourceLocation statusTexture = new ResourceLocation("thkaguyamod", "textures/mob/Status.png");

    public RenderTHBoss(ModelBase model, float size)
    {
        super(model, size);
    }
    
    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
    	//GL11.glDisable(GL11.GL_LIGHTING);
    	//GL11.glEnable(GL11.GL_BLEND);
    	super.doRender(entity, x, y, z, yaw, pitch);
    	this.renderTHBossStatus((EntityDanmakuMob)entity, x, y, z, yaw, pitch);
    	
    }

	public void renderTHBossStatus(EntityDanmakuMob danmakuMob, double x, double y, double z, float yaw, float pitch)
	{
		if(danmakuMob.getDanmakuPattern() == danmakuMob.NOT_ATTACK)
		{
			return;
		}
		
		//体力ゲージを表示する
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);
        
    	
        Tessellator tessellator = Tessellator.instance;
        //GL11.glDepthMask(false);
        float viewY = renderManager.playerViewY % 360F;
        if(viewY > 180F)
        {
        	viewY -= 360F;
        }
        else if(viewY <= -180)
        {
        	viewY += 360F;
        }
        Vec3 look = THShotLib.getVecFromAngle(viewY, renderManager.playerViewX);
        Vec3 toEntity = Vec3.createVectorHelper(x, y, z);
        float span = THShotLib.getVectorAndVectorAngle(look, toEntity);
        float alpha = 1F - (Math.abs(span) - 20F) / 30F;
        
		double distance = renderManager.getDistanceToCamera(danmakuMob.posX, danmakuMob.posY, danmakuMob.posZ);
		float size = 1.0F + (float)distance / 64F;
		if(size > 5.0F )
		{
			size = 5.0F;
		}
        
        if(Math.abs(span) <= 20F)
        {
        	GL11.glTranslatef((float)x, (float)y + danmakuMob.height + 1.5F, (float)z);
        	GL11.glScalef(1.0F * size, 1.0F * size, 1.0F * size);
        	

        
        	GL11.glRotatef(  -renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        	GL11.glRotatef(   renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        
    		this.bindTexture(statusTexture);
    		int cardNo = danmakuMob.getUsingSpellCardNo();

    		float hp = danmakuMob.getHealth() / danmakuMob.getMaxHealth();
    		float hp2 = danmakuMob.getHealth() / danmakuMob.getMaxHealth() * 2.0F - 1.0F;
    		
			tessellator.startDrawingQuads();
			//tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, alpha);
			//tessellator.setNormal(0.0F, 1.0F, 0.0F);
	    	tessellator.addVertexWithUV(  -1.02F, 0.07F, 0.001D, 0.0F,  3F / 16F);
	    	tessellator.addVertexWithUV(  1.02F, 0.07F, 0.001D, 1.0F,  3F / 16F);
	    	tessellator.addVertexWithUV(  1.02F, -0.02F, 0.001D, 1.0F,  4F / 16F);
	    	tessellator.addVertexWithUV(  -1.02F,-0.02F, 0.001D, 0.0F,  4F / 16F);
	    	tessellator.draw();
	        
	    	//スペルカードを使用中なら
	    	if(cardNo >= 0)
	    	{
				tessellator.startDrawingQuads();
				//tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, alpha);
				//tessellator.setNormal(0.0F, 1.0F, 0.0F);
		    	tessellator.addVertexWithUV(  -hp2, 0.05F, 0.0D, hp,  8F / 16F);
		    	tessellator.addVertexWithUV(  1.0F, 0.05F, 0.0D, 0.0F,  8F / 16F);
		    	tessellator.addVertexWithUV(  1.0F, 0.00F, 0.0D, 0.0F,  11F / 16F);
		    	tessellator.addVertexWithUV(  -hp2, 0.00F, 0.0D, hp,  11F / 16F);
		    	
		    	tessellator.draw();
	    	}
	    	//スペルカードを使用していないなら
	    	else
	    	{
				tessellator.startDrawingQuads();
				//tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, alpha);
				//tessellator.setNormal(0.0F, 1.0F, 0.0F);
		    	tessellator.addVertexWithUV(  -hp2, 0.05F, 0.0D, hp,  0F / 16F);
		    	tessellator.addVertexWithUV(  1.0F, 0.05F, 0.0D, 0.0F,  0F / 16F);
		    	tessellator.addVertexWithUV(  1.0F, 0.00F, 0.0D, 0.0F,  3F / 16F);
		    	tessellator.addVertexWithUV(  -hp2, 0.00F, 0.0D, hp,  3F / 16F);
		    	
		    	tessellator.draw();
	    	}
	    	
	    	GL11.glRotatef( 180F, 0.0F, 0.0F, 1.0F);
	    	GL11.glScalef(0.02F, 0.02F, 0.02F);
	    	tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, alpha);
	    	FontRenderer font = this.getFontRendererFromRenderManager();
	    	font.drawStringWithShadow(danmakuMob.getDanmakuMobName(), -50, 0, 0x00FF88);
	    	
	    	//スペルカードを使用中なら、スペルカード名を表示する
	    	if(cardNo >= 0)
	    	{
	    		tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, alpha);
		    	font.drawStringWithShadow(StatCollector.translateToLocal("item.thSpellCard." + cardNo + ".name"), -50, -12, 0xFFFFFF);
	    	}
    	}
    	
    	GL11.glDisable(GL11.GL_BLEND);
    	GL11.glEnable(GL11.GL_LIGHTING);
    	GL11.glPopMatrix();
	}

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.getEntityTexture((EntityDanmakuMob)entity);
    }
    
    protected ResourceLocation getEntityTexture(EntityDanmakuMob danmakuMob)
    {
    	return statusTexture;
    }
}