package com.company.U1M5ChallengeDowneyStephen.daos;

import com.company.U1M5ChallengeDowneyStephen.dtos.Publisher;

import java.util.List;

public interface PublisherDao {

    Publisher addPublisher(Publisher publisher);
    Publisher getPublisher(int id);
    List<Publisher> getAllPublishers();
    void updatePublisher(Publisher publisher);
    void deletePublisher(int id);

}
