package renderer3d.shapes;

import renderer3d.point.MyPoint;
import renderer3d.point.PointConverter;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Point;
import java.awt.Color;

public class MyPolygon
{
	public MyPoint[] points;
	private Color color;

	public MyPolygon(Color color, MyPoint... points)
	{
		this.color = color;
		this.points = new MyPoint[points.length];
		for(int i =0;i<points.length;i++)
		{
			this.points[i] = points[i];
		}
	}

	public MyPolygon(MyPoint... points)
	{
		this.points = new MyPoint[points.length];
		for(int i =0;i<points.length;i++)
		{
			MyPoint p = points[i];
			this.points[i] = new MyPoint(p.x, p.y, p.z);
		}
	}


	public void render(Graphics g)
	{
		Polygon poly = new Polygon();
		for(int i = 0;i<points.length;i++)
		{
			Point p = PointConverter.convertPoint(points[i]);
			poly.addPoint(p.x, p.y);
		}
		g.setColor(this.color);
		g.fillPolygon(poly);
	}
}
