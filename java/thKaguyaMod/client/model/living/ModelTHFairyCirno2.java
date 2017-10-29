package thKaguyaMod.client.model.living;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import thKaguyaMod.entity.living.EntityTHFairy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelTHFairyCirno2 extends ModelBase
{

    ModelRenderer rightWing;
    ModelRenderer leftWing;
    ModelRenderer rightWing2;
    ModelRenderer leftWing2;
    ModelRenderer rightWing3;
    ModelRenderer leftWing3;

  
  public ModelTHFairyCirno2()
  {  
	    textureWidth = 64;
	    textureHeight = 64;


	      //rightWing.mirror = true;
	      rightWing = new ModelRenderer(this, 0, 48);
	      rightWing.addBox(-14F, 0F, 0F, 14, 3, 3);
	      rightWing.setRotationPoint(-1F, -2F, 2F);
	      //rightWing.setTextureSize(64, 64);
	      //rightWing.mirror = true;
	      setRotation(rightWing, 0F, 0.4886922F, 0.3141593F);
	      //rightWing.mirror = false;
	      leftWing = new ModelRenderer(this, 0, 48);
	      leftWing.addBox(0F, 0F, 0F, 14, 3, 3);
	      leftWing.setRotationPoint(0F, -2F, 2F);
	      //leftWing.setTextureSize(64, 64);
	      //leftWing.mirror = true;
	      setRotation(leftWing, 0F, -0.4886922F, -0.3141593F);
	      //rightWing2.mirror = true;
	      rightWing2 = new ModelRenderer(this, 0, 48);
	      rightWing2.addBox(-14F, 0F, 0F, 14, 3, 3);
	      rightWing2.setRotationPoint(-1F, 1F, 2F);
	      //rightWing2.setTextureSize(64, 64);
	      //rightWing2.mirror = true;
	      setRotation(rightWing2, 0F, 0.4886922F, -0.3141593F);
	      //rightWing2.mirror = false;
	      leftWing2 = new ModelRenderer(this, 0, 48);
	      leftWing2.addBox(0F, 0F, 0F, 14, 3, 3);
	      leftWing2.setRotationPoint(1F, 1F, 2F);
	      //leftWing2.setTextureSize(64, 64);
	      //leftWing2.mirror = true;
	      setRotation(leftWing2, 0F, -0.4886922F, 0.3141593F);
	      rightWing3 = new ModelRenderer(this, 0, 48);
	      rightWing3.addBox(-14F, 0F, 0F, 14, 3, 3);
	      rightWing3.setRotationPoint(-1F, 0F, 2F);
	      //rightWing3.setTextureSize(64, 64);
	      //rightWing3.mirror = true;
	      setRotation(rightWing3, 0F, 0.4886922F, 0F);
	      leftWing3 = new ModelRenderer(this, 0, 48);
	      leftWing3.addBox(0F, 0F, 0F, 14, 3, 3);
	      leftWing3.setRotationPoint(1F, 0F, 2F);
	      //leftWing3.setTextureSize(64, 64);
	      //leftWing3.mirror = true;
	      setRotation(leftWing3, 0F, -0.4886922F, 0F);



  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  	

    



    rightWing.render(f5);
  	rightWing2.render(f5);
  	rightWing3.render(f5);
    leftWing.render(f5);
  	leftWing2.render(f5);
  	leftWing3.render(f5);
  	
  	EntityTHFairy var8 = (EntityTHFairy)entity;


        {
            this.rightWing.setRotationPoint(0.0F, -3.0F, 2.0F);
            this.leftWing.setRotationPoint(0.0F, -3.0F, 2.0F);
        	this.rightWing2.setRotationPoint(-1.0F, 0.0F, 2.0F);
            this.leftWing2.setRotationPoint(1.0F, 0.0F, 2.0F);
            this.rightWing3.setRotationPoint(-1.0F, -1.5F, 2.0F);
            this.leftWing3.setRotationPoint(1.0F, -1.5F, 2.0F);
            //this.batBody.rotateAngleX = ((float)Math.PI / 4F) + MathHelper.cos(par4 * 0.1F) * 0.15F;
            //this.batBody.rotateAngleY = 0.0F;
            this.rightWing.rotateAngleY = MathHelper.cos(f2 * 0.3F) * (float)Math.PI * 0.1F;
            this.leftWing.rotateAngleY = -this.rightWing.rotateAngleY;
        	this.rightWing2.rotateAngleY = MathHelper.cos(f2 * 0.3F) * (float)Math.PI * 0.1F;
            this.leftWing2.rotateAngleY = -this.rightWing2.rotateAngleY;
            this.rightWing3.rotateAngleY = MathHelper.cos(f2 * 0.3F) * (float)Math.PI * 0.1F;
            this.leftWing3.rotateAngleY = -this.rightWing3.rotateAngleY;
            //this.batOuterRightWing.rotateAngleY = this.batRightWing.rotateAngleY * 0.5F;
            //this.batOuterLeftWing.rotateAngleY = -this.batRightWing.rotateAngleY * 0.5F;
        }
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity par7Entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, par7Entity);
  }
}
