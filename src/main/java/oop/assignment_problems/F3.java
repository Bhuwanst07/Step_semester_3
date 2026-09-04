package oop.assignment_problems;

class Canteen {

    private String canteenCode;
    private String canteenName;
    private int trustScore;

    public Canteen(
            String canteenCode,
            String canteenName,
            int trustScore) {

        this.canteenCode = canteenCode;
        this.canteenName = canteenName;
        this.trustScore = trustScore;
    }

    public Canteen(
            String canteenCode,
            String canteenName) {

        this(canteenCode, canteenName, 3);
    }

    int compareTo(Canteen other) {

        // Higher trust score comes first.
        if (this.trustScore != other.trustScore) {
            return Integer.compare(
                    other.trustScore,
                    this.trustScore
            );
        }

        // Same score: compare code ignoring case.
        int codeResult =
                this.canteenCode.compareToIgnoreCase(
                        other.canteenCode
                );

        if (codeResult != 0) {
            return codeResult;
        }

        // Same code ignoring case: shorter name first.
        return Integer.compare(
                this.canteenName.length(),
                other.canteenName.length()
        );
    }

    static Canteen[] rankCanteens(Canteen[] canteens) {

        Canteen[] result = canteens.clone();

        // Stable insertion sort.
        for (int i = 1; i < result.length; i++) {

            Canteen current = result[i];
            int j = i - 1;

            while (j >= 0
                    && result[j].compareTo(current) > 0) {

                result[j + 1] = result[j];
                j--;
            }

            result[j + 1] = current;
        }

        return result;
    }

    String getCanteenCode() {
        return canteenCode;
    }
}

public class F3 {

    public static void main(String[] args) {

        Canteen[] canteens = {
            new Canteen("HB3-C", "Spice Junction", 3),
            new Canteen("hb1-c", "Grand Mess", 5),
            new Canteen("HB2-C", "Southern Treats")
        };

        Canteen[] ranked =
                Canteen.rankCanteens(canteens);

        for (Canteen canteen : ranked) {
            System.out.println(
                    canteen.getCanteenCode()
            );
        }
    }
}