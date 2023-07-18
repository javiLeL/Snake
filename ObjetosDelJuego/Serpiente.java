package ObjetosDelJuego;

import java.awt.Color;
import java.awt.Graphics;

import Interfaz.EstadoDelJuego;
import Interfaz.KeyBoard;
import Interfaz.Ventana;
import Mapache.PVector;

public class Serpiente {
    public PVector loc;
    Color col;
    public static PVector vel;
    public Serpiente(float x, float y) {
        loc = new PVector(x, y);
        vel = new PVector(0, 0);
        col = new Color(0, 255, 0);
    }

    public void draw(Graphics g) {
        g.setColor(col);
        g.fillRect((int)loc.x*EstadoDelJuego.TAM, (int)loc.y*EstadoDelJuego.TAM, EstadoDelJuego.TAM, EstadoDelJuego.TAM);
    }

    public void mover(){
        if(KeyBoard.key=='w' && vel.y!=1){
            vel = new PVector(0, -1);
        }else if(KeyBoard.key=='s' && vel.y!=-1){
            vel = new PVector(0, 1);
        }else if(KeyBoard.key=='a' && vel.x!=1){
            vel = new PVector(-1, 0);
        }else if(KeyBoard.key=='d' && vel.x!=-1){
            vel = new PVector(1, 0);
        }
        
    }

    public void update() {
        loc.add(vel);
    }

    public boolean morir(){
        boolean resultado = false;
        for(int i=3;i<EstadoDelJuego.serpiente.size();i++){
            if(EstadoDelJuego.serpiente.get(0).loc.x==EstadoDelJuego.serpiente.get(i).loc.x&&
                EstadoDelJuego.serpiente.get(0).loc.y==EstadoDelJuego.serpiente.get(i).loc.y){
                resultado=true;
            }
        }
        if(EstadoDelJuego.serpiente.get(0).loc.x<0||EstadoDelJuego.serpiente.get(0).loc.x>=Ventana.WIDTH/EstadoDelJuego.TAM||
        EstadoDelJuego.serpiente.get(0).loc.y<0||EstadoDelJuego.serpiente.get(0).loc.y>=Ventana.HEIGHT/EstadoDelJuego.TAM){
            resultado=true;
        }
        return resultado;
    }
}