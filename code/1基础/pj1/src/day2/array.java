package day2;

public class array {
    public static void main(String[] args) {
        /*
        // 1.静态初始化
        int[] array = new int[] {1, 2, 3};
        System.out.println(array[1]);
        double[] array2 = {4.1, 5, 6.0};
        System.out.println(array2[0]);
        // 直接写数组名会打印数组地址：
        // [:表示当前是一个数组
        // I:表示数组内元素类型为int
        // 后面的数字为地址
        System.out.println(array);
        System.out.println(array2);

        // 2.动态初始化，指定数组长度
        // 整型初始值为0
        // 小数初始值为0.0
        // 字符初始值为'/u0000' 空格
        // 布尔初始值为false
        // 引用初始值为null
        String[] arr = new String[10];
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i < 3; i++) {
            arr[i] = sc.next();
        }
        // 长度属性
        for(int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

         */

        // 3.二维数组
        // 外面的数组的值包括：长度，所有子数组的地址
        int[][] tarr = new int[][]{{1, 2, 3}, {4, 5, 6}};
        System.out.println(tarr.length);
        System.out.println(tarr[0].length);
        System.out.println(tarr);
        System.out.println(tarr[0]);
        System.out.println(tarr[1]);
        System.out.println(tarr[0][0]);
        System.out.println("-----");
        // 二维数组初始化时可以只指定最外边数组的长度
        int[][] tarr2 = new int[2][];
        int[] arr1 = {1, 2};
        int[] arr2 = {3, 4, 5};
        tarr2[0] = arr1;
        tarr2[1] = arr2;
        System.out.println(tarr2[0].length);
        System.out.println("-----");
        // 即使制定了最内层数组的大小，重新赋值更大的数组也没问题
        int[][] tarr3 = new int[3][2];
        tarr3[0] = arr1;
        tarr3[1] = arr2;
        System.out.println(tarr3[0].length);
        System.out.println(tarr3[1].length);
        System.out.println(tarr3[2].length);
    }
}
