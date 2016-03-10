package org.usfirst.frc.team3502.robot.subsystems;

import org.usfirst.frc.team3502.robot.MotionProfile;
import org.usfirst.frc.team3502.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RecordingDrive extends Subsystem {
	
	private static final CANTalon driveMotor = new CANTalon(RobotMap.driveMotorPort, 1, 1);
	private static final CANTalon driveMotorFollow = new CANTalon(RobotMap.driveMotorFollowPort, 1, 1);
	
	private CANTalon.MotionProfileStatus MPStatus = new CANTalon.MotionProfileStatus();
	
	private int MPState = 0;
	
	private int MPLoopTimeout = -1;
	
	private boolean MPStarted = false;
	
	private CANTalon.SetValueMotionProfile MPValue = CANTalon.SetValueMotionProfile.Disable;
	
	private static final int minPointsInTalon = 10;

	private static final int numLoopsTimeout = 10;
	
	public final int encCPR = 360;
	
	private static final double
		p = 0.0,
		i = 0.0,
		d = 0.0,
		f = 0.0018,
		closeLoopRampRate = 0.0;
	private static final int
		izone = 0,
		profile = 0;
	
	class PeriodicRunnable implements java.lang.Runnable {
	    public void run() {driveMotor.processMotionProfileBuffer();}
	}
	Notifier notifier = new Notifier(new PeriodicRunnable());
	
	
	public RecordingDrive(){
		driveMotor.changeControlMode(TalonControlMode.PercentVbus);
		driveMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		driveMotor.configEncoderCodesPerRev(encCPR);
		driveMotor.reverseOutput(false);
		
		driveMotorFollow.changeControlMode(TalonControlMode.Follower);
		driveMotorFollow.set(RobotMap.driveMotorPort);
		
		driveMotor.setEncPosition(0);
		
		driveMotor.setPID(p, i, d, f, izone, closeLoopRampRate, profile);
		
		driveMotor.changeMotionControlFramePeriod(10);
		notifier.startPeriodic(0.01);
	}

    public void initDefaultCommand() {
    }
    
    public void set(double outputValue){
    	driveMotor.set(outputValue);
    }
    
    public int getPosition(){
    	return driveMotor.getEncPosition();
    }
    
    public int getVelocity(){
    	return driveMotor.getEncVelocity();
    }

    public void setThrottleMode(){
    	driveMotor.changeControlMode(TalonControlMode.PercentVbus);
    }
    
    public void setProfileMode(){
    	driveMotor.changeControlMode(TalonControlMode.MotionProfile);
    }

	public void reset() {
		/* 
		 * Let's clear the buffer just in case user decided to disable in the
		 * middle of an MP, and now we have the second half of a profile just
		 * sitting in memory.
		 */
		driveMotor.clearMotionProfileTrajectories();
		/* When we do re-enter motionProfile control mode, stay disabled. */
		MPValue = CANTalon.SetValueMotionProfile.Disable;
		/* When we do start running our state machine start at the beginning. */
		MPState = 0;
		MPLoopTimeout = -1;
		/*
		 * If application wanted to start an MP before, ignore and wait for next
		 * button press
		 */
		MPStarted = false;
	}

	public void control() {
		/* Get the motion profile status every loop */
		driveMotor.getMotionProfileStatus(MPStatus);
		SmartDashboard.putNumber("MPlevel", 1);
		/*
		 * track time, this is rudimentary but that's okay, we just want to make
		 * sure things never get stuck.
		 */
		if (MPLoopTimeout < 0) {
			/* do nothing, timeout is disabled */
			SmartDashboard.putNumber("MPlevel",2);
		} else {
			/* our timeout is nonzero */
			SmartDashboard.putNumber("MPlevel", 3);
			if (MPLoopTimeout == 0) {
				/*
				 * something is wrong. Talon is not present, unplugged, breaker
				 * tripped
				 */
				SmartDashboard.putNumber("MPlevel", 4);
			} else {
				--MPLoopTimeout;
				SmartDashboard.putNumber("MPlevel", 5);
			}
		}
		
		/* first check if we are in MP mode */
		if (driveMotor.getControlMode() != TalonControlMode.MotionProfile) {
			/*
			 * we are not in MP mode. We are probably driving the robot around
			 * using gamepads or some other mode.
			 */
			MPState = 0;
			MPLoopTimeout = -1;
			SmartDashboard.putNumber("MPlevel", 6);
		} else {
			/*
			 * we are in MP control mode. That means: starting Mps, checking Mp
			 * progress, and possibly interrupting MPs if thats what you want to
			 * do.
			 */

			SmartDashboard.putNumber("MPlevel", 7);
			switch (MPState) {
				case 0: /* wait for application to tell us to start an MP */
					if (MPStarted) {
						MPStarted = false;
	
						MPValue = CANTalon.SetValueMotionProfile.Disable;
						startFilling(MotionProfile.doublePoints, MotionProfile.kNumPoints);
						/*
						 * MP is being sent to CAN bus, wait a small amount of time
						 */
						MPState = 1;
						MPLoopTimeout = numLoopsTimeout;
						SmartDashboard.putNumber("MPlevel", 8);
					}
					break;
				case 1: /*
						 * wait for MP to stream to Talon, really just the first few
						 * points
						 */
					/* do we have a minimum numberof points in Talon */
					SmartDashboard.putNumber("MPlevel", 9);
					if (MPStatus.btmBufferCnt > minPointsInTalon) {
						/* start (once) the motion profile */
						MPValue = CANTalon.SetValueMotionProfile.Enable;
						/* MP will start once the control frame gets scheduled */
						MPState = 2;
						MPLoopTimeout = numLoopsTimeout;
						SmartDashboard.putNumber("MPlevel", 10);
					}
					break;
				case 2: /* check the status of the MP */
					/*
					 * if talon is reporting things are good, keep adding to our
					 * timeout. Really this is so that you can unplug your talon in
					 * the middle of an MP and react to it.
					 */
					SmartDashboard.putNumber("MPlevel", 11);
					if (MPStatus.isUnderrun == false) {
						MPLoopTimeout = numLoopsTimeout;
						SmartDashboard.putNumber("MPlevel", 12);
					}
					/*
					 * If we are executing an MP and the MP finished, start loading
					 * another. We will go into hold state so robot servo's
					 * position.
					 */
					if (MPStatus.activePointValid && MPStatus.activePoint.isLastPoint) {
						/*
						 * because we set the last point's isLast to true, we will
						 * get here when the MP is done
						 */
						MPValue = CANTalon.SetValueMotionProfile.Hold;
						MPState = 0;
						MPLoopTimeout = -1;
						SmartDashboard.putNumber("MPlevel", 13);
					}
					break;
			}
		}
		SmartDashboard.putNumber("MPlevel", 14);
	}

	private void startFilling(double[][] profile, int totalCnt) {
		
		/* create an empty point */
		CANTalon.TrajectoryPoint point = new CANTalon.TrajectoryPoint();
		SmartDashboard.putNumber("Tlevel", 1);
		
		/* did we get an underrun condition since last time we checked ? */
		if (MPStatus.hasUnderrun) {
			/*
			 * clear the error. This flag does not auto clear, this way 
			 * we never miss logging it.
			 */
			driveMotor.clearMotionProfileHasUnderrun();
			SmartDashboard.putNumber("Tlevel", 2);
		}
		/*
		 * just in case we are interrupting another MP and there is still buffer
		 * points in memory, clear it.
		 */
		driveMotor.clearMotionProfileTrajectories();
		SmartDashboard.putNumber("Tlevel", 3);
		/* This is fast since it's just into our TOP buffer */
		for (int i = 0; i < totalCnt; ++i) {
			/* for each point, fill our structure and pass it to API */
			point.position = profile[i][0];
			point.velocity = profile[i][1];
			point.timeDurMs = (int) profile[i][2];
			point.profileSlotSelect = 0; /* which set of gains would you like to use? */
			point.velocityOnly = false; /* set true to not do any position
										 * servo, just velocity feedforward
										 */
			point.zeroPos = false;
			SmartDashboard.putNumber("Tlevel", 4);
			if (i == 0)
				point.zeroPos = true; /* set this to true on the first point */

			point.isLastPoint = false;
			if ((i + 1) == totalCnt)
				point.isLastPoint = true; /* set this to true on the last point  */

			driveMotor.pushMotionProfileTrajectory(point);
		}
		SmartDashboard.putNumber("Tlevel", 5);
	}
	
	public void startMotionProfile() {
		MPStarted = true;
	}
	
	public CANTalon.SetValueMotionProfile getSetValue() {
		return MPValue;
	}
}