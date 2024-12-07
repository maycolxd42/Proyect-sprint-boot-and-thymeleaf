package com.ed.ecommerce.mvcDemo.Repository;

import com.ed.ecommerce.mvcDemo.Model.TipoDocumento;
import java.util.List;

public interface ITipoDocumento {

    List<TipoDocumento> listarTiposDocumento();

    TipoDocumento listarPorId(int id);
}