/*   1:    */ package thKaguyaMod.entity.spellcard;
/*   2:    */ 
/*   3:    */ import java.util.Random;
/*   4:    */ import net.minecraft.entity.Entity;
/*   5:    */ import net.minecraft.entity.EntityLivingBase;
/*   6:    */ import net.minecraft.util.MovingObjectPosition;
/*   7:    */ import net.minecraft.util.Vec3;
/*   8:    */ import net.minecraft.world.World;
/*   9:    */ import thKaguyaMod.ShotData;
/*  10:    */ import thKaguyaMod.THShotLib;
/*  11:    */ import thKaguyaMod.entity.shot.EntityTHShot;
/*  12:    */ import thKaguyaMod.entity.shot.ISpecialShot;
/*  13:    */ import thKaguyaMod.registry.SpecialShotRegistry;
/*  14:    */ 
/*  15:    */ public class THSC_MeteorOnEarth
/*  16:    */   extends THSpellCard
/*  17:    */   implements ISpecialShot
/*  18:    */ {
/*  19:    */   public static final int SP_METEORONEARTH01 = 3500;
/*  20:    */   public static final int SP_METEORONEARTH11 = 3510;
/*  21:    */   
/*  22:    */   public THSC_MeteorOnEarth()
/*  23:    */   {
/*  24: 23 */     setNeedLevel(1);
/*  25: 24 */     setRemoveTime(25);
/*  26: 25 */     setEndTime(120);
/*  27: 26 */     setOriginalUserName(23);
/*  28:    */     
/*  29: 28 */     SpecialShotRegistry.registerSpecialShot(THSC_MeteorOnEarth.class, 3500);
/*  30: 29 */     SpecialShotRegistry.registerSpecialShot(THSC_MeteorOnEarth.class, 3510);
/*  31:    */   }
/*  32:    */   
/*  33:    */   public void spellcard_main()
/*  34:    */   {
/*  35: 37 */     int[] way = { 0, 5, 7, 4, 6 };
/*  36: 40 */     if (this.time == 5)
/*  37:    */     {
/*  38: 42 */       Vec3 angle = angle_UserToTarget();
/*  39: 43 */       THShotLib.createRingShot(this.user, this.user, pos_User(), angle, 2.0F, 60, 0.3D, 0.3D, 1.0D, 
/*  40: 44 */         gravity_Zero(), 
/*  41: 45 */         ShotData.shot(17, 2, 0, 60, 3500), way[this.level], 0.5D, 30.0F, this.rand
/*  42: 46 */         .nextFloat() * 360.0F);
/*  43: 47 */       if (this.level >= 3) {
/*  44: 49 */         THShotLib.createRingShot(this.user, this.user, pos_User(), angle, 2.0F, 60, 0.3D, 0.3D, 1.0D, 
/*  45: 50 */           gravity_Zero(), 
/*  46: 51 */           ShotData.shot(17, 2, 0, 60, 3500), 8, 0.5D, 70.0F, this.rand
/*  47: 52 */           .nextFloat() * 360.0F);
/*  48:    */       }
/*  49: 54 */       THShotLib.playShotSound(this.user);
/*  50:    */     }
/*  51: 56 */     if (this.time == 65)
/*  52:    */     {
/*  53: 58 */       Vec3 angle = angle_UserToTarget();
/*  54: 59 */       THShotLib.createRingShot(this.user, this.user, pos_User(), angle, -2.0F, 60, 0.3D, 0.3D, 1.0D, 
/*  55: 60 */         gravity_Zero(), 
/*  56: 61 */         ShotData.shot(17, 2, 0, 60, 3500), way[this.level], 0.5D, 30.0F, this.rand
/*  57: 62 */         .nextFloat() * 360.0F);
/*  58: 63 */       if (this.level >= 3) {
/*  59: 65 */         THShotLib.createRingShot(this.user, this.user, pos_User(), angle, -2.0F, 60, 0.3D, 0.3D, 1.0D, 
/*  60: 66 */           gravity_Zero(), 
/*  61: 67 */           ShotData.shot(17, 2, 0, 60, 3500), 8, 0.5D, 70.0F, this.rand
/*  62: 68 */           .nextFloat() * 360.0F);
/*  63:    */       }
/*  64: 70 */       THShotLib.playShotSound(this.user);
/*  65:    */     }
/*  66:    */   }
/*  67:    */   
/*  68:    */   public void specialShot_move(World world, int id, EntityTHShot shot)
/*  69:    */   {
/*  70: 77 */     switch (id)
/*  71:    */     {
/*  72:    */     case 3500: 
/*  73: 80 */       if (shot.ticksExisted == 1) {
/*  74: 82 */         shot.rotate = shot.user.getLookVec();
/*  75:    */       }
/*  76: 84 */       if (shot.ticksExisted % 2 == 0) {
/*  77: 86 */         THShotLib.createShot(shot.user, shot, shot.pos(), THShotLib.getVectorFromRotation(shot.angle, shot.rotate, shot.ticksExisted * 13.0F), 0.0F, 0.0D, 0.5D, 0.0D, gravity_Zero(), 
/*  78: 87 */           ShotData.shot(0, 5, 5, 40, 3510));
/*  79:    */       }
/*  80:    */       break;
/*  81:    */     case 3510: 
/*  82: 91 */       if (shot.ticksExisted == 30)
/*  83:    */       {
/*  84: 93 */         shot.shotAcceleration = 0.03D;
/*  85: 94 */         shot.updateMotion();
/*  86:    */       }
/*  87:    */       break;
/*  88:    */     }
/*  89:    */   }
/*  90:    */   
/*  91:    */   public boolean specialShot_hitBlock(World world, int id, EntityTHShot shot, MovingObjectPosition movingObjectPosition)
/*  92:    */   {
/*  93:108 */     return false;
/*  94:    */   }
/*  95:    */   
/*  96:    */   public boolean specialShot_hitEntity(World world, int id, EntityTHShot shot, Entity entity_Hit)
/*  97:    */   {
/*  98:115 */     return false;
/*  99:    */   }
/* 100:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.spellcard.THSC_MeteorOnEarth
 * JD-Core Version:    0.7.0.1
 */