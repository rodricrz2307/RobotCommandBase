package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import frc.robot.Commands.IndexCommands;
import frc.robot.Commands.OutakeCommands;
import frc.robot.Mechanisms.IntakeSub;
import frc.robot.Mechanisms.MechanismsConstants.IntakeConstants;
import frc.robot.Mechanisms.MechanismsConstants.ShootContants;
import frc.robot.Mechanisms.OutakeSub;
import frc.robot.Mechanisms.Rollers;

public class RobotContainer {

  private final CommandXboxController joystick = new CommandXboxController(0);

  private final OutakeSub outake = new OutakeSub();
  private final Rollers rollers = new Rollers();
  private final IntakeSub intake = new IntakeSub();
  private final SendableChooser<Command> autoChooser = new SendableChooser<>();

  public RobotContainer() {
    configureBindings();
    configureAutos();
  }

  private void configureBindings() {
    joystick.rightTrigger().whileTrue(
        OutakeCommands.shoot(outake, IndexCommands.feed(rollers),
            ShootContants.shooterCloseRPS, ShootContants.hoodClose));

    joystick.rightBumper().whileTrue(
        OutakeCommands.shoot(outake, IndexCommands.feed(rollers),
            ShootContants.shooterFarRPS, ShootContants.hoodFar));
    joystick.leftTrigger().whileTrue(intake.runIntakeCommand(IntakeConstants.intakeSpeed));

    joystick.leftBumper().whileTrue(intake.runIntakeCommand(-IntakeConstants.intakeSpeed));
  }

  private void configureAutos() {
    autoChooser.setDefaultOption("Nada", Commands.none());

    autoChooser.addOption("Tiro cercano",
        OutakeCommands.shoot(outake, IndexCommands.feed(rollers),
            ShootContants.shooterCloseRPS, ShootContants.hoodClose)
            .withTimeout(ShootContants.autoShootTime));

    autoChooser.addOption("Tiro lejano",
        OutakeCommands.shoot(outake, IndexCommands.feed(rollers),
            ShootContants.shooterFarRPS, ShootContants.hoodFar)
            .withTimeout(ShootContants.autoShootTime));

    SmartDashboard.putData("Auto Chooser", autoChooser);
  }

  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }
}
