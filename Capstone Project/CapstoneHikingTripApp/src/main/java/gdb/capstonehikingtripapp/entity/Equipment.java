package gdb.capstonehikingtripapp.entity;

import java.util.Objects;

/**
 * Capstone Project
 * @author garrettbecker
 */

public class Equipment {
    int equipmentID;
    String name;
    String description;
    String storageRoomLocation;
    int quantityAvailable;
    String photoLink;

    public int getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(int equipmentID) {
        this.equipmentID = equipmentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStorageRoomLocation() {
        return storageRoomLocation;
    }

    public void setStorageRoomLocation(String storageRoomLocation) {
        this.storageRoomLocation = storageRoomLocation;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.equipmentID;
        hash = 47 * hash + Objects.hashCode(this.name);
        hash = 47 * hash + Objects.hashCode(this.description);
        hash = 47 * hash + Objects.hashCode(this.storageRoomLocation);
        hash = 47 * hash + this.quantityAvailable;
        hash = 47 * hash + Objects.hashCode(this.photoLink);
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
        final Equipment other = (Equipment) obj;
        if (this.equipmentID != other.equipmentID) {
            return false;
        }
        if (this.quantityAvailable != other.quantityAvailable) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.storageRoomLocation, other.storageRoomLocation)) {
            return false;
        }
        if (!Objects.equals(this.photoLink, other.photoLink)) {
            return false;
        }
        return true;
    }
}
