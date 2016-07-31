/**
 * 
 */
package line_tracer;

/**
 * @author usamimasanori
 *
 */
public interface UltrasonicSensor {
	
	/**
	 * 距離の取得
	 */
	float getDistance();
	
	/**
	 * 障害物の有無の取得
	 * 障害物と判断する距離は別途閾値を設定する
	 * @return boolean 障害物の有無（あり：true, なし：false)
	 */
	boolean doesExist();
	
	/**
	 * 障害物と判断する距離の設定
	 * @param threashold 障害物と判断する距離（単位：cm）
	 */
	void setThreashold(float threashold);
	
	/**
	 * 障害物と判断する距離の閾値の取得
	 * @return threashold 障害物と判断する距離（単位：cm）の設定値
	 */
	float getThreashold();

}
