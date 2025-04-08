package org.soomin.sb2.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.soomin.sb2.board.dto.PageRequestDTO;
import org.soomin.sb2.board.dto.PageResponseDTO;
import org.soomin.sb2.product.dto.ProductAddDTO;
import org.soomin.sb2.product.dto.ProductListAllDTO;
import org.soomin.sb2.product.dto.ProductModifyDTO;
import org.soomin.sb2.product.dto.ProductReadDTO;
import org.soomin.sb2.product.entities.ProductEntity;
import org.soomin.sb2.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService{

    @Autowired(required = false)
    private final ProductRepository repository;

    @Override
    public Long add(ProductAddDTO addDTO) {
        ProductEntity productEntity = addDTOToEntity(addDTO);
        repository.save(productEntity);

        return productEntity.getPno();
    }

    @Override
    public ProductReadDTO read(Long pno) {

        return new ProductReadDTO(repository.selectOne(pno));
    }

    @Override
    public void modify(ProductModifyDTO modifyDTO) {
        // 상품 엔티티 조회 후에
        ProductEntity productEntity = repository.selectOne(modifyDTO.getPno());

        // 변경 내용을 반환한다
        productEntity.changePname(modifyDTO.getPname());
        productEntity.changePrice(modifyDTO.getPrice());

        //이미지 조정하고
        productEntity.clearImages();
        modifyDTO.getImageNames().forEach(imgName -> productEntity.addImage(imgName));

        //변경감지 혹은 save
    }


    @Transactional(readOnly = true)
    @Override
    public PageResponseDTO<ProductListAllDTO> listProducts(PageRequestDTO requestDTO) {
        return repository.listAllQuerydsl(requestDTO);
    }
}
