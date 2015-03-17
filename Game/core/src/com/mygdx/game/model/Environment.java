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


    // for creating a simple environment
    public Environment() {
        polygons = new ArrayList<Polygon>();
        drawBoringHills();
    }

    // for creating an environment with a certain number of hills
    // pixelStep is the width of each polygon that makes up the ground
    public Environment(int numberOfHills, int pixelStep) {

        this.numberOfHills = numberOfHills;
        this.pixelStep = pixelStep;

        // where the hill should start, will randomize this properly
        hillStartY = 500;
        //hillStartY = (140 + Math.random() * 200);
        hillWidth = Gdx.graphics.getWidth() / numberOfHills;
        numberOfSlices = (int)Math.round(hillWidth / pixelStep); // this really is number of polygon slices per hill

        polygons = new ArrayList<Polygon>(); // an array containing all polygons used for drawing ground

        drawHills();

    }

    // returns the height of the ground at the given x-position
    public float getGroundHeight(float xPos) {

        float y = 0;

        for (Polygon poly : polygons) {

            if (poly.contains(xPos, 0)) {
                float[] vertices = poly.getVertices();
                float[] verticesY = getVerticesY(vertices);
                float[] verticesX = getVerticesX(vertices);

                float y1 = verticesY[1];
                float y2 = verticesY[2];

                y = ((y1/y2) * (xPos - verticesX[0])) + y1; // y = ax + b
            }

        }

        return y;
    }

    public float getAngle(float xStart, float xStop) {

        float y1 = getGroundHeight(xStart);
        float y2 = getGroundHeight(xStop);

        float adjacent = xStop - xStart;
        float opposite = y1 - y2;

        //System.out.println("x1: " + xStart + ", x2: " + xStop);
        //System.out.println("y1: " + y1 + ", y2: " + y2);

        float angle = (float)Math.atan(opposite/adjacent);

        System.out.println("angle: " + Math.toDegrees(angle));

        return -1 * (float)Math.toDegrees(angle);
        //return 360 - (float)Math.toDegrees(angle);
    }


    private float[] getVerticesX (float[] vertices) {

        float[] verticesX = new float[vertices.length / 2];
        for (int i = 0; i < vertices.length/2; i++ ) {
            verticesX[i] = vertices[2*i];
        }
        return verticesX;
    }


    private float[] getVerticesY (float[] vertices) {

        float[] verticesY = new float[vertices.length / 2];
        for (int i = 0; i < vertices.length/2; i++ ) {
            verticesY[i] = vertices[(2*i)+1];
        }
        return verticesY;
    }


    private void drawBoringHills() {

        float[] vecs1 = {0, 0, Gdx.graphics.getWidth()/4, 0, Gdx.graphics.getWidth()/4, 400, 0, Gdx.graphics.getHeight() - 400};
        polygons.add(new Polygon(vecs1));
        float[] vecs2 = {Gdx.graphics.getWidth()/4, 0, 3*Gdx.graphics.getWidth()/4, 0, 3*Gdx.graphics.getWidth()/4, 400, Gdx.graphics.getWidth()/4, 400};
        polygons.add(new Polygon(vecs2));
        float[] vecs3 = {3*Gdx.graphics.getWidth()/4, 0, Gdx.graphics.getWidth(), 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() - 500, 3*Gdx.graphics.getWidth()/4,  400};
        polygons.add(new Polygon(vecs3));

    }


    // draws hills with random heights
    private void drawHills() {

        float[] vertices;

        for (int i = 0 ; i < numberOfHills ; i++) {

            // decides the random height of each hill
            double randomHeight = Math.random() * 400; // should be randomized properly

            if (i != 0) {
                hillStartY -= randomHeight; // to make sure the next hill starts when the last ended
            }

            // create a polygon for each slice of hill
            for (int j = 0 ; j < numberOfSlices ; j++) {

                // use some cosine magic to create the rounded appearance of the hill
                vertices = new float[8];
                vertices[0] = (float)(j*pixelStep+hillWidth*i);
                vertices[1] = 0;
                vertices[2] = (float)(j*pixelStep+hillWidth*i);
                vertices[3] = (float)(hillStartY+randomHeight*Math.cos(2*(Math.PI/numberOfSlices)*j));
                vertices[4] = (float)((j+1)*pixelStep+hillWidth*i);
                vertices[5] = (float)(hillStartY+randomHeight*Math.cos(2*(Math.PI/numberOfSlices)*(j+1)));
                vertices[6] = (float)((j+1)*pixelStep+hillWidth*i);
                vertices[7] = 0;

                Polygon poly = new Polygon(vertices); // create a polygon based on these coordinates
                polygons.add(poly);

            }
            hillStartY = hillStartY + randomHeight; // to make sure the next hill starts at the right place
        }

    }


    public ArrayList<Polygon> getPolygons() {
        return polygons;
    }




}
