package dsa.eetac.upc.edu.finalexamenminimo2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Recycler extends RecyclerView.Adapter<Recycler.ViewHolder> {

    //Necesario paar el constructor del recycler
    private Context context;
    private List<Element> data1;


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView municipiImageView;
        private TextView nommunicipi;
        private TextView inemunicipi;
        private ConstraintLayout constraintLayout;

        //DEcalramos los textviews y imageview del recycler

        public ViewHolder(View v) {
            super(v);
            municipiImageView=v.findViewById(R.id.municipiimage);
            nommunicipi=v.findViewById(R.id.nommunicipitxt);
            inemunicipi=v.findViewById(R.id.nomcomarcatxt);
            constraintLayout =v.findViewById(R.id.constraintLayout);
        }

    }

    public void addElements (List<Element> citiList){
        data1.addAll(citiList);
        notifyDataSetChanged();
    }

    //Constructor
    public Recycler(Context context) {
        this.data1 = new ArrayList<>();
        this.context=context;
    }

    @Override
    public Recycler.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(Recycler.ViewHolder holder, int position) {
        Element element = data1.get(position);
        holder.nommunicipi.setText(element.getMunicipiNom());
        holder.inemunicipi.setText(element.getIne());
        Picasso.with(context).load(element.getMunicipiEscut()).into(holder.municipiImageView);

    }

    @Override
    public int getItemCount() {
        return data1.size();
    }
}
