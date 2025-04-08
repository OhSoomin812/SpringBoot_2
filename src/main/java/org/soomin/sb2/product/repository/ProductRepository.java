package org.soomin.sb2.product.repository;

import org.soomin.sb2.product.dto.ProductListDTO;
import org.soomin.sb2.product.dto.ProductReadDTO;
import org.soomin.sb2.product.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>, ProductSearch {

    @EntityGraph(attributePaths = "images", type =  EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT p FROM ProductEntity p WHERE p.pno = :pno")
    ProductEntity selectOne(@Param("pno") Long pno);

    @EntityGraph(attributePaths = "images", type =  EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT new org.soomin.sb2.product.dto.ProductReadDTO(p) FROM ProductEntity p WHERE p.pno = :pno group by p")
    ProductReadDTO selectDTO(@Param("pno") Long pno);

    //ElementCollection이 여러 개인 경우 한 개의 객체로 변환하는데 어려움이 있다.
    //Java 코드를 이용해서 변환해야만 한다.
//    @EntityGraph(attributePaths = "images", type =  EntityGraph.EntityGraphType.FETCH)
//    @Query("SELECT new org.soomin.sb2.product.dto.ProductReadDTO(p) FROM ProductEntity p WHERE p.pno = :pno")
//    ProductReadDTO selectOneDTO(@Param("pno") Long pno);

    @Query("SELECT p.pno, p.pname, p.price FROM ProductEntity p LEFT JOIN p.images pi WHERE pi.ord = 0")
    Page<Object[]> list1(Pageable pageable);

//    @Query("SELECT new org.soomin.sb2.product.dto.ProductListDTO(p.pno, p.pname, p.price, pi.imgName) " +
//            "FROM ProductEntity p LEFT JOIN p.images pi WHERE pi.ord = 0")
//    Page<ProductListDTO> list1DTO(Pageable pageable);
}
