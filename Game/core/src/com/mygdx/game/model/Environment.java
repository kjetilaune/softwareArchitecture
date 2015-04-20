package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Enums.Appearance;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by annieaa on 10/03/15.
 */
public class Environment {

    private ArrayList<Polygon> polygons;
    private ArrayList<Boolean> traversables; // says which polygons are not traversable

    private int numberOfHills;
    private int pixelStep; // width of each polygon

    private double hillStartY;
    private double hillWidth;
    private int numberOfSlices;

    private Appearance appearance;


    // for creating an environment with a certain number of hills
    // pixelStep is the width of each polygon that makes up the ground
    public Environment(int numberOfHills, int pixelStep) {

        this.numberOfHills = numberOfHills;
        this.pixelStep = pixelStep;

        hillStartY = (Gdx.graphics.getHeight()/5 + Math.random() * Gdx.graphics.getHeight()/3);
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


    public void collide(Vector2 point, int blastRadius) {

        Circle blast = new Circle(point, blastRadius);
        int indexCollision = -1;

        // find the polygon of collision
        for (int i = 0 ; i < polygons.size() ; i++) {

            if (polygons.get(i).contains(point.x, point.y)) {
                indexCollision = i;
            }
        }

        // mark all polygons that should be affected
        // and alter the given polygons
        int indexRange = blastRadius/pixelStep;

        for (int i = indexCollision - indexRange ; i < indexCollision + indexRange + 1 ; i ++) {

            if (i > 0 && i < polygons.size()) {
                float[] vertices = polygons.get(i).getVertices();

                vertices[3] = getIntersection(blast, vertices[2], vertices[3]);
                vertices[5] = getIntersection(blast, vertices[4], vertices[5]);

                polygons.get(i).setVertices(vertices);
            }

        }

    }


    // returns the lowest y-coordinate at the intersection of the given circle and x-coordinate
    private float getIntersection(Circle blast, float x, float y) {

        // calculate the two solutions for intersection
        double y1 = Math.sqrt(Math.pow(blast.radius, 2) - Math.pow((x - blast.x), 2)) + blast.y;
        double y2 = -1 * Math.sqrt(Math.pow(blast.radius, 2) - Math.pow((x - blast.x), 2)) + blast.y;

        // check if solutions are imaginary
        // if so, return the existing y-coordinate
        if (Double.isNaN(y1) && Double.isNaN(y2)) {
            return y;
        }
        else if (Double.isNaN(y1) && y2 > y1) {
            return (float)y2;
        }
        else if (Double.isNaN(y2) && y1 > y2) {
            return (float)y1;
        }
        else if (y < y1 && y < y2) {
            return y; // check if the hill is already below the intersection
        }
        else {
            return y1<y2 ? (float)y1 : (float)y2;
        }

    }


    // returns the height of the ground at the given x-position
    public float getGroundHeight(float xPos) {

        float y = 0;
        // iterate the polygons and find the one that contains the given x
        for (Polygon poly : polygons) {

            if (poly.contains(xPos, 0)) {
                float[] vertices = poly.getVertices();
                float[] verticesY = getVerticesY(vertices);
                float[] verticesX = getVerticesX(vertices);

                float y1 = verticesY[1];
                float y2 = verticesY[2];
                float x1 = verticesX[1];
                float x2 = verticesX[2];

                // calculate the slope and y-coordinate
                float m = (y1-y2)/(x1-x2);
                y = (m * (xPos - x1)) + y1;
            }

        }

        return y;
    }


    // returns the angle of the ground at the given start and stop x-position
    public float getAngle(float xStart, float xStop, float width) {

        float x1 = xStart;
        float x2 = xStop - width/2;

        float y1 = getGroundHeight(x1);
        float y2 = getGroundHeight(x2);

        float adjacent = x1 - x2;
        float opposite = y1 - y2;

        float angle = (float)Math.atan(opposite/adjacent);

        return 1 * (float)Math.toDegrees(angle);

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


    // draws hills with random heights
    private void drawHills() {

        float[] vertices;

        for (int i = 0 ; i < numberOfHills ; i++) {

            // decides the random height of each hill
            // make sure it does not go below screen
            double randomHeight = Math.random() * Gdx.graphics.getHeight()/5;
            while (hillStartY - 2*randomHeight < Gdx.graphics.getHeight()/80) {
                randomHeight = Math.random() * Gdx.graphics.getHeight()/5;
            }

            if (i != 0) {
                hillStartY -= randomHeight; // to make sure the next hill starts when the last ended
            }

            System.out.println("height: " + randomHeight);

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
        int pick = new Random().nextInt(Appearance.values().length);
        appearance = Appearance.values()[pick];
    }


    public ArrayList<Polygon> getPolygons() {
        return polygons;
    }


    public ArrayList<Boolean> getTraversables() {
        return traversables;
    }


    public Texture getTexture() {
        return appearance.getTexture();
    }


    public float[] getBgColors() {
        return appearance.getBackground();
    }


}
