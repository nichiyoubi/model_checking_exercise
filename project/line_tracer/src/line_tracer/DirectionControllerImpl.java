/**
 * 
 */
package line_tracer;

/**
 * @author usamimasanori
 *
 */
public class DirectionControllerImpl implements DirectionController {
	Wheel rightWheel_;
	Wheel leftWheel_;
	int direction_;
	int max_speed_;
	
	/**
	 * コンストラクタ
	 * @param right
	 * @param left
	 */
	public DirectionControllerImpl(Wheel right, Wheel left) {
		rightWheel_ = right;
		leftWheel_ = left;
		direction_ = 0;
		max_speed_ = rightWheel_.getMaximumSpeed();
	}
	
	/**
	 * 操作対象の車輪を設定する
	 * @param right 右車輪
	 * @param left 左車輪
	 */
	public void setWheel(Wheel right, Wheel left) {
		rightWheel_ = right;
		leftWheel_ = left;
	}
	
	/**
	 * 進行方向背を設定する
	 * @param direction -100~+100で設定する（-100：左、+100：右）
	 */
	public void setDirection(int direction) {
		if ((direction < -100) | (direction > 100)) {
			return;
		}
		direction_ = direction;
		if (direction > 0) {
			leftWheel_.setSpeed(max_speed_ / 2);
//			rightWheel_.setSpeed(max_speed_ - direction);
			rightWheel_.setSpeed(0);
		}
		else if (direction < 0) {
//			leftWheel_.setSpeed(max_speed_ - direction);
			leftWheel_.setSpeed(0);
			rightWheel_.setSpeed(max_speed_ / 2);
		}
		else {
			leftWheel_.setSpeed(max_speed_ / 2);
			rightWheel_.setSpeed(max_speed_ / 2);
		}
		rightWheel_.forward();
		leftWheel_.forward();
	}
	
	/**
	 * 現在の方向を返す
	 * @return 現在の方向（-100：左〜+100：右）
	 */
	public int getDirection() {
		return direction_;
	}
	
	/**
	 * 車輪を停止する
	 */
	public void stop() {
		rightWheel_.stop();
		leftWheel_.stop();
	}
}
