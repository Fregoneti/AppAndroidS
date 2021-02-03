package views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.demo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.UUID;

import interfaces.IList;
import models.Question;
import models.QuestionModel;
import presenters.ListPresenter;

public class MainActivity extends AppCompatActivity implements IList.View {

    private IList.Presenter presenter;
    private Context context;
    String TAG = "Foro de Preguntas /MainActivity";
    private ArrayList<Question> items;
    QuestionAdapter adapter;
    TextView number_question;
    Button btn;
    Question a, i, e, o, u, b, c, d, f, g;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Starting onCreate...");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;
        presenter = new ListPresenter(this);
        final RecyclerView recyclerView = findViewById(R.id.recycled);
        items = new ArrayList<>();
        SharedPreferences prefs =getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);

        if(!prefs.getBoolean("firstTime",false)){
            presenter.SetItemsFirstTime();
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
        }

        items=QuestionModel.getAllQuestion();
        adapter = new QuestionAdapter(items);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FloatingActionButton fab = findViewById(R.id.siguiente);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                presenter.onClickFloatingButton();
            }
        });

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = recyclerView.getChildAdapterPosition(v);
                presenter.onClickQuestion(items.get(position).getId());
            }
        });







        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder target, int direction) {
                int position = target.getAdapterPosition();

                //items.remove(position).getId();
                QuestionModel.removeQuestion(items.remove(position).getId());
                adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Ha sido borrada ", Toast.LENGTH_SHORT).show();
            }
        });
        helper.attachToRecyclerView(recyclerView);


        //Creamos el textview de las preguntas
        number_question = (TextView) findViewById(R.id.numberQ);
        String message=number_question.getText().toString();
        //int first_size=items.size();
        String X=message.replace("x"+"",""+items.size());
        number_question.setText(X);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }

    @Override
    public void startSearchActivity() {
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        startActivity(intent);
    }

    @Override
    public void startAboutActivity() {
        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(intent);
    }

    @Override
    public void startFormularioActivity() {
        Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
        startActivity(intent);
    }

    @Override
    public void startFormularioActivity(String id) {
        Log.d(TAG, "Editing Question: " + id);
        Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_search) {
            Log.d(TAG, "Menu Search click");
            presenter.onClickSearchButton();
            return true;
        }
        if (id == R.id.about) {
            Log.d(TAG, "Menu About click");
            presenter.onClickAboutButton();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "Starting onResume");
        super.onResume();
        int numberofquestions = items.size();
        //items.removeAll(presenter.getAllUsers());
        items.clear();
        items.addAll(presenter.getAllQuestions());
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycled);
        // Muestra el RecyclerView en vertical
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Crea el Adaptador con los datos de la lista anterior
        adapter = new QuestionAdapter(items);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = recyclerView.getChildAdapterPosition(v);
                presenter.onClickQuestion(items.get(position).getId());
            }
        });
        recyclerView.setAdapter(adapter);

        //Actualizamos el textview de los elementos
        String message = number_question.getText().toString();
        String text1=message.replace("" + numberofquestions, "" + items.size());
        number_question.setText(text1);


    }
}







