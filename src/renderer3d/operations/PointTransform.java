package renderer3d.operations;

import java.util.ArrayList;

import renderer3d.environment.Camera;
import renderer3d.point.*;
import renderer3d.shapes.MyPolygon;

public class PointTransform
{
    public static void translate(ArrayList<MyPolygon> polies, int x, int y, int z)
    {
        for(MyPolygon poly : polies)
                for(MyPoint point : poly.points)
                {
                    point.x += x;
                    point.z -= z;
                    point.y += y;
                }

    }

    public static void rotate(ArrayList<MyPolygon> polies, double xR, double yR, double zR)
    {
        xR*=-1;
        yR*=-1;
        zR*=-1;

        int centerX = 0, centerY = 0, centerZ = 0;
        double newX, newY, newZ;
        double tmpX, tmpY, tmpZ;

        centerX = Camera.x;
        centerY = Camera.y;
        centerZ = Camera.z;

         for(MyPolygon poly : polies)
            for(MyPoint point : poly.points)
            {
                newX = point.x - centerX;
                newY = point.y - centerY;
                newZ = point.z - centerZ;

                tmpX = newX*1;
                tmpY = newY*Math.cos(xR) + newZ*Math.sin(xR);
                tmpZ = -newY*Math.sin(xR) + newZ*Math.cos(xR);

                newX = tmpX;
                newY = tmpY;
                newZ = tmpZ;

                tmpX = newX*Math.cos(yR) -  newZ*Math.sin(yR);
                tmpY = newY*1;
                tmpZ = newX*Math.sin(yR) + newZ*Math.cos(yR);

                newX = tmpX;
                newY = tmpY;
                newZ = tmpZ;

                tmpX = newX*Math.cos(zR) + newY*Math.sin(zR);
                tmpY = -newX*Math.sin(zR) + newY*Math.cos(zR);
                tmpZ = tmpZ*1;

                point.x = tmpX + centerX;
                point.y = tmpY + centerY;
                point.z = tmpZ + centerZ;

            }
        
    }
}