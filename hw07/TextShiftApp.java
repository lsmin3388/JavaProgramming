import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * LED 전광판
 * @date 2024-05-08
 * @author 이상민
 * @classNumber 2023012780
 */
public class TextShiftApp extends JFrame {
	JLabel shiftTextLabel;
	JLabel keyLabel;
	JTextField textField;
	TextShiftThread textShiftThread;
	KeyEventListener keyEventListener;

	public TextShiftApp() {
		keyEventListener = new KeyEventListener();

		setTitle("LED Text");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container c = getContentPane();
		c.setLayout(new BorderLayout());

		String orgText = "Please Type Text";
		String shiftText = "";

		// 문자 입력 용도의 TextField
		textField = new JTextField(orgText, SwingConstants.CENTER);
		c.add(textField, BorderLayout.NORTH);

		shiftText = textField.getText();

		// 문자열 이동을 위한 JLabel
		shiftTextLabel = new JLabel(shiftText, SwingConstants.CENTER);
		c.add(shiftTextLabel, BorderLayout.CENTER);

		shiftTextLabel.setFont(new Font("TimesRoman", Font.BOLD, 50));
		shiftTextLabel.setForeground(Color.BLACK);

		// 가운데 JLabel에 KeyEventAdapter 연결
		shiftTextLabel.addKeyListener(keyEventListener);

		// Key pressed 출력 용도 JLabel
		keyLabel = new JLabel("Key Pressed: ", SwingConstants.CENTER);
		c.add(keyLabel, BorderLayout.SOUTH);

		setSize(800, 400);
		setVisible(true);

		shiftTextLabel.setFocusable(true);
		shiftTextLabel.requestFocus();

		// Thread 시작
		textShiftThread = new TextShiftThread(shiftTextLabel);
		textShiftThread.start();

		/**
		 * JTextField에 ActionEvent 등록
		 * - JTextField에서 Enter키가 입력되면, JTextField의 문자열을 읽어서 JLabel의 문자열을 변경함
		 */
		textField.addActionListener(e -> {
			shiftTextLabel.setText(textField.getText());
			shiftTextLabel.setFocusable(true);
			shiftTextLabel.requestFocus();
			keyLabel.setText("VK_ENTER pressed");
		});

	}

	class KeyEventListener extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			keyLabel.setText("KeyEvent: " + KeyEvent.getKeyText(keyCode) + " pressed");
			System.out.println("KeyEvent: " + KeyEvent.getKeyText(keyCode) + " pressed");

			switch (keyCode) {
				case KeyEvent.VK_R:
					shiftTextLabel.setForeground(Color.RED);
					break;
				case KeyEvent.VK_G:
					shiftTextLabel.setForeground(Color.GREEN);
					break;
				case KeyEvent.VK_B:
					shiftTextLabel.setForeground(Color.BLUE);
					break;
				case KeyEvent.VK_L:
					shiftTextLabel.setForeground(Color.BLACK);
					break;
				case KeyEvent.VK_X:
					textShiftThread.stopThread();
					break;
				case KeyEvent.VK_UP:
					textShiftThread.increaseSpeed();
					break;
				case KeyEvent.VK_DOWN:
					textShiftThread.decreaseSpeed();
					break;
				default:
					break;
			}
		}
	}

	public static void main(String[] args) {
		new TextShiftApp();
	}
}
