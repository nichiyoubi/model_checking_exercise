/**
 * 
 */
package test_line_tracer;

import line_tracer.LightSensor;

/**
 * @author usamimasanori
 *
 */
public class MockLightSensor implements LightSensor {
	float value_;
	float threashold_;

	/* 光センサーの生値を返す
	 * @see line_tracer.LightSensor#getValue()
	 */
	@Override
	public float getValue() {
		return value_;
	}

	/**
	 * テスト用に光センサーの生値を設定する
	 * @param value 光センサーの生値
	 */
	public void setValue(float value) {
		value_ = value;
	}
	
	/**
	 * トレースする経路（ライン）の有無を検出する閾値を取得する
	 * 閾値は光センサー自身が自動検出できないためsetThreashold()で設定する
	 */
	@Override
	public float getThreashold() {
		return threashold_;
	}
	
	/**
	 * トレースする経路濃霧を識別するための閾値を設定する
	 * @param threashold 閾値
	 */
	@Override
	public void setThreashold(float threashold) {
		threashold_ = threashold;
	}
}
