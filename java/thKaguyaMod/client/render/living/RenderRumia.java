/*   1:    */ package thKaguyaMod.client.render.living;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.relauncher.Side;
/*   4:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   5:    */ import net.minecraft.client.renderer.Tessellator;
/*   6:    */ import net.minecraft.client.renderer.entity.RenderManager;
/*   7:    */ import net.minecraft.entity.Entity;
/*   8:    */ import net.minecraft.util.ResourceLocation;
/*   9:    */ import org.lwjgl.opengl.GL11;
/*  10:    */ import thKaguyaMod.client.model.living.ModelRumia;
/*  11:    */ import thKaguyaMod.entity.living.EntityRumia;
/*  12:    */ 
/*  13:    */ @SideOnly(Side.CLIENT)
/*  14:    */ public class RenderRumia
/*  15:    */   extends RenderTHBoss
/*  16:    */ {
/*  17: 20 */   ResourceLocation rumiaTexture = new ResourceLocation("thkaguyamod", "textures/mob/RumiaTexture.png");
/*  18: 21 */   ResourceLocation darkTexture = new ResourceLocation("thkaguyamod", "textures/DarkTexture.png");
/*  19:    */   
/*  20:    */   public RenderRumia()
/*  21:    */   {
/*  22: 25 */     super(new ModelRumia(), 0.25F);
/*  23:    */   }
/*  24:    */   
/*  25:    */   public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
/*  26:    */   {
/*  27: 31 */     super.doRender(entity, x, y, z, yaw, pitch);
/*  28: 32 */     renderRumia((EntityRumia)entity, x, y, z, yaw, pitch);
/*  29:    */   }
/*  30:    */   
/*  31:    */   public void renderRumia(EntityRumia rumia, double x, double y, double z, float yaw, float pitch)
/*  32:    */   {
/*  33: 39 */     GL11.glDisable(2896);
/*  34:    */     
/*  35: 41 */     GL11.glPushMatrix();
/*  36: 42 */     GL11.glDisable(2884);
/*  37: 43 */     if (RenderManager.instance.getDistanceToCamera(rumia.posX, rumia.posY, rumia.posZ) < 36.0D) {
/*  38: 45 */       GL11.glDepthFunc(519);
/*  39:    */     } else {
/*  40: 49 */       GL11.glDepthMask(false);
/*  41:    */     }
/*  42: 51 */     GL11.glTranslatef((float)x, (float)y, (float)z);
/*  43: 52 */     GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
/*  44: 53 */     GL11.glRotatef(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
/*  45:    */     
/*  46: 55 */     Tessellator tessellator = Tessellator.instance;
/*  47: 56 */     GL11.glDepthMask(false);
/*  48: 57 */     GL11.glEnable(3042);
/*  49:    */     
/*  50: 59 */     GL11.glBlendFunc(0, 769);
/*  51: 60 */     GL11.glTranslatef(0.0F, 1.3F, 0.0F);
/*  52: 61 */     bindTexture(this.darkTexture);
/*  53: 62 */     renderDark(tessellator, 12.0D, 12.0F, 0.0D, 1.0F, 0);
/*  54: 63 */     GL11.glDisable(3042);
/*  55:    */     
/*  56: 65 */     GL11.glDepthFunc(515);
/*  57: 66 */     GL11.glEnable(2884);
/*  58: 67 */     GL11.glDepthMask(true);
/*  59: 68 */     GL11.glPopMatrix();
/*  60:    */     
/*  61: 70 */     GL11.glEnable(2896);
/*  62:    */   }
/*  63:    */   
/*  64:    */   protected void renderDark(Tessellator tessellator, double length, float width, double zPos, float alpha, int time)
/*  65:    */   {
/*  66: 77 */     float maxWidth = width / 2.0F;
/*  67:    */     
/*  68: 79 */     int zAngleDivNum = 18;
/*  69: 80 */     float zSpan = 360.0F / zAngleDivNum;
/*  70: 81 */     double angleZ = 0.0D;
/*  71: 82 */     double angleSpanZ = 6.283185307179586D / zAngleDivNum;
/*  72:    */     
/*  73: 84 */     int zDivNum = 9;
/*  74: 85 */     double zLength = width;
/*  75: 86 */     double zDivLength = zLength / (zDivNum - 1);
/*  76: 87 */     double zLength2 = zLength / 2.0D;
/*  77:    */     
/*  78:    */ 
/*  79: 90 */     zPos = Math.sin(-1.570796326794897D) * maxWidth;
/*  80: 91 */     double zPosOld = zPos;
/*  81:    */     
/*  82: 93 */     float xPos = 0.0F;
/*  83: 94 */     float yPos = 0.0F;
/*  84: 95 */     float xPos2 = 0.0F;
/*  85: 96 */     float yPos2 = 0.0F;
/*  86:    */     
/*  87: 98 */     float xPosOld = xPos;
/*  88: 99 */     float yPosOld = yPos;
/*  89:100 */     float xPos2Old = xPos2;
/*  90:101 */     float yPos2Old = yPos2;
/*  91:    */     
/*  92:103 */     float angle = -1.570796F;
/*  93:104 */     float angleSpan = 3.141593F / zDivNum;
/*  94:105 */     angle += angleSpan;
/*  95:    */     
/*  96:    */ 
/*  97:108 */     float widthOld = 0.0F;
/*  98:112 */     for (int j = 0; j < zDivNum; j++)
/*  99:    */     {
/* 100:115 */       zPos = Math.sin(angle) * maxWidth;
/* 101:116 */       width = (float)Math.cos(angle) * maxWidth;
/* 102:    */       
/* 103:    */ 
/* 104:119 */       xPos = width;
/* 105:120 */       yPos = 0.0F;
/* 106:121 */       angleZ = 0.0D;
/* 107:122 */       xPosOld = (float)Math.cos(angleZ) * width;
/* 108:123 */       yPosOld = (float)Math.sin(angleZ) * width;
/* 109:124 */       xPos2Old = (float)Math.cos(angleZ) * widthOld;
/* 110:125 */       yPos2Old = (float)Math.sin(angleZ) * widthOld;
/* 111:    */       
/* 112:127 */       angleZ = angleSpanZ;
/* 113:129 */       for (int i = 1; i <= zAngleDivNum; i++)
/* 114:    */       {
/* 115:131 */         xPos = (float)Math.cos(angleZ) * width;
/* 116:132 */         yPos = (float)Math.sin(angleZ) * width;
/* 117:133 */         xPos2 = (float)Math.cos(angleZ) * widthOld;
/* 118:134 */         yPos2 = (float)Math.sin(angleZ) * widthOld;
/* 119:    */         
/* 120:136 */         double colorVar = 0.0D;
/* 121:137 */         if (time != 0) {
/* 122:139 */           colorVar = (time + j) / 10.0D;
/* 123:    */         }
/* 124:141 */         tessellator.startDrawingQuads();
/* 125:142 */         tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, alpha);
/* 126:143 */         tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 127:144 */         tessellator.addVertexWithUV(xPos, yPos, zPos, 1.0D, 0.0D);
/* 128:145 */         tessellator.addVertexWithUV(xPosOld, yPosOld, zPos, 0.0D, 0.0D);
/* 129:146 */         tessellator.addVertexWithUV(xPos2Old, yPos2Old, zPosOld, 0.0D, 1.0D);
/* 130:147 */         tessellator.addVertexWithUV(xPos2, yPos2, zPosOld, 1.0D, 1.0D);
/* 131:    */         
/* 132:149 */         tessellator.draw();
/* 133:    */         
/* 134:151 */         xPosOld = xPos;
/* 135:152 */         yPosOld = yPos;
/* 136:153 */         xPos2Old = xPos2;
/* 137:154 */         yPos2Old = yPos2;
/* 138:155 */         angleZ += angleSpanZ;
/* 139:    */       }
/* 140:158 */       zPosOld = zPos;
/* 141:159 */       angle += angleSpan;
/* 142:160 */       widthOld = width;
/* 143:    */     }
/* 144:    */   }
/* 145:    */   
/* 146:    */   protected ResourceLocation getEntityTexture(Entity entity)
/* 147:    */   {
/* 148:168 */     return getEntityTexture((EntityRumia)entity);
/* 149:    */   }
/* 150:    */   
/* 151:    */   protected ResourceLocation getEntityTexture(EntityRumia thFairy)
/* 152:    */   {
/* 153:173 */     return this.rumiaTexture;
/* 154:    */   }
/* 155:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.living.RenderRumia
 * JD-Core Version:    0.7.0.1
 */