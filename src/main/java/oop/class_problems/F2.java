package oop.class_problems;

public class F2 {

    public interface Alertable {
        String sendAlert(String message);
    }

    public static class SecuritySensor {

        private final String zoneName;

        public SecuritySensor(String zoneName) {
            this.zoneName = zoneName;
        }

        public String getZoneName() {
            return zoneName;
        }
    }

    public static class MotionSensor
            extends SecuritySensor
            implements Alertable {

        public MotionSensor(String zoneName) {
            super(zoneName);
        }

        @Override
        public String sendAlert(String message) {
            return "["
                    + getZoneName()
                    + "] "
                    + message;
        }
    }

    public static class DualZoneMotionSensor
            extends MotionSensor {

        private final String secondZoneName;

        public DualZoneMotionSensor(
                String zoneName,
                String secondZoneName) {

            super(zoneName);
            this.secondZoneName = secondZoneName;
        }

        @Override
        public String sendAlert(String message) {

            return super.sendAlert(message)
                    + " [also covering "
                    + secondZoneName
                    + "]";
        }
    }

    public static class SmokeDetector
            implements Alertable {

        private final String deviceId;

        public SmokeDetector(String deviceId) {
            this.deviceId = deviceId;
        }

        @Override
        public String sendAlert(String message) {
            return "["
                    + deviceId
                    + "] "
                    + message;
        }
    }

    public static void broadcastAll(
            Alertable[] devices,
            String message) {

        for (Alertable device : devices) {
            System.out.println(
                    device.sendAlert(message)
            );
        }
    }

    public static String getZoneIfMotionSensor(
            Alertable a) {

        if (a instanceof MotionSensor) {

            MotionSensor motion =
                    (MotionSensor) a;

            return motion.getZoneName();
        }

        return "Not a motion sensor";
    }

    public static void main(String[] args) {

        MotionSensor m =
                new MotionSensor("Living Room");

        DualZoneMotionSensor d =
                new DualZoneMotionSensor(
                        "Hallway",
                        "Stairwell"
                );

        SmokeDetector s =
                new SmokeDetector("SD-01");

        System.out.println(
                m.sendAlert("Motion detected")
        );

        System.out.println(
                d.sendAlert("Motion detected")
        );

        System.out.println(
                s.sendAlert("Smoke detected")
        );

        System.out.println(
                getZoneIfMotionSensor(m)
        );

        System.out.println(
                getZoneIfMotionSensor(s)
        );

        Alertable[] devices = {
            m,
            d,
            s
        };

        broadcastAll(
                devices,
                "Emergency alert"
        );
    }
}