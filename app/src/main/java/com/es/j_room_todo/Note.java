package com.es.j_room_todo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Note {

    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo
    private String noteTitle, noteDescription, notePriority, whenDate, noteStatus, whenToDo;

    private Long dateMilis;

    public Note(String noteTitle, String noteDescription, String notePriority, String whenDate, String noteStatus, String whenToDo, Long dateMilis) {
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
        this.notePriority = notePriority;
        this.whenDate = whenDate;
        this.noteStatus = noteStatus;
        this.whenToDo = whenToDo;
        this.dateMilis = dateMilis;
    }

    public Long getDateMilis() {
        return dateMilis;
    }

    public void setDateMilis(Long dateMilis) {
        this.dateMilis = dateMilis;
    }

    public String getWhenToDo() {
        return whenToDo;
    }

    public void setWhenToDo(String whenToDo) {
        this.whenToDo = whenToDo;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public String getNotePriority() {
        return notePriority;
    }

    public void setNotePriority(String notePriority) {
        this.notePriority = notePriority;
    }

    public String getWhenDate() {
        return whenDate;
    }

    public void setWhenDate(String whenDate) {
        this.whenDate = whenDate;
    }

    public String getNoteStatus() {
        return noteStatus;
    }

    public void setNoteStatus(String noteStatus) {
        this.noteStatus = noteStatus;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", noteTitle='" + noteTitle + '\'' +
                ", noteDescription='" + noteDescription + '\'' +
                ", notePriority='" + notePriority + '\'' +
                ", whenDate='" + whenDate + '\'' +
                ", noteStatus='" + noteStatus + '\'' +
                ", whenToDo='" + whenToDo + '\'' +
                ", dateMilis=" + dateMilis +
                '}';
    }
}
