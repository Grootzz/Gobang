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
 * �����е������ж�
 * 
 */
public class Judge {
	Set set = new Set();
	MainFrame frame = null; // ���ã�ָ��ͬһ�ε�ַ
	Gobang gobang = null;

	public void chessloction(MainFrame frame) {

		// ��õ��λ���ڽ�������
		if (frame.x >= 36 && frame.x <= 638 && frame.y >= 60 && frame.y <= 658) {
			frame.coox = (frame.x - 16) / 40;
			frame.cooy = (frame.y - 40) / 40;
			frame.tempx = (frame.coox + 1) * 40 + 16;
			frame.tempy = (frame.cooy + 1) * 40 + 40;
			System.out.println("tempx---->: " + frame.tempx);
			System.out.println("tempy---->: " + frame.tempy);
			System.out.println("coox---->: " + frame.coox);
			System.out.println("cooy---->: " + frame.cooy);
			// ��������������������λ��
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
			// �ڰ��ӵ��ֻ�
			if (frame.chess[frame.coox][frame.cooy] == 0) {
				if (frame.flag == 1) {
					frame.chess[frame.coox][frame.cooy] = 1;
					frame.store.push(new Point(frame.coox, frame.cooy, 1));
					frame.flag = 2; // ����
				} else {
					frame.chess[frame.coox][frame.cooy] = 2;
					frame.store.push(new Point(frame.coox, frame.cooy, 2));
					frame.flag = 1; // ����
				}
			}
			// ***********************************************************************
		}
	}

	// ***********************************************************************
	// ���˶�ս״̬
	public void stateofpp(MainFrame frame) {
		if (frame.x > 734 && frame.y > 293 && frame.x < 855 && frame.y < 338) {
			frame.SelectResult[0] = JOptionPane.showConfirmDialog(frame, "ȷ������˫�˶�սģʽ��", "ģʽѡ��", 2) + 1;
			frame.store.clear();
		}
	}

	// ***********************************************************************
	// �˻���ս״̬
	public void stateofpc(MainFrame frame) {
		if (frame.x > 734 && frame.y > 223 && frame.x < 855 && frame.y < 268) {
			frame.SelectResult[1] = JOptionPane.showConfirmDialog(frame, "ȷ�������˻���սģʽ��", "ģʽѡ��", 2) + 1;
			if (frame.SelectResult[1] == 1) {
				frame.pcfirst = JOptionPane.showConfirmDialog(frame,
						"          " + "ȷ������������" + "\n" + "          " + "ȡ�����������", "��Ϸ��ʾ", 2) + 1;
				frame.store.clear();
			}
		}
	}

	// ***********************************************************************
	// ������ս״̬
	public void stateoflink(final MainFrame frame) {
		if (frame.x > 734 && frame.y > 358 && frame.x < 855 && frame.y < 408) {
			frame.SelectResult[2] = JOptionPane.showConfirmDialog(frame, "ȷ������������սģʽ��", "ģʽѡ��", 2) + 1;
			if (frame.SelectResult[2] == 1) {

				final JDialog jd = new JDialog(frame, "l����ѡ��");
				FlowLayout buju = new FlowLayout(FlowLayout.CENTER, 40, 40);
				jd.setVisible(true);
				jd.setResizable(false);
				Container jd1 = jd.getContentPane();
				jd1.setBackground(Color.CYAN);
				jd.setBounds(500, 150, 200, 240);
				jd.setLayout(buju);

				final JButton bu1 = new JButton("��������");
				final JButton bu2 = new JButton("��������");
				// ****************************************************************
				bu1.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						new Thread(new Runnable() { // ����һ���߳���ɷ������Ľ���
							public void run() {
								try {
									frame.server = new ServerSocket(8888);
									System.out.println("�������׽��ִ����ɹ��� ");
									InetAddress ip = InetAddress.getLocalHost();
									String localip = ip.getHostAddress();
									System.out.println(localip);
									// ѭ���ȴ��ͻ�������
									System.out.println("�ȴ��ͻ������ӡ������������������������� ");
									frame.socket = frame.server.accept();
									System.out.println("��������ͻ������ӳɹ���*************************8");
								} catch (IOException e) {
									e.printStackTrace();
								}
								// **********************************
								// ���ݴ���ܵ��Ľ���
								try {
									frame.din = new DataInputStream(frame.socket.getInputStream());
									System.out.println("/���ݴ���ܵ��Ľ���");
									frame.dout = new DataOutputStream(frame.socket.getOutputStream());
									frame.isStart = true;
									frame.flag = 1; // ������Ϊ����
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
						new Thread(new Runnable() { //// ����һ���߳���ɿͻ��˵Ľ���
							public void run() {
								String ip = JOptionPane.showInputDialog(frame, "������Է�ip��ַ");
								System.out.println("�ͻ�������ip>>>>>>>>>>>>>" + ip);
								if (ip != null) {
									try {
										frame.client = new Socket(ip, 8888);
										System.out.println("�ͻ��������ɹ�>>>>>>>>>>>>>");
									} catch (IOException e) {
										e.printStackTrace();
									}
									// **********************************
									// ���ݴ���ܵ��Ľ���
									try {
										frame.din = new DataInputStream(frame.client.getInputStream());
										frame.dout = new DataOutputStream(frame.client.getOutputStream());
										System.out.println("ke fu duan/���ݴ���ܵ��Ľ���");
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
	// �¾�
	public void newgame(MainFrame frame) {
		if (frame.x > 734 && frame.y > 430 && frame.x < 855 && frame.y < 479) {
			frame.SelectResult[3] = JOptionPane.showConfirmDialog(frame, "ȷ�����¿�ʼ��Ϸ��", "ģʽѡ��", 2) + 1;

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
	// ����
	public void regretchess(MainFrame frame) {
		int result = 0;
		if (frame.x > 734 && frame.y > 502 && frame.x < 855 && frame.y < 550) {
			result = JOptionPane.showConfirmDialog(frame, "ȷ�����壿", "ģʽѡ��", 2) + 1;

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
	// �˵�
	public int menu(final MainFrame frame) { // final ���ս���
		int result = 0;
		if (frame.x > 734 && frame.y > 154 && frame.x < 855 && frame.y < 203) {
			System.out.println(result);
			final JDialog jd = new JDialog(frame, "�˵�ѡ��");
			FlowLayout buju = new FlowLayout(FlowLayout.CENTER, 20, 40);
			jd.setVisible(true);
			jd.setResizable(false);
			Container jd1 = jd.getContentPane();
			jd1.setBackground(Color.cyan);
			jd.setBounds(500, 150, 200, 350);
			jd.setLayout(buju);
			JButton bu1 = new JButton("˳������");
			JButton bu2 = new JButton("��������");
			JButton bu3 = new JButton("�����Ϣ");
			JButton bu4 = new JButton("�˳�����");
			bu1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					frame.flag = JOptionPane.showConfirmDialog(jd, "ȷ�����ڷ�����" + "\n" + "ȡ�����׷�����", "ģʽѡ��", 2) + 1;
					if (frame.flag != 1) {
						frame.flag = 2;
					}
				}
			});
			bu2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (frame.flag == 1) {
						JOptionPane.showMessageDialog(jd, "�׷�ʤ��");
					}
					if (frame.flag == 2) {
						JOptionPane.showMessageDialog(jd, "�ڷ�ʤ��");
					}
				}
			});
			bu3.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(jd,
							"\n" + "��Ϸ���ƣ�������" + "\n" + "\n" + "��Ϸ���ߣ��°���" + "\n" + ""
									+ "\n" + "��ǰ�汾��v1.0" + "\n" + "\n"
									+ "����˵��������Ϸ���ֹ����� ��������ͬ��������Ϯ��");
				}
			});
			bu4.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(jd, "ȷ���˳���Ϸ��", "��Ϸ��ʾ", 2);
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
	// ��õ��λ���ڽ�������
	public void PCchessloction(MainFrame frame) {

		if (frame.x >= 36 && frame.x <= 638 && frame.y >= 60 && frame.y <= 658) {
			frame.coox = (frame.x - 16) / 40;
			frame.cooy = (frame.y - 40) / 40;
			frame.tempx = (frame.coox + 1) * 40 + 16;
			frame.tempy = (frame.cooy + 1) * 40 + 40;
			// ��������������������λ��
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
			// �ڰ��ӵ��ֻ�����ʱΪ����
			if (frame.chess[frame.coox][frame.cooy] == 0) {
				frame.chess[frame.coox][frame.cooy] = 2;

				frame.store.push(new Point(frame.coox, frame.cooy, 2)); // �˻���������ѹ��ջ
				frame.Isfirst = true;
				frame.Clickfirst = true;
				frame.chesscount++;
				frame.repaint();
				System.out.println("��ǰ����������----------------��" + frame.chesscount);
			}
			// ***********************************************************************
		}
	}

	// ***********************************************************************
	public void ConnectChessloction(MainFrame frame) {
		// ��õ��λ���ڽ�������
		if (frame.x >= 36 && frame.x <= 638 && frame.y >= 60 && frame.y <= 658) {
			frame.coox = (frame.x - 16) / 40;
			frame.cooy = (frame.y - 40) / 40;
			frame.tempx = (frame.coox + 1) * 40 + 16;
			frame.tempy = (frame.cooy + 1) * 40 + 40;
			System.out.println("tempx---->: " + frame.tempx);
			System.out.println("tempy---->: " + frame.tempy);
			System.out.println("coox---->: " + frame.coox);
			System.out.println("cooy---->: " + frame.cooy);
			// ��������������������λ��
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
			// ���ݵĽ�����������������������������������������������������
			if (frame.chess[frame.coox][frame.cooy] == 0) {
				if (frame.isServer) {
					frame.chess[frame.coox][frame.cooy] = 1;
					frame.repaint();
					try {
						frame.dout.writeInt(frame.coox);
						frame.dout.writeInt(frame.cooy);
						frame.dout.writeInt(1); // ������Ϊ����
						System.out.println("��ͻ��˶Է�д���ݡ���������������");

						if (!frame.Iswin) {
							frame.isYourTurn = !frame.isYourTurn;
						}

						frame.swapServer = true;
					} catch (IOException e) {
						e.printStackTrace();
					} // ��Է�д����
					frame.store.push(new Point(frame.coox, frame.cooy, 1));
				}
				if (frame.isClient) {
					frame.chess[frame.coox][frame.cooy] = 2;
					frame.repaint();
					try {
						frame.dout.writeInt(frame.coox);
						frame.dout.writeInt(frame.cooy);
						frame.dout.writeInt(2); // �ͻ���Ϊ���塢����
						System.out.println("�����˶Է�д���ݡ���������������");

						if (!frame.Iswin) {
							frame.isYourTurn = !frame.isYourTurn;
						}

					} catch (IOException e) {
						e.printStackTrace();
						frame.store.push(new Point(frame.coox, frame.cooy, 2));
					} // �����˻�������������
				}
			}
		}
	}

	// ***********************************************************************
	public void LounchThread() {
		if (frame.SelectResult[2] == 1) {
			// ����һ���߳���ɼ����Է�������
			new Thread(new Runnable() {

				public void run() {
					// ѭ�������Է����͵�������Ϣ
					while (true) {
						while (frame.isStart && !frame.isYourTurn) {
							try {
								// �Գ��Է���x����
								frame.coox = frame.din.readInt();
								// �����Է���y����
								frame.cooy = frame.din.readInt();
								frame.chess[frame.coox][frame.cooy] = frame.din.readInt();
								System.out.println("coox = din.readInt();    //�Գ��Է���x����" + frame.din.readInt());
								frame.repaint();
								// �յ��Է����Ӻ󽻻����巽
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
			// �ж�ʤ��������һ����
			frame.pp.lounchpp(frame);
		}
	}
}
