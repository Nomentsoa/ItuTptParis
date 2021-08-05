package mg.itu.lazanomentsoa.itutptparis.sqlite;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import mg.itu.lazanomentsoa.itutptparis.sqlite.model.Profil;

@Dao
public interface ProfilDao {
    @Insert
    void Insert(Profil profil);

    @Update
    void Update(Profil profil);

    @Query("SELECT * FROM table_profil WHERE idUser =:userId")
    LiveData<Profil> getProfil(String userId);

    @Query("SELECT * FROM table_profil")
    LiveData<List<Profil>> getAllProfil();

}
