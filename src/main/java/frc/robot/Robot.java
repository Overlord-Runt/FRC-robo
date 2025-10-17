// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.motorcontrol.Talon;

/**
 * This is a demo program showing the use of the DifferentialDrive class, specifically it contains
 * the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private final Joystick m_driveStick;

  private final Talon m_leftFrontMotor = new Talon(1);
  private final Talon m_leftBackMotor = new Talon(2);
  private final Talon m_rightFrontMotor = new Talon(3);
  private final Talon m_rightBackMotor = new Talon(4);
  private final Talon m_intakeMotor = new Talon(5);


  /** Called once at the beginning of the robot program. */
  public Robot() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_rightFrontMotor.setInverted(true);
    m_rightBackMotor.setInverted(true);
    m_driveStick = new Joystick(0);

  }

  @Override
  public void teleopPeriodic() {
    double drive = -m_driveStick.getY();
    double turn = -m_driveStick.getX();
    
    double Lpower = drive - turn;
    double Rpower = drive + turn;
        
        // Set motor powers
    m_leftFrontMotor.set(Lpower);
    m_rightFrontMotor.set(Rpower);
    m_rightBackMotor.set(Rpower);
    m_leftBackMotor.set(Lpower);

    if (m_driveStick.getRawButton(1)) {
      m_intakeMotor.set(1);
    }
    if (m_driveStick.getRawButton(5)) {
      m_intakeMotor.set(0);
    }
    if (m_driveStick.getRawButton(2)) {
      m_intakeMotor.set(-1);
    }

  }
}
