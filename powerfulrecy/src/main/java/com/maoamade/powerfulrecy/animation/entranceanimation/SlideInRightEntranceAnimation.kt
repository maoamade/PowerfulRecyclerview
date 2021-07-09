package com.maoamade.powerfulrecy.animation.entranceanimation

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.DecelerateInterpolator

/**

 * @Author maoamade

 * @Date 2021/6/29 3:11 PM

 * @Info SlideInRightAnimation.kt

 */
class SlideInRightEntranceAnimation :
    BaseEntranceAnimation {

    override fun animators(view: View): Array<Animator> {
        val animator = ObjectAnimator.ofFloat(view,"TranslationX",view.rootView.width.toFloat(),0f)
        animator.duration = 400
        animator.interpolator = DecelerateInterpolator(1.8f)
        return arrayOf(animator)
    }

}