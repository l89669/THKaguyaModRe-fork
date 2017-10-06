/*  1:   */ package thKaguyaMod.entity.spellcard;
/*  2:   */ 
/*  3:   */ import net.minecraft.entity.EntityLivingBase;
/*  4:   */ import net.minecraft.util.Vec3;
/*  5:   */ import net.minecraft.world.World;
/*  6:   */ import thKaguyaMod.LaserData;
/*  7:   */ import thKaguyaMod.THShotLib;
/*  8:   */ import thKaguyaMod.init.THKaguyaConfig;
/*  9:   */ 
/* 10:   */ public class THSC_NonDirectionalLaser
/* 11:   */   extends THSpellCard
/* 12:   */ {
/* 13:   */   public THSC_NonDirectionalLaser()
/* 14:   */   {
/* 15:14 */     setNeedLevel(3);
/* 16:15 */     setRemoveTime(60);
/* 17:16 */     setEndTime(103);
/* 18:17 */     setOriginalUserName(1);
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void spellcard_main()
/* 22:   */   {
/* 23:23 */     if (this.time == 3)
/* 24:   */     {
/* 25:25 */       this.world.playSoundAtEntity(this.user, "thKaguyaMod.masterspark", THKaguyaConfig.MasterSparkVol, 1.0F);
/* 26:26 */       for (int i = 0; i < 5; i++)
/* 27:   */       {
/* 28:28 */         Vec3 look = this.user.getLookVec();
/* 29:29 */         Vec3 rotate = THShotLib.getVecFromAngle(this.user.rotationYaw, this.user.rotationPitch - 90.0F);
/* 30:30 */         Vec3 move = THShotLib.getVectorFromRotation(rotate.xCoord, rotate.yCoord, rotate.zCoord, look.xCoord, look.yCoord, look.zCoord, i * 72.0F);
/* 31:31 */         THShotLib.createLaserB(this.user, this.user, pos(0.0D, THShotLib.getPosYFromEye(this.user) - this.user.posY, 0.0D), move, rotate, -4.0F, 9999, LaserData.laser(0 + i, 0.7F, 20.799999F, 7.0F, 25, 60, 0), this.user, 1.5D, 1.0D);
/* 32:   */       }
/* 33:   */     }
/* 34:34 */     if (this.time == 63)
/* 35:   */     {
/* 36:36 */       this.world.playSoundAtEntity(this.user, "thKaguyaMod.masterspark", THKaguyaConfig.MasterSparkVol, 1.0F);
/* 37:37 */       for (int i = 0; i < 5; i++)
/* 38:   */       {
/* 39:39 */         Vec3 look = this.user.getLookVec();
/* 40:40 */         Vec3 rotate = THShotLib.getVecFromAngle(this.user.rotationYaw, this.user.rotationPitch - 90.0F);
/* 41:41 */         Vec3 move = THShotLib.getVectorFromRotation(rotate.xCoord, rotate.yCoord, rotate.zCoord, look.xCoord, look.yCoord, look.zCoord, i * 72.0F);
/* 42:42 */         THShotLib.createLaserB(this.user, this.user, pos(0.0D, THShotLib.getPosYFromEye(this.user) - this.user.posY, 0.0D), move, rotate, 4.0F, 9999, LaserData.laser(0 + i, 0.7F, 20.799999F, 7.0F, 25, 60, 0), this.user, 1.5D, 1.0D);
/* 43:   */       }
/* 44:   */     }
/* 45:   */   }
/* 46:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.spellcard.THSC_NonDirectionalLaser
 * JD-Core Version:    0.7.0.1
 */