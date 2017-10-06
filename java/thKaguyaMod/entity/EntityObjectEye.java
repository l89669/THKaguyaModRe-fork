/*   1:    */ package thKaguyaMod.entity;
/*   2:    */ 
/*   3:    */ import net.minecraft.entity.Entity;
/*   4:    */ import net.minecraft.entity.player.EntityPlayer;
/*   5:    */ import net.minecraft.nbt.NBTTagCompound;
/*   6:    */ import net.minecraft.world.World;
/*   7:    */ 
/*   8:    */ public class EntityObjectEye
/*   9:    */   extends Entity
/*  10:    */ {
/*  11:    */   public EntityPlayer user;
/*  12:    */   public Entity target;
/*  13:    */   public int count;
/*  14:    */   public float turnAngle;
/*  15:    */   public int befUseCount;
/*  16:    */   
/*  17:    */   public EntityObjectEye(World world)
/*  18:    */   {
/*  19: 24 */     super(world);
/*  20: 25 */     setSize(1.0F, 1.0F);
/*  21:    */   }
/*  22:    */   
/*  23:    */   public EntityObjectEye(World world, EntityPlayer userEntity, Entity targetEntity)
/*  24:    */   {
/*  25: 31 */     this(world);
/*  26: 32 */     this.user = userEntity;
/*  27: 33 */     this.target = targetEntity;
/*  28: 34 */     this.prevPosX = this.target.posX;
/*  29: 35 */     this.prevPosY = this.target.posY;
/*  30: 36 */     this.prevPosZ = this.target.posZ;
/*  31: 37 */     setPosition(this.target.posX, this.target.posY + this.target.getEyeHeight(), this.target.posZ);
/*  32:    */     
/*  33: 39 */     this.count = 0;
/*  34: 40 */     this.befUseCount = 0;
/*  35: 41 */     this.turnAngle = 0.0F;
/*  36:    */   }
/*  37:    */   
/*  38:    */   protected void entityInit() {}
/*  39:    */   
/*  40:    */   protected boolean canTriggerWalking()
/*  41:    */   {
/*  42: 55 */     return false;
/*  43:    */   }
/*  44:    */   
/*  45:    */   public boolean canBeCollidedWith()
/*  46:    */   {
/*  47: 61 */     return false;
/*  48:    */   }
/*  49:    */   
/*  50:    */   public void onUpdate()
/*  51:    */   {
/*  52: 69 */     if ((!this.worldObj.isRemote) && ((this.user == null) || (this.user.isDead) || (this.target == null) || (this.target.isDead)))
/*  53:    */     {
/*  54: 71 */       setDead();
/*  55: 72 */       return;
/*  56:    */     }
/*  57: 74 */     super.onUpdate();
/*  58: 77 */     if (this.user != null) {
/*  59: 80 */       if (!this.user.isUsingItem())
/*  60:    */       {
/*  61: 82 */         int time = this.count;
/*  62: 83 */         time /= 3;
/*  63:    */         
/*  64:    */ 
/*  65:    */ 
/*  66:    */ 
/*  67:    */ 
/*  68:    */ 
/*  69:    */ 
/*  70:    */ 
/*  71:    */ 
/*  72:    */ 
/*  73:    */ 
/*  74:    */ 
/*  75: 96 */         setDead();
/*  76:    */       }
/*  77:    */     }
/*  78:104 */     this.count += 1;
/*  79:105 */     if (this.count > 114) {
/*  80:107 */       this.count = 114;
/*  81:109 */     } else if (this.count > 50) {
/*  82:111 */       this.turnAngle += 2.88F;
/*  83:    */     }
/*  84:    */   }
/*  85:    */   
/*  86:    */   protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {}
/*  87:    */   
/*  88:    */   protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {}
/*  89:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.EntityObjectEye
 * JD-Core Version:    0.7.0.1
 */