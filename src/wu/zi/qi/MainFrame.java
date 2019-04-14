package wu.zi.qi;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * ������
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame implements MouseListener {
	int x; // ����λ�õ�x������
	int y; // ����λ�õ�y������
	int coox; // �����н����x������
	int cooy; // �����н����y������
	int tempx;
	int tempy;
	int flag = 1; // ������ɫ�ı�־λ , 1��ʾ��ɫ��2��ʾ��ɫ
	int pcfirst = 1; // ��������ʱ�������ѡ����Կ�ʼʱһ������������
	int regretcount = 0; // �������
	int chesscount = 0; // �Ե�ǰ���Ӽ���
	boolean Iswin = false;
	boolean Isfirst = true;
	boolean Issecond = false;
	boolean Clickfirst = false; // �˻�ģʽ�У��Ƿ��һ�ε������
	int SelectResult[] = new int[4];
	int menubutton[] = new int[4];
	MyStack store = new MyStack();
	/**
	 * ������ʱ����
	 */
	ServerSocket server = null;
	Socket socket = null;
	Socket client = null;
	String IP = null;
	DataInputStream din = null;
	DataOutputStream dout = null;
	boolean isStart = false; // �Ƿ������ɹ���ʼ��Ϸ
	boolean isYourTurn = false; // �Ƿ��ֵ��Լ�����
	boolean isClient = false; // �Ƿ�Ϊ �ͻ���
	boolean isServer = false; // �Ƿ� �����
	boolean swapServer;

	int chess[][] = new int[15][15];
	Judge judge = new Judge();
	PersonToPerson pp = new PersonToPerson();
	PersonToComputer pc = new PersonToComputer();
	Set set = new Set();

	/**
	 * ���̵Ļ�������
	 */
	@SuppressWarnings("static-access")
	public MainFrame() {
		this.setSize(900, 700);
		this.setTitle("������");
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int width = Toolkit.getDefaultToolkit().getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getDefaultToolkit().getScreenSize().height;
		this.setLocation((width - 900) / 2, (height - 700) / 2);
		this.addMouseListener(this);
	}

	/**
	 * paint��������
	 */
	public void paint(Graphics g) {

		BufferedImage background = null;
		BufferedImage qipan = null;
		BufferedImage blackchess = null;
		BufferedImage whitechess = null;
		try {
			background = ImageIO.read(new File("picture/����11.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(background, 0, 0, this);

		// ���ñ���ͼƬ
		try {
			qipan = ImageIO.read(new File("picture/����.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// ����
		try {
			whitechess = ImageIO.read(new File("picture/whitestone1.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// ����
		try {
			blackchess = ImageIO.read(new File("picture/blackstone6.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(qipan, 0, 10, this);
		// �������̣����̱߿��ߣ�����������
		set.setqipantbase(this);
		// �����ߵĻ���
		for (int i = 1; i <= 15; i++) {
			for (int j = 1; j <= 15; j++) {
				g.drawLine(17 + 40 * i, 40, 17 + 40 * i, 678);
				g.drawLine(17, 40 + j * 40, 658, 40 + j * 40);
			}
		}
		// *****************************************************************
		// �ڰ�����Ⱥ�˳��ѡ��
		if (SelectResult[3] == 1) {
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					chess[i][j] = 0;
					SelectResult[3]--;
				}
			}
		}
		// ******************************************************************
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (chess[i][j] == 1) { // ���ƺ���
					int m = (i + 1) * 40 + 16;
					int n = (j + 1) * 40 + 40;
					g.drawImage(blackchess, m - 15, n - 15, 30, 30, this);
				}
				if (chess[i][j] == 2) { // ���ư���
					int m = (i + 1) * 40 + 16;
					int n = (j + 1) * 40 + 40;
					g.drawImage(whitechess, m - 15, n - 15, 30, 30, this);
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.x = e.getX();
		this.y = e.getY();
		System.out.println("x---->: " + e.getX());
		System.out.println("y---->: " + e.getY());
		judge.stateofpp(this);
		judge.stateofpc(this);
		judge.stateoflink(this);
		judge.newgame(this);
		judge.regretchess(this);
		judge.menu(this);
		// ���˶�ս
		if (SelectResult[0] == 1) {
			SelectResult[1] = 0;
			// �������ӵ��������λ��,���ҽ����Ӵ���������
			judge.chessloction(this);
			this.repaint();
			// �������˶�ս
			pp.lounchpp(this);
			System.out.println("��ʼ�������˶�ս");
		}
		// ******************************************************************************
		// �˻���ս
		if (SelectResult[1] == 1) {
			SelectResult[0] = 0;
			// ���õ�������ӵ��������λ��,���ҽ����Ӵ���������
			judge.PCchessloction(this);
			// �ж�ʤ��������һ����
			pp.lounchpp(this);
			if (Iswin) {
				return;
			}
			try {
				Thread.sleep(300);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			if (Isfirst) {
				Isfirst = false;
				pc.lounchpc(this);
				this.repaint();
				pp.lounchpp(this);
			}
			System.out.println("��ʼ�����˻���ս");
		}
		// ******************************************************************************
		// l������ս
		if (SelectResult[2] == 1) {
			if (isStart && isYourTurn) {
				// �������ӵ��������λ��,���ҽ����Ӵ���������
				judge.ConnectChessloction(this);
				repaint();
			}
			// ����һ���߳���ɼ����Է�������
			new Thread(new Runnable() {
				public void run() {
					// ѭ�������Է����͵�������Ϣ
					while (true) {
						while (isStart && !isYourTurn) {
							try {
								// �Գ��Է���x����
								coox = din.readInt();
								// �����Է���y����
								cooy = din.readInt();
								chess[coox][cooy] = din.readInt();
								repaint();
								// �յ��Է����Ӻ󽻻����巽
								isYourTurn = !isYourTurn;
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}).start();
			// �ж�ʤ��������һ����
			pp.lounchpp(this);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
