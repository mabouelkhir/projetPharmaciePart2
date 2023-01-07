package ma.pack.ep.pharmacieees.ui.pharmacie;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ma.pack.ep.pharmacieees.R;

public class PharmacieAdapter extends RecyclerView.Adapter<PharmacieAdapter.ViewHolder>   implements Filterable {
    List<Pharmacie> pharmacieList ;
    Context context;
    List<Pharmacie> pharmacieFilter;


    public PharmacieAdapter( List<Pharmacie> pharmacieList,Context context) {
        this.context = context;
        this.pharmacieList=pharmacieList;
        pharmacieFilter=new ArrayList<Pharmacie>(pharmacieList);
    }

    @NonNull
    @Override
    public PharmacieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context =parent.getContext();
        View view= LayoutInflater.from(context).inflate(R.layout.pharmacie_item,parent, false);
        return new ViewHolder(view);
    }
    private Filter pharmacieFilt = new
            Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    List<Pharmacie> filteredList = new ArrayList<>();

                    if (constraint == null || constraint.length()==0){
                        filteredList.addAll(pharmacieFilter);
                        //  Log.d("TOTAL",""+pharmacieFilter);
                    }else{
                        String filteredPattern = constraint.toString().toLowerCase().trim();

                        for (Pharmacie item: pharmacieFilter){
                            if(item.getZone().getNom().toLowerCase().startsWith(filteredPattern)){
                                filteredList.add(item);
                                Log.d("itemmm",""+item);
                            }
                        }
                    }
                    FilterResults results=new FilterResults();
                    results.values = filteredList;
                    Log.d("TOTAL",""+results.values);
                    return results;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    pharmacieList.clear();

                    pharmacieList.addAll(((ArrayList<Pharmacie>) results.values));
                    notifyDataSetChanged();

                    Log.d("pharma",""+pharmacieList);

                }
            };

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nom.setText(pharmacieFilter.get(position).getNom());
        holder.adresse.setText(pharmacieFilter.get(position).getAdresse());
        holder.zone.setText(pharmacieFilter.get(position).getZone().getNom());
        holder.ville.setText(pharmacieFilter.get(position).getZone().getVille().getNom());
        holder.garde.setText(pharmacieFilter.get(position).getGarde().getType());
        //button map
        holder.map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(),RechercheMap.class);
                context.startActivity(intent);

            }
        });
        // button numero tele
        holder.mNbr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent i = new Intent(context, tel.class);
                //   context.startActivity(i);
                // String number= "0695555555";
                //Intent callIntent = new Intent(Intent.ACTION_CALL);
                // Intent intent = callIntent.setData(Uri.parse("tel:" + number));//change the number
                //startActivity(view.getContext(), callIntent, Bundle.EMPTY);
                //context.startActivity(intent);
                String number = "+212 6062-49739";
                Uri telephone = Uri.parse("tel:" + number);
                Intent secondeActivite = new Intent(Intent.ACTION_DIAL, telephone);
                context.startActivity(secondeActivite);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pharmacieFilter.size();
    }

    @Override
    public Filter getFilter() {
        return pharmacieFilt;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nom,adresse,zone,ville,garde;
        ImageButton map,mNbr;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nom=itemView.findViewById(R.id.nom);
            adresse=itemView.findViewById(R.id.adresse);
            zone=itemView.findViewById(R.id.zone);
            ville=itemView.findViewById(R.id.ville);
            garde=itemView.findViewById(R.id.garde);
            map =(ImageButton)itemView.findViewById(R.id.butt);
            mNbr = itemView.findViewById(R.id.telephone);

        }
    }

   // public class NewFilter extends Filter {
      //  public RecyclerView.Adapter mAdapter;
       // public NewFilter(RecyclerView.Adapter mAdapter) {
          //  super();
           // this.mAdapter = mAdapter;
        //}
        //@Override
        //protected FilterResults performFiltering(CharSequence charSequence) {
           // pharmacieFilter.clear();
            //final FilterResults results = new FilterResults();
            //if (charSequence.length() == 0) {
               // pharmacieFilter.addAll(pharmacieList);
       //     } else {
          //      final String filterPattern = charSequence.toString().toLowerCase().trim();
         //       for (Pharmacie p : pharmacieList) {
           //         if (p.getNom().toLowerCase().contains(filterPattern)) {
           //             pharmacieFilter.add(p);
              //      }
              //  }
            //}
            //results.values = pharmacieFilter;
            //results.count = pharmacieFilter.size();
            //return results;
        //}
        //@Override
        // protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
           // pharmacieFilter = (List<Pharmacie>) filterResults.values;
            //this.mAdapter.notifyDataSetChanged();
        //}
    //}



}
