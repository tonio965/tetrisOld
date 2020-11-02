/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author tonio
 */
public class Shape {
    private BufferedImage block;
    private int [][]coords;
    private Plansza plansza;
    private int deltaX=0;
    private int x,y;
    private long time,lastTime;
    private boolean coll=false, udX=false;
    private int normal=600, speedDown=70,currentS;
    public Shape(BufferedImage block, int [][]coords, Plansza plansza){
        this.block=block;
        x=4;
        y=0;
        this.coords=coords;
        this.plansza=plansza;
        time=0;
        lastTime=System.currentTimeMillis();
        currentS=normal;
    }
    public void speedDown(){
        currentS=speedDown;
    }
    
    public void makeNormal(){
        currentS=normal;
    }

    public BufferedImage getBlock() {
        return block;
    }

    public void speedUp() {
    	currentS=(int) (currentS*0.9);
    }
    public void setBlock(BufferedImage block) {
        this.block = block;
    }

    public int[][] getCoords() {
        return coords;
    }

    public void setCoords(int[][] coords) {
        this.coords = coords;
    }
    
    public void rotate(){
        int [][] obrocona=null;
        obrocona=getTransp(coords);
        obrocona=getRever(obrocona);
        if(x+obrocona[0].length>10 || y+ obrocona.length>20)
            return;
        coords=obrocona;
    }
    private int[][]getTransp(int[][] macierz){
        int [][]nowa=new int[macierz[0].length][macierz.length];
        for(int i=0;i<macierz.length;i++){
            for(int j=0;j<macierz[0].length;j++)  //transpozycje macierzy robie kolumny z rzedami
                nowa[j][i]=macierz[i][j];
        }
        return nowa;
    }
    private void check(){
        int he = plansza.getB().length-1;
        for(int i=he ;i>0; i--){
            int count = 0;
            for(int j=0; j<plansza.getB()[0].length;j++){
                if(plansza.getB()[i][j]!=0)
                    count++;
                plansza.getB()[he][j]=plansza.getB()[i][j];
                if(count==10){
                    plansza.setLines();
                }
            }
            if(count<plansza.getB()[0].length){
             he--;
            }
        }
    }
    private int[][]getRever(int[][] macierz){ //zamieniam lustrzanie po osi oy
        int mid=macierz.length/2; //srodek
        for(int i=0;i<mid;i++){
            int[]m=macierz[i]; // aktualne
            macierz[i]=macierz[macierz.length-i-1];//!aktualne
            macierz[macierz.length-i-1]=m; //zamieniam !aktualne z aktualne
        }
        return macierz;
    }
    public void update(){
        time+=System.currentTimeMillis()-lastTime;
        lastTime=System.currentTimeMillis();
        if(coll){
            for(int row=0; row<coords.length;row++)
                for(int col=0; col<coords[row].length;col++)
                    if(coords[row][col]!=0)
                        plansza.getB()[y+row][x+col]=1; //ustawiam tutaj na 1 na stale w planszy po tym jak pizłem w krawedz
            
            
            
            check();
            plansza.setNext(plansza.getPredicted());
        }
        
        if(!(y+1+coords.length>20)){
            
            for(int row=0; row<coords.length;row++)
                for(int col=0; col<coords[row].length;col++)
                    if(coords[row][col]!=0)
                    {
                        if(plansza.getB()[y+row+1][col+x]!=0)
                            coll=true;
                    }
            if(time>currentS){
                y++;
                time=0;
            }
        }else{
            coll=true;
        }
//        deltaX=0;
        udX=true;
        
        
        if(!(x+deltaX+coords[0].length>10) && !(x+deltaX<0)){
            for(int row=0; row<coords.length;row++)
                for(int col=0; col<coords[row].length;col++)
                    if(coords[row][col]!=0){
                        if(plansza.getB()[y+row][x+deltaX+col]!=0)
                            udX=false;
                    }
            
            
            if(udX==true)
                x+=deltaX;   
        }

        deltaX=0;
    }
    public void render (Graphics g){
        for(int row=0;row<coords.length;row++)
            for(int col=0; col<coords[row].length;col++)
                if(coords[row][col]==1)
                   g.drawImage(block, col*plansza.getBlockSize()+x*plansza.getBlockSize(), row*plansza.getBlockSize()+y*plansza.getBlockSize(), null);
           
     
    }
    public void setDeltaX(int deltax){
        deltaX=deltax;
        
    }
}
