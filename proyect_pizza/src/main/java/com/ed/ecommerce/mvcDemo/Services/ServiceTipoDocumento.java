package com.ed.ecommerce.mvcDemo.Services;

import com.ed.ecommerce.mvcDemo.Model.TipoDocumento;
import com.ed.ecommerce.mvcDemo.Repository.ITipoDocumento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTipoDocumento {

    @Autowired
    private ITipoDocumento iTipoDocumentoRepository;

    // Listar todos los tipos de documentos
    public List<TipoDocumento> listarTiposDocumento() {
        return iTipoDocumentoRepository.listarTiposDocumento();
    }

    // Listar tipo de documento por ID
    public TipoDocumento listarPorId(int idTipoDocumento) {
        return iTipoDocumentoRepository.listarPorId(idTipoDocumento);
    }

}
