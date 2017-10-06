/*   1:    */ package thKaguyaMod.entity.living;
/*   2:    */ 
/*   3:    */ import net.minecraft.entity.item.EntityItem;
/*   4:    */ import net.minecraft.util.DamageSource;
/*   5:    */ import net.minecraft.util.Vec3;
/*   6:    */ import net.minecraft.world.World;
/*   7:    */ import thKaguyaMod.THShotLib;
/*   8:    */ import thKaguyaMod.init.THKaguyaItems;
/*   9:    */ 
/*  10:    */ public class EntityReimu
/*  11:    */   extends EntityDanmakuMob
/*  12:    */ {
/*  13:    */   public EntityReimu(World world)
/*  14:    */   {
/*  15: 17 */     super(world);
/*  16:    */     
/*  17: 19 */     setSize(1.0F, 1.8F);
/*  18:    */     
/*  19: 21 */     this.experienceValue = 250;
/*  20:    */     
/*  21: 23 */     setDanmakuMobName("Reimu Hakurei");
/*  22: 24 */     setSpecies(0);
/*  23:    */     
/*  24:    */ 
/*  25: 27 */     setDanmakuPattern(101);
/*  26: 28 */     setMaxHP(74.0F);
/*  27: 29 */     setHealth(74.0F);
/*  28: 30 */     setSpeed(0.4000000059604645D);
/*  29: 31 */     setAttackDistance(14.0D);
/*  30: 32 */     setDetectionDistance(0.0D);
/*  31: 33 */     setFlyingHeight(4);
/*  32: 34 */     this.isFlyingMode = false;
/*  33:    */     
/*  34: 36 */     this.isSpellCardMode = false;
/*  35: 37 */     this.attackInterval = 0;
/*  36:    */   }
/*  37:    */   
/*  38:    */   public int getUsingSpellCardNo()
/*  39:    */   {
/*  40: 46 */     switch (getDanmakuPattern())
/*  41:    */     {
/*  42:    */     case 101: 
/*  43: 49 */       return 0;
/*  44:    */     }
/*  45: 51 */     return -1;
/*  46:    */   }
/*  47:    */   
/*  48:    */   protected void onDeathUpdate()
/*  49:    */   {
/*  50: 59 */     switch (getDanmakuPattern())
/*  51:    */     {
/*  52:    */     case 1: 
/*  53: 62 */       setFlyingHeight(2);
/*  54: 63 */       moveDanmakuAttack(101, 40, 60.0F, 160);
/*  55: 64 */       break;
/*  56:    */     case 101: 
/*  57: 66 */       moveDanmakuAttack(127, 90, 40.0F, 160);
/*  58: 67 */       break;
/*  59:    */     default: 
/*  60: 69 */       if (this.deathTime % 6 == 0) {
/*  61: 71 */         THShotLib.explosionEffect(this.worldObj, this.posX, this.posY, this.posZ, 1.0F + this.deathTime * 0.1F);
/*  62:    */       }
/*  63: 73 */       super.onDeathUpdate();
/*  64:    */     }
/*  65:    */   }
/*  66:    */   
/*  67:    */   public void onUpdate()
/*  68:    */   {
/*  69: 83 */     super.onUpdate();
/*  70:    */   }
/*  71:    */   
/*  72:    */   public void danmakuPattern(int level)
/*  73:    */   {
/*  74: 94 */     Vec3 look = getLookVec();
/*  75: 95 */     switch (getDanmakuPattern())
/*  76:    */     {
/*  77:    */     case 1: 
/*  78:    */       break;
/*  79:    */     case 101: 
/*  80:101 */       if (this.attackCounter == 1) {
/*  81:103 */         useSpellCard(0);
/*  82:105 */       } else if (this.attackCounter >= 140) {
/*  83:107 */         this.attackCounter = 0;
/*  84:    */       }
/*  85:    */       break;
/*  86:    */     }
/*  87:    */   }
/*  88:    */   
/*  89:    */   protected boolean canFairyCall()
/*  90:    */   {
/*  91:149 */     return false;
/*  92:    */   }
/*  93:    */   
/*  94:    */   protected float applyPotionDamageCalculations(DamageSource damageSource, float damage)
/*  95:    */   {
/*  96:158 */     damage = super.applyPotionDamageCalculations(damageSource, damage);
/*  97:160 */     if (isEntityInvulnerable()) {
/*  98:162 */       damage = (float)(damage * 0.05D);
/*  99:    */     }
/* 100:165 */     return damage;
/* 101:    */   }
/* 102:    */   
/* 103:    */   public boolean isEntityInvulnerable()
/* 104:    */   {
/* 105:173 */     if (getDanmakuPattern() == 0) {
/* 106:175 */       return true;
/* 107:    */     }
/* 108:177 */     return false;
/* 109:    */   }
/* 110:    */   
/* 111:    */   protected void updateAITasks()
/* 112:    */   {
/* 113:183 */     super.updateAITasks();
/* 114:    */   }
/* 115:    */   
/* 116:    */   protected void dropFewItems(boolean par1, int par2)
/* 117:    */   {
/* 118:190 */     super.dropFewItems(par1, par2);
/* 119:192 */     if (isSpellCardAttack())
/* 120:    */     {
/* 121:194 */       int j = 12;
/* 122:199 */       for (int k = 0; k < j; k++)
/* 123:    */       {
/* 124:201 */         EntityItem item = dropItem(THKaguyaItems.power_item, 1);
/* 125:202 */         item.rotationYaw = (k * 30.0F);
/* 126:203 */         item.rotationPitch = -60.0F;
/* 127:204 */         Vec3 vec3 = THShotLib.getVecFromAngle(item.rotationYaw, item.rotationPitch, 0.5D);
/* 128:205 */         item.setPosition(item.posX + vec3.xCoord * 2.0D, item.posY, item.posZ + vec3.zCoord * 2.0D);
/* 129:    */         
/* 130:207 */         item.motionX = vec3.xCoord;
/* 131:208 */         item.motionY = vec3.yCoord;
/* 132:209 */         item.motionZ = vec3.zCoord;
/* 133:    */       }
/* 134:212 */       for (int k = 0; k < j; k++)
/* 135:    */       {
/* 136:214 */         EntityItem item = dropItem(THKaguyaItems.point_item, 1);
/* 137:215 */         item.rotationYaw = (k * 30.0F + 15.0F);
/* 138:216 */         item.rotationPitch = -60.0F;
/* 139:217 */         Vec3 vec3 = THShotLib.getVecFromAngle(item.rotationYaw, item.rotationPitch, 0.300000011920929D);
/* 140:218 */         item.setPosition(item.posX + vec3.xCoord * 2.0D, item.posY, item.posZ + vec3.zCoord * 2.0D);
/* 141:    */         
/* 142:220 */         item.motionX = vec3.xCoord;
/* 143:221 */         item.motionY = vec3.yCoord;
/* 144:222 */         item.motionZ = vec3.zCoord;
/* 145:    */       }
/* 146:    */     }
/* 147:    */   }
/* 148:    */   
/* 149:    */   public int getMaxSpawnedInChunk()
/* 150:    */   {
/* 151:235 */     return 1;
/* 152:    */   }
/* 153:    */   
/* 154:    */   public boolean getCanSpawnHere()
/* 155:    */   {
/* 156:242 */     return false;
/* 157:    */   }
/* 158:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.living.EntityReimu
 * JD-Core Version:    0.7.0.1
 */