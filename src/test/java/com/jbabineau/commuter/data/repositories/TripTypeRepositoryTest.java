package com.jbabineau.commuter.data.repositories;

import com.jbabineau.commuter.data.entities.TripType;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.not;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TripTypeRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TripTypeRepository tripTypeRepository;

    @Test
    public void should_find_no_tripTypes_if_repository_is_empty() {

        Iterable<TripType> tripType = tripTypeRepository.findAll();

        assertThat((Collection<TripType>) tripType, is(empty()));
    }

    @Test
    public void should_store_a_TripType() throws Exception {

        TripType expectedTripType = new TripType("Sample", "This is a sample");
        TripType actualTripType = tripTypeRepository.save(expectedTripType);

        assertThat(actualTripType.getName(), is(expectedTripType.getName()));
        assertThat(actualTripType.getDescription(), is(expectedTripType.getDescription()));

    }

    @Test
    public void should_find_a_tripType_when_name_matches() throws Exception {

        TripType expected = new TripType("Test One", "this is a test");
        TripType dummyRow1 = new TripType("Dummy1", " this is a dummy row");
        TripType dummyRow2 = new TripType("Test one", " this is a dummy row");
        TripType dummyRow3 = new TripType("test one", " this is a dummy row");

        entityManager.persist(expected);
        entityManager.persist(dummyRow1);
        entityManager.persist(dummyRow2);
        entityManager.persist(dummyRow3);
        entityManager.flush();

        TripType actual = tripTypeRepository.findByName("Test One");

        assertThat(actual.getName(), is(expected.getName()));
        assertThat(actual.getDescription(), is(expected.getDescription()));
    }

    @Test
    public void should_find_a_tripType_when_name_is_similar() throws Exception {

        TripType expected = new TripType("Test One", "this is a test");
        TripType dummyRow1 = new TripType("Dummy1", " this is a dummy row");
        TripType dummyRow2 = new TripType("Test one", " this is a dummy row");
        TripType dummyRow3 = new TripType("test one", " this is a dummy row");

        entityManager.persist(expected);
        entityManager.persist(dummyRow1);
        entityManager.persist(dummyRow2);
        entityManager.persist(dummyRow3);
        entityManager.flush();

        List<TripType> actual = (List<TripType>) tripTypeRepository.findAllByNameLike("Test");

        assertThat(actual.size(), is(2));
        assertThat(actual, hasItem(expected));
        assertThat(actual, hasItem(dummyRow2));
    }

}