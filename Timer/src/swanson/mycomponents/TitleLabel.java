/*
File name: TitleLabel.java
Short description:
IST 261 Assignment:
@author jcswa
@version 1.01 Dec 29, 2020
*/

package swanson.mycomponents;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class TitleLabel extends JLabel {

    private static final long serialVersionUID = 1L;

    public TitleLabel(String title) {
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 32);
        Color purple = Color.decode("#3c0000");
        Color lime = Color.decode("#FDFFFC");
        setFont(font);
        setBackground(purple);
        setForeground(lime);
        setOpaque(true);
        setHorizontalAlignment(JLabel.CENTER);
        setText(title);
    }
}
