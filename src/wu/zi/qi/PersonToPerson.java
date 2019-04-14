package wu.zi.qi;

/**
 * 双人对战模式
 */
public class PersonToPerson {
	MainFrame frame = null;

	public void lounchpp(MainFrame frame) {
		int flag;
		Tip tip = new Tip();
		// **********************************************************************
		// 1黑方胜情形：A--------->B
		for (flag = 1; flag <= 2; flag++) {
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 15; j++) {
					if (frame.chess[i][j] == flag && frame.chess[i + 1][j] == flag && frame.chess[i + 2][j] == flag
							&& frame.chess[i + 3][j] == flag && frame.chess[i + 4][j] == flag) { // 黑方胜情形
						tip.win(frame, flag);
						tip.Overgameline(frame, i, j, i + 4, j);

						frame.Iswin = true;
					}
				}
			}
		}
		// ******************************************************************
		// 2 黑方胜情形：A--------->C
		for (flag = 1; flag <= 2; flag++) {
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if (frame.chess[i][j] == flag && frame.chess[i + 1][j + 1] == flag
							&& frame.chess[i + 2][j + 2] == flag && frame.chess[i + 3][j + 3] == flag
							&& frame.chess[i + 4][j + 4] == flag) { // 黑方胜情形
						tip.win(frame, flag);
						tip.Overgameline(frame, i, j, i + 4, j + 4);
						frame.Iswin = true;
					}
				}
			}
		}
		// ******************************************************************
		// 3 黑方胜情形：A--------->D
		for (flag = 1; flag <= 2; flag++) {
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 10; j++) {
					if (frame.chess[i][j] == flag && frame.chess[i][j + 1] == flag && frame.chess[i][j + 2] == flag
							&& frame.chess[i][j + 3] == flag && frame.chess[i][j + 4] == flag) { // 黑方胜情形
						tip.win(frame, flag);
						tip.Overgameline(frame, i, j, i, j + 4);
						frame.Iswin = true;
					}
				}
			}
		}
		// ******************************************************************
		// 4 黑方胜情形：D--------->B
		for (flag = 1; flag <= 2; flag++) {
			for (int i = 4; i < 15; i++) {
				for (int j = 0; j < 10; j++) {
					if (frame.chess[i][j] == flag && frame.chess[i - 1][j + 1] == flag
							&& frame.chess[i - 2][j + 2] == flag && frame.chess[i - 3][j + 3] == flag
							&& frame.chess[i - 4][j + 4] == flag) { // 黑方胜情形
						tip.win(frame, flag);
						tip.Overgameline(frame, i, j, i - 4, j + 4);
						frame.Iswin = true;
					}
				}
			}
		}
		// ******************************************************************
	}
}
