/*   1:    */ package thKaguyaMod.entity.ai;
/*   2:    */ 
/*   3:    */ import net.minecraft.entity.EntityLiving;
/*   4:    */ import net.minecraft.entity.EntityLivingBase;
/*   5:    */ import net.minecraft.entity.IRangedAttackMob;
/*   6:    */ import net.minecraft.entity.ai.EntityAIBase;
/*   7:    */ import net.minecraft.entity.ai.EntityLookHelper;
/*   8:    */ import net.minecraft.entity.ai.EntitySenses;
/*   9:    */ import net.minecraft.pathfinding.PathNavigate;
/*  10:    */ import net.minecraft.util.AxisAlignedBB;
/*  11:    */ import net.minecraft.util.MathHelper;
/*  12:    */ 
/*  13:    */ public class EntityAIDanmakuPosition
/*  14:    */   extends EntityAIBase
/*  15:    */ {
/*  16:    */   private final EntityLiving entityHost;
/*  17:    */   private final IRangedAttackMob rangedAttackEntityHost;
/*  18:    */   private EntityLivingBase attackTarget;
/*  19:    */   private int rangedAttackTime;
/*  20:    */   private double entityMoveSpeed;
/*  21:    */   private int field_75318_f;
/*  22:    */   private int field_96561_g;
/*  23:    */   private int maxRangedAttackTime;
/*  24:    */   private float field_96562_i;
/*  25:    */   private float field_82642_h;
/*  26:    */   private static final String __OBFID = "CL_00001609";
/*  27:    */   
/*  28:    */   public EntityAIDanmakuPosition(IRangedAttackMob par1IRangedAttackMob, double par2, int par4, float par5)
/*  29:    */   {
/*  30: 38 */     this(par1IRangedAttackMob, par2, par4, par4, par5);
/*  31:    */   }
/*  32:    */   
/*  33:    */   public EntityAIDanmakuPosition(IRangedAttackMob par1IRangedAttackMob, double par2, int par4, int par5, float par6)
/*  34:    */   {
/*  35: 43 */     this.rangedAttackTime = -1;
/*  36: 45 */     if (!(par1IRangedAttackMob instanceof EntityLivingBase)) {
/*  37: 47 */       throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
/*  38:    */     }
/*  39: 51 */     this.rangedAttackEntityHost = par1IRangedAttackMob;
/*  40: 52 */     this.entityHost = ((EntityLiving)par1IRangedAttackMob);
/*  41: 53 */     this.entityMoveSpeed = par2;
/*  42: 54 */     this.field_96561_g = par4;
/*  43: 55 */     this.maxRangedAttackTime = par5;
/*  44: 56 */     this.field_96562_i = par6;
/*  45: 57 */     this.field_82642_h = (par6 * par6);
/*  46: 58 */     setMutexBits(3);
/*  47:    */   }
/*  48:    */   
/*  49:    */   public boolean shouldExecute()
/*  50:    */   {
/*  51: 67 */     EntityLivingBase entitylivingbase = this.entityHost.getAttackTarget();
/*  52: 69 */     if (entitylivingbase == null) {
/*  53: 71 */       return false;
/*  54:    */     }
/*  55: 75 */     this.attackTarget = entitylivingbase;
/*  56: 76 */     return true;
/*  57:    */   }
/*  58:    */   
/*  59:    */   public boolean continueExecuting()
/*  60:    */   {
/*  61: 85 */     return (shouldExecute()) || (!this.entityHost.getNavigator().noPath());
/*  62:    */   }
/*  63:    */   
/*  64:    */   public void resetTask()
/*  65:    */   {
/*  66: 93 */     this.attackTarget = null;
/*  67: 94 */     this.field_75318_f = 0;
/*  68: 95 */     this.rangedAttackTime = -1;
/*  69:    */   }
/*  70:    */   
/*  71:    */   public void updateTask()
/*  72:    */   {
/*  73:103 */     double d0 = this.entityHost.getDistanceSq(this.attackTarget.posX, this.attackTarget.boundingBox.minY, this.attackTarget.posZ);
/*  74:104 */     boolean flag = this.entityHost.getEntitySenses().canSee(this.attackTarget);
/*  75:106 */     if (flag) {
/*  76:108 */       this.field_75318_f += 1;
/*  77:    */     } else {
/*  78:112 */       this.field_75318_f = 0;
/*  79:    */     }
/*  80:115 */     if ((d0 <= this.field_82642_h) && (this.field_75318_f >= 20)) {
/*  81:117 */       this.entityHost.getNavigator().clearPathEntity();
/*  82:    */     } else {
/*  83:121 */       this.entityHost.getNavigator().tryMoveToEntityLiving(this.attackTarget, this.entityMoveSpeed);
/*  84:    */     }
/*  85:124 */     this.entityHost.getLookHelper().setLookPositionWithEntity(this.attackTarget, 30.0F, 30.0F);
/*  86:127 */     if (--this.rangedAttackTime == 0)
/*  87:    */     {
/*  88:129 */       if ((d0 > this.field_82642_h) || (!flag)) {
/*  89:131 */         return;
/*  90:    */       }
/*  91:134 */       float f = MathHelper.sqrt_double(d0) / this.field_96562_i;
/*  92:135 */       float f1 = f;
/*  93:137 */       if (f < 0.1F) {
/*  94:139 */         f1 = 0.1F;
/*  95:    */       }
/*  96:142 */       if (f1 > 1.0F) {
/*  97:144 */         f1 = 1.0F;
/*  98:    */       }
/*  99:147 */       this.rangedAttackEntityHost.attackEntityWithRangedAttack(this.attackTarget, f1);
/* 100:148 */       this.rangedAttackTime = MathHelper.floor_float(f * (this.maxRangedAttackTime - this.field_96561_g) + this.field_96561_g);
/* 101:    */     }
/* 102:150 */     else if (this.rangedAttackTime < 0)
/* 103:    */     {
/* 104:152 */       float f = MathHelper.sqrt_double(d0) / this.field_96562_i;
/* 105:153 */       this.rangedAttackTime = MathHelper.floor_float(f * (this.maxRangedAttackTime - this.field_96561_g) + this.field_96561_g);
/* 106:    */     }
/* 107:    */   }
/* 108:    */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.ai.EntityAIDanmakuPosition
 * JD-Core Version:    0.7.0.1
 */