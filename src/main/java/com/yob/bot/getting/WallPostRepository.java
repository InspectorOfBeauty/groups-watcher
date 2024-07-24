package com.yob.bot.getting;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface WallPostRepository extends JpaRepository<WallPost, Integer> {
    WallPost getByGroupUrl(String groupUrl);
}
