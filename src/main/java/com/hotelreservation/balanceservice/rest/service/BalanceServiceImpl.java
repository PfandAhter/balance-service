package com.hotelreservation.balanceservice.rest.service;


import com.hotelreservation.balanceservice.api.request.AddBalanceRequest;
import com.hotelreservation.balanceservice.api.request.BaseRequest;
import com.hotelreservation.balanceservice.api.response.BaseResponse;
import com.hotelreservation.balanceservice.api.response.GetBalanceResponse;
import com.hotelreservation.balanceservice.lib.constants.Constants;
import com.hotelreservation.balanceservice.model.entity.Balance;
import com.hotelreservation.balanceservice.model.entity.User;
import com.hotelreservation.balanceservice.repository.BalanceRepository;
import com.hotelreservation.balanceservice.repository.UserRepository;
import com.hotelreservation.balanceservice.rest.exception.AuthException;
import com.hotelreservation.balanceservice.rest.service.interfaces.IBalanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class BalanceServiceImpl implements IBalanceService {
    private final RestTemplate restTemplate;

    private final UserRepository userRepository;

    private final BalanceRepository balanceRepository;

    @Override
    public GetBalanceResponse getBalance(BaseRequest request) throws AuthException {

        if (restTemplate.postForObject("http://localhost:8082/jwt/checkTokenValid", request, Boolean.class)) {
            User tokenUser = userRepository.findByUsername(restTemplate.postForObject("http://localhost:8082/jwt/extractUsername", request, String.class));
            Balance balanceRepo = balanceRepository.findByUserId(tokenUser.getId());

            GetBalanceResponse getBalanceResponse = new GetBalanceResponse();
            getBalanceResponse.setBalance(balanceRepo.getAmount());

            return getBalanceResponse;
        } else {
            throw new AuthException(Constants.ACCESS_DENIED);
        }
    }

    @Override
    public BaseResponse addBalance(AddBalanceRequest request) throws AuthException {
        if (restTemplate.postForObject("http://localhost:8082/jwt/checkTokenValid", request, Boolean.class)) {
            User tokenUser = userRepository.findByUsername(restTemplate.postForObject("http://localhost:8082/jwt/extractUsername", request, String.class));
            Balance balanceRepo = balanceRepository.findByUserId(tokenUser.getId());

            if (balanceRepo == null) {
                Balance balance = new Balance();
                balance.setAmount(request.getAmount());
                balance.setMoneyCode("TL");
                balance.setUser(tokenUser);
                balanceRepository.save(balance);
            } else {
                balanceRepo.setAmount(balanceRepo.getAmount() + request.getAmount());
                balanceRepository.save(balanceRepo);
            }
            userRepository.save(tokenUser);
            return new BaseResponse();
        } else {
            throw new AuthException(Constants.ACCESS_DENIED);
        }
    }
}
