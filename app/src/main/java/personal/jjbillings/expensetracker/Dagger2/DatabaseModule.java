package personal.jjbillings.expensetracker.Dagger2;

import android.app.Activity;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import personal.jjbillings.expensetracker.Helpers.DBHelper;

/**
 * Created by jbillz on 8/22/16.
 */
@Module
public class DatabaseModule {

    private Context ctx;

    public DatabaseModule(Context c) {
        this.ctx = c;
    }

    @Provides @Singleton
    Context providesContext() {
        return this.ctx;
    }

    @Provides @Singleton
    DBHelper provideDBHelper() {
        return new DBHelper(this.ctx);
    }

}
