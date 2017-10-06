/*    1:     */ package thKaguyaMod;
/*    2:     */ 
/*    3:     */ import java.util.List;
/*    4:     */ import java.util.Random;
/*    5:     */ import net.minecraft.entity.Entity;
/*    6:     */ import net.minecraft.entity.EntityLivingBase;
/*    7:     */ import net.minecraft.entity.player.EntityPlayer;
/*    8:     */ import net.minecraft.util.AxisAlignedBB;
/*    9:     */ import net.minecraft.util.DamageSource;
/*   10:     */ import net.minecraft.util.MathHelper;
/*   11:     */ import net.minecraft.util.Vec3;
/*   12:     */ import net.minecraft.world.World;
/*   13:     */ import thKaguyaMod.entity.living.EntityFamiliar;
/*   14:     */ import thKaguyaMod.entity.living.EntityTHFairy;
/*   15:     */ import thKaguyaMod.entity.shot.EntityNuclearShot;
/*   16:     */ import thKaguyaMod.entity.shot.EntityTHLaser;
/*   17:     */ import thKaguyaMod.entity.shot.EntityTHSetLaser;
/*   18:     */ import thKaguyaMod.entity.shot.EntityTHShot;
/*   19:     */ 
/*   20:     */ public class THShotLib
/*   21:     */   extends DanmakuConstants
/*   22:     */ {
/*   23:  35 */   public static final int[] SMALL = { 0, 1, 2, 3, 4, 5, 6, 7 };
/*   24:  37 */   public static final int[] TINY = { 8, 9, 10, 11, 12, 13, 14, 15 };
/*   25:  39 */   public static final int[] MEDIUM = { 16, 17, 18, 19, 20, 21, 22, 23 };
/*   26:  41 */   public static final int[] PEARL = { 24, 25, 26, 27, 28, 29, 30, 31 };
/*   27:  43 */   public static final int[] CIRCLE = { 32, 33, 34, 35, 36, 37, 38, 39 };
/*   28:  45 */   public static final int[] LIGHT = { 40, 41, 42, 43, 44, 45, 46, 47 };
/*   29:  47 */   public static final int[] SCALE = { 48, 49, 50, 51, 52, 53, 54, 55 };
/*   30:  49 */   public static final int[] BUTTERFLY = { 56, 57, 58, 59, 60, 61, 62, 63 };
/*   31:  51 */   public static final int[] SMALLSTAR = { 64, 65, 66, 67, 68, 69, 70, 71 };
/*   32:  53 */   public static final int[] STAR = { 72, 73, 74, 75, 76, 77, 78, 79 };
/*   33:  55 */   public static final int[] RICE = { 80, 81, 82, 83, 84, 85, 86, 87 };
/*   34:  57 */   public static final int[] CRYSTAL = { 88, 89, 90, 91, 92, 93, 94, 95 };
/*   35:  59 */   public static final int[] HEART = { 96, 97, 98, 99, 100, 101, 102, 103 };
/*   36:  61 */   public static final int[] KUNAI = { 104, 105, 106, 107, 108, 109, 110, 111 };
/*   37:  63 */   public static final int[] TALISMAN = { 112, 113, 114, 115, 116, 117, 118, 119 };
/*   38:  65 */   public static final int[] BIGLIGHT = { 120, 121, 122, 123, 124, 125, 126, 127 };
/*   39:  67 */   public static final int[] OVAL = { 128, 129, 130, 131, 132, 133, 134, 135 };
/*   40:  69 */   public static final int[] FAMILIAR = { 136, 137, 138, 139, 140, 141, 142, 143 };
/*   41:  71 */   public static final int[] ARROW = { 144, 145, 146, 147, 148, 149, 150, 151 };
/*   42:  73 */   public static final int[] AMULET = { 216, 217, 218, 219, 220, 221, 222, 223 };
/*   43:  75 */   public static final int[] KNIFE = { 224, 225, 226, 227, 228, 229, 230, 231 };
/*   44:  77 */   public static final int[] WIND = { 232, 233, 234, 235, 236, 237, 238, 239 };
/*   45:  79 */   public static final int[] BIG = { 240, 241, 242, 243, 244, 245, 246, 247 };
/*   46:  81 */   public static final int[] KISHITU = { 248, 249, 250, 251, 252, 253, 254, 255 };
/*   47:  83 */   public static final int[] LASER = { 512, 513, 514, 515, 516, 517, 518, 519 };
/*   48:  88 */   public static final String[] COLOR_NAME_JP = { "赤", "青", "緑", "黄", "紫", "水色", "橙", "白", "虹色", "ランダム", "暖色", "寒色", "", "", "", "" };
/*   49:  94 */   public static final String[] COLOR_NAME = { "Red", "Blue", "Green", "Yellow", "Purple", "Aqua", "Orange", "White", "Rainbow", "Random", "Hot", "Cold", "", "", "", "" };
/*   50: 100 */   public static final String[] SHOT_NAME_JP = { "小弾", "粒弾", "中弾", "真珠弾", "輪弾", "光弾", "鱗弾", "蝶弾", "小星弾", "星弾", "米弾", "結晶弾", "ハート", "クナイ", "札弾", "大光弾", "楕円弾", "使い魔", "　矢弾", "未定義", "未定義", "未定義", "未定義", "未定義", "未定義", "未定義", "未定義", "未定義", "ナイフ", "風弾", "大弾", "気質弾" };
/*   51: 109 */   public static final float[] DAMAGE = { 2.4F, 1.0F, 2.8F, 5.0F, 2.0F, 3.4F, 1.6F, 2.8F, 1.6F, 3.0F, 1.2F, 1.8F, 3.2F, 2.6F, 1.0F, 6.8F, 3.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 5.0F, 2.6F, 4.0F, 5.0F, 5.0F };
/*   52: 117 */   public static final float[] SIZE = { 0.3F, 0.15F, 0.5F, 0.3F, 0.3F, 0.3F, 0.15F, 0.3F, 0.25F, 0.5F, 0.15F, 0.15F, 0.5F, 0.15F, 0.15F, 0.6F, 0.5F, 1.0F, 0.15F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.4F, 0.3F, 1.0F, 0.9F, 0.9F };
/*   53: 122 */   private static int[][] ways = { { 0 }, { 1 }, { 1, 1 }, { 1, 0, 2, 0 }, { 1, 0, 3, 0 }, { 1, 3, 1 }, { 1, 4, 1 }, { 1, 5, 1 }, { 1, 3, 3, 1 }, { 1, 5, 3, 0 }, { 1, 4, 4, 1 }, { 1, 6, 4, 0 }, { 1, 5, 5, 1 }, { 1, 3, 5, 3, 1 }, { 1, 3, 6, 3, 1 }, { 1, 4, 5, 4, 1 }, { 1, 4, 6, 4, 1 }, { 1, 4, 7, 4, 1 }, { 1, 3, 5, 5, 3, 1 }, { 1, 3, 6, 6, 3 }, { 1, 3, 6, 6, 3, 1 }, { 1, 5, 9, 5, 1 }, { 1, 3, 7, 7, 3, 1 }, { 1, 6, 9, 6, 1 }, { 1, 4, 7, 7, 4, 1 }, { 1, 3, 5, 7, 5, 3, 1 }, { 1, 3, 5, 8, 5, 3, 1 }, { 1, 3, 5, 9, 5, 3, 1 }, { 1, 3, 6, 8, 6, 3, 1 }, { 1, 3, 6, 9, 6, 3, 1 }, { 1, 4, 6, 8, 6, 4, 1 }, { 1, 3, 6, 11, 6, 3, 1 }, { 1, 3, 6, 12, 6, 3, 1 }, { 0 }, { 0 }, { 0 }, { 0 }, { 0 }, { 0 }, { 0 }, { 0 }, { 0 }, { 0 }, { 0 }, { 1, 3, 6, 12, 12, 6, 3, 1 } };
/*   54:     */   
/*   55:     */   public static final int getFormID(int shotID)
/*   56:     */   {
/*   57: 186 */     return (shotID - shotID % 8) / 8;
/*   58:     */   }
/*   59:     */   
/*   60:     */   public static final EntityTHShot createShot(EntityLivingBase user, Entity source, Vec3 pos, Vec3 angle, float slope, Vec3 rotate, float rotationSpeed, int rotationEnd, double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity, ShotData shot)
/*   61:     */   {
/*   62: 217 */     World world = source.worldObj;
/*   63:     */     
/*   64:     */ 
/*   65: 220 */     ShotData shot2 = shot;
/*   66: 221 */     boolean random = false;
/*   67: 222 */     boolean rainbow = false;
/*   68: 223 */     if (shot2 != null) {
/*   69: 225 */       if (shot2.color == 9)
/*   70:     */       {
/*   71: 227 */         shot2.color = new Random().nextInt(7);
/*   72: 228 */         rainbow = true;
/*   73:     */       }
/*   74: 230 */       else if (shot2.color == 8)
/*   75:     */       {
/*   76: 232 */         shot2.color = new Random().nextInt(8);
/*   77: 233 */         random = true;
/*   78:     */       }
/*   79:     */     }
/*   80: 237 */     EntityTHShot entityTHShot = new EntityTHShot(world, user, source, pos, angle, slope, rotate, rotationSpeed, rotationEnd, firstSpeed, limitSpeed, acceleration, gravity, shot2);
/*   81: 242 */     if (!world.isRemote) {
/*   82: 244 */       world.spawnEntityInWorld(entityTHShot);
/*   83:     */     }
/*   84: 247 */     if (random) {
/*   85: 249 */       shot.color = 8;
/*   86: 251 */     } else if (rainbow) {
/*   87: 253 */       shot.color = 9;
/*   88:     */     }
/*   89: 255 */     return entityTHShot;
/*   90:     */   }
/*   91:     */   
/*   92:     */   public static final EntityTHShot createShot(EntityLivingBase user, Entity source, Vec3 pos, Vec3 angle, float slope, double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity, ShotData shot)
/*   93:     */   {
/*   94: 278 */     float yaw = getYawFromVector(angle.xCoord, angle.zCoord);
/*   95: 279 */     float pitch = getPitchFromVector(angle.xCoord, angle.yCoord, angle.zCoord);
/*   96: 280 */     Vec3 rotate = getVecFromAngle(yaw, pitch + 90.0F);
/*   97: 281 */     return createShot(user, source, pos, angle, slope, rotate, 0.0F, 9999, firstSpeed, limitSpeed, acceleration, gravity, shot);
/*   98:     */   }
/*   99:     */   
/*  100:     */   public static final EntityTHShot createShot(EntityLivingBase user, Vec3 pos, Vec3 angle, double firstSpeed, double limitSpeed, double acceleration, ShotData shot)
/*  101:     */   {
/*  102: 298 */     return createShot(user, pos, angle, firstSpeed, limitSpeed, acceleration, gravity_Zero(), shot);
/*  103:     */   }
/*  104:     */   
/*  105:     */   public static final EntityTHShot createShot(EntityLivingBase user, Vec3 pos, Vec3 angle, double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity, ShotData shot)
/*  106:     */   {
/*  107: 317 */     return createShot(user, user, pos, angle, 0.0F, firstSpeed, limitSpeed, acceleration, gravity, shot);
/*  108:     */   }
/*  109:     */   
/*  110:     */   public static final EntityTHShot createShot(EntityLivingBase user, Vec3 pos, Vec3 angle, double speed, ShotData shot)
/*  111:     */   {
/*  112: 333 */     return createShot(user, pos, angle, speed, speed, 0.0D, shot);
/*  113:     */   }
/*  114:     */   
/*  115:     */   public static final void createWideShot(EntityLivingBase user, Entity source, Vec3 pos, Vec3 angle, float rotationSpeed, int rotationEnd, double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity, ShotData shot, int way, float wideAngle, double distance, float baseAngle)
/*  116:     */   {
/*  117: 362 */     float yaw = getYawFromVector(angle.xCoord, angle.zCoord);
/*  118: 363 */     float pitch = getPitchFromVector(angle.xCoord, angle.yCoord, angle.zCoord);
/*  119:     */     
/*  120: 365 */     Vec3 rotateVec = getVecFromAngle(-yaw, -pitch + 90.0F, 1.0D);
/*  121:     */     
/*  122: 367 */     float angle2 = -wideAngle / 2.0F;
/*  123: 368 */     float span = wideAngle / (way - 1);
/*  124: 369 */     angle2 += baseAngle;
/*  125:     */     
/*  126:     */ 
/*  127: 372 */     int color = new Random().nextInt(7);
/*  128: 373 */     int[] rainbowPattern = { 0, 6, 3, 2, 5, 1, 4 };
/*  129: 374 */     boolean random = false;
/*  130: 375 */     boolean rainbow = false;
/*  131: 376 */     ShotData shot2 = shot;
/*  132: 377 */     if (shot2.color == 8) {
/*  133: 379 */       random = true;
/*  134: 381 */     } else if (shot2.color == 9) {
/*  135: 383 */       rainbow = true;
/*  136:     */     }
/*  137: 386 */     if (random) {
/*  138: 388 */       shot2.color = new Random().nextInt(8);
/*  139:     */     }
/*  140: 392 */     for (int i = 0; i < way; i++)
/*  141:     */     {
/*  142: 395 */       if (rainbow)
/*  143:     */       {
/*  144: 397 */         shot2.color = rainbowPattern[(color % 7)];
/*  145: 398 */         color++;
/*  146:     */       }
/*  147: 400 */       Vec3 angleVec = getVectorFromRotation(rotateVec.xCoord, rotateVec.yCoord, rotateVec.zCoord, angle.xCoord, angle.yCoord, angle.zCoord, angle2);
/*  148: 401 */       createShot(user, source, Vec3.createVectorHelper(pos.xCoord + angleVec.xCoord * distance, pos.yCoord + angleVec.yCoord * distance, pos.zCoord + angleVec.zCoord * distance), angleVec, pitch * 
/*  149: 402 */         (float)Math.sin(angle2 / 180.0F * 3.141593F), rotateVec, rotationSpeed, rotationEnd, firstSpeed, limitSpeed, acceleration, gravity, shot);
/*  150: 403 */       angle2 += span;
/*  151:     */     }
/*  152: 405 */     if (random) {
/*  153: 407 */       shot.color = 8;
/*  154: 409 */     } else if (rainbow) {
/*  155: 411 */       shot.color = 9;
/*  156:     */     }
/*  157:     */   }
/*  158:     */   
/*  159:     */   public static final void createWideShot(EntityLivingBase user, Entity source, Vec3 pos, Vec3 angle, float rotationSpeed, int rotationEnd, double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity, ShotData shot, int way, float wideAngle, double distance)
/*  160:     */   {
/*  161: 438 */     createWideShot(user, source, pos, angle, rotationSpeed, rotationEnd, firstSpeed, limitSpeed, acceleration, gravity, shot, way, wideAngle, distance, 0.0F);
/*  162:     */   }
/*  163:     */   
/*  164:     */   public static final void createWideShot(EntityLivingBase user, Entity source, Vec3 pos, Vec3 angle, double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity, ShotData shot, int way, float wideAngle, double distance, float baseAngle)
/*  165:     */   {
/*  166: 464 */     createWideShot(user, source, pos, angle, 0.0F, 9999, firstSpeed, limitSpeed, acceleration, gravity, shot, way, wideAngle, distance, baseAngle);
/*  167:     */   }
/*  168:     */   
/*  169:     */   public static final void createWideShot(EntityLivingBase user, Entity source, Vec3 pos, Vec3 angle, double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity, ShotData shot, int way, float wideAngle, double distance)
/*  170:     */   {
/*  171: 488 */     createWideShot(user, source, pos, angle, 0.0F, 9999, firstSpeed, limitSpeed, acceleration, gravity, shot, way, wideAngle, distance);
/*  172:     */   }
/*  173:     */   
/*  174:     */   public static final void createWideShot(EntityLivingBase user, Vec3 pos, Vec3 angle, double firstSpeed, double limitSpeed, double acceleration, ShotData shot, int way, float wideAngle)
/*  175:     */   {
/*  176: 505 */     createWideShot(user, user, pos, angle, firstSpeed, limitSpeed, acceleration, gravity_Zero(), shot, way, wideAngle, 0.0D);
/*  177:     */   }
/*  178:     */   
/*  179:     */   public static final void createWideShot(EntityLivingBase user, Vec3 pos, Vec3 angle, double speed, ShotData shot, int way, float wideAngle)
/*  180:     */   {
/*  181: 521 */     createWideShot(user, pos, angle, speed, speed, 0.0D, shot, way, wideAngle);
/*  182:     */   }
/*  183:     */   
/*  184:     */   public static final void createCircleShot(EntityLivingBase user, Entity source, Vec3 pos, Vec3 angle, float rotationSpeed, int rotationEnd, double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity, ShotData shot, int way, double distance, float baseAngle)
/*  185:     */   {
/*  186: 547 */     if (way % 2 == 0) {
/*  187: 549 */       baseAngle += 360.0F / (way * 2.0F);
/*  188:     */     }
/*  189: 552 */     createWideShot(user, source, pos, angle, rotationSpeed, rotationEnd, firstSpeed, limitSpeed, acceleration, gravity, shot, way, 360.0F - 360.0F / way, distance, baseAngle);
/*  190:     */   }
/*  191:     */   
/*  192:     */   public static final void createCircleShot(EntityLivingBase user, Entity source, Vec3 pos, Vec3 angle, float rotationSpeed, int rotationEnd, double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity, ShotData shot, int way, double distance)
/*  193:     */   {
/*  194: 581 */     createCircleShot(user, source, pos, angle, rotationSpeed, rotationEnd, firstSpeed, limitSpeed, acceleration, gravity, shot, way, distance, 0.0F);
/*  195:     */   }
/*  196:     */   
/*  197:     */   public static final void createCircleShot(EntityLivingBase user, Entity source, Vec3 pos, Vec3 angle, double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity, ShotData shot, int way, double distance, float baseAngle)
/*  198:     */   {
/*  199: 610 */     createCircleShot(user, source, pos, angle, 0.0F, 9999, firstSpeed, limitSpeed, acceleration, gravity, shot, way, distance, baseAngle);
/*  200:     */   }
/*  201:     */   
/*  202:     */   public static final void createCircleShot(EntityLivingBase user, Vec3 pos, Vec3 angle, double speed, ShotData shot, int way)
/*  203:     */   {
/*  204: 627 */     createCircleShot(user, pos, angle, speed, speed, 0.0D, shot, way);
/*  205:     */   }
/*  206:     */   
/*  207:     */   public static final void createCircleShot(EntityLivingBase user, Vec3 pos, Vec3 angle, double firstSpeed, double limitSpeed, double acceleration, ShotData shot, int way)
/*  208:     */   {
/*  209: 643 */     createCircleShot(user, user, pos, angle, firstSpeed, limitSpeed, acceleration, gravity_Zero(), shot, way, shot.size, 0.0F);
/*  210:     */   }
/*  211:     */   
/*  212:     */   public static final void createRingShot(EntityLivingBase user, Entity source, Vec3 pos, Vec3 angle, float rotationSpeed, int rotationEnd, double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity, ShotData shot, int way, double distance, float ringSpan, float ringBaseAngle)
/*  213:     */   {
/*  214: 672 */     ringSpan /= 2.0F;
/*  215: 673 */     float yaw = getYawFromVector(-angle.xCoord, angle.zCoord);
/*  216: 674 */     float pitch = getPitchFromVector(-angle.xCoord, -angle.yCoord, angle.zCoord);
/*  217: 675 */     Vec3 baseVec = getVecFromAngle(yaw, pitch + ringSpan, 1.0D);
/*  218: 676 */     Vec3 baseOverVec = getVecFromAngle(yaw, pitch + ringSpan + 90.0F, 1.0D);
/*  219: 677 */     Vec3 overVec = getVecFromAngle(yaw, pitch + 90.0F, 1.0D);
/*  220:     */     
/*  221: 679 */     float angle2 = ringBaseAngle;
/*  222: 680 */     float span = 360.0F / way;
/*  223:     */     
/*  224:     */ 
/*  225:     */ 
/*  226: 684 */     int color = new Random().nextInt(7);
/*  227: 685 */     int[] rainbowPattern = { 0, 6, 3, 2, 5, 1, 4 };
/*  228: 686 */     boolean random = false;
/*  229: 687 */     boolean rainbow = false;
/*  230: 688 */     ShotData shot2 = shot;
/*  231: 690 */     if (shot2 != null) {
/*  232: 692 */       if (shot2.color == 8) {
/*  233: 694 */         random = true;
/*  234: 696 */       } else if (shot2.color == 9) {
/*  235: 698 */         rainbow = true;
/*  236:     */       }
/*  237:     */     }
/*  238: 702 */     if (random) {
/*  239: 704 */       shot2.color = new Random().nextInt(8);
/*  240:     */     }
/*  241: 712 */     for (int i = 0; i < way; i++)
/*  242:     */     {
/*  243: 714 */       if (rainbow)
/*  244:     */       {
/*  245: 716 */         shot2.color = rainbowPattern[(color % 7)];
/*  246: 717 */         color++;
/*  247:     */       }
/*  248: 719 */       Vec3 movingVec = getVectorFromRotation(angle.xCoord, angle.yCoord, angle.zCoord, baseVec.xCoord, baseVec.yCoord, baseVec.zCoord, angle2);
/*  249: 720 */       Vec3 extendVec = getVectorFromRotation(angle.xCoord, angle.yCoord, angle.zCoord, overVec.xCoord, overVec.yCoord, overVec.zCoord, angle2);
/*  250: 721 */       Vec3 overVec2 = getVectorFromRotation(angle, baseOverVec, angle2);
/*  251: 722 */       createShot(user, source, Vec3.createVectorHelper(pos.xCoord + extendVec.xCoord * distance, pos.yCoord + extendVec.yCoord * distance, pos.zCoord + extendVec.zCoord * distance), Vec3.createVectorHelper(movingVec.xCoord, movingVec.yCoord, movingVec.zCoord), angle2, overVec2, rotationSpeed, rotationEnd, firstSpeed, limitSpeed, acceleration, gravity, shot2);
/*  252:     */       
/*  253:     */ 
/*  254:     */ 
/*  255: 726 */       angle2 += span;
/*  256:     */     }
/*  257: 728 */     if (random) {
/*  258: 730 */       shot.color = 8;
/*  259: 732 */     } else if (rainbow) {
/*  260: 734 */       shot.color = 9;
/*  261:     */     }
/*  262:     */   }
/*  263:     */   
/*  264:     */   public static final void createRingShot(EntityLivingBase user, Vec3 pos, Vec3 angle, double speed, ShotData shot, int way, double distance, float ringSpan, float ringBaseAngle)
/*  265:     */   {
/*  266: 755 */     ShotData shot2 = shot;
/*  267: 756 */     createRingShot(user, user, pos, angle, 0.0F, 9999, speed, speed, 0.0D, 
/*  268: 757 */       gravity_Zero(), shot2, way, distance, ringSpan, ringBaseAngle);
/*  269:     */   }
/*  270:     */   
/*  271:     */   public static final void createRandomRingShot(EntityLivingBase user, Entity source, Vec3 pos, Vec3 angle, float rotationSpeed, int rotationEnd, double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity, ShotData shot, int num, double distance, float ringSpan)
/*  272:     */   {
/*  273: 786 */     float ringMaxSpan = ringSpan / 2.0F;
/*  274: 787 */     float yaw = getYawFromVector(-angle.xCoord, angle.zCoord);
/*  275: 788 */     float pitch = getPitchFromVector(angle.xCoord, angle.yCoord, angle.zCoord);
/*  276:     */     
/*  277: 790 */     Vec3 overVec = getVecFromAngle(-yaw, pitch + 90.0F, 1.0D);
/*  278:     */     
/*  279:     */ 
/*  280: 793 */     Random rand = new Random();
/*  281:     */     
/*  282:     */ 
/*  283:     */ 
/*  284:     */ 
/*  285: 798 */     int color = new Random().nextInt(7);
/*  286: 799 */     int[] rainbowPattern = { 0, 6, 3, 2, 5, 1, 4 };
/*  287: 800 */     boolean random = false;
/*  288: 801 */     boolean rainbow = false;
/*  289: 802 */     if (shot != null) {
/*  290: 804 */       if (shot.color == 8) {
/*  291: 806 */         random = true;
/*  292: 808 */       } else if (shot.color == 9) {
/*  293: 810 */         rainbow = true;
/*  294:     */       }
/*  295:     */     }
/*  296: 814 */     if (random) {
/*  297: 816 */       shot.color = new Random().nextInt(8);
/*  298:     */     }
/*  299: 820 */     for (int i = 0; i < num; i++)
/*  300:     */     {
/*  301: 822 */       if (rainbow)
/*  302:     */       {
/*  303: 824 */         shot.color = rainbowPattern[(color % 7)];
/*  304: 825 */         color++;
/*  305:     */       }
/*  306: 828 */       float angle2 = rand.nextFloat() * 360.0F;
/*  307: 829 */       Vec3 baseVec = getVecFromAngle(-yaw, pitch + rand.nextFloat() * ringMaxSpan, 1.0D);
/*  308: 830 */       Vec3 movingVec = getVectorFromRotation(-angle.xCoord, -angle.yCoord, angle.zCoord, baseVec.xCoord, baseVec.yCoord, baseVec.zCoord, angle2);
/*  309: 831 */       Vec3 extendVec = getVectorFromRotation(-angle.xCoord, -angle.yCoord, angle.zCoord, overVec.xCoord, overVec.yCoord, overVec.zCoord, angle2);
/*  310: 832 */       createShot(user, source, Vec3.createVectorHelper(pos.xCoord - extendVec.xCoord * distance, pos.yCoord - extendVec.yCoord * distance, pos.zCoord + extendVec.zCoord * distance), Vec3.createVectorHelper(-movingVec.xCoord, -movingVec.yCoord, movingVec.zCoord), angle2, angle, rotationSpeed, rotationEnd, firstSpeed, limitSpeed, acceleration, gravity, shot);
/*  311:     */     }
/*  312: 837 */     if (random) {
/*  313: 839 */       shot.color = 8;
/*  314: 841 */     } else if (rainbow) {
/*  315: 843 */       shot.color = 9;
/*  316:     */     }
/*  317:     */   }
/*  318:     */   
/*  319:     */   public static final void createRandomRingShot(EntityLivingBase user, Vec3 pos, Vec3 angle, double speed, ShotData shot, int num, float ringSpan)
/*  320:     */   {
/*  321: 861 */     createRandomRingShot(user, pos, angle, speed, speed, 0.0D, shot, num, 0.0D, ringSpan);
/*  322:     */   }
/*  323:     */   
/*  324:     */   public static final void createRandomRingShot(EntityLivingBase user, Vec3 pos, Vec3 angle, double firstSpeed, double limitSpeed, double acceleration, ShotData shot, int num, double distance, float ringSpan)
/*  325:     */   {
/*  326: 885 */     createRandomRingShot(user, user, pos, angle, 0.0F, 9999, firstSpeed, limitSpeed, acceleration, 
/*  327: 886 */       gravity_Zero(), shot, num, distance, ringSpan);
/*  328:     */   }
/*  329:     */   
/*  330:     */   public static final void createSphereShot(EntityLivingBase user, Entity source, Vec3 pos, Vec3 angle, float angleZ, Vec3 rotate, float rotationSpeed, int rotationEnd, double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity, ShotData shot, int way, double distance, float baseAngle)
/*  331:     */   {
/*  332: 917 */     if (way >= ways.length) {
/*  333: 919 */       return;
/*  334:     */     }
/*  335: 922 */     Vec3 frontPos = pos(pos.xCoord + angle.xCoord * distance, pos.yCoord + angle.yCoord * distance, pos.zCoord + angle.zCoord * distance);
/*  336: 923 */     Vec3 backPos = pos(pos.xCoord - angle.xCoord * distance, pos.yCoord - angle.yCoord * distance, pos.zCoord - angle.zCoord * distance);
/*  337:     */     
/*  338:     */ 
/*  339:     */ 
/*  340: 927 */     float angleBase = 0.0F;
/*  341: 928 */     float angleSpan = 0.0F;
/*  342: 929 */     if (ways[way].length >= 2) {
/*  343: 931 */       angleSpan = 360.0F / (ways[way].length - 1);
/*  344:     */     }
/*  345: 933 */     boolean flagFB = false;
/*  346: 934 */     int oldWay = 0;
/*  347: 935 */     float slope = 0.0F;
/*  348:     */     
/*  349: 937 */     ShotData shot2 = shot;
/*  350: 938 */     if (shot2.color == 8) {
/*  351: 940 */       shot2.color = new Random().nextInt(8);
/*  352:     */     }
/*  353: 943 */     for (int i = 0; i < ways[way].length; i++)
/*  354:     */     {
/*  355: 946 */       if (ways[way][i] == 1)
/*  356:     */       {
/*  357: 948 */         if (!flagFB)
/*  358:     */         {
/*  359: 950 */           createShot(user, source, frontPos, angle, angleZ, rotate, rotationSpeed, rotationEnd, firstSpeed, limitSpeed, acceleration, gravity, shot2);
/*  360:     */           
/*  361:     */ 
/*  362:     */ 
/*  363: 954 */           flagFB = true;
/*  364:     */         }
/*  365:     */         else
/*  366:     */         {
/*  367: 958 */           createShot(user, source, backPos, angle(-angle.xCoord, -angle.yCoord, -angle.zCoord), angleZ, rotate, rotationSpeed, rotationEnd, firstSpeed, limitSpeed, acceleration, gravity, shot2);
/*  368:     */         }
/*  369:     */       }
/*  370:     */       else
/*  371:     */       {
/*  372: 966 */         createRingShot(user, source, pos, angle, rotationSpeed, rotationEnd, firstSpeed, limitSpeed, acceleration, gravity, shot2, ways[way][i], distance, angleBase, baseAngle + slope);
/*  373: 967 */         slope += 180.0F / ways[way].length;
/*  374:     */       }
/*  375: 970 */       oldWay = ways[way].length;
/*  376: 971 */       angleBase += angleSpan;
/*  377:     */     }
/*  378:     */   }
/*  379:     */   
/*  380:     */   public static final void createSphereShot(EntityLivingBase user, Entity source, Vec3 pos, Vec3 angle, float angleZ, double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity, ShotData shot, int way, double distance, float baseAngle)
/*  381:     */   {
/*  382: 998 */     createSphereShot(user, source, pos, angle, angleZ, rotate_Default(), 0.0F, 9999, firstSpeed, limitSpeed, acceleration, gravity, shot, way, distance, baseAngle);
/*  383:     */   }
/*  384:     */   
/*  385:     */   public static final void createSphereShot(EntityLivingBase user, Vec3 pos, Vec3 angle, double speed, ShotData shot, int way, float baseAngle)
/*  386:     */   {
/*  387:1016 */     createSphereShot(user, user, pos, angle, 0.0F, rotate_Default(), 0.0F, 9999, speed, speed, 0.0D, gravity_Zero(), shot, way, 0.0D, baseAngle);
/*  388:     */   }
/*  389:     */   
/*  390:     */   public static final EntityTHLaser createLaserA(EntityLivingBase user, Entity source, Vec3 pos, Vec3 angle, double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity, LaserData laser)
/*  391:     */   {
/*  392:1041 */     if (laser.color == 8) {
/*  393:1043 */       laser.color = new Random().nextInt(8);
/*  394:1045 */     } else if (laser.color == 9) {
/*  395:1047 */       laser.color = new Random().nextInt(7);
/*  396:     */     }
/*  397:1049 */     EntityTHLaser entityLaser = new EntityTHLaser(source.worldObj, user, source, pos, angle, 0.0F, rotate_Default(), 0.0F, 9999, firstSpeed, limitSpeed, acceleration, gravity, laser);
/*  398:1052 */     if (!source.worldObj.isRemote) {
/*  399:1055 */       source.worldObj.spawnEntityInWorld(entityLaser);
/*  400:     */     }
/*  401:1057 */     return entityLaser;
/*  402:     */   }
/*  403:     */   
/*  404:     */   public static final EntityTHLaser createLaserA(EntityLivingBase user, Vec3 pos, Vec3 angle, double speed, LaserData laser)
/*  405:     */   {
/*  406:1085 */     return createLaserA(user, pos, angle, speed, speed, 0.0D, laser);
/*  407:     */   }
/*  408:     */   
/*  409:     */   public static final EntityTHLaser createLaserA(EntityLivingBase user, Vec3 pos, Vec3 angle, double firstSpeed, double limitSpeed, double acceleration, LaserData laser)
/*  410:     */   {
/*  411:1103 */     return createLaserA(user, user, pos, angle, firstSpeed, limitSpeed, acceleration, gravity_Zero(), laser);
/*  412:     */   }
/*  413:     */   
/*  414:     */   public static final void createWideLaserA(EntityLivingBase user, Entity source, Vec3 pos, Vec3 angle, double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity, LaserData laser, int way, float wideAngle, double distance)
/*  415:     */   {
/*  416:1127 */     float yaw = getYawFromVector(angle.xCoord, angle.zCoord);
/*  417:1128 */     float pitch = getPitchFromVector(angle.xCoord, angle.yCoord, angle.zCoord);
/*  418:1129 */     Vec3 rotateVec = getVecFromAngle(-yaw, -pitch + 90.0F, 1.0D);
/*  419:     */     
/*  420:1131 */     float angle2 = -wideAngle / 2.0F;
/*  421:1132 */     float span = wideAngle / (way - 1);
/*  422:     */     
/*  423:1134 */     boolean rainbow = false;
/*  424:1135 */     int color = new Random().nextInt(7);
/*  425:1136 */     int[] rainbowPattern = { 0, 6, 3, 2, 5, 1, 4 };
/*  426:1137 */     boolean random = false;
/*  427:1138 */     if (laser.color == 8) {
/*  428:1140 */       random = true;
/*  429:1142 */     } else if (laser.color == 9) {
/*  430:1144 */       rainbow = true;
/*  431:     */     }
/*  432:1147 */     for (int i = 0; i < way; i++)
/*  433:     */     {
/*  434:1149 */       if (random)
/*  435:     */       {
/*  436:1151 */         laser.color = new Random().nextInt(8);
/*  437:     */       }
/*  438:1153 */       else if (rainbow)
/*  439:     */       {
/*  440:1155 */         laser.color = rainbowPattern[(color % 7)];
/*  441:1156 */         color++;
/*  442:     */       }
/*  443:1158 */       Vec3 angleVec = getVectorFromRotation(rotateVec.xCoord, rotateVec.yCoord, rotateVec.zCoord, angle.xCoord, angle.yCoord, angle.zCoord, angle2);
/*  444:1159 */       createLaserA(user, source, pos(pos.xCoord + angleVec.xCoord * distance, pos.yCoord + angleVec.yCoord * distance, pos.zCoord + angleVec.zCoord * distance), angleVec, firstSpeed, limitSpeed, acceleration, gravity, laser);
/*  445:     */       
/*  446:1161 */       angle2 += span;
/*  447:     */     }
/*  448:     */   }
/*  449:     */   
/*  450:     */   public static final void createWideLaserA(EntityLivingBase user, Vec3 pos, Vec3 angle, double speed, LaserData laser, int way, float wideAngle)
/*  451:     */   {
/*  452:1177 */     createWideLaserA(user, pos, angle, speed, speed, 0.0D, laser, way, wideAngle);
/*  453:     */   }
/*  454:     */   
/*  455:     */   public static final void createWideLaserA(EntityLivingBase user, Vec3 pos, Vec3 angle, double firstSpeed, double limitSpeed, double acceleration, LaserData laser, int way, float wideAngle)
/*  456:     */   {
/*  457:1194 */     createWideLaserA(user, user, pos, angle, firstSpeed, limitSpeed, acceleration, 
/*  458:1195 */       gravity_Zero(), laser, way, wideAngle, 0.0D);
/*  459:     */   }
/*  460:     */   
/*  461:     */   public static final void createCircleLaserA(EntityLivingBase user, Entity source, Vec3 pos, Vec3 angle, double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity, LaserData laser, int way, double distance)
/*  462:     */   {
/*  463:1216 */     createWideLaserA(user, source, pos, angle, firstSpeed, limitSpeed, acceleration, gravity, laser, way + 1, 360.0F, distance);
/*  464:     */   }
/*  465:     */   
/*  466:     */   public static final void createCircleLaserA(EntityLivingBase user, Vec3 pos, Vec3 angle, double speed, LaserData laser, int way)
/*  467:     */   {
/*  468:1233 */     createCircleLaserA(user, pos, angle, speed, speed, 0.0D, laser, way);
/*  469:     */   }
/*  470:     */   
/*  471:     */   public static final void createCircleLaserA(EntityLivingBase user, Vec3 pos, Vec3 angle, double firstSpeed, double limitSpeed, double acceleration, LaserData laser, int way)
/*  472:     */   {
/*  473:1249 */     createCircleLaserA(user, user, pos, angle, firstSpeed, limitSpeed, acceleration, 
/*  474:1250 */       gravity_Zero(), laser, way, 0.0D);
/*  475:     */   }
/*  476:     */   
/*  477:     */   public static final void createRingLaserA(EntityLivingBase user, Entity source, Vec3 pos, Vec3 angle, double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity, LaserData laser, int way, double distance, float ringSpan, float ringBaseAngle)
/*  478:     */   {
/*  479:1276 */     ringSpan /= 2.0F;
/*  480:1277 */     float yaw = getYawFromVector(-angle.xCoord, angle.zCoord);
/*  481:1278 */     float pitch = getPitchFromVector(angle.xCoord, angle.yCoord, angle.zCoord);
/*  482:1279 */     Vec3 baseVec = getVecFromAngle(-yaw, pitch + ringSpan, 1.0D);
/*  483:1280 */     Vec3 overVec = getVecFromAngle(-yaw, pitch + 90.0F, 1.0D);
/*  484:     */     
/*  485:1282 */     float angle2 = ringBaseAngle;
/*  486:1283 */     float span = 360.0F / way;
/*  487:     */     
/*  488:     */ 
/*  489:     */ 
/*  490:1287 */     int color = new Random().nextInt(7);
/*  491:1288 */     int[] rainbowPattern = { 0, 6, 3, 2, 5, 1, 4 };
/*  492:1289 */     boolean random = false;
/*  493:1290 */     boolean rainbow = false;
/*  494:1291 */     LaserData laser2 = laser;
/*  495:1292 */     if (laser2.color == 8) {
/*  496:1294 */       random = true;
/*  497:1296 */     } else if (laser2.color == 9) {
/*  498:1298 */       rainbow = true;
/*  499:     */     }
/*  500:1301 */     if (random) {
/*  501:1303 */       laser2.color = new Random().nextInt(8);
/*  502:     */     }
/*  503:1307 */     for (int i = 0; i < way; i++)
/*  504:     */     {
/*  505:1309 */       if (rainbow)
/*  506:     */       {
/*  507:1311 */         laser2.color = rainbowPattern[(color % 7)];
/*  508:1312 */         color++;
/*  509:     */       }
/*  510:1315 */       Vec3 movingVec = getVectorFromRotation(-angle.xCoord, -angle.yCoord, angle.zCoord, baseVec.xCoord, baseVec.yCoord, baseVec.zCoord, angle2);
/*  511:1316 */       Vec3 extendVec = getVectorFromRotation(-angle.xCoord, -angle.yCoord, angle.zCoord, overVec.xCoord, overVec.yCoord, overVec.zCoord, angle2);
/*  512:1317 */       createLaserA(user, source, pos(pos.xCoord - extendVec.xCoord * distance, pos.yCoord - extendVec.yCoord * distance, pos.zCoord + extendVec.zCoord * distance), angle(-movingVec.xCoord, -movingVec.yCoord, movingVec.zCoord), firstSpeed, limitSpeed, acceleration, gravity, laser2);
/*  513:     */       
/*  514:     */ 
/*  515:1320 */       angle2 += span;
/*  516:     */     }
/*  517:1322 */     if (random) {
/*  518:1324 */       laser.color = 8;
/*  519:1326 */     } else if (rainbow) {
/*  520:1328 */       laser.color = 9;
/*  521:     */     }
/*  522:     */   }
/*  523:     */   
/*  524:     */   public static final void createRingLaserA(EntityLivingBase user, Vec3 pos, Vec3 angle, double firstSpeed, double limitSpeed, double acceleration, LaserData laser, int way, double distance, float ringSpan, float ringBaseAngle)
/*  525:     */   {
/*  526:1352 */     createRingLaserA(user, user, pos, angle, firstSpeed, limitSpeed, acceleration, gravity_Zero(), laser, way, distance, ringSpan, ringBaseAngle);
/*  527:     */   }
/*  528:     */   
/*  529:     */   public static final void createRandomRingLaser(EntityLivingBase user, Entity source, Vec3 pos, Vec3 angle, double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity, LaserData laser, int num, double distance, float ringSpan)
/*  530:     */   {
/*  531:1392 */     float ringMaxSpan = ringSpan / 2.0F;
/*  532:1393 */     float yaw = getYawFromVector(-angle.xCoord, angle.zCoord);
/*  533:1394 */     float pitch = getPitchFromVector(angle.xCoord, angle.yCoord, angle.zCoord);
/*  534:     */     
/*  535:1396 */     Vec3 overVec = getVecFromAngle(-yaw, pitch + 90.0F, 1.0D);
/*  536:     */     
/*  537:     */ 
/*  538:1399 */     Random rand = new Random();
/*  539:1402 */     for (int i = 0; i < num; i++)
/*  540:     */     {
/*  541:1404 */       float angle2 = rand.nextFloat() * 360.0F;
/*  542:1405 */       Vec3 baseVec = getVecFromAngle(-yaw, pitch + rand.nextFloat() * ringMaxSpan, 1.0D);
/*  543:1406 */       Vec3 movingVec = getVectorFromRotation(-angle.xCoord, -angle.yCoord, angle.zCoord, baseVec.xCoord, baseVec.yCoord, baseVec.zCoord, angle2);
/*  544:1407 */       Vec3 extendVec = getVectorFromRotation(-angle.xCoord, -angle.yCoord, angle.zCoord, overVec.xCoord, overVec.yCoord, overVec.zCoord, angle2);
/*  545:     */       
/*  546:1409 */       createLaserA(user, source, pos(pos.xCoord - extendVec.xCoord * distance, pos.yCoord - extendVec.yCoord * distance, pos.zCoord + extendVec.zCoord * distance), angle(-movingVec.xCoord, -movingVec.yCoord, movingVec.zCoord), firstSpeed, limitSpeed, acceleration, gravity, laser);
/*  547:     */     }
/*  548:     */   }
/*  549:     */   
/*  550:     */   public static final void createSphereLaserA(EntityLivingBase user, Entity source, Vec3 pos, Vec3 angle, double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity, LaserData laser, int way, double distance, float baseAngle)
/*  551:     */   {
/*  552:1438 */     if (way >= ways.length) {
/*  553:1440 */       return;
/*  554:     */     }
/*  555:1443 */     Vec3 frontPos = pos(pos.xCoord + angle.xCoord * distance, pos.yCoord + angle.yCoord * distance, pos.zCoord + angle.zCoord * distance);
/*  556:1444 */     Vec3 backPos = pos(pos.xCoord - angle.xCoord * distance, pos.yCoord - angle.yCoord * distance, pos.zCoord - angle.zCoord * distance);
/*  557:     */     
/*  558:     */ 
/*  559:     */ 
/*  560:1448 */     float angleBase = 0.0F;
/*  561:1449 */     float angleSpan = 0.0F;
/*  562:1450 */     if (ways[way].length >= 2) {
/*  563:1452 */       angleSpan = 360.0F / (ways[way].length - 1);
/*  564:     */     }
/*  565:1454 */     boolean flagFB = false;
/*  566:1455 */     int oldWay = 0;
/*  567:1456 */     float slope = 0.0F;
/*  568:     */     
/*  569:1458 */     LaserData laser2 = laser;
/*  570:1459 */     if (laser2.color == 8) {
/*  571:1461 */       laser2.color = new Random().nextInt(8);
/*  572:     */     }
/*  573:1464 */     for (int i = 0; i < ways[way].length; i++)
/*  574:     */     {
/*  575:1467 */       if (ways[way][i] == 1)
/*  576:     */       {
/*  577:1469 */         if (!flagFB)
/*  578:     */         {
/*  579:1471 */           createLaserA(user, source, frontPos, angle, firstSpeed, limitSpeed, acceleration, gravity, laser2);
/*  580:     */           
/*  581:     */ 
/*  582:1474 */           flagFB = true;
/*  583:     */         }
/*  584:     */         else
/*  585:     */         {
/*  586:1478 */           createLaserA(user, source, frontPos, angle(-angle.xCoord, -angle.yCoord, -angle.zCoord), firstSpeed, limitSpeed, acceleration, gravity, laser2);
/*  587:     */         }
/*  588:     */       }
/*  589:     */       else
/*  590:     */       {
/*  591:1485 */         createRingLaserA(user, source, pos, angle, firstSpeed, limitSpeed, acceleration, gravity, laser2, ways[way][i], distance, angleBase, baseAngle + slope);
/*  592:1486 */         slope += 180.0F / ways[way].length;
/*  593:     */       }
/*  594:1489 */       oldWay = ways[way].length;
/*  595:1490 */       angleBase += angleSpan;
/*  596:     */     }
/*  597:     */   }
/*  598:     */   
/*  599:     */   public static final EntityTHSetLaser createLaserB(EntityLivingBase user, Entity setting, Vec3 pos, Vec3 angle, Vec3 rotate, float rotateYawSpeed, int rotationEnd, LaserData laser, Entity set, double setLength, double setYOffset)
/*  600:     */   {
/*  601:1514 */     EntityTHSetLaser entityLaser = new EntityTHSetLaser(setting.worldObj, user, setting, pos, angle, 0.0F, rotate, rotateYawSpeed, rotationEnd, laser, set, setLength, setYOffset);
/*  602:1518 */     if (!setting.worldObj.isRemote) {
/*  603:1521 */       setting.worldObj.spawnEntityInWorld(entityLaser);
/*  604:     */     }
/*  605:1523 */     return entityLaser;
/*  606:     */   }
/*  607:     */   
/*  608:     */   public static EntityNuclearShot createNuclearShot(EntityLivingBase user, Entity source, Vec3 pos, Vec3 angle, float slope, Vec3 rotate, float rotationSpeed, int rotationEnd, double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity, ShotData shot)
/*  609:     */   {
/*  610:1551 */     EntityNuclearShot nuclearShot = new EntityNuclearShot(source.worldObj, user, source, pos, angle, slope, rotate, rotationSpeed, rotationEnd, firstSpeed, limitSpeed, firstSpeed, gravity, shot);
/*  611:1553 */     if (!source.worldObj.isRemote) {
/*  612:1555 */       source.worldObj.spawnEntityInWorld(nuclearShot);
/*  613:     */     }
/*  614:1557 */     return nuclearShot;
/*  615:     */   }
/*  616:     */   
/*  617:     */   public static void playShotSound(Entity entity)
/*  618:     */   {
/*  619:1570 */     entity.worldObj.playSoundAtEntity(entity, "random.bow", 2.0F, 1.2F);
/*  620:     */   }
/*  621:     */   
/*  622:     */   public static void explosionEffect(World world, double posX, double posY, double posZ, float explosionSize)
/*  623:     */   {
/*  624:1583 */     world.playSoundEffect(posX, posY, posZ, "random.explosion", 2.0F, 3.0F);
/*  625:1585 */     if (explosionSize >= 2.0F) {
/*  626:1587 */       world.spawnParticle("hugeexplosion", posX, posY, posZ, 1.0D, 0.0D, 0.0D);
/*  627:     */     } else {
/*  628:1591 */       world.spawnParticle("largeexplode", posX, posY, posZ, 1.0D, 0.0D, 0.0D);
/*  629:     */     }
/*  630:     */   }
/*  631:     */   
/*  632:     */   public static void explosionEffect2(World world, double posX, double posY, double posZ, float explosionSize)
/*  633:     */   {
/*  634:1605 */     world.playSoundEffect(posX, posY, posZ, "fireworks.blast", 2.0F, 3.0F);
/*  635:1607 */     if (explosionSize >= 2.0F) {
/*  636:1609 */       world.spawnParticle("hugeexplosion", posX, posY, posZ, 1.0D, 0.0D, 0.0D);
/*  637:     */     } else {
/*  638:1613 */       world.spawnParticle("largeexplode", posX, posY, posZ, 1.0D, 0.0D, 0.0D);
/*  639:     */     }
/*  640:     */   }
/*  641:     */   
/*  642:     */   public static int danmakuRemove(Entity centerEntity, double range, String target, boolean isDropBounus)
/*  643:     */   {
/*  644:1635 */     int count = 0;
/*  645:1636 */     List list = centerEntity.worldObj.getEntitiesWithinAABBExcludingEntity(centerEntity, centerEntity.boundingBox.addCoord(0.0D, 0.0D, 0.0D).expand(range, range, range));
/*  646:1637 */     for (int k = 0; k < list.size(); k++)
/*  647:     */     {
/*  648:1639 */       Entity entity = (Entity)list.get(k);
/*  649:1640 */       if ((entity instanceof EntityTHShot))
/*  650:     */       {
/*  651:1642 */         EntityTHShot entityTHShot = (EntityTHShot)entity;
/*  652:1643 */         if (target.equals("ALL"))
/*  653:     */         {
/*  654:1645 */           if (isDropBounus) {
/*  655:1647 */             entityTHShot.shotFinishBonus();
/*  656:     */           } else {
/*  657:1651 */             entityTHShot.setDead();
/*  658:     */           }
/*  659:1653 */           count++;
/*  660:     */         }
/*  661:1655 */         else if (target.equals("Enemy"))
/*  662:     */         {
/*  663:1657 */           if (!(entityTHShot.user instanceof EntityPlayer))
/*  664:     */           {
/*  665:1659 */             if (isDropBounus) {
/*  666:1661 */               entityTHShot.shotFinishBonus();
/*  667:     */             } else {
/*  668:1665 */               entityTHShot.setDead();
/*  669:     */             }
/*  670:1667 */             count++;
/*  671:     */           }
/*  672:     */         }
/*  673:1670 */         else if (target.equals("Player"))
/*  674:     */         {
/*  675:1672 */           if ((entityTHShot.user instanceof EntityPlayer))
/*  676:     */           {
/*  677:1674 */             if (isDropBounus) {
/*  678:1676 */               entityTHShot.shotFinishBonus();
/*  679:     */             } else {
/*  680:1680 */               entityTHShot.setDead();
/*  681:     */             }
/*  682:1682 */             count++;
/*  683:     */           }
/*  684:     */         }
/*  685:1685 */         else if (target.equals("Other"))
/*  686:     */         {
/*  687:1687 */           if (entityTHShot.user != centerEntity)
/*  688:     */           {
/*  689:1689 */             if (isDropBounus) {
/*  690:1691 */               entityTHShot.shotFinishBonus();
/*  691:     */             } else {
/*  692:1695 */               entityTHShot.setDead();
/*  693:     */             }
/*  694:1697 */             count++;
/*  695:     */           }
/*  696:     */         }
/*  697:     */       }
/*  698:     */     }
/*  699:1702 */     return count;
/*  700:     */   }
/*  701:     */   
/*  702:     */   public static void banishExplosion(Entity deadEntity, double range, float maxDamage)
/*  703:     */   {
/*  704:1713 */     List list = deadEntity.worldObj.getEntitiesWithinAABBExcludingEntity(deadEntity, deadEntity.boundingBox.addCoord(0.0D, 0.0D, 0.0D).expand(range, range, range));
/*  705:1714 */     for (int k = 0; k < list.size(); k++)
/*  706:     */     {
/*  707:1716 */       Entity entity = (Entity)list.get(k);
/*  708:1718 */       if (((entity instanceof EntityTHFairy)) && (!(entity instanceof EntityFamiliar)))
/*  709:     */       {
/*  710:1720 */         EntityTHFairy fairy = (EntityTHFairy)entity;
/*  711:1721 */         double distance = entity.getDistanceToEntity(deadEntity);
/*  712:1723 */         if (distance <= range)
/*  713:     */         {
/*  714:1725 */           float damage = maxDamage * (1.0F - (float)(distance / range));
/*  715:1726 */           if (damage < 2.0F) {
/*  716:1728 */             damage = 2.0F;
/*  717:     */           }
/*  718:1730 */           fairy.attackEntityFrom(DamageSource.causeIndirectMagicDamage(entity, deadEntity), damage);
/*  719:     */         }
/*  720:     */       }
/*  721:     */     }
/*  722:     */   }
/*  723:     */   
/*  724:     */   public static Vec3 pos(double x, double y, double z)
/*  725:     */   {
/*  726:1753 */     return Vec3.createVectorHelper(x, y, z);
/*  727:     */   }
/*  728:     */   
/*  729:     */   public static Vec3 pos_Entity(Entity entity)
/*  730:     */   {
/*  731:1764 */     return pos(entity.posX, entity.posY, entity.posZ);
/*  732:     */   }
/*  733:     */   
/*  734:     */   public static Vec3 pos_Entity(Entity entity, double height)
/*  735:     */   {
/*  736:1776 */     return pos(entity.posX, entity.posY + height, entity.posZ);
/*  737:     */   }
/*  738:     */   
/*  739:     */   public static Vec3 pos_Living(EntityLivingBase living)
/*  740:     */   {
/*  741:1787 */     return pos(living.posX, getPosYFromEye(living), living.posZ);
/*  742:     */   }
/*  743:     */   
/*  744:     */   public static Vec3 pos_Distance(Vec3 pos, Vec3 angle, double distance)
/*  745:     */   {
/*  746:1799 */     return pos(pos.xCoord + angle.xCoord * distance, pos.yCoord + angle.yCoord * distance, pos.zCoord + angle.zCoord * distance);
/*  747:     */   }
/*  748:     */   
/*  749:     */   public static Vec3 pos_Random(Vec3 pos, double range)
/*  750:     */   {
/*  751:1810 */     Random rand = new Random();
/*  752:1811 */     Vec3 rand_pos = angle(rand.nextFloat() * 360.0F, rand.nextFloat() * 180.0F - 90.0F);
/*  753:1812 */     return pos.addVector(rand_pos.xCoord, rand_pos.yCoord, rand_pos.zCoord);
/*  754:     */   }
/*  755:     */   
/*  756:     */   public static Vec3 angle(double x, double y, double z)
/*  757:     */   {
/*  758:1824 */     return Vec3.createVectorHelper(x, y, z);
/*  759:     */   }
/*  760:     */   
/*  761:     */   public static Vec3 angle(float yaw, float pitch)
/*  762:     */   {
/*  763:1835 */     return getVecFromAngle(yaw, pitch);
/*  764:     */   }
/*  765:     */   
/*  766:     */   public static Vec3 angle_ToPos(Vec3 posA, Vec3 posB)
/*  767:     */   {
/*  768:1846 */     return getVectorNomalize(pos(posB.xCoord - posA.xCoord, posB.yCoord - posA.yCoord, posB.zCoord - posA.zCoord));
/*  769:     */   }
/*  770:     */   
/*  771:     */   public static Vec3 angle_Random()
/*  772:     */   {
/*  773:1855 */     Random rand = new Random();
/*  774:1856 */     return angle(rand.nextFloat() * 360.0F, rand.nextFloat() * 180.0F - 90.0F);
/*  775:     */   }
/*  776:     */   
/*  777:     */   public static Vec3 angle_LimitRandom(Vec3 angle, float limitAngle)
/*  778:     */   {
/*  779:1867 */     Vec3 rotate = rotate_Random();
/*  780:1868 */     Random rand = new Random();
/*  781:1869 */     return getVectorFromRotation(rotate, angle, rand.nextFloat() * limitAngle - limitAngle / 2.0F);
/*  782:     */   }
/*  783:     */   
/*  784:     */   public static Vec3 rotate(double x, double y, double z)
/*  785:     */   {
/*  786:1882 */     return Vec3.createVectorHelper(x, y, z);
/*  787:     */   }
/*  788:     */   
/*  789:     */   public static Vec3 rotate_Default()
/*  790:     */   {
/*  791:1891 */     return Vec3.createVectorHelper(0.0D, 1.0D, 0.0D);
/*  792:     */   }
/*  793:     */   
/*  794:     */   public static Vec3 rotate_Random()
/*  795:     */   {
/*  796:1900 */     Random rand = new Random();
/*  797:1901 */     return angle(rand.nextFloat() * 360.0F, rand.nextFloat() * 180.0F - 90.0F);
/*  798:     */   }
/*  799:     */   
/*  800:     */   public static Vec3 gravity(double x, double y, double z)
/*  801:     */   {
/*  802:1914 */     return Vec3.createVectorHelper(x, y, z);
/*  803:     */   }
/*  804:     */   
/*  805:     */   public static Vec3 gravity(double gravityY)
/*  806:     */   {
/*  807:1924 */     return gravity(0.0D, gravityY, 0.0D);
/*  808:     */   }
/*  809:     */   
/*  810:     */   public static Vec3 gravity_Zero()
/*  811:     */   {
/*  812:1932 */     return gravity(0.0D, 0.0D, 0.0D);
/*  813:     */   }
/*  814:     */   
/*  815:     */   public static Vec3 gravity_Default()
/*  816:     */   {
/*  817:1942 */     return gravity(-0.03D);
/*  818:     */   }
/*  819:     */   
/*  820:     */   public static Vec3 getVecOutY(Vec3 vec)
/*  821:     */   {
/*  822:1952 */     return getVectorNomalize(Vec3.createVectorHelper(vec.xCoord, 0.0D, vec.zCoord));
/*  823:     */   }
/*  824:     */   
/*  825:     */   public static Vec3 getOverVector(Vec3 vec)
/*  826:     */   {
/*  827:1963 */     vec = getVecOutY(vec);
/*  828:1964 */     float yaw = getYawFromVector(vec.xCoord, vec.zCoord);
/*  829:1965 */     float pitch = getPitchFromVector(vec.xCoord, vec.yCoord, vec.zCoord);
/*  830:1966 */     return getVecFromAngle(yaw, pitch + 90.0F);
/*  831:     */   }
/*  832:     */   
/*  833:     */   public static final float radTrans(float deg)
/*  834:     */   {
/*  835:1977 */     return (float)(deg / 180.0D * 3.141592653589793D);
/*  836:     */   }
/*  837:     */   
/*  838:     */   public static final float radTrans(double deg)
/*  839:     */   {
/*  840:1987 */     return (float)(deg / 180.0D * 3.141592653589793D);
/*  841:     */   }
/*  842:     */   
/*  843:     */   public static final float getAngleMax180(float angle)
/*  844:     */   {
/*  845:1997 */     angle %= 360.0F;
/*  846:1998 */     if (angle > 180.0F) {
/*  847:2000 */       angle -= 360.0F;
/*  848:2002 */     } else if (angle < -180.0F) {
/*  849:2004 */       angle += 360.0F;
/*  850:     */     }
/*  851:2006 */     return angle;
/*  852:     */   }
/*  853:     */   
/*  854:     */   public static final Vec3 getVectorNomalize(Vec3 vec)
/*  855:     */   {
/*  856:2016 */     double length = MathHelper.sqrt_double(vec.xCoord * vec.xCoord + vec.yCoord * vec.yCoord + vec.zCoord * vec.zCoord);
/*  857:2017 */     length = 1.0D / length;
/*  858:2018 */     return Vec3.createVectorHelper(vec.xCoord * length, vec.yCoord * length, vec.zCoord * length);
/*  859:     */   }
/*  860:     */   
/*  861:     */   public static final Vec3 getVectorAdd(Vec3 originalVec, Vec3 addVec)
/*  862:     */   {
/*  863:2029 */     return Vec3.createVectorHelper(originalVec.xCoord + addVec.xCoord, originalVec.yCoord + addVec.yCoord, originalVec.zCoord + addVec.zCoord);
/*  864:     */   }
/*  865:     */   
/*  866:     */   public static final Vec3 getVectorMultiply(Vec3 vec, double multiply)
/*  867:     */   {
/*  868:2041 */     return Vec3.createVectorHelper(vec.xCoord * multiply, vec.yCoord * multiply, vec.zCoord * multiply);
/*  869:     */   }
/*  870:     */   
/*  871:     */   public static final double getInnerProduct(double vectorAX, double vectorAY, double vectorAZ, double vectorBX, double vectorBY, double vectorBZ)
/*  872:     */   {
/*  873:2056 */     return vectorAX * vectorBX + vectorAY * vectorBY + vectorAZ * vectorBZ;
/*  874:     */   }
/*  875:     */   
/*  876:     */   public static final double getInnerProduct(Vec3 vectorA, Vec3 vectorB)
/*  877:     */   {
/*  878:2067 */     return vectorA.xCoord * vectorB.xCoord + vectorA.yCoord * vectorB.yCoord + vectorA.zCoord * vectorB.zCoord;
/*  879:     */   }
/*  880:     */   
/*  881:     */   public static final float getVectorAndVectorAngle(Vec3 vectorA, Vec3 vectorB)
/*  882:     */   {
/*  883:2078 */     double inner = getInnerProduct(vectorA, vectorB);
/*  884:2079 */     return getAngleMax180((float)(Math.acos(inner / (vectorA.lengthVector() * vectorB.lengthVector())) / 3.141592653589793D * 180.0D));
/*  885:     */   }
/*  886:     */   
/*  887:     */   public static final Vec3 getOuterProduct(double vectorAX, double vectorAY, double vectorAZ, double vectorBX, double vectorBY, double vectorBZ)
/*  888:     */   {
/*  889:2095 */     Vec3 vec = Vec3.createVectorHelper(vectorAY * vectorBZ - vectorAZ * vectorBY, vectorAZ * vectorBX - vectorAX * vectorBZ, vectorAX * vectorBY - vectorAY * vectorBX);
/*  890:     */     
/*  891:     */ 
/*  892:2098 */     return vec;
/*  893:     */   }
/*  894:     */   
/*  895:     */   public static final Vec3 getOuterProduct(Vec3 vectorA, Vec3 vectorB)
/*  896:     */   {
/*  897:2110 */     return getOuterProduct(vectorA.xCoord, vectorA.yCoord, vectorA.zCoord, vectorB.xCoord, vectorB.yCoord, vectorB.zCoord);
/*  898:     */   }
/*  899:     */   
/*  900:     */   public static final float getAngleAndAngleSpan(float angleA, float angleB)
/*  901:     */   {
/*  902:2121 */     angleA = getAngleMax180(angleA);
/*  903:2122 */     angleB = getAngleMax180(angleB);
/*  904:2124 */     if (angleA == 0.0F) {
/*  905:2126 */       return angleB;
/*  906:     */     }
/*  907:2129 */     if (angleA > 0.0F)
/*  908:     */     {
/*  909:2131 */       if (angleB > angleA - 180.0F) {
/*  910:2133 */         return angleB - angleA;
/*  911:     */       }
/*  912:2137 */       return angleB - angleA + 360.0F;
/*  913:     */     }
/*  914:2143 */     if (angleB < angleA + 180.0F) {
/*  915:2145 */       return angleB - angleA;
/*  916:     */     }
/*  917:2149 */     return angleB - angleA - 360.0F;
/*  918:     */   }
/*  919:     */   
/*  920:     */   public static final float getEntityAndEntityAngleSpanYaw(Entity entityA, Entity entityB)
/*  921:     */   {
/*  922:2162 */     float angleA = getAngleMax180(entityA.rotationYaw);
/*  923:2163 */     float angleB = (float)Math.atan2(entityB.posX - entityA.posX, entityB.posZ - entityA.posZ) / 3.141593F * 180.0F;
/*  924:2164 */     return getAngleAndAngleSpan(angleA, angleB);
/*  925:     */   }
/*  926:     */   
/*  927:     */   public static final float getEntityAndEntityAngleSpanPitch(Entity entityA, Entity entityB)
/*  928:     */   {
/*  929:2175 */     double distanceX = entityB.posX - entityA.posX;
/*  930:2176 */     double distanceY = entityB.posY - entityA.posY;
/*  931:2177 */     double distanceZ = entityB.posZ - entityA.posZ;
/*  932:2178 */     double distanceXZ = Math.sqrt(distanceX * distanceX + distanceZ * distanceZ);
/*  933:2179 */     float angleA = getAngleMax180(entityA.rotationPitch);
/*  934:2180 */     float angleB = (float)Math.atan2(distanceY, distanceXZ) / 3.141593F * 180.0F;
/*  935:2181 */     return getAngleAndAngleSpan(angleA, angleB);
/*  936:     */   }
/*  937:     */   
/*  938:     */   public static final double halfAbsSin(float angle)
/*  939:     */   {
/*  940:2191 */     angle *= 0.5F;
/*  941:2192 */     return Math.abs(Math.sin(angle));
/*  942:     */   }
/*  943:     */   
/*  944:     */   public static final double getPosYFromEye(EntityLivingBase living, double yAdjustment)
/*  945:     */   {
/*  946:2203 */     return living.posY + living.getEyeHeight() + Math.sin(living.rotationPitch / 180.0D * 3.141592653589793D) * yAdjustment + yAdjustment;
/*  947:     */   }
/*  948:     */   
/*  949:     */   public static final double getPosYFromEye(EntityLivingBase living)
/*  950:     */   {
/*  951:2213 */     return living.posY + living.getEyeHeight() + Math.sin(living.rotationPitch / 180.0D * 3.141592653589793D) * -0.5D + Math.cos(living.rotationPitch / 180.0D * 3.141592653589793D) * -0.5D;
/*  952:     */   }
/*  953:     */   
/*  954:     */   public static final Vec3 getVecFromAngle(float yaw, float pitch, double force)
/*  955:     */   {
/*  956:2225 */     double yaw_rad = yaw / 180.0D * 3.141592653589793D;
/*  957:2226 */     double pitch_rad = pitch / 180.0D * 3.141592653589793D;
/*  958:2227 */     double vectorX = -Math.sin(yaw_rad) * Math.cos(pitch_rad) * force;
/*  959:2228 */     double vectorY = -Math.sin(pitch_rad) * force;
/*  960:2229 */     double vectorZ = Math.cos(yaw_rad) * Math.cos(pitch_rad) * force;
/*  961:2230 */     return Vec3.createVectorHelper(vectorX, vectorY, vectorZ);
/*  962:     */   }
/*  963:     */   
/*  964:     */   public static final Vec3 getVecFromAngle(float yaw, float pitch)
/*  965:     */   {
/*  966:2241 */     return getVecFromAngle(yaw, pitch, 1.0D);
/*  967:     */   }
/*  968:     */   
/*  969:     */   public static final float getYawFromVector(double vectorX, double vectorZ)
/*  970:     */   {
/*  971:2252 */     return (float)(Math.atan2(vectorX, vectorZ) * 180.0D / 3.141592741012573D);
/*  972:     */   }
/*  973:     */   
/*  974:     */   public static final float getPitchFromVector(double vectorX, double vectorY, double vectorZ)
/*  975:     */   {
/*  976:2264 */     double f = MathHelper.sqrt_double(vectorX * vectorX + vectorZ * vectorZ);
/*  977:2265 */     return (float)(Math.atan2(vectorY, f) * 180.0D / 3.141592741012573D);
/*  978:     */   }
/*  979:     */   
/*  980:     */   public static final Vec3 getRotationVector(double vectorX, double vectorY, double vectorZ, float angle, double force)
/*  981:     */   {
/*  982:2280 */     float disXZ = (float)Math.sqrt(vectorX * vectorX + vectorZ * vectorZ);
/*  983:2281 */     float angleYaw = (float)Math.atan2(-vectorX, vectorZ);
/*  984:2282 */     float anglePitch = (float)Math.atan2(-vectorY, disXZ);
/*  985:2283 */     float baseYaw = angleYaw;
/*  986:2284 */     float basePitch = anglePitch;
/*  987:     */     
/*  988:     */ 
/*  989:2287 */     Vec3 lookAt = Vec3.createVectorHelper(vectorX, vectorY, vectorZ);
/*  990:2288 */     lookAt.rotateAroundY(6.283186F);
/*  991:2289 */     angle = angle / 180.0F * 3.141593F;
/*  992:     */     
/*  993:     */ 
/*  994:2292 */     angleYaw = angle;
/*  995:2293 */     anglePitch = 0.0F;
/*  996:2294 */     float anglePitch_rad = radTrans(anglePitch);
/*  997:     */     
/*  998:2296 */     double X1 = Math.sin(angleYaw) * Math.cos(baseYaw);
/*  999:2297 */     double Z1 = Math.sin(angleYaw) * Math.sin(baseYaw);
/* 1000:2298 */     double X2 = Math.cos(angleYaw) * Math.sin(anglePitch_rad) * Math.sin(basePitch) * Math.sin(baseYaw);
/* 1001:2299 */     double Z2 = Math.cos(angleYaw) * Math.sin(anglePitch_rad) * Math.sin(basePitch) * Math.cos(baseYaw);
/* 1002:     */     
/* 1003:2301 */     double vecY = -Math.cos(angleYaw) * Math.sin(basePitch - anglePitch_rad);
/* 1004:2302 */     double vecX = Math.cos(angleYaw) * Math.cos(anglePitch_rad) * lookAt.xCoord + X1 - X2;
/* 1005:2303 */     double vecZ = Math.cos(angleYaw) * Math.cos(anglePitch_rad) * lookAt.zCoord + Z1 + Z2;
/* 1006:     */     
/* 1007:2305 */     return Vec3.createVectorHelper(vecX * force, vecY * force, vecZ * force);
/* 1008:     */   }
/* 1009:     */   
/* 1010:     */   public static final Vec3 getRotationVectorFromAngle(float yaw, float pitch, float angle, double force)
/* 1011:     */   {
/* 1012:2320 */     Vec3 vec3 = getVecFromAngle(yaw, pitch, 1.0D);
/* 1013:2321 */     return getRotationVector(vec3.xCoord, vec3.yCoord, vec3.zCoord, angle, force);
/* 1014:     */   }
/* 1015:     */   
/* 1016:     */   public static Vec3 getVectorFromRotation(double rotateVectorX, double rotateVectorY, double rotateVectorZ, double angleVectorX, double angleVectorY, double angleVectorZ, float angle)
/* 1017:     */   {
/* 1018:2338 */     double angleRad = angle / 180.0D * 3.141592653589793D;
/* 1019:2339 */     double sinA = Math.sin(angleRad);
/* 1020:2340 */     double cosA = Math.cos(angleRad);
/* 1021:2341 */     double returnVectorX = (rotateVectorX * rotateVectorX * (1.0D - cosA) + cosA) * angleVectorX + (rotateVectorX * rotateVectorY * (1.0D - cosA) - rotateVectorZ * sinA) * angleVectorY + (rotateVectorZ * rotateVectorX * (1.0D - cosA) + rotateVectorY * sinA) * angleVectorZ;
/* 1022:2342 */     double returnVectorY = (rotateVectorX * rotateVectorY * (1.0D - cosA) + rotateVectorZ * sinA) * angleVectorX + (rotateVectorY * rotateVectorY * (1.0D - cosA) + cosA) * angleVectorY + (rotateVectorY * rotateVectorZ * (1.0D - cosA) - rotateVectorX * sinA) * angleVectorZ;
/* 1023:2343 */     double returnVectorZ = (rotateVectorZ * rotateVectorX * (1.0D - cosA) - rotateVectorY * sinA) * angleVectorX + (rotateVectorY * rotateVectorZ * (1.0D - cosA) + rotateVectorX * sinA) * angleVectorY + (rotateVectorZ * rotateVectorZ * (1.0D - cosA) + cosA) * angleVectorZ;
/* 1024:     */     
/* 1025:2345 */     return Vec3.createVectorHelper(returnVectorX, returnVectorY, returnVectorZ);
/* 1026:     */   }
/* 1027:     */   
/* 1028:     */   public static Vec3 getVectorFromRotation(Vec3 rotate, Vec3 angleVector, float angle)
/* 1029:     */   {
/* 1030:2357 */     return getVectorFromRotation(rotate.xCoord, rotate.yCoord, rotate.zCoord, angleVector.xCoord, angleVector.yCoord, angleVector.zCoord, angle);
/* 1031:     */   }
/* 1032:     */   
/* 1033:     */   public static final EntityTHShot 弾生成(EntityLivingBase user, Entity source, Vec3 pos, Vec3 angle, float slope, Vec3 rotate, float rotationSpeed, int rotationEnd, double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity, ShotData shot)
/* 1034:     */   {
/* 1035:2385 */     return createShot(user, source, pos, angle, slope, rotate, rotationSpeed, rotationEnd, firstSpeed, limitSpeed, acceleration, gravity, shot);
/* 1036:     */   }
/* 1037:     */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.THShotLib
 * JD-Core Version:    0.7.0.1
 */