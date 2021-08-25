package stepdefs;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Weather.Weather;
import model.Weather.WeatherResponse;
import org.junit.jupiter.api.Assertions;
import requesters.WeatherRequester;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

public class WeatherStepDefs {
    private long cityId;
    private WeatherResponse response;

    @Given("city ID: {long}")
    public void set_city_id(long cityId) {
        this.cityId = cityId;
    }

    @When("we are requesting weather data")
    public void request_weather() throws JsonProcessingException {
        WeatherRequester requester = new WeatherRequester();
        response = requester.getWeatherData(cityId);
    }

    @Then("coordinates are:")
    public void check_coords(Map<String, Double> coords) {
        Assertions.assertEquals(coords.get("lon"), response.getCoord().getLon(), "Incorrect Coord lon");
        Assertions.assertEquals(coords.get("lat"), response.getCoord().getLat(), "Incorrect Coord lat");
    }

    @Then("weathers are:")
    public void check_weathers(DataTable dataTable) {
        List<Map<String, String>> weathers = dataTable.asMaps();

        Assertions.assertEquals(weathers.size(), response.getWeathers().size(), "Wrong Weathers amount!");

        for (int i = 0; i < weathers.size(); i++) {
            Map<String, String> expectedWeather = weathers.get(i);
            Weather actualWeather = response.getWeathers().get(i);

            Assertions.assertEquals(Long.parseLong(expectedWeather.get("id")), actualWeather.getId(), "Wrong id!");
            Assertions.assertEquals(expectedWeather.get("main"), actualWeather.getMain(), "Wrong main!");
            Assertions.assertEquals(expectedWeather.get("description"), actualWeather.getDescription(), "Wrong description!");
            Assertions.assertEquals(expectedWeather.get("icon"), actualWeather.getIcon(), "Wrong icon!");
        }
    }

    @Then("base is {string}")
    public void check_base(String base) {
        Assertions.assertEquals(base, response.getBase(), "Wrong Base!");
    }

    @Then("main is:")
    public void check_main(Map<String, Double> main) {
        Assertions.assertEquals(main.get("temp"), response.getMain().getTemp(), "Wrong temperature!");
        Assertions.assertEquals(main.get("pressure"), response.getMain().getPressure(), "Wrong pressure!");
        Assertions.assertEquals(main.get("humidity"), response.getMain().getHumidity(), "Wrong humidity");
        Assertions.assertEquals(main.get("temp_min"), response.getMain().getTemp_min(), "Wrong minimal temperature!");
        Assertions.assertEquals(main.get("temp_max"), response.getMain().getTemp_max(), "Wrong maximal temperature!");
    }

    @Then("visibility is {long}")
    public void check_visibility(Long visibility) {
        Assertions.assertEquals(visibility, response.getVisibility(), "Wrong Visibility!");
    }

    @Then("wind is:")
    public void check_wind(Map<String, Double> wind) {
        Assertions.assertEquals(wind.get("speed"), response.getWind().getSpeed(), "Wrong speed!");
        Assertions.assertEquals(wind.get("deg"), response.getWind().getDeg(), "Wrong degrees!");
    }

    @Then("clouds are:")
    public void check_clouds(Map<String, Integer> clouds) {
        Assertions.assertEquals(clouds.get("all"), response.getClouds().getAll(), "Wrong clouds parameters!");
    }

    @Then("dt is {long}")
    public void check_dt(Long dt) {
        Assertions.assertEquals(dt, response.getDt(), "Wrong dt!");
    }

    @Then("sys is:")
    public void check_sys(Map<String, String> params) {
        Assertions.assertEquals(Integer.parseInt(params.get("type")), response.getSys().getType(), "Wrong Type!");
        Assertions.assertEquals(Long.parseLong(params.get("id")), response.getSys().getId(), "Wrong id!");
        Assertions.assertEquals(Double.parseDouble(params.get("message")), response.getSys().getMessage(), "Wrong message!");
        Assertions.assertEquals(params.get("country"), response.getSys().getCountry(), "Wrong country!");
        Assertions.assertEquals(Long.parseLong(params.get("sunrise")), response.getSys().getSunrise(), "Wrong Sunrise!");
        Assertions.assertEquals(Long.parseLong(params.get("sunset")), response.getSys().getSunset(), "Wrong Sunset!");

        LocalDate date = Instant.ofEpochSecond(response.getSys().getSunrise()).atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println(date);

        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(response.getSys().getSunrise()), ZoneId.systemDefault());
        System.out.println(dateTime);
    }

    @Then("id is {long}")
    public void check_id(Long id) {
        Assertions.assertEquals(id, response.getId(), "Wrong id!");
    }

    @Then("name is {string}")
    public void check_name(String name) {
        Assertions.assertEquals(name, response.getName(), "Wrong name!");
    }

    @Then("cod is {long}")
    public void check_cod(Long cod) {
        Assertions.assertEquals(cod, response.getCod(), "Wrong cod!");
    }
}

