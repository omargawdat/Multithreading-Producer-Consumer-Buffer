import java.io.FileWriter;
import java.io.IOException;

public class Consumer implements Runnable {
    Buffer buff;
    String fileName;

    public Consumer(Buffer buff, String fileName) {
        this.buff = buff;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try {

            FileWriter W = new FileWriter(fileName);
            while (!buff.is_empty() || !buff.is_producer_finished) {
                String num = buff.consume();
                W.write(num + ", ");
            }
            W.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}