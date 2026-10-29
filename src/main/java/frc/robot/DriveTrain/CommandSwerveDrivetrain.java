package frc.robot.DriveTrain;

import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.swerve.SwerveDrivetrain;
import com.ctre.phoenix6.swerve.SwerveDrivetrainConstants;
import com.ctre.phoenix6.swerve.SwerveModuleConstants;
import com.ctre.phoenix6.swerve.SwerveRequest;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.config.PIDConstants;
import com.pathplanner.lib.config.RobotConfig;
import com.pathplanner.lib.controllers.PPHolonomicDriveController;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

public class CommandSwerveDrivetrain extends SwerveDrivetrain<TalonFX, TalonFX, CANcoder> implements Subsystem {

    private final PIDController pidAngulo = new PIDController(
        SwerveConstants.kHeadingP, 
        SwerveConstants.kHeadingI, 
        SwerveConstants.kHeadingD
    );


    private final SwerveRequest.FieldCentric peticionCentradaEnCampo = new SwerveRequest.FieldCentric();

    public CommandSwerveDrivetrain(
        SwerveDrivetrainConstants constantesDeManejo,
        SwerveModuleConstants<?, ?, ?>... modulos
    ) {
        super(
            TalonFX::new, TalonFX::new, CANcoder::new, 
            constantesDeManejo, 0, modulos
        );
        pidAngulo.enableContinuousInput(-Math.PI, Math.PI);
    }

    
    public Command manejarApuntandoAngulo(DoubleSupplier proveedorX, DoubleSupplier proveedorY, Rotation2d anguloObjetivo) {
        return this.run(() -> {
            // Obtener el angulo actual directamente del estado de odometria de Phoenix 6
            double anguloActual = this.getState().Pose.getRotation().getRadians();
            
            // Calcular la velocidad de rotación requerida
            double salidaRotacion = pidAngulo.calculate(anguloActual, anguloObjetivo.getRadians());

            // Actualizar la orden y enviarla a los motores
            this.setControl(
                peticionCentradaEnCampo
                    .withVelocityX(proveedorX.getAsDouble())
                    .withVelocityY(proveedorY.getAsDouble())
                    .withRotationalRate(salidaRotacion)
            );
        });
    }

    public Command applyRequest(Supplier<SwerveRequest> requestSupplier) {
        return run(() -> this.setControl(requestSupplier.get()));
    }

    private final SwerveRequest.ApplyRobotSpeeds autoRequest = new SwerveRequest.ApplyRobotSpeeds();

    public void configurarPathPlanner() {
    RobotConfig config;
        try {
            config = RobotConfig.fromGUISettings();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        AutoBuilder.configure(
            () -> this.getState().Pose,
            (pose) -> this.resetPose(pose),
            () -> this.getState().Speeds,
            (speeds, feedforwards) -> this.setControl(
                autoRequest.withSpeeds(speeds)
            ),
            new PPHolonomicDriveController(
                new PIDConstants(5.0, 0.0, 0.0),            
                new PIDConstants(5.0, 0.0, 0.0)         
            ),
            config,                                         
            () -> {
                var alliance = DriverStation.getAlliance();
                if (alliance.isPresent()) {
                    return alliance.get() == DriverStation.Alliance.Red;
                }
                return false;
            },
            this
        );
    }
}