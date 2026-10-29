package frc.robot.DriveTrain;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.swerve.SwerveDrivetrainConstants;
import com.ctre.phoenix6.swerve.SwerveModuleConstants;
import com.ctre.phoenix6.swerve.SwerveModuleConstants.SteerFeedbackType;
import com.ctre.phoenix6.swerve.SwerveModuleConstantsFactory;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;



public class SwerveConstants {
    public static final double distancia_modulos = 0.665;

    public static final SwerveDriveKinematics ObjCinematico = new SwerveDriveKinematics(
        new Translation2d( distancia_modulos/2.0,  distancia_modulos/2.0), // Frente izq
        new Translation2d( distancia_modulos/2.0,  -distancia_modulos/2.0), // Frente der
        new Translation2d( -distancia_modulos/2.0,  distancia_modulos/2.0), // Atras izq
        new Translation2d( -distancia_modulos/2.0,  -distancia_modulos/2.0) // Atras der
    );

    public static final double kVelLinearMax = 4.5; // metros por segundo
    public static final double kVelAngularMax = 2 * Math.PI; // radianes

    // PID velocidad
    public static final double kDriveP = 1;
    public static final double kDriveI = 1;
    public static final double kDriveD = 1;
    public static final double kDriveKS = 1;
    public static final double kDriveKV = 1;

    // PID direccion
    public static final double kHeadingP = 1;
    public static final double kHeadingI = 0;
    public static final double kHeadingD = 0;

    public static final SwerveDrivetrainConstants DrivetrainConstants = new SwerveDrivetrainConstants()
        .withPigeon2Id(13)
        .withCANBusName("rio");

    private static final Slot0Configs driveGains = new Slot0Configs()
        .withKP(0.1).withKI(0.0).withKD(0.0)
        .withKS(0.25).withKV(0.12);

    private static final Slot0Configs steerGains = new Slot0Configs()
        .withKP(100.0).withKI(0.0).withKD(0.5);

    private static final SwerveModuleConstantsFactory<TalonFXConfiguration, TalonFXConfiguration, CANcoderConfiguration> ConstantCreator =
        new SwerveModuleConstantsFactory<TalonFXConfiguration, TalonFXConfiguration, CANcoderConfiguration>()
            .withDriveMotorGearRatio(6.12)       // Relación de engranajes de tracción (ejemplo SDS L3)
            .withSteerMotorGearRatio(12.8)       // Relación de engranajes de dirección
            .withWheelRadius(0.0508)             // Radio de la rueda en metros (2 pulgadas)
            .withDriveMotorGains(driveGains)
            .withSteerMotorGains(steerGains)
            .withSpeedAt12Volts(kVelLinearMax)                     // Velocidad maxima a 12V (m/s)
            .withFeedbackSource(SteerFeedbackType.RemoteCANcoder); // FusedCANcoder requiere Phoenix Pro


    public static final SwerveModuleConstants<?, ?, ?> FrontLeft = ConstantCreator.createModuleConstants(
        1,                           // Steer Motor CAN ID
        2,                           // Drive Motor CAN ID
        1,                              // CANcoder CAN ID
        0.0,                        // Magnet Offset (rotaciones)
        distancia_modulos / 2.0,                  // Posicion x
        distancia_modulos / 2.0,                  // Posicion x
        false,                 // driveMotorInverted
        false,                 // steerMotorInverted
        false                     // encoderInverted
    );

    public static final SwerveModuleConstants<?, ?, ?> FrontRight = ConstantCreator.createModuleConstants(
        3, 4, 2, 0.0, 
        distancia_modulos / 2.0, -distancia_modulos / 2.0, 
        false, false, false
    );

    public static final SwerveModuleConstants<?, ?, ?> BackLeft = ConstantCreator.createModuleConstants(
        5, 6, 3, 0.0, 
        -distancia_modulos / 2.0, distancia_modulos / 2.0, 
        false, false, false
    );

    public static final SwerveModuleConstants<?, ?, ?> BackRight = ConstantCreator.createModuleConstants(
        7, 8, 4, 0.0, 
        -distancia_modulos / 2.0, -distancia_modulos / 2.0, 
        false, false, false
    );
}
