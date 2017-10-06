/*   1:    */ package thKaguyaMod.entity.shot;
/*   2:    */ 
/*   3:    */ import net.minecraft.entity.Entity;
/*   4:    */ import net.minecraft.entity.EntityLivingBase;
/*   5:    */ import net.minecraft.util.MathHelper;
/*   6:    */ import net.minecraft.util.Vec3;
/*   7:    */ import net.minecraft.world.World;
/*   8:    */ 
/*   9:    */ public class EntitySanaeWind
/*  10:    */   extends EntityTHShot
/*  11:    */ {
/*  12:    */   public int type;
/*  13:    */   public int kamikaze;
/*  14:    */   private double lastPlayerPosX;
/*  15:    */   private double lastPlayerPosY;
/*  16:    */   private double lastPlayerPosZ;
/*  17:    */   
/*  18:    */   public EntitySanaeWind(World world)
/*  19:    */   {
/*  20: 24 */     super(world);
/*  21:    */   }
/*  22:    */   
/*  23:    */   public EntitySanaeWind(World world, EntityLivingBase user, Entity source, Vec3 pos, Vec3 angle, float slope, Vec3 rotate, float rotationSpeed, int rotationEnd, double firstSpeed, double limitSpeed, double acceleration, Vec3 gravity, int kazetype, float size, float damage, int delay, int end, int special)
/*  24:    */   {
/*  25: 33 */     super(world, user, source, pos, angle, slope, rotate, rotationSpeed, rotationEnd, firstSpeed, limitSpeed, acceleration, gravity, kazetype, size, damage, delay, end, special);
/*  26:    */     
/*  27:    */ 
/*  28:    */ 
/*  29: 37 */     this.lastPlayerPosX = user.posX;
/*  30: 38 */     this.lastPlayerPosY = user.posY;
/*  31: 39 */     this.lastPlayerPosZ = user.posZ;
/*  32:    */   }
/*  33:    */   
/*  34:    */   public void onUpdate()
/*  35:    */   {
/*  36: 46 */     if (this.user != null)
/*  37:    */     {
/*  38: 48 */       double userMotionX = this.user.posX - this.lastPlayerPosX;
/*  39: 49 */       double userMotionY = this.user.posY - this.lastPlayerPosY;
/*  40: 50 */       double userMotionZ = this.user.posZ - this.lastPlayerPosZ;
/*  41: 52 */       if (getShotColor() == 1)
/*  42:    */       {
/*  43: 54 */         this.rotationYaw += 2.0F;
/*  44: 55 */         this.shotSpeed = MathHelper.sqrt_double(userMotionX * userMotionX + userMotionY * userMotionY + userMotionZ * userMotionZ);
/*  45: 56 */         setVector();
/*  46:    */       }
/*  47:    */       else
/*  48:    */       {
/*  49: 60 */         this.gravity.xCoord = (userMotionX * 0.2D);
/*  50: 61 */         this.gravity.yCoord = (userMotionY * 0.2D);
/*  51: 62 */         this.gravity.zCoord = (userMotionZ * 0.2D);
/*  52: 64 */         if (this.gravity.yCoord < 0.0D) {
/*  53: 66 */           this.gravity.yCoord = 0.0D;
/*  54:    */         }
/*  55: 68 */         if (this.user.isSneaking()) {
/*  56: 70 */           this.gravity.yCoord = -0.1D;
/*  57:    */         }
/*  58:    */       }
/*  59:    */     }
/*  60: 74 */     super.onUpdate();
/*  61: 76 */     if (this.user != null)
/*  62:    */     {
/*  63: 78 */       this.lastPlayerPosX = this.user.posX;
/*  64: 79 */       this.lastPlayerPosY = this.user.posY;
/*  65: 80 */       this.lastPlayerPosZ = this.user.posZ;
/*  66:    */     }
/*  67:    */   }
/*  68:    */   
/*  69:    */   public int getWindRenderPattern()
/*  70:    */   {
/*  71:366 */     return this.ticksExisted % 4;
/*  72:    */   }
/*  73:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.shot.EntitySanaeWind
 * JD-Core Version:    0.7.0.1
 */