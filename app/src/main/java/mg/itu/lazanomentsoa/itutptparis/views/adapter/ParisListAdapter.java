package mg.itu.lazanomentsoa.itutptparis.views.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import mg.itu.lazanomentsoa.itutptparis.R;
import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.Pari;
import mg.itu.lazanomentsoa.itutptparis.utils.StringConstant;
import mg.itu.lazanomentsoa.itutptparis.viewmodel.QRCodeViewModel;

public class ParisListAdapter extends RecyclerView.Adapter<ParisListAdapter.ViewHolder> {
    private List<Pari> paris;
    private QRCodeViewModel qrCodeViewModel;
    private LifecycleOwner lifecycleOwner;

    public ParisListAdapter(List<Pari> paris, LifecycleOwner lifecycleOwner) {
        this.paris = paris;
        this.lifecycleOwner = lifecycleOwner;
        qrCodeViewModel = new ViewModelProvider((ViewModelStoreOwner) lifecycleOwner).get(QRCodeViewModel.class);

    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_paris, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Pari pari = paris.get(position);
        if(pari.getEquipe() == null)
            holder.tvEquipeGagnante.setText("Null");
        else
            holder.tvEquipeGagnante.setText(pari.getEquipe().getNom());

        holder.tvSommeMise.setText(StringConstant.getThousantNumberByString(pari.getMise()+"") + " Ar");
        qrCodeViewModel.getMatchById(pari.getIdMatch()).observe(lifecycleOwner, retourMatch -> {
            if(retourMatch != null){
                String date = StringConstant.dateFormat.format(retourMatch.getMatch().getDate());
                holder.tvDateHeure.setText(date + " à " + retourMatch.getMatch().getHeure());
                holder.tvCategorie.setText(retourMatch.getMatch().getCategorie().getNom());

                if(pari.getEquipe() == null)
                    holder.tvCote.setText(retourMatch.getMatch().getCoteMatchNull()+"");
                else if (pari.getEquipe().getNom().equals(retourMatch.getMatch().getEquipe1().getNom()))
                    holder.tvCote.setText(retourMatch.getMatch().getCoteEquipe1()+"");
                else
                    holder.tvCote.setText(retourMatch.getMatch().getCoteEquip2()+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return paris == null ? 0 : paris.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDateHeure, tvEquipeGagnante, tvCategorie, tvSommeMise, tvCote;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvDateHeure = itemView.findViewById(R.id.tv_date_heure);
            tvEquipeGagnante = itemView.findViewById(R.id.tv_equipechoisie);
            tvCategorie = itemView.findViewById(R.id.tv_categorie);
            tvSommeMise = itemView.findViewById(R.id.tv_somme_mise);
            tvCote = itemView.findViewById(R.id.tv_cote);
        }
    }
}
