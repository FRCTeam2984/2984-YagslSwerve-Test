package frc.robot;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Filesystem;
import swervelib.parser.SwerveParser;
import swervelib.SwerveDrive;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;
import java.io.File;

public class Robot extends TimedRobot {
  private SwerveDrive m_robotDrive;
  private final Joystick m_leftStick;
  private final Joystick m_rightStick;

  public Robot() {
    double maxSpeed = Units.feetToMeters(4);
    File directory = new File(Filesystem.getDeployDirectory(), "swerve");
    
    try {
      m_robotDrive = new SwerveParser(directory).createSwerveDrive(
        maxSpeed,
        new Pose2d(
          new Translation2d(1, 4),
          Rotation2d.fromDegrees(0)
        )
      );
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    m_leftStick = new Joystick(0);
    m_rightStick = new Joystick(1);
  }

  @Override
  public void teleopPeriodic() {
    m_robotDrive.drive(new Translation2d(-m_leftStick.getX(), 0), m_rightStick.getY()/4, false, false);
  }
}