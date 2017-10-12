package com.jbabineau.commuter.data.repositories;

import com.jbabineau.commuter.data.entities.TripType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TripTypeRepository extends CrudRepository<TripType, Long>{
    @Query("SELECT t FROM TripType t WHERE t.name = :name")
    TripType findByName(@Param("name") String name);

    @Query("SELECT t FROM TripType t WHERE t.name LIKE CONCAT('%',:name,'%')")
    Iterable<TripType> findAllByNameLike(@Param("name")String name);
}
