package uebung12;

public class LinkedStack {
    private Node topNode;

    public LinkedStack() {
        topNode = null;
    }

    public boolean isEmpty() {
        return topNode == null;
    }

    public void push(String data) {
        Node newNode = new Node(data);
        if(isEmpty()) {
            topNode = newNode;
        } else {
            newNode.setNextNode(topNode);
            topNode = newNode;
        }
    }

    public String pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return null;
        } else {
            String data = topNode.getData();
            topNode = topNode.getNextNode();
            return data;
        }
    }

    @Override
    public String toString() {
        StringBuilder stackBuilder = new StringBuilder();
        Node nextNode = topNode;
        while (nextNode != null) {
            stackBuilder.append(nextNode.getData());
            nextNode = nextNode.getNextNode();
            if(nextNode != null) {
                stackBuilder.append("->");
            }
        }
        return stackBuilder.toString();
    }
}
