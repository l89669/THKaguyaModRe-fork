/*  1:   */ package thKaguyaMod.entity.shot;
/*  2:   */ 
/*  3:   */ import java.util.Random;
/*  4:   */ import net.minecraft.entity.Entity;
/*  5:   */ import net.minecraft.entity.EntityLivingBase;
/*  6:   */ import net.minecraft.util.MovingObjectPosition;
/*  7:   */ import net.minecraft.util.Vec3;
/*  8:   */ import net.minecraft.world.World;
/*  9:   */ import thKaguyaMod.LaserData;
/* 10:   */ import thKaguyaMod.ShotData;
/* 11:   */ import thKaguyaMod.THShotLib;
/* 12:   */ 
/* 13:   */ public class EntityDragonNeckJewel
/* 14:   */   extends EntityTHShot
/* 15:   */ {
/* 16:   */   public float color_r;
/* 17:   */   public float color_g;
/* 18:   */   public float color_b;
/* 19:   */   
/* 20:   */   public EntityDragonNeckJewel(World world)
/* 21:   */   {
/* 22:22 */     super(world);
/* 23:   */   }
/* 24:   */   
/* 25:   */   public EntityDragonNeckJewel(World world, EntityLivingBase user, Vec3 angle, float cr, float cg, float cb)
/* 26:   */   {
/* 27:27 */     super(world, user, user, THShotLib.pos_Living(user), angle, 0.0F, THShotLib.rotate_Default(), 0.0F, 9999, 0.9D, 0.9D, 0.0D, THShotLib.gravity_Default(), ShotData.shot(0, 0, 1.0F, 7.0F));
/* 28:28 */     this.color_r = cr;
/* 29:29 */     this.color_g = cg;
/* 30:30 */     this.color_b = cb;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public EntityDragonNeckJewel(World world, EntityLivingBase user, Entity source, Vec3 pos, Vec3 angle, float cr, float cg, float cb)
/* 34:   */   {
/* 35:35 */     super(world, user, source, pos, angle, 0.0F, THShotLib.rotate_Default(), 0.0F, 9999, 0.9D, 0.9D, 0.0D, THShotLib.gravity_Default(), ShotData.shot(0, 0, 1.0F, 7.0F));
/* 36:36 */     this.color_r = cr;
/* 37:37 */     this.color_g = cg;
/* 38:38 */     this.color_b = cb;
/* 39:   */   }
/* 40:   */   
/* 41:   */   protected boolean onImpact(MovingObjectPosition movingObjectPosition)
/* 42:   */   {
/* 43:45 */     super.onImpact(movingObjectPosition);
/* 44:47 */     if (!createLightningBoltDanmaku(movingObjectPosition))
/* 45:   */     {
/* 46:49 */       delete();
/* 47:50 */       return true;
/* 48:   */     }
/* 49:53 */     return false;
/* 50:   */   }
/* 51:   */   
/* 52:   */   protected boolean createLightningBoltDanmaku(MovingObjectPosition movingObjectPosition)
/* 53:   */   {
/* 54:58 */     if ((movingObjectPosition.entityHit instanceof EntityTHShot)) {
/* 55:60 */       return true;
/* 56:   */     }
/* 57:63 */     int[] colors = { 0, 2, 4, 5, 6 };
/* 58:64 */     float angle = this.rand.nextFloat() * 360.0F;
/* 59:65 */     for (int i = 0; i < 5; i++)
/* 60:   */     {
/* 61:67 */       THShotLib.createLaserA(this.user, this, THShotLib.pos(this.posX, this.posY + 0.4D, this.posZ), THShotLib.angle(angle, -70.0F), 0.1D, 0.3D, 0.1D, THShotLib.gravity_Zero(), LaserData.laser(colors[i], 0.15F, 3.0F, 3.0F, 0, 40, 0));
/* 62:68 */       angle += 72.0F;
/* 63:   */     }
/* 64:70 */     for (int i = 0; i < 10; i++)
/* 65:   */     {
/* 66:72 */       double xVector = this.rand.nextGaussian() * 0.2D;
/* 67:73 */       double yVector = 0.8D + this.rand.nextDouble() * 0.2D;
/* 68:74 */       double zVector = this.rand.nextGaussian() * 0.2D;
/* 69:   */       
/* 70:76 */       ShotData shot = ShotData.shot(5, colors[this.rand.nextInt(5)], 0.3F, 3.0F, 1, 120);
/* 71:77 */       THShotLib.createShot(this.user, this, THShotLib.pos(this.posX, this.posY + 0.4D, this.posZ), THShotLib.angle(xVector, yVector, zVector), 0.0F, 0.4D, 0.4D, 0.0D, THShotLib.gravity(0.0D, -0.01D, 0.0D), shot);
/* 72:   */     }
/* 73:86 */     return false;
/* 74:   */   }
/* 75:   */   
/* 76:   */   public boolean blockHitSpecialProcess(MovingObjectPosition movingObjectPosition)
/* 77:   */   {
/* 78:92 */     return createLightningBoltDanmaku(movingObjectPosition);
/* 79:   */   }
/* 80:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.shot.EntityDragonNeckJewel
 * JD-Core Version:    0.7.0.1
 */