/*   1:    */ package thKaguyaMod.entity.living;
/*   2:    */ 
/*   3:    */ import java.util.ArrayList;
/*   4:    */ import java.util.Random;
/*   5:    */ import net.minecraft.item.Item;
/*   6:    */ import net.minecraft.util.AxisAlignedBB;
/*   7:    */ import net.minecraft.util.MathHelper;
/*   8:    */ import net.minecraft.world.EnumDifficulty;
/*   9:    */ import net.minecraft.world.EnumSkyBlock;
/*  10:    */ import net.minecraft.world.World;
/*  11:    */ import thKaguyaMod.init.THKaguyaItems;
/*  12:    */ import thKaguyaMod.registry.DanmakuPatternRegistry;
/*  13:    */ 
/*  14:    */ public class EntityTHPhantom
/*  15:    */   extends EntityTHFairy
/*  16:    */ {
/*  17:    */   public EntityTHPhantom(World world)
/*  18:    */   {
/*  19: 19 */     super(world);
/*  20:    */     
/*  21: 21 */     setSize(1.0F, 1.0F);
/*  22: 22 */     this.lastTime = 0;
/*  23:    */     
/*  24: 24 */     this.experienceValue = 5;
/*  25:    */     
/*  26: 26 */     setForm((byte)this.rand.nextInt(8));
/*  27: 27 */     setPattern(this.rand.nextInt(DanmakuPatternRegistry.pattern.size()));
/*  28:    */     
/*  29: 29 */     this.lostTarget = 0;
/*  30: 30 */     setSpeed(0.18D);
/*  31: 31 */     setSpecies(92);
/*  32: 32 */     this.isFlyingMode = true;
/*  33:    */     
/*  34: 34 */     setAttackDistance(6.0D);
/*  35: 35 */     setDetectionDistance(30.0D);
/*  36: 36 */     setFlyingHeight(3);
/*  37:    */     
/*  38: 38 */     this.height = 1.0F;
/*  39:    */   }
/*  40:    */   
/*  41:    */   protected void onDeathUpdate()
/*  42:    */   {
/*  43: 44 */     if (!this.worldObj.isRemote) {
/*  44: 46 */       setDead();
/*  45:    */     }
/*  46:    */   }
/*  47:    */   
/*  48:    */   public void onUpdate()
/*  49:    */   {
/*  50: 55 */     if (getHealth() <= 0.0F)
/*  51:    */     {
/*  52: 57 */       this.motionX = 0.0D;
/*  53: 58 */       this.motionY = 0.0D;
/*  54: 59 */       this.motionZ = 0.0D;
/*  55:    */     }
/*  56: 62 */     if (this.ticksExisted <= this.lastTime) {
/*  57: 64 */       return;
/*  58:    */     }
/*  59: 68 */     super.onUpdate();
/*  60: 69 */     if (this.attackCounter > this.danmakuSpan) {
/*  61: 71 */       this.attackCounter = 0;
/*  62:    */     }
/*  63: 75 */     float f = getBrightness(1.0F);
/*  64: 77 */     if ((getBrightness(1.0F) > 0.5F) && (this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F) && (this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)))) {
/*  65: 79 */       setHealth(0.0F);
/*  66:    */     }
/*  67:    */   }
/*  68:    */   
/*  69:    */   protected boolean canFairyCall()
/*  70:    */   {
/*  71: 87 */     return false;
/*  72:    */   }
/*  73:    */   
/*  74:    */   protected double getFairyCallDistance()
/*  75:    */   {
/*  76: 94 */     return 0.0D;
/*  77:    */   }
/*  78:    */   
/*  79:    */   protected Item getDropItem()
/*  80:    */   {
/*  81:100 */     if (getForm() < 0) {
/*  82:102 */       return null;
/*  83:    */     }
/*  84:106 */     return THKaguyaItems.power_item;
/*  85:    */   }
/*  86:    */   
/*  87:    */   protected void dropFewItems(boolean hasBeenAttackedByPlayer, int lootingLevel)
/*  88:    */   {
/*  89:114 */     super.dropFewItems(hasBeenAttackedByPlayer, lootingLevel);
/*  90:116 */     if (getForm() < 0) {
/*  91:118 */       return;
/*  92:    */     }
/*  93:121 */     if (hasBeenAttackedByPlayer)
/*  94:    */     {
/*  95:124 */       dropShotItem(0, this.rand.nextInt(2) + lootingLevel, 3, 0, 0, 0, 0, 2);
/*  96:    */       
/*  97:126 */       dropPowerUpItem(this.rand.nextInt(5) + this.rand.nextInt(1 + lootingLevel));
/*  98:    */       
/*  99:128 */       dropPointItem(this.rand.nextInt(5) + this.rand.nextInt(1 + lootingLevel));
/* 100:    */       
/* 101:130 */       dropItem(THKaguyaItems.soulTorch, this.rand.nextInt(10) / 9 + this.rand.nextInt(1 + lootingLevel));
/* 102:    */     }
/* 103:    */   }
/* 104:    */   
/* 105:    */   public int getMaxSpawnedInChunk()
/* 106:    */   {
/* 107:138 */     return 10;
/* 108:    */   }
/* 109:    */   
/* 110:    */   protected boolean isValidLightLevel()
/* 111:    */   {
/* 112:146 */     int i = MathHelper.floor_double(this.posX);
/* 113:147 */     int j = MathHelper.floor_double(this.boundingBox.minY);
/* 114:148 */     int k = MathHelper.floor_double(this.posZ);
/* 115:150 */     if (this.worldObj.getSavedLightValue(EnumSkyBlock.Sky, i, j, k) > this.rand.nextInt(32)) {
/* 116:152 */       return false;
/* 117:    */     }
/* 118:156 */     int l = this.worldObj.getBlockLightValue(i, j, k);
/* 119:158 */     if (this.worldObj.isThundering())
/* 120:    */     {
/* 121:160 */       int i1 = this.worldObj.skylightSubtracted;
/* 122:161 */       this.worldObj.skylightSubtracted = 10;
/* 123:162 */       l = this.worldObj.getBlockLightValue(i, j, k);
/* 124:163 */       this.worldObj.skylightSubtracted = i1;
/* 125:    */     }
/* 126:166 */     return l <= this.rand.nextInt(8);
/* 127:    */   }
/* 128:    */   
/* 129:    */   public boolean getCanSpawnHere()
/* 130:    */   {
/* 131:175 */     return (this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL) && (isValidLightLevel());
/* 132:    */   }
/* 133:    */   
/* 134:    */   protected float getSoundVolume()
/* 135:    */   {
/* 136:182 */     return 0.0F;
/* 137:    */   }
/* 138:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.living.EntityTHPhantom
 * JD-Core Version:    0.7.0.1
 */