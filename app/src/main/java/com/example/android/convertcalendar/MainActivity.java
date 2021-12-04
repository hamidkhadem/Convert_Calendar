package com.example.android.convertcalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    //get month input from spinner
    private int month = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setInputMonth();


        Button convertButton = findViewById(R.id.convert);
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Date convertDate = ConvertDate.PersianToGregorianCalender(inputDate());
                Date inputDate = inputDate();
                Date convertedDate = ConvertDate.PersianToGregorianCalender(inputDate.getYear(),inputDate.getMonth(),inputDate.getDate());
                showDate(convertedDate);
            }
        });



    }

    private void showDate(Date date) {
        //edit format
        String dateString = formatGregorianDate(date);
        //set string date
        TextView dateOutput = findViewById(R.id.show_date);
        dateOutput.setText(dateString);

    }

    private Date inputDate(){
        //Declare date for return
        Date date;

        //get year input from user
        EditText yearInput = findViewById(R.id.year);
        Editable yearEdit = yearInput.getText();
        int year = Integer.parseInt(yearEdit.toString());


        //get day input from user
        EditText dayInput = findViewById(R.id.day);
        Editable dayEdit = dayInput.getText();
        int day = Integer.parseInt(dayEdit.toString());

        date = new Date(year, month,day);
        return date;
    }

    private String formatGregorianDate(Date date){

/*
        Date date1 = new Date( date.getYear() , date.getMonth() , date.getDate());
        String pattern = "yyyy-MMM";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String stringDate = simpleDateFormat.format(date1);
        return stringDate;
*/
        String s = "" + date.getYear() + " - " + date.getMonth() + " - " + date.getDate();
        return s;

    }

    private void setInputMonth(){
        Spinner spinner =  findViewById(R.id.month);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.iranian_month_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                month = i + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                month = 1;
            }
        });

    }

    /*month = i + 1;

    Date convertDate = ConvertDate.PersianToGregorianCalender(inputDate());
    showDate(convertDate);
    */
}