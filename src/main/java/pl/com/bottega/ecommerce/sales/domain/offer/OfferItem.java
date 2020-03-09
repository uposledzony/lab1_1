/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Objects;

public class OfferItem {

    // product
    private int quantity;
    private Product product;

    private String discountCause;
    private Money discount;

    public Money getTotalCost() {
        return Money.getTotalCost(discount, quantity, product.getPrice());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof OfferItem))
            return false;
        OfferItem offerItem = (OfferItem) o;
        return quantity == offerItem.quantity && Objects.equals(product, offerItem.product) && Objects.equals(discountCause,
                offerItem.discountCause) && Objects.equals(discount, offerItem.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, product, discountCause, discount);
    }

    public Money getDiscount() {
        return discount;
    }

    public String getDiscountCause() {
        return discountCause;
    }

    public int getQuantity() {
        return quantity;
    }

    public OfferItem(int quantity, Product product, String discountCause, Money discount) {
        this.quantity = quantity;
        this.product = product;
        this.discountCause = discountCause;
        this.discount = discount == null ? new Money("", new BigDecimal(0)) : discount;
    }

    public Product getProduct() {
        return product;
    }

    /**
     * @param other
     * @param delta acceptable percentage difference
     * @return
     */
    public boolean sameAs(OfferItem other, double delta) {

        if (quantity != other.quantity) {
            return false;
        }
        if (!other.getProduct().equals(product)) {
            return false;
        }

        BigDecimal max;
        BigDecimal min;
        BigDecimal totalCost = this.getTotalCost().getDenomination();
        BigDecimal otherTotalCost = other.getTotalCost().getDenomination();
        if (totalCost.compareTo(otherTotalCost) > 0) {
            max = totalCost;
            min = otherTotalCost;
        } else {
            max = otherTotalCost;
            min = totalCost;
        }

        BigDecimal difference = max.subtract(min);
        BigDecimal acceptableDelta = max.multiply(BigDecimal.valueOf(delta / 100));

        return acceptableDelta.compareTo(difference) > 0;
    }

}
