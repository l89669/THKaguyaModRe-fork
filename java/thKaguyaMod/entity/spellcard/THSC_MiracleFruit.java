/*   1:    */ package thKaguyaMod.entity.spellcard;
/*   2:    */ 
/*   3:    */ import java.util.Random;
/*   4:    */ import net.minecraft.entity.Entity;
/*   5:    */ import net.minecraft.entity.EntityLivingBase;
/*   6:    */ import net.minecraft.util.MovingObjectPosition;
/*   7:    */ import net.minecraft.util.Vec3;
/*   8:    */ import net.minecraft.world.World;
/*   9:    */ import thKaguyaMod.ShotData;
/*  10:    */ import thKaguyaMod.THShotLib;
/*  11:    */ import thKaguyaMod.entity.shot.EntityTHShot;
/*  12:    */ import thKaguyaMod.entity.shot.ISpecialShot;
/*  13:    */ import thKaguyaMod.registry.SpecialShotRegistry;
/*  14:    */ 
/*  15:    */ public class THSC_MiracleFruit
/*  16:    */   extends THSpellCard
/*  17:    */   implements ISpecialShot
/*  18:    */ {
/*  19:    */   public static final int SPECIAL_MIRACLE_FRUIT01 = 1400;
/*  20:    */   
/*  21:    */   public THSC_MiracleFruit()
/*  22:    */   {
/*  23: 24 */     setNeedLevel(1);
/*  24: 25 */     setRemoveTime(30);
/*  25: 26 */     setEndTime(60);
/*  26: 27 */     setOriginalUserName(41);
/*  27:    */     
/*  28: 29 */     SpecialShotRegistry.registerSpecialShot(THSC_MiracleFruit.class, 1400);
/*  29:    */   }
/*  30:    */   
/*  31:    */   public void spellcard_main()
/*  32:    */   {
/*  33: 35 */     if (this.time == 1) {
/*  34: 37 */       this.user.clearActivePotions();
/*  35:    */     }
/*  36: 39 */     if (this.time < 5) {
/*  37: 41 */       return;
/*  38:    */     }
/*  39: 44 */     if (this.time % 30 == 6)
/*  40:    */     {
/*  41: 46 */       Vec3 angle = THShotLib.getRotationVectorFromAngle(this.user.rotationYaw, this.user.rotationPitch, 0.0F, 1.0D);
/*  42: 47 */       THShotLib.playShotSound(this.user);
/*  43: 48 */       THShotLib.createCircleShot(this.user, this.user, pos_User(), angle, 2.0D, 0.1D, -0.2D, gravity_Zero(), shot(16, 0, THShotLib.SIZE[16], 7.0F, 3, 20, 1400), 8, 0.2D, this.rand.nextFloat() * 360.0F);
/*  44:    */     }
/*  45:    */   }
/*  46:    */   
/*  47:    */   public void specialShot_move(World world, int id, EntityTHShot shot)
/*  48:    */   {
/*  49: 54 */     switch (id)
/*  50:    */     {
/*  51:    */     case 1400: 
/*  52: 57 */       if (shot.ticksExisted == shot.getDeadTime())
/*  53:    */       {
/*  54: 59 */         float angle2 = this.rand.nextFloat() * 360.0F;
/*  55: 61 */         for (int j = 0; j < 8; j++)
/*  56:    */         {
/*  57: 63 */           Vec3 look = THShotLib.getVectorFromRotation(shot.rotate, shot.angle, angle2);
/*  58: 64 */           for (int i = 0; i < 6; i++) {
/*  59: 66 */             THShotLib.createShot(shot.user, shot, THShotLib.pos_Entity(shot), look, 0.0F, shot.rotate, 0.0F, 9999, 0.5D, 0.2D, 0.8D, gravity_Zero(), ShotData.shot(0, 0, 0.3F, 6.0F, 3 + i * 2, 90));
/*  60:    */           }
/*  61: 69 */           angle2 += 45.0F;
/*  62:    */         }
/*  63:    */       }
/*  64: 72 */       break;
/*  65:    */     }
/*  66:    */   }
/*  67:    */   
/*  68:    */   public boolean specialShot_hitBlock(World world, int id, EntityTHShot shot, MovingObjectPosition movingObjectPosition)
/*  69:    */   {
/*  70: 84 */     return false;
/*  71:    */   }
/*  72:    */   
/*  73:    */   public boolean specialShot_hitEntity(World world, int id, EntityTHShot shot, Entity entity_Hit)
/*  74:    */   {
/*  75: 90 */     switch (id)
/*  76:    */     {
/*  77:    */     case 1400: 
/*  78: 93 */       if ((entity_Hit instanceof EntityLivingBase))
/*  79:    */       {
/*  80: 95 */         EntityLivingBase living = (EntityLivingBase)entity_Hit;
/*  81: 96 */         living.clearActivePotions();
/*  82:    */       }
/*  83: 98 */       return false;
/*  84:    */     }
/*  85:100 */     return false;
/*  86:    */   }
/*  87:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.spellcard.THSC_MiracleFruit
 * JD-Core Version:    0.7.0.1
 */