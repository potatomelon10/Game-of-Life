import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class main implements Runnable {

    Window gui = new Window();

    public static void main(String[] args) throws InterruptedException {
        new Thread(new main()).start();
    }

    @Override
    public void run() {
        while(true){
            gui.repaint();
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
