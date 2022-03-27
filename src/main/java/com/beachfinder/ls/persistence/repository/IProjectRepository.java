package com.beachfinder.ls.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.beachfinder.ls.persistence.model.Project;

public interface IProjectRepository extends PagingAndSortingRepository<Project, Long> {
}
