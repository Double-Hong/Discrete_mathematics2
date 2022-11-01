import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Menu {
    ArrayList<mySet> s = new ArrayList<>();
    ArrayList<String> Wrongs = new ArrayList<>();
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
                    showAllSet();
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
                        if (judgeSystem(as)) {
                            System.out.println("代数系统" + as.id + "是群");
                        } else {
                            System.out.println("代数系统" + as.id + "不是群");
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
        int time;
        int wrong = 0;
        for (int i = 0; i < as.results.size(); i++) {
            time = 0;
            for (int j = 0; j < as.set.set.size(); j++) {
                if (Objects.equals(as.results.get(i), as.set.set.get(j).value)) {
                    time++;
                    break;
                }
            }
            if (time == 0) {
                wrong = 1;
            }
        }
        if (wrong == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 计算两个元素结果
     *
     * @param a  第一个元素的序号
     * @param b  第二个元素的序号
     * @param as 代数系统
     * @return results里面的值
     */
    String culAandB(int a, int b, algebraicSystem as) {
        return as.results.get(a * as.set.set.size() + b);
    }

    /**
     * 通过值来找序号
     *
     * @param value 元素的值
     * @param as    代数系统
     * @return 元素的序号
     */
    int findNumByValue(String value, algebraicSystem as) {
        for (int i = 0; i < as.set.set.size(); i++) {
            if (Objects.equals(as.set.set.get(i).value, value)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 判断代数系统是否有幺元
     *
     * @param as 代数系统
     * @return 是否有幺元
     */
    boolean judgeIdentityElement(algebraicSystem as) {
        int times = 0;
        boolean flag = false;
        for (int i = 0; i < as.set.set.size(); i++) {
            for (int j = 0; j < as.set.set.size(); j++) {
                if (Objects.equals(culAandB(i, j, as), as.set.set.get(j).value) &&
                        Objects.equals(culAandB(j, i, as), as.set.set.get(j).value)) {
                    times++;
                }
            }
            if (times == as.set.set.size()) {
//                System.out.println("幺元是" + as.set.set.get(i).value);
                as.IE = as.set.set.get(i).value;
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * 判断是否有逆元
     *
     * @param as 代数系统
     * @return 是否有逆元
     */
    boolean judgeInverseElement(algebraicSystem as) {
        boolean flag = false;
        int times = 0;
        if (!judgeIdentityElement(as)) {
            flag = false;
        } else {
            for (int i = 0; i < as.set.set.size(); i++) {
                for (int j = 0; j < as.set.set.size(); j++) {
                    if (Objects.equals(culAandB(i, j, as), as.IE) &&
                            Objects.equals(culAandB(j, i, as), as.IE)) {
                        times++;
                        break;
                    }
                }
            }
            if (times == as.set.set.size()) {
                flag = true;
            }
        }
        return flag;
    }

    //判断结合性
    boolean judgeAssociative(algebraicSystem as) {
        boolean flag = true;
        int n = 0;
        for (int i = 0; i < as.set.set.size(); i++) {
            for (int j = 0; j < as.set.set.size(); j++) {
                for (int k = 0; k < as.set.set.size(); k++) {
                    n++;
                    int a = findNumByValue(culAandB(i, j, as), as);
                    int b = findNumByValue(culAandB(j, k, as), as);
                    if (a != -1 && b != -1) {
                        if (!Objects.equals(culAandB(a, k, as), culAandB(i, b, as))) {
                            flag = false;
                            String s = as.set.set.get(i).value + "*" + as.set.set.get(j).value + "*" + as.set.set.get(k).value;
                            Wrongs.add(s);
                        }
                    } else {
                        return false;
                    }
                }
            }
        }

        return flag;
    }

    //判断系统是否为群
    boolean judgeSystem(algebraicSystem as) {
        int flag = 0;
        if (judgeClosed(as)) {
            flag++;
            System.out.println("代数系统" + as.id + "是封闭的");
        } else {
            System.out.println("代数系统" + as.id + "不是封闭的");
        }
        if (judgeAssociative(as)) {
            flag++;
            System.out.println("代数系统" + as.id + "具有结合性");
        } else {
            System.out.println("代数系统" + as.id + "不具有结合性");
            for (int i = 0; i < Wrongs.size(); i++) {
                System.out.print(Wrongs.get(i) + "   ");
                if ((i + 1) % 3 == 0) {
                    System.out.println();
                }
            }
            System.out.println();
        }
        if (judgeIdentityElement(as)) {
            flag++;
            System.out.println("代数系统" + as.id + "含有幺元");
        } else {
            System.out.println("代数系统" + as.id + "不含有幺元");
        }
        if (judgeInverseElement(as)) {
            flag++;
            System.out.println("代数系统" + as.id + "含有逆元");
        } else {
            System.out.println("代数系统" + as.id + "不含有逆元");
        }
        if (flag == 4) {
            return true;
        } else {
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

