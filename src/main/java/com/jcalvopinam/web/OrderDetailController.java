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

package com.jcalvopinam.web;

import com.jcalvopinam.domain.OrderDetail;
import com.jcalvopinam.dto.OrderDetailDTO;
import com.jcalvopinam.service.OrderDetailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author juan.calvopina
 */
@RestController
@RequestMapping(value = "/order-detail")
@Slf4j
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailController(final OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping
    public List<OrderDetail> findAllDetails() {
        log.info("Find all order details");
        return orderDetailService.findAll();
    }

    @GetMapping("/{productId}/{orderId}")
    public OrderDetail findByDetailPk(@PathVariable String productId, @PathVariable String orderId) {
        log.info(String.format("Finding by: %s, %s", productId, orderId));
        return orderDetailService.findByDetailPk(productId, orderId);
    }

    @PostMapping
    public String saveDetail(@RequestBody OrderDetailDTO orderDetailDTO) {
        Validate.notNull(orderDetailDTO, "The order detail cannot be null");
        log.info(String.format("Saving detail: %s", orderDetailDTO.toString()));
        return orderDetailService.save(orderDetailDTO);
    }

    @PutMapping("/{id}")
    public String updateDetail(@RequestBody OrderDetailDTO orderDetailDTO, @PathVariable int id) {
        Validate.notNull(orderDetailDTO, "The order detail cannot be null");
        log.info(String.format("Updating order detail: %s", orderDetailDTO.toString()));
        return orderDetailService.update(orderDetailDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteDetail(@PathVariable int id) {
        log.info(String.format("Deleting order detail: %s", id));
        orderDetailService.deleteById(id);
    }

}
