package com.kiosk.cuss.service.baggage;

import com.kiosk.cuss.repository.BaggageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UpdateBaggageService {
    private final BaggageRepository baggageRepository;

    public void updateBaggageStatusV1(List<Long> baggageIds, String status) {
        baggageIds.stream().map(baggageRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(baggage -> {
                    baggage.setStatus(status);
                    baggageRepository.save(baggage);
                });
    }

    public void updateBaggageStatusV2(List<Long> baggageIds, String status) {
        baggageIds.parallelStream().map(baggageRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(baggage -> {
                    baggage.setStatus(status);
                    baggageRepository.save(baggage);
                });
    }

    public void updateBaggageStatusV3(List<Long> baggageIds, String status) {
        int BATCH_SIZE = 50;
        var batches = splitIntoBatches(baggageIds, BATCH_SIZE);
        List<CompletableFuture<Void>> futures = batches.stream()
                .map(batch -> CompletableFuture.runAsync(() -> processingBatch(batch, status))).toList();
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
    }

    private void processingBatch(List<Long> batch, String status) {
        System.out.println("Processing batch of size: " + batch.size() + " by thread: " + Thread.currentThread().getName());
        baggageRepository.updateStatus(batch, status);
    }

    private List<List<Long>> splitIntoBatches(List<Long> baggageIds, int batchSize) {
        int totalBaggage = baggageIds.size();
        int fullBatches = totalBaggage / batchSize;
        int remaining = totalBaggage % batchSize;

        List<List<Long>> batches = new java.util.ArrayList<>();
        for (int i = 0; i < fullBatches; i++) {
            batches.add(baggageIds.subList(i * batchSize, (i + 1) * batchSize));
        }
        if (remaining > 0) {
            batches.add(baggageIds.subList(fullBatches * batchSize, totalBaggage));
        }
        return batches;
    }
}
