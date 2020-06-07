//201802104065闫天意
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class ControlPanel extends JPanel{	
	private Controllable controllable;

	public Controllable getToMove() {
		return this.controllable;
	}

	public void setToMove(Controllable toMove) {
		this.controllable = toMove;
	}

	public ControlPanel(Controllable toMove) {
		this.controllable = toMove;
		addButtons(toMove);
	}

	private void addButtons(Controllable toMove) {

		JButton moveLeftBtn = new JButton("左移");
		this.add(moveLeftBtn);
		MoveLeftHandler moveLeftHandler = new MoveLeftHandler();
		moveLeftBtn.addActionListener(moveLeftHandler);

		/**
		JButton  refreshBtn = new JButton("刷新");
		this.add(refreshBtn);
		RefreshHandler refreshHandler = new RefreshHandler();
		refreshBtn.addActionListener(refreshHandler);
		 */

		JButton closeBtn = new JButton("退出");
		this.add(closeBtn);
		CloseFrameHandler closeFrameHandler = new CloseFrameHandler();
		closeBtn.addActionListener(closeFrameHandler);
	}


	private class MoveLeftHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			controllable.moveLeft();
			((MyFrame)(ControlPanel.this.getRootPane().getParent())).getDrawingPanelForArray().repaint();
		}
	}

	private class RefreshHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			((MyFrame)(ControlPanel.this.getRootPane().getParent())).getDrawingPanelForArray().repaint();
		}
	}

	private class CloseFrameHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}		
	}		
}
