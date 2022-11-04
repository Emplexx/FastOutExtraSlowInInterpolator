# FastOutExtraSlowInInterpolator.kt

![Speech Bubble of FastOutExtraSlowInInterpolator marked as unresolved reference](https://github.com/Emplexx/FastOutExtraSlowInInterpolator/blob/main/funnyimage.png)

A function that returns an Interpolator corresponding to [fast_out_extra_slow_in](https://developer.android.com/reference/android/R.interpolator.html#fast_out_extra_slow_in), since it doesn't exist in the [androidx.interpolator.view.animation](https://developer.android.com/reference/kotlin/androidx/interpolator/view/animation/package-summary) library by default for some reason.

## Usage example
```
...

val transform = MaterialContainerTransform()
transform.duration = 300
transform.interpolator = FastOutExtraSlowInInterpolator()

...
```

# FastOutExtraSlowInEasing.kt

![Speech Bubble of FastOutExtraSlowInEasing marked as unresolved reference](https://user-images.githubusercontent.com/64900852/199969599-4babeeb5-333b-4aa7-87fe-c2a9d871ece3.png)

An Easing variable that corresponds to [fast_out_extra_slow_in](https://developer.android.com/reference/android/R.interpolator.html#fast_out_extra_slow_in), since it doesn't exist in the [androidx.compose.animation.core](https://developer.android.com/reference/kotlin/androidx/compose/animation/core/package-summary) by default **either**.

## Usage example
```
...

val anim by animateDpAsState(
	targetValue = if (state) 16.dp else 200.dp ,
	animationSpec = tween(
		durationMillis = 500,
		easing = FastOutExtraSlowInEasing
	)
)

...
```
