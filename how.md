The way I “made” the `Interpolator` was me going into the .xml file of the interpolator, copying its path, throwing that path at a bunch of random APIs until finding the one that works (converting the raw path data into an android `Path`), and using the existing Interpolator API that allows you to create an `Interpolator` from a `Path`. 

The Compose part wasn’t as easy because the API only has an `Easing` interface (and a `CubicBezierEasing` class, but more on that later). The interface only has a `transform()` function which from a 0.0-1.0 float `fraction` (the time of whatever ongoing animation) should return a float between 0.0-1.0 which will be used for the easing.

My first thought was that I should at the very least understand what the raw path data I got from that .xml file means, and conveniently enough there was a [website that allowed me to do exactly that](https://svg-path-visualizer.netlify.app/#M%200%2C0%20C%200.05%2C%200%2C%200.133333%2C%200.06%2C%200.166666%2C%200.4%20C%200.208333%2C%200.82%2C%200.25%2C%201%2C%201%2C%201).

![mainpath](https://user-images.githubusercontent.com/64900852/199981855-71f874f7-3460-4e50-872d-2bd39b68fe72.png)

After being lost for some more time I have come to a realisation that this path is nothing but two cubic bezier curves joined together:

![p1](https://user-images.githubusercontent.com/64900852/199982528-ca2b059a-b161-4331-8287-80619324f497.png)
![p2](https://user-images.githubusercontent.com/64900852/199982546-fc2dc10b-f45c-4eee-adea-b94dee76a795.png)


And since Compose already has a `CubicBezierEasing` class, I could calculate the values for both curves, and then make `transform()` return the corresponding values based on the “fraction” float. 

So that’s exactly what I did. 

```
val FastOutExtraSlowInEasing = Easing { fraction -> 
	
  // the "FastOut" curve
  val e1 = CubicBezierEasing(.3f, 0f, .8f, .15f)
  // the "ExtraSlowIn" curve
  val e2 = CubicBezierEasing(.05f, .7f, .1f, 1f)
	
  // the curves are joined at 1/6 (0.166666f) of the duraction and 2/5 (0.4f) of the delta, so:
  // if fraction is less than 0.166666f...
  return@Easing if (fraction < 0.166666f) {
    // convert 0.0 - 0.166666f fraction to 0.0 - 1.0f
    val e1p = fraction/0.166666f
    // use it with the first bezier curve, convert the 0.0 - 1.0f output to 0.0 - 0.4f, return it
    0.4f * e1.transform(e1p)
  } else if (fraction == 0.166666f) {
    0.4f
  } else {
    // convert 0.166667 - 1.0 fraction to 0.0 - 1.0f
    val e2p = (fraction - 0.166666f) / 0.833334f
    // use it with the second bezier curve, convert the 0.0 - 1.0f output to 0.4 - 1.0f, return it
    0.6f * e2.transform(e2p) + 0.4f
  }
}
```



Note: After doing all of that and updating this repo with my results, I, by pure accident, came across [this tweet](https://twitter.com/jonasnaimark/status/1187857581030555649) that basically gives out all of the solutions. I didn’t even have to do all of the calculations myself, it was out there all along. I hate my life
