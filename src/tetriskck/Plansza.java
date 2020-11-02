/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetriskck;

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
    private int lines=0;
    private boolean over=false;
    private final int kl=60;
    private final int del=1000/kl;
    private Timer timer;
    private BufferedImage blocks=null;
    private final int blockSize=30;
    private final int boardW=10, boardH=20;
    private int[][] board=new int[boardH][boardW];
    private Shape[] shapes=new Shape[7];
    public Shape current;
    
    public int getBlockSize(){
        return blockSize;
    }

    public void setLines(int lines) {
        this.lines += lines;
        System.out.println("zbite linie: "+this.lines);
    }
    
    public Plansza(){
//        f=new File("C:\\Users\\tonio\\Desktop\\teksturav2.png");
          f=new File("src/tetriskck/teksturav2.png");
          if(f.isFile() && f.canRead()){
              System.out.println("wczytal sie obrazek");
          }
        try {
            blocks=ImageIO.read(f);
            if(blocks!=null)
                System.out.println("nadpisalo");
        } catch (IOException ex) {
//            System.out.println("jebło");
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
            }
            
        });
        timer.start();
        setNext();
    }
    
    public void update(){
        current.update();
        if(over)
            timer.stop();
    }
    public void setNext(){
        int i=(int)(Math.random()*shapes.length);
        Shape nowy=new Shape(shapes[i].getBlock(),shapes[i].getCoords(),this);
        current=nowy;
//        System.out.println(nowy.getCoords().toString());
        for(int row=0; row<current.getCoords().length;row++)
            for(int col=0; col<current.getCoords()[row].length;col++)
                if(current.getCoords()[row][col]!=0){
                    if(board[row][col+4]!=0)
                        over=true;
                }
    }
    public int[][] getB(){
        return board;
    }
    public void paintComponent(Graphics g){
        System.out.println("wywolany");
        super.paintComponent(g);
        for(int row=0; row<board.length;row++){
            for(int col=0; col<board[row].length;col++){
                if(board[row][col]!=0)
                    g.drawImage(blocks.getSubimage(0, 0, blockSize, blockSize), col*blockSize, row*blockSize, null);
            }
        }
        for(int i=0;i<boardH;i++){
            g.drawLine(0,i*blockSize,boardW*blockSize,i*blockSize);
            for(int j=0;j<boardW;j++){
                g.drawLine(j*blockSize, 0, j*blockSize, boardH*blockSize);
            }
        }
        current.render(g);
//        System.out.println("hops");
        
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
