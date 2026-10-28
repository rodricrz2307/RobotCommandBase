package frc.robot;

import com.ctre.phoenix6.swerve.SwerveRequest;
import com.pathplanner.lib.auto.AutoBuilder;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.DriveTrain.CommandSwerveDrivetrain;
import frc.robot.DriveTrain.SwerveConstants;
import frc.robot.Mechanisms.Rollers; 
import frc.robot.Mechanisms.IntakeSub;
import frc.robot.Commands.IndexCommands;
import frc.robot.Commands.OutakeCommands;
import frc.robot.Mechanisms.MechanismsConstants.IntakeConstants; 
import frc.robot.Mechanisms.MechanismsConstants.ShootContants;
import frc.robot.Mechanisms.OutakeSub;

public class RobotContainer {

  

  public CommandXboxController joystick = new CommandXboxController(0);
  public CommandSwerveDrivetrain drivetrain = new CommandSwerveDrivetrain(
        SwerveConstants.DrivetrainConstants,
        SwerveConstants.FrontLeft,
        SwerveConstants.FrontRight,
        SwerveConstants.BackLeft,
        SwerveConstants.BackRight
    );
  

  private final OutakeSub outake = new OutakeSub();
  private final Rollers flywheels = new Rollers();
  private final IntakeSub intake = new IntakeSub();

  private final SendableChooser<Command> autoChooser;


  private final SwerveRequest.FieldCentricFacingAngle driveFacingAngle = new SwerveRequest.FieldCentricFacingAngle();

  private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
        .withDeadband(0.1)
        .withRotationalDeadband(0.1);

  private final SwerveRequest.SwerveDriveBrake brake = new SwerveRequest.SwerveDriveBrake();

  public RobotContainer() {
    driveFacingAngle.HeadingController.setPID(5.0, 0.0, 0.0);
    driveFacingAngle.HeadingController.enableContinuousInput(-Math.PI, Math.PI);

    configureBindings();

    drivetrain.configurarPathPlanner();
    if (AutoBuilder.isConfigured()) {
      autoChooser = AutoBuilder.buildAutoChooser();
    } else {
      autoChooser = new SendableChooser<>();
    }
    SmartDashboard.putData("Auto Chooser", autoChooser);
  }

  private void configureBindings() {

      drivetrain.setDefaultCommand(
        drivetrain.applyRequest(() -> 
          drive
            .withVelocityX(-joystick.getLeftY() * SwerveConstants.kVelLinearMax)
            .withVelocityY(-joystick.getLeftX() * SwerveConstants.kVelLinearMax)
            .withRotationalRate(-joystick.getRightX() * SwerveConstants.kVelAngularMax)
        )
      );

        
        joystick.y().onTrue(
            drivetrain.runOnce(() -> drivetrain.seedFieldCentric())
        );

        
        joystick.x().whileTrue(
            drivetrain.applyRequest(() -> brake)
        );

        
        joystick.b().whileTrue(
        drivetrain.applyRequest(() ->
        driveFacingAngle
            .withVelocityX(-joystick.getLeftY() * SwerveConstants.kVelLinearMax)
            .withVelocityY(-joystick.getLeftX() * SwerveConstants.kVelLinearMax)
            .withTargetDirection(Rotation2d.fromDegrees(0))
    )
);

        joystick.rightTrigger().whileTrue(OutakeCommands.shoot(outake, IndexCommands.feed(flywheels),
                ShootContants.shooterCloseRPS, ShootContants.hoodClose));

        joystick.rightBumper().whileTrue(OutakeCommands.shoot(outake, IndexCommands.feed(flywheels),
                ShootContants.shooterFarRPS, ShootContants.hoodFar));
        joystick.leftTrigger().whileTrue(intake.runIntakeCommand(IntakeConstants.intakeSpeed));
        joystick.leftBumper().whileTrue(intake.runIntakeCommand(-IntakeConstants.intakeSpeed));
    }

  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }
}
