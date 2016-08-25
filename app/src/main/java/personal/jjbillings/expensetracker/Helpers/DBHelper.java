package personal.jjbillings.expensetracker.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import personal.jjbillings.expensetracker.User;

/**
 * Created by jbillz on 8/22/16.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Expense_DB";

    public static final String TABLE_USERS = "users";

    public static final String KEY_NAME = "name";
    public static final String KEY_PASS = "password";


    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_NAME + " TEXT PRIMARY KEY, "
                + KEY_PASS + " TEXT" + ");";
        db.execSQL(createUserTable);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        // Create tables again
        onCreate(db);
    }

    public boolean doesTableExist(String tableName) {
        SQLiteDatabase db = this.getReadableDatabase();

        String tableQuery = "SELECT name FROM sqlite_master WHERE type='table' AND name='" + tableName +"';";
        Cursor cursor = db.rawQuery(tableQuery,null);

        if(cursor != null) {
            cursor.moveToFirst();
        }

        if(cursor.getCount() == 0) {
            return false;
        }

        return true;
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new user
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getUsername());
        values.put(KEY_PASS, user.getPassword());

        // Inserting Row
        db.insert(TABLE_USERS, null, values);
        db.close(); // Closing database connection
    }

    //TODO: What if the user DNE?
    public User getUser(String uname) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS, new String[] {KEY_NAME, KEY_PASS}, KEY_NAME + " = '"
                + uname + "'",null,null,null,null);
        if(cursor != null) {
            cursor.moveToFirst();
        }

        User user = new User(0,cursor.getString(0),cursor.getString(1));
        return user;
    }

    // Getting All Contacts
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(0);
                user.setUsername(cursor.getString(0));
                user.setPassword(cursor.getString(1));
                // Adding user to list
                userList.add(user);
            } while (cursor.moveToNext());
        }

        // return User list
        return userList;
    }

    // Updating single user
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PASS, user.getPassword());

        // updating row
        return db.update(TABLE_USERS, values, KEY_NAME + " = ?",
                new String[] { user.getUsername() });
    }

    // Deleting single User
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, KEY_NAME + " = ?",
                new String[] { user.getUsername() });
        db.close();
    }


    // Getting User Count
    public int getUsersCount() {
        String countQuery = "SELECT * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    public String getPassword(User user) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS, new String[] {KEY_PASS}, KEY_NAME + " = '"
                + user.getUsername() + "'",null,null,null,null);
        if(cursor != null) {
            cursor.moveToFirst();
        }

        return cursor.getString(0);
    }
}