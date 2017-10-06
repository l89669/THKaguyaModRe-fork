/*   1:    */ package thKaguyaMod.entity.spellcard;
/*   2:    */ 
/*   3:    */ import java.util.Calendar;
/*   4:    */ import java.util.Random;
/*   5:    */ import net.minecraft.entity.Entity;
/*   6:    */ import net.minecraft.entity.EntityLivingBase;
/*   7:    */ import net.minecraft.entity.item.EntityItem;
/*   8:    */ import net.minecraft.entity.monster.EntityCreeper;
/*   9:    */ import net.minecraft.entity.passive.EntityChicken;
/*  10:    */ import net.minecraft.init.Items;
/*  11:    */ import net.minecraft.item.ItemStack;
/*  12:    */ import net.minecraft.util.MovingObjectPosition;
/*  13:    */ import net.minecraft.util.Vec3;
/*  14:    */ import net.minecraft.world.World;
/*  15:    */ import thKaguyaMod.ShotData;
/*  16:    */ import thKaguyaMod.THShotLib;
/*  17:    */ import thKaguyaMod.entity.living.EntityDanmakuCreeper;
/*  18:    */ import thKaguyaMod.entity.shot.EntityNuclearShot;
/*  19:    */ import thKaguyaMod.entity.shot.EntityTHShot;
/*  20:    */ import thKaguyaMod.entity.shot.ISpecialShot;
/*  21:    */ import thKaguyaMod.registry.SpecialShotRegistry;
/*  22:    */ 
/*  23:    */ public class THSC_Fafurotskies_no_Kiseki
/*  24:    */   extends THSpellCard
/*  25:    */   implements ISpecialShot
/*  26:    */ {
/*  27:    */   public static final int SPECIAL_FAFUROTSKIES01 = 1500;
/*  28:    */   
/*  29:    */   public THSC_Fafurotskies_no_Kiseki()
/*  30:    */   {
/*  31: 33 */     setNeedLevel(1);
/*  32: 34 */     setRemoveTime(20);
/*  33: 35 */     setEndTime(40);
/*  34: 36 */     setOriginalUserName(41);
/*  35:    */     
/*  36: 38 */     SpecialShotRegistry.registerSpecialShot(THSC_Fafurotskies_no_Kiseki.class, 1500);
/*  37:    */   }
/*  38:    */   
/*  39:    */   public void spellcard_main()
/*  40:    */   {
/*  41: 44 */     if (this.time == 1)
/*  42:    */     {
/*  43: 46 */       int pattern = this.rand.nextInt(100);
/*  44: 47 */       for (int i = 0; i < 10; i++)
/*  45:    */       {
/*  46: 49 */         Vec3 angle = getVecFromAngle(this.user.rotationYaw + this.rand.nextFloat() * 50.0F - 25.0F, this.rand.nextFloat() * 20.0F - 69.0F);
/*  47: 50 */         Vec3 angle2 = angle(this.user.rotationYaw, 0.0F);
/*  48:    */         
/*  49: 52 */         THShotLib.createShot(this.user, this.user, THShotLib.pos_Distance(pos_User(), angle2, 0.5D), angle, 0.0F, 1.2D, pattern, 0.0D, gravity_Default(), shot(4, 5, 0.4F, 6.0F, i, 20, 1500));
/*  50:    */       }
/*  51: 54 */       THShotLib.playShotSound(this.user);
/*  52:    */     }
/*  53:    */   }
/*  54:    */   
/*  55:    */   public void specialShot_move(World world, int id, EntityTHShot shot)
/*  56:    */   {
/*  57: 60 */     switch (id)
/*  58:    */     {
/*  59:    */     case 1500: 
/*  60: 63 */       if (shot.ticksExisted == shot.getDeadTime())
/*  61:    */       {
/*  62: 65 */         int pattern = (int)shot.shotLimitSpeed;
/*  63:    */         
/*  64: 67 */         Calendar calendar = world.getCurrentDate();
/*  65: 69 */         if ((calendar.get(2) + 1 == 12) && (calendar.get(5) == 25))
/*  66:    */         {
/*  67: 71 */           EntityDanmakuCreeper entityCreeper = new EntityDanmakuCreeper(world);
/*  68: 72 */           entityCreeper.setLocationAndAngles((int)shot.posX, (int)shot.posY, (int)shot.posZ, this.rand.nextFloat() * 360.0F, 0.0F);
/*  69: 73 */           if (!world.isRemote)
/*  70:    */           {
/*  71: 76 */             world.spawnEntityInWorld(entityCreeper);
/*  72: 77 */             shot.setDead();
/*  73:    */           }
/*  74: 79 */           return;
/*  75:    */         }
/*  76: 82 */         if ((calendar.get(2) + 1 == 1) && (calendar.get(5) >= 1) && (calendar.get(5) <= 3))
/*  77:    */         {
/*  78: 84 */           if (!world.isRemote)
/*  79:    */           {
/*  80: 87 */             EntityNuclearShot nuclearShot = new EntityNuclearShot(world, shot.user, shot.user, pos(shot.posX, shot.posY, shot.posZ), THShotLib.angle_Random(), 0.0F, rotate_Default(), 0.0F, 9999, 0.2D, 0.8D, 0.0D, gravity_Default(), ShotData.shot(0, this.rand.nextInt(7), 3.2F, 10.0F, 0, 70, 0));
/*  81: 88 */             if (!world.isRemote) {
/*  82: 90 */               world.spawnEntityInWorld(nuclearShot);
/*  83:    */             }
/*  84: 92 */             nuclearShot.shootingFlag = true;
/*  85: 93 */             shot.setDead();
/*  86:    */           }
/*  87: 95 */           return;
/*  88:    */         }
/*  89: 98 */         if (pattern <= 70)
/*  90:    */         {
/*  91:100 */           for (int i = 0; i < 8; i++)
/*  92:    */           {
/*  93:102 */             ShotData shotData = ShotData.shot(28, 8, 0.6F, 10.0F, 3, 120);
/*  94:103 */             THShotLib.createShot(shot.user, shot, THShotLib.pos_Entity(shot), angle(this.rand.nextFloat() * 360.0F, this.rand.nextFloat() * 180.0F - 90.0F), 0.0F, 0.6D, 0.6D, 0.0D, gravity_Default(), shotData);
/*  95:    */           }
/*  96:105 */           if (!world.isRemote) {
/*  97:107 */             shot.setDead();
/*  98:    */           }
/*  99:    */         }
/* 100:110 */         else if (pattern <= 80)
/* 101:    */         {
/* 102:112 */           if (!world.isRemote)
/* 103:    */           {
/* 104:114 */             world.spawnEntityInWorld(new EntityItem(world, shot.posX, shot.posY, shot.posZ, new ItemStack(Items.fish)));
/* 105:115 */             shot.setDead();
/* 106:    */           }
/* 107:    */         }
/* 108:118 */         else if (pattern <= 90)
/* 109:    */         {
/* 110:120 */           EntityCreeper entityCreeper = new EntityCreeper(world);
/* 111:121 */           entityCreeper.setLocationAndAngles((int)shot.posX, (int)shot.posY, (int)shot.posZ, this.rand.nextFloat() * 360.0F, 0.0F);
/* 112:122 */           if (!world.isRemote)
/* 113:    */           {
/* 114:124 */             world.spawnEntityInWorld(entityCreeper);
/* 115:125 */             shot.setDead();
/* 116:    */           }
/* 117:    */         }
/* 118:    */         else
/* 119:    */         {
/* 120:130 */           EntityChicken entitychicken = new EntityChicken(world);
/* 121:131 */           entitychicken.setLocationAndAngles((int)shot.posX, (int)shot.posY, (int)shot.posZ, this.rand.nextFloat() * 360.0F, 0.0F);
/* 122:132 */           if (!world.isRemote)
/* 123:    */           {
/* 124:134 */             world.spawnEntityInWorld(entitychicken);
/* 125:135 */             shot.setDead();
/* 126:    */           }
/* 127:    */         }
/* 128:    */       }
/* 129:138 */       break;
/* 130:    */     }
/* 131:    */   }
/* 132:    */   
/* 133:    */   public boolean specialShot_hitBlock(World world, int id, EntityTHShot shot, MovingObjectPosition movingObjectPosition)
/* 134:    */   {
/* 135:149 */     return false;
/* 136:    */   }
/* 137:    */   
/* 138:    */   public boolean specialShot_hitEntity(World world, int id, EntityTHShot shot, Entity entity_Hit)
/* 139:    */   {
/* 140:156 */     return false;
/* 141:    */   }
/* 142:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.spellcard.THSC_Fafurotskies_no_Kiseki
 * JD-Core Version:    0.7.0.1
 */