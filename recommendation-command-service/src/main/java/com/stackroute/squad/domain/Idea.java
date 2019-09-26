package com.stackroute.squad.domain;

import lombok.*;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Date;
import java.util.List;

/*@NodeEntity will be represented as nodes in the graph.*/
@NodeEntity
/*it can generate getters and setters for those object automatically by using Lombok annotations. The easiest way is to use the @Data annotation.*/
@Data
/*It generates constructor with no parameters*/
@NoArgsConstructor
/*It generates a constructor with 1 parameter for each field*/
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Idea
{
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private String duration;
    private String cost;
    private String status;
    private String postedBy;
    private Date postedOn;
    //Idea belongs to subdomain
    @Relationship(type = "belongs_to", direction = Relationship.OUTGOING)
    private SubDomain subDomain;
    //Idea requires roles
    @Relationship(type = "requires", direction = Relationship.OUTGOING)
    private List<Roles> role;
    //Idea needs skills
    @Relationship(type = "needs", direction = Relationship.OUTGOING)
    private List<Skills> skills;


}


