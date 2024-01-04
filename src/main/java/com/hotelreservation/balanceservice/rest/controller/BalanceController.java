package com.hotelreservation.balanceservice.rest.controller;


import com.hotelreservation.balanceservice.api.request.AddBalanceRequest;
import com.hotelreservation.balanceservice.api.request.BaseRequest;
import com.hotelreservation.balanceservice.api.response.BaseResponse;
import com.hotelreservation.balanceservice.api.response.GetBalanceResponse;
import com.hotelreservation.balanceservice.rest.exception.AuthException;
import com.hotelreservation.balanceservice.rest.service.BalanceServiceImpl;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/balance")
@RequiredArgsConstructor
@Slf4j
public class BalanceController {

    private final BalanceServiceImpl balanceService;

    @PostMapping(path = "/getbalance")
    public ResponseEntity<GetBalanceResponse> getBalane(@NonNull @RequestBody BaseRequest request) throws AuthException {
        return ResponseEntity.ok(balanceService.getBalance(request));
    }

    @PostMapping(path = "/addbalance")
    public ResponseEntity<BaseResponse> addbalance(@NonNull @RequestBody AddBalanceRequest request) throws AuthException{
        return ResponseEntity.ok(balanceService.addBalance(request));
    }
}