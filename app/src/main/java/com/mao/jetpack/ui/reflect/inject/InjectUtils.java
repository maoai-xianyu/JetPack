package com.mao.jetpack.ui.reflect.inject;

import android.app.Activity;
import android.view.View;

import com.mao.jetpack.utils.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author zhangkun
 * @time 2022/4/12 10:39
 * @Description
 */
public class InjectUtils {

    public static void injectView(Activity activity) {
        if (null == activity) return;

        Class<? extends Activity> activityClass = activity.getClass();

        Field[] declaredFields = activityClass.getDeclaredFields();

        for (Field field : declaredFields) {
            // 判读 feild 的上的标记注解是不是 InjectView
            if (field.isAnnotationPresent(InjectView.class)) {
                // 获取对应的注解
                InjectView annotation = field.getAnnotation(InjectView.class);
                if (annotation != null) {
                    try {
                        int value = annotation.value();
                        Logger.debug("------ value " + value);
                        View view = activity.findViewById(value);
                        // 反射设置，属性的值，
                        field.setAccessible(true); //设置访问权限，允许操作private的属性
                        // 反射赋值
                        // 1. 要操作的对象，如果值为 static 可以设置为 null
                        // 2. 要操作对象的属性的值
                        field.set(activity, view);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

    public static void injectViews(Activity activity) {
        if (null == activity) return;

        Class<? extends Activity> activityClass = activity.getClass();
        Field[] declaredFields = activityClass.getDeclaredFields();
        for (Field field : declaredFields) {//获取Activity类里面声明的所有成员变量
            if (field.isAnnotationPresent(InjectView.class)) {//找出标注了@InjectView的成员变量
                //解析InjectView 获取button id
                InjectView injectView = field.getAnnotation(InjectView.class);
                if (injectView != null) {
                    if (View.class.isAssignableFrom(field.getType()) && !Modifier.isStatic(field.getModifiers())) {
                        int value = injectView.value();//获取变量的值，也就是控件的id
                        try {
                            //找到findViewById方法
                            Method findViewById = activityClass.getMethod("findViewById", int.class);
                            Object view = findViewById.invoke(activity, value);
                            field.setAccessible(true);
                            field.set(activity, view);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }


        }
    }


    public static void findByid(Activity activity) {

        //获取每个activity对应的类类型
        Class<? extends Activity> aClass = activity.getClass();
        //获取该类型中所有的属性信息
        Field[] fields = aClass.getDeclaredFields();

        //遍历所以得属性
        for (Field field : fields) {
            //判断该属性上是否有Viewid这个注解的类类型
            InjectView viewid = field.getAnnotation(InjectView.class);
            //如果有就会拿到对应的注解信息，没有就会返回空
            if (viewid != null) {
                //判断该属性是否是属于View的子类类型，并且不是静态属性
                if (View.class.isAssignableFrom(field.getType()) && !Modifier.isStatic(field.getModifiers())) {

                    try {
                        //拿到注解上的ID
                        int id = viewid.value();
                        //拿到该类中的findViewById方法，对应传参为int类型
                        Method byId = aClass.getMethod("findViewById", int.class);
                        //然后执行该findViewById的方法
                        Object invoke = byId.invoke(activity, id);
                        //如果属性是私有的就修改一些访问权限，以便于我们修改值
                        field.setAccessible(true);
                        //将拿到的参数设置到对应的属性上，大公告成
                        field.set(activity, invoke);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }

        }

    }
}
