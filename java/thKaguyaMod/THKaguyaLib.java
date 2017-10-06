/*   1:    */ package thKaguyaMod;
/*   2:    */ 
/*   3:    */ import java.util.List;
/*   4:    */ import net.minecraft.entity.Entity;
/*   5:    */ import net.minecraft.entity.EntityLivingBase;
/*   6:    */ import net.minecraft.entity.item.EntityItem;
/*   7:    */ import net.minecraft.entity.passive.EntityAnimal;
/*   8:    */ import net.minecraft.entity.passive.EntityVillager;
/*   9:    */ import net.minecraft.entity.player.EntityPlayer;
/*  10:    */ import net.minecraft.entity.player.InventoryPlayer;
/*  11:    */ import net.minecraft.entity.player.PlayerCapabilities;
/*  12:    */ import net.minecraft.item.Item;
/*  13:    */ import net.minecraft.item.ItemStack;
/*  14:    */ import net.minecraft.nbt.NBTTagCompound;
/*  15:    */ import net.minecraft.util.AxisAlignedBB;
/*  16:    */ import net.minecraft.util.ChatComponentText;
/*  17:    */ import net.minecraft.util.MovingObjectPosition;
/*  18:    */ import net.minecraft.util.StatCollector;
/*  19:    */ import net.minecraft.util.Vec3;
/*  20:    */ import net.minecraft.world.World;
/*  21:    */ import thKaguyaMod.entity.item.EntitySilverKnife;
/*  22:    */ import thKaguyaMod.entity.spellcard.EntitySpellCard;
/*  23:    */ import thKaguyaMod.entity.spellcard.THSpellCard;
/*  24:    */ import thKaguyaMod.init.THKaguyaConfig;
/*  25:    */ import thKaguyaMod.init.THKaguyaItems;
/*  26:    */ import thKaguyaMod.registry.SpellCardRegistry;
/*  27:    */ 
/*  28:    */ public class THKaguyaLib
/*  29:    */ {
/*  30:    */   public static void itemEffectFollowUser(Entity itemEntity, Entity userEntity, double lengthToUser, float positionAngle, boolean yForrow, double yOffset)
/*  31:    */   {
/*  32: 46 */     if (userEntity != null)
/*  33:    */     {
/*  34:    */       double posY;
/*  35:    */       double posX;
/*  36:    */       double posZ;
/*  38: 50 */       if (yForrow)
/*  39:    */       {
/*  40: 52 */        posX = userEntity.posX - Math.sin((userEntity.rotationYaw + positionAngle) / 180.0F * 3.141593F) * Math.cos(userEntity.rotationPitch / 180.0F * 3.141593F) * lengthToUser;
/*  41: 53 */        posZ = userEntity.posZ + Math.cos((userEntity.rotationYaw + positionAngle) / 180.0F * 3.141593F) * Math.cos(userEntity.rotationPitch / 180.0F * 3.141593F) * lengthToUser;
/*  42: 54 */         posY = userEntity.posY - Math.sin(userEntity.rotationPitch / 180.0F * 3.141593F) * lengthToUser + userEntity.getEyeHeight() - 0.5D;
/*  43:    */       }
/*  44:    */       else
/*  45:    */       {
/*  46: 58 */         posX = userEntity.posX - Math.sin((userEntity.rotationYaw + positionAngle) / 180.0F * 3.141593F) * lengthToUser;
/*  47: 59 */         posZ = userEntity.posZ + Math.cos((userEntity.rotationYaw + positionAngle) / 180.0F * 3.141593F) * lengthToUser;
/*  48: 60 */         posY = userEntity.posY + yOffset;
/*  49:    */       }
/*  50: 62 */       userEntity.motionX += posX - itemEntity.posX;
/*  51: 63 */       userEntity.motionY += posY - itemEntity.posY;
/*  52: 64 */       userEntity.motionZ += posZ - itemEntity.posZ;
/*  53: 65 */       itemEntity.setPosition(posX, posY, posZ);
/*  54:    */     }
/*  55:    */   }
/*  56:    */   
/*  57:    */   public static void itemEffectFollowUser(Entity itemEntity, Entity userEntity, double lengthToUser, float positionAngle)
/*  58:    */   {
/*  59: 72 */     itemEffectFollowUser(itemEntity, userEntity, lengthToUser, positionAngle, true, 0.0D);
/*  60:    */   }
/*  61:    */   
/*  62:    */   public static boolean itemEffectFinish(Entity itemEntity, EntityLivingBase userEntity, Item item, int damage)
/*  63:    */   {
/*  64: 86 */     if (userEntity != null)
/*  65:    */     {
/*  66: 88 */       if ((userEntity instanceof EntityPlayer))
/*  67:    */       {
/*  68: 90 */         EntityPlayer userEntity_p = (EntityPlayer)userEntity;
/*  69: 91 */         if (userEntity_p.capabilities.isCreativeMode)
/*  70:    */         {
/*  71: 93 */           if (!itemEntity.worldObj.isRemote) {
/*  72: 95 */             itemEntity.setDead();
/*  73:    */           }
/*  74: 97 */           return true;
/*  75:    */         }
/*  76:100 */         if (!userEntity_p.inventory.addItemStackToInventory(new ItemStack(item, 1, damage)))
/*  77:    */         {
/*  78:104 */           if (!itemEntity.worldObj.isRemote)
/*  79:    */           {
/*  80:106 */             itemEntity.worldObj.spawnEntityInWorld(new EntityItem(itemEntity.worldObj, itemEntity.posX, itemEntity.posY, itemEntity.posZ, new ItemStack(item, 1, damage)));
/*  81:107 */             itemEntity.setDead();
/*  82:    */           }
/*  83:109 */           return true;
/*  84:    */         }
/*  85:    */       }
/*  86:112 */       if (userEntity.isDead) {
/*  87:114 */         if (!itemEntity.worldObj.isRemote) {
/*  88:117 */           itemEntity.worldObj.spawnEntityInWorld(new EntityItem(itemEntity.worldObj, itemEntity.posX, itemEntity.posY, itemEntity.posZ, new ItemStack(item, 1, damage)));
/*  89:    */         }
/*  90:    */       }
/*  91:    */     }
/*  92:124 */     else if (!itemEntity.worldObj.isRemote)
/*  93:    */     {
/*  94:126 */       itemEntity.worldObj.spawnEntityInWorld(new EntityItem(itemEntity.worldObj, itemEntity.posX, itemEntity.posY, itemEntity.posZ, new ItemStack(item, 1, damage)));
/*  95:    */     }
/*  96:129 */     if (!itemEntity.worldObj.isRemote) {
/*  97:131 */       itemEntity.setDead();
/*  98:    */     }
/*  99:133 */     return true;
/* 100:    */   }
/* 101:    */   
/* 102:    */   public static boolean itemEffectFinish(Entity itemEntity, EntityLivingBase userEntity, Item item)
/* 103:    */   {
/* 104:139 */     return itemEffectFinish(itemEntity, userEntity, item, 0);
/* 105:    */   }
/* 106:    */   
/* 107:    */   public static boolean checkSpellCardDeclaration(World world, ItemStack itemStack, EntityLivingBase user, int spellCardNumber, int level, boolean firstAttack)
/* 108:    */   {
/* 109:157 */     int needLevel = 0;
/* 110:160 */     if ((user instanceof EntityPlayer))
/* 111:    */     {
/* 112:162 */       EntityPlayer player = (EntityPlayer)user;
/* 113:165 */       if (!player.capabilities.isCreativeMode)
/* 114:    */       {
/* 115:168 */         if ((itemStack.getItem() == THKaguyaItems.spell_card) && (itemStack.getItemDamage() >= 14) && (itemStack.getItemDamage() <= 18))
/* 116:    */         {
/* 117:170 */           NBTTagCompound nbt = itemStack.getTagCompound();
/* 118:173 */           if (nbt != null)
/* 119:    */           {
/* 120:176 */             if (nbt.getInteger("Charge") <= 0) {
/* 121:179 */               return false;
/* 122:    */             }
/* 123:    */           }
/* 124:    */           else {
/* 125:185 */             return false;
/* 126:    */           }
/* 127:    */         }
/* 128:    */         Class<?> spellcard;
/* 129:193 */         if ((spellcard = SpellCardRegistry.getSpellCardClass(spellCardNumber)) != null) {
/* 130:    */           try
/* 131:    */           {
/* 132:196 */             THSpellCard useSpellCard = (THSpellCard)spellcard.newInstance();
/* 133:197 */             needLevel = useSpellCard.getNeedLevel();
/* 134:    */           }
/* 135:    */           catch (InstantiationException e)
/* 136:    */           {
/* 137:199 */             e.printStackTrace();
/* 138:    */           }
/* 139:    */           catch (IllegalAccessException e)
/* 140:    */           {
/* 141:201 */             e.printStackTrace();
/* 142:    */           }
/* 143:    */         }
/* 144:207 */         int bombItemNum = 0;
/* 145:210 */         for (int i = 0; i < player.inventory.mainInventory.length; i++) {
/* 146:212 */           if ((player.inventory.mainInventory[i] != null) && 
/* 147:213 */             (player.inventory.mainInventory[i].getItem() == THKaguyaItems.bomb_item)) {
/* 148:216 */             bombItemNum += player.inventory.mainInventory[i].stackSize;
/* 149:    */           }
/* 150:    */         }
/* 151:221 */         if (player.experienceLevel + bombItemNum < needLevel) {
/* 152:223 */           return false;
/* 153:    */         }
/* 154:    */       }
/* 155:    */     }
/* 156:230 */     double angleX = -Math.sin(radTrans(user.rotationYaw)) * Math.cos(radTrans(user.rotationPitch));
/* 157:231 */     double angleY = -Math.sin(radTrans(user.rotationPitch));
/* 158:232 */     double angleZ = Math.cos(radTrans(user.rotationYaw)) * Math.cos(radTrans(user.rotationPitch));
/* 159:    */     
/* 160:234 */     Vec3 vec3d = Vec3.createVectorHelper(user.posX, user.posY + user.getEyeHeight(), user.posZ);
/* 161:    */     
/* 162:236 */     Vec3 vec3d1 = Vec3.createVectorHelper(user.posX + angleX * 32.0D, user.posY + angleY * 32.0D, user.posZ + angleZ * 32.0D);
/* 163:    */     
/* 164:    */ 
/* 165:239 */     MovingObjectPosition movingObjectPosition = world.func_147447_a(vec3d, vec3d1, false, true, true);
/* 166:    */     
/* 167:241 */     vec3d = Vec3.createVectorHelper(user.posX, user.posY + user.getEyeHeight(), user.posZ);
/* 168:    */     
/* 169:243 */     vec3d1 = Vec3.createVectorHelper(user.posX + angleX * 32.0D, user.posY + angleY * 32.0D, user.posZ + angleZ * 32.0D);
/* 170:245 */     if (movingObjectPosition != null) {
/* 171:248 */       vec3d1 = Vec3.createVectorHelper(movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord);
/* 172:    */     }
/* 173:250 */     Entity entity = null;
/* 174:    */     
/* 175:    */ 
/* 176:253 */     List list = world.getEntitiesWithinAABBExcludingEntity(user, user.boundingBox.addCoord(angleX * 32.0D, angleY * 32.0D, angleZ * 32.0D).expand(1.0D, 1.0D, 1.0D));
/* 177:254 */     double nearDistance = 0.0D;
/* 178:255 */     for (int j = 0; j < list.size(); j++)
/* 179:    */     {
/* 180:258 */       Entity entity1 = (Entity)list.get(j);
/* 181:260 */       if (((entity1 instanceof EntityLivingBase)) && (!(entity1 instanceof EntityAnimal)) && (!(entity1 instanceof EntityVillager)))
/* 182:    */       {
/* 183:262 */         float expand = 0.3F;
/* 184:263 */         AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(expand, expand, expand);
/* 185:264 */         MovingObjectPosition movingObjectPosition1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);
/* 186:266 */         if (movingObjectPosition1 != null)
/* 187:    */         {
/* 188:269 */           double distanceToHitEntity = vec3d.distanceTo(movingObjectPosition1.hitVec);
/* 189:271 */           if ((distanceToHitEntity < nearDistance) || (nearDistance == 0.0D))
/* 190:    */           {
/* 191:273 */             entity = entity1;
/* 192:274 */             nearDistance = distanceToHitEntity;
/* 193:    */           }
/* 194:    */         }
/* 195:    */       }
/* 196:    */     }
/* 197:280 */     List list2 = world.getEntitiesWithinAABBExcludingEntity(user, user.boundingBox.addCoord(0.0D, 0.0D, 0.0D).expand(64.0D, 64.0D, 64.0D));
/* 198:281 */     for (int k = 0; k < list2.size(); k++)
/* 199:    */     {
/* 200:283 */       Entity entity2 = (Entity)list2.get(k);
/* 201:284 */       if ((entity2 instanceof EntitySpellCard))
/* 202:    */       {
/* 203:286 */         EntitySpellCard entitySpellCard2 = (EntitySpellCard)entity2;
/* 204:287 */         if (entitySpellCard2.user == user)
/* 205:    */         {
/* 206:289 */           entity = null;
/* 207:290 */           return false;
/* 208:    */         }
/* 209:    */       }
/* 210:    */     }
/* 211:296 */     if (entity != null) {
/* 212:298 */       movingObjectPosition = new MovingObjectPosition(entity);
/* 213:    */     } else {
/* 214:302 */       return false;
/* 215:    */     }
/* 216:305 */     if ((movingObjectPosition != null) && (movingObjectPosition.entityHit != null) && (itemProssessionCheck(world, user, spellCardNumber))) {
/* 217:307 */       if ((movingObjectPosition.entityHit instanceof EntityLivingBase))
/* 218:    */       {
/* 219:309 */         if (!world.isRemote)
/* 220:    */         {
/* 221:311 */           spellCardDeclaration(world, user, (EntityLivingBase)movingObjectPosition.entityHit, spellCardNumber, needLevel, level, firstAttack);
/* 222:312 */           return true;
/* 223:    */         }
/* 224:316 */         return false;
/* 225:    */       }
/* 226:    */     }
/* 227:320 */     return false;
/* 228:    */   }
/* 229:    */   
/* 230:    */   public static void spellCardDeclaration(World world, EntityLivingBase user, EntityLivingBase target, int spellCardNumber, int needLevel, int level, boolean firstAttack)
/* 231:    */   {
/* 232:335 */     EntitySpellCard entitySpellCard = new EntitySpellCard(world, user, target, spellCardNumber, level);
/* 233:    */     
/* 234:337 */     String spellCardName = StatCollector.translateToLocal("item.thSpellCard." + spellCardNumber + ".name");
/* 235:339 */     if (!world.isRemote) {
/* 236:341 */       world.spawnEntityInWorld(entitySpellCard);
/* 237:    */     }
/* 238:345 */     if ((user instanceof EntityPlayer))
/* 239:    */     {
/* 240:347 */       EntityPlayer entityPlayer_user = (EntityPlayer)user;
/* 241:348 */       int bomb = 0;
/* 242:351 */       if (!world.isRemote)
/* 243:    */       {
/* 244:354 */         entityPlayer_user.addChatMessage(new ChatComponentText(spellCardName));
/* 245:    */         
/* 246:356 */         world.playSoundAtEntity(entityPlayer_user, "thkaguyamod:spellcard", THKaguyaConfig.SpellCardVol, 1.0F);
/* 247:    */       }
/* 248:361 */       while ((bomb < needLevel) && (entityPlayer_user.inventory.hasItem(THKaguyaItems.bomb_item)))
/* 249:    */       {
/* 250:364 */         entityPlayer_user.inventory.consumeInventoryItem(THKaguyaItems.bomb_item);
/* 251:365 */         bomb++;
/* 252:    */       }
/* 253:369 */       entityPlayer_user.addExperienceLevel(bomb - needLevel);
/* 254:    */     }
/* 255:373 */     if ((target instanceof EntityPlayer)) {
/* 256:376 */       if (firstAttack)
/* 257:    */       {
/* 258:378 */         EntityPlayer entityPlayer_target = (EntityPlayer)target;
/* 259:380 */         if (!world.isRemote)
/* 260:    */         {
/* 261:383 */           entityPlayer_target.addChatMessage(new ChatComponentText(spellCardName));
/* 262:    */           
/* 263:385 */           world.playSoundAtEntity(entityPlayer_target, "thkaguyamod:spellcard", THKaguyaConfig.SpellCardVol, 1.0F);
/* 264:    */         }
/* 265:    */       }
/* 266:    */     }
/* 267:391 */     if (firstAttack) {
/* 268:393 */       THShotLib.danmakuRemove(user, 40.0D, "Enemy", true);
/* 269:    */     }
/* 270:    */   }
/* 271:    */   
/* 272:    */   private static boolean itemProssessionCheck(World world, EntityLivingBase EntityLivingBase, int nomber)
/* 273:    */   {
/* 274:399 */     return true;
/* 275:    */   }
/* 276:    */   
/* 277:    */   public static float getPlayerPower(EntityPlayer player)
/* 278:    */   {
/* 279:409 */     float power = 0.0F;
/* 280:412 */     for (int i = 0; i < player.inventory.mainInventory.length; i++) {
/* 281:415 */       if ((player.inventory.mainInventory[i] != null) && 
/* 282:416 */         (player.inventory.mainInventory[i].getItem() == THKaguyaItems.power_item)) {
/* 283:419 */         if (player.inventory.mainInventory[i].getItemDamage() == 0) {
/* 284:421 */           power += 1.0F * player.inventory.mainInventory[i].stackSize;
/* 285:424 */         } else if (player.inventory.mainInventory[i].getItemDamage() == 1) {
/* 286:426 */           power += 10.0F * player.inventory.mainInventory[i].stackSize;
/* 287:    */         }
/* 288:    */       }
/* 289:    */     }
/* 290:431 */     if (power > 400.0F) {
/* 291:433 */       power = 400.0F;
/* 292:    */     }
/* 293:436 */     return power;
/* 294:    */   }
/* 295:    */   
/* 296:    */   public static EntitySilverKnife createSilverKnife(EntityLivingBase user, Vec3 pos, Vec3 angle, double speed, int color, int startCount)
/* 297:    */   {
/* 298:454 */     if (user != null)
/* 299:    */     {
/* 300:456 */       EntitySilverKnife silverKnife = new EntitySilverKnife(user.worldObj, user, pos, angle, speed, 3);
/* 301:457 */       silverKnife.setKnifeCount(startCount);
/* 302:459 */       if (!user.worldObj.isRemote)
/* 303:    */       {
/* 304:461 */         user.worldObj.spawnEntityInWorld(silverKnife);
/* 305:462 */         return silverKnife;
/* 306:    */       }
/* 307:    */     }
/* 308:466 */     return null;
/* 309:    */   }
/* 310:    */   
/* 311:    */   public static float radTrans(float deg)
/* 312:    */   {
/* 313:512 */     return deg / 180.0F * 3.141593F;
/* 314:    */   }
/* 315:    */   
/* 316:    */   public static float degTrans(float rad)
/* 317:    */   {
/* 318:517 */     return rad / 3.141593F * 180.0F;
/* 319:    */   }
/* 320:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.THKaguyaLib
 * JD-Core Version:    0.7.0.1
 */