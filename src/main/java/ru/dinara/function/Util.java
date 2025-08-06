package ru.dinara.function;

import ru.dinara.models.Carrier;
import ru.dinara.models.Ticket;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Util {
    public static long flightTimeFromVVOtoTLV(List<Ticket> tickets, Carrier carrier) throws ParseException {
        long minTime = Long.MAX_VALUE;
        for (Ticket ticket : tickets) {
            if (ticket.getOriginName().equals("Владивосток")
                    && ticket.getDestinationName().equals("Тель-Авив")
                    && ticket.getCarrier().equals(carrier + "")) {
                long currentTime = ParsingDate(ticket);
                if (currentTime < minTime) {
                    minTime = currentTime;
                }
            }
        }
        return minTime;
    }

    public static long ParsingDate(Ticket ticket) throws ParseException {
        String dateDeparture = ticket.getDepartureDate();
        String timeDeparture = ticket.getDepartureTime();
        String dateArrival = ticket.getArrivalDate();
        String timeArrival = ticket.getArrivalTime();

        LocalDateTime departure = LocalDateTime.parse(
                dateDeparture + " " + timeDeparture,
                DateTimeFormatter.ofPattern("dd.MM.yy H:mm")
        );
        LocalDateTime arrival = LocalDateTime.parse(
                dateArrival + " " + timeArrival,
                DateTimeFormatter.ofPattern("dd.MM.yy HH:mm")
        );

        Duration duration = Duration.between(departure, arrival);
        long minutes = duration.toMinutes();

        return minutes;
    }

    public static int averagePriceFromVVOtoTLV(List<Ticket> tickets) {
        int count = 0;
        int totalPrice = 0;
        for (Ticket ticket : tickets) {
            if (ticket.getOriginName().equals("Владивосток")
                    && ticket.getDestinationName().equals("Тель-Авив")) {
                totalPrice += ticket.getPrice();
                ++count;
            }
        }
        return totalPrice/count;
    }

    public static int medianPriceFromVVOtoTLV(List<Ticket> tickets) {
        int result = 0;
        List<Integer> prices = new ArrayList<>();
        for (Ticket ticket : tickets) {
            if (ticket.getOriginName().equals("Владивосток")
                    && ticket.getDestinationName().equals("Тель-Авив")) {
                prices.add(ticket.getPrice());
            }
        }
        Collections.sort(prices);
        if (prices.size() % 2 == 0) {
            result = (prices.get(prices.size() / 2) + prices.get(prices.size() / 2 - 1)) / 2;
        } else {
            result = prices.get(prices.size() / 2);
        }
        return result;
    }
}
