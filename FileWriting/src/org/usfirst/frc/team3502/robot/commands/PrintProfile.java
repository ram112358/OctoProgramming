package org.usfirst.frc.team3502.robot.commands;

import org.usfirst.frc.team3502.robot.MotionProfile;
import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PrintProfile extends Command {
	
	private int n;
	
    public PrintProfile() {
    	requires(Robot.drive);
    }
    
    protected void initialize() {
    }
    
    protected void execute() {
    	SmartDashboard.putBoolean("ReadingFile", true);
    	for (n = 0; n < MotionProfile.kNumPoints; n++) {
    		SmartDashboard.putNumber("pos", MotionProfile.Points[n][0]);
    		SmartDashboard.putNumber("vel", MotionProfile.Points[n][1]);
    	}
    	//SmartDashboard.putBoolean("ReadingFile", false);
    }
    
    protected boolean isFinished() {
        return true;
    }
    
    protected void end() {
    }
    
    protected void interrupted() {
    }
}