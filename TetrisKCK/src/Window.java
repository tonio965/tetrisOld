/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 *
 * @author tonio
 */
public class Window {
    
    JFrame window;
    public static final int W=310,H=720;
    private Plansza plansza;
    private BufferedImage back=null;
    private File f;

    int wynik;
    JLabel label;

    
    public Window(){
        f=new File("src/tlo.png");
        try {
            back=ImageIO.read(f);
        } catch (IOException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        window = new JFrame("Tetris");
        window.setSize(W,H);
//        window.getContentPane().setBackground(Color.yellow);
        plansza=new Plansza();
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(plansza);
        window.setVisible(true);
        window.addKeyListener(plansza);
    }

//    public static void main(String[] args) {
//        Window w =new Window();        
//    }
    
}
