import java.util.*;

public final class Array {
    private final int[] array;
    private final boolean returnsComprasionCount;

    private Array(int[] array, boolean returnsComprasionCount) {
        this.array = array;
        this.returnsComprasionCount = returnsComprasionCount;
    }

    public static Array readArray(Scanner scanner, boolean returnsComprasionCount) {
        var exists = new TreeSet<Integer>();
        var size = readNumber("Введите размер массива: ", scanner, exists);
        while (size < 1) {
            System.out.println("Ошибка. Некорректный размер массива.");
            size = readNumber("Введите размер массива: ", scanner, exists);
        }
        var array = new int[size];
        for (var i = 0; i < size; ++i) {
            array[i] = readNumber("Введите " + (i + 1) + "-й элемент массива: ", scanner, exists);
            exists.add(array[i]);
        }
        return new Array(array, returnsComprasionCount);
    }

    public static Array generateArray(int size, boolean returnsComprasionCount) {
        if (size <= 0) {
            throw new IllegalArgumentException("Ошибка. Некорректный размер массива.");
        }
        var array = new int[size];
        for (var i = 0; i < size; ++i) {
            array[i] = i;
        }
        return new Array(array, returnsComprasionCount);
    }

    public Integer get(int index) {
        var result = (Integer) null;
        if (index >= 0 && index < array.length) {
            result = array[index];
        }
        return result;
    }

    public void sort() {
        Sorter.sort(array);
    }

    public int binarySearch(int value) {
        var left = 0;
        var right = array.length - 1;
        var result = -1;
        var iterations = 0;
        while (result == -1 && left <= right) {
            var middle = left + ((right - left) >> 1);
            ++iterations;
            if (array[middle] == value) {
                result = middle;
            } else if (value < array[middle]) {
                ++iterations;
                right = middle - 1;
            } else {
                ++iterations;
                left = middle + 1;
            }
        }
        return returnsComprasionCount ? iterations : result;
    }

    public int search(int value) {
        var result = -1;
        for (var i = 0; result == -1 && i < array.length; ++i) {
            if (array[i] == value) {
                result = i;
            }
        }
        return returnsComprasionCount ? result == -1 ? array.length : result + 1 : result;
    }

    private static int readNumber(String message, Scanner scanner, Set<Integer> exists) {
        var result = (Integer) null;
        while (result == null) {
            try {
                System.out.print(message);
                var line = scanner.nextLine();
                if ("end".equals(line)) {
                    throw new RuntimeException("Выполнение прервано.");
                }
                result = Integer.parseInt(line);
                if (exists.contains(result)) {
                    System.out.println("Ошибка. Такое число уже было введено.");
                    result = null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка. Введено не число.");
            }
        }
        return result;
    }

    public int size() {
        return array.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
