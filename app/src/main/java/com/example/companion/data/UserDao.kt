package com.example.companion.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

interface UserDao {

    @Query("SELECT * FROM user ORDER BY id ASC")
    fun getUsers(): Flow<User>

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getUser(id: Int): User

    @Insert suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}