package LeetCode.MerryChristmas;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.swing.*;

public class MyPanel extends JPanel implements ActionListener{
    int x, y ;		//坐标
    JButton onOff ;		//开关按钮
    Timer time ;		//触发时间事件
    boolean flag ;		//判断是开还是关
    boolean color ;		//控制动画
    File file = new File("music.wav") ;
    URL url = null;
    URI uri = null ;
    AudioClip clip = null;
    MyPanel() {
        setLayout(null);
        ImageIcon icon = new ImageIcon("OFF.png");
        icon.setImage(icon.getImage().getScaledInstance(50,50, 0)) ;	//压缩图片大小
        onOff = new JButton() ;
        onOff.addActionListener(this) ;
        onOff.setIcon(icon) ;					//添加按钮图片
        onOff.setBorder(null) ;					//去除边框
        onOff.setContentAreaFilled(false) ;		//去除默认背景颜色
        onOff.setBounds(0, 0, 50, 50) ;
        add(onOff) ;
        flag = true ;
        color = true ;
        time = new Timer(300,this) ;
        time.stop() ;
        try {
            uri=file.toURI();
            url = uri.toURL() ;
        }
        catch (MalformedURLException e1) {}
        clip=Applet.newAudioClip(url);
    }
    public void paintComponent(Graphics g) {
        x = 380 ;
        y = 100 ;
        if(color) {
            ImageIcon image1 = new ImageIcon("2.png") ;
            g.drawImage(image1.getImage(), x-3, y-25, 28, 26, null) ;
        }
        else {
            ImageIcon image1 = new ImageIcon("1.png") ;
            g.drawImage(image1.getImage(), x-3, y-25, 25, 25, null) ;
        }
        Color red = new Color(255, 0, 0) ;
        Color yellow = new Color(255, 241, 0) ;
        drawTree(1, 4, g) ;		//画第一个三角形
        if(color) {
            drawDecoration(x+22, y-44, 6, yellow, g);	//画第一个三角形的黄色装饰
            drawDecoration(x, y-22, 8, red, g);		//画第一个三角形的红色装饰
        }
        else {
            drawDecoration(x+22, y-44, 6, red, g);	//画第一个三角形的黄色装饰
            drawDecoration(x, y-22, 8, yellow, g);		//画第一个三角形的红色装饰
        }
        x = 380-2*22;
        drawTree(3, 6, g) ;		//画第二个三角形
        if(color) {
            drawDecoration(x+22, y-44, 10, yellow, g);	//画第二个三角形的黄色装饰
            drawDecoration(x, y-22, 12, red, g);		//画第二个三角形的红色装饰
        }
        else {
            drawDecoration(x+22, y-44, 10, red, g);	//画第二个三角形的黄色装饰
            drawDecoration(x, y-22, 12, yellow, g);		//画第二个三角形的红色装饰
        }
        x = 380-4*22;
        drawTree(5, 8, g) ;		//画第三个三角形
        if(color) {
            drawDecoration(x+22, y-44, 14, yellow, g);	//画第三个三角形的黄色装饰
            drawDecoration(x, y-22, 16, red, g);		//画第三个三角形的红色装饰
        }
        else {
            drawDecoration(x+22, y-44, 14, red, g);	//画第三个三角形的黄色装饰
            drawDecoration(x, y-22, 16, yellow, g);		//画第三个三角形的红色装饰
        }
        x = 380-1*22 ;
        drwaRoot(g) ;			//画树根
    }
    void drawTree(int from, int to, Graphics g)	{		//画三角形
        Color c = new Color(9, 124, 37) ;
        g.setColor(c) ;
        for(int i=from; i<=to; i++) {
            for(int j=0; j<(i*2-1); j++) {
                g.fillRect(x, y, 20, 20);
                x += 22 ;
            }
            x = 380-i*22 ;
            y += 22 ;
        }
    }
    void drawDecoration(int tx, int ty, int num, Color c, Graphics g) {		//画装饰
        g.setColor(c) ;
        g.fillRoundRect(tx, ty, 18, 18, 18, 18) ;		//画圆
        g.fillRoundRect(tx+num*22, ty, 18, 18, 18, 18) ;
    }
    void drwaRoot(Graphics g) {			//画树根
        Color c = new Color(131, 78, 0) ;
        g.setColor(c);;
        for(int i=0; i<4; i++) {
            for(int j=0; j<3; j++) {
                g.fillRect(x, y, 20, 20);
                x += 22 ;
            }
            x = 380-1*22 ;
            y += 22 ;
        }
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == onOff) {		//按钮事件
            if(flag) {		//开
                ImageIcon icon = new ImageIcon("ON.png");
                icon.setImage(icon.getImage().getScaledInstance(50,50, 0)) ;
                onOff.setIcon(icon) ;
                flag = false ;
                clip.loop();
                time.restart() ;
            }
            else {			//关
                ImageIcon icon = new ImageIcon("OFF.png");
                icon.setImage(icon.getImage().getScaledInstance(50,50, 0)) ;
                onOff.setIcon(icon) ;
                flag = true ;
                time.stop() ;
                clip.stop() ;
            }
        }
        else if(e.getSource() == time) {		//时间事件
            repaint() ;
            color = !color ;
        }
    }
}

