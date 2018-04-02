package com.spring.beanFilter.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TASK_MASTER")
@Data
public class Task {

    @Id
    @Column(name = "ID")
    private String taskId;
    @Column(name = "NAME")
    private String taskName;
}
