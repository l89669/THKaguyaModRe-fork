/*   1:    */ package thKaguyaMod.client.render.living;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.relauncher.Side;
/*   4:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   5:    */ import net.minecraft.client.renderer.Tessellator;
/*   6:    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*   7:    */ import net.minecraft.entity.Entity;
/*   8:    */ import net.minecraft.util.ResourceLocation;
/*   9:    */ import org.lwjgl.opengl.GL11;
/*  10:    */ import thKaguyaMod.client.model.living.ModelTHFairy;
/*  11:    */ import thKaguyaMod.entity.living.EntityTHFairy;
/*  12:    */ 
/*  13:    */ @SideOnly(Side.CLIENT)
/*  14:    */ public class RenderTHFairy
/*  15:    */   extends RenderLiving
/*  16:    */ {
/*  17:    */   public RenderTHFairy()
/*  18:    */   {
/*  19: 22 */     super(new ModelTHFairy(), 0.25F);
/*  20:    */   }
/*  21:    */   
/*  22:    */   public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
/*  23:    */   {
/*  24: 29 */     super.doRender(entity, x, y, z, yaw, pitch);
/*  25: 30 */     renderTHFairy((EntityTHFairy)entity, x, y, z, yaw, pitch);
/*  26:    */   }
/*  27:    */   
/*  28:    */   public void renderTHFairy(EntityTHFairy thFairy, double x, double y, double z, float yaw, float pitch)
/*  29:    */   {
/*  30: 38 */     if (thFairy.getHealth() <= 0.0F) {
/*  31: 40 */       if (thFairy.getDanmakuPattern() < 0)
/*  32:    */       {
/*  33: 42 */         GL11.glDisable(2896);
/*  34: 43 */         GL11.glEnable(3042);
/*  35: 44 */         GL11.glBlendFunc(770, 1);
/*  36: 45 */         GL11.glDisable(2884);
/*  37:    */         
/*  38: 47 */         Tessellator tessellator = Tessellator.instance;
/*  39: 48 */         float f3 = 0.75F;
/*  40: 49 */         float f4 = 1.0F;
/*  41: 50 */         float f5 = 0.5F;
/*  42: 51 */         float f6 = 0.75F;
/*  43: 52 */         float f7 = 1.0F;
/*  44: 53 */         float f8 = 0.5F;
/*  45: 54 */         float f9 = 0.5F;
/*  46:    */         
/*  47: 56 */         tessellator.startDrawingQuads();
/*  48: 57 */         tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  49: 58 */         tessellator.addVertexWithUV(x - 0.5D, y + 2.5D, z + 0.5D, f3, f6);
/*  50: 59 */         tessellator.addVertexWithUV(x + 0.5D, y + 2.5D, z + 0.5D, f4, f6);
/*  51: 60 */         tessellator.addVertexWithUV(x + 0.5D, y + 2.5D, z - 0.5D, f4, f5);
/*  52: 61 */         tessellator.addVertexWithUV(x - 0.5D, y + 2.5D, z - 0.5D, f3, f5);
/*  53: 62 */         tessellator.draw();
/*  54: 63 */         GL11.glEnable(2884);
/*  55: 64 */         GL11.glDisable(3042);
/*  56: 65 */         GL11.glEnable(2896);
/*  57:    */       }
/*  58:    */     }
/*  59:    */   }
/*  60:    */   
/*  61:    */   protected void renderDark(Tessellator tessellator, double length, float width, double zPos, float alpha, int time)
/*  62:    */   {
/*  63:107 */     float maxWidth = width / 2.0F;
/*  64:    */     
/*  65:109 */     int zAngleDivNum = 18;
/*  66:110 */     float zSpan = 360.0F / zAngleDivNum;
/*  67:111 */     double angleZ = 0.0D;
/*  68:112 */     double angleSpanZ = 6.283185307179586D / zAngleDivNum;
/*  69:    */     
/*  70:114 */     int zDivNum = 9;
/*  71:115 */     double zLength = width;
/*  72:116 */     double zDivLength = zLength / (zDivNum - 1);
/*  73:117 */     double zLength2 = zLength / 2.0D;
/*  74:    */     
/*  75:    */ 
/*  76:120 */     zPos = Math.sin(-1.570796326794897D) * maxWidth;
/*  77:121 */     double zPosOld = zPos;
/*  78:    */     
/*  79:123 */     float xPos = 0.0F;
/*  80:124 */     float yPos = 0.0F;
/*  81:125 */     float xPos2 = 0.0F;
/*  82:126 */     float yPos2 = 0.0F;
/*  83:    */     
/*  84:128 */     float xPosOld = xPos;
/*  85:129 */     float yPosOld = yPos;
/*  86:130 */     float xPos2Old = xPos2;
/*  87:131 */     float yPos2Old = yPos2;
/*  88:    */     
/*  89:133 */     float angle = -1.570796F;
/*  90:134 */     float angleSpan = 3.141593F / zDivNum;
/*  91:135 */     angle += angleSpan;
/*  92:    */     
/*  93:    */ 
/*  94:138 */     float widthOld = 0.0F;
/*  95:142 */     for (int j = 0; j < zDivNum; j++)
/*  96:    */     {
/*  97:145 */       zPos = Math.sin(angle) * maxWidth;
/*  98:146 */       width = (float)Math.cos(angle) * maxWidth;
/*  99:    */       
/* 100:    */ 
/* 101:149 */       xPos = width;
/* 102:150 */       yPos = 0.0F;
/* 103:151 */       angleZ = 0.0D;
/* 104:152 */       xPosOld = (float)Math.cos(angleZ) * width;
/* 105:153 */       yPosOld = (float)Math.sin(angleZ) * width;
/* 106:154 */       xPos2Old = (float)Math.cos(angleZ) * widthOld;
/* 107:155 */       yPos2Old = (float)Math.sin(angleZ) * widthOld;
/* 108:    */       
/* 109:157 */       angleZ = angleSpanZ;
/* 110:159 */       for (int i = 1; i <= zAngleDivNum; i++)
/* 111:    */       {
/* 112:161 */         xPos = (float)Math.cos(angleZ) * width;
/* 113:162 */         yPos = (float)Math.sin(angleZ) * width;
/* 114:163 */         xPos2 = (float)Math.cos(angleZ) * widthOld;
/* 115:164 */         yPos2 = (float)Math.sin(angleZ) * widthOld;
/* 116:    */         
/* 117:166 */         double colorVar = 0.0D;
/* 118:167 */         if (time != 0) {
/* 119:169 */           colorVar = (time + j) / 10.0D;
/* 120:    */         }
/* 121:171 */         tessellator.startDrawingQuads();
/* 122:172 */         tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, alpha);
/* 123:173 */         tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 124:174 */         tessellator.addVertexWithUV(xPos, yPos, zPos, 1.0D, 0.0D);
/* 125:175 */         tessellator.addVertexWithUV(xPosOld, yPosOld, zPos, 0.0D, 0.0D);
/* 126:176 */         tessellator.addVertexWithUV(xPos2Old, yPos2Old, zPosOld, 0.0D, 1.0D);
/* 127:177 */         tessellator.addVertexWithUV(xPos2, yPos2, zPosOld, 1.0D, 1.0D);
/* 128:    */         
/* 129:    */ 
/* 130:180 */         tessellator.draw();
/* 131:    */         
/* 132:182 */         xPosOld = xPos;
/* 133:183 */         yPosOld = yPos;
/* 134:184 */         xPos2Old = xPos2;
/* 135:185 */         yPos2Old = yPos2;
/* 136:186 */         angleZ += angleSpanZ;
/* 137:    */       }
/* 138:189 */       zPosOld = zPos;
/* 139:190 */       angle += angleSpan;
/* 140:191 */       widthOld = width;
/* 141:    */     }
/* 142:    */   }
/* 143:    */   
/* 144:    */   protected ResourceLocation getEntityTexture(Entity entity)
/* 145:    */   {
/* 146:199 */     return getEntityTexture((EntityTHFairy)entity);
/* 147:    */   }
/* 148:    */   
/* 149:    */   protected ResourceLocation getEntityTexture(EntityTHFairy thFairy)
/* 150:    */   {
/* 151:    */     ResourceLocation resourceLocation;
/* 153:205 */     if (thFairy.getForm() >= 0) {
/* 154:207 */       resourceLocation = new ResourceLocation("thkaguyamod", "textures/mob/FairyTexture_" + thFairy.getDanmakuPattern() % 3 + ".png");
/* 155:    */     } else {
/* 156:211 */       resourceLocation = new ResourceLocation("thkaguyamod", "textures/mob/ZombieFairyTexture.png");
/* 157:    */     }
/* 158:215 */     return resourceLocation;
/* 159:    */   }
/* 160:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.living.RenderTHFairy
 * JD-Core Version:    0.7.0.1
 */