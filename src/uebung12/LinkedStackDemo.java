package uebung12;

public class LinkedStackDemo {
    public static void main(String[] args) {
        LinkedStack stack = new LinkedStack();
        System.out.println("Stack: " + stack);
        System.out.println("Pop: " + stack.pop());
        System.out.println("Pushing \"A\"");
        stack.push("A");
        System.out.println("Pushing \"B\"");
        stack.push("B");
        System.out.println("Pushing \"C\"");
        stack.push("C");
        System.out.println("Pushing \"D\"");
        stack.push("D");
        System.out.println("Stack: " + stack);
        System.out.println("Pop: " + stack.pop());
        System.out.println("Stack: " + stack);
        System.out.println("Pop: " + stack.pop());
        System.out.println("Stack: " + stack);
    }
}
