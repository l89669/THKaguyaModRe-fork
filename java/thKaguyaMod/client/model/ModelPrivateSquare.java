package thKaguyaMod.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/** 咲夜の懐中時計のモデル */
@SideOnly(Side.CLIENT)
public class ModelPrivateSquare extends ModelBase
{
    
    /** 時計本体 */
    public ModelRenderer watchBase;
    /** 時計中心 */
    public ModelRenderer watchCenter;
    /** 時計取っ手 */
    public ModelRenderer watchHandle;
    /** 時計カバー */
    public ModelRenderer watchCover;

    /** 咲夜の懐中時計のコンストラクタ */
    public ModelPrivateSquare()
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
    	//本体最前部
        watchBase = new ModelRenderer(this, 0, 0);//テクスチャ座標0,0を左上にする
    	
    	//ボックスを追加する。
    	//addBox(X座標、Z座標、Y座標、X方向のサイズ、Z方向のサイズ、Y方向のサイズ、？（倍率？）)
    	//サイズはテクスチャのサイズそのもの
    	watchBase.addBox(-6, -6, -2, 12,12 , 4, 0.0F);
    	
    	// 時計の中心
    	watchCenter = new ModelRenderer(this, 28, 14);
    	watchCenter.addBox(-8, -8,  -1, 16,16 , 2, 0.0F);

    	// 時計の把手
    	watchHandle = new ModelRenderer(this, 32, 0);
    	watchHandle.addBox(-4, 8, 0, 8,6 , 0, 0.0F);
    	
    	// 時計のカバー
    	watchCover = new ModelRenderer(this, 48, 16);
    	watchCover.addBox(-8, -8, 0, 16,16 , 0, 0.0F);
        watchCover.setRotationPoint(0.0F, -16.0F, -4.0F);
        watchCover.rotateAngleX = ((float)Math.PI / 6F);
    }

    @Override
    public void render(Entity entity, float movement, float far, float tick, float yaw, float pitch, float size)
    {
    	watchBase.render( size );
    	watchCenter.render( size );
    	watchHandle.render( size );
    	watchCover.render( size );
    }
}
