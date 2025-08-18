package ru.netology.money_transfer_service.repository;

import org.springframework.stereotype.Repository;
import ru.netology.money_transfer_service.transfer.MoneyTransfer;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class CardRepository {

    private final ConcurrentMap<String, TransferStatus> transferStateMap;
    private final ConcurrentMap<String, MoneyTransfer> transferList;
    private final AtomicInteger id;

    public CardRepository() {
        this.transferStateMap = new ConcurrentHashMap<>();
        this.transferList = new ConcurrentHashMap<>();
        id = new AtomicInteger(0);
    }

    public String addTransfer(MoneyTransfer transfer) {
        String transferId = id.incrementAndGet() + "";
        transferStateMap.put(transferId + "", TransferStatus.LOAD);
        transferList.put(transferId + "", transfer);
        return transferId;

    }

    public MoneyTransfer confirmOperation(String id) {
        if (!transferStateMap.containsKey(id)) {
            return null;
        }
        transferStateMap.put(id, TransferStatus.OK);
        return transferList.get(id);
    }

    public MoneyTransfer errorConfirmOperation(String id) {
        if (!transferStateMap.containsKey(id)) {
            return null;
        }
        transferStateMap.put(id, TransferStatus.ERROR);
        return transferList.get(id);
    }

    public TransferStatus getTransferState(String id) {
        return transferStateMap.get(id);
    }
}