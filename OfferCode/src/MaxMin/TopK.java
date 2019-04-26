package MaxMin;

public class TopK {
    public static void main(String []args){
        Solution solution=new Solution();
        int[] b = { 49, 38, 65, 97, 76, 13, 27, 50 };
       int max= solution.findKthLargest(b,2);
       System.out.println(max);

    }
}
class Solution {
    public int findKthLargest(int[] nums, int k) {
        if(nums.length<k){
            return 0;
        }
        HeapSort(nums,k-1);
        return nums[0];

    }
    public void HeapSort(int nums[],int len){
        int i;
        for(i=len/2;i>=0;i--){
            HeapAdjust(nums,i,len);
        }
        for(i=len+1;i<nums.length;i++){
            if(nums[0]<=nums[i]){
                swap(nums,0,i);
                HeapAdjust(nums,0,len);
            }
        }

    }
    public void HeapAdjust(int a[],int start,int len){
        int temp,j;
        temp=a[start];
        for(j=2*start;j<=len;j*=2){
            if(j<len&&a[j]>a[j+1])
                j++;
            if(temp<=a[j])
                break;
            a[start]=a[j];
            start=j;
        }
        a[start]=temp;
    }
    public void swap(int nums[],int a,int b){
        int tmp=nums[a];
        nums[a]=nums[b];
        nums[b]=tmp;
    }
}
