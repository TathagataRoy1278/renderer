package renderer3d.point;

import renderer3d.Display;
import renderer3d.environment.Camera;

public class MyPoint
{
	public double x,y,z;

	public MyPoint(double x, double y, double z)
	{

		int x2d = (int)(x);
		int y2d = (int)(z);
		int depth = (int)y;

		x2d = Display.WIDTH/2 + x2d;
		y2d = Display.HEIGHT/2 - y2d;

		this.x = x2d;
		this.y = depth;
		this.z = y2d;
	}
}
