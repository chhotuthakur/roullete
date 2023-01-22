package com.nileshsdahiya.roullete;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.content.pm.ActivityInfo;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final Matrix matrix = new Matrix();

    private ImageView imageView;
    private Drawable drawable;
    private float scaleX;
    private float scaleY;
    private float pivotX;
    private float pivotY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        imageView = findViewById(R.id.image_view);
        drawable = imageView.getDrawable();

        findViewById(R.id.button).setOnClickListener(v -> {
            ValueAnimator animator = ValueAnimator.ofFloat(0F, 2900F);
            animator.setDuration(2000L);
            animator.addUpdateListener(a -> rotate((float) animator.getAnimatedValue()));
            animator.start();
        });

        // The post() delays this until after the first layout.
        imageView.post(this::initializeMatrix);
    }

    private void initializeMatrix() {
        final float width = (float) drawable.getIntrinsicWidth();
        final float height = (float) drawable.getIntrinsicHeight();
        pivotX = width / 2F;
        pivotY = height / 2F;
        scaleX = imageView.getWidth() / width;
        scaleY = imageView.getHeight() / height;
        rotate(0F);
    }

    private void rotate(float rotation) {
        matrix.setRotate(rotation, pivotX, pivotY);
        matrix.postScale(scaleX, scaleY);
        imageView.setImageMatrix(matrix);
    }




           }


