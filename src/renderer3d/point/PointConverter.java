package renderer3d.point;

import java.awt.Point;
import renderer3d.Display;
import renderer3d.environment.Camera;


public class PointConverter{
	
    static int dist = Display.dist;
	public static Point convertPoint(MyPoint point3d){
		int x2d = (int)(point3d.x) - Display.WIDTH/2;
		int y2d = (int)(point3d.z) + Display.HEIGHT/2;// these two vars are used the pupil of the eye is suppossed to be in  the middle of the screen
		int depth = (int)point3d.y - Camera.y;

		x2d = (int)(dist * (float)(x2d)/depth) + Display.WIDTH/2;
		y2d = (int)(dist * (float)(y2d)/depth) - Display.HEIGHT/2;

		Point point2d = new Point(x2d, y2d);

		return point2d;
	}
}
