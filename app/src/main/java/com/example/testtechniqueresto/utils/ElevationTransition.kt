package com.example.testtechniqueresto.utils

import android.animation.Animator
import android.animation.ValueAnimator
import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.transition.Transition
import android.transition.TransitionValues
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup


@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class ElevationTransition(context: Context?, attrs: AttributeSet?) : Transition(context, attrs) {

    companion object {
        private const val PROPNAME_ELEVATION = "my.elevation:transition:elevation"
    }

    override fun captureStartValues(transitionValues: TransitionValues) {
        captureValues(transitionValues)
    }

    override fun captureEndValues(transitionValues: TransitionValues) {
        captureValues(transitionValues)
    }

    private fun captureValues(transitionValues: TransitionValues) {
        val elevation: Float = transitionValues.view.elevation
        transitionValues.values[PROPNAME_ELEVATION] = elevation
    }

    override fun createAnimator(
        sceneRoot: ViewGroup,
        startValues: TransitionValues,
        endValues: TransitionValues
    ): Animator? {
        val startVal = startValues.values[PROPNAME_ELEVATION] as? Float?
        val endVal = endValues.values[PROPNAME_ELEVATION] as? Float?

        if (startVal == null || endVal == null || startVal == endVal) {
            return null
        }

        val view: View = endValues.view
        val a = ValueAnimator.ofFloat(startVal, endVal)

        a.addUpdateListener { animation -> view.elevation = animation.animatedValue as Float }
        return a
    }
}