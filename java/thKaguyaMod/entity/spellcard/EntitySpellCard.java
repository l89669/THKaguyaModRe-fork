/*   1:    */ package thKaguyaMod.entity.spellcard;
/*   2:    */ 
/*   3:    */ import net.minecraft.entity.DataWatcher;
/*   4:    */ import net.minecraft.entity.Entity;
/*   5:    */ import net.minecraft.entity.EntityLivingBase;
/*   6:    */ import net.minecraft.entity.player.EntityPlayer;
/*   7:    */ import net.minecraft.nbt.NBTTagCompound;
/*   8:    */ import net.minecraft.util.MathHelper;
/*   9:    */ import net.minecraft.util.Vec3;
/*  10:    */ import net.minecraft.world.World;
/*  11:    */ import thKaguyaMod.THShotLib;
/*  12:    */ import thKaguyaMod.entity.effect.EntitySpellCardCircle;
/*  13:    */ import thKaguyaMod.entity.living.EntityDanmakuMob;
/*  14:    */ import thKaguyaMod.registry.SpellCardRegistry;
/*  15:    */ 
/*  16:    */ public class EntitySpellCard
/*  17:    */   extends Entity
/*  18:    */ {
/*  19:    */   public EntityLivingBase user;
/*  20:    */   public EntityLivingBase tgEntity;
/*  21:    */   public int count;
/*  22:    */   private int spellCardNumber;
/*  23:    */   protected Vec3 tgVec;
/*  24:    */   private int spellCardUsedTime;
/*  25:    */   private int lastTime;
/*  26:    */   public int level;
/*  27:    */   public EntitySpellCardCircle circle;
/*  28:    */   public boolean canMoveInTimeStop;
/*  29:    */   public static final int SC_REIMU_MusouFuuin = 0;
/*  30:    */   public static final int SC_MARISA_MasterSpark = 1;
/*  31:    */   public static final int SC_YUYUKO_Kasho_no_Eimin = 2;
/*  32:    */   public static final int SC_MARISA_MeteonicShower = 3;
/*  33:    */   public static final int SC_YUKARI_Nami_to_Tubu_no_Kyoukai = 4;
/*  34:    */   public static final int SC_YUKARI_NijuuKokushichou = 5;
/*  35:    */   public static final int SC_REMILIA_ScarletShoot = 6;
/*  36:    */   public static final int SC_TENSHI_Zenzinrui_no_Hisouten = 7;
/*  37:    */   public static final int SC_SAKUYA_SatuzinDoll = 8;
/*  38:    */   public static final int SC_CIRNO_PerfectFreeze = 9;
/*  39:    */   public static final int SC_YUKARI_HikouchuuNest = 10;
/*  40:    */   public static final int SC_NITORI_Kappa_no_Pororoca = 11;
/*  41:    */   public static final int SC_MARISA_StardustReverie = 12;
/*  42:    */   public static final int SC_SUWAKO_Kerochan_Fuu_ni_Makezu = 13;
/*  43:    */   public static final int SC_SANAE_MiracleFruit = 14;
/*  44:    */   public static final int SC_SANAE_Fafrotskies_no_Kiseki = 15;
/*  45:    */   public static final int SC_SANAE_YouryokuSpoiler = 16;
/*  46:    */   public static final int SC_SANAE_Moses_no_Kiseki = 17;
/*  47:    */   public static final int SC_SANAE_Yasaka_no_Kamikaze = 18;
/*  48:    */   public static final int SC_CIRNO_IcicleFall = 19;
/*  49:    */   public static final int SC_FLANDRE_StarbowBreak = 20;
/*  50:    */   public static final int SC_FLANDRE_Catadioptric = 21;
/*  51:    */   public static final int SC_SUWAKO_MishagujiSama = 22;
/*  52:    */   public static final int SC_REMILIA_RedMagic = 23;
/*  53:    */   public static final int SC_SAKUYA_EternalMeek = 24;
/*  54:    */   public static final int SC_MARISA_NonDirectionalLaser = 25;
/*  55:    */   public static final int SC_YUUKA_Kachoufuugetu_Shoufuurougetu = 26;
/*  56:    */   public static final int SC_REMILIA_Spear_the_Gungnir = 27;
/*  57:    */   public static final int SC_RUMIA_MoonlightRay = 28;
/*  58:    */   public static final int SC_MEIRIN_Houkakenran = 29;
/*  59:    */   public static final int SC_MEIRIN_Saikouranbu = 30;
/*  60:    */   public static final int SC_PATCHOULI_PrincessUndine = 31;
/*  61:    */   Class<?> spellcard_0;
/*  62:    */   Class<?> spellcard;
/*  63:    */   THSpellCard useSpellCard;
/*  64:    */   
/*  65:    */   public EntitySpellCard(World world)
/*  66:    */   {
/*  67: 76 */     super(world);
/*  68: 77 */     this.preventEntitySpawning = true;
/*  69: 78 */     setSize(0.4F, 0.4F);
/*  70: 79 */     this.yOffset = 0.0F;
/*  71:    */   }
/*  72:    */   
/*  73:    */   public EntitySpellCard(World world, EntityLivingBase spUser, EntityLivingBase target, int num, int spLevel)
/*  74:    */   {
/*  75: 85 */     this(world);
/*  76:    */     
/*  77: 87 */     setPosition(spUser.posX - MathHelper.sin((spUser.rotationYaw + 30.0F) / 180.0F * 3.141593F) * MathHelper.cos(spUser.rotationPitch / 180.0F * 3.141593F), spUser.posY - 
/*  78: 88 */       MathHelper.sin(spUser.rotationPitch / 180.0F * 3.141593F) + spUser.getEyeHeight() - 0.1000000014901161D, spUser.posZ + 
/*  79: 89 */       MathHelper.cos((spUser.rotationYaw + 30.0F) / 180.0F * 3.141593F) * MathHelper.cos(spUser.rotationPitch / 180.0F * 3.141593F));
/*  80: 90 */     this.rotationYaw = spUser.rotationYaw;
/*  81: 91 */     this.rotationPitch = spUser.rotationPitch;
/*  82: 92 */     setRotation(this.rotationYaw, this.rotationPitch);
/*  83: 93 */     this.tgVec = spUser.getLookVec();
/*  84: 94 */     this.user = spUser;
/*  85: 95 */     this.tgEntity = target;
/*  86: 96 */     this.spellCardNumber = num;
/*  87: 97 */     setSpellCardNumber(num);
/*  88: 98 */     this.spellCardUsedTime = 0;
/*  89: 99 */     this.count = 0;
/*  90:100 */     this.lastTime = 0;
/*  91:    */     
/*  92:    */ 
/*  93:    */ 
/*  94:    */ 
/*  95:105 */     this.level = spLevel;
/*  96:107 */     if ((this.spellcard = SpellCardRegistry.getSpellCardClass(num)) != null)
/*  97:    */     {
/*  98:    */       try
/*  99:    */       {
/* 100:110 */         this.useSpellCard = ((THSpellCard)this.spellcard.newInstance());
/* 101:    */       }
/* 102:    */       catch (InstantiationException e)
/* 103:    */       {
/* 104:112 */         e.printStackTrace();
/* 105:    */       }
/* 106:    */       catch (IllegalAccessException e)
/* 107:    */       {
/* 108:114 */         e.printStackTrace();
/* 109:    */       }
/* 110:117 */       this.useSpellCard.init(world, this, this.user, this.tgEntity, this.level);
/* 111:118 */       this.canMoveInTimeStop = this.useSpellCard.canMoveInTimeStop();
/* 112:119 */       if ((spUser instanceof EntityPlayer))
/* 113:    */       {
/* 114:123 */         this.circle = new EntitySpellCardCircle(world, spUser, this.useSpellCard.getSpellCardCircleColor(), this.useSpellCard.getEndTime());
/* 115:124 */         if (!world.isRemote) {
/* 116:126 */           world.spawnEntityInWorld(this.circle);
/* 117:    */         }
/* 118:    */       }
/* 119:    */     }
/* 120:132 */     else if (!this.worldObj.isRemote)
/* 121:    */     {
/* 122:134 */       setDead();
/* 123:    */     }
/* 124:    */   }
/* 125:    */   
/* 126:    */   protected void entityInit()
/* 127:    */   {
/* 128:150 */     this.dataWatcher.addObject(18, new Integer(0));
/* 129:    */   }
/* 130:    */   
/* 131:    */   public boolean canBePushed()
/* 132:    */   {
/* 133:159 */     return false;
/* 134:    */   }
/* 135:    */   
/* 136:    */   public boolean canBeCollidedWith()
/* 137:    */   {
/* 138:168 */     return false;
/* 139:    */   }
/* 140:    */   
/* 141:    */   private void finish()
/* 142:    */   {
/* 143:176 */     if (!this.worldObj.isRemote)
/* 144:    */     {
/* 145:178 */       setDead();
/* 146:179 */       if (this.circle != null) {
/* 147:181 */         this.circle.setDead();
/* 148:    */       }
/* 149:    */     }
/* 150:    */   }
/* 151:    */   
/* 152:    */   private void spellCardEnd(int time)
/* 153:    */   {
/* 154:191 */     if (this.ticksExisted >= time) {
/* 155:193 */       finish();
/* 156:    */     }
/* 157:    */   }
/* 158:    */   
/* 159:    */   private void playerSpellCardDanmakuRemove(int time)
/* 160:    */   {
/* 161:199 */     if (((this.user instanceof EntityPlayer)) && (this.ticksExisted < time)) {
/* 162:201 */       THShotLib.danmakuRemove(this.user, 40.0D, "Other", true);
/* 163:    */     }
/* 164:    */   }
/* 165:    */   
/* 166:    */   private void playerSpellCardDanmakuRemove(int start, int end)
/* 167:    */   {
/* 168:207 */     if (((this.user instanceof EntityPlayer)) && (this.ticksExisted >= start) && (this.ticksExisted < end)) {
/* 169:209 */       THShotLib.danmakuRemove(this.user, 40.0D, "Other", true);
/* 170:    */     }
/* 171:    */   }
/* 172:    */   
/* 173:    */   public void specialProcessInTimeStop()
/* 174:    */   {
/* 175:215 */     this.useSpellCard.specialProcessInTimeStop();
/* 176:    */   }
/* 177:    */   
/* 178:    */   public void onUpdate()
/* 179:    */   {
/* 180:225 */     if (this.ticksExisted <= this.lastTime) {
/* 181:227 */       return;
/* 182:    */     }
/* 183:230 */     super.onUpdate();
/* 184:233 */     if ((!this.worldObj.isRemote) && ((this.user == null) || (this.user.isDead)))
/* 185:    */     {
/* 186:235 */       finish();
/* 187:236 */       return;
/* 188:    */     }
/* 189:240 */     if (this.user != null) {
/* 190:243 */       if ((this.user instanceof EntityDanmakuMob))
/* 191:    */       {
/* 192:245 */         EntityDanmakuMob danmakuMob = (EntityDanmakuMob)this.user;
/* 193:247 */         if (!danmakuMob.isSpellCardMode)
/* 194:    */         {
/* 195:249 */           finish();
/* 196:250 */           return;
/* 197:    */         }
/* 198:    */       }
/* 199:    */     }
/* 200:256 */     if (this.ticksExisted > this.spellCardUsedTime)
/* 201:    */     {
/* 202:258 */       if (this.spellcard == null) {
/* 203:260 */         return;
/* 204:    */       }
/* 205:262 */       if (this.useSpellCard == null) {
/* 206:264 */         return;
/* 207:    */       }
/* 208:267 */       this.useSpellCard.onUpdate();
/* 209:    */       
/* 210:269 */       playerSpellCardDanmakuRemove(this.useSpellCard.getRemoveTime());
/* 211:271 */       if (this.useSpellCard.time >= this.useSpellCard.getEndTime())
/* 212:    */       {
/* 213:273 */         finish();
/* 214:274 */         return;
/* 215:    */       }
/* 216:    */     }
/* 217:278 */     if (this.spellCardUsedTime < this.ticksExisted) {
/* 218:280 */       this.spellCardUsedTime = this.ticksExisted;
/* 219:    */     }
/* 220:284 */     if (this.ticksExisted >= 9999) {
/* 221:286 */       if (!this.worldObj.isRemote) {
/* 222:288 */         finish();
/* 223:    */       }
/* 224:    */     }
/* 225:293 */     if (this.ticksExisted > this.lastTime) {
/* 226:296 */       this.lastTime = this.ticksExisted;
/* 227:    */     }
/* 228:    */   }
/* 229:    */   
/* 230:    */   protected void writeEntityToNBT(NBTTagCompound nbtTagCompound) {}
/* 231:    */   
/* 232:    */   protected void readEntityFromNBT(NBTTagCompound nbtTagCompound) {}
/* 233:    */   
/* 234:    */   public void setSpellCardNumber(int number)
/* 235:    */   {
/* 236:324 */     this.dataWatcher.updateObject(18, Integer.valueOf(number));
/* 237:    */   }
/* 238:    */   
/* 239:    */   public int getSpellCardNumber()
/* 240:    */   {
/* 241:332 */     return this.dataWatcher.getWatchableObjectInt(18);
/* 242:    */   }
/* 243:    */   
/* 244:    */   public float getBrightness(float par1)
/* 245:    */   {
/* 246:340 */     return 0.5F;
/* 247:    */   }
/* 248:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.spellcard.EntitySpellCard
 * JD-Core Version:    0.7.0.1
 */