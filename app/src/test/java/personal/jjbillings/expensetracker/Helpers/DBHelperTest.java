package personal.jjbillings.expensetracker.Helpers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowApplication;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import personal.jjbillings.expensetracker.User;

import static org.junit.Assert.*;

/**
 * Created by jbillz on 8/24/16.
 */
@RunWith(RobolectricTestRunner.class)
public class DBHelperTest {

    public static final String DATABASE_NAME = "Expense_DB";

    private DBHelper dbh;

    @Before
    public void setUp() throws Exception {
        ShadowApplication context = Shadows.shadowOf(RuntimeEnvironment.application);
        dbh = new DBHelper(context.getApplicationContext());
    }

    @After
    public void tearDown() throws Exception {
        dbh.close();
    }

    @Test
    public void checkDBName() {
        assertEquals(DATABASE_NAME,dbh.getDatabaseName());
    }

    @Test
    public void checkIfTablesAreCreated() {
        assertTrue(dbh.doesTableExist(dbh.TABLE_USERS));
    }

    @Test
    public void checkIfAddUserCorrectly() {
        User testUser = new User(0,"jbillz","password");
        dbh.addUser(testUser);
        assertEquals(1,dbh.getUsersCount());
        assertEquals(testUser,dbh.getUser(testUser.getUsername()));
        assertEquals(testUser.getPassword(),dbh.getPassword(testUser));
    }

    @Test
    public void checkIfUpdateUserCorrectly() {
        String uname1 = "jbillz";
        String pass1 = "password";
        String pass2 = "newpassword";

        User testUser = new User(0,uname1,pass1);
        dbh.addUser(testUser);

        testUser.setPassword(pass2);

        dbh.updateUser(testUser);

        assertEquals(1,dbh.getUsersCount());
        assertEquals(pass2,dbh.getPassword(testUser));
        assertEquals(testUser,dbh.getUser(testUser.getUsername()));
    }

    @Test
    public void checkIfDeleteUserCorrectly() {
        String uname = "jdollar";
        String pass = "password";
        User testUser = new User(0,uname,pass);
        dbh.addUser(testUser);

        assertEquals(1,dbh.getUsersCount());

        dbh.deleteUser(testUser);

        assertEquals(0,dbh.getUsersCount());
    }

    @Test
    public void checkIfGetAllUsersCorrectly() {
        List<User> testUsers = new ArrayList<>();
        User u1 = new User(0,"jbillz","pass1");
        User u2 = new User(1,"jdollar","pass2");
        testUsers.add(u1);
        testUsers.add(u2);

        dbh.addUser(u1);
        dbh.addUser(u2);

        List<User> dbUsers = dbh.getAllUsers();

        assertArrayEquals(testUsers.toArray(new User[testUsers.size()]),
                dbUsers.toArray(new User[testUsers.size()]));
    }

    @Test
    public void checkNotDoubleAddingUsers() {
        User testUser = new User(0,"jbillz","password");

        dbh.addUser(testUser);
        dbh.addUser(testUser);

        assertEquals(1,dbh.getUsersCount());
    }
}