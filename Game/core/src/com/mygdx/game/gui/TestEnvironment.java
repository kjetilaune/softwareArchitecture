package com.mygdx.game.gui;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by annieaa on 11/03/15.
 */
public class TestEnvironment {

    private World world = new World(new Vector2(0, 10), true);
    private int worldScale = 30;

    private Box2DDebugRenderer debugRenderer;

    public TestEnvironment() {
        debugDraw();
        drawHills(2, 10);
    }

    private void debugDraw() {

        debugRenderer = new Box2DDebugRenderer();
        //debugRenderer.render(world, camera.combined);

    }

    private void drawHills(int numberOfHills, int pixelStep) {

        double hillStartY = (140 + Math.random() * 200);
        double hillWidth = 640 / numberOfHills;
        double hillSliceWidth = hillWidth / pixelStep;
        Vector2[] hillVector;

        for (int i = 0 ; i < numberOfHills ; i++) {
            double randomHeight = Math.random() * 100;

            if (i != 0) {
                hillStartY = randomHeight;
            }

            for (int j = 0 ; j < hillSliceWidth ; j++) {

                hillVector = new Vector2[3];
                hillVector[0] = new Vector2((float)(j*pixelStep+hillWidth*i)/worldScale, 480/worldScale);
                hillVector[1] = new Vector2((float)(j*pixelStep+hillWidth*i)/worldScale, (float)(hillStartY+randomHeight*Math.cos(2*Math.PI/hillSliceWidth*j))/worldScale);
                hillVector[2] = new Vector2((float)((j+1)*pixelStep+hillWidth*i)/worldScale, (float)(hillStartY+randomHeight*Math.cos(2*Math.PI/hillSliceWidth*(j+1))/worldScale));
                hillVector[3] = new Vector2((float)((j+1)*pixelStep+hillWidth*i)/worldScale, 480/worldScale);

                BodyDef sliceBody = new BodyDef();
                Vector2 centre = findCentroid(hillVector, hillVector.length);
                sliceBody.type = BodyDef.BodyType.StaticBody;
                sliceBody.position.set(centre.x, centre.y);

                for (int z = 0 ; z < hillVector.length; z++) {
                    hillVector[z].sub(centre);
                }

                PolygonShape slicePoly = new PolygonShape();
                slicePoly.set(hillVector);
                FixtureDef sliceFixture = new FixtureDef();
                sliceFixture.shape = slicePoly;
                Body worldSlice = world.createBody(sliceBody);
                worldSlice.createFixture(sliceFixture);

                slicePoly.dispose();
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





}
