package thKaguyaMod.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelMiniHakkero extends ModelBase
{
	
    public ModelRenderer miniHakkero[];

    public ModelMiniHakkero()
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
       
        miniHakkero = new ModelRenderer[2];
       //持つ場所
        miniHakkero[0] = new ModelRenderer(this, 0, 15);//テクスチャ座標0,0を左上にする
       
       //ボックスを追加する。
       //addBox(X座標、Z座標、Y座標、X方向のサイズ、Z方向のサイズ、Y方向のサイズ、？（倍率？）)
       //サイズはテクスチャのサイズそのもの
       miniHakkero[0].addBox(-8, -8, 0, 16, 16 , 1, 0.0F);
       miniHakkero[1] = new ModelRenderer(this, 0, 15);
       miniHakkero[1].addBox(-8, -8,  5, 16, 16 , 1, 0.0F);
    }

    @Override
    public void render(Entity entity, float movement, float far, float tick, float yaw, float pitch, float size)
    {
        for (int i = 0; i < 2; i++)
    	{
    		miniHakkero[i].render(size);
    	}
    }

    @Override
    public void setRotationAngles(float movement, float far, float tick, float yaw, float pitch, float size, Entity entity)
    {
    }
}
