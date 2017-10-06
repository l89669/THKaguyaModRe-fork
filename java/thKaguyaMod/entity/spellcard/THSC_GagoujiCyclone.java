/*   1:    */ package thKaguyaMod.entity.spellcard;
/*   2:    */ 
/*   3:    */ import net.minecraft.entity.Entity;
/*   4:    */ import net.minecraft.entity.EntityLivingBase;
/*   5:    */ import net.minecraft.util.MovingObjectPosition;
/*   6:    */ import net.minecraft.world.World;
/*   7:    */ import thKaguyaMod.ShotData;
/*   8:    */ import thKaguyaMod.THShotLib;
/*   9:    */ import thKaguyaMod.entity.living.EntityDanmakuMob;
/*  10:    */ import thKaguyaMod.entity.shot.EntityTHShot;
/*  11:    */ import thKaguyaMod.entity.shot.ISpecialShot;
/*  12:    */ import thKaguyaMod.init.THKaguyaConfig;
/*  13:    */ import thKaguyaMod.registry.SpecialShotRegistry;
/*  14:    */ 
/*  15:    */ public class THSC_GagoujiCyclone
/*  16:    */   extends THSpellCard
/*  17:    */   implements ISpecialShot
/*  18:    */ {
/*  19:    */   public static final int SPECIAL_GAGOUJI01 = 4000;
/*  20:    */   public static final int SPECIAL_TOZIKO01 = 4020;
/*  21:    */   
/*  22:    */   public THSC_GagoujiCyclone()
/*  23:    */   {
/*  24: 26 */     setNeedLevel(2);
/*  25: 27 */     setRemoveTime(30);
/*  26: 28 */     setEndTime(120);
/*  27: 29 */     setOriginalUserName(65);
/*  28:    */     
/*  29: 31 */     SpecialShotRegistry.registerSpecialShot(THSC_GagoujiCyclone.class, 4000);
/*  30:    */     
/*  31: 33 */     SpecialShotRegistry.registerSpecialShot(THSC_GagoujiCyclone.class, 4020);
/*  32:    */   }
/*  33:    */   
/*  34:    */   public void spellcard_main()
/*  35:    */   {
/*  36: 39 */     if ((this.time % 120 <= 40) && (this.time % 2 == 0))
/*  37:    */     {
/*  38: 41 */       float angle = (float)Math.sin(this.time * 13.0F / 180.0F * 3.141593F) * (30.0F + this.level * 30.0F) + 90.0F;
/*  39: 42 */       int num = 4 + this.level * 2;
/*  40: 43 */       float damage = 6.0F;
/*  41:    */       
/*  42: 45 */       THShotLib.createRingShot(this.user, this.user, pos_User(), angle_UserToTarget(), 0.0F, 9999, 1.0D, 1.0D, 0.0D, gravity_Zero(), ShotData.shot(18, 3, 0, 50, 4000), 9, 1.0D, 80.0F, this.time / 2.0F);
/*  43: 46 */       if (this.level >= 2) {
/*  44: 47 */         THShotLib.createShot(this.user, this.user, pos_User(), angle_UserToTarget(), 0.0F, angle(this.user.rotationYaw, this.user.rotationPitch + 90.0F), 0.0F, 99999, 1.0D, 0.0D, -0.1D, gravity_Zero(), ShotData.shot(18, 3, THShotLib.SIZE[18], 5.0F, 0, 60, 4000));
/*  45:    */       }
/*  46:    */     }
/*  47: 50 */     if ((this.time % 120 >= 60) && (this.time % 120 < 100) && (this.time % 2 == 0))
/*  48:    */     {
/*  49: 52 */       float angle = (float)Math.sin(this.time * 13.0F / 180.0F * 3.141593F) * (30.0F + this.level * 30.0F) + 90.0F;
/*  50: 53 */       int num = 4 + this.level * 2;
/*  51: 54 */       float damage = 6.0F;
/*  52:    */       
/*  53: 56 */       THShotLib.createRingShot(this.user, this.user, pos_User(), angle_UserToTarget(), 0.0F, 9999, 1.0D, 1.0D, 0.0D, gravity_Zero(), ShotData.shot(18, 3, 0, 50, 4000), 9, 1.0D, 80.0F, -this.time / 2.0F);
/*  54: 57 */       if (this.level >= 2) {
/*  55: 58 */         THShotLib.createShot(this.user, this.user, pos_User(), angle_UserToTarget(), 0.0F, angle(this.user.rotationYaw, this.user.rotationPitch + 90.0F), 0.0F, 99999, 1.0D, 0.0D, -0.1D, gravity_Zero(), ShotData.shot(18, 3, THShotLib.SIZE[18], 5.0F, 0, 60, 4000));
/*  56:    */       }
/*  57:    */     }
/*  58: 61 */     if (this.level == 4) {
/*  59: 63 */       if ((this.time % 120 > 30) && (this.time % 120 <= 90)) {
/*  60: 65 */         THShotLib.createRingShot(this.user, this.user, pos_User(), angle_UserToTarget(), 0.0F, 99999, 1.0D, 0.0D, -0.1D, gravity_Zero(), ShotData.shot(18, 0, 0, 60, 4000), 3, 1.0D, 10.0F, this.time / 2.0F);
/*  61:    */       }
/*  62:    */     }
/*  63: 69 */     if (this.time % 20 == 0)
/*  64:    */     {
/*  65: 71 */       THShotLib.createRingShot(this.user, this.user, pos_User(), angle_UserToTarget(), 0.0F, 99999, 1.2D, 0.15D + this.level * 0.05D, -0.1D, gravity_Zero(), ShotData.shot(4, 3, THShotLib.SIZE[4], 5.0F, 0, 90, 0), 8 + this.level * 4, 1.0D, 70.0F, 0.0F);
/*  66: 72 */       THShotLib.createRingShot(this.user, this.user, pos_User(), angle_UserToTarget(), 0.0F, 99999, 1.2D, 0.15D + this.level * 0.05D, -0.1D, gravity_Zero(), ShotData.shot(4, 3, THShotLib.SIZE[4], 5.0F, 0, 90, 0), 4 + this.level * 2, 1.0D, 20.0F, 0.0F);
/*  67: 73 */       if (this.level >= 3) {
/*  68: 75 */         THShotLib.createShot(this.user, this.user, pos_User(), angle_UserToTarget(), 0.0F, rotate_Default(), 0.0F, 99999, 1.2D, 0.15D + this.level * 0.05D, -0.1D, gravity_Zero(), ShotData.shot(4, 3, THShotLib.SIZE[4], 5.0F, 0, 90, 0));
/*  69:    */       }
/*  70: 77 */       THShotLib.playShotSound(this.user);
/*  71:    */     }
/*  72:    */   }
/*  73:    */   
/*  74:    */   public void specialShot_move(World world, int id, EntityTHShot shot)
/*  75:    */   {
/*  76: 84 */     switch (id)
/*  77:    */     {
/*  78:    */     case 4000: 
/*  79: 87 */       if (shot.ticksExisted == 1)
/*  80:    */       {
/*  81: 89 */         shot.setShotRotationYaw(45.0F);
/*  82:    */         
/*  83: 91 */         shot.shotAcceleration = -0.09900000000000001D;
/*  84: 92 */         shot.shotLimitSpeed = 0.0D;
/*  85: 93 */         shot.setMotion();
/*  86:    */       }
/*  87: 98 */       if (shot.ticksExisted % 20 == 9)
/*  88:    */       {
/*  89:100 */         shot.setShotRotationYaw(-90.0F);
/*  90:101 */         shot.setSpeed(1.0D);
/*  91:102 */         shot.setMotion();
/*  92:    */       }
/*  93:104 */       else if (shot.ticksExisted % 20 == 19)
/*  94:    */       {
/*  95:106 */         shot.setShotRotationYaw(90.0F);
/*  96:107 */         shot.setSpeed(1.0D);
/*  97:108 */         shot.setMotion();
/*  98:    */       }
/*  99:    */       break;
/* 100:    */     case 4020: 
/* 101:112 */       if (shot.ticksExisted >= shot.getDeadTime())
/* 102:    */       {
/* 103:114 */         if ((shot.user instanceof EntityDanmakuMob))
/* 104:    */         {
/* 105:116 */           EntityDanmakuMob danmakuMob = (EntityDanmakuMob)shot.user;
/* 106:117 */           if (danmakuMob.getTarget() != null)
/* 107:    */           {
/* 108:119 */             shot.angle = THShotLib.angle_ToPos(danmakuMob.pos(), pos(danmakuMob.getTarget().posX, danmakuMob.getTarget().posY, danmakuMob.getTarget().posZ));
/* 109:120 */             shot.setMotion();
/* 110:    */           }
/* 111:    */         }
/* 112:123 */         shot.setDeadTime(90);
/* 113:124 */         shot.shotSpeed = 0.01D;
/* 114:125 */         shot.shotAcceleration = 0.03D;
/* 115:126 */         shot.shotLimitSpeed = (0.2D + THKaguyaConfig.danmakuLevel * 0.1D);
/* 116:127 */         shot.specialEnd();
/* 117:    */       }
/* 118:    */       break;
/* 119:    */     }
/* 120:    */   }
/* 121:    */   
/* 122:    */   public boolean specialShot_hitBlock(World world, int id, EntityTHShot shot, MovingObjectPosition movingObjectPosition)
/* 123:    */   {
/* 124:138 */     return false;
/* 125:    */   }
/* 126:    */   
/* 127:    */   public boolean specialShot_hitEntity(World world, int id, EntityTHShot shot, Entity entity_Hit)
/* 128:    */   {
/* 129:145 */     return false;
/* 130:    */   }
/* 131:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.spellcard.THSC_GagoujiCyclone
 * JD-Core Version:    0.7.0.1
 */