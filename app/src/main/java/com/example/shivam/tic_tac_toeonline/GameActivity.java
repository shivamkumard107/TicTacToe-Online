package com.example.shivam.tic_tac_toeonline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GameActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    Button btUpdate;
    String code;
    TextView tvLoading, tvTurn;

    Boolean isInitialised = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

//        tvLoading = (TextView) findViewById(R.id.tv_loading);
//        tvTurn = (TextView) findViewById(R.id.tv_turn);
//        btUpdate = (Button) findViewById(R.id.bt_last_move);
//
//
//        final boolean isHost = getIntent().getBooleanExtra("isHost", false);
//
//        code = getIntent().getStringExtra("code");
//
//        if (!isInitialised) {
//
//            Log.i("CODE", "HERE");
//            databaseReference.child(code).addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//
//                    Game game = dataSnapshot.getValue(Game.class);
//
//
//                    if (game != null && !game.isStarted) {
//                        if (!isHost) {
//                            Log.i("CODE", "HERE");
//                            game.isStarted = true;
//                            game.host.setTurn(true);
//                            game.away.setTurn(false);
//                            isInitialised = true;
//                            databaseReference.child(code).setValue(game);
//                            btUpdate.setVisibility(View.VISIBLE);
//                        }
//                    }
//
//                    if (game != null && game.isStarted) {
//
//                        tvLoading.setVisibility(View.GONE);
//                        tvTurn.setVisibility(View.VISIBLE);
//                        isInitialised = true;
//
//                    }
//
//
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });
//
//
//        }
//
//
//
//
//
//        btUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                databaseReference.child(code).addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//
//                        Game game = dataSnapshot.getValue(Game.class);
//                        boolean hostTurn = game.host.turn;
//                        boolean awayTurn = game.away.turn;
//
//                        if (isHost && hostTurn) {
//
//                            game.lastMove = 100;
//                            game.host.setTurn(false);
//                            game.away.setTurn(true);
//                            databaseReference.child(code).setValue(game);
//                            tvTurn.setVisibility(View.INVISIBLE);
//                        }
//
//                        else if (!isHost && awayTurn) {
//                            game.lastMove = 9;
//                            game.host.setTurn(true);
//                            game.away.setTurn(false);
//                            databaseReference.child(code).setValue(game);
//                            tvTurn.setVisibility(View.INVISIBLE);
//                        }
//
//
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });
//
//
//            }
//        });
//
    }
}
