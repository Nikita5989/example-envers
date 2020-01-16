/*
 * MIT License
 *
 * Copyright (c) 2017 JUAN CALVOPINA M
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.jcalvopinam.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jcalvopinam.dto.OrderDetailDTO;
import lombok.Data;
import lombok.ToString;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * The persistent class for the env_order_details database table.
 *
 * @author juan.calvopina
 */
@Entity
@Audited
@Table(name = "env_order_detail")
@Data
@ToString
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1183975993716362588L;

    @EmbeddedId
    private OrderDetailPK id;

    @Column(name = "discount")
    private double discount;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "unit_price")
    private double unitPrice;

    /**
     * bi-directional many-to-one association to Order entity
     * JoinColumn name must have the same name as the Order entity Id, as well as the same
     * name as defined in the column name of OrderDetailPK entity, because this is part of composite pk
     * check the name of the column annotation @Column(name = "order_id") in Order entity
     */
    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    @JsonIgnore
    private Order order;

    /**
     * bi-directional many-to-one association to Product
     * JoinColumn name must have the same name as the Product entity Id, as well as the same
     * name as defined in the column name of OrderDetailPK entity, because this is part of composite pk
     * check the name of the column annotation @Column(name = "product_id") in Product entity
     */
    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    @JsonIgnore
    private Product product;

    public OrderDetail() {
    }

    public OrderDetail(final OrderDetailDTO orderDetailDTO) {
        this.id = orderDetailDTO.getId();
        this.quantity = orderDetailDTO.getQuantity();
        this.discount = orderDetailDTO.getDiscount();
        this.unitPrice = orderDetailDTO.getUnitPrice();
    }

}