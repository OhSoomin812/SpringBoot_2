package org.soomin.sb2.product.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductListAllDTO {

    private Long pno;
    private String pname;
    private int price;
    private List<String> imgName;

    private double avgRating;
    private long reviewCnt;
}
