package gdb.flooringmastery.dto;

import java.util.Objects;

/**
 * Final Project OOP
 * @author garrettbecker
 */

public class Tax {
    private String state;
    private double taxRate;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.state);
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.taxRate) ^ (Double.doubleToLongBits(this.taxRate) >>> 32));
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
        final Tax other = (Tax) obj;
        if (Double.doubleToLongBits(this.taxRate) != Double.doubleToLongBits(other.taxRate)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        return true;
    }
    
}
