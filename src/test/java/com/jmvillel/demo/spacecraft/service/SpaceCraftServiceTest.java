package com.jmvillel.demo.spacecraft.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.jmvillel.demo.spacecraft.domain.SpaceCraft;
import com.jmvillel.demo.spacecraft.repository.SpaceCraftRepository;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
public class SpaceCraftServiceTest {
	
	@Mock
	List<SpaceCraft> spacecraftsFiltered;
	
	@Mock
	Page<SpaceCraft> spacecrafts;
	
	@Mock
	SpaceCraftRepository repository;
	
	@InjectMocks
	SpaceCraftService service;

	
	@Test
    @DisplayName("FindAllPaginated method should return a page of spacecrafts")
    void findAllPaginated_shouldReturnAPageOfSpacecrafts() {

        when(repository.findAll(any(PageRequest.class))).thenReturn(spacecrafts);

        service.findAllPaginated(PageRequest.of(0, 5, Sort.Direction.ASC, "name"));

        verify(repository).findAll(any(PageRequest.class));
    }
	
	@Test
    @DisplayName("FindOneById method should return a spacecraft if exists")
    void findOneById_shouldReturnASpaceCraftIfExists() {
		
		SpaceCraft sc = new SpaceCraft(1L, "X-Wing");
		
		when(repository.existsById(sc.getId())).thenReturn(true);
        when(repository.findById(sc.getId())).thenReturn(Optional.of(sc));

        SpaceCraft scExpected = service.findOneById(1L);

		assertThat(scExpected).isSameAs(sc);

        verify(repository).findById(sc.getId());	
    }
	
	@Test
	@DisplayName("FindAllByName method should return a list of spacecrafts filter by name")
	void findAllByName_shouldReturnAListOfSpaceCraftsFilterByName() {
		
		when(repository.findAllByNameContainingIgnoreCase(any(String.class))).thenReturn(spacecraftsFiltered);
		
		service.findAllByName("wing");
		
		verify(repository).findAllByNameContainingIgnoreCase(any(String.class));
	}
	
	@Test
	@DisplayName("Create method should return a new spacecraft if not exist")
	void create_shouldCreateASpacecraftIfNotExist() {
		
		SpaceCraft sc = new SpaceCraft();
		sc.setName("New SpaceCraft");
		when(repository.save(sc)).thenReturn(sc);
		SpaceCraft scCreated = service.create(sc);
		
		assertThat(sc.getName()).isSameAs(scCreated.getName());
		verify(repository).save(sc);
		
	}
	
	@Test
	@DisplayName("Update method should update a spacecraft if exist")
	void update_shouldUpdateASpacecraftIfExist() {
		
		SpaceCraft sc = new SpaceCraft();
		sc.setId(1L);
		sc.setName("New name");
		
		when(repository.existsById(sc.getId())).thenReturn(true);
		when(repository.save(sc)).thenReturn(sc);
		
		SpaceCraft scUpdated = service.update(sc);
		
		assertThat(sc.getName()).isSameAs(scUpdated.getName());
		verify(repository).save(sc);
		
	}
	
	@Test
	@DisplayName("Delete method should delete a spacecraft if exist")
	void delete_shouldDeleteASpacecraftIfExist() {
		
		when(repository.existsById(1L)).thenReturn(true);
		
		Long idExpected = service.delete(1L);
		
		assertThat(idExpected).isSameAs(1L);

        verify(repository).deleteById(1L);
		
		
	}
	
	@Test
	@DisplayName("Delete method should throw an EntityNotFoundException if spacecraft not exist")
	void delete_shouldThrowAnEntityNotFoundExceptionIfNotExist() {
		
        when(repository.existsById(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class,
                ()->{service.delete(1L);});
		
	}
	
	
}
