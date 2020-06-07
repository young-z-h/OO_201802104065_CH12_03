//导入swing包

import javax.swing.*;
import java.awt.*;

//导入awt包

public class ImgHelper {
    /**
     * @param filename 文件有名
     * @return Image对象
     */
    public static Image getImage(String filename) {
        ImageIcon imageIcon = new ImageIcon(filename);
        Image image = imageIcon.getImage();
        return image;
    }

}