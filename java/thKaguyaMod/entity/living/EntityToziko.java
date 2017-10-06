/*   1:    */ package thKaguyaMod.entity.living;
/*   2:    */ 
/*   3:    */ import java.util.Random;
/*   4:    */ import net.minecraft.util.AxisAlignedBB;
/*   5:    */ import net.minecraft.util.MathHelper;
/*   6:    */ import net.minecraft.util.Vec3;
/*   7:    */ import net.minecraft.world.EnumDifficulty;
/*   8:    */ import net.minecraft.world.EnumSkyBlock;
/*   9:    */ import net.minecraft.world.World;
/*  10:    */ import thKaguyaMod.ShotData;
/*  11:    */ import thKaguyaMod.THShotLib;
/*  12:    */ 
/*  13:    */ public class EntityToziko
/*  14:    */   extends EntityDanmakuMob
/*  15:    */ {
/*  16:    */   public EntityToziko(World world)
/*  17:    */   {
/*  18: 21 */     super(world);
/*  19:    */     
/*  20: 23 */     setSize(1.0F, 1.8F);
/*  21:    */     
/*  22: 25 */     this.experienceValue = 160;
/*  23:    */     
/*  24: 27 */     setDanmakuPattern(1);
/*  25: 28 */     setMaxHP(40.0F);
/*  26: 29 */     setHealth(40.0F);
/*  27: 30 */     setDanmakuMobName("Soga no Toziko");
/*  28: 31 */     setSpecies(94);
/*  29:    */     
/*  30: 33 */     setAttackDistance(14.0D);
/*  31: 34 */     setDetectionDistance(12.0D);
/*  32: 35 */     setFlyingHeight(2);
/*  33: 36 */     this.isFlyingMode = true;
/*  34:    */   }
/*  35:    */   
/*  36:    */   public int getUsingSpellCardNo()
/*  37:    */   {
/*  38: 46 */     switch (getDanmakuPattern())
/*  39:    */     {
/*  40:    */     case 101: 
/*  41: 49 */       return 40;
/*  42:    */     }
/*  43: 51 */     return -1;
/*  44:    */   }
/*  45:    */   
/*  46:    */   protected void onDeathUpdate()
/*  47:    */   {
/*  48: 59 */     if (this.ticksExisted <= this.lastTime) {
/*  49: 61 */       return;
/*  50:    */     }
/*  51: 64 */     switch (getDanmakuPattern())
/*  52:    */     {
/*  53:    */     case 1: 
/*  54: 67 */       moveDanmakuAttack(101, 40, 44.0F, 60);
/*  55: 68 */       setAttackDistance(14.0D);
/*  56: 69 */       setDetectionDistance(30.0D);
/*  57: 70 */       setFlyingHeight(2);
/*  58: 71 */       break;
/*  59:    */     case 101: 
/*  60: 73 */       moveDanmakuAttack(127, 30, 0.0F, 60);
/*  61: 74 */       break;
/*  62:    */     default: 
/*  63: 76 */       if (this.deathTime % 9 == 0) {
/*  64: 78 */         THShotLib.explosionEffect(this.worldObj, this.posX, this.posY, this.posZ, 1.0F + this.deathTime * 0.1F);
/*  65:    */       }
/*  66: 80 */       super.onDeathUpdate();
/*  67:    */     }
/*  68:    */   }
/*  69:    */   
/*  70:    */   public void danmakuPattern(int level)
/*  71:    */   {
/*  72: 88 */     switch (getDanmakuPattern())
/*  73:    */     {
/*  74:    */     case 1: 
/*  75: 91 */       Vec3 look = getLookVec();
/*  76: 92 */       if (this.attackCounter % 80 < 20)
/*  77:    */       {
/*  78: 94 */         THShotLib.playShotSound(this);
/*  79: 95 */         for (int i = 0; i < 1 + level; i++) {
/*  80: 98 */           THShotLib.createShot(this, this, THShotLib.pos_Random(pos(), this.attackCounter * 0.2D), THShotLib.angle_Random(), 0.0F, 0.3D, 0.0D, -0.03D, gravity_Zero(), ShotData.shot(18, 1, 1, 20, 4020));
/*  81:    */         }
/*  82:    */       }
/*  83:101 */       else if (this.attackCounter == 24)
/*  84:    */       {
/*  85:103 */         if (this.rand.nextInt(2) == 0) {
/*  86:105 */           moveRight(this.rand.nextDouble() * 0.3D + 0.2D, 16);
/*  87:    */         } else {
/*  88:109 */           moveLeft(this.rand.nextDouble() * 0.3D + 0.2D, 16);
/*  89:    */         }
/*  90:    */       }
/*  91:112 */       if (this.attackCounter >= 80) {
/*  92:115 */         this.attackCounter = 0;
/*  93:    */       }
/*  94:    */       break;
/*  95:    */     case 101: 
/*  96:119 */       if (this.attackCounter == 1) {
/*  97:121 */         useSpellCard(getUsingSpellCardNo());
/*  98:123 */       } else if (this.attackCounter >= 200) {
/*  99:125 */         this.attackCounter = 0;
/* 100:    */       }
/* 101:    */       break;
/* 102:    */     }
/* 103:    */   }
/* 104:    */   
/* 105:    */   protected void dropFewItems(boolean hasBeenAttackedByPlayer, int lootingLevel)
/* 106:    */   {
/* 107:137 */     super.dropFewItems(hasBeenAttackedByPlayer, lootingLevel);
/* 108:139 */     if (hasBeenAttackedByPlayer)
/* 109:    */     {
/* 110:142 */       dropShotItem(18, 8 + this.rand.nextInt(2) + lootingLevel, 5, 32, 3, 0, 4000, 2);
/* 111:    */       
/* 112:144 */       dropPowerUpItem(8 + this.rand.nextInt(5) + this.rand.nextInt(1 + lootingLevel));
/* 113:    */       
/* 114:146 */       dropPointItem(8 + this.rand.nextInt(5) + this.rand.nextInt(1 + lootingLevel));
/* 115:    */     }
/* 116:    */   }
/* 117:    */   
/* 118:    */   protected boolean isValidLightLevel()
/* 119:    */   {
/* 120:155 */     int i = MathHelper.floor_double(this.posX);
/* 121:156 */     int j = MathHelper.floor_double(this.boundingBox.minY);
/* 122:157 */     int k = MathHelper.floor_double(this.posZ);
/* 123:159 */     if (this.worldObj.getSavedLightValue(EnumSkyBlock.Sky, i, j, k) > this.rand.nextInt(32)) {
/* 124:161 */       return false;
/* 125:    */     }
/* 126:165 */     int l = this.worldObj.getBlockLightValue(i, j, k);
/* 127:167 */     if (this.worldObj.isThundering())
/* 128:    */     {
/* 129:169 */       int i1 = this.worldObj.skylightSubtracted;
/* 130:170 */       this.worldObj.skylightSubtracted = 10;
/* 131:171 */       l = this.worldObj.getBlockLightValue(i, j, k);
/* 132:172 */       this.worldObj.skylightSubtracted = i1;
/* 133:    */     }
/* 134:175 */     return l <= this.rand.nextInt(8);
/* 135:    */   }
/* 136:    */   
/* 137:    */   public boolean getCanSpawnHere()
/* 138:    */   {
/* 139:184 */     return (this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL) && (isValidLightLevel()) && (this.worldObj.isThundering());
/* 140:    */   }
/* 141:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.living.EntityToziko
 * JD-Core Version:    0.7.0.1
 */