package org.usfirst.frc.team3502.robot.commands.machTwo;

import org.usfirst.frc.team3502.robot.MotionProfile;
import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class PlayArrayVel extends Command {

	private int[] pointBuffer;
	
	private int recordLength;
	
	private int n;

    public PlayArrayVel() {
    	requires(Robot.drive);
    }

    protected void initialize() {
    	recordLength = MotionProfile.kNumPoints;
    	pointBuffer = MotionProfile.intPoints;
    	n = 0;
    	Robot.drive.setVelocityMode();
    	Robot.drive.clearEnc();
    }

    protected void execute() {
    	Robot.drive.set(pointBuffer[n]);
    	n++;
    }

    protected boolean isFinished() {
    	if (n == recordLength)
    		return true;
        return false;
    }

    protected void end() {
    	Robot.drive.setThrottleMode();
    }

    protected void interrupted() {
    	Robot.drive.setThrottleMode();
    }
}
