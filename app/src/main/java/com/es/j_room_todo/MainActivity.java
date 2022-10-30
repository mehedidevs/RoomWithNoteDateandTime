package com.es.j_room_todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import android.text.format.DateFormat;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;


import com.es.j_room_todo.databinding.ActivityMainBinding;

import java.sql.Time;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;

    private int mYear, mMonth, mDay, mHour, mMinute;
    long toDoTimeMillis;
    Calendar c;
    String priorityStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        c = Calendar.getInstance();

        binding.btnDate.setOnClickListener(v -> {

            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            binding.inDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            c.set(Calendar.YEAR, year);
                            c.set(Calendar.MONTH, monthOfYear);
                            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                            toDoTimeMillis = c.getTimeInMillis();


                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();

        });


        binding.btnTime.setOnClickListener(v -> {

            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {


                            binding.inTime.setText(getTime(hourOfDay, minute));
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();


        });


        binding.priorityRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton priorityBtn = findViewById(checkedId);

                priorityStr = priorityBtn.getText().toString();


            }
        });


        binding.addBtn.setOnClickListener(v -> {


            String title = binding.titleEdt.getText().toString().trim();
            String description = binding.descEdt.getText().toString().trim();
            String date = binding.inDate.getText().toString().trim();
            String time = binding.inTime.getText().toString().trim();
            String priority = priorityStr;

            Note note = new Note(title, description, priority, date, "NOT_DONE", time, toDoTimeMillis);

            NoteDatabase.getInstance(this).getNoteDao().insert(note);


        });

        binding.getBtn.setOnClickListener(v -> {
            String today = getToday();

            //  List<Note> notes = NoteDatabase.getInstance(this).getNoteDao().getAllNote(today);

            List<Note> notes = NoteDatabase.getInstance(this).getNoteDao().getAllNoteByPriority("Medium");

            Log.i("TAG", "Notes : " + notes);


        });


    }

    private String getToday() {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        return format.format(calendar.getTime());

    }


    private String getTime(int hr, int min) {
        Time tme = new Time(hr, min, 0);//seconds by default set to zero
        Format formatter = new SimpleDateFormat("h:mm a");
        return formatter.format(tme);
    }
}