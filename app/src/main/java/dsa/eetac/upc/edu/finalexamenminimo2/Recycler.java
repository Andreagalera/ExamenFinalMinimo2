package dsa.eetac.upc.edu.finalexamenminimo2;

import android.content.Context;
import android.support.annotation.NonNull;
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
    private List<Municipi> data;
    private List<Element> data1;


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView municipiImageView;
        private TextView nommunicipi;
        private Text escutmunicipi;
        private LinearLayout linearLayout;


        //DEcalramos los textviews y imageview del recycler

        public ViewHolder(View v) {
            super(v);
            municipiImageView=v.findViewById(R.id.municipiimage);
            nommunicipi=v.findViewById(R.id.nommunicipitxt);
            escutmunicipi=v.findViewById(R.id.nomcomarcatxt);
            linearLayout =v.findViewById(R.id.linearLayout);
        }

    }

    public void addMunicipis (List<Municipi> citiList){
        data.addAll(citiList);
        notifyDataSetChanged();
    }

    //Constructor
    public Recycler(Context context) {
        this.data = new ArrayList<>();
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
        //Municipi municipidata = ((Municipi) data.get(position));
        Element element =((Element) data1.get(position));
        holder.nommunicipi.setText(element.getIne());
        //holder.escutmunicipi.setText(element.getMunicipiEscut();
        Picasso.with(context).load(element.getMunicipiEscut()).into(holder.municipiImageView);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
