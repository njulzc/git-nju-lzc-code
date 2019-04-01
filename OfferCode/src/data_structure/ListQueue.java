package data_structure;

 class qNode<E>{
    E data;
    qNode<E> next=null;
    public qNode(E data){
        this.data=data;
    }
}
public class ListQueue<E> {
     private qNode<E> head=null;
     private qNode<E> tail=null;
     public void push(E data){
         qNode<E> newNode=new qNode<E>(data);
        if(isEmpty()){
            head=newNode;
            tail=newNode;
        }
        else{
            tail.next=newNode;
            tail=newNode;
        }
     }
     public E pop(){
        if(isEmpty())
            return null;
        E data=head.data;
         if(head==tail){
             tail=null;
         }
         head=head.next;
        return data;
     }
    public E peekLast(){
        if(isEmpty())
            return null;
        E data=tail.data;

        return data;
    }
    public E peekFirst(){
        if(isEmpty())
            return null;
        E data=head.data;
        return data;
    }

     public boolean isEmpty(){
         if(tail==null&&head==null)
            return true;
         return false;
     }
     public int size(){
         qNode<E> tmp=head;
         int n=0;
         while(tmp!=null){
             tmp=tmp.next;
             n++;
         }
         return n;
     }
    public static void main(String []args){
        ListQueue<Integer> queue=new ListQueue<>();
        queue.push(1);
        queue.push(2);
        System.out.println(queue.size());
        System.out.println(queue.isEmpty());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.isEmpty());
        queue.push(3);
        queue.push(4);
        System.out.println(queue.peekFirst());
        System.out.println(queue.peekLast());

    }
}
