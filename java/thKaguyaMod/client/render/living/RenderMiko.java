package thKaguyaMod.client.render.living;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import thKaguyaMod.client.model.living.ModelMiko;
import thKaguyaMod.entity.living.EntityMiko;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/** 神子を描画する */
@SideOnly(Side.CLIENT)
public class RenderMiko extends RenderTHBoss
{
	
	ResourceLocation texture = new ResourceLocation("thkaguyamod", "textures/mob/MikoTexture.png");

    public RenderMiko()
    {
        super(new ModelMiko(), 0.25F);
    }
    
    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
    	super.doRender(entity, x, y, z, yaw, pitch);
    	this.render((EntityMiko)entity, x, y, z, yaw, pitch);
    	
    }

	public void render(EntityMiko entity, double x, double y, double z, float yaw, float pitch)
	{
	}

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.getEntityTexture((EntityMiko)entity);
    }
    
    protected ResourceLocation getEntityTexture(EntityMiko entity)
    {
    	return texture;
    }
}