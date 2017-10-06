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
/*  12:    */ import thKaguyaMod.client.model.ModelMarisaBroom;
/*  13:    */ import thKaguyaMod.entity.item.EntityMarisaBroom;
/*  14:    */ 
/*  15:    */ @SideOnly(Side.CLIENT)
/*  16:    */ public class RenderMarisaBroom
/*  17:    */   extends Render
/*  18:    */ {
/*  19: 21 */   private static final ResourceLocation marisaBroomTexture = new ResourceLocation("thkaguyamod", "textures/MagicBloom.png");
/*  20:    */   protected ModelBase modelMarisaBroom;
/*  21:    */   
/*  22:    */   public RenderMarisaBroom()
/*  23:    */   {
/*  24: 26 */     this.shadowSize = 0.5F;
/*  25: 27 */     this.modelMarisaBroom = new ModelMarisaBroom();
/*  26:    */   }
/*  27:    */   
/*  28:    */   public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
/*  29:    */   {
/*  30: 33 */     doRenderMarisaBroom((EntityMarisaBroom)entity, x, y, z, yaw, pitch);
/*  31:    */   }
/*  32:    */   
/*  33:    */   public void doRenderMarisaBroom(EntityMarisaBroom marisaBroom, double x, double y, double z, float yaw, float pitch)
/*  34:    */   {
/*  35: 38 */     GL11.glPushMatrix();
/*  36: 39 */     bindTexture(getEntityTexture(marisaBroom));
/*  37: 40 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/*  38: 41 */     GL11.glRotatef(180.0F - yaw, 0.0F, 1.0F, 0.0F);
/*  39: 42 */     float f2 = marisaBroom.getTimeSinceHit() - pitch;
/*  40: 43 */     float f3 = marisaBroom.getDamageTaken() - pitch;
/*  41: 45 */     if (f3 < 0.0F) {
/*  42: 47 */       f3 = 0.0F;
/*  43:    */     }
/*  44: 50 */     if (f2 > 0.0F) {
/*  45: 52 */       GL11.glRotatef(MathHelper.sin(f2) * f2 * f3 / 10.0F * marisaBroom.getForwardDirection(), 0.0F, 0.0F, 1.0F);
/*  46:    */     }
/*  47: 55 */     float size = 0.75F;
/*  48: 56 */     GL11.glScalef(size, size, size);
/*  49: 57 */     GL11.glScalef(1.0F / size, 1.0F / size, 1.0F / size);
/*  50: 58 */     GL11.glScalef(-1.0F, -1.0F, 1.0F);
/*  51: 59 */     Tessellator tessellator = Tessellator.instance;
/*  52: 60 */     GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
/*  53: 61 */     GL11.glRotatef(-marisaBroom.rotationPitch, 1.0F, 0.0F, 0.0F);
/*  54: 62 */     float rodWidth = 0.05F;
/*  55: 63 */     float rodWidth2 = 0.3F;
/*  56: 64 */     float rodWidth3 = 0.4F;
/*  57: 65 */     float rodWidth4 = 0.15F;
/*  58: 66 */     double rodLength = 2.8D;
/*  59: 67 */     double rodLengthHalf = rodLength / 2.0D;
/*  60: 69 */     for (int i = 0; i < 4; i++)
/*  61:    */     {
/*  62: 71 */       tessellator.startDrawingQuads();
/*  63:    */       
/*  64: 73 */       tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  65: 74 */       tessellator.addVertexWithUV(rodWidth, -rodWidth, rodLengthHalf, 0.0D, 0.0625D);
/*  66: 75 */       tessellator.addVertexWithUV(rodWidth, -rodWidth, -rodLengthHalf, 1.0D, 0.0625D);
/*  67: 76 */       tessellator.addVertexWithUV(rodWidth, rodWidth, -rodLengthHalf, 1.0D, 0.0D);
/*  68: 77 */       tessellator.addVertexWithUV(rodWidth, rodWidth, rodLengthHalf, 0.0D, 0.0D);
/*  69: 78 */       tessellator.draw();
/*  70: 79 */       GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/*  71:    */     }
/*  72: 81 */     tessellator.startDrawingQuads();
/*  73: 82 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  74: 83 */     tessellator.addVertexWithUV(-rodWidth, -rodWidth, rodLengthHalf, 0.0D, 0.0625D);
/*  75: 84 */     tessellator.addVertexWithUV(rodWidth, -rodWidth, rodLengthHalf, 0.03125D, 0.0625D);
/*  76: 85 */     tessellator.addVertexWithUV(rodWidth, rodWidth, rodLengthHalf, 0.03125D, 0.125D);
/*  77: 86 */     tessellator.addVertexWithUV(-rodWidth, rodWidth, rodLengthHalf, 0.0D, 0.125D);
/*  78: 87 */     tessellator.draw();
/*  79:    */     
/*  80: 89 */     tessellator.startDrawingQuads();
/*  81: 90 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  82: 91 */     tessellator.addVertexWithUV(-rodWidth4, -rodWidth4, -rodLengthHalf + 0.3D, 0.25D, 0.5D);
/*  83: 92 */     tessellator.addVertexWithUV(rodWidth4, -rodWidth4, -rodLengthHalf + 0.3D, 0.5D, 0.5D);
/*  84: 93 */     tessellator.addVertexWithUV(rodWidth4, rodWidth4, -rodLengthHalf + 0.3D, 0.5D, 1.0D);
/*  85: 94 */     tessellator.addVertexWithUV(-rodWidth4, rodWidth4, -rodLengthHalf + 0.3D, 0.25D, 1.0D);
/*  86: 95 */     tessellator.draw();
/*  87:    */     
/*  88: 97 */     tessellator.startDrawingQuads();
/*  89: 98 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  90: 99 */     tessellator.addVertexWithUV(-rodWidth3, -rodWidth3, -rodLengthHalf - 1.3D, 0.25D, 0.5D);
/*  91:100 */     tessellator.addVertexWithUV(-rodWidth3, rodWidth3, -rodLengthHalf - 1.3D, 0.25D, 1.0D);
/*  92:101 */     tessellator.addVertexWithUV(rodWidth3, rodWidth3, -rodLengthHalf - 1.3D, 0.5D, 1.0D);
/*  93:102 */     tessellator.addVertexWithUV(rodWidth3, -rodWidth3, -rodLengthHalf - 1.3D, 0.5D, 0.5D);
/*  94:    */     
/*  95:    */ 
/*  96:105 */     tessellator.draw();
/*  97:107 */     for (int i = 0; i < 4; i++)
/*  98:    */     {
/*  99:109 */       tessellator.startDrawingQuads();
/* 100:110 */       tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 101:111 */       tessellator.addVertexWithUV(rodWidth4, -rodWidth4, -rodLengthHalf + 0.3D, 0.5D, 0.5D);
/* 102:112 */       tessellator.addVertexWithUV(rodWidth, -rodWidth, -rodLengthHalf, 0.5D, 0.09375D);
/* 103:113 */       tessellator.addVertexWithUV(rodWidth, rodWidth, -rodLengthHalf, 1.0D, 0.09375D);
/* 104:114 */       tessellator.addVertexWithUV(rodWidth4, rodWidth4, -rodLengthHalf + 0.3D, 1.0D, 0.5D);
/* 105:115 */       tessellator.draw();
/* 106:    */       
/* 107:117 */       tessellator.startDrawingQuads();
/* 108:118 */       tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 109:119 */       tessellator.addVertexWithUV(rodWidth, -rodWidth, -rodLengthHalf, 0.5D, 0.5D);
/* 110:120 */       tessellator.addVertexWithUV(rodWidth2, -rodWidth2, -rodLengthHalf - 0.6D, 0.5D, 0.09375D);
/* 111:121 */       tessellator.addVertexWithUV(rodWidth2, rodWidth2, -rodLengthHalf - 0.6D, 1.0D, 0.09375D);
/* 112:122 */       tessellator.addVertexWithUV(rodWidth, rodWidth, -rodLengthHalf, 1.0D, 0.5D);
/* 113:123 */       tessellator.draw();
/* 114:    */       
/* 115:125 */       tessellator.startDrawingQuads();
/* 116:126 */       tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 117:127 */       tessellator.addVertexWithUV(rodWidth2, -rodWidth2, -rodLengthHalf - 0.6D, 0.5D, 1.0D);
/* 118:128 */       tessellator.addVertexWithUV(rodWidth3, -rodWidth3, -rodLengthHalf - 1.3D, 0.5D, 0.5D);
/* 119:129 */       tessellator.addVertexWithUV(rodWidth3, rodWidth3, -rodLengthHalf - 1.3D, 1.0D, 0.5D);
/* 120:130 */       tessellator.addVertexWithUV(rodWidth2, rodWidth2, -rodLengthHalf - 0.6D, 1.0D, 1.0D);
/* 121:131 */       tessellator.draw();
/* 122:132 */       GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/* 123:    */     }
/* 124:134 */     GL11.glPopMatrix();
/* 125:    */   }
/* 126:    */   
/* 127:    */   protected ResourceLocation getEntityTexture(Entity var1)
/* 128:    */   {
/* 129:139 */     return marisaBroomTexture;
/* 130:    */   }
/* 131:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.RenderMarisaBroom
 * JD-Core Version:    0.7.0.1
 */