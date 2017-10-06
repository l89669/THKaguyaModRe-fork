/*   1:    */ package thKaguyaMod.entity.shot;
/*   2:    */ 
/*   3:    */ import cpw.mods.fml.relauncher.Side;
/*   4:    */ import cpw.mods.fml.relauncher.SideOnly;
/*   5:    */ import net.minecraft.entity.DataWatcher;
/*   6:    */ import net.minecraft.entity.Entity;
/*   7:    */ import net.minecraft.entity.EntityLivingBase;
/*   8:    */ import net.minecraft.util.MovingObjectPosition;
/*   9:    */ import net.minecraft.util.Vec3;
/*  10:    */ import net.minecraft.world.World;
/*  11:    */ 
/*  12:    */ public class EntityMasterSpark
/*  13:    */   extends EntityTHSetLaser
/*  14:    */ {
/*  15:    */   public int count;
/*  16:    */   public int flagtime;
/*  17:    */   public double speed;
/*  18:    */   public float size;
/*  19:    */   public float oldSize;
/*  20:    */   public int endtime;
/*  21:    */   public int inicount;
/*  22:    */   
/*  23:    */   public EntityMasterSpark(World world)
/*  24:    */   {
/*  25: 26 */     super(world);
/*  26:    */     
/*  27: 28 */     setSize(60.0F, 5.0F);
/*  28: 29 */     this.count = 0;
/*  29:    */   }
/*  30:    */   
/*  31:    */   public EntityMasterSpark(World world, EntityLivingBase user, Entity source, Vec3 pos, Vec3 angle, float slope, Vec3 rotate, float rotationSpeed, int rotationEnd, int color, float width, float length, float damage, int delay, int end, int special, Entity set, double setLength, double setYOffset)
/*  32:    */   {
/*  33: 38 */     super(world, user, source, pos, angle, slope, rotate, rotationSpeed, rotationEnd, 0, width, length, damage, delay, end, special, set, setLength, setYOffset);
/*  34: 39 */     setSize(5.0F, 5.0F);
/*  35:    */   }
/*  36:    */   
/*  37:    */   protected void entityInit()
/*  38:    */   {
/*  39: 49 */     super.entityInit();
/*  40: 50 */     this.dataWatcher.addObject(21, new Integer(0));
/*  41: 51 */     this.dataWatcher.addObject(22, new Integer(0));
/*  42:    */   }
/*  43:    */   
/*  44:    */   @SideOnly(Side.CLIENT)
/*  45:    */   public boolean isInRangeToRenderDist(double d)
/*  46:    */   {
/*  47: 62 */     return true;
/*  48:    */   }
/*  49:    */   
/*  50:    */   public void onUpdate()
/*  51:    */   {
/*  52: 75 */     super.onUpdate();
/*  53: 77 */     if ((!this.worldObj.isRemote) && (this.ticksExisted == 1)) {
/*  54: 79 */       setDead();
/*  55:    */     }
/*  56:    */   }
/*  57:    */   
/*  58:    */   protected boolean onImpact(MovingObjectPosition movingobjectposition)
/*  59:    */   {
/*  60: 89 */     return true;
/*  61:    */   }
/*  62:    */   
/*  63:    */   public void setIniTime(int par1)
/*  64:    */   {
/*  65: 94 */     this.dataWatcher.updateObject(21, Integer.valueOf(par1));
/*  66:    */   }
/*  67:    */   
/*  68:    */   public int getIniTime()
/*  69:    */   {
/*  70: 99 */     return this.dataWatcher.getWatchableObjectInt(21);
/*  71:    */   }
/*  72:    */   
/*  73:    */   public void setMaxLength(double par1)
/*  74:    */   {
/*  75:104 */     this.dataWatcher.updateObject(22, Integer.valueOf((int)(par1 * 100.0D)));
/*  76:    */   }
/*  77:    */   
/*  78:    */   public double getMaxLength()
/*  79:    */   {
/*  80:109 */     return this.dataWatcher.getWatchableObjectInt(22) / 100.0D;
/*  81:    */   }
/*  82:    */   
/*  83:    */   public boolean canBeCollidedWith()
/*  84:    */   {
/*  85:115 */     return false;
/*  86:    */   }
/*  87:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.shot.EntityMasterSpark
 * JD-Core Version:    0.7.0.1
 */