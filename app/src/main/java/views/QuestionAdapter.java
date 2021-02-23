package views;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.demo.R;
import java.util.ArrayList;
import models.Question;


public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> implements View.OnClickListener{

    private ArrayList<Question> items;
    private View.OnClickListener listener;

    // Clase interna:
    // Se implementa el ViewHolder que se encargará
    // de almacenar la vista del elemento y sus datos
    public static class QuestionViewHolder extends RecyclerView.ViewHolder {

        private TextView tittle;
        private TextView color;
        private ImageView photo;



        public QuestionViewHolder(View itemView) {
            super(itemView);
            tittle = (TextView) itemView.findViewById(R.id.tittle);
            color = (TextView) itemView.findViewById(R.id.color);
            photo = (ImageView) itemView.findViewById(R.id.photo);
        }

        public void QuestionBind(Question item) {
            tittle.setText((item.getTittle()));
            color.setText((item.getColour()));
            if (item.getImage() != "") {
                try {
                    byte[] decodedString = Base64.decode(item.getImage(), Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    photo.setImageBitmap(decodedByte);
                    photo.setBackground(null);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error desconocido");
                }
            }else{
                photo.setBackgroundResource(R.mipmap.ic_launcher);
            }
        }
    }

    // Contruye el objeto adaptador recibiendo la lista de datos
    public QuestionAdapter(@NonNull ArrayList<Question> items) {
        this.items = items;
    }

    // Se encarga de crear los nuevos objetos ViewHolder necesarios
    // para los elementos de la colección.
    // Infla la vista del layout, crea y devuelve el objeto ViewHolder
    @Override
    public QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_row, parent, false);
        row.setOnClickListener(this);

        QuestionViewHolder questionViewHolder = new QuestionViewHolder(row);
        return questionViewHolder;
    }



    // Se encarga de actualizar los datos de un ViewHolder ya existente.
    @Override
    public void onBindViewHolder(QuestionViewHolder viewHolder, int position) {

        Question item = items.get(position);
        viewHolder.QuestionBind(item);
    }

    // Indica el número de elementos de la colección de datos.
    @Override
    public int getItemCount() {
        return items.size();
    }

    // Asigna un listener al elemento
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }


}

