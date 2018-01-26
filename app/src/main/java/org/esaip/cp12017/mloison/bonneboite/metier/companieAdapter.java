package org.esaip.cp12017.mloison.bonneboite.metier;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.esaip.cp12017.mloison.bonneboite.R;

import java.util.List;

/**
 * Created by Darkadok on 24/01/2018.
 */

public class companieAdapter extends RecyclerView.Adapter<companieAdapter.CompanieViewHolder> {
    private List<companie> _companies;

    public companieAdapter(List<companie> _companies) {
        this._companies = _companies;
    }

    public void onArticlesReceived(List<companie> companies, boolean hasMore) {
        _companies.clear();
        _companies.addAll(companies);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return _companies.size();
    }

    @Override
    public CompanieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_companie, parent, false);
        return new CompanieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CompanieViewHolder holder, int position) {
        holder.bind(_companies.get(position));
    }



    public static class CompanieViewHolder extends RecyclerView.ViewHolder {

        private final TextView _title;
        private final Button _button;

        public CompanieViewHolder(View view) {
            super(view);
            _title = (TextView) view.findViewById(R.id.Text);
            _button = (Button) view.findViewById(R.id.Button);
        }

        public void bind(final companie c) {
            _title.setText(c.toString());
            _button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 1. Appeler une URL web
                    String url = c.getUrl();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}

