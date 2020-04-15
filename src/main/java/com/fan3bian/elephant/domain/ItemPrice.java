package com.fan3bian.elephant.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemPrice {

    private static final int scale = 6;
    private static final int roundingMode = BigDecimal.ROUND_HALF_EVEN;

    /**
     * 税率
     */
    private BigDecimal taxRate;
    /**
     * 单位换算率
     */
    private BigDecimal unitRate;
    /**
     * 单位数量
     */
    private BigDecimal qty;
    /**
     * 主单位数量
     */
    private BigDecimal mainQty;
    /**
     * tax-inclusive unit price
     * 含税单价
     */
    private BigDecimal taxInUnitPrice;
    /**
     * tax-exclusive unit price
     * 无税单价
     */
    private BigDecimal taxExUnitPrice;
    /**
     * tax-inclusive clean(flat) unit price
     * 含税净价
     */
    private BigDecimal taxInCleanPrice;
    /**
     * tax-exclusive clean(flat) unit price
     * 无税净价
     */
    private BigDecimal taxExCleanPrice;
    /**
     * tax-inclusive unit price
     * 含税主单位单价
     */
    private BigDecimal taxInMainUnitPrice;
    /**
     * tax-exclusive unit price
     * 无税主单位单价
     */
    private BigDecimal taxExMainUnitPrice;
    /**
     * tax-inclusive clean(flat) unit price
     * 含税主单位净价
     */
    private BigDecimal taxInMainCleanPrice;
    /**
     * tax-exclusive clean(flat) unit price
     * 无税主单位净价
     */
    private BigDecimal taxExMainCleanPrice;
    /**
     * 单品折扣率
     */
    private BigDecimal itemDiscountRate;
    /**
     * 整单折扣率
     */
    private BigDecimal orderDiscountRate;
    /**
     * 税额
     */
    private BigDecimal taxAmount;
    /**
     * 折扣额
     */
    private BigDecimal discountAmount;
    /**
     * 无税金额
     */
    private BigDecimal taxExAmount;
    /**
     * 价税合计
     */
    private BigDecimal taxInAmount;

//    private Boolean tanIn;

    /**
     * 含税优先：计算价税合计
     * 并通过计算价税合计计算折扣额、税额、无税金额
     */
    private void calculateAmountTaxIncludedMode() {
        //价税合计 = 数量 * 含税净价
        taxInAmount = qty.multiply(taxInCleanPrice);
        calculateByPriceTaxIncluded();
    }

    /**
     * 计算价税合计计算折扣额、税额、无税金额
     */
    private void calculateByPriceTaxIncluded() {
        //折扣额 = 数量 * 含税单价 – 价税合计
        discountAmount = qty.multiply(taxInUnitPrice).subtract(taxInAmount);
        //税额 = 价税合计 * 税率 / （1 + 税率）
        taxAmount = taxInAmount.multiply(taxRate).divide(taxRate.add(BigDecimal.ONE), scale, roundingMode);
        //无税金额 = 价税合计 - 税额
        taxExAmount = taxInAmount.subtract(taxAmount);
    }

    /**
     * 无税优先：计算无税金额
     * 并通过无税金额计算折扣额、税额、价税合计
     */
    private void calculateAmountTaxExcludedMode() {
        //无税金额 = 数量 * 主单位无税净价
        taxExAmount = qty.multiply(taxExCleanPrice);
        calculateByPriceTaxExcluded();
    }

    /**
     * 无税金额计算折扣额、税额、价税合计
     */
    private void calculateByPriceTaxExcluded() {
        //税额 = 无税金额 * 税率
        taxAmount = taxExAmount.multiply(taxRate);
        //价税合计 = 无税金额 + 税额
        taxInAmount = taxExAmount.add(taxAmount);
        //折扣额 = 数量 * 含税单价 – 价税合计
        discountAmount = qty.multiply(taxInUnitPrice).subtract(taxInAmount);
    }

    private boolean valid(BigDecimal t) {
        return t != null && t.compareTo(BigDecimal.ZERO) > -1;
    }

    private void editTaxInUnitPrice() {

        //含税净价 = 含税单价 * （单品扣率 * 整单扣率）
        taxInCleanPrice = taxInUnitPrice.multiply(itemDiscountRate.multiply(orderDiscountRate));

        // 净价转换
        taxInMainCleanPrice = taxInCleanPrice.divide(unitRate, scale, roundingMode);

        // 主单位含税单价 = 主单位含税净价 / （单品扣率 * 整单扣率）
        taxInMainUnitPrice = taxInMainCleanPrice.divide(itemDiscountRate.multiply(orderDiscountRate), scale, roundingMode);

        calculatePriceTaxExcludedByTaxIncluded();

        //金额
        calculateAmountTaxIncludedMode();
    }

    private void editTaxInCleanPrice() {

        // 单品扣率 = 含税净价 / （含税单价 * 整单扣率）
        itemDiscountRate = taxInCleanPrice.divide(taxInUnitPrice.multiply(orderDiscountRate), scale, roundingMode);

        // 净价转换
        // 主单位含税净价 = 含税净价 / 换算率
        taxInMainCleanPrice = taxInCleanPrice.divide(unitRate, scale, roundingMode);

        calculatePriceTaxExcludedByTaxIncluded();

        //金额
        calculateAmountTaxIncludedMode();

    }

    private void editTaxExUnitPrice() {
        //无税净价 = 无税单价 * （单品扣率 * 整单扣率）
        taxExCleanPrice = taxExUnitPrice.multiply(itemDiscountRate.multiply(orderDiscountRate));

        //净价转换
//        主单位无税净价 = 无税金额 / 主数量
        taxExMainCleanPrice = taxExCleanPrice.divide(unitRate, scale, roundingMode);

//        主单位无税单价 = 主单位无税净价 / （单品扣率 * 整单扣率）
        taxExMainUnitPrice = taxExMainCleanPrice.divide(itemDiscountRate.multiply(orderDiscountRate), scale, roundingMode);

        calculatePriceTaxIncludedByTaxExcluded();

        //金额
        calculateAmountTaxExcludedMode();
    }


    private void editTaxExCleanPrice() {

//        单品扣率 = 无税净价 / （无税单价 * 整单扣率）
        itemDiscountRate = taxExCleanPrice.divide(taxExUnitPrice.multiply(orderDiscountRate), scale, roundingMode);

        //净价转换
        taxExMainCleanPrice = taxExCleanPrice.divide(unitRate, scale, roundingMode);

        calculatePriceTaxIncludedByTaxExcluded();

        //金额
        calculateAmountTaxExcludedMode();
    }

    private void editTaxInMainUnitPrice() {

//        主单位含税净价 = 主单位含税单价 * （单品扣率 * 整单扣率）
        taxInMainCleanPrice = taxInMainUnitPrice.multiply(itemDiscountRate.multiply(orderDiscountRate));

        //净价转换
        taxInCleanPrice = taxInMainCleanPrice.multiply(unitRate);

        //单价转换
        taxInUnitPrice = taxInCleanPrice.divide(itemDiscountRate.multiply(orderDiscountRate), scale, roundingMode);

        calculatePriceTaxExcludedByTaxIncluded();
        //金额
        calculateAmountTaxIncludedMode();
    }

    private void editTaxInMainCleanPrice() {

//        单品扣率 = 主单位含税净价 / （主单位含税单价 * 整单扣率）
        itemDiscountRate = taxInMainCleanPrice.divide(taxInMainUnitPrice.multiply(orderDiscountRate), scale, roundingMode);

        //净价转换
        taxInCleanPrice = taxInMainCleanPrice.multiply(unitRate);

        calculatePriceTaxExcludedByTaxIncluded();
        //金额
        calculateAmountTaxIncludedMode();
    }

    private void editTaxExMainUnitPrice() {

//        主单位无税净价 = 主单位无税单价 * （单品扣率 * 整单扣率）
        taxExMainCleanPrice = taxExMainUnitPrice.multiply(itemDiscountRate.multiply(orderDiscountRate));

        //净价转换
        taxExCleanPrice = taxExMainCleanPrice.multiply(unitRate);

        //无税净价 = 无税单价 * （单品扣率 * 整单扣率）
        taxExUnitPrice = taxExCleanPrice.divide(itemDiscountRate.multiply(orderDiscountRate), scale, roundingMode);

        calculatePriceTaxIncludedByTaxExcluded();
        //金额线
        calculateAmountTaxExcludedMode();

    }

    private void editTaxExMainCleanPrice() {

//        单品扣率 = 主单位无税净价 / （主单位无税单价 * 整单扣率）
        itemDiscountRate = taxExMainCleanPrice.divide(taxExMainUnitPrice.multiply(orderDiscountRate), scale, roundingMode);

        //净价转换
        taxExCleanPrice = taxExMainCleanPrice.multiply(unitRate);

        calculatePriceTaxIncludedByTaxExcluded();
        //金额线
        calculateAmountTaxExcludedMode();

    }

    private void editQty() {
        //主数量 = 数量 * 换算率
        mainQty = qty.multiply(unitRate);
        calculateAmountTaxIncludedMode();
    }

    private void editMainQty() {
        //数量 = 主数量 / 换算率
        qty = mainQty.divide(unitRate, scale, roundingMode);
        calculateAmountTaxIncludedMode();
    }

    private void editUnitRate() {
        qty = mainQty.divide(unitRate, scale, roundingMode);
        editTaxInUnitPrice();
    }

    /**
     * 用于含税优先模式
     * 通过含税价格计算无税价格，使用前需保证含税价格完整
     */
    private void calculatePriceTaxExcludedByTaxIncluded() {
        //无税单价 = 含税单价 / （1 + 税率）
        taxExUnitPrice = taxInUnitPrice.divide(taxRate.add(BigDecimal.ONE), scale, roundingMode);
        //无税净价 = 含税净价 / （1 + 税率）
        taxExCleanPrice = taxInCleanPrice.divide(taxRate.add(BigDecimal.ONE), scale, roundingMode);
        // 主单位无税单价 = 主单位含税单价 / （1 + 税率）
        taxExMainUnitPrice = taxInMainUnitPrice.divide(taxRate.add(BigDecimal.ONE), scale, roundingMode);
        // 主单位无税净价 = 主单位含税净价 / （1 + 税率）
        taxExMainCleanPrice = taxInMainCleanPrice.divide(taxRate.add(BigDecimal.ONE), scale, roundingMode);
    }

    /**
     * 用于无税优先模式
     * 通过无税价格计算含税价格，使用前需保证无税价格完整
     */
    private void calculatePriceTaxIncludedByTaxExcluded() {
        //含税单价 = 无税单价 * （1 + 税率）
        taxInUnitPrice = taxExUnitPrice.multiply(taxRate.add(BigDecimal.ONE));
        //含税净价 = 无税净价 * （1 + 税率）
        taxInCleanPrice = taxExCleanPrice.multiply(taxRate.add(BigDecimal.ONE));
//        主单位含税单价 = 主单位无税单价 * （1 + 税率）
        taxInMainUnitPrice = taxExMainUnitPrice.multiply(taxRate.add(BigDecimal.ONE));
//        主单位含税净价 = 主单位无税净价 * （1 + 税率）
        taxInMainCleanPrice = taxExMainCleanPrice.multiply(taxRate.add(BigDecimal.ONE));
    }

    private void editTaxInAmount() {

        taxInCleanPrice = taxInAmount.divide(qty, scale, roundingMode);

        itemDiscountRate = taxInCleanPrice.divide(taxInUnitPrice.multiply(orderDiscountRate), scale, roundingMode);

        //净价转换
        taxInMainCleanPrice = taxInCleanPrice.divide(unitRate, scale, roundingMode);

        calculatePriceTaxExcludedByTaxIncluded();
        //金额
        calculateByPriceTaxIncluded();
    }

    private void editTaxExAmount() {

//        无税净价 = 无税金额 / 数量
        taxExCleanPrice = taxExAmount.divide(qty, scale, roundingMode);
//        单品扣率 = 无税净价 / （无税单价 * 整单扣率）
        itemDiscountRate = taxInCleanPrice.divide(taxInUnitPrice.multiply(orderDiscountRate), scale, roundingMode);

        //净价转换
        taxExMainCleanPrice = taxExCleanPrice.divide(unitRate, scale, roundingMode);

        calculatePriceTaxIncludedByTaxExcluded();

        //金额
        calculateByPriceTaxExcluded();

    }

    private void editTaxRate() {
        calculatePriceTaxExcludedByTaxIncluded();
        //金额线
        calculateAmountTaxIncludedMode();
    }


    private void editItemDiscountRate() {
//        含税净价 = 含税单价 * （单品扣率 * 整单扣率）
        taxInCleanPrice = taxInUnitPrice.multiply(itemDiscountRate.multiply(orderDiscountRate));

        calculatePriceTaxExcludedByTaxIncluded();

        //金额
        calculateAmountTaxIncludedMode();
    }

    private void editOrderDiscountRate() {
        editItemDiscountRate();
    }

}