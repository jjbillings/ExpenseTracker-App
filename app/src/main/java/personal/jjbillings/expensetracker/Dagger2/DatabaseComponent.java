package personal.jjbillings.expensetracker.Dagger2;

import javax.inject.Singleton;

import dagger.Component;
import personal.jjbillings.expensetracker.Helpers.DBHelper;
import personal.jjbillings.expensetracker.Login.LoginActivity;
import personal.jjbillings.expensetracker.MainActivity.MainActivity;

/**
 * Created by jbillz on 8/22/16.
 */
@Singleton
@Component(modules = DatabaseModule.class)
public interface DatabaseComponent {

    void inject(LoginActivity activity);

    void inject(MainActivity activity);
}
