package net.mwav.sala.transaction.repository;

import org.springframework.data.repository.CrudRepository;

import net.mwav.sala.transaction.entity.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

}
