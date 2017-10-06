/*   1:    */ package thKaguyaMod.entity.living;
/*   2:    */ 
/*   3:    */ import java.util.Random;
/*   4:    */ import net.minecraft.entity.item.EntityXPOrb;
/*   5:    */ import net.minecraft.entity.monster.EntityCreeper;
/*   6:    */ import net.minecraft.nbt.NBTTagCompound;
/*   7:    */ import net.minecraft.util.DamageSource;
/*   8:    */ import net.minecraft.util.Vec3;
/*   9:    */ import net.minecraft.world.GameRules;
/*  10:    */ import net.minecraft.world.World;
/*  11:    */ import thKaguyaMod.ShotData;
/*  12:    */ import thKaguyaMod.THShotLib;
/*  13:    */ import thKaguyaMod.init.THKaguyaConfig;
/*  14:    */ 
/*  15:    */ public class EntityDanmakuCreeper
/*  16:    */   extends EntityCreeper
/*  17:    */ {
/*  18:    */   private int lastActiveTime;
/*  19:    */   private int timeSinceIgnited2;
/*  20: 27 */   private int fuseTime2 = 30;
/*  21: 30 */   private int explosionRadius = 3;
/*  22:    */   
/*  23:    */   public EntityDanmakuCreeper(World par1World)
/*  24:    */   {
/*  25: 34 */     super(par1World);
/*  26:    */   }
/*  27:    */   
/*  28:    */   protected void onDeathUpdate()
/*  29:    */   {
/*  30: 41 */     this.deathTime += 1;
/*  31: 43 */     if (this.deathTime == 40)
/*  32:    */     {
/*  33: 47 */       if ((!this.worldObj.isRemote) && ((this.recentlyHit > 0) || (isPlayer())) && (!isChild()) && (this.worldObj.getGameRules().getGameRuleBooleanValue("doMobLoot")))
/*  34:    */       {
/*  35: 49 */         int i = getExperiencePoints(this.attackingPlayer);
/*  36: 51 */         while (i > 0)
/*  37:    */         {
/*  38: 53 */           int j = EntityXPOrb.getXPSplit(i);
/*  39: 54 */           i -= j;
/*  40: 55 */           this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, j));
/*  41:    */         }
/*  42:    */       }
/*  43: 59 */       setDead();
/*  44: 61 */       for (int i = 0; i < 20; i++)
/*  45:    */       {
/*  46: 63 */         double d0 = this.rand.nextGaussian() * 0.02D;
/*  47: 64 */         double d1 = this.rand.nextGaussian() * 0.02D;
/*  48: 65 */         double d2 = this.rand.nextGaussian() * 0.02D;
/*  49: 66 */         this.worldObj.spawnParticle("explode", this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width, this.posY + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width, d0, d1, d2);
/*  50:    */       }
/*  51:    */     }
/*  52:    */   }
/*  53:    */   
/*  54:    */   public void onUpdate()
/*  55:    */   {
/*  56: 75 */     boolean danmakuFlag = false;
/*  57: 78 */     if (isEntityAlive())
/*  58:    */     {
/*  59: 94 */       this.timeSinceIgnited2 += getCreeperState();
/*  60: 96 */       if (this.timeSinceIgnited2 >= this.fuseTime2)
/*  61:    */       {
/*  62: 98 */         this.timeSinceIgnited2 = -300;
/*  63:101 */         if (!this.worldObj.isRemote)
/*  64:    */         {
/*  65:103 */           boolean flag = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
/*  66:104 */           float damage = 5.0F;
/*  67:105 */           int level = THKaguyaConfig.danmakuLevel;
/*  68:106 */           float size = 0.1F + level * 0.1F;
/*  69:107 */           int[] ways = { 8, 12, 20, 32, 44 };
/*  70:108 */           int way = ways[level];
/*  71:110 */           if (getPowered())
/*  72:    */           {
/*  73:112 */             Vec3 angle = THShotLib.angle_Random();
/*  74:113 */             ShotData shot = ShotData.shot(5, 2, size, damage * 2.0F);
/*  75:114 */             THShotLib.createSphereShot(this, this, THShotLib.pos_Living(this), angle, 0.0F, 1.2D, 0.01D, 0.0D, THShotLib.gravity(0.0D, -0.02D, 0.0D), shot, way, 0.0D, 0.0F);
/*  76:115 */             shot.color = 0;
/*  77:116 */             THShotLib.createSphereShot(this, this, THShotLib.pos_Living(this), angle, 0.0F, 0.5D, 0.01D, 0.0D, THShotLib.gravity(0.0D, -0.02D, 0.0D), shot, way, 0.0D, 0.0F);
/*  78:117 */             shot.color = 3;
/*  79:118 */             THShotLib.createSphereShot(this, this, THShotLib.pos_Living(this), angle, 0.0F, 0.4D, 0.01D, 0.0D, THShotLib.gravity(0.0D, -0.02D, 0.0D), shot, way, 0.0D, 0.0F);
/*  80:    */           }
/*  81:    */           else
/*  82:    */           {
/*  83:123 */             Vec3 angle = THShotLib.angle_Random();
/*  84:124 */             ShotData shot = ShotData.shot(5, 2, size, damage);
/*  85:125 */             THShotLib.createSphereShot(this, this, THShotLib.pos_Living(this), angle, 0.0F, 0.6D, 0.01D, 0.0D, THShotLib.gravity(0.0D, -0.02D, 0.0D), shot, way, 0.0D, 0.0F);
/*  86:126 */             shot.color = 0;
/*  87:127 */             THShotLib.createSphereShot(this, this, THShotLib.pos_Living(this), angle, 0.0F, 0.5D, 0.01D, 0.0D, THShotLib.gravity(0.0D, -0.02D, 0.0D), shot, way, 0.0D, 0.0F);
/*  88:128 */             shot.color = 3;
/*  89:129 */             THShotLib.createSphereShot(this, this, THShotLib.pos_Living(this), angle, 0.0F, 0.4D, 0.01D, 0.0D, THShotLib.gravity(0.0D, -0.02D, 0.0D), shot, way, 0.0D, 0.0F);
/*  90:    */           }
/*  91:132 */           this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "random.explode", 4.0F, (1.0F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F) * 0.7F);
/*  92:    */         }
/*  93:139 */         danmakuFlag = true;
/*  94:    */       }
/*  95:    */     }
/*  96:147 */     if ((this.timeSinceIgnited2 >= -100) && (this.timeSinceIgnited2 < 0)) {
/*  97:149 */       this.timeSinceIgnited2 = 0;
/*  98:    */     }
/*  99:152 */     if ((this.timeSinceIgnited2 < -100) && (this.timeSinceIgnited2 > -280)) {
/* 100:154 */       if (getHealth() > 0.0F) {
/* 101:157 */         onDeathUpdate();
/* 102:    */       }
/* 103:    */     }
/* 104:162 */     if ((this.timeSinceIgnited2 < 20) && (this.timeSinceIgnited2 >= 0)) {
/* 105:164 */       super.onUpdate();
/* 106:    */     }
/* 107:    */   }
/* 108:    */   
/* 109:    */   public void onDeath(DamageSource damageSource)
/* 110:    */   {
/* 111:179 */     super.onDeath(damageSource);
/* 112:    */   }
/* 113:    */   
/* 114:    */   public void writeEntityToNBT(NBTTagCompound nbtTagCompound)
/* 115:    */   {
/* 116:195 */     super.writeEntityToNBT(nbtTagCompound);
/* 117:    */     
/* 118:197 */     nbtTagCompound.setShort("Fuse", (short)200);
/* 119:    */   }
/* 120:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.living.EntityDanmakuCreeper
 * JD-Core Version:    0.7.0.1
 */