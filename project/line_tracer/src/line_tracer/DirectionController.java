/**
 * 
 */
package line_tracer;

/**
 * @author usamimasanori
 * 方向を指示すると車輪を操作してくれる制御器
 */
public interface DirectionController {
	/**
	 * 操作対象の車輪を設定する
	 * @param right 右の車輪
	 * @param left 左の車輪
	 */
	void setWheel(Wheel right, Wheel left);
	
	/**
	 * 進行方向を設定する
	 * @param direction 進行方向（-100：左~100：右）
	 */
	void setDirection(int direction);
	
	/**
	 * 現在の進行方向を戻り値として返す
	 * @return 現在の進行方向（-100：左〜100：右）
	 */
	int getDirection();
	
	/**
	 * 
	 */
	void stop();
}
