package org.usfirst.frc.team3502.robot.commands;

import org.usfirst.frc.team3502.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RegulatedDrive extends Command {

    public RegulatedDrive() {
    	requires(Robot.voltageControlled);
    }

    protected void initialize() {
    	Robot.voltageControlled.enable();
    }

    protected void execute() {
    
    	SmartDashboard.putNumber("Curent voltage", Robot.voltageControlled.getCurrent());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
