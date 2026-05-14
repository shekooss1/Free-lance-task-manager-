package com.example.demo.Controller;

import com.example.demo.Model.Bid;
import com.example.demo.Service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bids")
@CrossOrigin(origins = "*")
public class BidController {

    @Autowired
    private BidService bidService;

    // Freelancer places a bid
    @PostMapping("/place")
    public ResponseEntity<?> placeBid(@RequestBody Map<String, Object> body) {
        try {
            Long taskId = Long.valueOf(body.get("taskId").toString());
            Long freelancerId = Long.valueOf(body.get("freelancerId").toString());
            Double amount = Double.valueOf(body.get("amount").toString());
            return ResponseEntity.ok(bidService.placeBid(taskId, freelancerId, amount));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Get all bids for a task (client sees these)
    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Bid>> getBidsForTask(@PathVariable Long taskId) {
        return ResponseEntity.ok(bidService.getBidsForTask(taskId));
    }

    // Get all bids by a freelancer
    @GetMapping("/freelancer/{freelancerId}")
    public ResponseEntity<List<Bid>> getBidsByFreelancer(@PathVariable Long freelancerId) {
        return ResponseEntity.ok(bidService.getBidsByFreelancer(freelancerId));
    }

    // Client accepts a bid
    @PutMapping("/{bidId}/accept")
    public ResponseEntity<?> acceptBid(@PathVariable Long bidId) {
        try {
            return ResponseEntity.ok(bidService.acceptBid(bidId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Client rejects a bid
    @PutMapping("/{bidId}/reject")
    public ResponseEntity<?> rejectBid(@PathVariable Long bidId) {
        try {
            return ResponseEntity.ok(bidService.rejectBid(bidId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}