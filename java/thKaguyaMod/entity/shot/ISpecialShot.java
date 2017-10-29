package thKaguyaMod.entity.shot;

import net.minecraft.entity.Entity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/** 特殊弾を定義するインターフェース */
public interface ISpecialShot {

	/**
	 * 毎tick呼ばれる特殊弾の処理
	 * @param id 特殊弾ID
	 * @param shot 特殊動作をする弾またはレーザー
	 */
	public void specialShot_move(World world, int id, EntityTHShot shot);
	
	/**
	 * ブロックに当たったときの特殊弾の処理
	 * @param id 特殊弾ID
	 * @param shot 特殊動作をする弾またはレーザー
	 * @param movingObjectPosition 当たったブロックの情報などを持っている
	 * @return ブロックに当って消滅するならfalse、そのまま存在し続けるならtrue
	 */
	public boolean specialShot_hitBlock(World world, int id, EntityTHShot shot, MovingObjectPosition movingObjectPosition);
	
	/**
	 * Entityに当たったときの特殊弾の処理
	 * @param id 特殊弾ID
	 * @param shot 特殊動作をする弾またはレーザー
	 * @param entity_Hit 当たったEntity
	 * @return Entityに当って消滅するならfalse、そのまま存在し続けるならtrue
	 */
	public boolean specialShot_hitEntity(World world, int id, EntityTHShot shot, Entity entity_Hit);
}
