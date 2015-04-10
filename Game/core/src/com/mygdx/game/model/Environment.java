package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Enums.EnvironmentEnum;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

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

    private EnvironmentEnum appearance;



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

        System.out.println("Height: " + Gdx.graphics.getHeight() + ", Width: " + Gdx.graphics.getWidth());

        // where the hill should start, will randomize this properly
        //hillStartY = 500;
        //hillStartY = (140 + Math.random() * 200);

        hillStartY = (Gdx.graphics.getHeight()/3 + Math.random() * Gdx.graphics.getHeight()/3);
        hillWidth = Gdx.graphics.getWidth() / numberOfHills;
        numberOfSlices = (int)Math.round(hillWidth / pixelStep); // this really is number of polygon slices per hill

        polygons = new ArrayList<Polygon>(); // an array containing all polygons used for drawing ground

        drawHills();
        setRandomAppearance();

    }


    // returns whether the given point is colliding with the environment
    public boolean isColliding(Vector2 point) {

        for (Polygon polygon : polygons) {
            if (polygon.contains(point.x, point.y)) {
                return true;
            }
        }

        return false;
    }

    public boolean collide(Vector2 point) {


        if (isColliding(point)) {

            int collisionIndex = -1;

            for (int i = 0 ; i < polygons.size() ; i++) {

                if (polygons.get(i).contains(point.x, point.y)) {
                    return true;
                }

            }


        }


        return false;

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


    // returns the angle of the ground at the given start and stop x-position
    public float getAngle(float xStart, float xStop) {

        float y1 = getGroundHeight(xStart);
        float y2 = getGroundHeight(xStop);

        float adjacent = xStop - xStart;
        float opposite = y1 - y2;

        float angle = (float)Math.atan(opposite/adjacent);


        return -1 * (float)Math.toDegrees(angle);
    }


    // help function for getting all x-coordinates of a polygon given its array of points
    private float[] getVerticesX (float[] vertices) {

        float[] verticesX = new float[vertices.length / 2];
        for (int i = 0; i < vertices.length/2; i++ ) {
            verticesX[i] = vertices[2*i];
        }
        return verticesX;
    }


    // help function for getting all y-coordinates of a polygon given its array of points
    private float[] getVerticesY (float[] vertices) {

        float[] verticesY = new float[vertices.length / 2];
        for (int i = 0; i < vertices.length/2; i++ ) {
            verticesY[i] = vertices[(2*i)+1];
        }
        return verticesY;
    }


    // for testing purposes. will probably be deleted soon.
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
            //double randomHeight = Math.random() * 400; // should be randomized properly
            double randomHeight = Math.random() * Gdx.graphics.getHeight()/5;

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


    // sets random appearance of the environment, ex. grass, snow, rock etc.
    private void setRandomAppearance() {
        int pick = new Random().nextInt(EnvironmentEnum.values().length);
        appearance = EnvironmentEnum.values()[pick];
    }


    public ArrayList<Polygon> getPolygons() {
        return polygons;
    }


    public Texture getTexture() {
        return appearance.getTexture();
    }


    public float[] getBgColors() {
        return appearance.getBackground();
    }


}
