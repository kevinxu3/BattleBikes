package com.kevinxu.battlebikes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Kevin on 6/7/2017.
 */

public class Line {

    private int top, left, right, bottom, direction;
    private Rect box;
    private Paint paint;

    public Line(int x, int y, int w, int h, int direction, Paint paint){
        left = x-(w/2);
        top = y-(h/2);
        right = x+w;
        bottom = y+h;
        this.direction = direction;
        this.paint = paint;

        box = new Rect(left, top, right, bottom);
    }

    public void update(int x, int y){
        if(direction == 0) top = y;
        else if(direction == 1) right = x;
        else if(direction == 2) bottom = y;
        else left = x;
        box = new Rect(left, top, right, bottom);
    }

    public Rect getBox(){
        return box;
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawRect(box, paint);
    }

}
