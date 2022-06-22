public class Main {
    public static final double S = 0.6;
    public static final double DELTA = 0.29;
    public static final double N = 0.03;
    public static final double A = 0.72;
    public static final double K1 = 0.1;
    public static final int ARRAY_SIZE = 100;
    public static final int DIGITS_AFTER_COMMA = 4;


    public static void main(String[] args) {
        double[] k_t = new double[ARRAY_SIZE];
        double[] y_t = new double[ARRAY_SIZE];
        double[] i_t = new double[ARRAY_SIZE];
        double[] k_t2NPlusDelta = new double[ARRAY_SIZE];
        double[] c_t = new double[ARRAY_SIZE];

        k_t[0] = K1;

        for (int i = 0; i < ARRAY_SIZE; i++) {
            y_t[i] = round(Math.pow(k_t[i], A));
            i_t[i] = round(S * y_t[i]);
            k_t2NPlusDelta[i] = round(k_t[i] * (N + DELTA));
            c_t[i] = round((1 - S) * y_t[i]);

            if (i + 1 == ARRAY_SIZE) {
                break;
            }
            k_t[i + 1] = round(S * y_t[i] + (1 - DELTA + N) * k_t[i]);
        }

        System.out.println("i\t|\t" +
                "k_i\t|\t" +
                "y(t)\t|\t" +
                "I(t)\t|\t" +
                "k_i * (n + delta)\t|\t" +
                "c(t)");
        for (int i = 0; i < ARRAY_SIZE; i++) {
            System.out.println("[" + (i + 1) + "]" + ": \t" +
                    k_t[i] + "\t|\t" +
                    y_t[i] + "\t|\t" +
                    i_t[i] + "\t|\t" +
                    k_t2NPlusDelta[i] + "\t|\t" +
                    c_t[i]);
        }


        double kInAsterisk = round(Math.pow(S / (DELTA + N), 1 / (1 - A)));
        System.out.println("\nk^* = " + kInAsterisk);
        double kInG = round(Math.pow((DELTA + N) / A, 1 / (A - 1)));
        System.out.println("k^* = " + kInG);


        double[] yDerivative = new double[ARRAY_SIZE];
        double[] nPlusDelta = new double[ARRAY_SIZE];

        for (int i = 0; i < ARRAY_SIZE; i++) {
            yDerivative[i] = round(A * Math.pow(k_t[i], A - 1));
            nPlusDelta[i] = round(N + DELTA);
        }

        System.out.println("\n(f(k))'\t|\tn + delta");
        for (int i = 0; i < ARRAY_SIZE; i++) {
            System.out.println(yDerivative[i] + "\t|\t" + nPlusDelta[i]);
        }


        double sInG = round(kInG * (N + DELTA) / Math.pow(kInG, A));

        double[] k_t1 = new double[ARRAY_SIZE];
        double[] y_t1 = new double[ARRAY_SIZE];
        double[] deltaK1 = new double[ARRAY_SIZE];
        double[] deltaY1 = new double[ARRAY_SIZE];

        k_t1[0] = K1;
        for (int i = 0; i < ARRAY_SIZE; i++) {
            y_t1[i] = round(Math.pow(k_t1[i], A));

            if (i + 1 == ARRAY_SIZE) {
                break;
            }
            k_t1[i + 1] = round((sInG - 0.1) * y_t1[i] + (1 - DELTA + N) * k_t1[i]);
            deltaK1[i] = round(k_t1[i + 1] - k_t1[i]);
        }

        for (int i = 0; i < ARRAY_SIZE; i++) {
            if (i + 1 == ARRAY_SIZE) {
                break;
            }
            deltaY1[i] = round(y_t1[i + 1] - y_t1[i]);
        }

        double[] k_t2 = new double[ARRAY_SIZE];
        double[] y_t2 = new double[ARRAY_SIZE];
        double[] deltaK2 = new double[ARRAY_SIZE];
        double[] deltaY2 = new double[ARRAY_SIZE];

        k_t2[0] = K1;
        for (int i = 0; i < ARRAY_SIZE; i++) {
            y_t2[i] = round(Math.pow(k_t2[i], A));

            if (i + 1 == ARRAY_SIZE) {
                break;
            }
            k_t2[i + 1] = round(sInG * y_t2[i] + (1 - DELTA + N) * k_t2[i]);
            deltaK2[i] = round(k_t2[i + 1] - k_t2[i]);
        }

        for (int i = 0; i < ARRAY_SIZE; i++) {
            if (i + 1 == ARRAY_SIZE) {
                break;
            }
            deltaY2[i] = round(y_t2[i + 1] - y_t2[i]);
        }

        double[] k_t3 = new double[ARRAY_SIZE];
        double[] y_t3 = new double[ARRAY_SIZE];
        double[] deltaK3 = new double[ARRAY_SIZE];
        double[] deltaY3 = new double[ARRAY_SIZE];

        k_t3[0] = K1;
        for (int i = 0; i < ARRAY_SIZE; i++) {
            y_t3[i] = round(Math.pow(k_t3[i], A));

            if (i + 1 == ARRAY_SIZE) {
                break;
            }
            k_t3[i + 1] = round((sInG + 0.1) * y_t3[i] + (1 - DELTA + N) * k_t3[i]);
            deltaK3[i] = round(k_t3[i + 1] - k_t3[i]);
        }

        for (int i = 0; i < ARRAY_SIZE; i++) {
            if (i + 1 == ARRAY_SIZE) {
                break;
            }
            deltaY3[i] = round(y_t3[i + 1] - y_t3[i]);
        }

        System.out.println("\ns^g = " + sInG);
        System.out.print("\t\t\t\ts^g - 0.1 = " + (sInG - 0.1) + "\t\t\t\t||\t\t\t\t");
        System.out.print("s^g = " + sInG + "\t\t\t\t||\t\t\t\t");
        System.out.print("s^g + 0.1 = " + (sInG + 0.1) + "\n");
        System.out.println("i\t\tk_i\t|\ty(t)\t|\tΔk\t\t|\t\tΔy\t||\t" +
                "k_i\t|\ty(t)\t|\tΔk\t\t|\t\tΔy\t||\t" +
                "k_i\t|\ty(t)\t|\tΔk\t\t|\t\tΔy");
        for (int i = 0; i < ARRAY_SIZE; i++) {
            if (i == ARRAY_SIZE - 1) {
                System.out.println("[" + (i + 1) + "]: " +
                        k_t1[i] + "\t|\t" +
                        y_t1[i] + "\t|\t" +
                        "\t\t|\t" +
                        "\t\t||\t" +
                        k_t2[i] + "\t|\t" +
                        y_t2[i] + "\t|\t" +
                        "\t\t|\t" +
                        "\t\t||\t" +
                        k_t3[i] + "\t|\t" +
                        y_t3[i] + "\t|\t" +
                        "\t\t|\t");
                break;
            }
            System.out.println("[" + (i + 1) + "]: " +
                    k_t1[i] + "\t|\t" +
                    y_t1[i] + "\t|\t" +
                    deltaK1[i] + "\t|\t" +
                    deltaY1[i] + "\t||\t" +
                    k_t2[i] + "\t|\t" +
                    y_t2[i] + "\t|\t" +
                    deltaK2[i] + "\t|\t" +
                    deltaY2[i] + "\t||\t" +
                    k_t3[i] + "\t|\t" +
                    y_t3[i] + "\t|\t" +
                    deltaK3[i] + "\t|\t" +
                    deltaY3[i]);
        }
    }

    private static double round(double number) {
        return (double) Math.round(number * Math.pow(10, Main.DIGITS_AFTER_COMMA)) / Math.pow(10, Main.DIGITS_AFTER_COMMA);
    }
}
