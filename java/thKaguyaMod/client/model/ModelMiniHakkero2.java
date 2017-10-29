package thKaguyaMod.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelMiniHakkero2 extends ModelBase
{	
    public ModelRenderer miniHakkero[];

    public ModelMiniHakkero2()
    {
       /*Minecraftのモデルの基本事項
       基本的にテクスチャに64x32の画像を使用
       ボックスタイプのモデルしか扱えない
       ボックスのサイズ＝テクスチャのサイズと考えていい
       例えば横幅が10の箱ならば、そこに設定されるテクスチャも10ドット分使用する
       横幅6、高さ4、奥行き3の箱を考えたとき、イメージとしてはテクスチャはこのようになる
       000000111111222222000000
       000000111111222222000000
       000000111111222222000000
       333333444444555555666666
       333333444444555555666666
       333333444444555555666666
       333333444444555555666666
       0は未使用部と考えて、ボックス型は6面存在するためこのように６つの部分に分けれれることになる。
       この配置は人間の頭で言うと、
       1は頭の上　2は頭の下　3は頭右　4は顔　5は頭左　6は頭後ろにあたる
       一番左上にあたる座標はModelRenderer(this,x,y)で設定可能。
       */
       
        miniHakkero = new ModelRenderer[8];
       
       float pi8 = (float)Math.PI / 4.0F;
       float angle = 0.0F;
       //側面
       for(int i = 0; i < 8; i++)
       {
          miniHakkero[i] = new ModelRenderer(this, 32, 0);
          
          //ここの-0.001Fの部分で前にずらしている、ここ以外は数字を合わせただけ
          miniHakkero[i].addBox( -4, -10, -0.001F, 8, 2 , 6, 0.0F);
          
          
          miniHakkero[i].setRotationPoint(0.0F, 0.0F, 0.0F);
          miniHakkero[i].rotateAngleZ = angle;
          angle += pi8;
       }
    }

    @Override
    public void render(Entity entity, float movement, float far, float tick, float yaw, float pitch, float size)
    {
        for (int i = 0; i < 8; i++)
    	{
    		miniHakkero[i].render(size);
    	}
    }

    @Override
    public void setRotationAngles( float movement, float far, float tick, float yaw, float pitch, float size, Entity entity)
    {
    }
}
