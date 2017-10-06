/*   1:    */ package thKaguyaMod.entity.living;
/*   2:    */ 
/*   3:    */ import java.util.Collections;
/*   4:    */ import java.util.HashMap;
/*   5:    */ import java.util.Map;
/*   6:    */ import java.util.Random;
/*   7:    */ import net.minecraft.block.Block;
/*   8:    */ import net.minecraft.entity.IMerchant;
/*   9:    */ import net.minecraft.entity.player.EntityPlayer;
/*  10:    */ import net.minecraft.entity.player.InventoryPlayer;
/*  11:    */ import net.minecraft.init.Blocks;
/*  12:    */ import net.minecraft.init.Items;
/*  13:    */ import net.minecraft.item.Item;
/*  14:    */ import net.minecraft.item.ItemStack;
/*  15:    */ import net.minecraft.nbt.NBTTagCompound;
/*  16:    */ import net.minecraft.util.AxisAlignedBB;
/*  17:    */ import net.minecraft.util.MathHelper;
/*  18:    */ import net.minecraft.util.StatCollector;
/*  19:    */ import net.minecraft.util.Tuple;
/*  20:    */ import net.minecraft.util.Vec3;
/*  21:    */ import net.minecraft.village.MerchantRecipe;
/*  22:    */ import net.minecraft.village.MerchantRecipeList;
/*  23:    */ import net.minecraft.world.World;
/*  24:    */ import thKaguyaMod.ShotData;
/*  25:    */ import thKaguyaMod.THShotLib;
/*  26:    */ import thKaguyaMod.init.THKaguyaItems;
/*  27:    */ 
/*  28:    */ public class EntitySanae
/*  29:    */   extends EntityDanmakuMob
/*  30:    */   implements IDanmakuMob, IMerchant
/*  31:    */ {
/*  32:    */   private EntityPlayer buyingPlayer;
/*  33:    */   private MerchantRecipeList buyingList;
/*  34:    */   private float field_82191_bN;
/*  35: 38 */   public static final Map villagersSellingList = new HashMap();
/*  36:    */   
/*  37:    */   public EntitySanae(World world)
/*  38:    */   {
/*  39: 42 */     super(world);
/*  40:    */     
/*  41: 44 */     setSize(0.6F, 1.7F);
/*  42:    */     
/*  43: 46 */     this.experienceValue = 760;
/*  44:    */     
/*  45: 48 */     setDanmakuMobName("Sanae Kochiya");
/*  46: 49 */     setSpecies(0, 32);
/*  47:    */     
/*  48: 51 */     setDanmakuPattern(0);
/*  49: 52 */     setMaxHP(56.0F);
/*  50: 53 */     setHealth(56.0F);
/*  51: 54 */     setSpeed(0.5D);
/*  52: 55 */     setAttackDistance(14.0D);
/*  53: 56 */     setDetectionDistance(0.0D);
/*  54: 57 */     setFlyingHeight(0);
/*  55: 58 */     this.isFlyingMode = false;
/*  56:    */     
/*  57: 60 */     this.isSpellCardMode = false;
/*  58: 61 */     this.attackInterval = 0;
/*  59:    */   }
/*  60:    */   
/*  61:    */   protected void entityInit()
/*  62:    */   {
/*  63: 68 */     super.entityInit();
/*  64: 69 */     this.buyingList = new MerchantRecipeList();
/*  65: 70 */     this.buyingList.add(new MerchantRecipe(new ItemStack(THKaguyaItems.shot_material, 64, 0), new ItemStack(THKaguyaItems.illness_recovery_charm, 1, 0)));
/*  66:    */   }
/*  67:    */   
/*  68:    */   public int getUsingSpellCardNo()
/*  69:    */   {
/*  70: 81 */     switch (getDanmakuPattern())
/*  71:    */     {
/*  72:    */     case 101: 
/*  73: 84 */       return 14;
/*  74:    */     case 102: 
/*  75: 86 */       return 17;
/*  76:    */     case 103: 
/*  77: 88 */       return 18;
/*  78:    */     case 104: 
/*  79: 90 */       return 16;
/*  80:    */     }
/*  81: 92 */     return -1;
/*  82:    */   }
/*  83:    */   
/*  84:    */   protected void onDeathUpdate()
/*  85:    */   {
/*  86:100 */     if (this.ticksExisted <= this.lastTime) {
/*  87:102 */       return;
/*  88:    */     }
/*  89:106 */     switch (getDanmakuPattern())
/*  90:    */     {
/*  91:    */     case 1: 
/*  92:109 */       setFlyingHeight(2);
/*  93:110 */       moveDanmakuAttack(101, 40, 60.0F, 160);
/*  94:111 */       break;
/*  95:    */     case 101: 
/*  96:113 */       moveDanmakuAttack(127, 90, 40.0F, 160);
/*  97:114 */       break;
/*  98:    */     case 102: 
/*  99:116 */       moveDanmakuAttack(127, 90, 40.0F, 160);
/* 100:117 */       break;
/* 101:    */     case 103: 
/* 102:119 */       moveDanmakuAttack(127, 90, 40.0F, 160);
/* 103:120 */       break;
/* 104:    */     case 104: 
/* 105:122 */       moveDanmakuAttack(127, 90, 40.0F, 160);
/* 106:123 */       break;
/* 107:    */     default: 
/* 108:125 */       if (this.deathTime % 6 == 0) {
/* 109:127 */         THShotLib.explosionEffect(this.worldObj, this.posX, this.posY, this.posZ, 1.0F + this.deathTime * 0.1F);
/* 110:    */       }
/* 111:129 */       super.onDeathUpdate();
/* 112:    */     }
/* 113:    */   }
/* 114:    */   
/* 115:    */   public void danmakuPattern(int level)
/* 116:    */   {
/* 117:137 */     Vec3 look = getLookVec();
/* 118:138 */     switch (getDanmakuPattern())
/* 119:    */     {
/* 120:    */     case 1: 
/* 121:141 */       danmaku01(look, level);
/* 122:142 */       break;
/* 123:    */     case 101: 
/* 124:144 */       if (this.attackCounter == 1) {
/* 125:146 */         useSpellCard(14);
/* 126:148 */       } else if (this.attackCounter >= 140) {
/* 127:150 */         this.attackCounter = 0;
/* 128:    */       }
/* 129:    */       break;
/* 130:    */     case 102: 
/* 131:154 */       if (this.attackCounter == 1) {
/* 132:156 */         useSpellCard(17);
/* 133:158 */       } else if (this.attackCounter >= 140) {
/* 134:160 */         this.attackCounter = 0;
/* 135:    */       }
/* 136:    */       break;
/* 137:    */     case 103: 
/* 138:164 */       if (this.attackCounter == 1) {
/* 139:166 */         useSpellCard(18);
/* 140:168 */       } else if (this.attackCounter >= 140) {
/* 141:170 */         this.attackCounter = 0;
/* 142:    */       }
/* 143:    */       break;
/* 144:    */     case 104: 
/* 145:174 */       if (this.attackCounter == 1) {
/* 146:176 */         useSpellCard(16);
/* 147:178 */       } else if (this.attackCounter >= 140) {
/* 148:180 */         this.attackCounter = 0;
/* 149:    */       }
/* 150:    */       break;
/* 151:    */     }
/* 152:    */   }
/* 153:    */   
/* 154:    */   private void danmaku01(Vec3 look, int level)
/* 155:    */   {
/* 156:190 */     if (this.attackCounter < 50)
/* 157:    */     {
/* 158:192 */       setStopStart();
/* 159:193 */       double size = 2.0D;
/* 160:194 */       int div = 10;
/* 161:195 */       double divLength = size / div;
/* 162:    */       
/* 163:197 */       Vec3 pos = THShotLib.pos(Math.cos(1.570796326794897D) * size, Math.sin(1.570796326794897D) * size, 0.0D);
/* 164:198 */       Vec3 pos2 = pos;
/* 165:    */       
/* 166:200 */       float angle = 4.39823F;
/* 167:202 */       for (int i = 0; i < this.attackCounter; i++)
/* 168:    */       {
/* 169:204 */         pos.xCoord += Math.cos(angle) * divLength;
/* 170:205 */         pos.yCoord += Math.sin(angle) * divLength;
/* 171:207 */         if (i % div == div - 1) {
/* 172:209 */           angle += 2.513274F;
/* 173:    */         }
/* 174:    */       }
/* 175:212 */       pos = THShotLib.getOuterProduct(look, pos);
/* 176:    */       
/* 177:    */ 
/* 178:    */ 
/* 179:    */ 
/* 180:217 */       pos.xCoord += this.posX;
/* 181:218 */       pos.yCoord += THShotLib.getPosYFromEye(this);
/* 182:219 */       pos.zCoord += this.posZ;
/* 183:    */       
/* 184:221 */       THShotLib.createShot(this, this, pos, look, 0.0F, 0.0D, 0.5D, 0.02999999932944775D, gravity_Zero(), ShotData.shot(1, 1, 55 - this.attackCounter, 120));
/* 185:    */     }
/* 186:223 */     else if (this.attackCounter == 50)
/* 187:    */     {
/* 188:225 */       setStopEnd();
/* 189:    */     }
/* 190:227 */     else if (this.attackCounter >= 100)
/* 191:    */     {
/* 192:229 */       this.attackCounter = 0;
/* 193:    */     }
/* 194:    */   }
/* 195:    */   
/* 196:    */   protected Item getDropItem()
/* 197:    */   {
/* 198:236 */     return THKaguyaItems.power_item;
/* 199:    */   }
/* 200:    */   
/* 201:    */   protected void dropFewItems(boolean hasBeenAttackedByPlayer, int lootingLevel)
/* 202:    */   {
/* 203:243 */     super.dropFewItems(hasBeenAttackedByPlayer, lootingLevel);
/* 204:245 */     if ((hasBeenAttackedByPlayer) && (getUsingSpellCardNo() != -1))
/* 205:    */     {
/* 206:248 */       dropShotItem(4, 16 + this.rand.nextInt(2) + lootingLevel, 5, 32, 2, 0, 41, 2);
/* 207:    */       
/* 208:250 */       dropPowerUpItem(25 + this.rand.nextInt(5) + this.rand.nextInt(1 + lootingLevel));
/* 209:    */       
/* 210:252 */       dropPointItem(25 + this.rand.nextInt(5) + this.rand.nextInt(1 + lootingLevel));
/* 211:    */     }
/* 212:    */   }
/* 213:    */   
/* 214:    */   public void readEntityFromNBT(NBTTagCompound nbtTagCompound)
/* 215:    */   {
/* 216:260 */     super.readEntityFromNBT(nbtTagCompound);
/* 217:261 */     if (nbtTagCompound.hasKey("Offers", 10))
/* 218:    */     {
/* 219:263 */       NBTTagCompound nbttagcompound1 = nbtTagCompound.getCompoundTag("Offers");
/* 220:264 */       this.buyingList = new MerchantRecipeList(nbttagcompound1);
/* 221:    */     }
/* 222:    */   }
/* 223:    */   
/* 224:    */   public void writeEntityToNBT(NBTTagCompound nbtTagCompound)
/* 225:    */   {
/* 226:272 */     super.writeEntityToNBT(nbtTagCompound);
/* 227:273 */     if (this.buyingList != null) {
/* 228:275 */       nbtTagCompound.setTag("Offers", this.buyingList.getRecipiesAsTags());
/* 229:    */     }
/* 230:    */   }
/* 231:    */   
/* 232:    */   public boolean getCanSpawnHere()
/* 233:    */   {
/* 234:283 */     if (!super.getCanSpawnHere()) {
/* 235:285 */       return false;
/* 236:    */     }
/* 237:288 */     if (this.rand.nextInt(100) < 98) {
/* 238:290 */       return false;
/* 239:    */     }
/* 240:293 */     int yPosition = MathHelper.floor_double(this.boundingBox.minY);
/* 241:294 */     int xPosition = MathHelper.floor_double(this.posX);
/* 242:295 */     int zPosition = MathHelper.floor_double(this.posZ);
/* 243:296 */     Block pointBlock = this.worldObj.getBlock(xPosition, yPosition - 1, zPosition);
/* 244:299 */     if ((this.worldObj.getBlock(xPosition - 1, yPosition - 1, zPosition) == pointBlock) && 
/* 245:300 */       (this.worldObj.getBlock(xPosition + 1, yPosition - 1, zPosition) == pointBlock) && 
/* 246:301 */       (this.worldObj.getBlock(xPosition, yPosition - 1, zPosition - 1) == pointBlock) && 
/* 247:302 */       (this.worldObj.getBlock(xPosition, yPosition - 1, zPosition + 1) == pointBlock)) {
/* 248:304 */       return false;
/* 249:    */     }
/* 250:307 */     return true;
/* 251:    */   }
/* 252:    */   
/* 253:    */   public boolean isEntityInvulnerable()
/* 254:    */   {
/* 255:314 */     if (getDanmakuPattern() == 0) {
/* 256:316 */       return true;
/* 257:    */     }
/* 258:318 */     return false;
/* 259:    */   }
/* 260:    */   
/* 261:    */   public boolean interact(EntityPlayer player)
/* 262:    */   {
/* 263:326 */     ItemStack itemstack = player.inventory.getCurrentItem();
/* 264:327 */     boolean flag = (itemstack != null) && (itemstack.getItem() == Items.spawn_egg);
/* 265:329 */     if ((!flag) && (isEntityAlive()) && (!player.isSneaking()))
/* 266:    */     {
/* 267:331 */       if (!this.worldObj.isRemote)
/* 268:    */       {
/* 269:333 */         setCustomer(player);
/* 270:334 */         player.displayGUIMerchant(this, StatCollector.translateToLocal("entity.Sanae.name"));
/* 271:    */       }
/* 272:338 */       return true;
/* 273:    */     }
/* 274:342 */     return super.interact(player);
/* 275:    */   }
/* 276:    */   
/* 277:    */   public void setCustomer(EntityPlayer player)
/* 278:    */   {
/* 279:348 */     this.buyingPlayer = player;
/* 280:    */   }
/* 281:    */   
/* 282:    */   public EntityPlayer getCustomer()
/* 283:    */   {
/* 284:353 */     return this.buyingPlayer;
/* 285:    */   }
/* 286:    */   
/* 287:    */   public MerchantRecipeList getRecipes(EntityPlayer var1)
/* 288:    */   {
/* 289:358 */     return this.buyingList;
/* 290:    */   }
/* 291:    */   
/* 292:    */   public void setRecipes(MerchantRecipeList merchantRecipeList) {}
/* 293:    */   
/* 294:    */   public void useRecipe(MerchantRecipe par1MerchantRecipe)
/* 295:    */   {
/* 296:369 */     par1MerchantRecipe.incrementToolUses();
/* 297:370 */     this.livingSoundTime = (-getTalkInterval());
/* 298:373 */     if ((!par1MerchantRecipe.hasSameIDsAs((MerchantRecipe)this.buyingList.get(this.buyingList.size() - 1))) || 
/* 299:    */     
/* 300:    */ 
/* 301:    */ 
/* 302:    */ 
/* 303:    */ 
/* 304:    */ 
/* 305:    */ 
/* 306:    */ 
/* 307:    */ 
/* 308:    */ 
/* 309:    */ 
/* 310:    */ 
/* 311:    */ 
/* 312:    */ 
/* 313:388 */       (par1MerchantRecipe.getItemToBuy().getItem() == Items.emerald)) {}
/* 314:    */   }
/* 315:    */   
/* 316:    */   public void func_110297_a_(ItemStack par1ItemStack)
/* 317:    */   {
/* 318:396 */     if ((!this.worldObj.isRemote) && (this.livingSoundTime > -getTalkInterval() + 20)) {
/* 319:398 */       this.livingSoundTime = (-getTalkInterval());
/* 320:    */     }
/* 321:    */   }
/* 322:    */   
/* 323:    */   private void addDefaultEquipmentAndRecipies(int par1)
/* 324:    */   {
/* 325:417 */     if (this.buyingList != null) {
/* 326:419 */       this.field_82191_bN = (MathHelper.sqrt_float(this.buyingList.size()) * 0.2F);
/* 327:    */     } else {
/* 328:423 */       this.field_82191_bN = 0.0F;
/* 329:    */     }
/* 330:427 */     MerchantRecipeList merchantrecipelist = new MerchantRecipeList();
/* 331:    */     
/* 332:    */ 
/* 333:    */ 
/* 334:    */ 
/* 335:432 */     func_146091_a(merchantrecipelist, Items.wheat, this.rand, adjustProbability(0.9F));
/* 336:433 */     func_146091_a(merchantrecipelist, Item.getItemFromBlock(Blocks.wool), this.rand, adjustProbability(0.5F));
/* 337:    */     
/* 338:    */ 
/* 339:    */ 
/* 340:    */ 
/* 341:    */ 
/* 342:    */ 
/* 343:440 */     Collections.shuffle(merchantrecipelist);
/* 344:442 */     if (this.buyingList == null) {
/* 345:444 */       this.buyingList = new MerchantRecipeList();
/* 346:    */     }
/* 347:447 */     for (int l = 0; (l < par1) && (l < merchantrecipelist.size()); l++) {
/* 348:449 */       this.buyingList.addToListWithCheck((MerchantRecipe)merchantrecipelist.get(l));
/* 349:    */     }
/* 350:    */   }
/* 351:    */   
/* 352:    */   public static void func_146091_a(MerchantRecipeList p_146091_0_, Item p_146091_1_, Random p_146091_2_, float p_146091_3_)
/* 353:    */   {
/* 354:455 */     if (p_146091_2_.nextFloat() < p_146091_3_) {
/* 355:457 */       p_146091_0_.add(new MerchantRecipe(func_146088_a(p_146091_1_, p_146091_2_), Items.emerald));
/* 356:    */     }
/* 357:    */   }
/* 358:    */   
/* 359:    */   private static ItemStack func_146088_a(Item p_146088_0_, Random p_146088_1_)
/* 360:    */   {
/* 361:463 */     return new ItemStack(p_146088_0_, func_146092_b(p_146088_0_, p_146088_1_), 0);
/* 362:    */   }
/* 363:    */   
/* 364:    */   private static int func_146092_b(Item p_146092_0_, Random p_146092_1_)
/* 365:    */   {
/* 366:468 */     Tuple tuple = (Tuple)villagersSellingList.get(p_146092_0_);
/* 367:469 */     return ((Integer)tuple.getFirst()).intValue() >= ((Integer)tuple.getSecond()).intValue() ? ((Integer)tuple.getFirst()).intValue() : tuple == null ? 1 : ((Integer)tuple.getFirst()).intValue() + p_146092_1_.nextInt(((Integer)tuple.getSecond()).intValue() - ((Integer)tuple.getFirst()).intValue());
/* 368:    */   }
/* 369:    */   
/* 370:    */   private float adjustProbability(float par1)
/* 371:    */   {
/* 372:477 */     float f1 = par1 + this.field_82191_bN;
/* 373:478 */     return f1 > 0.9F ? 0.9F - (f1 - 0.9F) : f1;
/* 374:    */   }
/* 375:    */   
/* 376:    */   static
/* 377:    */   {
/* 378:483 */     villagersSellingList.put(Items.coal, new Tuple(Integer.valueOf(16), Integer.valueOf(24)));
/* 379:484 */     villagersSellingList.put(Items.iron_ingot, new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
/* 380:485 */     villagersSellingList.put(Items.gold_ingot, new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
/* 381:486 */     villagersSellingList.put(Items.diamond, new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
/* 382:487 */     villagersSellingList.put(Items.paper, new Tuple(Integer.valueOf(24), Integer.valueOf(36)));
/* 383:488 */     villagersSellingList.put(Items.book, new Tuple(Integer.valueOf(11), Integer.valueOf(13)));
/* 384:    */   }
/* 385:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.living.EntitySanae
 * JD-Core Version:    0.7.0.1
 */