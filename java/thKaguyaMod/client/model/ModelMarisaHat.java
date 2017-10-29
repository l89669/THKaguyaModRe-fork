package thKaguyaMod.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMarisaHat extends ModelBiped
{
    ModelRenderer hatBase;
    ModelRenderer hatBase2;
    ModelRenderer hatBase3;
    ModelRenderer hatBase4;
    ModelRenderer brim;
    ModelRenderer ribbonRight;
    ModelRenderer ribbonLeft;
  
  public ModelMarisaHat(float scale, float yOffset, int width, int height)
  {
    textureWidth = width;
    textureHeight = height;
    
      hatBase = new ModelRenderer(this, 0, 0);
      hatBase.addBox(-4F, -8F, -4F, 8, 4, 8, scale);
      hatBase.setRotationPoint(0F, 0F, 0F);
      hatBase.setTextureSize(64, 32);
      hatBase.mirror = true;
      bipedHead.addChild(hatBase);
      setRotation(hatBase, 0F, 0F, 0F);
      hatBase2 = new ModelRenderer(this, 32, 2);
      hatBase2.addBox(-3F, -3F, -3F, 6, 3, 6, scale);
      hatBase2.setRotationPoint(0F, -7F, 0F);
      hatBase2.setTextureSize(64, 32);
      hatBase2.mirror = true;
      hatBase.addChild(hatBase2);
      setRotation(hatBase2, -0.2792527F, 0F, 0F);
      hatBase3 = new ModelRenderer(this, 48, 16);
      hatBase3.addBox(-2F, -3F, -2F, 4, 3, 4, scale);
      hatBase3.setRotationPoint(0F, -9F, 0F);
      hatBase3.setTextureSize(64, 32);
      hatBase3.mirror = true;
      hatBase.addChild(hatBase3);
      setRotation(hatBase3, -0.5410521F, 0F, 0F);
      hatBase4 = new ModelRenderer(this, 48, 24);
      hatBase4.addBox(-1F, -3F, -1F, 2, 3, 2, scale);
      hatBase4.setRotationPoint(0F, -11F, 1F);
      hatBase4.setTextureSize(64, 32);
      hatBase4.mirror = true;
      hatBase.addChild(hatBase4);
      setRotation(hatBase4, -0.8203047F, 0F, 0F);
      brim = new ModelRenderer(this, 0, 16);
      brim.addBox(-6F, 0F, -6F, 12, 1, 12, scale);
      brim.setRotationPoint(0F, -6F, 0F);
      brim.setTextureSize(64, 32);
      brim.mirror = true;
      hatBase.addChild(brim);
      setRotation(brim, 0F, 0F, 0F);
      ribbonRight = new ModelRenderer(this, 44, 12);
      ribbonRight.addBox(0F, -2F, -1F, 4, 3, 1, scale);
      ribbonRight.setRotationPoint(0.5F, -6F, -4F);
      ribbonRight.setTextureSize(64, 32);
      ribbonRight.mirror = true;
      hatBase.addChild(ribbonRight);
      setRotation(ribbonRight, 0F, 0F, -0.3490659F);
      ribbonLeft = new ModelRenderer(this, 32, 12);
      ribbonLeft.addBox(-4F, -2F, -1F, 4, 3, 1, scale);
      ribbonLeft.setRotationPoint(-0.5F, -6F, -4F);
      ribbonLeft.setTextureSize(64, 32);
      ribbonLeft.mirror = true;
      hatBase.addChild(ribbonLeft);
      setRotation(ribbonLeft, 0F, 0F, 0.3490659F);
  }
  
  @Override
  public void render(Entity entity, float movement, float far, float tick, float yaw, float pitch, float size)
  {
    setRotationAngles(movement, far, tick, yaw, pitch, size, entity);
    bipedHead.render(size);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  @Override
  public void setRotationAngles(float movement, float far, float tick, float yaw, float pitch, float size, Entity entity)
  {
    super.setRotationAngles(movement, far, tick, yaw, pitch, size, entity);
  }

}
