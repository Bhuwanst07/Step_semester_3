package oop.assignment_problems;

public class F5 {

    public static abstract class HomeDevice {

        private static int serialCounter = 1000;

        private final String serialNumber;

        public HomeDevice() {

            serialCounter++;

            serialNumber =
                    "HD-"
                    + serialCounter;
        }

        public abstract String activate();

        public String getSerialNumber() {
            return serialNumber;
        }
    }

    public interface RemoteControllable {
        String connect(String appId);
    }

    public interface EnergyTrackable {
        double getConsumptionWatts();
    }

    public static class WashingMachine
            extends HomeDevice
            implements RemoteControllable,
                       EnergyTrackable {

        private final double consumptionWatts;

        public WashingMachine(
                double consumptionWatts) {

            if (consumptionWatts <= 0) {
                throw new IllegalArgumentException(
                        "Consumption must be positive"
                );
            }

            this.consumptionWatts =
                    consumptionWatts;
        }

        @Override
        public String activate() {

            return "Washing machine "
                    + getSerialNumber()
                    + " started a cycle";
        }

        @Override
        public String connect(String appId) {

            return getSerialNumber()
                    + " connected to "
                    + appId;
        }

        @Override
        public double getConsumptionWatts() {
            return consumptionWatts;
        }
    }

    public static class Refrigerator
            extends HomeDevice
            implements EnergyTrackable {

        private final double consumptionWatts;

        public Refrigerator(
                double consumptionWatts) {

            if (consumptionWatts <= 0) {
                throw new IllegalArgumentException(
                        "Consumption must be positive"
                );
            }

            this.consumptionWatts =
                    consumptionWatts;
        }

        @Override
        public String activate() {

            return "Refrigerator "
                    + getSerialNumber()
                    + " activated";
        }

        @Override
        public double getConsumptionWatts() {
            return consumptionWatts;
        }
    }

    public static class MobileApp
            implements RemoteControllable {

        private final String appName;

        public MobileApp(String appName) {

            if (appName == null
                    || appName.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "App name cannot be blank"
                );
            }

            this.appName = appName;
        }

        @Override
        public String connect(String appId) {

            return appName
                    + " connected to "
                    + appId;
        }
    }

    public static void connectAll(
            RemoteControllable[] items,
            String appId) {

        for (RemoteControllable item : items) {
            System.out.println(
                    item.connect(appId)
            );
        }
    }

    public static double getConsumptionIfTrackable(
            HomeDevice d) {

        if (d instanceof EnergyTrackable) {

            EnergyTrackable trackable =
                    (EnergyTrackable) d;

            return trackable.getConsumptionWatts();
        }

        return 0.0;
    }

    public static void main(String[] args) {

        WashingMachine wm =
                new WashingMachine(500.0);

        System.out.println(
                wm.activate()
        );

        System.out.println(
                wm.connect("HomeConnect")
        );

        Refrigerator fridge =
                new Refrigerator(150.0);

        System.out.println(
                getConsumptionIfTrackable(
                        fridge
                )
        );

        MobileApp app =
                new MobileApp(
                        "HomeConnect App"
                );

        System.out.println(
                app.connect("HomeConnect")
        );

        // Upcasting:
        // WashingMachine stored as HomeDevice.
        HomeDevice ref = wm;

        System.out.println(
                getConsumptionIfTrackable(ref)
        );

        RemoteControllable[] items = {
            wm,
            app
        };

        connectAll(
                items,
                "HomeConnect"
        );
    }
}