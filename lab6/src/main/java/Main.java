import run.DataReader;
import run.Solver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            try {
                Solver.solve(DataReader.readData(new BufferedReader(new InputStreamReader(System.in)),false));
            } catch (Exception e) {
                System.out.println("Ошибка. Неверный формат ввода.");
            }
        } else {
            try {
                var matrices = new double[args.length][][];
                for (var i = 0; i < args.length; ++i) {
                    try (var reader = Files.newBufferedReader(Path.of(args[i]))) {
                        matrices[i] = DataReader.readData(reader, true);
                    } catch (Exception e) {
                        System.out.printf("Ошибка при работе с файлом %s.\n", args[i]);
                        throw e;
                    }
                }
                Solver.solve(matrices);
            } catch (Exception ignored) {
            }
        }
    }
}
