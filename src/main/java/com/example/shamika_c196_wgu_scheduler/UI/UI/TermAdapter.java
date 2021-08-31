package com.example.shamika_c196_wgu_scheduler.UI.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shamika_c196_wgu_scheduler.R;
import com.example.shamika_c196_wgu_scheduler.UI.Entities.TermEntity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewholder> {
    class TermViewholder extends RecyclerView.ViewHolder{
        private final TextView termItemView;
        private final TextView termItemView2;

        private TermViewholder(View itemView){
            super(itemView);
            termItemView=itemView.findViewById(R.id.textView3);
            termItemView2=itemView.findViewById(R.id.textView4);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final TermEntity current = mTerms.get(position);
                    Intent intent = new Intent(context, CourseList.class);
                    intent.putExtra("termID", current.getTermID());
                    intent.putExtra("termTitle", current.getTermTitle());
                    intent.putExtra("startDate", current.getStartDate());
                    intent.putExtra("endDate", current.getEndDate());
                    context.startActivity(intent);
                }
            });
        }

    }
    private List<TermEntity> mTerms;
    private final Context context;
    private final LayoutInflater mInflater;

    public TermAdapter(Context context){
        mInflater=LayoutInflater.from(context);
        this.context=context;
    }


    @NonNull
    @NotNull
    @Override
    public TermViewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.term_list_item,parent,false);

        return new TermViewholder(itemView);
    }

    @NonNull
    @NotNull
    @Override
    public void onBindViewHolder(@NonNull @NotNull TermViewholder holder, int position) {

        if(mTerms!=null){
            final TermEntity current = mTerms.get(position);
            holder.termItemView2.setText(current.getTermTitle());
            holder.termItemView.setText(Integer.toString(current.getTermID()));
        }
        else {
            holder.termItemView2.setText("No Term Title");
            holder.termItemView.setText("No Term ID");
        }
    }

    public void setTerms(List<TermEntity> terms){
        mTerms=terms;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mTerms!= null)
        return mTerms.size();
        else return 0;
    }
}
