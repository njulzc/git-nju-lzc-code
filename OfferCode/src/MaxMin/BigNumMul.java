package MaxMin;
import java.util.*;
public class BigNumMul {
    public static void main(String args[]){
       Solution4 s=new Solution4();
       char str[]={'a','b','c','d','e','f','g'};
      s.rotateString(str,3);
        System.out.println(Arrays.toString(str));
    }
}

class SolutionBigNumMul{

    public int res[]=new int[10000];
    public void mul(int num){
        int pos=1;//当前数组的有效位数+1；
        res[0]=1;
        for(int i=2;i<=num;i++){
            for(int j=0;j<pos;j++){
                res[j]=res[j]*i;
            }
            pos=Carry(res,pos);//把数组从index=0~pos-1,进位，然后更新pos的值。
        }
        StringBuffer sb=new StringBuffer();//用一个StringBuffer来显示大数
        for(int i=pos-1;i>=0;i--){
            sb.append(res[i]);
        }
        System.out.println(sb.toString());
    }
    public int Carry(int res[],int pos){
        int carry=0;int i=0;
        for(;i<=pos-1;i++){
            res[i]+=carry;
            if(res[i]<10){
                carry=0;
            }else if(res[i]>9&&i<pos-1){
                carry=res[i]/10;
                res[i]=res[i]%10;
            }else{
                while(res[i]>9){
                    carry=res[i]/10;
                    res[i]=res[i]%10;
                    i++;
                    res[i]=carry;
                }
            }
        }
        return i;
    }
}
class Solution2 {

    public int[] twoSum(int[] numbers, int target) {
        // write your code here
        int res[]=new int[2];
        HashMap<Integer,Integer> map=new HashMap<Integer, Integer>();
        for(int i=0;i<numbers.length;i++){
            map.put(numbers[i],i);
        }
        for(int i=0;i<numbers.length;i++){
            if(map.containsKey(target-numbers[i])){
                res[0]=i;res[1]=map.get(target-numbers[i]);
            }
        }

        return res;
    }
}
class Solution3 {
    /**
     * @param n: The number of digits
     * @return: All narcissistic numbers with n digits
     */
    public List<Integer> getNarcissisticNumbers(int n) {
        // write your code here
        List<Integer> list=new ArrayList<>();
        int start=1;
        for(int i=1;i<n;i++){
            start*=10;
        }
        if(n==1)start=0;
        for(int i=start;i<(int)Math.pow(10.0,(double)n);i++){
            int sum=0;
            int tmp;
            int curnum=i;
            for(int j=0;j<n;j++){
                tmp=curnum%10;
               sum+= Math.pow((double)tmp,(double)n);
               curnum=curnum/10;
            }
            if(sum==i){
                list.add(i);
            }
        }
        return list;
    }
}
class Solution4 {
    /**
     * @param str: An array of char
     * @param offset: An integer
     * @return: nothing
     */
    public void rotateString(char[] str, int offset) {
        // write your code here
        int len=str.length-1;
        offset=offset%(len+1);
        for(int i=0;i<offset;i++){
            swap(str,i,len-i);
        }
        int left=0,right=offset-1;
        while(left<right){
            swap(str,left,right);
            left++;right--;
        }
        left=offset;right=len;
        while(left<right){
            swap(str,left,right);
            left++;right--;
        }

    }
    void swap(char[] str,int a,int b){
        char tmp=str[a];
        str[a]=str[b];
        str[b]=tmp;
    }

}