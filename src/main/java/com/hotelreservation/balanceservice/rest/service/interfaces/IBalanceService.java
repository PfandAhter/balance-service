package com.hotelreservation.balanceservice.rest.service.interfaces;


import com.hotelreservation.balanceservice.api.request.AddBalanceRequest;
import com.hotelreservation.balanceservice.api.request.BaseRequest;
import com.hotelreservation.balanceservice.api.response.BaseResponse;
import com.hotelreservation.balanceservice.api.response.GetBalanceResponse;
import com.hotelreservation.balanceservice.rest.exception.AuthException;

public interface IBalanceService {
    GetBalanceResponse getBalance(BaseRequest request) throws AuthException;

    BaseResponse addBalance (AddBalanceRequest request) throws AuthException;
}
