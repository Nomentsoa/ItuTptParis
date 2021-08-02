package mg.itu.lazanomentsoa.itutptparis.views.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import mg.itu.lazanomentsoa.itutptparis.R;
import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.LocalisationAgence;

public class LocalisationAgenceSpinnerAdapter extends ArrayAdapter<LocalisationAgence> {
    List<LocalisationAgence> localisationAgences;
    LayoutInflater flater;


    public LocalisationAgenceSpinnerAdapter(@NonNull @NotNull Activity context, int resource, @NonNull @NotNull List<LocalisationAgence> objects) {
        super(context, resource, objects);
        flater = context.getLayoutInflater();
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        if(convertView == null){
            convertView = flater.inflate(R.layout.agence_item_spinner,parent, false);
        }
        LocalisationAgence localisationAgence = getItem(position);
        TextView tvNomAgence = (TextView) convertView.findViewById(R.id.tv_nom_agence);
        if(localisationAgence != null && localisationAgence.getAgence() != null)
            tvNomAgence.setText(localisationAgence.getAgence().getNom());

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable @org.jetbrains.annotations.Nullable View convertView, @NonNull @NotNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent){
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.agence_item_spinner, parent, false);

        TextView tvNom = convertView.findViewById(R.id.tv_nom_agence);
        LocalisationAgence currentLocalisationAgence = getItem(position);

        if(currentLocalisationAgence != null && currentLocalisationAgence.getAgence() != null)
            tvNom.setText(currentLocalisationAgence.getAgence().getNom());

        return convertView;
    }
}
