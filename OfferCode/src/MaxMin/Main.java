package MaxMin;

import javafx.scene.transform.Scale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        int nums[]=new int [n];
        String[] str=in.nextLine().split(" ");
        for(int i=0;i<n;i++){
            nums[i]=Integer.parseInt(str[0]);
        }
        if (nums.length == 0)  System.out.println(0);
        else if (nums.length == 1) System.out.println( nums[0]);
        else{
            ArrayList<Integer> dpl=new ArrayList<>();
            ArrayList<Integer> dpr=new ArrayList<>();
            dpl.add(nums[0]);
            dpr.add(nums[nums.length-1]);
            for(int i=1;i<nums.length;i++){
                int left=nums[i]+ dpl.get(i-1)-2*nums[i-1]>nums[i]?nums[i]+dpl.get(i-1)-2*nums[i-1]:nums[i];
                dpl.add(left);
                int rightIndex=nums.length-i-1;
                int right=nums[rightIndex]+dpr.get(i-1)-2*nums[rightIndex+1]>nums[rightIndex]?nums[rightIndex]+dpr.get(i-1)-2*nums[rightIndex]: nums[rightIndex];
                dpr.add(right);
            }
            int max=dpl.get(0)+dpr.get(nums.length-1)-nums[0];
            int index=0;
            for (int i = 1; i < nums.length; i++) {
                if (max < dpl.get(i) + dpr.get(nums.length-1-i)- nums[i]) {
                    max = dpl.get(i) + dpr.get(nums.length-1-i)- nums[i];
                }
            }
            System.out.println(max);
        }

    }
}
class main{
    public void ss() {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        String s = in.nextLine();
        char c[] = s.toCharArray();

        for (int i = 0; i < c.length; i++) {
            if (c[i]=='0') {
                int left = i + 1;
                int right = i + 8;
                while (left < right)
                    swap(c, left++, right--);
                for(int j=i+1;j<=i+8;j++){
                    System.out.print(c[j]);
                }
                System.out.print(" ");
                i=i+8;

            }if(c[i]=='1'){
                for(int j=i+1;j<=i+8;j++){
                    System.out.print(c[j]);
                }
                System.out.print(" ");
                i=i+8;
            }

        }
    }
    public  void swap(char []c,int a,int b){
        char tmp=c[a];
        c[a]=c[b];
        c[b]=tmp;
    }
}
  /*  static int car(int a, int b, int c, int d, int e, int f) {
        int[] g = {0, 5, 3, 1};
        int ans, x, y;

        ans = f + e + d + (c + 3) / 4;
        x = 5 * d + g[c % 4];
        if (x < b) {
            ans += (b - x + 8) / 9;
        }
        y = 36 * ans - 36 * f - 25 * e - 16 * d - 9 * c - 4 * b;
        if (y < a) {
            ans += (a - y + 35) / 36;
        }
        System.out.println(ans);

        return 0;
    }
class Solution {
    public String longestPalindrome(String s) {
        int len = s.length();
        char[] c = s.toCharArray();
        if (len == 0) return null;
        int i, j;
        int dp[][] = new int[len][len];
        for (i = 0; i < len; ++i) {
            dp[i][i] = 1;
        }
        int biggest = 1, begin = 0;
        for (i = len - 2; i >= 0; i--) {
            for (j = i + 1; j < len; j++) {
                if (c[j] == c[i] && dp[i + 1][j - 1] == (j - i)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                    if (dp[i][j] > biggest) {
                        biggest = dp[i][j];
                        begin = i;
                    }
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return s.substring(begin, biggest);
    }
}

/*public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n=Integer.parseInt(in.nextLine()) ;
        int x[]=new int[n];
        int a[]=new int [n];
        for(int i=0;i<n;i++){
            String[] str=in.nextLine().split(" ");
            x[i]=Integer.parseInt(str[0]);
            a[i]=Integer.parseInt(str[1]);
        }
        int sum1=0,sum2=0;
        int left=Integer.MIN_VALUE;int right=Integer.MAX_VALUE;
        int leftindex=0,rightindex=0;
        for(int i=0;i<n;i++){
            if(x[i]<0){
                sum1+=a[i];
                if(x[i]>left)
                    left=x[i];
            }

            else{
                sum2+=a[i];
                if(x[i]<right)
                    right=x[i];
            }
        }
        for(int i=0;i<n;i++){
            if(x[i]==right)
                rightindex=i;
            else if(x[i]==left)
                leftindex=i;
        }
        int res=0;
        if(rightindex!=0&&leftindex!=0){
            res=Math.max(sum1+a[rightindex],sum2+a[leftindex]);
            System.out.println(res);

        }else{
            if(sum1==0){
                res=sum2;
            }
            if(sum2==0){
                res=sum1;
            }
            System.out.println(res);
        }
    }
}
/*public class Main {
    public static void main(String []args){

        int coins[]={1,2,5};int amount=3;
        int[] dp=new int[amount+1];
        dp[0]=1;
        //先找大面额，再找小面额
        for(int j=coins.length-1;j>=0;j--){
            for(int i=0;i<amount+1;i++){
                    if(i-coins[j]>=0){
                        dp[i]+=dp[i-coins[j]];
                }
            }
        }

        System.out.println(dp[amount]);
    }
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle.size()==0)
            return 0;
        int n=triangle.size();
        for(int i=1;i<n;i++){
            for(int j=0;j<triangle.get(i).size();j++){
                if(j==0){
                    triangle.get(i).set(j,triangle.get(i).get(j)+ triangle.get(i-1).get(j));
                }else if(j==triangle.get(i).size()-1){
                    triangle.get(i).set(j,triangle.get(i).get(j)+ triangle.get(i-1).get(triangle.get(i-1).size()-1));
                }else{
                    triangle.get(i).set(j,triangle.get(i).get(j)+Math.min(triangle.get(i-1).get(j),triangle.get(i-1).get(j-1)));
                }
            }
        }
        int res=triangle.get(n-1).get(0);
        for(int i=1;i<triangle.get(n-1).size();i++){
            res=Math.min(res,triangle.get(n-1).get(i));
        }
        return res;
    }
    public int rob(int[] nums) {
        if(nums.length==0)
            return 0;
        if(nums.length==1)
            return nums[0];
        int a=Math.max(nums[0],nums[1]);
        if(nums.length==2)
            return a;
        int dp[]=new int [nums.length];
        dp[0]=nums[0];dp[1]=a;

        for(int i=2;i<nums.length;i++){
            dp[i]=Math.max(dp[i-2]+nums[i],nums[i]);
        }
        return dp[nums.length-1];
    }
}*/
