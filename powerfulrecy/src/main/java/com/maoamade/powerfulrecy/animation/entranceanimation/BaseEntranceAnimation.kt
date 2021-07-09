package com.maoamade.powerfulrecy.animation.entranceanimation

import android.animation.Animator
import android.view.View

/**

 * @Author maoamade

 * @Date 2021/6/29 10:58 AM

 * @Info BaseEntranceAnimation  the animation play when the holder attach to the window
 */
interface BaseEntranceAnimation{
    fun animators(view:View):Array<Animator>
}