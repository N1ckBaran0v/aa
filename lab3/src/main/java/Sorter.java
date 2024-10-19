public final class Sorter {
    private Sorter() {
    }

    public static void sort(int[] array) {
        for (var i = array.length >> 1; i >= 0; --i) {
            heapify(array, i, array.length);
        }
        for (var i = array.length - 1; i > 0; --i) {
            swap(array, 0, i);
            heapify(array, 0, i);
        }
    }

    private static void heapify(int[] array, int index, int size) {
        while (index < (size >> 1)) {
            var left = (index << 1) + 1;
            var right = left + 1;
            if (right >= size) {
                if (left == size - 1 && array[index] < array[left]) {
                    swap(array, index, left);
                }
                break;
            } else if (array[index] < array[left] && array[left] > array[right]) {
                swap(array, index, left);
                index = left;
            } else if (array[index] < array[right]) {
                swap(array, index, right);
                index = right;
            } else {
                break;
            }
        }
    }

    private static void swap(int[] array, int i, int j) {
        array[i] ^= array[j];
        array[j] ^= array[i];
        array[i] ^= array[j];
    }
}
