public class Producer implements Runnable {
    Buffer buff;
    int n;

    public Producer(Buffer buff, int n) {
        this.buff = buff;
        this.n = n;
    }

    @Override
    public void run() {
        for (int i = 2; i <= n; i++) {
            boolean is_prime = true;
            for (int j = 2; j <= Math.pow(i, 0.5); j++) {
                if (i % j == 0) {
                    is_prime = false;
                    break;
                }
            }
            if (is_prime) {
                buff.add(Integer.toString(i));
                Main.count++;
                Main.max_value = i;
            }
        }
        buff.finish();
    }
}