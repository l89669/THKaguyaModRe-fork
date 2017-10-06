/*   1:    */ package thKaguyaMod.entity.living;
/*   2:    */ 
/*   3:    */ import java.util.ArrayList;
/*   4:    */ import java.util.Random;
/*   5:    */ import net.minecraft.entity.Entity;
/*   6:    */ import net.minecraft.entity.EntityCreature;
/*   7:    */ import net.minecraft.entity.EntityLivingBase;
/*   8:    */ import net.minecraft.item.Item;
/*   9:    */ import net.minecraft.util.AxisAlignedBB;
/*  10:    */ import net.minecraft.world.World;
/*  11:    */ import thKaguyaMod.init.THKaguyaItems;
/*  12:    */ import thKaguyaMod.registry.DanmakuPatternRegistry;
/*  13:    */ 
/*  14:    */ public class EntityFamiliar
/*  15:    */   extends EntityTHFairy
/*  16:    */ {
/*  17:    */   public EntityFamiliar(World world)
/*  18:    */   {
/*  19: 19 */     super(world);
/*  20:    */     
/*  21: 21 */     setSize(0.6F, 0.6F);
/*  22: 22 */     this.lastTime = 0;
/*  23:    */     
/*  24: 24 */     this.experienceValue = 0;
/*  25:    */     
/*  26: 26 */     setForm((byte)this.rand.nextInt(3));
/*  27: 27 */     setPattern(this.rand.nextInt(DanmakuPatternRegistry.pattern.size()));
/*  28:    */     
/*  29: 29 */     this.lostTarget = 0;
/*  30: 30 */     setSpeed(0.3D);
/*  31: 31 */     setSpecies(110);
/*  32: 32 */     this.isFlyingMode = true;
/*  33:    */     
/*  34: 34 */     setAttackDistance(8.0D);
/*  35: 35 */     setDetectionDistance(0.0D);
/*  36: 36 */     setFlyingHeight(2);
/*  37:    */   }
/*  38:    */   
/*  39:    */   protected void onDeathUpdate()
/*  40:    */   {
/*  41: 42 */     if (!this.worldObj.isRemote) {
/*  42: 44 */       setDead();
/*  43:    */     }
/*  44:    */   }
/*  45:    */   
/*  46:    */   public EntityLivingBase getShooter()
/*  47:    */   {
/*  48: 55 */     if (this.ridingEntity != null) {
/*  49: 58 */       if ((this.ridingEntity instanceof EntityLivingBase))
/*  50:    */       {
/*  51: 60 */         EntityLivingBase currentEntity = (EntityLivingBase)this.ridingEntity;
/*  52: 61 */         if (currentEntity.getHealth() <= 0.0F) {
/*  53: 63 */           return null;
/*  54:    */         }
/*  55: 66 */         if ((this.ridingEntity instanceof EntityFamiliar))
/*  56:    */         {
/*  57:    */           do
/*  58:    */           {
/*  59: 70 */             if (currentEntity == null) {
/*  60: 72 */               return null;
/*  61:    */             }
/*  62: 74 */             if ((currentEntity.ridingEntity instanceof EntityLivingBase)) {
/*  63: 76 */               currentEntity = (EntityLivingBase)currentEntity.ridingEntity;
/*  64:    */             } else {
/*  65: 80 */               return null;
/*  66:    */             }
/*  67: 82 */           } while ((currentEntity.ridingEntity instanceof EntityFamiliar));
/*  68: 83 */           if ((currentEntity instanceof EntityLivingBase)) {
/*  69: 85 */             return currentEntity;
/*  70:    */           }
/*  71:    */         }
/*  72:    */         else
/*  73:    */         {
/*  74: 90 */           return (EntityLivingBase)this.ridingEntity;
/*  75:    */         }
/*  76:    */       }
/*  77:    */     }
/*  78: 94 */     return null;
/*  79:    */   }
/*  80:    */   
/*  81:    */   public void onUpdate()
/*  82:    */   {
/*  83:101 */     if (this.ticksExisted <= this.lastTime) {
/*  84:103 */       return;
/*  85:    */     }
/*  86:107 */     if ((this.ticksExisted >= 1) && (getShooter() == null))
/*  87:    */     {
/*  88:109 */       if (!this.worldObj.isRemote) {
/*  89:111 */         setDead();
/*  90:    */       }
/*  91:113 */       return;
/*  92:    */     }
/*  93:115 */     if ((this.ridingEntity instanceof EntityCreature))
/*  94:    */     {
/*  95:117 */       EntityCreature riding = (EntityCreature)this.ridingEntity;
/*  96:118 */       this.entityToAttack = riding.getEntityToAttack();
/*  97:    */     }
/*  98:120 */     super.onUpdate();
/*  99:    */   }
/* 100:    */   
/* 101:    */   protected boolean canFairyCall()
/* 102:    */   {
/* 103:129 */     return false;
/* 104:    */   }
/* 105:    */   
/* 106:    */   protected double getFairyCallDistance()
/* 107:    */   {
/* 108:136 */     return 0.0D;
/* 109:    */   }
/* 110:    */   
/* 111:    */   protected Item getDropItem()
/* 112:    */   {
/* 113:142 */     if (getForm() < 0) {
/* 114:144 */       return null;
/* 115:    */     }
/* 116:148 */     return THKaguyaItems.point_item;
/* 117:    */   }
/* 118:    */   
/* 119:    */   protected void dropFewItems(boolean hasBeenAttackedByPlayer, int lootingLevel) {}
/* 120:    */   
/* 121:    */   protected String getLivingSound()
/* 122:    */   {
/* 123:163 */     return null;
/* 124:    */   }
/* 125:    */   
/* 126:    */   protected String getHurtSound()
/* 127:    */   {
/* 128:170 */     return null;
/* 129:    */   }
/* 130:    */   
/* 131:    */   protected String getDeathSound()
/* 132:    */   {
/* 133:177 */     return null;
/* 134:    */   }
/* 135:    */   
/* 136:    */   public double getMountedYOffset()
/* 137:    */   {
/* 138:183 */     return 0.5D;
/* 139:    */   }
/* 140:    */   
/* 141:    */   public AxisAlignedBB getCollisionBox(Entity entity)
/* 142:    */   {
/* 143:191 */     return null;
/* 144:    */   }
/* 145:    */   
/* 146:    */   public AxisAlignedBB getBoundingBox()
/* 147:    */   {
/* 148:198 */     return null;
/* 149:    */   }
/* 150:    */   
/* 151:    */   public boolean canBeCollidedWith()
/* 152:    */   {
/* 153:204 */     return false;
/* 154:    */   }
/* 155:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.living.EntityFamiliar
 * JD-Core Version:    0.7.0.1
 */