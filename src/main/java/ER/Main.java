package ER;

public class Main {

    public static void main(String[] args) {

        for (int run = 1; run <= 10; run++) {

            double arrivalRate = (run <= 5) ? 0.4 : 0.8;   // vary arrival rate
            double serviceRate = (run % 2 == 0) ? 0.5 : 0.7; // vary service rate
            int doctors = (run <= 3) ? 1 : 3;             // vary doctors

            double simLength = 300; // 5 minutes simulated

            System.out.println("\n=== RUN " + run + " ===");

            SimulationEngine engine = new SimulationEngine(
                    arrivalRate,
                    serviceRate,
                    doctors,
                    simLength
            );

            engine.runSimulation();
        }
    }
}
