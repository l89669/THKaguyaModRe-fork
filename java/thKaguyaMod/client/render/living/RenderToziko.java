package thKaguyaMod.client.render.living;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import thKaguyaMod.client.model.living.ModelToziko;
import thKaguyaMod.entity.living.EntityToziko;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderToziko extends RenderTHBoss
{
	//屠自古を描画する
	
	ResourceLocation TozikoTexture = new ResourceLocation("thkaguyamod", "textures/mob/TozikoTexture.png");

    public RenderToziko()
    {
        super(new ModelToziko(), 0.25F);
    }
    
    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
    	super.doRender(entity, x, y, z, yaw, pitch);
    	this.renderToziko((EntityToziko)entity, x, y, z, yaw, pitch);
    	
    }

	public void renderToziko(EntityToziko toziko, double x, double y, double z, float yaw, float pitch)
	{


	}


    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.getEntityTexture((EntityToziko)entity);
    }
    
    protected ResourceLocation getEntityTexture(EntityToziko toziko)
    {
    	return TozikoTexture;
    }
}