import bencmark.Benchmark;
import exec.Execute;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            Execute.execute();
        } else if (args.length == 1 && "-b".equals(args[0])) {
            Benchmark.benchmark();
        }
        else {
            System.out.println("Usage: java -jar lab4.jar [-b]");
        }
    }
}
