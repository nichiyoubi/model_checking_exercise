package line_tracer_test;
/**
 * 
 */


import line_tracer.LightSensor;

/**
 * @author usamimasanori
 *
 */
public class MockLightSensor implements LightSensor {
	float value_;
	float threashold_;

	/* 光センサーの生�?�を返す
	 * @see line_tracer.LightSensor#getValue()
	 */
	@Override
	public float getValue() {
		return value_;
	}

	/**
	 * �?スト用に光センサーの生�?�を設定す�?
	 * @param value 光センサーの生�?�
	 */
	public void setValue(float value) {
		value_ = value;
	}
	
	/**
	 * トレースする経路?��ライン?���?�有無を検�?�する閾値を取得す�?
	 * 閾値は光センサー自身が�?�動検�?�できな�?ためsetThreashold()で設定す�?
	 */
	@Override
	public float getThreashold() {
		return threashold_;
	}
	
	/**
	 * トレースする経路�?霧を識別するための閾値を設定す�?
	 * @param threashold 閾値
	 */
	@Override
	public void setThreashold(float threashold) {
		threashold_ = threashold;
	}
}
