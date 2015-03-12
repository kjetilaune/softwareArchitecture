package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.awt.Polygon;
import java.util.ArrayList;

/**
 * Created by annieaa on 10/03/15.
 */
public class Environment {

    private ArrayList<Polygon> polygons;

    private int numberOfHills;
    private int pixelStep;

    private double hillStartY;
    private double hillWidth;
    private int hillSliceWidth;

    public Environment(int numberOfHills, int pixelStep) {

        this.numberOfHills = numberOfHills;
        this.pixelStep = pixelStep;

        hillStartY = (140 + Math.random() * 200); // should be randomized properly
        hillWidth = Gdx.graphics.getWidth() / numberOfHills;
        hillSliceWidth = (int)hillWidth / pixelStep;

        polygons = new ArrayList<Polygon>();

        drawHills();

    }

    private void drawHills() {

        Vector2[] hillVector;
        int worldScale = 30;

        for (int i = 0 ; i < numberOfHills ; i++) {
            double randomHeight = Math.random() * 100; // should be randomized properly

            if (i != 0) {
                hillStartY = randomHeight;
            }

            for (int j = 0 ; j < hillSliceWidth ; j++) {

                hillVector = new Vector2[3];
                hillVector[0] = new Vector2((float)(j*pixelStep+hillWidth*i)/worldScale, 480/worldScale);
                hillVector[1] = new Vector2((float)(j*pixelStep+hillWidth*i)/worldScale, (float)(hillStartY+randomHeight*Math.cos(2*Math.PI/hillSliceWidth*j))/worldScale);
                hillVector[2] = new Vector2((float)((j+1)*pixelStep+hillWidth*i)/worldScale, (float)(hillStartY+randomHeight*Math.cos(2*Math.PI/hillSliceWidth*(j+1))/worldScale));
                hillVector[3] = new Vector2((float)((j+1)*pixelStep+hillWidth*i)/worldScale, 480/worldScale);

                Vector2 centre = findCentroid(hillVector, hillVector.length);

                for (int z = 0 ; z < hillVector.length; z++) {
                    hillVector[z].sub(centre);
                }

                Polygon poly = new Polygon();
                for (int z = 0 ; z < hillVector.length; z++) {
                    poly.addPoint((int)hillVector[z].x, (int)hillVector[z].y);
                }

                polygons.add(poly);

            }
            hillStartY = hillStartY + randomHeight;
        }



    }

    private Vector2 findCentroid(Vector2[] vs, int count) {

        Vector2 c = new Vector2();
        double area = 0.0;
        double p1X = 0.0;
        double p1Y = 0.0;
        double inv3 = 1.0/3.0;

        for(int i = 0 ; i < count ; i++) {
            Vector2 p2 = vs[i];
            Vector2 p3 = i+1 < count? vs[i+1] : vs[0];
            double e1X = p2.x - p1X;
            double e1Y = p2.y - p1Y;
            double e2X = p3.x - p1X;
            double e2Y = p3.x - p1Y;
            double D = e1X * e2Y - e1Y * e2X;
            double triangleArea = 0.5 * D;
            area += triangleArea;
            c.x += triangleArea * inv3 * (p1X + p2.x + p3.x);
            c.y += triangleArea * inv3 * (p1Y + p2.y + p3.y);
        }

        c.x *= 1.0/area;
        c.y *= 1.0/area;

        return c;

    }

    public ArrayList<Polygon> getPolygons() {
        return polygons;
    }




}
