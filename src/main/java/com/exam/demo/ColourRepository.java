package com.exam.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ColourRepository extends MongoRepository<Colour, String> {
}
