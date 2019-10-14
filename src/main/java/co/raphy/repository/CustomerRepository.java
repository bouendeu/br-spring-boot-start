package co.raphy.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import co.raphy.entity.Customer;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer,Integer> {
 
}
