package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import java.awt.*;

public class Projectile {
    private static final Texture texture = new Texture("projectile.png");
    private float x;
    private float y;
    private float vx;
    private float vy;
    private static final float speed = 600f;
    private boolean active;
    private Target target;
    private Dimension targetPosition;
    private int targetSize;

    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        active = false;
    }

    public Projectile() {
    }

    public void shoot(float x, float y, float angle,Target target) {
        this.x = x;
        this.y = y;
        this.vx = speed * MathUtils.cosDeg(angle);
        this.vy = speed * MathUtils.sinDeg(angle);
        this.active = true;
        this.target = target;
        targetSize = Target.size*Target.scale;
        targetPosition = target.getPosition();
    }

    public void update(float dt) {
        x += vx * dt;
        y += vy * dt;
        System.out.println(targetSize);
        if(x>=targetPosition.width && x<=targetPosition.width + targetSize && y>=targetPosition.height && y<=targetPosition.height+targetSize){
            deactivate();
            target.destroy();
        }
        if (x < 0 || x > 1280 || y < 0 || y > 720) {
            deactivate();
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x - 8, y - 8, 8, 8, 16, 16, 2, 2, 0, 0, 0, 16, 16, false, false);
    }

    public void dispose() {
        texture.dispose();
    }
}
