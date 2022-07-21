package com.top.react.load

import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import com.airbnb.lottie.LottieAnimationView
import com.top.arch.loading.impl.LoadingView
import com.top.react.R

class ReactNativeLoadingView : LoadingView {

    private lateinit var loadingAnimation: Animation

    private lateinit var dialogView: LinearLayout
    private lateinit var loadingImg: AppCompatImageView


    override fun layout(): Int {
        return R.layout.loading_dialog_rn
    }

    override fun initView(view: View?) {

        checkNotNull(view)

        dialogView = view.findViewById(R.id.dialog_view)
        loadingImg = view.findViewById(R.id.loading_img)
        loadingAnimation = RotateAnimation(
            0f,
            360f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        loadingAnimation.repeatCount=Animation.INFINITE
        loadingAnimation.duration = 1500
        loadingAnimation.interpolator = LinearInterpolator()


    }

    override fun show() {
        dialogView.visibility = View.VISIBLE
        loadingImg.startAnimation(loadingAnimation)
    }

    override fun dismiss() {
        //dialogView.visibility=View.GONE
    }
}