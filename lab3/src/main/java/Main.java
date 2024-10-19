public class Main {
    public static void main(String[] args) {
        if (args.length == 1 && args[0].equals("-r")) {
            Execution.execute();
        } else if (args.length == 1 && args[0].equals("-b")) {
            Benchmark.benchmark();
        } else {
            System.out.println("Usage: java -jar lab3.jar [-r] [-b]");
        }
    }
}
