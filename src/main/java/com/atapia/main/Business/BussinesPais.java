package com.atapia.main.Business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atapia.main.Dto.DtoPais;
import com.atapia.main.Repository.PaisRepository;
import com.atapia.main.entity.Tpais;

import jakarta.transaction.Transactional;
@Service
public class BussinesPais {
    @Autowired
    private PaisRepository paisRepository;

    @Transactional
    public void insert(DtoPais dtoPais) {
        dtoPais.setIdPais(UUID.randomUUID().toString());
        dtoPais.setCreatedAt(new Date());

        Tpais tPais = new Tpais();

        tPais.setIdPais(dtoPais.getIdPais());
        tPais.setName(dtoPais.getName());
        tPais.setCreatedAt(dtoPais.getCreatedAt());

        paisRepository.save(tPais);
    }

    public List<DtoPais> getAll() {
        List<Tpais> listTPais = paisRepository.findAll();

        List<DtoPais> listDtoPais = new ArrayList<>();

        for (Tpais item : listTPais) {
            DtoPais dtoPais = new DtoPais();

            dtoPais.setIdPais(item.getIdPais());
            dtoPais.setName(item.getName());
            dtoPais.setCreatedAt(item.getCreatedAt());

            listDtoPais.add(dtoPais);
        }

        return listDtoPais;
        }

        @Transactional
        public Boolean delete(String idPais) {
            Optional<Tpais> tPais = paisRepository.findById(idPais);
            if (tPais.isPresent()) {
                paisRepository.deleteById(idPais);
                return true;
            }
            return false;
        }

        
        public List<DtoPais> findByName(String name) {

            List<Tpais> listTPais = paisRepository.findByName(name);
            List<DtoPais> listDtoPais = new ArrayList<>();

            for (Tpais item : listTPais) {
                DtoPais dtoPais = new DtoPais();

                dtoPais.setIdPais(item.getIdPais());
                dtoPais.setName(item.getName());
                dtoPais.setCreatedAt(item.getCreatedAt());

                listDtoPais.add(dtoPais);
            }

            return listDtoPais;
        }
}
