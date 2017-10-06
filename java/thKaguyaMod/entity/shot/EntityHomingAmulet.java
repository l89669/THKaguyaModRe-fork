/*   1:    */ package thKaguyaMod.entity.shot;
/*   2:    */ 
/*   3:    */ import java.util.List;
/*   4:    */ import java.util.Random;
/*   5:    */ import net.minecraft.entity.DataWatcher;
/*   6:    */ import net.minecraft.entity.Entity;
/*   7:    */ import net.minecraft.entity.EntityLivingBase;
/*   8:    */ import net.minecraft.entity.passive.EntityAnimal;
/*   9:    */ import net.minecraft.entity.passive.EntityTameable;
/*  10:    */ import net.minecraft.entity.passive.EntityVillager;
/*  11:    */ import net.minecraft.util.AxisAlignedBB;
/*  12:    */ import net.minecraft.util.MovingObjectPosition;
/*  13:    */ import net.minecraft.util.Vec3;
/*  14:    */ import net.minecraft.world.World;
/*  15:    */ import thKaguyaMod.ShotData;
/*  16:    */ import thKaguyaMod.THShotLib;
/*  17:    */ 
/*  18:    */ public class EntityHomingAmulet
/*  19:    */   extends EntityTHShot
/*  20:    */ {
/*  21:    */   private String userPlayerName;
/*  22:    */   
/*  23:    */   public EntityHomingAmulet(World world)
/*  24:    */   {
/*  25: 27 */     super(world);
/*  26:    */   }
/*  27:    */   
/*  28:    */   public EntityHomingAmulet(World world, EntityLivingBase user, Entity source, Vec3 pos, Vec3 angle, float slope, Vec3 rotate, float rotationSpeed, int rotationEnd, double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity, int color, float size, float damage, int delay, int end, int special)
/*  29:    */   {
/*  30: 41 */     super(world, user, source, pos, angle, slope, rotate, rotationSpeed, rotationEnd, firstSpeed, limitSpeed, acceleration, gravity, color, size, damage, delay, end, special);
/*  31: 43 */     if ((user instanceof EntityTameable))
/*  32:    */     {
/*  33: 45 */       EntityTameable tameable = (EntityTameable)user;
/*  34: 46 */       setOwner(tameable.getOwner().getCommandSenderName());
/*  35:    */     }
/*  36:    */     else
/*  37:    */     {
/*  38: 50 */       setOwner("");
/*  39:    */     }
/*  40:    */   }
/*  41:    */   
/*  42:    */   protected void entityInit()
/*  43:    */   {
/*  44: 58 */     super.entityInit();
/*  45: 59 */     this.dataWatcher.addObject(21, "");
/*  46:    */   }
/*  47:    */   
/*  48:    */   public void specialMotion()
/*  49:    */   {
/*  50: 69 */     if ((this.ticksExisted == 16) && (getShotColor() == 3))
/*  51:    */     {
/*  52: 74 */       double vectorXG = 0.0D;double vectorYG = 0.0D;double vectorZG = 0.0D;
/*  53: 75 */       if (this.user != null)
/*  54:    */       {
/*  55: 77 */         vectorXG = -Math.sin(this.user.rotationYaw / 180.0F * 3.141593F) * Math.cos(this.user.rotationPitch / 180.0F * 3.141593F) * 0.09D;
/*  56: 78 */         vectorYG = -Math.sin(this.user.rotationPitch / 180.0F * 3.141593F) * 0.09D;
/*  57: 79 */         vectorZG = Math.cos(this.user.rotationYaw / 180.0F * 3.141593F) * Math.cos(this.user.rotationPitch / 180.0F * 3.141593F) * 0.09D;
/*  58: 80 */         ShotData shot = ShotData.shot(27, 1, 0.4F, 5.0F, 0, 60);
/*  59: 81 */         THShotLib.createSphereShot(this.user, this.user, THShotLib.pos_Entity(this), this.angle, 0.0F, THShotLib.rotate_Default(), 0.0F, 9999, 0.6D, 0.6D, 0.0D, THShotLib.gravity(vectorXG, vectorYG, vectorZG), shot, 32, 0.0D, this.rand.nextFloat() * 360.0F);
/*  60:    */       }
/*  61: 84 */       if (!this.worldObj.isRemote) {
/*  62: 86 */         setDead();
/*  63:    */       }
/*  64: 88 */       return;
/*  65:    */     }
/*  66: 91 */     if (getShotColor() >= 2) {
/*  67: 93 */       return;
/*  68:    */     }
/*  69: 97 */     Entity entity = null;
/*  70: 98 */     List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(24.0D, 24.0D, 24.0D));
/*  71:    */     
/*  72:100 */     EntityLivingBase nearEntity = null;
/*  73:101 */     double nearDistance = 999.89999999999998D;
/*  74:102 */     float nearAngle = 180.0F;
/*  75:103 */     double nearValue = nearDistance * THShotLib.halfAbsSin(nearAngle / 180.0F * 3.141593F);
/*  76:104 */     Vec3 shotVec = getShotVector();
/*  77:106 */     for (int j = 0; j < list.size(); j++)
/*  78:    */     {
/*  79:108 */       Entity entitys = (Entity)list.get(j);
/*  80:110 */       if (((entitys instanceof EntityLivingBase)) && (!(entitys instanceof EntityAnimal)) && (!(entitys instanceof EntityVillager)) && (entitys != this.source) && (entitys != this.user))
/*  81:    */       {
/*  82:119 */         EntityLivingBase entity1 = (EntityLivingBase)entitys;
/*  83:121 */         if (!entity1.isDead)
/*  84:    */         {
/*  85:127 */           Vec3 vec3d = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
/*  86:    */           
/*  87:129 */           Vec3 vec3d1 = Vec3.createVectorHelper(entity1.posX, entity1.posY + entity1.getEyeHeight(), entity1.posZ);
/*  88:    */           
/*  89:    */ 
/*  90:132 */           MovingObjectPosition movingObjectPosition = this.worldObj.func_147447_a(vec3d, vec3d1, false, true, false);
/*  91:133 */           vec3d = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
/*  92:134 */           vec3d1 = Vec3.createVectorHelper(entity1.posX, entity1.posY + entity1.getEyeHeight(), entity1.posZ);
/*  93:136 */           if ((movingObjectPosition == null) || (movingObjectPosition.entityHit != null))
/*  94:    */           {
/*  95:141 */             Vec3 targetVec = Vec3.createVectorHelper(entity1.posX - this.posX, entity1.posY + entity1.getEyeHeight() - this.posY, entity1.posZ - this.posZ);
/*  96:142 */             targetVec = THShotLib.getVectorNomalize(targetVec);
/*  97:143 */             float angleSpan = Math.abs(THShotLib.getVectorAndVectorAngle(shotVec, targetVec));
/*  98:144 */             double toEntity1Distance = getDistance(entity1.posX, entity1.posY + entity1.getEyeHeight(), entity1.posZ);
/*  99:145 */             double value = toEntity1Distance * THShotLib.halfAbsSin(angleSpan / 180.0F * 3.141593F);
/* 100:146 */             if (nearValue > value)
/* 101:    */             {
/* 102:151 */               nearEntity = entity1;
/* 103:152 */               nearAngle = angleSpan;
/* 104:153 */               nearValue = value;
/* 105:154 */               nearDistance = toEntity1Distance;
/* 106:    */             }
/* 107:    */           }
/* 108:    */         }
/* 109:    */       }
/* 110:    */     }
/* 111:166 */     if (nearEntity != null)
/* 112:    */     {
/* 113:168 */       float homingLevel = 4.0F;
/* 114:169 */       Vec3 targetVec = Vec3.createVectorHelper(nearEntity.posX - this.posX, nearEntity.posY + nearEntity.getEyeHeight() - this.posY, nearEntity.posZ - this.posZ);
/* 115:170 */       targetVec = THShotLib.getVectorNomalize(targetVec);
/* 116:171 */       Vec3 rotate = THShotLib.getOuterProduct(getShotVector(), targetVec);
/* 117:172 */       float rotateAngle = THShotLib.getVectorAndVectorAngle(shotVec, targetVec);
/* 118:173 */       if (rotateAngle > homingLevel) {
/* 119:175 */         rotateAngle = homingLevel;
/* 120:177 */       } else if (rotateAngle < -homingLevel) {
/* 121:179 */         rotateAngle = -homingLevel;
/* 122:    */       }
/* 123:181 */       Vec3 newVec = THShotLib.getVectorFromRotation(rotate, this.angle, rotateAngle);
/* 124:182 */       this.angle = newVec;
/* 125:183 */       if (!this.worldObj.isRemote)
/* 126:    */       {
/* 127:185 */         updateMotion();
/* 128:186 */         updateAngle();
/* 129:    */       }
/* 130:    */     }
/* 131:    */   }
/* 132:    */   
/* 133:    */   public String getOwnerName()
/* 134:    */   {
/* 135:193 */     return this.dataWatcher.getWatchableObjectString(21);
/* 136:    */   }
/* 137:    */   
/* 138:    */   public void setOwner(String str)
/* 139:    */   {
/* 140:198 */     this.dataWatcher.updateObject(21, str);
/* 141:    */   }
/* 142:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.shot.EntityHomingAmulet
 * JD-Core Version:    0.7.0.1
 */