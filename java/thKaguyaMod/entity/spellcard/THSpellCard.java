/*   1:    */ package thKaguyaMod.entity.spellcard;
/*   2:    */ 
/*   3:    */ import java.util.Random;
/*   4:    */ import net.minecraft.entity.EntityLivingBase;
/*   5:    */ import net.minecraft.util.Vec3;
/*   6:    */ import net.minecraft.world.World;
/*   7:    */ import thKaguyaMod.ShotData;
/*   8:    */ import thKaguyaMod.THShotLib;
/*   9:    */ 
/*  10:    */ public abstract class THSpellCard
/*  11:    */ {
/*  12: 16 */   public Random rand = new Random();
/*  13:    */   protected World world;
/*  14:    */   private int end;
/*  15:    */   private int remove;
/*  16:    */   private int number;
/*  17:    */   public int time;
/*  18:    */   public int level;
/*  19:    */   public EntitySpellCard card;
/*  20:    */   public EntityLivingBase user;
/*  21:    */   public EntityLivingBase target;
/*  22:    */   protected double cardPosX;
/*  23:    */   protected double cardPosY;
/*  24:    */   protected double cardPosZ;
/*  25:    */   protected double userPosX;
/*  26:    */   protected double userPosY;
/*  27:    */   protected double userPosZ;
/*  28:    */   protected double targetPosX;
/*  29:    */   protected double targetPosY;
/*  30:    */   protected double targetPosZ;
/*  31:    */   private int needLevel;
/*  32:    */   private int originalUserName;
/*  33:    */   protected String spellcardName;
/*  34:    */   protected String iconName;
/*  35:    */   public static THSpellCard instance;
/*  36:    */   public static final int OLD = -1;
/*  37:    */   public static final int REIMU = 0;
/*  38:    */   public static final int MARISA = 1;
/*  39:    */   public static final int RUMIA = 2;
/*  40:    */   public static final int DAIYOUSEI = 3;
/*  41:    */   public static final int CIRNO = 4;
/*  42:    */   public static final int MEIRIN = 5;
/*  43:    */   public static final int KOAKUMA = 6;
/*  44:    */   public static final int PATCHOULI = 7;
/*  45:    */   public static final int SAKUYA = 8;
/*  46:    */   public static final int REMILIA = 9;
/*  47:    */   public static final int FLANDORE = 10;
/*  48:    */   public static final int LETTY = 11;
/*  49:    */   public static final int CHEN = 12;
/*  50:    */   public static final int ALICE = 13;
/*  51:    */   public static final int LILY = 14;
/*  52:    */   public static final int LUNASA = 15;
/*  53:    */   public static final int MERLIN = 16;
/*  54:    */   public static final int LYRICA = 17;
/*  55:    */   public static final int YOUMU = 18;
/*  56:    */   public static final int YUYUKO = 19;
/*  57:    */   public static final int RAN = 20;
/*  58:    */   public static final int YUKARI = 21;
/*  59:    */   public static final int SUIKA = 22;
/*  60:    */   public static final int WRIGGLE = 23;
/*  61:    */   public static final int MYSTIA = 24;
/*  62:    */   public static final int KEINE = 25;
/*  63:    */   public static final int TEI = 26;
/*  64:    */   public static final int UDONGEIN = 27;
/*  65:    */   public static final int EIRIN = 28;
/*  66:    */   public static final int KAGUYA = 29;
/*  67:    */   public static final int MOKOU = 30;
/*  68:    */   public static final int AYA = 31;
/*  69:    */   public static final int MEDICINE = 32;
/*  70:    */   public static final int YUUKA = 33;
/*  71:    */   public static final int KOMACHI = 34;
/*  72:    */   public static final int SHIKIEIKI = 35;
/*  73:    */   public static final int SHIZUHA = 36;
/*  74:    */   public static final int MINORIKO = 37;
/*  75:    */   public static final int HINA = 38;
/*  76:    */   public static final int NITORI = 39;
/*  77:    */   public static final int MOMIZI = 40;
/*  78:    */   public static final int SANAE = 41;
/*  79:    */   public static final int KANAKO = 42;
/*  80:    */   public static final int SUWAKO = 43;
/*  81:    */   public static final int IKU = 44;
/*  82:    */   public static final int TENSHI = 45;
/*  83:    */   public static final int KISUME = 46;
/*  84:    */   public static final int YAMAME = 47;
/*  85:    */   public static final int PARSEE = 48;
/*  86:    */   public static final int YUUGI = 49;
/*  87:    */   public static final int SATORI = 50;
/*  88:    */   public static final int RIN = 51;
/*  89:    */   public static final int UTUHO = 52;
/*  90:    */   public static final int KOISHI = 53;
/*  91:    */   public static final int NAZRIN = 54;
/*  92:    */   public static final int KOGASA = 55;
/*  93:    */   public static final int ICHIRIN = 56;
/*  94:    */   public static final int MINAMITU = 57;
/*  95:    */   public static final int SHOU = 58;
/*  96:    */   public static final int BYAKUREN = 59;
/*  97:    */   public static final int NUE = 60;
/*  98:    */   public static final int HATATE = 61;
/*  99:    */   public static final int KYOUKO = 62;
/* 100:    */   public static final int YOSHIKA = 63;
/* 101:    */   public static final int SEIGA = 64;
/* 102:    */   public static final int TOZIKO = 65;
/* 103:    */   public static final int FUTO = 66;
/* 104:    */   public static final int MIKO = 67;
/* 105:    */   public static final int MAMIZOU = 68;
/* 106:    */   public static final int KOKORO = 69;
/* 107:    */   public static final int WAKASAGIHIME = 70;
/* 108:    */   public static final int SEKIBANKI = 71;
/* 109:    */   public static final int KAGEROU = 72;
/* 110:    */   public static final int BENBEN = 73;
/* 111:    */   public static final int YATUHASHI = 74;
/* 112:    */   public static final int SEIJA = 75;
/* 113:    */   public static final int SHINMYOUMARU = 76;
/* 114:    */   public static final int RAIKO = 77;
/* 115:    */   public static final int RINNOSUKE = 128;
/* 116:    */   public static final int TOKIKO = 129;
/* 117:    */   public static final int SUNNY = 132;
/* 118:    */   public static final int LUNAR = 133;
/* 119:    */   public static final int STAR = 134;
/* 120:    */   public static final int AKYUU = 138;
/* 121:    */   public static final int TOYOHIME = 142;
/* 122:    */   public static final int YORIHIME = 143;
/* 123:    */   public static final int REISEN = 144;
/* 124:    */   public static final int KASEN = 146;
/* 125:    */   public static final int KOSUZU = 150;
/* 126:    */   public static final int RENKO = 192;
/* 127:    */   public static final int MAERIBERRY = 193;
/* 128:    */   public static final int OTHER = 254;
/* 129:    */   
/* 130:    */   public THSpellCard()
/* 131:    */   {
/* 132:181 */     setNeedLevel(1);
/* 133:    */   }
/* 134:    */   
/* 135:    */   public void init(World worldObj, EntitySpellCard entitySpellCard, EntityLivingBase living_user, EntityLivingBase living_target, int spLevel)
/* 136:    */   {
/* 137:193 */     this.world = worldObj;
/* 138:194 */     this.card = entitySpellCard;
/* 139:195 */     this.user = living_user;
/* 140:196 */     this.target = living_target;
/* 141:197 */     this.time = 0;
/* 142:198 */     this.level = spLevel;
/* 143:    */   }
/* 144:    */   
/* 145:    */   public boolean onUpdate()
/* 146:    */   {
/* 147:208 */     this.cardPosX = this.card.posX;
/* 148:209 */     this.cardPosY = this.card.posY;
/* 149:210 */     this.cardPosZ = this.card.posZ;
/* 150:    */     
/* 151:212 */     this.userPosX = this.user.posX;
/* 152:213 */     this.userPosY = THShotLib.getPosYFromEye(this.user);
/* 153:214 */     this.userPosZ = this.user.posZ;
/* 154:    */     
/* 155:216 */     this.targetPosX = this.target.posX;
/* 156:217 */     this.targetPosY = (this.target.posY + this.target.height / 2.0D);
/* 157:218 */     this.targetPosZ = this.target.posZ;
/* 158:    */     
/* 159:220 */     spellcard_main();
/* 160:    */     
/* 161:222 */     this.time += 1;
/* 162:223 */     return true;
/* 163:    */   }
/* 164:    */   
/* 165:    */   public void spellcard_main() {}
/* 166:    */   
/* 167:    */   public void specialProcessInTimeStop()
/* 168:    */   {
/* 169:239 */     if (canMoveInTimeStop()) {
/* 170:241 */       setEndTime(getEndTime() + 1);
/* 171:    */     }
/* 172:    */   }
/* 173:    */   
/* 174:    */   public Vec3 pos(double x, double y, double z)
/* 175:    */   {
/* 176:258 */     return Vec3.createVectorHelper(x, y, z);
/* 177:    */   }
/* 178:    */   
/* 179:    */   public Vec3 pos_User()
/* 180:    */   {
/* 181:267 */     return pos(this.userPosX, this.userPosY, this.userPosZ);
/* 182:    */   }
/* 183:    */   
/* 184:    */   public Vec3 pos_User(Vec3 angle, double distance)
/* 185:    */   {
/* 186:276 */     angle = THShotLib.getVectorNomalize(angle);
/* 187:277 */     return pos(this.userPosX + angle.xCoord * distance, this.userPosY + angle.yCoord, this.userPosZ + angle.zCoord * distance);
/* 188:    */   }
/* 189:    */   
/* 190:    */   public Vec3 pos_Card()
/* 191:    */   {
/* 192:286 */     return Vec3.createVectorHelper(this.cardPosX, this.cardPosY, this.cardPosZ);
/* 193:    */   }
/* 194:    */   
/* 195:    */   public Vec3 pos_Target()
/* 196:    */   {
/* 197:295 */     return THShotLib.pos_Living(this.target);
/* 198:    */   }
/* 199:    */   
/* 200:    */   public Vec3 angle(double x, double y, double z)
/* 201:    */   {
/* 202:307 */     return THShotLib.angle(x, y, z);
/* 203:    */   }
/* 204:    */   
/* 205:    */   public Vec3 angle(float yaw, float pitch)
/* 206:    */   {
/* 207:318 */     return THShotLib.angle(yaw, pitch);
/* 208:    */   }
/* 209:    */   
/* 210:    */   public Vec3 angle_UserToTarget()
/* 211:    */   {
/* 212:327 */     return THShotLib.angle_ToPos(pos_User(), pos_Target());
/* 213:    */   }
/* 214:    */   
/* 215:    */   public static Vec3 rotate_Default()
/* 216:    */   {
/* 217:335 */     return THShotLib.rotate_Default();
/* 218:    */   }
/* 219:    */   
/* 220:    */   public Vec3 gravity_Zero()
/* 221:    */   {
/* 222:344 */     return THShotLib.gravity_Zero();
/* 223:    */   }
/* 224:    */   
/* 225:    */   public Vec3 gravity_Default()
/* 226:    */   {
/* 227:353 */     return THShotLib.gravity_Default();
/* 228:    */   }
/* 229:    */   
/* 230:    */   public Vec3 getVecFromAngle(float yaw, float pitch)
/* 231:    */   {
/* 232:364 */     return THShotLib.getVecFromAngle(yaw, pitch);
/* 233:    */   }
/* 234:    */   
/* 235:    */   public static ShotData shot(int form, int color, float size, float damage, int delay, int end, int special)
/* 236:    */   {
/* 237:380 */     return ShotData.shot(form, color, size, damage, delay, end, special);
/* 238:    */   }
/* 239:    */   
/* 240:    */   public static ShotData shot(int form, int color, float size, float damage, int delay, int end)
/* 241:    */   {
/* 242:385 */     return shot(form, color, size, damage, delay, end, 0);
/* 243:    */   }
/* 244:    */   
/* 245:    */   public static ShotData shot(int form, int color, float size, float damage)
/* 246:    */   {
/* 247:390 */     return shot(form, color, size, damage, 0, 80);
/* 248:    */   }
/* 249:    */   
/* 250:    */   public static ShotData shot(int form, int color)
/* 251:    */   {
/* 252:395 */     return shot(form, color, 0, 80, 0);
/* 253:    */   }
/* 254:    */   
/* 255:    */   public static ShotData shot(int form, int color, int delay)
/* 256:    */   {
/* 257:400 */     return shot(form, color, delay);
/* 258:    */   }
/* 259:    */   
/* 260:    */   public static ShotData shot(int form, int color, int delay, int end)
/* 261:    */   {
/* 262:405 */     return shot(form, color, delay, end, 0);
/* 263:    */   }
/* 264:    */   
/* 265:    */   public static ShotData shot(int form, int color, int delay, int end, int special)
/* 266:    */   {
/* 267:410 */     float size = THShotLib.SIZE[form];
/* 268:411 */     float damage = THShotLib.DAMAGE[form];
/* 269:412 */     return shot(form, color, size, damage, delay, end, special);
/* 270:    */   }
/* 271:    */   
/* 272:    */   public int getSpellCardNumber()
/* 273:    */   {
/* 274:425 */     return -1;
/* 275:    */   }
/* 276:    */   
/* 277:    */   public String getSpellCardName()
/* 278:    */   {
/* 279:430 */     return this.spellcardName;
/* 280:    */   }
/* 281:    */   
/* 282:    */   public int getNeedLevel()
/* 283:    */   {
/* 284:439 */     return this.needLevel;
/* 285:    */   }
/* 286:    */   
/* 287:    */   public int getRemoveTime()
/* 288:    */   {
/* 289:448 */     return this.remove;
/* 290:    */   }
/* 291:    */   
/* 292:    */   public int getEndTime()
/* 293:    */   {
/* 294:457 */     return this.end;
/* 295:    */   }
/* 296:    */   
/* 297:    */   public int getOriginalUserName()
/* 298:    */   {
/* 299:467 */     return this.originalUserName;
/* 300:    */   }
/* 301:    */   
/* 302:    */   public int getSpellCardCircleColor()
/* 303:    */   {
/* 304:476 */     return 0;
/* 305:    */   }
/* 306:    */   
/* 307:    */   public void setSpellCardName(String name)
/* 308:    */   {
/* 309:484 */     this.spellcardName = name;
/* 310:    */   }
/* 311:    */   
/* 312:    */   public void setNeedLevel(int level)
/* 313:    */   {
/* 314:493 */     this.needLevel = level;
/* 315:    */   }
/* 316:    */   
/* 317:    */   public void setRemoveTime(int setTime)
/* 318:    */   {
/* 319:502 */     this.remove = setTime;
/* 320:    */   }
/* 321:    */   
/* 322:    */   public void setEndTime(int setTime)
/* 323:    */   {
/* 324:512 */     this.end = setTime;
/* 325:    */   }
/* 326:    */   
/* 327:    */   public void setOriginalUserName(int originalUserNameID)
/* 328:    */   {
/* 329:523 */     this.originalUserName = originalUserNameID;
/* 330:    */   }
/* 331:    */   
/* 332:    */   public void setIconName(String name)
/* 333:    */   {
/* 334:534 */     this.iconName = name;
/* 335:    */   }
/* 336:    */   
/* 337:    */   public String getIconName()
/* 338:    */   {
/* 339:539 */     return this.iconName;
/* 340:    */   }
/* 341:    */   
/* 342:    */   public boolean canMoveInTimeStop()
/* 343:    */   {
/* 344:549 */     return false;
/* 345:    */   }
/* 346:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.spellcard.THSpellCard
 * JD-Core Version:    0.7.0.1
 */