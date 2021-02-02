package views;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import com.example.demo.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Base64;
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
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import interfaces.IFormulario;
import io.realm.Realm;
import models.Question;
import models.QuestionModel;
import presenters.FormularioPresenter;

public class FormularioActivity extends AppCompatActivity implements IFormulario.View {

    String TAG = "Foro de Preguntas/FormularioActivity";
    private IFormulario.Presenter presenter;
    private Context myContext;
    boolean exist;
    String id;
    TextInputEditText nameP;
    TextInputEditText mailP;
    TextInputEditText date;
    TextInputEditText titleP;
    TextInputEditText colorP;
    TextInputEditText questionP;
    Button add;
    ImageView photo;
    Button remove;
    Calendar calendar;
    EditText editTextDate;
    DatePickerDialog datePickerDialog;
    ImageView buttonDate;
    int Year, Month, Day;
    Question question = new Question(); //Creacion de una pregunta
    private ArrayAdapter<String> adt;
    private static final int REQUEST_CAPTURE_IMAGE = 200;
    private static final int REQUEST_SELECT_IMAGE = 201;
    final private int CODE_WRITE_EXTERNAL_STORAGE_PERMISSION = 123;
    private ConstraintLayout constraintLayoutFormActivity;
    final String pathFotos = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/demoAndroidImages/";
    private Uri uri;
    ImageView Gallery;
    ImageView Camera;
    Button clear;
    Button save;
    TextInputEditText editTextName;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Starting Create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        presenter = new FormularioPresenter(this);
        myContext = this;
        id=null;

        constraintLayoutFormActivity = findViewById(R.id.constraintl);
        editTextName = findViewById(R.id.textInputEditText2);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String[] letra = {"Público", "Privado"};
        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, letra));

        Button add = findViewById(R.id.addSpinner);

        nameP = findViewById(R.id.textInputEditText2);
        mailP = findViewById(R.id.textInputEditText3);
        date = findViewById(R.id.textInputEditText4);
        titleP = findViewById(R.id.textInputEditText);
        colorP = findViewById(R.id.textInputEditText5);
        questionP = findViewById(R.id.editText);
        photo=findViewById(R.id.imageView2);
        remove = (Button) findViewById(R.id.button3);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "add spinner ");
                presenter.onClickAddSpinnerOption();
            }
        });

        Log.d(TAG, "Starting Toolbar");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Nueva Pregunta");
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Pressing back button");
                    onBackPressed();
                }
            });
        } else {
            Log.d(TAG, "Error loading toolbar");
        }


        id = getIntent().getStringExtra("id");


        //Si  encuentra una pregunta con ID->El boton de borrar se activa, si no encuentra, se desactiva
        if(id!=null){
            exist=true;
            presenter.onAddParameters(id);
            remove.setEnabled(true);

        }else{
            exist=false;
            remove.setEnabled(false);
        }







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
        date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                                          @Override
                                          public void onFocusChange(View view, boolean hasFocus) {
                                              if (!hasFocus) {
                                                  Log.d("FormActivity", "Exit EditText");
                                                  if (question.setName(date.getText().toString()) == false) {
                                                      date.setError(presenter.getError("Date"));
                                                  } else {
                                                      date.setError("");
                                                  }
                                              } else {
                                                  Log.d("FormActivity", "Input EditText");
                                              }
                                          }
                                      }
        );


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


        if(exist==true){


            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(FormularioActivity.this);
                    builder.setTitle("Borrando Pregunta");
                    builder.setMessage("¿Estás seguro de querer borrar esta pregunta?");


                    //Yes Button
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(), "Pregunta borrada con éxito", Toast.LENGTH_LONG).show();
                            Log.i("Code2care ", "Yes button Clicked!");
                            QuestionModel.removeQuestion(QuestionModel.searchQuestionById(id).getId());
                            presenter.onStartMainActivity();
                        }
                    });


                    //Cancel Button
                    builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(), "¡No se ha borrado la pregunta!", Toast.LENGTH_LONG).show();
                            Log.i("Code2care ", "Cancel button Clicked!");
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            });

        }else{
          //  remove.setEnabled(false);
        }


        // Obtener la fecha actual
        calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR);
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);

        editTextDate = (EditText) findViewById(R.id.textInputEditText4);

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

                        String day0 = "" + day;
                        if (day < 10) {
                            day0 = "0" + day;
                        }
                        String month0 = "" + (month + 1);
                        if (month < 10) {
                            month0 = "0" + month0;
                        }

                        editTextDate.setText(day0 + "/" + month0 + "/" + year);
                        String s=day0 + "/" + month0 + "/" + year;

                    }
                }, Year, Month, Day);
                // Mostrar el calendario
                datePickerDialog.show();


            }
        });

        Gallery = (ImageView) findViewById(R.id.imageView13);
        Gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean valid = false;
                presenter.onClickImage();
                //presenter.selectPicture();
            }
        });

        Camera = findViewById(R.id.imageView12);
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            Camera.setEnabled(false);
        } else {
            Camera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
                        presenter.takePicture();
                    }
                }
            });

            clear = findViewById(R.id.button4);
            clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }


        clear = findViewById(R.id.button4);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.cleanImage();
            }
        });


/*

        AlertDialog.Builder builder = new AlertDialog.Builder(myContext);
        builder.setTitle(getResources().getString(R.string.addspinner));
        // I'm using fragment here so I'm using getView() to provide ViewGroup
        // but you can provide here any other instance of ViewGroup from your Fragment / Activity
        View viewInflated = LayoutInflater.from(myContext()).inflate(R.layout.add_spinner, (ViewGroup) getView(), false);
        // Set up the input
        final EditText input = (EditText) viewInflated.findViewById(R.id.input);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        builder.setView(viewInflated);

        // Set up the buttons
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                m_Text = input.getText().toString();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

 */


// Configuración del clic del botón guardar
        save = findViewById(R.id.button2);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String result = null;
                try {
                    BitmapDrawable drawable = (BitmapDrawable) photo.getDrawable();
                    if (drawable == null) {
                        result = "";
                    } else {
                        Bitmap bitmap = Bitmap.createBitmap(drawable.getBitmap());
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 90, bos);
                        byte[] imageBytes = bos.toByteArray();
                        result = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (result != null) {
                    question.setImage(result);
                }

                question.setId(UUID.randomUUID().toString());

                if(nameP.getText().toString().equals("")){
                    presenter.onshowFormuAlert(1);
                }else{
                    question.setName(nameP.getText().toString());
                }


                if(editTextDate.getText().toString().equals("")){
                    presenter.onshowFormuAlert(2);
                }else{
                    question.setDate(editTextDate.getText().toString());
                }

                if(mailP.getText().toString().equals("")){
                    presenter.onshowFormuAlert(3);
                }else{
                    question.setMail(mailP.getText().toString());
                }

                if(titleP.getText().toString().equals("")){
                    presenter.onshowFormuAlert(4);
                }else{
                    question.setTittle(titleP.getText().toString());
                }

                if(colorP.getText().toString().equals("")){
                    presenter.onshowFormuAlert(5);
                }else{
                    question.setColour(colorP.getText().toString());
                }

                if(questionP.getText().toString().equals("")){
                    presenter.onshowFormuAlert(5);
                }else{
                    question.setQuestion(questionP.getText().toString());
                }

                presenter.onClickSave(question);
                presenter.onStartMainActivity();
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


    private String getFileCode() {
        // Se crea un código a partir de la fecha y hora
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss", java.util.Locale.getDefault());
        String date = dateFormat.format(new Date());
        // Se devuelve el código
        return "pic_" + date;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case (REQUEST_CAPTURE_IMAGE):
                if (resultCode == Activity.RESULT_OK) {
                    // Se carga la imagen desde un objeto URI al imageView
                    ImageView imageView = findViewById(R.id.imageView);
                    imageView.setImageURI(uri);

                    // Se le envía un broadcast a la Galería para que se actualice
                    Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    mediaScanIntent.setData(uri);
                    sendBroadcast(mediaScanIntent);
                } else if (resultCode == Activity.RESULT_CANCELED) {
                    // Se borra el archivo temporal
                    File file = new File(uri.getPath());
                    file.delete();
                }
                break;


            case (REQUEST_SELECT_IMAGE):
                if (resultCode == Activity.RESULT_OK) {
                    // Se carga la imagen desde un objeto Bitmap
                    Uri selectedImage = data.getData();
                    String selectedPath = selectedImage.getPath();

                    if (selectedPath != null) {
                        // Se leen los bytes de la imagen
                        InputStream imageStream = null;
                        try {
                            imageStream = getContentResolver().openInputStream(selectedImage);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        // Se transformam los bytes de la imagen a un Bitmap
                        Bitmap bmp = BitmapFactory.decodeStream(imageStream);

                        // Se carga el Bitmap en el ImageView
                        ImageView imageView = findViewById(R.id.imageView2);
                        imageView.setImageBitmap(bmp);
                        imageView.setBackground(null);
                    }
                }
                break;
        }
    }

    public void takePicture() {
        try {
            // Se crea el directorio para las fotografías
            File dirFotos = new File(pathFotos);
            dirFotos.mkdirs();

            // Se crea el archivo para almacenar la fotografía
            File fileFoto = File.createTempFile(getFileCode(), ".jpg", dirFotos);

            // Se crea el objeto Uri a partir del archivo
            // A partir de la API 24 se debe utilizar FileProvider para proteger
            // con permisos los archivos creados
            // Con estas funciones podemos evitarlo
            // https://stackoverflow.com/questions/42251634/android-os-fileuriexposedexception-file-jpg-exposed-beyond-app-through-clipdata
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            uri = Uri.fromFile(fileFoto);
            Log.d(TAG, uri.getPath().toString());

            // Se crea la comunicación con la cámara
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // Se le indica dónde almacenar la fotografía
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            // Se lanza la cámara y se espera su resultado
            startActivityForResult(cameraIntent, REQUEST_CAPTURE_IMAGE);

        } catch (IOException ex) {

            Log.d(TAG, "Error: " + ex);
            CoordinatorLayout coordinatorLayout = findViewById(R.id.coordinatorLayout);
            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, getResources().getString(R.string.error_files), Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }

    public void showPermission(int n) {
        switch (n) {
            case 0:
                //Si la versión de android es superior a la 6, se ejecutará esta línea para pedir permisos en tiempo real
                ActivityCompat.requestPermissions(FormularioActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CODE_WRITE_EXTERNAL_STORAGE_PERMISSION);
                break;
            case 1:
                // Permiso denegado
                Snackbar.make(constraintLayoutFormActivity, getResources().getString(R.string.denied), Snackbar.LENGTH_LONG).show();
                break;
            default:
        }
    }

    @Override
    public void showFormuAlert(int n) {
        switch (n){

            case 1: Toast.makeText(myContext, "Error al introducir nombre", Toast.LENGTH_SHORT).show();
            case 2:Toast.makeText(myContext, "Error al introducir fecha", Toast.LENGTH_SHORT).show();
            case 3:Toast.makeText(myContext, "Error al introducir correo", Toast.LENGTH_SHORT).show();
            case 4:Toast.makeText(myContext, "Error al introducir título", Toast.LENGTH_SHORT).show();
            case 5:Toast.makeText(myContext, "Error al introducir color", Toast.LENGTH_SHORT).show();




        }
    }

    @Override
    public void refillParameters(Question q) {

        nameP.setText(q.getName());
        date.setText(q.getDate());
        mailP.setText(q.getMail());
        titleP.setText(q.getTittle());
        colorP.setText(q.getColour());
        questionP.setText(q.getQuestion());
    }


    @Override
    public void selectPicture() {
        // Se le pide al sistema una imagen del dispositivo
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(intent, getResources().getString(R.string.choose_picture)),
                REQUEST_SELECT_IMAGE);
    }

    @Override
    public void resetPicture() {

        ImageView imageView_Form = findViewById(R.id.imageView2);
        imageView_Form.setImageBitmap(null);
        imageView_Form.setBackgroundResource(R.drawable.splashscreen);

    }
    @Override
    public void startMainActivity() {
        Intent intent = new Intent(FormularioActivity.this, MainActivity.class);
        startActivity(intent);
    }


}