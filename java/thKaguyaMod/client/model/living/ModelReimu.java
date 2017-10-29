package thKaguyaMod.client.model.living;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelReimu extends ModelBase {

	public ModelRenderer bipedBody;
	public ModelRenderer bipedHead;
	public ModelRenderer bipedRightArm;
	public ModelRenderer bipedLeftArm;
	public ModelRenderer bipedRightLeg;
	public ModelRenderer bipedLeftLeg;
	public ModelRenderer skirtTop;
	public ModelRenderer skirtBottom;
	public ModelRenderer handRight;
	public ModelRenderer handLeft;
	public ModelRenderer longHair;
	public ModelRenderer braidRight;
	public ModelRenderer braidLeft;
	public ModelRenderer rightRibbon;
	public ModelRenderer rightRibbon2;
	public ModelRenderer leftRibbon;
	public ModelRenderer leftRibbon2;
	
	protected float legHeight = 12F;
	protected float bodyHeight = 9F;
	
    public ModelReimu()
    {
        this(0.0F);
    }

    public ModelReimu(float size)
    {
        this(size, 0.0F, 64, 64);
    }

	public ModelReimu(float size, float yOffset, int textureXSize, int textureH)
	{
		textureHeight = 64;
		textureWidth = 64;

		
	      bipedBody = new ModelRenderer(this, 32, 8);
	      bipedBody.addBox(-3F, -1F, -2F, 6, 9, 4);
	      bipedBody.setRotationPoint(0F, 3F, 0F);
	      bipedBody.setTextureSize(64, 64);
	      bipedBody.mirror = true;
	      setRotation(bipedBody, 0F, 0F, 0F);
	      bipedRightArm = new ModelRenderer(this, 48, 0);
	      bipedRightArm.addBox(-1F, 0F, -1F, 4, 7, 2);
	      bipedRightArm.setRotationPoint(-3F, 3F, 0F);
	      bipedRightArm.setTextureSize(64, 64);
	      bipedRightArm.mirror = true;
	      setRotation(bipedRightArm, 0F, 0F, 0F);
	      handRight = new ModelRenderer(this, 52, 9);
	      handRight.addBox( -1F, 1F, -1F, 2, 1, 2);
	      handRight.setRotationPoint(-0F, 6F, 0F);
	      handRight.setTextureSize(64, 64);
	      handRight.mirror = true;
	      setRotation(handRight, 0F, 0F, 0F);
	      bipedLeftArm = new ModelRenderer(this, 48, 0);
	      bipedLeftArm.addBox(-3F, 0F, -1F, 4, 7, 2);
	      bipedLeftArm.setRotationPoint(3F, 3F, 0F);
	      bipedLeftArm.setTextureSize(64, 64);
	      bipedLeftArm.mirror = true;
	      setRotation(bipedLeftArm, 0F, 0F, 0F);
	      handLeft = new ModelRenderer(this, 52, 9);
	      handLeft.addBox(-1F, 1F, -1F, 2, 1, 2);
	      handLeft.setRotationPoint(0F, 6F, 0F);
	      handLeft.setTextureSize(64, 64);
	      handLeft.mirror = true;
	      setRotation(handLeft, 0F, 0F, 0F);
	      bipedRightLeg = new ModelRenderer(this, 50, 18);
	      bipedRightLeg.addBox(-1F, 0F, -2F, 3, 12, 4);
	      bipedRightLeg.setRotationPoint(-2F, 12F, 0F);
	      bipedRightLeg.setTextureSize(64, 64);
	      bipedRightLeg.mirror = true;
	      setRotation(bipedRightLeg, 0F, 0F, 0F);
	      bipedLeftLeg = new ModelRenderer(this, 50, 18);
	      bipedLeftLeg.addBox(-2F, 0F, -2F, 3, 12, 4);
	      bipedLeftLeg.setRotationPoint(2F, 12F, 0F);
	      bipedLeftLeg.setTextureSize(64, 64);
	      bipedLeftLeg.mirror = true;
	      setRotation(bipedLeftLeg, 0F, 0F, 0F);
	      skirtTop = new ModelRenderer(this, 0, 16);
	      skirtTop.addBox(-4F, 0F, -4F, 8, 4, 8);
	      skirtTop.setRotationPoint(0F, 9F, 0F);
	      skirtTop.setTextureSize(64, 64);
	      skirtTop.mirror = true;
	      setRotation(skirtTop, 0F, 0F, 0F);
	      skirtBottom = new ModelRenderer(this, 16, 33);
	      skirtBottom.addBox(-5F, 0F, -5F, 10, 7, 10);
	      skirtBottom.setRotationPoint(0F, 13F, 0F);
	      skirtBottom.setTextureSize(64, 64);
	      skirtBottom.mirror = true;
	      setRotation(skirtBottom, 0F, 0F, 0F);
	      bipedHead = new ModelRenderer(this, 0, 0);
	      bipedHead.addBox(-4F, -8F, -4F, 8, 8, 8);
	      bipedHead.setRotationPoint(0F, 3F, 0F);
	      bipedHead.setTextureSize(64, 64);
	      bipedHead.mirror = true;
	      setRotation(bipedHead, 0F, 0F, 0F);
	      longHair = new ModelRenderer(this, 0, 50);
	      longHair.addBox(-4F, -2F, -3F, 8, 9, 3);
	      longHair.setRotationPoint(0F, 0F, 4F);
	      longHair.setTextureSize(64, 64);
	      longHair.mirror = true;
	      setRotation(longHair, 0.2094395F, 0F, 0F);
	      braidLeft = new ModelRenderer(this, 0, 32);
	      braidLeft.addBox(0F, -3F, -2F, 1, 6, 1);
	      braidLeft.setRotationPoint(3F, 0F, -3F);
	      braidLeft.setTextureSize(64, 64);
	      braidLeft.mirror = true;
	      setRotation(braidLeft, -0.2443461F, 0F, 0F);
	      braidRight = new ModelRenderer(this, 0, 32);
	      braidRight.addBox(-1F, -3F, -2F, 1, 6, 1);
	      braidRight.setRotationPoint(-3F, 0F, -3F);
	      braidRight.setTextureSize(64, 64);
	      braidRight.mirror = true;
	      setRotation(braidRight, -0.2443461F, 0F, 0F);
	      leftRibbon = new ModelRenderer(this, 32, 56);
	      leftRibbon.addBox(1F, -6F, 1F, 8, 7, 1);
	      leftRibbon.setRotationPoint(0F, -3F, 4F);
	      leftRibbon.setTextureSize(64, 64);
	      leftRibbon.mirror = true;
	      setRotation(leftRibbon, 0.4461433F, 0F, -0.4089647F);
	      rightRibbon = new ModelRenderer(this, 32, 56);
	      rightRibbon.addBox(1F, -6F, -2F, 8, 7, 1);
	      rightRibbon.setRotationPoint(0F, -3F, 4F);
	      rightRibbon.setTextureSize(64, 64);
	      rightRibbon.mirror = true;
	      setRotation(rightRibbon, -0.4461411F, 3.141593F, -0.4089647F + (float)Math.PI / 4.0F);
	      rightRibbon2 = new ModelRenderer(this, 32, 56);
	      rightRibbon2.addBox(-1F, -5F, -1F, 8, 3, 1);
	      rightRibbon2.setRotationPoint(0F, -3F, 5F);
	      rightRibbon2.setTextureSize(64, 64);
	      rightRibbon2.mirror = true;
	      setRotation(rightRibbon2, -0.260248F, 3.141593F, -0.7063936F);
	      leftRibbon2 = new ModelRenderer(this, 32, 56);
	      leftRibbon2.addBox(-1F, -5F, 0F, 8, 3, 1);
	      leftRibbon2.setRotationPoint(0F, -3F, 5F);
	      leftRibbon2.setTextureSize(64, 64);
	      leftRibbon2.mirror = true;
	      setRotation(leftRibbon2, 0.260246F, 0F, 0.7063936F);
	      
	      bipedBody.addChild(bipedHead);
	      bipedBody.addChild(bipedRightArm);
	      bipedBody.addChild(bipedLeftArm);
	      bipedBody.addChild(bipedRightLeg);
	      bipedBody.addChild(bipedLeftLeg);
	      bipedRightArm.addChild(handRight);
	      bipedLeftArm.addChild(handLeft);
	      //bipedBody.addChild(skirtTop);
	      bipedHead.addChild(braidRight);
	      bipedHead.addChild(braidLeft);
	      bipedHead.addChild(rightRibbon);
	      bipedHead.addChild(rightRibbon2);
	      bipedHead.addChild(leftRibbon);
	      bipedHead.addChild(leftRibbon2);
	      bipedHead.addChild(longHair);
		  bipedBody.addChild(skirtTop);
		  skirtTop.addChild(skirtBottom);
		  
		  
	}
	
    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entity, float moveCounter, float moveCycle, float ticksExisted, float headYaw, float headPitch, float scale)
    {
    	this.setRotationAngles(moveCounter, moveCycle, ticksExisted, headYaw, headPitch, scale, entity);
    	bipedBody.render(scale);
    }

	@Override
	public void setRotationAngles(float moveCounter, float moveCycle, float ticksExisted, float pheadYaw, float pheadPitch, float scale, Entity entity)
	{
		
		//f = 移動時に増加する
		//f1 = 増加したり戻ったり。スニーク、移動時は変化量が少し。
		//ticksExisted = 時間で常に増え続けている
		//pheadYaw = 向いている方角方向で変化
		//pheadPitch = 向いている上下方向で変化
		//scale = スケール値？
		setDefaultPause(moveCounter, moveCycle, ticksExisted, pheadYaw, pheadPitch, scale, entity);
		bipedBody.rotationPointY = 5F;
		
		bipedRightLeg.rotationPointX = -2.0F;
		bipedLeftLeg.rotationPointX = 2.0F;
		//bipedBody.rotateAngleY = AS.getFloat(AS.mathHelperCos, f * 0.6662F + (float) Math.PI) * 1.4F * f1;
		bipedHead.rotationPointY = -1.0F;
		bipedHead.rotateAngleY = pheadYaw / 57.29578F;
		bipedHead.rotateAngleX = pheadPitch / 57.29578F;
		//bipedRightArm.rotateAngleY = 90F / 180F * 3.141593F;
		//bipedLeftArm.rotateAngleY = 90F / 180F * 3.141593F;
		
		bipedRightArm.rotateAngleX = MathHelper.cos(moveCounter * 0.6662F + 3.141593F) * 2.0F * moveCycle * 0.5F;
		bipedLeftArm.rotateAngleX = MathHelper.cos(moveCounter * 0.6662F) * 2.0F * moveCycle * 0.5F;
		bipedRightArm.rotateAngleZ = 30F / 180F * 3.141593F;
		bipedLeftArm.rotateAngleZ = -30F / 180F * 3.141593F;
		this.skirtTop.rotateAngleX = 0.0F;
		this.skirtBottom.rotationPointZ = 0.0F;
		this.skirtBottom.rotateAngleX = 0.0F;
		this.bipedLeftArm.rotationPointY = -1F;
		this.bipedRightArm.rotationPointY = -1F;
		
		if (entity.isSneaking()){
			bipedBody.rotateAngleY = MathHelper.cos(moveCounter * 0.6662F + (float) Math.PI) * 2.4F * moveCycle;
			skirtTop.rotateAngleY = MathHelper.cos(moveCounter * 0.6662F + (float) Math.PI) * 2.4F * moveCycle;
			bipedRightArm.rotateAngleX = MathHelper.cos(moveCounter * 0.6662F + (float) Math.PI) * 1.4F * moveCycle;
			bipedLeftArm.rotateAngleX = MathHelper.cos(moveCounter * 0.6662F) * 1.4F * moveCycle;
			//bipedLeftArm.rotateAngleZ = (MathHelper.cos(moveCounter * 0.2812F) - 1.0F) * 1.0F * moveCycle;
			//bipedRightArm.rotateAngleZ = (MathHelper.cos(moveCounter * 0.2312F) + 1.0F) * 1.0F * moveCycle;
		} else {
			
			//bipedRightArm.rotateAngleZ = 0.0F;
			//bipedLeftArm.rotateAngleZ = 0.0F;
		}
		
		bipedRightLeg.rotateAngleX = MathHelper.cos(moveCounter * 0.6662F) * 0.5F * moveCycle;//AS.getFloat(AS.mathHelperCos, f * 0.6662F) * 1.4F * f1;
		bipedLeftLeg.rotateAngleX = MathHelper.cos(moveCounter * 0.6662F + (float)Math.PI) * 0.5F * moveCycle;//AS.getFloat(AS.mathHelperCos, f * 0.6662F + 3.141593F) * 1.4F * f1;
		bipedRightLeg.rotateAngleY = bipedLeftLeg.rotateAngleY = bipedRightLeg.rotateAngleZ = bipedLeftLeg.rotateAngleZ = 0.0F;
		
		if (entity.isRiding()) {
			// 乗り物に乗っている
			this.bipedBody.rotationPointY = -1F;
			this.bipedRightLeg.rotationPointY = 5.0F;
			this.bipedLeftLeg.rotationPointY = 5.0F;
			this.bipedRightArm.rotateAngleX += -((float)Math.PI / 5F);
			this.bipedLeftArm.rotateAngleX += -((float)Math.PI / 5F);
			this.bipedRightLeg.rotateAngleX = -((float)Math.PI * 2F / 5F);
			this.bipedLeftLeg.rotateAngleX = -((float)Math.PI * 2F / 5F);
			this.bipedRightLeg.rotateAngleY = ((float)Math.PI / 14F);
			this.bipedLeftLeg.rotateAngleY = -((float)Math.PI / 14F);
			this.bipedRightLeg.rotateAngleZ = ((float)Math.PI / 14F);
			this.bipedLeftLeg.rotateAngleZ = -((float)Math.PI / 14F);
	          
	          skirtTop.rotationPointY = 5F;
	          skirtTop.rotationPointZ = 0F;
	          this.skirtTop.rotateAngleX = -((float)Math.PI / 5F);
	          skirtBottom.rotationPointZ =  0.0F;
	          skirtBottom.rotationPointY =  4F;
	          this.skirtBottom.rotateAngleX = -((float)Math.PI / 4F);
		} else {
			skirtTop.rotationPointY = 5F;
			skirtTop.rotationPointZ = 0F;
			skirtBottom.rotationPointZ = 0.0F;
	        skirtBottom.rotationPointY =  4.0F;
			this.bipedRightLeg.rotationPointY = 7.0F;
			this.bipedLeftLeg.rotationPointY = 7.0F;
			//setRotationAnglesGulliverBefore(f, f1, ticksExisted, pheadYaw, pheadPitch, scale, entity);
		}

		bipedRightArm.rotateAngleY = bipedLeftArm.rotateAngleY = 0.0F;

		bipedRightLeg.rotationPointZ = bipedLeftLeg.rotationPointZ =
				bipedBody.rotateAngleX = /*bipedBody.rotateAngleY =*/ bipedBody.rotateAngleZ = 0.0F;
		
		if (entity.isSneaking()) {
			// しゃがみ
			bipedHead.rotateAngleX -= 0.5F;
			//skirtTop.rotateAngleX = -upperBody.rotateAngleX;
		} else {
			// 通常立ち
		}

		//setRotationAnglesGulliverAfter(f, f1, ticksExisted, pheadYaw, pheadPitch, scale, entity);
	}
	
	public void setDefaultPause(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		if (bipedHead != null) bipedHead.setRotationPoint(0.0F, 5.0F, 0.0F);
		//if (bipedHeadwear != null) bipedHeadwear.setRotationPoint(0.0F, 0.0F, 0.0F);
		if (bipedBody != null) bipedBody.setRotationPoint(0.0F, -5.0F, 0.0F);
		if (bipedRightArm != null) bipedRightArm.setRotationPoint(-3.0F, 6.0F, 0.0F);
		if (bipedLeftArm != null) bipedLeftArm.setRotationPoint(3.0F, 6.0F, 0.0F);
		if (bipedRightLeg != null) bipedRightLeg.setRotationPoint(-2.0F, bodyHeight + 5F, 0.0F);
		if (bipedLeftLeg != null) bipedLeftLeg.setRotationPoint(2.0F, bodyHeight + 5F, 0.0F);
	}
	
	  private void setRotation(ModelRenderer model, float rotateX, float rotateY, float rotateZ)
	  {
		  model.rotateAngleX = rotateX;
		  model.rotateAngleY = rotateY;
		  model.rotateAngleZ = rotateZ;
	  }
}