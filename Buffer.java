import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
    private final Queue<String> queue = new LinkedList<>();
    private final int size;
    Boolean is_producer_finished = false;

    public Buffer(int size) {
        this.size = size;
    }

    public boolean is_empty() {
        return queue.size() == 0;
    }

    public void finish() {
        // Called when the producer finished adding elements
        this.is_producer_finished = true;
    }

    public synchronized void add(String item) {
        // if buffer is full then make producer wait
        while (queue.size() == size) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.add(item);
        notify();
    }

    public synchronized String consume() {
        // if buffer is empty then make consumer wait
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String item = queue.remove();
        notify();
        return item;
    }
}