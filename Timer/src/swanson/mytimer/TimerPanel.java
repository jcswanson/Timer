/*
File name: TimerPanel.java
Short description:
IST 261 Assignment:
@author jcswa
@version 1.01 Dec 29, 2020
 */
package swanson.mytimer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TimerPanel extends JPanel implements Runnable {

    private static final long serialVersionUID = 1L;
// Instance Variables -- define your private data
    private static final String ALARM_FILE = "../alarm.wav";
    private int width = 150;
    private int height = 24;
    private String timeString = "00:00:00";
    private long time = 10;
    private Thread timerThread;
// Constructors
    public TimerPanel(long time, Font font) {
        setTime(time);
        setFont(font);
        height = font.getSize();
        FontMetrics fm = getFontMetrics(font);
        width = fm.stringWidth(timeString);
        
    }
    public void run(){
        while(time > 0){
            time -= 1;
            setTime(time);
            
            try{
                Thread.sleep(1000);
            }
            catch(InterruptedException ie){
                return;
            }
        }
        timesUp();
    }
    public void setTime(long time){
        this.time = time;
        long h = time / 3600;
        long m = (time / 60) % 60;
        long s = time % 60;
        timeString = String.format("%02d:%02d:%02d", h, m, s);
        repaint();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.drawString(timeString, 0, height);
    }
    @Override
    public Dimension getPreferredSize(){
        Dimension size = new Dimension(width, height);
        return size;
    }
    public long getTime(){
        return time;
    }
    public void start(){
        stop();
        timerThread = new Thread(this);
        timerThread.start();
    }
    public void stop(){
        if(timerThread != null){                  
            timerThread.interrupt();
            timerThread = null;
        }
    }
    protected void timesUp(){
        try{
            URL url = getClass().getResource(ALARM_FILE);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            String message = "Time is up!";
            JOptionPane.showMessageDialog(this, message);
        }
        catch(IOException ioe){
            String message = "Audio File cannot be opened.";
            JOptionPane.showMessageDialog(this, message);
        }
        catch(UnsupportedAudioFileException uafe){
            String message = "Audio File is not valid.";
            JOptionPane.showMessageDialog(this, message);
        }
        catch(LineUnavailableException lue){
            String message = "No resource available to open audio file.";
            JOptionPane.showMessageDialog(this, message);
        }
        
    }
}
