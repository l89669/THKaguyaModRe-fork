package thKaguyaMod.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thKaguyaMod.client.model.ModelHisou;
import thKaguyaMod.entity.item.EntityHisou;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderHisou extends Render
{
	
	//緋想の剣の描画
	private static final ResourceLocation hisouTexture = new ResourceLocation("thkaguyamod", "textures/HisouTexture.png");
    protected ModelBase modelHisou;

    public RenderHisou()
    {
        shadowSize = 0.5F;//多分影のサイズ
        modelHisou = new ModelHisou();
    }
    
    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
        doRenderHisou((EntityHisou)entity, x, y, z, yaw, pitch);
    }

    public void doRenderHisou(EntityHisou hisou, double x, double y, double z, float yaw, float pitch)
    {
        GL11.glPushMatrix();
        this.bindTexture(getEntityTexture(hisou));
        GL11.glDisable(GL11.GL_LIGHTING);
    	GL11.glEnable(GL11.GL_BLEND);
    	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        GL11.glTranslatef((float)x, (float)y, (float)z);
    	GL11.glRotatef(-yaw, 0.0F, 1.0F, 0.0F);
    	GL11.glRotatef(hisou.rotationPitch, 1.0F, 0.0F, 0.0F);
    	GL11.glRotatef(hisou.getAngle(), 0.0F, 0.0F, 1.0F);

        GL11.glScalef(0.3F, 0.7F, 0.3F);//倍率　縦方向 高さ　幅
    	GL11.glColor4f(1.0F, 1.0F, 1.0F, (float)hisou.getNum() * 0.125F);
    	
        modelHisou.render(hisou, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
    	GL11.glDisable(GL11.GL_BLEND);
    	GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return hisouTexture;
	}
}
