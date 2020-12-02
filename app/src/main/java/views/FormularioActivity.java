package views;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.demo.R;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

import interfaces.IFormulario;
import models.Question;

public class FormularioActivity extends AppCompatActivity implements IFormulario.View {
    String TAG="Foro de Preguntas/FormularioActivity";
    private IFormulario.Presenter presenter;
    private Context myContext;
    TextInputEditText nameP;
    TextInputEditText mailP;
    TextInputEditText data;
    TextInputEditText titleP;
    TextInputEditText colorP;
    TextInputEditText questionP;
    Button add;
    Button save;
    Calendar calendar;
    EditText editTextDate;
    DatePickerDialog datePickerDialog;
    ImageView buttonDate;
    int Year, Month, Day;
    Question question=new Question(); //Creacion de una pregunta
    private ArrayAdapter<String>adt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"Starting Create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);


        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String[] letra = {"Público", "Privado"};
        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, letra));

        Button add =findViewById(R.id.addSpinner);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "add spinner ");
                presenter.onClickAddSpinnerOption();
            }
        });

        Log.d(TAG,"Starting Toolbar");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Nueva Pregunta");
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG,"Pressing back button");
                    onBackPressed();
                }
            });
        } else {
            Log.d(TAG, "Error loading toolbar");
        }


        nameP = findViewById(R.id.textInputEditText2);
        mailP = findViewById(R.id.textInputEditText3);
        data = findViewById(R.id.textInputEditText4);
        titleP = findViewById(R.id.textInputEditText);
        colorP = findViewById(R.id.textInputEditText5);
        questionP = findViewById(R.id.editText);

        nameP.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    Log.d(TAG, "name focused");
                    if (!(question.setName(nameP.getText().toString()))) {
                        nameP.setError(presenter.getError("Name"));
                    } else {
                        nameP.setError("");
                    }
                } else {
                    Log.d("FormActivity", "Input EditText");
                }

            }


        });
        mailP.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    Log.d("FormActivity", "Exit EditText");
                    if (question.setMail(mailP.getText().toString()) == false) {
                        mailP.setError(presenter.getError("Email"));
                    } else {
                        mailP.setError("");
                    }
                } else {
                    Log.d("FormActivity", "Input EditText");
                }

            }
        });
//FECHA???
        data.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    Log.d("FormActivity", "Exit EditText");
                    if (question.setName(data.getText().toString()) == false) {
                        data.setError(presenter.getError("Date"));
                    } else {
                        data.setError("");
                    }
                } else {
                    Log.d("FormActivity", "Input EditText");
                }
            }
        });


        titleP.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    Log.d("FormActivity", "Exit EditText");
                    if (question.setTittle(titleP.getText().toString()) == false) {
                        titleP.setError(presenter.getError("Tittle"));
                    } else {
                        titleP.setError("");
                    }
                } else {
                    Log.d("FormActivity", "Input EditText");
                }
            }
        });


        colorP.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    Log.d("FormActivity", "Exit EditText");
                    if (question.setColour(colorP.getText().toString()) == false) {
                        colorP.setError(presenter.getError("Colour"));
                    } else {
                        colorP.setError("");
                    }
                } else {
                    Log.d("FormActivity", "Input EditText");
                }

            }


        });
        questionP.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    Log.d("FormActivity", "Exit EditText");
                    if (question.setQuestion(questionP.getText().toString()) == false) {
                        questionP.setError(presenter.getError("Question"));
                    } else {
                        questionP.setError("");
                    }
                } else {
                    Log.d("FormActivity", "Input EditText");
                }
            }
        });

        save = (Button)findViewById(R.id.button2);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FormularioActivity.this);
                builder.setTitle("Guardando Pregunta");
                builder.setMessage("¿Estás seguro de querer guardar esta pregunta?");


                //Yes Button
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Yes button Clicked",Toast.LENGTH_LONG).show();
                        Log.i("Code2care ", "Yes button Clicked!");

                    }
                });

                //No Button
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"No button Clicked",Toast.LENGTH_LONG).show();
                        Log.i("Code2care ","No button Clicked!");
                        dialog.dismiss();

                    }
                });
                //Cancel Button
                builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Cancel button Clicked",Toast.LENGTH_LONG).show();
                        Log.i("Code2care ","Cancel button Clicked!");
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        // Obtener la fecha actual
        calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR);
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);

        editTextDate = (EditText) findViewById(R.id.imageView3);

        // Definir la acción del botón para abrir el calendario
        buttonDate = (ImageView) findViewById(R.id.imageView3);
        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Definir el calendario con la fecha seleccionada por defecto
                datePickerDialog = new DatePickerDialog(myContext, new DatePickerDialog.OnDateSetListener() {
                    // Definir la acción al pulsar OK en el calendario
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        // Asignar la fecha a un campo de texto
                        //editTextDate.setText(String.valueOf(day) + "/" + String.valueOf(month+1) + "/" + String.valueOf(year));
                        editTextDate.setText((day) + "/" +(month+1) + "/" + (year));
                    }
                }, Year, Month, Day);
                // Mostrar el calendario
                datePickerDialog.show();


            }
        });



    }


    @Override
    public void onGoBack() {
        finish();
    }

    @Override
    public void addSpinnerOption() {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);


        return true;
    }





}