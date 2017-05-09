package br.com.hinovamobile.loginbuttontoform;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ViewAnimator;

public class MainActivity extends AppCompatActivity {

    private boolean isPressed;

    private float initialWidth;
    private Double finalWidth;

    private float initialHeigth;
    private int finalHeigth;

    private float deltaX;
    private float deltaY;

    private MorphAnimation morphAnimationLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ViewGroup buttonGroup = (ViewGroup) findViewById(R.id.form_btn);
        final Button buttonInside = (Button) findViewById(R.id.button_inside_group);
        final FrameLayout rootView = (FrameLayout) findViewById(R.id.root_view);

        buttonInside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (morphAnimationLogin == null) {
                    morphAnimationLogin = new MorphAnimation(
                            MainActivity.this,
                            buttonGroup,
                            buttonInside,
                            rootView,
                            buttonGroup.getWidth() * 1.6f,
                            buttonGroup.getHeight() * 6);
                }

                if (!morphAnimationLogin.isPressed()) {
//                    morphAnimationLogin.morphIntoForm();
                    morphAnimationLogin.changeGravity();
                } else {
                    morphAnimationLogin.morphIntoButton();
                }

            }
        });
    }

    public static int convertDpToPixels(float dp, Context context) {
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
        return px;
    }
}
