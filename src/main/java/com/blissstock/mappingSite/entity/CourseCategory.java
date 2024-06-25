package com.blissstock.mappingSite.entity;

import java.sql.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "course_category")
public class CourseCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoryId;

    @Column(name = "category_name", length = 100)
    private String categoryName;

    @Column(name = "created_on")
    private Date createdOn = new Date(System.currentTimeMillis());

    public long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public Date getCreatedOn() {
        return createdOn;
    }
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }



   
    }
    
    
    // mapping

	// @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "sqlcourseCategory")
	// @JsonIgnore
	// private CourseInfo sqlcourseCategory;

	// @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "sqlcourseCategory")
	// @JsonIgnore
	// private List<CourseCategory> sqlCourseCategories = new ArrayList<>();

