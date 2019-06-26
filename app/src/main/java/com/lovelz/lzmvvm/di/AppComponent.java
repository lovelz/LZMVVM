package com.lovelz.lzmvvm.di;

import android.app.Application;

import com.lovelz.lzmvvm.App;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 *  App级别注入
 */
@Singleton
@Component(modules = {AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBindModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        //@BindsInstance注解的作用，只能在 Component.Builder 中使用
        //创建 Component 的时候绑定依赖实例
        @BindsInstance
        Builder application(Application application);

        AppComponent build();

    }

    void inject(App app);

}
