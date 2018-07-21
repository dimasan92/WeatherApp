package ru.geekbrains.weatherapp.di.screen;

import dagger.Module;
import dagger.Provides;
import ru.geekbrains.weatherapp.screens.main.contracts.IMainModel;
import ru.geekbrains.weatherapp.screens.main.contracts.IMainPresenter;
import ru.geekbrains.weatherapp.screens.main.contracts.IMainView;
import ru.geekbrains.weatherapp.screens.main.implementations.MainModel;
import ru.geekbrains.weatherapp.screens.main.implementations.MainPresenter;

@Module
public class MainModule {

    @ScreenScope
    @Provides
    IMainPresenter<IMainView> providePresenter(IMainModel model){
        return new MainPresenter<>(model);
    }

    @ScreenScope
    @Provides
    IMainModel provideModel(){
        return new MainModel();
    }
}
