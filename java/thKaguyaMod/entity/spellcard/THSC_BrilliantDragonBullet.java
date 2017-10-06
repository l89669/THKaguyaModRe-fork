/*  1:   */ package thKaguyaMod.entity.spellcard;
/*  2:   */ 
/*  3:   */ import net.minecraft.entity.Entity;
/*  4:   */ import net.minecraft.entity.EntityLivingBase;
/*  5:   */ import net.minecraft.util.MovingObjectPosition;
/*  6:   */ import net.minecraft.util.Vec3;
/*  7:   */ import net.minecraft.world.World;
/*  8:   */ import thKaguyaMod.LaserData;
/*  9:   */ import thKaguyaMod.ShotData;
/* 10:   */ import thKaguyaMod.THShotLib;
/* 11:   */ import thKaguyaMod.entity.shot.EntityTHShot;
/* 12:   */ import thKaguyaMod.entity.shot.ISpecialShot;
/* 13:   */ import thKaguyaMod.registry.SpecialShotRegistry;
/* 14:   */ 
/* 15:   */ public class THSC_BrilliantDragonBullet
/* 16:   */   extends THSpellCard
/* 17:   */   implements ISpecialShot
/* 18:   */ {
/* 19:   */   public static final int SPECIAL_DRAGONBULLET01 = 4100;
/* 20:   */   
/* 21:   */   public THSC_BrilliantDragonBullet()
/* 22:   */   {
/* 23:23 */     setNeedLevel(2);
/* 24:24 */     setRemoveTime(20);
/* 25:25 */     setEndTime(90);
/* 26:26 */     setOriginalUserName(29);
/* 27:   */     
/* 28:28 */     SpecialShotRegistry.registerSpecialShot(THSC_BrilliantDragonBullet.class, 4100);
/* 29:   */   }
/* 30:   */   
/* 31:   */   public void spellcard_main()
/* 32:   */   {
/* 33:34 */     if (this.time % 5 == 0)
/* 34:   */     {
/* 35:37 */       Vec3 angle = THShotLib.angle(this.user.rotationYaw + this.time * 4 - 90.0F, this.user.rotationPitch);
/* 36:38 */       Vec3 angle2 = THShotLib.angle(this.user.rotationYaw - this.time * 4 + 90.0F, this.user.rotationPitch);
/* 37:   */       
/* 38:   */ 
/* 39:   */ 
/* 40:   */ 
/* 41:   */ 
/* 42:   */ 
/* 43:45 */       THShotLib.createShot(this.user, this.user, pos_User(), angle, 0.0F, 0.4000000059604645D, 0.4000000059604645D, 0.0D, gravity_Zero(), ShotData.shot(17, 0, 1.0F, 1.0F, 0, 5, 4100));
/* 44:46 */       THShotLib.createShot(this.user, this.user, pos_User(), angle2, 0.0F, 0.4000000059604645D, 0.4000000059604645D, 0.0D, gravity_Zero(), ShotData.shot(17, 0, 1.0F, 1.0F, 0, 5, 4100));
/* 45:   */     }
/* 46:   */   }
/* 47:   */   
/* 48:   */   public void specialShot_move(World world, int id, EntityTHShot shot)
/* 49:   */   {
/* 50:55 */     switch (id)
/* 51:   */     {
/* 52:   */     case 4100: 
/* 53:58 */       int[] color = { 4, 5, 3 };
/* 54:59 */       int[] color2 = { 0, 1, 3, 4, 5 };
/* 55:60 */       if (shot.ticksExisted >= shot.getShotEndTime() - 1)
/* 56:   */       {
/* 57:62 */         Vec3 angle = THShotLib.angle_LimitRandom(shot.angle, 180.0F);
/* 58:63 */         angle = THShotLib.getVectorMultiply(angle, -1.0D);
/* 59:64 */         Vec3 angle2 = THShotLib.angle_LimitRandom(shot.angle, 30.0F);
/* 60:65 */         angle2 = THShotLib.getVectorMultiply(angle2, 0.1D);
/* 61:66 */         for (int i = 0; i < 10; i++) {
/* 62:68 */           THShotLib.createRandomRingShot(shot.user, shot.user, shot.pos_Shot(), angle, 0.0F, 9999, 0.6D, 0.2D, -0.05D, angle2, shot(5, color2[this.rand.nextInt(5)], THShotLib.SIZE[5], 3.0F, 3, 60, 0), 1, 0.1D, 50.0F);
/* 63:   */         }
/* 64:70 */         for (int i = 0; i < 4; i++) {
/* 65:72 */           THShotLib.createLaserA(shot.user, shot.pos_Shot(), THShotLib.angle_LimitRandom(shot.angle, 30.0F), 0.6D, 0.1D, -0.1D, LaserData.laser(color[this.rand.nextInt(3)], 0.2F, 6.0F, 6.0F));
/* 66:   */         }
/* 67:74 */         if (!world.isRemote) {
/* 68:76 */           shot.setDead();
/* 69:   */         }
/* 70:78 */         THShotLib.playShotSound(shot.user);
/* 71:   */       }
/* 72:79 */       break;
/* 73:   */     }
/* 74:   */   }
/* 75:   */   
/* 76:   */   public boolean specialShot_hitBlock(World world, int id, EntityTHShot shot, MovingObjectPosition movingObjectPosition)
/* 77:   */   {
/* 78:91 */     return false;
/* 79:   */   }
/* 80:   */   
/* 81:   */   public boolean specialShot_hitEntity(World world, int id, EntityTHShot shot, Entity entity_Hit)
/* 82:   */   {
/* 83:98 */     return false;
/* 84:   */   }
/* 85:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.spellcard.THSC_BrilliantDragonBullet
 * JD-Core Version:    0.7.0.1
 */