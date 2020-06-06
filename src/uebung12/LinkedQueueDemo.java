package uebung12;

public class LinkedQueueDemo {
    public static void main(String[] args) {
        LinkedQueue queue = new LinkedQueue();
        System.out.println("Queue: " + queue);
        System.out.println("Poll: " + queue.poll());
        queue.offer("A");
        queue.offer("B");
        queue.offer("C");
        queue.offer("D");
        queue.offer("E");
        System.out.println("Queue: " + queue);
        System.out.println("Poll: " + queue.poll());
        System.out.println("Poll: " + queue.poll());
        System.out.println("Queue: " + queue);
        queue.offer("F");
        System.out.println("Queue: " + queue);
        System.out.println("Poll: " + queue.poll());
        System.out.println("Poll: " + queue.poll());
        System.out.println("Queue: " + queue);
        queue.offer("G");
        System.out.println("Queue: " + queue);
        System.out.println("Poll: " + queue.poll());
        System.out.println("Queue: " + queue);
    }
}
