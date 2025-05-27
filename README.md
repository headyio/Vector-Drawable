# Vector-Drawable
Android Vector Drawable by Hithesh Vurjana

https://github.com/user-attachments/assets/b3e613bf-d1d0-4b0a-9c78-5f1234a7f084

https://giphy.com/gifs/FTHQpme5PUuYCquoIB

In the ever-evolving world of mobile app development, smooth and engaging animations are key to creating intuitive user experiences. **Animated Vector Drawables (AVDs)** empower Android developers to bring static icons and graphics to life with lightweight, scalable animations. This article explores what AVDs are, their benefits, and how to implement them effectively.

You can get the code used in this article on [**GitHub**](https://github.com/headyio/Vector-Drawable/tree/main).

<https://github.com/headyio/Vector-Drawable/tree/main>

**Why Animate?**

While static images have their place, animated elements can significantly enhance the user experience, provide feedback, guide attention, and add a touch of polish.

As per Apple design guidelines,

Beautiful, fluid motions bring the interface to life, conveying status, providing feedback and instruction, and enriching the visual experience of your app or game.

As per Material Design guidelines,

Motion makes a UI expressive and easy to use. It can make products more usable when used in transitions and interactions.

**What Are Animated Vector Drawables?**

AVDs are vector graphics defined in XML whose properties can be animated programmatically. Unlike traditional raster-based animations (like GIFs or PNG sequences), vector graphics scale seamlessly without losing quality, making them ideal for Android's diverse screen sizes. AVDs take this a step further by allowing you to define animations within the XML itself. These animations are fire-forget type animations, introduced in API 21. From API 25 AVDs run on RenderThread which previously ran on UI thread. So from API 25 animations will be smooth even if heavy tasks are performed on the UI thread. AVDs allow you to animate attributes like:

-   Path morphing (changing shapes)
-   Rotation, scale, and translation
-   Stroke color and fill color
-   Alpha (transparency)
-   Repetitions

**AVD Core Components**

1.  **VectorDrawable** <vector>: Defines the initial static graphic using XML paths and shapes.
2.  **AnimatedVectorDrawable** <animated-vector>: Links the VectorDrawable to animations.
3.  **ObjectAnimator/AnimatorSet** <objectAnimator>: Specifies how properties change over time.

AVD applies the animation <objectAnimator> on the vector drawable <vector>.

You can define an Animated Vector Drawable in two file structures: a [**single file**](https://developer.android.com/reference/android/graphics/drawable/AnimatedVectorDrawable.html#define-an-animatedvectordrawable-all-in-one-xml-file), which is easier to maintain, and [**multiple files**](https://developer.android.com/reference/android/graphics/drawable/AnimatedVectorDrawable.html#define-an-animatedvectordrawable-in-three-separate-xml-files), which allow reusability. In this article, we will use single-file AVDs.

**Why Use AVDs?**

-   **Small File Size:** Vector graphics are inherently smaller than raster image sequences (e.g., PNGs).
-   **Scalability:** AVDs look sharp on any screen size, eliminating the need for multiple image assets.
-   **Maintainability:** Animations are defined in XML, making them easier to manage and modify.
-   **Performance:** AVDs are hardware accelerated, resulting in smooth and efficient animations. Lightweight XML files consume fewer resources than bitmap animations.
-   **Flexibility:** AVDs can be used for various animations, from simple loading spinners to complex transitions. You can also animate specific parts of a graphic (e.g., a single path in a complex icon) without needing multiple PNG/SVG files.

**Prerequisites**

Before we explore using AVDs, we must know some common animation concepts. 

**Transformations**

In Android Vector Drawables, transformations like **rotation**, **scale**, **translate**, and **pivot** are applied to <group> elements within the <vector> XML definition. These attributes allow you to manipulate paths or subgroups of your drawable. Let's look at each property and how they work:

**1\. rotation**

Rotates the group by a specified angle (in degrees). The rotation occurs around the pivot point defined by pivotX and pivotY. Units are 0 to 360.

https://giphy.com/gifs/PeSHSn6yLkHD5IiCfX

**2\. scaleX & scaleY**

Scales the group along the X-axis (scaleX) or Y-axis (scaleY). Units are  1.0 = original size, 2.0 = double size, 0.5 = half size. Scaling is relative to the pivot point (pivotX, pivotY).

https://giphy.com/gifs/avMalBHcvi26XFHtXP

**3\. pivotX & pivotY**

Define the center point (pivot) for transformations like rotation, scaling, or translation. Units can be in Absolute pixels (e.g., "12") or Percentage of the drawable's width/height (e.g., "50%" for center) or Fraction (e.g., "0.5" equivalent to 50%).

**4\. translateX & translateY**

Moves the group along the X-axis (translateX) or Y-axis (translateY). Units are in pixels (relative to the original position) of the viewport.

https://giphy.com/gifs/V0FyqJOr2lvXneM2Ga

**Things to note**

-   Transformations are applied in the order: pivot → scale → rotation → translation. This means that scaling happens before rotation.
-   Forgetting to set pivotX/pivotY can cause unexpected rotations/scaling (the default is the top-left corner (0,0)).
-   Mixing absolute (pixels) and relative (%) units incorrectly can cause issues.

**Interpolations**

Interpolation is often used in animation, UI design, and other areas where smooth transitions are needed. They describe how a value changes over time, creating different feelings of movement.

-   **Interpolation:** The process of determining intermediate values between two known values. In animation, this means calculating the positions, sizes, opacities, etc., of an object between its start and end states. In Android, it defines the rate of change of an animation.
-   **Easing:** A way to control the rate of change during interpolation. It makes animations feel more natural and less robotic. The terms below describe different easing functions.

These common interpolation types offer a range of ways to control motion. These are standard interpolators, which you can use in ObjectAnimator or ValueAnimator: 

**1\. Fast Out, Slow In (Ease-out):** Starts quickly (accelerates) and ends slowly (decelerates). Think of a ball thrown into the air -- it starts fast and slows as it reaches its peak. Used in smooth transitions (e.g., FAB opening/closing). XML attribute @android:interpolator/fast_out_slow_in.

https://giphy.com/gifs/fastoutslowin-eCmvzFUVplZQBN5Sti

**2\. Linear Out, Slow In (Ease-in):** Starts at a constant speed (linear) and ends with deceleration. Used in elements exiting the screen (e.g., sliding out). XML attribute  @android:interpolator/linear_out_slow_in.

https://giphy.com/gifs/linearoutslowin-eXlolWSWqqkjwaTiJC

**3\. Fast Out, Linear In:** Starts with acceleration and ends at a constant speed. Used in elements entering the screen (e.g., sliding in). XML attribute  @android:interpolator/fast_out_linear_in.

https://giphy.com/gifs/fastoutlinearin-S86FVgHyC1xfFJwHDc

**4\. Accelerate/Decelerate (Ease-in-out):** Smoothly accelerates and then decelerates symmetrically. The animation starts slowly, speeds up in the middle, and then slows down at the end. Used in natural motion (e.g., object moving like a pendulum). XML attribute  @android:anim/accelerate_decelerate_interpolator.

https://giphy.com/gifs/acceleratedecelerateinterpolator-CGs47B9tWRd4bYA01T

**5\. Accelerate (Ease-in):** Speed increases over time (starts slow, ends fast). Used in objects speeding up (e.g., falling under gravity). XML attribute  @android:anim/accelerate_interpolator.

https://giphy.com/gifs/accelerateinterpolator-edCHvHMXEVg3BC4npK

**6\. Decelerate (Ease-out):** Speed decreases over time (starts fast, ends slow). Used in objects slowing down (e.g., coming to rest). XML attribute  @android:anim/decelerate_interpolator.

https://giphy.com/gifs/decelerateinterpolator-3kYicIAOqwHIQcHqW4

**7\. Linear:** The value changes at a constant rate. This creates a very mechanical, unnatural feel. It's often used as a baseline for comparison. Used in predictable, mechanical motion. XML attribute  @android:anim/linear_interpolator.

https://giphy.com/gifs/linearinterpolator-jFBIh8fWFPj3IF4rPY

**8\. Anticipate:** The animation briefly moves backward or in the opposite direction before moving forward. This creates a sense of anticipation and makes the movement more engaging. Think of a spring being pulled back before it's released. XML attribute  @android:anim/anticipate_interpolator.

https://giphy.com/gifs/anticipateinterpolator-uTMrJd1pKtJ3dsUE3W

**9\. Overshoot:** The animation goes *past* the target value and then comes back. This creates a bouncy or energetic effect. Think of a ball bouncing -- it goes higher than its resting point before settling. XML attribute @android:anim/overshoot_interpolator.

https://giphy.com/gifs/overshootinterpolator-cJIN7eYhe2nQPt9Q1Q

**10\. Anticipate + Overshoot:** Combines both anticipate (backward motion) and overshoot (forward overshoot). The animation moves back slightly, then goes past the target, and finally settles at the target. Used in complex animations with emphasis on motion dynamics. XML attribute  @android:anim/anticipate_overshoot_interpolator.

https://giphy.com/gifs/anticipateovershootinterpolator-LdhCX4y6xJFLlwjuk5

**11\. Bounce:** Ends with a bouncing effect (like a ball hitting the ground). Used in playful animations (e.g., notifications, icons). XML attribute  @android:anim/bounce_interpolator.

https://giphy.com/gifs/bounceinterpolator-ZEhNAKbOmtHwykqkaC

**How to Apply Interpolators in XML**

Use the android:interpolator attribute in your animator XML (e.g., objectAnimator). The animator below will rotate with a bounce effect:
```
<objectAnimator

    android:propertyName="rotation"

    android:duration="1000"

    android:valueFrom="0"

    android:valueTo="360"

    android:interpolator="@android:anim/bounce_interpolator" />
```
**Things to note**

-   Use fast_out_slow_in, linear_out_slow_in, and fast_out_linear_in for Material Motion compliance.
-   Define your custom interpolator using PathInterpolator (e.g., cubic Bézier curves).

**Creating an Animated Vector Drawable**

We will try to create almost all the Vector Drawables in the GitHub project and explore different movements and effects in animation.

We know, an AVD consists of two main parts: the <vector> drawable and the <animated-vector>. We will mostly use [**Material Icons**](https://fonts.google.com/icons) to get ready-made SVGs which we then convert to drawables in Android Studio to implement in our AVDs. Still, if you want to create custom SVG icons you can do it the hard way using SVG Path editors like [**Vectr**](https://vectr.com/) or [**SVG Path Editor**](https://yqnn.github.io/svg-path-editor/) or [**SVG Path Visualiser**](https://svg-path-visualizer.netlify.app/) to draw the vector icon. But in this article, we will use [**Inkscape**](https://inkscape.org/release/inkscape-1.4/), a free open-source vector graphics tool like Adobe Illustrator to edit or create vector drawables. Then to create <animated-vector>Drawable part we will use a web-based IDE called [**Shape Shifter**](https://shapeshifter.design/).

Let's look at the code for one of the AVDs from the GitHub project. We will animate an "open door home" icon transforming into a "closed door home" icon. This morphs the path commands in a 24x24 viewport. The full AVD looks like this avd_home_forward.xml:
```
<animated-vector

    xmlns:android="http://schemas.android.com/apk/res/android"

    xmlns:aapt="http://schemas.android.com/aapt">

    <aapt:attr name="android:drawable">

        <vector

            android:name="vector"

            android:width="24dp"

            android:height="24dp"

            android:viewportWidth="24"

            android:viewportHeight="24">

            <path

                android:name="home"

                android:pathData="M 12 2 L 3.111 8.667 L 3.111 22 L 6.445 22 L 6.445 19.778 L 5.334 19.778 L 5.334 9.778 L 12 4.778 L 18.667 9.778 L 18.667 19.778 L 17.556 19.778 L 17.556 22 L 20.889 22 L 20.889 8.667 Z"

                android:fillColor="#000"

                android:strokeWidth="1"/>

            <path

                android:name="door_support"

                android:pathData="M 8.667 13.111 L 8.667 22 L 8.895 22 L 8.895 13.111 Z"

                android:fillColor="#000"

                android:strokeWidth="1"/>

        </vector>

    </aapt:attr>

    <target android:name="door_support">

        <aapt:attr name="android:animation">

            <objectAnimator

                android:propertyName="pathData"

                android:duration="800"

                android:valueFrom="M 8.667 13.111 L 8.667 22 L 8.895 22 L 8.895 13.111 Z"

                android:valueTo="M 8.667 13.111 L 8.667 22 L 15.333 22 L 15.333 13.111 Z"

                android:valueType="pathType"

                android:interpolator="@android:interpolator/fast_out_slow_in"/>

        </aapt:attr>

    </target>

</animated-vector>
```
**1\. Define the VectorDrawable**

<vector>defines the static vector graphic, similar to a regular Vector Drawable. It uses elements like <path>, <group>, and <clip-path> to define shapes and their properties.
```
<aapt:attr name="android:drawable">

    <vector

        android:height="24dp"

        android:name="vector"

        android:viewportHeight="24"

        android:viewportWidth="24"

        android:width="24dp">

        <path

            android:fillColor="#000"

            android:name="home"

            android:pathData="M 12 2 L 3.111 8.667 L 3.111 22 L 6.445 22 L 6.445 19.778 L 5.334 19.778 L 5.334 9.778 L 12 4.778 L 18.667 9.778 L 18.667 19.778 L 17.556 19.778 L 17.556 22 L 20.889 22 L 20.889 8.667 Z"

            android:strokeWidth="1" />

        <path

            android:fillColor="#000"

            android:name="door_support"

            android:pathData="M 8.667 13.111 L 8.667 22 L 8.895 22 L 8.895 13.111 Z"

            android:strokeWidth="1" />

    </vector>

</aapt:attr>
```
**2\. Create the Animation**

This animates the path data to form a closed door.
```
<aapt:attr name="android:animation">

    <objectAnimator

        android:duration="800"

        android:interpolator="@android:interpolator/fast_out_slow_in"

        android:propertyName="pathData"

        android:valueFrom="M 8.667 13.111 L 8.667 22 L 8.895 22 L 8.895 13.111 Z"

        android:valueTo="M 8.667 13.111 L 8.667 22 L 15.333 22 L 15.333 13.111 Z"

        android:valueType="pathType" />

</aapt:attr>
```
**3\. Link the Animation to the VectorDrawable (avd_home_forward.xml)**

<animated-vector>defines the animation to be applied to the <vector>drawable. It uses <target> elements to specify which parts of the vector graphic should be animated and <animation> elements (like <objectAnimator>, <valueAnimator>, and <set>) to define the animation itself.
```
<animated-vector

    xmlns:android="http://schemas.android.com/apk/res/android"

    xmlns:aapt="http://schemas.android.com/aapt">

    <aapt:attr name="android:drawable">

        <vector

        ...

        </vector>

         </aapt:attr>

    <target android:name="door_support">

        <aapt:attr name="android:animation">

            <objectAnimator

            ...

            />

        </aapt:attr>

    </target>

</animated-vector>
```
**4\. Trigger the Animation Programmatically**

**Implementing AVDs in Your App:**

1.  **Create the XML files:** Put the <animated-vector> XML files in your res/drawable folder.
2.  **Set the drawable:** You can set the AVD as the source of an ImageView in your layout XML or programmatically.
3.  **Start the animation:** Programmatically, you can start the animation using the AnimatedVectorDrawableCompatclass (for backward compatibility) although in this project the min SDK is set to 33 so it's not necessary.

imageView.setImageResource(R.drawable.avd_home_forward)

(imageView.drawable as AnimatedVectorDrawable).start()

**Compatibility and Best Practices**

-   **Support Library**: Use AnimatedVectorDrawableCompat for backward compatibility (API 14+).
-   **Performance Tips**:

-   Avoid animating complex paths with many points. Use hardware acceleration effectively and optimize your animations.
-   Prefer pathData animations for simple morphing; use rotation/translation for smoother performance.

-   **Testing**: Validate animations on different screen sizes and low-end devices to ensure fluidity.
-   **Keep it simple:** Start with simple animations and gradually add complexity.
-   **Use meaningful names:** Use descriptive names for your resources to keep your code organized.

**When to Use AVDs vs. Alternatives**

-   **AVDs** excel at simple, performant icon animations (e.g., button transitions, loading spinners).
-   **Lottie** is better for complex animations (e.g., After Effects exports) but may increase APK size.
-   **RippleDrawables** handles touch feedback, while AVDs focus on programmatic animations.

**References**

<https://developer.android.com/reference/android/graphics/drawable/VectorDrawable.html>

<https://developer.android.com/reference/android/graphics/drawable/AnimatedVectorDrawable.html>

<https://medium.com/androiddevelopers/animation-jump-through-861f4f5b3de4>

<https://www.androiddesignpatterns.com/2016/11/introduction-to-icon-animation-techniques.html>

<https://medium.com/@naththeprince/delightful-animations-in-android-d6e9c62a23d3>












