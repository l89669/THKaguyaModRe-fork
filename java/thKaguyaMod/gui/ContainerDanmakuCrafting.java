/*   1:    */ package thKaguyaMod.gui;
/*   2:    */ 
/*   3:    */ import java.util.List;
/*   4:    */ import net.minecraft.entity.player.EntityPlayer;
/*   5:    */ import net.minecraft.entity.player.InventoryPlayer;
/*   6:    */ import net.minecraft.init.Items;
/*   7:    */ import net.minecraft.inventory.Container;
/*   8:    */ import net.minecraft.inventory.IInventory;
/*   9:    */ import net.minecraft.inventory.InventoryCraftResult;
/*  10:    */ import net.minecraft.inventory.InventoryCrafting;
/*  11:    */ import net.minecraft.inventory.Slot;
/*  12:    */ import net.minecraft.item.ItemStack;
/*  13:    */ import net.minecraft.nbt.NBTTagCompound;
/*  14:    */ import net.minecraft.world.World;
/*  15:    */ import thKaguyaMod.init.THKaguyaBlocks;
/*  16:    */ import thKaguyaMod.init.THKaguyaConfig;
/*  17:    */ import thKaguyaMod.init.THKaguyaItems;
/*  18:    */ 
/*  19:    */ public class ContainerDanmakuCrafting
/*  20:    */   extends Container
/*  21:    */ {
/*  22: 23 */   public IInventory craftMatrix = new InventoryCrafting(this, 3, 3);
/*  23: 24 */   public IInventory craftMaterial = new InventoryCrafting(this, 1, 1);
/*  24: 25 */   public IInventory craftSpeed = new InventoryCrafting(this, 1, 1);
/*  25: 26 */   public IInventory craftWay = new InventoryCrafting(this, 1, 1);
/*  26: 27 */   public IInventory craftCopy = new InventoryCrafting(this, 1, 1);
/*  27: 28 */   public IInventory craftColor = new InventoryCrafting(this, 1, 1);
/*  28: 29 */   public IInventory craftBound = new InventoryCrafting(this, 1, 1);
/*  29: 30 */   public IInventory craftGravity = new InventoryCrafting(this, 1, 1);
/*  30: 31 */   public IInventory craftSpecial = new InventoryCrafting(this, 1, 1);
/*  31: 32 */   public IInventory craftResult = new InventoryCraftResult();
/*  32:    */   private World world;
/*  33:    */   private int xCoord;
/*  34:    */   private int yCoord;
/*  35:    */   private int zCoord;
/*  36: 38 */   ItemStack newShot = null;
/*  37:    */   
/*  38:    */   public ContainerDanmakuCrafting(InventoryPlayer inventoryPlayer, World world, int x, int y, int z)
/*  39:    */   {
/*  40: 43 */     this.world = world;
/*  41: 44 */     this.xCoord = x;
/*  42: 45 */     this.yCoord = y;
/*  43: 46 */     this.zCoord = z;
/*  44: 47 */     addSlotToContainer(new SlotDanmakuResult(inventoryPlayer.player, this.craftMaterial, this.craftMatrix, this.craftWay, this.craftSpeed, this.craftCopy, this.craftGravity, this.craftBound, this.craftResult, 0, 228, 35));
/*  45: 48 */     addSlotToContainer(new SlotDanmakuCraftingA(this.craftMaterial, 0, 8, 17, 2));
/*  46: 49 */     addSlotToContainer(new SlotDanmakuCraftingA(this.craftWay, 0, 8, 35, 0));
/*  47: 50 */     addSlotToContainer(new SlotDanmakuCraftingA(this.craftSpeed, 0, 8, 53, 0));
/*  48: 51 */     addSlotToContainer(new SlotDanmakuCraftingA(this.craftCopy, 0, 192, 17, 1));
/*  49: 52 */     addSlotToContainer(new SlotDanmakuCraftingA(this.craftColor, 0, 69, 17, 3));
/*  50: 53 */     addSlotToContainer(new SlotDanmakuCraftingA(this.craftGravity, 0, 69, 35, 0));
/*  51: 54 */     addSlotToContainer(new SlotDanmakuCraftingA(this.craftBound, 0, 69, 53, 4));
/*  52: 59 */     for (int l = 0; l < 3; l++) {
/*  53: 61 */       for (int i1 = 0; i1 < 3; i1++) {
/*  54: 63 */         addSlotToContainer(new SlotDanmakuCraftingA(this.craftMatrix, i1 + l * 3, 134 + i1 * 18, 17 + l * 18, 0));
/*  55:    */       }
/*  56:    */     }
/*  57: 67 */     for (int l = 0; l < 3; l++) {
/*  58: 69 */       for (int i1 = 0; i1 < 9; i1++) {
/*  59: 71 */         addSlotToContainer(new Slot(inventoryPlayer, i1 + l * 9 + 9, 48 + i1 * 18, 84 + l * 18));
/*  60:    */       }
/*  61:    */     }
/*  62: 75 */     for (int l = 0; l < 9; l++) {
/*  63: 77 */       addSlotToContainer(new Slot(inventoryPlayer, l, 48 + l * 18, 142));
/*  64:    */     }
/*  65: 80 */     onCraftMatrixChanged(this.craftMatrix);
/*  66:    */   }
/*  67:    */   
/*  68:    */   public boolean canInteractWith(EntityPlayer entityPlayer)
/*  69:    */   {
/*  70: 90 */     return this.world.getBlock(this.xCoord, this.yCoord, this.zCoord) == THKaguyaBlocks.danmaku_crafting_table;
/*  71:    */   }
/*  72:    */   
/*  73:    */   public void onCraftMatrixChanged(IInventory par1IInventory)
/*  74:    */   {
/*  75: 99 */     ItemStack shot = this.craftMaterial.getStackInSlot(0);
/*  76:    */     
/*  77:101 */     ItemStack power = new ItemStack(THKaguyaItems.power_item);
/*  78:    */     
/*  79:103 */     short way = 1;
/*  80:104 */     byte form = 0;
/*  81:105 */     byte addSpeed = 0;
/*  82:106 */     byte color = 8;
/*  83:107 */     byte gravity = 0;
/*  84:108 */     int special = 0;
/*  85:111 */     if ((shot != null) && (shot.getTagCompound() != null))
/*  86:    */     {
/*  87:114 */       NBTTagCompound nbt = shot.getTagCompound();
/*  88:    */       
/*  89:116 */       way = nbt.getShort("Number");
/*  90:117 */       form = nbt.getByte("DanmakuForm");
/*  91:118 */       addSpeed = nbt.getByte("Speed");
/*  92:119 */       special = nbt.getInteger("Special");
/*  93:120 */       gravity = nbt.getByte("Gravity");
/*  94:121 */       color = nbt.getByte("Color");
/*  95:    */     }
/*  96:125 */     if (this.craftSpeed.getStackInSlot(0) != null)
/*  97:    */     {
/*  98:128 */       addSpeed = (byte)(addSpeed + this.craftSpeed.getStackInSlot(0).stackSize);
/*  99:130 */       if ((addSpeed > 64) || (addSpeed < 0)) {
/* 100:132 */         addSpeed = 64;
/* 101:    */       }
/* 102:    */     }
/* 103:137 */     if (this.craftWay.getStackInSlot(0) != null)
/* 104:    */     {
/* 105:140 */       way = (short)(way + this.craftWay.getStackInSlot(0).stackSize);
/* 106:142 */       if ((way > THKaguyaConfig.shotMaxNumber) || (way < 0)) {
/* 107:144 */         way = (short)THKaguyaConfig.shotMaxNumber;
/* 108:    */       }
/* 109:    */     }
/* 110:149 */     if (this.craftGravity.getStackInSlot(0) != null)
/* 111:    */     {
/* 112:152 */       gravity = (byte)(gravity + this.craftGravity.getStackInSlot(0).stackSize);
/* 113:154 */       if ((gravity > 16) || (gravity < 0)) {
/* 114:156 */         gravity = 16;
/* 115:    */       }
/* 116:    */     }
/* 117:161 */     if (this.craftBound.getStackInSlot(0) != null)
/* 118:    */     {
/* 119:164 */       int num = this.craftBound.getStackInSlot(0).stackSize;
/* 120:165 */       if ((special >= 40) && (special <= 43))
/* 121:    */       {
/* 122:167 */         special = (byte)(special + num);
/* 123:    */       }
/* 124:    */       else
/* 125:    */       {
/* 126:171 */         special = 40;
/* 127:172 */         num--;
/* 128:173 */         special += num;
/* 129:    */       }
/* 130:175 */       if (special > 43) {
/* 131:178 */         special = 43;
/* 132:    */       }
/* 133:    */     }
/* 134:190 */     if ((this.craftMatrix.getStackInSlot(0) == null) && 
/* 135:191 */       (this.craftMatrix.getStackInSlot(1) == null) && 
/* 136:192 */       (this.craftMatrix.getStackInSlot(2) == null) && 
/* 137:193 */       (this.craftMatrix.getStackInSlot(3) == null) && 
/* 138:194 */       (this.craftMatrix.getStackInSlot(4) != null) && 
/* 139:195 */       (this.craftMatrix.getStackInSlot(5) == null) && 
/* 140:196 */       (this.craftMatrix.getStackInSlot(6) == null) && 
/* 141:197 */       (this.craftMatrix.getStackInSlot(7) == null) && 
/* 142:198 */       (this.craftMatrix.getStackInSlot(8) == null)) {
/* 143:200 */       form = 0;
/* 144:204 */     } else if ((this.craftMatrix.getStackInSlot(0) == null) && 
/* 145:205 */       (this.craftMatrix.getStackInSlot(1) != null) && 
/* 146:206 */       (this.craftMatrix.getStackInSlot(2) == null) && 
/* 147:207 */       (this.craftMatrix.getStackInSlot(3) == null) && 
/* 148:208 */       (this.craftMatrix.getStackInSlot(4) != null) && 
/* 149:209 */       (this.craftMatrix.getStackInSlot(5) == null) && 
/* 150:210 */       (this.craftMatrix.getStackInSlot(6) == null) && 
/* 151:211 */       (this.craftMatrix.getStackInSlot(7) == null) && 
/* 152:212 */       (this.craftMatrix.getStackInSlot(8) == null)) {
/* 153:214 */       form = 1;
/* 154:218 */     } else if ((this.craftMatrix.getStackInSlot(0) != null) && 
/* 155:219 */       (this.craftMatrix.getStackInSlot(1) != null) && 
/* 156:220 */       (this.craftMatrix.getStackInSlot(2) != null) && 
/* 157:221 */       (this.craftMatrix.getStackInSlot(3) == null) && 
/* 158:222 */       (this.craftMatrix.getStackInSlot(4) == null) && 
/* 159:223 */       (this.craftMatrix.getStackInSlot(5) == null) && 
/* 160:224 */       (this.craftMatrix.getStackInSlot(6) == null) && 
/* 161:225 */       (this.craftMatrix.getStackInSlot(7) == null) && 
/* 162:226 */       (this.craftMatrix.getStackInSlot(8) == null)) {
/* 163:228 */       form = 2;
/* 164:232 */     } else if ((this.craftMatrix.getStackInSlot(0) != null) && 
/* 165:233 */       (this.craftMatrix.getStackInSlot(1) != null) && 
/* 166:234 */       (this.craftMatrix.getStackInSlot(2) != null) && 
/* 167:235 */       (this.craftMatrix.getStackInSlot(3) != null) && 
/* 168:236 */       (this.craftMatrix.getStackInSlot(4) == null) && 
/* 169:237 */       (this.craftMatrix.getStackInSlot(5) != null) && 
/* 170:238 */       (this.craftMatrix.getStackInSlot(6) != null) && 
/* 171:239 */       (this.craftMatrix.getStackInSlot(7) != null) && 
/* 172:240 */       (this.craftMatrix.getStackInSlot(8) != null)) {
/* 173:242 */       form = 3;
/* 174:246 */     } else if ((this.craftMatrix.getStackInSlot(0) != null) && 
/* 175:247 */       (this.craftMatrix.getStackInSlot(1) != null) && 
/* 176:248 */       (this.craftMatrix.getStackInSlot(2) != null) && 
/* 177:249 */       (this.craftMatrix.getStackInSlot(3) != null) && 
/* 178:250 */       (this.craftMatrix.getStackInSlot(4) != null) && 
/* 179:251 */       (this.craftMatrix.getStackInSlot(5) != null) && 
/* 180:252 */       (this.craftMatrix.getStackInSlot(6) != null) && 
/* 181:253 */       (this.craftMatrix.getStackInSlot(7) != null) && 
/* 182:254 */       (this.craftMatrix.getStackInSlot(8) != null))
/* 183:    */     {
/* 184:256 */       if (way > 2) {
/* 185:258 */         form = 4;
/* 186:    */       }
/* 187:    */     }
/* 188:263 */     else if ((this.craftMatrix.getStackInSlot(0) == null) && 
/* 189:264 */       (this.craftMatrix.getStackInSlot(1) != null) && 
/* 190:265 */       (this.craftMatrix.getStackInSlot(2) == null) && 
/* 191:266 */       (this.craftMatrix.getStackInSlot(3) != null) && 
/* 192:267 */       (this.craftMatrix.getStackInSlot(4) == null) && 
/* 193:268 */       (this.craftMatrix.getStackInSlot(5) != null) && 
/* 194:269 */       (this.craftMatrix.getStackInSlot(6) == null) && 
/* 195:270 */       (this.craftMatrix.getStackInSlot(7) != null) && 
/* 196:271 */       (this.craftMatrix.getStackInSlot(8) == null))
/* 197:    */     {
/* 198:273 */       if (way > 2) {
/* 199:275 */         form = 5;
/* 200:    */       }
/* 201:    */     }
/* 202:280 */     else if ((this.craftMatrix.getStackInSlot(0) != null) || 
/* 203:281 */       (this.craftMatrix.getStackInSlot(1) != null) || 
/* 204:282 */       (this.craftMatrix.getStackInSlot(2) != null) || 
/* 205:283 */       (this.craftMatrix.getStackInSlot(3) != null) || 
/* 206:284 */       (this.craftMatrix.getStackInSlot(4) != null) || 
/* 207:285 */       (this.craftMatrix.getStackInSlot(5) != null) || 
/* 208:286 */       (this.craftMatrix.getStackInSlot(6) != null) || 
/* 209:287 */       (this.craftMatrix.getStackInSlot(7) != null) || 
/* 210:288 */       (this.craftMatrix.getStackInSlot(8) != null)) {
/* 211:295 */       form = -1;
/* 212:    */     }
/* 213:299 */     if (this.craftColor.getStackInSlot(0) != null) {
/* 214:301 */       if (this.craftColor.getStackInSlot(0).getItem() == Items.dye) {
/* 215:303 */         color = (byte)thKaguyaMod.item.ItemTHShot.sellectbleColor[(this.craftColor.getStackInSlot(0).getItemDamage() % 16)];
/* 216:305 */       } else if (this.craftColor.getStackInSlot(0).getItem() == Items.gunpowder) {
/* 217:308 */         color = 8;
/* 218:310 */       } else if (this.craftColor.getStackInSlot(0).getItem() == Items.diamond) {
/* 219:313 */         color = 9;
/* 220:    */       }
/* 221:    */     }
/* 222:318 */     if ((shot != null) && ((form != 4) || (way <= 32)) && (form >= 0))
/* 223:    */     {
/* 224:321 */       this.newShot = new ItemStack(THKaguyaItems.shot_item);
/* 225:    */       
/* 226:323 */       this.newShot.setItemDamage(this.craftMaterial.getStackInSlot(0).getItemDamage());
/* 227:    */       
/* 228:    */ 
/* 229:326 */       NBTTagCompound nbt2 = this.newShot.getTagCompound();
/* 230:328 */       if (nbt2 == null)
/* 231:    */       {
/* 232:330 */         nbt2 = new NBTTagCompound();
/* 233:331 */         this.newShot.setTagCompound(nbt2);
/* 234:    */       }
/* 235:334 */       nbt2.setShort("Number", way);
/* 236:335 */       nbt2.setByte("DanmakuForm", form);
/* 237:336 */       nbt2.setByte("Speed", addSpeed);
/* 238:337 */       nbt2.setByte("Color", color);
/* 239:338 */       nbt2.setInteger("Special", special);
/* 240:339 */       nbt2.setByte("Gravity", gravity);
/* 241:    */       
/* 242:    */ 
/* 243:    */ 
/* 244:343 */       int copyNum = 1;
/* 245:345 */       if (this.craftCopy.getStackInSlot(0) != null)
/* 246:    */       {
/* 247:348 */         int oriNum = shot.stackSize;
/* 248:    */         
/* 249:350 */         copyNum = this.craftCopy.getStackInSlot(0).stackSize + 1;
/* 250:353 */         if (copyNum > oriNum) {
/* 251:355 */           copyNum = oriNum;
/* 252:    */         }
/* 253:358 */         this.newShot.stackSize = copyNum;
/* 254:    */       }
/* 255:362 */       this.craftResult.setInventorySlotContents(0, this.newShot);
/* 256:    */     }
/* 257:    */     else
/* 258:    */     {
/* 259:366 */       this.craftResult.setInventorySlotContents(0, null);
/* 260:    */     }
/* 261:369 */     detectAndSendChanges();
/* 262:    */   }
/* 263:    */   
/* 264:    */   public void onContainerClosed(EntityPlayer player)
/* 265:    */   {
/* 266:376 */     super.onContainerClosed(player);
/* 267:378 */     if (!this.world.isRemote)
/* 268:    */     {
/* 269:381 */       for (int i = 0; i < 9; i++)
/* 270:    */       {
/* 271:384 */         ItemStack itemStack = this.craftMatrix.getStackInSlotOnClosing(i);
/* 272:386 */         if (itemStack != null) {
/* 273:388 */           player.dropPlayerItemWithRandomChoice(itemStack, false);
/* 274:    */         }
/* 275:    */       }
/* 276:391 */       ItemStack itemStack = this.craftWay.getStackInSlotOnClosing(0);
/* 277:392 */       if (itemStack != null) {
/* 278:394 */         player.dropPlayerItemWithRandomChoice(itemStack, false);
/* 279:    */       }
/* 280:396 */       itemStack = this.craftSpeed.getStackInSlotOnClosing(0);
/* 281:397 */       if (itemStack != null) {
/* 282:399 */         player.dropPlayerItemWithRandomChoice(itemStack, false);
/* 283:    */       }
/* 284:401 */       itemStack = this.craftCopy.getStackInSlotOnClosing(0);
/* 285:402 */       if (itemStack != null) {
/* 286:404 */         player.dropPlayerItemWithRandomChoice(itemStack, false);
/* 287:    */       }
/* 288:406 */       itemStack = this.craftColor.getStackInSlotOnClosing(0);
/* 289:407 */       if (itemStack != null) {
/* 290:409 */         player.dropPlayerItemWithRandomChoice(itemStack, false);
/* 291:    */       }
/* 292:411 */       itemStack = this.craftGravity.getStackInSlotOnClosing(0);
/* 293:412 */       if (itemStack != null) {
/* 294:414 */         player.dropPlayerItemWithRandomChoice(itemStack, false);
/* 295:    */       }
/* 296:416 */       itemStack = this.craftBound.getStackInSlotOnClosing(0);
/* 297:417 */       if (itemStack != null) {
/* 298:419 */         player.dropPlayerItemWithRandomChoice(itemStack, false);
/* 299:    */       }
/* 300:421 */       itemStack = this.craftMaterial.getStackInSlotOnClosing(0);
/* 301:422 */       if (itemStack != null) {
/* 302:424 */         player.dropPlayerItemWithRandomChoice(itemStack, false);
/* 303:    */       }
/* 304:    */     }
/* 305:    */   }
/* 306:    */   
/* 307:    */   public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
/* 308:    */   {
/* 309:434 */     ItemStack itemstack = null;
/* 310:435 */     Slot slot = (Slot)this.inventorySlots.get(par2);
/* 311:437 */     if ((slot != null) && (slot.getHasStack()))
/* 312:    */     {
/* 313:439 */       ItemStack itemstack1 = slot.getStack();
/* 314:440 */       itemstack = itemstack1.copy();
/* 315:442 */       if (par2 == 0)
/* 316:    */       {
/* 317:444 */         if (!mergeItemStack(itemstack1, 10, 46, true)) {
/* 318:446 */           return null;
/* 319:    */         }
/* 320:449 */         slot.onSlotChange(itemstack1, itemstack);
/* 321:    */       }
/* 322:451 */       else if ((par2 >= 10) && (par2 < 37))
/* 323:    */       {
/* 324:453 */         if (!mergeItemStack(itemstack1, 37, 46, false)) {
/* 325:455 */           return null;
/* 326:    */         }
/* 327:    */       }
/* 328:458 */       else if ((par2 >= 37) && (par2 < 46))
/* 329:    */       {
/* 330:460 */         if (!mergeItemStack(itemstack1, 10, 37, false)) {
/* 331:462 */           return null;
/* 332:    */         }
/* 333:    */       }
/* 334:465 */       else if (!mergeItemStack(itemstack1, 10, 46, false))
/* 335:    */       {
/* 336:467 */         return null;
/* 337:    */       }
/* 338:470 */       if (itemstack1.stackSize == 0) {
/* 339:472 */         slot.putStack((ItemStack)null);
/* 340:    */       } else {
/* 341:476 */         slot.onSlotChanged();
/* 342:    */       }
/* 343:479 */       if (itemstack1.stackSize == itemstack.stackSize) {
/* 344:481 */         return null;
/* 345:    */       }
/* 346:485 */       slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
/* 347:    */     }
/* 348:488 */     return itemstack;
/* 349:    */   }
/* 350:    */   
/* 351:    */   public ItemStack slotClick(int par1, int par2, int par3, EntityPlayer par4EntityPlayer)
/* 352:    */   {
/* 353:513 */     super.slotClick(par1, par2, par3, par4EntityPlayer);
/* 354:    */     
/* 355:    */ 
/* 356:516 */     onCraftMatrixChanged(null);
/* 357:    */     
/* 358:    */ 
/* 359:519 */     return this.newShot;
/* 360:    */   }
/* 361:    */   
/* 362:    */   public boolean func_94530_a(ItemStack par1ItemStack, Slot par2Slot)
/* 363:    */   {
/* 364:524 */     return (par2Slot.inventory != this.craftResult) && (super.func_94530_a(par1ItemStack, par2Slot));
/* 365:    */   }
/* 366:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.gui.ContainerDanmakuCrafting
 * JD-Core Version:    0.7.0.1
 */