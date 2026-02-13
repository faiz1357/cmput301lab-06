package com.example.listcity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the CityList class using JUnit 5.
 * Tests cover add(), getCities(), hasCity(), delete(), and countCities().
 * Because City does not override equals() or hashCode(), tests that call
 * hasCity() or delete() pass the same object reference that was originally
 * added to the list.
 *
 * @author Your Name
 * @version 1.0
 * @see CityList
 * @see City
 */
class Citylisttest {

    /**
     * Creates a CityList pre-populated with one city (Edmonton, Alberta).
     *
     * @return A CityList containing one mock City.
     * @see #mockCity()
     */
    private CityList mockCityList() {
        CityList cityList = new CityList();
        cityList.add(mockCity());
        return cityList;
    }

    /**
     * Creates a single mock City representing Edmonton, Alberta.
     *
     * @return A new City instance for Edmonton, Alberta.
     */
    private City mockCity() {
        return new City("Edmonton", "Alberta");
    }

    /**
     * Verifies that add() correctly inserts cities into the list and that
     * the list size increases accordingly.
     *
     * @see CityList#add(City)
     */
    @Test
    void testAdd() {
        CityList cityList = mockCityList();
        assertEquals(1, cityList.getCities().size());

        City city = new City("Regina", "Saskatchewan");
        cityList.add(city);

        assertEquals(2, cityList.getCities().size());
        assertTrue(cityList.getCities().contains(city));
    }

    /**
     * Verifies that add() throws an IllegalArgumentException when attempting
     * to add a city reference that already exists in the list.
     *
     * @throws IllegalArgumentException Expected when a duplicate city is added.
     * @see CityList#add(City)
     */
    @Test
    void testAddException() {
        CityList cityList = mockCityList();
        City city = new City("Yellowknife", "Northwest Territories");
        cityList.add(city);

        assertThrows(IllegalArgumentException.class, () -> {
            cityList.add(city);
        });
    }

    /**
     * Verifies that getCities() returns cities in sorted lexicographic order
     * by city name. Charlottetown (C) sorts before Edmonton (E), so after
     * adding Charlottetown it should appear at index 0 and Edmonton at index 1.
     *
     * @see CityList#getCities()
     * @see City#compareTo(City)
     */
    @Test
    void testGetCities() {
        CityList cityList = mockCityList();

        assertEquals(0, mockCity().compareTo(cityList.getCities().get(0)));

        City city = new City("Charlottetown", "Prince Edward Island");
        cityList.add(city);

        assertEquals(0, city.compareTo(cityList.getCities().get(0)));
        assertEquals(0, mockCity().compareTo(cityList.getCities().get(1)));
    }

    /**
     * Verifies that hasCity() returns true when the same city reference
     * that was added is searched for.
     *
     * @see CityList#hasCity(City)
     */
    @Test
    void testHasCityReturnsTrue() {
        CityList cityList = new CityList();
        City city = new City("Vancouver", "British Columbia");
        cityList.add(city);

        assertTrue(cityList.hasCity(city));
    }

    /**
     * Verifies that hasCity() returns false for a city reference that was
     * never added to the list.
     *
     * @see CityList#hasCity(City)
     */
    @Test
    void testHasCityReturnsFalse() {
        CityList cityList = mockCityList();
        City notInList = new City("Toronto", "Ontario");

        assertFalse(cityList.hasCity(notInList));
    }

    /**
     * Demonstrates that hasCity() uses reference equality because City does
     * not override equals() and hashCode(). A new City with the same name
     * and province as an existing city will NOT be found, only the original
     * reference will be found.
     *
     * @see CityList#hasCity(City)
     * @see City
     */
    @Test
    void testHasCityReferenceEquality() {
        CityList cityList = new CityList();
        City original = new City("Ottawa", "Ontario");
        cityList.add(original);

        City duplicate = new City("Ottawa", "Ontario");
        assertFalse(cityList.hasCity(duplicate));
        assertTrue(cityList.hasCity(original));
    }

    // -----------------------------------------------------------------------
    // Tests for delete()
    // -----------------------------------------------------------------------

    /**
     * Verifies that delete() removes the specified city from the list and
     * that the list size decreases by one.
     *
     * @see CityList#delete(City)
     */
    @Test
    void testDeleteRemovesCity() {
        CityList cityList = new CityList();
        City city = new City("Winnipeg", "Manitoba");
        cityList.add(city);

        assertEquals(1, cityList.countCities());
        cityList.delete(city);
        assertEquals(0, cityList.countCities());
        assertFalse(cityList.hasCity(city));
    }

    /**
     * Verifies that delete() throws an IllegalArgumentException when
     * attempting to delete a city that is not present in the list.
     *
     * @throws IllegalArgumentException Expected when deleting a city not in the list.
     * @see CityList#delete(City)
     */
    @Test
    void testDeleteException() {
        CityList cityList = mockCityList();
        City notInList = new City("Halifax", "Nova Scotia");

        assertThrows(IllegalArgumentException.class, () -> {
            cityList.delete(notInList);
        });
    }

    // -----------------------------------------------------------------------
    // Tests for countCities()
    // -----------------------------------------------------------------------

    /**
     * Verifies that countCities() returns 0 for a newly created, empty list.
     *
     * @see CityList#countCities()
     */
    @Test
    void testCountCitiesEmpty() {
        CityList cityList = new CityList();
        assertEquals(0, cityList.countCities());
    }

    /**
     * Verifies that countCities() increments correctly after each successive
     * call to add().
     *
     * @see CityList#countCities()
     * @see CityList#add(City)
     */
    @Test
    void testCountCitiesAfterAdding() {
        CityList cityList = new CityList();
        assertEquals(0, cityList.countCities());

        cityList.add(new City("Edmonton", "Alberta"));
        assertEquals(1, cityList.countCities());

        cityList.add(new City("Calgary", "Alberta"));
        assertEquals(2, cityList.countCities());

        cityList.add(new City("Red Deer", "Alberta"));
        assertEquals(3, cityList.countCities());
    }

    /**
     * Verifies that countCities() decrements correctly after a call to delete().
     *
     * @see CityList#countCities()
     * @see CityList#delete(City)
     */
    @Test
    void testCountCitiesAfterDeleting() {
        CityList cityList = new CityList();
        City city1 = new City("Edmonton", "Alberta");
        City city2 = new City("Calgary", "Alberta");
        cityList.add(city1);
        cityList.add(city2);

        assertEquals(2, cityList.countCities());
        cityList.delete(city1);
        assertEquals(1, cityList.countCities());
    }
}