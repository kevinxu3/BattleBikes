package com.kevinxu.battlebikes;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;

/**
 * Created by Kevin on 6/5/2017.
 */

public class GameView extends View{

    private Bike player, CPU;
    private final int SCREEN_WIDTH, SCREEN_HEIGHT;

    public GameView(Context context){
        super(context);
        SCREEN_WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;
        SCREEN_HEIGHT = Resources.getSystem().getDisplayMetrics().heightPixels;
        player = new Bike(SCREEN_WIDTH/5, SCREEN_HEIGHT/2+10, 10, 10, 3, true);
        CPU = new Bike(4*SCREEN_WIDTH/5, SCREEN_HEIGHT/2+10, 10, 10, 3, false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        player.getTrail().onDraw(canvas);
        player.onDraw(canvas);
        CPU.getTrail().onDraw(canvas);
        CPU.onDraw(canvas);
        player.update();
        CPU.update();
        invalidate();
    }

    public void swipeRight(){
        if(player.getDirection() == 0) player.turnRight();
        if(player.getDirection() == 2) player.turnLeft();
    }

    public void swipeLeft(){
        if(player.getDirection() == 0) player.turnLeft();
        if(player.getDirection() == 2) player.turnRight();
    }

    public void swipeDown(){
        if(player.getDirection() == 1) player.turnRight();
        if(player.getDirection() == 3) player.turnLeft();
    }

    public void swipeUp(){
        if(player.getDirection() == 1) player.turnLeft();
        if(player.getDirection() == 3) player.turnRight();
    }

    public void ability(){
        player.ability();
    }

}
