package ru.volodin.SarComp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.volodin.SarComp.entity.components.*;
import ru.volodin.SarComp.entity.enums.ComponentType;
import ru.volodin.SarComp.repository.components.ComponentRepository;

import java.util.List;
import java.util.NoSuchElementException;

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
}
