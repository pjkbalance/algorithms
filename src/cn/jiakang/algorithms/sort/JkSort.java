package cn.jiakang.algorithms.sort;

public abstract class JkSort {
    public abstract void sort(Comparable[] arr);

    // 判断对象v是否小于对象w
    protected static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // 将数组索引为i的对象与索引为j的对象互换
    protected static void exch(Comparable[] arr, int i, int j) {
        Comparable tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // 打印数组内容
    protected static void show(Comparable[] arr) {
        for (Comparable tmp : arr) {
            System.out.print(tmp + " ");
        }
        System.out.println();
    }

    // 判断数组是否已经排序
    protected static boolean isSorted(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (less(arr[i], arr[i - 1])) return false;
        }
        return true;
    }

    private static JkSort getJkSort(String type) {
        switch (type) {
            case "SELECTION":
                return new Selection();
            case "INSERTION":
                return new Insertion();
            case "SHELL":
                return new Shell();
            case "MERGE":
                return new Merge();
        }
        return null;
    }

    private static void test(String jkSortType, Comparable[] arr) {
        System.out.print("=== " + jkSortType + " begin === data is: ");
        show(arr);
        getJkSort(jkSortType).sort(arr);
        System.out.print("=== " + jkSortType + " end === data is: ");
        show(arr);
        System.out.println();
    }

    public static void main(String... args) {
        String[] arr1 = {"f", "e", "d", "c", "b", "a"};
        String[] arr2 = {"a", "b", "c", "d", "e", "f"};
        String[] arr3 = {"m", "n", "f", "l", "i", "b", "e", "g", "h", "d", "c", "k", "j", "a"};
        String jkSortType;
//        jkSortType = "SELECTION";
//        jkSortType = "INSERTION";
//        jkSortType = "SHELL";
        jkSortType = "MERGE";
        test(jkSortType, arr1);
        test(jkSortType, arr2);
        test(jkSortType, arr3);
    }
}
