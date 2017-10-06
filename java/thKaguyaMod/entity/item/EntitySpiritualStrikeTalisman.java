/*   1:    */ package thKaguyaMod.entity.item;
/*   2:    */ 
/*   3:    */ import java.util.List;
/*   4:    */ import net.minecraft.entity.Entity;
/*   5:    */ import net.minecraft.entity.EntityLivingBase;
/*   6:    */ import net.minecraft.entity.passive.EntityAnimal;
/*   7:    */ import net.minecraft.entity.passive.EntityVillager;
/*   8:    */ import net.minecraft.nbt.NBTTagCompound;
/*   9:    */ import net.minecraft.util.AxisAlignedBB;
/*  10:    */ import net.minecraft.util.MovingObjectPosition;
/*  11:    */ import net.minecraft.util.Vec3;
/*  12:    */ import net.minecraft.world.World;
/*  13:    */ import thKaguyaMod.THKaguyaLib;
/*  14:    */ import thKaguyaMod.THShotLib;
/*  15:    */ import thKaguyaMod.entity.shot.EntityTHShot;
/*  16:    */ 
/*  17:    */ public class EntitySpiritualStrikeTalisman
/*  18:    */   extends Entity
/*  19:    */ {
/*  20:    */   public EntityLivingBase userEntity;
/*  21:    */   private int count;
/*  22:    */   
/*  23:    */   public EntitySpiritualStrikeTalisman(World world)
/*  24:    */   {
/*  25: 29 */     super(world);
/*  26: 30 */     this.ignoreFrustumCheck = true;
/*  27: 31 */     this.preventEntitySpawning = true;
/*  28: 32 */     setSize(0.2F, 0.2F);
/*  29: 33 */     this.yOffset = 0.0F;
/*  30:    */   }
/*  31:    */   
/*  32:    */   public EntitySpiritualStrikeTalisman(World world, EntityLivingBase living)
/*  33:    */   {
/*  34: 39 */     this(world);
/*  35:    */     
/*  36: 41 */     this.userEntity = living;
/*  37: 42 */     THKaguyaLib.itemEffectFollowUser(this, this.userEntity, 2.0D, 30.0F);
/*  38:    */   }
/*  39:    */   
/*  40:    */   protected void entityInit() {}
/*  41:    */   
/*  42:    */   public boolean canBePushed()
/*  43:    */   {
/*  44: 58 */     return false;
/*  45:    */   }
/*  46:    */   
/*  47:    */   public boolean canBeCollidedWith()
/*  48:    */   {
/*  49: 67 */     return false;
/*  50:    */   }
/*  51:    */   
/*  52:    */   public void onUpdate()
/*  53:    */   {
/*  54: 74 */     super.onUpdate();
/*  55: 77 */     if (this.ticksExisted > 3) {
/*  56: 79 */       if (!this.worldObj.isRemote) {
/*  57: 81 */         setDead();
/*  58:    */       }
/*  59:    */     }
/*  60: 86 */     MovingObjectPosition movingObjectPosition = new MovingObjectPosition(this);
/*  61: 87 */     Entity entity = null;
/*  62: 88 */     double effectiveBoundary = this.ticksExisted * 4.0D;
/*  63:    */     
/*  64: 90 */     List<?> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(effectiveBoundary, effectiveBoundary, effectiveBoundary));
/*  65: 92 */     if ((list != null) && (list.size() > 0)) {
/*  66: 94 */       for (int j1 = 0; j1 < list.size(); j1++)
/*  67:    */       {
/*  68: 96 */         entity = (Entity)list.get(j1);
/*  69: 97 */         if (entity != null) {
/*  70: 99 */           movingObjectPosition = new MovingObjectPosition(entity);
/*  71:    */         }
/*  72:102 */         if ((movingObjectPosition != null) && (movingObjectPosition.entityHit != this.userEntity) && (movingObjectPosition.entityHit.riddenByEntity != this.userEntity))
/*  73:    */         {
/*  74:104 */           double distance = entity.getDistanceToEntity(this);
/*  75:105 */           if (distance <= effectiveBoundary)
/*  76:    */           {
/*  77:107 */             Vec3 angle = THShotLib.angle_ToPos(THShotLib.pos_Entity(this), 
/*  78:108 */               THShotLib.pos(entity.posX, entity.posY, entity.posZ));
/*  79:109 */             double effectivePower = (effectiveBoundary - distance) * 0.1D;
/*  80:111 */             if ((entity instanceof EntityLivingBase))
/*  81:    */             {
/*  82:113 */               if ((!(entity instanceof EntityAnimal)) && (!(entity instanceof EntityVillager)))
/*  83:    */               {
/*  84:115 */                 entity.motionX = (angle.xCoord * effectivePower);
/*  85:116 */                 entity.motionY = (0.5D * effectivePower);
/*  86:117 */                 entity.motionZ = (angle.zCoord * effectivePower);
/*  87:    */               }
/*  88:    */             }
/*  89:120 */             else if ((entity instanceof EntityTHShot))
/*  90:    */             {
/*  91:122 */               EntityTHShot shot = (EntityTHShot)entity;
/*  92:    */               
/*  93:124 */               shot.shotFinishBonus();
/*  94:    */             }
/*  95:    */           }
/*  96:    */         }
/*  97:    */       }
/*  98:    */     }
/*  99:    */   }
/* 100:    */   
/* 101:    */   protected void writeEntityToNBT(NBTTagCompound nbtTagCompound)
/* 102:    */   {
/* 103:142 */     nbtTagCompound.setShort("count", (short)this.count);
/* 104:    */   }
/* 105:    */   
/* 106:    */   protected void readEntityFromNBT(NBTTagCompound nbtTagCompound)
/* 107:    */   {
/* 108:153 */     this.count = nbtTagCompound.getShort("count");
/* 109:    */   }
/* 110:    */   
/* 111:    */   public float getShadowSize()
/* 112:    */   {
/* 113:160 */     return 0.5F;
/* 114:    */   }
/* 115:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.item.EntitySpiritualStrikeTalisman
 * JD-Core Version:    0.7.0.1
 */