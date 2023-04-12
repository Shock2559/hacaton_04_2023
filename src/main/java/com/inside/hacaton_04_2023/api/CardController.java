package com.inside.hacaton_04_2023.api;

import com.inside.hacaton_04_2023.dao.*;
import com.inside.hacaton_04_2023.entity.*;
import com.inside.hacaton_04_2023.restClasses.CardDopParameterResponse;
import com.inside.hacaton_04_2023.restClasses.CreateCardRequest;
import com.inside.hacaton_04_2023.restClasses.OtklikRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/card", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CardController {

    private CardDAO cardDAO;
    private CardDopParameterDAO cardDopParameterDAO;
    private UserDAO userDAO;
    private OtklickCardDAO otklickCardDAO;
    private HistoryCardDAO historyCardDAO;

    @Autowired
    public void setCardDAO(CardDAO cardDAO) { this.cardDAO = cardDAO; }
    @Autowired
    public void setCardDopParameterDAO(CardDopParameterDAO cardDopParameterDAO) { this.cardDopParameterDAO = cardDopParameterDAO; }
    @Autowired
    public void setUserDAO(UserDAO userDAO) { this.userDAO = userDAO; }
    @Autowired
    public void setOtklickCardDAO(OtklickCardDAO otklickCardDAO) { this.otklickCardDAO = otklickCardDAO; }
    @Autowired
    public void setHistoryCardDAO(HistoryCardDAO historyCardDAO) { this.historyCardDAO = historyCardDAO; }


    @CrossOrigin
    @GetMapping("/car_for_employer")
    public List<Card> getCardForEmployer() {
        List<Card> response = cardDAO.getCardForEmployer();

        for(int i = 0; i < response.size(); i++) {
            List<CardDopParameter> list = cardDopParameterDAO.findCardDopParameterByCard(response.get(i));

            response.get(i).cardDopParameterResponses = new ArrayList<>();

            for(int j = 0; j < list.size(); j++) {
                response.get(i).cardDopParameterResponses.add(new CardDopParameterResponse(list.get(j).getTitle(), list.get(j).getDescription()));
            }
        }

        return response;
    }

    @CrossOrigin
    @PostMapping("/car_for_employer")
    public List<Card> postCardForEmployer(@RequestBody String filter) {
        List<Card> result = cardDAO.getCardForEmployer();
        List<Card> response = new ArrayList<>();

        for(int i = 0; i < result.size(); i++) {
            if(result.get(i).getName().toLowerCase().indexOf(filter.toLowerCase()) != -1) {
                List<CardDopParameter> list = cardDopParameterDAO.findCardDopParameterByCard(result.get(i));

                result.get(i).cardDopParameterResponses = new ArrayList<>();

                for(int j = 0; j < list.size(); j++) {
                    result.get(i).cardDopParameterResponses.add(new CardDopParameterResponse(list.get(j).getTitle(), list.get(j).getDescription()));
                }

                response.add(result.get(i));
            }
        }

        return response;
    }

    @CrossOrigin
    @GetMapping("/car_for_employee")
    public List<Card> getCardForEmployee() {
        List<Card> response = cardDAO.getCardForEmployee();

        for(int i = 0; i < response.size(); i++) {
            List<CardDopParameter> list = cardDopParameterDAO.findCardDopParameterByCard(response.get(i));

            response.get(i).cardDopParameterResponses = new ArrayList<>();

            for(int j = 0; j < list.size(); j++) {
                response.get(i).cardDopParameterResponses.add(new CardDopParameterResponse(list.get(j).getTitle(), list.get(j).getDescription()));
            }
        }

        return response;
    }

    @CrossOrigin
    @PostMapping("/car_for_employee")
    public List<Card> postCardForEmployee(@RequestBody String filter) {
        List<Card> result = cardDAO.getCardForEmployee();
        List<Card> response = new ArrayList<>();

        for(int i = 0; i < result.size(); i++) {
            if(result.get(i).getName().toLowerCase().indexOf(filter.toLowerCase()) != -1) {
                List<CardDopParameter> list = cardDopParameterDAO.findCardDopParameterByCard(result.get(i));

                result.get(i).cardDopParameterResponses = new ArrayList<>();

                for(int j = 0; j < list.size(); j++) {
                    result.get(i).cardDopParameterResponses.add(new CardDopParameterResponse(list.get(j).getTitle(), list.get(j).getDescription()));
                }

                response.add(result.get(i));
            }
        }

        return response;
    }

    @CrossOrigin
    @PostMapping("/create_card")
    public Card postCreateCard(@RequestBody CreateCardRequest request) {

        User user = userDAO.getUserById(request.userId);

        if(user == null) {
            return null;
        }

        Card card = new Card();
        card.setName(request.name);
        card.setPost(request.post);
        card.setUser(user);
        card.setDescriptionOne(request.descriptionOne);
        card.setDescriptionTwo(request.descriptionTwo);
        card.setStatusOpen(1);

        cardDAO.save(card);

        for(int i = 0; i < request.cardDopParameter.size(); i++) {
            CardDopParameter cardDopParameter = new CardDopParameter();
            cardDopParameter.setCard(card);
            cardDopParameter.setTitle(request.cardDopParameter.get(i).title);
            cardDopParameter.setDescription(request.cardDopParameter.get(i).description);

            cardDopParameterDAO.save(cardDopParameter);
        }

        return card;
    }

    @CrossOrigin
    @PostMapping("/add_otklik_card")
    public String postAddOtklickCard(@RequestBody OtklikRequest request) {

        User user = userDAO.getUserById(request.idUser);
        Card card = cardDAO.getCardById(request.idCard);

        if(user == null || card == null) {
            return "{\"status\":\"Error\"}";
        }

        OtklickCard otklickCard = new OtklickCard();
        otklickCard.setUser(user);
        otklickCard.setCard(card);
        otklickCardDAO.save(otklickCard);

        return "{\"status\":\"OK\"}";
    }

    @CrossOrigin
    @PostMapping("/enter_otklik_card")
    public String postEnterOtklickCard(@RequestBody OtklikRequest request) {

        User user = userDAO.getUserById(request.idUser);
        Card card = cardDAO.getCardById(request.idCard);

        if(user == null || card == null) {
            return "{\"status\":\"Error\"}";
        }

        List<OtklickCard> list = otklickCardDAO.getOtklickCardByCard(card);

        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).getUser().getId() == request.idUser) {
                card.setStatusOpen(2);
                cardDAO.save(card);

                HistoryCard historyCard = new HistoryCard();
                historyCard.setCard(card);
                historyCard.setUser(user);
                historyCardDAO.save(historyCard);
            }

            otklickCardDAO.delete(list.get(i));

        }

        return "{\"status\":\"OK\"}";
    }

    @CrossOrigin
    @PostMapping("/end_card")
    public String postEndCard(@RequestBody int id) {

        Card card = cardDAO.getCardById(id);

        if(card == null) {
            return "{\"status\":\"OK\"}";
        }

        card.setStatusOpen(3);
        cardDAO.save(card);

        return "{\"status\":\"OK\"}";
    }

    @CrossOrigin
    @PostMapping("/card_start")
    public List<Card> postStartCard(@RequestBody int id) {
        List<Card> response = cardDAO.getStartCardById(id);

        for(int i = 0; i < response.size(); i++) {
            List<CardDopParameter> list = cardDopParameterDAO.findCardDopParameterByCard(response.get(i));

            response.get(i).cardDopParameterResponses = new ArrayList<>();

            for(int j = 0; j < list.size(); j++) {
                response.get(i).cardDopParameterResponses.add(new CardDopParameterResponse(list.get(j).getTitle(), list.get(j).getDescription()));
            }
        }

        return response;
    }

    @CrossOrigin
    @PostMapping("/card_work")
    public List<Card> postWorkCard(@RequestBody int id) {
        List<HistoryCard> listH = historyCardDAO.getWorkHistoryCard(id);
        List<Card> response = new ArrayList<>();

        for(int i = 0; i < listH.size(); i++) {
            Card card = listH.get(i).getCard();
            List<CardDopParameter> list = cardDopParameterDAO.findCardDopParameterByCard(card);

            card.cardDopParameterResponses = new ArrayList<>();

            for(int j = 0; j < list.size(); j++) {
                card.cardDopParameterResponses.add(new CardDopParameterResponse(list.get(j).getTitle(), list.get(j).getDescription()));
            }

            response.add(card);
        }

        return response;
    }

    @CrossOrigin
    @PostMapping("/card_end")
    public List<Card> postEndCardList(@RequestBody int id) {
        List<HistoryCard> listH = historyCardDAO.getEndHistoryCard(id);
        List<Card> response = new ArrayList<>();

        for(int i = 0; i < listH.size(); i++) {
            Card card = listH.get(i).getCard();
            List<CardDopParameter> list = cardDopParameterDAO.findCardDopParameterByCard(card);

            card.cardDopParameterResponses = new ArrayList<>();

            for(int j = 0; j < list.size(); j++) {
                card.cardDopParameterResponses.add(new CardDopParameterResponse(list.get(j).getTitle(), list.get(j).getDescription()));
            }

            response.add(card);
        }

        return response;
    }

    @CrossOrigin
    @PostMapping("/my_card_end")
    public List<Card> postMyEndCardList(@RequestBody int id) {
        List<HistoryCard> listH = historyCardDAO.getMyEndHistoryCard(id);
        List<Card> response = new ArrayList<>();

        for(int i = 0; i < listH.size(); i++) {
            Card card = listH.get(i).getCard();
            List<CardDopParameter> list = cardDopParameterDAO.findCardDopParameterByCard(card);

            card.cardDopParameterResponses = new ArrayList<>();

            for(int j = 0; j < list.size(); j++) {
                card.cardDopParameterResponses.add(new CardDopParameterResponse(list.get(j).getTitle(), list.get(j).getDescription()));
            }

            response.add(card);
        }

        return response;
    }


}
