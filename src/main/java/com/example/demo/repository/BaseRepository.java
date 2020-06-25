package com.example.demo.repository;

import com.example.demo.entities.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

public interface BaseRepository <E extends BaseEntity, ID extends Serializable> extends JpaRepository<E, ID>, JpaSpecificationExecutor<E> {
}