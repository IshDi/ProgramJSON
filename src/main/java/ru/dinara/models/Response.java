package ru.dinara.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {
    @SerializedName("tickets")
    private List<Ticket> ticketList;

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }
}
