package oop.class_problems;

import java.util.*;

public class F1 {

    interface Dimmable {
        void dim(int level);
    }

    interface Schedulable {
        void schedule(String time);
    }

    interface EnergyMonitor {
        void showEnergy();
    }

    static abstract class Device {

        String id;
        boolean on;

        Device(String id) {
            this.id = id;
            this.on = false;
        }

        void turnOn() {
            on = true;
            System.out.println(id + " is ON");
        }

        void turnOff() {
            on = false;
            System.out.println(id + " is OFF");
        }
    }

    static class Light extends Device
            implements Dimmable, Schedulable {

        Light(String id) {
            super(id);
        }

        public void dim(int level) {
            if (level < 0 || level > 100) {
                System.out.println("Invalid brightness");
                return;
            }

            System.out.println(id + " dimmed to " + level);
        }

        public void schedule(String time) {
            System.out.println(id + " scheduled " + time);
        }
    }

    static class Fan extends Device
            implements Schedulable {

        Fan(String id) {
            super(id);
        }

        public void schedule(String time) {
            System.out.println(id + " scheduled " + time);
        }
    }

    static class Plug extends Device
            implements EnergyMonitor {

        int energy;

        Plug(String id, int energy) {
            super(id);
            this.energy = energy;
        }

        public void showEnergy() {
            System.out.println(id + " energy " + energy + " kWh");
        }
    }

    static class SmartHome {

        Map<String, Device> devices =
                new LinkedHashMap<>();

        void addDevice(Device device) {
            devices.put(device.id, device);
        }

        void execute(String command) {

            String[] parts = command.split("\\s+");

            String operation = parts[0];
            String id = parts[1];

            Device device = devices.get(id);

            if (device == null) {
                System.out.println("Unknown device: " + id);
                return;
            }

            switch (operation) {

                case "ON":
                    device.turnOn();
                    break;

                case "OFF":
                    device.turnOff();
                    break;

                case "DIM":

                    if (device instanceof Dimmable) {
                        ((Dimmable) device).dim(
                                Integer.parseInt(parts[2])
                        );
                    } else {
                        System.out.println(
                                id + " rejected: DIM unsupported"
                        );
                    }
                    break;

                case "SCHEDULE":

                    if (device instanceof Schedulable) {
                        ((Schedulable) device).schedule(parts[2]);
                    } else {
                        System.out.println(
                                id + " rejected: SCHEDULE unsupported"
                        );
                    }
                    break;

                case "ENERGY":

                    if (device instanceof EnergyMonitor) {
                        ((EnergyMonitor) device).showEnergy();
                    } else {
                        System.out.println(
                                id + " rejected: ENERGY unsupported"
                        );
                    }
                    break;

                default:
                    System.out.println("Unknown command");
            }
        }
    }

    public static void main(String[] args) {

        SmartHome home = new SmartHome();

        home.addDevice(new Light("L1"));
        home.addDevice(new Fan("F1"));
        home.addDevice(new Plug("P1", 12));

        String[] commands = {
                "ON L1",
                "DIM L1 40",
                "DIM F1 30",
                "SCHEDULE F1 22:00",
                "ENERGY P1",
                "ENERGY L1"
        };

        for (String command : commands) {
            home.execute(command);
        }
    }
}