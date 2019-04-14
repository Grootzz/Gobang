package wu.zi.qi;

import java.awt.Color;

/**
 * 棋盘的相关设置
 * 
 */
public class Set {
	MainFrame frame = null;

	public void setqipantbase(MainFrame frame) {
		frame.getGraphics().setColor(Color.BLACK);
		frame.getGraphics().drawLine(16, 39, 658, 39);
		frame.getGraphics().drawLine(16, 40, 658, 40);// A---B
		frame.getGraphics().drawLine(16, 677, 658, 677);
		frame.getGraphics().drawLine(16, 678, 658, 678);// C---D
		frame.getGraphics().drawLine(16, 40, 16, 678);
		frame.getGraphics().drawLine(17, 40, 17, 678);// A---D
		frame.getGraphics().drawLine(657, 40, 657, 677);
		frame.getGraphics().drawLine(658, 40, 658, 678);// B---C

		// 绘制棋盘中的小点
		frame.getGraphics().setColor(Color.BLACK);
		frame.getGraphics().fillOval(134, 157, 6, 6);
		frame.getGraphics().fillOval(534, 157, 6, 6);
		frame.getGraphics().fillOval(134, 557, 6, 6);
		frame.getGraphics().fillOval(534, 557, 6, 6);
		frame.getGraphics().fillOval(334, 357, 6, 6);

	}
}
