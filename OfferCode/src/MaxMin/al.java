package MaxMin;

import java.util.*;

public class al {
    static String calculate(int m, int k) {
        ArrayList<Integer> list =new ArrayList<>();
        list.add(1);list.add(2);list.add(3);list.add(4);
        int index=4;
        int fn=0;
        while(index<=m){
            fn=list.get(index-2)+list.get(index-3);
            list.add(fn);
            System.out.println(fn+" "+reverse(fn));
            list.add(reverse(fn));
            index++;
        }
        System.out.println(list);
        return null;

    }

    static int reverse(int x){
        int res=0;int k=0;
        while(x!=0){
            k=x%10;
            x=x/10;
            res=res*10+k;
        }
        return res;
    }
    public static void main(String[] args){
        System.out.println(reverse(465));
        calculate(20,3);
    }
}
