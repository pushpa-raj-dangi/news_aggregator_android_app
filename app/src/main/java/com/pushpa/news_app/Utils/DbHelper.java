package com.pushpa.news_app.Utils;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.pushpa.news_app.models.News;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
    private SQLiteDatabase db;
Context context ;

    public DbHelper(Context c){
        super(c,"news.db",null,1);
        this.context = c;
        db = getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " +
                "news(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title VARCHAR(255)," +
                " url VARCHAR(255)," +
                "publishedAt varchar(250)," +
                "description VARCHAR(1200)," +
                " urlToImage varchar(255)," +
                "favStatus text not null default '',"+
                " author VARCHAR(240) )");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " +
                "favorite_news(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title VARCHAR(255)," +
                " url VARCHAR(255)," +
                "publishedAt varchar(250)," +
                "description VARCHAR(1200)," +
                " urlToImage varchar(255)," +
                "favStatus text not null default '0',"+
                " author VARCHAR(240) )");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " +
                "users(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username VARCHAR(255)," +
                "password VARCHAR(255)," +
                "email varchar(250),"+
                "favorite_id INTEGER ," +
                "CONSTRAINT fk_favorites FOREIGN KEY(favorite_id) REFERENCES favorites(id))");


        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " +
                "favorites(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title VARCHAR(255))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS news.db");
        onCreate(sqLiteDatabase);
    }

    public void insertDataToDb(News news) {
        ContentValues cv = new ContentValues();
        cv.put("title", news.getTitle());
        cv.put("url",news.getTitle());
        cv.put("publishedAt", news.getPublishedAt());
        cv.put("description",news.getDescription());
        cv.put("urlToImage",news.getImage());
        cv.put("author",news.getAuthor());
        db.insert("news", null, cv);
    }
     public void addToFav(News news) {
            Log.e("TAG", "insertDataToDb: Success" );
            if(checkIfExist(news.getTitle())){
                delete(news.getTitle());
                Log.e("TAG", "addToFav:deleted" );
                Toast.makeText(this.context, "Favorite removed", Toast.LENGTH_SHORT).show();
            }else{
                ContentValues cv = new ContentValues();
                cv.put("title", news.getTitle());
                cv.put("url",news.getLink());
                cv.put("publishedAt", news.getPublishedAt());
                cv.put("description",news.getDescription());
                cv.put("urlToImage",news.getImage());
                cv.put("favStatus","1");
                cv.put("author",news.getAuthor());
                db.insert("favorite_news", null, cv);
                Toast.makeText(this.context, "Favorite added Successfully", Toast.LENGTH_SHORT).show();

            }

        }


    public long delete(String  title) {
       return db.delete("favorite_news","title=?",new String[]{title});

    }

    public ArrayList<News> retrieveData() {
        ArrayList<News> data = new ArrayList<>();
        String query = "SELECT * FROM favorite_news";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                News st = new News();
                st.setTitle(cursor.getString(1));
                st.setLink(cursor.getString(2));
                st.setTime(cursor.getString(3));
                st.setDescription(cursor.getString(4));
                st.setImage(cursor.getString(5));
                st.setFavoriteStatus(cursor.getString(6));
                st.setAuthor(cursor.getString(7));

                data.add(st);

            }while(cursor.moveToNext());
        }
        return data;
    }

    public ArrayList<News> getFavoriteNews() {
        ArrayList<News> data = new ArrayList<>();
        String query = "SELECT * FROM favorite_news";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                News st = new News();
                st.setTitle(cursor.getString(1));
                st.setLink(cursor.getString(2));
                st.setTime(cursor.getString(3));
                st.setDescription(cursor.getString(4));
                st.setImage(cursor.getString(5));
                st.setFavoriteStatus(cursor.getString(6));
                st.setAuthor(cursor.getString(7));

                data.add(st);

            }while(cursor.moveToNext());
        }
        return data;
    }
    public boolean checkIfExist(String title) {
        Cursor csr = db.query("favorite_news",null,"title" + "=? ",new String[]{title},null,null,null);
            boolean rv = (csr.getCount() > 0);
            csr.close();
            return rv;


    }
}
