package data_structure;

import java.util.Arrays;

public class ArrayStack<E> {
    private Object []arrayStack;
    private int size;
    public ArrayStack(){
        arrayStack=new Object[10];
    }

    public E push(E item){
        ensureCapacity(size+1);
        arrayStack[size++]=item;
        return  item;
    }
    public E pop(){
        E item=peek();
        arrayStack[size-1]=null;
        size--;
        return item;
    }
    public E peek(){
        if(!isEmpty())
            return (E)arrayStack[size-1];
        else
            return null;
    }
    public boolean isEmpty(){
        return size==0?true:false;
    }
    public void ensureCapacity(int size){
        int len=arrayStack.length;
        if(size>len){
            arrayStack= Arrays.copyOf(arrayStack,len+10);
        }
    }
    public static void main(String []args){
        ArrayStack<Integer> stack=new ArrayStack();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.size);
        System.out.println(stack.pop());
        System.out.println(stack.pop());

    }
}
