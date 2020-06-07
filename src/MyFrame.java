import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public final class MyFrame extends JFrame {
    private DrawingPanelForArray drawingPanelForArray;

    // ��ʼ��
    public MyFrame(DrawingPanelForArray drawingPanelForArray,
                   ControlPanel controlPanel) {
        super("̹面向对象程序设计实验平台 by 佀同光");
        this.drawingPanelForArray = drawingPanelForArray;
        // ����Panel��ӿ��Frame��
        this.add(drawingPanelForArray);
        // BorderLayout.SOUTH��ʾ���������Frame�е�λ��
        this.add(controlPanel, BorderLayout.SOUTH);
        this.setGui();
    }

    public MyFrame(DrawingPanelForArray drawingPanelForArray) {
        super("̹面向对象程序设计实验平台 by 佀同光");
        this.drawingPanelForArray = drawingPanelForArray;
        // ����Panel��ӿ��Frame��
        this.add(drawingPanelForArray);
        this.setGui();
    }


    private void setGui() {
        // ʹframe�ɼ�
        this.setVisible(true);
        // ������ڵĺ��󣬲��������ر�
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        // ���ü���������������󣬻ᵯ�����д������õ�һ��С�Ի���
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(MyFrame.this,
                        "确认退出吗", "提示", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    // Ϊ������keyControlPanel��frame�еõ�drawingPanel��׼��
    public DrawingPanelForArray getDrawingPanelForArray() {
        return this.drawingPanelForArray;
    }
}
