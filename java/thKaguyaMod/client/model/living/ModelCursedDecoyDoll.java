package thKaguyaMod.client.model.living;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCursedDecoyDoll extends ModelBase
{
  //fields
    ModelRenderer bipedBody;
    ModelRenderer bipedRightArm;
    ModelRenderer bipedLeftArm;
    ModelRenderer bipedRightLeg;
    ModelRenderer bipedLeftLeg;
    ModelRenderer skirtTop;
    ModelRenderer bipedHead;
    ModelRenderer longHair;
    ModelRenderer leftBraid;
    ModelRenderer rightBraid;
    ModelRenderer rightRibbon;
    ModelRenderer leftRibbon;
  
  public ModelCursedDecoyDoll()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      bipedBody = new ModelRenderer(this, 32, 8);
      bipedBody.addBox(-3F, 0F, -2F, 6, 6, 4);
      bipedBody.setRotationPoint(0F, 10F, 0F);
      bipedBody.setTextureSize(64, 64);
      bipedBody.mirror = true;
      setRotation(bipedBody, 0F, 0F, 0F);
      bipedRightArm = new ModelRenderer(this, 32, 0);
      bipedRightArm.addBox(0F, -1F, -1F, 4, 6, 2);
      bipedRightArm.setRotationPoint(-4F, 11F, 0F);
      bipedRightArm.setTextureSize(64, 64);
      bipedRightArm.mirror = true;
      setRotation(bipedRightArm, 0F, 0F, 0.5235988F);
      bipedLeftArm = new ModelRenderer(this, 32, 0);
      bipedLeftArm.addBox(-4F, -1F, -1F, 4, 6, 2);
      bipedLeftArm.setRotationPoint(4F, 11F, 0F);
      bipedLeftArm.setTextureSize(64, 64);
      bipedLeftArm.mirror = true;
      setRotation(bipedLeftArm, 0F, 0F, -0.5235988F);
      bipedRightLeg = new ModelRenderer(this, 36, 22);
      bipedRightLeg.addBox(-1F, 0F, -2F, 3, 7, 4);
      bipedRightLeg.setRotationPoint(-2F, 17F, 0F);
      bipedRightLeg.setTextureSize(64, 64);
      bipedRightLeg.mirror = true;
      setRotation(bipedRightLeg, 0F, 0F, 0F);
      bipedLeftLeg = new ModelRenderer(this, 36, 22);
      bipedLeftLeg.addBox(-2F, 0F, -2F, 3, 7, 4);
      bipedLeftLeg.setRotationPoint(2F, 17F, 0F);
      bipedLeftLeg.setTextureSize(64, 64);
      bipedLeftLeg.mirror = true;
      setRotation(bipedLeftLeg, 0F, 0F, 0F);
      skirtTop = new ModelRenderer(this, 0, 16);
      skirtTop.addBox(-4F, 0F, -5F, 8, 7, 6);
      skirtTop.setRotationPoint(0F, 16F, 2F);
      skirtTop.setTextureSize(64, 64);
      skirtTop.mirror = true;
      setRotation(skirtTop, 0F, 0F, 0F);
      bipedHead = new ModelRenderer(this, 0, 0);
      bipedHead.addBox(-4F, -8F, -4F, 8, 8, 8);
      bipedHead.setRotationPoint(0F, 10F, 0F);
      bipedHead.setTextureSize(64, 64);
      bipedHead.mirror = true;
      setRotation(bipedHead, 0F, 0F, 0F);
      longHair = new ModelRenderer(this, 0, 49);
      longHair.addBox(-5F, 0F, -5F, 10, 7, 8);
      longHair.setRotationPoint(0F, 4F, 2F);
      longHair.setTextureSize(64, 64);
      longHair.mirror = true;
      setRotation(longHair, 0F, 0F, 0F);
      leftBraid = new ModelRenderer(this, 0, 32);
      leftBraid.addBox(0F, 0F, -1F, 1, 6, 1);
      leftBraid.setRotationPoint(3F, 5F, -3F);
      leftBraid.setTextureSize(64, 64);
      leftBraid.mirror = true;
      setRotation(leftBraid, -0.2443461F, 0F, 0F);
      rightBraid = new ModelRenderer(this, 0, 32);
      rightBraid.addBox(-1F, 0F, -1F, 1, 6, 1);
      rightBraid.setRotationPoint(-3F, 5F, -3F);
      rightBraid.setTextureSize(64, 64);
      rightBraid.mirror = true;
      setRotation(rightBraid, -0.2443461F, 0F, 0F);
      leftRibbon = new ModelRenderer(this, 46, 59);
      leftRibbon.addBox(0F, -3F, 0F, 5, 4, 1);
      leftRibbon.setRotationPoint(-4F, 4F, 0F);
      leftRibbon.setTextureSize(64, 64);
      leftRibbon.mirror = true;
      setRotation(leftRibbon, 0.4461433F, 0F, -0.7853982F);
      rightRibbon = new ModelRenderer(this, 46, 59);
      rightRibbon.addBox(0F, -3F, -1F, 5, 4, 1);
      rightRibbon.setRotationPoint(-4F, 4F, 0F);
      rightRibbon.setTextureSize(64, 64);
      rightRibbon.mirror = true;
      setRotation(rightRibbon, -0.4461411F, 3.141593F, 0.3490659F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    bipedBody.render(f5);
    bipedRightArm.render(f5);
    bipedLeftArm.render(f5);
    bipedRightLeg.render(f5);
    bipedLeftLeg.render(f5);
    skirtTop.render(f5);
    bipedHead.render(f5);
    longHair.render(f5);
    leftBraid.render(f5);
    rightBraid.render(f5);
    leftRibbon.render(f5);
    rightRibbon.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }
}