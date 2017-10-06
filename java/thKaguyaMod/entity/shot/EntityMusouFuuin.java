/*   1:    */ package thKaguyaMod.entity.shot;
/*   2:    */ 
/*   3:    */ import net.minecraft.entity.Entity;
/*   4:    */ import net.minecraft.entity.EntityLivingBase;
/*   5:    */ import net.minecraft.util.Vec3;
/*   6:    */ import net.minecraft.world.World;
/*   7:    */ import thKaguyaMod.THShotLib;
/*   8:    */ 
/*   9:    */ public class EntityMusouFuuin
/*  10:    */   extends EntityTHShot
/*  11:    */ {
/*  12:    */   public EntityLivingBase target;
/*  13:    */   private float maxSize;
/*  14:    */   
/*  15:    */   public EntityMusouFuuin(World world)
/*  16:    */   {
/*  17: 19 */     super(world);
/*  18:    */   }
/*  19:    */   
/*  20:    */   public EntityMusouFuuin(World world, EntityLivingBase user, Entity source, Vec3 pos, Vec3 angle, float slope, Vec3 rotate, float rotationSpeed, int rotationEnd, double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity, float size, float damage, EntityLivingBase target)
/*  21:    */   {
/*  22: 26 */     super(world, user, source, pos, angle, slope, rotate, rotationSpeed, rotationEnd, firstSpeed, limitSpeed, acceleration, gravity, 0, size, damage, 0, 40, 0);
/*  23: 27 */     this.maxSize = size;
/*  24: 28 */     setShotSize(0.1F);
/*  25: 29 */     this.target = target;
/*  26:    */   }
/*  27:    */   
/*  28:    */   public void specialMotion()
/*  29:    */   {
/*  30: 38 */     if (getShotSize() < this.maxSize) {
/*  31: 40 */       setShotSize(getShotSize() + 0.1F);
/*  32:    */     }
/*  33: 46 */     if (this.ticksExisted < 15)
/*  34:    */     {
/*  35: 48 */       this.motionX *= 0.8D;
/*  36: 49 */       this.motionY *= 0.8D;
/*  37: 50 */       this.motionZ *= 0.8D;
/*  38:    */     }
/*  39: 54 */     else if (this.target != null)
/*  40:    */     {
/*  41: 56 */       if (this.target.isDead)
/*  42:    */       {
/*  43: 58 */         this.shotLimitSpeed = 0.6D;
/*  44: 59 */         this.shotAcceleration = 0.03D;
/*  45:    */         
/*  46: 61 */         setVector();
/*  47: 62 */         this.target = null;
/*  48: 63 */         return;
/*  49:    */       }
/*  50: 65 */       this.shotLimitSpeed = 0.6D;
/*  51:    */       
/*  52:    */ 
/*  53: 68 */       this.shotAcceleration = 0.03D;
/*  54:    */       
/*  55:    */ 
/*  56:    */ 
/*  57: 72 */       Vec3 targetVec = Vec3.createVectorHelper(this.target.posX - this.posX, this.target.posY + this.target.getEyeHeight() - this.posY, this.target.posZ - this.posZ);
/*  58: 73 */       targetVec = THShotLib.getVectorNomalize(targetVec);
/*  59: 74 */       Vec3 rotate = THShotLib.getOuterProduct(getShotVector(), targetVec);
/*  60: 75 */       float rotateAngle = THShotLib.getVectorAndVectorAngle(getShotVector(), targetVec);
/*  61: 76 */       float homingLevel = 12.0F;
/*  62: 78 */       if (rotateAngle > homingLevel) {
/*  63: 80 */         rotateAngle = homingLevel;
/*  64: 82 */       } else if (rotateAngle < -homingLevel) {
/*  65: 84 */         rotateAngle = -homingLevel;
/*  66:    */       }
/*  67: 86 */       Vec3 newVec = THShotLib.getVectorFromRotation(rotate, this.angle, rotateAngle);
/*  68: 87 */       this.angle = newVec;
/*  69: 88 */       if (!this.worldObj.isRemote) {
/*  70: 91 */         shotAcceleration();
/*  71:    */       }
/*  72: 97 */       updateYawAndPitch();
/*  73:    */     }
/*  74:    */     else
/*  75:    */     {
/*  76:101 */       this.shotLimitSpeed = 0.6D;
/*  77:102 */       this.shotAcceleration = 0.03D;
/*  78:    */     }
/*  79:    */   }
/*  80:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.shot.EntityMusouFuuin
 * JD-Core Version:    0.7.0.1
 */