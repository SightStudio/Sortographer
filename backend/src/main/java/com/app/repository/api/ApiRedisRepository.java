package com.app.repository.api;

import com.app.model.redisHash.ApiCall;
import org.springframework.data.repository.CrudRepository;

public interface ApiRedisRepository extends CrudRepository<ApiCall, String> { }
