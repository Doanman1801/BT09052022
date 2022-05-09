package com.example.bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.os.Bundle;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    CalendarView simpleCalendarView;
    TextView dob_text, dob_label, major_label, programLanguage_label;
    EditText id_text;
    EditText name_text;
    EditText cccd_text;
    EditText phone_text;
    EditText email_text;
    RadioButton KH, KT;
    CheckBox C_id, Java_id, Python_id, ok;
    Button button;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id_text =(EditText) findViewById(R.id.id_text);
        name_text =(EditText) findViewById(R.id.name_text);
        cccd_text =(EditText) findViewById(R.id.cccd_text);
        phone_text =(EditText) findViewById(R.id.phone_text);
        email_text =(EditText) findViewById(R.id.email_text);
        dob_text = (TextView) findViewById(R.id.dob_text);
        dob_label = (TextView) findViewById(R.id.dob_label);
        programLanguage_label = (TextView) findViewById(R.id.programLanguage_label);
        KH = (RadioButton) findViewById(R.id.KH);
        KT = (RadioButton) findViewById(R.id.KT);
        C_id = (CheckBox) findViewById(R.id.C_id);
        Java_id = (CheckBox) findViewById(R.id.Java_id);
        Python_id = (CheckBox) findViewById(R.id.Python_id);
        ok = (CheckBox) findViewById(R.id.ok);

        major_label = (TextView) findViewById(R.id.major_label);
        List<EditText> list = new ArrayList<EditText>();
        list.add(id_text);
        list.add(name_text);
        list.add(cccd_text);
        list.add(phone_text);
        list.add(email_text);
        for(int i = 0 ; i<list.size(); i++){
            int finalI = i;
            list.get(i).addTextChangedListener(new TextWatcher() {
                EditText editText = list.get(finalI);
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    editText.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));

                }
            });
        }

        dob_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                dob_label.setTextColor(ColorStateList.valueOf(Color.BLACK));
            }
        });
        simpleCalendarView = (CalendarView) findViewById(R.id.simpleCalendarView);

//        info.setTextColor(Integer.parseInt("yellow"));
        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // display the selected date by using a toast
                dob_text.setText(dayOfMonth + "/" + (month+1) + "/" + year);
//                Toast.makeText(getApplicationContext(), dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
            }
        });
        List<CheckBox> list1 = new ArrayList<CheckBox>();
        list1.add(C_id);
        list1.add(Java_id);
        list1.add(Python_id);

        for(int i = 0 ;i<list1.size(); i++){

            list1.get(i).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    programLanguage_label.setTextColor(ColorStateList.valueOf(Color.BLACK));
                }
            });
        }

        List<RadioButton> list2 = new ArrayList<RadioButton>();
        list2.add(KH);
        list2.add(KT);

        for(int i = 0 ;i<list2.size(); i++){

            list2.get(i).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    major_label.setTextColor(ColorStateList.valueOf(Color.BLACK));
                }
            });
        }
        button = (Button) findViewById(R.id.but_submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count =0;
                for(int i = 0 ; i<list.size(); i++){
                    if(list.get(i).getText().length() == 0){
                        count++;
                        list.get(i).setBackgroundTintList(ColorStateList.valueOf(Color.RED));

                    }
                }
                int flagCheckbox = 0;
                for(int i = 0 ; i< list1.size(); i++){
                    if(list1.get(i).isChecked() == false){
                        flagCheckbox++;
                    }
                }

                if(flagCheckbox == list1.size()){
                    count++;
                    programLanguage_label.setTextColor(ColorStateList.valueOf(Color.RED));

                }

                int flagRadio = 0;
                for(int i = 0 ; i< list2.size(); i++){
                    if(list2.get(i).isChecked() == false){
                        flagRadio++;
                    }
                }

                if(flagRadio == list2.size()){
                    count++;
                    major_label.setTextColor(ColorStateList.valueOf(Color.RED));

                }

                if (dob_text.getText().length() == 0){
                    count++;
                    dob_label.setTextColor(ColorStateList.valueOf(Color.RED));

                }

                if (ok.isChecked() == false){
                    count++;
                }
                if(count == 0){

                    Toast.makeText(MainActivity.this, "Thành công", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}