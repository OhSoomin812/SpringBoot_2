package org.soomin.sb2.product.service;

import org.soomin.sb2.board.dto.PageRequestDTO;
import org.soomin.sb2.board.dto.PageResponseDTO;
import org.soomin.sb2.product.dto.ProductAddDTO;
import org.soomin.sb2.product.dto.ProductListAllDTO;
import org.soomin.sb2.product.dto.ProductModifyDTO;
import org.soomin.sb2.product.dto.ProductReadDTO;
import org.soomin.sb2.product.entities.ProductEntity;

public interface ProductService {

    Long add(ProductAddDTO addDTO);

    ProductReadDTO read(Long pno);

    void modify(ProductModifyDTO modifyDTO);

    PageResponseDTO<ProductListAllDTO> listProducts(PageRequestDTO requestDTO);

    default ProductEntity addDTOToEntity(ProductAddDTO dto){
        ProductEntity entity = ProductEntity.builder()
                .pname(dto.getPname())
                .price(dto.getPrice())
                .build();

        dto.getImages().forEach(imgName -> entity.addImage(imgName));

        return entity;
    }
}
