package thKaguyaMod.client.render.living;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import thKaguyaMod.client.model.living.ModelSanae;
import thKaguyaMod.entity.living.EntitySanae;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/** 早苗を描画する */
@SideOnly(Side.CLIENT)
public class RenderSanae extends RenderTHBoss
{
	
	ResourceLocation sanaeTexture = new ResourceLocation("thkaguyamod", "textures/mob/SanaeTexture.png");

    public RenderSanae()
    {
        super(new ModelSanae(), 0.25F);
    }
    
    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
    	super.doRender(entity, x, y, z, yaw, pitch);
    	this.renderSanae((EntitySanae)entity, x, y, z, yaw, pitch);
    	
    }

	public void renderSanae(EntitySanae sanae, double x, double y, double z, float yaw, float pitch)
	{
	}

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.getEntityTexture((EntitySanae)entity);
    }
    
    protected ResourceLocation getEntityTexture(EntitySanae sanae)
    {
    	return sanaeTexture;
    }
}