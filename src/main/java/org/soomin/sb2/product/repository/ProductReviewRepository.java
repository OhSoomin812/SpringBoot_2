package org.soomin.sb2.product.repository;

import org.soomin.sb2.product.entities.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {
}
