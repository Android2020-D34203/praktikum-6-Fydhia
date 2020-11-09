package id.ac.id.telkomuniversity.tass.praktikactivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button buttonPindah;
    EditText editText;
    private String KEY_DATA = "DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.textField);
        buttonPindah = findViewById(R.id.buttonPindah);

        buttonPindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String data = editText.getText().toString();
                    if (data.equals("")) {
                        Toast.makeText(getApplication(), "Input tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Peringatan!");
                        builder.setMessage("Apakah anda yakin ingin pindah?");
                        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String data = editText.getText().toString();
                                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                                i.putExtra(KEY_DATA, data);
                                startActivity(i);

                                int NOTIFICATION_ID = 234;
                                String CHANNEL_ID   = "my_channel_01";
                                Intent intent       = new Intent(MainActivity.this, SecondActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    CharSequence name           = CHANNEL_ID;
                                    String description          = CHANNEL_ID;
                                    int importance              = NotificationManager.IMPORTANCE_DEFAULT;

                                    NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
                                    channel.setDescription(description);
                                    NotificationManager notificationManager = getSystemService(NotificationManager.class);
                                    notificationManager.createNotificationChannel(channel);
                                }


                                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID);
                                builder.setSmallIcon(R.drawable.ic_message);
                                builder.setContentTitle("Notifikasi Aplikasi");
                                builder.setContentText("Berhasil pindah ke activity kedua.");
                                builder.setStyle(new NotificationCompat.BigTextStyle()
                                        .bigText("Selamat yaaaa!! Berhasil pindah ke activity kedua."));
                                builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

                                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);

                                // notificationId is a unique int for each notification that you must define
                                notificationManager.notify(NOTIFICATION_ID, builder.build());


                            }
                        });
                        builder.setNegativeButton("Tidak", null);
                        builder.show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplication(), "ERROR, TRY AGAIN !", Toast.LENGTH_SHORT);
                }

            }
        });
    }
    }
