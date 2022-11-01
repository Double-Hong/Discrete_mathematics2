import java.util.ArrayList;
import java.util.Scanner;

public class algebraicSystem {
    int id;
    mySet set;
    String IE;
    ArrayList<String> results = new ArrayList<>();
    Scanner in = new Scanner(System.in);

    public algebraicSystem(mySet set, int id) {
        this.set = set;
        this.id = id;
    }

    public algebraicSystem() {

    }

    //输入定义在该集合上的二元关系
    public void inputTheRelation() {
        for (int i = 0; i < set.set.size(); i++) {
            for (int j = 0; j < set.set.size(); j++) {
                System.out.print(set.set.get(i).value + " * " + set.set.get(j).value + " = ");
                String result = in.nextLine();
                results.add(result);
            }
        }
    }

    public void showRelation() {
        int n = 0;
        set.outTheSet();
        System.out.println("集合" + set.name + "上的二元关系: ");
        for (int i = 0; i < set.set.size(); i++) {
            for (int j = 0; j < set.set.size(); j++) {
                System.out.print(set.set.get(i).value + "\t*\t" + set.set.get(j).value + "\t=\t" + results.get(n) + "\t\t");
                n++;
            }
            System.out.println();
        }
    }
}
