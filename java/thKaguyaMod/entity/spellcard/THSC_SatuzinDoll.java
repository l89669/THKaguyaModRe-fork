/*   1:    */ package thKaguyaMod.entity.spellcard;
/*   2:    */ 
/*   3:    */ import java.util.List;
/*   4:    */ import java.util.Random;
/*   5:    */ import net.minecraft.entity.Entity;
/*   6:    */ import net.minecraft.entity.EntityLivingBase;
/*   7:    */ import net.minecraft.util.AxisAlignedBB;
/*   8:    */ import net.minecraft.util.Vec3;
/*   9:    */ import net.minecraft.world.World;
/*  10:    */ import thKaguyaMod.THShotLib;
/*  11:    */ import thKaguyaMod.entity.item.EntitySakuyaWatch;
/*  12:    */ import thKaguyaMod.entity.shot.EntityTHShot;
/*  13:    */ 
/*  14:    */ public class THSC_SatuzinDoll
/*  15:    */   extends THSpellCard
/*  16:    */ {
/*  17:    */   public THSC_SatuzinDoll()
/*  18:    */   {
/*  19: 20 */     setNeedLevel(4);
/*  20: 21 */     setRemoveTime(30);
/*  21: 22 */     setEndTime(110);
/*  22: 23 */     setOriginalUserName(8);
/*  23:    */   }
/*  24:    */   
/*  25:    */   public void spellcard_main()
/*  26:    */   {
/*  27: 29 */     if (this.time < 5) {
/*  28: 31 */       return;
/*  29:    */     }
/*  30: 34 */     if (this.time < 15) {
/*  31: 36 */       THShotLib.createSphereShot(this.user, this.user, pos_User(), this.user.getLookVec(), 0.0F, rotate_Default(), 0.0F, 9999, 0.4D, 0.4D, 0.0D, 
/*  32: 37 */         gravity_Zero(), shot(28, 1, THShotLib.SIZE[28], 12.0F, 3, 80), 32, 1.0D, this.time * 6.0F);
/*  33:    */     }
/*  34: 39 */     if ((this.time > 14) && (this.time < 24) && (this.time % 4 == 0))
/*  35:    */     {
/*  36: 41 */       int way = 9;
/*  37: 42 */       float angleXZ = this.user.rotationYaw;
/*  38: 43 */       float angleY = this.user.rotationPitch;
/*  39:    */       
/*  40:    */ 
/*  41: 46 */       THShotLib.createCircleShot(this.user, this.user, pos_User(), getVecFromAngle(angleXZ, angleY), 0.4D, 0.4D, 0.0D, 
/*  42: 47 */         gravity_Zero(), shot(28, 0, THShotLib.SIZE[28], 12.0F, 3, 80), way, 1.0D, 0.0F);
/*  43: 48 */       THShotLib.createCircleShot(this.user, this.user, pos_User(), getVecFromAngle(angleXZ + 5.0F, angleY), 0.4D, 0.4D, 0.0D, 
/*  44: 49 */         gravity_Zero(), shot(28, 0, THShotLib.SIZE[28], 12.0F, 3, 80), way, 1.0D, 0.0F);
/*  45:    */     }
/*  46: 51 */     if (this.time == 36)
/*  47:    */     {
/*  48: 53 */       boolean flag = true;
/*  49: 54 */       List list = this.world.getEntitiesWithinAABBExcludingEntity(this.card, this.card.boundingBox.addCoord(0.0D, 0.0D, 0.0D).expand(20.0D, 20.0D, 20.0D));
/*  50: 55 */       for (int k = 0; k < list.size(); k++)
/*  51:    */       {
/*  52: 57 */         Entity entity = (Entity)list.get(k);
/*  53: 58 */         if ((entity instanceof EntitySakuyaWatch))
/*  54:    */         {
/*  55: 60 */           EntitySakuyaWatch entityPrivateSquare = (EntitySakuyaWatch)entity;
/*  56: 61 */           if (entityPrivateSquare.userEntity == this.user) {
/*  57: 63 */             flag = false;
/*  58:    */           }
/*  59:    */         }
/*  60:    */       }
/*  61: 67 */       if (flag)
/*  62:    */       {
/*  63: 69 */         EntitySakuyaWatch entityPrivateSquare = new EntitySakuyaWatch(this.world, this.user, 1);
/*  64: 70 */         if (!this.world.isRemote) {
/*  65: 72 */           this.world.spawnEntityInWorld(entityPrivateSquare);
/*  66:    */         }
/*  67:    */       }
/*  68:    */     }
/*  69:    */   }
/*  70:    */   
/*  71:    */   public void specialProcessInTimeStop()
/*  72:    */   {
/*  73: 85 */     super.specialProcessInTimeStop();
/*  74: 87 */     if ((this.time > 44) && (this.time < 68) && (this.time % 8 == 3))
/*  75:    */     {
/*  76: 89 */       List list = this.world.getEntitiesWithinAABBExcludingEntity(this.card, this.card.boundingBox.addCoord(0.0D, 0.0D, 0.0D).expand(20.0D, 20.0D, 20.0D));
/*  77: 90 */       for (int k = 0; k < list.size(); k++)
/*  78:    */       {
/*  79: 92 */         Entity entity = (Entity)list.get(k);
/*  80: 93 */         if ((entity instanceof EntityTHShot))
/*  81:    */         {
/*  82: 95 */           EntityTHShot entityTHShot = (EntityTHShot)entity;
/*  83: 96 */           if ((entityTHShot.getShotForm() == 28) && ((entityTHShot.getShotColor() == 1) || (entityTHShot.getShotColor() == 0)) && (entityTHShot.user.equals(this.user)) && (this.rand.nextInt(100) < 25))
/*  84:    */           {
/*  85:    */             Vec3 angle;
/*  87:101 */             if (this.target != null) {
/*  88:103 */               angle = THShotLib.angle_ToPos(THShotLib.pos_Entity(entityTHShot), pos_Target());
/*  89:    */             } else {
/*  90:107 */               angle = THShotLib.angle_Random();
/*  91:    */             }
/*  92:109 */             THShotLib.createShot(this.user, this.user, pos(entityTHShot.posX, entityTHShot.posY - (this.user.getEyeHeight() - 0.25D), entityTHShot.posZ), angle, 0.0F, 0.2D + this.rand
/*  93:110 */               .nextDouble() * 0.5D, 0.7D, 0.02D, gravity_Zero(), 
/*  94:111 */               shot(28, 2, THShotLib.SIZE[28], 12.0F, 0, 80));
/*  95:112 */             if (!this.world.isRemote) {
/*  96:114 */               entityTHShot.setDead();
/*  97:    */             }
/*  98:    */           }
/*  99:    */         }
/* 100:    */       }
/* 101:    */     }
/* 102:    */   }
/* 103:    */   
/* 104:    */   public boolean canMoveInTimeStop()
/* 105:    */   {
/* 106:131 */     return true;
/* 107:    */   }
/* 108:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.spellcard.THSC_SatuzinDoll
 * JD-Core Version:    0.7.0.1
 */