package day3;

public class StaticTest {
    public static void main(String[] args) {
        System.out.println(StaticType.teacher);
        StaticType.teacher = "hello";
        System.out.println(StaticType.teacher);
        StaticType st = new StaticType();
        System.out.println(st.teacher);
        System.out.println(st);

        System.out.println("--------");

        int[] arr = {1, 2, 3};
        ToolClass.show(arr);
    }
}
