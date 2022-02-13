package com.ruller.officalapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    //destination path (location) of our database on device
    private static String DB_PATH = "";
    private static String DB_NAME = "dict.db";// Database name

    //private static String DB_NAME = "topic.db";// Database name
    private SQLiteDatabase mDataBase;
    private final Context mContext;


    private String AV_TABLE = "av";
    private String VA_TABLE = "va";

    public String ID = "id";
    public String WORD = "word";
    public String HTML = "html";
    public String DES = "description";
    public String PRO = "pronounce";

    private String TABLE_NAME = "IrregularVerb";


    // do đường dẫn ở phiên bản API > 17 thay đổi nên chúng ta cần kiểm tra nhé
    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, 1);// 1? Its database Version
        if (android.os.Build.VERSION.SDK_INT >= 17) {
            this.DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            this.DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
        Log.i("Path",DB_PATH);
        this.mContext = context;
    }

    public void createDataBase() {
        //If the database does not exist, copy it from the assets.

        boolean mDataBaseExist = checkDataBase();
        if (!mDataBaseExist) {
            this.getReadableDatabase();
            this.close();
            try {
                //Copy the database from assests
                copyDataBase();
                Log.i("DataBaseHelper", "createDatabase database created");
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    //Check that the database exists here: /data/data/your package/databases/Da Name
    public boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        //Log.v("dbFile", dbFile + "   "+ dbFile.exists());
        return dbFile.exists();
    }

    //Copy the database from assets
    private void copyDataBase() throws IOException {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0) {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
        Log.i("Copy Database","done!!");
    }

    //Open the database, so we can query it
    public void openDataBase() throws SQLException {
        String mPath = DB_PATH + DB_NAME;
        //Log.v("mPath", mPath);
        mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.OPEN_READWRITE);
        //mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
    }

    @Override
    public synchronized void close() {
        if (mDataBase != null)
            mDataBase.close();
        super.close();
    }

    // ***********************************************************
    // ***********************************************************
    // ***********************************************************
    // SQL FOR DATABASE
    public void doSQL(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String SQL = null;
        //SQL = "ALTER TABLE av ADD COLUMN checked INTEGER";
        //SQL = "UPDATE av SET learned = 0 WHERE id < 120000";
        sqLiteDatabase.execSQL(SQL);
    }

    //
    // ***********************************************************
    // ***********************************************************
    // ***********************************************************
    // LOOK UP IN ENGLISH - VIETNAMESE

    public String searchword(String text) {
        String word = null;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String SQL = "SELECT * FROM " + AV_TABLE + " WHERE " + WORD + " = '" + text + "'";

        Cursor cursor = sqLiteDatabase.rawQuery(SQL, null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {

                cursor.moveToFirst();
                word= cursor.getString(cursor.getColumnIndex(HTML));
                cursor.close();
            }
        }
        return word;
    }

    public int get_learned(String text) {
        int state = -1;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String SQL = "SELECT * FROM " + AV_TABLE + " WHERE " + WORD + " = '" + text + "'";

        Cursor cursor = sqLiteDatabase.rawQuery(SQL, null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {

                cursor.moveToFirst();
                state= cursor.getInt(cursor.getColumnIndex("learned"));
                cursor.close();
            }
        }
        return state;
    }

    //
    public void updateWordMeaning_learned(int state, String word){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "UPDATE av SET learned = " + state + " WHERE word = '" + word + "'";
        sqLiteDatabase.execSQL(query);
    }

    // ***********************************************************
    // ***********************************************************
    // ***********************************************************
    // LOOK UP IN VIETNAMESE - ENGLISH

    // Search word Vietnamese - English
    public String searchWordVietEng(String text) {
        String word = null;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String SQL = "SELECT * FROM " + VA_TABLE + " WHERE " + WORD + " = '" + text + "'";



        Cursor cursor = sqLiteDatabase.rawQuery(SQL, null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {

                cursor.moveToFirst();

                //word.description = cursor.getString(cursor.getColumnIndex(DES));
                word= cursor.getString(cursor.getColumnIndex(HTML));

                cursor.moveToNext();


                cursor.close();
            }
        }

        return word;
    }

    public Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    // ***********************************************************
    // ***********************************************************
    // ***********************************************************
    // FOR TOEIC FUNCTION

    public Cursor readAllData_fromToeic(){
        String query = "SELECT * FROM " + "TOEIC";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public int get_learned_TOEIC(String text) {
        int state = -1;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String SQL = "SELECT * FROM " + "TOEIC" + " WHERE " + WORD + " = '" + text + "'";

        Cursor cursor = sqLiteDatabase.rawQuery(SQL, null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {

                cursor.moveToFirst();
                state= cursor.getInt(cursor.getColumnIndex("learned"));
                cursor.close();
            }
        }
        return state;
    }

    public void updateTOEIC_learned(int state, String word){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "UPDATE TOEIC SET learned = " + state + " WHERE word = '" + word + "'";
        sqLiteDatabase.execSQL(query);
    }

    // ***********************************************************
    // ***********************************************************
    // ***********************************************************
    // FOR IELTS FUNCTION

    public Cursor readAllData_fromIelts(){
        String query = "SELECT * FROM " + "IELTS";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    // ***********************************************************
    // ***********************************************************
    // ***********************************************************
    // FOR MY VOCABULARY CONTROL

    // CREATE
    // check folder exist ?
    public int check_folder_exist(String text){
        Cursor cursor = null;
        int res = -1;
        String query = "SELECT * FROM myvocabulary_table WHERE table_name = '" + text +"'";
        SQLiteDatabase db = this.getWritableDatabase();
        cursor = db.rawQuery(query,null);
        if(cursor.getCount() == 0) res = 0;
        else  res = 1;
        Log.i("res",String.valueOf(res));
        return  res;

    }

    // Add folder in vocabulary_table
    public void add_folder_to_vocabulary_table(String text){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("table_name", text);
        db.insert("myvocabulary_table",null,contentValues);
    }

    // Create new folder_table
    public void createFolder(String text){
        String query = "CREATE TABLE `" + text +"` (word TEXT, meaning TEXT)";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
        Log.i("Create folder", "done");
    }

    //add word to folder
    public void add_word_item(String word, String meaning, String folder_name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "INSERT INTO `" + folder_name + "` VALUES ('" + word + "','" + meaning +"') ";
        db.execSQL(query);
    }

    // DELETE

    // Delete folder in myvocabulary_table
    public void delete_folder_to_vocabulary_table(String text){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "CREATE TABLE `" + text +"` (word TEXT, meaning TEXT)";
        db.execSQL(query);
        Log.i("Create folder", "done");
    }


    // Delete folder_table

    // READ DATA

    // Read All Folder Name
    public Cursor ReadAllFolderName(){
        String query = "SELECT * FROM  myvocabulary_table";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    // Read Data From Folder
    public Cursor readAllData_fromFolder(String folder){

        String query = "SELECT * FROM  `"+ folder + "`";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    //

    public String readData_fromFolder(String folder_name, String word){
        String query = "SELECT * FROM `" + folder_name + "` WHERE word ='" + word + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        String meaning = null;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {

                cursor.moveToFirst();

                //word.description = cursor.getString(cursor.getColumnIndex(DES));
                meaning= cursor.getString(cursor.getColumnIndex("meaning"));

                cursor.moveToNext();


                cursor.close();
            }
        }

        return meaning;
    }

    public void delete_word_item(String word, String table){
        String query = "DELETE FROM `" + table + "` WHERE word ='" + word + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(query);
    }

    public void delete_folder(String folder_name){
        String query = "DROP TABLE `" + folder_name + "`";
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(query);
        query = "DELETE FROM myvocabulary_table WHERE table_name ='" + folder_name + "'";
        db.execSQL(query);
    }

    public void edit_folder(String folder_name, String new_name){
        String query = "UPDATE myvocabulary_table SET table_name = '" + new_name + "' WHERE table_name = '" + folder_name + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(query);
        query = "ALTER TABLE `" + folder_name + "`" + " RENAME TO `" + new_name + "`";
        db.execSQL(query);
    }
    // ***********************************************************
    // ***********************************************************
    // ***********************************************************



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
