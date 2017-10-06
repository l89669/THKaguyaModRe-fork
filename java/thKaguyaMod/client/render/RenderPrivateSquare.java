/*   1:    */ package thKaguyaMod.client.render;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.relauncher.Side;
/*   4:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   5:    */ import net.minecraft.client.model.ModelBase;
/*   6:    */ import net.minecraft.client.renderer.Tessellator;
/*   7:    */ import net.minecraft.client.renderer.entity.Render;
/*   8:    */ import net.minecraft.entity.Entity;
/*   9:    */ import net.minecraft.util.ResourceLocation;
/*  10:    */ import org.lwjgl.opengl.GL11;
/*  11:    */ import thKaguyaMod.client.model.ModelPrivateSquare;
/*  12:    */ import thKaguyaMod.entity.item.EntitySakuyaWatch;
/*  13:    */ import thKaguyaMod.init.THKaguyaConfig;
/*  14:    */ 
/*  15:    */ @SideOnly(Side.CLIENT)
/*  16:    */ public class RenderPrivateSquare
/*  17:    */   extends Render
/*  18:    */ {
/*  19: 22 */   private static final ResourceLocation sakuyaWatchTexture = new ResourceLocation("thkaguyamod", "textures/SakuyaWatchTexture.png");
/*  20: 23 */   ResourceLocation darkTexture = new ResourceLocation("thkaguyamod", "textures/DarkTexture.png");
/*  21:    */   protected ModelBase modelPrivateSquare;
/*  22:    */   
/*  23:    */   public RenderPrivateSquare()
/*  24:    */   {
/*  25: 29 */     this.shadowSize = 0.5F;
/*  26: 30 */     this.modelPrivateSquare = new ModelPrivateSquare();
/*  27:    */   }
/*  28:    */   
/*  29:    */   public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
/*  30:    */   {
/*  31: 36 */     renderPrivateSquare((EntitySakuyaWatch)entity, x, y, z, yaw, pitch);
/*  32:    */   }
/*  33:    */   
/*  34:    */   public void renderPrivateSquare(EntitySakuyaWatch privateSquare, double x, double y, double z, float yaw, float pitch)
/*  35:    */   {
/*  36: 42 */     GL11.glPushMatrix();
/*  37:    */     
/*  38: 44 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/*  39:    */     
/*  40: 46 */     GL11.glScalef(0.3F, 0.3F, 0.3F);
/*  41:    */     
/*  42: 48 */     Tessellator tessellator = Tessellator.instance;
/*  43:    */     
/*  44: 50 */     GL11.glEnable(32826);
/*  45: 51 */     GL11.glDisable(2884);
/*  46:    */     
/*  47: 53 */     GL11.glDepthMask(false);
/*  48: 54 */     GL11.glDepthFunc(515);
/*  49:    */     
/*  50: 56 */     GL11.glEnable(3042);
/*  51:    */     
/*  52:    */ 
/*  53: 59 */     GL11.glBlendFunc(775, 0);
/*  54:    */     
/*  55:    */ 
/*  56:    */ 
/*  57:    */ 
/*  58: 64 */     bindTexture(this.darkTexture);
/*  59: 65 */     double size = privateSquare.ticksExisted * 12;
/*  60: 66 */     float alpha = 1.0F;
/*  61: 67 */     if (size > 240.0D) {
/*  62: 69 */       size = 240.0D;
/*  63:    */     }
/*  64: 73 */     if (privateSquare.ticksExisted > 20) {
/*  65: 75 */       alpha -= (privateSquare.ticksExisted - 20) * 0.05F;
/*  66:    */     }
/*  67: 77 */     if (THKaguyaConfig.useTimeStopEffect) {
/*  68: 79 */       renderDark(tessellator, size, (float)size, 0.0D, 1.0F, 0);
/*  69:    */     }
/*  70: 83 */     GL11.glDisable(3042);
/*  71:    */     
/*  72:    */ 
/*  73: 86 */     GL11.glDepthMask(true);
/*  74: 87 */     GL11.glEnable(2884);
/*  75: 88 */     GL11.glDisable(32826);
/*  76:    */     
/*  77: 90 */     bindEntityTexture(privateSquare);
/*  78: 91 */     GL11.glRotatef(180.0F - yaw + privateSquare.ticksExisted * 7.0F, 0.0F, 1.0F, 0.0F);
/*  79: 92 */     this.modelPrivateSquare.render(privateSquare, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
/*  80: 93 */     GL11.glPopMatrix();
/*  81:    */   }
/*  82:    */   
/*  83:    */   protected void renderDark(Tessellator tessellator, double length, float width, double zPos, float alpha, int time)
/*  84:    */   {
/*  85:100 */     float maxWidth = width / 2.0F;
/*  86:    */     
/*  87:102 */     int zAngleDivNum = 18;
/*  88:103 */     float zSpan = 360.0F / zAngleDivNum;
/*  89:104 */     double angleZ = 0.0D;
/*  90:105 */     double angleSpanZ = 6.283185307179586D / zAngleDivNum;
/*  91:    */     
/*  92:107 */     int zDivNum = 9;
/*  93:108 */     double zLength = width;
/*  94:109 */     double zDivLength = zLength / (zDivNum - 1);
/*  95:110 */     double zLength2 = zLength / 2.0D;
/*  96:    */     
/*  97:    */ 
/*  98:113 */     zPos = Math.sin(-1.570796326794897D) * maxWidth;
/*  99:114 */     double zPosOld = zPos;
/* 100:    */     
/* 101:116 */     float xPos = 0.0F;
/* 102:117 */     float yPos = 0.0F;
/* 103:118 */     float xPos2 = 0.0F;
/* 104:119 */     float yPos2 = 0.0F;
/* 105:    */     
/* 106:121 */     float xPosOld = xPos;
/* 107:122 */     float yPosOld = yPos;
/* 108:123 */     float xPos2Old = xPos2;
/* 109:124 */     float yPos2Old = yPos2;
/* 110:    */     
/* 111:126 */     float angle = -1.570796F;
/* 112:127 */     float angleSpan = 3.141593F / zDivNum;
/* 113:128 */     angle += angleSpan;
/* 114:    */     
/* 115:    */ 
/* 116:131 */     float widthOld = 0.0F;
/* 117:135 */     for (int j = 0; j < zDivNum; j++)
/* 118:    */     {
/* 119:138 */       zPos = Math.sin(angle) * maxWidth;
/* 120:139 */       width = (float)Math.cos(angle) * maxWidth;
/* 121:    */       
/* 122:    */ 
/* 123:142 */       xPos = width;
/* 124:143 */       yPos = 0.0F;
/* 125:144 */       angleZ = 0.0D;
/* 126:145 */       xPosOld = (float)Math.cos(angleZ) * width;
/* 127:146 */       yPosOld = (float)Math.sin(angleZ) * width;
/* 128:147 */       xPos2Old = (float)Math.cos(angleZ) * widthOld;
/* 129:148 */       yPos2Old = (float)Math.sin(angleZ) * widthOld;
/* 130:    */       
/* 131:150 */       angleZ = angleSpanZ;
/* 132:152 */       for (int i = 1; i <= zAngleDivNum; i++)
/* 133:    */       {
/* 134:154 */         xPos = (float)Math.cos(angleZ) * width;
/* 135:155 */         yPos = (float)Math.sin(angleZ) * width;
/* 136:156 */         xPos2 = (float)Math.cos(angleZ) * widthOld;
/* 137:157 */         yPos2 = (float)Math.sin(angleZ) * widthOld;
/* 138:    */         
/* 139:159 */         double colorVar = 0.0D;
/* 140:160 */         if (time != 0) {
/* 141:162 */           colorVar = (time + j) / 10.0D;
/* 142:    */         }
/* 143:164 */         tessellator.startDrawingQuads();
/* 144:165 */         tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, alpha);
/* 145:166 */         tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 146:167 */         tessellator.addVertexWithUV(xPos, yPos, zPos, 1.0D, 0.0D);
/* 147:168 */         tessellator.addVertexWithUV(xPosOld, yPosOld, zPos, 0.0D, 0.0D);
/* 148:169 */         tessellator.addVertexWithUV(xPos2Old, yPos2Old, zPosOld, 0.0D, 1.0D);
/* 149:170 */         tessellator.addVertexWithUV(xPos2, yPos2, zPosOld, 1.0D, 1.0D);
/* 150:    */         
/* 151:172 */         tessellator.draw();
/* 152:    */         
/* 153:174 */         xPosOld = xPos;
/* 154:175 */         yPosOld = yPos;
/* 155:176 */         xPos2Old = xPos2;
/* 156:177 */         yPos2Old = yPos2;
/* 157:178 */         angleZ += angleSpanZ;
/* 158:    */       }
/* 159:181 */       zPosOld = zPos;
/* 160:182 */       angle += angleSpan;
/* 161:183 */       widthOld = width;
/* 162:    */     }
/* 163:    */   }
/* 164:    */   
/* 165:    */   protected ResourceLocation getEntityTexture(Entity entity)
/* 166:    */   {
/* 167:191 */     return sakuyaWatchTexture;
/* 168:    */   }
/* 169:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.RenderPrivateSquare
 * JD-Core Version:    0.7.0.1
 */