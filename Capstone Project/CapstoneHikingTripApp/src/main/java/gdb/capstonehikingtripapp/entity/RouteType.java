package gdb.capstonehikingtripapp.entity;

import java.util.Objects;

/**
 * Capstone Project
 * @author garrettbecker
 */

public class RouteType {
    int RouteTypeID;
    String type;
    String description;

    public int getRouteTypeID() {
        return RouteTypeID;
    }

    public void setRouteTypeID(int RouteTypeID) {
        this.RouteTypeID = RouteTypeID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.RouteTypeID;
        hash = 59 * hash + Objects.hashCode(this.type);
        hash = 59 * hash + Objects.hashCode(this.description);
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
        final RouteType other = (RouteType) obj;
        if (this.RouteTypeID != other.RouteTypeID) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }
}
