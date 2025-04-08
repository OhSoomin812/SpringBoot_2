package org.soomin.sb2.product.entities;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Embeddable
@Getter
@ToString
@Setter
public class ProductImage {

    private String imgName;

    private int ord;
}
