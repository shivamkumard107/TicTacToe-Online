package com.example.shivam.tic_tac_toeonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btHost, btJoin;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btHost = (Button) findViewById(R.id.bt_host);
        btJoin = (Button) findViewById(R.id.bt_join);

        btHost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Host host = new Host();
                host.setTurn(false);

                Away away = new Away();
                away.setTurn(false);

                Box box = new Box() ;
                box.setTakenType(true); //true --> yellow false --> red
                box.boxPosition = new ArrayList<Integer>(9){{
                    add(-1);
                    add(-1);
                    add(-1);
                    add(-1);
                    add(-1);
                    add(-1);
                    add(-1);
                    add(-1);
                    add(-1);
                }};
                box.setBoxPosition(box.boxPosition);

                box.setIstaken(false);

                Game game = new Game();
                game.setHost(host);
                game.setAway(away);
                game.setBox(box);
                game.setLastMove(0);
                game.setStarted(false);
                game.setHostZero(true);




                String hostNumber = "9582857833";
                databaseReference.child(hostNumber).setValue(game);

                Intent intent = new Intent(MainActivity.this, Game_PlayActivity.class);
                intent.putExtra("isHost", true);
                intent.putExtra("code", hostNumber);
                startActivity(intent);
                finish();

            }
        });

        btJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, JoinActivity.class));
                finish();

            }
        });

    }
}
