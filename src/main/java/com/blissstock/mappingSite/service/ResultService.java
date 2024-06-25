package com.blissstock.mappingSite.service;

import com.blissstock.mappingSite.entity.Result;

import java.util.List;
import java.util.Optional;

public interface ResultService {
    List<Result> getAllResults();
    Optional<Result> getResultById(Long id);
    Result saveResult(Result result);
    Result updateResult(Result result);
    void deleteResult(Long id);
}

