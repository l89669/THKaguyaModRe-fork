package thKaguyaMod.client.model.living;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

	
@SideOnly(Side.CLIENT)
public class ModelRinnosuke extends ModelBase
{
	  //fields
    ModelRenderer bipedBody;
    ModelRenderer bipedRightArm;
    ModelRenderer bipedLeftArm;
    ModelRenderer bipedRightLeg;
    ModelRenderer bipedLeftLeg;
    ModelRenderer box;
    ModelRenderer bottom;
    ModelRenderer bipedHead;
    ModelRenderer longHair;
    ModelRenderer Grass;
  
  public ModelRinnosuke()
  {
	    textureWidth = 64;
	    textureHeight = 64;
	    
	      bipedBody = new ModelRenderer(this, 0, 16);
	      bipedBody.addBox(-4F, 0F, -2F, 8, 10, 4);
	      bipedBody.setRotationPoint(0F, 0F, 0F);
	      bipedBody.setTextureSize(64, 64);
	      bipedBody.mirror = true;
	      setRotation(bipedBody, 0F, 0F, 0F);
	      bipedRightArm = new ModelRenderer(this, 0, 32);
	      bipedRightArm.addBox(-2F, -1F, -1.5F, 3, 10, 3);
	      bipedRightArm.setRotationPoint(-5F, 1F, 0F);
	      bipedRightArm.setTextureSize(64, 64);
	      bipedRightArm.mirror = true;
	      setRotation(bipedRightArm, 0F, 0F, 0F);
	      bipedLeftArm = new ModelRenderer(this, 12, 32);
	      bipedLeftArm.addBox(-1F, -1F, -1.5F, 3, 10, 3);
	      bipedLeftArm.setRotationPoint(5F, 1F, 0F);
	      bipedLeftArm.setTextureSize(64, 64);
	      bipedLeftArm.mirror = true;
	      setRotation(bipedLeftArm, 0F, 0F, 0F);
	      bipedRightLeg = new ModelRenderer(this, 48, 16);
	      bipedRightLeg.addBox(-1F, 0F, -2F, 4, 14, 4);
	      bipedRightLeg.setRotationPoint(-3F, 10F, 0F);
	      bipedRightLeg.setTextureSize(64, 64);
	      bipedRightLeg.mirror = true;
	      setRotation(bipedRightLeg, 0F, 0F, 0F);
	      bipedLeftLeg = new ModelRenderer(this, 32, 16);
	      bipedLeftLeg.addBox(-2F, 0F, -2F, 4, 14, 4);
	      bipedLeftLeg.setRotationPoint(2F, 10F, 0F);
	      bipedLeftLeg.setTextureSize(64, 64);
	      bipedLeftLeg.mirror = true;
	      setRotation(bipedLeftLeg, 0F, 0F, 0F);
	      box = new ModelRenderer(this, 32, 10);
	      box.addBox(-3F, -2F, 0F, 6, 4, 2);
	      box.setRotationPoint(0F, 8F, -4F);
	      box.setTextureSize(64, 64);
	      box.mirror = true;
	      setRotation(box, 0F, 0F, 0F);
	      bottom = new ModelRenderer(this, 0, 46);
	      bottom.addBox(-4.5F, 0F, -2.5F, 9, 13, 5);
	      bottom.setRotationPoint(0F, 8F, 0F);
	      bottom.setTextureSize(64, 64);
	      bottom.mirror = true;
	      setRotation(bottom, 0F, 0F, 0F);
	      bipedHead = new ModelRenderer(this, 0, 0);
	      bipedHead.addBox(-4F, -8F, -4F, 8, 8, 8);
	      bipedHead.setRotationPoint(0F, 0F, 0F);
	      bipedHead.setTextureSize(64, 64);
	      bipedHead.mirror = true;
	      setRotation(bipedHead, 0F, 0F, 0F);
	      longHair = new ModelRenderer(this, 24, 0);
	      longHair.addBox(-4F, 0F, -3F, 8, 2, 3);
	      longHair.setRotationPoint(0F, 0F, 4F);
	      longHair.setTextureSize(64, 64);
	      longHair.mirror = true;
	      setRotation(longHair, 0F, 0F, 0F);
	      Grass = new ModelRenderer(this, 48, 0);
	      Grass.addBox(-4F, 0F, 0F, 8, 2, 1);
	      Grass.setRotationPoint(0F, -3F, -4.2F);
	      Grass.setTextureSize(64, 64);
	      Grass.mirror = true;
	      setRotation(Grass, 0F, 0F, 0F);
	      
	      bipedHead.addChild(longHair);
	      bipedHead.addChild(Grass);
	      bipedBody.addChild(box);
	      bipedBody.addChild(bottom);
    }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
	    super.render(entity, f, f1, f2, f3, f4, f5);
	    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    bipedBody.render(f5);
	    bipedRightArm.render(f5);
	    //rightHand.render(f5);
	    bipedLeftArm.render(f5);
	    //leftHand.render(f5);
	    bipedRightLeg.render(f5);
	    bipedLeftLeg.render(f5);
	    //skirtTop.render(f5);
	    //skirtBottom.render(f5);
	    bipedHead.render(f5);
	    //longHair.render(f5);
	    //leftBraid2.render(f5);
	    //rightBraid.render(f5);
	    //leftBraid.render(f5);
	    //kaeruBase.render(f5);
	    //kaeruEyeRight.render(f5);
	    //kaeruEyeLeft.render(f5);
	    
	  //EntityDanmakuMob danmakuMob = (EntityDanmakuMob)entity;

	  //スペルカードの宣言をする
	  /*if(danmakuMob.getSpellCardMotion() < -15)
	  {
		  bipedRightArm.rotateAngleX = -0.7F - MathHelper.sin(danmakuMob.ticksExisted  / 10F) * 0.1F - MathHelper.sin((float)(30F + danmakuMob.getSpellCardMotion()) / 180F * 3.141593F) * 4F;
	  }
	  else if(danmakuMob.getSpellCardMotion() < 0)
	  {
		  bipedRightArm.rotateAngleX = -0.7F - MathHelper.sin(danmakuMob.ticksExisted  / 10F) * 0.1F - MathHelper.sin((float)(-danmakuMob.getSpellCardMotion()) / 180F * 3.141593F) * 4F;
	  }
	  else*/
	  {
		  bipedRightArm.rotateAngleX =  -12F / 180F * (float)Math.PI;
		  bipedRightArm.rotateAngleY = -10F / 180F * (float)Math.PI;
		  bipedRightArm.rotateAngleZ = 30F / 180F * (float)Math.PI;
		  
		  bipedLeftArm.rotateAngleX =  bipedRightArm.rotateAngleX;
		  bipedLeftArm.rotateAngleY = -bipedRightArm.rotateAngleY;
		  bipedLeftArm.rotateAngleZ = -bipedRightArm.rotateAngleZ;
		  //bipedLeftArm.rotateAngleX = bipedRightArm.rotateAngleX;
		  //bipedRightArm.rotateAngleY = 0.9F;
		  //bipedRightArm.rotateAngleX = -0.8F - MathHelper.sin(danmakuMob.ticksExisted  / 10F) * 0.1F;
		  //bipedRightArm.rotateAngleZ = 20F / 180F * (float)Math.PI;

		  //bipedLeftArm.rotateAngleZ = -bipedRightArm.rotateAngleZ;
		  
	  }

  }
  
  private void setRotation(ModelRenderer model, float rotateX, float rotateY, float rotateZ)
  {
	  model.rotateAngleX = rotateX;
	  model.rotateAngleY = rotateY;
	  model.rotateAngleZ = rotateZ;
  }

  public void setRotationAngles(float movement, float far, float f2, float f3, float f4, float f5, Entity entity)
  {
	  //super.setRotationAngles(movement, far, f2, f3, f4, f5, entity);
	  //this.skirtTop.rotateAngleX = 0.0F;
	  this.bipedHead.rotateAngleY = f3 / (180F / (float)Math.PI);
	  this.bipedHead.rotateAngleX = f4 / (180F / (float)Math.PI);
	  this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(onGround) * (float)Math.PI * 2.0F) * 0.2F;

	  bipedRightLeg.rotateAngleX = MathHelper.cos(movement * 0.6662F) * 0.7F * far;
	  bipedLeftLeg.rotateAngleX = MathHelper.cos(movement * 0.6662F + (float)Math.PI) * 0.7F * far;
	  this.bipedRightLeg.rotateAngleY = 0.0F;
      this.bipedLeftLeg.rotateAngleY = 0.0F;
	  this.bipedRightLeg.rotateAngleZ = 0.0F;
      this.bipedLeftLeg.rotateAngleZ = 0.0F;
	  
      this.bipedRightArm.rotateAngleX = MathHelper.cos(movement * 0.6662F + (float)Math.PI) * 2.0F * far * 0.5F;
      this.bipedLeftArm.rotateAngleX = MathHelper.cos(movement * 0.6662F) * 2.0F * far * 0.5F;
	  //bipedRightArm.rotateAngleY = -10F / 180F * (float)Math.PI;
	  bipedRightArm.rotateAngleZ = 10F / 180F * (float)Math.PI;
	  //bipedLeftArm.rotateAngleY = -bipedRightArm.rotateAngleY;
	  bipedLeftArm.rotateAngleZ = -bipedRightArm.rotateAngleZ;
	  
      if (this.isRiding)
      {
          this.bipedRightArm.rotateAngleX = -((float)Math.PI / 5F);
          this.bipedLeftArm.rotateAngleX = -((float)Math.PI / 5F);
          this.bipedRightLeg.rotateAngleX = -((float)Math.PI * 2F / 5F);
          this.bipedLeftLeg.rotateAngleX = -((float)Math.PI * 2F / 5F);
          this.bipedRightLeg.rotateAngleY = ((float)Math.PI / 14F);
          this.bipedLeftLeg.rotateAngleY = -((float)Math.PI / 14F);
          this.bipedRightLeg.rotateAngleZ = ((float)Math.PI / 14F);
          this.bipedLeftLeg.rotateAngleZ = -((float)Math.PI / 14F);
          
          //this.skirtTop.rotateAngleX += -((float)Math.PI / 5F);
      }
      
      /*if (this.isSneak)
      {
          this.bipedBody.rotateAngleX = 0.5F;
          this.bipedRightArm.rotateAngleX += 0.4F;
          this.bipedLeftArm.rotateAngleX += 0.4F;
          this.bipedRightLeg.rotationPointZ = 4.0F;
          this.bipedLeftLeg.rotationPointZ = 4.0F;
          this.bipedRightLeg.rotationPointY = 9.0F;
          this.bipedLeftLeg.rotationPointY = 9.0F;
          this.bipedHead.rotationPointY = 1.0F;
          //this.bipedHeadwear.rotationPointY = 1.0F;
      }
      else
      {
          this.bipedBody.rotateAngleX = 0.0F;
          this.bipedRightLeg.rotationPointZ = 0.1F;
          this.bipedLeftLeg.rotationPointZ = 0.1F;
          this.bipedRightLeg.rotationPointY = 12.0F;
          this.bipedLeftLeg.rotationPointY = 12.0F;
          this.bipedHead.rotationPointY = 0.0F;
          //this.bipedHeadwear.rotationPointY = 0.0F;
      }*/
      
      this.bipedRightArm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
      this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
      this.bipedRightArm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
      this.bipedLeftArm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
  }
}