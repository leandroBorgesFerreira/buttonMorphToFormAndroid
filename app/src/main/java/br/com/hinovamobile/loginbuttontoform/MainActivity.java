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

    private MorphAnimation morphAnimationLogin;
    private MorphAnimation morphAnimationRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View loginContainer = findViewById(R.id.form_btn);
        View registeContainer = findViewById(R.id.form_register);

        Button buttonLogin = (Button) findViewById(R.id.button_inside_group);
        Button buttonRegister = (Button) findViewById(R.id.button_register);

        ViewGroup loginViews = (ViewGroup) findViewById(R.id.login_views);
        ViewGroup registeViews = (ViewGroup) findViewById(R.id.register_views);

        final FrameLayout rootView = (FrameLayout) findViewById(R.id.root_view);

        morphAnimationLogin = new MorphAnimation(loginContainer, rootView, loginViews);
        morphAnimationRegister = new MorphAnimation(registeContainer, rootView, registeViews);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!morphAnimationLogin.isPressed()) {
                    morphAnimationLogin.morphIntoForm();
                } else {
                    morphAnimationLogin.morphIntoButton();
                }
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!morphAnimationRegister.isPressed()) {
                    morphAnimationRegister.morphIntoForm();
                } else {
                    morphAnimationRegister.morphIntoButton();
                }
            }
        });
    }
}
