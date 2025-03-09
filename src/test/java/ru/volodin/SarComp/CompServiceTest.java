package ru.volodin.SarComp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import ru.volodin.SarComp.entity.Comp;
import ru.volodin.SarComp.repository.CompRepository;
import ru.volodin.SarComp.service.CompService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static ru.volodin.SarComp.CompServiceConstants.*;

@SpringBootTest
public class CompServiceTest {
    @Autowired
    protected CompService compService;

    @MockitoBean
    protected CompRepository mockCompRepo;

    @Test
    void findAllCompsTest(){
        List<Comp> compListTest = new ArrayList<>();
        compListTest.add(comp1);
        compListTest.add(comp2);
        compListTest.get(0).setId(compTestUUIDOne);
        compListTest.get(1).setId(compTestUUIDTwo);

        when(mockCompRepo.findAll()).thenReturn(compListTest);

        List<Comp> comps = compService.findAllComp();

        assertEquals(compListTest, comps);
    }

    @Test
    void addCompTest(){
        Comp compTest = new Comp();
        compTest.setId(compTestUUID);
        compTest.setName(name);

        when(mockCompRepo.save(compTest)).thenReturn(compTest);
        when(mockCompRepo.findByIdWithDetails(compTestUUID)).thenReturn(Optional.of(compTest));

        Comp client = compService.addComp(compTest);

        assertNotNull(client);
        assertEquals(compTestUUID, client.getId());
        assertEquals(name, client.getName());
    }

    @Test
    void getCompByIdTest(){
        Comp compTest = new Comp();
        compTest.setId(compTestUUID);

        when(mockCompRepo.findById(compTestUUID)).thenReturn(Optional.of(compTest));

        Comp client = compService.getCompById(compTestUUID);

        assertEquals(compTest, client);
    }

    @Test
    void getCompByIdEmptyTest(){
        when(mockCompRepo.findById(any())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            compService.getCompById(compTestUUID);
        });
    }

    @Test
    void editCompByIdTest(){
        Comp compTest = new Comp();
        compTest.setId(compTestUUID);
        compTest.setName(name);

        when(mockCompRepo.save(compTest)).thenReturn(compTest);

        Comp comp = compService.updateComp(compTest);

        assertNotNull(comp);
        assertEquals(compTestUUID, comp.getId());
        assertEquals(name, comp.getName());
    }

    @Test
    void deleteCompById() {
        UUID compId = UUID.randomUUID();

        compService.deleteCompById(compId);

        verify(mockCompRepo, times(1)).deleteById(compId);
    }
}
