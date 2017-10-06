/*  1:   */ package thKaguyaMod.client.render.effect;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import net.minecraft.client.renderer.Tessellator;
/*  6:   */ import net.minecraft.client.renderer.entity.Render;
/*  7:   */ import net.minecraft.entity.Entity;
/*  8:   */ import net.minecraft.util.ResourceLocation;
/*  9:   */ import org.lwjgl.opengl.GL11;
/* 10:   */ import thKaguyaMod.entity.effect.EntityHakureiShield;
/* 11:   */ 
/* 12:   */ @SideOnly(Side.CLIENT)
/* 13:   */ public class RenderHakureiShield
/* 14:   */   extends Render
/* 15:   */ {
/* 16:19 */   private static final ResourceLocation boatTextures = new ResourceLocation("thkaguyamod", "textures/Amulet.png");
/* 17:   */   
/* 18:   */   public void doRenderHakureiShield(EntityHakureiShield entityHakureiShield, double x, double y, double z, float f, float f1)
/* 19:   */   {
/* 20:28 */     GL11.glPushMatrix();
/* 21:   */     
/* 22:30 */     bindEntityTexture(entityHakureiShield);
/* 23:31 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/* 24:32 */     GL11.glDisable(2896);
/* 25:33 */     GL11.glEnable(3042);
/* 26:34 */     GL11.glDisable(2884);
/* 27:35 */     GL11.glBlendFunc(1, 769);
/* 28:36 */     float size = 1.0F;
/* 29:37 */     GL11.glScalef(size, size, size);
/* 30:   */     
/* 31:   */ 
/* 32:40 */     Tessellator tessellator = Tessellator.instance;
/* 33:   */     
/* 34:42 */     float minU = 0.0F;
/* 35:43 */     float maxU = 0.5F;
/* 36:44 */     float minV = 0.0F;
/* 37:45 */     float maxV = 1.0F;
/* 38:46 */     float width = 1.5F;
/* 39:47 */     float height = 1.5F;
/* 40:48 */     GL11.glRotatef(f, 0.0F, 1.0F, 0.0F);
/* 41:49 */     GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
/* 42:50 */     tessellator.startDrawingQuads();
/* 43:   */     
/* 44:52 */     tessellator.setColorRGBA_F(0.9F, 0.6F, 0.1F, 0.8F);
/* 45:53 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 46:54 */     tessellator.addVertexWithUV(-width, -height, 0.0D, minU, maxV);
/* 47:55 */     tessellator.addVertexWithUV(width, -height, 0.0D, maxU, maxV);
/* 48:56 */     tessellator.addVertexWithUV(width, height, 0.0D, maxU, minV);
/* 49:57 */     tessellator.addVertexWithUV(-width, height, 0.0D, minU, minV);
/* 50:   */     
/* 51:59 */     tessellator.draw();
/* 52:60 */     GL11.glEnable(2884);
/* 53:61 */     GL11.glDisable(3042);
/* 54:62 */     GL11.glEnable(2896);
/* 55:63 */     GL11.glPopMatrix();
/* 56:   */   }
/* 57:   */   
/* 58:   */   protected ResourceLocation func_110784_a(EntityHakureiShield entityHakureiShield)
/* 59:   */   {
/* 60:68 */     return boatTextures;
/* 61:   */   }
/* 62:   */   
/* 63:   */   protected ResourceLocation getEntityTexture(Entity entity)
/* 64:   */   {
/* 65:73 */     return func_110784_a((EntityHakureiShield)entity);
/* 66:   */   }
/* 67:   */   
/* 68:   */   public void doRender(Entity entity, double x, double y, double z, float f, float f1)
/* 69:   */   {
/* 70:79 */     doRenderHakureiShield((EntityHakureiShield)entity, x, y, z, f, f1);
/* 71:   */   }
/* 77:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.effect.RenderHakureiShield
 * JD-Core Version:    0.7.0.1
 */