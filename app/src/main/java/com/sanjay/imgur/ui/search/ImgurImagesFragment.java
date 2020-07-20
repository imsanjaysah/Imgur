package com.sanjay.imgur.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import com.jakewharton.rxbinding3.appcompat.RxSearchView;


import com.sanjay.imgur.R;
import com.sanjay.imgur.constants.State;
import com.sanjay.imgur.databinding.FragmentImgurImagesBinding;
import com.sanjay.imgur.ui.BaseFragment;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

import static com.sanjay.imgur.ui.search.ImgurImagesPagedListAdapter.DATA_VIEW_TYPE;
import static com.sanjay.imgur.ui.search.ImgurImagesPagedListAdapter.FOOTER_VIEW_TYPE;

public class ImgurImagesFragment extends BaseFragment {
    private static final String DEFAULT_SEARCH = "vanilla";


    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private ImgurSearchViewModel viewModel;
    private ImgurImagesPagedListAdapter imgurAdapter;
    private FragmentImgurImagesBinding binding;
    private SearchView searchView;
    private Disposable searchSubscription;

    private Boolean isQueryChanged = false;
    private Boolean isPaused = false;
    private Boolean isSearchViewOpened = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentComponent.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (binding == null) {
            binding = FragmentImgurImagesBinding.inflate(inflater, container, false);
            //context ?: return binding?.root;
            viewModel =
                    new ViewModelProvider(this, viewModelFactory).get(ImgurSearchViewModel.class);

            viewModel.getSearchQuery().setValue(DEFAULT_SEARCH);

        }
        initAdapter();
        initState();
        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.toolbar);
        setHasOptionsMenu(true);
        return binding.getRoot();
    }


    /**
     * Initializing the adapter
     */
    private void initAdapter() {
        imgurAdapter = new ImgurImagesPagedListAdapter();

        ((GridLayoutManager) binding.recyclerViewImages.getLayoutManager()).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (imgurAdapter.getItemViewType(position)) {
                    case DATA_VIEW_TYPE:
                        return 1;
                    case FOOTER_VIEW_TYPE:
                        return 2; //number of columns of the grid
                    default:
                        return -1;
                }
            }
        });

        //set the adapter
        binding.recyclerViewImages.setAdapter(imgurAdapter);
        //Observing live data for changes, new changes are submitted to PagedAdapter
        viewModel.getImages().observe(getViewLifecycleOwner(), images -> {
            imgurAdapter.submitList(images);
            //Workaround to fix this issue
            //https://stackoverflow.com/questions/30220771/recyclerview-inconsistency-detected-invalid-item-position
            if (isQueryChanged) {
                imgurAdapter.notifyDataSetChanged();
                isQueryChanged = false;
            }
        });
    }

    /**
     * Initializing the state
     */
    private void initState() {
        //On click of retry textview call the api again
        binding.txtError.setOnClickListener(view -> viewModel.retry());
        //Observing the different states of the API calling, and updating the UI accordingly
        viewModel.getState().observe(getViewLifecycleOwner(), state -> {
            binding.progressBar.setVisibility(
                    (viewModel.listIsEmpty() && state == State.LOADING) ? View.VISIBLE : View.GONE);
            binding.txtError.setVisibility(
                    (viewModel.listIsEmpty() && state == State.ERROR) ? View.VISIBLE : View.GONE);
            if (!viewModel.listIsEmpty()) {
                imgurAdapter.setState(state != null ? state : State.DONE);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        isPaused = true;
        isSearchViewOpened = !searchView.isIconified();
    }

    @Override
    public void onResume() {
        super.onResume();
        isPaused = false ;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        searchSubscription.dispose();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_activity_imgur, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = ((SearchView) searchItem.getActionView());
        searchView.setQueryHint("Search fruits, flowers, etc.");
        if (isSearchViewOpened) {
            searchItem.expandActionView();
            searchView.setQuery(viewModel.getSearchQuery().getValue(), false);
        }
        searchSubscription = RxSearchView.queryTextChangeEvents(searchView)
                .skip(1)
                .debounce(250,
                        TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .map(event -> event.getQueryText()
                        .toString())
                .subscribe(it -> {
                    if (!isPaused) {
                        isQueryChanged = true;
                        viewModel.getSearchQuery().setValue(
                        (it.isEmpty()) ? DEFAULT_SEARCH : it);
                    }
                });

    }
}
