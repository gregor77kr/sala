package net.mwav.sala.subscription.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.mwav.sala.subscription.entity.SubscriptionTransaction;
import net.mwav.sala.subscription.repository.SubscriptionTransactionRepository;

@Service
@RequiredArgsConstructor
public class SubscriptionTransactionService {

	private final SubscriptionTransactionRepository subscriptionTransactionRepository;

	public SubscriptionTransaction createTransaction(SubscriptionTransaction subscriptionTransaction) {
		return subscriptionTransactionRepository.save(subscriptionTransaction);
	}

}
