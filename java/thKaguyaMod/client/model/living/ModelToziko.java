package thKaguyaMod.client.model.living;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import thKaguyaMod.entity.living.EntityToziko;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

	
@SideOnly(Side.CLIENT)
public class ModelToziko extends ModelBase
{
    ModelRenderer Body;
    ModelRenderer RightArm;
    ModelRenderer LeftArm;
    ModelRenderer Leg1;
    ModelRenderer Leg2;
    ModelRenderer Leg3;
    ModelRenderer SkirtTop;
    ModelRenderer SkirtBottom;
    ModelRenderer HeadBase;
    ModelRenderer BackHair;
    ModelRenderer LeftHair;
    ModelRenderer RightHair;
    ModelRenderer Cup;
    ModelRenderer CupLeft;
    ModelRenderer CupRight;
    ModelRenderer CupRibbon;
  
    public ModelToziko()
    {  
        textureWidth = 64;
        textureHeight = 64;
        
          Body = new ModelRenderer(this, 32, 6);
          Body.addBox(-3F, 0F, -2F, 6, 9, 4);
          Body.setRotationPoint(0F, 2F, 0F);
          Body.setTextureSize(64, 64);
          Body.mirror = true;
          setRotation(Body, 0F, 0F, 0F);
          RightArm = new ModelRenderer(this, 48, 0);
          RightArm.addBox(-2F, -1F, -1F, 2, 8, 2);
          RightArm.setRotationPoint(-3F, 3F, 0F);
          RightArm.setTextureSize(64, 64);
          RightArm.mirror = true;
          setRotation(RightArm, -1.570796F, 0F, 0F);
          LeftArm = new ModelRenderer(this, 48, 0);
          LeftArm.addBox(0F, -1F, -1F, 2, 8, 2);
          LeftArm.setRotationPoint(3F, 3F, 0F);
          LeftArm.setTextureSize(64, 64);
          LeftArm.mirror = true;
          setRotation(LeftArm, -1.570796F, 0F, 0F);
          Leg1 = new ModelRenderer(this, 32, 17);
          Leg1.addBox(-4F, 0F, -4F, 8, 7, 8);
          Leg1.setRotationPoint(0F, 12F, 0F);
          Leg1.setTextureSize(64, 64);
          Leg1.mirror = true;
          setRotation(Leg1, 0F, 0F, 0F);
          Leg2 = new ModelRenderer(this, 32, 17);
          Leg2.addBox(-3F, 0F, -3F, 6, 4, 6);
          Leg2.setRotationPoint(0F, 17F, 0F);
          Leg2.setTextureSize(64, 64);
          Leg2.mirror = true;
          setRotation(Leg2, 0F, 0F, 0F);
          Leg3 = new ModelRenderer(this, 32, 17);
          Leg3.addBox(-2F, 0F, -2F, 4, 4, 4);
          Leg3.setRotationPoint(0F, 20F, 0F);
          Leg3.setTextureSize(64, 64);
          Leg3.mirror = true;
          setRotation(Leg3, 0F, 0F, 0F);
          SkirtTop = new ModelRenderer(this, 0, 16);
          SkirtTop.addBox(-4F, 0F, -4F, 8, 4, 8);
          SkirtTop.setRotationPoint(0F, 8F, 0F);
          SkirtTop.setTextureSize(64, 64);
          SkirtTop.mirror = true;
          setRotation(SkirtTop, 0F, 0F, 0F);
          SkirtBottom = new ModelRenderer(this, 16, 32);
          SkirtBottom.addBox(-5F, 0F, -5F, 10, 7, 10);
          SkirtBottom.setRotationPoint(0F, 12F, 0F);
          SkirtBottom.setTextureSize(64, 64);
          SkirtBottom.mirror = true;
          setRotation(SkirtBottom, 0F, 0F, 0F);
          HeadBase = new ModelRenderer(this, 0, 0);
          HeadBase.addBox(-4F, -8F, -4F, 8, 8, 8);
          HeadBase.setRotationPoint(0F, 2F, 0F);
          HeadBase.setTextureSize(64, 64);
          HeadBase.mirror = true;
          setRotation(HeadBase, 0F, 0F, 0F);
          BackHair = new ModelRenderer(this, 0, 48);
          BackHair.addBox(-4F, -2F, -3F, 8, 2, 3);
          BackHair.setRotationPoint(0F, 2F, 4F);
          BackHair.setTextureSize(64, 64);
          BackHair.mirror = true;
          HeadBase.addChild(BackHair);
          setRotation(BackHair, 0F, 0F, 0F);
          LeftHair = new ModelRenderer(this, 11, 56);
          LeftHair.addBox(-4F, -2F, 0F, 8, 5, 1);
          LeftHair.setRotationPoint(5F, -3F, 0F);
          LeftHair.setTextureSize(64, 64);
          LeftHair.mirror = true;
          HeadBase.addChild(LeftHair);
          setRotation(LeftHair, 0F, -1.570796F, 0F);
          RightHair = new ModelRenderer(this, 11, 56);
          RightHair.addBox(-4F, -2F, 0F, 8, 5, 1);
          RightHair.setRotationPoint(-5F, -3F, 0F);
          RightHair.setTextureSize(64, 64);
          RightHair.mirror = true;
          HeadBase.addChild(RightHair);
          setRotation(RightHair, 0F, 1.570796F, 0F);
          Cup = new ModelRenderer(this, 0, 32);
          Cup.addBox(-2F, -8F, -2F, 4, 6, 4);
          Cup.setRotationPoint(0F, -6F, 0F);
          Cup.setTextureSize(64, 64);
          Cup.mirror = true;
          HeadBase.addChild(Cup);
          setRotation(Cup, 0F, 0F, 0F);
          CupLeft = new ModelRenderer(this, 0, 42);
          CupLeft.addBox(0F, 4F, 0F, 4, 1, 1);
          CupLeft.setRotationPoint(2F, -7F, 0F);
          CupLeft.setTextureSize(64, 64);
          CupLeft.mirror = true;
          Cup.addChild(CupLeft);
          setRotation(CupLeft, 0F, 0F, 0F);
          CupRight = new ModelRenderer(this, 0, 42);
          CupRight.addBox(-4F, 4F, 0F, 4, 1, 1);
          CupRight.setRotationPoint(-2F, -7F, 0F);
          CupRight.setTextureSize(64, 64);
          CupRight.mirror = true;
          Cup.addChild(CupRight);
          setRotation(CupRight, 0F, 0F, 0F);
          CupRibbon = new ModelRenderer(this, 0, 44);
          CupRibbon.addBox(-2F, 2F, 0F, 5, 4, 1);
          CupRibbon.setRotationPoint(2F, -7F, 1F);
          CupRibbon.setTextureSize(64, 64);
          CupRibbon.mirror = true;
          Cup.addChild(CupRibbon);
          setRotation(CupRibbon, 0F, -0.4833219F, -0.5205006F);
    }
  
  @Override
  public void render(Entity entity, float movement, float far, float tick, float yaw, float pitch, float size)
  {
	  super.render(entity, movement, far, tick, yaw, pitch, size);
	  setRotationAngles(movement, far, tick, yaw, pitch, size, entity);
	    Body.render(size);
	    RightArm.render(size);
	    LeftArm.render(size);
	    Leg1.render(size);
	    Leg2.render(size);
	    Leg3.render(size);
	    SkirtTop.render(size);
	    SkirtBottom.render(size);
	    HeadBase.render(size);
	    //BackHair.render(size);
	    //LeftHair.render(size);
	    //RightHair.render(size);
	    //Cup.render(size);
	    //CupLeft.render(size);
	    //CupRight.render(size);
	    //CupRibbon.render(size);
  }
  
  private void setRotation(ModelRenderer model, float rotateX, float rotateY, float rotateZ)
  {
	  model.rotateAngleX = rotateX;
	  model.rotateAngleY = rotateY;
	  model.rotateAngleZ = rotateZ;
  }

  @Override
  public void setRotationAngles(float movement, float far, float tick, float yaw, float pitch, float size, Entity entity)
  {
    super.setRotationAngles(movement, far, tick, yaw, pitch, size, entity);
    EntityToziko toziko = (EntityToziko)entity;
    
  	this.HeadBase.rotateAngleY = yaw / (180F / (float)Math.PI);
    this.HeadBase.rotateAngleX = pitch / (180F / (float)Math.PI);
  	this.Body.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(onGround) * (float)Math.PI * 2.0F) * 0.2F;
  	this.SkirtTop.rotateAngleX = 0F;

  	//スペルカードの宣言をする
  	if(toziko.getSpellCardMotion() < -15)
  	{
  		RightArm.rotateAngleX = -0.7F - MathHelper.sin(tick  / 10F) * 0.1F - MathHelper.sin((float)(30F + toziko.getSpellCardMotion()) / 180F * 3.141593F) * 4F;
  	}
  	else if(toziko.getSpellCardMotion() < 0)
  	{
  		RightArm.rotateAngleX = -0.7F - MathHelper.sin(tick  / 10F) * 0.1F - MathHelper.sin((float)(-toziko.getSpellCardMotion()) / 180F * 3.141593F) * 4F;
  	}
  	else
  	{
  		RightArm.rotateAngleY = 90F / 180F * (float)Math.PI;
		LeftArm.rotateAngleY = -RightArm.rotateAngleZ;
  	}
  	
    if (this.isRiding)
    {
        this.RightArm.rotateAngleX = -((float)Math.PI / 5F);
        this.LeftArm.rotateAngleX = -((float)Math.PI / 5F);
       // this.RightLeg.rotateAngleX = -((float)Math.PI * 2F / 5F);
       // this.LeftLeg.rotateAngleX = -((float)Math.PI * 2F / 5F);
       // this.RightLeg.rotateAngleY = ((float)Math.PI / 14F);
       // this.LeftLeg.rotateAngleY = -((float)Math.PI / 14F);
       // this.RightLeg.rotateAngleZ = ((float)Math.PI / 14F);
       // this.LeftLeg.rotateAngleZ = -((float)Math.PI / 14F);
        
        this.SkirtTop.rotateAngleX += -((float)Math.PI / 5F);
    }
    else
    {
  	
	  	if(toziko.getFlyingHeight() == 0)
	  	{
	  		//rightLeg.rotateAngleX = MathHelper.cos(movement) * 0.7F * far;
	  		//leftLeg.rotateAngleX = MathHelper.cos(movement + (float)Math.PI) * 0.7F * far;
		  	//rightLeg.rotateAngleZ = 0F; 
		  	//leftLeg.rotateAngleZ = 0F;
		  	
		  	if(movement > 0F)
		  	{
		        this.RightArm.rotateAngleX = MathHelper.cos(movement + (float)Math.PI) * 2.0F * far * 0.5F;
		        this.LeftArm.rotateAngleX = MathHelper.cos(movement) * 2.0F * far * 0.5F;
		        RightArm.rotateAngleY = -90F / 180F * (float)Math.PI;
		        RightArm.rotateAngleZ = 20F / 180F * (float)Math.PI;
		        LeftArm.rotateAngleY = -RightArm.rotateAngleY;
		        LeftArm.rotateAngleZ = -RightArm.rotateAngleZ;
		  	}
		  	else
		  	{
		  	  	RightArm.rotateAngleX = -0.7F - MathHelper.sin(tick  / 10F) * 0.1F;
		  	  	RightArm.rotateAngleY = 0.0F;
		  	  	RightArm.rotateAngleZ = -0.6457718F;
		  	  	LeftArm.rotateAngleX = RightArm.rotateAngleX;
		  	  	LeftArm.rotateAngleY = 0.0F;
		  	  	LeftArm.rotateAngleZ = 0.6457718F;
	
		  	}
	  	}
	  	else
	  	{
	  		
	  	  	//RightLeg.rotateAngleZ = Math.abs(MathHelper.sin(tick  / 10F) * 0.1F); 
	  	  	//LeftLeg.rotateAngleZ = -RightLeg.rotateAngleZ;
	  	  	//RightLeg.rotateAngleX = Math.abs(MathHelper.sin(tick  / 10F) * 0.2F); 
	  	  	//LeftLeg.rotateAngleX = RightLeg.rotateAngleZ;
	  	  	
	  	  	/*rightArm.rotateAngleX = -0.7F - MathHelper.sin(tick  / 10F) * 0.1F;
	  	  	rightArm.rotateAngleY = 0.0F;
		  	rightArm.rotateAngleZ = -0.6457718F;
	  	  	leftArm.rotateAngleX = rightArm.rotateAngleX;
	  	  	leftArm.rotateAngleY = 0.0F;
		  	leftArm.rotateAngleZ = 0.6457718F;*/
	  	  	
	  	  	RightArm.rotateAngleZ = 0.0F;
	  	  	RightArm.rotateAngleY = MathHelper.sin(tick  / 13F) * 0.1F;
	  	  	RightArm.rotateAngleX = -80F / 180F * (float)Math.PI + MathHelper.sin(tick  / 10F) * 0.1F;
			LeftArm.rotateAngleZ = 0.0F;
			LeftArm.rotateAngleY = -RightArm.rotateAngleY;
			LeftArm.rotateAngleX = RightArm.rotateAngleX;
	  	}
    }

  }
}