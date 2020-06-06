package uebung12;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(4);
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
