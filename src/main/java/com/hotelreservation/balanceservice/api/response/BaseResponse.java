package com.hotelreservation.balanceservice.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.hotelreservation.balanceservice.lib.constants.Constants;
import com.hotelreservation.balanceservice.lib.constants.ResponseStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({
        "Statu",
        "HataKodu",
        "HataMesaji"
})
public class BaseResponse {
    @JsonProperty("Statu")
    private String status = ResponseStatus.SUCCESS;

    @JsonProperty("HataKodu")
    private String errorCode = Constants.SUCCES;

    @JsonProperty("HataMesaji")
    private String errorDescription = Constants.SUCCES;
}
