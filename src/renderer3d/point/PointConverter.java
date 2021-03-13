package renderer3d.point;

import java.awt.Point;
import renderer3d.Display;
import renderer3d.environment.Camera;


public class PointConverter{
	
    static int dist = Display.dist;
	public static Point convertPoint(MyPoint point3d){
		int depth = (int)Math.sqrt( Math.pow(point3d.x - Camera.x, 2) + 
									Math.pow(point3d.y - Camera.y, 2) +
									Math.pow(point3d.z - Camera.z, 2)  );
		int x2d = (int)(point3d.x) - Camera.x;
		int y2d = -((int)(point3d.z) - Camera.z);

		x2d = Camera.x + (int)(dist * (float)(x2d)/depth);
		y2d = Camera.z - (int)(dist * (float)(y2d)/depth);

		Point point2d = new Point(x2d, y2d);

		return point2d;
	}
}
