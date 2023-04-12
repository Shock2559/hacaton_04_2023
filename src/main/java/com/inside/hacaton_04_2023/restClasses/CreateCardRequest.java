package com.inside.hacaton_04_2023.restClasses;

import java.util.List;

public class CreateCardRequest {

    public int userId;
    public String name;
    public String post;
    public String descriptionOne;
    public String descriptionTwo;
    public List<CardDopParameterResponse> cardDopParameter;

}
