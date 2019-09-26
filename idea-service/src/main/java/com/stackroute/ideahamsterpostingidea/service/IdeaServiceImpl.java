package com.stackroute.ideahamsterpostingidea.service;

import com.stackroute.ideahamsterpostingidea.domain.Idea;
import com.stackroute.ideahamsterpostingidea.exception.IdeaAlreadyExistException;
import com.stackroute.ideahamsterpostingidea.exception.IdeaNotFoundException;
import com.stackroute.ideahamsterpostingidea.repository.IdeaRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


/*
  @Service indicates annotated class is a service which hold business logic in the Service layer
 */
@Service
public class IdeaServiceImpl implements IdeaService {
    private IdeaRepository ideaRepository;
    private RabbitTemplate rabbitTemplate;

    /*
      Constructor based Dependency injection to inject IdeaRepository here
     */
    @Autowired
    public IdeaServiceImpl(IdeaRepository ideaRepository, RabbitTemplate amqpTemplate) {
        this.ideaRepository = ideaRepository;
        this.rabbitTemplate = amqpTemplate;
    }

    /*@Value annotation is found on a method, Spring context will invoke
      it when all the spring configurations and beans are getting loaded. */
    @Value("${idea.rabbitmq.exchange}")
    public String exchange;

    @Value("${idea.rabbitmq.routingkey}")
    public String routingkey;

    @Value("${ideat.rabbitmq.exchange}")
    public String teamExchange;

    @Value("${ideat.rabbitmq.routingkey}")
    public String teamRoutingkey;

    @Value("${ideah.rabbitmq.exchange}")
    public String hamsterExchange;

    @Value("${ideah.rabbitmq.routingkey}")
    public String hamsterRoutingkey;


    //deleting idea
    @Value("${ideaDelete.rabbitmq.exchange}")
    public String deleteExchange;

    @Value("${ideaDelete.rabbitmq.routingkey}")
    public String deleteRoutingKey;


    /*
      Implementation of saveTrack method
     */
    @Override
    public Idea save(Idea idea) throws IdeaAlreadyExistException {
        if (ideaRepository.findByTitle(idea.getTitle()) != null) {

            throw new IdeaAlreadyExistException("idea already exist");
        }

        Idea savedIdea = ideaRepository.save(idea);
        rabbitTemplate.convertAndSend(exchange, routingkey, idea);
        rabbitTemplate.convertAndSend(teamExchange, teamRoutingkey, idea);
        rabbitTemplate.convertAndSend(hamsterExchange, hamsterRoutingkey, idea);
        if (savedIdea == null) {
            throw new IdeaAlreadyExistException("idea is null");
        }
        return savedIdea;
    }



    /*
     * Implementation of getIdea by title method
     */
    @Override
    public Idea getIdeaByTitle(String title) throws IdeaNotFoundException {
        Idea retriveIdeaByTitle = ideaRepository.findByTitle(title);
        return retriveIdeaByTitle;
    }

    /*
     * Implementation of getIdea by location method
     */

    @Override
    public Idea getIdeaByLocation(String location) throws IdeaNotFoundException {
        Idea retriveIdeaByLocation = ideaRepository.findByLocation(location);
        return retriveIdeaByLocation;
    }

    /*
     * Implementation of getAll ideas method
     */
    @Override
    public List<Idea> getAllIdeas() throws Exception {
        List<Idea> allIdeas = ideaRepository.findAll();
        return allIdeas;
    }

    /*
     * Implementation of update idea method
     */
    @Override
    public Idea updateIdea(Idea idea) throws IdeaNotFoundException {
        /**Throw ideaNotFoundException if idea we want to update is not found*/
        if (ideaRepository.existsById(idea.getTitle())) {
            Idea getidea = ideaRepository.findByTitle(idea.getTitle());
            getidea.setTitle(idea.getTitle());
            return ideaRepository.save(getidea);
        } else {
            throw new IdeaNotFoundException("Idea you want to update is not found");
        }
    }


    /*
     * Implementation of delete idea by title method
     */
    @Override
    public Idea deleteIdeaByTitle(String title) throws IdeaNotFoundException {
        Idea deleteIdeaByTitle = ideaRepository.findByTitle(title);
        rabbitTemplate.convertAndSend(deleteExchange,deleteRoutingKey,deleteIdeaByTitle);
        ideaRepository.delete(deleteIdeaByTitle);
        return deleteIdeaByTitle;
    }


    /**
     * Implementation of get recent ideas method
     */

    @Override
    public List<Idea> getRecentIdeas() {
        List<Idea> getRecentIdea = ideaRepository.findAll(Sort.by(Sort.Direction.DESC, "postedOn"));
        return getRecentIdea;
    }


    /**
     * Implementation of get All ideas by emailId method
     */
    @Override
    public List<Idea> getPostedByIdeas(String postedBy) {
        List<Idea> getPostedIdea=ideaRepository.findByPostedBy(postedBy);
        return getPostedIdea;
    }

}
