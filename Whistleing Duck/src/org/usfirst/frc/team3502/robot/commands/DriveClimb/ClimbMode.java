package org.usfirst.frc.team3502.robot.commands.DriveClimb;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimbMode extends Command {

    public ClimbMode() {
    	requires(Robot.shifting);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.shifting.setClimbMode();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
