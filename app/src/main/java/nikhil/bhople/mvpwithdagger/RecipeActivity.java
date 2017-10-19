package nikhil.bhople.mvpwithdagger;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import nikhil.bhople.mvpwithdagger.adapter.RecipeRecyclerAdapter;
import nikhil.bhople.mvpwithdagger.dagger.component.DaggerRecipeActivityComponent;
import nikhil.bhople.mvpwithdagger.dagger.component.RecipeActivityComponent;
import nikhil.bhople.mvpwithdagger.dagger.module.RecipeActivityModule;
import nikhil.bhople.mvpwithdagger.model.RecipeApiResponce;
import nikhil.bhople.mvpwithdagger.presenter.RecipeActivityPresenter;
import nikhil.bhople.mvpwithdagger.view.RecipeActivityView;

public class RecipeActivity extends AppCompatActivity implements RecipeActivityView {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private EditText query;
    private String queryString;
    private ProgressBar progressBar;

    @Inject  // use this annotation to tell dagger that you require this dependency
    RecipeActivityPresenter presenter;
    @Inject
    Picasso picasso;

    private ProgressDialog pDialog;
    private boolean isUserScrolled = true, isGetResponceFromApi = true, isFirstApiCall = true, isAdapterSet = false ;
    private Integer pageNumber = 1;
    private List<RecipeApiResponce.Result> resultList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        query = (EditText) findViewById(R.id.E_query);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //  building dagger component for getting our dependency
        RecipeActivityComponent component = DaggerRecipeActivityComponent.builder()
                .recipeActivityModule(new RecipeActivityModule(this))
                .mainComponent(MyApplication.get(this).getComponent())
                .build();

        // injecting our dependency from dagger
        component.injectRecipeActivity(this);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait ...");

        initRecyclerView();

        findViewById(R.id.B_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                queryString = query.getText().toString();

                if(queryString.equals(""))
                {
                    Toast.makeText(RecipeActivity.this, "Please enter search string..", Toast.LENGTH_SHORT).show();
                }
                else {
                    isFirstApiCall = true;
                    pageNumber = 1;
                    isUserScrolled = true;
                    if(isAdapterSet){
                        adapter.notifyDataSetChanged();
                    }
                    resultList.clear(); // clearing list if previous search result is present
                    updateRecyclerView();
                }
            }
        });
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // for pagination we set custom scrollListener
        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {

              /*       checking have more data to load and
                 if previously, we call to api then we must got the response otherwise we wait for it */

                if (isUserScrolled && isGetResponceFromApi)
                {
                    isGetResponceFromApi = false; // wait for responce come from api
                    updateRecyclerView();
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        };
        recyclerView.setOnScrollListener(scrollListener);
    }

    private void updateRecyclerView() {

        if(isFirstApiCall){
            pDialog.show(); // only first time show progress dialog as recylcerview is not set yet
        }

        Map<String,String> params = new HashMap<>();
        params.put("q", queryString);
        params.put("p", "" + pageNumber);

        pageNumber++; // increment page number for next call

        presenter.getRecipeData(params); // handing api call from presenter to get data

    }

    @Override // method of view triggered form presenter on successful api call
    public void onSuccessApiResponce(List<RecipeApiResponce.Result> results) {
        isGetResponceFromApi = true;
        progressBar.setVisibility(View.GONE);

        if(isUserScrolled && results.size()>0) // checking api have more data or not
        {
            resultList.addAll(results);
            if(isFirstApiCall)
            {
                pDialog.dismiss();
                isFirstApiCall = false;
                if(!isAdapterSet)
                {
                    // only one time we have to set recycler adapter otherwise it leads to inconsistency in item position
                    adapter = new RecipeRecyclerAdapter(resultList, picasso, RecipeActivity.this);
                    recyclerView.setAdapter(adapter); // only first time setting the adapter
                    isAdapterSet = true;
                }
            }
            adapter.notifyDataSetChanged();
        }
        else
        {
            isUserScrolled = false;  // when there is no more data to load we have to stop api call
        }
    }

    @Override // method of view triggered form presenter on the failure of api
    public void onFailApiCall(String message) {
        pDialog.dismiss();
        isGetResponceFromApi = true;
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
