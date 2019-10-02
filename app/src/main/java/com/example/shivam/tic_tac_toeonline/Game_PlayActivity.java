package com.example.shivam.tic_tac_toeonline;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class Game_PlayActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    String code;
    Game game;
    boolean isHost;
    String hostCode;

    LinearLayout timer;
    Timer t;

    int minutes=0,seconds=0;

    ImageView iv_counter; // first box in the game iv_box1
    ImageView iv_pointer;
    TextView host_code,min,sec;


    ImageView iv_box2, iv_box3, iv_box4, iv_box5, iv_box6, iv_box7, iv_box8, iv_box9;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game__play);

        minutes=seconds=0;


        iv_pointer = findViewById(R.id.iv_pointer);
        iv_pointer.setImageResource(R.drawable.right_pointer);
        iv_counter = findViewById(R.id.iv_pt1);

        iv_box2 = findViewById(R.id.iv_pt2);
        iv_box3 = findViewById(R.id.iv_pt3);
        iv_box4 = findViewById(R.id.iv_pt4);
        iv_box5 = findViewById(R.id.iv_pt5);
        iv_box6 = findViewById(R.id.iv_pt6);
        iv_box7 = findViewById(R.id.iv_pt7);
        iv_box8 = findViewById(R.id.iv_pt8);
        iv_box9 = findViewById(R.id.iv_pt9);

        iv_counter.setOnClickListener(this);
        iv_box2.setOnClickListener(this);
        iv_box3.setOnClickListener(this);
        iv_box4.setOnClickListener(this);
        iv_box5.setOnClickListener(this);
        iv_box6.setOnClickListener(this);
        iv_box7.setOnClickListener(this);
        iv_box8.setOnClickListener(this);
        iv_box9.setOnClickListener(this);

        timer = findViewById(R.id.timer);
        host_code = findViewById(R.id.tv_code);
        min = findViewById(R.id.minutes);
        sec = findViewById(R.id.seconds);

        isHost = getIntent().getBooleanExtra("isHost", true);
        hostCode = getIntent().getStringExtra("code");

        code = getIntent().getStringExtra("code");
        host_code.setText(hostCode);
        // making the code readble by making it bold
        host_code.setTypeface(null, Typeface.BOLD);
//        code = "9582184794";
        databaseReference.child(code).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                game = dataSnapshot.getValue(Game.class);

                Log.i("CODE", "5");
                if (game != null && game.isStarted) {

                    // creating a timer to keep track of who takes min time to win
                    //Declare the timer
                    t = new Timer();

                    //Set the schedule function and rate
                    // timer runs
                    t.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    min.setText(String.valueOf(minutes));
                                    if(seconds<10)
                                        sec.setText("0"+ seconds);
                                    else
                                        sec.setText(String.valueOf(seconds));
                                    seconds += 1;

                                    if(seconds == 59 )
                                    {
                                        seconds=0;
                                        minutes+=1;
                                        min.setText(String.valueOf(minutes));

                                        if(seconds<10)
                                            sec.setText("0"+ seconds);
                                        else
                                            sec.setText(String.valueOf(seconds));

                                        seconds += 1;

                                    }



                                }

                            });
                        }

                    }, 0, 1000);






                    boolean hostTurn = game.host.turn;
                    boolean awayTurn = game.away.turn;
                    iv_pointer.setVisibility(View.GONE);
                    if (isHost && hostTurn) {
                        host_code.setText(hostCode);
                        Log.i("CODE", "1");
                        Log.i("isHost", "hostTurn");
                        onClickListeners();
                        fillingInBox();

                        if (!game.box.getBoxPosition().get(0).equals(-1)) {
                            iv_counter.setClickable(false);
                            ;
                        }
                        if (!game.box.getBoxPosition().get(1).equals(-1)) {
                            iv_box2.setClickable(false);
                        }
                        if (!game.box.getBoxPosition().get(2).equals(-1)) {
                            iv_box3.setClickable(false);
                        }
                        if (!game.box.getBoxPosition().get(3).equals(-1)) {
                            iv_box4.setClickable(false);
                        }
                        if (!game.box.getBoxPosition().get(4).equals(-1)) {
                            iv_box5.setClickable(false);
                        }
                        if (!game.box.getBoxPosition().get(5).equals(-1)) {
                            iv_box6.setClickable(false);
                        }
                        if (!game.box.getBoxPosition().get(6).equals(-1)) {
                            iv_box7.setClickable(false);
                        }
                        if (!game.box.getBoxPosition().get(7).equals(-1)) {
                            iv_box8.setClickable(false);
                        }
                        if (!game.box.getBoxPosition().get(8).equals(-1)) {
                            iv_box9.setClickable(false);
                        }

                    } else if (isHost && awayTurn) {
                        Log.i("CODE", "2");
                        Log.i("isHost", "awayTurn");
                        onClickListeners();
                        fillingInBox();
                        iv_counter.setClickable(false);
                        iv_box2.setClickable(false);
                        iv_box3.setClickable(false);
                        iv_box4.setClickable(false);
                        iv_box5.setClickable(false);
                        iv_box6.setClickable(false);
                        iv_box7.setClickable(false);
                        iv_box8.setClickable(false);
                        iv_box9.setClickable(false);

                    } else if (!isHost && awayTurn) {
                        Log.i("! isHost", "awayTurn");
                        Log.i("CODE", "3");

                        onClickListeners();
                        fillingInBox();
                        if (!game.box.getBoxPosition().get(0).equals(-1)) {
                            iv_counter.setClickable(false);
                            ;
                        }
                        if (!game.box.getBoxPosition().get(1).equals(-1)) {
                            iv_box2.setClickable(false);
                        }
                        if (!game.box.getBoxPosition().get(2).equals(-1)) {
                            iv_box3.setClickable(false);
                        }
                        if (!game.box.getBoxPosition().get(3).equals(-1)) {
                            iv_box4.setClickable(false);
                        }
                        if (!game.box.getBoxPosition().get(4).equals(-1)) {
                            iv_box5.setClickable(false);
                        }
                        if (!game.box.getBoxPosition().get(5).equals(-1)) {
                            iv_box6.setClickable(false);
                        }
                        if (!game.box.getBoxPosition().get(6).equals(-1)) {
                            iv_box7.setClickable(false);
                        }
                        if (!game.box.getBoxPosition().get(7).equals(-1)) {
                            iv_box8.setClickable(false);
                        }
                        if (!game.box.getBoxPosition().get(8).equals(-1)) {
                            iv_box9.setClickable(false);
                        }


                    } else if (!isHost && hostTurn) {
                        Log.i("! isHost", "hostTurn");
                        Log.i("CODE", "4");
                        onClickListeners();
                        fillingInBox();
                        iv_counter.setClickable(false);
                        iv_box2.setClickable(false);
                        iv_box3.setClickable(false);
                        iv_box4.setClickable(false);
                        iv_box5.setClickable(false);
                        iv_box6.setClickable(false);
                        iv_box7.setClickable(false);
                        iv_box8.setClickable(false);
                        iv_box9.setClickable(false);

                    }

                } else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(Game_PlayActivity.this);
                    dialog.setMessage("Game not Connected");
                    dialog.show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void onClickListeners() {
        iv_counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                                int position = 0 ;
                updateDatabase(iv_counter, 0);
//                checkWin();
            }
        });
        iv_box2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                                int position = 1 ;
                updateDatabase(iv_box2, 1);
//                checkWin();
            }
        });
        iv_box3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDatabase(iv_box3, 2);
//                checkWin();
            }
        });

        iv_box4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDatabase(iv_box4, 3);
//                checkWin();
            }
        });
        iv_box5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDatabase(iv_box5, 4);
//                checkWin();
            }
        });
        iv_box6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDatabase(iv_box6, 5);
//                checkWin();
            }
        });
        iv_box7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDatabase(iv_box7, 6);
//                checkWin();
            }
        });
        iv_box8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDatabase(iv_box8, 7);
//                checkWin();
            }
        });
        iv_box9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDatabase(iv_box9, 8);
//                checkWin();
            }
        });
    }

    public void checkWin() {

        if (game.box.getBoxPosition().get(0) == 1 && game.box.getBoxPosition().get(1) == 1 && game.box.getBoxPosition().get(2) == 1) {
            yellowWon();
        } else if (game.box.getBoxPosition().get(3) == 1 && game.box.getBoxPosition().get(4) == 1 && game.box.getBoxPosition().get(5) == 1) {
            yellowWon();
        } else if (game.box.getBoxPosition().get(6) == 1 && game.box.getBoxPosition().get(7) == 1 && game.box.getBoxPosition().get(8) == 1) {
            yellowWon();
        } else if (game.box.getBoxPosition().get(0) == 1 && game.box.getBoxPosition().get(3) == 1 && game.box.getBoxPosition().get(6) == 1) {
            yellowWon();
        } else if (game.box.getBoxPosition().get(1) == 1 && game.box.getBoxPosition().get(4) == 1 && game.box.getBoxPosition().get(7) == 1) {
            yellowWon();
        } else if (game.box.getBoxPosition().get(2) == 1 && game.box.getBoxPosition().get(5) == 1 && game.box.getBoxPosition().get(8) == 1) {
            yellowWon();
        } else if (game.box.getBoxPosition().get(0) == 1 && game.box.getBoxPosition().get(4) == 1 && game.box.getBoxPosition().get(8) == 1) {
            yellowWon();
        } else if (game.box.getBoxPosition().get(2) == 1 && game.box.getBoxPosition().get(4) == 1 && game.box.getBoxPosition().get(6) == 1) {
            yellowWon();
        } else if (game.box.getBoxPosition().get(0) == 0 && game.box.getBoxPosition().get(1) == 0 && game.box.getBoxPosition().get(2) == 0) {
            redWon();
        } else if (game.box.getBoxPosition().get(3) == 0 && game.box.getBoxPosition().get(4) == 0 && game.box.getBoxPosition().get(5) == 0) {
            redWon();
        } else if (game.box.getBoxPosition().get(6) == 0 && game.box.getBoxPosition().get(7) == 0 && game.box.getBoxPosition().get(8) == 0) {
            redWon();
        } else if (game.box.getBoxPosition().get(0) == 0 && game.box.getBoxPosition().get(3) == 0 && game.box.getBoxPosition().get(6) == 0) {
            redWon();
        } else if (game.box.getBoxPosition().get(1) == 0 && game.box.getBoxPosition().get(4) == 0 && game.box.getBoxPosition().get(7) == 0) {
            redWon();
        } else if (game.box.getBoxPosition().get(2) == 0 && game.box.getBoxPosition().get(5) == 0 && game.box.getBoxPosition().get(8) == 0) {
            redWon();
        } else if (game.box.getBoxPosition().get(0) == 0 && game.box.getBoxPosition().get(4) == 0 && game.box.getBoxPosition().get(8) == 0) {
            redWon();
        } else if (game.box.getBoxPosition().get(2) == 0 && game.box.getBoxPosition().get(4) == 0 && game.box.getBoxPosition().get(6) == 0) {
            redWon();
        } else if (game.box.getBoxPosition().get(0) != -1 &&
                game.box.getBoxPosition().get(1) != -1 &&
                game.box.getBoxPosition().get(2) != -1 &&
                game.box.getBoxPosition().get(3) != -1 &&
                game.box.getBoxPosition().get(4) != -1 &&
                game.box.getBoxPosition().get(5) != -1 &&
                game.box.getBoxPosition().get(6) != -1 &&
                game.box.getBoxPosition().get(7) != -1 &&
                game.box.getBoxPosition().get(8) != -1) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(Game_PlayActivity.this);
            dialog.setMessage("It's a Draw");
            dialog.setPositiveButton("Play Again !?!", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    Intent intent = new Intent(Game_PlayActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
            dialog.show();

        }
    }

    private void fillingInBox() {
        if (game.box.boxPosition.get(0).equals(1)) {

            iv_counter.setImageResource(R.drawable.yellow);
//            iv_pointer.setImageResource(R.drawable.left_pointer);

        } else if (game.box.boxPosition.get(0).equals(0)) {
            iv_counter.setImageResource(R.drawable.red);
//            iv_pointer.setImageResource(R.drawable.right_pointer);
        }
        if (game.box.boxPosition.get(1).equals(1)) {

            iv_box2.setImageResource(R.drawable.yellow);
//            iv_pointer.setImageResource(R.drawable.left_pointer);

        } else if (game.box.boxPosition.get(1).equals(0)) {
            iv_box2.setImageResource(R.drawable.red);
//            iv_pointer.setImageResource(R.drawable.right_pointer);
        }
        if (game.box.boxPosition.get(2).equals(1)) {

            iv_box3.setImageResource(R.drawable.yellow);
//            iv_pointer.setImageResource(R.drawable.left_pointer);

        } else if (game.box.boxPosition.get(2).equals(0)) {
            iv_box3.setImageResource(R.drawable.red);
//            iv_pointer.setImageResource(R.drawable.right_pointer);
        }
        if (game.box.boxPosition.get(3).equals(1)) {
            iv_box4.setImageResource(R.drawable.yellow);
//            iv_pointer.setImageResource(R.drawable.left_pointer);

        } else if (game.box.boxPosition.get(3).equals(0)) {
            iv_box4.setImageResource(R.drawable.red);
//            iv_pointer.setImageResource(R.drawable.right_pointer);
        }
        if (game.box.boxPosition.get(4).equals(1)) {

            iv_box5.setImageResource(R.drawable.yellow);
//            iv_pointer.setImageResource(R.drawable.left_pointer);

        } else if (game.box.boxPosition.get(4).equals(0)) {
            iv_box5.setImageResource(R.drawable.red);
//            iv_pointer.setImageResource(R.drawable.right_pointer);
        }
        if (game.box.boxPosition.get(5).equals(1)) {

            iv_box6.setImageResource(R.drawable.yellow);
//            iv_pointer.setImageResource(R.drawable.left_pointer);

        } else if (game.box.boxPosition.get(5).equals(0)) {
            iv_box6.setImageResource(R.drawable.red);
//            iv_pointer.setImageResource(R.drawable.right_pointer);
        }
        if (game.box.boxPosition.get(6).equals(1)) {

            iv_box7.setImageResource(R.drawable.yellow);
//            iv_pointer.setImageResource(R.drawable.left_pointer);

        } else if (game.box.boxPosition.get(6).equals(0)) {
            iv_box7.setImageResource(R.drawable.red);
//            iv_pointer.setImageResource(R.drawable.right_pointer);
        }
        if (game.box.boxPosition.get(7).equals(1)) {
            iv_box8.setImageResource(R.drawable.yellow);
//            iv_pointer.setImageResource(R.drawable.left_pointer);

        } else if (game.box.boxPosition.get(7).equals(0)) {
            iv_box8.setImageResource(R.drawable.red);
//            iv_pointer.setImageResource(R.drawable.right_pointer);
        }
        if (game.box.boxPosition.get(8).equals(1)) {

            iv_box9.setImageResource(R.drawable.yellow);
//            iv_pointer.setImageResource(R.drawable.left_pointer);

        } else if (game.box.boxPosition.get(8).equals(0)) {
            iv_box9.setImageResource(R.drawable.red);
//            iv_pointer.setImageResource(R.drawable.right_pointer);
        }
//        if (!game.box.getTakenType()){
//            iv_pointer.setImageResource(R.drawable.left_pointer);
//        }else{
//            iv_pointer.setImageResource(R.drawable.right_pointer);
//        }
        checkWin();
    }

    public void updateDatabase(ImageView view, int position) {


        if (isHost) {
            if (game.box.getBoxPosition().get(position).equals(-1)) {
                view.setImageResource(R.drawable.yellow);
            }

//                iv_pointer.setImageResource(R.drawable.right_pointer);
            game.box.boxPosition.set(position, 1);
            game.box.setTakenType(true);  // true for yellow. host always take yellow .
            Log.i("CODE", "6");
            game.lastMove = 100;
            game.host.setTurn(false);
            game.away.setTurn(true);
            databaseReference.child(code).setValue(game);

        } else {
            if (game.box.getBoxPosition().get(position).equals(-1)) {
                view.setImageResource(R.drawable.red);
            }
//                iv_pointer.setImageResource(R.drawable.left_pointer);
            game.box.boxPosition.set(position, 0);
            game.box.setTakenType(false);  // false for red. joiner alwaays take red
            Log.i("CODE", "7");

            game.lastMove = 9;
            game.host.setTurn(true);
            game.away.setTurn(false);
            databaseReference.child(code).setValue(game);

        }

    }

    private void yellowWon() {

        t.cancel();
        t.purge();
        final Dialog dialog = new Dialog(Game_PlayActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_match_result);

        TextView winner;
        ImageView win;
        Button play;
        winner = dialog.findViewById(R.id.tv_loser);
        win = dialog.findViewById(R.id.iv_loser);
        play = dialog.findViewById(R.id.bt_try_again);
        if (isHost) {
            // host has yellow always
            winner.setVisibility(View.VISIBLE);
            win.setVisibility(View.VISIBLE);
        }



        dialog.show();


//        AlertDialog.Builder dialog = new AlertDialog.Builder(Game_PlayActivity.this);
//        dialog.setMessage("Yellow Won");
//        dialog.setPositiveButton("Play Again !?!", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                finish();
//                Intent intent = new Intent(Game_PlayActivity.this, MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
//            }
//        });
//        dialog.show();
    }

    private void redWon() {

        t.cancel();
        t.purge();

        final Dialog dialog = new Dialog(Game_PlayActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_match_result);
        TextView winner, loser;
        ImageView win, lose;
        Button play;
        loser = dialog.findViewById(R.id.tv_loser);
        lose = dialog.findViewById(R.id.iv_loser);
        play = dialog.findViewById(R.id.bt_try_again);
        if (isHost) {
            // host has yellow always
            loser.setVisibility(View.VISIBLE);
            lose.setVisibility(View.VISIBLE);
        }
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
                Intent intent = new Intent(Game_PlayActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
// TODO CORRECT CODE : https://stackoverflow.com/questions/10710888/add-positive-button-to-dialog ,
// todo https://stackoverflow.com/questions/6276501/how-to-put-an-image-in-an-alertdialog-android/6276592#6276592

        dialog.show();

//        AlertDialog.Builder dialog = new AlertDialog.Builder(Game_PlayActivity.this);
//        dialog.setMessage("Red Won");
//        dialog.setPositiveButton("Play Again !?!", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                finish();
//                Intent intent = new Intent(Game_PlayActivity.this, MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
//            }
//        });
//        dialog.show();
    }

    @Override
    public void onClick(View view) {


    }
}
