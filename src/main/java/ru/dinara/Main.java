package ru.dinara;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.dinara.models.Carrier;
import ru.dinara.models.Response;
import ru.dinara.models.Ticket;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.List;

import static ru.dinara.function.Util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("tickets.json");
        if (inputStream != null) {
            try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream)) {
                Type listType = new TypeToken<Response>() {}.getType();
                Response response = gson.fromJson(inputStreamReader, listType);
                List<Ticket> tickets = response.getTicketList();

                System.out.println("Минимальное время полета для TK в минутах: " + flightTimeFromVVOtoTLV(tickets, Carrier.TK));
                System.out.println("Минимальное время полета для S7 в минутах: " + flightTimeFromVVOtoTLV(tickets, Carrier.S7));
                System.out.println("Минимальное время полета для SU в минутах: " + flightTimeFromVVOtoTLV(tickets, Carrier.SU));
                System.out.println("Минимальное время полета для BA в минутах: " + flightTimeFromVVOtoTLV(tickets, Carrier.BA));

                System.out.println("Средняя цена: " + averagePriceFromVVOtoTLV(tickets));
                System.out.println("Медианная цена: " + medianPriceFromVVOtoTLV(tickets));
                System.out.println("Разница между средней ценой и медианной ценой: " + (averagePriceFromVVOtoTLV(tickets) - medianPriceFromVVOtoTLV(tickets)));

            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }


}