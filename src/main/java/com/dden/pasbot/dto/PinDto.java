package com.dden.pasbot.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public record PinDto(String pin, PinState pinState) {

}
