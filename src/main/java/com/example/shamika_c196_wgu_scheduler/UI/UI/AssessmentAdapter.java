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
import com.example.shamika_c196_wgu_scheduler.UI.Entities.AssessmentEntity;
import com.example.shamika_c196_wgu_scheduler.UI.Entities.TermEntity;


import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder>{
    class AssessmentViewHolder extends RecyclerView.ViewHolder{
        private final TextView assessmentItemView;
        private final TextView assessmentItemView2;

        private AssessmentViewHolder(View itemView){
            super(itemView);
            assessmentItemView=itemView.findViewById(R.id.textView7);
            assessmentItemView2=itemView.findViewById(R.id.textView8);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final AssessmentEntity current = mAssessments.get(position);
                    Intent intent = new Intent(context, AssessmentDetail.class);
                    intent.putExtra("assessmentID", current.getAssessmentID());
                    intent.putExtra("title", current.getTitle());
                    intent.putExtra("startDate", current.getStartDate());
                    intent.putExtra("endDate", current.getEndDate());
                    intent.putExtra("type", current.getType());
                    intent.putExtra("courseID", current.getCourseID());
                    context.startActivity(intent);
                }
            });
        }

    }
    private List<AssessmentEntity> mAssessments;
    private final Context context;
    private final LayoutInflater mInflater;

    public AssessmentAdapter(Context context){
        mInflater=LayoutInflater.from(context);
        this.context=context;
    }
    @NonNull
    @NotNull
    @Override

    public AssessmentAdapter.AssessmentViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.assessment_list_item,parent,false);
        return new AssessmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AssessmentAdapter.AssessmentViewHolder holder, int position) {

        if(mAssessments!=null){
            final AssessmentEntity current = mAssessments.get(position);
            holder.assessmentItemView2.setText(current.getTitle());
            holder.assessmentItemView.setText(Integer.toString(current.getAssessmentID()));
        }
        else {
            holder.assessmentItemView2.setText("No Assessment Title");
            holder.assessmentItemView.setText("No Assessment ID");
        }
    }

    public void setAssessments(List<AssessmentEntity> assessments){
        mAssessments=assessments;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mAssessments!= null)
            return mAssessments.size();
        else return 0;
    }
}
