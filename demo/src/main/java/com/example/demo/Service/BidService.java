package com.example.demo.Service;

import com.example.demo.Model.*;
import com.example.demo.Repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidService {

    @Autowired private BidRepository bidRepository;
    @Autowired private TaskService taskService;
    @Autowired private UserService userService;

    public Bid placeBid(Long taskId, Long freelancerId, Double amount) {
        Task task = taskService.getTaskById(taskId);

        if (task.getStatus() != TaskStatus.OPEN) {
            throw new RuntimeException("Task is not open for bidding.");
        }
        if (bidRepository.existsByTaskIdAndFreelancerId(taskId, freelancerId)) {
            throw new RuntimeException("You have already placed a bid on this task.");
        }

        User freelancer = userService.getUserById(freelancerId);

        Bid bid = new Bid();
        bid.setTask(task);
        bid.setFreelancer(freelancer);
        bid.setAmount(amount);
        return bidRepository.save(bid);
    }

    public List<Bid> getBidsForTask(Long taskId) {
        return bidRepository.findByTaskId(taskId);
    }

    public List<Bid> getBidsByFreelancer(Long freelancerId) {
        return bidRepository.findByFreelancerId(freelancerId);
    }

    public Bid acceptBid(Long bidId) {
        Bid bid = bidRepository.findById(bidId)
            .orElseThrow(() -> new RuntimeException("Bid not found"));

        // Reject all other bids for same task
        bidRepository.findByTaskId(bid.getTask().getId())
            .forEach(b -> {
                if (!b.getId().equals(bidId)) {
                    b.setStatus(BidStatus.REJECTED);
                    bidRepository.save(b);
                }
            });

        bid.setStatus(BidStatus.ACCEPTED);

        // Assign freelancer and move task to IN_PROGRESS
        Task task = bid.getTask();
        task.setFreelancer(bid.getFreelancer());
        task.setStatus(TaskStatus.IN_PROGRESS);
        taskService.saveTask(task);

        return bidRepository.save(bid);
    }

    public Bid rejectBid(Long bidId) {
        Bid bid = bidRepository.findById(bidId)
            .orElseThrow(() -> new RuntimeException("Bid not found"));
        bid.setStatus(BidStatus.REJECTED);
        return bidRepository.save(bid);
    }
}