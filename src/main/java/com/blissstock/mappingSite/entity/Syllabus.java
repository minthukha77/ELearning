package com.blissstock.mappingSite.entity;

import java.util.ArrayList;
import java.util.List;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data

@Table(name = "syllabus")
public class Syllabus {

  @Column(name = "syllabus_id")
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long syllabusId;

  @NotBlank(message = "Please fill title")
  @Column(name = "title", length = 100)
  private String title;

  // mapping
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "courseId_fkey")
  @JsonIgnore
  private CourseInfo courseInfo;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "syllabus")
  @JsonIgnore
  private List<Content> content = new ArrayList<>();

}
