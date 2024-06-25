package com.blissstock.mappingSite.repository;

import java.util.List;

import com.blissstock.mappingSite.entity.Content;
import com.blissstock.mappingSite.entity.Syllabus;

import org.springframework.data.repository.CrudRepository;

public interface ContentRepository extends CrudRepository<Content, Long>{

    List<Content> findBySyllabus(Syllabus syllabus);

    void deleteBySyllabus(Syllabus syllabus);
    
}
