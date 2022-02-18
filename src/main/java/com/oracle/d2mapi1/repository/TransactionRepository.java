package com.oracle.d2mapi1.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oracle.d2mapi1.model.Transaction;


@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long>{

}