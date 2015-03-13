package com.mygdx.game.model;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.controller.FireController;

/**
 * Created by Jonathan on 12.03.2015.
 */
public class BulletPhysics {
    public static class Equation {
        public float gravity;
        public Vector2 startVelocity = new Vector2();
        public Vector2 startPoint = new Vector2();

        public float getX(float t){
            return startVelocity.x*t + startPoint.x;
        }
        public float getY(float t){
            return 0.5f * gravity * t * t + startVelocity.y*t + startPoint.y;
        }

        public float getTforGivenX(float x){
            return (x- startPoint.x) / (startVelocity.x);
        }
    }
    public static class BulletActor extends Actor{
        private FireController controller;
        private Equation equation;
        private Sprite bulletSprite;

        public int bulletPointCount = 50;
        public float timeSeparation = 1f;

        public BulletActor(FireController controller, float gravity, Sprite bulletSprite){
            this.controller = controller;
            this.bulletSprite = bulletSprite;
            this.equation = new Equation();
            this.equation.gravity = gravity;
        }
        public void act(float delta){
            super.act(delta);
            equation.startVelocity.set(controller.power, 0f);
            equation.startVelocity.rotate(controller.angle);
        }

        public void draw(SpriteBatch batch, float parent){
            float t = 0f;
            float a = 1f;
            float adiff = a/bulletPointCount;
            float width = this.getWidth();
            float height = this.getHeight();
            float widthDiff = width / bulletPointCount;
            float heightDiff = height / bulletPointCount;

            float timeSeparation = this.timeSeparation;
            if (controller.fixedHorizontalDistance)
                timeSeparation = equation.getTforGivenX(15f);

            for (int i = 0; i < bulletPointCount; i++){
                float x = this.getX() + equation.getX(t);
                float y = this.getY() + equation.getY(t);

                batch.draw(bulletSprite, x, y, width, height);

                a -= adiff;
                t += timeSeparation;

                width -= widthDiff;
                height -= heightDiff;
            }

        }


    }


}
