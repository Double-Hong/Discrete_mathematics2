import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Menu {
    ArrayList<mySet> s = new ArrayList<>();
    Scanner in = new Scanner(System.in);

    public void showMenu() {
        while (true) {
            System.out.println("1----创建集合");
            System.out.println("2----显示集合");
            System.out.println("3----构建代数系统");
            String select = in.nextLine();
            switch (select) {
                case "1": {
                    mySet newSet = new mySet();
                    checkSameName(newSet);
                    break;
                }
                case "2": {
                    showAllSet();
                    break;
                }
                case "3":{

                    break;
                }
            }
        }
    }

    public void showAllSet() {
        if (s.size() == 0) {
            System.out.println("当前没有集合");
        } else {
            System.out.println("当前集合数: " + s.size());
            for (int i = 0; i < s.size(); i++) {
                s.get(i).outTheSet();
            }
        }
    }
    public void checkSameName(mySet newSet) {
        boolean flag = true;
        for (int i = 0; i < s.size(); i++) {
            if (Objects.equals(s.get(i).name, newSet.name)) {
                flag = false;
                break;
            }
        }
        if (flag) {
            s.add(newSet);
            System.out.println("创建成功 !");
        } else {
            System.out.println("集合名已重复 ! !");
        }
    }
}

