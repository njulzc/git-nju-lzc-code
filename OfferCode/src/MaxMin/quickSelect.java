package MaxMin;

import java.util.*;

public class quickSelect {
    public static int sol(int N){
        int sum=0;
       /* for(int i=1;;i++){
            if(i*i>2*N*N*2){
                sum=(i-1)*(i-1);
                break;
            }
        }*/
       for(int i=1;;i++){
           if(i*i*2<N*N&&(i+1)*(i+1)*2>N*N){
               sum=i*8;
               break;
           }
       }
        return sum;
    }
    private static class cat{
        private final int x;
        private  final int y;
        public cat(int x,int y){
            this.x=x;
            this.y=y;
        }

        @Override
        public String toString() {
            return x+" "+y;
        }

        @Override
        public boolean equals(Object obj) {
            cat other=(cat) obj;
            return x==other.x&&y==other.y;
            //return true;
        }
    }
    public static void main(String []args){
        Scanner in=new Scanner(System.in);
        int n=Integer.parseInt(in.nextLine());
        int m=Integer.parseInt(in.nextLine());
        ArrayList<ArrayList<cat>> lists=new ArrayList<ArrayList<cat>>();

        for(int i=0;i<n;i++){
            int max=1;
            int res=1;
            for(int j=0;j<m;j++){
                String str[]=in.nextLine().split(" ");
                ArrayList<cat> list=new ArrayList<>();
                for(int k=1;k<str.length;k+=2){
                    list.add(new cat(Integer.parseInt(str[k]),Integer.parseInt(str[k+1])));
                }
                lists.add(list);
            }
            int index=0;
            for(int p=0;p<lists.get(index).size();p++){
                for(int z=1;z<m;z++){
                    if(lists.get(z).contains(lists.get(index).get(p))){
                        max++;
                    }else{
                       max=1;
                       index=z-1;
                    }
                }
                if(max>res)
                    res=max;
            }
            System.out.println(res);
        }
        boolean b=lists.get(0).contains(lists.get(1).get(0));
        System.out.println(lists.toString());
        System.out.println(b);
    }
}
