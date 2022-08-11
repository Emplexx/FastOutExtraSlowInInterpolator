# FastOutExtraSlowInInterpolator

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
