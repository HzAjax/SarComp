package ru.volodin.SarComp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.volodin.SarComp.entity.components.Component;
import ru.volodin.SarComp.entity.enums.ComponentType;
import ru.volodin.SarComp.service.ComponentService;

import java.util.UUID;

@RestController
@RequestMapping("/sarcomp/components")
@SuppressWarnings({"unused"})
public class ComponentController {

    @Autowired
    private ComponentService componentService;

    @PostMapping
    public ResponseEntity<?> addComponent(@RequestBody Component component){
        try{
            return ResponseEntity.ok(componentService.addComponent(component));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{componentType}")
    public ResponseEntity<?> getAllComponent(@PathVariable("componentType")ComponentType componentType){
        try {
            return ResponseEntity.ok(componentService.findAll(componentType));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getComponentById(@RequestParam("componentId") UUID componentId){
        try {
            return ResponseEntity.ok(componentService.getComponentById(componentId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editComponent(@RequestBody Component component){
        try{
            return ResponseEntity.ok(componentService.addComponent(component));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{componentId}")
    public ResponseEntity<?> deleteComponentById(@PathVariable("componentId") UUID componentId){
        try {
            componentService.deleteComponentById(componentId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
