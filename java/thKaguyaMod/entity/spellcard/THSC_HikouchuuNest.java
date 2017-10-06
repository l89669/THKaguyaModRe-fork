/*  1:   */ package thKaguyaMod.entity.spellcard;
/*  2:   */ 
/*  3:   */ import java.util.Random;
/*  4:   */ import net.minecraft.entity.EntityLivingBase;
/*  5:   */ import net.minecraft.util.MovingObjectPosition;
/*  6:   */ import net.minecraft.util.Vec3;
/*  7:   */ import net.minecraft.world.World;
/*  8:   */ import thKaguyaMod.entity.item.EntitySukima;
/*  9:   */ 
/* 10:   */ public class THSC_HikouchuuNest
/* 11:   */   extends THSpellCard
/* 12:   */ {
/* 13:   */   public THSC_HikouchuuNest()
/* 14:   */   {
/* 15:13 */     setNeedLevel(4);
/* 16:14 */     setRemoveTime(50);
/* 17:15 */     setEndTime(160);
/* 18:16 */     setOriginalUserName(21);
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void spellcard_main()
/* 22:   */   {
/* 23:22 */     boolean flag = false;
/* 24:23 */     int count = 0;
/* 25:24 */     while ((!flag) && (count < 4))
/* 26:   */     {
/* 27:26 */       double length = this.rand.nextDouble() * 5.0D + 1.0D;
/* 28:27 */       float angleXZ = (this.card.rotationYaw + 90.0F) / 180.0F * 3.141593F + this.rand.nextFloat() * 3.141593F;
/* 29:28 */       float angleY = this.rand.nextFloat() * 3.141593F - 1.570796F;
/* 30:29 */       double xVector = Math.sin(angleXZ) * Math.cos(angleY) * length;
/* 31:30 */       double yVector = Math.sin(angleY) * length;
/* 32:31 */       double zVector = Math.cos(angleXZ) * Math.cos(angleY) * length;
/* 33:   */       
/* 34:   */ 
/* 35:34 */       Vec3 vec3d = Vec3.createVectorHelper(this.user.posX, this.user.posY, this.user.posZ);
/* 36:   */       
/* 37:36 */       Vec3 vec3d1 = Vec3.createVectorHelper(this.user.posX + xVector, this.user.posY + yVector, this.user.posZ + zVector);
/* 38:   */       
/* 39:38 */       MovingObjectPosition movingObjectPosition = this.world.func_147447_a(vec3d, vec3d1, false, true, true);
/* 40:39 */       vec3d = Vec3.createVectorHelper(this.user.posX, this.user.posY, this.user.posZ);
/* 41:40 */       vec3d1 = Vec3.createVectorHelper(this.user.posX + xVector, this.user.posY + yVector, this.user.posZ + zVector);
/* 42:42 */       if (movingObjectPosition != null)
/* 43:   */       {
/* 44:44 */         count++;
/* 45:   */       }
/* 46:   */       else
/* 47:   */       {
/* 48:48 */         double xPos = this.user.posX + xVector;
/* 49:49 */         double yPos = this.user.posY + yVector;
/* 50:50 */         double zPos = this.user.posZ + zVector;
/* 51:51 */         EntitySukima entitySukima = new EntitySukima(this.world, this.user, xPos, yPos, zPos, this.user.rotationYaw, 17);
/* 52:52 */         if (!this.world.isRemote) {
/* 53:54 */           this.world.spawnEntityInWorld(entitySukima);
/* 54:   */         }
/* 55:56 */         flag = true;
/* 56:   */       }
/* 57:   */     }
/* 58:   */   }
/* 59:   */ }


/* Location:           C:\Users\acer\Downloads\五つの難題MOD+ ver2.90.1-1.7.10-deobf.jar
 * Qualified Name:     thKaguyaMod.entity.spellcard.THSC_HikouchuuNest
 * JD-Core Version:    0.7.0.1
 */