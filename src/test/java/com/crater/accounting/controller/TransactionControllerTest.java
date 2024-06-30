package com.crater.accounting.controller;

import com.crater.accounting.bean.request.transactionController.AddNewTransactionRequest;
import com.crater.accounting.bean.request.transactionController.UpdateTransactionRequest;
import com.crater.accounting.bean.response.transactionContoller.GetTransactionResponse;
import com.crater.accounting.bean.service.transactionService.AddTransactionDto;
import com.crater.accounting.bean.service.transactionService.GetTransactionResultDto;
import com.crater.accounting.bean.service.transactionService.UpdateTransactionDto;
import com.crater.accounting.exception.RequestFormatException;
import com.crater.accounting.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransactionControllerTest {
    private TransactionController testTarget;
    private TransactionService transactionService;

    @BeforeEach
    public void beforeEach() {
        transactionService = Mockito.mock(TransactionService.class);
        testTarget = new TransactionController();
        testTarget.setTransactionService(transactionService);
    }

    @Test
    public void addNewTransaction_allInputNull_throwRequestFormatException() {
        var exception = assertThrows(RequestFormatException.class, () -> testTarget.addNewTransaction(null, null));
        assertEquals("AddNewTransactionRequest is cant be null", exception.getMessage());
    }

    @Test
    public void addNewTransaction_requestAllNull_throwRequestFormatException() {
        var exception = assertThrows(RequestFormatException.class, () ->
                testTarget.addNewTransaction(new AddNewTransactionRequest(null, null, null,
                        null), null));

        assertEquals("""
                Category serial number is required
                Name is required
                Amount is required""", exception.getMessage());
    }

    @Test
    public void addNewTransaction_success() {
        AddNewTransactionRequest request = new AddNewTransactionRequest(1,
                "Transaction 1", new BigDecimal("100.00"), "2022-01-01T11:11:11");
        Mockito.doNothing().doAnswer(o -> {
            var dto = o.getArgument(0, AddTransactionDto.class);
            assertEquals(1, dto.categorySerialNo());
            assertEquals("Transaction 1", dto.name());
            assertEquals(new BigDecimal("100.00"), dto.amount());
            assertEquals("user123", dto.authorization());
            return null;
        }).when(transactionService).addTransaction(Mockito.any());
        testTarget.addNewTransaction(request, "user123");
    }

    @Test
    public void getTransaction_success() {
        List<GetTransactionResultDto> expectedTransactions = List.of(
                new GetTransactionResultDto(1, 1, "Transaction 1", new BigDecimal("100.00"),
                        LocalDateTime.of(2022, 1, 1, 0, 0), "user123",
                        LocalDateTime.of(2022, 1, 1, 0, 0), "user123",
                        LocalDateTime.of(2022, 1, 1, 0, 0))
        );
        Mockito.when(transactionService.getTransaction(Mockito.any())).thenReturn(expectedTransactions);

        GetTransactionResponse response = testTarget.getTransaction("user123", null, null,
                null, null, null);
        assertEquals(1, response.transactions().size());
        var firstTransaction = response.transactions().getFirst();
        assertEquals(1, firstTransaction.serialNo());
        assertEquals(1, firstTransaction.categorySerialNo());
        assertEquals("Transaction 1", firstTransaction.name());
        assertEquals(new BigDecimal("100.00"), firstTransaction.amount());
        assertEquals("user123", firstTransaction.createUserId());
        assertEquals("user123", firstTransaction.updateUserId());
    }

    @Test
    public void deleteTransaction_allInputNull_throwRequestFormatException() {
        var exception = assertThrows(RequestFormatException.class, () ->
                testTarget.deleteTransaction(null, null));
        assertEquals("""
                Transaction serial number is required""", exception.getMessage());
    }

    @Test
    public void deleteTransaction_success() {
        Mockito.doNothing().doAnswer(o -> {
            var transactionSerialNo = o.getArgument(0, Integer.class);
            assertEquals(1, transactionSerialNo);
            return null;
        }).when(transactionService).deleteTransaction(Mockito.anyInt());
        testTarget.deleteTransaction("user123", 1);
    }

    @Test
    public void updateTransaction_allInputNull_throwRequestFormatException() {
        var exception = assertThrows(RequestFormatException.class, () -> testTarget.updateTransaction(null, null));
        assertEquals("UpdateTransactionRequest is cant be null", exception.getMessage());
    }

    @Test
    public void updateTransaction_requestAllNull_throwRequestFormatException() {
        var exception = assertThrows(RequestFormatException.class, () ->
                testTarget.updateTransaction(new UpdateTransactionRequest(null, null, null, null, null), null));
        assertEquals("""
                Serial number is required""", exception.getMessage());
    }

    @Test
    public void updateTransaction_success() {
        UpdateTransactionRequest request = new UpdateTransactionRequest(1, 1, "Transaction 1",
                new BigDecimal("200.00"), LocalDateTime.of(2022, 1, 1, 0, 0));
        Mockito.doNothing().doAnswer(o -> {
            var dto = o.getArgument(0, UpdateTransactionDto.class);
            assertEquals(1, dto.serialNo());
            assertEquals(1, dto.categorySerialNo());
            assertEquals("Transaction 1", dto.name());
            assertEquals(new BigDecimal("200.00"), dto.amount());
            assertEquals("user123", dto.authorization());
            assertEquals(LocalDateTime.of(2022, 1, 1, 0, 0), dto.transactionTime());
            return null;
        }).when(transactionService).updateTransaction(Mockito.any());
        testTarget.updateTransaction(request, "user123");
    }
}
