/*   1:    */ package thKaguyaMod.entity.spellcard;
/*   2:    */ 
/*   3:    */ import java.util.Random;
/*   4:    */ import net.minecraft.entity.Entity;
/*   5:    */ import net.minecraft.entity.EntityLivingBase;
/*   6:    */ import net.minecraft.util.MovingObjectPosition;
/*   7:    */ import net.minecraft.world.World;
/*   8:    */ import thKaguyaMod.ShotData;
/*   9:    */ import thKaguyaMod.THShotLib;
/*  10:    */ import thKaguyaMod.entity.shot.EntityTHShot;
/*  11:    */ import thKaguyaMod.entity.shot.ISpecialShot;
/*  12:    */ import thKaguyaMod.registry.SpecialShotRegistry;
/*  13:    */ 
/*  14:    */ public class THSC_FujiyamaVolcano
/*  15:    */   extends THSpellCard
/*  16:    */   implements ISpecialShot
/*  17:    */ {
/*  18:    */   public static final int SPECIAL_FUJUYAMA01 = 3300;
/*  19:    */   public static final int SPECIAL_FUJUYAMA02 = 3301;
/*  20:    */   public static final int SPECIAL_FUJUYAMA03 = 3302;
/*  21:    */   public static final int SPECIAL_FUJUYAMA04 = 3303;
/*  22:    */   
/*  23:    */   public THSC_FujiyamaVolcano()
/*  24:    */   {
/*  25: 25 */     setNeedLevel(5);
/*  26: 26 */     setRemoveTime(20);
/*  27: 27 */     setEndTime(120);
/*  28: 28 */     setOriginalUserName(30);
/*  29:    */     
/*  30: 30 */     SpecialShotRegistry.registerSpecialShot(THSC_FujiyamaVolcano.class, 3300);
/*  31: 31 */     SpecialShotRegistry.registerSpecialShot(THSC_FujiyamaVolcano.class, 3301);
/*  32: 32 */     SpecialShotRegistry.registerSpecialShot(THSC_FujiyamaVolcano.class, 3302);
/*  33: 33 */     SpecialShotRegistry.registerSpecialShot(THSC_FujiyamaVolcano.class, 3303);
/*  34:    */   }
/*  35:    */   
/*  36:    */   public void spellcard_main()
/*  37:    */   {
/*  38: 39 */     if ((this.time < 45) && (this.time % 15 == 0))
/*  39:    */     {
/*  40: 41 */       ShotData shot = ShotData.shot(17, 1, 0, 40, 3300);
/*  41: 42 */       THShotLib.createShot(this.user, this.user, pos_User(), angle_UserToTarget(), 0.0F, 2.4D, 0.0D, -(2.4D / this.user.getDistanceToEntity(this.target)), gravity_Zero(), shot);
/*  42: 43 */       THShotLib.playShotSound(this.user);
/*  43:    */     }
/*  44: 45 */     if ((this.time >= 60) && (this.time <= 90) && (this.time % 7 == 0))
/*  45:    */     {
/*  46: 47 */       ShotData shot = ShotData.shot(17, 1, 0, 40, 3300);
/*  47: 48 */       THShotLib.createShot(this.user, this.user, pos_User(), angle_UserToTarget(), 0.0F, 2.4D, 0.0D, -(2.4D / this.user.getDistanceToEntity(this.target)), gravity_Zero(), shot);
/*  48: 49 */       THShotLib.playShotSound(this.user);
/*  49:    */     }
/*  50:    */   }
/*  51:    */   
/*  52:    */   public void specialShot_move(World world, int id, EntityTHShot shot)
/*  53:    */   {
/*  54: 55 */     switch (id)
/*  55:    */     {
/*  56:    */     case 3300: 
/*  57: 58 */       if (shot.getSpeed() <= 0.0D)
/*  58:    */       {
/*  59: 64 */         THShotLib.createSphereShot(shot.user, shot, shot.pos_Shot(), THShotLib.angle_Random(), 0.0F, 0.5D, 0.5D, 0.0D, gravity_Zero(), ShotData.shot(30, 0, THShotLib.SIZE[30], 10.0F, 0, 9, 3301), 44, 0.0D, this.rand.nextFloat() * 360.0F);
/*  60: 65 */         if (!world.isRemote)
/*  61:    */         {
/*  62: 67 */           world.createExplosion(shot, shot.posX, shot.posY, shot.posZ, 1.0F, false);
/*  63: 68 */           shot.setDead();
/*  64:    */         }
/*  65:    */       }
/*  66:    */       break;
/*  67:    */     case 3301: 
/*  68: 73 */       if (shot.isShotEndTime())
/*  69:    */       {
/*  70: 75 */         for (int i = 0; i < 2; i++) {
/*  71: 77 */           THShotLib.createShot(shot.user, shot, shot.pos_Shot(), THShotLib.angle_LimitRandom(shot.angle, 50.0F), 0.0F, 0.5D, 0.5D, 1.0D, gravity_Zero(), ShotData.shot(2, 0, THShotLib.SIZE[2], 7.0F, 0, 5, 3302));
/*  72:    */         }
/*  73: 79 */         if (!world.isRemote) {
/*  74: 81 */           shot.setDead();
/*  75:    */         }
/*  76:    */       }
/*  77:    */       break;
/*  78:    */     case 3302: 
/*  79: 86 */       if (shot.isShotEndTime())
/*  80:    */       {
/*  81: 88 */         for (int i = 0; i < 2; i++) {
/*  82: 90 */           THShotLib.createShot(shot.user, shot, shot.pos_Shot(), THShotLib.angle_LimitRandom(shot.angle, 50.0F), 0.0F, 0.5D, 0.5D, 1.0D, gravity_Zero(), ShotData.shot(0, 0, THShotLib.SIZE[0], 5.0F, 0, 4, 3303));
/*  83:    */         }
/*  84: 92 */         if (!world.isRemote) {
/*  85: 94 */           shot.setDead();
/*  86:    */         }
/*  87:    */       }
/*  88:    */       break;
/*  89:    */     case 3303: 
/*  90: 99 */       if (shot.isShotEndTime())
/*  91:    */       {
/*  92:101 */         for (int i = 0; i < 2; i++) {
/*  93:103 */           THShotLib.createShot(shot.user, shot, shot.pos_Shot(), THShotLib.angle_LimitRandom(shot.angle, 50.0F), 0.0F, 0.5D, 0.5D, 1.0D, gravity_Zero(), ShotData.shot(1, 0, THShotLib.SIZE[1], 3.0F, 0, 2, 0));
/*  94:    */         }
/*  95:105 */         if (!world.isRemote) {
/*  96:107 */           shot.setDead();
/*  97:    */         }
/*  98:    */       }
/*  99:    */       break;
/* 100:    */     }
/* 101:    */   }
/* 102:    */   
/* 103:    */   public double getJustTimeAcceleration(int time, double distance, double firstSpeed)
/* 104:    */   {
/* 105:125 */     return (distance - firstSpeed) / time;
/* 106:    */   }
/* 107:    */   
/* 108:    */   public boolean specialShot_hitBlock(World world, int id, EntityTHShot shot, MovingObjectPosition movingObjectPosition)
/* 109:    */   {
/* 110:131 */     switch (id)
/* 111:    */     {
/* 112:    */     case 3300: 
/* 113:136 */       for (int i = 0; i < 40; i++) {
/* 114:138 */         THShotLib.createShot(shot.user, shot, shot.pos_Shot(), THShotLib.angle_Random(), 0.0F, 0.5D, 0.5D, 1.0D, gravity_Zero(), ShotData.shot(30, 0, THShotLib.SIZE[30], 10.0F, 0, 6, 3301));
/* 115:    */       }
/* 116:140 */       if (!world.isRemote) {
/* 117:142 */         shot.setDead();
/* 118:    */       }
/* 119:145 */       return false;
/* 120:    */     }
/* 121:149 */     return false;
/* 122:    */   }
/* 123:    */   
/* 124:    */   public boolean specialShot_hitEntity(World world, int id, EntityTHShot shot, Entity entity_Hit)
/* 125:    */   {
/* 126:156 */     return false;
/* 127:    */   }
/* 128:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.spellcard.THSC_FujiyamaVolcano
 * JD-Core Version:    0.7.0.1
 */