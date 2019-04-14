package wu.zi.qi;

import java.util.LinkedList;

/**
 * 为悔棋建立的栈
 */
public class MyStack {
	// <Object>表示LinkedList只能装point
	private LinkedList<Point> Rechess = new LinkedList<Point>();

	public void push(Point o) {
		Rechess.addFirst(o);
	}

	public Point pop() {
		return Rechess.removeFirst();
	}

	public Point peek() {
		return Rechess.getFirst();
	}

	public boolean empty() {
		return Rechess.isEmpty();
	}

	public void clear() {
		Rechess.clear();
	}

}
