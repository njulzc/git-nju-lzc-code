package MaxMin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Alibaba {
    public static void main(String []args){
        Shell shell=new Shell();
        shell.cat("E:test.log","Login");

     }
}
class Shell {
    void cat(String path, String target) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String str;
            int count=0;
            ArrayList<String> strlist = new ArrayList<>();
            while ((str = br.readLine()) != null) {
                count=uniq(str, target,count, strlist);
            }
            System.out.println(count);
            sort(strlist);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    int uniq(String str, String target,int count, ArrayList<String> strlist) {

        char[] tar = target.toCharArray();
        //KMP(c, tar, list, count);

        String strs[] = str.trim().split(target);
        if(strs.length>1)count+=strs.length-1;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strs.length; i++) {
            sb.append(strs[i]);
            if(count==1&&i==0){  //保留第一个Login
                sb.append(target);
            }
        }
        strlist.add(sb.toString());
        return count;
    }

    void sort(ArrayList<String> strlist) {
        Collections.sort(strlist);
        Collections.reverse(strlist);
        for (int i = 0; i < strlist.size(); i++) {
            System.out.println(strlist.get(i));
        }

    }
}
   /* void KMP(char []c, char []target, ArrayList<Integer> list,int count){
        int n=target.length;
        int next[]=new int [n];
        getNextArray(target,next,n);
        moveNextArray(next,n);
        int i=0,j=0;
        while(i<c.length){
            if(j==n-1&&c[i]==target[j]){
                int pos=i-j;
                count=1;
                if(count>1)
                    list.add(pos);
                j=next[j];
            }
            if(c[i]==target[j]){
                i++;j++;
            }else{
                j=next[j];
                if (j == -1) {
                    i++;j++;
                }
            }
        }
    }
    void getNextArray(char[] target,int []next,int n){
        next[0]=0;
        int len=0;
        int i=1;
        while(i<n){
            if(target[i]==target[len]){
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
    void moveNextArray(int next[],int n){
        int i;
        for( i=n-1;i>0;i--){
            next[i]=next[i-1];
        }
        next[0]=-1;
    }
}*/
