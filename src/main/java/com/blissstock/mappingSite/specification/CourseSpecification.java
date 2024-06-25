package com.blissstock.mappingSite.specification;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.LoggerFactory;
import javax.persistence.criteria.Predicate;

import com.blissstock.mappingSite.dto.CourseInfoDTO;
import com.blissstock.mappingSite.entity.CourseInfo;
import com.blissstock.mappingSite.enums.UserRole;

import org.slf4j.Logger;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Component;

@Component
public class CourseSpecification {
    private final static Logger logger = LoggerFactory.getLogger(CourseSpecification.class);

    public Specification<CourseInfo> getCourses(CourseInfoDTO courseInfoDTO) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            // By course name
            if (courseInfoDTO.getCourseName() != null && !courseInfoDTO.getCourseName().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("courseName")),
                        "%" + courseInfoDTO.getCourseName().toLowerCase() + "%"));
            }

            if (courseInfoDTO.getTeacherName() != null && !courseInfoDTO.getTeacherName().isEmpty()) {

                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("userInfo").get("userName")),
                        "%" + courseInfoDTO.getTeacherName().toLowerCase() + "%"));
            }

            // By start_date
            if (courseInfoDTO.getStartDate() != null) {
                predicates
                        .add(criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), courseInfoDTO.getStartDate()));
            }

            // By end_date
            if (courseInfoDTO.getEndDate() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("endDate"), courseInfoDTO.getEndDate()));
            }

            if (courseInfoDTO.getLowestFee() > 0) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("fees"), courseInfoDTO.getLowestFee()));
            }

            if (courseInfoDTO.getHighestFee() > 0) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("fees"), courseInfoDTO.getHighestFee()));
            }

            // Only get approved course

            Boolean isApproved = true;

            // logger.warn("{}",
            // courseInfoDTO.getLogInUser().equals(UserRole.ADMIN.getValue()));
            if (!(courseInfoDTO.getLogInUser().equals(UserRole.ADMIN.getValue())
                    || courseInfoDTO.getLogInUser().equals(UserRole.SUPER_ADMIN.getValue()))) {

                if (courseInfoDTO.getLogInUser().equals(UserRole.TEACHER.getValue())) {
                    Predicate predicateForTeacherCourse = criteriaBuilder.equal(root.get("userInfo").get("uid"),
                            courseInfoDTO.getLogInUserId());
                    Predicate predicateForApprovedCourse = criteriaBuilder.equal(root.get("isCourseApproved"), true);
                    predicates.add(criteriaBuilder.or(predicateForTeacherCourse,
                            predicateForApprovedCourse));
                } else {
                    predicates.add(criteriaBuilder.equal(root.get("isCourseApproved"), isApproved));
                }
            }
            query.orderBy(criteriaBuilder.desc(root.get("startDate")));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
