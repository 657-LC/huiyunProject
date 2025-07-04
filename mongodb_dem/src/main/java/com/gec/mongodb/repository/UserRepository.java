package com.gec.mongodb.repository;

import com.gec.mongodb.model.MyUser;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository  extends MongoRepository<MyUser, ObjectId> {

}
