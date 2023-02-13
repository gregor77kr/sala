package net.mwav.sala.transaction.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.mwav.sala.transaction.entity.Transaction;
import net.mwav.sala.transaction.repository.TransactionRepository;

@Service
@RequiredArgsConstructor
public class TransactionService {

	private final TransactionRepository transactionRepository;

	public Transaction createTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);
	}

}
