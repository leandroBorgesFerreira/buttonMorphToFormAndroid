package br.com.hinovamobile.loginbuttontoform;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;

public class MorphAnimation {

    private final View parentView;
    private ViewGroup buttonGroup;

    private boolean isPressed;

    private int initialWidth;
    private final float finalWidth;
    private int initialHeight;
    private final float finalHeight;

    private float initialX;
    private float initialY;
    private float finalX;
    private float finalY;

    public boolean isPressed() {
        return isPressed;
    }

    public MorphAnimation(ViewGroup buttonGroup, View parentView, float finalWidth, float finalHeight) {
        this.buttonGroup = buttonGroup;
        this.parentView = parentView;
        this.finalWidth = finalWidth;
        this.finalHeight = finalHeight;


    }

    public void morphIntoForm() {
        initialWidth = buttonGroup.getWidth();
        initialHeight = buttonGroup.getHeight();

        initialX = buttonGroup.getX();
        initialY = buttonGroup.getY();

        finalX = (parentView.getWidth() - finalWidth) / 2;
        finalY = (parentView.getHeight() - finalHeight) / 2;

        float deltaX = finalX - initialX + finalWidth / 3;
        float deltaY = finalY - initialY + finalHeight * 5 / 6;

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

//        heightAnimator.setStartDelay(100);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());

        animatorSet.playTogether(translateX, translateY, widthAnimator, heightAnimator);
        animatorSet.start();
    }

}
