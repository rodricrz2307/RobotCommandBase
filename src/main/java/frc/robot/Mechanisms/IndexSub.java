package frc.robot.Mechanisms;

import com.ctre.phoenix6.signals.InvertedValue;

public class MechanismsConstants {
    public static final class IndexConstants {
        public static final int roller1Id = 15;
        public static final int roller2Id = 16;

        public static final InvertedValue rollersInversion = InvertedValue.Clockwise_Positive;

        public static final double kP = 0.0;
        public static final double kI = 0.0;
        public static final double kD = 0.0;
    }
}
