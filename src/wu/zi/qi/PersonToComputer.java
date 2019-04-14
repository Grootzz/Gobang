package wu.zi.qi;

/**
 * 实现人机模式
 */
public class PersonToComputer {
	MainFrame frame = null;
	int pcx;
	int pcy;
	int x;
	int y;
	int pcflag;
	int state[] = new int[2];
	int computer[][] = new int[15][15];
	int player[][] = new int[15][15];
	int scorec[][] = new int[15][15];
	int scorep[][] = new int[15][15];
	int score[][] = new int[15][15];

	public void lounchpc(MainFrame frame) {
		// ****************************************************************************
		// 电脑先行
		if (frame.pcfirst == 1) {
			this.pcx = 7;
			this.pcy = 7;
			this.pcflag = 1;

			if (frame.chesscount == 0) {
				frame.chess[pcx][pcy] = 1;
				frame.store.push(new Point(pcx, pcy, 1)); // 人机：将棋子压入栈
			}
			if (frame.chesscount == 1) {
				First(frame);
				Tryfirst(frame);
			}
			if (frame.chesscount > 1) {
				chesstype(frame, 1);
				copy(score, scorec);
				fill(score, 0);
				chesstype(frame, 2);
				copy(score, scorep);
				AutoSelect(frame);
				fill(score, 0);
			}
		}
		// ****************************************************************************
		// ****************************************************************************
		// 玩家先行
		if (frame.pcfirst == 3) {
			if (frame.chesscount == 1 || frame.chesscount == 2) {
				this.pcflag = 1;
				RandomFirst();
				Tryfirst(frame);
			}
			if (frame.chesscount > 2) {
				chesstype(frame, 1);
				copy(score, scorec);
				fill(score, 0);
				chesstype(frame, 2);
				copy(score, scorep);
				AutoSelect(frame);
				fill(score, 0);
			}
		}
		// ****************************************************************************
	}

	public void A(MainFrame frame) {
		if (frame.chess[frame.coox - 2][frame.cooy + 1] == 0) {
			frame.chess[frame.coox - 2][frame.cooy + 1] = 1;
			frame.store.push(new Point(frame.coox - 2, frame.cooy + 1, 1)); // 人机：将棋子压入栈

		} // 1
		else if (frame.chess[frame.coox - 2][frame.cooy - 1] == 0) {
			frame.chess[frame.coox - 2][frame.cooy - 1] = 1;
			frame.store.push(new Point(frame.coox - 2, frame.cooy - 1, 1)); // 人机：将棋子压入栈
		} // 2
		else if (frame.chess[frame.coox - 1][frame.cooy - 1] == 0) {
			frame.chess[frame.coox - 1][frame.cooy - 1] = 1;
			frame.store.push(new Point(frame.coox - 1, frame.cooy - 1, 1)); // 人机：将棋子压入栈
		} // 3
		else if (frame.chess[frame.coox - 1][frame.cooy + 1] == 0) {
			frame.chess[frame.coox - 1][frame.cooy + 1] = 1;
			frame.store.push(new Point(frame.coox - 1, frame.cooy + 1, 1)); // 人机：将棋子压入栈
		} // 4
		else if (frame.chess[frame.coox][frame.cooy - 1] == 0) {
			frame.chess[frame.coox][frame.cooy - 1] = 1;
			frame.store.push(new Point(frame.coox, frame.cooy - 1, 1)); // 人机：将棋子压入栈
		} // 5
		else if (frame.chess[frame.coox][frame.cooy + 1] == 0) {
			frame.chess[frame.coox][frame.cooy + 1] = 1;
			frame.store.push(new Point(frame.coox + 1, frame.cooy + 1, 1)); // 人机：将棋子压入栈
		} // 6
		else if (frame.chess[frame.coox - 2][frame.cooy] == 0) {
			frame.chess[frame.coox - 2][frame.cooy] = 1;
			frame.store.push(new Point(frame.coox - 2, frame.cooy, 1)); // 人机：将棋子压入栈
		} // 7
	}

	public void B(MainFrame frame) {
		if (frame.chess[frame.coox - 2][frame.cooy] == 0) {
			frame.chess[frame.coox - 2][frame.cooy] = 1;
			frame.store.push(new Point(frame.coox - 2, frame.cooy, 1)); // 人机：将棋子压入栈
		} // 1
		else if (frame.chess[frame.coox][frame.cooy + 2] == 0) {
			frame.chess[frame.coox][frame.cooy + 2] = 1;
			frame.store.push(new Point(frame.coox, frame.cooy + 2, 1)); // 人机：将棋子压入栈
		} // 2
		else if (frame.chess[frame.coox - 2][frame.cooy + 1] == 0) {
			frame.chess[frame.coox - 2][frame.cooy + 1] = 1;
			frame.store.push(new Point(frame.coox - 2, frame.cooy + 1, 1)); // 人机：将棋子压入栈
		} // 3
		else if (frame.chess[frame.coox - 1][frame.cooy + 2] == 0) {
			frame.chess[frame.coox - 1][frame.cooy + 2] = 1;
			frame.store.push(new Point(frame.coox - 1, frame.cooy + 1, 1)); // 人机：将棋子压入栈
		} // 4
		else if (frame.chess[frame.coox - 1][frame.cooy] == 0) {
			frame.chess[frame.coox - 1][frame.cooy] = 1;
			frame.store.push(new Point(frame.coox - 1, frame.cooy, 1)); // 人机：将棋子压入栈
		} // 5
		else if (frame.chess[frame.coox][frame.cooy + 1] == 0) {
			frame.chess[frame.coox][frame.cooy + 1] = 1;
			frame.store.push(new Point(frame.coox, frame.cooy + 1, 1)); // 人机：将棋子压入栈
		} // 6
		else if (frame.chess[frame.coox - 2][frame.cooy + 2] == 0) {
			frame.chess[frame.coox - 2][frame.cooy + 2] = 1;
			frame.store.push(new Point(frame.coox - 2, frame.cooy + 2, 1)); // 人机：将棋子压入栈
		} // 7

	}

	public void C(MainFrame frame) {
		if (frame.chess[frame.coox + 1][frame.cooy + 2] == 0) {
			frame.chess[frame.coox + 1][frame.cooy + 2] = 1;
			frame.store.push(new Point(frame.coox + 1, frame.cooy + 2, 1)); // 人机：将棋子压入栈
		} // 1
		else if (frame.chess[frame.coox - 1][frame.cooy + 2] == 0) {
			frame.chess[frame.coox - 1][frame.cooy + 2] = 1;
			frame.store.push(new Point(frame.coox - 1, frame.cooy + 2, 1)); // 人机：将棋子压入栈
		} // 2
		else if (frame.chess[frame.coox - 1][frame.cooy + 1] == 0) {
			frame.chess[frame.coox - 1][frame.cooy + 1] = 1;
			frame.store.push(new Point(frame.coox - 1, frame.cooy + 1, 1)); // 人机：将棋子压入栈
		} // 3
		else if (frame.chess[frame.coox + 1][frame.cooy + 1] == 0) {
			frame.chess[frame.coox + 1][frame.cooy + 1] = 1;
			frame.store.push(new Point(frame.coox + 1, frame.cooy + 1, 1)); // 人机：将棋子压入栈
		} // 4
		else if (frame.chess[frame.coox + 1][frame.cooy] == 0) {
			frame.chess[frame.coox + 1][frame.cooy] = 1;
			frame.store.push(new Point(frame.coox + 1, frame.cooy, 1)); // 人机：将棋子压入栈
		} // 5
		else if (frame.chess[frame.coox - 1][frame.cooy] == 0) {
			frame.chess[frame.coox - 1][frame.cooy] = 1;
			frame.store.push(new Point(frame.coox - 1, frame.cooy, 1)); // 人机：将棋子压入栈
		} // 6
		else if (frame.chess[frame.coox][frame.cooy + 2] == 0) {
			frame.chess[frame.coox][frame.cooy + 2] = 1;
			frame.store.push(new Point(frame.coox, frame.cooy + 2, 1)); // 人机：将棋子压入栈
		} // 7false
	}

	public void D(MainFrame frame) {
		if (frame.chess[frame.coox][frame.cooy + 2] == 0) {
			frame.chess[frame.coox][frame.cooy + 2] = 1;
			frame.store.push(new Point(frame.coox, frame.cooy + 2, 1)); // 人机：将棋子压入栈
		} // 1
		else if (frame.chess[frame.coox + 2][frame.cooy] == 0) {
			frame.chess[frame.coox + 2][frame.cooy] = 1;
			frame.store.push(new Point(frame.coox + 2, frame.cooy, 1)); // 人机：将棋子压入栈
		} // 2
		else if (frame.chess[frame.coox + 2][frame.cooy + 1] == 0) {
			frame.chess[frame.coox + 2][frame.cooy + 1] = 1;
			frame.store.push(new Point(frame.coox + 2, frame.cooy + 1, 1)); // 人机：将棋子压入栈
		} // 3
		else if (frame.chess[frame.coox + 1][frame.cooy + 2] == 0) {
			frame.chess[frame.coox + 1][frame.cooy + 2] = 1;
			frame.store.push(new Point(frame.coox + 1, frame.cooy + 2, 1)); // 人机：将棋子压入栈
		} // 4
		else if (frame.chess[frame.coox][frame.cooy + 1] == 0) {
			frame.chess[frame.coox][frame.cooy + 1] = 1;
			frame.store.push(new Point(frame.coox, frame.cooy + 1, 1)); // 人机：将棋子压入栈
		} // 5
		else if (frame.chess[frame.coox + 1][frame.cooy] == 0) {
			frame.chess[frame.coox + 1][frame.cooy] = 1;
			frame.store.push(new Point(frame.coox + 1, frame.cooy, 1)); // 人机：将棋子压入栈
		} // 6
		else if (frame.chess[frame.coox + 2][frame.cooy + 2] == 0) {
			frame.chess[frame.coox + 2][frame.cooy + 2] = 1;
			frame.store.push(new Point(frame.coox + 2, frame.cooy + 2, 1)); // 人机：将棋子压入栈
		} // 7
	}

	public void E(MainFrame frame) {
		if (frame.chess[frame.coox + 2][frame.cooy + 1] == 0) {
			frame.chess[frame.coox + 2][frame.cooy + 1] = 1;
			frame.store.push(new Point(frame.coox + 2, frame.cooy + 1, 1)); // 人机：将棋子压入栈
		} // 1
		else if (frame.chess[frame.coox + 2][frame.cooy - 1] == 0) {
			frame.chess[frame.coox + 2][frame.cooy - 1] = 1;
			frame.store.push(new Point(frame.coox + 2, frame.cooy - 1, 1)); // 人机：将棋子压入栈
		} // 2
		else if (frame.chess[frame.coox + 1][frame.cooy - 1] == 0) {
			frame.chess[frame.coox + 1][frame.cooy - 1] = 1;
			frame.store.push(new Point(frame.coox + 1, frame.cooy - 1, 1)); // 人机：将棋子压入栈
		} // 3
		else if (frame.chess[frame.coox + 1][frame.cooy + 1] == 0) {
			frame.chess[frame.coox + 1][frame.cooy + 1] = 1;
			frame.store.push(new Point(frame.coox + 1, frame.cooy + 1, 1)); // 人机：将棋子压入栈
		} // 4
		else if (frame.chess[frame.coox][frame.cooy + 1] == 0) {
			frame.chess[frame.coox][frame.cooy + 1] = 1;
			frame.store.push(new Point(frame.coox, frame.cooy + 1, 1)); // 人机：将棋子压入栈
		} // 5
		else if (frame.chess[frame.coox][frame.cooy - 1] == 0) {
			frame.chess[frame.coox][frame.cooy - 1] = 1;
			frame.store.push(new Point(frame.coox, frame.cooy - 1, 1)); // 人机：将棋子压入栈
		} // 6
		else if (frame.chess[frame.coox + 2][frame.cooy] == 0) {
			frame.chess[frame.coox + 2][frame.cooy] = 1;
			frame.store.push(new Point(frame.coox + 2, frame.cooy, 1)); // 人机：将棋子压入栈
		} // 7
	}

	public void F(MainFrame frame) {
		if (frame.chess[frame.coox + 2][frame.cooy] == 0) {
			frame.chess[frame.coox + 2][frame.cooy] = 1;
			frame.store.push(new Point(frame.coox + 2, frame.cooy, 1)); // 人机：将棋子压入栈
		} // 1
		else if (frame.chess[frame.coox][frame.cooy - 2] == 0) {
			frame.chess[frame.coox][frame.cooy - 2] = 1;
			frame.store.push(new Point(frame.coox, frame.cooy - 2, 1)); // 人机：将棋子压入栈
		} // 2
		else if (frame.chess[frame.coox + 1][frame.cooy - 2] == 0) {
			frame.chess[frame.coox + 1][frame.cooy - 2] = 1;

			frame.store.push(new Point(frame.coox + 1, frame.cooy - 2, 1)); // 人机：将棋子压入栈
		} // 3
		else if (frame.chess[frame.coox + 2][frame.cooy - 1] == 0) {
			frame.chess[frame.coox + 2][frame.cooy - 1] = 1;
			frame.store.push(new Point(frame.coox + 2, frame.cooy - 1, 1)); // 人机：将棋子压入栈
		} // 4
		else if (frame.chess[frame.coox + 1][frame.cooy] == 0) {
			frame.chess[frame.coox + 1][frame.cooy] = 1;
			frame.store.push(new Point(frame.coox + 1, frame.cooy, 1)); // 人机：将棋子压入栈
		} // 5
		else if (frame.chess[frame.coox][frame.cooy - 1] == 0) {
			frame.chess[frame.coox][frame.cooy - 1] = 1;
			frame.store.push(new Point(frame.coox, frame.cooy - 1, 1)); // 人机：将棋子压入栈
		} // 6
		else if (frame.chess[frame.coox + 2][frame.cooy - 2] == 0) {
			frame.chess[frame.coox + 2][frame.cooy - 2] = 1;
			frame.store.push(new Point(frame.coox + 2, frame.cooy - 2, 1)); // 人机：将棋子压入栈
		} // 7
	}

	public void G(MainFrame frame) {
		if (frame.chess[frame.coox + 1][frame.cooy - 2] == 0) {
			frame.chess[frame.coox + 1][frame.cooy - 2] = 1;
			frame.store.push(new Point(frame.coox + 1, frame.cooy - 2, 1)); // 人机：将棋子压入栈
		} // 1
		else if (frame.chess[frame.coox - 1][frame.cooy - 2] == 0) {
			frame.chess[frame.coox - 1][frame.cooy - 2] = 1;
			frame.store.push(new Point(frame.coox - 1, frame.cooy - 2, 1)); // 人机：将棋子压入栈
		} // 2
		else if (frame.chess[frame.coox - 1][frame.cooy - 1] == 0) {
			frame.chess[frame.coox - 1][frame.cooy - 1] = 1;
			frame.store.push(new Point(frame.coox - 1, frame.cooy - 1, 1)); // 人机：将棋子压入栈
		} // 3
		else if (frame.chess[frame.coox + 1][frame.cooy - 1] == 0) {
			frame.chess[frame.coox + 1][frame.cooy - 1] = 1;
			frame.store.push(new Point(frame.coox + 1, frame.cooy - 1, 1)); // 人机：将棋子压入栈
		} // 4
		else if (frame.chess[frame.coox + 1][frame.cooy] == 0) {
			frame.chess[frame.coox + 1][frame.cooy] = 1;
			frame.store.push(new Point(frame.coox + 1, frame.cooy, 1)); // 人机：将棋子压入栈
		} // 5
		else if (frame.chess[frame.coox - 1][frame.cooy] == 0) {
			frame.chess[frame.coox - 1][frame.cooy] = 1;
			frame.store.push(new Point(frame.coox - 1, frame.cooy, 1)); // 人机：将棋子压入栈
		} // 6
		else if (frame.chess[frame.coox][frame.cooy - 2] == 0) {
			frame.chess[frame.coox][frame.cooy - 2] = 1;
			frame.store.push(new Point(frame.coox, frame.cooy - 2, 1)); // 人机：将棋子压入栈
		} // 7
	}

	public void H(MainFrame frame) {
		if (frame.chess[frame.coox - 2][frame.cooy] == 0) {
			frame.chess[frame.coox - 2][frame.cooy] = 1;
			frame.store.push(new Point(frame.coox - 2, frame.cooy, 1)); // 人机：将棋子压入栈
		} // 1
		else if (frame.chess[frame.coox][frame.cooy - 2] == 0) {
			frame.chess[frame.coox][frame.cooy - 2] = 1;
			frame.store.push(new Point(frame.coox, frame.cooy - 2, 1)); // 人机：将棋子压入栈
		} // 2
		else if (frame.chess[frame.coox - 1][frame.cooy - 2] == 0) {
			frame.chess[frame.coox - 1][frame.cooy - 2] = 1;
			frame.store.push(new Point(frame.coox - 1, frame.cooy - 2, 1)); // 人机：将棋子压入栈
		} // 3
		else if (frame.chess[frame.coox - 2][frame.cooy - 1] == 0) {
			frame.chess[frame.coox - 2][frame.cooy - 1] = 1;
			frame.store.push(new Point(frame.coox - 2, frame.cooy - 1, 1)); // 人机：将棋子压入栈
		} // 4
		else if (frame.chess[frame.coox - 1][frame.cooy] == 0) {
			frame.chess[frame.coox - 1][frame.cooy] = 1;
			frame.store.push(new Point(frame.coox - 1, frame.cooy, 1)); // 人机：将棋子压入栈
		} // 5
		else if (frame.chess[frame.coox][frame.cooy - 1] == 0) {
			frame.chess[frame.coox][frame.cooy - 1] = 1;
			frame.store.push(new Point(frame.coox, frame.cooy - 1, 1)); // 人机：将棋子压入栈
		} // 6
		else if (frame.chess[frame.coox - 2][frame.cooy - 2] == 0) {
			frame.chess[frame.coox - 2][frame.cooy - 2] = 1;
			frame.store.push(new Point(frame.coox - 2, frame.cooy - 2, 1)); // 人机：将棋子压入栈
		} // 7
	}

	public void First(MainFrame frame) {
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 14; j++) {
				if (frame.chess[i][j] == 1 && frame.chess[i][j + 1] == 2) {
					this.state[0] = 7;
					return;
				} else if (frame.chess[i][j] == 2 && frame.chess[i][j + 1] == 1) {
					this.state[0] = 3;
					return;
				}
			}
		}
		for (int i = 0; i < 14; i++) {
			for (int j = 0; j < 15; j++) {
				if (frame.chess[i][j] == 1 && frame.chess[i + 1][j] == 2) {
					this.state[0] = 1;
					return;
				} else if (frame.chess[i][j] == 2 && frame.chess[i + 1][j] == 1) {
					this.state[0] = 5;
					return;
				}
			}
		}
		for (int i = 0; i < 14; i++) {
			for (int j = 0; j < 14; j++) {
				if (frame.chess[i][j] == 1 && frame.chess[i + 1][j + 1] == 2) {
					this.state[0] = 8;
					return;
				} else if (frame.chess[i][j] == 2 && frame.chess[i + 1][j + 1] == 1) {
					this.state[0] = 4;
					return;
				}
			}
		}
		for (int i = 0; i < 14; i++) {
			for (int j = 1; j < 15; j++) {
				if (frame.chess[i][j] == 1 && frame.chess[i + 1][j - 1] == 2) {
					this.state[0] = 2;
					return;
				} else if (frame.chess[i][j] == 2 && frame.chess[i + 1][j - 1] == 1) {
					this.state[0] = 6;
					return;
				}
			}
		}
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 15; j++) {
				if (frame.chess[i][j] == 1 && frame.chess[i + 2][j] == 2) {
					this.state[0] = 1;
					return;
				} else if (frame.chess[i][j] == 2 && frame.chess[i + 2][j] == 1) {
					this.state[0] = 5;
					return;
				}
			}
		}
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 13; j++) {
				if (frame.chess[i][j] == 1 && frame.chess[i][j + 2] == 2) {
					this.state[0] = 7;
					return;
				} else if (frame.chess[i][j] == 2 && frame.chess[i][j + 2] == 1) {
					this.state[0] = 3;
					return;
				}
			}
		}
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 13; j++) {
				if (frame.chess[i][j] == 1 && frame.chess[i + 2][j + 2] == 2) {
					this.state[0] = 8;
					return;
				} else if (frame.chess[i][j] == 2 && frame.chess[i + 2][j + 2] == 1) {
					this.state[0] = 4;
					return;
				}
			}
		}
		for (int i = 0; i < 13; i++) {
			for (int j = 2; j < 15; j++) {
				if (frame.chess[i][j] == 1 && frame.chess[i + 2][j - 2] == 2) {
					this.state[0] = 2;
					return;
				} else if (frame.chess[i][j] == 2 && frame.chess[i + 2][j - 2] == 1) {
					this.state[0] = 6;
					return;
				}
			}
		}
		for (int i = 0; i < 13; i++) {
			for (int j = 2; j < 15; j++) {
				if (frame.chess[i][j] == 1) {
					frame.chess[i + 1][j] = 1;
					frame.store.push(new Point(i + 1, j, 1)); // 人机：将棋子压入栈
					return;
				}
			}
		}
	}

	public void Tryfirst(MainFrame frame) {
		switch (this.state[0]) {
		case 1:
			A(frame);
			break;
		case 2:
			B(frame);
			break;
		case 3:
			C(frame);
			break;
		case 4:
			D(frame);
			break;
		case 5:
			E(frame);
			break;
		case 6:
			F(frame);
			break;
		case 7:
			G(frame);
			break;
		case 8:
			H(frame);
			break;
		}
	}

	public void Second(MainFrame frame) {
		// a1
		if (this.state[1] == 1) {
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 15; j++) {
					if (frame.chess[i][j] == 1) {
						if (frame.chess[i][j] == 1 && frame.chess[i + 1][j] == 1 && frame.chess[i + 2][j] == 0
								&& frame.chess[i + 3][j] == 0) {
							if (i >= 2 && frame.chess[i - 1][j] == 0 && frame.chess[i - 2][j] == 0) {
								frame.chess[i - 1][j] = 1;
								frame.store.push(new Point(i - 1, j, 1)); // 人机：将棋子压入栈
								return;
							} else {
								frame.chess[i + 2][j] = 1;
								frame.store.push(new Point(i + 2, j, 1)); // 人机：将棋子压入栈
								return;
							}
						} else if (frame.chess[i][j] == 1 && frame.chess[i + 1][j] == 1 && frame.chess[i + 2][j] == 2
								&& frame.chess[i + 3][j] == 0) {
							if (frame.chess[i][j + 1] == 0) {
								frame.chess[i][j + 1] = 1;
								frame.store.push(new Point(i, j + 1, 1)); // 人机：将棋子压入栈
								return;
							}
							if (j > 1 && frame.chess[i][j - 1] == 0) {
								frame.chess[i][j - 1] = 1;
								frame.store.push(new Point(i, j - 1, 1)); // 人机：将棋子压入栈
								return;
							}
						} else {
							if (frame.chess[i][j + 1] == 0) {
								frame.chess[i][j + 1] = 1;
								frame.store.push(new Point(i, j + 1, 1)); // 人机：将棋子压入栈
								return;
							}
							if (j > 1 && frame.chess[i][j - 1] == 0) {
								frame.chess[i][j - 1] = 1;
								frame.store.push(new Point(i, j - 1, 1)); // 人机：将棋子压入栈
								return;
							}
						}
					}

				}
			}
		}
		// a2
		if (this.state[1] == 2) {
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 12; j++) {
					if (frame.chess[i][j] == 1) {
						if (frame.chess[i][j] == 1 && frame.chess[i][j + 1] == 1 && frame.chess[i][j + 2] == 0
								&& frame.chess[i][j + 3] == 0) {
							if (j >= 2 && frame.chess[i][j - 1] == 0 && frame.chess[i][j - 2] == 0) {
								frame.chess[i][j - 1] = 1;
								frame.store.push(new Point(i, j - 1, 1)); // 人机：将棋子压入栈
								return;
							} else {
								frame.chess[i][j + 2] = 1;
								frame.store.push(new Point(i, j + 2, 1)); // 人机：将棋子压入栈
								return;
							}
						} else if (frame.chess[i][j] == 1 && frame.chess[i][j + 1] == 1 && frame.chess[i][j + 2] == 2
								&& frame.chess[i][j + 3] == 0) {
							if (frame.chess[i + 1][j] == 0) {
								frame.chess[i + 1][j] = 1;
								frame.store.push(new Point(i + 1, j, 1)); // 人机：将棋子压入栈
								return;
							}
							if (i > 1 && frame.chess[i - 1][j] == 0) {
								frame.chess[i - 1][j] = 1;
								frame.store.push(new Point(i - 1, j, 1)); // 人机：将棋子压入栈
								return;
							}
						} else {
							if (frame.chess[i + 1][j] == 0) {
								frame.chess[i + 1][j] = 1;
								frame.store.push(new Point(i + 1, j, 1)); // 人机：将棋子压入栈
								return;
							}
							if (i > 1 && frame.chess[i - 1][j] == 0) {
								frame.chess[i - 1][j] = 1;
								frame.store.push(new Point(i - 1, j, 1)); // 人机：将棋子压入栈
								return;
							}
						}
					}
				}
			}
		}
		// a3
		if (this.state[1] == 3) {
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 15; j++) {
					if (frame.chess[i][j] == 1) {
						if (frame.chess[i][j] == 1 && frame.chess[i + 1][j] == 0 && frame.chess[i + 2][j] == 1
								&& frame.chess[i + 3][j] == 0) {
							if (i >= 2 && frame.chess[i - 1][j] == 0 && frame.chess[i - 2][j] == 0) {
								frame.chess[i + 1][j] = 1;
								frame.store.push(new Point(i + 1, j, 1)); // 人机：将棋子压入栈
								return;
							} else if (i >= 2 && j > 1 && frame.chess[i - 1][j] == 0 && frame.chess[i - 2][j] != 0) {
								frame.chess[i][j + 1] = 1;
								frame.store.push(new Point(i, j + 1, 1)); // 人机：将棋子压入栈
								return;
							}
						} else if (frame.chess[i][j] == 1 && frame.chess[i + 1][j] == 2 && frame.chess[i + 2][j] == 1
								&& frame.chess[i + 3][j] == 0) {
							if (frame.chess[i][j + 1] == 0) {
								frame.chess[i][j + 1] = 1;
								frame.store.push(new Point(i, j + 1, 1)); // 人机：将棋子压入栈
								return;
							}
							if (j > 1 && frame.chess[i][j - 1] == 0) {
								frame.chess[i][j - 1] = 1;
								frame.store.push(new Point(i, j - 1, 1)); // 人机：将棋子压入栈
								return;
							}
						} else {
							if (frame.chess[i + 2][j + 1] == 0) {
								frame.chess[i + 2][j + 1] = 1;
								frame.store.push(new Point(i + 2, j + 1, 1)); // 人机：将棋子压入栈
								return;
							}
							if (j > 1 && frame.chess[i + 2][j - 1] == 0) {
								frame.chess[i + 2][j - 1] = 1;
								frame.store.push(new Point(i + 2, j - 1, 1)); // 人机：将棋子压入栈
								return;
							} else if (frame.chess[i + 1][j + 1] == 0) {
								frame.chess[i + 1][j + 1] = 1;
								frame.store.push(new Point(i + 1, j + 1, 1)); // 人机：将棋子压入栈
								return;
							}
							if (frame.chess[i + 1][j - 1] == 0) {
								frame.chess[i + 1][j - 1] = 1;
								frame.store.push(new Point(i + 1, j - 1, 1)); // 人机：将棋子压入栈
								return;
							}
						}
					}
				}
			}
		}
		// a4
		if (this.state[1] == 4) {
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 12; j++) {
					if (frame.chess[i][j] == 1) {
						if (frame.chess[i][j] == 1 && frame.chess[i][j + 1] == 0 && frame.chess[i][j + 2] == 1
								&& frame.chess[i][j + 3] == 0) {
							if (j >= 2 && frame.chess[i][j - 1] == 0 && frame.chess[i][j - 2] == 0) {
								frame.chess[i][j + 1] = 1;
								frame.store.push(new Point(i, j + 1, 1)); // 人机：将棋子压入栈
								return;
							} else if (j >= 2 && i > 1 && frame.chess[i][j - 1] == 0 && frame.chess[i][j - 2] != 0) {
								frame.chess[i + 1][j] = 1;
								frame.store.push(new Point(i + 1, j, 1)); // 人机：将棋子压入栈
								return;
							}
						} else if (frame.chess[i][j] == 1 && frame.chess[i][j + 1] == 2 && frame.chess[i][j + 2] == 1
								&& frame.chess[i][j + 3] == 0) {
							if (frame.chess[i + 1][j] == 0) {
								frame.chess[i + 1][j] = 1;
								frame.store.push(new Point(i + 1, j, 1)); // 人机：将棋子压入栈
								return;
							}
							if (i > 1 && frame.chess[i - 1][j] == 0) {
								frame.chess[i - 1][j] = 1;
								frame.store.push(new Point(i - 1, j, 1)); // 人机：将棋子压入栈
								return;
							}
						} else {
							if (frame.chess[i + 1][j + 2] == 0) {
								frame.chess[i + 1][j + 2] = 1;
								frame.store.push(new Point(i + 1, j + 2, 1)); // 人机：将棋子压入栈
								return;
							}
							if (i > 1 && frame.chess[i - 1][j + 2] == 0) {
								frame.chess[i - 1][j + 2] = 1;
								frame.store.push(new Point(i - 1, j + 2, 1)); // 人机：将棋子压入栈
								return;
							} else if (frame.chess[i + 1][j + 1] == 0) {
								frame.chess[i + 1][j + 1] = 1;
								frame.store.push(new Point(i + 1, j + 1, 1)); // 人机：将棋子压入栈
								return;
							}
							if (frame.chess[i - 1][j + 1] == 0) {
								frame.chess[i - 1][j + 1] = 1;
								frame.store.push(new Point(i - 1, j + 1, 1)); // 人机：将棋子压入栈
								return;
							}
						}
					}
				}
			}
		}
		// a5
		if (this.state[1] == 5) {
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					if (frame.chess[i][j] == 1) {
						if (i > 0 && j > 0 && i < 13 && j < 13 && frame.chess[i + 2][j + 2] == 0
								&& frame.chess[i - 1][j - 1] == 0) {
							if (i > 1 && j > 1 && frame.chess[i - 2][j - 2] == 0) {
								if (i < 12 && j < 12 && frame.chess[i + 3][j + 3] == 0 && frame.chess[i + 2][j + 2] == 0
										&& frame.chess[i - 1][j - 1] == 0) {
									frame.chess[i + 2][j + 2] = 1;
									frame.store.push(new Point(i + 2, j + 2, 1)); // 人机：将棋子压入栈
									return;
								} else {
									frame.chess[i - 1][j - 1] = 1;
									frame.store.push(new Point(i - 1, j - 1, 1)); // 人机：将棋子压入栈
									return;
								}
							} else if (i < 12 && i < 12 && frame.chess[i + 3][j + 3] == 0
									&& frame.chess[i - 1][j - 1] == 0) {
								frame.chess[i - 1][j - 1] = 1;
								frame.store.push(new Point(i - 1, j - 1, 1)); // 人机：将棋子压入栈
								return;
							} else if (frame.chess[i + 1][j] == 0) {
								frame.chess[i + 1][j] = 1;
								frame.store.push(new Point(i + 1, j, 1)); // 人机：将棋子压入栈
								return;
							} else if (frame.chess[i][j + 1] == 0) {
								frame.chess[i][j + 1] = 1;
								frame.store.push(new Point(i, j + 1, 1)); // 人机：将棋子压入栈
								return;
							} else if (j > 0 && frame.chess[i][j - 1] == 0) {
								frame.chess[i][j - 1] = 1;
								frame.store.push(new Point(i, j - 1, 1)); // 人机：将棋子压入栈
								return;
							} else if (i < 14 && j < 13 && frame.chess[i + 1][j + 2] == 0) {
								frame.chess[i + 1][j + 2] = 1;
								frame.store.push(new Point(i + 1, j + 2, 1)); // 人机：将棋子压入栈
								return;
							} else if (j < 13 && frame.chess[i][j + 2] == 0) {
								frame.chess[i][j + 2] = 1;
								frame.store.push(new Point(i, j + 2, 1)); // 人机：将棋子压入栈
								return;
							} else if (j > 0 && i < 14 && frame.chess[i + 1][j - 1] == 0) {
								frame.chess[i + 1][j - 1] = 1;
								frame.store.push(new Point(i + 1, j - 1, 1)); // 人机：将棋子压入栈
								return;
							} else {
								D(frame);
								return;
							}
						} else if (i < 12 && i < 12 && frame.chess[i + 3][j + 3] == 0
								&& frame.chess[i - 1][j - 1] == 0) {
							frame.chess[i - 1][j - 1] = 1;
							frame.store.push(new Point(i - 1, j - 1, 1)); // 人机：将棋子压入栈
							return;
						} else if (frame.chess[i + 1][j] == 0) {
							frame.chess[i + 1][j] = 1;
							frame.store.push(new Point(i + 1, j, 1)); // 人机：将棋子压入栈
							return;
						} else if (frame.chess[i][j + 1] == 0) {
							frame.chess[i][j + 1] = 1;
							frame.store.push(new Point(i, j + 1, 1)); // 人机：将棋子压入栈
							return;
						} else if (j > 0 && frame.chess[i][j - 1] == 0) {
							frame.chess[i][j - 1] = 1;
							frame.store.push(new Point(i, j - 1, 1)); // 人机：将棋子压入栈
							return;
						} else if (i < 14 && j < 13 && frame.chess[i + 1][j + 2] == 0) {
							frame.chess[i + 1][j + 2] = 1;
							frame.store.push(new Point(i + 1, j + 2, 1)); // 人机：将棋子压入栈
							return;
						} else if (j < 13 && frame.chess[i][j + 2] == 0) {
							frame.chess[i][j + 2] = 1;
							frame.store.push(new Point(i, j + 2, 1)); // 人机：将棋子压入栈
							return;
						} else if (j > 0 && i < 14 && frame.chess[i + 1][j - 1] == 0) {
							frame.chess[i + 1][j - 1] = 1;
							frame.store.push(new Point(i + 1, j - 1, 1)); // 人机：将棋子压入栈
							return;
						} else {
							D(frame);
							return;
						}
					}
				}
			}
		}
		// a6
		if (this.state[1] == 6) {
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 12; j++) {
					if (frame.chess[i][j] == 1) {
						if (frame.chess[i + 1][j + 1] == 0 && frame.chess[i + 2][j + 2] == 1
								&& frame.chess[i + 3][j + 3] == 0) {
							if (i > 2 && j > 2 && frame.chess[i - 1][j - 1] == 0 && frame.chess[i - 2][j - 2] == 0) {
								frame.chess[i - 1][j - 1] = 1;
								frame.store.push(new Point(i - 1, j - 1, 1)); // 人机：将棋子压入栈
								return;
							} else if (frame.chess[i + 1][j + 1] == 0) {
								frame.chess[i + 1][j + 1] = 1;
								frame.store.push(new Point(i + 1, j + 1, 1)); // 人机：将棋子压入栈
								return;
							} else if (frame.chess[i][j + 1] == 0) {
								frame.chess[i][j + 1] = 1;
								frame.store.push(new Point(i, j + 1, 1)); // 人机：将棋子压入栈
								return;
							} else if (frame.chess[i + 1][j] == 0) {
								frame.chess[i + 1][j] = 1;
								frame.store.push(new Point(i + 1, j, 1)); // 人机：将棋子压入栈
								return;
							}
						}
					}
				}
			}
		}
		// a7
		if (this.state[1] == 7) {
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					if (frame.chess[i][j] == 1) {
						if (i > 1 && j < 13 && frame.chess[i - 1][j + 1] == 0 && frame.chess[i + 2][j - 2] == 0) {
							if (frame.chess[i + 3][j - 3] == 0) {
								frame.chess[i + 2][j - 2] = 1;
								frame.store.push(new Point(i + 2, j - 2, 1)); // 人机：将棋子压入栈
								return;
							} else if (i > 1 && j < 13 && frame.chess[i - 2][j + 2] == 0) {
								frame.chess[i - 1][j + 1] = 1;
								frame.store.push(new Point(i - 1, j + 1, 1)); // 人机：将棋子压入栈
								return;
							}
						} else if (i > 1 && j < 13 && frame.chess[i - 1][j + 1] != 0
								&& frame.chess[i + 2][j - 2] == 0) {
							if (frame.chess[i + 1][j] == 0) {
								frame.chess[i + 1][j] = 1;
								frame.store.push(new Point(i + 1, j, 1)); // 人机：将棋子压入栈
								return;
							} else if (frame.chess[i][j + 1] == 0) {
								frame.chess[i][j + 1] = 1;
								frame.store.push(new Point(i, j + 1, 1)); // 人机：将棋子压入栈
								return;
							} else {
								B(frame);
								return;
							}
						} else {
							C(frame);
							return;
						}
					}
				}
			}
		}
		// a8
		if (this.state[1] == 8) {
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					if (frame.chess[i][j] == 1) {
						if (i > 0 && i < 12 && j > 2 && j < 14 && frame.chess[i - 1][j + 1] == 0
								&& frame.chess[i + 1][j - 1] == 0 && frame.chess[i + 3][j - 3] == 0) {
							frame.chess[i + 1][j - 1] = 1;
							frame.store.push(new Point(i + 1, j - 1, 1)); // 人机：将棋子压入栈
							return;
						} else if (i > 0 && i < 12 && j > 2 && j < 14 && frame.chess[i - 1][j + 1] != 0
								&& frame.chess[i + 1][j - 1] == 0 && frame.chess[i + 3][j - 3] == 0) {
							if (frame.chess[i + 2][j] == 0) {
								frame.chess[i + 2][j] = 1;
								frame.store.push(new Point(i + 2, j, 1)); // 人机：将棋子压入栈
								return;
							} else if (frame.chess[i][j - 2] == 0) {
								frame.chess[i][j - 2] = 1;
								frame.store.push(new Point(i, j - 2, 1)); // 人机：将棋子压入栈
								return;
							} else {
								F(frame);
								return;
							}
						} else if (i > 0 && i < 12 && j > 2 && j < 14 && frame.chess[i - 1][j + 1] == 0
								&& frame.chess[i + 1][j - 1] == 0 && frame.chess[i + 3][j - 3] != 0) {
							if (frame.chess[i + 2][j] == 0) {
								frame.chess[i + 2][j] = 1;
								frame.store.push(new Point(i + 2, j, 1)); // 人机：将棋子压入栈
								return;
							} else if (frame.chess[i][j - 2] == 0) {
								frame.chess[i][j - 2] = 1;
								frame.store.push(new Point(i, j - 2, 1)); // 人机：将棋子压入栈
								return;
							} else {
								F(frame);
								return;
							}
						} else if (i > 0 && i < 12 && j > 2 && j < 14 && frame.chess[i - 1][j + 1] == 0
								&& frame.chess[i + 1][j - 1] != 0 && frame.chess[i + 3][j - 3] == 0) {
							if (frame.chess[i + 2][j] == 0) {
								frame.chess[i + 2][j] = 1;
								frame.store.push(new Point(i + 2, j, 1)); // 人机：将棋子压入栈
								return;
							} else if (frame.chess[i][j - 2] == 0) {
								frame.chess[i][j - 2] = 1;
								frame.store.push(new Point(i, j - 2, 1)); // 人机：将棋子压入栈
								return;
							} else {
								F(frame);
								return;
							}
						} else {
							C(frame);
							return;
						}
					}
				}
			}
		}

	}

	public void Trysecond(MainFrame frame) {

		for (int i = 0; i < 14; i++) {
			for (int j = 0; j < 15; j++) {
				if (frame.chess[i][j] == 1 && frame.chess[i + 1][j] == 1) {
					this.state[1] = 1;
					return;
				}
			}
		}
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 14; j++) {
				if (frame.chess[i][j] == 1 && frame.chess[i][j + 1] == 1) {
					this.state[1] = 2;
					return;
				}
			}
		}
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 15; j++) {
				if (frame.chess[i][j] == 1 && frame.chess[i + 2][j] == 1) {
					this.state[1] = 3;
					return;
				}
			}
		}
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 13; j++) {
				if (frame.chess[i][j] == 1 && frame.chess[i][j + 2] == 1) {
					this.state[1] = 4;
					return;
				}
			}
		}
		for (int i = 0; i < 14; i++) {
			for (int j = 0; j < 14; j++) {
				if (frame.chess[i][j] == 1 && frame.chess[i + 1][j + 1] == 1) {
					this.state[1] = 5;
					return;
				}
			}
		}
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 13; j++) {
				if (frame.chess[i][j] == 1 && frame.chess[i + 2][j + 2] == 1) {
					this.state[1] = 6;
					return;
				}
			}
		}
		for (int i = 0; i < 14; i++) {
			for (int j = 1; j < 15; j++) {
				if (frame.chess[i][j] == 1 && frame.chess[i + 1][j - 1] == 1) {
					this.state[1] = 7;
					return;
				}
			}
		}
		for (int i = 0; i < 13; i++) {
			for (int j = 2; j < 15; j++) {
				if (frame.chess[i][j] == 1 && frame.chess[i + 2][j - 2] == 1) {
					this.state[1] = 8;
					return;
				}
			}
		}
	}

	public void chesstype(MainFrame frame, int flag) {

		// **************************************************************************************************************
		// 两个棋子相连的情况，若满足，则为2分，不满足，则为之前的值
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (frame.chess[i][j] == 0) {
					if (i < 13 && frame.chess[i + 1][j] == flag && frame.chess[i + 2][j] == flag) {
						if (i < 12 && i > 0) {
							if (frame.chess[i + 3][j] == 0 && frame.chess[i - 1][j] == 0) {
								this.score[i][j] = this.score[i][j] + 3;
								System.out.println("A");
							} else if (frame.chess[i + 3][j] != 0 && frame.chess[i - 1][j] == 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("B");
							} else if (frame.chess[i + 3][j] == 0 && frame.chess[i - 1][j] != 0) {
								this.score[i][j] = this.score[i][j] + 1;
								System.out.println("C");
							}
						}
					}
					if (i > 1 && frame.chess[i - 1][j] == flag && frame.chess[i - 2][j] == flag) {
						if (i < 14 && i > 2) {
							if (frame.chess[i - 3][j] == 0 && frame.chess[i + 1][j] == 0) {
								this.score[i][j] = this.score[i][j] + 3;
								System.out.println("D");
							} else if (frame.chess[i - 3][j] != 0 && frame.chess[i + 1][j] == 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("E");
							} else if (frame.chess[i - 3][j] == 0 && frame.chess[i + 1][j] != 0) {
								this.score[i][j] = this.score[i][j] + 1;
								System.out.println("F");
							}
						}
					}
					if (i < 13 && j < 13 && frame.chess[i + 1][j + 1] == flag && frame.chess[i + 2][j + 2] == flag) {
						if (i < 12 && i > 0 && j < 12 && j > 0) {
							if (frame.chess[i + 3][j + 3] == 0 && frame.chess[i - 1][j - 1] == 0) {
								this.score[i][j] = this.score[i][j] + 3;
								System.out.println("G");
							} else if (frame.chess[i + 3][j + 3] != 0 && frame.chess[i - 1][j - 1] == 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("H");
							} else if (frame.chess[i + 3][j + 3] == 0 && frame.chess[i - 1][j - 1] != 0) {
								this.score[i][j] = this.score[i][j] + 1;
								System.out.println("I");
							}
						}
					}
					if (i > 1 && j > 1 && frame.chess[i - 1][j - 1] == flag && frame.chess[i - 2][j - 2] == flag) {
						if (i < 14 && i > 2 && j > 2 && j < 14) {
							if (frame.chess[i - 3][j - 3] == 0 && frame.chess[i + 1][j + 1] == 0) {
								this.score[i][j] = this.score[i][j] + 3;
								System.out.println("J");
							} else if (i < 12 && j < 12 && frame.chess[i + 3][j + 3] != 0
									&& frame.chess[i - 1][j - 1] == 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("K");
							} else if (i < 12 && j < 12 && frame.chess[i + 3][j + 3] == 0
									&& frame.chess[i - 1][j - 1] != 0) {
								this.score[i][j] = this.score[i][j] + 1;
								System.out.println("L");
							}
						}
					}
					if (i < 13 && j > 1 && frame.chess[i + 1][j - 1] == flag && frame.chess[i + 2][j - 2] == flag) {
						if (i < 12 && i > 0 && j > 2 && j < 14) {
							if (frame.chess[i + 3][j - 3] == 0 && frame.chess[i - 1][j + 1] == 0) {
								this.score[i][j] = this.score[i][j] + 3;
								System.out.println("M");
							} else if (frame.chess[i + 3][j - 3] != 0 && frame.chess[i - 1][j + 1] == 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("N");
							} else if (frame.chess[i + 3][j - 3] == 0 && frame.chess[i - 1][j + 1] != 0) {
								this.score[i][j] = this.score[i][j] + 1;
								System.out.println("O");
							}
						}
					}
					if (i > 1 && j < 13 && frame.chess[i - 1][j + 1] == flag && frame.chess[i - 2][j + 2] == flag) {
						if (i < 12 && i > 2 && j > 0 && j < 12) {
							if (frame.chess[i - 3][j + 3] == 0 && frame.chess[i + 1][j - 1] == 0) {
								this.score[i][j] = this.score[i][j] + 3;
								System.out.println("P");
							} else if (frame.chess[i - 3][j + 3] != 0 && frame.chess[i + 1][j - 1] == 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("Q");
							} else if (frame.chess[i - 3][j + 3] == 0 && frame.chess[i + 1][j - 1] != 0) {
								this.score[i][j] = this.score[i][j] + 1;
								System.out.println("R");
							}
						}
					}
					if (j > 1 && frame.chess[i][j - 1] == flag && frame.chess[i][j - 2] == flag) {
						if (j > 2 && j < 14) {
							if (frame.chess[i][j - 3] == 0 && frame.chess[i][j + 1] == 0) {
								this.score[i][j] = this.score[i][j] + 3;
								System.out.println("S");
							} else if (frame.chess[i][j - 3] != 0 && frame.chess[i][j + 1] == 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("T");
							} else if (frame.chess[i][j - 3] == 0 && frame.chess[i][j + 1] != 0) {
								this.score[i][j] = this.score[i][j] + 1;
								System.out.println("U");
							}
						}
					}
					if (j < 13 && frame.chess[i][j + 1] == flag && frame.chess[i][j + 2] == flag) {
						if (j > 0 && j < 12) {
							if (frame.chess[i][j + 3] == 0 && frame.chess[i][j - 1] == 0) {
								this.score[i][j] = this.score[i][j] + 3;
								System.out.println("V");
							} else if (frame.chess[i][j + 3] == 0 && frame.chess[i][j - 1] == 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("W");
							} else if (frame.chess[i][j + 3] == 0 && frame.chess[i][j - 1] == 0) {
								this.score[i][j] = this.score[i][j] + 1;
								System.out.println("S");
							}
						}
					}
				}
			}
		}
		// **************************************************************************************************************
		// **************************************************************************************************************
		// 两个棋子中间有一个空格的情况，若满足，则为3分，不满足，则为之前的值
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (frame.chess[i][j] == 0) {
					if (i < 14 && i > 0 && frame.chess[i + 1][j] == flag && frame.chess[i - 1][j] == flag) {
						if (i < 13 && i > 1) {
							if (frame.chess[i + 2][j] == 0 && frame.chess[i - 2][j] == 0) {
								this.score[i][j] = this.score[i][j] + 3;
								System.out.println("Y");
							} else if (frame.chess[i + 2][j] != 0 && frame.chess[i - 2][j] == 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("Z");
							} else if (frame.chess[i + 2][j] == 0 && frame.chess[i - 2][j] != 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("A1");
							} else {
								this.score[i][j] = this.score[i][j] + 0;
								System.out.println("A2");
							}
						}
					}
					if (j < 14 && j > 0 && frame.chess[i][j - 1] == flag && frame.chess[i][j + 1] == flag) {
						if (j < 13 && j > 1) {
							if (frame.chess[i][j + 2] == 0 && frame.chess[i][j - 2] == 0) {
								this.score[i][j] = this.score[i][j] + 3;
								System.out.println("A3");
							} else if (frame.chess[i][j + 2] != 0 && frame.chess[i][j - 2] == 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("A4");
							} else if (frame.chess[i][j + 2] == 0 && frame.chess[i][j - 2] != 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("A5");
							} else {
								this.score[i][j] = this.score[i][j] + 0;
								System.out.println("A6");
							}
						}
					}
					if (i < 14 && i > 0 && j < 14 && j > 0 && frame.chess[i + 1][j + 1] == flag
							&& frame.chess[i - 1][j - 1] == flag) {
						if (j < 13 && j > 1 && i < 13 && i > 1) {
							if (frame.chess[i + 2][j + 2] == 0 && frame.chess[i - 2][j - 2] == 0) {
								this.score[i][j] = this.score[i][j] + 3;
								System.out.println("A7");
							} else if (frame.chess[i + 2][j + 2] != 0 && frame.chess[i - 2][j - 2] == 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("A8");
							} else if (frame.chess[i + 2][j + 2] == 0 && frame.chess[i - 2][j - 2] != 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("A9");
							} else {
								this.score[i][j] = this.score[i][j] + 0;
								System.out.println("A10");
							}
						}
					}
					if (i < 14 && i > 0 && j < 14 && j > 0 && frame.chess[i + 1][j - 1] == flag
							&& frame.chess[i - 1][j + 1] == flag) {
						if (j > 1 && i < 13) {
							if (frame.chess[i + 2][j - 2] == 0 && frame.chess[i + 2][j - 2] == 0) {
								this.score[i][j] = this.score[i][j] + 3;
								System.out.println("A11");
							} else if (frame.chess[i + 2][j - 2] != 0 && frame.chess[i + 2][j - 2] == 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("A12");
							} else if (frame.chess[i + 2][j - 2] == 0 && frame.chess[i + 2][j - 2] != 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("A13");
							} else {
								this.score[i][j] = this.score[i][j] + 0;
								System.out.println("A14");
							}
						}
					}
				}
			}
		}
		// **************************************************************************************************************
		// **************************************************************************************************************
		// 两个棋子中间有两个空格的情况，若满足，则为4分，不满足，则为之前的值
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (frame.chess[i][j] == 0) {
					if (i > 0 && i < 13 && frame.chess[i + 1][j] == 0 && frame.chess[i - 1][j] == flag
							&& frame.chess[i + 2][j] == flag) {// 横向
						if (i < 12 && i > 1 && frame.chess[i + 3][j] == 0 && frame.chess[i - 2][j] == 0) {
							this.score[i][j] = this.score[i][j] + 4;
							System.out.println("A15");
						} else if (i < 12 && frame.chess[i + 3][j] == 0) {
							this.score[i][j] = this.score[i][j] + 2;
							System.out.println("A16");
						} else if (i > 1 && frame.chess[i - 2][j] == 0) {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A17");
						} else {
							this.score[i][j] = this.score[i][j] + 0;
							System.out.println("A18");
						}
					} else {
						this.score[i][j] = this.score[i][j] + 0;
						System.out.println("A19");
					}
					if (j < 13 && j > 0 && frame.chess[i][j + 1] == 0 && frame.chess[i][j - 1] == flag
							&& frame.chess[i][j + 2] == flag) {// 纵向
						if (j < 12 && j > 1 && frame.chess[i][j + 3] == 0 && frame.chess[i][j - 2] == 0) {
							this.score[i][j] = this.score[i][j] + 4;
							System.out.println("A20");
						} else if (j < 12 && frame.chess[i][j + 3] == 0) {
							this.score[i][j] = this.score[i][j] + 2;
							System.out.println("A21");
						} else if (j > 1 && frame.chess[i][j - 2] == 0) {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A22");
						} else {
							this.score[i][j] = this.score[i][j] + 0;
							System.out.println("A23");
						}
					} else {
						this.score[i][j] = this.score[i][j] + 0;
						System.out.println("A24");
					}
					if (i < 13 && i > 0 && j < 13 && j > 0 && frame.chess[i + 1][j + 1] == flag
							&& frame.chess[i - 1][j - 1] == flag && frame.chess[i + 2][j + 2] == flag) {// 左斜
						if (i < 12 && i > 1 && j < 12 && j > 1 && frame.chess[i + 3][j + 3] == 0
								&& frame.chess[i - 2][j - 2] == 0) {
							this.score[i][j] = this.score[i][j] + 4;
							System.out.println("A25");
						} else if (i < 12 && j < 12 && frame.chess[i + 3][j + 3] == 0) {
							this.score[i][j] = this.score[i][j] + 2;
							System.out.println("A26");
						} else if (i > 1 && j > 1 && frame.chess[i - 2][j - 2] == 0) {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A27");
						} else {
							this.score[i][j] = this.score[i][j] + 0;
							System.out.println("A28");
						}
					} else {
						this.score[i][j] = this.score[i][j] + 0;
						System.out.println("A29");
					}
					if (i < 12 && i > 0 && j > 1 && j < 14 && frame.chess[i + 1][j - 1] == 0
							&& frame.chess[i - 1][j + 1] == flag && frame.chess[i + 2][j - 2] == flag) {// 右斜
						if (i < 12 && i > 1 && j < 12 && j > 2 && frame.chess[i + 3][j - 3] == 0
								&& frame.chess[i - 2][j + 2] == 0) {
							this.score[i][j] = this.score[i][j] + 4;
							System.out.println("A30");
						} else if (i < 12 && j > 2 && frame.chess[i + 3][j - 3] == 0) {
							this.score[i][j] = this.score[i][j] + 2;
							System.out.println("A31");
						} else if (j < 12 && i > 2 && frame.chess[i - 2][j + 2] == 0) {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A32");
						} else {
							this.score[i][j] = this.score[i][j] + 0;
							System.out.println("A33");
						}
					} else {
						this.score[i][j] = this.score[i][j] + 0;
						System.out.println("A34");
					}
				}
			}
		}
		// **************************************************************************************************************
		// **************************************************************************************************************
		// 3个棋子相连的情况，若满足，则为6分，不满足，则为之前的值
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (frame.chess[i][j] == 0) {
					if (i < 12 && frame.chess[i + 1][j] == flag && frame.chess[i + 2][j] == flag
							&& frame.chess[i + 3][j] == flag) { // 横向+
						if (i < 11) {
							if (frame.chess[i + 4][j] == 0) {
								this.score[i][j] = this.score[i][j] + 9;
								System.out.println("A37");
							} else {
								this.score[i][j] = this.score[i][j] + 3;
								System.out.println("A38");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A39");
						}
					}
					if (i > 2 && frame.chess[i - 1][j] == flag && frame.chess[i - 2][j] == flag
							&& frame.chess[i - 3][j] == flag) { // 横向-
						if (i > 3) {
							if (frame.chess[i - 4][j] == 0) {
								this.score[i][j] = this.score[i][j] + 9;
								System.out.println("A40");
							} else {
								this.score[i][j] = this.score[i][j] + 3;
								System.out.println("A41");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A42");
						}
					}
					if (j < 12 && frame.chess[i][j + 1] == flag && frame.chess[i][j + 2] == flag
							&& frame.chess[i][j + 3] == flag) { // 纵向+
						if (j < 11) {
							if (frame.chess[i][j + 4] == 0) {
								this.score[i][j] = this.score[i][j] + 9;
								System.out.println("A43");
							} else {
								this.score[i][j] = this.score[i][j] + 3;
								System.out.println("A44");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A45");
						}
					}
					if (j > 2 && frame.chess[i][j - 1] == flag && frame.chess[i][j - 2] == flag
							&& frame.chess[i][j - 3] == flag) { // 纵向-
						if (j > 3) {
							if (frame.chess[i][j - 4] == 0) {
								this.score[i][j] = this.score[i][j] + 9;
								System.out.println("A46");
							} else {
								this.score[i][j] = this.score[i][j] + 3;
								System.out.println("A47");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A48");
						}
					} //
					if (i < 12 && j < 12 && frame.chess[i + 1][j + 1] == flag && frame.chess[i + 2][j + 2] == flag
							&& frame.chess[i + 3][j + 3] == flag) { // 左斜+
						if (j < 11 && i < 11) {
							if (frame.chess[i + 4][j + 4] == 0) {
								this.score[i][j] = this.score[i][j] + 9;
								System.out.println("A49");
							} else {
								this.score[i][j] = this.score[i][j] + 3;
								System.out.println("A50");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A51");
						}
					}
					if (i > 2 && j > 2 && frame.chess[i - 1][j - 1] == flag && frame.chess[i - 2][j - 2] == flag
							&& frame.chess[i - 3][j - 3] == flag) { // 左斜-
						if (j > 3 && i > 3) {
							if (frame.chess[i - 4][j - 4] == 0) {
								this.score[i][j] = this.score[i][j] + 9;
								System.out.println("A51");
							} else {
								this.score[i][j] = this.score[i][j] + 3;
								System.out.println("A53");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A54");
						}
					} //
					if (i < 12 && j > 2 && frame.chess[i + 1][j - 1] == flag && frame.chess[i + 2][j - 2] == flag
							&& frame.chess[i + 3][j - 3] == flag) {// 右斜+
						if (j > 3 && i < 11) {
							if (frame.chess[i + 4][j - 4] == 0) {
								this.score[i][j] = this.score[i][j] + 9;
								System.out.println("A55");
							} else {
								this.score[i][j] = this.score[i][j] + 3;
								System.out.println("A56");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A57");
						}
					}
					if (i > 2 && j < 12 && frame.chess[i - 1][j + 1] == flag && frame.chess[i - 2][j + 2] == flag
							&& frame.chess[i - 3][j + 3] == flag) {// 右斜-
						if (i > 3 && j < 11) {
							if (frame.chess[i - 4][j + 4] == 0) {
								this.score[i][j] = this.score[i][j] + 9;
								System.out.println("A58");
							} else {
								this.score[i][j] = this.score[i][j] + 3;
								System.out.println("A59");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A60");
						}
					}
				}
			}
		}
		// **************************************************************************************************************
		// **************************************************************************************************************
		// 3个棋子不相连的情况且在一条直线上，若满足，则为6分，不满足，则为之前的值
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (frame.chess[i][j] == 0) {
					if (i < 13 && i > 0 && frame.chess[i + 1][j] == flag && frame.chess[i + 2][j] == flag
							&& frame.chess[i - 1][j] == flag) { // 横向+
						if (i > 1 && i < 12) {
							if (frame.chess[i - 2][j] == 0 && frame.chess[i + 3][j] == 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A61");
							} else if (frame.chess[i - 2][j] != 0 && frame.chess[i + 3][j] == 0) {
								this.score[i][j] = this.score[i][j] + 4;
								System.out.println("A62");
							} else if (frame.chess[i - 2][j] == 0 && frame.chess[i + 3][j] != 0) {
								this.score[i][j] = this.score[i][j] + 4;
								System.out.println("A63");
							} else if (frame.chess[i - 2][j] != 0 && frame.chess[i + 3][j] != 0) {
								this.score[i][j] = this.score[i][j] + 1;
								System.out.println("A64");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A65");
						}
					}
					if (i < 14 && i > 1 && frame.chess[i - 1][j] == flag && frame.chess[i - 2][j] == flag
							&& frame.chess[i + 1][j] == flag) { // 横向-
						if (i > 2 && i < 13) {
							if (frame.chess[i - 3][j] == 0 && frame.chess[i + 2][j] == 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A66");
							} else if (frame.chess[i - 3][j] != 0 && frame.chess[i + 2][j] == 0) {
								this.score[i][j] = this.score[i][j] + 4;
								System.out.println("A67");
							} else if (frame.chess[i - 3][j] == 0 && frame.chess[i + 2][j] != 0) {
								this.score[i][j] = this.score[i][j] + 4;
								System.out.println("A68");
							} else if (frame.chess[i - 3][j] != 0 && frame.chess[i + 2][j] != 0) {
								this.score[i][j] = this.score[i][j] + 1;
								System.out.println("A69");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("70");
						}
					}
					if (j < 13 && j > 0 && frame.chess[i][j + 1] == flag && frame.chess[i][j + 2] == flag
							&& frame.chess[i][j - 1] == flag) { // 纵向+
						if (j > 1 && j < 12) {
							if (frame.chess[i][j - 2] == 0 && frame.chess[i][j + 3] == 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A71");
							} else if (frame.chess[i][j - 2] != 0 && frame.chess[i][j + 3] == 0) {
								this.score[i][j] = this.score[i][j] + 4;
								System.out.println("A72");
							} else if (frame.chess[i][j - 2] == 0 && frame.chess[i][j + 3] != 0) {
								this.score[i][j] = this.score[i][j] + 4;
								System.out.println("A73");
							} else if (frame.chess[i][j - 2] != 0 && frame.chess[i][j + 3] != 0) {
								this.score[i][j] = this.score[i][j] + 1;
								System.out.println("A74");
							} else {
								this.score[i][j] = this.score[i][j] + 1;
								System.out.println("75");
							}
						}
					}
					if (j < 14 && j > 1 && frame.chess[i][j - 1] == flag && frame.chess[i][j - 2] == flag
							&& frame.chess[i][j + 1] == flag) { // 纵向-
						if (j > 2 && j < 13) {
							if (frame.chess[i][j - 3] == 0 && frame.chess[i][j + 2] == 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A76");
							} else if (frame.chess[i][j - 3] != 0 && frame.chess[i][j + 2] == 0) {
								this.score[i][j] = this.score[i][j] + 4;
								System.out.println("A77");
							} else if (frame.chess[i][j - 3] == 0 && frame.chess[i][j + 2] != 0) {
								this.score[i][j] = this.score[i][j] + 4;
								System.out.println("A78");
							} else if (frame.chess[i][j - 3] != 0 && frame.chess[i][j + 2] != 0) {
								this.score[i][j] = this.score[i][j] + 1;
								System.out.println("A79");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("80");
						}
					}
					if (i < 13 && i > 0 && j < 13 && j > 0 && frame.chess[i + 1][j + 1] == flag
							&& frame.chess[i + 2][j + 2] == flag && frame.chess[i - 1][j - 1] == flag) { // 左斜+
						if (i > 1 && i < 12 && j > 1 && j < 12) {
							if (frame.chess[i - 2][j - 2] == 0 && frame.chess[i + 3][j + 3] == 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A81");
							} else if (frame.chess[i - 2][j - 2] != 0 && frame.chess[i + 3][j + 3] == 0) {
								this.score[i][j] = this.score[i][j] + 4;
								System.out.println("A82");
							} else if (frame.chess[i - 2][j - 2] == 0 && frame.chess[i + 3][j + 3] != 0) {
								this.score[i][j] = this.score[i][j] + 4;
								System.out.println("A83");
							} else if (frame.chess[i - 2][j - 2] != 0 && frame.chess[i + 3][j + 3] != 0) {
								this.score[i][j] = this.score[i][j] + 1;
								System.out.println("A84");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A85");
						}
					}
					if (i < 14 && i > 1 && j < 14 && j > 1 && frame.chess[i - 1][j - 1] == flag
							&& frame.chess[i - 2][j - 2] == flag && frame.chess[i + 1][j + 1] == flag) { // 左斜-
						if (i > 2 && i < 13 && j > 2 && j < 13) {
							if (frame.chess[i - 3][j - 3] == 0 && frame.chess[i + 2][j + 2] == 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A86");
							} else if (frame.chess[i - 3][j - 3] != 0 && frame.chess[i + 2][j + 2] == 0) {
								this.score[i][j] = this.score[i][j] + 4;
								System.out.println("A87");
							} else if (frame.chess[i - 3][j - 3] == 0 && frame.chess[i + 2][j + 2] != 0) {
								this.score[i][j] = this.score[i][j] + 4;
								System.out.println("A88");
							} else if (frame.chess[i - 3][j - 3] != 0 && frame.chess[i + 2][j + 2] != 0) {
								this.score[i][j] = this.score[i][j] + 1;
								System.out.println("A89");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A90");
						}
					}
					if (i < 13 && i > 0 && j < 14 && j > 1 && frame.chess[i - 1][j + 1] == flag
							&& frame.chess[i + 1][j - 1] == flag && frame.chess[i + 2][j - 2] == flag) { // 右斜+
						if (i > 1 && i < 12 && j > 2 && j < 13) {
							if (frame.chess[i + 3][j - 3] == 0 && frame.chess[i - 2][j + 2] == 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A91");
							} else if (frame.chess[i + 3][j - 3] != 0 && frame.chess[i - 2][j + 2] == 0) {
								this.score[i][j] = this.score[i][j] + 4;
								System.out.println("A92");
							} else if (frame.chess[i + 3][j - 3] == 0 && frame.chess[i - 2][j + 2] != 0) {
								this.score[i][j] = this.score[i][j] + 4;
								System.out.println("A93");
							} else if (frame.chess[i + 3][j - 3] != 0 && frame.chess[i - 2][j + 2] != 0) {
								this.score[i][j] = this.score[i][j] + 1;
								System.out.println("A94");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A95");
						}
					}
					if (i < 14 && i > 1 && j < 13 && j > 0 && frame.chess[i + 1][j - 1] == flag
							&& frame.chess[i - 1][j + 1] == flag && frame.chess[i - 2][j + 2] == flag) { // 右斜-
						if (i > 2 && i < 13 && j > 1 && j < 12) {
							if (frame.chess[i + 2][j - 2] == 0 && frame.chess[i - 3][j + 3] == 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A96");
							} else if (frame.chess[i + 2][j - 2] != 0 && frame.chess[i - 3][j + 3] == 0) {
								this.score[i][j] = this.score[i][j] + 4;
								System.out.println("A97");
							} else if (frame.chess[i + 2][j - 2] == 0 && frame.chess[i - 3][j + 3] != 0) {
								this.score[i][j] = this.score[i][j] + 4;
								System.out.println("A98");
							} else if (frame.chess[i + 2][j - 2] != 0 && frame.chess[i - 3][j + 3] != 0) {
								this.score[i][j] = this.score[i][j] + 1;
								System.out.println("A99");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A100");
						}
					}
				}
			}
		}
		// **************************************************************************************************************
		// **************************************************************************************************************
		// 4个棋子相连的情况，若满足，则为7分，不满足，则为之前的值
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (frame.chess[i][j] == 0) {
					if (i < 11 && frame.chess[i + 1][j] == flag && frame.chess[i + 2][j] == flag
							&& frame.chess[i + 3][j] == flag && frame.chess[i + 4][j] == flag) { // 横向+
						this.score[i][j] = this.score[i][j] + 10;
						System.out.println("A101");
					}
					if (i > 3 && frame.chess[i - 1][j] == flag && frame.chess[i - 2][j] == flag
							&& frame.chess[i - 3][j] == flag && frame.chess[i - 4][j] == flag) { // 横向-
						this.score[i][j] = this.score[i][j] + 10;
						System.out.println("A102");
					}
					if (j < 11 && frame.chess[i][j + 1] == flag && frame.chess[i][j + 2] == flag
							&& frame.chess[i][j + 3] == flag && frame.chess[i][j + 4] == flag) { // 纵向+
						this.score[i][j] = this.score[i][j] + 10;
						System.out.println("A103");
					}
					if (j > 3 && frame.chess[i][j - 1] == flag && frame.chess[i][j - 2] == flag
							&& frame.chess[i][j - 3] == flag && frame.chess[i][j - 4] == flag) { // 纵向-
						this.score[i][j] = this.score[i][j] + 10;
						System.out.println("A104");
					}
					if (i < 11 && j < 11 && frame.chess[i + 1][j + 1] == flag && frame.chess[i + 2][j + 2] == flag
							&& frame.chess[i + 3][j + 3] == flag && frame.chess[i + 4][j + 4] == flag) { // 左斜+
						this.score[i][j] = this.score[i][j] + 10;
						System.out.println("A105");
					}
					if (i > 3 && j > 3 && frame.chess[i - 1][j - 1] == flag && frame.chess[i - 2][j - 2] == flag
							&& frame.chess[i - 3][j - 3] == flag && frame.chess[i - 4][j - 4] == flag) { // 左斜-
						this.score[i][j] = this.score[i][j] + 10;
						System.out.println("A106");
					} //
					if (i < 11 && j > 3 && frame.chess[i + 1][j - 1] == flag && frame.chess[i + 2][j - 2] == flag
							&& frame.chess[i + 3][j - 3] == flag && frame.chess[i + 4][j - 4] == flag) {// 右斜+
						this.score[i][j] = this.score[i][j] + 10;
						System.out.println("A107");
					}
					if (i > 3 && j < 11 && frame.chess[i - 1][j + 1] == flag && frame.chess[i - 2][j + 2] == flag
							&& frame.chess[i - 3][j + 3] == flag && frame.chess[i - 4][j + 4] == flag) {// 右斜-
						this.score[i][j] = this.score[i][j] + 10;
						System.out.println("A108");
					}
				}
			}
		}
		// **************************************************************************************************************
		// **************************************************************************************************************
		// 4个棋子不相连的情况且在一条直线，若满足11011，则为7分，不满足，则为之前的值
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (frame.chess[i][j] == 0) {
					if (i < 13 && i > 1 && frame.chess[i - 1][j] == flag && frame.chess[i - 2][j] == flag
							&& frame.chess[i + 1][j] == flag && frame.chess[i + 2][j] == flag) { // 横向
						this.score[i][j] = this.score[i][j] + 7;
						System.out.println("A109");
					}
					if (j < 13 && j > 1 && frame.chess[i][j + 1] == flag && frame.chess[i][j + 2] == flag
							&& frame.chess[i][j - 1] == flag && frame.chess[i][j - 2] == flag) { // 纵向
						this.score[i][j] = this.score[i][j] + 7;
						System.out.println("A110");
					}
					if (i < 13 && i > 1 && j < 13 && j > 1 && frame.chess[i + 1][j + 1] == flag
							&& frame.chess[i + 2][j + 2] == flag && frame.chess[i - 1][j - 2] == flag
							&& frame.chess[i - 2][j - 2] == flag) { // 左斜
						this.score[i][j] = this.score[i][j] + 7;
						System.out.println("A111");
					}
					if (i < 13 && i > 1 && j > 1 && j < 13 && frame.chess[i + 1][j - 1] == flag
							&& frame.chess[i + 2][j - 2] == flag && frame.chess[i - 1][j + 1] == flag
							&& frame.chess[i - 2][j + 2] == flag) {// 右斜
						this.score[i][j] = this.score[i][j] + 7;
						System.out.println("A112");
					}
				}
			}
		}
		// **************************************************************************************************************
		// **************************************************************************************************************
		// 4个棋子不相连的情况且在一条直线，若满足10111，则为7分，不满足，则为之前的值
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (frame.chess[i][j] == 0) {
					if (i < 12 && i > 0 && frame.chess[i - 1][j] == flag && frame.chess[i + 1][j] == flag
							&& frame.chess[i + 2][j] == flag && frame.chess[i + 3][j] == flag) { // 横向+
						this.score[i][j] = this.score[i][j] + 7;
						System.out.println("A113");
					}
					if (i > 2 && i < 14 && frame.chess[i + 1][j] == flag && frame.chess[i - 1][j] == flag
							&& frame.chess[i - 2][j] == flag && frame.chess[i - 3][j] == flag) { // 横向-
						this.score[i][j] = this.score[i][j] + 7;
						System.out.println("A114");
					}
					if (j < 12 && j > 0 && frame.chess[i][j - 1] == flag && frame.chess[i][j + 1] == flag
							&& frame.chess[i][j + 2] == flag && frame.chess[i][j + 3] == flag) { // 纵向+
						this.score[i][j] = this.score[i][j] + 7;
						System.out.println("A115");
					}
					if (j > 2 && j < 14 && frame.chess[i][j + 1] == flag && frame.chess[i][j - 1] == flag
							&& frame.chess[i][j - 2] == flag && frame.chess[i][j - 3] == flag) { // 纵向-
						this.score[i][j] = this.score[i][j] + 7;
						System.out.println("A116");
					}
					if (i < 12 && i > 0 && j > 0 && j < 12 && frame.chess[i + 1][j + 1] == flag
							&& frame.chess[i + 2][j + 2] == flag && frame.chess[i + 3][j + 3] == flag
							&& frame.chess[i - 1][j - 1] == flag) { // 左斜+
						this.score[i][j] = this.score[i][j] + 7;
						System.out.println("A119");
					}
					if (i > 2 && i < 14 && j > 2 && j < 14 && frame.chess[i - 1][j - 1] == flag
							&& frame.chess[i - 2][j - 2] == flag && frame.chess[i - 3][j - 3] == flag
							&& frame.chess[i + 1][j + 1] == flag) { // 左斜-
						this.score[i][j] = this.score[i][j] + 7;
						System.out.println("A118");
					} //
					if (i < 12 && i > 0 && j > 2 && j < 14 && frame.chess[i + 1][j - 1] == flag
							&& frame.chess[i + 2][j - 2] == flag && frame.chess[i + 3][j - 3] == flag
							&& frame.chess[i - 1][j + 1] == flag) {// 右斜+
						this.score[i][j] = this.score[i][j] + 7;
						System.out.println("A119");
					}
					if (i < 14 && i > 2 && j > 0 && j < 12 && frame.chess[i - 1][j + 1] == flag
							&& frame.chess[i - 2][j + 2] == flag && frame.chess[i - 3][j + 3] == flag
							&& frame.chess[i + 1][j - 1] == flag) {// 右斜-
						this.score[i][j] = this.score[i][j] + 7;
						System.out.println("A120");
					}
				}
			}
		}
		// **************************************************************************************************************
		// **************************************************************************************************************
		// 4个棋子不相连的情况且不在一条直线，若满足双杀（见图），则为7分，不满足，则为之前的值
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (frame.chess[i][j] == 0) {
					if (i < 13 && j > 1 && frame.chess[i + 1][j] == flag && frame.chess[i + 2][j] == flag
							&& frame.chess[i + 1][j - 1] == flag && frame.chess[i + 2][j - 2] == flag) { // BC
						if (i < 12 && i > 0 && j < 14 && j > 2 && frame.chess[i - 1][j] == 0
								&& frame.chess[i - 1][j + 1] == 0) {
							if (frame.chess[i + 3][j] == 0 && frame.chess[i + 3][j - 3] == 0) {
								this.score[i][j] = this.score[i][j] + 7;
								System.out.println("A121");
							} else if (frame.chess[i + 3][j] != 0 && frame.chess[i + 3][j - 3] == 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A122");
							} else if (frame.chess[i + 3][j] == 0 && frame.chess[i + 3][j - 3] != 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A123");
							} else if (frame.chess[i + 3][j] != 0 && frame.chess[i + 3][j - 3] != 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("A124");
							}
						} else if (i < 12 && i > 0 && j < 14 && j > 2 && frame.chess[i - 1][j] == 0
								&& frame.chess[i - 1][j + 1] != 0) {
							if (frame.chess[i + 3][j] == 0 && frame.chess[i + 3][j - 3] == 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("A125");
							} else if (frame.chess[i + 3][j] != 0 && frame.chess[i + 3][j - 3] == 0) {
								this.score[i][j] = this.score[i][j] + 5;
								System.out.println("A126");
							} else if (frame.chess[i + 3][j] == 0 && frame.chess[i + 3][j - 3] != 0) {
								this.score[i][j] = this.score[i][j] + 5;
								System.out.println("A127");
							} else if (frame.chess[i + 3][j] != 0 && frame.chess[i + 3][j - 3] != 0) {
								this.score[i][j] = this.score[i][j] + 1;
								System.out.println("A128");
							}
						} else if (i < 12 && i > 0 && j < 14 && j > 2 && frame.chess[i - 1][j] != 0
								&& frame.chess[i - 1][j + 1] == 0) {
							if (frame.chess[i + 3][j] == 0 && frame.chess[i + 3][j - 3] == 0) {
								this.score[i][j] = this.score[i][j] + 5;
								System.out.println("A129");
							} else if (frame.chess[i + 3][j] != 0 && frame.chess[i + 3][j - 3] == 0) {
								this.score[i][j] = this.score[i][j] + 5;
								System.out.println("A130");
							} else if (frame.chess[i + 3][j] == 0 && frame.chess[i + 3][j - 3] != 0) {
								this.score[i][j] = this.score[i][j] + 5;
								System.out.println("A131");
							} else if (frame.chess[i + 3][j] != 0 && frame.chess[i + 3][j - 3] != 0) {
								this.score[i][j] = this.score[i][j] + 1;
								System.out.println("A132");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A133");
						}
					}
					if (i > 1 && j > 1 && frame.chess[i - 1][j] == flag && frame.chess[i - 2][j] == flag
							&& frame.chess[i - 1][j - 1] == flag && frame.chess[i - 2][j - 2] == flag) { // GF
						if (i < 14 && i > 2 && j > 2 && j < 14 && frame.chess[i + 1][j] == 0
								&& frame.chess[i + 1][j + 1] == 0) {
							if (frame.chess[i - 3][j] == 0 && frame.chess[i - 3][j - 3] == 0) {
								this.score[i][j] = this.score[i][j] + 7;
								System.out.println("A134");
							} else if (frame.chess[i - 3][j] != 0 && frame.chess[i - 3][j - 3] == 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A135");
							} else if (frame.chess[i - 3][j] == 0 && frame.chess[i - 3][j - 3] != 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A136");
							} else if (frame.chess[i - 3][j] != 0 && frame.chess[i - 3][j - 3] != 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("A137");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A138");
						}
					}
					if (i > 1 && j > 1 && frame.chess[i][j - 1] == flag && frame.chess[i][j - 2] == flag
							&& frame.chess[i - 1][j - 1] == flag && frame.chess[i - 2][j - 2] == flag) { // AG
						if (i < 14 && i > 2 && j > 2 && j < 14 && frame.chess[i + 1][j] == 0
								&& frame.chess[i + 1][j + 1] == 0) {
							if (frame.chess[i - 3][j] == 0 && frame.chess[i - 3][j - 3] == 0) {
								this.score[i][j] = this.score[i][j] + 7;
								System.out.println("A139");
							} else if (frame.chess[i - 3][j] != 0 && frame.chess[i - 3][j - 3] == 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A140");
							} else if (frame.chess[i - 3][j] == 0 && frame.chess[i - 3][j - 3] != 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A141");
							} else if (frame.chess[i - 3][j] != 0 && frame.chess[i - 3][j - 3] != 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("A142");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A143");
						}
					}
					if (i < 13 && j > 1 && frame.chess[i][j - 1] == flag && frame.chess[i][j - 2] == flag
							&& frame.chess[i + 1][j - 1] == flag && frame.chess[i + 2][j - 2] == flag) { // AB
						if (i < 12 && i > 0 && j > 2 && j < 14 && frame.chess[i][j + 1] == 0
								&& frame.chess[i - 1][j + 1] == 0) {
							if (frame.chess[i][j - 3] == 0 && frame.chess[i + 3][j - 3] == 0) {
								this.score[i][j] = this.score[i][j] + 7;
								System.out.println("A145");
							} else if (frame.chess[i][j - 3] != 0 && frame.chess[i + 3][j - 3] == 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A146");
							} else if (frame.chess[i][j - 3] == 0 && frame.chess[i + 3][j - 3] != 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A147");
							} else if (frame.chess[i][j - 3] != 0 && frame.chess[i + 3][j - 3] != 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("A148");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A149");
						}
					}
					if (i > 1 && j < 13 && frame.chess[i - 1][j] == flag && frame.chess[i - 2][j] == flag
							&& frame.chess[i - 1][j + 1] == flag && frame.chess[i - 2][j + 2] == flag) { // FH
						if (i < 14 && i > 2 && j > 0 && j < 12 && frame.chess[i + 1][j] == 0
								&& frame.chess[i + 1][j - 1] == 0) {
							if (frame.chess[i - 3][j] == 0 && frame.chess[i - 3][j + 3] == 0) {
								this.score[i][j] = this.score[i][j] + 7;
								System.out.println("A150");
							} else if (frame.chess[i - 3][j] != 0 && frame.chess[i - 3][j + 3] == 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A151");
							} else if (frame.chess[i - 3][j] == 0 && frame.chess[i - 3][j + 3] != 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A152");
							} else if (frame.chess[i - 3][j] != 0 && frame.chess[i - 3][j + 3] != 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("A153");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A154");
						}
					}
					if (i > 1 && j < 13 && frame.chess[i - 1][j + 1] == flag && frame.chess[i - 2][j + 2] == flag
							&& frame.chess[i][j + 2] == flag && frame.chess[i][j + 2] == flag) { // HE
						if (i < 14 && i > 2 && j > 0 && j < 12 && frame.chess[i][j - 1] == 0
								&& frame.chess[i + 1][j - 1] == 0) {
							if (frame.chess[i][j + 3] == 0 && frame.chess[i - 3][j + 3] == 0) {
								this.score[i][j] = this.score[i][j] + 7;
								System.out.println("A155");
							} else if (frame.chess[i][j + 3] != 0 && frame.chess[i - 3][j + 3] == 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A156");
							} else if (frame.chess[i][j + 3] == 0 && frame.chess[i - 3][j + 3] != 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A157");
							} else if (frame.chess[i][j + 3] != 0 && frame.chess[i - 3][j + 3] != 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("A158");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A159");
						}
					}
					if (i < 13 && j < 13 && frame.chess[i][j + 1] == flag && frame.chess[i][j + 2] == flag
							&& frame.chess[i + 1][j + 1] == flag && frame.chess[i + 2][j + 2] == flag) {// DE
						if (i < 12 && i > 0 && j > 0 && j < 12 && frame.chess[i - 1][j - 1] == 0
								&& frame.chess[i][j - 1] == 0) {
							if (frame.chess[i][j + 3] == 0 && frame.chess[i + 3][j + 3] == 0) {
								this.score[i][j] = this.score[i][j] + 7;
								System.out.println("A160");
							} else if (frame.chess[i][j + 3] != 0 && frame.chess[i + 3][j + 3] == 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A161");
							} else if (frame.chess[i][j + 3] == 0 && frame.chess[i + 3][j + 3] != 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A162");
							} else if (frame.chess[i][j + 3] != 0 && frame.chess[i + 3][j + 3] != 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("A163");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A164");
						}
					}
					if (i < 13 && j < 13 && frame.chess[i + 1][j] == flag && frame.chess[i + 2][j] == flag
							&& frame.chess[i + 1][j + 1] == flag && frame.chess[i + 2][j + 2] == flag) {// CD
						if (i < 12 && i > 0 && j > 0 && j < 12 && frame.chess[i - 1][j] == 0
								&& frame.chess[i - 1][j - 1] == 0) {
							if (frame.chess[i + 3][j] == 0 && frame.chess[i + 3][j + 3] == 0) {
								this.score[i][j] = this.score[i][j] + 7;
								System.out.println("A165");
							} else if (frame.chess[i + 3][j] != 0 && frame.chess[i + 3][j + 3] == 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A166");
							} else if (frame.chess[i + 3][j] == 0 && frame.chess[i + 3][j + 3] != 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A167");
							} else if (frame.chess[i + 3][j] != 0 && frame.chess[i + 3][j + 3] != 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("A168");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A169");
						}
					}
					if (i < 13 && j > 1 && frame.chess[i + 1][j] == flag && frame.chess[i + 2][j] == flag
							&& frame.chess[i][j - 1] == flag && frame.chess[i + 2][j - 2] == flag) {// AC
						if (i < 12 && i > 0 && j > 2 && j < 14 && frame.chess[i - 1][j] == 0
								&& frame.chess[i][j + 1] == 0) {
							if (frame.chess[i][j - 3] == 0 && frame.chess[i + 3][j] == 0) {
								this.score[i][j] = this.score[i][j] + 7;
								System.out.println("A170");
							} else if (frame.chess[i][j - 3] != 0 && frame.chess[i + 3][j] == 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A171");
							} else if (frame.chess[i][j - 3] == 0 && frame.chess[i + 3][j] != 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A172");
							} else if (frame.chess[i][j - 3] != 0 && frame.chess[i + 3][j] != 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("A173");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A174");
						}
					}
					if (i < 13 && i > 1 && j > 1 && frame.chess[i + 1][j - 1] == flag
							&& frame.chess[i + 2][j - 2] == flag && frame.chess[i - 1][j - 1] == flag
							&& frame.chess[i - 2][j - 2] == flag) {// BG
						if (i < 12 && i > 2 && j > 2 && j < 14 && frame.chess[i + 1][j + 1] == 0
								&& frame.chess[i - 1][j + 1] == 0) {
							if (frame.chess[i - 3][j - 3] == 0 && frame.chess[i + 3][j - 3] == 0) {
								this.score[i][j] = this.score[i][j] + 7;
								System.out.println("A175");
							} else if (frame.chess[i - 3][j - 3] != 0 && frame.chess[i + 3][j - 3] == 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A176");
							} else if (frame.chess[i - 3][j - 3] == 0 && frame.chess[i + 3][j - 3] != 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A177");
							} else if (frame.chess[i - 3][j - 3] != 0 && frame.chess[i + 3][j - 3] != 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("A178");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A179");
						}
					}
					if (i > 1 && j > 1 && frame.chess[i][j - 1] == flag && frame.chess[i][j - 2] == flag
							&& frame.chess[i - 1][j] == flag && frame.chess[i - 2][j] == flag) {// AF
						if (i < 14 && i > 2 && j > 2 && j < 14 && frame.chess[i][j + 1] == 0
								&& frame.chess[i + 1][j] == 0) {
							if (frame.chess[i][j - 3] == 0 && frame.chess[i - 3][j] == 0) {
								this.score[i][j] = this.score[i][j] + 7;
								System.out.println("A180");
							} else if (frame.chess[i][j - 3] != 0 && frame.chess[i - 3][j] == 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A181");
							} else if (frame.chess[i][j - 3] == 0 && frame.chess[i - 3][j] != 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A182");
							} else if (frame.chess[i][j - 3] != 0 && frame.chess[i - 3][j] != 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("A183");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A184");
						}
					}
					if (i > 1 && j > 1 && j < 13 && frame.chess[i - 1][j - 1] == flag
							&& frame.chess[i - 1][j - 2] == flag && frame.chess[i - 1][j + 1] == flag
							&& frame.chess[i - 2][j + 2] == flag) {// GH
						if (i < 14 && i > 2 && j > 2 && j < 12 && frame.chess[i + 1][j + 1] == 0
								&& frame.chess[i + 1][j - 1] == 0) {
							if (frame.chess[i - 3][j - 3] == 0 && frame.chess[i - 3][j + 3] == 0) {
								this.score[i][j] = this.score[i][j] + 7;
								System.out.println("A185");
							} else if (frame.chess[i - 3][j - 3] != 0 && frame.chess[i - 3][j + 3] == 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A186");
							} else if (frame.chess[i - 3][j - 3] == 0 && frame.chess[i - 3][j + 3] != 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A187");
							} else if (frame.chess[i - 3][j - 3] != 0 && frame.chess[i - 3][j + 3] != 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("A188");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A189");
						}
					}
					if (i > 1 && j > 1 && j < 13 && frame.chess[i - 1][j] == flag && frame.chess[i - 2][j] == flag
							&& frame.chess[i][j + 1] == flag && frame.chess[i][j + 2] == flag) {// FE
						if (i < 14 && i > 2 && j < 12 && frame.chess[i][j + 1] == 0 && frame.chess[i + 1][j] == 0) {
							if (frame.chess[i][j + 3] == 0 && frame.chess[i - 3][j] == 0) {
								this.score[i][j] = this.score[i][j] + 7;
								System.out.println("A190");
							} else if (frame.chess[i][j + 3] != 0 && frame.chess[i - 3][j] == 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A191");
							} else if (frame.chess[i][j + 3] == 0 && frame.chess[i - 3][j] != 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A192");
							} else if (frame.chess[i][j + 3] != 0 && frame.chess[i - 3][j] != 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("A193");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A194");
						}
					}
					if (i > 1 && i < 13 && j > 1 && frame.chess[i - 1][j - 1] == flag
							&& frame.chess[i - 2][j - 2] == flag && frame.chess[i + 1][j - 1] == flag
							&& frame.chess[i + 2][j - 2] == flag) {// HD
						if (i < 14 && i > 2 && j > 0 && j < 12 && frame.chess[i - 1][j - 1] == 0
								&& frame.chess[i + 1][j - 1] == 0) {
							if (frame.chess[i - 3][j + 3] == 0 && frame.chess[i + 3][j + 3] == 0) {
								this.score[i][j] = this.score[i][j] + 7;
								System.out.println("A195");
							} else if (frame.chess[i - 3][j + 3] != 0 && frame.chess[i + 3][j + 3] == 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A196");
							} else if (frame.chess[i - 3][j + 3] == 0 && frame.chess[i + 3][j + 3] != 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A197");
							} else if (frame.chess[i - 3][j + 3] != 0 && frame.chess[i + 3][j + 3] != 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("A198");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A199");
						}
					}
					if (i < 13 && j < 13 && frame.chess[i + 1][j] == flag && frame.chess[i + 2][j] == flag
							&& frame.chess[i][j + 1] == flag && frame.chess[i][j + 2] == flag) {// CE
						if (i < 12 && i > 0 && j > 0 && j < 12 && frame.chess[i - 1][j] == 0
								&& frame.chess[i][j - 1] == 0) {
							if (frame.chess[i][j + 3] == 0 && frame.chess[i + 3][j] == 0) {
								this.score[i][j] = this.score[i][j] + 7;
								System.out.println("A200");
							} else if (frame.chess[i][j + 3] != 0 && frame.chess[i + 3][j] == 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A201");
							} else if (frame.chess[i][j + 3] == 0 && frame.chess[i + 3][j] != 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A202");
							} else if (frame.chess[i][j + 3] != 0 && frame.chess[i + 3][j] != 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("A203");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A205");
						}
					}
					if (i < 13 && j > 1 && j < 13 && frame.chess[i + 1][j - 1] == flag
							&& frame.chess[i + 2][j - 2] == flag && frame.chess[i + 1][j + 1] == flag
							&& frame.chess[i + 2][j + 2] == flag) {// DB
						if (i < 12 && i > 0 && j > 2 && j < 12 && frame.chess[i - 1][j + 1] == 0
								&& frame.chess[i - 1][j - 1] == 0) {
							if (frame.chess[i + 3][j - 3] == 0 && frame.chess[i + 3][j + 3] == 0) {
								this.score[i][j] = this.score[i][j] + 7;
								System.out.println("A206");
							} else if (frame.chess[i + 3][j - 3] != 0 && frame.chess[i + 3][j + 3] == 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A207");
							} else if (frame.chess[i + 3][j - 3] == 0 && frame.chess[i + 3][j + 3] != 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A208");
							} else if (frame.chess[i + 3][j - 3] != 0 && frame.chess[i + 3][j + 3] != 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("A209");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A210");
						}
					}
					if (i < 13 && i > 1 && j > 1 && frame.chess[i + 1][j] == flag && frame.chess[i + 2][j] == flag
							&& frame.chess[i - 1][j - 1] == flag && frame.chess[i - 2][j - 2] == flag) {// CG
						if (i < 12 && i > 2 && j > 2 && frame.chess[i + 1][j + 1] == 0 && frame.chess[i - 1][j] == 0) {
							if (frame.chess[i + 3][j] == 0 && frame.chess[i - 3][j - 3] == 0) {
								this.score[i][j] = this.score[i][j] + 7;
								System.out.println("A211");
							} else if (frame.chess[i + 3][j] != 0 && frame.chess[i - 3][j - 3] == 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A212");
							} else if (frame.chess[i + 3][j] == 0 && frame.chess[i - 3][j - 3] != 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A213");
							} else if (frame.chess[i + 3][j] != 0 && frame.chess[i - 3][j - 3] != 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("A214");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A215");
						}
					}
					if (i < 13 && i > 1 && j > 1 && frame.chess[i + 1][j] == flag && frame.chess[i + 2][j] == flag
							&& frame.chess[i - 1][j - 1] == flag && frame.chess[i - 2][j - 2] == flag) {// BF
						if (i < 12 && i > 2 && j > 2 && frame.chess[i + 1][j + 1] == 0 && frame.chess[i - 1][j] == 0) {
							if (frame.chess[i + 3][j] == 0 && frame.chess[i - 3][j - 3] == 0) {
								this.score[i][j] = this.score[i][j] + 7;
								System.out.println("A216");
							} else if (frame.chess[i + 3][j] != 0 && frame.chess[i - 3][j - 3] == 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A217");
							} else if (frame.chess[i + 3][j] == 0 && frame.chess[i - 3][j - 3] != 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A218");
							} else if (frame.chess[i + 3][j] != 0 && frame.chess[i - 3][j - 3] != 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("A219");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A220");
						}
					}
					if (j < 13 && i > 1 && j > 1 && frame.chess[i][j - 1] == flag && frame.chess[i][j - 2] == flag
							&& frame.chess[i - 1][j + 1] == flag && frame.chess[i - 2][j + 2] == flag) {// AH
						if (i < 14 && i > 2 && j > 2 && j < 12 && frame.chess[i][j + 1] == 0
								&& frame.chess[i + 1][j - 1] == 0) {
							if (frame.chess[i][j - 3] == 0 && frame.chess[i - 3][j + 3] == 0) {
								this.score[i][j] = this.score[i][j] + 7;
								System.out.println("A221");
							} else if (frame.chess[i][j - 3] != 0 && frame.chess[i - 3][j + 3] == 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A222");
							} else if (frame.chess[i][j - 3] == 0 && frame.chess[i - 3][j + 3] != 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A223");
							} else if (frame.chess[i][j - 3] != 0 && frame.chess[i - 3][j + 3] != 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("A224");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A225");
						}
					}
					if (j < 13 && i > 1 && j > 1 && frame.chess[i][j + 1] == flag && frame.chess[i][j + 2] == flag
							&& frame.chess[i - 1][j - 1] == flag && frame.chess[i - 2][j - 2] == flag) {// GE
						if (i < 14 && i > 2 && j > 2 && j < 12 && frame.chess[i][j - 1] == 0
								&& frame.chess[i + 1][j + 1] == 0) {
							if (frame.chess[i][j + 3] == 0 && frame.chess[i - 3][j - 3] == 0) {
								this.score[i][j] = this.score[i][j] + 7;
								System.out.println("A226");
							} else if (frame.chess[i][j + 3] != 0 && frame.chess[i - 3][j - 3] == 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A227");
							} else if (frame.chess[i][j + 3] == 0 && frame.chess[i - 3][j - 3] != 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A228");
							} else if (frame.chess[i][j + 3] != 0 && frame.chess[i - 3][j - 3] != 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("A229");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A230");
						}
					}
					if (j < 13 && i > 1 && i < 13 && frame.chess[i - 1][j] == flag && frame.chess[i - 2][j] == flag
							&& frame.chess[i + 1][j + 1] == flag && frame.chess[i + 2][j + 2] == flag) {// FD
						if (i < 12 && i > 0 && j > 0 && j < 12 && frame.chess[i + 1][j] == 0
								&& frame.chess[i - 1][j - 1] == 0) {
							if (frame.chess[i + 3][j] == 0 && frame.chess[i + 3][j + 3] == 0) {
								this.score[i][j] = this.score[i][j] + 7;
								System.out.println("A231");
							} else if (frame.chess[i + 3][j] != 0 && frame.chess[i + 3][j + 3] == 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A232");
							} else if (frame.chess[i + 3][j] == 0 && frame.chess[i + 3][j + 3] != 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A233");
							} else if (frame.chess[i + 3][j] != 0 && frame.chess[i + 3][j + 3] != 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("A234");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A235");
						}
					}
					if (j < 13 && i > 1 && i < 13 && frame.chess[i + 1][j] == flag && frame.chess[i + 2][j] == flag
							&& frame.chess[i - 1][j + 1] == flag && frame.chess[i - 2][j + 2] == flag) {// HC
						if (i < 12 && i > 0 && j > 0 && j < 12 && frame.chess[i - 1][j] == 0
								&& frame.chess[i + 1][j - 1] == 0) {
							if (frame.chess[i + 3][j] == 0 && frame.chess[i - 3][j + 3] == 0) {
								this.score[i][j] = this.score[i][j] + 7;
								System.out.println("A236");
							} else if (frame.chess[i + 3][j] != 0 && frame.chess[i - 3][j + 3] == 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A23");
							} else if (frame.chess[i + 3][j] == 0 && frame.chess[i - 3][j + 3] != 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A238");
							} else if (frame.chess[i + 3][j] != 0 && frame.chess[i - 3][j + 3] != 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("A239");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A240");
						}
					}
					if (j < 13 && j > 1 && i < 13 && frame.chess[i][j + 1] == flag && frame.chess[i][j + 2] == flag
							&& frame.chess[i + 1][j - 1] == flag && frame.chess[i + 2][j - 2] == flag) {// EB
						if (i < 12 && i > 0 && j > 2 && j < 14 && frame.chess[i][j - 1] == 0
								&& frame.chess[i - 1][j + 1] == 0) {
							if (frame.chess[i][j + 3] == 0 && frame.chess[i + 3][j - 3] == 0) {
								this.score[i][j] = this.score[i][j] + 7;
								System.out.println("A241");
							} else if (frame.chess[i][j + 3] != 0 && frame.chess[i + 3][j - 3] == 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A242");
							} else if (frame.chess[i][j + 3] == 0 && frame.chess[i + 3][j - 3] != 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A243");
							} else if (frame.chess[i][j + 3] != 0 && frame.chess[i + 3][j - 3] != 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("A244");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A245");
						}
					}
					if (j < 13 && j > 1 && i < 13 && frame.chess[i][j - 1] == flag && frame.chess[i][j - 2] == flag
							&& frame.chess[i + 1][j + 1] == flag && frame.chess[i + 2][j + 2] == flag) {// AD
						if (i < 12 && i > 0 && j > 2 && j < 12 && frame.chess[i][j + 1] == 0
								&& frame.chess[i - 1][j - 1] == 0) {
							if (frame.chess[i][j - 3] == 0 && frame.chess[i + 3][j + 3] == 0) {
								this.score[i][j] = this.score[i][j] + 7;
								System.out.println("A246");
							} else if (frame.chess[i][j - 3] != 0 && frame.chess[i + 3][j + 3] == 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A2");
							} else if (frame.chess[i][j - 3] == 0 && frame.chess[i + 3][j + 3] != 0) {
								this.score[i][j] = this.score[i][j] + 6;
								System.out.println("A248");
							} else if (frame.chess[i][j - 3] != 0 && frame.chess[i + 3][j + 3] != 0) {
								this.score[i][j] = this.score[i][j] + 2;
								System.out.println("A249");
							}
						} else {
							this.score[i][j] = this.score[i][j] + 1;
							System.out.println("A250");
						}
					}
					if (j < 13 && j > 1 && i > 1 && i < 13 && frame.chess[i + 1][j - 1] == flag
							&& frame.chess[i + 2][j - 2] == flag && frame.chess[i - 1][j + 1] == flag
							&& frame.chess[i - 2][j + 2] == flag) {// BH
						this.score[i][j] = this.score[i][j] + 7;
						System.out.println("A251");
					}
					if (j < 13 && j > 1 && frame.chess[i][j - 1] == flag && frame.chess[i][j - 2] == flag
							&& frame.chess[i][j + 1] == flag && frame.chess[i][j + 2] == flag) {// AE
						this.score[i][j] = this.score[i][j] + 7;
						System.out.println("A252");
					}
					if (i < 13 && i > 1 && j < 13 && j > 1 && frame.chess[i - 1][j - 1] == flag
							&& frame.chess[i - 2][j - 2] == flag && frame.chess[i + 1][j + 1] == flag
							&& frame.chess[i + 2][j + 2] == flag) {// AE
						this.score[i][j] = this.score[i][j] + 7;
						System.out.println("A253");
					}
				}
			}
		}
	}

	// **************************************************************************************************************
	// 功能:计算每种颜色的最高分，并且记录下坐标
	public int MaxScore(int score[][]) {
		int max = 0;
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (max < score[i][j]) {
					max = score[i][j];
					this.x = i;
					this.y = j;
				}
			}
		}
		return max;
	}

	// **************************************************************************************************************
	public void AutoSelect(MainFrame frame) {
		int c;
		int p;
		c = MaxScore(scorec);
		System.out.println("电脑棋盘最高得分：" + c);
		p = MaxScore(scorep);
		System.out.println("玩家棋盘最高得分：" + p);
		if (frame.pcfirst == 1) {// 电脑先行
			if (c >= p) {
				MaxScore(scorec);
				frame.chess[this.x][this.y] = 1;
				frame.store.push(new Point(this.x, this.y, 1)); // 人机：将棋子压入栈
			} else {
				MaxScore(scorep);
				frame.chess[this.x][this.y] = 1;
				frame.store.push(new Point(this.x, this.y, 1)); // 人机：将棋子压入栈
			}
		}
		if (frame.pcfirst == 3) {// 用户先行
			if (p >= c) {
				MaxScore(scorep);
				frame.chess[this.x][this.y] = 1;
				frame.store.push(new Point(this.x, this.y, 1)); // 人机：将棋子压入栈
			} else {
				MaxScore(scorec);
				frame.chess[this.x][this.y] = 1;
				frame.store.push(new Point(this.x, this.y, 1)); // 人机：将棋子压入栈
			}
		}
		if (p == 0 && c == 0) {
			Trysecond(frame);
			Second(frame);
		}
	}

	public void copy(int a[][], int b[][]) {
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				b[i][j] = a[i][j];
			}
		}
	}

	public void fill(int a[][], int val) {
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				a[i][j] = val;
			}
		}
	}

	public void RandomFirst() {
		this.state[0] = (int) (1 + Math.random() * (7 - 1 + 1));
	}
}