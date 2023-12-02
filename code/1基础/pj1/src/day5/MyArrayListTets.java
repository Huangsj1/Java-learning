package day5;

public class MyArrayListTets {
    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();
        System.out.println(list.size);
        System.out.println(list);

        list.add("aaa");
        list.add("bbb");
        System.out.println(list);

        String str = list.get(0);
        System.out.println(str);
        System.out.println(str == list.obj[0]);
    }
}
