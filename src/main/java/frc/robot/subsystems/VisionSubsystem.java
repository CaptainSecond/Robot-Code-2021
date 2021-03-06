// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import org.photonvision.PhotonCamera;

import org.photonvision.PhotonPipelineResult;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VisionSubsystem extends SubsystemBase {
  /** Creates a new Vision. */
  PhotonCamera camera;
  PhotonPipelineResult result;
  NetworkTableEntry yawResult;
  NetworkTableInstance NTmain;
  NetworkTable nt;
  
  public VisionSubsystem() {
    this.camera = new PhotonCamera("compcam");
    this.NTmain = NetworkTableInstance.getDefault();
    this.nt = NTmain.getTable("photonvision").getSubTable("compcam");
  }

  
  public double getYaw(){
    if(result.hasTargets()){
      return result.getBestTarget().getYaw();
    }
    else return 0.0;
  }
  

  /*public double getYaw(){
    return nt.getEntry("targetYaw").getDouble(Double.NaN);
  }*/

  public boolean hasTarget(){
    // ! FIXME
    return nt.getEntry("hasTarget").getBoolean(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    result = camera.getLatestResult();
    SmartDashboard.putNumber("yawSD", getYaw());
  }
}
