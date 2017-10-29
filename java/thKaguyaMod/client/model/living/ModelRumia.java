package thKaguyaMod.client.model.living;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import thKaguyaMod.entity.living.EntityRumia;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

	
@SideOnly(Side.CLIENT)
public class ModelRumia extends ModelBase
{
    ModelRenderer body;
    ModelRenderer head;
    ModelRenderer rightArm;
    ModelRenderer leftArm;
    ModelRenderer rightLeg;
    ModelRenderer leftLeg;
    ModelRenderer skirt;
    ModelRenderer skirt2;
    //ModelRenderer base;
    ModelRenderer longHair;
    //ModelRenderer leftArm;
  
    public ModelRumia()
    {  
    	textureWidth = 64;
    	textureHeight = 64;
	    
	      head = new ModelRenderer(this, 0, 0);
	      head.addBox(-4F, -3F, -4F, 8, 8, 8);
	      head.setRotationPoint(0F, 0F, 0F);
	      head.setTextureSize(64, 64);
	      head.mirror = true;
	      //setRotation(head, 0F, 0F, 0F);
	      longHair = new ModelRenderer(this, 24, 0);
	      longHair.addBox(-4F, 4F, -3F, 8, 5, 3);
	      longHair.setRotationPoint(0F, 0F, 4F);
	      //longHair.setTextureSize(64, 64);
	      //longHair.mirror = true;
	      //setRotation(longHair, 0F, 0F, 0F);
	      head.addChild(this.longHair);
	      
	      body = new ModelRenderer(this, 32, 8);
	      body.addBox(-3F, 5F, -2F, 6, 7, 4);
	      body.setRotationPoint(0F, 0F, 0F);
	      body.setTextureSize(64, 64);
	      body.mirror = true;
	      setRotation(body, 0F, 0F, 0F);
	      
	      skirt = new ModelRenderer(this, 0, 16);
	      skirt.addBox(-4F, 0F, -4F, 8, 5, 8);
	      skirt.setRotationPoint(0F, 10F, 0F);
	      //skirt.setTextureSize(64, 64);
	      //skirt.mirror = true;
	      setRotation(skirt, 0F, 0F, 0F);
	      skirt2 = new ModelRenderer(this, 24, 32);
	      skirt2.addBox(-5F, -5F, -5F, 10, 6, 10);
	      skirt2.setRotationPoint(0F, 9F, 0F);
	      //skirt2.setTextureSize(64, 64);
	      //skirt2.mirror = true;
	      skirt.addChild(skirt2);
	      setRotation(skirt2, 0F, 0F, 0F);
	      
	      rightArm = new ModelRenderer(this, 48, 0);
	      rightArm.addBox(-1F, -1F, -1F, 2, 8, 2);
	      rightArm.setRotationPoint(-4F, 6F, 0F);
	      rightArm.setTextureSize(64, 64);
	      rightArm.mirror = true;
	      //setRotation(rightArm, -0.7679449F, 0F, -0.6457718F);
	      setRotation(rightArm, 0F, 0F, 0F);
	      
	      /*rightArm = new ModelRenderer(this, 48, 3);
	      rightArm.addBox(-1F, -1F, -1F, 2, 8, 2);
	      rightArm.setRotationPoint(4F, 1F, 0F);
	      //rightArm.setTextureSize(64, 64);
	      //rightArm.mirror = true;
	      //setRotation(rightArm, -1.291544F, -1.375609F, 0F);
	      setRotation(rightArm,  0.7679449F, 0F, 0.6457718F);*/
	      leftArm = new ModelRenderer(this, 56, 0);
	      leftArm.addBox(-1F, -1F, -1F, 2, 8, 2);
	      leftArm.setRotationPoint(4F, 6F, 0F);
	      //leftArm.setTextureSize(64, 64);
	      //leftArm.mirror = true;
	      setRotation(leftArm, 0F, 0F, 0F);
	      rightLeg = new ModelRenderer(this, 50, 16);
	      rightLeg.addBox(-1F, 0F, -2F, 3, 12, 4);
	      rightLeg.setRotationPoint(-2F, 12F, 0F);
	      //rightLeg.setTextureSize(64, 64);
	      //rightLeg.mirror = true;
	      setRotation(rightLeg, 0F, 0F, 0F);
	      leftLeg = new ModelRenderer(this, 50, 16);
	      leftLeg.addBox(-2F, 0F, -2F, 3, 12, 4);
	      leftLeg.setRotationPoint(2F, 12F, 0F);
	      //leftLeg.setTextureSize(64, 64);
	      //leftLeg.mirror = true;
	      setRotation(leftLeg, 0F, 0F, 0F);
    }
  
  @Override
  public void render(Entity entity, float movement, float far, float tick, float yaw, float pitch, float size)
  {
	  super.render(entity, movement, far, tick, yaw, pitch, size);
	  setRotationAngles(movement, far, tick, yaw, pitch, size, entity);
	  head.render(size);
	  body.render(size);
	  skirt.render(size);
	  rightArm.render(size);
	  rightLeg.render(size);
	  leftArm.render(size);
	  leftLeg.render(size);
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
    EntityRumia rumia = (EntityRumia)entity;
    
  	this.head.rotateAngleY = yaw / (180F / (float)Math.PI);
    this.head.rotateAngleX = pitch / (180F / (float)Math.PI);
  	this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(onGround) * (float)Math.PI * 2.0F) * 0.2F;
  	this.skirt.rotateAngleX = 0F;

  	//スペルカードの宣言をする
  	if(rumia.getSpellCardMotion() < -15)
  	{
  		rightArm.rotateAngleX = -0.7F - MathHelper.sin(tick  / 10F) * 0.1F - MathHelper.sin((float)(30F + rumia.getSpellCardMotion()) / 180F * 3.141593F) * 4F;
  	}
  	else if(rumia.getSpellCardMotion() < 0)
  	{
  		rightArm.rotateAngleX = -0.7F - MathHelper.sin(tick  / 10F) * 0.1F - MathHelper.sin((float)(-rumia.getSpellCardMotion()) / 180F * 3.141593F) * 4F;
  	}
  	else
  	{
  		rightArm.rotateAngleZ = 90F / 180F * (float)Math.PI;
		leftArm.rotateAngleZ = -rightArm.rotateAngleZ;
  	}
  	
    if (this.isRiding)
    {
        this.rightArm.rotateAngleX = -((float)Math.PI / 5F);
        this.leftArm.rotateAngleX = -((float)Math.PI / 5F);
        this.rightLeg.rotateAngleX = -((float)Math.PI * 2F / 5F);
        this.leftLeg.rotateAngleX = -((float)Math.PI * 2F / 5F);
        this.rightLeg.rotateAngleY = ((float)Math.PI / 14F);
        this.leftLeg.rotateAngleY = -((float)Math.PI / 14F);
        this.rightLeg.rotateAngleZ = ((float)Math.PI / 14F);
        this.leftLeg.rotateAngleZ = -((float)Math.PI / 14F);
        
        this.skirt.rotateAngleX += -((float)Math.PI / 5F);
    }
    else
    {
  	
	  	if(rumia.getFlyingHeight() == 0)
	  	{
	  		rightLeg.rotateAngleX = MathHelper.cos(movement) * 0.7F * far;
	  		leftLeg.rotateAngleX = MathHelper.cos(movement + (float)Math.PI) * 0.7F * far;
		  	rightLeg.rotateAngleZ = 0F; 
		  	leftLeg.rotateAngleZ = 0F;
		  	
		  	if(movement > 0F)
		  	{
		        this.rightArm.rotateAngleX = MathHelper.cos(movement + (float)Math.PI) * 2.0F * far * 0.5F;
		        this.leftArm.rotateAngleX = MathHelper.cos(movement) * 2.0F * far * 0.5F;
		        rightArm.rotateAngleY = -10F / 180F * (float)Math.PI;
		        rightArm.rotateAngleZ = 20F / 180F * (float)Math.PI;
		        leftArm.rotateAngleY = -rightArm.rotateAngleY;
		        leftArm.rotateAngleZ = -rightArm.rotateAngleZ;
		  	}
		  	else
		  	{
		  	  	rightArm.rotateAngleX = -0.7F - MathHelper.sin(tick  / 10F) * 0.1F;
		  	  	rightArm.rotateAngleY = 0.0F;
		  	  	rightArm.rotateAngleZ = -0.6457718F;
		  	  	leftArm.rotateAngleX = rightArm.rotateAngleX;
		  	  	leftArm.rotateAngleY = 0.0F;
		  	  	leftArm.rotateAngleZ = 0.6457718F;
	
		  	}
	  	}
	  	else
	  	{
	  		
	  	  	rightLeg.rotateAngleZ = Math.abs(MathHelper.sin(tick  / 10F) * 0.1F); 
	  	  	leftLeg.rotateAngleZ = -rightLeg.rotateAngleZ;
	  	  	rightLeg.rotateAngleX = Math.abs(MathHelper.sin(tick  / 10F) * 0.2F); 
	  	  	leftLeg.rotateAngleX = rightLeg.rotateAngleZ;
	  	  	
	  	  	/*rightArm.rotateAngleX = -0.7F - MathHelper.sin(tick  / 10F) * 0.1F;
	  	  	rightArm.rotateAngleY = 0.0F;
		  	rightArm.rotateAngleZ = -0.6457718F;
	  	  	leftArm.rotateAngleX = rightArm.rotateAngleX;
	  	  	leftArm.rotateAngleY = 0.0F;
		  	leftArm.rotateAngleZ = 0.6457718F;*/
	  	  	
	  	  	rightArm.rotateAngleZ = 90F / 180F * (float)Math.PI;
	  	  	rightArm.rotateAngleY = 0.0F;
	  	  	rightArm.rotateAngleX = 0.0F;
			leftArm.rotateAngleZ = -rightArm.rotateAngleZ;
			leftArm.rotateAngleY = 0.0F;
			leftArm.rotateAngleX = 0.0F;
	  	}
    }

  }
}
