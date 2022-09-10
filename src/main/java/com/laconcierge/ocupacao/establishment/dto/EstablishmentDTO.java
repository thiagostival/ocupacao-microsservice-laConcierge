package com.laconcierge.ocupacao.establishment.dto;

import java.util.UUID;

import lombok.Builder;

@Builder
public class EstablishmentDTO {
    public UUID id;
    public String name;
    public Integer availableCapacity;
    public String avatar_url;
}
