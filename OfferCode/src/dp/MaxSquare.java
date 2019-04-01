package dp;

public class MaxSquare {
    public static void main(String []args){
        MaxSquareSolution maxSquareSolution=new MaxSquareSolution();
        char[][] matrix={{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        System.out.println(maxSquareSolution.maximalSquare(matrix));
    }
}
class MaxSquareSolution{
    public int maximalSquare(char[][] matrix) {
        int n=matrix.length;
        if(n==0)return 0;
        int m=matrix[0].length;
        int [][]dp=new int[n][m];
        int res=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i==0||j==0)
                    dp[i][j]=matrix[i][j]-'0';
                else if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        return res*res;
    }
}
