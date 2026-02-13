package com.example.listcity;

/**
 * This class represents a City with a name and a province.
 * It implements Comparable to allow sorting by city name lexicographically.
 *
 * @author Faiz Malik
 * @version 1.0
 * @see CityList
 */
public class City implements Comparable<City> {

    /**
     * The name of the city.
     */
    private String city;

    /**
     * The name of the province this city belongs to.
     */
    private String province;

    /**
     * Constructs a new City with the specified name and province.
     *
     * @param city     The name of the city.
     * @param province The name of the province.
     */
    City(String city, String province) {
        this.city = city;
        this.province = province;
    }

    /**
     * Returns the name of the city.
     *
     * @return The city name as a String.
     */
    String getCityName() {
        return this.city;
    }

    /**
     * Returns the name of the province this city belongs to.
     *
     * @return The province name as a String.
     */
    String getProvinceName() {
        return this.province;
    }

    /**
     * Compares this city to another city lexicographically by city name.
     * Returns 0 if the two cities have the same name, a negative integer
     * if this city's name comes before the other alphabetically, or a
     * positive integer if it comes after.
     *
     * @param other The other City to compare against.
     * @return A negative integer, zero, or a positive integer as this city's
     *         name is lexicographically less than, equal to, or greater than
     *         the other city's name.
     */
    @Override
    public int compareTo(City other) {
        return this.city.compareTo(other.getCityName());
    }
}