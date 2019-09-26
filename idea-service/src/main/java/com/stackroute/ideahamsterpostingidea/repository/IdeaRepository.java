package com.stackroute.ideahamsterpostingidea.repository;

import com.stackroute.ideahamsterpostingidea.domain.Idea;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/*@Repository annotation is used to indicate that the class provides the mechanism for storage, retrieval,
 search, update and delete operation on objects.*/
@Repository
public interface IdeaRepository extends MongoRepository<Idea, String> {
  /**findByTitle method to get idea by its title*/
  public Idea findByTitle(String title);
  /**findByLocation method to get idea by its location*/
  public Idea findByLocation(String location);

  /**findByPostedBy method to get idea by its emailId*/
  public List<Idea> findByPostedBy(String postedBy);

}
