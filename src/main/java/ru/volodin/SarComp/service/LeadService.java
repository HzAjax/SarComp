package ru.volodin.SarComp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.volodin.SarComp.entity.Comp;
import ru.volodin.SarComp.entity.Lead;
import ru.volodin.SarComp.repository.CompRepository;
import ru.volodin.SarComp.repository.LeadRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@SuppressWarnings({"unused"})
public class LeadService {
    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private CompRepository compRepository;

    public List<Lead> findAllLeads() {
        return leadRepository.findAll();
    }

    public Lead addLead(Lead lead) {
        Comp compFromDb = compRepository.findById(lead.getComp().getId()).orElseThrow(() ->
                new NoSuchElementException("ПК не был найден в БД!"));
        lead.setComp(compFromDb);
        return leadRepository.save(lead);
    }

    public Lead getLeadById(UUID id) {
        return leadRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Лид не был найден в БД!"));
    }

    public Lead editLead(Lead lead) {
        return leadRepository.save(lead);
    }

    public void deleteLeadById(UUID id) {
        leadRepository.deleteById(id);
    }
}
