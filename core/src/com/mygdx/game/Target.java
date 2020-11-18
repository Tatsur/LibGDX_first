package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sun.tools.javac.code.TargetType;

import java.awt.*;
import java.util.Random;

public class Target {
    private static final Random random = new Random();
    private int x;
    private int y;
    private static Texture texture = new Texture("block.png");
    public static int scale = 3;
    public static int size = 20;

    public Target(int x, int y, Tank tank) {
        this.x = x;
        this.y = y;
        tank.setTarget(this);
    }
    public Dimension getPosition(){
        return new Dimension(x,y);
    }
    public void destroy(){
        newSpawn();
    }
    public void render(SpriteBatch batch){
        batch.draw(texture,x,y,size/2,size/2,size,size,scale,scale,0,0,0,(int)size,(int)size,false,false);
    }
    public void dispose(){
        texture.dispose();
    }
    public void newSpawn(){
        x = random.nextInt(1280-size*scale);
        y = random.nextInt(720-size*scale);
    }
}
