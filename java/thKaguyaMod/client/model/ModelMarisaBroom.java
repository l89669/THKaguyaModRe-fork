package thKaguyaMod.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelMarisaBroom extends ModelBase
{
    public ModelRenderer[] broom = new ModelRenderer[5];

    public ModelMarisaBroom()
    {
        this.broom[0] = new ModelRenderer(this, 0, 8);
        this.broom[1] = new ModelRenderer(this, 0, 0);
        this.broom[2] = new ModelRenderer(this, 0, 0);
        this.broom[3] = new ModelRenderer(this, 0, 0);
        this.broom[4] = new ModelRenderer(this, 0, 0);
        byte b0 = 24;
        byte b1 = 6;
        byte b2 = 20;
        byte b3 = 4;
        this.broom[0].addBox((float)(-b0 / 2), (float)(-b2 / 2 + 2), -3.0F, b0, b2 - 4, 4, 0.0F);
        this.broom[0].setRotationPoint(0.0F, (float)b3, 0.0F);
        this.broom[1].addBox((float)(-b0 / 2 + 2), (float)(-b1 - 1), -1.0F, b0 - 4, b1, 2, 0.0F);
        this.broom[1].setRotationPoint((float)(-b0 / 2 + 1), (float)b3, 0.0F);
        this.broom[2].addBox((float)(-b0 / 2 + 2), (float)(-b1 - 1), -1.0F, b0 - 4, b1, 2, 0.0F);
        this.broom[2].setRotationPoint((float)(b0 / 2 - 1), (float)b3, 0.0F);
        this.broom[3].addBox((float)(-b0 / 2 + 2), (float)(-b1 - 1), -1.0F, b0 - 4, b1, 2, 0.0F);
        this.broom[3].setRotationPoint(0.0F, (float)b3, (float)(-b2 / 2 + 1));
        this.broom[4].addBox((float)(-b0 / 2 + 2), (float)(-b1 - 1), -1.0F, b0 - 4, b1, 2, 0.0F);
        this.broom[4].setRotationPoint(0.0F, (float)b3, (float)(b2 / 2 - 1));
        this.broom[0].rotateAngleX = ((float)Math.PI / 2F);
        this.broom[1].rotateAngleY = ((float)Math.PI * 3F / 2F);
        this.broom[2].rotateAngleY = ((float)Math.PI / 2F);
        this.broom[3].rotateAngleY = (float)Math.PI;
    }

    @Override
    public void render(Entity entity, float movement, float far, float tick, float yaw, float pitch, float size)
    {
        for (int i = 0; i < 5; ++i)
        {
            this.broom[i].render(size);
        }
    }
}
