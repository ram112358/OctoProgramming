package org.usfirst.frc.team3502.robot.commands.DriveClimb;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveMode extends Command {

    public DriveMode() {
    	requires(Robot.shifting);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.shifting.setDriveMode();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
