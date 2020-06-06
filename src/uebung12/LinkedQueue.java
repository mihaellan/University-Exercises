package uebung12;

public class LinkedQueue {
    private Node frontNode;
    private Node backNode;

    public LinkedQueue() {
        frontNode = null;
        backNode = null;
    }

    public boolean isEmpty() {
        return frontNode == null;
    }

    public void offer(String data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            frontNode = newNode;
            backNode = newNode;
        } else {
            backNode.setNextNode(newNode);
            backNode = newNode;
        }
    }

    public String poll() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return null;
        } else {
            String data = frontNode.getData();
            frontNode = frontNode.getNextNode();
            if (frontNode == null) {
                backNode = null;
            }
            return data;
        }
    }

    @Override
    public String toString() {
        StringBuilder stackBuilder = new StringBuilder();
        Node nextNode = frontNode;
        while (nextNode != null) {
            stackBuilder.append(nextNode.getData());
            nextNode = nextNode.getNextNode();
            if (nextNode != null) {
                stackBuilder.append("->");
            }
        }
        return stackBuilder.toString();
    }
}
