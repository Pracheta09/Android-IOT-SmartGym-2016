package com.demo.pracheta.mygym;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import java.io.File;
import java.sql.SQLException;

/**
 * Created by Pracheta on 11/14/2016.
 */
public class GymDatabase {
    public GymDatabase(Context c)
    {
        ourContext=c;
    }
   //rows
    public static final String KEY_ROWID="_id";
    public static final String KEY_NAME="users_name";
    public static final String KEY_USERNAME="users_username";
    public static final String KEY_PASSWORD="users_password";
    public static final String KEY_EMAILID="users_emailid";
    public static final String KEY_AGE="users_age";
    public static final String KEY_WEIGHT="users_weight";
    public static final String KEY_MOBILE="users_mobile";
    public static final String KEY_GENDER="users_gender";

    //Date Goal_Time Goal_Set Achieved_Time Achieved_Set Calories
    public static final String KEY_HISTORYID="_id";

    public static final String KEY_DATE="DATE";

    public static final String KEY_ACHIEVED_TIME="ACHIEVED_TIME";
    public static final String KEY_ACHIEVED_SET="ACHIEVED_SET";
    public static final String KEY_CALORIES="CALORIES";

    public static final String KEY_GOALID="_id";
    public static final String KEY_GOAL_TIME="GOAL_TIME";
    public static final String KEY_GOAL_SET="GOAL_SET";


    private static final String DATABASE_NAME="GymDatabase";
    private static final String DATABASE_TABLE="Users";
    private static final String DATABASE_TABLE1="History";
    private static final String DATABASE_TABLE2="SetGoal";
    private static final int DATABASE_VERSION=3;

    private DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    public long createEntry(String name, String user_name, String password, String age, String email, String mobile, String weight
                            ) {

        ContentValues cv=new ContentValues();
        cv.put(KEY_NAME,name);
        cv.put(KEY_USERNAME,user_name);
        cv.put(KEY_PASSWORD,password);
        cv.put(KEY_EMAILID,email);
        cv.put(KEY_AGE,age);
        cv.put(KEY_WEIGHT,weight);
        cv.put(KEY_MOBILE,mobile);


        //cv.put(KEY_GENDER);
        /*ourDatabase.insert(DATABASE_TABLE,null,cv);

       File dbFile=ourContext.getDatabasePath(DATABASE_TABLE);
        if(dbFile.exists()) {
            Dialog d = new Dialog(ourContext);
            d.setTitle("Found!");
            TextView tv = new TextView(ourContext);
            tv.setText("DB");
            d.setContentView(tv);
            d.show();
        }
        else
        {
            Dialog d = new Dialog(ourContext);
            d.setTitle("Not Found!");
            TextView tv = new TextView(ourContext);
            tv.setText("DB");
            d.setContentView(tv);
            d.show();
        }*/
            return ourDatabase.insert(DATABASE_TABLE, null, cv);



    }

    public String show_calories() {
        String dbString = "";
        String date = "";
        //SQLiteDatabase db = getWritableDatabase();
        String query = " SELECT "+ KEY_CALORIES + " , " + KEY_DATE  + " FROM " + DATABASE_TABLE1 + " WHERE 1; ";

       // String query = "SELECT " + KEY_CALORIES + " , " + KEY_DATE + " FROM " + DATABASE_TABLE1 +"; " ;
        Cursor c = ourDatabase.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("CALORIES")) != null) {
                dbString += c.getString(c.getColumnIndex("CALORIES"));
                dbString += ",";
               // date = c.getString(c.getColumnIndex("DATE"));
                c.moveToNext();
            }
        }
        //to find of which month the data is by adding month to the last of the steps string
        //String str[]=date.split("-");
        date=""+12; //date ---> month
        dbString+=date;
        ourDatabase.close();
        return dbString;
       /* String[] columns= new String[]{KEY_DATE,KEY_CALORIES};
        Cursor c=ourDatabase.query(DATABASE_TABLE1,columns,null,null,null,null,null);
        String result  =" ";
        String date = " ";
        int iDate=c.getColumnIndex(KEY_DATE);
        int iCal=c.getColumnIndex(KEY_CALORIES);
       // int iPassword=c.getColumnIndex(KEY_PASSWORD);

        for (c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            result=result+c.getString(iCal)+",";
            date=c.getString(iDate);
        }

        String str[]=date.split("-");
        date=str[1]; //date ---> month

        return (result+date);
*/

    }

    public long createEntryHistory(String date,String achieved_time,String achieved_set,String calories){

        ContentValues cv=new ContentValues();
        cv.put(KEY_DATE,date);

        cv.put(KEY_ACHIEVED_TIME,achieved_time);
        cv.put(KEY_ACHIEVED_SET,achieved_set);
        cv.put(KEY_CALORIES, calories);

        /*File dbFile=ourContext.getDatabasePath(DATABASE_TABLE1);
        if(dbFile.exists()) {
            Dialog d = new Dialog(ourContext);
            d.setTitle("Found!");
            TextView tv = new TextView(ourContext);
            tv.setText("DB");
            d.setContentView(tv);
            d.show();
        }
        else {
            Dialog d = new Dialog(ourContext);
            d.setTitle("Not Found!");
            TextView tv = new TextView(ourContext);
            tv.setText("DB");
            d.setContentView(tv);
            d.show();
        }*/

        return ourDatabase.insert(DATABASE_TABLE1, null, cv);



    }
    public long createEntryGoal(String date,String goal_time, String goal_set){

        ContentValues cv=new ContentValues();
        cv.put(KEY_DATE,date);
        cv.put(KEY_GOAL_TIME,goal_time);
        cv.put(KEY_GOAL_SET,goal_set);

        File dbFile=ourContext.getDatabasePath(DATABASE_TABLE2);
        if(dbFile.exists()) {
            Dialog d = new Dialog(ourContext);
            d.setTitle("Found!");
            TextView tv = new TextView(ourContext);
            tv.setText("DB");
            d.setContentView(tv);
            d.show();
        }
        else {
            Dialog d = new Dialog(ourContext);
            d.setTitle("Not Found!");
            TextView tv = new TextView(ourContext);
            tv.setText("DB");
            d.setContentView(tv);
            d.show();
        }

        return ourDatabase.insert(DATABASE_TABLE2, null, cv);



    }

    public String getHistory()
    {
        String dbString = "No data";
        String date = "";
        //SQLiteDatabase db = getWritableDatabase();
        String query = " SELECT "+ KEY_CALORIES + " , " + KEY_DATE  + " FROM " + DATABASE_TABLE1 + " WHERE 1; ";

        // String query = "SELECT " + KEY_CALORIES + " , " + KEY_DATE + " FROM " + DATABASE_TABLE1 +"; " ;
        Cursor c = ourDatabase.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("CALORIES")) != null && c.getString(c.getColumnIndex("DATE")) != null ) {
                dbString += c.getString(c.getColumnIndex("CALORIES"));
                dbString += ",";
                dbString += c.getString(c.getColumnIndex("DATE"));
                dbString +="-";
                c.moveToNext();
            }
        }

        ourDatabase.close();
        return dbString;
    }
    public String getData() {
//In SQLView class
        //Reading data  from database with CURSOR
        String[] columns= new String[]{KEY_ROWID,KEY_NAME,KEY_PASSWORD};
        Cursor c=ourDatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
        String result  =" ";

        int iRow=c.getColumnIndex(KEY_ROWID);
        int iName=c.getColumnIndex(KEY_NAME);
        int iPassword=c.getColumnIndex(KEY_PASSWORD);

        for (c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
                result=result+c.getString(iRow)+""+c.getString(iName)+"\t\t"+c.getString(iPassword)+"\n";
        }

        return result;
    }

       public String getValidation(String username) {
        //InLogin validation

        /*String[] columns= new String[]{KEY_NAME,KEY_PASSWORD};
        Cursor c=ourDatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
        String result  =" ";

        //int iRow=c.getColumnIndex(KEY_ROWID);
        int iName=c.getColumnIndex(KEY_NAME);
        int iPassword=c.getColumnIndex(KEY_PASSWORD);

        for (c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            result=result+" "+c.getString(iName)+" "+c.getString(iPassword);
        }*/
       Cursor c=ourDatabase.query(DATABASE_TABLE,null,KEY_USERNAME+"=?",new String[]{username},null,null,null);
        if(c.getCount()<1){
            c.close();
            return "NOT EXIST";
        }
        c.moveToFirst();
        String password=c.getString(c.getColumnIndex(KEY_PASSWORD));
        c.close();

        return password;
    }



    private static class DbHelper extends SQLiteOpenHelper{

        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE "+DATABASE_TABLE +" ("+
                            KEY_ROWID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                            KEY_NAME +" TEXT NOT NULL, "+
                            KEY_USERNAME +" TEXT NOT NULL, "+
                            KEY_PASSWORD +" TEXT NOT NULL, "+
                            KEY_EMAILID +" TEXT NOT NULL, "+
                            KEY_AGE +" INTEGER NOT NULL, "+
                            KEY_WEIGHT +" INTEGER NOT NULL, "+
                            KEY_MOBILE +" INTEGER NOT NULL); "
            );

            db.execSQL("CREATE TABLE "+DATABASE_TABLE1 +" ("+
                            KEY_HISTORYID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                            KEY_DATE +" TEXT NOT NULL, "+
                            KEY_ACHIEVED_SET +" TEXT NOT NULL, "+
                            KEY_ACHIEVED_TIME +" TEXT NOT NULL, "+
                            KEY_CALORIES +" TEXT NOT NULL); "

            );
            db.execSQL("CREATE TABLE "+DATABASE_TABLE2 +" ("+
                            KEY_GOALID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                            KEY_DATE +" TEXT NOT NULL, "+
                            KEY_GOAL_SET +" TEXT NOT NULL, "+
                            KEY_GOAL_TIME +" TEXT NOT NULL); "


            );

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
            onCreate(db);
            db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE1);
            onCreate(db);
            db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE2);
            onCreate(db);
        }
    }

    public GymDatabase open() throws SQLException
    {
        ourHelper=new DbHelper(ourContext);
        ourDatabase=ourHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        ourHelper.close();
    }


}
