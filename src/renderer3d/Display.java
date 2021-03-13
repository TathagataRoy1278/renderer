package renderer3d;

import renderer3d.environment.Camera;
import renderer3d.operations.KeyboardHandler;
import renderer3d.operations.PointTransform;
import renderer3d.point.*;
import renderer3d.shapes.*;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Display extends Canvas implements Runnable
{
  
  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private int numberOfSides = 6;

  private Thread thread;
  private JFrame frame;
  private static String title = "3d";
  public static final int WIDTH = 800;
  public static final int HEIGHT = 600;
  private static boolean running = false; 
  public static int dist = 100;

  private ArrayList<MyPolygon> polies = new ArrayList<MyPolygon>();
  

  private void init()
  {
	  polies.add(new MyPolygon(Color.RED,
                              new MyPoint(-50, 100, -50),
                              new MyPoint(50, 100, -50),
                              new MyPoint(50, 100, 50),
                              new MyPoint(-50, 100 , 50)));

    polies.add(new MyPolygon(Color.GREEN,
                            new MyPoint(-50, 100, -50),
                            new MyPoint(-50, 200, -50),
                            new MyPoint(-50, 200, 50),
                            new MyPoint(-50, 100, 50)));
    
    polies.add(new MyPolygon(Color.BLUE,
                            new MyPoint(-50, 100, 50),
                            new MyPoint(-50, 200, 50),
                            new MyPoint(50, 200, 50),
                            new MyPoint(50, 100, 50)));

    polies.add(new MyPolygon(Color.GRAY,
                            new MyPoint(50, 100, 50),
                            new MyPoint(50, 200, 50),
                            new MyPoint(50, 200, -50),
                            new MyPoint(50, 100, -50)));

    polies.add(new MyPolygon(Color.LIGHT_GRAY,
                            new MyPoint(50, 100, -50),
                            new MyPoint(50, 200, -50),
                            new MyPoint(-50, 200, -50),
                            new MyPoint(-50, 100, -50)));

    polies.add(new MyPolygon(Color.ORANGE,
                            new MyPoint(-50, 200, -50),
                            new MyPoint(50, 200, -50),
                            new MyPoint(50, 200, 50),
                            new MyPoint(-50, 200, 50))); 

   
                            


  frame.setTitle(title);
  //frame.setSize(new Dimension(WIDTH, HEIGHT));
  frame.add(this);
  frame.pack();
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.setLocationRelativeTo(null);
  frame.setResizable(false);
  frame.setVisible(true);
  
  addKeyListener(new KeyboardHandler(polies));
  setFocusable(true);

  start();

  }

  public Display()
  {
    this.frame = new JFrame(); 
    
    Dimension size = new Dimension(WIDTH, HEIGHT);
    this.setPreferredSize(size);
  
    init();
  }
  
  public synchronized void start()
  {
    running = true;
    thread = new Thread(this, "Display");
    this.thread.start();
  }
  
  public synchronized void stop()
  {
    running = false;
    try
    {
      this.thread.join();
    }
    catch(Exception e)
    {
      System.err.println(e);
    }
  }
  
  
  public void run()
  {
    final short frameRate = 10;
      
    long lastTime = System.nanoTime();
    long timer = System.currentTimeMillis();
    final double ns = 1000000000 / frameRate;
    
    double delta = 0;
    int frames = 0;
    
      
    while(running)
    {
      
      long now = System.nanoTime();
      delta += (now-lastTime)/ns;

      lastTime = now;
      while(delta >= 1)
      {
        update();
        render();
	      delta--;
      }
      
      //render();
      frames++;

      if(System.currentTimeMillis()-timer > 1000){
	      timer+=1000;
	      this.frame.setTitle(title + " " +  frames + "fps");
	      frames = 0;
      }

    }

    stop();

  }
  
  
  private void render()
  {
    BufferStrategy bs = this.getBufferStrategy();
    if(bs == null)
    {
	    this.createBufferStrategy(3);
	    return;
    }

    Graphics g = bs.getDrawGraphics();
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, WIDTH, HEIGHT );
    g.setColor(Color.WHITE);
    
    sortPolygons();

    for(MyPolygon poly : polies)
      poly.render(g);
    g.dispose();
    bs.show();


  }
  
  private void sortPolygons() {
    Collections.sort(polies, new Comparator<MyPolygon>(){
      public int compare(MyPolygon p1, MyPolygon p2)
      {
        int centerY_p1 = 0, centerY_p2 = 0;

        for(MyPoint point : p1.points)
        {
          centerY_p1 += point.y;
        }
        centerY_p1 /= p1.points.length;

        for(MyPoint point : p2.points)
        {
          centerY_p2 += point.y;
        }
        centerY_p2 /= p2.points.length;

        return centerY_p2 - centerY_p1;
      }

    });
  }

  private void update()
  {

  }

  
}
