package data_structure;

import java.util.Arrays;

public class KMP {
   static  void getNextArray(char pattern[],int next[],int n){
        next[0]=0;
        int len=0;
        int i=1;
        while(i<n){
           if(pattern[i]==pattern[len]){
               len++;
               next[i]=len;
               i++;
           }else{
               if(len>0)
                   len=next[len-1];
               else{
                   next[i]=0;
                   i++;
               }
           }
        }
    }
    static  void moveNextArray(int next[],int n){
        int i;
        for( i=n-1;i>0;i--){
            next[i]=next[i-1];
        }
        next[0]=-1;
    }
    static void KMP(char []T,char []pattern){
        int n=pattern.length;
        int next[]=new int [n];
        getNextArray(pattern,next,n);
        moveNextArray(next,n);
        int i=0,j=0;
        while(i<T.length){
            if(j==n-1&&T[i]==pattern[j]){
                int pos=i-j;
                System.out.println("Found pattern at"+" "+pos);
                j=next[j];
            }
            if(T[i]==pattern[j]){
                i++;j++;
            }else{
                j=next[j];
                if (j == -1) {
                    i++;j++;
                }
            }
        }
    }
    public static void main(String []args){
        String s1="AB ALoginBAA BLoginB";
        String s3[]=s1.split("Login");
        System.out.println(Arrays.toString(s3));
        String s2="Login";
        KMP(s1.toCharArray(),s2.toCharArray());
    }
}
