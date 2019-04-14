package wu.zi.qi;

import java.util.LinkedList;

/**
 * Ϊ���彨����ջ
 */
public class MyStack {
	// <Object>��ʾLinkedListֻ��װpoint
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
