package frc.robot.Mechanisms;

import com.ctre.phoenix6.signals.InvertedValue;


public class MechanismsConstants {

    public class IntakeConstants {

        public static final int intakeId = 10;

        public static final InvertedValue intakeInversion =
                InvertedValue.CounterClockwise_Positive;

        public static final double kP = 0.0;
        public static final double kI = 0.0;
        public static final double kD = 0.0;
        public static final double kS = 0.0;
        public static final double kG = 0.0;
        public static final double kV = 0.0;
        public static final double kA = 0.0;
    }

    public class IntakeboxConstants {

        public static final int intakeboxId = 11;

        public static final InvertedValue intakeboxInversion =
                InvertedValue.CounterClockwise_Positive;

        public static final double kP = 0.0;
        public static final double kI = 0.0;
        public static final double kD = 0.0;
        public static final double kS = 0.0;
        public static final double kG = 0.0;
        public static final double kV = 0.0;
        public static final double kA = 0.0;
    }

    public static final class OutakeConstants {

        public static final int shooterLeftId = 22;        //Modificar los id
        public static final int shooterRightId = 23;
        public static final int hoodId = 24;
        public static final InvertedValue shooterLeftInversion = InvertedValue.CounterClockwise_Positive;
        public static final InvertedValue shooterRightInversion = InvertedValue.Clockwise_Positive;
        public static final InvertedValue hoodInversion = InvertedValue.CounterClockwise_Positive;

        public static final double shooterkP = 0.0;
        public static final double shooterkI = 0.0;
        public static final double shooterkD = 0.0;
        public static final double shooterkS = 0.0;
        public static final double shooterkG = 0.0;
        public static final double shooterkV = 0.0;
        public static final double shooterkA = 0.0;

        public static final double hoodkP = 0.0;
        public static final double hoodkI = 0.0;
        public static final double hoodkD = 0.0;
        public static final double hoodkS = 0.0;
        public static final double hoodkG = 0.0;
        public static final double hoodkV = 0.0;
        public static final double hoodkA = 0.0;

        public static final double hoodMinPosition = 0.0;
        public static final double hoodMaxPosition = 3.0;
    }

    public static final class ShootContants {
        public static final double hoodHome = 0.0;
        public static final double hoodClose = 0.5;
        public static final double hoodFar = 2.5;
        public static final double hoodTolerance = 0.1;

        public static final double shooterCloseRPS = 45.0;
        public static final double shooterFarRPS = 65.0;
        public static final double shooterTolerance = 4.0;

        public static final double autoShootTime = 2.0;
    }

    public static class IndexConstants {

        public static final int index_motor_id = 0;
        public static final int roller1Id = 30;
        public static final int roller2Id = 31;
        public static final InvertedValue rollersInversion = InvertedValue.CounterClockwise_Positive;

        public static final double intakeSpeed = 0.4;
        public static final double feedSpeed = 1.0;

        public static final double kP = 0.0;
        public static final double kI = 0.0;
        public static final double kD = 0.0;
        public static final double kS = 0.0;
        public static final double kV = 0.0;
        public static final double kA = 0.0;

    }
}
