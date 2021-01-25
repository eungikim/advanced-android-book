## 변경사항
* Leak canary 버전이 1에서 2로 변경되면서 설정과 관련된 여러부분 변경
* [예제 14-12] 프래그먼트에 대한 추적이 기본으로 되어있으므로 서비스에 대한 추적 예제 코드 추가
> *The default configuration of LeakCanary will automatically watch Activity, Fragment, Fragment View and ViewModel instances.*

## Ref
* [LeakCanary DOC](https://square.github.io/leakcanary/)
  * [Code recipes](https://square.github.io/leakcanary/recipes/)
  * [Upgrading to LeakCanary 2](https://square.github.io/leakcanary/upgrading-to-leakcanary-2.0)

## build config
* com.android.tools.build:gradle:4.0.2
* compileSdkVersion 30
* buildToolsVersion "30.0.3"
* debugImplementation 'com.squareup.leakcanary:leakcanary-android:2.6'

## Getting started
Confirm that LeakCanary is running on startup by filtering on the LeakCanary tag in Logcat:
``` 
10:37:08.075 4625-4625/? W/.leakcanarydem: Unexpected CPU variant for X86 using defaults: x86_64
10:37:08.596 4625-4625/leakcanarydemo W/.leakcanarydem: Accessing hidden method Landroid/view/WindowManagerGlobal;->getInstance()Landroid/view/WindowManagerGlobal; (light greylist, reflection)
10:37:08.596 4625-4625/leakcanarydemo W/.leakcanarydem: Accessing hidden field Landroid/view/WindowManagerGlobal;->mViews:Ljava/util/ArrayList; (light greylist, reflection)
10:37:08.598 4625-4625/leakcanarydemo W/.leakcanarydem: Accessing hidden field Landroid/app/ActivityThread;->mH:Landroid/app/ActivityThread$H; (light greylist, reflection)
10:37:08.598 4625-4625/leakcanarydemo W/.leakcanarydem: Accessing hidden method Landroid/app/ActivityThread;->currentActivityThread()Landroid/app/ActivityThread; (light greylist, reflection)
10:37:08.598 4625-4625/leakcanarydemo W/.leakcanarydem: Accessing hidden field Landroid/os/Handler;->mCallback:Landroid/os/Handler$Callback; (light greylist, reflection)
10:37:08.599 4625-4625/leakcanarydemo W/.leakcanarydem: Accessing hidden field Landroid/util/Singleton;->mInstance:Ljava/lang/Object; (light greylist, reflection)
10:37:08.599 4625-4625/leakcanarydemo W/.leakcanarydem: Accessing hidden method Landroid/util/Singleton;->get()Ljava/lang/Object; (light greylist, reflection)
10:37:08.600 4625-4625/leakcanarydemo W/.leakcanarydem: Accessing hidden field Landroid/app/ActivityManager;->IActivityManagerSingleton:Landroid/util/Singleton; (light greylist, reflection)
10:37:08.649 4625-4625/leakcanarydemo W/.leakcanarydem: Accessing hidden field Landroid/view/inputmethod/InputMethodManager;->mCurRootView:Landroid/view/View; (light greylist, reflection)
10:37:08.764 4625-4625/leakcanarydemo W/.leakcanarydem: Accessing hidden method Landroid/graphics/drawable/Drawable;->getOpticalInsets()Landroid/graphics/Insets; (light greylist, linking)
10:37:08.764 4625-4625/leakcanarydemo W/.leakcanarydem: Accessing hidden field Landroid/graphics/Insets;->left:I (light greylist, linking)
10:37:08.764 4625-4625/leakcanarydemo W/.leakcanarydem: Accessing hidden field Landroid/graphics/Insets;->right:I (light greylist, linking)
10:37:08.764 4625-4625/leakcanarydemo W/.leakcanarydem: Accessing hidden field Landroid/graphics/Insets;->top:I (light greylist, linking)
10:37:08.764 4625-4625/leakcanarydemo W/.leakcanarydem: Accessing hidden field Landroid/graphics/Insets;->bottom:I (light greylist, linking)
10:37:08.864 4625-4625/leakcanarydemo W/.leakcanarydem: Accessing hidden field Landroid/view/WindowInsets;->CONSUMED:Landroid/view/WindowInsets; (light greylist, reflection)
10:37:08.881 4625-4625/leakcanarydemo W/.leakcanarydem: Accessing hidden method Landroid/view/View;->getAccessibilityDelegate()Landroid/view/View$AccessibilityDelegate; (light greylist, linking)
10:37:08.940 4625-4625/leakcanarydemo W/.leakcanarydem: Accessing hidden method Landroid/view/View;->computeFitSystemWindows(Landroid/graphics/Rect;Landroid/graphics/Rect;)Z (light greylist, reflection)
10:37:08.940 4625-4625/leakcanarydemo W/.leakcanarydem: Accessing hidden method Landroid/view/ViewGroup;->makeOptionalFitsSystemWindows()V (light greylist, reflection)

>>>>> This
10:37:09.054 4625-4670/leakcanarydemo D/LeakCanary: LeakCanary is running and ready to detect memory leaks.
>>>>> Log

10:37:09.070 4625-4625/leakcanarydemo W/.leakcanarydem: Accessing hidden method Landroid/graphics/Insets;->of(IIII)Landroid/graphics/Insets; (light greylist, linking)
10:37:10.688 4625-4679/leakcanarydemo D/LeakCanary: Setting up flushing for Thread[LeakCanary-Heap-Dump,5,main]
```
