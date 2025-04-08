package org.soomin.sb2.product.repository;

import org.soomin.sb2.board.dto.PageRequestDTO;
import org.soomin.sb2.board.dto.PageResponseDTO;
import org.soomin.sb2.product.dto.ProductListAllDTO;
import org.soomin.sb2.product.dto.ProductListDTO;

public interface ProductSearch {

    PageResponseDTO<ProductListDTO> listQuerydsl(PageRequestDTO pageRequestDTO);

    PageResponseDTO<ProductListAllDTO> listAllQuerydsl(PageRequestDTO pageRequest);
}
