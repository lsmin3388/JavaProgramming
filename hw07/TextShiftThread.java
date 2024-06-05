import javax.swing.*;

/**
 * TextShiftThread
 */
public class TextShiftThread extends Thread {
    private final JLabel label;
    private volatile boolean running = true;
    private int speed = 500; // 초기 속도

    public TextShiftThread(JLabel label) {
        this.label = label;
    }

    /**
     * Thread 실행
     */
    public void run() {
        while (running) {
            String text = label.getText();
            if (text.length() > 1) {
                text = text.substring(1) + text.charAt(0);
                label.setText(text);
            }
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    /**
     * 속도 증가
     */
    public void increaseSpeed() {
        if (speed > 50) {
            speed -= 50;
        }
    }

    /**
     * 속도 감소
     */
    public void decreaseSpeed() {
        if (speed < 2000) {
            speed += 50;
        }
    }

    /**
     * Thread 종료
     */
    public void stopThread() {
        running = false;
    }
}
