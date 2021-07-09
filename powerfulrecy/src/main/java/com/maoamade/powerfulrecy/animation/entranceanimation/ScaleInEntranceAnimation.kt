package com.maoamade.powerfulrecy.animation.entranceanimation

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.LinearInterpolator

/**

 * @Author maoamade

 * @Date 2021/6/29 10:58 AM

 * @Info ScaleInAnimation
 */

class ScaleInEntranceAnimation :
    BaseEntranceAnimation {

    override fun animators(view: View): Array<Animator> {
        val animatorX = ObjectAnimator.ofFloat(view,"ScaleX",DEFAULT_SCALE_FROM,1f)
        animatorX.duration = 300L
        animatorX.interpolator = LinearInterpolator()

        val animatorY = ObjectAnimator.ofFloat(view,"ScaleY",DEFAULT_SCALE_FROM,1f)
        animatorY.duration = 300L
        animatorY.interpolator = LinearInterpolator()

        return arrayOf(animatorX,animatorY)
    }

    companion object{
        private const val DEFAULT_SCALE_FROM = .5F
    }
}