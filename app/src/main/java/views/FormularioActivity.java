package views;

import android.content.Context;
import android.os.Bundle;

import com.example.demo.R;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import interfaces.IFormulario;
import interfaces.IList;
import models.Question;

public class FormularioActivity extends AppCompatActivity implements IFormulario.View {
    String TAG="Foro de Preguntas/FormularioActivity"
    private IList.Presenter presenter;
    private Context myContext;
    TextInputEditText nameP;
    TextInputEditText mailP;
    TextInputEditText data;
    TextInputEditText titleP;
    TextInputEditText colorP;
    TextInputEditText questionP;
    Question question=new Question(); //Creacion de una pregunta



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"Starting Create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);


        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String[] letra = {"Público", "Privado"};
        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, letra));
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
                    if (question.setName(nameP.getText().toString()) == false) {
                        nameP.setError(presenter.getError("QuestionName"));
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
                    if (question.setTittle(titleP.getText().toString()); == false) {
                        titleP.setError(presenter.getError("Colour"));
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
    }


    @Override
    public void startAboutActivity() {

    }
}