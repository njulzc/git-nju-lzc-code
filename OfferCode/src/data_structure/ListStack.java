package data_structure;

class Node<E>{
    Node<E> next=null;
    E data;
    public Node(E data){
        this.data=data;
    }
}
public class ListStack<E> {
    private Node<E> top=null;
    public void push(E data){
        Node<E> newNode=new Node<E>(data);
        newNode.next=top;
        top=newNode;
    }
    public E pop(){
        if(isEmpty())
            return null;
        E data=top.data;
        top=top.next;
        return data;
    }
    public E peek(){
        if(isEmpty())
            return null;
        return (E)top.data;
    }
    public boolean isEmpty(){
        return top==null?true:false;
    }
    public static void main(String []args){
        ListStack<Integer> stack=new ListStack<>();
        stack.push(2);
        stack.push(3);
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.peek());
    }
}
