package com.oneasad.socialapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    // Get all posts
    @Query("SELECT * FROM post_table ORDER BY userId ASC")
    fun getAll(): Flow<List<LocalPost>>

    // Get posts by userId
    @Query("SELECT * FROM post_table WHERE userId = :userId ORDER BY id ASC")
    fun getByUserId(userId: Int): Flow<List<LocalPost>>

    // Insert a post (for both single and multiple)
    @Insert(onConflict = OnConflictStrategy.REPLACE)  // Replace if already exists
    suspend fun insert(post: LocalPost)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<LocalPost>)

    // Update an existing post
    @Update
    suspend fun update(post: LocalPost)

    // Delete a single post
    @Delete
    suspend fun delete(post: LocalPost)

    // Delete all posts
    @Query("DELETE FROM post_table")
    suspend fun deleteAll()
}
