package gdb.statecapitals2;

/**
 * @date Wednesday December 11, 2019
 * @author Garrett Becker
 */

class Capital {
    protected String name;
    protected int population;
    protected int squareMileage;

    public Capital(String name, int population, int squareMileage) {
        this.name = name;
        this.population = population;
        this.squareMileage = squareMileage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getSquareMileage() {
        return squareMileage;
    }

    public void setSquareMileage(int squareMileage) {
        this.squareMileage = squareMileage;
    }

    @Override
    public String toString() {
        return name + " | Pop: " + population + " | Area: " + squareMileage + " sq mi";
    }
    
}
