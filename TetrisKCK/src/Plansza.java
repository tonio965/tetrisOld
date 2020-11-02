/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author tonio
 */
public class Plansza extends JPanel implements KeyListener{
    private File f;
    private File f0;
    private File next;
    private File fig0;
    private File fig1;
    private File fig2;
    private File fig3;
    private File fig4;
    private File fig5;
    private File fig6;
    private File f1;
    private File f2;
    private File f3;
    private File f4;
    private File f5;
    private File f6;
    private File f7;
    private File f8;
    private File f9;
    private File f10;
    private int predictedF=0;
    private int setki;
    private int dziesiatki;
    private int jednosci;
    private int lines=0;
    private boolean over=false;
    private final int kl=60;
    private final int del=1000/kl;
    private Timer timer;
    private BufferedImage blocks=null;
    private BufferedImage n1=null;
    private BufferedImage next1=null;
    private BufferedImage n10=null;
    private BufferedImage n0=null;
    private BufferedImage n2=null;
    private BufferedImage n3=null;
    private BufferedImage n4=null;
    private BufferedImage n5=null;
    private BufferedImage n6=null;
    private BufferedImage n7=null;
    private BufferedImage n8=null;
    private BufferedImage n9=null;
    private BufferedImage fq0=null;
    private BufferedImage fq1=null;
    private BufferedImage fq2=null;
    private BufferedImage fq3=null;
    private BufferedImage fq4=null;
    private BufferedImage fq5=null;
    private BufferedImage fq6=null;
    private final int blockSize=30;
    private final int boardW=10, boardH=20;
    private int[][] board=new int[boardH][boardW];
    private Shape[] shapes=new Shape[7];
    public Shape current;
    
    public int getBlockSize(){
        return blockSize;
    }

    public void setLines() {
        this.lines +=1;
//        setScore(lines);
    }
    
    public Plansza(){
//        f=new File("C:\\Users\\tonio\\Desktop\\teksturav2.png");
          f=new File("src/teksturav2.png");
          next=new File("src/n.png");
          f1=new File("src/1.png");
          f2=new File("src/2.png");
          f3=new File("src/3.png");
          f4=new File("src/4.png");
          f5=new File("src/5.png");
          f6=new File("src/6.png");
          f7=new File("src/7.png");
          f8=new File("src/8.png");
          f9=new File("src/9.png");
          f0=new File("src/0.png");
          f10=new File("src/score.png");
          fig0=new File("src/f0.png");
          fig1=new File("src/f1.png");
          fig2=new File("src/f2.png");
          fig3=new File("src/f3.png");
          fig4=new File("src/f4.png");
          fig5=new File("src/f5.png");
          fig6=new File("src/f6.png");
          try {
              next1=ImageIO.read(next);
          } catch (IOException ex) {

          }
          try {
              fq0=ImageIO.read(fig0);
          } catch (IOException ex) {

          }
          try {
              fq1=ImageIO.read(fig1);
          } catch (IOException ex) {

          }
          try {
              fq2=ImageIO.read(fig2);
          } catch (IOException ex) {

          }
          try {
              fq3=ImageIO.read(fig3);
          } catch (IOException ex) {

          }
          try {
              fq4=ImageIO.read(fig4);
          } catch (IOException ex) {

          }
          try {
              fq5=ImageIO.read(fig5);
          } catch (IOException ex) {

          }
          try {
              fq6=ImageIO.read(fig6);
          } catch (IOException ex) {

          }
          //////////////////////////
        try {
            n1=ImageIO.read(f1);
        } catch (IOException ex) {

        }
        try {
            n10=ImageIO.read(f10);
        } catch (IOException ex) {

        }
        try {
            n0=ImageIO.read(f0);
        } catch (IOException ex) {

        }
        try {
            n2=ImageIO.read(f2);
        } catch (IOException ex) {

        }
        try {
            n3=ImageIO.read(f3);
        } catch (IOException ex) {

        }
        try {
            n4=ImageIO.read(f4);
        } catch (IOException ex) {

        }
        try {
            n5=ImageIO.read(f5);
        } catch (IOException ex) {

        }
        try {
            n6=ImageIO.read(f6);
        } catch (IOException ex) {
            
        }
        try {
            n7=ImageIO.read(f7);
        } catch (IOException ex) {
            
        }
        try {
            n8=ImageIO.read(f8);
        } catch (IOException ex) {
            
        }
        try {
            n9=ImageIO.read(f9);
        } catch (IOException ex) {
            
        }
        try {
            blocks=ImageIO.read(f);
            
        } catch (IOException ex) {
            
        }
        shapes[0]=new Shape(blocks.getSubimage(blockSize*0, 0, blockSize, blockSize), new int[][]{
            {1,1,1,1} //I
        },this);
        shapes[1]=new Shape(blocks.getSubimage(blockSize, 0, blockSize, blockSize), new int[][]{
            {1,1,0}, //Z
            {0,1,1}
        },this);
        shapes[2]=new Shape(blocks.getSubimage(blockSize*2, 0, blockSize, blockSize), new int[][]{
            {0,1,1}, //Z2
            {1,1,0}
        },this);
        shapes[3]=new Shape(blocks.getSubimage(blockSize*3, 0, blockSize, blockSize), new int[][]{
            {1,1,1}, //L1
            {0,0,1}
        },this);
        shapes[4]=new Shape(blocks.getSubimage(blockSize*4, 0, blockSize, blockSize), new int[][]{
            {1,1,1}, //L2
            {1,0,0}
        },this);
        shapes[5]=new Shape(blocks.getSubimage(blockSize*5, 0, blockSize, blockSize), new int[][]{
            {1,1,1}, //T
            {0,1,0}
        },this);
        shapes[6]=new Shape(blocks.getSubimage(blockSize*6, 0, blockSize, blockSize), new int[][]{
            {1,1}, //sq
            {1,1}
        },this);
        timer=new Timer(del, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                update();
                repaint();
                System.gc();
            }
            
        });
        timer.start();
        setNext(); //przy odpaleniu jaka figura
    }

    public void update(){
        current.update();
        if(over==true)
            timer.stop();
    }

    public void setNext(){
    	int i=(int)(Math.random()*shapes.length);
        Shape nowy=new Shape(shapes[i].getBlock(),shapes[i].getCoords(),this);
        if(jednosci==9) {
        	nowy.speedUp();
        }
        current=nowy;
        for(int row=0; row<current.getCoords().length;row++)
            for(int col=0; col<current.getCoords()[row].length;col++)
                if(current.getCoords()[row][col]!=0){
                    if(board[row][col+4]!=0)
                        over=true;
                }
        System.gc();
        predictedF=predictNext();
    }
    public int predictNext() {
    	int i=(int)(Math.random()*shapes.length);
    	return i;
    }
    public void setNext(int predicted){
        Shape nowy=new Shape(shapes[predicted].getBlock(),shapes[predicted].getCoords(),this);
        if(jednosci==9) {
        	nowy.speedUp();
        }
        current=nowy;
        for(int row=0; row<current.getCoords().length;row++)
            for(int col=0; col<current.getCoords()[row].length;col++)
                if(current.getCoords()[row][col]!=0){
                    if(board[row][col+4]!=0)
                        over=true;
                }
        System.gc();
        predictedF=predictNext();
//        System.out.println("nastepna: "+predictedF);
    }
    public int getPredicted() {
    	return predictedF;
    }
    public int[][] getB(){
        return board;
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawRect(0, 0, 310, 630);
        g.setColor(Color.black);
        g.fillRect(0, 0, 310, 750);
        for(int row=0; row<board.length;row++){
            for(int col=0; col<board[row].length;col++){
                if(board[row][col]!=0)
                    g.drawImage(blocks.getSubimage(0, 0, blockSize, blockSize), col*blockSize, row*blockSize, null);
            }
        }
        for(int i=0;i<boardH;i++){
            g.drawLine(50,i*blockSize,boardW*blockSize,i*blockSize);
            for(int j=0;j<boardW;j++){
                g.drawLine(j*blockSize, 0, j*blockSize, boardH*blockSize);
            }
        }
        current.render(g);
        g.drawImage(n10, 0, 600,150,37, null);
        g.drawImage(next1,0,650,150,37 ,null); 
        setScore(g);
        drawNextFig(g,predictedF);
        
    }
    public void drawNextFig(Graphics g,int pred) {
    	if(pred==0) {
    		g.drawImage(fq0, 160, 637, null); 
    	}
    	if(pred==1) {
    		g.drawImage(fq1, 160, 637, null); 
    	}
    	if(pred==2) {
    		g.drawImage(fq2, 160, 637, null); 
    	}
    	if(pred==3) {
    		g.drawImage(fq3, 160, 637, null); 
    	}
    	if(pred==4) {
    		g.drawImage(fq4, 160, 637, null); 
    	}
    	if(pred==5) {
    		g.drawImage(fq5, 160, 637, null); 
    	}
    	if(pred==6) {
    		g.drawImage(fq6, 160, 637, null); 
    	}

    }
    public void setScore(Graphics g){
        jednosci=lines%10;
        dziesiatki=(lines%100-jednosci)/10;
        setki=((lines%1000)/100);

        if(jednosci == 0){
            g.drawImage(n0, 200, 600,25,37, null);    
        }
        if(jednosci == 1){
            g.drawImage(n1, 200, 600,25,37, null);    
        }
        if(jednosci == 2){
            g.drawImage(n2, 200, 600,25,37, null);    
        }
        if(jednosci == 3){
            g.drawImage(n3, 200, 600,25,37, null);    
        }
        if(jednosci == 4){
            g.drawImage(n4, 200, 600,25,37, null);    
        }
        if(jednosci == 5){
            g.drawImage(n5, 200, 600,25,37, null);    
        }
        if(jednosci == 6){
            g.drawImage(n6, 200, 600,25,37, null);    
        }
        if(jednosci == 7){
            g.drawImage(n7, 200, 600,25,37, null);    
        }
        if(jednosci == 8){
            g.drawImage(n8, 200, 600,25,37, null);    
        }
        if(jednosci == 9){
            g.drawImage(n9, 200, 600,25,37, null);    
        } 
        if(dziesiatki == 0){
            g.drawImage(n0, 175, 600,25,37, null);    
        }
        if(dziesiatki == 1){
            g.drawImage(n1, 175, 600,25,37, null);    
        }
        if(dziesiatki == 2){
            g.drawImage(n2, 175, 600,25,37, null);    
        }
        if(dziesiatki == 3){
            g.drawImage(n3, 175, 600,25,37, null);    
        }
        if(dziesiatki == 4){
            g.drawImage(n4, 175, 600,25,37, null);    
        }
        if(dziesiatki == 5){
            g.drawImage(n5, 175, 600,25,37, null);    
        }
        if(dziesiatki == 6){
            g.drawImage(n6, 175, 600,25,37, null);    
        }
        if(dziesiatki == 7){
            g.drawImage(n7, 175, 600,25,37, null);    
        }
        if(dziesiatki == 8){
            g.drawImage(n8, 175, 600,25,37, null);    
        }
        if(dziesiatki == 9){
            g.drawImage(n9, 175, 600,25,37, null);    
        }
        if(setki == 0){
            g.drawImage(n0, 150, 600,25,37, null);    
        }
        if(setki == 1){
            g.drawImage(n1, 150, 600,25,37, null);    
        }
        if(setki == 2){
            g.drawImage(n2, 150, 600,25,37, null);    
        }
        if(setki == 3){
            g.drawImage(n3, 150, 600,25,37, null);    
        }
        if(setki == 4){
            g.drawImage(n4, 150, 600,25,37, null);    
        }
        if(setki == 5){
            g.drawImage(n5, 150, 600,25,37, null);    
        }
        if(setki == 6){
            g.drawImage(n6, 150, 600,25,37, null);    
        }
        if(setki == 7){
            g.drawImage(n7, 150, 600,25,37, null);    
        }
        if(setki == 8){
            g.drawImage(n8, 150, 600,25,37, null);    
        }
        if(setki == 9){
            g.drawImage(n9, 150, 600,25,37, null);    
        }

    }
    @Override
    public void keyTyped(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode()==KeyEvent.VK_LEFT)
            current.setDeltaX(-1);
        if(ke.getKeyCode()==KeyEvent.VK_RIGHT)
            current.setDeltaX(1);
        if(ke.getKeyCode()==KeyEvent.VK_DOWN)
            current.speedDown();
        if(ke.getKeyCode()==KeyEvent.VK_UP)
            current.rotate();
        if(ke.getKeyCode()==KeyEvent.VK_P)
            timer.stop();
        if(ke.getKeyCode()==KeyEvent.VK_U)
            timer.start();
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if(ke.getKeyCode()==KeyEvent.VK_DOWN)
            current.makeNormal();
    }
}
