package com.portfolio.management.portfolio.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.management.portfolio.model.UserInfo;

@Repository
public interface UserInfoRepo extends CrudRepository<UserInfo, Integer> {

}
