/*  1:   */ package thKaguyaMod.entity.spellcard;
/*  2:   */ 
/*  3:   */ import java.util.Random;
/*  4:   */ import net.minecraft.entity.Entity;
/*  5:   */ import net.minecraft.entity.EntityLivingBase;
/*  6:   */ import net.minecraft.util.MovingObjectPosition;
/*  7:   */ import net.minecraft.util.Vec3;
/*  8:   */ import net.minecraft.world.World;
/*  9:   */ import thKaguyaMod.ShotData;
/* 10:   */ import thKaguyaMod.THShotLib;
/* 11:   */ import thKaguyaMod.entity.shot.EntityTHShot;
/* 12:   */ import thKaguyaMod.entity.shot.ISpecialShot;
/* 13:   */ import thKaguyaMod.registry.SpecialShotRegistry;
/* 14:   */ 
/* 15:   */ public class THSC_StarbowBreak
/* 16:   */   extends THSpellCard
/* 17:   */   implements ISpecialShot
/* 18:   */ {
/* 19:   */   public static final int SPECIAL_STARBOW_BRAKE01 = 2000;
/* 20:   */   
/* 21:   */   public THSC_StarbowBreak()
/* 22:   */   {
/* 23:23 */     setNeedLevel(3);
/* 24:24 */     setRemoveTime(40);
/* 25:25 */     setEndTime(150);
/* 26:26 */     setOriginalUserName(10);
/* 27:   */     
/* 28:28 */     SpecialShotRegistry.registerSpecialShot(THSC_StarbowBreak.class, 2000);
/* 29:   */   }
/* 30:   */   
/* 31:   */   public void spellcard_main()
/* 32:   */   {
/* 33:34 */     if ((this.time % 60 >= 3) && (this.time % 60 <= 23) && (this.time % 3 == 0) && (this.time < 119))
/* 34:   */     {
/* 35:37 */       int[] rainbowPattern = { 4, 1, 5, 2, 3, 6, 0 };
/* 36:   */       
/* 37:39 */       Vec3 look = this.user.getLookVec();
/* 38:40 */       if (!this.target.isDead) {
/* 39:42 */         look = THShotLib.angle_ToPos(pos_User(), pos_Target());
/* 40:   */       }
/* 41:44 */       Vec3 side = THShotLib.getVecFromAngle(this.user.rotationYaw + 90.0F, 0.0F, 0.300000011920929D);
/* 42:   */       
/* 43:46 */       int num = this.time % 60;
/* 44:47 */       float span = 360.0F / num;
/* 45:   */       
/* 46:49 */       Vec3 gravity = THShotLib.gravity(look.xCoord * 0.13D, look.yCoord * 0.13D, look.zCoord * 0.13D);
/* 47:50 */       int color = rainbowPattern[(num / 3 - 1)];
/* 48:51 */       THShotLib.createRingShot(this.user, this.user, pos(this.user.posX + gravity.xCoord * num, THShotLib.getPosYFromEye(this.user, -0.2D) + gravity.yCoord * num, this.user.posZ + gravity.zCoord * num), look, 0.0F, 9999, 0.1D, 4.0D, 0.01D, gravity, 
/* 49:52 */         shot(5, color, 0.3F, 8.0F, 5, 8, 2000), num * 2, num / 5.0F, 180.0F, this.rand.nextFloat() * 360.0F);
/* 50:   */     }
/* 51:   */   }
/* 52:   */   
/* 53:   */   public void specialShot_move(World world, int id, EntityTHShot shot)
/* 54:   */   {
/* 55:58 */     switch (id)
/* 56:   */     {
/* 57:   */     case 2000: 
/* 58:61 */       if (shot.isEndTime())
/* 59:   */       {
/* 60:63 */         shot.rotationYaw = (-shot.rotationYaw + this.rand.nextFloat() * 14.0F - 7.0F);
/* 61:64 */         shot.rotationPitch = (-shot.rotationPitch + this.rand.nextFloat() * 14.0F - 7.0F);
/* 62:65 */         Vec3 vec = THShotLib.getVecFromAngle(shot.rotationYaw, shot.rotationPitch, 1.0D);
/* 63:66 */         double gravityLevel = this.rand.nextDouble() * 0.1D + 0.02D;
/* 64:67 */         ShotData shotData = ShotData.shot(shot.getShotForm(), shot.getShotColor(), shot.getShotSize(), shot.shotDamage, 10, 90);
/* 65:68 */         THShotLib.createShot(shot.user, shot, THShotLib.pos_Entity(shot), angle(shot.rotationYaw, shot.rotationPitch), 0.0F, -0.3D, 0.7D, this.rand.nextDouble() * 0.03D, THShotLib.gravity(shot.gravity.xCoord * gravityLevel, shot.gravity.yCoord * gravityLevel, shot.gravity.zCoord * gravityLevel), shotData);
/* 66:   */       }
/* 67:70 */       break;
/* 68:   */     }
/* 69:   */   }
/* 70:   */   
/* 71:   */   public boolean specialShot_hitBlock(World world, int id, EntityTHShot shot, MovingObjectPosition movingObjectPosition)
/* 72:   */   {
/* 73:82 */     return false;
/* 74:   */   }
/* 75:   */   
/* 76:   */   public boolean specialShot_hitEntity(World world, int id, EntityTHShot shot, Entity entity_Hit)
/* 77:   */   {
/* 78:89 */     return false;
/* 79:   */   }
/* 80:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.spellcard.THSC_StarbowBreak
 * JD-Core Version:    0.7.0.1
 */