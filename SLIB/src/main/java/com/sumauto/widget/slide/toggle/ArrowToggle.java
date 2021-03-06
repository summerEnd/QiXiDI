package com.sumauto.widget.slide.toggle;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.sumauto.widget.slide.toggle.shape.LineTransformer;
import com.sumauto.widget.slide.toggle.shape.SLine;

public class ArrowToggle extends ToggleView {
    LineTransformer line1, line2, line3;

    public ArrowToggle(Context context) {
        this(context, null);
    }

    public ArrowToggle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArrowToggle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        line1 = new LineTransformer(
                new SLine(),
                new SLine());

        line2 = new LineTransformer(
                new SLine(),
                new SLine());

        line3 = new LineTransformer(
                new SLine(),
                new SLine());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        canvas.save();
        canvas.rotate(180 * (1 - ratio), width / 2, height / 2);
        line1.outPut(ratio).draw(canvas, mPaint);
        line2.outPut(ratio).draw(canvas, mPaint);
        line3.outPut(ratio).draw(canvas, mPaint);


        if (ratio == 1) {
            canvas.drawCircle(getPaddingLeft(), height / 2, 2f, mPaint);
        }
        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int pTop = getPaddingTop();
        int pBottom = getPaddingBottom();
        int pLeft = getPaddingLeft();
        int pRight = getPaddingRight();
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        float finalXRatio = 0.4f;//x轴最终位置与宽度的比例
        float yFloat = 5;//y轴波动
        final float finalX = pLeft + (width - pLeft - pRight) * finalXRatio;
        int midHeight = height / 2;
        line1.getShapeStart().set(pLeft, pTop, width - pRight, pTop);
        line1.getShapeEnd().set(pLeft, midHeight, finalX, pTop - yFloat);

        line2.getShapeStart().set(pLeft, midHeight, width - pRight, midHeight);
        line2.getShapeEnd().set(pLeft, midHeight, width - pRight, midHeight);

        line3.getShapeStart().set(pLeft, height - pBottom, width - pRight, height - pBottom);
        line3.getShapeEnd().set(pLeft, midHeight, finalX, height - pBottom + yFloat);
    }


}
