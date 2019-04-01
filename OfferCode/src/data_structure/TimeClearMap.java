package data_structure;

import java.util.Timer;
import java.util.Date;
import java.util.TimerTask;

public class TimeClearMap <K,V>{
    public static void main(String []args) {

    }
    private class Entry<K,V>{
        private final int hash;
        private final K key;
        V value;
        Entry<K,V> next;
        private long date;
        Entry(int hash, K key, V value, Entry<K,V> next,long startTime) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
            this.date=startTime;
        }

    }
    private class MyTask extends TimerTask {
        @Override
        public void run() {
            Date date=new Date();
            long curTime ;
            for(int i=0;i<size;i++){
                while(table[i].next!=null){
                    curTime = date.getTime();
                    if(curTime-table[i].date>5000){
                        //Remove(int i,Key k);
                        table[i]=table[i].next;
                    }
                }
            }
        }
    }
    private static final int DEFAULT_CAPACITY = 1 << 4;

    private Entry<K, V>[] table;

    private int capacity;

    private int size;

    public TimeClearMap() {
        this(DEFAULT_CAPACITY);
        Timer time =new Timer();
        time.schedule(new MyTask(),1000,5000);
    }

    public TimeClearMap(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException();
        } else {
            table = new Entry[capacity];
            size = 0;
            this.capacity = capacity;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0 ? true : false;
    }

    private int hash(K key) {
        double tmp = key.hashCode() * (Math.pow(5, 0.5) - 1) / 2;
        double digit = tmp - Math.floor(tmp);
        return (int) Math.floor(digit * capacity);
    }

    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int hash = hash(key);
        Date date=new Date();
        long startTime = date.getTime();//当前时间
        Entry<K, V> nEntry = new Entry<K, V>(hash, key, value, null,startTime);
        Entry<K, V> entry = table[hash];
        while (entry != null) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
            entry = entry.next;
        }
        nEntry.next = table[hash];
        table[hash] = nEntry;

        size++;
    }

    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int hash = hash(key);
        Entry<K, V> entry = table[hash];
        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }

}


