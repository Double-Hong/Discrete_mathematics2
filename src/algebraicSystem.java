import java.util.ArrayList;
import java.util.Scanner;

public class algebraicSystem {
    mySet set;
    ArrayList<String> results = new ArrayList<>();
    Scanner in = new Scanner(System.in);
    public algebraicSystem(mySet set){
        this.set = set;
    }

    //输入定义在该集合上的二元关系
    public void inputTheRelation(){
        for (int i=0;i<set.set.size();i++){
            for (int j=0;j<set.set.size();j++){
                System.out.print(set.set.get(i).value+" * "+set.set.get(j).value+" = ");
                String result = in.nextLine();
                results.add(result);
            }
        }
    }

    public void showRelation(){
        int n=0;
        for (int i = 0;i<set.set.size();i++){
            for (int j= 0;j<set.set.size();j++){
                System.out.print(set.set.get(i).value+" * "+set.set.get(j).value+" = "+results.get(n)+"\t");
                n++;
            }
            System.out.println();
        }
    }
}
