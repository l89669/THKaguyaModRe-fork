/*    1:     */ package thKaguyaMod.entity.living;
/*    2:     */ 
/*    3:     */ import cpw.mods.fml.common.eventhandler.EventBus;
/*    4:     */ import java.util.List;
/*    5:     */ import java.util.Random;
/*    6:     */ import net.minecraft.block.Block;
/*    7:     */ import net.minecraft.block.material.Material;
/*    8:     */ import net.minecraft.entity.DataWatcher;
/*    9:     */ import net.minecraft.entity.Entity;
/*   10:     */ import net.minecraft.entity.EntityCreature;
/*   11:     */ import net.minecraft.entity.EntityLivingBase;
/*   12:     */ import net.minecraft.entity.SharedMonsterAttributes;
/*   13:     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*   14:     */ import net.minecraft.entity.item.EntityItem;
/*   15:     */ import net.minecraft.entity.player.EntityPlayer;
/*   16:     */ import net.minecraft.item.ItemStack;
/*   17:     */ import net.minecraft.nbt.NBTTagCompound;
/*   18:     */ import net.minecraft.util.AxisAlignedBB;
/*   19:     */ import net.minecraft.util.ChunkCoordinates;
/*   20:     */ import net.minecraft.util.DamageSource;
/*   21:     */ import net.minecraft.util.MathHelper;
/*   22:     */ import net.minecraft.util.MovingObjectPosition;
/*   23:     */ import net.minecraft.util.Vec3;
/*   24:     */ import net.minecraft.world.EnumDifficulty;
/*   25:     */ import net.minecraft.world.World;
/*   26:     */ import net.minecraftforge.common.MinecraftForge;
/*   27:     */ import net.minecraftforge.event.entity.living.EnderTeleportEvent;
/*   28:     */ import thKaguyaMod.THKaguyaLib;
/*   29:     */ import thKaguyaMod.THShotLib;
/*   30:     */ import thKaguyaMod.entity.EntityTHItem;
/*   31:     */ import thKaguyaMod.entity.effect.EntitySpellCardCircle;
/*   32:     */ import thKaguyaMod.init.THKaguyaConfig;
/*   33:     */ import thKaguyaMod.init.THKaguyaItems;
/*   34:     */ 
/*   35:     */ public abstract class EntityDanmakuMob
/*   36:     */   extends EntityCreature
/*   37:     */ {
/*   38:     */   protected ChunkCoordinates currentFlightTarget;
/*   39:     */   protected int lastTime;
/*   40:  40 */   public int attackCounter = 0;
/*   41:     */   public int attackInterval;
/*   42:  42 */   public int courseChangeCooldown = 0;
/*   43:     */   public double waypointX;
/*   44:     */   public double waypointY;
/*   45:     */   public double waypointZ;
/*   46:     */   public int lostTarget;
/*   47:     */   protected Vec3 moveVec;
/*   48:     */   protected int moveTimer;
/*   49:     */   protected int moveTimerMax;
/*   50:     */   protected boolean stop;
/*   51:     */   protected double attackDistance;
/*   52:     */   protected double detectionDistance;
/*   53:     */   protected int flyingHeight;
/*   54:     */   public boolean isFlyingMode;
/*   55:     */   public int danmakuPattern;
/*   56:     */   public int danmakuSpan;
/*   57:     */   public byte shotForm;
/*   58:     */   public byte shotColor;
/*   59:     */   public float speedRate;
/*   60:     */   public short special;
/*   61:     */   public boolean isSpellCardMode;
/*   62: 102 */   public final int EASY = 1;
/*   63: 103 */   public final int NORMAL = 2;
/*   64: 104 */   public final int HARD = 3;
/*   65: 105 */   public final int LUNATIC = 4;
/*   66: 108 */   private String danmakuMobName = "";
/*   67:     */   protected int species_1;
/*   68:     */   protected int species_2;
/*   69:     */   public static final int CIRCLE_COLOR_RED = 0;
/*   70:     */   public static final int CIRCLE_COLOR_BLUE = 1;
/*   71:     */   public static final int CIRCLE_COLOR_GREEN = 2;
/*   72:     */   public static final int CIRCLE_COLOR_YELLOW = 3;
/*   73:     */   public static final int CIRCLE_COLOR_PURPLE = 4;
/*   74:     */   public static final int CIRCLE_COLOR_AQUA = 5;
/*   75:     */   public static final int CIRCLE_COLOR_ORANGE = 6;
/*   76:     */   public static final int CIRCLE_COLOR_WHITE = 7;
/*   77:     */   public static final int CIRCLE_COLOR_RAINBOW = 8;
/*   78:     */   public EntitySpellCardCircle circle;
/*   79:     */   public static final int SPECIES_HUMAN = 0;
/*   80:     */   public static final int SPECIES_HUMAN_HALF = 1;
/*   81:     */   public static final int SPECIES_HUMAN_LUNARIAN = 2;
/*   82:     */   public static final int SPECIES_HUMAN_HERMIT = 3;
/*   83:     */   public static final int SPECIES_HUMAN_SHIKAISEN = 4;
/*   84:     */   public static final int SPECIES_HUMAN_CELESTIALS = 5;
/*   85:     */   public static final int SPECIES_HUMAN_SAINT = 6;
/*   86:     */   public static final int SPECIES_HUMAN_INCHLINGS = 7;
/*   87:     */   public static final int SPECIES_HUMAN_HOURAI = 8;
/*   88:     */   public static final int SPECIES_GOD = 32;
/*   89:     */   public static final int SPECIES_GOD_ARAHITOGAMI = 33;
/*   90:     */   public static final int SPECIES_GOD_YATAGARASU = 34;
/*   91:     */   public static final int SPECIES_ENMA = 48;
/*   92:     */   public static final int SPECIES_FAIRY = 64;
/*   93:     */   public static final int SPECIES_FAIRY_ICE = 65;
/*   94:     */   public static final int SPECIES_PHANTOM = 92;
/*   95:     */   public static final int SPECIES_PHANTOM_HALF = 93;
/*   96:     */   public static final int SPECIES_PHANTOM_GHOST = 94;
/*   97:     */   public static final int SPECIES_PHANTOM_DIVINESPIRIT = 95;
/*   98:     */   public static final int SPECIES_PHANTOM_SHIP = 96;
/*   99:     */   public static final int SPECIES_PHANTOM_JIANGSHI = 97;
/*  100:     */   public static final int SPECIES_SHIKIGAMI = 108;
/*  101:     */   public static final int SPECIES_SHIKIGAMI_DOLL = 109;
/*  102:     */   public static final int SPECIES_SHIKIGAMI_FAMILIAR = 110;
/*  103:     */   public static final int SPECIES_YOUKAI = 128;
/*  104:     */   public static final int SPECIES_YOUKAI_MAGICIAN = 129;
/*  105:     */   public static final int SPECIES_YOUKAI_BEAST = 130;
/*  106:     */   public static final int SPECIES_YOUKAI_VAMPIRE = 131;
/*  107:     */   public static final int SPECIES_YOUKAI_ONI = 132;
/*  108:     */   public static final int SPECIES_YOUKAI_KAPPA = 133;
/*  109:     */   public static final int SPECIES_YOUKAI_TENGU_CROW = 134;
/*  110:     */   public static final int SPECIES_YOUKAI_TENGU_WHITEWOLF = 135;
/*  111:     */   public static final int SPECIES_YOUKAI_SHINIGAMI = 136;
/*  112:     */   public static final int SPECIES_YOUKAI_YUKIONNA = 137;
/*  113:     */   public static final int SPECIES_YOUKAI_BUG = 139;
/*  114:     */   public static final int SPECIES_YOUKAI_YOSUZUME = 140;
/*  115:     */   public static final int SPECIES_YOUKAI_RABBIT = 142;
/*  116:     */   public static final int SPECIES_YOUKAI_TUKUMOGAMI = 143;
/*  117:     */   public static final int SPECIES_YOUKAI_TURUBEOTOSHI = 144;
/*  118:     */   public static final int SPECIES_YOUKAI_HASHIHIME = 145;
/*  119:     */   public static final int SPECIES_YOUKAI_SATORI = 146;
/*  120:     */   public static final int SPECIES_YOUKAI_NYUUDOU = 147;
/*  121:     */   public static final int SPECIES_YOUKAI_NUE = 148;
/*  122:     */   public static final int SPECIES_YOUKAI_MERMAID = 149;
/*  123:     */   public static final int SPECIES_YOUKAI_AMANOJAKU = 150;
/*  124:     */   public static final int SPECIES_DRAGON = 192;
/*  125:     */   public static final int SPECIES_OTHERS = 255;
/*  126:     */   public static final int NOT_ATTACK = 0;
/*  127:     */   public static final int NORMAL_ATTACK01 = 1;
/*  128:     */   public static final int NORMAL_ATTACK02 = 2;
/*  129:     */   public static final int NORMAL_ATTACK03 = 3;
/*  130:     */   public static final int NORMAL_ATTACK04 = 4;
/*  131:     */   public static final int NORMAL_ATTACK05 = 5;
/*  132:     */   public static final int NORMAL_ATTACK06 = 6;
/*  133:     */   public static final int NORMAL_ATTACK07 = 7;
/*  134:     */   public static final int NORMAL_ATTACK08 = 8;
/*  135:     */   public static final int NORMAL_ATTACK09 = 9;
/*  136:     */   public static final int NORMAL_ATTACK10 = 10;
/*  137:     */   public static final int NORMAL_ATTACK11 = 11;
/*  138:     */   public static final int NORMAL_ATTACK12 = 12;
/*  139:     */   public static final int NORMAL_ATTACK13 = 13;
/*  140:     */   public static final int NORMAL_ATTACK14 = 14;
/*  141:     */   public static final int NORMAL_ATTACK15 = 15;
/*  142:     */   public static final int NORMAL_ATTACK16 = 16;
/*  143:     */   public static final int NORMAL_ATTACK17 = 17;
/*  144:     */   public static final int NORMAL_ATTACK18 = 18;
/*  145:     */   public static final int NORMAL_ATTACK19 = 19;
/*  146:     */   public static final int NORMAL_ATTACK20 = 20;
/*  147:     */   public static final int SPELLCARD_ATTACK01 = 101;
/*  148:     */   public static final int SPELLCARD_ATTACK02 = 102;
/*  149:     */   public static final int SPELLCARD_ATTACK03 = 103;
/*  150:     */   public static final int SPELLCARD_ATTACK04 = 104;
/*  151:     */   public static final int SPELLCARD_ATTACK05 = 105;
/*  152:     */   public static final int SPELLCARD_ATTACK06 = 106;
/*  153:     */   public static final int SPELLCARD_ATTACK07 = 107;
/*  154:     */   public static final int SPELLCARD_ATTACK08 = 108;
/*  155:     */   public static final int SPELLCARD_ATTACK09 = 109;
/*  156:     */   public static final int SPELLCARD_ATTACK10 = 110;
/*  157:     */   public static final int SPELLCARD_ATTACK11 = 111;
/*  158:     */   public static final int SPELLCARD_ATTACK12 = 112;
/*  159:     */   public static final int SPELLCARD_ATTACK13 = 113;
/*  160:     */   public static final int SPELLCARD_ATTACK14 = 114;
/*  161:     */   public static final int SPELLCARD_ATTACK15 = 115;
/*  162:     */   public static final int SPELLCARD_ATTACK16 = 116;
/*  163:     */   public static final int SPELLCARD_ATTACK17 = 117;
/*  164:     */   public static final int SPELLCARD_ATTACK18 = 118;
/*  165:     */   public static final int SPELLCARD_ATTACK19 = 119;
/*  166:     */   public static final int SPELLCARD_ATTACK20 = 120;
/*  167:     */   public static final int SPELLCARD_ATTACK21 = 121;
/*  168:     */   public static final int SPELLCARD_ATTACK22 = 122;
/*  169:     */   public static final int SPELLCARD_ATTACK23 = 123;
/*  170:     */   public static final int SPELLCARD_ATTACK24 = 124;
/*  171:     */   public static final int SPELLCARD_ATTACK25 = 125;
/*  172:     */   public static final int SPELLCARD_ATTACK26 = 126;
/*  173:     */   public static final int ATTACK_END = 127;
/*  174:     */   
/*  175:     */   public EntityDanmakuMob(World world)
/*  176:     */   {
/*  177: 311 */     super(world);
/*  178: 312 */     this.isFlyingMode = true;
/*  179: 313 */     setFlyingHeight(3);
/*  180: 314 */     this.stop = false;
/*  181: 315 */     setSpecies(255);
/*  182:     */   }
/*  183:     */   
/*  184:     */   public EntityDanmakuMob(World world, double setX, double setY, double setZ)
/*  185:     */   {
/*  186: 320 */     this(world);
/*  187: 321 */     setPosition(setX, setY, setZ);
/*  188:     */   }
/*  189:     */   
/*  190:     */   protected void entityInit()
/*  191:     */   {
/*  192: 330 */     super.entityInit();
/*  193: 331 */     this.dataWatcher.addObject(16, new Byte((byte)0));
/*  194: 332 */     this.dataWatcher.addObject(17, new Integer(0));
/*  195: 333 */     this.dataWatcher.addObject(18, new Byte((byte)0));
/*  196: 334 */     this.dataWatcher.addObject(19, new Byte((byte)0));
/*  197:     */   }
/*  198:     */   
/*  199:     */   public boolean isAIEnabled()
/*  200:     */   {
/*  201: 340 */     return false;
/*  202:     */   }
/*  203:     */   
/*  204:     */   public void setMaxHP(float hp)
/*  205:     */   {
/*  206: 349 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(hp);
/*  207:     */   }
/*  208:     */   
/*  209:     */   public void setSpeed(double speed)
/*  210:     */   {
/*  211: 358 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(speed);
/*  212:     */   }
/*  213:     */   
/*  214:     */   public double getSpeed()
/*  215:     */   {
/*  216: 367 */     return getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue();
/*  217:     */   }
/*  218:     */   
/*  219:     */   public void setAttackDistance(double distance)
/*  220:     */   {
/*  221: 376 */     this.attackDistance = distance;
/*  222:     */   }
/*  223:     */   
/*  224:     */   public double getAttackDistance()
/*  225:     */   {
/*  226: 385 */     return this.attackDistance;
/*  227:     */   }
/*  228:     */   
/*  229:     */   public void setDetectionDistance(double distance)
/*  230:     */   {
/*  231: 394 */     this.detectionDistance = distance;
/*  232:     */   }
/*  233:     */   
/*  234:     */   protected double getDetectionDistance()
/*  235:     */   {
/*  236: 403 */     return this.detectionDistance;
/*  237:     */   }
/*  238:     */   
/*  239:     */   protected boolean canFairyCall()
/*  240:     */   {
/*  241: 412 */     return false;
/*  242:     */   }
/*  243:     */   
/*  244:     */   protected double getFairyCallDistance()
/*  245:     */   {
/*  246: 421 */     return 10.0D;
/*  247:     */   }
/*  248:     */   
/*  249:     */   protected void setSpellCardAttack()
/*  250:     */   {
/*  251: 431 */     setSpellCardMotion((byte)-30);
/*  252:     */   }
/*  253:     */   
/*  254:     */   public EntityLivingBase getTarget()
/*  255:     */   {
/*  256: 436 */     if (THKaguyaConfig.danmakuLevel <= 0) {
/*  257: 438 */       return null;
/*  258:     */     }
/*  259: 440 */     if ((this.entityToAttack instanceof EntityLivingBase)) {
/*  260: 442 */       return (EntityLivingBase)this.entityToAttack;
/*  261:     */     }
/*  262: 444 */     return null;
/*  263:     */   }
/*  264:     */   
/*  265:     */   public Vec3 pos(double x, double y, double z)
/*  266:     */   {
/*  267: 455 */     return THShotLib.pos(x, y, z);
/*  268:     */   }
/*  269:     */   
/*  270:     */   public Vec3 pos()
/*  271:     */   {
/*  272: 464 */     return THShotLib.pos_Living(this);
/*  273:     */   }
/*  274:     */   
/*  275:     */   public Vec3 pos(EntityLivingBase living)
/*  276:     */   {
/*  277: 474 */     return THShotLib.pos_Living(living);
/*  278:     */   }
/*  279:     */   
/*  280:     */   public Vec3 angle(float yaw, float pitch)
/*  281:     */   {
/*  282: 485 */     return THShotLib.angle(yaw, pitch);
/*  283:     */   }
/*  284:     */   
/*  285:     */   public Vec3 gravity_Zero()
/*  286:     */   {
/*  287: 494 */     return THShotLib.gravity_Zero();
/*  288:     */   }
/*  289:     */   
/*  290:     */   public int getDanmakuPattern()
/*  291:     */   {
/*  292: 503 */     return this.dataWatcher.getWatchableObjectInt(17);
/*  293:     */   }
/*  294:     */   
/*  295:     */   public void setDanmakuPattern(int pattern)
/*  296:     */   {
/*  297: 512 */     this.dataWatcher.updateObject(17, Integer.valueOf(pattern));
/*  298:     */   }
/*  299:     */   
/*  300:     */   public byte getSpellCardMotion()
/*  301:     */   {
/*  302: 521 */     return this.dataWatcher.getWatchableObjectByte(18);
/*  303:     */   }
/*  304:     */   
/*  305:     */   public void setSpellCardMotion(byte motion)
/*  306:     */   {
/*  307: 530 */     this.dataWatcher.updateObject(18, Byte.valueOf(motion));
/*  308:     */   }
/*  309:     */   
/*  310:     */   public int getUsingSpellCardNo()
/*  311:     */   {
/*  312: 539 */     return -1;
/*  313:     */   }
/*  314:     */   
/*  315:     */   public boolean isSpellCardAttack()
/*  316:     */   {
/*  317: 548 */     return getUsingSpellCardNo() >= 0;
/*  318:     */   }
/*  319:     */   
/*  320:     */   public byte getFlyingHeight()
/*  321:     */   {
/*  322: 557 */     return this.dataWatcher.getWatchableObjectByte(19);
/*  323:     */   }
/*  324:     */   
/*  325:     */   public void setFlyingHeight(int height)
/*  326:     */   {
/*  327: 566 */     this.dataWatcher.updateObject(19, Byte.valueOf((byte)height));
/*  328:     */   }
/*  329:     */   
/*  330:     */   public String getDanmakuMobName()
/*  331:     */   {
/*  332: 575 */     return this.danmakuMobName;
/*  333:     */   }
/*  334:     */   
/*  335:     */   public void setDanmakuMobName(String name)
/*  336:     */   {
/*  337: 584 */     this.danmakuMobName = name;
/*  338:     */   }
/*  339:     */   
/*  340:     */   protected void setSpecies(int species1, int species2)
/*  341:     */   {
/*  342: 594 */     this.species_1 = species1;
/*  343: 595 */     this.species_2 = species2;
/*  344:     */   }
/*  345:     */   
/*  346:     */   protected void setSpecies(int species)
/*  347:     */   {
/*  348: 604 */     setSpecies(species, -1);
/*  349:     */   }
/*  350:     */   
/*  351:     */   public int getSpecies_1()
/*  352:     */   {
/*  353: 613 */     return 255;
/*  354:     */   }
/*  355:     */   
/*  356:     */   public int getSpecies_2()
/*  357:     */   {
/*  358: 622 */     return 255;
/*  359:     */   }
/*  360:     */   
/*  361:     */   public boolean isHuman()
/*  362:     */   {
/*  363: 631 */     return (getSpecies_1() < 32) || (getSpecies_2() < 32);
/*  364:     */   }
/*  365:     */   
/*  366:     */   public boolean isPhantom()
/*  367:     */   {
/*  368: 641 */     return ((getSpecies_1() >= 92) && (getSpecies_1() < 108)) || ((getSpecies_2() >= 92) && (getSpecies_2() < 108));
/*  369:     */   }
/*  370:     */   
/*  371:     */   public boolean isFairy()
/*  372:     */   {
/*  373: 650 */     return (getSpecies_1() == 64) || (getSpecies_2() == 64);
/*  374:     */   }
/*  375:     */   
/*  376:     */   public boolean isYoukai()
/*  377:     */   {
/*  378: 660 */     return ((getSpecies_1() >= 128) && (getSpecies_1() < 192)) || ((getSpecies_2() >= 128) && (getSpecies_2() < 192));
/*  379:     */   }
/*  380:     */   
/*  381:     */   public boolean isInSpecies(int species)
/*  382:     */   {
/*  383: 670 */     return (getSpecies_1() == species) || (getSpecies_2() == species);
/*  384:     */   }
/*  385:     */   
/*  386:     */   protected void setStopStart()
/*  387:     */   {
/*  388: 678 */     this.stop = true;
/*  389: 679 */     this.moveTimer = 0;
/*  390: 680 */     this.moveTimerMax = 0;
/*  391:     */   }
/*  392:     */   
/*  393:     */   protected void setStopEnd()
/*  394:     */   {
/*  395: 688 */     this.stop = false;
/*  396:     */   }
/*  397:     */   
/*  398:     */   public boolean isStop()
/*  399:     */   {
/*  400: 697 */     return this.stop;
/*  401:     */   }
/*  402:     */   
/*  403:     */   protected void move(Vec3 move, double speed, int time)
/*  404:     */   {
/*  405: 710 */     this.moveVec = move;
/*  406: 711 */     this.moveVec.xCoord *= speed;
/*  407: 712 */     this.moveVec.yCoord *= speed;
/*  408: 713 */     this.moveVec.zCoord *= speed;
/*  409: 714 */     this.moveTimer = time;
/*  410: 715 */     this.moveTimerMax = time;
/*  411:     */   }
/*  412:     */   
/*  413:     */   protected void moveRight(double speed, int time)
/*  414:     */   {
/*  415: 725 */     this.moveVec = THShotLib.getVecFromAngle(this.rotationYaw + 90.0F, this.rotationPitch);
/*  416: 726 */     move(this.moveVec, speed, time);
/*  417:     */   }
/*  418:     */   
/*  419:     */   protected void moveLeft(double speed, int time)
/*  420:     */   {
/*  421: 736 */     this.moveVec = THShotLib.getVecFromAngle(this.rotationYaw - 90.0F, this.rotationPitch);
/*  422: 737 */     move(this.moveVec, speed, time);
/*  423:     */   }
/*  424:     */   
/*  425:     */   protected void moveForward(double speed, int time)
/*  426:     */   {
/*  427: 747 */     this.moveVec = THShotLib.getVecFromAngle(this.rotationYaw, this.rotationPitch);
/*  428: 748 */     move(this.moveVec, speed, time);
/*  429:     */   }
/*  430:     */   
/*  431:     */   protected void moveBack(double speed, int time)
/*  432:     */   {
/*  433: 758 */     this.moveVec = THShotLib.getVecFromAngle(this.rotationYaw + 180.0F, this.rotationPitch);
/*  434: 759 */     move(this.moveVec, speed, time);
/*  435:     */   }
/*  436:     */   
/*  437:     */   protected void moveDanmakuAttack(int nextPattern, int interval, float maxHP, int invincibleTime)
/*  438:     */   {
/*  439: 771 */     setDanmakuPattern((byte)nextPattern);
/*  440: 772 */     this.attackCounter = (-interval);
/*  441: 773 */     this.deathTime = 0;
/*  442: 774 */     if (!this.worldObj.isRemote)
/*  443:     */     {
/*  444: 776 */       getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHP);
/*  445: 777 */       setHealth(maxHP);
/*  446:     */     }
/*  447: 779 */     this.attackInterval = invincibleTime;
/*  448: 780 */     THShotLib.danmakuRemove(this, 40.0D, "Enemy", true);
/*  449: 781 */     this.isSpellCardMode = false;
/*  450: 782 */     if (this.circle != null) {
/*  451: 784 */       this.circle.setDead();
/*  452:     */     }
/*  453:     */   }
/*  454:     */   
/*  455:     */   public Vec3 look()
/*  456:     */   {
/*  457: 794 */     return THShotLib.angle(this.rotationYaw, this.rotationPitch);
/*  458:     */   }
/*  459:     */   
/*  460:     */   public int getSpellCardCircleColor()
/*  461:     */   {
/*  462: 799 */     return 0;
/*  463:     */   }
/*  464:     */   
/*  465:     */   protected void useSpellCard(int spellCardNo)
/*  466:     */   {
/*  467: 808 */     if ((this.entityToAttack instanceof EntityLivingBase)) {
/*  468: 810 */       THKaguyaLib.spellCardDeclaration(this.worldObj, this, (EntityLivingBase)this.entityToAttack, spellCardNo, 0, THKaguyaConfig.danmakuLevel, !this.isSpellCardMode);
/*  469:     */     }
/*  470: 812 */     if (!this.isSpellCardMode)
/*  471:     */     {
/*  472: 814 */       this.isSpellCardMode = true;
/*  473:     */       
/*  474: 816 */       this.circle = new EntitySpellCardCircle(this.worldObj, this, 16 + getSpellCardCircleColor(), -100);
/*  475: 817 */       if (!this.worldObj.isRemote) {
/*  476: 819 */         this.worldObj.spawnEntityInWorld(this.circle);
/*  477:     */       }
/*  478: 821 */       setSpellCardAttack();
/*  479:     */     }
/*  480:     */   }
/*  481:     */   
/*  482:     */   protected void applyEntityAttributes()
/*  483:     */   {
/*  484: 829 */     super.applyEntityAttributes();
/*  485:     */     
/*  486: 831 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(2.0D);
/*  487:     */     
/*  488: 833 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
/*  489:     */     
/*  490: 835 */     getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.0D);
/*  491:     */     
/*  492: 837 */     getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(30.0D);
/*  493:     */   }
/*  494:     */   
/*  495:     */   public void onEntityUpdate()
/*  496:     */   {
/*  497: 844 */     if (this.ticksExisted > this.lastTime) {
/*  498: 846 */       super.onEntityUpdate();
/*  499:     */     }
/*  500:     */   }
/*  501:     */   
/*  502:     */   public void onUpdate()
/*  503:     */   {
/*  504: 855 */     if ((!this.worldObj.isRemote) && (this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL))
/*  505:     */     {
/*  506: 857 */       setDead();
/*  507: 858 */       return;
/*  508:     */     }
/*  509: 862 */     if (THKaguyaConfig.danmakuLevel <= 0) {
/*  510: 864 */       this.entityToAttack = null;
/*  511:     */     }
/*  512: 868 */     if (this.ticksExisted <= this.lastTime)
/*  513:     */     {
/*  514: 870 */       this.motionX = 0.0D;
/*  515: 871 */       this.motionY = 0.0D;
/*  516: 872 */       this.motionZ = 0.0D;
/*  517: 873 */       setPosition(this.prevPosX, this.prevPosY, this.prevPosZ);
/*  518: 874 */       return;
/*  519:     */     }
/*  520: 879 */     super.onUpdate();
/*  521: 882 */     if (getHealth() <= 0.0F) {
/*  522: 884 */       return;
/*  523:     */     }
/*  524: 887 */     if (this.attackInterval > 0) {
/*  525: 889 */       this.attackInterval -= 1;
/*  526:     */     }
/*  527: 891 */     if (isSpellCardAttack()) {
/*  528: 893 */       if (this.attackCounter == -20) {
/*  529: 893 */         setSpellCardAttack();
/*  530:     */       }
/*  531:     */     }
/*  532: 897 */     if (getSpellCardMotion() < 0) {
/*  533: 899 */       if (!this.worldObj.isRemote) {
/*  534: 901 */         setSpellCardMotion((byte)(getSpellCardMotion() + 2));
/*  535:     */       }
/*  536:     */     }
/*  537: 905 */     if (this.moveTimer > 0)
/*  538:     */     {
/*  539: 907 */       double moveRate = Math.cos(this.moveTimer / this.moveTimerMax * 3.141592653589793D / 2.0D);
/*  540: 908 */       if (!this.worldObj.isRemote) {
/*  541: 910 */         moveEntity(this.moveVec.xCoord * moveRate, this.moveVec.yCoord * moveRate, this.moveVec.zCoord * moveRate);
/*  542:     */       }
/*  543: 912 */       this.moveTimer -= 1;
/*  544:     */     }
/*  545: 915 */     if (getFlyingHeight() > 0)
/*  546:     */     {
/*  547: 918 */       int heightCount = 0;
/*  548: 920 */       while ((this.worldObj.isAirBlock((int)this.posX, (int)this.posY - heightCount, (int)this.posZ)) && (heightCount < 8)) {
/*  549: 922 */         heightCount++;
/*  550:     */       }
/*  551: 926 */       if ((this.entityToAttack != null) && (heightCount > getFlyingHeight()))
/*  552:     */       {
/*  553: 929 */         double distance = this.entityToAttack.posY - this.posY;
/*  554:     */         
/*  555: 931 */         this.motionY += distance * 0.0006D;
/*  556:     */       }
/*  557: 934 */       else if (heightCount < getFlyingHeight())
/*  558:     */       {
/*  559: 936 */         if (heightCount >= getFlyingHeight()) {
/*  560: 938 */           this.motionY -= 0.0006D;
/*  561: 940 */         } else if (heightCount < getFlyingHeight()) {
/*  562: 942 */           this.motionY += 0.006D;
/*  563:     */         }
/*  564:     */       }
/*  565: 945 */       if (!this.stop) {
/*  566: 947 */         moveEntity(0.0D, this.motionY, 0.0D);
/*  567:     */       }
/*  568:     */     }
/*  569: 953 */     if ((this.entityToAttack != null) && (this.entityToAttack.isDead)) {
/*  570: 955 */       this.entityToAttack = null;
/*  571:     */     }
/*  572: 959 */     if (this.entityToAttack == null)
/*  573:     */     {
/*  574: 962 */       this.entityToAttack = this.worldObj.getClosestVulnerablePlayerToEntity(this, getDetectionDistance());
/*  575: 965 */       if (this.entityToAttack != null) {
/*  576: 968 */         if (canFairyCall())
/*  577:     */         {
/*  578: 971 */           List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(0.0D, 0.0D, 0.0D).expand(getFairyCallDistance(), getFairyCallDistance(), getFairyCallDistance()));
/*  579: 972 */           for (int i = 0; i < list.size(); i++)
/*  580:     */           {
/*  581: 974 */             Entity entity1 = (Entity)list.get(i);
/*  582: 976 */             if ((entity1 instanceof EntityTHFairy))
/*  583:     */             {
/*  584: 978 */               EntityTHFairy danmakuMob = (EntityTHFairy)entity1;
/*  585: 980 */               if (danmakuMob.entityToAttack == null) {
/*  586: 983 */                 danmakuMob.entityToAttack = this.entityToAttack;
/*  587:     */               }
/*  588:     */             }
/*  589:     */           }
/*  590:     */         }
/*  591:     */       }
/*  592:     */     }
/*  593: 992 */     double entityToAttackableLength = getAttackDistance();
/*  594: 995 */     if ((this.entityToAttack != null) && (!this.isDead))
/*  595:     */     {
/*  596:1000 */       double xDistance = this.entityToAttack.posX - this.posX;
/*  597:1001 */       double yDistance = this.entityToAttack.posY + this.entityToAttack.getEyeHeight() - (this.posY + getEyeHeight());
/*  598:1002 */       double zDistance = this.entityToAttack.posZ - this.posZ;
/*  599:1003 */       float angleXZ = -(float)Math.atan2(xDistance, zDistance) / 3.141593F * 180.0F;
/*  600:1004 */       float angleY = -(float)Math.atan2(yDistance, Math.sqrt(xDistance * xDistance + zDistance * zDistance)) / 3.141593F * 180.0F;
/*  601:1006 */       if (!this.stop) {
/*  602:1008 */         setRotation(angleXZ, angleY);
/*  603:     */       }
/*  604:1012 */       if (canEntityBeSeen(this.entityToAttack))
/*  605:     */       {
/*  606:1015 */         this.lostTarget = 0;
/*  607:     */         
/*  608:1017 */         int level = THKaguyaConfig.danmakuLevel;
/*  609:1018 */         if ((level != 0) && (this.attackCounter >= 0)) {
/*  610:1020 */           danmakuPattern(level);
/*  611:     */         }
/*  612:1025 */         this.attackCounter += 1;
/*  613:     */       }
/*  614:     */       else
/*  615:     */       {
/*  616:1032 */         this.lostTarget += 1;
/*  617:1034 */         if (this.lostTarget > 200) {
/*  618:1037 */           this.entityToAttack = null;
/*  619:     */         }
/*  620:     */       }
/*  621:1041 */       if ((this.moveTimer <= 0) && (this.lostTarget > 0)) {
/*  622:1044 */         move(THShotLib.getVecFromAngle(this.rand.nextFloat() + 360.0F, this.rand.nextFloat() * 360.0F), getSpeed(), 30);
/*  623:     */       }
/*  624:1047 */       if ((this.moveTimer <= 0) && (this.entityToAttack != null) && (getAttackDistance() > 0.0D))
/*  625:     */       {
/*  626:1049 */         double toTargetDistance = getDistanceToEntity(this.entityToAttack);
/*  627:1052 */         if (toTargetDistance < getAttackDistance())
/*  628:     */         {
/*  629:1054 */           double rate = 1.0D - toTargetDistance / getAttackDistance();
/*  630:1055 */           moveBack(getSpeed() * 0.3D * rate, 15);
/*  631:     */         }
/*  632:     */         else
/*  633:     */         {
/*  634:1059 */           double rate = toTargetDistance / getAttackDistance();
/*  635:1060 */           moveForward(getSpeed() * 0.5D * rate, 15);
/*  636:     */         }
/*  637:     */       }
/*  638:     */     }
/*  639:1067 */     if (this.ticksExisted > this.lastTime) {
/*  640:1069 */       this.lastTime = this.ticksExisted;
/*  641:     */     }
/*  642:     */   }
/*  643:     */   
/*  644:     */   public boolean isEntityInvulnerable()
/*  645:     */   {
/*  646:1083 */     if (this.attackInterval > 0) {
/*  647:1085 */       return true;
/*  648:     */     }
/*  649:1089 */     return super.isEntityInvulnerable();
/*  650:     */   }
/*  651:     */   
/*  652:     */   public boolean attackEntityFrom(DamageSource damageSource, float damage)
/*  653:     */   {
/*  654:1097 */     if ((super.attackEntityFrom(damageSource, applyPotionDamageCalculations(damageSource, damage))) && ((damageSource.getEntity() instanceof EntityLivingBase)))
/*  655:     */     {
/*  656:1099 */       EntityLivingBase entity = (EntityLivingBase)damageSource.getEntity();
/*  657:     */       
/*  658:1101 */       this.motionX *= 0.0001D;
/*  659:1102 */       this.motionY *= 0.0001D;
/*  660:1103 */       this.motionZ *= 0.0001D;
/*  661:1104 */       if ((this.riddenByEntity != entity) && (this.ridingEntity != entity))
/*  662:     */       {
/*  663:1106 */         if ((entity instanceof EntityPlayer)) {
/*  664:1108 */           this.entityToAttack = entity;
/*  665:     */         }
/*  666:1111 */         return true;
/*  667:     */       }
/*  668:1115 */       return true;
/*  669:     */     }
/*  670:1120 */     return false;
/*  671:     */   }
/*  672:     */   
/*  673:     */   public void hitCheck(float damage)
/*  674:     */   {
/*  675:1128 */     Vec3 vec3d = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
/*  676:     */     
/*  677:1130 */     Vec3 vec3d1 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
/*  678:     */     
/*  679:     */ 
/*  680:1133 */     MovingObjectPosition movingObjectPosition = this.worldObj.func_147447_a(vec3d, vec3d1, false, true, true);
/*  681:1134 */     vec3d = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
/*  682:1135 */     vec3d1 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
/*  683:1137 */     if (movingObjectPosition != null)
/*  684:     */     {
/*  685:1139 */       THShotLib.playShotSound(this);
/*  686:     */       
/*  687:1141 */       vec3d1 = Vec3.createVectorHelper(movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord);
/*  688:     */     }
/*  689:1144 */     movingObjectPosition = hitEntityCheck(movingObjectPosition, vec3d, vec3d1);
/*  690:1146 */     if (movingObjectPosition != null) {
/*  691:1149 */       onImpact(movingObjectPosition, damage);
/*  692:     */     }
/*  693:     */   }
/*  694:     */   
/*  695:     */   public MovingObjectPosition hitEntityCheck(MovingObjectPosition movingObjectPosition, Vec3 vec3d, Vec3 vec3d1)
/*  696:     */   {
/*  697:1157 */     Entity entity = null;
/*  698:1158 */     double d = 0.0D;
/*  699:1159 */     float hitSize = 0.9F;
/*  700:     */     
/*  701:1161 */     List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(hitSize, hitSize, hitSize));
/*  702:1163 */     for (int j = 0; j < list.size(); j++)
/*  703:     */     {
/*  704:1165 */       Entity entity1 = (Entity)list.get(j);
/*  705:1167 */       if (entity1.canBeCollidedWith())
/*  706:     */       {
/*  707:1171 */         AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(hitSize, hitSize, hitSize);
/*  708:1172 */         MovingObjectPosition movingObjectPosition1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);
/*  709:1174 */         if (movingObjectPosition1 != null)
/*  710:     */         {
/*  711:1177 */           double d1 = vec3d.distanceTo(movingObjectPosition1.hitVec);
/*  712:1179 */           if ((d1 < d) || (d == 0.0D))
/*  713:     */           {
/*  714:1181 */             entity = entity1;
/*  715:1182 */             d = d1;
/*  716:     */           }
/*  717:     */         }
/*  718:     */       }
/*  719:     */     }
/*  720:1190 */     if (entity != null) {
/*  721:1193 */       movingObjectPosition = new MovingObjectPosition(entity);
/*  722:     */     }
/*  723:1196 */     return movingObjectPosition;
/*  724:     */   }
/*  725:     */   
/*  726:     */   protected void onImpact(MovingObjectPosition movingObjectPosition, float damage)
/*  727:     */   {
/*  728:1203 */     if (!this.worldObj.isRemote)
/*  729:     */     {
/*  730:1205 */       Entity hitEntity = movingObjectPosition.entityHit;
/*  731:1208 */       if (hitEntity != null)
/*  732:     */       {
/*  733:1211 */         float damageRate = 1.0F;
/*  734:1213 */         if (hitEntity.attackEntityFrom(DamageSource.causeMobDamage(this), damage)) {}
/*  735:     */       }
/*  736:     */     }
/*  737:     */   }
/*  738:     */   
/*  739:     */   protected void updateEntityActionState()
/*  740:     */   {
/*  741:1224 */     if (this.entityToAttack == null) {
/*  742:1226 */       super.updateEntityActionState();
/*  743:     */     }
/*  744:     */   }
/*  745:     */   
/*  746:     */   public void moveEntityWithHeading(float par1, float par2)
/*  747:     */   {
/*  748:1298 */     if (getFlyingHeight() <= 0)
/*  749:     */     {
/*  750:1300 */       super.moveEntityWithHeading(par1, par2);
/*  751:1301 */       return;
/*  752:     */     }
/*  753:1304 */     moveFlying(par1, par2, 0.02F);
/*  754:1306 */     if ((this.worldObj.isRemote) || 
/*  755:     */     
/*  756:     */ 
/*  757:     */ 
/*  758:     */ 
/*  759:     */ 
/*  760:     */ 
/*  761:1313 */       (isInWater()))
/*  762:     */     {
/*  763:1315 */       moveFlying(par1, par2, 0.02F);
/*  764:1316 */       moveEntity(this.motionX, this.motionY, this.motionZ);
/*  765:1317 */       this.motionX *= 0.800000011920929D;
/*  766:1318 */       this.motionY *= 0.800000011920929D;
/*  767:1319 */       this.motionZ *= 0.800000011920929D;
/*  768:     */     }
/*  769:     */     else
/*  770:     */     {
/*  771:1323 */       float f2 = 0.91F;
/*  772:1325 */       if (this.onGround) {
/*  773:1327 */         f2 = this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ)).slipperiness * 0.91F;
/*  774:     */       }
/*  775:1330 */       float f3 = 0.1627714F / (f2 * f2 * f2);
/*  776:1331 */       moveFlying(par1, par2, this.onGround ? 0.1F * f3 : 0.02F);
/*  777:1332 */       f2 = 0.91F;
/*  778:1334 */       if (this.onGround) {
/*  779:1336 */         f2 = this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ)).slipperiness * 0.91F;
/*  780:     */       }
/*  781:1339 */       moveEntity(this.motionX, this.motionY, this.motionZ);
/*  782:1340 */       this.motionX *= f2;
/*  783:1341 */       this.motionY *= f2;
/*  784:1342 */       this.motionZ *= f2;
/*  785:     */     }
/*  786:1345 */     this.prevLimbSwingAmount = this.limbSwingAmount;
/*  787:1346 */     double d1 = this.posX - this.prevPosX;
/*  788:1347 */     double d0 = this.posZ - this.prevPosZ;
/*  789:1348 */     float f4 = MathHelper.sqrt_double(d1 * d1 + d0 * d0) * 4.0F;
/*  790:1350 */     if (f4 > 1.0F) {
/*  791:1352 */       f4 = 1.0F;
/*  792:     */     }
/*  793:1355 */     this.limbSwingAmount += (f4 - this.limbSwingAmount) * 0.4F;
/*  794:1356 */     this.limbSwing += this.limbSwingAmount;
/*  795:     */   }
/*  796:     */   
/*  797:     */   protected void danmakuPattern(int level) {}
/*  798:     */   
/*  799:     */   protected void onDeathUpdate()
/*  800:     */   {
/*  801:1377 */     super.onDeathUpdate();
/*  802:1380 */     if (this.deathTime == 7)
/*  803:     */     {
/*  804:1382 */       THShotLib.explosionEffect2(this.worldObj, this.posX, this.posY, this.posZ, 1.0F + this.deathTime * 0.1F);
/*  805:1383 */       THShotLib.banishExplosion(this, 5.0D, 5.0F);
/*  806:     */     }
/*  807:     */   }
/*  808:     */   
/*  809:     */   protected void dropFewItems(boolean hasBeenAttackedByPlayer, int lootingLevel)
/*  810:     */   {
/*  811:1393 */     if ((getUsingSpellCardNo() >= 0) && (hasBeenAttackedByPlayer)) {
/*  812:1395 */       entityDropItem(new ItemStack(THKaguyaItems.spell_card, 1, getUsingSpellCardNo()), 0.0F);
/*  813:     */     }
/*  814:     */   }
/*  815:     */   
/*  816:     */   public void dropItem(int type, Vec3 pos, Vec3 angle)
/*  817:     */   {
/*  818:1401 */     EntityTHItem item = new EntityTHItem(this.worldObj, pos.xCoord, pos.yCoord, pos.zCoord, angle, (byte)type);
/*  819:1403 */     if (!this.worldObj.isRemote) {
/*  820:1405 */       this.worldObj.spawnEntityInWorld(item);
/*  821:     */     }
/*  822:     */   }
/*  823:     */   
/*  824:     */   public void dropPowerUpItem(Vec3 pos, Vec3 angle)
/*  825:     */   {
/*  826:1416 */     dropItem(1, pos, angle);
/*  827:     */   }
/*  828:     */   
/*  829:     */   public void dropPowerUpItem(int num)
/*  830:     */   {
/*  831:1425 */     for (int i = 1; i <= num; i++) {
/*  832:1427 */       dropPowerUpItem(pos(), THShotLib.angle_LimitRandom(THShotLib.angle(0.0D, 1.0D, 0.0D), 90.0F));
/*  833:     */     }
/*  834:     */   }
/*  835:     */   
/*  836:     */   public void dropPointItem(Vec3 pos, Vec3 angle)
/*  837:     */   {
/*  838:1438 */     dropItem(4, pos, angle);
/*  839:     */   }
/*  840:     */   
/*  841:     */   public void dropPointItem(int num)
/*  842:     */   {
/*  843:1447 */     for (int i = 1; i <= num; i++) {
/*  844:1449 */       dropPointItem(pos(), THShotLib.angle_LimitRandom(THShotLib.angle(0.0D, 1.0D, 0.0D), 90.0F));
/*  845:     */     }
/*  846:     */   }
/*  847:     */   
/*  848:     */   public void dropSpellCardItem(Vec3 pos, Vec3 angle)
/*  849:     */   {
/*  850:1460 */     dropItem(10, pos, angle);
/*  851:     */   }
/*  852:     */   
/*  853:     */   public void dropSpellCardItem(int num)
/*  854:     */   {
/*  855:1469 */     for (int i = 1; i <= num; i++) {
/*  856:1471 */       dropSpellCardItem(pos(), THShotLib.angle_LimitRandom(THShotLib.angle(0.0D, 1.0D, 0.0D), 90.0F));
/*  857:     */     }
/*  858:     */   }
/*  859:     */   
/*  860:     */   public void dropExtendItem(Vec3 pos, Vec3 angle)
/*  861:     */   {
/*  862:1482 */     dropItem(11, pos, angle);
/*  863:     */   }
/*  864:     */   
/*  865:     */   public void dropExtendItem(int num)
/*  866:     */   {
/*  867:1491 */     for (int i = 1; i <= num; i++) {
/*  868:1493 */       dropExtendItem(pos(), THShotLib.angle_LimitRandom(THShotLib.angle(0.0D, 1.0D, 0.0D), 90.0F));
/*  869:     */     }
/*  870:     */   }
/*  871:     */   
/*  872:     */   public EntityItem dropShotItem(int shotID, int stackNumber, int number, int speed, int color, int gravity, int special, int form)
/*  873:     */   {
/*  874:1511 */     ItemStack shot = new ItemStack(THKaguyaItems.shot_item, stackNumber, shotID);
/*  875:     */     
/*  876:1513 */     NBTTagCompound nbt = shot.getTagCompound();
/*  877:1514 */     if (nbt == null)
/*  878:     */     {
/*  879:1516 */       nbt = new NBTTagCompound();
/*  880:1517 */       nbt.setShort("Number", (short)number);
/*  881:1518 */       nbt.setByte("Speed", (byte)(speed % 64));
/*  882:1519 */       nbt.setByte("Color", (byte)(color % 16));
/*  883:1520 */       nbt.setByte("Gravity", (byte)(gravity % 16));
/*  884:1521 */       nbt.setInteger("Special", special);
/*  885:1522 */       nbt.setByte("DanmakuForm", (byte)(form % 8));
/*  886:1523 */       shot.setTagCompound(nbt);
/*  887:     */     }
/*  888:1526 */     return entityDropItem(shot, 0.0F);
/*  889:     */   }
/*  890:     */   
/*  891:     */   protected void fall(float par1)
/*  892:     */   {
/*  893:1533 */     if (getFlyingHeight() > 0) {
/*  894:1535 */       super.fall(par1);
/*  895:     */     }
/*  896:     */   }
/*  897:     */   
/*  898:     */   protected void updateFallState(double par1, boolean par3) {}
/*  899:     */   
/*  900:     */   protected float getSoundVolume()
/*  901:     */   {
/*  902:1549 */     return 0.3F;
/*  903:     */   }
/*  904:     */   
/*  905:     */   protected float getSoundPitch()
/*  906:     */   {
/*  907:1556 */     return super.getSoundPitch() * 1.95F;
/*  908:     */   }
/*  909:     */   
/*  910:     */   protected String getLivingSound()
/*  911:     */   {
/*  912:1563 */     return this.rand.nextInt(4) != 0 ? null : "mob.bat.idle";
/*  913:     */   }
/*  914:     */   
/*  915:     */   protected String getHurtSound()
/*  916:     */   {
/*  917:1570 */     return "mob.bat.hurt";
/*  918:     */   }
/*  919:     */   
/*  920:     */   protected String getDeathSound()
/*  921:     */   {
/*  922:1577 */     return "mob.bat.death";
/*  923:     */   }
/*  924:     */   
/*  925:     */   public int getMaxSpawnedInChunk()
/*  926:     */   {
/*  927:1584 */     return 1;
/*  928:     */   }
/*  929:     */   
/*  930:     */   public byte getForm()
/*  931:     */   {
/*  932:1593 */     return this.dataWatcher.getWatchableObjectByte(16);
/*  933:     */   }
/*  934:     */   
/*  935:     */   public void setForm(byte type)
/*  936:     */   {
/*  937:1602 */     this.dataWatcher.updateObject(16, Byte.valueOf(type));
/*  938:     */   }
/*  939:     */   
/*  940:     */   protected boolean teleportRandomly(double range)
/*  941:     */   {
/*  942:1616 */     int count = 0;
/*  943:     */     double warpX;
/*  944:     */     double warpY;
/*  945:     */     double warpZ;
/*  946:     */     do
/*  947:     */     {
/*  948:1619 */       warpX = this.posX + (this.rand.nextDouble() - 0.5D) * range;
/*  949:1620 */       warpY = this.posY + (this.rand.nextInt((int)range) - (int)(range / 2.0D));
/*  950:1621 */       warpZ = this.posZ + (this.rand.nextDouble() - 0.5D) * range;
/*  951:1622 */       count++;
/*  952:1623 */     } while ((!teleportTo(warpX, warpY, warpZ)) && (count < 10));
/*  953:1624 */     return true;
/*  954:     */   }
/*  955:     */   
/*  956:     */   protected boolean teleportToEntity(Entity par1Entity)
/*  957:     */   {
/*  958:1632 */     Vec3 vec3 = Vec3.createVectorHelper(this.posX - par1Entity.posX, this.boundingBox.minY + this.height / 2.0F - par1Entity.posY + par1Entity.getEyeHeight(), this.posZ - par1Entity.posZ);
/*  959:1633 */     vec3 = vec3.normalize();
/*  960:1634 */     double d0 = 16.0D;
/*  961:1635 */     double d1 = this.posX + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3.xCoord * d0;
/*  962:1636 */     double d2 = this.posY + (this.rand.nextInt(16) - 8) - vec3.yCoord * d0;
/*  963:1637 */     double d3 = this.posZ + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3.zCoord * d0;
/*  964:1638 */     return teleportTo(d1, d2, d3);
/*  965:     */   }
/*  966:     */   
/*  967:     */   protected boolean teleportTo(double par1, double par3, double par5)
/*  968:     */   {
/*  969:1646 */     EnderTeleportEvent event = new EnderTeleportEvent(this, par1, par3, par5, 0.0F);
/*  970:1647 */     if (MinecraftForge.EVENT_BUS.post(event)) {
/*  971:1648 */       return false;
/*  972:     */     }
/*  973:1650 */     double d3 = this.posX;
/*  974:1651 */     double d4 = this.posY;
/*  975:1652 */     double d5 = this.posZ;
/*  976:1653 */     this.posX = event.targetX;
/*  977:1654 */     this.posY = event.targetY;
/*  978:1655 */     this.posZ = event.targetZ;
/*  979:1656 */     boolean flag = false;
/*  980:1657 */     int i = MathHelper.floor_double(this.posX);
/*  981:1658 */     int j = MathHelper.floor_double(this.posY);
/*  982:1659 */     int k = MathHelper.floor_double(this.posZ);
/*  983:1661 */     if (this.worldObj.blockExists(i, j, k))
/*  984:     */     {
/*  985:1663 */       boolean flag1 = false;
/*  986:1665 */       while ((!flag1) && (j > 0))
/*  987:     */       {
/*  988:1667 */         Block block = this.worldObj.getBlock(i, j - 1, k);
/*  989:1669 */         if (block.getMaterial().blocksMovement())
/*  990:     */         {
/*  991:1671 */           flag1 = true;
/*  992:     */         }
/*  993:     */         else
/*  994:     */         {
/*  995:1675 */           this.posY -= 1.0D;
/*  996:1676 */           j--;
/*  997:     */         }
/*  998:     */       }
/*  999:1680 */       if (flag1)
/* 1000:     */       {
/* 1001:1682 */         setPosition(this.posX, this.posY, this.posZ);
/* 1002:1684 */         if ((this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty()) && (!this.worldObj.isAnyLiquid(this.boundingBox))) {
/* 1003:1686 */           flag = true;
/* 1004:     */         }
/* 1005:     */       }
/* 1006:     */     }
/* 1007:1691 */     if (!flag)
/* 1008:     */     {
/* 1009:1693 */       setPosition(d3, d4, d5);
/* 1010:1694 */       return false;
/* 1011:     */     }
/* 1012:1698 */     short short1 = 128;
/* 1013:1700 */     for (int l = 0; l < short1; l++)
/* 1014:     */     {
/* 1015:1702 */       double d6 = l / (short1 - 1.0D);
/* 1016:1703 */       float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
/* 1017:1704 */       float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
/* 1018:1705 */       float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
/* 1019:1706 */       double d7 = d3 + (this.posX - d3) * d6 + (this.rand.nextDouble() - 0.5D) * this.width * 2.0D;
/* 1020:1707 */       double d8 = d4 + (this.posY - d4) * d6 + this.rand.nextDouble() * this.height;
/* 1021:1708 */       double d9 = d5 + (this.posZ - d5) * d6 + (this.rand.nextDouble() - 0.5D) * this.width * 2.0D;
/* 1022:1709 */       this.worldObj.spawnParticle("portal", d7, d8, d9, f, f1, f2);
/* 1023:     */     }
/* 1024:1712 */     this.worldObj.playSoundEffect(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
/* 1025:1713 */     playSound("mob.endermen.portal", 1.0F, 1.0F);
/* 1026:1714 */     return true;
/* 1027:     */   }
/* 1028:     */   
/* 1029:     */   public void readEntityFromNBT(NBTTagCompound nbtTagCompound)
/* 1030:     */   {
/* 1031:1724 */     super.readEntityFromNBT(nbtTagCompound);
/* 1032:1725 */     if (nbtTagCompound != null)
/* 1033:     */     {
/* 1034:1728 */       setForm(nbtTagCompound.getByte("Form"));
/* 1035:1729 */       setDanmakuPattern(nbtTagCompound.getInteger("Danmaku"));
/* 1036:1730 */       this.danmakuSpan = nbtTagCompound.getShort("Span");
/* 1037:1731 */       this.shotForm = nbtTagCompound.getByte("ShotForm");
/* 1038:1732 */       this.shotColor = nbtTagCompound.getByte("ShotColor");
/* 1039:1733 */       this.speedRate = nbtTagCompound.getFloat("Speed");
/* 1040:1734 */       setAttackDistance(nbtTagCompound.getByte("AttackRange"));
/* 1041:1735 */       setDetectionDistance(nbtTagCompound.getByte("DetectRange"));
/* 1042:1736 */       setFlyingHeight(nbtTagCompound.getByte("Flying"));
/* 1043:1737 */       this.special = nbtTagCompound.getShort("Special");
/* 1044:1738 */       if (getFlyingHeight() <= 0) {
/* 1045:1740 */         this.isFlyingMode = false;
/* 1046:     */       } else {
/* 1047:1744 */         this.isFlyingMode = true;
/* 1048:     */       }
/* 1049:1746 */       if ((this.shotForm < 0) || (this.shotForm >= 32)) {
/* 1050:1748 */         this.shotForm = 0;
/* 1051:     */       }
/* 1052:1750 */       if ((this.shotColor < 0) || (this.shotColor >= 10)) {
/* 1053:1752 */         this.shotColor = 8;
/* 1054:     */       }
/* 1055:     */     }
/* 1056:     */     else
/* 1057:     */     {
/* 1058:1757 */       setDanmakuPattern(0);
/* 1059:1758 */       setAttackDistance(14.0D);
/* 1060:1759 */       setDetectionDistance(30.0D);
/* 1061:1760 */       setFlyingHeight(3);
/* 1062:     */     }
/* 1063:     */   }
/* 1064:     */   
/* 1065:     */   public void writeEntityToNBT(NBTTagCompound nbtTagCompound)
/* 1066:     */   {
/* 1067:1770 */     super.writeEntityToNBT(nbtTagCompound);
/* 1068:     */     
/* 1069:1772 */     nbtTagCompound.setByte("Form", getForm());
/* 1070:1773 */     nbtTagCompound.setInteger("Danmaku", getDanmakuPattern());
/* 1071:1774 */     nbtTagCompound.setShort("Span", (short)this.danmakuSpan);
/* 1072:1775 */     nbtTagCompound.setByte("ShotForm", this.shotForm);
/* 1073:1776 */     nbtTagCompound.setByte("ShotColor", this.shotColor);
/* 1074:1777 */     nbtTagCompound.setFloat("Speed", this.speedRate);
/* 1075:1778 */     nbtTagCompound.setByte("AttackRange", (byte)(int)getAttackDistance());
/* 1076:1779 */     nbtTagCompound.setByte("DetectRange", (byte)(int)getDetectionDistance());
/* 1077:1780 */     nbtTagCompound.setByte("Flying", getFlyingHeight());
/* 1078:1781 */     nbtTagCompound.setShort("Special", this.special);
/* 1079:     */   }
/* 1080:     */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.living.EntityDanmakuMob
 * JD-Core Version:    0.7.0.1
 */