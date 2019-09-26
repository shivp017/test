package com.stackroute.squad.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
/*It generates a constructor with 1 parameter for each field*/
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Roles {
  @Id
@GeneratedValue
  private Long id;
  private String role;
  private String noOfPeople;
  private String experience;
  private Skills skills;
  @Relationship(type = "played_by", direction = Relationship.OUTGOING)
  private ServiceProvider serviceProvider;
}


