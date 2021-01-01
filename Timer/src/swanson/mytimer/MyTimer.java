/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swanson.mytimer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import swanson.mycomponents.*;

/**
 *
 * @author jcswa
 */
public class MyTimer extends JFrame {

    private static final long serialVersionUID = 1L;
    private Font font = new Font(Font.DIALOG, Font.BOLD, 36);
    private TimerPanel timerPanel = new TimerPanel(0, font);
    private Color start = Color.decode("#32DE8A");
    private Color stop = Color.decode("#D8315B");
    private Color blue = Color.decode("#B8C5D6");
    private Color crayon = Color.decode("#335C81");
    private Color lime = Color.decode("#EDF67D");
    private Color yellowlight = Color.decode("#FFE66D");
   public MyTimer(){
       initGUI();
       setTitle("Timer");
       setResizable(false);
       this.pack();
       setLocationRelativeTo(null);
       setVisible(true);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       
   }
   private void initGUI(){
       TitleLabel titleLabel = new TitleLabel("My Timer");
       add(titleLabel, BorderLayout.PAGE_START);
       JPanel centerPanel = new JPanel();
       add(centerPanel, BorderLayout.CENTER);
       centerPanel.add(timerPanel);
       JPanel buttonPanel = new JPanel();
       add(buttonPanel, BorderLayout.PAGE_END);
       centerPanel.setBackground(lime);
 
       timerPanel.setBackground(lime);
       buttonPanel.setBackground(Color.black);
       
       // Start button
       JButton startButton = new JButton("Start");
       startButton.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e) {
               start();
           }
       
   });
       buttonPanel.add(startButton);
       
       // Stop button
       JButton stopButton = new JButton("Stop");
       stopButton.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e) {
               stop();
           }
           
       });
       buttonPanel.add(stopButton);
       
       // Hour button
       JButton hoursButton = new JButton("Hour");
       hoursButton.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e) {
              addAnHour();
           }
           
       });
       buttonPanel.add(hoursButton);
       
       // Minutes button
       JButton minutesButton = new JButton("Min");
       minutesButton.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e) {
               addAMinute();
           }
           
       });
       buttonPanel.add(minutesButton);
       
       // Clear button
       JButton clearButton = new JButton("Clear");
       clearButton.addActionListener(new ActionListener(){

           @Override
           public void actionPerformed(ActionEvent e) {
               clear();
           }
       });
       buttonPanel.add(clearButton);
       
       hoursButton.setBackground(blue);
       minutesButton.setBackground(blue);
       clearButton.setBackground(yellowlight);
       stopButton.setBackground(stop);
       startButton.setBackground(start);
}
   private void addAnHour(){
       long time = timerPanel.getTime();
       time += 3600;
       timerPanel.setTime(time);
   }
   private void addAMinute(){
       long time = timerPanel.getTime();
       time += 60;
       timerPanel.setTime(time);
   }
   private void clear(){
       timerPanel.stop();
       timerPanel.setTime(0);
   }
   
   private void stop(){
       timerPanel.stop();
   }
   private void start(){
       timerPanel.start();
   }
    public static void main(String[] args) {
        try{
            String className = UIManager.getCrossPlatformLookAndFeelClassName();
            UIManager.setLookAndFeel(className);
        }
        catch(Exception e){
        
        }
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                new MyTimer();
            }
            
        });
    }
    
}
