package com.springboot.lms.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.lms.entities.BookIssue;

public interface BookIssueRepository extends JpaRepository<BookIssue, Integer>{

	public BookIssue findById(int id);
}
