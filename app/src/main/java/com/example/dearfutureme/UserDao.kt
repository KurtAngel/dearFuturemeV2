package com.example.dearfutureme

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun getUsername(user: User)

//    @Query("SELECT pId FROM user_acc")
//    fun deleteById(pId: Int)

    @Query("DELETE FROM user_acc WHERE username = :username")
    suspend fun deleteByUsername(username: String)

    @Query("DELETE FROM user_acc WHERE pId = :pId")
    suspend fun deleteById(pId : Int)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM user_acc WHERE username = :name AND password = :password")
    suspend fun getUserByNameAndPassword(name: String, password: String): User?

    @Query("SELECT COUNT(*) > 0 FROM user_acc WHERE username = :username")
    suspend fun isUsernameExists(username: String): Boolean

    @Query("SELECT pId FROM user_acc ORDER BY pId DESC LIMIT 1")
    fun getLastId(): Int?

    @Query("SELECT * FROM user_acc")
    fun getAllData() : Flow<List<User>>
}