package com.stackroute.Muzix.domain;

import lombok.*;
import org.springframework.context.annotation.PropertySource;
import org.springframework.beans.factory.annotation.Value;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Track")
@PropertySource("application.properties")
public class Track {
    @Id
    @Column(name="id")
    @Value("id")
    private int id;
    @Column(name="name")

    @Value("${value.name}")
    private String name;
    @Column(name="comment")
    @Value("${value.comment}")
    private String comment;


}