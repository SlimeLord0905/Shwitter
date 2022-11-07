package com.mathieu_mercier.shwitter.api;

import com.mathieu_mercier.shwitter.model.Relation;

import java.util.ArrayList;

public interface RelationFetchListener {
    void OnRespond(ArrayList<Relation> response);
}
