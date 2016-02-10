package org.usfirst.frc.team3502.robot.subsystems;

import org.usfirst.frc.team3502.robot.RobotMap;
import org.usfirst.frc.team3539.robot.instrumentation;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RecordingDrive extends Subsystem {
	
	private static final CANTalon driveMotor = new CANTalon(RobotMap.driveMotorPort, 1, 1);
	private static final CANTalon driveMotorFollow = new CANTalon(RobotMap.driveMotorFollowPort, 1, 1);
	
	private CANTalon.MotionProfileStatus MPStat = new CANTalon.MotionProfileStatus();
	
	public RecordingDrive(){
		driveMotor.changeControlMode(TalonControlMode.PercentVbus);
		driveMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		
		driveMotorFollow.changeControlMode(TalonControlMode.Follower);
		driveMotorFollow.set(RobotMap.driveMotorPort);
		
		driveMotor.setEncPosition(0);
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
    
    public void setProfileMode(){
    	driveMotor.changeControlMode(TalonControlMode.MotionProfile);
    }

	private void startFilling(double[][] profile, int totalCnt) {

		/* create an empty point */
		CANTalon.TrajectoryPoint point = new CANTalon.TrajectoryPoint();

		/* did we get an underrun condition since last time we checked ? */
		if (_status.hasUnderrun) {
			/* better log it so we know about it */
			instrumentation.OnUnderrun();
			/*
			 * clear the error. This flag does not auto clear, this way 
			 * we never miss logging it.
			 */
			driveMotor.clearMotionProfileHasUnderrun();
		}
		/*
		 * just in case we are interrupting another MP and there is still buffer
		 * points in memory, clear it.
		 */
		driveMotor.clearMotionProfileTrajectories();

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
			if (i == 0)
				point.zeroPos = true; /* set this to true on the first point */

			point.isLastPoint = false;
			if ((i + 1) == totalCnt)
				point.isLastPoint = true; /* set this to true on the last point  */

			driveMotor.pushMotionProfileTrajectory(point);
		}
	}
}