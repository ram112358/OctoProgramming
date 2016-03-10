package org.usfirst.frc.team3502.robot.commands.machTwo;

import org.usfirst.frc.team3502.robot.MotionProfile;
import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RecordArrayPos extends Command {

	private int[] pointBuffer;
	
	private int recordLength = 500;
	
	private int n;
	
    public RecordArrayPos() {
    	requires(Robot.drive);
    }

    protected void initialize() {
    	Robot.drive.setThrottleMode();
    	Robot.drive.clearEnc();
    	pointBuffer = new int[recordLength];
    	n = 0;
    }

    protected void execute() {
    	Robot.drive.set(Robot.oi.getManY());
    	pointBuffer[n] = Robot.drive.getPosition();
    	n++;
    }

    protected boolean isFinished() {
    	if (n == recordLength)
    		return true;
        return false;
    }

    protected void end() {
    	Robot.drive.set(0);
    	MotionProfile.intPoints = pointBuffer;
    	MotionProfile.kNumPoints = recordLength;
    }

    protected void interrupted() {
    	Robot.drive.set(0);
    	MotionProfile.intPoints = pointBuffer;
    	MotionProfile.kNumPoints = recordLength;
    }
}
