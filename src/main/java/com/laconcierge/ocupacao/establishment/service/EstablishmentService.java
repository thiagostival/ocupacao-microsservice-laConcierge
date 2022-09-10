package com.laconcierge.ocupacao.establishment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.laconcierge.ocupacao.establishment.dto.EstablishmentDTO;
import com.laconcierge.ocupacao.establishment.entity.Establishment;
import com.laconcierge.ocupacao.establishment.repository.EstablishmentRepository;
import com.laconcierge.ocupacao.user.constant.UserConstants;

@Service
public class EstablishmentService {
    @Autowired
    private EstablishmentRepository establishmentRepository;

    public List<EstablishmentDTO> list() {
        List<Establishment> establishments = establishmentRepository.findAll(Sort.by("availableCapacity").descending());
        List<EstablishmentDTO> establishmentDTOS = new ArrayList<>();
        establishments.forEach(establishment -> establishmentDTOS.add(EstablishmentDTO
                .builder()
                .id(establishment.getId())
                .name(establishment.getUser().getName())
                .avatar_url(UserConstants.AVATAR_URL + establishment.getUser().getAvatar_url())
                .availableCapacity(establishment.getAvailableCapacity()).build()));
        return establishmentDTOS;
    }

    public void updateMaxCapacity(Establishment establishment) {
        establishmentRepository.updateMaxCapacity(establishment.getId(), establishment.getMaxCapacity());
    }

    public void updateBusyCapacity(Establishment establishment) {
        establishmentRepository.updateBusyCapacity(establishment.getId(), establishment.getBusyCapacity());
    }

}
