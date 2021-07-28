package mg.itu.lazanomentsoa.itutptparis.views.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import mg.itu.lazanomentsoa.itutptparis.R;
import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.Match;

public class MatchListAdapter extends RecyclerView.Adapter<MatchListAdapter.ViewHolder> {
    private List<Match> matches;

    public MatchListAdapter(List<Match> matches) {
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
        holder.btnNull.setText(match.get()+"");
    }

    @Override
    public int getItemCount() {
        return matches != null ? matches.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvEquipe1, tvEquipe2;
        Button btnEquipe1, btnEquipe2, btnNull;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            tvEquipe1 = (TextView)itemView.findViewById(R.id.tv_nom_equipe1);
            tvEquipe2 = (TextView)itemView.findViewById(R.id.tv_nom_equipe2);
            btnEquipe1 = (Button)itemView.findViewById(R.id.btn_equipe1);
            btnEquipe2 = (Button)itemView.findViewById(R.id.btn_equipe2);
            btnNull = (Button)itemView.findViewById(R.id.btn_null);
        }
    }
}
