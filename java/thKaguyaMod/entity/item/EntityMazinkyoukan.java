/*   1:    */ package thKaguyaMod.entity.item;
/*   2:    */ 
/*   3:    */ import net.minecraft.entity.Entity;
/*   4:    */ import net.minecraft.entity.player.EntityPlayer;
/*   5:    */ import net.minecraft.nbt.NBTTagCompound;
/*   6:    */ import net.minecraft.potion.PotionEffect;
/*   7:    */ import net.minecraft.world.World;
/*   8:    */ import thKaguyaMod.THKaguyaLib;
/*   9:    */ 
/*  10:    */ public class EntityMazinkyoukan
/*  11:    */   extends Entity
/*  12:    */ {
/*  13:    */   public EntityPlayer user;
/*  14:    */   public int count;
/*  15:    */   public float turnAngle;
/*  16:    */   public int befUseCount;
/*  17:    */   
/*  18:    */   public EntityMazinkyoukan(World world)
/*  19:    */   {
/*  20: 22 */     super(world);
/*  21: 23 */     setSize(2.0F, 2.0F);
/*  22:    */   }
/*  23:    */   
/*  24:    */   public EntityMazinkyoukan(World world, EntityPlayer player)
/*  25:    */   {
/*  26: 29 */     this(world);
/*  27: 30 */     this.user = player;
/*  28: 31 */     this.prevPosX = player.posX;
/*  29: 32 */     this.prevPosY = player.posY;
/*  30: 33 */     this.prevPosZ = player.posZ;
/*  31: 34 */     setPosition(player.posX, player.posY + player.getEyeHeight(), player.posZ);
/*  32: 35 */     setRotation(player.rotationYaw, 0.0F);
/*  33: 36 */     this.count = 0;
/*  34: 37 */     this.befUseCount = 0;
/*  35: 38 */     this.turnAngle = 0.0F;
/*  36:    */   }
/*  37:    */   
/*  38:    */   protected void entityInit() {}
/*  39:    */   
/*  40:    */   public boolean canBeCollidedWith()
/*  41:    */   {
/*  42: 51 */     return false;
/*  43:    */   }
/*  44:    */   
/*  45:    */   public void onUpdate()
/*  46:    */   {
/*  47: 61 */     if ((!this.worldObj.isRemote) && ((this.user == null) || (this.user.isDead)))
/*  48:    */     {
/*  49: 63 */       setDead();
/*  50: 64 */       return;
/*  51:    */     }
/*  52: 66 */     super.onUpdate();
/*  53: 69 */     if (this.user != null) {
/*  54: 71 */       if (!this.user.isUsingItem())
/*  55:    */       {
/*  56: 73 */         int time = this.count;
/*  57: 74 */         time /= 3;
/*  58: 77 */         if (!this.worldObj.isRemote)
/*  59:    */         {
/*  60: 79 */           int removeLevel = (int)(time / 8.0F) + 1;
/*  61: 81 */           if (this.user.experienceLevel - removeLevel < 0) {
/*  62: 83 */             time = (int)(time + (this.user.experienceLevel - removeLevel) * 8.0F);
/*  63:    */           }
/*  64: 85 */           this.user.addExperienceLevel(-removeLevel);
/*  65: 86 */           this.user.addPotionEffect(new PotionEffect(1, time * 10, 4));
/*  66: 87 */           this.user.addPotionEffect(new PotionEffect(5, time * 10, 0));
/*  67: 88 */           this.user.addPotionEffect(new PotionEffect(8, time * 10, 1));
/*  68: 89 */           this.user.addPotionEffect(new PotionEffect(11, time * 10, 0));
/*  69:    */         }
/*  70: 95 */         if (!this.worldObj.isRemote) {
/*  71: 97 */           setDead();
/*  72:    */         }
/*  73:    */       }
/*  74:    */       else
/*  75:    */       {
/*  76:102 */         THKaguyaLib.itemEffectFollowUser(this, this.user, 0.5D, 0.0F);
/*  77:103 */         this.rotationYaw = this.user.rotationYaw;
/*  78:104 */         this.rotationPitch = 0.0F;
/*  79:    */       }
/*  80:    */     }
/*  81:108 */     this.count += 1;
/*  82:109 */     if (this.count > 114) {
/*  83:111 */       this.count = 114;
/*  84:113 */     } else if (this.count > 50) {
/*  85:115 */       this.turnAngle += 2.88F;
/*  86:    */     }
/*  87:119 */     if (this.rotationYaw > 180.0F) {
/*  88:119 */       this.rotationYaw -= 360.0F;
/*  89:    */     }
/*  90:120 */     if (this.rotationYaw < -180.0F) {
/*  91:120 */       this.rotationYaw += 360.0F;
/*  92:    */     }
/*  93:121 */     if (this.rotationPitch > 180.0F) {
/*  94:121 */       this.rotationPitch -= 360.0F;
/*  95:    */     }
/*  96:122 */     if (this.rotationPitch < -180.0F) {
/*  97:122 */       this.rotationPitch += 360.0F;
/*  98:    */     }
/*  99:    */   }
/* 100:    */   
/* 101:    */   protected void writeEntityToNBT(NBTTagCompound nbtTagCompound) {}
/* 102:    */   
/* 103:    */   protected void readEntityFromNBT(NBTTagCompound nbtTagCompound) {}
/* 104:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.item.EntityMazinkyoukan
 * JD-Core Version:    0.7.0.1
 */