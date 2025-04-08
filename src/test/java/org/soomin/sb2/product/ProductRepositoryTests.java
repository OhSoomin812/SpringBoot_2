package org.soomin.sb2.product;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.soomin.sb2.board.dto.PageRequestDTO;
import org.soomin.sb2.board.dto.PageResponseDTO;
import org.soomin.sb2.product.dto.ProductListAllDTO;
import org.soomin.sb2.product.dto.ProductListDTO;
import org.soomin.sb2.product.dto.ProductReadDTO;
import org.soomin.sb2.product.entities.ProductEntity;
import org.soomin.sb2.product.entities.ProductReview;
import org.soomin.sb2.product.repository.ProductRepository;
import org.soomin.sb2.product.repository.ProductReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

@SpringBootTest
@Log4j2
public class ProductRepositoryTests {

    @Autowired(required = false)
    ProductRepository repository;

    @Autowired(required = false)
    ProductReviewRepository reviewRepository;

    @Test
    public void insertProduct() {

        for(int i = 0; i < 32; i++) {
            ProductEntity product = ProductEntity.builder()
                    .pname("Product " + i)
                    .price(5000)
                    .build();

            product.addImage(i+"_img0.jpg");
            product.addImage(i+"_img1.jpg");

            repository.save(product);
        }
    }

    @Test
    public void testRead1() {
        Optional<ProductEntity> result = repository.findById(1L);
        ProductEntity product = result.get();

        // tbl_product_img 테이블은 처리되지 않는다.
        log.info(product);
    }

    @Test
    public void testRead2() {
        ProductEntity product = repository.selectOne(1L);

        // tbl_product_img 테이블은 처리되지 않는다.
        log.info(product);
        log.info(product.getImages());
    }

    @Test
    public void testRead3() {
        ProductEntity product = repository.selectOne(1L);
        ProductReadDTO dto = new ProductReadDTO(product);

        log.info(product);
    }

    @Test
    public void testList1() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("pno").descending());
        Page<Object[]> result = repository.list1(pageable);

        result.forEach(arr -> log.info(Arrays.toString(arr)));
    }

//    @Test
//    public void testList2() {
//        Pageable pageable = PageRequest.of(0, 10, Sort.by("pno").descending());
//        Page<ProductListDTO> result = repository.list1DTO(pageable);
//
//        result.forEach(dto -> log.info(dto));
//    }

    @Test
    public void testListQuerydsl() {
        PageRequestDTO requestDTO = new PageRequestDTO();

        PageResponseDTO<ProductListDTO> result = repository.listQuerydsl(requestDTO);

        log.info(result);
    }

    @Test
    public void insertReviews() {
        Long[] pnos = {32L, 31L, 30L};

        for(Long pno : pnos) {
            for (int i = 0; i < 10; i++) {

                ProductReview pr = ProductReview.builder()
                        .reviewer("user00")
                        .comment("so good")
                        .score( (i % 5) +1 )
                        .product(ProductEntity.builder().pno(pno).build())
                        .build();

                reviewRepository.save(pr);
            }
        }
    }

    @Transactional
    @Test
    public void testZ1() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("pno").descending());

        Page<ProductEntity> result = repository.findAll(pageable);

        result.forEach(product -> {
            log.info("----------------------");
            log.info(product);
            log.info(product.getImages());
        });
    }

    @Test
    public void testAll() {
        PageRequestDTO requestDTO = new PageRequestDTO();

        PageResponseDTO<ProductListAllDTO> result = repository.listAllQuerydsl(requestDTO);


        result.getDtoList().forEach(log::info);
    }
}
