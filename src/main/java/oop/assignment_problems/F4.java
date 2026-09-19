package oop.assignment_problems;

public class F4 {

    public interface Attackable {

        String attack();

        String attack(String weaponName);
    }

    public interface Defendable {
        String defend();
    }

    public static abstract class GameCharacter {

        private static int characterCounter = 1000;

        private final String characterId;

        public GameCharacter() {

            characterCounter++;

            characterId =
                    "CHAR-"
                    + characterCounter;
        }

        public abstract String getSpecialMove();

        public String getCharacterId() {
            return characterId;
        }
    }

    public static class Warrior
            extends GameCharacter
            implements Attackable, Defendable {

        private final String name;

        public Warrior(String name) {

            if (name == null
                    || name.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Name cannot be blank"
                );
            }

            this.name = name;
        }

        @Override
        public String attack() {

            return name
                    + " strikes with a blade";
        }

        @Override
        public String attack(
                String weaponName) {

            return name
                    + " strikes with an "
                    + weaponName;
        }

        @Override
        public String defend() {

            return name
                    + " raises a shield";
        }

        @Override
        public String getSpecialMove() {

            return name
                    + " unleashes Whirlwind Slash";
        }
    }

    public static class Trap
            implements Defendable {

        private final String trapType;

        public Trap(String trapType) {

            if (trapType == null
                    || trapType.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Trap type cannot be blank"
                );
            }

            this.trapType = trapType;
        }

        @Override
        public String defend() {

            return trapType
                    + " triggers automatically";
        }
    }

    public static void resolveDefense(
            Defendable[] combatants) {

        for (Defendable combatant : combatants) {
            System.out.println(
                    combatant.defend()
            );
        }
    }

    public static void main(String[] args) {

        Warrior w =
                new Warrior("Kael");

        System.out.println(
                w.attack()
        );

        System.out.println(
                w.attack("Iron Sword")
        );

        System.out.println(
                w.defend()
        );

        System.out.println(
                w.getSpecialMove()
        );

        Trap t =
                new Trap("Spike Pit");

        System.out.println(
                t.defend()
        );

        resolveDefense(
                new Defendable[]{
                    w,
                    t
                }
        );

        System.out.println(
                w.getCharacterId()
        );
    }
}