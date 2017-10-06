/*   1:    */ package thKaguyaMod.client.render;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.relauncher.Side;
/*   4:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   5:    */ import net.minecraft.client.renderer.Tessellator;
/*   6:    */ import net.minecraft.client.renderer.entity.Render;
/*   7:    */ import net.minecraft.entity.Entity;
/*   8:    */ import net.minecraft.util.MathHelper;
/*   9:    */ import net.minecraft.util.ResourceLocation;
/*  10:    */ import org.lwjgl.opengl.GL11;
/*  11:    */ import thKaguyaMod.entity.item.EntityPendulum;
/*  12:    */ 
/*  13:    */ @SideOnly(Side.CLIENT)
/*  14:    */ public class RenderPendulum
/*  15:    */   extends Render
/*  16:    */ {
/*  17: 21 */   private static final ResourceLocation pendulumTexture = new ResourceLocation("thkaguyamod", "textures/PendulumTexture.png");
/*  18:    */   
/*  19:    */   public RenderPendulum()
/*  20:    */   {
/*  21: 25 */     this.shadowSize = 0.5F;
/*  22:    */   }
/*  23:    */   
/*  24:    */   public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
/*  25:    */   {
/*  26: 31 */     renderPendulum((EntityPendulum)entity, x, y, z, yaw, pitch);
/*  27:    */   }
/*  28:    */   
/*  29:    */   public void renderPendulum(EntityPendulum pendulum, double x, double y, double z, float yaw, float pitch)
/*  30:    */   {
/*  31: 37 */     GL11.glPushMatrix();
/*  32: 38 */     bindEntityTexture(pendulum);
/*  33: 39 */     float rotationLength = 0.0F;
/*  34: 40 */     rotationLength = pendulum.getSearchCount() / 48.0F;
/*  35: 41 */     float xDrawPos = -MathHelper.sin(pendulum.getSearchAngle() / 180.0F * 3.141593F) * rotationLength;
/*  36: 42 */     float zDrawPos = MathHelper.cos(pendulum.getSearchAngle() / 180.0F * 3.141593F) * rotationLength;
/*  37: 43 */     GL11.glTranslatef((float)x + xDrawPos, (float)y, (float)z + zDrawPos);
/*  38:    */     
/*  39:    */ 
/*  40:    */ 
/*  41: 47 */     GL11.glEnable(2896);
/*  42: 48 */     GL11.glEnable(3042);
/*  43: 49 */     GL11.glBlendFunc(1, 769);
/*  44:    */     
/*  45: 51 */     GL11.glRotatef(180.0F - yaw, 0.0F, 1.0F, 0.0F);
/*  46:    */     
/*  47:    */ 
/*  48:    */ 
/*  49: 55 */     float u_min = 0.25F;
/*  50: 56 */     float v_min = 0.625F;
/*  51: 57 */     float u_max = 0.4375F;
/*  52: 58 */     float v_max = 1.0F;
/*  53:    */     
/*  54: 60 */     float top_p1_u = 0.1875F;
/*  55: 61 */     float top_p1_v = 0.125F;
/*  56: 62 */     float top_p2_u = 0.015625F;
/*  57: 63 */     float top_p2_v = 0.59375F;
/*  58: 64 */     float top_p3_u = 0.34375F;
/*  59: 65 */     float top_p3_v = 0.59375F;
/*  60:    */     
/*  61: 67 */     float down_p1_u = 0.515625F;
/*  62: 68 */     float down_p1_v = 0.0F;
/*  63: 69 */     float down_p2_u = 0.859375F;
/*  64: 70 */     float down_p2_v = 0.0F;
/*  65: 71 */     float down_p3_u = 0.6875F;
/*  66: 72 */     float down_p3_v = 0.96875F;
/*  67:    */     
/*  68: 74 */     Tessellator tessellator = Tessellator.instance;
/*  69: 75 */     float rate = 0.3F;
/*  70: 76 */     GL11.glScalef(rate, rate, rate);
/*  71: 78 */     for (int i = 0; i < 4; i++)
/*  72:    */     {
/*  73: 80 */       GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/*  74: 81 */       tessellator.startDrawingQuads();
/*  75: 82 */       tessellator.addVertexWithUV(0.0D, 1.75D, 0.0D, top_p1_u, top_p1_v);
/*  76: 83 */       tessellator.addVertexWithUV(0.0D, 1.75D, 0.0D, top_p1_u, top_p1_v);
/*  77: 84 */       tessellator.addVertexWithUV(0.300000011920929D, 1.25D, -0.3D, top_p3_u, top_p3_v);
/*  78: 85 */       tessellator.addVertexWithUV(-0.300000011920929D, 1.25D, -0.3D, top_p2_u, top_p2_v);
/*  79: 86 */       tessellator.draw();
/*  80: 87 */       tessellator.startDrawingQuads();
/*  81: 88 */       tessellator.addVertexWithUV(-0.300000011920929D, 1.25D, -0.3D, down_p1_u, down_p1_v);
/*  82: 89 */       tessellator.addVertexWithUV(0.300000011920929D, 1.25D, -0.3D, down_p2_u, down_p2_v);
/*  83: 90 */       tessellator.addVertexWithUV(0.0D, 0.25D, 0.0D, down_p3_u, down_p3_v);
/*  84: 91 */       tessellator.addVertexWithUV(0.0D, 0.25D, 0.0D, down_p3_u, down_p3_v);
/*  85: 92 */       tessellator.draw();
/*  86:    */     }
/*  87: 94 */     GL11.glDisable(2884);
/*  88: 95 */     for (int i = 0; i < 4; i++)
/*  89:    */     {
/*  90: 97 */       GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/*  91: 98 */       tessellator.startDrawingQuads();
/*  92: 99 */       tessellator.addVertexWithUV(-0.2000000029802322D, 1.899999976158142D, 0.0D, u_min, v_max);
/*  93:100 */       tessellator.addVertexWithUV(0.2000000029802322D, 1.899999976158142D, 0.0D, u_max, v_max);
/*  94:101 */       tessellator.addVertexWithUV(0.2000000029802322D, 1.600000023841858D, 0.0D, u_max, v_min);
/*  95:102 */       tessellator.addVertexWithUV(-0.2000000029802322D, 1.600000023841858D, 0.0D, u_min, v_min);
/*  96:103 */       tessellator.draw();
/*  97:    */     }
/*  98:105 */     GL11.glEnable(2884);
/*  99:106 */     GL11.glDisable(3042);
/* 100:    */     
/* 101:    */ 
/* 102:109 */     GL11.glPopMatrix();
/* 103:    */   }
/* 104:    */   
/* 105:    */   protected ResourceLocation getEntityTexture(Entity entity)
/* 106:    */   {
/* 107:115 */     return pendulumTexture;
/* 108:    */   }
/* 109:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.RenderPendulum
 * JD-Core Version:    0.7.0.1
 */