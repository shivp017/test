package com.stackroute.recommendedqueryservice.domain;


import com.stackroute.recommendedqueryservice.domain.Idea;
import com.stackroute.recommendedqueryservice.domain.Skills;
import lombok.*;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/*@NodeEntity will be represented as nodes in the graph.*/
@NodeEntity
/*it can generate getters and setters for those object automatically by using Lombok annotations. The easiest way is to use the @Data annotation.*/
@Data
/*It generates constructor with no parameters*/
@NoArgsConstructor
/*It generates a cnstructor with 1 parameter for each field*/
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ServiceProvider {
  @Id
  @GeneratedValue
  private Long id;
  private String name;
  private String mobileNo;
  private String email;
  private String domain;
  private String subDomain;
  private String previousProject;
  private String chargePerHour;
  private String currentLocation;
  private String role;
  private List<String> skillList;
  private String experience;
  private List<String> preferredLocation;
  @Relationship(type = "has_skills", direction = Relationship.OUTGOING)
  private List<Skills> skills;
  @Relationship(type = "worked_on", direction = Relationship.OUTGOING)
  private Idea idea;
  @Relationship(type = "applied_for", direction = Relationship.OUTGOING)
  private Idea ideaDto;
}
