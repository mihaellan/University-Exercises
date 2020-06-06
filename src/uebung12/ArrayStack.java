package uebung12;

import java.util.Arrays;

public class ArrayStack {
    private String[] dataArray;
    private int topIndex;

    public ArrayStack(int size) {
        dataArray = new String[size];
        topIndex = 0;
    }

    public boolean isEmpty() {
        return topIndex == 0;
    }

    public void push(String data) {
        if (topIndex == dataArray.length) {
            System.out.println("Stack overflow!");
        } else {
            dataArray[topIndex] = data;
            topIndex++;
        }
    }

    public String pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return null;
        } else {
            topIndex--;
            String data = dataArray[topIndex];
            dataArray[topIndex] = null;
            return data;
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(dataArray);
    }
}
