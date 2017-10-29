package thKaguyaMod.client.render.living;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import thKaguyaMod.client.model.living.ModelSakuya;
import thKaguyaMod.entity.living.EntitySakuya;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/** 咲夜を描画する */
@SideOnly(Side.CLIENT)
public class RenderSakuya extends RenderTHBoss
{
	
	ResourceLocation texture = new ResourceLocation("thkaguyamod", "textures/mob/SakuyaTexture.png");

    public RenderSakuya()
    {
        super(new ModelSakuya(), 0.25F);
    }
    
    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
    	super.doRender(entity, x, y, z, yaw, pitch);
    	this.render((EntitySakuya)entity, x, y, z, yaw, pitch);
    	
    }

	public void render(EntitySakuya entity, double x, double y, double z, float yaw, float pitch)
	{
	}

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.getEntityTexture((EntitySakuya)entity);
    }
    
    protected ResourceLocation getEntityTexture(EntitySakuya entity)
    {
    	return texture;
    }
}