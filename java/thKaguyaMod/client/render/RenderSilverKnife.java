package thKaguyaMod.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thKaguyaMod.client.model.ModelSilverKnife;
import thKaguyaMod.entity.item.EntitySilverKnife;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSilverKnife extends Render
{
	
	//銀のナイフの描画
	private static final ResourceLocation resourceLocation_Knife_Blue = new ResourceLocation("thkaguyamod", "textures/shot/SilverKnife_Blue.png");
	private static final ResourceLocation resourceLocation_Knife_Red = new ResourceLocation("thkaguyamod", "textures/shot/SilverKnife_Red.png");
	private static final ResourceLocation resourceLocation_Knife_Green = new ResourceLocation("thkaguyamod", "textures/shot/SilverKnife_Green.png");
	private static final ResourceLocation resourceLocation_Knife_White= new ResourceLocation("thkaguyamod", "textures/shot/SilverKnife_White.png");
	
    protected ModelBase modelSilverKnife;

    public RenderSilverKnife()
    {
        shadowSize = 0.3F;//多分影のサイズ
        modelSilverKnife = new ModelSilverKnife();
    }
    
    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
        renderSilverKnife((EntitySilverKnife)entity, x, y, z, yaw, pitch);
    }

    public void renderSilverKnife(EntitySilverKnife silverKnife, double x, double y, double z, float yaw, float pitch)
    {
        GL11.glPushMatrix();
        this.bindEntityTexture(silverKnife);
        GL11.glTranslatef((float)x, (float)y, (float)z);
        
    	GL11.glRotatef( 180F - yaw,   0F, 1F, 0F);
    	GL11.glRotatef(-silverKnife.rotationPitch, 1F, 0F, 0F);
    	GL11.glRotatef( silverKnife.getKnifeZAngle(), 0F, 0F, 1F);

    	GL11.glScalef(0.5F, 0.5F, 0.5F);//倍率　縦方向 高さ　幅
    	//最後の引数がサイズの倍率
    	modelSilverKnife.render(silverKnife, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return getEntityTexture((EntitySilverKnife)entity);
	}
	
	//テクスチャを返す
    protected ResourceLocation getEntityTexture(EntitySilverKnife knife)
    {
    	switch(knife.getKnifeColor())
    	{
    		case 0:
    			return resourceLocation_Knife_Blue;
    		case 1:
    			return resourceLocation_Knife_Red;
    		case 2:
    			return resourceLocation_Knife_Green;
    		default:
    			return resourceLocation_Knife_White;
    	}
    }
}
