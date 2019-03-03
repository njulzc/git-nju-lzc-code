package sort;

public class MergeSort {
     static void msort(int []a,int start,int end){
        int mid=(start+end)>>1;
        if(start<end){
            msort(a,start,mid);
            msort(a,mid+1,end);
            mergeSort(a,start,mid,end);
        }
    }
     static void mergeSort(int []a,int start,int mid,int end){
        int []t=new int[end-start+1];
        int i=start,j=mid+1,k=0;
        while(i<=mid&&j<=end){
            if(a[i]<a[j])
                t[k++]=a[i++];
            else
                t[k++]=a[j++];
        }
        while(i<=mid){
            t[k++]=a[i++];
        }
        while(j<=end){
            t[k++]=a[j++];
        }
        for(int p:t){
            a[start++]=p;
        }
    }
    public static void main(String []args){
        int[] b = { 49, 38, 65, 97, 76, 13, 27, 50 };
        msort(b, 0, b.length - 1);
        for(int w:b)
            System.out.print(" "+w);
    }
}
