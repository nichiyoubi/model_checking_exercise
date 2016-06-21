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

int light = LIGHT_TARGET;    	/* light sensor value */
int motor_l, motor_r;	/* motor power */
int obstacle;		/* distance from obstacle(cm) */
mtype mutex_l = UNLOCKED; /* mutex for motor_l */
mtype mutex_r = UNLOCKED; /* mutex for motor_r */

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
	skip;
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
proctype line_tracer_data_provider() {
	skip;
}


/*
 *
 */
proctype pc_remote_controller() {
	skip;
}

/** environment model **/
/* cource model */
proctype model_course() {
	 do
	 ::light = LIGHT_BLACK;		/* black */
	 ::light = LIGHT_TARGET;	/* on the edge */
	 ::light = LIGHT_WHITE;		/* white */
	 ::else -> skip;
	 od;
}

/* ostable model */
proctype model_obstacle() {
	obstacle = 0;

	do
	::obstacle = 50;
	::obstacle > 1 -> obstacle = obstacle - 1;
	::obstacle = obstacle;
	::else -> assert((obstacle <= 50) && (obstacle >= MIN_DISTANCE));
	od;

}

/** initialize **/
init {
	run model_obstacle();
	run model_course();
	run line_tracer();
	run line_tracer_obstacle_detector();
}

ltl spec1 { []<> line_tracer@progress_trace }
ltl spec2 { []<> line_tracer_obstacle_detector@progress_detector }