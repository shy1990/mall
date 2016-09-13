package com.sanji.mall.model;

import java.math.BigDecimal;

public class Color {
    private BigDecimal id;

    private String colorName;

    private String colorRgb;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName == null ? null : colorName.trim();
    }

    public String getColorRgb() {
        return colorRgb;
    }

    public void setColorRgb(String colorRgb) {
        this.colorRgb = colorRgb == null ? null : colorRgb.trim();
    }
}