package thKaguyaMod.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thKaguyaMod.client.model.ModelKinkakuzi;
import thKaguyaMod.entity.item.EntityKinkakuzi;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderKinkakuzi extends Render
{
	
	//金閣寺の一枚天井を描画
	private static final ResourceLocation kinkakuziTexture = new ResourceLocation("thkaguyamod", "textures/Kinkakuzi.png");
    protected ModelBase modelKinkakuzi;

    public RenderKinkakuzi()
    {
        shadowSize = 3.0F;//多分影のサイズ
        modelKinkakuzi = new ModelKinkakuzi();
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
        doRenderKinkakuzi((EntityKinkakuzi)entity, x, y, z, yaw, pitch);
    }
    
    public void doRenderKinkakuzi(EntityKinkakuzi kinkakuzi, double x, double y, double z, float yaw, float pitch)
    {
        GL11.glPushMatrix();
        bindTexture(getEntityTexture(kinkakuzi));
        GL11.glTranslatef((float)x, (float)y, (float)z);
        GL11.glScalef(3.0F, 0.75F, 3.0F);
        GL11.glRotatef(180F - yaw, 0.0F, 1.0F, 0.0F);
        float f = (float)kinkakuzi.getTimeSinceHit() - pitch;
        float f1 = (float)kinkakuzi.getDamageTaken() - pitch;

        if (f1 < 0.0F)
        {
            f1 = 0.0F;
        }

        if (f > 0.0F)
        {
            GL11.glRotatef(((MathHelper.sin(f) * f * f1) / 10.0F) * (float)kinkakuzi.getForwardDirection(), 1.0F, 0.0F, 0.0F);
        }

    	
        modelKinkakuzi.render(kinkakuzi, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return kinkakuziTexture;
	}
}
