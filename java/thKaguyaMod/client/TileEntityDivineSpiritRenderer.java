package thKaguyaMod.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thKaguyaMod.tileentity.TileEntityDivineSpirit;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityDivineSpiritRenderer extends TileEntitySpecialRenderer
{
	private static final ResourceLocation resourceLocation_Shinrei = new ResourceLocation("thkaguyamod", "textures/shot/MusouFuuin.png");
	protected float colorR[] = { 224F/255F,   0F/255F,   0F/255F, 224F/255F, 224F/255F,   0F/255F, 255F/255F, 255F/255F};
	protected float colorG[] = {   0F/255F,   0F/255F, 224F/255F, 224F/255F,   0F/255F, 224F/255F, 128F/255F, 255F/255F};
	protected float colorB[] = {   0F/255F, 224F/255F,   0F/255F,   0F/255F, 224F/255F, 224F/255F,   0F/255F, 255F/255F};
	float yaw = 0F;
	
    public void renderTileEntityDivineSpirit(TileEntityDivineSpirit tileEntityDivineSpirit, double x, double y, double z, float par8)
    {
        GL11.glPushMatrix();
        
        Minecraft mc = Minecraft.getMinecraft();
        int time = ((int)mc.theWorld.getWorldTime() + tileEntityDivineSpirit.xCoord * tileEntityDivineSpirit.yCoord * tileEntityDivineSpirit.zCoord);
        double time_r = time / 180.0D * Math.PI;
    	double posX = x + 0.5D;
    	double posY = y + 0.5D + (float)Math.sin(time * 5F / 180F * Math.PI) * 0.16F;
    	double posZ = z + 0.5D;
        GL11.glTranslatef((float)posX, (float)posY, (float)posZ);
    	GL11.glEnable(GL11.GL_NORMALIZE);
    	GL11.glEnable(GL11.GL_BLEND);
    	GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
    	//GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
    	GL11.glDepthMask(false);

        this.bindTexture(resourceLocation_Shinrei);
    	Tessellator tessellator = Tessellator.instance;
    	int color = tileEntityDivineSpirit.getBlockMetadata();
    	
    	

    	float viewYaw = (float)Math.atan2(posX, posZ) / (float)Math.PI * 180.0F;
    	float viewPitch = (float)Math.atan2(posY, Math.sqrt(posX * posX + posZ * posZ)) / (float)Math.PI * 180.0F;
    	
    	if(mc.thePlayer.posX - mc.thePlayer.prevPosX > 0.1D)
    	{yaw+=10;}
        GL11.glRotatef(viewYaw - 180F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(viewPitch, 1.0F, 0.0F, 0.0F);
        GL11.glScalef(0.8F + (float)Math.sin(time_r * 5.0D) * 0.16F, 0.8F + (float)Math.sin(time_r * 5.0D) * 0.16F, 0.0F);
    	
    	int pattern = 0;//time % 2;
        float umin = (float)((pattern % 32) * 32 + 0) / 64F;
        float umax = (float)((pattern % 32) * 32 + 32) / 64F;
        float vmin = 0.0F;
        float vmax = 1.0F;
    	
    	tessellator.startDrawingQuads();
    	tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], 0.3F);
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(-0.7F + Math.sin(time_r * 3.0D) * 0.1F, -0.7F - Math.cos(time_r * 7.0D) * 0.1F, 0.0D, umin, vmax);
        tessellator.addVertexWithUV( 0.7F - Math.cos(time_r * 4.0D) * 0.1F, -0.7F - Math.sin(time_r * 5.0D) * 0.1F, 0.0D, umax, vmax);
        tessellator.addVertexWithUV( 0.7F + Math.sin(time_r * 5.0D) * 0.1F,  0.7F + Math.cos(time_r * 4.0D) * 0.1F, 0.0D, umax, vmin);
        tessellator.addVertexWithUV(-0.7F - Math.cos(time_r * 7.0D) * 0.1F,  0.7F + Math.sin(time_r * 3.0D) * 0.1F, 0.0D, umin, vmin);
        
        
        tessellator.draw();
        tessellator.startDrawingQuads();
    	tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], 0.7F);
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(-0.6F - Math.cos(time_r * 7.0D) * 0.1F, -0.6F + Math.sin(time_r * 5.0D) * 0.1F, 0.001D, umin, vmax);
        tessellator.addVertexWithUV( 0.6F + Math.sin(time_r * 3.0D) * 0.1F, -0.6F + Math.cos(time_r * 4.0D) * 0.1F, 0.001D, umax, vmax);
        tessellator.addVertexWithUV( 0.6F - Math.cos(time_r * 4.0D) * 0.1F,  0.6F - Math.sin(time_r * 3.0D) * 0.1F, 0.001D, umax, vmin);
        tessellator.addVertexWithUV(-0.6F + Math.sin(time_r * 5.0D) * 0.1F,  0.6F - Math.cos(time_r * 7.0D) * 0.1F, 0.001D, umin, vmin);
        tessellator.draw();
    	tessellator.startDrawingQuads();
    	tessellator.setColorRGBA_F(colorR[color], colorG[color], colorB[color], 1.0F);
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(-0.4F, -0.4F, 0.002D, umin, vmax);
        tessellator.addVertexWithUV( 0.4F, -0.4F, 0.002D, umax, vmax);
        tessellator.addVertexWithUV( 0.4F,  0.4F, 0.002D, umax, vmin);
        tessellator.addVertexWithUV(-0.4F,  0.4F, 0.002D, umin, vmin);
        tessellator.draw();

		GL11.glDisable(GL11.GL_BLEND);
        GL11.glDepthMask(true);
        GL11.glPopMatrix();
    }

    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
    {
        this.renderTileEntityDivineSpirit((TileEntityDivineSpirit)par1TileEntity, par2, par4, par6, par8);
    }
}