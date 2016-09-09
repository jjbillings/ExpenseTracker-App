package personal.jjbillings.expensetracker.Helpers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowApplication;

import java.util.ArrayList;
import java.util.List;

import personal.jjbillings.expensetracker.Models.User;

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
        assertTrue(dbh.doesTableExist(dbh.TABLE_REPORTS));

        assertTrue(dbh.doesTableExist(dbh.TABLE_RECEIPT_IMAGES));
        assertTrue(dbh.doesTableExist(dbh.TABLE_PAYMENT_METHODS));
        assertTrue(dbh.doesTableExist(dbh.TABLE_PAYMENT_TYPES));
        assertTrue(dbh.doesTableExist(dbh.TABLE_EXPENSES));
        assertTrue(dbh.doesTableExist(dbh.TABLE_EXPENSE_CATEGORIES));

        assertTrue(dbh.doesTableExist(dbh.TABLE_REPORT_EXPENSE_LINKS));
        assertTrue(dbh.doesTableExist(dbh.TABLE_RECEIPT_EXPENSE_LINKS));
        assertTrue(dbh.doesTableExist(dbh.TABLE_EXPENSE_METHOD_LINKS));
        assertTrue(dbh.doesTableExist(dbh.TABLE_PAYMENT_TYPE_METHOD_LINKS));
        assertTrue(dbh.doesTableExist(dbh.TABLE_EXPENSE_CATEGORY_LINKS));
    }

    /*
    @Test
    public void checkIfAddUserCorrectly() {

        User testUser = new User("jbillz","password");
        dbh.addUser(testUser);
        assertEquals(1,dbh.getEntryCount(dbh.TABLE_USERS));
        assertEquals(testUser,dbh.getUser(testUser.getUsername()));
        assertEquals(testUser.getPassword(),dbh.getPassword(testUser));
    }
    */

    @Test
    public void checkIfAddUserCorrectly() {
        User testUser = dbh.addUser("jdollhair","password");
        assertEquals(1,dbh.getEntryCount(dbh.TABLE_USERS));
        assertEquals(testUser,dbh.getUser("jdollhair"));
        assertEquals(testUser, dbh.getUser(1));
        assertEquals("password",dbh.getPassword(testUser));
    }

    @Test
    public void checkIfUpdateUserCorrectly() {
        String uname1 = "jbillz";
        String pass1 = "password";
        String pass2 = "newpassword";

        User testUser = dbh.addUser(uname1, pass1);

        testUser.setPassword(pass2);

        dbh.updateUser(testUser);

        assertEquals(1,dbh.getEntryCount(dbh.TABLE_USERS));
        assertEquals(pass2,dbh.getPassword(testUser));
        assertEquals(testUser,dbh.getUser(testUser.getUsername()));
    }

    @Test
    public void checkIfDeleteUserCorrectly() {
        String uname = "jdollar";
        String pass = "password";
        User testUser = dbh.addUser(uname,pass);

        assertEquals(1,dbh.getEntryCount(dbh.TABLE_USERS));

        dbh.deleteUser(testUser);

        assertEquals(0,dbh.getEntryCount(dbh.TABLE_USERS));
    }

    @Test
    public void checkIfGetAllUsersCorrectly() {
        List<User> testUsers = new ArrayList<>();

        testUsers.add(dbh.addUser("jbillz","pass1"));
        testUsers.add(dbh.addUser("jdollar","pass2"));

        List<User> dbUsers = dbh.getAllUsers();

        assertArrayEquals(testUsers.toArray(new User[testUsers.size()]),
                dbUsers.toArray(new User[testUsers.size()]));
    }

    @Test
    public void checkNotDoubleAddingUsers() {
        String uname = "jbillz";
        String pass = "password";

        User testUser1 = dbh.addUser(uname,pass);
        User testUser2 = dbh.addUser(uname,pass);

        assertEquals(1,dbh.getEntryCount(dbh.TABLE_USERS));
        assertNotNull(testUser1);
        assertNotNull(testUser2);
        assertEquals(testUser1,testUser2);
    }
}