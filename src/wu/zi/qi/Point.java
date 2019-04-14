package wu.zi.qi;

/**
 * 封装一个棋子的信息
 */
public class Point {
	private int x;
	private int y;
	private int flag;

	public Point(int x, int y, int flag) {
		this.x = x;
		this.y = y;
		this.flag = flag;
	}

	public int getx() {
		return this.x;
	}

	public int gety() {
		return this.y;
	}

	public int getflag() {
		return this.flag;
	}

}
