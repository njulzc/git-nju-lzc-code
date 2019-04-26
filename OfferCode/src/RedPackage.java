import java.math.BigDecimal;
import java.util.*;

public class RedPackage {
    public static List<Integer>  divideRedPackage(int allMoney, int peopleCount,int MAX) {
        //人数比钱数多则直接返回错误
        if(peopleCount<1||allMoney<peopleCount){
            System.out.println("钱数人数设置错误！");
            return null;
        }
        List<Integer> indexList = new ArrayList<>();
        List<Integer> amountList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < peopleCount - 1; i++) {
            int index;
            do{
                index = random.nextInt(allMoney - 2) + 1;
            }while (indexList.contains(index));//解决碰撞
            indexList.add(index);
        }
        Collections.sort(indexList);
        int start = 0;
        for (Integer index:indexList) {
            //解决最大红包值
            if(index-start>MAX){
                amountList.add(MAX);
                start=start+MAX;
            }else{
                amountList.add(index-start);
                start = index;
            }
        }
        amountList.add(allMoney-start);
        return amountList;
    }
    public static void main(String args[]){
        Scanner in=new Scanner(System.in);
        int n=Integer.parseInt(in.nextLine());
        int pnum=Integer.parseInt(in.nextLine());
        int money=n*100;int max=n*90;
        List<Integer> amountList = divideRedPackage(money, pnum,max);
        if(amountList!=null){
            for (Integer amount : amountList) {
                System.out.println("抢到金额：" + new BigDecimal(amount).divide(new BigDecimal(100)));
            }
        }

    }
}
