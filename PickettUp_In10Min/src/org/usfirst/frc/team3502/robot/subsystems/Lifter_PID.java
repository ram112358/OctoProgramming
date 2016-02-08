package org.usfirst.frc.team3502.robot.subsystems;

import org.usfirst.frc.team3502.robot.Robot;
import org.usfirst.frc.team3502.robot.RobotMap;
import org.usfirst.frc.team3502.robot.commands.manualLifter;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Lifter_PID extends PIDSubsystem {
	private static final CANTalon motor = RobotMap.liftMotor;
	private static final Encoder encoder = RobotMap.liftEncoder;
	private static final DigitalInput 
		liftBottomStopper = RobotMap.liftBottomStopper,
		liftTopStopper = RobotMap.liftTopStopper;
	private static final DoubleSolenoid 
		brake = RobotMap.brake;
	private static final double 
		distancePerPulse = .01, 
		voltageRampRate = -1.1;
	private static double 
		kP = .85,//.5
		kI = 0,//0
		kD = 0,//0
		targetDistance;
	private static boolean
		brakeOn = false;
	private double scaleOutput;
	public static final double TOLERANCE = .1;
	public static final double BOTTOM = -.2, MIDTOTEBOTTOM = 3, MIDTOTETOP = 4, TOPTOTE = 7.7, BINONTOTE = 6.893, MAX = 8, MIN = 0;
	
    // Initialize your subsystem here
    public Lifter_PID() {
    	super("Lifter_PID", kP, kI, kD);
    	getPIDController().setContinuous(false);
    	//getPIDController().setInputRange(MIN, MAX);
    	targetDistance = 0;
    	setSetpoint(targetDistance);
    	setAbsoluteTolerance(TOLERANCE);
    	//LiveWindow.addActuator("Lift_PID", "Lift PID Controller", getPIDController());
    	//enable();
    	this.setBrakeOff();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new manualLifter());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
    	return encoder.getDistance();
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
    	if(getSetpoint() == 0){
    		output /= 1.15;
    	}
    	setLiftMotor(output);
    	//SmartDashboard.putNumber("PID Output", output);
    }
    
    public void setLiftMotor(double outputValue){
    	updateEncoder();
	    if(((getLiftBottomStopper())&&(outputValue<0))||(brakeOn)||((getLiftTopStopper())&&(outputValue>0))){
	    	outputValue = 0;
	    }
	    scaleOutput = (MAX - encoder.getDistance()) / MAX;
	    //outputValue *= scaleOutput;
	    SmartDashboard.putBoolean("Top Stopper", getLiftTopStopper());
	    SmartDashboard.putBoolean("Bottom Stopper", getLiftBottomStopper());
	    SmartDashboard.putNumber("Lifter Output", outputValue);
	    motor.set(-outputValue);
	    SmartDashboard.putBoolean("Brake On", brakeOn);
    }
    
    public void setDistance(){
    	encoder.setDistancePerPulse(distancePerPulse);
    }
    
    public void setVoltageRamp(){
    	motor.setVoltageRampRate(voltageRampRate);
    }
    
    public double getCurrent(){
    	return motor.getOutputCurrent();
    }
    public int getRaw(){
    	return encoder.getRaw(); //The raw value is the actual count unscaled by the 1x, 2x, or 4x scale factor
    }
    
    public double getRate(){ //current rate of encoder
		return encoder.getRate(); //Units are distance per second as scaled by the value from setDistancePerPulse()
    }
    
    private void updateEncoder(){
    	if(liftBottomStopper.get()){
    		encoder.reset();
    	}
    }
    
    public void publicResetEncoder(){
    	encoder.reset();
    }
    
    public boolean getLiftBottomStopper(){
    	return liftBottomStopper.get();
    }
    
    public boolean getLiftTopStopper(){
    	return (!liftTopStopper.get());
    }
    
    public void setBrakeOn(){
    	brake.set(DoubleSolenoid.Value.kForward);
    	brakeOn=true;
    }
    
    public void setBrakeOff(){
    	brake.set(DoubleSolenoid.Value.kReverse);
    	brakeOn=false;
    }
    
    public void switchBrake(){
    	if(brakeOn){
    		setBrakeOff();
    	}
    	else{
    		setBrakeOn();
    	}
    }
}
