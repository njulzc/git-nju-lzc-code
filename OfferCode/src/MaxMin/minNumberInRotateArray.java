package MaxMin;

public class minNumberInRotateArray {
    static int minNumber(int []array){
        if(array.length==0)
            return 0;
        int left=0,right=array.length-1;
        int mid;
        while(left<right){
            mid=(left+right)>>1;
            if(array[mid]>array[right]) /*当中间的数大于右边的数，说明最小值在（mid，right]区间内*/
                left=mid+1;
            else if(array[mid]==array[right])/*因为数组可能有重复的数字，所以当判断到中间值和右侧值相等，只能一个一个遍历*/
                right--;
            else     /*其他情况array[mid]小于array[right]，最小值在区间[left,mid]内。array[left]可能小于array[mid]也可能大于或等于*/
                right=mid;
        }
        return array[left];
    }
    public static void main(String []args){
        int array[]={3,4,5,1,2};
        System.out.println(minNumber(array));
        int array1[]={1,0,1,1,1};
        System.out.println(minNumber(array1));
        int array2[]={1,1,1,0,1};
        System.out.println(minNumber(array2));
    }
}
