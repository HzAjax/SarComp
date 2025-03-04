package ru.volodin.SarComp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.volodin.SarComp.entity.Comp;
import ru.volodin.SarComp.repository.CompRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@SuppressWarnings({"unused"})
public class CompService {

    @Autowired
    private CompRepository compRepository;

    public List<Comp> findAllComp(){
        return compRepository.findAll();
    }

    public Comp addComp(Comp comp){
        Comp savedComp = compRepository.save(comp);
        return compRepository.findByIdWithDetails(savedComp.getId()).orElseThrow(() ->
                new NoSuchElementException("Услуга не была найдена!"));
    }

    public Comp getCompById(UUID id){
        return compRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Услуга не была найдена!"));
    }

    public Comp updateComp(Comp comp){
        return compRepository.save(comp);
    }

    public void deleteCompById(UUID id){
        compRepository.deleteById(id);
    }
}
