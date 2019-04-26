package Concurrent;

import java.math.BigDecimal;
import java.util.*;

public class HongBao {
    public static List<Integer> divideRedPackage(Integer totalAmount, Integer totalPeopleNum) {
        List<Integer> amountList = new ArrayList<Integer>();

        Integer restAmount = totalAmount;

        Integer restPeopleNum = totalPeopleNum;

        Random random = new Random();

        for (int i = 0; i < totalPeopleNum - 1; i++) {
            //随机范围：[1，剩余人均金额的两倍)，左闭右开
            int amount = random.nextInt(restAmount / restPeopleNum * 2 - 1) + 1;
            restAmount -= amount;
            restPeopleNum--;
            amountList.add(amount);
        }
        amountList.add(restAmount);

        return amountList;
    }
    public static List<Integer> divideRedPackagePlan1(Integer totalAmount, Integer totalPeopleNum) {
        List<Integer> amountList = new ArrayList<Integer>();
        for(int i=0;i<totalPeopleNum;i++){
            amountList.add(1);
        }
        Integer restAmount = totalAmount-totalPeopleNum;

        Integer restPeopleNum = totalPeopleNum;

        Random random = new Random();

        for (int i = 0; i < totalPeopleNum - 1; i++) {
            //随机范围：[1，剩余人均金额的两倍)，左闭右开
            int amount = random.nextInt(restAmount-1) + 1;
            restAmount -= amount;
            restPeopleNum--;
            amountList.set(i,amount+amountList.get(i));
        }
        amountList.add(restAmount);

        return amountList;
    }
    public static List<Integer> divideRedPackageNew(Integer totalAmount, Integer totalPeopleNum) {
        //人数比钱数多则直接返回错误
        if(totalAmount<totalPeopleNum){
            System.out.println("钱数人数设置错误！");
            return null;
        }
        //随机分割totalPeopleNum-1个点
        List<Integer> indexList = new ArrayList<>();
        List<Integer> amountList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < totalPeopleNum - 1; i++) {

            int index;
            {
                index = random.nextInt(totalAmount - 2) + 1;
                //如果出现碰撞怎么办
            }
            while (indexList.contains(index));
            indexList.add(index);
        }
        Collections.sort(indexList);
        int start = 0;
        for (Integer index:indexList) {
            amountList.add(index-start);
            start = index;
        }
        amountList.add(totalAmount-start);
        return amountList;
    }

    public static void main(String[] args) {
        List<Integer> amountList = divideRedPackageNew(5000, 10);
        for (Integer amount : amountList) {
            System.out.println("抢到金额：" + new BigDecimal(amount).divide(new BigDecimal(100)));
        }
    }

}
