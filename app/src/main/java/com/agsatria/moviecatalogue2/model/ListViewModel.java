package com.agsatria.moviecatalogue2.model;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

public class ListViewModel extends ViewModel {
    private RecyclerView rv;

    public RecyclerView getRv() {
        return rv;
    }

    public void setRv(RecyclerView rv) {
        this.rv = rv;
    }
}
