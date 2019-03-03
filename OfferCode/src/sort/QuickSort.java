package sort;

public class QuickSort {

    public static void main(String []args){
        int[] b = { 49, 38, 65, 97, 76, 13, 27, 50 };
        qsort(b, 0, b.length - 1);
        for(int w:b)
            System.out.print(" "+w);
    }
    static void qsort(int []a,int low,int high){
        int pivot;
        while(low<high){
            pivot=partition(a,low,high);
            qsort(a,low,pivot);
            low=pivot+1;
        }
    }
    static int  partition(int a[],int low,int high){
        int mid=(low+high)>>1;
        /*前中后三点取大小在中间的一点,让a[low]为中间值*/
        if(a[low]>a[high])
            swap(a,low,high);
        if(a[mid]>a[high])
            swap(a,mid,high);
        if(a[mid]>a[low])
            swap(a,low,mid);
        int pivotkey=a[low];  //枢轴值
        while(low<high){
            while(low<high&&a[high]>=pivotkey)
                high--;
            a[low]=a[high];
            while(low<high&&a[low]<=pivotkey)
                low++;
            a[high]=a[low];
        }
        a[low]=pivotkey;
        return low; //返回枢轴值在排序后所在位置

    }
    static void swap(int a[],int low,int high){
        int temp=a[low];
        a[low]=a[high];
        a[high]=temp;
    }
}
