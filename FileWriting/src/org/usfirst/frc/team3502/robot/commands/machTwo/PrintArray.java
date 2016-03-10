package org.usfirst.frc.team3502.robot.commands.machTwo;

import org.usfirst.frc.team3502.robot.MotionProfile;
import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class PrintArray extends Command {

	private int[] pointBuffer;
	
	private int recordLength;
	
	private int n;

    public PrintArray() {
    	requires(Robot.drive);
    }

    protected void initialize() {
    	recordLength = MotionProfile.kNumPoints;
    	pointBuffer = MotionProfile.intPoints;
    	n = 0;
    }

    protected void execute() {
    	System.out.println(pointBuffer[n]);
    	n++;
    }

    protected boolean isFinished() {
    	if (n == recordLength)
    		return true;
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
