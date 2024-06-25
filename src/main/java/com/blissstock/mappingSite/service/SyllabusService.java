package com.blissstock.mappingSite.service;

import java.util.List;

import com.blissstock.mappingSite.entity.Syllabus;
import com.blissstock.mappingSite.exceptions.CourseNotFoundException;
import com.blissstock.mappingSite.exceptions.SyllabusNotFoundException;


public interface SyllabusService {

    public void addSyllabus(Syllabus syllabus, Long id) throws CourseNotFoundException;
    public List<Syllabus> getAllSyllabus(long id) throws CourseNotFoundException;
    public void deleteSyllabus(long syllabusId) throws SyllabusNotFoundException;
    
}
