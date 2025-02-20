package ru.volodin.SarComp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.volodin.SarComp.entity.Addition;
import ru.volodin.SarComp.entity.components.Motherboard;
import ru.volodin.SarComp.repository.AdditionRepository;
import ru.volodin.SarComp.repository.components.ComponentRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@SuppressWarnings({"unused"})
@Service
public class AdditionService {

    @Autowired
    private AdditionRepository additionRepository;

    public List<Addition> findAllAddition(){
        return additionRepository.findAll();
    }

    public Addition addAddition(Addition addition){
        return additionRepository.save(addition);
    }

    public Addition getAdditionById(UUID id){
        return additionRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Услуга не была найдена!"));
    }

    public Addition updateAddition(Addition addition){
        return additionRepository.save(addition);
    }

    public void deleteAdditionById(UUID id){
        additionRepository.deleteById(id);
    }
}
