package com.angelsheaven.demo.di;

import androidx.lifecycle.ViewModel;
import dagger.MapKey;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@MapKey
@interface ViewModelKeyJava {
    Class<? extends ViewModel> value();
}
