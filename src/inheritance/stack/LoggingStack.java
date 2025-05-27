package inheritance.stack;

import java.util.Stack;

public class LoggingStack extends Stack<Integer> {

    @Override
    public Integer push(Integer item) {
        System.out.println("Pushing item: " + item);
        return super.push(item);
    }

    @Override
    public synchronized Integer pop() {

        Integer poppedItem = super.pop();
        System.out.println("Popping item: " + poppedItem);


        return poppedItem;
    }

    public void pushAll(int ... elements) {
        for (Integer element : elements) {
            push(element);
        }
    }
}
