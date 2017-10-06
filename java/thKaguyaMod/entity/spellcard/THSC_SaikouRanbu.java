/*  1:   */ package thKaguyaMod.entity.spellcard;
/*  2:   */ 
/*  3:   */ import java.util.Random;
/*  4:   */ import net.minecraft.entity.EntityLivingBase;
/*  5:   */ import net.minecraft.util.Vec3;
/*  6:   */ import thKaguyaMod.THShotLib;
/*  7:   */ 
/*  8:   */ public class THSC_SaikouRanbu
/*  9:   */   extends THSpellCard
/* 10:   */ {
/* 11:   */   public THSC_SaikouRanbu()
/* 12:   */   {
/* 13:13 */     setNeedLevel(2);
/* 14:14 */     setRemoveTime(60);
/* 15:15 */     setEndTime(80);
/* 16:16 */     setOriginalUserName(5);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void spellcard_main()
/* 20:   */   {
/* 21:22 */     if (this.time > 60) {
/* 22:24 */       return;
/* 23:   */     }
/* 24:27 */     if ((this.time % 30 < 10) && (this.time % 2 == 1))
/* 25:   */     {
/* 26:29 */       Vec3 angle = this.user.getLookVec();
/* 27:30 */       int[] color = { 3, 5, 1, 2 };
/* 28:31 */       angle = THShotLib.getVecFromAngle(this.card.rotationYaw, -90.0F);
/* 29:32 */       THShotLib.createRingShot(this.user, this.user, pos(this.userPosX, THShotLib.getPosYFromEye(this.user, 0.3D), this.userPosZ), angle, 0.0F, 9999, 0.7D, 0.7D, 0.0D, gravity_Default(), shot(11, color[(this.time % 4)], 0.2F, 2.5F, 0, 60), 15, 0.7D, 40.0F + this.time % 30 * 5.0F, 0.0F);
/* 30:   */     }
/* 31:35 */     if ((this.time % 30 >= 10) && (this.time % 30 < 20))
/* 32:   */     {
/* 33:37 */       int[] color = { 0, 6, 4 };
/* 34:38 */       for (int i = 1; i <= 14; i++)
/* 35:   */       {
/* 36:40 */         float randYaw = this.rand.nextFloat() * 360.0F;
/* 37:41 */         float randPitch = this.rand.nextFloat() * 360.0F;
/* 38:42 */         Vec3 move = THShotLib.getVecFromAngle(randYaw, randPitch);
/* 39:43 */         Vec3 rotate = THShotLib.getVecFromAngle(randYaw, randPitch + 90.0F);
/* 40:44 */         THShotLib.createShot(this.user, this.user, pos_User(), move, 0.0F, rotate, 3.0F, 20, 1.5D, 0.4D, -0.4D, 
/* 41:   */         
/* 42:   */ 
/* 43:47 */           gravity_Zero(), 
/* 44:48 */           shot(11, color[this.rand.nextInt(3)], 0.2F, 3.5F, 0, 50));
/* 45:   */       }
/* 46:   */     }
/* 47:51 */     if ((this.time % 30 >= 20) && (this.time % 30 < 30))
/* 48:   */     {
/* 49:53 */       int[] color = { 0, 6, 4 };
/* 50:54 */       for (int i = 1; i <= 14; i++)
/* 51:   */       {
/* 52:56 */         float randYaw = this.rand.nextFloat() * 360.0F;
/* 53:57 */         float randPitch = this.rand.nextFloat() * 360.0F;
/* 54:58 */         Vec3 move = THShotLib.getVecFromAngle(randYaw, randPitch);
/* 55:59 */         Vec3 rotate = THShotLib.getVecFromAngle(randYaw, randPitch + 90.0F);
/* 56:60 */         THShotLib.createShot(this.user, this.user, pos_User(), move, 0.0F, rotate, -3.0F, 20, 1.5D, 0.4D, -0.4D, 
/* 57:   */         
/* 58:   */ 
/* 59:63 */           gravity_Zero(), 
/* 60:64 */           shot(11, color[this.rand.nextInt(3)], 0.2F, 3.5F, 0, 50));
/* 61:   */       }
/* 62:   */     }
/* 63:   */   }
/* 64:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.spellcard.THSC_SaikouRanbu
 * JD-Core Version:    0.7.0.1
 */