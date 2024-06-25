package com.blissstock.mappingSite.service;

import java.util.List;
import java.util.Optional;

import com.blissstock.mappingSite.dto.CourseInfoDTO;
import com.blissstock.mappingSite.entity.CourseCategory;
import com.blissstock.mappingSite.entity.CourseInfo;
import com.blissstock.mappingSite.entity.JoinCourseUser;
import com.blissstock.mappingSite.entity.Syllabus;
import com.blissstock.mappingSite.exceptions.CourseNotFoundException;
import com.blissstock.mappingSite.exceptions.SyllabusNotFoundException;
import com.blissstock.mappingSite.repository.CourseCategoryRepository;
import com.blissstock.mappingSite.repository.CourseInfoRepository;
import com.blissstock.mappingSite.repository.CourseTimeRepository;
import com.blissstock.mappingSite.repository.JoinCourseUserRepository;
import com.blissstock.mappingSite.specification.CourseSpecification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    private static Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Autowired
    CourseInfoRepository courseInfoRepository;

    @Autowired
    CourseSpecification courseSpecification;

    @Autowired
    CourseTimeRepository courseTimeRepository;

    @Autowired
    SyllabusServiceImpl syllabusServiceImpl;

    @Autowired
    JoinCourseUserRepository joinCourseUserRepository;

    // TODO Get from application.properties
    @Value("${com.blissstock.mapping-site.config.courseSearchLimit}")
    Integer DEFAULT_COURSE_LIMIT = 0;

    @Override
    public List<CourseInfo> getCourseList(CourseInfoDTO courseInfoDTO) {
        logger.debug("DEFAULT_COURSE_LIMIT: {}", DEFAULT_COURSE_LIMIT);
        Pageable paging = PageRequest.of(0, DEFAULT_COURSE_LIMIT);
        return courseInfoRepository.findAll(courseSpecification.getCourses(courseInfoDTO), paging);
    }

    @Override
    public void deleteCourseInfo(CourseInfo courseInfo) {
        logger.info("delete course {}", courseInfo.getCourseId());
        courseInfoRepository.delete(courseInfo);
        // if(courseInfo.getClassType() == "live"){
        // courseTimeRepository.deleteByCourseID(courseInfo.getCourseId());
        // }

        // if (!syllabusServiceImpl.getAllSyllabus(courseInfo.getCourseId().isPresent())
        // {
        // logger.error("Course_id: {} is not found", courseInfo.getCourseId());
        // throw new CourseNotFoundException(courseInfo.getCourseId());
        // }

        // List<Syllabus> syllabusList;
        // syllabusList = syllabusServiceImpl.getAllSyllabus(courseInfo.getCourseId());
        // if(!syllabusList.isEmpty()){

        // }

        // Optional<List<Syllabus>> optionalSyllabus =
        // syllabusServiceImpl.getAllSyllabus(courseInfo.getCourseId());

        // if (optionalSyllabus.isPresent()){

        // }

        // try{
        // syllabusList = syllabusServiceImpl.getAllSyllabus(courseInfo.getCourseId());
        // logger.info("Get syllabus list of courseid {}", courseInfo.getCourseId());
        // }catch(CourseNotFoundException e){
        // syllabusList = null;
        // logger.error("Syllabus of Course not found {}", courseInfo.getCourseId());
        // }
        // // List<Syllabus> syllabusList =
        // syllabusServiceImpl.getAllSyllabus(courseInfo.getCourseId());
        // if(syllabusList != null){
        // for(Syllabus syllabus: syllabusList){
        // syllabusServiceImpl.deleteSyllabus(syllabus.getSyllabusId());
        // }
        // }

        // List<JoinCourseUser> joinUserInfo =
        // joinCourseUserRepository.findByCourseID(courseInfo.getCourseId());
        // if(joinUserInfo != null){
        // for(JoinCourseUser joinuserList: joinUserInfo){
        // joinCourseUserRepository.deleteById(joinuserList.getJoinId());
        // }
        // }

    }

    @Override
    public void verifyCourseInfo(CourseInfo courseInfo) {
        logger.info("verify course {}", courseInfo.getCourseId());
        courseInfo.setIsCourseApproved(true);
        courseInfoRepository.save(courseInfo);

    }

    @Override
    public CourseInfo getCourseById(long id) {
        return courseInfoRepository.findByCourseID(id);
    }

    @Autowired
    private CourseCategoryRepository courseCategoryRepository;
    @Override
    public List<CourseCategory> getAllCourseCategory(){
        return courseCategoryRepository.findAll();
    }

    @Override
    public void addCategory(CourseCategory categoryName) {
        this.courseCategoryRepository.save(categoryName);
        
    }
    
    @Override
    public CourseCategory getCategoryById(long categoryId) {
        Optional<CourseCategory> optional = courseCategoryRepository.findById(categoryId);
        CourseCategory courseCategory = null;
        if(optional.isPresent()) {
            courseCategory = optional.get();
        }
        else {
            throw new RuntimeException("Category not found for ID :: " + categoryId);
        }
        return courseCategory;
    }

    @Override
    public void deleteCategoryById(long categoryId) {
       this.courseCategoryRepository.deleteById(categoryId);
        
    }

    

    
}
