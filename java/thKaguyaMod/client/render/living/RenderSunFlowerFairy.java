package thKaguyaMod.client.render.living;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import thKaguyaMod.client.model.living.ModelSunFlowerFairy;
import thKaguyaMod.entity.living.EntityTHFairy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSunFlowerFairy extends RenderLiving
{
	//ひまわり妖精を描画する

    public RenderSunFlowerFairy()
    {
        super(new ModelSunFlowerFairy(), 0.25F);
        
    }
    
    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
    	super.doRender(entity, x, y, z, yaw, pitch);
    	this.renderTHFairy((EntityTHFairy)entity, x, y, z, yaw, pitch);
    	
    }

	public void renderTHFairy(EntityTHFairy thFairy, double x, double y, double z, float yaw, float pitch)
	{
		
	}

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.getEntityTexture((EntityTHFairy)entity);
    }
    
    protected ResourceLocation getEntityTexture(EntityTHFairy thFairy)
    {	
        return new ResourceLocation("thkaguyamod", "textures/mob/FairyTexture_10.png");
    }
}
