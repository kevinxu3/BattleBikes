package com.kevinxu.battlebikes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Kevin on 6/7/2017.
 */

public class Bike {

    private int x, y, w, h, startX, startY;
    private int speed, normalSpeed, xv, yv;
    private int direction, trailCount;
    private Paint paint;
    private Paint trailPaint;

    private int abilityCount;
    private Timer timer;
    private TimerTask task;
    private int boostSpeed;

    private Rect box;
    private Trail trail;
    private boolean start;

    public Bike(int x, int y, int w, int h, final int speed, boolean blue){
        this.x = x;
        this.y = y;
        startX = x;
        startY = y;
        this.w = w;
        this.h = h;
        this.speed = speed;
        normalSpeed = speed;
        trailCount = 0;
        paint = new Paint();
        trailPaint = new Paint();
        if(blue){
            paint.setColor(Color.BLUE);
            trailPaint.setColor(Color.rgb(0, 204, 255));
            direction = 1;
            xv = speed;
            yv = 0;
        }
        else{
            paint.setColor(Color.RED);
            trailPaint.setColor(Color.rgb(255, 80, 80));
            direction = 3;
            xv = -speed;
            yv = 0;
        }

        boostSpeed = speed*3;
        abilityCount = 3;
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                setSpeed(normalSpeed);
            }
        };
        box = new Rect(x-(w/2), y-(h/2), x+w, y+h);

        trail = new Trail();
        trail.add(new Line(startX, startY, w, h, direction, trailPaint));
        start = true;
    }

    public void turnRight(){
        if(direction == 0){
            xv = speed;
            yv = 0;
            direction = 1;
        }
        else if(direction == 1){
            xv = 0;
            yv = speed;
            direction = 2;
        }
        else if(direction == 2){
            xv = -speed;
            yv = 0;
            direction = 3;
        }
        else{
            xv = 0;
            yv = -speed;
            direction = 0;
        }
        startX = x;
        startY = y;
        trail.add(new Line(startX, startY, w, h, direction, trailPaint));
        trailCount++;
    }

    public void turnLeft(){
        if(direction == 0){
            xv = -speed;
            yv = 0;
            direction = 3;
        }
        else if(direction == 1){
            xv = 0;
            yv = -speed;
            direction = 0;
        }
        else if(direction == 2){
            xv = speed;
            yv = 0;
            direction = 1;
        }
        else{
            xv = 0;
            yv = speed;
            direction = 2;
        }
        startX = x;
        startY = y;
        trail.add(new Line(startX, startY, w, h, direction, trailPaint));
        trailCount++;
    }

    public void setSpeed(int speed){
        this.speed = speed;
        if(xv > 0) xv = speed;
        if(xv < 0) xv = -speed;
        if(yv > 0) yv = speed;
        if(yv < 0) yv = -speed;
    }

    public void ability(){
        task = new TimerTask() {
            @Override
            public void run() {
                setSpeed(normalSpeed);
            }
        };
        if(abilityCount > 0){
            setSpeed(boostSpeed);
            timer.schedule(task, 1500);
            abilityCount--;
        }
    }

    public void update(){
        if(start){
            x += xv;
            y += yv;
            trail.get(trailCount).update(x, y);
            box = new Rect(x-(w/2), y-(h/2), x+w, y+h);
        }
    }

    public Trail getTrail(){
        return trail;
    }

    public void explode(){
        xv = 0;
        yv = 0;
    }

    public int getDirection(){
        return direction;
    }

    public void start(){
        start = true;
    }

    public void stop(){
        start = false;
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawRect(box, paint);
    }

}
