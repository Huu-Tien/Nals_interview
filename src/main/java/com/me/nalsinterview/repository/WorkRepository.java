package com.me.nalsinterview.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.me.nalsinterview.entity.Work;

public interface WorkRepository extends JpaRepository<Work, Long> {

}
