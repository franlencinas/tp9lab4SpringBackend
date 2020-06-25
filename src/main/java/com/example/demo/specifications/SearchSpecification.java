package com.example.demo.specifications;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SearchSpecification<E> {

    public Specification<E> findByProperty(String propertyName, String propertyValue) {
        return new Specification<E>() {
            public Predicate toPredicate(Root<E> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                return builder.like(root.get(propertyName).as(String.class), "%" + propertyValue + "%");
            }
        };
    }

}
