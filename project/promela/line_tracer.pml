/*******************************************************************
 ***		Verification model for Line Tracer Robot	 ***
 ***			LEGO Mindstorms EV3			 ***
 *******************************************************************/
mtype = {STRAIGHT, LEFT, RIGHT, STOP};		/* remote command */
mtype = {STS_TRACE, STS_SAFETY, STS_REMOTE};	/* robot status */
mtype = {LOCKED, UNLOCKED};			/* mutex status */

int light;	    	/* light sensor value */
int motor_l, motor_r;	/* motor power */
int obstacle;		/* distance from obstacle(cm) */
mtype mutex_l = UNLOCKED; /* mutex for motor_l */
mtype mutex_r = UNLOCKED; /* mutex for motor_r */

/**
 *	lock a mutex
 */
inline lock(mutex) {
	d_step {
		mutex == UNLOCKED -> mutex = LOCKED;
	}
}

/**
 *	unlock a mutex
 */
inline unlock(mutex) {
	d_step {
		mutex == LOCKED -> mutex = UNLOCKED;
	}
}

inline go_straight() {
	lock(mutex_r);
	lock(mutex_l);

	motor_r = 100;
	motor_l = 100;

	unlock(mutex_l);
	unlock(mutex_r);
}

inline go_left() {
	lock(mutex_r);
	lock(mutex_l);

	motor_r = 100;
	motor_l = 0;

	unlock(mutex_l);
	unlock(mutex_r);
}

inline go_right() {
	lock(mutex_r);
	lock(mutex_l);

	motor_r = 0;
	motor_l = 100;

	unlock(mutex_l);
	unlock(mutex_r);
}


/*
	The main process of line tracer robot.
	This process control motors.
 */
proctype line_tracer() {
	/*
	*/
	do
	::light == 100 -> go_straight();
	::light > 100  -> go_left();
	::light < 100  -> go_right();
	od;
	skip;
}

proctype line_tracer_network_controller() {
	 skip;
}

proctype line_tracer_obstacle_detector() {
	 skip;
}

proctype line_tracer_data_provider() {
	 skip;
}


proctype pc_remote_controller() {
	 skip;
}

/** environment model **/
/* cource model */
proctype model_course() {
	 light = 100;	/* target value */

	 printf("model cource");

	 do
	 ::skip -> light = 50;	/* black */
	 ::skip -> light = 100;	/* on the edge */
	 ::skip -> light = 150;	/* white */
	 od;
}

/** initialize **/
init {
     run model_course();
}
