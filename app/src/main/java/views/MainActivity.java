package views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.example.demo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import interfaces.IList;
import presenters.ListPresenter;

public class MainActivity extends AppCompatActivity implements IList.View {

    private IList.Presenter presenter;
    private Context context;
    String TAG = "Foro de Preguntas /MainActivity";

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Starting onCreate...");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;
        presenter = new ListPresenter(this);


        FloatingActionButton fab = findViewById(R.id.siguiente);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                presenter.onClickFloatingButton();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }


    public void startSearchActivity() {
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        startActivity(intent);
    }

    @Override
    public void startAboutActivity() {
        Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
        startActivity(intent);
    }


    public void startFormularioActivity() {
        Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
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
}







