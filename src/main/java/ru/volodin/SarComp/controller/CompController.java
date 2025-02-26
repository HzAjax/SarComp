package ru.volodin.SarComp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.volodin.SarComp.entity.Comp;
import ru.volodin.SarComp.service.CompService;

import java.util.UUID;

@RestController
@RequestMapping("/sarcomp/comps")
@SuppressWarnings({"unused"})
public class CompController {

    @Autowired
    private CompService compService;

    @PostMapping
    public ResponseEntity<?> addComp(@Valid @RequestBody Comp comp){
        try{
            return ResponseEntity.ok(compService.addComp(comp));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{compId}")
    public ResponseEntity<?> getCompById(@PathVariable(value = "compId") UUID additionId){
        try {
            return ResponseEntity.ok(compService.getCompById(additionId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllComps(){
        try {
            return ResponseEntity.ok(compService.findAllComp());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editComp(@Valid @RequestBody Comp comp){
        try {
            return ResponseEntity.ok(compService.updateComp(comp));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{compId}")
    public ResponseEntity<?> deleteCompById (@PathVariable("compId") UUID additionId){
        try {
            compService.deleteCompById(additionId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
