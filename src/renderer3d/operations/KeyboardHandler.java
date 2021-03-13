package renderer3d.operations;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import renderer3d.point.MyPoint;
import renderer3d.shapes.MyPolygon;

public class KeyboardHandler implements KeyListener
{

    ArrayList<MyPolygon> polies;
    public KeyboardHandler(ArrayList<MyPolygon> polies)
    {
        this.polies = polies;
    }

    @Override
    public void keyTyped(KeyEvent e) {


        
    }

    @Override
    public void keyPressed(KeyEvent e) {
       switch(e.getKeyCode())
       {
            case KeyEvent.VK_A : 
                PointTransform.rotate(polies, 0, 0, Math.toRadians(1));
                break;
            case KeyEvent.VK_D :
                PointTransform.rotate(polies, 0, 0, -Math.toRadians(1));
                break;
            case KeyEvent.VK_W :
                PointTransform.rotate(polies, Math.toRadians(1), 0, 0);
                break;
            case KeyEvent.VK_S :
                PointTransform.rotate(polies, -Math.toRadians(1), 0, 0);
                break;

            case KeyEvent.VK_L:
                PointTransform.translate(polies, -5, 0, 0);
                break;
            case KeyEvent.VK_J:
                PointTransform.translate(polies, 5, 0, 0);
                break;
            case KeyEvent.VK_I:
                PointTransform.translate(polies, 0, -5, 0);
                break;
            case KeyEvent.VK_K:
                PointTransform.translate(polies, 0, 5, 0);
                break;

            
            
                
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}
