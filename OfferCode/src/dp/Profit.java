package dp;

public class Profit {
    public static void main(String []args){
        Solution s=new Solution();
        int[] prices={3,3,5,0,0,3,1,4};
        int result=s.maxProfit(prices);
        System.out.println(result);
    }
}
 class Solution {
    public int maxProfit(int[] prices) {

        int len=prices.length;
        if(len==1||prices==null||len==0){
            return 0;
        }
        int []left=new int[len];
        int []right=new int[len];
        int min=prices[0];
        for(int i=1;i<len;i++){
            if(min<prices[i]){
                left[i]=Math.max(left[i-1],prices[i]-min);
            }else{
                left[i]=left[i-1];
                min=prices[i];
            }
            System.out.print(left[i]+",");
        }
        System.out.println();
        int max=prices[len-1];
        for(int i=len-2;i>=0;i--){
            if(max>prices[i]){
                right[i]=Math.max(right[i+1],max-prices[i]);
            }else{
                right[i]=right[i+1];
                max=prices[i];
            }
            System.out.print(right[i]+",");
        }
        System.out.println();
        int result=0;
        for(int i=0;i<len;i++){
            result=Math.max(result,left[i]+right[i]);
        }
        return result;
    }

}