package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

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
    private int numberOfSlices;

    public Environment() {

        polygons = new ArrayList<Polygon>();

        drawBoringHills();
    }


    public Environment(int numberOfHills, int pixelStep) {

        this.numberOfHills = numberOfHills;
        this.pixelStep = pixelStep;


        hillStartY = Gdx.graphics.getHeight() / 2;
        //hillStartY = (140 + Math.random() * 200); // should be randomized properly
        hillWidth = Gdx.graphics.getWidth() / numberOfHills;
        numberOfSlices = (int)hillWidth / pixelStep;

        polygons = new ArrayList<Polygon>();

        drawHills();

    }

    public int getGroundHeight(int xPos) {

        int numberOfPolygons = polygons.size();

        for (int i = 0 ; i < numberOfPolygons ; i++) {

            //if (polygons.get(i).
        }
        return 0;
    }

    private void drawBoringHills() {

        float[] vecs1 = {0, 0, Gdx.graphics.getWidth()/4, 0, Gdx.graphics.getWidth()/4, 400, 0, Gdx.graphics.getHeight() - 400};
        polygons.add(new Polygon(vecs1));
        float[] vecs2 = {Gdx.graphics.getWidth()/4, 0, 3*Gdx.graphics.getWidth()/4, 0, 3*Gdx.graphics.getWidth()/4, 400, Gdx.graphics.getWidth()/4, 400};
        polygons.add(new Polygon(vecs2));
        float[] vecs3 = {3*Gdx.graphics.getWidth()/4, 0, Gdx.graphics.getWidth(), 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() - 500, 3*Gdx.graphics.getWidth()/4,  400};
        polygons.add(new Polygon(vecs3));

    }

    private void drawHills() {

        Vector2[] hillVector;
        //int worldScale = 30;

        for (int i = 0 ; i < numberOfHills ; i++) {
            double randomHeight = Math.random() * 1000; // should be randomized properly

            if (i != 0) {
                hillStartY = randomHeight;
            }

            for (int j = 0 ; j < numberOfSlices ; j++) {

                hillVector = new Vector2[4];
                /*hillVector[0] = new Vector2((float)(j*pixelStep+hillWidth*i)/worldScale, 480/worldScale);
                hillVector[1] = new Vector2((float)(j*pixelStep+hillWidth*i)/worldScale, (float)(hillStartY+randomHeight*Math.cos(2*Math.PI/hillSliceWidth*j))/worldScale);
                hillVector[2] = new Vector2((float)((j+1)*pixelStep+hillWidth*i)/worldScale, (float)(hillStartY+randomHeight*Math.cos(2*Math.PI/hillSliceWidth*(j+1))/worldScale));
                hillVector[3] = new Vector2((float)((j+1)*pixelStep+hillWidth*i)/worldScale, 480/worldScale);*/

                hillVector[0] = new Vector2((float)(j*pixelStep+hillWidth*i), 0);
                hillVector[1] = new Vector2((float)(j*pixelStep+hillWidth*i), (float)(hillStartY+randomHeight*Math.cos(2*Math.PI/numberOfSlices*j)));
                hillVector[2] = new Vector2((float)((j+1)*pixelStep+hillWidth*i), (float)(hillStartY+randomHeight*Math.cos(2*Math.PI/numberOfSlices*(j+1))));
                hillVector[3] = new Vector2((float)((j+1)*pixelStep+hillWidth*i), 0);

                Vector2 centre = findCentroid(hillVector, hillVector.length);

                for (int z = 0 ; z < hillVector.length; z++) {
                    hillVector[z].sub(centre);
                }



                float[] vertices = {hillVector[0].x, hillVector[0].y,
                                    hillVector[1].x, hillVector[1].y,
                                    hillVector[2].x, hillVector[2].y,
                                    hillVector[3].x, hillVector[3].y};

                Polygon poly = new Polygon(vertices);

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
