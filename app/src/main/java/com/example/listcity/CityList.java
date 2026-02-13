package com.example.listcity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class keeps track of a list of City objects.
 * Duplicate cities are not permitted. Attempting to add a duplicate or
 * delete a city that does not exist will throw an IllegalArgumentException.
 *
 * @author Faiz Malik
 * @version 1.0
 * @see City
 */
public class CityList {

    /**
     * Internal list storing all City objects.
     */
    private List<City> cities = new ArrayList<>();

    /**
     * Adds a City to the list if it does not already exist.
     * Since City does not override equals(), two City instances are
     * considered equal only if they are the exact same object reference.
     *
     * @param city The candidate City to add.
     * @throws IllegalArgumentException If the same city reference already
     * exists.
     * @see City
     */
    public void add(City city) {
        if (cities.contains(city)) {
            throw new IllegalArgumentException();
        }
        cities.add(city);
    }

    /**
     * Returns a sorted list of all City objects ordered lexicographically
     * by city name. The internal list is sorted in-place before being returned.
     *
     * @return A List of City objects sorted by city name.
     * Returns an empty list if no cities have been added.
     * @see City#compareTo(City)
     * @see java.util.Collections
     */
    public List<City> getCities() {
        List<City> list = cities;
        Collections.sort(list);
        return list;
    }

    /**
     * Checks whether the given City is present in the list.
     * Because City does not override equals(), this performs reference
     * equality — only the exact same object instance will be found.
     *
     * @param city The City to search for.
     * @return true if the exact city reference is in the list, false otherwise.
     * @see City
     */
    public boolean hasCity(City city) {
        return cities.contains(city);
    }

    /**
     * Removes a City from the list if it is present. If the city is not
     * found, an exception is thrown and the list remains unchanged.
     *
     * @param city The City to remove.
     * @throws IllegalArgumentException If the city does not exist in the list.
     * @see #hasCity(City)
     */
    public void delete(City city) {
        if (!cities.contains(city)) {
            throw new IllegalArgumentException();
        }
        cities.remove(city);
    }

    /**
     * Returns the total number of cities currently in the list.
     *
     * @return The count of City objects as a non-negative int.
     *         Returns 0 if the list is empty.
     */
    public int countCities() {
        return cities.size();
    }
}