package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.Bid;

public interface BidRepository extends JpaRepository<Bid, Long> {
    List<Bid> findByTaskId(Long taskId);
    List<Bid> findByFreelancerId(Long freelancerId);
    boolean existsByTaskIdAndFreelancerId(Long taskId, Long freelancerId);
}