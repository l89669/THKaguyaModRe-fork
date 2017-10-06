/*  1:   */ package thKaguyaMod.entity.spellcard;
/*  2:   */ 
/*  3:   */ import net.minecraft.entity.Entity;
/*  4:   */ import net.minecraft.entity.EntityLivingBase;
/*  5:   */ import net.minecraft.util.MovingObjectPosition;
/*  6:   */ import net.minecraft.world.World;
/*  7:   */ import thKaguyaMod.THShotLib;
/*  8:   */ import thKaguyaMod.entity.shot.EntityTHShot;
/*  9:   */ import thKaguyaMod.entity.shot.ISpecialShot;
/* 10:   */ import thKaguyaMod.registry.SpecialShotRegistry;
/* 11:   */ 
/* 12:   */ public class THSC_IcicleFall
/* 13:   */   extends THSpellCard
/* 14:   */   implements ISpecialShot
/* 15:   */ {
/* 16:   */   public static final int SPECIAL_ICECLEFALL01 = 1900;
/* 17:   */   public static final int SPECIAL_ICECLEFALL02 = 1901;
/* 18:   */   
/* 19:   */   public THSC_IcicleFall()
/* 20:   */   {
/* 21:21 */     setNeedLevel(1);
/* 22:22 */     setRemoveTime(60);
/* 23:23 */     setEndTime(180);
/* 24:24 */     setOriginalUserName(4);
/* 25:   */     
/* 26:26 */     SpecialShotRegistry.registerSpecialShot(THSC_IcicleFall.class, 1900);
/* 27:27 */     SpecialShotRegistry.registerSpecialShot(THSC_IcicleFall.class, 1901);
/* 28:   */   }
/* 29:   */   
/* 30:   */   public void spellcard_main()
/* 31:   */   {
/* 32:33 */     if (this.time % 5 == 0)
/* 33:   */     {
/* 34:35 */       float angle = (float)Math.sin(this.time * 13.0F / 180.0F * 3.141593F) * (30.0F + this.level * 30.0F) + 90.0F;
/* 35:36 */       int num = 4 + this.level * 2;
/* 36:37 */       float damage = 6.0F;
/* 37:38 */       for (int i = 1; i < num; i++)
/* 38:   */       {
/* 39:40 */         THShotLib.createShot(this.user, this.user, pos_User(), getVecFromAngle(this.user.rotationYaw + angle, -30.0F), 0.0F, i * 1.0D, 0.0D, -i * 0.1D, gravity_Zero(), shot(11, 1, 0.5F, damage, 0, num, 1900)).rotate = THShotLib.rotate_Default();
/* 40:41 */         THShotLib.createShot(this.user, this.user, pos_User(), getVecFromAngle(this.user.rotationYaw - angle, -30.0F), 0.0F, i * 1.0D, 0.0D, -i * 0.1D, gravity_Zero(), shot(11, 1, 0.5F, damage, 0, num, 1901)).rotate = THShotLib.rotate_Default();
/* 41:   */       }
/* 42:   */     }
/* 43:   */   }
/* 44:   */   
/* 45:   */   public void specialShot_move(World world, int id, EntityTHShot shot)
/* 46:   */   {
/* 47:50 */     switch (id)
/* 48:   */     {
/* 49:   */     case 1900: 
/* 50:53 */       if (shot.ticksExisted >= shot.getDeadTime())
/* 51:   */       {
/* 52:55 */         shot.setShotRotationYaw(90.0F);
/* 53:56 */         shot.setDeadTime(120);
/* 54:57 */         shot.gravity.yCoord = -0.03D;
/* 55:58 */         shot.shotAcceleration = 0.01D;
/* 56:59 */         shot.shotLimitSpeed = 10.0D;
/* 57:60 */         shot.updateMotion();
/* 58:61 */         shot.specialEnd();
/* 59:   */       }
/* 60:   */       break;
/* 61:   */     case 1901: 
/* 62:65 */       if (shot.ticksExisted == shot.getDeadTime())
/* 63:   */       {
/* 64:67 */         shot.setShotRotationYaw(-90.0F);
/* 65:68 */         shot.setDeadTime(120);
/* 66:69 */         shot.gravity.yCoord = -0.03D;
/* 67:70 */         shot.shotAcceleration = 0.01D;
/* 68:71 */         shot.shotLimitSpeed = 10.0D;
/* 69:72 */         shot.updateMotion();
/* 70:73 */         shot.specialEnd();
/* 71:   */       }
/* 72:   */       break;
/* 73:   */     }
/* 74:   */   }
/* 75:   */   
/* 76:   */   public boolean specialShot_hitBlock(World world, int id, EntityTHShot shot, MovingObjectPosition movingObjectPosition)
/* 77:   */   {
/* 78:84 */     return false;
/* 79:   */   }
/* 80:   */   
/* 81:   */   public boolean specialShot_hitEntity(World world, int id, EntityTHShot shot, Entity entity_Hit)
/* 82:   */   {
/* 83:91 */     return false;
/* 84:   */   }
/* 85:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.spellcard.THSC_IcicleFall
 * JD-Core Version:    0.7.0.1
 */