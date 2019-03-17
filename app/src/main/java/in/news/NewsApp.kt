package `in`.news

import `in`.news.di.component.DaggerAppComponent
import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class NewsApp : Application(),HasActivityInjector
{
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

    companion object {
    lateinit var instance: Application
}
    override fun onCreate() {
        super.onCreate()
        instance=this;

        DaggerAppComponent
                .builder()
                .appContext(this)
                .create(this)
                .inject(this)
    }
}