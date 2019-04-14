package wu.zi.qi;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * 程序中的所有判断
 * 
 */
public class Judge {
	Set set = new Set();
	MainFrame frame = null; // 引用，指向同一段地址
	Gobang gobang = null;

	public void chessloction(MainFrame frame) {

		// 获得点击位置邻近的坐标
		if (frame.x >= 36 && frame.x <= 638 && frame.y >= 60 && frame.y <= 658) {
			frame.coox = (frame.x - 16) / 40;
			frame.cooy = (frame.y - 40) / 40;
			frame.tempx = (frame.coox + 1) * 40 + 16;
			frame.tempy = (frame.cooy + 1) * 40 + 40;
			System.out.println("tempx---->: " + frame.tempx);
			System.out.println("tempy---->: " + frame.tempy);
			System.out.println("coox---->: " + frame.coox);
			System.out.println("cooy---->: " + frame.cooy);
			// 让棋子落在最近的坐标的位置
			if ((frame.tempx - frame.x) < 20 && (frame.tempy - frame.y) < 20 && (frame.tempx - frame.x) > 0
					&& (frame.tempy - frame.y) > 0) {
				System.out.println("1@x-tempx: " + (frame.x - frame.tempx));
				frame.coox = frame.coox;
				frame.cooy = frame.cooy;
			}
			if ((frame.tempx - frame.x) >= 20 && (frame.tempy - frame.y) >= 20) {
				System.out.println("2@x-tempx: " + (frame.x - frame.tempx));

				if (frame.coox == 0) {
					frame.coox = frame.coox;
				} else {
					frame.coox = frame.coox - 1;
				}
				if (frame.cooy == 0) {
					frame.cooy = frame.cooy;
				} else {
					frame.cooy = frame.cooy - 1;
				}
			}
			if ((frame.tempx - frame.x) < 20 && (frame.tempy - frame.y) >= 20 && (frame.tempx - frame.x) > 0) {
				System.out.println("3@x-tempx: " + (frame.x - frame.tempx));
				frame.coox = frame.coox;
				if (frame.cooy == 0) {
					frame.cooy = frame.cooy;
				} else {
					frame.cooy = frame.cooy - 1;
				}
			}
			if ((frame.tempx - frame.x) >= 20 && (frame.tempy - frame.y) < 20 && (frame.tempy - frame.y) > 0) {
				System.out.println("4@x-tempx: " + (frame.x - frame.tempx));
				if (frame.coox == 0) {
					frame.coox = frame.coox;
				} else {
					frame.coox = frame.coox - 1;
				}
				frame.cooy = frame.cooy;
			}
			// ***********************************************************************
			// 黑白子的轮换
			if (frame.chess[frame.coox][frame.cooy] == 0) {
				if (frame.flag == 1) {
					frame.chess[frame.coox][frame.cooy] = 1;
					frame.store.push(new Point(frame.coox, frame.cooy, 1));
					frame.flag = 2; // 白子
				} else {
					frame.chess[frame.coox][frame.cooy] = 2;
					frame.store.push(new Point(frame.coox, frame.cooy, 2));
					frame.flag = 1; // 黑子
				}
			}
			// ***********************************************************************
		}
	}

	// ***********************************************************************
	// 人人对战状态
	public void stateofpp(MainFrame frame) {
		if (frame.x > 734 && frame.y > 293 && frame.x < 855 && frame.y < 338) {
			frame.SelectResult[0] = JOptionPane.showConfirmDialog(frame, "确定启动双人对战模式？", "模式选择", 2) + 1;
			frame.store.clear();
		}
	}

	// ***********************************************************************
	// 人机对战状态
	public void stateofpc(MainFrame frame) {
		if (frame.x > 734 && frame.y > 223 && frame.x < 855 && frame.y < 268) {
			frame.SelectResult[1] = JOptionPane.showConfirmDialog(frame, "确定启动人机对战模式？", "模式选择", 2) + 1;
			if (frame.SelectResult[1] == 1) {
				frame.pcfirst = JOptionPane.showConfirmDialog(frame,
						"          " + "确定：电脑先行" + "\n" + "          " + "取消：玩家先行", "游戏提示", 2) + 1;
				frame.store.clear();
			}
		}
	}

	// ***********************************************************************
	// 联机对战状态
	public void stateoflink(final MainFrame frame) {
		if (frame.x > 734 && frame.y > 358 && frame.x < 855 && frame.y < 408) {
			frame.SelectResult[2] = JOptionPane.showConfirmDialog(frame, "确定启动联机对战模式？", "模式选择", 2) + 1;
			if (frame.SelectResult[2] == 1) {

				final JDialog jd = new JDialog(frame, "l联机选项");
				FlowLayout buju = new FlowLayout(FlowLayout.CENTER, 40, 40);
				jd.setVisible(true);
				jd.setResizable(false);
				Container jd1 = jd.getContentPane();
				jd1.setBackground(Color.CYAN);
				jd.setBounds(500, 150, 200, 240);
				jd.setLayout(buju);

				final JButton bu1 = new JButton("创建联机");
				final JButton bu2 = new JButton("加入联机");
				// ****************************************************************
				bu1.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						new Thread(new Runnable() { // 启动一个线程完成服务器的建立
							public void run() {
								try {
									frame.server = new ServerSocket(8888);
									System.out.println("服务器套接字创建成功！ ");
									InetAddress ip = InetAddress.getLocalHost();
									String localip = ip.getHostAddress();
									System.out.println(localip);
									// 循环等待客户机连接
									System.out.println("等待客户机连接、、、、、、、、、、、、、 ");
									frame.socket = frame.server.accept();
									System.out.println("服务器与客户机连接成功！*************************8");
								} catch (IOException e) {
									e.printStackTrace();
								}
								// **********************************
								// 数据传输管道的建立
								try {
									frame.din = new DataInputStream(frame.socket.getInputStream());
									System.out.println("/数据传输管道的建立");
									frame.dout = new DataOutputStream(frame.socket.getOutputStream());
									frame.isStart = true;
									frame.flag = 1; // 服务器为黑子
									frame.isYourTurn = true;
									frame.isServer = true;
								} catch (IOException e) {
									e.printStackTrace();
								}

								if (frame.SelectResult[0] == 1 && frame.SelectResult[1] == 1
										&& frame.SelectResult[3] == 1) {
									try {
										frame.server.close();
										frame.din.close();
										frame.dout.close();
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
								// **********************************

							}
						}).start();
						bu2.setEnabled(false);
					}
				});
				// ********************************************************
				bu2.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						new Thread(new Runnable() { //// 启动一个线程完成客户端的建立
							public void run() {
								String ip = JOptionPane.showInputDialog(frame, "请输入对方ip地址");
								System.out.println("客户机创建ip>>>>>>>>>>>>>" + ip);
								if (ip != null) {
									try {
										frame.client = new Socket(ip, 8888);
										System.out.println("客户机创建成功>>>>>>>>>>>>>");
									} catch (IOException e) {
										e.printStackTrace();
									}
									// **********************************
									// 数据传输管道的建立
									try {
										frame.din = new DataInputStream(frame.client.getInputStream());
										frame.dout = new DataOutputStream(frame.client.getOutputStream());
										System.out.println("ke fu duan/数据传输管道的建立");
										frame.isStart = true;
										frame.isYourTurn = true;
										frame.isClient = true;
										frame.flag = 2;
									} catch (IOException e) {
										e.printStackTrace();
									}
									// **********************************
								}
								if (frame.SelectResult[0] == 1 && frame.SelectResult[1] == 1
										&& frame.SelectResult[3] == 1) {
									try {
										frame.client.close();
										frame.din.close();
										frame.dout.close();
									} catch (IOException e) {
										e.printStackTrace();
									}
								}

							}
						}).start();
						bu1.setEnabled(false);
					}
				});
				// ************************************************************
				jd.add(bu1);
				jd.add(bu2);
				frame.store.clear();
			}
		}
	}

	// ***********************************************************************
	// 新局
	public void newgame(MainFrame frame) {
		if (frame.x > 734 && frame.y > 430 && frame.x < 855 && frame.y < 479) {
			frame.SelectResult[3] = JOptionPane.showConfirmDialog(frame, "确定重新开始游戏？", "模式选择", 2) + 1;

			frame.SelectResult[0] = 0;
			frame.SelectResult[1] = 0;
			frame.SelectResult[2] = 0;
			frame.Clickfirst = false;
			frame.Isfirst = true;
			frame.Iswin = false;
			frame.repaint();
			frame.coox = 0;
			frame.cooy = 0;
			frame.chesscount = 0;
			frame.store.clear();

		}
	}

	// ***********************************************************************
	// 悔棋
	public void regretchess(MainFrame frame) {
		int result = 0;
		if (frame.x > 734 && frame.y > 502 && frame.x < 855 && frame.y < 550) {
			result = JOptionPane.showConfirmDialog(frame, "确定悔棋？", "模式选择", 2) + 1;

			if (result == 1) {
				result = 0;
				Point xoy = frame.store.pop();
				int x = xoy.getx();
				int y = xoy.gety();
				frame.flag = xoy.getflag();
				frame.chess[x][y] = 0;
				frame.repaint();
			}
			frame.regretcount = result + frame.regretcount;
		}

	}

	// ***********************************************************************
	// 菜单
	public int menu(final MainFrame frame) { // final ：终结器
		int result = 0;
		if (frame.x > 734 && frame.y > 154 && frame.x < 855 && frame.y < 203) {
			System.out.println(result);
			final JDialog jd = new JDialog(frame, "菜单选项");
			FlowLayout buju = new FlowLayout(FlowLayout.CENTER, 20, 40);
			jd.setVisible(true);
			jd.setResizable(false);
			Container jd1 = jd.getContentPane();
			jd1.setBackground(Color.cyan);
			jd.setBounds(500, 150, 200, 350);
			jd.setLayout(buju);
			JButton bu1 = new JButton("顺序设置");
			JButton bu2 = new JButton("主动认输");
			JButton bu3 = new JButton("软件信息");
			JButton bu4 = new JButton("退出程序");
			bu1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					frame.flag = JOptionPane.showConfirmDialog(jd, "确定：黑方先行" + "\n" + "取消：白方先行", "模式选择", 2) + 1;
					if (frame.flag != 1) {
						frame.flag = 2;
					}
				}
			});
			bu2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (frame.flag == 1) {
						JOptionPane.showMessageDialog(jd, "白方胜！");
					}
					if (frame.flag == 2) {
						JOptionPane.showMessageDialog(jd, "黑方胜！");
					}
				}
			});
			bu3.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(jd,
							"\n" + "游戏名称：五子棋" + "\n" + "\n" + "游戏作者：陈安旭" + "\n" + ""
									+ "\n" + "当前版本：v1.0" + "\n" + "\n"
									+ "附加说明：本游戏纯手工打造 ，如有雷同，纯属抄袭！");
				}
			});
			bu4.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(jd, "确定退出游戏吗？", "游戏提示", 2);
					if (result == 0) {
						System.exit(0);
					}
				}
			});
			jd.add(bu1);
			jd.add(bu2);
			jd.add(bu3);
			jd.add(bu4);
			return result;
		}
		return 1;
	}

	// ***********************************************************************
	// 获得点击位置邻近的坐标
	public void PCchessloction(MainFrame frame) {

		if (frame.x >= 36 && frame.x <= 638 && frame.y >= 60 && frame.y <= 658) {
			frame.coox = (frame.x - 16) / 40;
			frame.cooy = (frame.y - 40) / 40;
			frame.tempx = (frame.coox + 1) * 40 + 16;
			frame.tempy = (frame.cooy + 1) * 40 + 40;
			// 让棋子落在最近的坐标的位置
			if ((frame.tempx - frame.x) < 20 && (frame.tempy - frame.y) < 20 && (frame.tempx - frame.x) > 0
					&& (frame.tempy - frame.y) > 0) {
				System.out.println("1@x-tempx: " + (frame.x - frame.tempx));
				frame.coox = frame.coox;
				frame.cooy = frame.cooy;
			}
			if ((frame.tempx - frame.x) >= 20 && (frame.tempy - frame.y) >= 20) {
				System.out.println("2@x-tempx: " + (frame.x - frame.tempx));

				if (frame.coox == 0) {
					frame.coox = frame.coox;
				} else {
					frame.coox = frame.coox - 1;
				}
				if (frame.cooy == 0) {
					frame.cooy = frame.cooy;
				} else {
					frame.cooy = frame.cooy - 1;
				}
			}
			if ((frame.tempx - frame.x) < 20 && (frame.tempy - frame.y) >= 20 && (frame.tempx - frame.x) > 0) {
				System.out.println("3@x-tempx: " + (frame.x - frame.tempx));
				frame.coox = frame.coox;
				if (frame.cooy == 0) {
					frame.cooy = frame.cooy;
				} else {
					frame.cooy = frame.cooy - 1;
				}
			}
			if ((frame.tempx - frame.x) >= 20 && (frame.tempy - frame.y) < 20 && (frame.tempy - frame.y) > 0) {
				System.out.println("4@x-tempx: " + (frame.x - frame.tempx));
				if (frame.coox == 0) {
					frame.coox = frame.coox;
				} else {
					frame.coox = frame.coox - 1;
				}
				frame.cooy = frame.cooy;
			}
			// ***********************************************************************
			// 黑白子的轮换，此时为黑先
			if (frame.chess[frame.coox][frame.cooy] == 0) {
				frame.chess[frame.coox][frame.cooy] = 2;

				frame.store.push(new Point(frame.coox, frame.cooy, 2)); // 人机：将棋子压入栈
				frame.Isfirst = true;
				frame.Clickfirst = true;
				frame.chesscount++;
				frame.repaint();
				System.out.println("当前白棋子总数----------------》" + frame.chesscount);
			}
			// ***********************************************************************
		}
	}

	// ***********************************************************************
	public void ConnectChessloction(MainFrame frame) {
		// 获得点击位置邻近的坐标
		if (frame.x >= 36 && frame.x <= 638 && frame.y >= 60 && frame.y <= 658) {
			frame.coox = (frame.x - 16) / 40;
			frame.cooy = (frame.y - 40) / 40;
			frame.tempx = (frame.coox + 1) * 40 + 16;
			frame.tempy = (frame.cooy + 1) * 40 + 40;
			System.out.println("tempx---->: " + frame.tempx);
			System.out.println("tempy---->: " + frame.tempy);
			System.out.println("coox---->: " + frame.coox);
			System.out.println("cooy---->: " + frame.cooy);
			// 让棋子落在最近的坐标的位置
			if ((frame.tempx - frame.x) < 20 && (frame.tempy - frame.y) < 20 && (frame.tempx - frame.x) > 0
					&& (frame.tempy - frame.y) > 0) {
				System.out.println("1@x-tempx: " + (frame.x - frame.tempx));
				frame.coox = frame.coox;
				frame.cooy = frame.cooy;
			}
			if ((frame.tempx - frame.x) >= 20 && (frame.tempy - frame.y) >= 20) {
				System.out.println("2@x-tempx: " + (frame.x - frame.tempx));

				if (frame.coox == 0) {
					frame.coox = frame.coox;
				} else {
					frame.coox = frame.coox - 1;
				}
				if (frame.cooy == 0) {
					frame.cooy = frame.cooy;
				} else {
					frame.cooy = frame.cooy - 1;
				}
			}
			if ((frame.tempx - frame.x) < 20 && (frame.tempy - frame.y) >= 20 && (frame.tempx - frame.x) > 0) {
				System.out.println("3@x-tempx: " + (frame.x - frame.tempx));
				frame.coox = frame.coox;
				if (frame.cooy == 0) {
					frame.cooy = frame.cooy;
				} else {
					frame.cooy = frame.cooy - 1;
				}
			}
			if ((frame.tempx - frame.x) >= 20 && (frame.tempy - frame.y) < 20 && (frame.tempy - frame.y) > 0) {
				System.out.println("4@x-tempx: " + (frame.x - frame.tempx));
				if (frame.coox == 0) {
					frame.coox = frame.coox;
				} else {
					frame.coox = frame.coox - 1;
				}
				frame.cooy = frame.cooy;
			}
			// ***********************************************************************
			// 数据的交互、、、、、、、、、、、、、、、、、、、、、、、、、
			if (frame.chess[frame.coox][frame.cooy] == 0) {
				if (frame.isServer) {
					frame.chess[frame.coox][frame.cooy] = 1;
					frame.repaint();
					try {
						frame.dout.writeInt(frame.coox);
						frame.dout.writeInt(frame.cooy);
						frame.dout.writeInt(1); // 呼我器为黑棋
						System.out.println("向客户端对方写数据、、、、、、、、");

						if (!frame.Iswin) {
							frame.isYourTurn = !frame.isYourTurn;
						}

						frame.swapServer = true;
					} catch (IOException e) {
						e.printStackTrace();
					} // 向对方写数据
					frame.store.push(new Point(frame.coox, frame.cooy, 1));
				}
				if (frame.isClient) {
					frame.chess[frame.coox][frame.cooy] = 2;
					frame.repaint();
					try {
						frame.dout.writeInt(frame.coox);
						frame.dout.writeInt(frame.cooy);
						frame.dout.writeInt(2); // 客户端为白棋、、、
						System.out.println("向服务端对方写数据、、、、、、、、");

						if (!frame.Iswin) {
							frame.isYourTurn = !frame.isYourTurn;
						}

					} catch (IOException e) {
						e.printStackTrace();
						frame.store.push(new Point(frame.coox, frame.cooy, 2));
					} // 用于人机、、、、、、
				}
			}
		}
	}

	// ***********************************************************************
	public void LounchThread() {
		if (frame.SelectResult[2] == 1) {
			// 启动一个线程完成监听对方的任务
			new Thread(new Runnable() {

				public void run() {
					// 循环监听对方发送的走棋消息
					while (true) {
						while (frame.isStart && !frame.isYourTurn) {
							try {
								// 对出对方的x坐标
								frame.coox = frame.din.readInt();
								// 读出对方的y坐标
								frame.cooy = frame.din.readInt();
								frame.chess[frame.coox][frame.cooy] = frame.din.readInt();
								System.out.println("coox = din.readInt();    //对出对方的x坐标" + frame.din.readInt());
								frame.repaint();
								// 收到对方棋子后交换走棋方
								frame.isYourTurn = !frame.isYourTurn;

							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}).start();

			if (frame.isServer && frame.swapServer) {
				frame.swapServer = false;
				frame.isYourTurn = !frame.isYourTurn;
			}
			// 判断胜负，并画一条线
			frame.pp.lounchpp(frame);
		}
	}
}
