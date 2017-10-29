package thKaguyaMod.client.render.living;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import thKaguyaMod.client.model.living.ModelWriggle;
import thKaguyaMod.entity.living.EntityWriggle;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/** リグルを描画する */
@SideOnly(Side.CLIENT)
public class RenderWriggle extends RenderTHBoss
{
	
	ResourceLocation texture = new ResourceLocation("thkaguyamod", "textures/mob/WriggleTexture.png");

    public RenderWriggle()
    {
        super(new ModelWriggle(1.0F, 1.0F, 64, 64), 0.25F);
    }
    
    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
    	super.doRender(entity, x, y, z, yaw, pitch);
    	this.render((EntityWriggle)entity, x, y, z, yaw, pitch);
    	
    }

	public void render(EntityWriggle entity, double x, double y, double z, float yaw, float pitch)
	{
	}

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.getEntityTexture((EntityWriggle)entity);
    }
    
    protected ResourceLocation getEntityTexture(EntityWriggle entity)
    {
    	return texture;
    }
}