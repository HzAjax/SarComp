package ru.volodin.SarComp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.volodin.SarComp.entity.components.*;
import ru.volodin.SarComp.entity.enums.ComponentType;
import ru.volodin.SarComp.repository.components.ComponentRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@SuppressWarnings({"unused"})
@Service
public class ComponentService {

    @Autowired
    private ComponentRepository<GraphicsCard> graphicsCardComponentRepository;
    @Autowired
    private ComponentRepository<Memory> memoryComponentRepository;
    @Autowired
    private ComponentRepository<Motherboard> motherboardComponentRepository;
    @Autowired
    private ComponentRepository<Processor> processComponentRepository;

    public List<? extends Component> findAll(ComponentType componentType){
        if (componentType == ComponentType.GC){
            return graphicsCardComponentRepository.findAllByType(componentType);
        } if (componentType == ComponentType.MEM){
            return memoryComponentRepository.findAllByType(componentType);
        } if (componentType == ComponentType.MOTHER){
            return motherboardComponentRepository.findAllByType(componentType);
        } if (componentType == ComponentType.PROC){
            return processComponentRepository.findAllByType(componentType);
        } else {
            throw new NoSuchElementException("Неизвестный тип компонента: " + componentType);
        }
    }

    public Component addComponent(Component component) {
        if (component.getType() == ComponentType.GC) {
           return graphicsCardComponentRepository.save((GraphicsCard) component);
        } if (component.getType() == ComponentType.MEM) {
            return memoryComponentRepository.save((Memory) component);
        } if (component.getType() == ComponentType.MOTHER) {
            return motherboardComponentRepository.save((Motherboard) component);
        } if (component.getType() == ComponentType.PROC) {
            return processComponentRepository.save((Processor) component);
        } else {
            throw new NoSuchElementException("Неизвестный тип компонента: " + component.getType());
        }
    }

    public Optional<? extends Component> getComponentById(UUID id) {
        if(graphicsCardComponentRepository.findById(id).isPresent()) {
            return graphicsCardComponentRepository.findById(id);
        } if (memoryComponentRepository.findById(id).isPresent()) {
            return memoryComponentRepository.findById(id);
        } if (motherboardComponentRepository.findById(id).isPresent()) {
            return motherboardComponentRepository.findById(id);
        } if (processComponentRepository.findById(id).isPresent()) {
            return processComponentRepository.findById(id);
        } else {
            throw new NoSuchElementException("Компонент не был найден!");
        }
    }

    public Component updateComponent (Component component) {
        if (component.getType() == ComponentType.GC) {
            return graphicsCardComponentRepository.save((GraphicsCard) component);
        } if (component.getType() == ComponentType.MEM) {
            return memoryComponentRepository.save((Memory) component);
        } if (component.getType() == ComponentType.MOTHER) {
            return motherboardComponentRepository.save((Motherboard) component);
        } if (component.getType() == ComponentType.PROC) {
            return processComponentRepository.save((Processor) component);
        } else {
            throw new NoSuchElementException("Неизвестный тип компонента: " + component.getType());
        }
    }

    public void deleteComponentById(UUID id) {
        if(graphicsCardComponentRepository.findById(id).isPresent()) {
            graphicsCardComponentRepository.deleteById(id);
        } if (memoryComponentRepository.findById(id).isPresent()) {
            memoryComponentRepository.deleteById(id);
        } if (motherboardComponentRepository.findById(id).isPresent()) {
            motherboardComponentRepository.deleteById(id);
        } if (processComponentRepository.findById(id).isPresent()) {
            processComponentRepository.deleteById(id);
        }
    }
}
