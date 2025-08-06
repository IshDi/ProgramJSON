package ru.dinara.models;


import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Ticket {
    @SerializedName("VVO")
    private String VVO;
    @SerializedName("origin_name")
    private String originName;
    @SerializedName("destination")
    private String destination;
    @SerializedName("destination_name")
    private String destinationName;
    @SerializedName("departure_date")
    private String departureDate;
    @SerializedName("departure_time")
    private String departureTime;
    @SerializedName("arrival_date")
    private String arrivalDate;
    @SerializedName("arrival_time")
    private String arrivalTime;
    @SerializedName("carrier")
    private String carrier;
    @SerializedName("stops")
    private int stops;
    @SerializedName("price")
    private int price;
}
