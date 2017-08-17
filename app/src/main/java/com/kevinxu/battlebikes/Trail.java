package com.kevinxu.battlebikes;

import android.graphics.Canvas;
import java.util.ArrayList;

/**
 * Created by Kevin on 6/7/2017.
 */

public class Trail extends ArrayList<Line>{

    protected void onDraw(Canvas canvas) {
        for(Line line: this) line.onDraw(canvas);
    }

}
