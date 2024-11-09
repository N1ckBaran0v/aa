package exec;

import work.ThreadsSheduler;

import java.io.IOException;
import java.util.Scanner;

public class Execute {
    private Execute() {
    }

    private static int readNumber(Scanner scanner, int min, int max) throws InterruptedException {
        int result = min - 1;
        var message = (String) null;
        if (max < min) {
            message = "Введите целое число не меньше " + min + ": ";
        } else {
            message = "Введите целое число в диапазоне от " + min + " до " + max + ": ";
        }
        while (result < min) {
            try {
                System.out.print(message);
                System.out.flush();
                var buffer = scanner.nextLine().strip();
                result = Integer.parseInt(buffer);
                if (result < min) {
                    System.out.println("Ошибка. Число меньше " + min + ".");
                }
                if (max > min && result > max) {
                    System.out.println("Ошибка. Число больше " + min + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка. Введено не целое число.");
            }
        }
        return result;
    }

    private static int readNumber(Scanner scanner, int min) throws InterruptedException {
        return readNumber(scanner, min, Integer.MIN_VALUE);
    }

    public static void execute() {
        try (var scanner = new Scanner(System.in)) {
            System.out.println("Ресурс: eda.ru");
            System.out.println("Ввод количества страниц.");
            var pages = readNumber(scanner, 1);
            System.out.println("Ввод количества дополнительных потоков.");
            var threads = readNumber(scanner, 0, 16);
            ThreadsSheduler.start("output", pages, threads);
        } catch (InterruptedException e) {
            System.out.println("Завершение работы.");
        } catch (IOException e) {
            System.out.println("Работа программы была прервана из-за ошибки загрузки.");
        }
    }
}
