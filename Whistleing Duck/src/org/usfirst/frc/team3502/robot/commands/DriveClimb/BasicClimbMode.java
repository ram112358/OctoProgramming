package org.usfirst.frc.team3502.robot.commands.DriveClimb;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class BasicClimbMode extends Command {

    public BasicClimbMode() {
    	requires(Robot.shifting);
    	requires(Robot.hooker);
    	requires(Robot.rightDrive);
    	requires(Robot.leftDrive);
    }

    protected void initialize() {
    	Robot.shifting.setClimbMode();
    	Robot.hooker.setServoPosition(0);
    }

    protected void execute() {
    	Robot.rightDrive.set(- Math.abs(Robot.oi.getLeftY()));
    	Robot.leftDrive.set(- Math.abs(Robot.oi.getRightY()));
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}