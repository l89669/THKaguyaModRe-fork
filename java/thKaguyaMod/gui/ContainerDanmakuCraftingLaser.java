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
/*  19:    */ public class ContainerDanmakuCraftingLaser
/*  20:    */   extends Container
/*  21:    */ {
/*  22: 22 */   public IInventory craftMatrix = new InventoryCrafting(this, 3, 3);
/*  23: 23 */   public IInventory craftMaterial = new InventoryCrafting(this, 1, 1);
/*  24: 24 */   public IInventory craftSpeed = new InventoryCrafting(this, 1, 1);
/*  25: 25 */   public IInventory craftWay = new InventoryCrafting(this, 1, 1);
/*  26: 26 */   public IInventory craftCopy = new InventoryCrafting(this, 1, 1);
/*  27: 27 */   public IInventory craftColor = new InventoryCrafting(this, 1, 1);
/*  28: 31 */   public IInventory craftResult = new InventoryCraftResult();
/*  29:    */   private World world;
/*  30:    */   private int xCoord;
/*  31:    */   private int yCoord;
/*  32:    */   private int zCoord;
/*  33: 37 */   ItemStack newShot = null;
/*  34:    */   
/*  35:    */   public ContainerDanmakuCraftingLaser(InventoryPlayer inventoryPlayer, World world, int x, int y, int z)
/*  36:    */   {
/*  37: 42 */     this.world = world;
/*  38: 43 */     this.xCoord = x;
/*  39: 44 */     this.yCoord = y;
/*  40: 45 */     this.zCoord = z;
/*  41: 46 */     addSlotToContainer(new SlotLaserResult(inventoryPlayer.player, this.craftMaterial, this.craftMatrix, this.craftWay, this.craftSpeed, this.craftCopy, this.craftResult, 0, 228, 35));
/*  42: 47 */     addSlotToContainer(new SlotDanmakuCraftingA(this.craftMaterial, 0, 8, 17, 5));
/*  43: 48 */     addSlotToContainer(new SlotDanmakuCraftingA(this.craftWay, 0, 8, 35, 0));
/*  44: 49 */     addSlotToContainer(new SlotDanmakuCraftingA(this.craftSpeed, 0, 8, 53, 0));
/*  45: 50 */     addSlotToContainer(new SlotDanmakuCraftingA(this.craftCopy, 0, 192, 17, 1));
/*  46: 51 */     addSlotToContainer(new SlotDanmakuCraftingA(this.craftColor, 0, 69, 17, 3));
/*  47: 58 */     for (int l = 0; l < 3; l++) {
/*  48: 60 */       for (int i1 = 0; i1 < 3; i1++) {
/*  49: 62 */         addSlotToContainer(new SlotDanmakuCraftingA(this.craftMatrix, i1 + l * 3, 134 + i1 * 18, 17 + l * 18, 0));
/*  50:    */       }
/*  51:    */     }
/*  52: 66 */     for (int l = 0; l < 3; l++) {
/*  53: 68 */       for (int i1 = 0; i1 < 9; i1++) {
/*  54: 70 */         addSlotToContainer(new Slot(inventoryPlayer, i1 + l * 9 + 9, 48 + i1 * 18, 84 + l * 18));
/*  55:    */       }
/*  56:    */     }
/*  57: 74 */     for (int l = 0; l < 9; l++) {
/*  58: 76 */       addSlotToContainer(new Slot(inventoryPlayer, l, 48 + l * 18, 142));
/*  59:    */     }
/*  60: 79 */     onCraftMatrixChanged(this.craftMatrix);
/*  61:    */   }
/*  62:    */   
/*  63:    */   public boolean canInteractWith(EntityPlayer entityPlayer)
/*  64:    */   {
/*  65: 89 */     return this.world.getBlock(this.xCoord, this.yCoord, this.zCoord) == THKaguyaBlocks.danmaku_crafting_table;
/*  66:    */   }
/*  67:    */   
/*  68:    */   public void onCraftMatrixChanged(IInventory par1IInventory)
/*  69:    */   {
/*  70: 99 */     ItemStack shot = this.craftMaterial.getStackInSlot(0);
/*  71:    */     
/*  72:101 */     short way = 1;
/*  73:102 */     byte form = 0;
/*  74:103 */     byte addSpeed = 0;
/*  75:104 */     byte color = 8;
/*  76:108 */     if ((shot != null) && (shot.getTagCompound() != null))
/*  77:    */     {
/*  78:110 */       NBTTagCompound nbt = shot.getTagCompound();
/*  79:111 */       way = nbt.getShort("Number");
/*  80:112 */       form = nbt.getByte("DanmakuForm");
/*  81:113 */       addSpeed = nbt.getByte("Speed");
/*  82:    */       
/*  83:    */ 
/*  84:116 */       color = nbt.getByte("Color");
/*  85:    */     }
/*  86:120 */     if (this.craftSpeed.getStackInSlot(0) != null)
/*  87:    */     {
/*  88:122 */       addSpeed = (byte)(addSpeed + this.craftSpeed.getStackInSlot(0).stackSize);
/*  89:123 */       if ((addSpeed > 64) || (addSpeed < 0)) {
/*  90:125 */         addSpeed = 64;
/*  91:    */       }
/*  92:    */     }
/*  93:129 */     if (this.craftWay.getStackInSlot(0) != null)
/*  94:    */     {
/*  95:131 */       way = (short)(way + this.craftWay.getStackInSlot(0).stackSize);
/*  96:132 */       if ((way > THKaguyaConfig.laserMaxNumber) || (way < 0)) {
/*  97:134 */         way = (short)THKaguyaConfig.laserMaxNumber;
/*  98:    */       }
/*  99:    */     }
/* 100:170 */     if ((this.craftMatrix.getStackInSlot(0) == null) && 
/* 101:171 */       (this.craftMatrix.getStackInSlot(1) == null) && 
/* 102:172 */       (this.craftMatrix.getStackInSlot(2) == null) && 
/* 103:173 */       (this.craftMatrix.getStackInSlot(3) == null) && 
/* 104:174 */       (this.craftMatrix.getStackInSlot(4) != null) && 
/* 105:175 */       (this.craftMatrix.getStackInSlot(5) == null) && 
/* 106:176 */       (this.craftMatrix.getStackInSlot(6) == null) && 
/* 107:177 */       (this.craftMatrix.getStackInSlot(7) == null) && 
/* 108:178 */       (this.craftMatrix.getStackInSlot(8) == null)) {
/* 109:180 */       form = 0;
/* 110:184 */     } else if ((this.craftMatrix.getStackInSlot(0) == null) && 
/* 111:185 */       (this.craftMatrix.getStackInSlot(1) != null) && 
/* 112:186 */       (this.craftMatrix.getStackInSlot(2) == null) && 
/* 113:187 */       (this.craftMatrix.getStackInSlot(3) == null) && 
/* 114:188 */       (this.craftMatrix.getStackInSlot(4) != null) && 
/* 115:189 */       (this.craftMatrix.getStackInSlot(5) == null) && 
/* 116:190 */       (this.craftMatrix.getStackInSlot(6) == null) && 
/* 117:191 */       (this.craftMatrix.getStackInSlot(7) == null) && 
/* 118:192 */       (this.craftMatrix.getStackInSlot(8) == null)) {
/* 119:194 */       form = 1;
/* 120:198 */     } else if ((this.craftMatrix.getStackInSlot(0) != null) && 
/* 121:199 */       (this.craftMatrix.getStackInSlot(1) != null) && 
/* 122:200 */       (this.craftMatrix.getStackInSlot(2) != null) && 
/* 123:201 */       (this.craftMatrix.getStackInSlot(3) == null) && 
/* 124:202 */       (this.craftMatrix.getStackInSlot(4) == null) && 
/* 125:203 */       (this.craftMatrix.getStackInSlot(5) == null) && 
/* 126:204 */       (this.craftMatrix.getStackInSlot(6) == null) && 
/* 127:205 */       (this.craftMatrix.getStackInSlot(7) == null) && 
/* 128:206 */       (this.craftMatrix.getStackInSlot(8) == null)) {
/* 129:208 */       form = 2;
/* 130:212 */     } else if ((this.craftMatrix.getStackInSlot(0) != null) && 
/* 131:213 */       (this.craftMatrix.getStackInSlot(1) != null) && 
/* 132:214 */       (this.craftMatrix.getStackInSlot(2) != null) && 
/* 133:215 */       (this.craftMatrix.getStackInSlot(3) != null) && 
/* 134:216 */       (this.craftMatrix.getStackInSlot(4) == null) && 
/* 135:217 */       (this.craftMatrix.getStackInSlot(5) != null) && 
/* 136:218 */       (this.craftMatrix.getStackInSlot(6) != null) && 
/* 137:219 */       (this.craftMatrix.getStackInSlot(7) != null) && 
/* 138:220 */       (this.craftMatrix.getStackInSlot(8) != null)) {
/* 139:222 */       form = 3;
/* 140:226 */     } else if ((this.craftMatrix.getStackInSlot(0) != null) && 
/* 141:227 */       (this.craftMatrix.getStackInSlot(1) != null) && 
/* 142:228 */       (this.craftMatrix.getStackInSlot(2) != null) && 
/* 143:229 */       (this.craftMatrix.getStackInSlot(3) != null) && 
/* 144:230 */       (this.craftMatrix.getStackInSlot(4) != null) && 
/* 145:231 */       (this.craftMatrix.getStackInSlot(5) != null) && 
/* 146:232 */       (this.craftMatrix.getStackInSlot(6) != null) && 
/* 147:233 */       (this.craftMatrix.getStackInSlot(7) != null) && 
/* 148:234 */       (this.craftMatrix.getStackInSlot(8) != null))
/* 149:    */     {
/* 150:236 */       if (way > 2) {
/* 151:238 */         form = 4;
/* 152:    */       }
/* 153:    */     }
/* 154:243 */     else if ((this.craftMatrix.getStackInSlot(0) == null) && 
/* 155:244 */       (this.craftMatrix.getStackInSlot(1) != null) && 
/* 156:245 */       (this.craftMatrix.getStackInSlot(2) == null) && 
/* 157:246 */       (this.craftMatrix.getStackInSlot(3) != null) && 
/* 158:247 */       (this.craftMatrix.getStackInSlot(4) == null) && 
/* 159:248 */       (this.craftMatrix.getStackInSlot(5) != null) && 
/* 160:249 */       (this.craftMatrix.getStackInSlot(6) == null) && 
/* 161:250 */       (this.craftMatrix.getStackInSlot(7) != null) && 
/* 162:251 */       (this.craftMatrix.getStackInSlot(8) == null))
/* 163:    */     {
/* 164:253 */       if (way > 2) {
/* 165:255 */         form = 5;
/* 166:    */       }
/* 167:    */     }
/* 168:259 */     else if ((this.craftMatrix.getStackInSlot(0) != null) || 
/* 169:260 */       (this.craftMatrix.getStackInSlot(1) != null) || 
/* 170:261 */       (this.craftMatrix.getStackInSlot(2) != null) || 
/* 171:262 */       (this.craftMatrix.getStackInSlot(3) != null) || 
/* 172:263 */       (this.craftMatrix.getStackInSlot(4) != null) || 
/* 173:264 */       (this.craftMatrix.getStackInSlot(5) != null) || 
/* 174:265 */       (this.craftMatrix.getStackInSlot(6) != null) || 
/* 175:266 */       (this.craftMatrix.getStackInSlot(7) != null) || 
/* 176:267 */       (this.craftMatrix.getStackInSlot(8) != null)) {
/* 177:273 */       form = -1;
/* 178:    */     }
/* 179:276 */     if (this.craftColor.getStackInSlot(0) != null) {
/* 180:278 */       if (this.craftColor.getStackInSlot(0).getItem() == Items.dye) {
/* 181:280 */         color = (byte)thKaguyaMod.item.ItemTHShot.sellectbleColor[(this.craftColor.getStackInSlot(0).getItemDamage() % 16)];
/* 182:282 */       } else if (this.craftColor.getStackInSlot(0).getItem() == Items.gunpowder) {
/* 183:285 */         color = 8;
/* 184:287 */       } else if (this.craftColor.getStackInSlot(0).getItem() == Items.diamond) {
/* 185:290 */         color = 9;
/* 186:    */       }
/* 187:    */     }
/* 188:294 */     if ((shot != null) && ((form != 4) || (way <= 32)) && (form >= 0))
/* 189:    */     {
/* 190:296 */       this.newShot = new ItemStack(THKaguyaItems.laser_item);
/* 191:297 */       this.newShot.setItemDamage(this.craftMaterial.getStackInSlot(0).getItemDamage());
/* 192:298 */       NBTTagCompound nbt2 = this.newShot.getTagCompound();
/* 193:299 */       if (nbt2 == null)
/* 194:    */       {
/* 195:301 */         nbt2 = new NBTTagCompound();
/* 196:302 */         this.newShot.setTagCompound(nbt2);
/* 197:    */       }
/* 198:304 */       nbt2.setShort("Number", way);
/* 199:305 */       nbt2.setByte("DanmakuForm", form);
/* 200:306 */       nbt2.setByte("Speed", addSpeed);
/* 201:307 */       nbt2.setByte("Color", color);
/* 202:    */       
/* 203:    */ 
/* 204:    */ 
/* 205:311 */       int copyNum = 1;
/* 206:312 */       if (this.craftCopy.getStackInSlot(0) != null)
/* 207:    */       {
/* 208:314 */         int oriNum = shot.stackSize;
/* 209:315 */         copyNum = this.craftCopy.getStackInSlot(0).stackSize + 1;
/* 210:317 */         if (copyNum > oriNum) {
/* 211:319 */           copyNum = oriNum;
/* 212:    */         }
/* 213:322 */         this.newShot.stackSize = copyNum;
/* 214:    */       }
/* 215:325 */       this.craftResult.setInventorySlotContents(0, this.newShot);
/* 216:    */     }
/* 217:    */     else
/* 218:    */     {
/* 219:330 */       this.craftResult.setInventorySlotContents(0, null);
/* 220:    */     }
/* 221:333 */     detectAndSendChanges();
/* 222:    */   }
/* 223:    */   
/* 224:    */   public void onContainerClosed(EntityPlayer player)
/* 225:    */   {
/* 226:339 */     super.onContainerClosed(player);
/* 227:341 */     if (!this.world.isRemote)
/* 228:    */     {
/* 229:344 */       for (int i = 0; i < 9; i++)
/* 230:    */       {
/* 231:346 */         ItemStack itemStack = this.craftMatrix.getStackInSlotOnClosing(i);
/* 232:348 */         if (itemStack != null) {
/* 233:350 */           player.dropPlayerItemWithRandomChoice(itemStack, false);
/* 234:    */         }
/* 235:    */       }
/* 236:353 */       ItemStack itemStack = this.craftWay.getStackInSlotOnClosing(0);
/* 237:354 */       if (itemStack != null) {
/* 238:356 */         player.dropPlayerItemWithRandomChoice(itemStack, false);
/* 239:    */       }
/* 240:358 */       itemStack = this.craftSpeed.getStackInSlotOnClosing(0);
/* 241:359 */       if (itemStack != null) {
/* 242:361 */         player.dropPlayerItemWithRandomChoice(itemStack, false);
/* 243:    */       }
/* 244:363 */       itemStack = this.craftCopy.getStackInSlotOnClosing(0);
/* 245:364 */       if (itemStack != null) {
/* 246:366 */         player.dropPlayerItemWithRandomChoice(itemStack, false);
/* 247:    */       }
/* 248:368 */       itemStack = this.craftColor.getStackInSlotOnClosing(0);
/* 249:369 */       if (itemStack != null) {
/* 250:371 */         player.dropPlayerItemWithRandomChoice(itemStack, false);
/* 251:    */       }
/* 252:373 */       itemStack = this.craftMaterial.getStackInSlotOnClosing(0);
/* 253:374 */       if (itemStack != null) {
/* 254:376 */         player.dropPlayerItemWithRandomChoice(itemStack, false);
/* 255:    */       }
/* 256:    */     }
/* 257:    */   }
/* 258:    */   
/* 259:    */   public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
/* 260:    */   {
/* 261:430 */     ItemStack itemstack = null;
/* 262:431 */     Slot slot = (Slot)this.inventorySlots.get(par2);
/* 263:433 */     if ((slot != null) && (slot.getHasStack()))
/* 264:    */     {
/* 265:435 */       ItemStack itemstack1 = slot.getStack();
/* 266:436 */       itemstack = itemstack1.copy();
/* 267:438 */       if (par2 == 0)
/* 268:    */       {
/* 269:440 */         if (!mergeItemStack(itemstack1, 10, 46, true)) {
/* 270:442 */           return null;
/* 271:    */         }
/* 272:445 */         slot.onSlotChange(itemstack1, itemstack);
/* 273:    */       }
/* 274:447 */       else if ((par2 >= 10) && (par2 < 37))
/* 275:    */       {
/* 276:449 */         if (!mergeItemStack(itemstack1, 37, 46, false)) {
/* 277:451 */           return null;
/* 278:    */         }
/* 279:    */       }
/* 280:454 */       else if ((par2 >= 37) && (par2 < 46))
/* 281:    */       {
/* 282:456 */         if (!mergeItemStack(itemstack1, 10, 37, false)) {
/* 283:458 */           return null;
/* 284:    */         }
/* 285:    */       }
/* 286:461 */       else if (!mergeItemStack(itemstack1, 10, 46, false))
/* 287:    */       {
/* 288:463 */         return null;
/* 289:    */       }
/* 290:466 */       if (itemstack1.stackSize == 0) {
/* 291:468 */         slot.putStack((ItemStack)null);
/* 292:    */       } else {
/* 293:472 */         slot.onSlotChanged();
/* 294:    */       }
/* 295:475 */       if (itemstack1.stackSize == itemstack.stackSize) {
/* 296:477 */         return null;
/* 297:    */       }
/* 298:480 */       slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
/* 299:    */     }
/* 300:483 */     return itemstack;
/* 301:    */   }
/* 302:    */   
/* 303:    */   public ItemStack slotClick(int par1, int par2, int par3, EntityPlayer par4EntityPlayer)
/* 304:    */   {
/* 305:508 */     super.slotClick(par1, par2, par3, par4EntityPlayer);
/* 306:    */     
/* 307:    */ 
/* 308:511 */     onCraftMatrixChanged(null);
/* 309:    */     
/* 310:    */ 
/* 311:514 */     return this.newShot;
/* 312:    */   }
/* 313:    */   
/* 314:    */   public boolean func_94530_a(ItemStack par1ItemStack, Slot par2Slot)
/* 315:    */   {
/* 316:519 */     return (par2Slot.inventory != this.craftResult) && (super.func_94530_a(par1ItemStack, par2Slot));
/* 317:    */   }
/* 318:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.gui.ContainerDanmakuCraftingLaser
 * JD-Core Version:    0.7.0.1
 */