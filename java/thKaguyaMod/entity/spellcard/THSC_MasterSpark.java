/*  1:   */ package thKaguyaMod.entity.spellcard;
/*  2:   */ 
/*  3:   */ import net.minecraft.entity.EntityLivingBase;
/*  4:   */ import net.minecraft.util.MathHelper;
/*  5:   */ import net.minecraft.util.Vec3;
/*  6:   */ import net.minecraft.world.World;
/*  7:   */ import thKaguyaMod.ShotData;
/*  8:   */ import thKaguyaMod.THShotLib;
/*  9:   */ import thKaguyaMod.entity.item.EntityMiniHakkero;
/* 10:   */ 
/* 11:   */ public class THSC_MasterSpark
/* 12:   */   extends THSpellCard
/* 13:   */ {
/* 14:   */   private Vec3 tgVec;
/* 15:   */   
/* 16:   */   public THSC_MasterSpark()
/* 17:   */   {
/* 18:19 */     setSpellCardName("");
/* 19:20 */     setIconName("MasterSpark");
/* 20:21 */     setNeedLevel(5);
/* 21:22 */     setRemoveTime(99);
/* 22:23 */     setEndTime(109);
/* 23:24 */     setOriginalUserName(1);
/* 24:   */   }
/* 25:   */   
/* 26:   */   public void spellcard_main()
/* 27:   */   {
/* 28:30 */     if (this.time == 1)
/* 29:   */     {
/* 30:32 */       this.tgVec = this.user.getLookVec();
/* 31:   */       
/* 32:   */ 
/* 33:35 */       EntityMiniHakkero miniHakkero = new EntityMiniHakkero(this.world, this.user, this.target);
/* 34:36 */       if (!this.world.isRemote) {
/* 35:38 */         this.world.spawnEntityInWorld(miniHakkero);
/* 36:   */       }
/* 37:   */     }
/* 38:41 */     if ((this.time >= 30) && (this.time < 99))
/* 39:   */     {
/* 40:44 */       double angleXZ = 0.0D;double angleY = 0.0D;
/* 41:45 */       Vec3 lookAt = this.tgVec;
/* 42:46 */       lookAt.xCoord = (-MathHelper.sin(this.card.rotationYaw / 180.0F * 3.141593F) * MathHelper.cos((this.card.rotationPitch + 90.0F) / 180.0F * 3.141593F));
/* 43:47 */       lookAt.yCoord = (-MathHelper.sin((this.card.rotationPitch + 90.0F) / 180.0F * 3.141593F));
/* 44:48 */       lookAt.zCoord = (MathHelper.cos(this.card.rotationYaw / 180.0F * 3.141593F) * MathHelper.cos((this.card.rotationPitch + 90.0F) / 180.0F * 3.141593F));
/* 45:49 */       lookAt.rotateAroundY(6.283186F);
/* 46:50 */       float angle = this.time * 6.0F;
/* 47:51 */       float angleSpan = 51.42857F;
/* 48:   */       
/* 49:53 */       double gRate = 0.034D + 0.03D * Math.sin(angle / 180.0F * 3.141593F);
/* 50:   */       
/* 51:55 */       double xVectorG = -MathHelper.sin(this.card.rotationYaw / 180.0F * 3.141593F) * MathHelper.cos(this.card.rotationPitch / 180.0F * 3.141593F) * gRate;
/* 52:56 */       double yVectorG = -MathHelper.sin(this.card.rotationPitch / 180.0F * 3.141593F) * gRate;
/* 53:57 */       double zVectorG = MathHelper.cos(this.card.rotationYaw / 180.0F * 3.141593F) * MathHelper.cos(this.card.rotationPitch / 180.0F * 3.141593F) * gRate;
/* 54:59 */       for (int i = 0; i < 7; i++)
/* 55:   */       {
/* 56:61 */         angleXZ = angle;
/* 57:62 */         angleY = 0.0D;
/* 58:   */         
/* 59:64 */         double X1 = Math.sin(angleXZ / 180.0D * 3.141592653589793D) * Math.cos(this.card.rotationYaw / 180.0F * 3.141592653589793D);
/* 60:65 */         double Z1 = Math.sin(angleXZ / 180.0D * 3.141592653589793D) * Math.sin(this.card.rotationYaw / 180.0F * 3.141592653589793D);
/* 61:66 */         double X2 = Math.cos(angleXZ / 180.0D * 3.141592653589793D) * Math.sin(angleY / 180.0D * 3.141592653589793D) * Math.sin((this.card.rotationPitch + 90.0F) / 180.0F * 3.141592653589793D) * Math.sin(this.card.rotationYaw / 180.0F * 3.141592653589793D);
/* 62:67 */         double Z2 = Math.cos(angleXZ / 180.0D * 3.141592653589793D) * Math.sin(angleY / 180.0D * 3.141592653589793D) * Math.sin((this.card.rotationPitch + 90.0F) / 180.0F * 3.141592653589793D) * Math.cos(this.card.rotationYaw / 180.0F * 3.141592653589793D);
/* 63:   */         
/* 64:69 */         double yVector = -Math.cos(angleXZ / 180.0D * 3.141592653589793D) * Math.sin((this.card.rotationPitch + 90.0F - angleY) / 180.0D * 3.141592653589793D);
/* 65:70 */         double xVector = Math.cos(angleXZ / 180.0D * 3.141592653589793D) * Math.cos(angleY / 180.0D * 3.141592653589793D) * lookAt.xCoord + X1 - X2;
/* 66:71 */         double zVector = Math.cos(angleXZ / 180.0D * 3.141592653589793D) * Math.cos(angleY / 180.0D * 3.141592653589793D) * lookAt.zCoord + Z1 + Z2;
/* 67:   */         
/* 68:73 */         ShotData shot = ShotData.shot(9, this.time % 7, 0.4F, 8.0F, 0, 55);
/* 69:74 */         THShotLib.createShot(this.user, this.card, pos_Card(), angle(xVector, yVector, zVector), 0.0F, 0.2D, 0.3D, 0.05D, THShotLib.gravity(xVectorG, yVectorG, zVectorG), shot);
/* 70:   */         
/* 71:   */ 
/* 72:77 */         angle += angleSpan;
/* 73:   */       }
/* 74:   */     }
/* 75:   */   }
/* 76:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.spellcard.THSC_MasterSpark
 * JD-Core Version:    0.7.0.1
 */