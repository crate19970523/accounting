package com.crater.accounting.controller;

import com.crater.accounting.bean.request.transactionController.AddNewTransactionRequest;
import com.crater.accounting.bean.request.transactionController.UpdateTransactionRequest;
import com.crater.accounting.bean.response.Status;
import com.crater.accounting.bean.response.transactionContoller.*;
import com.crater.accounting.bean.service.transactionService.AddTransactionDto;
import com.crater.accounting.bean.service.transactionService.GetTransactionDto;
import com.crater.accounting.bean.service.transactionService.GetTransactionResultDto;
import com.crater.accounting.bean.service.transactionService.UpdateTransactionDto;
import com.crater.accounting.exception.RequestFormatException;
import com.crater.accounting.service.TransactionService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TransactionController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private TransactionService transactionService;

    @PostMapping("transactionController/AddNew")
    public AddNewTransactionResponse addNewTransaction(@RequestBody AddNewTransactionRequest request,
                                                       @RequestHeader(name = "User-ID") String userId) {
        try {
            validateRequest(request, userId);
            var addNewTransactionDto = generateAddNewTransactionDto(request, userId);
            transactionService.addTransaction(addNewTransactionDto);
            return new AddNewTransactionResponse(Status.generateSuccessStatus());
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw e;
        }
    }

    private void validateRequest(AddNewTransactionRequest request, String userId) {
        if (request == null) {
            throw new RequestFormatException("AddNewTransactionRequest is cant be null");
        }
        var errorMessage = new ArrayList<String>();
        if (request.categorySerialNo() == null) {
            errorMessage.add("Category serial number is required");
        }
        if (request.name() == null || request.name().isEmpty()) {
            errorMessage.add("Name is required");
        }
        if (request.amount() == null) {
            errorMessage.add("Amount is required");
        }
        if (StringUtils.isBlank(userId)) {
            errorMessage.add("User ID is required");
        }
        if (!errorMessage.isEmpty()) {
            throw new RequestFormatException(String.join("\n", errorMessage));
        }
    }

    private AddTransactionDto generateAddNewTransactionDto(AddNewTransactionRequest request, String userId) {
        return new AddTransactionDto(request.categorySerialNo(), request.name(), request.amount(),
                LocalDateTime.parse(request.transactionTime(), DateTimeFormatter.ISO_LOCAL_DATE_TIME), userId);
    }

    @GetMapping("transactionController/transaction")
    public GetTransactionResponse getTransaction(@RequestHeader(name = "User-ID") String userId,
                                                 @RequestParam(value = "serialNo", required = false) Integer serialNo,
                                                 @RequestParam(value = "categorySerNo", required = false) Integer categorySerNo,
                                                 @RequestParam(value = "name", required = false) String name,
                                                 @RequestParam(value = "startDate", required = false) String startDate,
                                                 @RequestParam(value = "endDate", required = false) String endDate) {
        try {
            validateRequest(userId);
            var queryTransactionDto = generateQueryTransactionDto(serialNo, categorySerNo, name, startDate, endDate);
            var queryTransactionResultDto = transactionService.getTransaction(queryTransactionDto);
            return generateGetTransactionResponse(queryTransactionResultDto);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw e;
        }
    }

    public void validateRequest(String userId) throws RequestFormatException {
        if (StringUtils.isBlank(userId)) {
            throw new RequestFormatException("User ID is required");
        }
    }

    private GetTransactionDto generateQueryTransactionDto(Integer serialNo, Integer categorySerNo,
                                                          String name, String startDate, String endDate) {
        var startDateForDto = startDate == null ? null : LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        var endDateForDto = endDate == null ? null : LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return new GetTransactionDto(serialNo, categorySerNo, name, startDateForDto,
                endDateForDto);
    }

    private GetTransactionResponse generateGetTransactionResponse(List<GetTransactionResultDto> getTransactionResultDtos) {
        List<Transaction> transactions = getTransactionResultDtos.stream().map(g -> new Transaction(g.serNo(),
                g.consumptionCategorySerNo(), g.name(), g.amount(), g.createTime(), g.crateUser(), g.updateTime(),
                g.updateUser(), g.transactionTime())).toList();
        return new GetTransactionResponse(Status.generateSuccessStatus(), transactions);
    }

    @DeleteMapping("transactionController/transaction")
    public DeleteTransactionResponse deleteTransaction(@RequestHeader(name = "User-ID") String userId,
                                                       @RequestParam("transactionSerialNo") Integer transactionSerialNo) {
        try {
            validateRequest(userId, transactionSerialNo);
            transactionService.deleteTransaction(transactionSerialNo);
            return new DeleteTransactionResponse(Status.generateSuccessStatus());
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw e;
        }
    }

    private void validateRequest(String userId, Integer transactionSerialNo) throws RequestFormatException {
        var errorMessage = new ArrayList<String>();
        if (transactionSerialNo == null) {
            errorMessage.add("Transaction serial number is required");
        }
        if (StringUtils.isBlank(userId)) {
            errorMessage.add("User ID is required");
        }
        if (!errorMessage.isEmpty()) {
            throw new RequestFormatException(String.join("\n", errorMessage));
        }
    }

    @PutMapping("transactionController/transaction")
    public UpdateTransactionResponse updateTransaction(@RequestBody UpdateTransactionRequest request,
                                                       @RequestHeader("User-ID") String userId) {
        try {
            validateRequest(request);
            var updateTransactionDto = generateUpdateTransactionDto(request, userId);
            transactionService.updateTransaction(updateTransactionDto);
            return new UpdateTransactionResponse(Status.generateSuccessStatus());
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw e;
        }
    }

    private void validateRequest(UpdateTransactionRequest request) {
        if (request == null) {
            throw new RequestFormatException("UpdateTransactionRequest is cant be null");
        }
        var errorMessage = new ArrayList<String>();
        if (request.serialNo() == null) {
            errorMessage.add("Serial number is required");
        }
        if (!errorMessage.isEmpty()) {
            throw new RequestFormatException(String.join(", ", errorMessage));
        }
    }

    private UpdateTransactionDto generateUpdateTransactionDto(UpdateTransactionRequest request, String userId) {
        return new UpdateTransactionDto(request.serialNo(), request.categorySerialNo(), request.name(), request.amount(),
                userId, request.transactionTime());
    }

    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
}
