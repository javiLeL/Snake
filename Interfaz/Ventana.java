package Interfaz;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Ventana extends JFrame implements Runnable{
    public static final int WIDTH = 614, HEIGHT = 597;
    private Canvas canvas;
    private Thread tread;
    public boolean funcionar;
    static public int frameCount;

    BufferStrategy bs;
    Graphics g;

    private final int FPS;
    private double targetTime;
    private double delta;
    private int averageFPS;

    EstadoDelJuego estadoDelJuego;
    KeyBoard keyBoard;

    public Ventana(){
        setTitle("Space Invaders");
        setSize(WIDTH, HEIGHT);
        // setExtendedState(JFrame.MAXIMIZED_BOTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);;
        setVisible(true);
        
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        canvas.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        canvas.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        canvas.setFocusable(true);
        add(canvas);

        FPS = 60;
        targetTime = 1000000000/FPS;
        delta = 0;
        averageFPS = FPS;

        keyBoard = new KeyBoard();
        canvas.addKeyListener(keyBoard);
        System.out.println(averageFPS);
    }

    @Override
    public void run() {
        long now = 0;
        long lastTime = System.nanoTime();
        int frames = 0;
        long time = 0;

        setup();
        do{
            now = System.nanoTime();
            delta += (now - lastTime)/targetTime;
            time = now - lastTime;

            lastTime = System.nanoTime();
            if(delta>=1){
                update();
                draw();
                delta--;
                frames++;
            }
            if(time >= 1000000000){
                averageFPS = frames;
                frames = 0;
                time = 0;
            }
        }while(funcionar);
        stop();
    }

    private void update(){
        frameCount++;
        estadoDelJuego.update();
    }

    private void setup(){
        estadoDelJuego = new EstadoDelJuego(); 
    }

    private void draw(){
        bs = canvas.getBufferStrategy();
        if(bs==null){
            canvas.createBufferStrategy(3);
            return;
        }
        g=bs.getDrawGraphics();
        
        estadoDelJuego.draw(g);
        /*
            g.setColor(Color.white);
            g.drawString(averageFPS+"", 0,10);
        */

        g.dispose();
        bs.show();
        
    }

    
    
    public void start(){
        funcionar=true;
        tread = new Thread(this);
        tread.start();
    }

    void stop(){
        try {
            tread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*public static void main(String[] args) {
        new Ventana().start();
    }*/ 
}