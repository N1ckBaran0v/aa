import java.util.Scanner;

public final class Execution {
    private Execution() {
    }

    public static void execute() {
        try (var scanner = new Scanner(System.in)) {
            var array = Array.readArray(scanner, false);
            array.sort();
            while (true) {
                System.out.println("число - поиск числа в массиве;");
                System.out.println("print - вывод массива;");
                System.out.println("end - выход;");
                System.out.print("Введите команду или число: ");
                var line = scanner.nextLine();
                if (line.equals("end")) {
                    System.out.println("Завершение работы.");
                    break;
                }
                if (line.equals("print")) {
                    System.out.println(array);
                } else {
                    try {
                        var number = Integer.parseInt(line);
                        System.out.println(array.search(number) + " " + array.binarySearch(number));
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка. Неизвестная команда.");
                    }
                }
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
