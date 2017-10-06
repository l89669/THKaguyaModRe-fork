/*   1:    */ package thKaguyaMod.client.render;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.relauncher.Side;
/*   4:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   5:    */ import net.minecraft.client.model.ModelBase;
/*   6:    */ import net.minecraft.client.renderer.Tessellator;
/*   7:    */ import net.minecraft.client.renderer.entity.Render;
/*   8:    */ import net.minecraft.entity.Entity;
/*   9:    */ import net.minecraft.util.MathHelper;
/*  10:    */ import net.minecraft.util.ResourceLocation;
/*  11:    */ import org.lwjgl.opengl.GL11;
/*  12:    */ import thKaguyaMod.client.model.ModelMiniHakkero;
/*  13:    */ import thKaguyaMod.client.model.ModelMiniHakkero2;
/*  14:    */ import thKaguyaMod.entity.item.EntityMiniHakkero;
/*  15:    */ 
/*  16:    */ @SideOnly(Side.CLIENT)
/*  17:    */ public class RenderMiniHakkero
/*  18:    */   extends Render
/*  19:    */ {
/*  20: 25 */   private static final ResourceLocation miniHakkeroTexture = new ResourceLocation("thkaguyamod", "textures/MiniHakkeroTexture.png");
/*  21:    */   protected ModelBase modelMiniHakkero;
/*  22:    */   protected ModelBase modelMiniHakkero2;
/*  23:    */   
/*  24:    */   public RenderMiniHakkero()
/*  25:    */   {
/*  26: 30 */     this.shadowSize = 0.5F;
/*  27: 31 */     this.modelMiniHakkero = new ModelMiniHakkero();
/*  28: 32 */     this.modelMiniHakkero2 = new ModelMiniHakkero2();
/*  29:    */   }
/*  30:    */   
/*  31:    */   public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
/*  32:    */   {
/*  33: 38 */     renderMiniHakkero((EntityMiniHakkero)entity, x, y, z, yaw, pitch);
/*  34: 39 */     renderMiniHakkero2((EntityMiniHakkero)entity, x, y, z, yaw, pitch);
/*  35:    */   }
/*  36:    */   
/*  37:    */   public void renderMiniHakkero(EntityMiniHakkero miniHakkero, double x, double y, double z, float yaw, float pitch)
/*  38:    */   {
/*  39: 44 */     GL11.glPushMatrix();
/*  40: 45 */     bindTexture(getEntityTexture(miniHakkero));
/*  41: 46 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/*  42: 47 */     GL11.glRotatef(miniHakkero.rotationPitch, -MathHelper.sin((yaw - 90.0F) / 180.0F * 3.141593F), 0.0F, MathHelper.cos((yaw - 90.0F) / 180.0F * 3.141593F));
/*  43: 48 */     GL11.glRotatef(180.0F - yaw, 0.0F, 1.0F, 0.0F);
/*  44:    */     
/*  45: 50 */     GL11.glScalef(0.5F, 0.5F, 0.5F);
/*  46: 51 */     this.modelMiniHakkero.render(miniHakkero, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
/*  47: 52 */     GL11.glDisable(2896);
/*  48: 53 */     GL11.glEnable(3042);
/*  49: 54 */     GL11.glBlendFunc(770, 1);
/*  50: 55 */     Tessellator tessellator = Tessellator.instance;
/*  51: 56 */     float[] constR = { 255.0F, 255.0F, 255.0F, 0.0F, 0.0F, 76.0F, 128.0F };
/*  52: 57 */     float[] constG = { 0.0F, 165.0F, 255.0F, 128.0F, 0.0F, 0.0F, 0.0F };
/*  53: 58 */     float[] constB = { 0.0F, 0.0F, 0.0F, 0.0F, 255.0F, 130.0F, 128.0F };
/*  54: 59 */     float u_min = 0.0F;
/*  55: 60 */     float v_min = 0.0F;
/*  56: 61 */     float u_max = 0.5F;
/*  57: 62 */     float v_max = 0.25F;
/*  58:    */     
/*  59: 64 */     float angle = miniHakkero.getCircleAngle();
/*  60: 65 */     float pi18 = 0.3490659F;
/*  61: 66 */     int c = miniHakkero.ticksExisted;
/*  62: 67 */     GL11.glRotatef(MathHelper.sin(angle) / 3.141593F * 180.0F, 0.0F, 0.0F, 1.0F);
/*  63: 68 */     for (int i = 0; i < 19; i++)
/*  64:    */     {
/*  65: 71 */       GL11.glRotatef(MathHelper.sin(pi18) / 3.141593F * 180.0F, 0.0F, 0.0F, 1.0F);
/*  66: 72 */       tessellator.startDrawingQuads();
/*  67: 73 */       tessellator.setColorRGBA_F(constR[(c % 7)] / 255.0F * 0.5F, constG[(c % 7)] / 255.0F * 0.5F, constB[(c % 7)] / 255.0F * 0.5F, 0.6F);
/*  68: 74 */       tessellator.addVertexWithUV(0.7699999809265137D, 4.5D, -6.0D, u_min, v_min);
/*  69: 75 */       tessellator.addVertexWithUV(-0.7699999809265137D, 4.5D, -6.0D, u_max, v_min);
/*  70: 76 */       tessellator.addVertexWithUV(-0.7699999809265137D, 4.5D, -4.0D, u_max, v_max);
/*  71: 77 */       tessellator.addVertexWithUV(0.7699999809265137D, 4.5D, -4.0D, u_min, v_max);
/*  72: 78 */       tessellator.draw();
/*  73: 79 */       tessellator.startDrawingQuads();
/*  74: 80 */       tessellator.setColorRGBA_F(constR[(c % 7)] / 255.0F * 0.5F, constG[(c % 7)] / 255.0F * 0.5F, constB[(c % 7)] / 255.0F * 0.5F, 0.6F);
/*  75: 81 */       tessellator.addVertexWithUV(-0.7699999809265137D, 4.5D, -6.0D, u_min, v_min);
/*  76: 82 */       tessellator.addVertexWithUV(0.7699999809265137D, 4.5D, -6.0D, u_max, v_min);
/*  77: 83 */       tessellator.addVertexWithUV(0.7699999809265137D, 4.5D, -4.0D, u_max, v_max);
/*  78: 84 */       tessellator.addVertexWithUV(-0.7699999809265137D, 4.5D, -4.0D, u_min, v_max);
/*  79: 85 */       tessellator.draw();
/*  80:    */       
/*  81: 87 */       tessellator.startDrawingQuads();
/*  82: 88 */       tessellator.setColorRGBA_F(constR[((c + 3) % 7)] / 255.0F * 0.5F, constG[((c + 3) % 7)] / 255.0F * 0.5F, constB[((c + 3) % 7)] / 255.0F * 0.5F, 0.6F);
/*  83: 89 */       tessellator.addVertexWithUV(1.110000014305115D, 6.5D, -12.0D, u_min, v_min);
/*  84: 90 */       tessellator.addVertexWithUV(-1.110000014305115D, 6.5D, -12.0D, u_max, v_min);
/*  85: 91 */       tessellator.addVertexWithUV(-1.110000014305115D, 6.5D, -10.0D, u_max, v_max);
/*  86: 92 */       tessellator.addVertexWithUV(1.110000014305115D, 6.5D, -10.0D, u_min, v_max);
/*  87: 93 */       tessellator.draw();
/*  88: 94 */       tessellator.startDrawingQuads();
/*  89: 95 */       tessellator.setColorRGBA_F(constR[((c + 3) % 7)] / 255.0F * 0.5F, constG[((c + 3) % 7)] / 255.0F * 0.5F, constB[((c + 3) % 7)] / 255.0F * 0.5F, 0.6F);
/*  90: 96 */       tessellator.addVertexWithUV(-1.110000014305115D, 6.5D, -12.0D, u_min, v_min);
/*  91: 97 */       tessellator.addVertexWithUV(1.110000014305115D, 6.5D, -12.0D, u_max, v_min);
/*  92: 98 */       tessellator.addVertexWithUV(1.110000014305115D, 6.5D, -10.0D, u_max, v_max);
/*  93: 99 */       tessellator.addVertexWithUV(-1.110000014305115D, 6.5D, -10.0D, u_min, v_max);
/*  94:100 */       tessellator.draw();
/*  95:101 */       angle += pi18;
/*  96:    */     }
/*  97:105 */     GL11.glDisable(3042);
/*  98:106 */     GL11.glEnable(2896);
/*  99:107 */     GL11.glPopMatrix();
/* 100:    */   }
/* 101:    */   
/* 102:    */   public void renderMiniHakkero2(EntityMiniHakkero miniHakkero, double x, double y, double z, float yaw, float pitch)
/* 103:    */   {
/* 104:113 */     GL11.glPushMatrix();
/* 105:114 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/* 106:115 */     GL11.glRotatef(miniHakkero.rotationPitch, -MathHelper.sin((miniHakkero.rotationYaw - 90.0F) / 180.0F * 3.141593F), 0.0F, MathHelper.cos((yaw - 90.0F) / 180.0F * 3.141593F));
/* 107:116 */     GL11.glRotatef(180.0F - yaw, 0.0F, 1.0F, 0.0F);
/* 108:    */     
/* 109:    */ 
/* 110:119 */     GL11.glScalef(0.501F, 0.501F, 0.501F);
/* 111:    */     
/* 112:121 */     this.modelMiniHakkero2.render(miniHakkero, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
/* 113:122 */     GL11.glDisable(2896);
/* 114:123 */     GL11.glEnable(3042);
/* 115:124 */     GL11.glBlendFunc(770, 1);
/* 116:125 */     Tessellator tessellator = Tessellator.instance;
/* 117:    */     
/* 118:127 */     GL11.glPopMatrix();
/* 119:    */   }
/* 120:    */   
/* 121:    */   protected ResourceLocation getEntityTexture(Entity entity)
/* 122:    */   {
/* 123:132 */     return miniHakkeroTexture;
/* 124:    */   }
/* 125:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.RenderMiniHakkero
 * JD-Core Version:    0.7.0.1
 */