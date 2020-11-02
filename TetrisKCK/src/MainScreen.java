import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class MainScreen{

    public MainScreen() {
 
    }

    public static void main(String[] args) { 
    	int wynik=0;
        JFrame f=new JFrame("Tetris");  
        JButton b=new JButton("Start"); 
//        final JTextField tf=new JTextField();
//        tf.setBounds(50,50, 150,20);  
        b.setBounds(50,100,95,30);  
        b.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
                Window w = new Window();
            }  
        });  
        f.add(b);
//        f.add(tf);
        f.setSize(200,400);  
        f.setLayout(null);  
        f.setVisible(true);   
    } 

}
