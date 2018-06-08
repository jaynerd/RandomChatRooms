package com.namyoon.randomchatrooms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    private ListView lv_chatting;
    private EditText et_send;
    private Button btn_send;

    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> arr_room = new ArrayList<>();

    private String str_room_name;
    private String str_user_name;

    private DatabaseReference reference;
    private String key;
    private String chat_user;
    private String chat_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        et_send = (EditText) findViewById(R.id.et_send);
        lv_chatting = (ListView) findViewById(R.id.lv_chatting);
        btn_send = (Button) findViewById(R.id.btn_send);

        str_room_name = getIntent().getExtras().get("room_name").toString();
    }
}
