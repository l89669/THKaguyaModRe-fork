package thKaguyaMod.client.render.living;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import thKaguyaMod.client.model.living.ModelCirno;
import thKaguyaMod.entity.living.EntityCirno;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCirno extends RenderTHBoss
{
	//チルノを描画する
	
	ResourceLocation cirnoTexture = new ResourceLocation("thkaguyamod", "textures/mob/CirnoTexture.png");

    public RenderCirno()
    {
        super(new ModelCirno(), 0.25F);
    }
    
    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
    	super.doRender(entity, x, y, z, yaw, pitch);
    	this.renderTHFairyCirno((EntityCirno)entity, x, y, z, yaw, pitch);
    }

	public void renderTHFairyCirno(EntityCirno thFairyCirno, double x, double y, double z, float yaw, float pitch)
	{
	}

	//@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.getEntityTexture((EntityCirno)entity);
	}
	
    protected ResourceLocation getEntityTexture(EntityCirno cirno)
    {
    	return cirnoTexture;
    }
}
