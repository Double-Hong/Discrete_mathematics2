import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class mySet {
    String name;
    ArrayList<myElement> set = new ArrayList<>();
    Scanner in = new Scanner(System.in);

    public mySet() {
        System.out.println("输入规则:");
        System.out.println("输入时先输入集合名(集合名需唯一),再输入元素");
        System.out.println("每个元素用空车隔开,输入回车结束");
        System.out.println("输入集合名:");
        name = in.nextLine();
        System.out.println("输入元素:");
        String ele = in.nextLine();
        StringBuilder l = new StringBuilder();
        for (int i = 0; i < ele.length(); i++) {
            if (ele.charAt(i) == ' ') {
                myElement e = new myElement(l.toString());
                set.add(e);
                l = new StringBuilder();
            } else {
                l.append(ele.charAt(i));
            }
            if (i == ele.length() - 1) {
                myElement e = new myElement(l.toString());
                set.add(e);
            }
        }
    }

    public mySet(String name) {
        this.name = name;
        myElement a = new myElement("a");
        myElement b = new myElement("b");
        myElement c = new myElement("c");
        set.add(a);
        set.add(b);
        set.add(c);
    }

    //输出集合
    public void outTheSet() {
        System.out.print("集合" + name + ": ");
        System.out.print("<");
        for (int i = 0; i < set.size(); i++) {
            if (i != set.size() - 1) {
                if (!Objects.equals(set.get(i).value, "")) {
                    System.out.print(set.get(i).value);
                } else {
                    System.out.print("空集");
                }
                System.out.print(",");
            } else {
                System.out.print(set.get(i).value);
            }
        }
        System.out.println(">");
    }
}
