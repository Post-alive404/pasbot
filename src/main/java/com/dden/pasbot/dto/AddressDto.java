package com.dden.pasbot.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public record AddressDto(Integer id, String address, String name) {

    @Override
    public String toString() {
        return name == null ? address : address + " - " + name;
    }
}
