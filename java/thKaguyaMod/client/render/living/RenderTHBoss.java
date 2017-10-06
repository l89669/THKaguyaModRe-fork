/*   1:    */ package thKaguyaMod.client.render.living;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.relauncher.Side;
/*   4:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   5:    */ import net.minecraft.client.gui.FontRenderer;
/*   6:    */ import net.minecraft.client.model.ModelBase;
/*   7:    */ import net.minecraft.client.renderer.Tessellator;
/*   8:    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*   9:    */ import net.minecraft.client.renderer.entity.RenderManager;
/*  10:    */ import net.minecraft.entity.Entity;
/*  11:    */ import net.minecraft.util.ResourceLocation;
/*  12:    */ import net.minecraft.util.StatCollector;
/*  13:    */ import net.minecraft.util.Vec3;
/*  14:    */ import org.lwjgl.opengl.GL11;
/*  15:    */ import thKaguyaMod.THShotLib;
/*  16:    */ import thKaguyaMod.entity.living.EntityDanmakuMob;
/*  17:    */ 
/*  18:    */ @SideOnly(Side.CLIENT)
/*  19:    */ public abstract class RenderTHBoss
/*  20:    */   extends RenderLiving
/*  21:    */ {
/*  22: 24 */   ResourceLocation statusTexture = new ResourceLocation("thkaguyamod", "textures/mob/Status.png");
/*  23:    */   
/*  24:    */   public RenderTHBoss(ModelBase model, float size)
/*  25:    */   {
/*  26: 28 */     super(model, size);
/*  27:    */   }
/*  28:    */   
/*  29:    */   public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
/*  30:    */   {
/*  31: 36 */     super.doRender(entity, x, y, z, yaw, pitch);
/*  32: 37 */     renderTHBossStatus((EntityDanmakuMob)entity, x, y, z, yaw, pitch);
/*  33:    */   }
/*  34:    */   
/*  35:    */   public void renderTHBossStatus(EntityDanmakuMob danmakuMob, double x, double y, double z, float yaw, float pitch)
/*  36:    */   {
/*  37: 43 */     if (danmakuMob.getDanmakuPattern() == 0) {
/*  38: 45 */       return;
/*  39:    */     }
/*  40: 49 */     GL11.glPushMatrix();
/*  41: 50 */     GL11.glDisable(2896);
/*  42:    */     
/*  43:    */ 
/*  44: 53 */     Tessellator tessellator = Tessellator.instance;
/*  45:    */     
/*  46: 55 */     float viewY = this.renderManager.playerViewY % 360.0F;
/*  47: 56 */     if (viewY > 180.0F) {
/*  48: 58 */       viewY -= 360.0F;
/*  49: 60 */     } else if (viewY <= -180.0F) {
/*  50: 62 */       viewY += 360.0F;
/*  51:    */     }
/*  52: 64 */     Vec3 look = THShotLib.getVecFromAngle(viewY, this.renderManager.playerViewX);
/*  53: 65 */     Vec3 toEntity = Vec3.createVectorHelper(x, y, z);
/*  54: 66 */     float span = THShotLib.getVectorAndVectorAngle(look, toEntity);
/*  55: 67 */     float alpha = 1.0F - (Math.abs(span) - 20.0F) / 30.0F;
/*  56:    */     
/*  57: 69 */     double distance = this.renderManager.getDistanceToCamera(danmakuMob.posX, danmakuMob.posY, danmakuMob.posZ);
/*  58: 70 */     float size = 1.0F + (float)distance / 64.0F;
/*  59: 71 */     if (size > 5.0F) {
/*  60: 73 */       size = 5.0F;
/*  61:    */     }
/*  62: 76 */     if (Math.abs(span) <= 20.0F)
/*  63:    */     {
/*  64: 78 */       GL11.glTranslatef((float)x, (float)y + danmakuMob.height + 1.5F, (float)z);
/*  65: 79 */       GL11.glScalef(1.0F * size, 1.0F * size, 1.0F * size);
/*  66:    */       
/*  67:    */ 
/*  68:    */ 
/*  69: 83 */       GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
/*  70: 84 */       GL11.glRotatef(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
/*  71:    */       
/*  72: 86 */       bindTexture(this.statusTexture);
/*  73: 87 */       int cardNo = danmakuMob.getUsingSpellCardNo();
/*  74:    */       
/*  75: 89 */       float hp = danmakuMob.getHealth() / danmakuMob.getMaxHealth();
/*  76: 90 */       float hp2 = danmakuMob.getHealth() / danmakuMob.getMaxHealth() * 2.0F - 1.0F;
/*  77:    */       
/*  78: 92 */       tessellator.startDrawingQuads();
/*  79:    */       
/*  80:    */ 
/*  81: 95 */       tessellator.addVertexWithUV(-1.019999980926514D, 0.07000000029802322D, 0.001D, 0.0D, 0.1875D);
/*  82: 96 */       tessellator.addVertexWithUV(1.019999980926514D, 0.07000000029802322D, 0.001D, 1.0D, 0.1875D);
/*  83: 97 */       tessellator.addVertexWithUV(1.019999980926514D, -0.01999999955296516D, 0.001D, 1.0D, 0.25D);
/*  84: 98 */       tessellator.addVertexWithUV(-1.019999980926514D, -0.01999999955296516D, 0.001D, 0.0D, 0.25D);
/*  85: 99 */       tessellator.draw();
/*  86:102 */       if (cardNo >= 0)
/*  87:    */       {
/*  88:104 */         tessellator.startDrawingQuads();
/*  89:    */         
/*  90:    */ 
/*  91:107 */         tessellator.addVertexWithUV(-hp2, 0.0500000007450581D, 0.0D, hp, 0.5D);
/*  92:108 */         tessellator.addVertexWithUV(1.0D, 0.0500000007450581D, 0.0D, 0.0D, 0.5D);
/*  93:109 */         tessellator.addVertexWithUV(1.0D, 0.0D, 0.0D, 0.0D, 0.6875D);
/*  94:110 */         tessellator.addVertexWithUV(-hp2, 0.0D, 0.0D, hp, 0.6875D);
/*  95:    */         
/*  96:112 */         tessellator.draw();
/*  97:    */       }
/*  98:    */       else
/*  99:    */       {
/* 100:117 */         tessellator.startDrawingQuads();
/* 101:    */         
/* 102:    */ 
/* 103:120 */         tessellator.addVertexWithUV(-hp2, 0.0500000007450581D, 0.0D, hp, 0.0D);
/* 104:121 */         tessellator.addVertexWithUV(1.0D, 0.0500000007450581D, 0.0D, 0.0D, 0.0D);
/* 105:122 */         tessellator.addVertexWithUV(1.0D, 0.0D, 0.0D, 0.0D, 0.1875D);
/* 106:123 */         tessellator.addVertexWithUV(-hp2, 0.0D, 0.0D, hp, 0.1875D);
/* 107:    */         
/* 108:125 */         tessellator.draw();
/* 109:    */       }
/* 110:128 */       GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/* 111:129 */       GL11.glScalef(0.02F, 0.02F, 0.02F);
/* 112:130 */       tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, alpha);
/* 113:131 */       FontRenderer font = getFontRendererFromRenderManager();
/* 114:132 */       font.drawStringWithShadow(danmakuMob.getDanmakuMobName(), -50, 0, 65416);
/* 115:135 */       if (cardNo >= 0)
/* 116:    */       {
/* 117:137 */         tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, alpha);
/* 118:138 */         font.drawStringWithShadow(StatCollector.translateToLocal("item.thSpellCard." + cardNo + ".name"), -50, -12, 16777215);
/* 119:    */       }
/* 120:    */     }
/* 121:142 */     GL11.glDisable(3042);
/* 122:143 */     GL11.glEnable(2896);
/* 123:144 */     GL11.glPopMatrix();
/* 124:    */   }
/* 125:    */   
/* 126:    */   protected ResourceLocation getEntityTexture(Entity entity)
/* 127:    */   {
/* 128:150 */     return getEntityTexture((EntityDanmakuMob)entity);
/* 129:    */   }
/* 130:    */   
/* 131:    */   protected ResourceLocation getEntityTexture(EntityDanmakuMob danmakuMob)
/* 132:    */   {
/* 133:155 */     return this.statusTexture;
/* 134:    */   }
/* 135:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.client.render.living.RenderTHBoss
 * JD-Core Version:    0.7.0.1
 */