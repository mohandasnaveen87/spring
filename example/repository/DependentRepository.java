/*
 * package com.example.repository;
 * 
 * import java.util.List;
 * 
 * import org.springframework.data.jpa.repository.Query; import
 * org.springframework.data.repository.CrudRepository; import
 * org.springframework.data.repository.query.Param; import
 * org.springframework.stereotype.Repository;
 * 
 * import com.example.demo.Dependent;
 * 
 * @Repository public interface DependentRepository extends
 * CrudRepository<Dependent,String> {
 * 
 * @Query("from Person per inner join fetch per.dependent where per.personId = :personId"
 * ) findByPersonId(@Param("personId") String personId).;
 * 
 * }
 */