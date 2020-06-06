package uebung12;

import java.util.Arrays;

public class ArrayQueue {
    private String[] dataArray;
    private int frontIndex;
    private int backIndex;
    private boolean empty;

    public ArrayQueue(int size) {
        dataArray = new String[size];
        frontIndex = 0;
        backIndex = 0;
        empty = true;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void offer(String data) {
        if ((frontIndex == backIndex) && !empty) {
            System.out.println("Queue overflow!");
        } else {
            dataArray[backIndex] = data;
            backIndex++;
            if (backIndex == dataArray.length) {
                backIndex = 0;
            }
            empty = false;
        }
    }

    public String poll() {
        if (empty) {
            System.out.println("Queue is empty!");
            return null;
        } else {
            String data = dataArray[frontIndex];
            dataArray[frontIndex] = null;
            frontIndex++;
            if(frontIndex == dataArray.length) {
                frontIndex = 0;
            }
            if (frontIndex == backIndex) {
                empty = true;
            }
            return data;
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(dataArray);
    }
}
