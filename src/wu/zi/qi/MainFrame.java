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
 * 主窗体
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame implements MouseListener {
	int x; // 鼠标点位置的x轴坐标
	int y; // 鼠标点位置的y轴坐标
	int coox; // 棋盘中交点的x轴坐标
	int cooy; // 棋盘中交点的y轴坐标
	int tempx;
	int tempy;
	int flag = 1; // 棋子颜色的标志位 , 1表示黑色，2表示白色
	int pcfirst = 1; // 电脑先行时，避免放选择电脑开始时一下落两个棋子
	int regretcount = 0; // 悔棋次数
	int chesscount = 0; // 对当前棋子计数
	boolean Iswin = false;
	boolean Isfirst = true;
	boolean Issecond = false;
	boolean Clickfirst = false; // 人机模式中，是否第一次点击棋盘
	int SelectResult[] = new int[4];
	int menubutton[] = new int[4];
	MyStack store = new MyStack();
	/**
	 * 网络编程时所用
	 */
	ServerSocket server = null;
	Socket socket = null;
	Socket client = null;
	String IP = null;
	DataInputStream din = null;
	DataOutputStream dout = null;
	boolean isStart = false; // 是否联机成功开始游戏
	boolean isYourTurn = false; // 是否轮到自己走子
	boolean isClient = false; // 是否为 客户端
	boolean isServer = false; // 是否 服务端
	boolean swapServer;

	int chess[][] = new int[15][15];
	Judge judge = new Judge();
	PersonToPerson pp = new PersonToPerson();
	PersonToComputer pc = new PersonToComputer();
	Set set = new Set();

	/**
	 * 棋盘的基础设置
	 */
	@SuppressWarnings("static-access")
	public MainFrame() {
		this.setSize(900, 700);
		this.setTitle("五子棋");
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int width = Toolkit.getDefaultToolkit().getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getDefaultToolkit().getScreenSize().height;
		this.setLocation((width - 900) / 2, (height - 700) / 2);
		this.addMouseListener(this);
	}

	/**
	 * paint（）方法
	 */
	public void paint(Graphics g) {

		BufferedImage background = null;
		BufferedImage qipan = null;
		BufferedImage blackchess = null;
		BufferedImage whitechess = null;
		try {
			background = ImageIO.read(new File("picture/背景11.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(background, 0, 0, this);

		// 设置背景图片
		try {
			qipan = ImageIO.read(new File("picture/棋盘.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 白子
		try {
			whitechess = ImageIO.read(new File("picture/whitestone1.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 黑子
		try {
			blackchess = ImageIO.read(new File("picture/blackstone6.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(qipan, 0, 10, this);
		// 设置棋盘，棋盘边框线，绘制棋盘线
		set.setqipantbase(this);
		// 棋盘线的绘制
		for (int i = 1; i <= 15; i++) {
			for (int j = 1; j <= 15; j++) {
				g.drawLine(17 + 40 * i, 40, 17 + 40 * i, 678);
				g.drawLine(17, 40 + j * 40, 658, 40 + j * 40);
			}
		}
		// *****************************************************************
		// 黑白棋的先后顺序选择
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
				if (chess[i][j] == 1) { // 绘制黑子
					int m = (i + 1) * 40 + 16;
					int n = (j + 1) * 40 + 40;
					g.drawImage(blackchess, m - 15, n - 15, 30, 30, this);
				}
				if (chess[i][j] == 2) { // 绘制白子
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
		// 人人对战
		if (SelectResult[0] == 1) {
			SelectResult[1] = 0;
			// 设置棋子的最近落子位置,并且将棋子存入数组中
			judge.chessloction(this);
			this.repaint();
			// 运行人人对战
			pp.lounchpp(this);
			System.out.println("开始运行人人对战");
		}
		// ******************************************************************************
		// 人机对战
		if (SelectResult[1] == 1) {
			SelectResult[0] = 0;
			// 设置点击后棋子的最近落子位置,并且将棋子存入数组中
			judge.PCchessloction(this);
			// 判断胜负，并画一条线
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
			System.out.println("开始运行人机对战");
		}
		// ******************************************************************************
		// l联机对战
		if (SelectResult[2] == 1) {
			if (isStart && isYourTurn) {
				// 设置棋子的最近落子位置,并且将棋子存入数组中
				judge.ConnectChessloction(this);
				repaint();
			}
			// 启动一个线程完成监听对方的任务
			new Thread(new Runnable() {
				public void run() {
					// 循环监听对方发送的走棋消息
					while (true) {
						while (isStart && !isYourTurn) {
							try {
								// 对出对方的x坐标
								coox = din.readInt();
								// 读出对方的y坐标
								cooy = din.readInt();
								chess[coox][cooy] = din.readInt();
								repaint();
								// 收到对方棋子后交换走棋方
								isYourTurn = !isYourTurn;
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}).start();
			// 判断胜负，并画一条线
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
