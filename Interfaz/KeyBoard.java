package Interfaz;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard implements KeyListener{
    static public char key;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        key = e.getKeyChar();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
