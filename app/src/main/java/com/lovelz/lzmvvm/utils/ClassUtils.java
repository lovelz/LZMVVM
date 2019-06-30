package com.lovelz.lzmvvm.utils;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

import com.lovelz.lzmvvm.base.model.BaseViewModel;
import com.lovelz.lzmvvm.base.model.NoneViewModel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ClassUtils {

    private static <T> Class<T> getGenericClass(Class<?> klass, Class<?> filterClass) {
        Type type = klass.getGenericSuperclass();
        if (type == null || !(type instanceof ParameterizedType)) return null;
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type[] types = parameterizedType.getActualTypeArguments();
        for (Type t : types) {
            Class<T> tClass = (Class<T>) t;
            if (filterClass.isAssignableFrom(tClass)) {
                return tClass;
            }
        }
        return null;
    }


    /**
     * 获取泛型ViewModel的class对象
     */
    public static <T> Class<T> getViewModel(Object obj) {
        Class<?> currentClass = obj.getClass();
        Class<T> tClass = getGenericClass(currentClass, BaseViewModel.class);
        if (tClass == null || tClass == BaseViewModel.class || tClass == NoneViewModel.class) {
            return null;
        }
        return tClass;
    }

    /**
     * 获取泛型Binding的class对象
     */
    public static <T> T getBinding(@NonNull Object obj, @NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        Class<?> currentClass = obj.getClass();
        Class<T> tClass = getGenericClass(currentClass, ViewDataBinding.class);
        if (tClass == null || tClass == ViewDataBinding.class) {
            return null;
        }
        try {
            Method method = tClass.getMethod("inflate", LayoutInflater.class, ViewGroup.class, Boolean.TYPE);
            Object returnValue = method.invoke(null, inflater, container, false);
            return (T) returnValue;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

}
