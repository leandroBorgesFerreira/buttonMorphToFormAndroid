package br.com.hinovamobile.loginbuttontoform;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MorphAnimation {

    private final FrameLayout parentView;
    private ViewGroup buttonGroup;
    private Button button;

    private boolean isPressed;

    private int initialWidth;
    private final float finalWidth;
    private int initialHeight;
    private final float finalHeight;

    private float initialX;
    private float initialY;
    private float finalX;
    private float finalY;

    Context context;

    public boolean isPressed() {
        return isPressed;
    }

    public MorphAnimation(Context context, ViewGroup buttonGroup, Button button, FrameLayout parentView, float finalWidth, float finalHeight) {
        this.buttonGroup = buttonGroup;
        this.parentView = parentView;
        this.finalWidth = finalWidth;
        this.finalHeight = finalHeight;
        this.button = button;

        this.context = context;

    }

    public void changeGravity() {
        LayoutTransition layoutTransition = parentView.getLayoutTransition();
        layoutTransition.setDuration(400);
        layoutTransition.enableTransitionType(LayoutTransition.CHANGING);


        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) buttonGroup.getLayoutParams();
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.width = buttonGroup.getWidth() * 3;
        buttonGroup.setLayoutParams(layoutParams);


        parentView.findViewById(R.id.name).setVisibility(View.VISIBLE);
        parentView.findViewById(R.id.age).setVisibility(View.VISIBLE);
        parentView.findViewById(R.id.phone).setVisibility(View.VISIBLE);
    }

    public void morphIntoForm() {
        initialWidth = buttonGroup.getWidth();
        initialHeight = buttonGroup.getHeight();

        initialX = buttonGroup.getX();
        initialY = buttonGroup.getY();

        finalX = (parentView.getWidth() - finalWidth) / 2;
        finalY = (parentView.getHeight() - finalHeight) / 2;

        float deltaX = finalX - initialX;
        float deltaY = finalY - initialY;

        final EditText nameEt = (EditText) parentView.findViewById(R.id.name);

        ObjectAnimator translateX = ObjectAnimator.ofFloat(buttonGroup, "translationX", deltaX);
        ObjectAnimator translateY = ObjectAnimator.ofFloat(buttonGroup, "translationY", deltaY);

        ValueAnimator widthAnimator = ValueAnimator.ofFloat(initialWidth, finalWidth);

        widthAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Float val = (Float) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = buttonGroup.getLayoutParams();
                layoutParams.width = val.intValue();
                buttonGroup.setLayoutParams(layoutParams);
            }
        });

        widthAnimator.setStartDelay(1000);

        ValueAnimator heightAnimator = ValueAnimator.ofFloat(initialHeight, finalHeight);

        heightAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Float val = (Float) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = buttonGroup.getLayoutParams();
                layoutParams.height = val.intValue();
                buttonGroup.setLayoutParams(layoutParams);
            }
        });

        heightAnimator.setStartDelay(2000);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                button.setEnabled(false);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                button.setEnabled(true);
                isPressed = true;
                nameEt.setWidth((buttonGroup.getWidth()));
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        animatorSet.playTogether(translateX, translateY, widthAnimator, heightAnimator);
        animatorSet.start();
    }

    public void morphIntoButton() {
        finalX = initialX - finalX + (finalWidth - initialWidth) / 2 ;
        finalY = initialY - finalY + (finalHeight - initialHeight) / 2;

        float deltaX = initialX - finalX;
        float deltaY = initialY - finalY;

        ObjectAnimator translateX = ObjectAnimator.ofFloat(buttonGroup, "translationX", deltaX);
        ObjectAnimator translateY = ObjectAnimator.ofFloat(buttonGroup, "translationY", deltaY);

        ValueAnimator widthAnimator = ValueAnimator.ofFloat(finalWidth, initialWidth);

        widthAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Float val = (Float) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = buttonGroup.getLayoutParams();
                layoutParams.width = val.intValue();
                buttonGroup.setLayoutParams(layoutParams);
            }
        });

        widthAnimator.setStartDelay(1000);

        ValueAnimator heightAnimator = ValueAnimator.ofFloat(finalHeight, initialHeight);

        heightAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Float val = (Float) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = buttonGroup.getLayoutParams();
                layoutParams.height = val.intValue();
                buttonGroup.setLayoutParams(layoutParams);
            }
        });

        heightAnimator.setStartDelay(2000);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                button.setEnabled(false);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                button.setEnabled(true);
                isPressed = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        animatorSet.playTogether(translateX, translateY, widthAnimator, heightAnimator);
        animatorSet.start();
    }

}
