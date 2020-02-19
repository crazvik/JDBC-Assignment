package se.ecutb.jonatan;

import java.util.Objects;

public class City {
    private int cityId;
    private String cityName;
    private String countryCode;
    private String cityDistrict;
    private int cityPopulation;

    public City(int cityId, String cityName, String countryCode, String cityDistrict, int cityPopulation) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.countryCode = countryCode;
        this.cityDistrict = cityDistrict;
        this.cityPopulation = cityPopulation;
    }

    public City(String cityName, String countryCode, String cityDistrict, int cityPopulation) {
        this.cityName = cityName;
        this.countryCode = countryCode;
        this.cityDistrict = cityDistrict;
        this.cityPopulation = cityPopulation;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCityDistrict() {
        return cityDistrict;
    }

    public void setCityDistrict(String cityDistrict) {
        this.cityDistrict = cityDistrict;
    }

    public int getCityPopulation() {
        return cityPopulation;
    }

    public void setCityPopulation(int cityPopulation) {
        this.cityPopulation = cityPopulation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return cityId == city.cityId &&
                cityPopulation == city.cityPopulation &&
                Objects.equals(cityName, city.cityName) &&
                Objects.equals(countryCode, city.countryCode) &&
                Objects.equals(cityDistrict, city.cityDistrict);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityId, cityName, countryCode, cityDistrict, cityPopulation);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("City{");
        sb.append("cityId=").append(cityId);
        sb.append(", cityName='").append(cityName).append('\'');
        sb.append(", countryCode='").append(countryCode).append('\'');
        sb.append(", cityDistrict='").append(cityDistrict).append('\'');
        sb.append(", cityPopulation=").append(cityPopulation);
        sb.append('}');
        return sb.toString();
    }
}
