package org.usfirst.frc.team3502.robot.commands.DriveClimb;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class povHooker extends Command {

    public povHooker() {
    	requires(Robot.hooker);
    }

    protected void initialize() {
		Robot.hooker.setServoPosition(90);
    }

    protected void execute() {
    	SmartDashboard.putNumber("POV Test", Robot.oi.getOpPOV());
    	if(Robot.oi.getOpPOV() != -1) {
    		Robot.hooker.setServoPosition(Robot.oi.getOpPOV());
    	}
    	else
    		Robot.hooker.setServoPosition(90);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}