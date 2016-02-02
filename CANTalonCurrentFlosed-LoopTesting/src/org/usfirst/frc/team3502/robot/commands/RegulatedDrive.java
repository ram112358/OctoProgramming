package org.usfirst.frc.team3502.robot.commands;

import org.usfirst.frc.team3502.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RegulatedDrive extends Command {

    public RegulatedDrive() {
    	requires(Robot.currentControlled);
    }

    protected void initialize() {
    	Robot.currentControlled.enable();
    }

    protected void execute() {
    	Robot.currentControlled.setCurrent(Robot.oi.getManY(), Robot.currentControlled.MAXCURRENT);
    	SmartDashboard.putNumber("Closed-Loop Error", Robot.currentControlled.getError());
    	SmartDashboard.putNumber("Curent Current", Robot.currentControlled.getCurrent());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
