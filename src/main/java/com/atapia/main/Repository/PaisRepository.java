package com.atapia.main.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atapia.main.entity.Tpais;

@Repository
public interface PaisRepository extends JpaRepository<Tpais, String> {

    List<Tpais> findByName(String name);

}
