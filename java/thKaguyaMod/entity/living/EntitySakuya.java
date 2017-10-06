/*   1:    */ package thKaguyaMod.entity.living;
/*   2:    */ 
/*   3:    */ import java.util.Collections;
/*   4:    */ import java.util.HashMap;
/*   5:    */ import java.util.Map;
/*   6:    */ import java.util.Random;
/*   7:    */ import net.minecraft.block.Block;
/*   8:    */ import net.minecraft.entity.IMerchant;
/*   9:    */ import net.minecraft.entity.item.EntityItem;
/*  10:    */ import net.minecraft.entity.player.EntityPlayer;
/*  11:    */ import net.minecraft.entity.player.InventoryPlayer;
/*  12:    */ import net.minecraft.init.Blocks;
/*  13:    */ import net.minecraft.init.Items;
/*  14:    */ import net.minecraft.item.Item;
/*  15:    */ import net.minecraft.item.ItemStack;
/*  16:    */ import net.minecraft.nbt.NBTTagCompound;
/*  17:    */ import net.minecraft.util.AxisAlignedBB;
/*  18:    */ import net.minecraft.util.DamageSource;
/*  19:    */ import net.minecraft.util.MathHelper;
/*  20:    */ import net.minecraft.util.StatCollector;
/*  21:    */ import net.minecraft.util.Tuple;
/*  22:    */ import net.minecraft.util.Vec3;
/*  23:    */ import net.minecraft.village.MerchantRecipe;
/*  24:    */ import net.minecraft.village.MerchantRecipeList;
/*  25:    */ import net.minecraft.world.World;
/*  26:    */ import thKaguyaMod.THShotLib;
/*  27:    */ import thKaguyaMod.init.THKaguyaItems;
/*  28:    */ 
/*  29:    */ public class EntitySakuya
/*  30:    */   extends EntityDanmakuMob
/*  31:    */   implements IDanmakuMob, IMerchant
/*  32:    */ {
/*  33:    */   private EntityPlayer buyingPlayer;
/*  34:    */   private MerchantRecipeList buyingList;
/*  35:    */   private float field_82191_bN;
/*  36: 35 */   public static final Map villagersSellingList = new HashMap();
/*  37:    */   
/*  38:    */   public EntitySakuya(World world)
/*  39:    */   {
/*  40: 39 */     super(world);
/*  41:    */     
/*  42: 41 */     setSize(1.0F, 1.8F);
/*  43:    */     
/*  44: 43 */     this.experienceValue = 250;
/*  45:    */     
/*  46: 45 */     setDanmakuMobName("Sakuya Izayoi");
/*  47: 46 */     setSpecies(0);
/*  48:    */     
/*  49:    */ 
/*  50: 49 */     setDanmakuPattern(0);
/*  51: 50 */     setMaxHP(44.0F);
/*  52: 51 */     setHealth(44.0F);
/*  53: 52 */     setSpeed(0.5D);
/*  54: 53 */     setAttackDistance(14.0D);
/*  55: 54 */     setDetectionDistance(0.0D);
/*  56: 55 */     setFlyingHeight(0);
/*  57: 56 */     this.isFlyingMode = false;
/*  58:    */     
/*  59: 58 */     this.isSpellCardMode = false;
/*  60: 59 */     this.attackInterval = 0;
/*  61:    */   }
/*  62:    */   
/*  63:    */   protected void entityInit()
/*  64:    */   {
/*  65: 66 */     super.entityInit();
/*  66: 67 */     this.buyingList = new MerchantRecipeList();
/*  67: 68 */     this.buyingList.add(new MerchantRecipe(new ItemStack(THKaguyaItems.shot_material, 16, 0), new ItemStack(THKaguyaItems.silver_knife, 1, 0)));
/*  68:    */     
/*  69: 70 */     this.buyingList.add(new MerchantRecipe(new ItemStack(THKaguyaItems.shot_material, 64, 0), new ItemStack(THKaguyaItems.sakuya_stopwatch, 1, 0)));
/*  70:    */   }
/*  71:    */   
/*  72:    */   public int getUsingSpellCardNo()
/*  73:    */   {
/*  74: 80 */     switch (getDanmakuPattern())
/*  75:    */     {
/*  76:    */     case 101: 
/*  77: 83 */       return 8;
/*  78:    */     }
/*  79: 85 */     return -1;
/*  80:    */   }
/*  81:    */   
/*  82:    */   protected void onDeathUpdate()
/*  83:    */   {
/*  84: 93 */     switch (getDanmakuPattern())
/*  85:    */     {
/*  86:    */     case 1: 
/*  87: 96 */       setFlyingHeight(2);
/*  88: 97 */       moveDanmakuAttack(101, 40, 60.0F, 160);
/*  89: 98 */       break;
/*  90:    */     case 101: 
/*  91:100 */       moveDanmakuAttack(127, 90, 40.0F, 160);
/*  92:101 */       break;
/*  93:    */     default: 
/*  94:103 */       if (this.deathTime % 6 == 0) {
/*  95:105 */         THShotLib.explosionEffect(this.worldObj, this.posX, this.posY, this.posZ, 1.0F + this.deathTime * 0.1F);
/*  96:    */       }
/*  97:107 */       super.onDeathUpdate();
/*  98:    */     }
/*  99:    */   }
/* 100:    */   
/* 101:    */   public void onUpdate()
/* 102:    */   {
/* 103:117 */     super.onUpdate();
/* 104:    */   }
/* 105:    */   
/* 106:    */   public void danmakuPattern(int level)
/* 107:    */   {
/* 108:128 */     Vec3 look = getLookVec();
/* 109:129 */     switch (getDanmakuPattern())
/* 110:    */     {
/* 111:    */     case 1: 
/* 112:    */       break;
/* 113:    */     case 101: 
/* 114:135 */       if (this.attackCounter == 1) {
/* 115:137 */         useSpellCard(getUsingSpellCardNo());
/* 116:139 */       } else if (this.attackCounter >= 140) {
/* 117:141 */         this.attackCounter = 0;
/* 118:    */       }
/* 119:    */       break;
/* 120:    */     }
/* 121:    */   }
/* 122:    */   
/* 123:    */   protected boolean canFairyCall()
/* 124:    */   {
/* 125:183 */     return false;
/* 126:    */   }
/* 127:    */   
/* 128:    */   protected float applyPotionDamageCalculations(DamageSource damageSource, float damage)
/* 129:    */   {
/* 130:192 */     damage = super.applyPotionDamageCalculations(damageSource, damage);
/* 131:194 */     if (isEntityInvulnerable()) {
/* 132:196 */       damage = (float)(damage * 0.05D);
/* 133:    */     }
/* 134:199 */     return damage;
/* 135:    */   }
/* 136:    */   
/* 137:    */   public boolean isEntityInvulnerable()
/* 138:    */   {
/* 139:207 */     if (getDanmakuPattern() == 0) {
/* 140:209 */       return true;
/* 141:    */     }
/* 142:211 */     return false;
/* 143:    */   }
/* 144:    */   
/* 145:    */   protected void updateAITasks()
/* 146:    */   {
/* 147:217 */     super.updateAITasks();
/* 148:    */   }
/* 149:    */   
/* 150:    */   protected void dropFewItems(boolean par1, int par2)
/* 151:    */   {
/* 152:224 */     super.dropFewItems(par1, par2);
/* 153:226 */     if (isSpellCardAttack())
/* 154:    */     {
/* 155:228 */       int j = 12;
/* 156:233 */       for (int k = 0; k < j; k++)
/* 157:    */       {
/* 158:235 */         EntityItem item = dropItem(THKaguyaItems.power_item, 1);
/* 159:236 */         item.rotationYaw = (k * 30.0F);
/* 160:237 */         item.rotationPitch = -60.0F;
/* 161:238 */         Vec3 vec3 = THShotLib.getVecFromAngle(item.rotationYaw, item.rotationPitch, 0.5D);
/* 162:239 */         item.setPosition(item.posX + vec3.xCoord * 2.0D, item.posY, item.posZ + vec3.zCoord * 2.0D);
/* 163:    */         
/* 164:241 */         item.motionX = vec3.xCoord;
/* 165:242 */         item.motionY = vec3.yCoord;
/* 166:243 */         item.motionZ = vec3.zCoord;
/* 167:    */       }
/* 168:246 */       for (int k = 0; k < j; k++)
/* 169:    */       {
/* 170:248 */         EntityItem item = dropItem(THKaguyaItems.point_item, 1);
/* 171:249 */         item.rotationYaw = (k * 30.0F + 15.0F);
/* 172:250 */         item.rotationPitch = -60.0F;
/* 173:251 */         Vec3 vec3 = THShotLib.getVecFromAngle(item.rotationYaw, item.rotationPitch, 0.300000011920929D);
/* 174:252 */         item.setPosition(item.posX + vec3.xCoord * 2.0D, item.posY, item.posZ + vec3.zCoord * 2.0D);
/* 175:    */         
/* 176:254 */         item.motionX = vec3.xCoord;
/* 177:255 */         item.motionY = vec3.yCoord;
/* 178:256 */         item.motionZ = vec3.zCoord;
/* 179:    */       }
/* 180:    */     }
/* 181:    */   }
/* 182:    */   
/* 183:    */   public int getMaxSpawnedInChunk()
/* 184:    */   {
/* 185:269 */     return 1;
/* 186:    */   }
/* 187:    */   
/* 188:    */   public void readEntityFromNBT(NBTTagCompound nbtTagCompound)
/* 189:    */   {
/* 190:276 */     super.readEntityFromNBT(nbtTagCompound);
/* 191:277 */     if (nbtTagCompound.hasKey("Offers", 10))
/* 192:    */     {
/* 193:279 */       NBTTagCompound nbttagcompound1 = nbtTagCompound.getCompoundTag("Offers");
/* 194:280 */       this.buyingList = new MerchantRecipeList(nbttagcompound1);
/* 195:    */     }
/* 196:    */   }
/* 197:    */   
/* 198:    */   public void writeEntityToNBT(NBTTagCompound nbtTagCompound)
/* 199:    */   {
/* 200:288 */     super.writeEntityToNBT(nbtTagCompound);
/* 201:289 */     if (this.buyingList != null) {
/* 202:291 */       nbtTagCompound.setTag("Offers", this.buyingList.getRecipiesAsTags());
/* 203:    */     }
/* 204:    */   }
/* 205:    */   
/* 206:    */   public boolean getCanSpawnHere()
/* 207:    */   {
/* 208:299 */     if (!super.getCanSpawnHere()) {
/* 209:301 */       return false;
/* 210:    */     }
/* 211:304 */     if (this.rand.nextInt(100) < 98) {
/* 212:306 */       return false;
/* 213:    */     }
/* 214:309 */     int yPosition = MathHelper.floor_double(this.boundingBox.minY);
/* 215:310 */     int xPosition = MathHelper.floor_double(this.posX);
/* 216:311 */     int zPosition = MathHelper.floor_double(this.posZ);
/* 217:312 */     Block pointBlock = this.worldObj.getBlock(xPosition, yPosition - 1, zPosition);
/* 218:315 */     if ((this.worldObj.getBlock(xPosition - 1, yPosition - 1, zPosition) == pointBlock) && 
/* 219:316 */       (this.worldObj.getBlock(xPosition + 1, yPosition - 1, zPosition) == pointBlock) && 
/* 220:317 */       (this.worldObj.getBlock(xPosition, yPosition - 1, zPosition - 1) == pointBlock) && 
/* 221:318 */       (this.worldObj.getBlock(xPosition, yPosition - 1, zPosition + 1) == pointBlock)) {
/* 222:320 */       return false;
/* 223:    */     }
/* 224:323 */     return true;
/* 225:    */   }
/* 226:    */   
/* 227:    */   public boolean interact(EntityPlayer player)
/* 228:    */   {
/* 229:331 */     ItemStack itemstack = player.inventory.getCurrentItem();
/* 230:332 */     boolean flag = (itemstack != null) && (itemstack.getItem() == Items.spawn_egg);
/* 231:334 */     if ((!flag) && (isEntityAlive()) && (!player.isSneaking()))
/* 232:    */     {
/* 233:336 */       if (!this.worldObj.isRemote)
/* 234:    */       {
/* 235:338 */         setCustomer(player);
/* 236:339 */         player.displayGUIMerchant(this, StatCollector.translateToLocal("entity.Sakuya.name"));
/* 237:    */       }
/* 238:343 */       return true;
/* 239:    */     }
/* 240:347 */     return super.interact(player);
/* 241:    */   }
/* 242:    */   
/* 243:    */   public void setCustomer(EntityPlayer player)
/* 244:    */   {
/* 245:353 */     this.buyingPlayer = player;
/* 246:    */   }
/* 247:    */   
/* 248:    */   public EntityPlayer getCustomer()
/* 249:    */   {
/* 250:358 */     return this.buyingPlayer;
/* 251:    */   }
/* 252:    */   
/* 253:    */   public MerchantRecipeList getRecipes(EntityPlayer var1)
/* 254:    */   {
/* 255:363 */     return this.buyingList;
/* 256:    */   }
/* 257:    */   
/* 258:    */   public void setRecipes(MerchantRecipeList merchantRecipeList) {}
/* 259:    */   
/* 260:    */   public void useRecipe(MerchantRecipe par1MerchantRecipe)
/* 261:    */   {
/* 262:374 */     par1MerchantRecipe.incrementToolUses();
/* 263:375 */     this.livingSoundTime = (-getTalkInterval());
/* 264:378 */     if ((!par1MerchantRecipe.hasSameIDsAs((MerchantRecipe)this.buyingList.get(this.buyingList.size() - 1))) || 
/* 265:    */     
/* 266:    */ 
/* 267:    */ 
/* 268:    */ 
/* 269:    */ 
/* 270:    */ 
/* 271:    */ 
/* 272:    */ 
/* 273:    */ 
/* 274:    */ 
/* 275:    */ 
/* 276:    */ 
/* 277:    */ 
/* 278:    */ 
/* 279:393 */       (par1MerchantRecipe.getItemToBuy().getItem() == Items.emerald)) {}
/* 280:    */   }
/* 281:    */   
/* 282:    */   public void func_110297_a_(ItemStack par1ItemStack)
/* 283:    */   {
/* 284:401 */     if ((!this.worldObj.isRemote) && (this.livingSoundTime > -getTalkInterval() + 20)) {
/* 285:403 */       this.livingSoundTime = (-getTalkInterval());
/* 286:    */     }
/* 287:    */   }
/* 288:    */   
/* 289:    */   private void addDefaultEquipmentAndRecipies(int par1)
/* 290:    */   {
/* 291:422 */     if (this.buyingList != null) {
/* 292:424 */       this.field_82191_bN = (MathHelper.sqrt_float(this.buyingList.size()) * 0.2F);
/* 293:    */     } else {
/* 294:428 */       this.field_82191_bN = 0.0F;
/* 295:    */     }
/* 296:432 */     MerchantRecipeList merchantrecipelist = new MerchantRecipeList();
/* 297:    */     
/* 298:    */ 
/* 299:    */ 
/* 300:    */ 
/* 301:437 */     func_146091_a(merchantrecipelist, Items.wheat, this.rand, adjustProbability(0.9F));
/* 302:438 */     func_146091_a(merchantrecipelist, Item.getItemFromBlock(Blocks.wool), this.rand, adjustProbability(0.5F));
/* 303:    */     
/* 304:    */ 
/* 305:    */ 
/* 306:    */ 
/* 307:    */ 
/* 308:    */ 
/* 309:445 */     Collections.shuffle(merchantrecipelist);
/* 310:447 */     if (this.buyingList == null) {
/* 311:449 */       this.buyingList = new MerchantRecipeList();
/* 312:    */     }
/* 313:452 */     for (int l = 0; (l < par1) && (l < merchantrecipelist.size()); l++) {
/* 314:454 */       this.buyingList.addToListWithCheck((MerchantRecipe)merchantrecipelist.get(l));
/* 315:    */     }
/* 316:    */   }
/* 317:    */   
/* 318:    */   public static void func_146091_a(MerchantRecipeList p_146091_0_, Item p_146091_1_, Random p_146091_2_, float p_146091_3_)
/* 319:    */   {
/* 320:460 */     if (p_146091_2_.nextFloat() < p_146091_3_) {
/* 321:462 */       p_146091_0_.add(new MerchantRecipe(func_146088_a(p_146091_1_, p_146091_2_), Items.emerald));
/* 322:    */     }
/* 323:    */   }
/* 324:    */   
/* 325:    */   private static ItemStack func_146088_a(Item p_146088_0_, Random p_146088_1_)
/* 326:    */   {
/* 327:468 */     return new ItemStack(p_146088_0_, func_146092_b(p_146088_0_, p_146088_1_), 0);
/* 328:    */   }
/* 329:    */   
/* 330:    */   private static int func_146092_b(Item p_146092_0_, Random p_146092_1_)
/* 331:    */   {
/* 332:473 */     Tuple tuple = (Tuple)villagersSellingList.get(p_146092_0_);
/* 333:474 */     return ((Integer)tuple.getFirst()).intValue() >= ((Integer)tuple.getSecond()).intValue() ? ((Integer)tuple.getFirst()).intValue() : tuple == null ? 1 : ((Integer)tuple.getFirst()).intValue() + p_146092_1_.nextInt(((Integer)tuple.getSecond()).intValue() - ((Integer)tuple.getFirst()).intValue());
/* 334:    */   }
/* 335:    */   
/* 336:    */   private float adjustProbability(float par1)
/* 337:    */   {
/* 338:482 */     float f1 = par1 + this.field_82191_bN;
/* 339:483 */     return f1 > 0.9F ? 0.9F - (f1 - 0.9F) : f1;
/* 340:    */   }
/* 341:    */   
/* 342:    */   static
/* 343:    */   {
/* 344:488 */     villagersSellingList.put(Items.coal, new Tuple(Integer.valueOf(16), Integer.valueOf(24)));
/* 345:489 */     villagersSellingList.put(Items.iron_ingot, new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
/* 346:490 */     villagersSellingList.put(Items.gold_ingot, new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
/* 347:491 */     villagersSellingList.put(Items.diamond, new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
/* 348:492 */     villagersSellingList.put(Items.paper, new Tuple(Integer.valueOf(24), Integer.valueOf(36)));
/* 349:493 */     villagersSellingList.put(Items.book, new Tuple(Integer.valueOf(11), Integer.valueOf(13)));
/* 350:    */   }
/* 351:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.living.EntitySakuya
 * JD-Core Version:    0.7.0.1
 */