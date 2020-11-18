package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import org.graalvm.compiler.loop.MathUtil;

public class Tank {
    private Texture texture;
    private Texture textureWeapon;
    private float x;
    private float y;
    private float speed;
    private float angle;
    private float angleWeapon;
    private Target target;

    public Projectile getProjectile() {
        return projectile;
    }

    private Projectile projectile;
    private static float scale = 3;
    private static final float width = 40;
    private static final float height = 40;
    private static final float realSizeX = width*scale;
    private static final float realSizeY = height*scale;

    public Tank() {
        this.texture = new Texture("tank.png");
        this.textureWeapon = new Texture("weapon.png");
        this.projectile = new Projectile();
        this.x = 100.0f;
        this.y = 100.0f;
        this.speed = 240.0f;
        this.scale = 3.0f;
    }

    public void update(float dt) {
        movement(dt);

        movementLimit();

        weaponRotate(dt);
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !projectile.isActive()) {
            projectile.shoot(x + 16 * scale * MathUtils.cosDeg(angle), y + 16* scale * MathUtils.sinDeg(angle), angle + angleWeapon,target);
        }
        if (projectile.isActive()) {
            projectile.update(dt);
        }
    }

    private void weaponRotate(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            angleWeapon -= 90.0f * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            angleWeapon += 90.0f * dt;
        }
    }

    private void movement(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            angle -= 90.0f * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            angle += 90.0f * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            x += speed * MathUtils.cosDeg(angle) * dt;
            y += speed * MathUtils.sinDeg(angle) * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            x -= speed * MathUtils.cosDeg(angle) * dt * 0.2f;
            y -= speed * MathUtils.sinDeg(angle) * dt * 0.2f;
        }
    }

    private void movementLimit() {
        if(x<=realSizeX/2) x = realSizeX/2;
        if(y<=realSizeY/2) y = realSizeY/2;
        if(x>=1280-realSizeX/2) x = 1280-realSizeX/2;
        if(y>=720-realSizeY/2) y = 720-realSizeY/2;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x - 20, y - 20, width/2, height/2, width, height, scale, scale, angle, 0, 0, 40, 40, false, false);
        batch.draw(textureWeapon, x - 20, y - 20, width/2, height/2, width, height, scale, scale, angle + angleWeapon, 0, 0, 40, 40, false, false);
        if (projectile.isActive()) {
            projectile.render(batch);
        }
    }

    public void dispose() {
        texture.dispose();
        projectile.dispose();
    }

    public void setTarget(Target target){
        this.target = target;
    }
}
