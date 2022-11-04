import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing

val FastOutExtraSlowInEasing = Easing { fraction ->
	
	val e1 = CubicBezierEasing(.3f, 0f, .8f, .15f)
	val e2 = CubicBezierEasing(.05f, .7f, .1f, 1f)
	
	return@Easing if (fraction < 0.166666f) {
		val e1p = fraction/0.166666f
		0.4f * e1.transform(e1p)
	} else if (fraction == 0.166666f) {
		0.4f
	} else {
		val e2p = (fraction - 0.166666f) / 0.833334f
		0.6f * e2.transform(e2p) + 0.4f
	}
}