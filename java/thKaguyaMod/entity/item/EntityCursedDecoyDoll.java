/*   1:    */ package thKaguyaMod.entity.item;
/*   2:    */ 
/*   3:    */ import java.util.List;
/*   4:    */ import net.minecraft.entity.Entity;
/*   5:    */ import net.minecraft.entity.EntityCreature;
/*   6:    */ import net.minecraft.entity.EntityLivingBase;
/*   7:    */ import net.minecraft.entity.item.EntityItemFrame;
/*   8:    */ import net.minecraft.entity.item.EntityPainting;
/*   9:    */ import net.minecraft.entity.player.EntityPlayer;
/*  10:    */ import net.minecraft.util.AxisAlignedBB;
/*  11:    */ import net.minecraft.util.MovingObjectPosition;
/*  12:    */ import net.minecraft.world.World;
/*  13:    */ import thKaguyaMod.THKaguyaLib;
/*  14:    */ import thKaguyaMod.entity.living.EntityDanmakuMob;
/*  15:    */ import thKaguyaMod.init.THKaguyaItems;
/*  16:    */ 
/*  17:    */ public class EntityCursedDecoyDoll
/*  18:    */   extends EntityDanmakuMob
/*  19:    */ {
/*  20:    */   public EntityLivingBase userEntity;
/*  21:    */   
/*  22:    */   public EntityCursedDecoyDoll(World world)
/*  23:    */   {
/*  24: 25 */     super(world);
/*  25: 26 */     setSize(1.0F, 1.8F);
/*  26:    */     
/*  27: 28 */     this.experienceValue = 1;
/*  28:    */     
/*  29: 30 */     setDanmakuMobName("");
/*  30: 31 */     setSpecies(109);
/*  31:    */     
/*  32: 33 */     setDanmakuPattern(0);
/*  33: 34 */     setMaxHP(40.0F);
/*  34: 35 */     setHealth(40.0F);
/*  35: 36 */     setSpeed(0.0D);
/*  36: 37 */     setAttackDistance(0.0D);
/*  37: 38 */     setDetectionDistance(0.0D);
/*  38: 39 */     setFlyingHeight(0);
/*  39: 40 */     this.isFlyingMode = false;
/*  40:    */     
/*  41: 42 */     this.isSpellCardMode = false;
/*  42: 43 */     this.attackInterval = 0;
/*  43:    */   }
/*  44:    */   
/*  45:    */   public EntityCursedDecoyDoll(World world, EntityLivingBase living)
/*  46:    */   {
/*  47: 48 */     this(world);
/*  48:    */     
/*  49: 50 */     this.userEntity = living;
/*  50: 51 */     setPosition(living.posX, living.posY, living.posZ);
/*  51:    */   }
/*  52:    */   
/*  53:    */   protected boolean canFairyCall()
/*  54:    */   {
/*  55: 59 */     return false;
/*  56:    */   }
/*  57:    */   
/*  58:    */   public void onUpdate()
/*  59:    */   {
/*  60: 68 */     super.onUpdate();
/*  61: 82 */     if (this.ticksExisted > 180) {
/*  62: 86 */       if (!this.worldObj.isRemote) {
/*  63: 88 */         setDead();
/*  64:    */       }
/*  65:    */     }
/*  66: 93 */     int playerCount = 0;
/*  67:    */     
/*  68:    */ 
/*  69: 96 */     MovingObjectPosition movingObjectPosition = new MovingObjectPosition(this);
/*  70: 97 */     Entity entity = null;
/*  71: 98 */     boolean dubbleWatch = false;
/*  72: 99 */     boolean pass = false;
/*  73:100 */     double effectiveBoundary = 40.0D;
/*  74:    */     
/*  75:102 */     List<?> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(effectiveBoundary, effectiveBoundary, effectiveBoundary));
/*  76:104 */     if ((list != null) && (list.size() > 0)) {
/*  77:106 */       for (int j1 = 0; j1 < list.size(); j1++)
/*  78:    */       {
/*  79:108 */         entity = (Entity)list.get(j1);
/*  80:109 */         if (entity != null) {
/*  81:111 */           movingObjectPosition = new MovingObjectPosition(entity);
/*  82:    */         }
/*  83:113 */         if ((entity instanceof EntityPlayer)) {
/*  84:115 */           playerCount++;
/*  85:    */         }
/*  86:118 */         if ((movingObjectPosition != null) && (movingObjectPosition.entityHit != this.userEntity) && (movingObjectPosition.entityHit.riddenByEntity != this.userEntity) && (!(movingObjectPosition.entityHit instanceof EntityItemFrame)) && (!(movingObjectPosition.entityHit instanceof EntityPainting)))
/*  87:    */         {
/*  88:122 */           pass = false;
/*  89:124 */           if ((movingObjectPosition.entityHit instanceof EntityCursedDecoyDoll))
/*  90:    */           {
/*  91:126 */             EntityCursedDecoyDoll decoy = (EntityCursedDecoyDoll)movingObjectPosition.entityHit;
/*  92:    */             
/*  93:128 */             THKaguyaLib.itemEffectFinish(decoy, decoy.userEntity, THKaguyaItems.cursedDecoyDoll);
/*  94:129 */             dubbleWatch = true;
/*  95:    */           }
/*  96:131 */           if (!pass) {
/*  97:133 */             inDecoyEffectedArea(movingObjectPosition);
/*  98:    */           }
/*  99:    */         }
/* 100:    */       }
/* 101:    */     }
/* 102:139 */     if (playerCount == 0) {
/* 103:141 */       if (!this.worldObj.isRemote) {
/* 104:143 */         THKaguyaLib.itemEffectFinish(this, this.userEntity, THKaguyaItems.cursedDecoyDoll);
/* 105:    */       }
/* 106:    */     }
/* 107:147 */     if (dubbleWatch) {
/* 108:150 */       THKaguyaLib.itemEffectFinish(this, this.userEntity, THKaguyaItems.cursedDecoyDoll);
/* 109:    */     }
/* 110:155 */     setRotation(this.prevRotationYaw, this.prevRotationPitch);
/* 111:    */   }
/* 112:    */   
/* 113:    */   public void inDecoyEffectedArea(MovingObjectPosition movingObjectPosition)
/* 114:    */   {
/* 115:166 */     if ((movingObjectPosition.entityHit instanceof EntityCreature))
/* 116:    */     {
/* 117:168 */       EntityCreature effectedCreature = (EntityCreature)movingObjectPosition.entityHit;
/* 118:170 */       if (effectedCreature.getEntityToAttack() != null) {
/* 119:172 */         effectedCreature.setTarget(this);
/* 120:    */       }
/* 121:    */     }
/* 122:175 */     if ((movingObjectPosition.entityHit instanceof EntityLivingBase))
/* 123:    */     {
/* 124:177 */       EntityLivingBase effectedLiving = (EntityLivingBase)movingObjectPosition.entityHit;
/* 125:    */       
/* 126:    */ 
/* 127:180 */       effectedLiving.setRevengeTarget(this);
/* 128:    */     }
/* 129:    */   }
/* 130:    */   
/* 131:    */   protected String getLivingSound()
/* 132:    */   {
/* 133:240 */     return "";
/* 134:    */   }
/* 135:    */   
/* 136:    */   protected String getHurtSound()
/* 137:    */   {
/* 138:247 */     return "";
/* 139:    */   }
/* 140:    */   
/* 141:    */   protected String getDeathSound()
/* 142:    */   {
/* 143:254 */     return "";
/* 144:    */   }
/* 145:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.item.EntityCursedDecoyDoll
 * JD-Core Version:    0.7.0.1
 */