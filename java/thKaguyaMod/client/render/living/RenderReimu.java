package thKaguyaMod.client.render.living;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import thKaguyaMod.client.model.living.ModelReimu;
import thKaguyaMod.entity.living.EntityReimu;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/** 霊夢を描画する */
@SideOnly(Side.CLIENT)
public class RenderReimu extends RenderTHBoss
{
	
	ResourceLocation texture = new ResourceLocation("thkaguyamod", "textures/mob/ReimuTexture.png");

    public RenderReimu()
    {
        super(new ModelReimu(), 0.25F);
    }
    
    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
    	super.doRender(entity, x, y, z, yaw, pitch);
    	this.render((EntityReimu)entity, x, y, z, yaw, pitch);
    	
    }

	public void render(EntityReimu entity, double x, double y, double z, float yaw, float pitch)
	{
	}

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.getEntityTexture((EntityReimu)entity);
    }
    
    protected ResourceLocation getEntityTexture(EntityReimu entity)
    {
    	return texture;
    }
}