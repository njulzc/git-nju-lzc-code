package sort;
import java.util.ArrayList;
import java.util.Arrays;

public class Permute {
    static ArrayList<ArrayList<Integer>> lists=new ArrayList<ArrayList<Integer>>();
    static  ArrayList<String> str=new ArrayList<String>();
    public static void main(String args[]){
        int array[]={1,2,2};
        allArrayList(array,0);
        System.out.println(lists.toString());
    }
    static void allArrayList(int[] array,int start){
        if(start==array.length){
            String arrayStr = Arrays.toString(array);
            if(!str.contains(arrayStr)){
                str.add(arrayStr);
                ArrayList<Integer> list=new ArrayList<Integer>();
                for(int i=0;i<array.length;i++)
                    list.add(array[i]);
                lists.add(list);
                System.out.println(Arrays.toString(array));
            }
            return;
        }
        else{
            for(int i=start;i<array.length;++i){
                exchange(array,start,i);  //  交换元素
                allArrayList(array,start+1);  //交换后，再进行全排列算法
                exchange(array,start,i);  //还原成原来的数组，便于下一次的全排列
            }
        }
    }
    static void exchange(int[] array,int s,int i){
        int t=array[s];
        array[s]=array[i];
        array[i]=t;
    }
}

