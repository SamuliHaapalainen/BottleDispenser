package com.example.j4011.bottledispenser;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Mainclass extends AppCompatActivity {

    Context context = null;

    TextView moneyText;
    TextView bottleList;
    EditText bottleChoice;
    TextView bottleAnnouncement;
    SeekBar adjustMoney;
    Spinner spinner;
    String drink = "Drink";
    double size = 0;


    int choice;
    Button btnAddMoney;
    Button btnGetMoney;
    Button btnBuyBottle;
    int min = 0, max = 15, current = 5;
    int clicknmb = 0;
    final BottleDispenser bd = BottleDispenser.getInstance();
    Bottle bt = new Bottle();
    String input = "";
    String input2 = "";
    //BottleDispenser bd = new BottleDispenser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_class);
        btnAddMoney = (Button) findViewById(R.id.buttonAddMoney);
        btnGetMoney = (Button) findViewById(R.id.getMoneyOut);
        moneyText = (TextView) findViewById(R.id.moneyInDispenser);
        bottleList = (TextView) findViewById(R.id.bottleList);
        bottleChoice = (EditText) findViewById(R.id.bottleChoice);
        btnBuyBottle = (Button) findViewById(R.id.buyBottle);
        bottleAnnouncement = (TextView) findViewById(R.id.bottleAnnouncement);
        adjustMoney = (SeekBar) findViewById(R.id.adjustMoney);
        spinner = (Spinner) findViewById(R.id.spinnerDrink);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinnerSize);
        ArrayAdapter<Float> adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, bd.size_array);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, bd.drink_array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        context=Mainclass.this;
        System.out.println(context.getFilesDir());


        bottleList.setText(bd.BottleList());
        adjustMoney.setMax(max - min);
        adjustMoney.setProgress(current - min);
        bottleAnnouncement.setText(Integer.toString(current));


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                drink = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String stringSize = parent.getItemAtPosition(position).toString();
                size = Double.valueOf(stringSize).doubleValue();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




            /*btnAddMoney.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    moneyText.setText(Float.toString(bd.addMoney()));
                    Toast toast = Toast.makeText(getApplicationContext(),"ClinToast.makeText(this,"Recipe saved",Toast.LENGTH_SHORT);k, money added!", Toast.LENGTH_SHORT);
                    toast.show();
                    moneyText.setText(Float.toString(bd.getMoney()));
                }
            });*/


        btnGetMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bd.returnMoney();
                moneyText.setVisibility(View.INVISIBLE);
                Toast toast = Toast.makeText(getApplicationContext(), "Money returned", Toast.LENGTH_SHORT);
                toast.show();
                bottleAnnouncement.setText("");
            }
        });
        /*btnBuyBottle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottleAnnouncement.setTextSize(12);
                bottleAnnouncement.setText(bd.buyBottle(Integer.parseInt(bottleChoice.getText().toString())));
                moneyText.setText(Float.toString(bd.getMoney()));
                bottleList.setText(bd.BottleList());
                //bottleList.setText(bd.buyBottle(Integer.parseInt(bottleChoice.getText().toString())));
            }
        });*/

        btnBuyBottle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottleAnnouncement.setTextSize(12);
                bottleAnnouncement.setText(bd.buyBottle1(drink, size));
                moneyText.setText(Float.toString(bd.getMoney()));
                bottleList.setText(bd.BottleList());
                writeFile(v);
                readFile(v);
                //bottleList.setText(bd.buyBottle(Integer.parseInt(bottleChoice.getText().toString())));
            }
        });

        btnAddMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicknmb == 0) {

                    bottleAnnouncement.setVisibility(View.VISIBLE);
                    adjustMoney.setVisibility(View.VISIBLE);
                    clicknmb = 1;
                } else {
                    moneyText.setText(Float.toString(bd.addMoney(current)));
                }
            }

        });

        adjustMoney.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                current = progress + min;
                bottleAnnouncement.setText(Integer.toString(current));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    //KUITTI
    public void readFile(View v) {
        try {
            InputStream ins = context.openFileInput("Kuitti");// TODO Tälle arvo!
            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            String s = "";
            while ((s = br.readLine()) != null) {
                System.out.println(s);
            }
            ins.close();

        } catch (IOException e) {
            Log.e("IOExcinput = userInput.getText().toString();eption", "Virhe syötteessä");
        } finally {
            System.out.println("READ");
        }
    }

    public void writeFile(View v) {
        try {
            OutputStreamWriter osw = new  OutputStreamWriter(context.openFileOutput("Kuitti",Context.MODE_PRIVATE));
            String s=bd.getKuitti();
            osw.write(s);
            osw.close();
        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä");
        } finally {
            System.out.println("WRITTEN");
        }
    }







            /*while(true) {
                System.out.println("\n*** LIMSA-AUTOMAATTI ***");
                System.out.println("1) Lisää rahaa koneeseen");
                System.out.println("2) Osta pullo");
                System.out.println("3) Ota rahat ulos");
                System.out.println("4) Listaa koneessa olevat pullot");
                System.out.println("0) Lopeta");
                System.out.print("Valintasi: ");
                choice = sc.nextInt();

                if (choice == 0) {
                    return;
                } else if (choice == 1) {
                    bd.addMoney();
                } else if (choice == 2) {
                    bd.BottleList();
                    System.out.print("Valintasi: ");
                    bottleChoice = sc.nextInt();
                    bd.buyBottle(bottleChoice);
                } else if (choice == 3) {
                    bd.returnMoney();
                } else if (choice == 4) {
                    bd.BottleList();
                } else {
                    System.out.println("Väärä valinta.");
                }
            }*/
        }

