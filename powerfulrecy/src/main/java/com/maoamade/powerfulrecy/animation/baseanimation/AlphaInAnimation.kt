package com.maoamade.powerfulrecy.animation.baseanimation

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.LinearInterpolator
import com.maoamade.powerfulrecy.animation.BaseAnimation

/**

 * @Author maoamade

 * @Date 2021/6/29 10:58 AM

 * @Info AlphaInAnimation
 */
class AlphaInAnimation :BaseAnimation{

    override fun animators(view: View): Array<Animator> {
        val animator = ObjectAnimator.ofFloat(view,"alpha", DEFAULT_ALPHA_FROM,1F)
        animator.duration = 300L
        animator.interpolator = LinearInterpolator()
        return arrayOf(animator)
    }

    companion object{
        private const val DEFAULT_ALPHA_FROM = 0F
    }

}