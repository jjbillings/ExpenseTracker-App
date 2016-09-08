package personal.jjbillings.expensetracker.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import personal.jjbillings.expensetracker.Models.User;

/**
 * Created by jbillz on 8/22/16.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Expense_DB";

    //Tables
    public static final String TABLE_USERS = "users";
    public static final String TABLE_REPORTS = "reports";
    public static final String TABLE_RECEIPT_IMAGES = "receipt_images";
    public static final String TABLE_PAYMENT_METHODS = "payment_methods";
    public static final String TABLE_PAYMENT_TYPES = "payment_types";
    public static final String TABLE_EXPENSES = "expenses";
    public static final String TABLE_EXPENSE_CATEGORIES = "expense_categories";

    //Link Tables
    public static final String TABLE_REPORT_EXPENSE_LINKS = "report_expense_links";
    public static final String TABLE_RECEIPT_EXPENSE_LINKS = "receipt_expense_links";
    public static final String TABLE_EXPENSE_METHOD_LINKS = "expense_method_links";
    public static final String TABLE_PAYMENT_TYPE_METHOD_LINKS = "payment_type_method_links";
    public static final String TABLE_EXPENSE_CATEGORY_LINKS = "expense_category_links";


    //All tables
    public static final String KEY_NAME = "name";
    public static final String KEY_DESCRIPTION = "description";

    //user table
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_PASS = "password";

    //reports table
    public static final String KEY_REPORT_ID = "report_id";
    public static final String KEY_REPORT_TIME_CREATED = "time_created";
    public static final String KEY_REPORT_TIME_STARTED = "time_started";
    public static final String KEY_REPORT_TIME_ENDED = "time_ended";
    public static final String KEY_TOTAL_SPENT = "total_spent";
    public static final String KEY_AVG_SPENT = "avg_spent";

    //payment methods table
    public static final String KEY_METHOD_ID = "method_id";

    //Expenses table
    public static final String KEY_EXPENSE_ID = "expense_id";
    public static final String KEY_DATE = "date";
    public static final String KEY_AMOUNT = "amount";

    ////payment types table
    public static final String KEY_PAYMENT_TYPE_ID = "payment_type_id";

    //Receipt image table
    public static final String KEY_RECEIPT_ID = "receipt_id";
    public static final String KEY_PATH = "path";
    public static final String KEY_FILENAME = "filename";

    //Expense Categories table
    public static final String KEY_CATEGORY_ID = "category_id";
    public static final String KEY_CATEGORY_PARENT_ID = "category_parent_id";



    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_NAME + " TEXT PRIMARY KEY, "
                + KEY_PASS + " TEXT);";

        String createReportsTable = "CREATE TABLE " + TABLE_REPORTS + "("
                + KEY_REPORT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_USER_ID + " INTEGER, "
                + KEY_NAME + " TEXT, "
                + KEY_DESCRIPTION + " TEXT, "
                + KEY_REPORT_TIME_CREATED + " TEXT, "
                + KEY_REPORT_TIME_STARTED + " TEXT, "
                + KEY_REPORT_TIME_ENDED + " TEXT, "
                + KEY_TOTAL_SPENT + " REAL, "
                + KEY_AVG_SPENT + " REAL, FOREIGN KEY(" + KEY_USER_ID + ") REFERENCES "
                + TABLE_USERS + "("+KEY_USER_ID+"));";

        String createReceiptImagesTable = "CREATE TABLE " + TABLE_RECEIPT_IMAGES + "("
                + KEY_RECEIPT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_PATH + " TEXT, " + KEY_FILENAME + " TEXT, "
                + KEY_DATE + " TEXT);";

        String createPaymentTypesTable = "CREATE TABLE " + TABLE_PAYMENT_TYPES + "("
                + KEY_PAYMENT_TYPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NAME + " TEXT);";

        String createExpensesTable = "CREATE TABLE " + TABLE_EXPENSES + "("
                + KEY_EXPENSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_USER_ID + " INTEGER, " + KEY_NAME + " TEXT, "
                + KEY_DESCRIPTION + " TEXT, " + KEY_DATE + " TEXT, "
                + KEY_AMOUNT + " REAL, "
                + "FOREIGN KEY("+KEY_USER_ID+") REFERENCES " + TABLE_USERS + "("+KEY_USER_ID+"));";

        String createPaymentMethodsTable = "CREATE TABLE " + TABLE_PAYMENT_METHODS + "("
                + KEY_METHOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NAME + " TEXT, " + KEY_DESCRIPTION + " TEXT);";

        String createExpenseCategoriesTable = "CREATE TABLE " + TABLE_EXPENSE_CATEGORIES + "("
                + KEY_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NAME + " TEXT, " + KEY_CATEGORY_PARENT_ID + " INTEGER);";

        String createReceiptExpenseLinksTable = "CREATE TABLE " + TABLE_RECEIPT_EXPENSE_LINKS + "("
                + KEY_RECEIPT_ID + " INTEGER, " + KEY_EXPENSE_ID + " INTEGER, "
                + "FOREIGN KEY("+KEY_RECEIPT_ID+") REFERENCES " + TABLE_RECEIPT_IMAGES +"("+KEY_RECEIPT_ID+"), "
                + "FOREIGN KEY("+KEY_EXPENSE_ID+") REFERENCES " + TABLE_EXPENSES +"("+KEY_EXPENSE_ID+"));";

        String createExpenseCategoryLinksTable = "CREATE TABLE " + TABLE_EXPENSE_CATEGORY_LINKS + "("
                + KEY_EXPENSE_ID + " INTEGER, " + KEY_CATEGORY_ID + " INTEGER, "
                + "FOREIGN KEY("+KEY_EXPENSE_ID+") REFERENCES " + TABLE_EXPENSES +"("+KEY_EXPENSE_ID+"), "
                + "FOREIGN KEY("+KEY_CATEGORY_ID+") REFERENCES " + TABLE_EXPENSE_CATEGORIES +"("+KEY_CATEGORY_ID+"));";

        String createReportExpenseLinks = "CREATE TABLE " + TABLE_REPORT_EXPENSE_LINKS + "("
                + KEY_EXPENSE_ID + " INTEGER, " + KEY_REPORT_ID + " INTEGER, "
                + "FOREIGN KEY("+KEY_EXPENSE_ID+") REFERENCES " + TABLE_EXPENSES +"("+KEY_EXPENSE_ID+"), "
                + "FOREIGN KEY("+KEY_REPORT_ID+") REFERENCES " + TABLE_REPORTS +"("+KEY_REPORT_ID+"));";

        String createPaymentTypeMethodLinks = "CREATE TABLE " + TABLE_PAYMENT_TYPE_METHOD_LINKS + "("
                + KEY_PAYMENT_TYPE_ID + " INTEGER, " + KEY_METHOD_ID + " INTEGER, "
                + "FOREIGN KEY("+KEY_PAYMENT_TYPE_ID+") REFERENCES " + TABLE_PAYMENT_TYPES +"("+KEY_PAYMENT_TYPE_ID+"), "
                + "FOREIGN KEY("+KEY_METHOD_ID+") REFERENCES " + TABLE_PAYMENT_METHODS +"("+KEY_METHOD_ID+"));";

        String createExpenseMethodsLinksTable = "CREATE TABLE " + TABLE_EXPENSE_METHOD_LINKS + "("
                + KEY_EXPENSE_ID + " INTEGER, " + KEY_METHOD_ID + " INTEGER, "
                + "FOREIGN KEY("+KEY_EXPENSE_ID+") REFERENCES " + TABLE_EXPENSES +"("+KEY_EXPENSE_ID+"), "
                + "FOREIGN KEY("+KEY_METHOD_ID+") REFERENCES " + TABLE_PAYMENT_METHODS +"("+KEY_METHOD_ID+"));";

        db.execSQL(createUserTable);
        db.execSQL(createReportsTable);
        db.execSQL(createReceiptImagesTable);
        db.execSQL(createPaymentTypesTable);
        db.execSQL(createExpensesTable);
        db.execSQL(createPaymentMethodsTable);
        db.execSQL(createExpenseCategoriesTable);

        db.execSQL(createReceiptExpenseLinksTable);
        db.execSQL(createExpenseCategoryLinksTable);
        db.execSQL(createReportExpenseLinks);
        db.execSQL(createPaymentTypeMethodLinks);
        db.execSQL(createExpenseMethodsLinksTable);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECEIPT_IMAGES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAYMENT_METHODS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAYMENT_TYPES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSE_CATEGORIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPORTS);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSE_CATEGORY_LINKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSE_METHOD_LINKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECEIPT_EXPENSE_LINKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPORT_EXPENSE_LINKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAYMENT_TYPE_METHOD_LINKS);
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

        User user = new User(cursor.getString(0),cursor.getString(1));
        return user;
    }

    // Getting All Users
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
    public int getEntryCount(String tableName) {
        String countQuery = "SELECT * FROM " + tableName;
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