package com.crater.accounting.bean.response.account;

import com.crater.accounting.bean.response.Status;

public record LoginResponse(Status status, String token) {
}
