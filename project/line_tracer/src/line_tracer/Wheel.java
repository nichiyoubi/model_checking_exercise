/**
 * 
 */
package line_tracer;

/**
 * 車輪インターフェース
 * 前進、後退を速度指定で指示できます
 * @author usamimasanori
 *
 */
public interface Wheel {
	/**
	 * 最大速度を返します
	 * @return int 最大速度を返します（0-100%）
	 */
	int getMaximumSpeed();
	
	/**
	 * 最小速度を返します
	 * @return int 最小速度を返します（0-100%）
	 */
	int getMinimumSpeed();
	
	/**
	 * 速度を設定します。
	 * @param int 速度（0-100%)
	 */
	void setSpeed(int power);
	
	/**
	 * 現在の速度を返します
	 * @return int 速度（0-100%)
	 */
	int getSpeed();
	
	/**
	 * 強制停止します
	 */
	void stop();
	
	/**
	 * 前進します
	 */
	void forward();
	
	/**
	 * 後退します
	 */
	void backward();
}
