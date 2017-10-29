package thKaguyaMod.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSuwakoHat extends ModelBiped
{
    ModelRenderer hatBase;
    ModelRenderer eyeright;
    ModelRenderer eyeleft;
    ModelRenderer brim;
	
    public ModelSuwakoHat(float scale, float yOffset, int width, int height)
    {
        textureWidth = width;
        textureHeight = height;
        
        
        hatBase = new ModelRenderer(this, 0, 0);
        hatBase.addBox(-4F, -9F, -4F, 8, 4, 8, scale);
        hatBase.setRotationPoint(0F, 0F, 0F);
        hatBase.setTextureSize(64, 32);
        hatBase.mirror = true;
        bipedHead.addChild(hatBase);
        setRotation(hatBase, 0F, 0F, 0F);
        eyeright = new ModelRenderer(this, 0, 0);
        eyeright.addBox(-1F, -2F, -1F, 2, 2, 2, scale);
        eyeright.setRotationPoint(-4F, -9F, -4F);
        eyeright.setTextureSize(64, 32);
        eyeright.mirror = true;
        hatBase.addChild(eyeright);
        setRotation(eyeright, 0F, 0F, 0F);
        eyeleft = new ModelRenderer(this, 24, 0);
        eyeleft.addBox(-1F, -2F, -1F, 2, 2, 2, scale);
        eyeleft.setRotationPoint(4F, -9F, -4F);
        eyeleft.setTextureSize(64, 32);
        eyeleft.mirror = true;
        hatBase.addChild(eyeleft);
        setRotation(eyeleft, 0F, 0F, 0F);
        brim = new ModelRenderer(this, 0, 16);
        brim.addBox(-6F, -1F, -6F, 12, 1, 12, scale);
        brim.setRotationPoint(0F, -5F, 0F);
        brim.setTextureSize(64, 32);
        brim.mirror = true;
        hatBase.addChild(brim);
        setRotation(brim, 0F, 0F, 0F);
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
}
