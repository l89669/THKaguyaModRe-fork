/*  1:   */ package thKaguyaMod.client;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import net.minecraft.client.Minecraft;
/*  6:   */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*  7:   */ import net.minecraft.client.multiplayer.WorldClient;
/*  8:   */ import net.minecraft.client.renderer.Tessellator;
/*  9:   */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/* 10:   */ import net.minecraft.tileentity.TileEntity;
/* 11:   */ import net.minecraft.util.ResourceLocation;
/* 12:   */ import org.lwjgl.opengl.GL11;
/* 13:   */ import thKaguyaMod.tileentity.TileEntityDivineSpirit;
/* 14:   */ 
/* 15:   */ @SideOnly(Side.CLIENT)
/* 16:   */ public class TileEntityDivineSpiritRenderer
/* 17:   */   extends TileEntitySpecialRenderer
/* 18:   */ {
/* 19:18 */   private static final ResourceLocation resourceLocation_Shinrei = new ResourceLocation("thkaguyamod", "textures/shot/MusouFuuin.png");
/* 20:19 */   protected float[] colorR = { 0.8784314F, 0.0F, 0.0F, 0.8784314F, 0.8784314F, 0.0F, 1.0F, 1.0F };
/* 21:20 */   protected float[] colorG = { 0.0F, 0.0F, 0.8784314F, 0.8784314F, 0.0F, 0.8784314F, 0.5019608F, 1.0F };
/* 22:21 */   protected float[] colorB = { 0.0F, 0.8784314F, 0.0F, 0.0F, 0.8784314F, 0.8784314F, 0.0F, 1.0F };
/* 23:22 */   float yaw = 0.0F;
/* 24:   */   
/* 25:   */   public void renderTileEntityDivineSpirit(TileEntityDivineSpirit tileEntityDivineSpirit, double x, double y, double z, float par8)
/* 26:   */   {
/* 27:26 */     GL11.glPushMatrix();
/* 28:   */     
/* 29:28 */     Minecraft mc = Minecraft.getMinecraft();
/* 30:29 */     int time = (int)mc.theWorld.getWorldTime() + tileEntityDivineSpirit.xCoord * tileEntityDivineSpirit.yCoord * tileEntityDivineSpirit.zCoord;
/* 31:30 */     double time_r = time / 180.0D * 3.141592653589793D;
/* 32:31 */     double posX = x + 0.5D;
/* 33:32 */     double posY = y + 0.5D + (float)Math.sin(time * 5.0F / 180.0F * 3.141592653589793D) * 0.16F;
/* 34:33 */     double posZ = z + 0.5D;
/* 35:34 */     GL11.glTranslatef((float)posX, (float)posY, (float)posZ);
/* 36:35 */     GL11.glEnable(2977);
/* 37:36 */     GL11.glEnable(3042);
/* 38:37 */     GL11.glBlendFunc(1, 769);
/* 39:   */     
/* 40:39 */     GL11.glDepthMask(false);
/* 41:   */     
/* 42:41 */     bindTexture(resourceLocation_Shinrei);
/* 43:42 */     Tessellator tessellator = Tessellator.instance;
/* 44:43 */     int color = tileEntityDivineSpirit.getBlockMetadata();
/* 45:   */     
/* 46:   */ 
/* 47:   */ 
/* 48:47 */     float viewYaw = (float)Math.atan2(posX, posZ) / 3.141593F * 180.0F;
/* 49:48 */     float viewPitch = (float)Math.atan2(posY, Math.sqrt(posX * posX + posZ * posZ)) / 3.141593F * 180.0F;
/* 50:50 */     if (mc.thePlayer.posX - mc.thePlayer.prevPosX > 0.1D) {
/* 51:51 */       this.yaw += 10.0F;
/* 52:   */     }
/* 53:52 */     GL11.glRotatef(viewYaw - 180.0F, 0.0F, 1.0F, 0.0F);
/* 54:53 */     GL11.glRotatef(viewPitch, 1.0F, 0.0F, 0.0F);
/* 55:54 */     GL11.glScalef(0.8F + (float)Math.sin(time_r * 5.0D) * 0.16F, 0.8F + (float)Math.sin(time_r * 5.0D) * 0.16F, 0.0F);
/* 56:   */     
/* 57:56 */     int pattern = 0;
/* 58:57 */     float umin = (pattern % 32 * 32 + 0) / 64.0F;
/* 59:58 */     float umax = (pattern % 32 * 32 + 32) / 64.0F;
/* 60:59 */     float vmin = 0.0F;
/* 61:60 */     float vmax = 1.0F;
/* 62:   */     
/* 63:62 */     tessellator.startDrawingQuads();
/* 64:63 */     tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], 0.3F);
/* 65:64 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 66:65 */     tessellator.addVertexWithUV(-0.699999988079071D + Math.sin(time_r * 3.0D) * 0.1000000014901161D, -0.699999988079071D - Math.cos(time_r * 7.0D) * 0.1000000014901161D, 0.0D, umin, vmax);
/* 67:66 */     tessellator.addVertexWithUV(0.699999988079071D - Math.cos(time_r * 4.0D) * 0.1000000014901161D, -0.699999988079071D - Math.sin(time_r * 5.0D) * 0.1000000014901161D, 0.0D, umax, vmax);
/* 68:67 */     tessellator.addVertexWithUV(0.699999988079071D + Math.sin(time_r * 5.0D) * 0.1000000014901161D, 0.699999988079071D + Math.cos(time_r * 4.0D) * 0.1000000014901161D, 0.0D, umax, vmin);
/* 69:68 */     tessellator.addVertexWithUV(-0.699999988079071D - Math.cos(time_r * 7.0D) * 0.1000000014901161D, 0.699999988079071D + Math.sin(time_r * 3.0D) * 0.1000000014901161D, 0.0D, umin, vmin);
/* 70:   */     
/* 71:   */ 
/* 72:71 */     tessellator.draw();
/* 73:72 */     tessellator.startDrawingQuads();
/* 74:73 */     tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], 0.7F);
/* 75:74 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 76:75 */     tessellator.addVertexWithUV(-0.6000000238418579D - Math.cos(time_r * 7.0D) * 0.1000000014901161D, -0.6000000238418579D + Math.sin(time_r * 5.0D) * 0.1000000014901161D, 0.001D, umin, vmax);
/* 77:76 */     tessellator.addVertexWithUV(0.6000000238418579D + Math.sin(time_r * 3.0D) * 0.1000000014901161D, -0.6000000238418579D + Math.cos(time_r * 4.0D) * 0.1000000014901161D, 0.001D, umax, vmax);
/* 78:77 */     tessellator.addVertexWithUV(0.6000000238418579D - Math.cos(time_r * 4.0D) * 0.1000000014901161D, 0.6000000238418579D - Math.sin(time_r * 3.0D) * 0.1000000014901161D, 0.001D, umax, vmin);
/* 79:78 */     tessellator.addVertexWithUV(-0.6000000238418579D + Math.sin(time_r * 5.0D) * 0.1000000014901161D, 0.6000000238418579D - Math.cos(time_r * 7.0D) * 0.1000000014901161D, 0.001D, umin, vmin);
/* 80:79 */     tessellator.draw();
/* 81:80 */     tessellator.startDrawingQuads();
/* 82:81 */     tessellator.setColorRGBA_F(this.colorR[color], this.colorG[color], this.colorB[color], 1.0F);
/* 83:82 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 84:83 */     tessellator.addVertexWithUV(-0.4000000059604645D, -0.4000000059604645D, 0.002D, umin, vmax);
/* 85:84 */     tessellator.addVertexWithUV(0.4000000059604645D, -0.4000000059604645D, 0.002D, umax, vmax);
/* 86:85 */     tessellator.addVertexWithUV(0.4000000059604645D, 0.4000000059604645D, 0.002D, umax, vmin);
/* 87:86 */     tessellator.addVertexWithUV(-0.4000000059604645D, 0.4000000059604645D, 0.002D, umin, vmin);
/* 88:87 */     tessellator.draw();
/* 89:   */     
/* 90:89 */     GL11.glDisable(3042);
/* 91:90 */     GL11.glDepthMask(true);
/* 92:91 */     GL11.glPopMatrix();
/* 93:   */   }
/* 94:   */   
/* 95:   */   public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
/* 96:   */   {
/* 97:96 */     renderTileEntityDivineSpirit((TileEntityDivineSpirit)par1TileEntity, par2, par4, par6, par8);
/* 98:   */   }
/* 99:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.TileEntityDivineSpiritRenderer
 * JD-Core Version:    0.7.0.1
 */