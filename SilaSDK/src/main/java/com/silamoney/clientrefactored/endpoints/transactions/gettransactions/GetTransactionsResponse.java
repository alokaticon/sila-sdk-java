package com.silamoney.clientrefactored.endpoints.transactions.gettransactions;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.silamoney.clientrefactored.domain.Pagination;
import com.silamoney.clientrefactored.domain.Transaction;

import lombok.Getter;

@Getter
public class GetTransactionsResponse {

    private boolean success;
    private String status;
    private long page;
    @SerializedName("returned_count")
    private long returnedCount;
    @SerializedName("total_count")
    private long totalCount;
    private Pagination pagination;
    private List<Transaction> transactions;

}
