package thKaguyaMod.client.render.living;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import thKaguyaMod.client.model.living.ModelRinnosuke;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderRinnosuke extends RenderLiving
{
	//森近霖之助を描画する
	
	ResourceLocation texture = new ResourceLocation("thkaguyamod", "textures/mob/RinnosukeTexture.png");

    public RenderRinnosuke()
    {
        super(new ModelRinnosuke(), 0.25F);
    }
    
    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
    	super.doRender(entity, x, y, z, yaw, pitch);
    	this.renderRinnosuke((EntityLivingBase)entity, x, y, z, yaw, pitch);
    	
    }

	public void renderRinnosuke(EntityLivingBase living, double x, double y, double z, float yaw, float pitch)
	{
	}

    @Override
    protected ResourceLocation getEntityTexture(Entity living)
    {
        return this.getEntityTexture((EntityLivingBase)living);
    }
    
    protected ResourceLocation getEntityTexture(EntityLivingBase living)
    {
    	return texture;
    }
}