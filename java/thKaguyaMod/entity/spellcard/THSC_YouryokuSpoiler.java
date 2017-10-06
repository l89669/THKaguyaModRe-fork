/*   1:    */ package thKaguyaMod.entity.spellcard;
/*   2:    */ 
/*   3:    */ import java.util.Random;
/*   4:    */ import net.minecraft.entity.Entity;
/*   5:    */ import net.minecraft.entity.EntityLivingBase;
/*   6:    */ import net.minecraft.potion.PotionEffect;
/*   7:    */ import net.minecraft.util.MovingObjectPosition;
/*   8:    */ import net.minecraft.world.World;
/*   9:    */ import thKaguyaMod.THShotLib;
/*  10:    */ import thKaguyaMod.entity.shot.EntityTHShot;
/*  11:    */ import thKaguyaMod.entity.shot.ISpecialShot;
/*  12:    */ import thKaguyaMod.registry.SpecialShotRegistry;
/*  13:    */ 
/*  14:    */ public class THSC_YouryokuSpoiler
/*  15:    */   extends THSpellCard
/*  16:    */   implements ISpecialShot
/*  17:    */ {
/*  18:    */   public static final int SPECIAL_SPOILER01 = 1600;
/*  19:    */   public static final int SPECIAL_SPOILER02 = 1601;
/*  20:    */   
/*  21:    */   public THSC_YouryokuSpoiler()
/*  22:    */   {
/*  23: 23 */     setNeedLevel(2);
/*  24: 24 */     setRemoveTime(10);
/*  25: 25 */     setEndTime(80);
/*  26: 26 */     setOriginalUserName(41);
/*  27:    */     
/*  28: 28 */     SpecialShotRegistry.registerSpecialShot(THSC_YouryokuSpoiler.class, 1600);
/*  29: 29 */     SpecialShotRegistry.registerSpecialShot(THSC_YouryokuSpoiler.class, 1601);
/*  30:    */   }
/*  31:    */   
/*  32:    */   public void spellcard_main()
/*  33:    */   {
/*  34: 35 */     if (this.time < 20) {
/*  35: 37 */       if (this.target != null)
/*  36:    */       {
/*  37: 39 */         this.world.playSoundAtEntity(this.user, "portal.portal", 2.0F, 1.9F);
/*  38: 40 */         THShotLib.createCircleShot(this.user, this.user, pos(this.targetPosX, this.targetPosY, this.targetPosZ), getVecFromAngle(this.card.rotationYaw, this.card.rotationPitch), 2.0D, 0.2D, -0.1D, gravity_Zero(), shot(6, this.rand.nextInt(7), 0.3F, 3.0F, 6, 70, 1600), 7, 1.5D, this.time * 3.0F);
/*  39:    */       }
/*  40:    */     }
/*  41: 44 */     if ((this.time > 40) && (this.time < 60)) {
/*  42: 46 */       if (this.target != null)
/*  43:    */       {
/*  44: 48 */         this.world.playSoundAtEntity(this.user, "portal.portal", 2.0F, 1.9F);
/*  45: 49 */         THShotLib.createCircleShot(this.user, this.user, pos(this.targetPosX, this.targetPosY, this.targetPosZ), getVecFromAngle(this.card.rotationYaw, this.card.rotationPitch), 2.0D, 0.2D, -0.1D, gravity_Zero(), shot(6, this.rand.nextInt(7), 0.3F, 3.0F, 6, 70, 1600), 7, 1.5D, -this.time * 3.0F);
/*  46:    */       }
/*  47:    */     }
/*  48:    */   }
/*  49:    */   
/*  50:    */   public void specialShot_move(World world, int id, EntityTHShot shot)
/*  51:    */   {
/*  52: 57 */     switch (id)
/*  53:    */     {
/*  54:    */     case 1600: 
/*  55: 60 */       if (shot.ticksExisted >= 20) {
/*  56: 62 */         if (shot.user != null)
/*  57:    */         {
/*  58: 64 */           shot.shotLimitSpeed = 1.0D;
/*  59: 65 */           shot.shotAcceleration = 0.05D;
/*  60:    */           
/*  61: 67 */           double lengthToUserX = shot.user.posX - shot.posX;
/*  62: 68 */           double lengthToUserZ = shot.user.posZ - shot.posZ;
/*  63: 69 */           shot.rotationYaw = ((float)Math.atan2(lengthToUserX, lengthToUserZ) / 3.141593F * 180.0F);
/*  64: 70 */           shot.rotationPitch = ((float)Math.atan2(shot.user.posY + 1.0D - shot.posY, Math.sqrt(lengthToUserX * lengthToUserX + lengthToUserZ * lengthToUserZ)) / 3.141593F * 180.0F);
/*  65:    */           
/*  66: 72 */           shot.setVector();
/*  67: 74 */           if (shot.getDistanceToEntity(shot.user) < 1.5F) {
/*  68: 76 */             if (!world.isRemote) {
/*  69: 78 */               shot.setDead();
/*  70:    */             }
/*  71:    */           }
/*  72:    */         }
/*  73:    */       }
/*  74: 81 */       break;
/*  75:    */     case 1601: 
/*  76: 85 */       if (shot.ticksExisted >= 20) {
/*  77: 87 */         if (shot.user != null)
/*  78:    */         {
/*  79: 89 */           shot.shotLimitSpeed = 1.0D;
/*  80: 90 */           shot.shotAcceleration = 0.05D;
/*  81:    */           
/*  82: 92 */           double lengthToUserX = shot.user.posX - shot.posX;
/*  83: 93 */           double lengthToUserZ = shot.user.posZ - shot.posZ;
/*  84: 94 */           shot.rotationYaw = ((float)Math.atan2(lengthToUserX, lengthToUserZ) / 3.141593F * 180.0F);
/*  85: 95 */           shot.rotationPitch = ((float)Math.atan2(shot.user.posY + 1.0D - shot.posY, Math.sqrt(lengthToUserX * lengthToUserX + lengthToUserZ * lengthToUserZ)) / 3.141593F * 180.0F);
/*  86:    */           
/*  87: 97 */           shot.setVector();
/*  88:    */         }
/*  89:    */       }
/*  90: 98 */       break;
/*  91:    */     }
/*  92:    */   }
/*  93:    */   
/*  94:    */   public boolean specialShot_hitBlock(World world, int id, EntityTHShot shot, MovingObjectPosition movingObjectPosition)
/*  95:    */   {
/*  96:110 */     return false;
/*  97:    */   }
/*  98:    */   
/*  99:    */   public boolean specialShot_hitEntity(World world, int id, EntityTHShot shot, Entity entity_Hit)
/* 100:    */   {
/* 101:116 */     switch (id)
/* 102:    */     {
/* 103:    */     case 1600: 
/* 104:119 */       if ((entity_Hit instanceof EntityLivingBase))
/* 105:    */       {
/* 106:121 */         EntityLivingBase living = (EntityLivingBase)entity_Hit;
/* 107:122 */         if (!living.isEntityEqual(shot.user))
/* 108:    */         {
/* 109:124 */           living.addPotionEffect(new PotionEffect(20, 400, 1));
/* 110:125 */           living.addPotionEffect(new PotionEffect(18, 400, 1));
/* 111:126 */           shot.shotSpecial = 1601;
/* 112:127 */           return true;
/* 113:    */         }
/* 114:132 */         return false;
/* 115:    */       }
/* 116:    */     case 1601: 
/* 117:136 */       if ((shot.user != null) && (entity_Hit.isEntityEqual(shot.user)))
/* 118:    */       {
/* 119:138 */         EntityLivingBase living = (EntityLivingBase)entity_Hit;
/* 120:139 */         living.addPotionEffect(new PotionEffect(10, 400, 1));
/* 121:140 */         living.addPotionEffect(new PotionEffect(5, 400, 1));
/* 122:141 */         return false;
/* 123:    */       }
/* 124:145 */       return true;
/* 125:    */     }
/* 126:148 */     return false;
/* 127:    */   }
/* 128:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.spellcard.THSC_YouryokuSpoiler
 * JD-Core Version:    0.7.0.1
 */