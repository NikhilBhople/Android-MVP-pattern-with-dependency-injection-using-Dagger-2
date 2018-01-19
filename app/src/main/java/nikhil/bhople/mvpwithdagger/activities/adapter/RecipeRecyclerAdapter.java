package nikhil.bhople.mvpwithdagger.activities.adapter;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import nikhil.bhople.mvpwithdagger.R;
import nikhil.bhople.mvpwithdagger.activities.RecipeActivity;
import nikhil.bhople.mvpwithdagger.activities.mvp.RecipeApiResponce;

/**
 * Created by admin on 18-Oct-17.
 */

public class RecipeRecyclerAdapter extends RecyclerView.Adapter<RecipeRecyclerAdapter.ViewHolder> {
    private final List<RecipeApiResponce.Result> results;
    private final Picasso picasso;
    private RecipeActivity activity;

    public RecipeRecyclerAdapter(List<RecipeApiResponce.Result> results, Picasso picasso, RecipeActivity activity) {

        this.results = results;
        this.picasso = picasso;
        this.activity = activity;
    }

    @Override
    public RecipeRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_layout,null));
    }

    @Override
    public void onBindViewHolder(RecipeRecyclerAdapter.ViewHolder holder, int position) {

        holder.title.setText(results.get(position).getTitle());
        holder.content.setText(results.get(position).getIngredients());

        if(!results.get(position).getThumbnail().equals(""))
        {
            picasso.setIndicatorsEnabled(true);
            picasso.load(results.get(position).getThumbnail()).placeholder(R.drawable.progress_animation).into(holder.image);
        }
        else {
            picasso.load(R.drawable.no_image_icon).into(holder.image);
        }
    }

    @Override
    public int getItemCount() {
        return results == null ? 0 :results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView image;
        private TextView title, content;
        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.T_title);
            content = (TextView) itemView.findViewById(R.id.T_content);

            image = (ImageView) itemView.findViewById(R.id.image);

            itemView.findViewById(R.id.LL_main).setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(results.get(getAdapterPosition()).getHref()));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setPackage("com.android.chrome");
            try {
                activity.startActivity(intent);
            } catch (ActivityNotFoundException ex) {
                // Chrome browser presumably not installed so allow user to choose instead
                intent.setPackage(null);
                activity.startActivity(intent);
            }
        }
    }
}
