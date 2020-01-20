package gdb.capstonehikingtripapp.entity;

import java.util.Objects;

/**
 * Capstone Project
 * @author garrettbecker
 */

public class Trail {
    int trailID;
    int locationID;
    String trailName;
    String difficultyRating;
    int routeTypeID;
    double distance;
    double elevationGain;
    String mapLink;

    public int getTrailID() {
        return trailID;
    }

    public void setTrailID(int trailID) {
        this.trailID = trailID;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public String getTrailName() {
        return trailName;
    }

    public void setTrailName(String trailName) {
        this.trailName = trailName;
    }

    public String getDifficultyRating() {
        return difficultyRating;
    }

    public void setDifficultyRating(String difficultyRating) {
        this.difficultyRating = difficultyRating;
    }

    public int getRouteTypeID() {
        return routeTypeID;
    }

    public void setRouteTypeID(int routeTypeID) {
        this.routeTypeID = routeTypeID;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getElevationGain() {
        return elevationGain;
    }

    public void setElevationGain(double elevationGain) {
        this.elevationGain = elevationGain;
    }

    public String getMapLink() {
        return mapLink;
    }

    public void setMapLink(String mapLink) {
        this.mapLink = mapLink;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.trailID;
        hash = 41 * hash + this.locationID;
        hash = 41 * hash + Objects.hashCode(this.trailName);
        hash = 41 * hash + Objects.hashCode(this.difficultyRating);
        hash = 41 * hash + this.routeTypeID;
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.distance) ^ (Double.doubleToLongBits(this.distance) >>> 32));
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.elevationGain) ^ (Double.doubleToLongBits(this.elevationGain) >>> 32));
        hash = 41 * hash + Objects.hashCode(this.mapLink);
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
        final Trail other = (Trail) obj;
        if (this.trailID != other.trailID) {
            return false;
        }
        if (this.locationID != other.locationID) {
            return false;
        }
        if (this.routeTypeID != other.routeTypeID) {
            return false;
        }
        if (Double.doubleToLongBits(this.distance) != Double.doubleToLongBits(other.distance)) {
            return false;
        }
        if (Double.doubleToLongBits(this.elevationGain) != Double.doubleToLongBits(other.elevationGain)) {
            return false;
        }
        if (!Objects.equals(this.trailName, other.trailName)) {
            return false;
        }
        if (!Objects.equals(this.difficultyRating, other.difficultyRating)) {
            return false;
        }
        if (!Objects.equals(this.mapLink, other.mapLink)) {
            return false;
        }
        return true;
    }
}
