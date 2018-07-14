package ru.geekbrains.weatherapp.screens.welcome.di;

import dagger.Module;
import dagger.Provides;
import ru.geekbrains.weatherapp.database.CitiesDao;
import ru.geekbrains.weatherapp.screens.welcome.contracts.IWelcomeModel;
import ru.geekbrains.weatherapp.screens.welcome.contracts.IWelcomePresenter;
import ru.geekbrains.weatherapp.screens.welcome.contracts.IWelcomeView;
import ru.geekbrains.weatherapp.screens.welcome.implementations.WelcomeModel;
import ru.geekbrains.weatherapp.screens.welcome.implementations.WelcomePresenter;

@Module
public class WelcomeModule {

    @WelcomeScope
    @Provides
    IWelcomePresenter<IWelcomeView> providePresenter(IWelcomeModel model){
        return new WelcomePresenter<>(model);
    }

    @WelcomeScope
    @Provides
    IWelcomeModel provideModel(CitiesDao dao){
        return new WelcomeModel(dao);
    }
}
