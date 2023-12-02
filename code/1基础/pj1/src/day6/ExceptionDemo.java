package day6;

public class ExceptionDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};

        try{
            // 可能出现异常的代码
            System.out.println(arr[5]);
            System.out.println("这里是try中的异常后面的内容");
            // 出现异常后会创建一个异常类型的对象，传递到最近的catch中对比看是否可以被接收/捕获，被捕获后执行catch代码块中的代码
        } catch(ArrayIndexOutOfBoundsException | ArithmeticException e){
            // 异常处理
            System.out.println("索引越界了");
            System.out.println(e.getMessage());
            System.out.println(e.toString());
            e.printStackTrace();
        } catch(Exception e) {
            System.out.println("Exception");
        } finally {
            System.out.println("finally一定会执行");
        }

        System.out.println("这里被执行了吗");
        System.out.println("==========================================");

        int[] arr2 = {};
        try {
            int maxNum = getMax(arr2);
        } catch(NullPointerException e) {
            System.out.println("空指针异常");
        } catch(ArrayLengthException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception");
        }

        System.out.println("这里是程序结尾-----");
    }

    // 下面主动抛出异常
    public static int getMax(int[] arr)throws NullPointerException, ArrayLengthException {
        if (arr == null) {
            throw new NullPointerException();
        }
        if (arr.length == 0) {
            throw new ArrayLengthException("数组长度为:" + arr.length + ",数组长度有问题");
        }

        int ans = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > ans) ans = arr[i];
        }
        return ans;
    }
}

// 自定义异常类，需要定义空参和带一个参的构造方法
class ArrayLengthException extends RuntimeException {
    public ArrayLengthException() {
    }

    public ArrayLengthException(String message) {
        super(message);
    }
}