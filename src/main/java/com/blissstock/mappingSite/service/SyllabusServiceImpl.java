package com.blissstock.mappingSite.service;

import com.blissstock.mappingSite.entity.Content;
import com.blissstock.mappingSite.entity.CourseInfo;
import com.blissstock.mappingSite.entity.Syllabus;
import com.blissstock.mappingSite.exceptions.CourseNotFoundException;
import com.blissstock.mappingSite.exceptions.SyllabusNotFoundException;
import com.blissstock.mappingSite.repository.ContentRepository;
import com.blissstock.mappingSite.repository.CourseInfoRepository;
import com.blissstock.mappingSite.repository.SyllabusRepository;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SyllabusServiceImpl implements SyllabusService {

  private static Logger logger = LoggerFactory.getLogger(
    SyllabusServiceImpl.class
  );

  @Autowired
  UserSessionService userSessionService;

  @Autowired
  CourseInfoRepository courseRepository;

  @Autowired
  SyllabusRepository syllabusRepository;

  @Autowired
  ContentRepository contentRepository;

  @Override
  public void addSyllabus(Syllabus syllabus, Long id)
    throws CourseNotFoundException {
    Optional<CourseInfo> optionalCourseInfo = courseRepository.findById(id);

    if (!optionalCourseInfo.isPresent()) {
      logger.error("Course_id: {} is not found", id);
      throw new CourseNotFoundException(id);
    }


    if (syllabus.getSyllabusId() != 0L) {
      //Remove existing all content
      syllabusRepository.deleteById(syllabus.getSyllabusId());
      logger.info(
        "All content from syllabusId:{} are removed",
        syllabus.getSyllabusId()
      );

      
    }

    CourseInfo courseInfo = optionalCourseInfo.get();
    syllabus.setCourseInfo(courseInfo);

    //Adding Syllabus reference to the content
    for (Content content : syllabus.getContent()) {
      logger.debug("User adding content {}", content);
      content.setSyllabus(syllabus);
      
    }

    syllabusRepository.save(syllabus);

   /*
    courseInfo.getSyllabus().add(syllabus); 
    courseRepository.save(courseInfo); */
    //syllabusRepository.save(syllabus);
    /* syllabus.setCourseInfo(courseInfo);

    //If syllabus id is not 0, it is a update function
    if (syllabus.getSyllabusId() != 0L) {
      //Remove existing all content
      syllabusRepository.deleteById(syllabus.getSyllabusId());
      logger.info(
        "All content from syllabusId:{} are removed",
        syllabus.getSyllabusId()
      );

      
    }  else {
      logger.debug(
        "Size of Syllabus in courseID: {} is {}",
        courseInfo.getCourseId(),
        courseInfo.getSyllabus().size()
      ); 
      System.out.println(syllabus.getCourseInfo().getCourseId() );

      courseInfo.getSyllabus().add(syllabus);
      courseRepository.save(courseInfo);
      return;
    }
    syllabusRepository.save(syllabus); */
  }

  @Override
  public List<Syllabus> getAllSyllabus(long courseId)
    throws CourseNotFoundException {
    Optional<CourseInfo> optionalCourseInfo = courseRepository.findById(
      courseId
    );

    if (!optionalCourseInfo.isPresent()) {
      logger.error("Course_id: {} is not found", courseId);
      throw new CourseNotFoundException(courseId);
    }

    CourseInfo courseInfo = optionalCourseInfo.get();

    List<Syllabus> syllabusList = syllabusRepository.findByCourseInfo(
      courseInfo
    );
    return syllabusList;
  }

  @Override
  public void deleteSyllabus(long syllabusId) throws SyllabusNotFoundException {
    Optional<Syllabus> optionalSyllabus = syllabusRepository.findById(
      syllabusId
    );

    if (!optionalSyllabus.isPresent()) {
      logger.error("SyllabusId: {} is not found", syllabusId);
      throw new SyllabusNotFoundException(syllabusId);
    }

    syllabusRepository.delete(optionalSyllabus.get());
  }
}
