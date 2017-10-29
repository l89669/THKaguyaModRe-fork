package thKaguyaMod.client.render.living;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import thKaguyaMod.client.model.living.ModelCursedDecoyDoll;
import thKaguyaMod.entity.item.EntityCursedDecoyDoll;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/** 呪いのデコイ人形を描画する */
@SideOnly(Side.CLIENT)
public class RenderCursedDecoyDoll extends RenderLiving
{
	
	ResourceLocation texture = new ResourceLocation("thkaguyamod", "textures/mob/DecoyTexture.png");

    public RenderCursedDecoyDoll()
    {
        super(new ModelCursedDecoyDoll(), 0.25F);
    }
    
    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
    	super.doRender(entity, x, y, z, yaw, pitch);
    	this.render((EntityCursedDecoyDoll)entity, x, y, z, yaw, pitch);
    	
    }

	public void render(EntityCursedDecoyDoll entity, double x, double y, double z, float yaw, float pitch)
	{
	}

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.getEntityTexture((EntityCursedDecoyDoll)entity);
    }
    
    protected ResourceLocation getEntityTexture(EntityCursedDecoyDoll entity)
    {
    	return texture;
    }
}