package Interfaz;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import Mapache.PVector;
import ObjetosDelJuego.*;

public class EstadoDelJuego {
    final public static int TAM=40;
    public static ArrayList <Serpiente> serpiente;
    public static Manazana manazana;

    public EstadoDelJuego(){
        serpiente = new ArrayList<>();
        manazana = new Manazana(Ventana.WIDTH/2/TAM+5, Ventana.HEIGHT/2/TAM-1);
        for(int i=0;i<3;i++){
            serpiente.add(new Serpiente(Ventana.WIDTH/2/TAM ,Ventana.HEIGHT/2/TAM-1));
        }
    }
    void draw(Graphics g){
        g.setColor(new Color(0));
        g.fillRect(0, 0, Ventana.WIDTH, Ventana.HEIGHT);
        for(int i=0;i<serpiente.size();i++){
            serpiente.get(i).draw(g);
        }
        g.setColor(new Color(100, 100, 100));
        for(int x=0;x<Ventana.WIDTH/TAM;x++){
            g.drawLine(x*TAM, 0, x*TAM, Ventana.WIDTH);
        }
        for(int y=0;y<Ventana.HEIGHT/TAM;y++){
            g.drawLine(0, y*TAM, Ventana.HEIGHT, y*TAM);
        }
        manazana.draw(g);
    }

    void update(){
        serpiente.get(0).mover();
        if(Ventana.frameCount%5==0){
            for(int i=serpiente.size()-1;i>0;i--){
                serpiente.get(i).loc=new PVector(serpiente.get(i-1).loc.x, serpiente.get(i-1).loc.y);
            }
            serpiente.get(0).update();
            if(serpiente.get(0).morir()){
                serpiente.clear();
                KeyBoard.key='+';
                Serpiente.vel = new PVector(0, 0);
                for(int i=0;i<3;i++){
                    serpiente.add(new Serpiente(Ventana.WIDTH/2/TAM ,Ventana.HEIGHT/2/TAM-1));
                }
                manazana = new Manazana(Ventana.WIDTH/2/TAM+5, Ventana.HEIGHT/2/TAM-1);
            }
        }
        manazana.update();
    } 
}