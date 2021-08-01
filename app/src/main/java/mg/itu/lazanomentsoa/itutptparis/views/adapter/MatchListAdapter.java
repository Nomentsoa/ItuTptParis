package mg.itu.lazanomentsoa.itutptparis.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.List;

import mg.itu.lazanomentsoa.itutptparis.R;
import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.Match;
import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.Pari;
import mg.itu.lazanomentsoa.itutptparis.utils.SessionManager;
import mg.itu.lazanomentsoa.itutptparis.views.fragments.AccueilFragment;
import mg.itu.lazanomentsoa.itutptparis.views.fragments.PariDialogFragment;

public class  MatchListAdapter extends RecyclerView.Adapter<MatchListAdapter.ViewHolder> {
    private List<Match> matches;
    private Context context;
    SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy");
    private String idUserConnected;

    public MatchListAdapter(List<Match> matches, Context context) {
        this.idUserConnected = SessionManager.getInstance(context).getIdConnectedUser();
        this.matches = matches;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_match, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Match match = matches.get(position);
        holder.tvEquipe1.setText(match.getEquipe1().getNom());
        holder.tvEquipe2.setText(match.getEquipe2().getNom());
        holder.btnEquipe1.setText(match.getCoteEquipe1()+"");
        holder.btnEquipe2.setText(match.getCoteEquip2()+"");
        holder.btnNull.setText(match.getCoteMatchNull()+"");
        holder.tvBtnLabelEquipe1.setText(match.getEquipe1().getNom());
        holder.tvBtnLabelEquipe2.setText(match.getEquipe2().getNom());

        String date = fromUser.format(match.getDate());
        holder.tvDateHeure.setText(date + " à " + match.getHeure());

        holder.btnEquipe1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pari pari = new Pari(match.getId(), match.getEquipe1(), idUserConnected);
                AccueilFragment.showPariDialog(pari, match.getCoteEquipe1());
            }
        });

        holder.btnEquipe2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pari pari = new Pari(match.getId(), match.getEquipe2(), idUserConnected);
                AccueilFragment.showPariDialog(pari, match.getCoteEquip2());
            }
        });

        holder.btnNull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pari pari = new Pari(match.getId(), null, idUserConnected);
                AccueilFragment.showPariDialog(pari, match.getCoteMatchNull());
            }
        });
    }

    @Override
    public int getItemCount() {
        return matches != null ? matches.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvEquipe1, tvEquipe2, tvBtnLabelEquipe1, tvBtnLabelEquipe2, tvDateHeure;
        Button btnEquipe1, btnEquipe2, btnNull;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvDateHeure = (TextView)itemView.findViewById(R.id.tv_date_heure);
            tvEquipe1 = (TextView)itemView.findViewById(R.id.tv_nom_equipe1);
            tvEquipe2 = (TextView)itemView.findViewById(R.id.tv_nom_equipe2);
            tvBtnLabelEquipe1 = (TextView)itemView.findViewById(R.id.tv_btn_label_equipe1);
            tvBtnLabelEquipe2 = (TextView)itemView.findViewById(R.id.tv_btn_label_equipe2);
            btnEquipe1 = (Button)itemView.findViewById(R.id.btn_equipe1);
            btnEquipe2 = (Button)itemView.findViewById(R.id.btn_equipe2);
            btnNull = (Button)itemView.findViewById(R.id.btn_null);
        }
    }

    public void setNewMatchList(List<Match> newMatchList){
        this.matches = newMatchList;
        notifyDataSetChanged();
    }


}
