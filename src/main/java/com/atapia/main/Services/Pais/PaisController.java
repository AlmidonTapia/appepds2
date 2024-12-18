package com.atapia.main.Services.Pais;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atapia.main.Business.BussinesPais;
import com.atapia.main.Dto.DtoPais;
import com.atapia.main.Services.Pais.RequestObject.RequestInsert;
import com.atapia.main.Services.Pais.ResponseObject.ResponseBysearch;
import com.atapia.main.Services.Pais.ResponseObject.ResponseGetAll;

@RestController
@RequestMapping("pais")
public class PaisController {

    @Autowired
    private BussinesPais bussinesPais;

    @PostMapping(path = "/insert", consumes = "multipart/form-data")
    public ResponseEntity<String> insert(@ModelAttribute RequestInsert request) {
        try {
            DtoPais dtoPais = new DtoPais();
            dtoPais.setName(request.getName());
            bussinesPais.insert(dtoPais);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("País registrado", HttpStatus.OK);
    }

    @GetMapping(path = "getAll")
    public ResponseEntity<ResponseGetAll> getAll() {
        ResponseGetAll responseGetAll = new ResponseGetAll();

        try {
            List<DtoPais> listDtoPais = bussinesPais.getAll();

            for (DtoPais item : listDtoPais) {
                Map<String, Object> map = new HashMap<>();

                map.put("idPais", item.getIdPais());
                map.put("Name", item.getName());
                map.put("createdAt", item.getCreatedAt());

                responseGetAll.response.listPais.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(responseGetAll, HttpStatus.OK);
    }

    @DeleteMapping(path = "delete/{idPais}")
    public ResponseEntity<Boolean> delete(@PathVariable String idPais) {
        try {
            bussinesPais.delete(idPais);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(bussinesPais.delete(idPais), HttpStatus.OK);
    }
    /*
     * implementacion del EndPoint de busqueda de pais por nombre desde el
     * backend,tambien se puede hacer filtros a la tabla desde el frontend
    */
    // se cambio el response  de un Dto a un responseList porque puede haber varios  registros con el mismo nombre entonces devolvemos una lista

    @GetMapping(path = "search/{name}")
    public ResponseEntity<ResponseBysearch> getByName(@PathVariable String name) {
        ResponseBysearch responseBysearch = new ResponseBysearch();
        try {
            List<DtoPais> listDtoPais = bussinesPais.findByName(name);

            for (DtoPais item : listDtoPais) {
                Map<String, Object> map = new HashMap<>();

                map.put("idPais", item.getIdPais());
                map.put("Name", item.getName());
                map.put("createdAt", item.getCreatedAt());

                responseBysearch.response.listPais.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(responseBysearch, HttpStatus.OK);
    }
}
