package ru.volodin.SarComp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import ru.volodin.SarComp.entity.Comp;
import ru.volodin.SarComp.entity.Lead;
import ru.volodin.SarComp.repository.CompRepository;
import ru.volodin.SarComp.repository.LeadRepository;
import ru.volodin.SarComp.service.LeadService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static ru.volodin.SarComp.LeadServiceConstants.*;

@SpringBootTest
public class LeadServiceTest {
    @Autowired
    protected LeadService leadService;

    @MockitoBean
    protected LeadRepository leadMockRepo;

    @MockitoBean
    protected CompRepository compMockRepo;

    @Test
    void findAllLeadsTest(){
        List<Lead> leadListTest = new ArrayList<>();
        leadListTest.add(lead1);
        leadListTest.add(lead2);
        leadListTest.get(0).setId(leadTestUUIDOne);
        leadListTest.get(1).setId(leadTestUUIDTwo);

        when(leadMockRepo.findAll()).thenReturn(leadListTest);

        List<Lead> leads = leadService.findAllLeads();

        assertEquals(leadListTest, leads);
    }

    @Test
    void addLeadTest(){
        Comp compTest = new Comp();
        compTest.setId(leadTestUUIDOne);
        Lead leadTest = new Lead();
        leadTest.setId(leadTestUUID);
        leadTest.setName(name);
        leadTest.setComp(compTest);

        when(leadMockRepo.save(leadTest)).thenReturn(leadTest);
        when(compMockRepo.findById(leadTestUUIDOne)).thenReturn(Optional.of(compTest));

        Lead lead = leadService.addLead(leadTest);

        assertNotNull(lead);
        assertEquals(leadTestUUID, lead.getId());
        assertEquals(leadTestUUIDOne, lead.getComp().getId());
    }

    @Test
    void getLeadByIdTest(){
        Lead leadTest = new Lead();
        leadTest.setId(leadTestUUID);

        when(leadMockRepo.findById(leadTestUUID)).thenReturn(Optional.of(leadTest));

        Lead addition = leadService.getLeadById(leadTestUUID);

        assertEquals(leadTest, addition);
    }

    @Test
    void getALeadByIdEmptyTest(){
        when(leadMockRepo.findById(any())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            leadService.getLeadById(leadTestUUID);
        });
    }

    @Test
    void editLeadByIdTest(){
        Lead leadTest = new Lead();
        leadTest.setId(leadTestUUID);
        leadTest.setName(name);

        when(leadMockRepo.save(leadTest)).thenReturn(leadTest);

        leadService.editLead(leadTest);

        assertNotNull(leadTest);
        assertEquals(name, leadTest.getName());
    }

    @Test
    void deleteLeadById_Success() {
        UUID leadID = UUID.randomUUID();

        leadService.deleteLeadById(leadID);

        verify(leadMockRepo, times(1)).deleteById(leadID);
    }
}
