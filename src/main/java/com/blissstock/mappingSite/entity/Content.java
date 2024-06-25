package com.blissstock.mappingSite.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Data
@Table(name = "content")
public class Content {
	
	@Column(name = "content_id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long contentId;

    @NotBlank(message="Please fill content under title")
	@Column(name="content", length = 50)
	private String content;

	//mapping
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "syllabus_id")
    @JsonIgnore
    private Syllabus syllabus;
	
	//Constructors

	public Content() {
	}

	public Content(Long contentId, String content, Syllabus syllabus) {
		this.contentId = contentId;
		this.content = content;
		this.syllabus = syllabus;
	}

	public Long getContentId() {
		return this.contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Syllabus getSyllabus() {
		return this.syllabus;
	}

	public void setSyllabus(Syllabus syllabus) {
		this.syllabus = syllabus;
	}
	

}

