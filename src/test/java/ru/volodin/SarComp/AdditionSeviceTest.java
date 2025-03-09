package ru.volodin.SarComp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import ru.volodin.SarComp.entity.Addition;
import ru.volodin.SarComp.repository.AdditionRepository;
import ru.volodin.SarComp.service.AdditionService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static ru.volodin.SarComp.AdditionalServiceConstants.*;

@SpringBootTest
public class AdditionSeviceTest {
    @Autowired
    protected AdditionService additionService;

    @MockitoBean
    protected AdditionRepository mockAdditRepo;

    @Test
    void findAllAdditionsTest(){
        List<Addition> additionListTest = new ArrayList<>();
        additionListTest.add(addit1);
        additionListTest.add(addit2);
        additionListTest.get(0).setId(additTestUUIDOne);
        additionListTest.get(1).setId(additTestUUIDTwo);

        when(mockAdditRepo.findAll()).thenReturn(additionListTest);

        List<Addition> additions = additionService.findAllAddition();

        assertEquals(additionListTest, additions);
    }

    @Test
    void addAdditionTest(){
        Addition additionTest = new Addition();
        additionTest.setId(additTestUUID);
        additionTest.setName(name);

        when(mockAdditRepo.save(additionTest)).thenReturn(additionTest);

        Addition addition = additionService.addAddition(additionTest);

        assertNotNull(addition);
        assertEquals(additTestUUID, addition.getId());
    }

    @Test
    void getAdditionByIdTest(){
        Addition additionTest = new Addition();
        additionTest.setId(additTestUUID);

        when(mockAdditRepo.findById(additTestUUID)).thenReturn(Optional.of(additionTest));

        Addition addition = additionService.getAdditionById(additTestUUID);

        assertEquals(additionTest, addition);
    }

    @Test
    void getAdditionByIdEmptyTest(){
        when(mockAdditRepo.findById(any())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            additionService.getAdditionById(additTestUUID);
        });
    }

    @Test
    void editAdditionByIdTest(){
        Addition additionTest = new Addition();
        additionTest.setId(additTestUUID);
        additionTest.setName(name);

        when(mockAdditRepo.save(additionTest)).thenReturn(additionTest);

        additionService.updateAddition(additionTest);

        assertNotNull(additionTest);
        assertEquals(name, additionTest.getName());
    }

    @Test
    void deleteAdditionById_Success() {
        UUID additionId = UUID.randomUUID();

        additionService.deleteAdditionById(additionId);

        verify(mockAdditRepo, times(1)).deleteById(additionId);
    }
}
