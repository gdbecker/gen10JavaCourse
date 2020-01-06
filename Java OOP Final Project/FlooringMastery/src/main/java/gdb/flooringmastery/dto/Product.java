package gdb.flooringmastery.dto;

import java.util.Objects;

/**
 * Final Project OOP
 * @author garrettbecker
 */

public class Product {
    private String productType;
    private double costPerSquareFoot;
    private double laborCostPerSquareFoot;

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public double getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    public void setCostPerSquareFoot(double costPerSquareFoot) {
        this.costPerSquareFoot = costPerSquareFoot;
    }

    public double getLaborCostPerSquareFoot() {
        return laborCostPerSquareFoot;
    }

    public void setLaborCostPerSquareFoot(double laborCostPerSquareFoot) {
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.productType);
        hash = 11 * hash + (int) (Double.doubleToLongBits(this.costPerSquareFoot) ^ (Double.doubleToLongBits(this.costPerSquareFoot) >>> 32));
        hash = 11 * hash + (int) (Double.doubleToLongBits(this.laborCostPerSquareFoot) ^ (Double.doubleToLongBits(this.laborCostPerSquareFoot) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (Double.doubleToLongBits(this.costPerSquareFoot) != Double.doubleToLongBits(other.costPerSquareFoot)) {
            return false;
        }
        if (Double.doubleToLongBits(this.laborCostPerSquareFoot) != Double.doubleToLongBits(other.laborCostPerSquareFoot)) {
            return false;
        }
        if (!Objects.equals(this.productType, other.productType)) {
            return false;
        }
        return true;
    }
    
}
