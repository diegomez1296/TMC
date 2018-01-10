/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TMC.Classes;

import TMC.TMC_Application;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author diegomez
 */
public class Author {

    private JPanel authorPanel;
    private JLabel authorLabel;

    public Author(String iconPath, Point authorPanelLocation, JPanel panel) {

        authorPanel = new JPanel();
        authorLabel = new JLabel();
        TMC.TMC_Application.setComponentSettings(authorPanel, "", "", new Color(170, 170, 170).getRGB(), new Dimension(158, 158), authorPanelLocation, panel, true);
        TMC.TMC_Application.setComponentSettings(authorLabel, "", iconPath, 0, new Dimension(150, 150), new Point(4, 4), authorPanel, true);
        
    }

    public void authorsListener(String fullName, JLabel descriptionLabel, String url_FB, String url_Git) {
        authorLabel.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {

                showAuthorInBrowser(url_FB);
                showAuthorInBrowser(url_Git);
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {

                descriptionLabel.setText(fullName);
                authorPanel.setBackground(new Color(192, 192, 192));

            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                descriptionLabel.setText("TMC Team");
                authorPanel.setBackground(new Color(170, 170, 170));
            }
        });
    }

    private void showAuthorInBrowser(String url) {
        Runtime rt = Runtime.getRuntime();
        try {
            rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
        } catch (Exception e) {
        }
    }

}
