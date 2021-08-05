package mg.itu.lazanomentsoa.itutptparis.sqlite;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.jetbrains.annotations.NotNull;

import mg.itu.lazanomentsoa.itutptparis.sqlite.model.Profil;

@Database(entities = Profil.class, version = 1, exportSchema = false)
public abstract class TPTDatabase extends RoomDatabase {
    private static TPTDatabase instance;
    public abstract ProfilDao profilDao();

    public static synchronized TPTDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), TPTDatabase.class, "tpt_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull @NotNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };

}
