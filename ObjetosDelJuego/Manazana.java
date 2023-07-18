package ObjetosDelJuego;

import java.awt.Color;
import java.awt.Graphics;

import Interfaz.EstadoDelJuego;
import Interfaz.Ventana;
import Mapache.PVector;

public class Manazana {
    PVector loc;
    Color col;
    public Manazana(float x, float y){
        loc = new PVector(x, y);
        col = new Color(255, 0, 0);
    }
    public void draw(Graphics g) {
        g.setColor(col);
        g.fillRect((int)loc.x*EstadoDelJuego.TAM, (int)loc.y*EstadoDelJuego.TAM, EstadoDelJuego.TAM, EstadoDelJuego.TAM);
    }
    public void update() {
        int prueva = 2;
        if(EstadoDelJuego.serpiente.get(0).loc.x==loc.x&&EstadoDelJuego.serpiente.get(0).loc.y==loc.y){
            EstadoDelJuego.serpiente.add(new Serpiente(
            EstadoDelJuego.serpiente.get(EstadoDelJuego.serpiente.size()-1).loc.x, 
            EstadoDelJuego.serpiente.get(EstadoDelJuego.serpiente.size()-1).loc.y));
            while(prueva>=0){
                for(int i=0;i<EstadoDelJuego.serpiente.size();i++){
                    if(EstadoDelJuego.serpiente.get(i).loc.x!=loc.x&&EstadoDelJuego.serpiente.get(i).loc.y!=loc.y){
                        prueva--;
                    }else{
                        loc = new PVector((int)(Math.random()*Ventana.WIDTH/EstadoDelJuego.TAM-1), (int)(Math.random()*Ventana.HEIGHT/EstadoDelJuego.TAM-1));
                        System.out.println(loc.x+" // "+loc.y);
                        prueva = 2;
                    }
                }
            }
        }
    }
}