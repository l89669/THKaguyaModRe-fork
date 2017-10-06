/*   1:    */ package thKaguyaMod.entity.spellcard;
/*   2:    */ 
/*   3:    */ import net.minecraft.entity.Entity;
/*   4:    */ import net.minecraft.util.MovingObjectPosition;
/*   5:    */ import net.minecraft.util.Vec3;
/*   6:    */ import net.minecraft.world.World;
/*   7:    */ import thKaguyaMod.THShotLib;
/*   8:    */ import thKaguyaMod.entity.shot.EntityTHShot;
/*   9:    */ import thKaguyaMod.entity.shot.ISpecialShot;
/*  10:    */ import thKaguyaMod.registry.SpecialShotRegistry;
/*  11:    */ 
/*  12:    */ public class THSC_LittleBugStorm
/*  13:    */   extends THSpellCard
/*  14:    */   implements ISpecialShot
/*  15:    */ {
/*  16:    */   public static final int SP_LITTLEBUGSTORM01 = 3400;
/*  17:    */   public static final int SP_LITTLEBUGSTORM02 = 3401;
/*  18:    */   public static final int SP_LITTLEBUGSTORM03 = 3402;
/*  19:    */   public static final int SP_LITTLEBUGSTORM11 = 3410;
/*  20:    */   public static final int SP_LITTLEBUGSTORM12 = 3411;
/*  21:    */   public static final int SP_LITTLEBUGSTORM13 = 3412;
/*  22:    */   public static final int SP_ATTACK01_01 = 3450;
/*  23:    */   public static final int SP_ATTACK01_02 = 3451;
/*  24:    */   private float yaw;
/*  25:    */   private float pitch;
/*  26:    */   
/*  27:    */   public THSC_LittleBugStorm()
/*  28:    */   {
/*  29: 32 */     setNeedLevel(2);
/*  30: 33 */     setRemoveTime(25);
/*  31: 34 */     setEndTime(150);
/*  32: 35 */     setOriginalUserName(23);
/*  33:    */     
/*  34: 37 */     SpecialShotRegistry.registerSpecialShot(THSC_LittleBugStorm.class, 3400);
/*  35: 38 */     SpecialShotRegistry.registerSpecialShot(THSC_LittleBugStorm.class, 3401);
/*  36: 39 */     SpecialShotRegistry.registerSpecialShot(THSC_LittleBugStorm.class, 3402);
/*  37: 40 */     SpecialShotRegistry.registerSpecialShot(THSC_LittleBugStorm.class, 3410);
/*  38: 41 */     SpecialShotRegistry.registerSpecialShot(THSC_LittleBugStorm.class, 3411);
/*  39: 42 */     SpecialShotRegistry.registerSpecialShot(THSC_LittleBugStorm.class, 3412);
/*  40:    */     
/*  41: 44 */     SpecialShotRegistry.registerSpecialShot(THSC_LittleBugStorm.class, 3450);
/*  42: 45 */     SpecialShotRegistry.registerSpecialShot(THSC_LittleBugStorm.class, 3451);
/*  43:    */   }
/*  44:    */   
/*  45:    */   public void spellcard_main()
/*  46:    */   {
/*  47: 53 */     int way = 1 + this.level * 2;
/*  48: 55 */     if (this.time < 15)
/*  49:    */     {
/*  50: 57 */       this.yaw += 2.7F;
/*  51: 58 */       this.pitch += 2.7F;
/*  52:    */       
/*  53:    */ 
/*  54: 61 */       Vec3 angle = THShotLib.angle(this.yaw, this.pitch);
/*  55:    */       
/*  56: 63 */       THShotLib.createSphereShot(this.user, this.user, pos_User(), angle, 1.0F, 1.0D, 0.0D, -0.0500000007450581D - this.time * 0.001D, gravity_Zero(), shot(1, 7, THShotLib.SIZE[1], 2.0F, 0, 50, 3400), way, 0.3D, 1.4F);
/*  57: 64 */       THShotLib.playShotSound(this.user);
/*  58:    */     }
/*  59: 66 */     else if (this.time < 30)
/*  60:    */     {
/*  61: 68 */       this.yaw += 2.7F;
/*  62: 69 */       this.pitch += 2.7F;
/*  63:    */       
/*  64:    */ 
/*  65: 72 */       Vec3 angle = THShotLib.angle(this.yaw, this.pitch);
/*  66:    */       
/*  67: 74 */       THShotLib.createSphereShot(this.user, this.user, pos_User(), angle, 1.0F, 1.0D, 0.0D, -0.0500000007450581D - this.time * 0.001D, gravity_Zero(), shot(1, 7, THShotLib.SIZE[1], 2.0F, 0, 50, 3410), way, 0.3D, 1.4F);
/*  68: 75 */       THShotLib.playShotSound(this.user);
/*  69:    */     }
/*  70: 77 */     else if (this.time < 45)
/*  71:    */     {
/*  72: 79 */       this.yaw += 2.7F;
/*  73: 80 */       this.pitch += 2.7F;
/*  74:    */       
/*  75:    */ 
/*  76: 83 */       Vec3 angle = THShotLib.angle(this.yaw, this.pitch);
/*  77:    */       
/*  78: 85 */       THShotLib.createSphereShot(this.user, this.user, pos_User(), angle, 1.0F, 1.0D, 0.0D, -0.0500000007450581D - this.time * 0.001D, gravity_Zero(), shot(1, 7, THShotLib.SIZE[1], 2.0F, 0, 50, 3400), way, 0.3D, 1.4F);
/*  79: 86 */       THShotLib.playShotSound(this.user);
/*  80:    */     }
/*  81: 88 */     else if (this.time < 60)
/*  82:    */     {
/*  83: 90 */       this.yaw += 2.7F;
/*  84: 91 */       this.pitch += 2.7F;
/*  85:    */       
/*  86:    */ 
/*  87: 94 */       Vec3 angle = THShotLib.angle(this.yaw, this.pitch);
/*  88:    */       
/*  89: 96 */       THShotLib.createSphereShot(this.user, this.user, pos_User(), angle, 1.0F, 1.0D, 0.0D, -0.0500000007450581D - this.time * 0.001D, gravity_Zero(), shot(1, 7, THShotLib.SIZE[1], 2.0F, 0, 50, 3410), way, 0.3D, 1.4F);
/*  90: 97 */       THShotLib.playShotSound(this.user);
/*  91:    */     }
/*  92:    */   }
/*  93:    */   
/*  94:    */   public void specialShot_move(World world, int id, EntityTHShot shot)
/*  95:    */   {
/*  96:103 */     switch (id)
/*  97:    */     {
/*  98:    */     case 3400: 
/*  99:106 */       if (shot.isShotEndTime())
/* 100:    */       {
/* 101:108 */         shot.setShotColor(7);
/* 102:109 */         shot.setShotSize(THShotLib.SIZE[0]);
/* 103:110 */         shot.shotSpecial = 3401;
/* 104:111 */         shot.setShotEndTime(20);
/* 105:112 */         shot.angle = THShotLib.getVectorFromRotation(shot.rotate, shot.angle, 90.0F);
/* 106:113 */         shot.shotSpeed = 3.0D;
/* 107:114 */         shot.shotLimitSpeed = 0.0D;
/* 108:115 */         shot.shotAcceleration = -1.0D;
/* 109:116 */         shot.reCreate();
/* 110:117 */         shot.delete();
/* 111:    */       }
/* 112:    */       break;
/* 113:    */     case 3401: 
/* 114:123 */       if (shot.isShotEndTime())
/* 115:    */       {
/* 116:125 */         shot.setShotForm(5);
/* 117:126 */         shot.setShotColor(3);
/* 118:127 */         shot.shotDamage = 4.0F;
/* 119:128 */         shot.setShotSize(THShotLib.SIZE[1]);
/* 120:129 */         shot.shotSpecial = 3402;
/* 121:130 */         shot.reCreate();
/* 122:131 */         shot.delete();
/* 123:    */       }
/* 124:    */       break;
/* 125:    */     case 3402: 
/* 126:135 */       if (shot.ticksExisted % 3 == 0) {
/* 127:137 */         if (shot.getShotColor() == 3)
/* 128:    */         {
/* 129:139 */           shot.setShotColor(7);
/* 130:140 */           shot.setShotSize(THShotLib.SIZE[1]);
/* 131:    */         }
/* 132:    */         else
/* 133:    */         {
/* 134:144 */           shot.setShotColor(3);
/* 135:145 */           shot.setShotSize(THShotLib.SIZE[1] * 2.0F);
/* 136:    */         }
/* 137:    */       }
/* 138:148 */       if (shot.isShotEndTime())
/* 139:    */       {
/* 140:150 */         shot.setShotForm(10);
/* 141:151 */         shot.setShotColor(3);
/* 142:152 */         shot.shotDamage = 6.0F;
/* 143:153 */         shot.setShotSize(THShotLib.SIZE[10] * 2.0F);
/* 144:154 */         shot.specialEnd();
/* 145:155 */         shot.angle = THShotLib.getVectorFromRotation(shot.rotate, shot.angle, -45.0F);
/* 146:156 */         shot.angle.yCoord = (Math.sin(THShotLib.getYawFromVector(shot.angle.xCoord, shot.angle.zCoord) * 23.0F / 180.0F * 3.141593F) * 30.0D * 0.01D);
/* 147:157 */         shot.updateAngle();
/* 148:158 */         shot.shotSpeed = 0.01D;
/* 149:159 */         shot.shotLimitSpeed = 0.5D;
/* 150:160 */         shot.shotAcceleration = 0.02D;
/* 151:161 */         shot.setShotEndTime(80);
/* 152:162 */         shot.reCreate();
/* 153:163 */         shot.delete();
/* 154:    */       }
/* 155:    */       break;
/* 156:    */     case 3410: 
/* 157:167 */       if (shot.isShotEndTime())
/* 158:    */       {
/* 159:169 */         shot.setShotColor(7);
/* 160:170 */         shot.setShotSize(THShotLib.SIZE[0]);
/* 161:171 */         shot.shotSpecial = 3411;
/* 162:172 */         shot.setShotEndTime(20);
/* 163:173 */         shot.angle = THShotLib.getVectorFromRotation(shot.rotate, shot.angle, 90.0F);
/* 164:174 */         shot.shotSpeed = 3.0D;
/* 165:175 */         shot.shotLimitSpeed = 0.0D;
/* 166:176 */         shot.shotAcceleration = -1.0D;
/* 167:177 */         shot.reCreate();
/* 168:178 */         shot.delete();
/* 169:    */       }
/* 170:    */       break;
/* 171:    */     case 3411: 
/* 172:182 */       if (shot.isShotEndTime())
/* 173:    */       {
/* 174:184 */         shot.setShotForm(5);
/* 175:185 */         shot.setShotColor(3);
/* 176:186 */         shot.shotDamage = 4.0F;
/* 177:187 */         shot.setShotSize(THShotLib.SIZE[1]);
/* 178:188 */         shot.shotSpecial = 3412;
/* 179:189 */         shot.reCreate();
/* 180:190 */         shot.delete();
/* 181:    */       }
/* 182:    */       break;
/* 183:    */     case 3412: 
/* 184:194 */       if (shot.ticksExisted % 3 == 0) {
/* 185:196 */         if (shot.getShotColor() == 3)
/* 186:    */         {
/* 187:198 */           shot.setShotColor(7);
/* 188:199 */           shot.setShotSize(THShotLib.SIZE[1]);
/* 189:    */         }
/* 190:    */         else
/* 191:    */         {
/* 192:203 */           shot.setShotColor(3);
/* 193:204 */           shot.setShotSize(THShotLib.SIZE[1] * 2.0F);
/* 194:    */         }
/* 195:    */       }
/* 196:207 */       if (shot.isShotEndTime())
/* 197:    */       {
/* 198:209 */         shot.setShotForm(10);
/* 199:210 */         shot.setShotColor(2);
/* 200:211 */         shot.setShotSize(THShotLib.SIZE[10] * 2.0F);
/* 201:212 */         shot.shotDamage = 6.0F;
/* 202:213 */         shot.specialEnd();
/* 203:214 */         shot.angle = THShotLib.getVectorFromRotation(shot.rotate, shot.angle, 135.0F);
/* 204:215 */         shot.angle.yCoord = (Math.sin(THShotLib.getYawFromVector(shot.angle.xCoord, shot.angle.zCoord) * 23.0F / 180.0F * 3.141593F) * 30.0D * 0.01D);
/* 205:216 */         shot.updateAngle();
/* 206:217 */         shot.shotSpeed = 0.01D;
/* 207:218 */         shot.shotLimitSpeed = 0.5D;
/* 208:219 */         shot.shotAcceleration = 0.02D;
/* 209:220 */         shot.setShotEndTime(80);
/* 210:221 */         shot.reCreate();
/* 211:222 */         shot.delete();
/* 212:    */       }
/* 213:    */       break;
/* 214:    */     case 3450: 
/* 215:226 */       if (shot.isShotEndTime())
/* 216:    */       {
/* 217:228 */         shot.specialEnd();
/* 218:229 */         shot.setShotEndTime(80);
/* 219:230 */         shot.angle = THShotLib.getVectorFromRotation(shot.rotate, shot.angle, 70.0F);
/* 220:231 */         shot.shotLimitSpeed = 0.5D;
/* 221:232 */         shot.shotAcceleration = 0.03D;
/* 222:233 */         shot.reCreate();
/* 223:234 */         shot.delete();
/* 224:    */       }
/* 225:    */       break;
/* 226:    */     case 3451: 
/* 227:238 */       if (shot.isShotEndTime())
/* 228:    */       {
/* 229:240 */         shot.specialEnd();
/* 230:241 */         shot.setShotEndTime(80);
/* 231:242 */         shot.angle = THShotLib.getVectorFromRotation(shot.rotate, shot.angle, -70.0F);
/* 232:243 */         shot.shotLimitSpeed = 0.5D;
/* 233:244 */         shot.shotAcceleration = 0.03D;
/* 234:245 */         shot.reCreate();
/* 235:246 */         shot.delete();
/* 236:    */       }
/* 237:    */       break;
/* 238:    */     }
/* 239:    */   }
/* 240:    */   
/* 241:    */   public boolean specialShot_hitBlock(World world, int id, EntityTHShot shot, MovingObjectPosition movingObjectPosition)
/* 242:    */   {
/* 243:260 */     return false;
/* 244:    */   }
/* 245:    */   
/* 246:    */   public boolean specialShot_hitEntity(World world, int id, EntityTHShot shot, Entity entity_Hit)
/* 247:    */   {
/* 248:267 */     return false;
/* 249:    */   }
/* 250:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.spellcard.THSC_LittleBugStorm
 * JD-Core Version:    0.7.0.1
 */