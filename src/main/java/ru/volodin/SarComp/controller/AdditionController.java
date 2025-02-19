package ru.volodin.SarComp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.volodin.SarComp.entity.Addition;
import ru.volodin.SarComp.service.AdditionService;

import java.util.UUID;

@RestController
@RequestMapping("/sarcomp/addition")
@SuppressWarnings({"unused"})
public class AdditionController {

    @Autowired
    private AdditionService additionService;

    @PostMapping
    public ResponseEntity<?> addAddition(@Valid @RequestBody Addition addition){
        try{
           return ResponseEntity.ok(additionService.addAddition(addition));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{additionId}")
    public ResponseEntity<?> getAdditionById(@PathVariable(value = "additionId") UUID additionId){
        try {
            return ResponseEntity.ok(additionService.getAdditionById(additionId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllAddition(){
        try {
            return ResponseEntity.ok(additionService.findAllAddition());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editAddition(@Valid @RequestBody Addition addition){
        try {
            return ResponseEntity.ok(additionService.updateAddition(addition));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{additionId}")
    public ResponseEntity<?> deleteAdditionById (@PathVariable("additionId") UUID additionId){
        try {
            additionService.deleteAdditionById(additionId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
