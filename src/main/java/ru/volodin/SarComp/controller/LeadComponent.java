package ru.volodin.SarComp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.volodin.SarComp.entity.Lead;
import ru.volodin.SarComp.service.LeadService;

import java.util.UUID;

@RestController
@RequestMapping("/sarcomp/leads")
@SuppressWarnings({"unused"})
public class LeadComponent {

    @Autowired
    private LeadService leadService;

    @PostMapping
    public ResponseEntity<?> addLead(@Valid @RequestBody Lead lead) {
        try {
            return ResponseEntity.ok(leadService.addLead(lead));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{leadId}")
    public ResponseEntity<?> getLead(@PathVariable(value = "leadId") UUID leadId) {
        try {
            return ResponseEntity.ok(leadService.getLeadById(leadId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllLeads() {
        try {
            return ResponseEntity.ok(leadService.findAllLeads());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editLead(@Valid @RequestBody Lead lead) {
        try {
            return ResponseEntity.ok(leadService.editLead(lead));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{leadId}")
    public ResponseEntity<?> deleteLeadById(@PathVariable("leadId") UUID leadId) {
        try {
            leadService.deleteLeadById(leadId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
