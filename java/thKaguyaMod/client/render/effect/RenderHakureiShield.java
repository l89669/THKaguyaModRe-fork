package thKaguyaMod.client.render.effect;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thKaguyaMod.entity.effect.EntityHakureiShield;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderHakureiShield extends Render
{
	
	//霊夢の結界の描画
	private static final ResourceLocation field_110782_f = new ResourceLocation("thkaguyamod", "textures/Amulet.png");

    public RenderHakureiShield()
    {
    }

    public void doRenderHakureiShield(EntityHakureiShield entityHakureiShield, double x, double y, double z,
            float f, float f1)
    {
        GL11.glPushMatrix();
        //func_110777_b(entityHakureiShield);
        bindEntityTexture(entityHakureiShield);
        GL11.glTranslatef((float)x, (float)y, (float)z);
		GL11.glDisable(GL11.GL_LIGHTING);
    	GL11.glEnable(GL11.GL_BLEND);
    	GL11.glDisable(GL11.GL_CULL_FACE);//両面描画
    	GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
        float size = 1.0F;
        GL11.glScalef(size, size, size);
        //loadTexture("/textures/Kishitudan2.png");
        
    	Tessellator tessellator = Tessellator.instance;

        float minU = 0.0F;
        float maxU = 0.5F;
        float minV = 0.0F;
        float maxV = 1.0F;
    	float width = 1.5F;
        float height= 1.5F;
        GL11.glRotatef(f, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(0F, 1.0F, 0.0F, 0.0F);
        tessellator.startDrawingQuads();

    	tessellator.setColorRGBA_F(0.9F, 0.6F, 0.1F, 0.8F);
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(-width, -height, 0.0D, minU, maxV);
        tessellator.addVertexWithUV( width, -height, 0.0D, maxU, maxV);
        tessellator.addVertexWithUV( width,  height, 0.0D, maxU, minV);
        tessellator.addVertexWithUV(-width,  height, 0.0D, minU, minV);
    	
        tessellator.draw();
        GL11.glEnable(GL11.GL_CULL_FACE);//表綿描画
    	GL11.glDisable(GL11.GL_BLEND);
    	GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
    
    protected ResourceLocation func_110784_a(EntityHakureiShield entityHakureiShield)
    {
        return field_110782_f;
    }

    protected ResourceLocation func_110775_a(Entity entity)
    {
        return this.func_110784_a((EntityHakureiShield)entity);
    }

    public void doRender(Entity entity, double x, double y, double z,
            float f, float f1)
    {
        doRenderHakureiShield((EntityHakureiShield)entity, x, y, z, f, f1);
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}
