package com.jrstep.dao.local.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "students_seq")
public class StudentsSeq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "next_not_cached_value", nullable = false)
    private Long nextNotCachedValue;

    @Column(name = "minimum_value", nullable = false)
    private Long minimumValue;

    @Column(name = "maximum_value", nullable = false)
    private Long maximumValue;

    /**
     * start value when sequences is created or value if RESTART is used
     */
    @Column(name = "start_value", nullable = false)
    private Long startValue;

    /**
     * increment value
     */
    @Column(name = "increment", nullable = false)
    private Long increment;

    @Column(name = "cache_size", nullable = false)
    private Long cacheSize;

    /**
     * 0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed
     */
    @Column(name = "cycle_option", nullable = false)
    private Integer cycleOption;

    /**
     * How many cycles have been done
     */
    @Column(name = "cycle_count", nullable = false)
    private Long cycleCount;

}
