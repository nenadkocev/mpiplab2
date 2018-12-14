package kocev.nenad.lab2;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Movie.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MovieDao MovieDao();
    private static volatile AppDatabase INSTANCE;

    static AppDatabase getDatabaseConnection(final Context context){
        if(INSTANCE == null){
            synchronized (AppDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(
                            context,
                            AppDatabase.class,
                            "movie_database")
                            .fallbackToDestructiveMigration()
                    .build();
                }
            }
        }
        return INSTANCE;
    }
}
