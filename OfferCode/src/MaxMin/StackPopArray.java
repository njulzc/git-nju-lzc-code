package MaxMin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class StackPopArray {
    static ArrayList<ArrayList<Integer>> lists=new ArrayList<ArrayList<Integer>>();

    public static void main(String []args){
        int array[]={1,2,3};
        int pushs[]={1,2,3};
        allArrayList(array,0,pushs);//全排列


    }
    static boolean isPopStack(int push[],int pop[]){
        Stack<Integer> stack=new Stack<Integer>();
        int k=0;
        for(int i=0;i<pop.length;i++){
            stack.push(push[i]);
            while(!stack.empty()&&stack.peek()==pop[k]){
                 stack.pop();
                 k++;
            }
        }
        return stack.empty();
    }
    static void allArrayList(int[] array,int start,int push[]){
        if(start==array.length){
            if(isPopStack(push,array)){

               /* ArrayList<Integer> list=new ArrayList<Integer>();
                for(int i=0;i<array.length;i++)
                    list.add(array[i]);
                lists.add(list);*/
                System.out.println(Arrays.toString(array));
            }

        }
        else{
            for(int i=start;i<array.length;++i){
                exchange(array,start,i);  //  交换元素
                allArrayList(array,start+1,push);  //交换后，再进行全排列算法
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
