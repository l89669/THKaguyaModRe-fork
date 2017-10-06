/*   1:    */ package thKaguyaMod.entity.living;
/*   2:    */ 
/*   3:    */ import net.minecraft.entity.item.EntityItem;
/*   4:    */ import net.minecraft.entity.player.EntityPlayer;
/*   5:    */ import net.minecraft.util.ChatComponentText;
/*   6:    */ import net.minecraft.util.DamageSource;
/*   7:    */ import net.minecraft.util.Vec3;
/*   8:    */ import net.minecraft.world.World;
/*   9:    */ import thKaguyaMod.THShotLib;
/*  10:    */ import thKaguyaMod.init.THKaguyaConfig;
/*  11:    */ import thKaguyaMod.init.THKaguyaItems;
/*  12:    */ 
/*  13:    */ public class EntityMiko
/*  14:    */   extends EntityDanmakuMob
/*  15:    */ {
/*  16:    */   public EntityMiko(World world)
/*  17:    */   {
/*  18: 20 */     super(world);
/*  19:    */     
/*  20: 22 */     setSize(1.0F, 1.8F);
/*  21:    */     
/*  22: 24 */     this.experienceValue = 250;
/*  23:    */     
/*  24: 26 */     setDanmakuMobName("Toyosatomimi no Miko");
/*  25: 27 */     setSpecies(6);
/*  26:    */     
/*  27: 29 */     setDanmakuPattern(0);
/*  28: 30 */     setMaxHP(74.0F);
/*  29: 31 */     setHealth(74.0F);
/*  30: 32 */     setSpeed(0.4000000059604645D);
/*  31: 33 */     setAttackDistance(14.0D);
/*  32: 34 */     setDetectionDistance(0.0D);
/*  33: 35 */     setFlyingHeight(0);
/*  34: 36 */     this.isFlyingMode = false;
/*  35:    */     
/*  36: 38 */     this.isSpellCardMode = false;
/*  37: 39 */     this.attackInterval = 0;
/*  38:    */   }
/*  39:    */   
/*  40:    */   public int getUsingSpellCardNo()
/*  41:    */   {
/*  42: 48 */     switch (getDanmakuPattern())
/*  43:    */     {
/*  44:    */     case 101: 
/*  45: 51 */       return 0;
/*  46:    */     }
/*  47: 53 */     return -1;
/*  48:    */   }
/*  49:    */   
/*  50:    */   protected void onDeathUpdate()
/*  51:    */   {
/*  52: 61 */     switch (getDanmakuPattern())
/*  53:    */     {
/*  54:    */     case 1: 
/*  55: 64 */       setFlyingHeight(2);
/*  56: 65 */       moveDanmakuAttack(101, 40, 60.0F, 160);
/*  57: 66 */       break;
/*  58:    */     case 101: 
/*  59: 68 */       moveDanmakuAttack(127, 90, 40.0F, 160);
/*  60: 69 */       break;
/*  61:    */     default: 
/*  62: 71 */       if (this.deathTime % 6 == 0) {
/*  63: 73 */         THShotLib.explosionEffect(this.worldObj, this.posX, this.posY, this.posZ, 1.0F + this.deathTime * 0.1F);
/*  64:    */       }
/*  65: 75 */       super.onDeathUpdate();
/*  66:    */     }
/*  67:    */   }
/*  68:    */   
/*  69:    */   public void onUpdate()
/*  70:    */   {
/*  71: 85 */     super.onUpdate();
/*  72:    */   }
/*  73:    */   
/*  74:    */   public void danmakuPattern(int level)
/*  75:    */   {
/*  76: 96 */     Vec3 look = getLookVec();
/*  77: 97 */     switch (getDanmakuPattern())
/*  78:    */     {
/*  79:    */     case 1: 
/*  80:    */       break;
/*  81:    */     case 101: 
/*  82:103 */       if (this.attackCounter == 1) {
/*  83:105 */         useSpellCard(0);
/*  84:107 */       } else if (this.attackCounter >= 140) {
/*  85:109 */         this.attackCounter = 0;
/*  86:    */       }
/*  87:    */       break;
/*  88:    */     }
/*  89:    */   }
/*  90:    */   
/*  91:    */   protected boolean canFairyCall()
/*  92:    */   {
/*  93:151 */     return false;
/*  94:    */   }
/*  95:    */   
/*  96:    */   protected float applyPotionDamageCalculations(DamageSource damageSource, float damage)
/*  97:    */   {
/*  98:160 */     damage = super.applyPotionDamageCalculations(damageSource, damage);
/*  99:162 */     if (isEntityInvulnerable()) {
/* 100:164 */       damage = (float)(damage * 0.05D);
/* 101:    */     }
/* 102:167 */     return damage;
/* 103:    */   }
/* 104:    */   
/* 105:    */   public boolean isEntityInvulnerable()
/* 106:    */   {
/* 107:175 */     if (getDanmakuPattern() == 0) {
/* 108:177 */       return true;
/* 109:    */     }
/* 110:179 */     return false;
/* 111:    */   }
/* 112:    */   
/* 113:    */   protected void updateAITasks()
/* 114:    */   {
/* 115:185 */     super.updateAITasks();
/* 116:    */   }
/* 117:    */   
/* 118:    */   protected void dropFewItems(boolean par1, int par2)
/* 119:    */   {
/* 120:192 */     super.dropFewItems(par1, par2);
/* 121:194 */     if (isSpellCardAttack())
/* 122:    */     {
/* 123:196 */       int j = 12;
/* 124:201 */       for (int k = 0; k < j; k++)
/* 125:    */       {
/* 126:203 */         EntityItem item = dropItem(THKaguyaItems.power_item, 1);
/* 127:204 */         item.rotationYaw = (k * 30.0F);
/* 128:205 */         item.rotationPitch = -60.0F;
/* 129:206 */         Vec3 vec3 = THShotLib.getVecFromAngle(item.rotationYaw, item.rotationPitch, 0.5D);
/* 130:207 */         item.setPosition(item.posX + vec3.xCoord * 2.0D, item.posY, item.posZ + vec3.zCoord * 2.0D);
/* 131:    */         
/* 132:209 */         item.motionX = vec3.xCoord;
/* 133:210 */         item.motionY = vec3.yCoord;
/* 134:211 */         item.motionZ = vec3.zCoord;
/* 135:    */       }
/* 136:214 */       for (int k = 0; k < j; k++)
/* 137:    */       {
/* 138:216 */         EntityItem item = dropItem(THKaguyaItems.point_item, 1);
/* 139:217 */         item.rotationYaw = (k * 30.0F + 15.0F);
/* 140:218 */         item.rotationPitch = -60.0F;
/* 141:219 */         Vec3 vec3 = THShotLib.getVecFromAngle(item.rotationYaw, item.rotationPitch, 0.300000011920929D);
/* 142:220 */         item.setPosition(item.posX + vec3.xCoord * 2.0D, item.posY, item.posZ + vec3.zCoord * 2.0D);
/* 143:    */         
/* 144:222 */         item.motionX = vec3.xCoord;
/* 145:223 */         item.motionY = vec3.yCoord;
/* 146:224 */         item.motionZ = vec3.zCoord;
/* 147:    */       }
/* 148:    */     }
/* 149:    */   }
/* 150:    */   
/* 151:    */   public int getMaxSpawnedInChunk()
/* 152:    */   {
/* 153:237 */     return 1;
/* 154:    */   }
/* 155:    */   
/* 156:    */   public boolean getCanSpawnHere()
/* 157:    */   {
/* 158:244 */     return false;
/* 159:    */   }
/* 160:    */   
/* 161:    */   public boolean interact(EntityPlayer player)
/* 162:    */   {
/* 163:252 */     if (this.ticksExisted % 2 == 0) {
/* 164:254 */       return false;
/* 165:    */     }
/* 166:257 */     String str = "";
/* 167:259 */     if (THKaguyaConfig.danmakuLevel == 1)
/* 168:    */     {
/* 169:261 */       THKaguyaConfig.danmakuLevel = 2;
/* 170:262 */       str = "NORMAL";
/* 171:    */     }
/* 172:264 */     else if (THKaguyaConfig.danmakuLevel == 2)
/* 173:    */     {
/* 174:266 */       THKaguyaConfig.danmakuLevel = 3;
/* 175:267 */       str = "HARD";
/* 176:    */     }
/* 177:269 */     else if (THKaguyaConfig.danmakuLevel == 3)
/* 178:    */     {
/* 179:271 */       THKaguyaConfig.danmakuLevel = 4;
/* 180:272 */       str = "LUNATIC";
/* 181:    */     }
/* 182:    */     else
/* 183:    */     {
/* 184:276 */       THKaguyaConfig.danmakuLevel = 1;
/* 185:277 */       str = "EASY";
/* 186:    */     }
/* 187:279 */     player.addChatComponentMessage(new ChatComponentText(str));
/* 188:280 */     return true;
/* 189:    */   }
/* 190:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.living.EntityMiko
 * JD-Core Version:    0.7.0.1
 */