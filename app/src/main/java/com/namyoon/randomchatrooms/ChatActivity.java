package com.namyoon.randomchatrooms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        str_user_name = getIntent().getExtras().get("user_name").toString();
        reference = FirebaseDatabase.getInstance().getReference().child(str_room_name);

        setTitle(str_room_name);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr_room);
        lv_chatting.setAdapter(arrayAdapter);
        lv_chatting.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> map = new HashMap<String, Object>();
                key = reference.push().getKey();
                reference.updateChildren(map);

                DatabaseReference root = reference.child(key);

                Map<String, Object> objMap = new HashMap<String, Object>();
                objMap.put("name", str_user_name);
                objMap.put("message", et_send.getText().toString());

                root.updateChildren(objMap);

                et_send.setText("");
            }
        });
    }
}
