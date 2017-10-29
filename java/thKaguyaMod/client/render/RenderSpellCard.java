package thKaguyaMod.client.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thKaguyaMod.entity.spellcard.EntitySpellCard;
import thKaguyaMod.entity.spellcard.THSpellCard;
import thKaguyaMod.registry.SpellCardRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSpellCard extends Render
{
	
	//スペルカードの描画

    public RenderSpellCard()
    {
    }
    
    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
        renderSpellCard((EntitySpellCard)entity, x, y, z, yaw, pitch);
    }


    public void renderSpellCard(EntitySpellCard spellCard, double x, double y, double z, float yaw, float pitch)
    {
        GL11.glPushMatrix();
        bindEntityTexture(spellCard);
        GL11.glTranslatef((float)x, (float)y, (float)z);
    	GL11.glDisable(GL11.GL_LIGHTING);
        float sizeRate = 1.0F;
        GL11.glScalef(sizeRate, sizeRate, sizeRate);
        Tessellator tessellator = Tessellator.instance;
    	int color2 = 0;
    	float rvl = 14F / 128F;
    	float rul = 0.0F;
    	float rvr = 114F/ 128F;
    	float rur = 1.00F;
    	float fvl = 0.25F;
    	float ful = 0.0F;
    	float fvr = 0.50F;
    	float fur = 0.75F;
    	GL11.glRotatef((float)spellCard.ticksExisted * 21F, 0.0F, 1.0F, 0.0F);
    	GL11.glRotatef(30F, 0.0F, 0.0F, 1.0F);
    	
    	//裏面
    	tessellator.startDrawingQuads();
    	tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(  0.2F,  0.3F, 0.0D, rvl, rul);
        tessellator.addVertexWithUV( -0.2F,  0.3F, 0.0D, rvr, rul);
        tessellator.addVertexWithUV( -0.2F, -0.3F, 0.0D, rvr, rur);
        tessellator.addVertexWithUV(  0.2F, -0.3F, 0.0D, rvl, rur);
        tessellator.draw();
    	//表面
    	tessellator.startDrawingQuads();
    	tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV( -0.2F,  0.3F, 0.0D, rvl, rul);
        tessellator.addVertexWithUV(  0.2F,  0.3F, 0.0D, rvr, rul);
        tessellator.addVertexWithUV(  0.2F, -0.3F, 0.0D, rvr, rur);
        tessellator.addVertexWithUV( -0.2F, -0.3F, 0.0D, rvl, rur);
        tessellator.draw();
        
        this.bindTexture(getFrameTexture(spellCard));
        
    	//裏面
    	tessellator.startDrawingQuads();
    	tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(  0.2F,  0.3F,  0.001D, rvl, rul);
        tessellator.addVertexWithUV( -0.2F,  0.3F,  0.001D, rvr, rul);
        tessellator.addVertexWithUV( -0.2F, -0.3F,  0.001D, rvr, rur);
        tessellator.addVertexWithUV(  0.2F, -0.3F,  0.001D, rvl, rur);
        tessellator.draw();
    	//表面
    	tessellator.startDrawingQuads();
    	tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV( -0.2F,  0.3F, -0.001D, rvl, rul);
        tessellator.addVertexWithUV(  0.2F,  0.3F, -0.001D, rvr, rul);
        tessellator.addVertexWithUV(  0.2F, -0.3F, -0.001D, rvr, rur);
        tessellator.addVertexWithUV( -0.2F, -0.3F, -0.001D, rvl, rur);
        tessellator.draw();

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
    


 
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return getEntityTexture((EntitySpellCard)entity);
	}
	
    protected ResourceLocation getEntityTexture(EntitySpellCard spellCard)
    {
    	String domain = "thkaguyamod";
    	domain = SpellCardRegistry.getSpellCardModDomain(spellCard.getSpellCardNumber());
    	return new ResourceLocation(domain, "textures/items/spellCard/" + SpellCardRegistry.getSpellCardName(spellCard.getSpellCardNumber()) + ".png");
    }
    
    protected ResourceLocation getFrameTexture(EntitySpellCard spellCard)
    {
    	String domain = "thkaguyamod";
    	domain = SpellCardRegistry.getSpellCardModDomain(spellCard.getSpellCardNumber());
    	return new ResourceLocation(domain, "textures/items/spellCard/spellcard_lv" + this.getLevel(spellCard.getSpellCardNumber()) + ".png");
    }
    
    /**
     * スペルカードIDからスペルカードの宣言コストのレベルを取得する
     * @param itemStack : アイテムスタック
     * @return 宣言コストのレベル（1～5)
     */
    protected int getLevel( int spellCardID )
    {
		int needLevel = 1;
		Class<?> spellcard;// = THSpellCard_0.class;
		THSpellCard useSpellCard;// = spellcard.newInstance();
		
		if((spellcard = SpellCardRegistry.getSpellCardClass( spellCardID )) != null)
		{
			try {
				useSpellCard = (THSpellCard)spellcard.newInstance();

				needLevel = useSpellCard.getNeedLevel();
				
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		return needLevel;
    }
}
