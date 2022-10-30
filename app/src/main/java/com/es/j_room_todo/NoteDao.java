package com.es.j_room_todo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);


    @Query("SELECT * FROM Note")
    List<Note> getAllNote();

    @Query("SELECT * FROM Note  WHERE whenDate == :today")
    List<Note> getAllNote(String today);

    @Query("SELECT * FROM Note  WHERE notePriority == :priority")
    List<Note> getAllNoteByPriority(String priority);

}
