package com.acme.myshoes.platform.shoes;


import com.acme.myshoes.platform.shoes.domain.model.Collection;
import com.acme.myshoes.platform.shoes.domain.persistence.CollectionRepository;
import com.acme.myshoes.platform.shoes.domain.service.CollectionService;
import com.acme.myshoes.platform.shoes.mapping.CollectionMapper;
import com.acme.myshoes.platform.shoes.service.CollectionServiceImpl;
import net.bytebuddy.matcher.CollectionElementMatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CollectionTests {
    @InjectMocks
    private CollectionServiceImpl collectionService;

    @Mock
    private CollectionMapper collectionMapper;

    @Mock
    private CollectionRepository collectionRepository;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void GetAllCollection() {
        Long userId = 1L;
        List<Collection> expected = Arrays.asList(new Collection(), new Collection());
        when(collectionRepository.findAllByUser(userId)).thenReturn(expected);

        List<Collection> actual = collectionService.getByUser(userId);

        assertEquals(expected.size(), actual.size());
    }

    @Test
    public void TestGetCollectionById() {
        Long collectionId = 1L;
        Collection expected = new Collection();
        when(collectionRepository.findById(collectionId)).thenReturn(Optional.of(expected));

        Collection actual = collectionService.getById(collectionId);

        assertEquals(expected, actual);
    }

}