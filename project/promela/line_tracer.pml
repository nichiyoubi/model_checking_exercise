
mtype = {STRAIGHT, LEFT, RIGHT, STOP};		/* remote command */
mtype = {STS_TRACE, STS_SAFETY, STS_REMOTE};	/* robot status */

int light;	    	/* light sensor value */
int motor_l, motor_r;	/* motor power */
int obstacle;		/* distance from obstacle(cm) */

/*
	The main process of line tracer robot.
	This process control motors.
 */
proctype line_tracer() {
	/*
	*/
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
