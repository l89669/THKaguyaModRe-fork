/*  1:   */ package thKaguyaMod.client.render;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.Side;
/*  4:   */ import cpw.mods.fml.relauncher.SideOnly;
/*  5:   */ import net.minecraft.client.model.ModelBase;
/*  6:   */ import net.minecraft.client.renderer.Tessellator;
/*  7:   */ import net.minecraft.client.renderer.entity.Render;
/*  8:   */ import net.minecraft.entity.Entity;
/*  9:   */ import net.minecraft.util.ResourceLocation;
/* 10:   */ import org.lwjgl.opengl.GL11;
/* 11:   */ import thKaguyaMod.client.model.ModelYuukaParasol;
/* 12:   */ import thKaguyaMod.entity.item.EntityYuukaParasol;
/* 13:   */ 
/* 14:   */ @SideOnly(Side.CLIENT)
/* 15:   */ public class RenderYuukaParasol
/* 16:   */   extends Render
/* 17:   */ {
/* 18:21 */   private static final ResourceLocation parasolTexture = new ResourceLocation("thkaguyamod", "textures/YuukaParasolTexture.png");
/* 19:   */   protected ModelBase modelYuukaParasol;
/* 20:   */   
/* 21:   */   public RenderYuukaParasol()
/* 22:   */   {
/* 23:26 */     this.modelYuukaParasol = new ModelYuukaParasol();
/* 24:   */   }
/* 25:   */   
/* 26:   */   public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
/* 27:   */   {
/* 28:32 */     renderYuukaParasol((EntityYuukaParasol)entity, x, y, z, yaw, pitch);
/* 29:   */   }
/* 30:   */   
/* 31:   */   public void renderYuukaParasol(EntityYuukaParasol yuukaParasol, double x, double y, double z, float yaw, float pitch)
/* 32:   */   {
/* 33:38 */     GL11.glPushMatrix();
/* 34:39 */     bindEntityTexture(yuukaParasol);
/* 35:40 */     GL11.glTranslatef((float)x, (float)y - 2.4F, (float)z);
/* 36:41 */     GL11.glEnable(2896);
/* 37:42 */     GL11.glDisable(2884);
/* 38:   */     
/* 39:44 */     Tessellator tessellator = Tessellator.instance;
/* 40:   */     
/* 41:46 */     double stickSideLength = 0.07000000000000001D;
/* 42:47 */     double stickHeight = 2.7D;
/* 43:48 */     float stickUMin = 0.0F;
/* 44:49 */     float stickUMax = 0.078125F;
/* 45:50 */     float stickVMin = 0.0F;
/* 46:51 */     float stickVMax = 1.0F;
/* 47:52 */     GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
/* 48:   */     
/* 49:54 */     GL11.glRotatef(180.0F - yaw, 0.0F, 1.0F, 0.0F);
/* 50:55 */     GL11.glRotatef(yuukaParasol.rotationPitch, 1.0F, 0.0F, 0.0F);
/* 51:   */     
/* 52:57 */     float parasolUMin = 0.5F;
/* 53:58 */     float parasolUMax = 0.75F;
/* 54:59 */     float parasolVMin = 0.0F;
/* 55:60 */     float parasolVMax = 0.5F;
/* 56:61 */     float parasolLength = 2.2F;
/* 57:62 */     float parasolWidth = 0.5F;
/* 58:63 */     float angleSpan = 20.0F;
/* 59:64 */     GL11.glScalef(0.7F, 0.7F, 0.7F);
/* 60:65 */     for (int i = 0; i < 18; i++)
/* 61:   */     {
/* 62:67 */       tessellator.startDrawingQuads();
/* 63:68 */       tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 64:69 */       tessellator.addVertexWithUV(0.0D, stickHeight, 0.0D, parasolUMin, parasolVMax);
/* 65:70 */       tessellator.addVertexWithUV(0.0D, stickHeight, 0.0D, parasolUMax, parasolVMax);
/* 66:71 */       tessellator.addVertexWithUV(parasolWidth, parasolLength, -1.0D, parasolUMax, parasolVMin);
/* 67:72 */       tessellator.addVertexWithUV(-parasolWidth, parasolLength, -1.0D, parasolUMin, parasolVMin);
/* 68:73 */       tessellator.draw();
/* 69:74 */       tessellator.startDrawingQuads();
/* 70:75 */       tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 71:76 */       tessellator.addVertexWithUV(-parasolWidth, parasolLength, -1.0D, parasolUMin, parasolVMax);
/* 72:77 */       tessellator.addVertexWithUV(parasolWidth, parasolLength, -1.0D, parasolUMax, parasolVMax);
/* 73:78 */       tessellator.addVertexWithUV(parasolWidth * 2.0F, parasolLength - 0.5F, -1.7D, parasolUMax, parasolVMin);
/* 74:79 */       tessellator.addVertexWithUV(-parasolWidth * 2.0F, parasolLength - 0.5F, -1.7D, parasolUMin, parasolVMin);
/* 75:80 */       tessellator.draw();
/* 76:81 */       GL11.glRotatef(angleSpan, 0.0F, 1.0F, 0.0F);
/* 77:   */     }
/* 78:83 */     GL11.glScalef(0.3F, 0.4F, 0.3F);
/* 79:84 */     this.modelYuukaParasol.render(yuukaParasol, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
/* 80:   */     
/* 81:   */ 
/* 82:87 */     GL11.glEnable(2884);
/* 83:88 */     GL11.glDisable(2896);
/* 84:89 */     GL11.glPopMatrix();
/* 85:   */   }
/* 86:   */   
/* 87:   */   protected ResourceLocation getEntityTexture(Entity entity)
/* 88:   */   {
/* 89:95 */     return parasolTexture;
/* 90:   */   }
/* 91:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.RenderYuukaParasol
 * JD-Core Version:    0.7.0.1
 */