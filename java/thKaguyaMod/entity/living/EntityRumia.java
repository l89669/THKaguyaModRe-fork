/*   1:    */ package thKaguyaMod.entity.living;
/*   2:    */ 
/*   3:    */ import java.util.Random;
/*   4:    */ import net.minecraft.entity.EntityLivingBase;
/*   5:    */ import net.minecraft.entity.player.EntityPlayer;
/*   6:    */ import net.minecraft.util.AxisAlignedBB;
/*   7:    */ import net.minecraft.util.DamageSource;
/*   8:    */ import net.minecraft.util.MathHelper;
/*   9:    */ import net.minecraft.util.Vec3;
/*  10:    */ import net.minecraft.world.EnumDifficulty;
/*  11:    */ import net.minecraft.world.EnumSkyBlock;
/*  12:    */ import net.minecraft.world.World;
/*  13:    */ import thKaguyaMod.ShotData;
/*  14:    */ import thKaguyaMod.THShotLib;
/*  15:    */ 
/*  16:    */ public class EntityRumia
/*  17:    */   extends EntityDanmakuMob
/*  18:    */ {
/*  19:    */   public EntityRumia(World world)
/*  20:    */   {
/*  21: 22 */     super(world);
/*  22:    */     
/*  23: 24 */     setSize(1.0F, 1.8F);
/*  24:    */     
/*  25: 26 */     this.experienceValue = 160;
/*  26: 27 */     this.ignoreFrustumCheck = true;
/*  27:    */     
/*  28: 29 */     setDanmakuPattern(1);
/*  29: 30 */     setMaxHP(36.0F);
/*  30: 31 */     setHealth(36.0F);
/*  31: 32 */     setDanmakuMobName("Rumia");
/*  32: 33 */     setSpecies(128);
/*  33:    */     
/*  34: 35 */     setAttackDistance(14.0D);
/*  35: 36 */     setDetectionDistance(12.0D);
/*  36: 37 */     setFlyingHeight(2);
/*  37: 38 */     this.isFlyingMode = true;
/*  38:    */   }
/*  39:    */   
/*  40:    */   public int getUsingSpellCardNo()
/*  41:    */   {
/*  42: 47 */     switch (getDanmakuPattern())
/*  43:    */     {
/*  44:    */     case 101: 
/*  45: 50 */       return 28;
/*  46:    */     }
/*  47: 52 */     return -1;
/*  48:    */   }
/*  49:    */   
/*  50:    */   protected void onDeathUpdate()
/*  51:    */   {
/*  52: 60 */     if (this.ticksExisted <= this.lastTime) {
/*  53: 62 */       return;
/*  54:    */     }
/*  55: 65 */     switch (getDanmakuPattern())
/*  56:    */     {
/*  57:    */     case 1: 
/*  58: 68 */       moveDanmakuAttack(101, 40, 44.0F, 60);
/*  59: 69 */       setAttackDistance(14.0D);
/*  60: 70 */       setDetectionDistance(30.0D);
/*  61: 71 */       setFlyingHeight(2);
/*  62: 72 */       break;
/*  63:    */     case 101: 
/*  64: 74 */       moveDanmakuAttack(127, 30, 0.0F, 60);
/*  65: 75 */       break;
/*  66:    */     default: 
/*  67: 77 */       if (this.deathTime % 9 == 0) {
/*  68: 79 */         THShotLib.explosionEffect(this.worldObj, this.posX, this.posY, this.posZ, 1.0F + this.deathTime * 0.1F);
/*  69:    */       }
/*  70: 81 */       super.onDeathUpdate();
/*  71:    */     }
/*  72:    */   }
/*  73:    */   
/*  74:    */   public void danmakuPattern(int level)
/*  75:    */   {
/*  76: 89 */     switch (getDanmakuPattern())
/*  77:    */     {
/*  78:    */     case 1: 
/*  79: 92 */       Vec3 look = getLookVec();
/*  80: 93 */       if (this.attackCounter == 1)
/*  81:    */       {
/*  82: 95 */         THShotLib.playShotSound(this);
/*  83: 96 */         for (int i = 0; i < 1 + level; i++) {
/*  84: 98 */           THShotLib.createCircleShot(this, pos(), angle(this.rotationYaw, this.rotationPitch), 2.0D, 0.4D, -0.3D, ShotData.shot(4, 1, 10 + i), 12 + 3 * level);
/*  85:    */         }
/*  86:    */       }
/*  87:102 */       else if (this.attackCounter == 14)
/*  88:    */       {
/*  89:104 */         THShotLib.playShotSound(this);
/*  90:105 */         for (int i = 0; i < 1 + level; i++) {
/*  91:107 */           THShotLib.createCircleShot(this, pos(), angle(this.rotationYaw, this.rotationPitch), 2.0D, 0.4D, -0.3D, ShotData.shot(4, 0, 10 + i), 12 + 3 * level);
/*  92:    */         }
/*  93:    */       }
/*  94:111 */       else if (this.attackCounter == 24)
/*  95:    */       {
/*  96:113 */         moveRight(0.3D, 20);
/*  97:    */       }
/*  98:115 */       else if ((this.attackCounter >= 62) && (this.attackCounter < 67))
/*  99:    */       {
/* 100:117 */         THShotLib.playShotSound(this);
/* 101:118 */         int i = this.attackCounter - 62;
/* 102:119 */         THShotLib.createCircleShot(this, pos(), angle(this.rotationYaw + i * 5.0F, this.rotationPitch), 0.2D + level * 0.1D, ShotData.shot(1, this.attackCounter % 7), 5 + level * 2);
/* 103:    */       }
/* 104:121 */       else if (this.attackCounter == 67)
/* 105:    */       {
/* 106:123 */         moveLeft(0.3D, 20);
/* 107:    */       }
/* 108:125 */       if (this.attackCounter >= 127) {
/* 109:128 */         this.attackCounter = 0;
/* 110:    */       }
/* 111:    */       break;
/* 112:    */     case 101: 
/* 113:132 */       if (this.attackCounter == 1)
/* 114:    */       {
/* 115:134 */         useSpellCard(getUsingSpellCardNo());
/* 116:    */       }
/* 117:136 */       else if ((this.attackCounter < 60) && (this.attackCounter % 9 == 0))
/* 118:    */       {
/* 119:138 */         THShotLib.createCircleShot(this, pos(), angle(this.rotationYaw, this.rotationPitch), 0.1D + level * 0.1D, ShotData.shot(1, 7), 15 + level * 3);
/* 120:139 */         THShotLib.playShotSound(this);
/* 121:    */       }
/* 122:141 */       else if (this.attackCounter >= 120)
/* 123:    */       {
/* 124:143 */         this.attackCounter = 0;
/* 125:    */       }
/* 126:    */       break;
/* 127:    */     }
/* 128:    */   }
/* 129:    */   
/* 130:    */   protected void dropFewItems(boolean hasBeenAttackedByPlayer, int lootingLevel)
/* 131:    */   {
/* 132:155 */     super.dropFewItems(hasBeenAttackedByPlayer, lootingLevel);
/* 133:157 */     if (hasBeenAttackedByPlayer)
/* 134:    */     {
/* 135:160 */       dropShotItem(0, 8 + this.rand.nextInt(2) + lootingLevel, 5, 32, 1, 0, 0, 2);
/* 136:    */       
/* 137:162 */       dropPowerUpItem(8 + this.rand.nextInt(5) + this.rand.nextInt(1 + lootingLevel));
/* 138:    */       
/* 139:164 */       dropPointItem(8 + this.rand.nextInt(5) + this.rand.nextInt(1 + lootingLevel));
/* 140:    */     }
/* 141:    */   }
/* 142:    */   
/* 143:    */   public boolean attackEntityFrom(DamageSource damageSource, float damage)
/* 144:    */   {
/* 145:173 */     if ((super.attackEntityFrom(damageSource, applyPotionDamageCalculations(damageSource, damage))) && ((damageSource.getEntity() instanceof EntityLivingBase)))
/* 146:    */     {
/* 147:175 */       EntityLivingBase entity = (EntityLivingBase)damageSource.getEntity();
/* 148:    */       
/* 149:    */ 
/* 150:178 */       this.motionX *= 0.01D;
/* 151:179 */       this.motionY *= 0.01D;
/* 152:180 */       this.motionZ *= 0.01D;
/* 153:181 */       if ((this.riddenByEntity != entity) && (this.ridingEntity != entity))
/* 154:    */       {
/* 155:183 */         if ((entity instanceof EntityPlayer)) {
/* 156:185 */           this.entityToAttack = entity;
/* 157:    */         }
/* 158:188 */         return true;
/* 159:    */       }
/* 160:192 */       return true;
/* 161:    */     }
/* 162:197 */     return false;
/* 163:    */   }
/* 164:    */   
/* 165:    */   public int getMaxSpawnedInChunk()
/* 166:    */   {
/* 167:204 */     return 1;
/* 168:    */   }
/* 169:    */   
/* 170:    */   protected boolean isValidLightLevel()
/* 171:    */   {
/* 172:212 */     int i = MathHelper.floor_double(this.posX);
/* 173:213 */     int j = MathHelper.floor_double(this.boundingBox.minY);
/* 174:214 */     int k = MathHelper.floor_double(this.posZ);
/* 175:216 */     if (this.worldObj.getSavedLightValue(EnumSkyBlock.Sky, i, j, k) > this.rand.nextInt(32)) {
/* 176:218 */       return false;
/* 177:    */     }
/* 178:222 */     int l = this.worldObj.getBlockLightValue(i, j, k);
/* 179:224 */     if (this.worldObj.isThundering())
/* 180:    */     {
/* 181:226 */       int i1 = this.worldObj.skylightSubtracted;
/* 182:227 */       this.worldObj.skylightSubtracted = 10;
/* 183:228 */       l = this.worldObj.getBlockLightValue(i, j, k);
/* 184:229 */       this.worldObj.skylightSubtracted = i1;
/* 185:    */     }
/* 186:232 */     return l <= this.rand.nextInt(8);
/* 187:    */   }
/* 188:    */   
/* 189:    */   public boolean getCanSpawnHere()
/* 190:    */   {
/* 191:241 */     return (this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL) && (isValidLightLevel());
/* 192:    */   }
/* 193:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.living.EntityRumia
 * JD-Core Version:    0.7.0.1
 */