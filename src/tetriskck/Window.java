/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetriskck;

import javax.swing.JFrame;

/**
 *
 * @author tonio
 */
public class Window {
    
    JFrame window;
    public static final int W=310,H=630;
    private Plansza plansza;
    
    public Window(){
        window = new JFrame("Tetris");
        window.setSize(W,H);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        plansza=new Plansza();
        window.add(plansza);
        window.addKeyListener(plansza);
    }
    
    public static void main(String[] args) {
        new Window();
        
    }
    
}
