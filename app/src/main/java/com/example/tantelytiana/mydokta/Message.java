package com.example.tantelytiana.mydokta;

import java.util.Date;

/**
 * Created by Tantely Tiana on 05/07/2016.
 */
public class Message{
    String contenu;
    Date date_message;
    String sender;

    private  Message(){}

    Message(String cont, Date dat, String send)
    {
        contenu=cont;
        date_message=dat;
        sender=send;

    }

    public String getContenu() {
        return contenu;
    }

    public Date getDate_message() {
        return date_message;
    }

    public String getSender() {
        return sender;
    }
}

