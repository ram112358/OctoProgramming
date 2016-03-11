package org.usfirst.frc.team3502.robot;

public class Constants {
	
	public static boolean
		killPID = false;
	
	// Top Duck
	
	public static final int
		kTopUpSoftLimit = 0,
		kTopIntakePos = 0,
		kTopClimbingPos = 0;
	
	// Ducked
	public static final double
		kTopP = 0.96,
		kTopI = 0.0,
		kTopD = 0.0,
		kTopF = 0.0,
		kTopCloseLoopRampRate = 0.0;
	public static final int
		kTopIzone = 0;
	public static final int
		kTopProfile = 0;
	
	// Unducked
	public static final double
		kTopPUn = 0.43,
		kTopIUn = 0.0002,
		kTopDUn = 0.0,
		kTopFUn = 0.0,
		kTopCloseLoopRampRateUn = 0.0;
	public static final int
		kTopIzoneUn = 150;
	public static final int
		kTopProfileUn = 1;
	
	// Bottom Duck
	
	public static final int
		kBottomDownSoftLimit = 0,
		kBottomClimbPos = 0;
	
	// Ducked
	public static final double
		kBottomP = 0.35,
		kBottomI = 0.0,
		kBottomD = 0.0,
		kBottomF = 0.0,
		kBottomCloseLoopRampRate = 0.0;
	public static final int
		kBottomIzone = 0;
	public static final int
		kBottomProfile = 0;
	
	// Unducked
	public static final double
		kBottomPUn = 0.09,
		kBottomIUn = 0.0002,
		kBottomDUn = 0.0,
		kBottomFUn = 0.0,
		kBottomCloseLoopRampRateUn = 0.0;
	public static final int
		kBottomIzoneUn = 150;
	public static final int
		kBottomProfileUn = 1;
	
	// Drive Train Stuff
	public static final double
		kStraightP = 0.5,
		kStraightD = 0.15;
	public static final double
		kTurnP = 0.1,  //Needs tuning
		kTurnD = 0;  //Needs tuning
	
	// Brownout Values
	public static double
		kBrownLimit = 8.0,
		kBrownScale = 0.5;
	
	// Misc Values
	public static int
		kSqueezeFactor = 0;
}