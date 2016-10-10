package br.com.hinovamobile.loginbuttontoform;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewParent viewParent;

        isPressed = false;



        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.form_btn);

        final Button buttonInside = (Button) findViewById(R.id.button_inside_group);

        buttonInside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!isPressed) {
                    isPressed= true;

                    ViewGroup group = (ViewGroup) view.getParent().getParent();
                    float midX = group.getWidth() / 2;
                    float midY = group.getHeight() / 2;

                    finalWidth = linearLayout.getWidth() * 1.3;
                    finalHeigth = linearLayout.getHeight() * 8;

                    //float deltaX = view.getTranslationX() + linearLayout.getWidth()/2 - midX ;
                    deltaX = 0;
                    deltaY = view.getTranslationY() + finalHeigth / 2 - midY;

                    ObjectAnimator translateX = ObjectAnimator.ofFloat(linearLayout, "translationX", deltaX);
                    ObjectAnimator translateY = ObjectAnimator.ofFloat(linearLayout, "translationY", deltaY);

                    ValueAnimator valueAnimatorWidth =
                            ValueAnimator.ofInt(linearLayout.getWidth(), finalWidth.intValue());

                    valueAnimatorWidth.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            int val = (Integer) valueAnimator.getAnimatedValue();
                            ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
                            layoutParams.width = val;
                            linearLayout.setLayoutParams(layoutParams);
                        }
                    });

                    ValueAnimator valueAnimatorHeight =
                            ValueAnimator.ofInt(linearLayout.getHeight(), finalHeigth);

                    valueAnimatorHeight.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            int val = (Integer) valueAnimator.getAnimatedValue();
                            ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
                            layoutParams.height = val;
                            linearLayout.setLayoutParams(layoutParams);
                        }
                    });

                    valueAnimatorHeight.setStartDelay(100);

                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());

                    ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(buttonInside,
                            "alpha",
                            buttonInside.getAlpha(),
                            0);

                    alphaAnimator.setDuration(150);

                    animatorSet.playTogether(alphaAnimator, translateX, translateY,
                            valueAnimatorWidth, valueAnimatorHeight);

                    animatorSet.start();
                } else{
                    isPressed = false;
                    ObjectAnimator translateX = ObjectAnimator.ofFloat(linearLayout, "translationX", -deltaX);
                    ObjectAnimator translateY = ObjectAnimator.ofFloat(linearLayout, "translationY", -deltaY);

                    finalHeigth = finalHeigth / 8;

                    ValueAnimator valueAnimatorHeight =
                            ValueAnimator.ofInt(linearLayout.getHeight(), finalHeigth);

                    valueAnimatorHeight.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            int val = (Integer) valueAnimator.getAnimatedValue();
                            ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
                            layoutParams.height = val;
                            linearLayout.setLayoutParams(layoutParams);
                        }
                    });

                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());

                    animatorSet.playTogether(translateX, translateY, valueAnimatorHeight);

                    animatorSet.start();
                }

            }
        });
    }
}
