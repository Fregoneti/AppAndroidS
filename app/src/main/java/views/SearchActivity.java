package views;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.demo.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

import interfaces.ISearch;
import presenters.SearchPresenter;

public class SearchActivity extends AppCompatActivity implements ISearch.View {

    private ISearch.Presenter presenter;
    public String TAG = "Foro de Preguntas/SearchActivity";
    Context myContext;
    EditText editTextDate;
    ImageView buttonDate;
    Calendar calendar;
    Button search;
    DatePickerDialog datePickerDialog;
    int Year, Month, Day;
    String tittle;
    String date;
    TextInputEditText tittleS;
    TextInputEditText dateS;
    String spinnerr;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        myContext = this;
        presenter = new SearchPresenter(this);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String[] letra = {"1 Option"};

        tittleS = findViewById(R.id.textInputEditText2);
      //  dateS = findViewById(R.id.input_fechaT);


        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, letra));


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Buscar Pregunta");
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Asignar la acción necesaria. En este caso "volver atrás"
                    onBackPressed();
                }
            });
        } else {
            Log.d("SobreNosotros", "Error al cargar toolbar");
        }


        // Obtener la fecha actual
        calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR);
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);

        editTextDate = (EditText) findViewById(R.id.input_fechaT);

        // Definir la acción del botón para abrir el calendario
        buttonDate = (ImageView) findViewById(R.id.input_fecha);
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

                        String day0 = "" + day;
                        if (day < 10) {
                            day0 = "0" + day;
                        }
                        String month0 = "" + (month + 1);
                        if (month < 10) {
                            month0 = "0" + month0;
                        }

                        editTextDate.setText(day0 + "/" + month0 + "/" + year);
                        String s = day0 + "/" + month0 + "/" + year;

                    }
                }, Year, Month, Day);
                // Mostrar el calendario
                datePickerDialog.show();


            }
        });

        search = (Button) findViewById(R.id.searchp);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onSearch();
                presenter.onBack();
            }
        });




    }


    @Override
    public void startDatePicker() {

        //    DatePickerFragment newFragment = new DatePickerFragment();
        //    newFragment.show(getSupportFragmentManager(), "datePicker");
    }


    @Override
    public void goBack() {
        finish();
    }

    @Override
    public void search() {

        String temp = "";
        Intent i = getIntent();

        if (tittleS.getText().length() > 0

                || !(editTextDate.getText().toString().equals(temp))) {
            if (tittleS.getText().length() > 0) {
                System.out.println(tittleS.getText().toString());
                i.putExtra("TITTLE", tittleS.getText().toString());
                System.out.println(tittleS.getText().toString());
            }

            if (editTextDate.getText().length() != 0) {
                i.putExtra("DATE", editTextDate.getText().toString());
            }

            setResult(RESULT_OK, i);
            finish();
        } else {
            //showMessageSearch();
        }
        /*
        spinnerr = getResources().getString(R.string.spinner);
        Intent intent = getIntent();

        if(tittle.length()!=0) {
            String tittle = tittleS.getText().toString();
            intent.putExtra("TITTLE", tittle);
        }

        if (tittleS.getText().length() != 0) {
            intent.putExtra("DATE", editTextDate.getText().toString());
        }
        setResult(RESULT_OK, intent);
        finish();

         */

    }

    @Override
    public void clickHelpButton() {
        Log.d(TAG, "Starting Help Activity");
        Intent intent = new Intent(SearchActivity.this, HelpActivity.class);
        intent.putExtra("activity","main");
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);


        return true;
    }


}
