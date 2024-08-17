package com.swsa.controller;
import com.swsa.model.Card;
import com.swsa.service.CardService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardController extends HttpServlet {
    private String message;

    private CardService cardService = new CardService();


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("--------------- inside the doGet() method ---------------");
        List<Card> cardList = new ArrayList<>();
        try {
            cardList = cardService.retrieveCards();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Card Details</h1>");
        out.println("<table border=1>");
        out.println("<tr>");
        out.println("<th>CardId</th>");
        out.println("<th>CardNo</th>");
        out.println("<th>AccountNo</th>");
        out.println("<th>AccountHolderName</th>");
        out.println("<th>Cvv</th>");
        out.println("<th>CardType</th>");
        out.println("</tr>");
        cardList.parallelStream().forEach(card -> {
            out.println("<tr>");
            out.println("<td>" + card.getCardId()+"</td>");
            out.println("<td>" + card.getCardNo() +"</td>");
            out.println("<td>" + card.getAccountNo() +"</td>");
            out.println("<td>" + card.getAccountHolderName() +"</td>");
            out.println("<td>" + card.getCvv() +"</td>");
            out.println("<td>" + card.getCardType() +"</td>");
            out.println("</tr>");
        });
        out.println("</table>");
        out.println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("--------------- inside the doGet() method ---------------");
        String cardId= request.getParameter("cardId");
        String cardNo = request.getParameter("cardNo");
        String accountNo = request.getParameter("accountNo");
        String accountHolderName = request.getParameter("accountHolderName");
        String cvv= request.getParameter("cvv");
        String cardType=request.getParameter("cardType");


        Card card = new Card();
        card.setCardId(Integer.parseInt(cardId));
        card.setCardNo(Long.parseLong(cardNo));
        card.setAccountNo(Long.parseLong(accountNo));
        card.setAccountHolderName(accountHolderName);
        card.setCvv(Integer.parseInt(cvv));
        card.setCardType(cardType);


        try {
            boolean isInserted = cardService.insertCard(card);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            if (isInserted) {
                out.println("<h1> Card object inserted to db</h1>");
            } else {
                out.println("<h1> Card object in NOT inserted to db</h1>");
            }
            out.println("</body></html>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("--------------- inside the service() method ---------------");
        if (request.getMethod().equals("POST")) {
            this.doPost(request, response);
        } else {
            this.doGet(request, response);
        }
    }

    public void destroy() {
        System.out.println("--------------- inside the destroy() method ---------------");
    }
}

