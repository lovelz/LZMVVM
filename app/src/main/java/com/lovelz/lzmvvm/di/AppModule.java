package com.lovelz.lzmvvm.di;

import com.lovelz.lzmvvm.net.RetrofitFactory;

import dagger.Module;
import retrofit2.Retrofit;

@Module(includes = ViewModelModule.class)
class AppModule {

    Retrofit providerRetrofit() {
        return RetrofitFactory.getInstance().retrofit;
    }

}
