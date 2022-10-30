import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Menu {
    ArrayList<mySet> s = new ArrayList<>();

    ArrayList<algebraicSystem> ass = new ArrayList<>();

    public void showMenu() {
        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.println("1----创建集合");
            System.out.println("2----显示集合");
            System.out.println("3----构建代数系统");
            System.out.println("4----显示系统代数");
            System.out.println("5----判断代数系统");
            System.out.println("0----退出");
            String select = in.next();
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
                case "3": {
                    System.out.println("请输入集合名称: ");
                    String name = in.next();
                    if (findSetByName(name) != null) {
                        int id = ass.size() + 1;
                        algebraicSystem as = new algebraicSystem(findSetByName(name), id);
                        ass.add(as);
                        as.inputTheRelation();
                    } else {
                        System.out.println("没有集合" + name);
                    }
                    break;
                }
                case "4": {
                    if (ass.size() == 0) {
                        System.out.println("当前没有代数系统");
                    } else {
                        for (int i = 0; i < ass.size(); i++) {
                            ass.get(i).showRelation();
                        }
                    }
                    break;
                }
                case "5": {
                    showAlgebraicSystem();
                    System.out.println("请输入代数系统id: ");
                    int id = in.nextInt();
                    if (findSystemById(id) != null) {
                        algebraicSystem as = findSystemById(id);
                        if (judgeSystem(as)){
                            System.out.println("代数系统"+as.id+"是群");
                        }
                        else {
                            System.out.println("代数系统"+as.id+"不是群");
                        }
                    } else {
                        System.out.println("没有代数系统" + id);
                    }
                    break;
                }
                case "0": {
                    return;
                }
                default: {
                    System.out.println("请重新输入 !");
                    break;
                }
            }
            try {
                System.out.println("---------");
                System.out.println();
                System.out.println("按回车继续");
                System.out.println();
                new BufferedReader(new InputStreamReader(System.in)).readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //判断封闭性
    boolean judgeClosed(algebraicSystem as) {
        int time;int wrong =0;
        for (int i = 0;i<as.results.size();i++){
            time=0;
            for (int j=0;j<as.set.set.size();j++){
                if (Objects.equals(as.results.get(i),as.set.set.get(j).value)){
                    time++;
                    break;
                }
            }
            if (time==0){
                wrong=1;
            }
        }
        if (wrong==0){
            return true;
        }
        else {
            return false;
        }
    }

    //判断结合性
    boolean judgeAssociative(algebraicSystem as) {

        return false;
    }

    //判断系统是否为群
    boolean judgeSystem(algebraicSystem as) {
        int flag=0;
        if (judgeClosed(as)){
            flag++;
            System.out.println("代数系统"+as.id+"是封闭的");
        }
        else {
            System.out.println("代数系统"+as.id+"不是封闭的");
        }
        if (flag==4){
            return true;
        }
        else {
            return false;
        }
    }

    //通过代数系统id找代数系统
    public algebraicSystem findSystemById(int id) {
        algebraicSystem as = new algebraicSystem();
        boolean flag = false;
        for (int i = 0; i < ass.size(); i++) {
            if (ass.get(i).id == id) {
                as = ass.get(i);
                flag = true;
                break;
            }
        }
        if (flag) {
            return as;
        } else {
            return null;
        }
    }

    //简要展示所有代数系统
    public void showAlgebraicSystem() {
        for (int i = 0; i < ass.size(); i++) {
            System.out.println("代数系统" + ass.get(i).id);
            ass.get(i).set.outTheSet();
        }
    }

    public mySet findSetByName(String name) {
        for (int i = 0; i < s.size(); i++) {
            if (Objects.equals(s.get(i).name, name)) {
                return s.get(i);
            }
        }
        return null;
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

