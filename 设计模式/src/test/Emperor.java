package src.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class Emperor {
    //定义最多能产生的实例数量
    private static int maxNumOfEmperor = 2;
    //每个皇帝都有名字，使用一个ArrayList来容纳，每个对象的私有属性
    private static ArrayList<String> nameList=new ArrayList<String>();
    //定义一个列表，容纳所有的皇帝实例
    private static ArrayList<Emperor> emperorList=new ArrayList<Emperor>();
    //当前皇帝序列号
    private static int countNumOfEmperor =0;
    //产生所有的对象
    static{
        for(int i=0;i<maxNumOfEmperor;i++){
            emperorList.add(new Emperor("皇"+(i+1)+"帝"));
        }
    }
    private Emperor(){
//世俗和道德约束你，目的就是不产生第二个皇帝
    }
    //传入皇帝名称，建立一个皇帝对象
    private Emperor(String name){
        nameList.add(name);
    }
    //随机获得一个皇帝对象
    public static Emperor getInstance(){
        Random random = new Random();
//随机拉出一个皇帝，只要是个精神领袖就成
        countNumOfEmperor = random.nextInt(maxNumOfEmperor);
        return emperorList.get(countNumOfEmperor);
    }
    //皇帝发话了
    public void say(){
        System.out.println(nameList.get(countNumOfEmperor));
    }

    public static void main(String[] args) {
        Emperor.getInstance().say();
        Emperor.getInstance().say();
        Emperor.getInstance().say();
        Emperor.getInstance().say();
    }
}


