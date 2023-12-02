package day2;

public class method {
    public static void main(String[] args) {
        System.out.println(getSum(1, 2));
        System.out.println(getSum(1.0, 3.0));
        System.out.println(getSum(1, 2, 3));

        int[] arr = {1, 2, 3};
        printArray(arr);
    }

    public static int getSum(int num1, int num2) {
        return num1 + num2;
    }

    // 方法重载：同一个类中，相同方法名，不同参数(数量、类型、顺序)
    public static double getSum(double num1, double num2) {
        return num1 + num2;
    }

    public static int getSum(int n1, int n2, int n3) {
        return n1 + n2 + n3;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // print不换行打印，println换行打印
            System.out.print(arr[i] + ", ");
        }
    }
}
