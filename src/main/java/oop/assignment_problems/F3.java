package oop.assignment_problems;

import java.util.*;

public class F3 {

    interface Capability {
        String name();
        void apply(Object value);
    }

    static class PowerCapability implements Capability {

        private boolean on = false;

        public String name() {
            return "Power";
        }

        public void apply(Object value) {

            if (!(value instanceof Boolean)) {
                throw new IllegalArgumentException(
                        "Power must be ON or OFF."
                );
            }

            on = (Boolean) value;
        }

        public boolean isOn() {
            return on;
        }
    }

    static class BrightnessCapability implements Capability {

        private int brightness = 0;

        public String name() {
            return "Brightness";
        }

        public void apply(Object value) {

            if (!(value instanceof Number)) {
                throw new IllegalArgumentException(
                        "brightness must be a number."
                );
            }

            int v =
                    ((Number) value).intValue();

            if (v < 0 || v > 100) {
                throw new IllegalArgumentException(
                        "brightness must be between 0% and 100%."
                );
            }

            brightness = v;
        }

        public int getBrightness() {
            return brightness;
        }
    }

    static class TemperatureCapability implements Capability {

        private double temperature = 16;

        public String name() {
            return "Temperature";
        }

        public void apply(Object value) {

            if (!(value instanceof Number)) {
                throw new IllegalArgumentException(
                        "temperature must be a number."
                );
            }

            double v =
                    ((Number) value).doubleValue();

            if (v < 16 || v > 30) {
                throw new IllegalArgumentException(
                        "temperature must be between 16°C and 30°C."
                );
            }

            temperature = v;
        }

        public double getTemperature() {
            return temperature;
        }
    }

    static class Device {

        String name;

        Map<String, Capability> capabilities =
                new LinkedHashMap<>();

        Device(String name) {
            this.name = name;
        }

        void addCapability(Capability capability) {

            capabilities.put(
                    capability.name(),
                    capability
            );

            System.out.println(
                    name + ": "
                            + capability.name()
                            + " capability added."
            );
        }

        boolean supports(String capabilityName) {
            return capabilities.containsKey(capabilityName);
        }

        void apply(
                String capabilityName,
                Object value) {

            Capability capability =
                    capabilities.get(capabilityName);

            if (capability == null) {
                return;
            }

            try {

                capability.apply(value);

                if (capability instanceof PowerCapability) {

                    System.out.println(
                            name + ": "
                                    + (((PowerCapability)
                                    capability).isOn()
                                    ? "ON"
                                    : "OFF")
                                    + "."
                    );

                } else if (
                        capability instanceof BrightnessCapability) {

                    System.out.println(
                            name
                                    + ": brightness set to "
                                    + ((BrightnessCapability)
                                    capability).getBrightness()
                                    + "%."
                    );

                } else if (
                        capability instanceof TemperatureCapability) {

                    System.out.printf(
                            "%s: temperature set to %.0f°C.%n",
                            name,
                            ((TemperatureCapability)
                                    capability).getTemperature()
                    );
                }

            } catch (IllegalArgumentException e) {

                System.out.println(
                        "Rejected: "
                                + name
                                + " "
                                + e.getMessage()
                );
            }
        }
    }

    static class SceneStep {

        String capabilityName;
        Object value;

        SceneStep(
                String capabilityName,
                Object value) {

            this.capabilityName = capabilityName;
            this.value = value;
        }
    }

    static class Scene {

        String name;

        List<SceneStep> steps =
                new ArrayList<>();

        Scene(String name) {
            this.name = name;
        }

        void addStep(
                String capabilityName,
                Object value) {

            steps.add(
                    new SceneStep(
                            capabilityName,
                            value
                    )
            );
        }

        void execute(List<Device> devices) {

            System.out.println(
                    "Scene '" + name + "' started."
            );

            int actions = 0;

            for (SceneStep step : steps) {

                for (Device device : devices) {

                    if (device.supports(
                            step.capabilityName)) {

                        device.apply(
                                step.capabilityName,
                                step.value
                        );

                        actions++;
                    }
                }
            }

            System.out.println(
                    "Scene '" + name
                            + "' completed: "
                            + actions
                            + " actions applied."
            );
        }
    }

    public static void main(String[] args) {

        Device ac =
                new Device("Lab AC");

        ac.addCapability(
                new PowerCapability()
        );

        ac.addCapability(
                new TemperatureCapability()
        );

        Device lights =
                new Device("Ceiling Lights");

        lights.addCapability(
                new PowerCapability()
        );

        lights.addCapability(
                new BrightnessCapability()
        );

        Device projector =
                new Device("Projector");

        projector.addCapability(
                new PowerCapability()
        );

        List<Device> devices =
                Arrays.asList(
                        ac,
                        lights,
                        projector
                );

        Scene lecture =
                new Scene("Lecture Mode");

        lecture.addStep(
                "Power",
                true
        );

        lecture.addStep(
                "Brightness",
                40
        );

        lecture.addStep(
                "Temperature",
                24
        );

        lecture.execute(devices);

        ac.apply(
                "Temperature",
                12
        );

        projector.addCapability(
                new BrightnessCapability()
        );

        projector.apply(
                "Brightness",
                70
        );
    }
}