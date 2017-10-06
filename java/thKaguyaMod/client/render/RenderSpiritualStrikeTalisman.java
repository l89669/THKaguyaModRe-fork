/*   1:    */ package thKaguyaMod.client.render;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.relauncher.Side;
/*   4:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   5:    */ import net.minecraft.client.renderer.Tessellator;
/*   6:    */ import net.minecraft.client.renderer.entity.Render;
/*   7:    */ import net.minecraft.entity.Entity;
/*   8:    */ import net.minecraft.util.ResourceLocation;
/*   9:    */ import org.lwjgl.opengl.GL11;
/*  10:    */ import thKaguyaMod.entity.item.EntitySpiritualStrikeTalisman;
/*  11:    */ import thKaguyaMod.init.THKaguyaConfig;
/*  12:    */ 
/*  13:    */ @SideOnly(Side.CLIENT)
/*  14:    */ public class RenderSpiritualStrikeTalisman
/*  15:    */   extends Render
/*  16:    */ {
/*  17: 20 */   ResourceLocation darkTexture = new ResourceLocation("thkaguyamod", "textures/DarkTexture.png");
/*  18: 21 */   ResourceLocation talismanTexture = new ResourceLocation("thkaguyamod", "textures/SpiritualStrikeTalismanTexture.png");
/*  19:    */   
/*  20:    */   public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
/*  21:    */   {
/*  22: 29 */     renderSpiritualStrikeTalisman((EntitySpiritualStrikeTalisman)entity, x, y, z, yaw, pitch);
/*  23:    */   }
/*  24:    */   
/*  25:    */   public void renderSpiritualStrikeTalisman(EntitySpiritualStrikeTalisman talisman, double x, double y, double z, float yaw, float pitch)
/*  26:    */   {
/*  27: 35 */     GL11.glPushMatrix();
/*  28: 36 */     bindEntityTexture(talisman);
/*  29: 37 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/*  30: 38 */     GL11.glDisable(2896);
/*  31: 39 */     GL11.glDisable(2884);
/*  32: 40 */     float sizeRate = 1.0F;
/*  33: 41 */     GL11.glScalef(sizeRate, sizeRate, sizeRate);
/*  34: 42 */     Tessellator tessellator = Tessellator.instance;
/*  35: 43 */     float rvl = 0.0F;
/*  36: 44 */     float rul = 0.0F;
/*  37: 45 */     float rvr = 0.5F;
/*  38: 46 */     float rur = 1.0F;
/*  39: 47 */     GL11.glRotatef(talisman.ticksExisted * 21.0F, 0.0F, 1.0F, 0.0F);
/*  40: 48 */     GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
/*  41:    */     
/*  42:    */ 
/*  43: 51 */     tessellator.startDrawingQuads();
/*  44: 52 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  45: 53 */     tessellator.addVertexWithUV(0.2000000029802322D, 0.4000000059604645D, 0.0D, rvl, rul);
/*  46: 54 */     tessellator.addVertexWithUV(-0.2000000029802322D, 0.4000000059604645D, 0.0D, rvr, rul);
/*  47: 55 */     tessellator.addVertexWithUV(-0.2000000029802322D, -0.4000000059604645D, 0.0D, rvr, rur);
/*  48: 56 */     tessellator.addVertexWithUV(0.2000000029802322D, -0.4000000059604645D, 0.0D, rvl, rur);
/*  49: 57 */     tessellator.draw();
/*  50:    */     
/*  51: 59 */     tessellator.startDrawingQuads();
/*  52: 60 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*  53: 61 */     tessellator.addVertexWithUV(-0.2000000029802322D, 0.4000000059604645D, 0.0D, rvl, rul);
/*  54: 62 */     tessellator.addVertexWithUV(0.2000000029802322D, 0.4000000059604645D, 0.0D, rvr, rul);
/*  55: 63 */     tessellator.addVertexWithUV(0.2000000029802322D, -0.4000000059604645D, 0.0D, rvr, rur);
/*  56: 64 */     tessellator.addVertexWithUV(-0.2000000029802322D, -0.4000000059604645D, 0.0D, rvl, rur);
/*  57: 65 */     tessellator.draw();
/*  58:    */     
/*  59: 67 */     GL11.glEnable(2884);
/*  60: 68 */     GL11.glEnable(2896);
/*  61: 69 */     GL11.glPopMatrix();
/*  62:    */     
/*  63:    */ 
/*  64:    */ 
/*  65: 73 */     GL11.glPushMatrix();
/*  66: 74 */     GL11.glDepthMask(false);
/*  67: 75 */     GL11.glDepthFunc(515);
/*  68: 76 */     GL11.glEnable(3042);
/*  69: 77 */     GL11.glBlendFunc(770, 1);
/*  70: 78 */     bindTexture(this.darkTexture);
/*  71: 79 */     double size = talisman.ticksExisted * 3.0D;
/*  72: 80 */     float alpha = 0.8F;
/*  73: 81 */     if (size > 240.0D) {
/*  74: 83 */       size = 240.0D;
/*  75:    */     }
/*  76: 85 */     if (talisman.ticksExisted > 5) {
/*  77: 87 */       alpha -= (talisman.ticksExisted - 5) * 0.05F;
/*  78:    */     }
/*  79: 89 */     if (THKaguyaConfig.useTimeStopEffect) {
/*  80: 91 */       renderDark(tessellator, size, (float)size, 0.0D, 0.4F, 0);
/*  81:    */     }
/*  82: 93 */     GL11.glDepthFunc(515);
/*  83: 94 */     GL11.glDisable(3042);
/*  84:    */     
/*  85: 96 */     GL11.glDepthMask(true);
/*  86: 97 */     GL11.glEnable(2884);
/*  87: 98 */     GL11.glPopMatrix();
/*  88:    */   }
/*  89:    */   
/*  90:    */   protected void renderDark(Tessellator tessellator, double length, float width, double zPos, float alpha, int time)
/*  91:    */   {
/*  92:105 */     float maxWidth = width / 2.0F;
/*  93:    */     
/*  94:107 */     int zAngleDivNum = 18;
/*  95:108 */     float zSpan = 360.0F / zAngleDivNum;
/*  96:109 */     double angleZ = 0.0D;
/*  97:110 */     double angleSpanZ = 6.283185307179586D / zAngleDivNum;
/*  98:    */     
/*  99:112 */     int zDivNum = 9;
/* 100:113 */     double zLength = width;
/* 101:114 */     double zDivLength = zLength / (zDivNum - 1);
/* 102:115 */     double zLength2 = zLength / 2.0D;
/* 103:    */     
/* 104:    */ 
/* 105:118 */     zPos = Math.sin(-1.570796326794897D) * maxWidth;
/* 106:119 */     double zPosOld = zPos;
/* 107:    */     
/* 108:121 */     float xPos = 0.0F;
/* 109:122 */     float yPos = 0.0F;
/* 110:123 */     float xPos2 = 0.0F;
/* 111:124 */     float yPos2 = 0.0F;
/* 112:    */     
/* 113:126 */     float xPosOld = xPos;
/* 114:127 */     float yPosOld = yPos;
/* 115:128 */     float xPos2Old = xPos2;
/* 116:129 */     float yPos2Old = yPos2;
/* 117:    */     
/* 118:131 */     float angle = -1.570796F;
/* 119:132 */     float angleSpan = 3.141593F / zDivNum;
/* 120:133 */     angle += angleSpan;
/* 121:    */     
/* 122:    */ 
/* 123:136 */     float widthOld = 0.0F;
/* 124:140 */     for (int j = 0; j < zDivNum; j++)
/* 125:    */     {
/* 126:143 */       zPos = Math.sin(angle) * maxWidth;
/* 127:144 */       width = (float)Math.cos(angle) * maxWidth;
/* 128:    */       
/* 129:    */ 
/* 130:147 */       xPos = width;
/* 131:148 */       yPos = 0.0F;
/* 132:149 */       angleZ = 0.0D;
/* 133:150 */       xPosOld = (float)Math.cos(angleZ) * width;
/* 134:151 */       yPosOld = (float)Math.sin(angleZ) * width;
/* 135:152 */       xPos2Old = (float)Math.cos(angleZ) * widthOld;
/* 136:153 */       yPos2Old = (float)Math.sin(angleZ) * widthOld;
/* 137:    */       
/* 138:155 */       angleZ = angleSpanZ;
/* 139:157 */       for (int i = 1; i <= zAngleDivNum; i++)
/* 140:    */       {
/* 141:159 */         xPos = (float)Math.cos(angleZ) * width;
/* 142:160 */         yPos = (float)Math.sin(angleZ) * width;
/* 143:161 */         xPos2 = (float)Math.cos(angleZ) * widthOld;
/* 144:162 */         yPos2 = (float)Math.sin(angleZ) * widthOld;
/* 145:    */         
/* 146:164 */         double colorVar = 0.0D;
/* 147:165 */         if (time != 0) {
/* 148:167 */           colorVar = (time + j) / 10.0D;
/* 149:    */         }
/* 150:169 */         tessellator.startDrawingQuads();
/* 151:170 */         tessellator.setColorRGBA_F(0.2F, 0.3F, 1.0F, alpha);
/* 152:171 */         tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 153:172 */         tessellator.addVertexWithUV(xPos, yPos, zPos, 1.0D, 0.0D);
/* 154:173 */         tessellator.addVertexWithUV(xPosOld, yPosOld, zPos, 0.0D, 0.0D);
/* 155:174 */         tessellator.addVertexWithUV(xPos2Old, yPos2Old, zPosOld, 0.0D, 1.0D);
/* 156:175 */         tessellator.addVertexWithUV(xPos2, yPos2, zPosOld, 1.0D, 1.0D);
/* 157:    */         
/* 158:177 */         tessellator.draw();
/* 159:    */         
/* 160:179 */         xPosOld = xPos;
/* 161:180 */         yPosOld = yPos;
/* 162:181 */         xPos2Old = xPos2;
/* 163:182 */         yPos2Old = yPos2;
/* 164:183 */         angleZ += angleSpanZ;
/* 165:    */       }
/* 166:186 */       zPosOld = zPos;
/* 167:187 */       angle += angleSpan;
/* 168:188 */       widthOld = width;
/* 169:    */     }
/* 170:    */   }
/* 171:    */   
/* 172:    */   protected ResourceLocation getEntityTexture(Entity entity)
/* 173:    */   {
/* 174:196 */     return this.talismanTexture;
/* 175:    */   }
/* 176:    */   
/* 177:    */   protected ResourceLocation getEntityTexture(EntitySpiritualStrikeTalisman talisman)
/* 178:    */   {
/* 179:201 */     return this.talismanTexture;
/* 180:    */   }
/* 181:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.RenderSpiritualStrikeTalisman
 * JD-Core Version:    0.7.0.1
 */