/*******************************************************************
 ***		Verification model for Line Tracer Robot	 ***
 ***			LEGO Mindstorms EV3			 ***
 *******************************************************************/
#define LIGHT_TARGET	100	/* target value of course edget */
#define LIGHT_WHITE	150	/* sensor value of white color */
#define	LIGHT_BLACK	50	/* sensor value of black color */

#define MIN_DISTANCE	10	/* minimum distance to obstacles */

mtype = {STRAIGHT, LEFT, RIGHT, STOP};		/* remote command */
mtype = {STS_TRACE, STS_SAFETY, STS_REMOTE};	/* robot status */
mtype = {LOCKED, UNLOCKED};			/* mutex status */

mtype mutex_l = UNLOCKED; /* mutex for motor_l */
mtype mutex_r = UNLOCKED; /* mutex for motor_r */

int light = LIGHT_TARGET;    	/* light sensor value */
int motor_l, motor_r;		/* motor power */
int obstacle = 0;		/* distance from obstacle(cm) */

chan ch_wifi = [2] of { mtype };

/**
 *	lock a mutex
 */
inline lock(mutex) {
	d_step { mutex == UNLOCKED -> mutex = LOCKED }
}

/**
 *	unlock a mutex
 */
inline unlock(mutex) {
	mutex = UNLOCKED
}

inline go_straight() {
	lock(mutex_r);
	lock(mutex_l);

cmd_straight:
	motor_r = 100;
	motor_l = 100;

	unlock(mutex_l);
	unlock(mutex_r);
}

inline go_left() {
	lock(mutex_r);
	lock(mutex_l);

cmd_left:
	motor_r = 100;
	motor_l = 0;

	unlock(mutex_l);
	unlock(mutex_r);
}

inline go_right() {
	lock(mutex_r);
	lock(mutex_l);

cmd_right:
	motor_r = 0;
	motor_l = 100;

	unlock(mutex_l);
	unlock(mutex_r);
}

/** motor stop function **/
inline stop() {
	lock(mutex_l);
	lock(mutex_r);

	motor_r = 0;
	motor_l = 0;

	unlock(mutex_r);
	unlock(mutex_l);
}

/*
 *	line trace process.
 */
proctype line_tracer() {

progress_trace:
	if
	::light == LIGHT_TARGET -> go_straight();
	::light >  LIGHT_TARGET -> go_left();
	::light <  LIGHT_TARGET -> go_right();
	fi;
	goto progress_trace;
}

/*
 *
 */
proctype line_tracer_network_controller() {
progress_controller:
	if
	::ch_wifi ? STRAIGHT -> go_straight();
	::ch_wifi ? LEFT -> go_left();
	::ch_wifi ? RIGHT -> go_right();
	fi;
	goto progress_controller;
}

/*
 *
 */
proctype line_tracer_obstacle_detector() {

progress_detector:
	if
	::obstacle < MIN_DISTANCE -> stop();
	::obstacle >= MIN_DISTANCE -> skip;
	fi;
	goto progress_detector;

}

/*
 *
 */
proctype pc_remote_controller() {
progress_pc_controller:
	if
	::skip -> ch_wifi ! STRAIGHT;
	::skip -> ch_wifi ! LEFT;
	::skip -> ch_wifi ! RIGHT;
	fi;
	goto progress_pc_controller;
}

/** environment model **/
/* cource model */
proctype model_course() {
	do
	::skip -> light = LIGHT_BLACK;		/* black */
	::skip -> light = LIGHT_TARGET;		/* on the edge */
	::skip -> light = LIGHT_WHITE;		/* white */
	::skip -> light = light;
	od;
}

/* ostable model */
proctype model_obstacle() {
	do
	::obstacle > 1 -> obstacle = obstacle - 1;
	::skip -> obstacle = 50;
	::skip -> obstacle = obstacle;
	::skip -> assert((obstacle <= 50) && (obstacle >= MIN_DISTANCE));
	od;
}

/** initialize **/
init {
	run model_obstacle();
	run model_course();
	run pc_remote_controller();
	run line_tracer();
	run line_tracer_obstacle_detector();
	run line_tracer_network_controller();
}


ltl spec1 { []<> line_tracer@progress_trace }
ltl spec2 { []<> line_tracer_obstacle_detector@progress_detector }
ltl spec3 { []<> line_tracer_network_controller@progress_controller }
ltl spec4 { [] ((light == LIGHT_WHITE) -> line_tracer@cmd_left) }
ltl spec5 { [] ((light == LIGHT_BLACK) -> line_tracer@cmd_right) }
ltl spec6 { [] ((light == LIGHT_TARGET) -> line_tracer@cmd_straight) }

/*******************************************************************
 test case
	spec1	line_tracer() process can never deadlock.
	spec2	line_tracer_obstacle_detector() process can never deadlock.
	spec3	line_tracer_network_controller() process can never deadlock.
	spec4	if the light sensor detects the course off, 
		the robots must turn left.
	spec5	if the light sensor detects the course on, 
		the robots must turn right.
	spec6	if the light sensor detects the course edge, 
		the robots must go straight.
	
 *******************************************************************/
