package gdb.capstonehikingtripapp.entity;

import java.util.Objects;

/**
 * Capstone Project
 * @author garrettbecker
 */

public class Location {
    int locationID;
    String parkName;
    String nearbyCity;
    String state;

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public String getNearbyCity() {
        return nearbyCity;
    }

    public void setNearbyCity(String nearbyCity) {
        this.nearbyCity = nearbyCity;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.locationID;
        hash = 97 * hash + Objects.hashCode(this.parkName);
        hash = 97 * hash + Objects.hashCode(this.nearbyCity);
        hash = 97 * hash + Objects.hashCode(this.state);
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
        final Location other = (Location) obj;
        if (this.locationID != other.locationID) {
            return false;
        }
        if (!Objects.equals(this.parkName, other.parkName)) {
            return false;
        }
        if (!Objects.equals(this.nearbyCity, other.nearbyCity)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        return true;
    }
}
