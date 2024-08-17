
package com.swsa.repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.swsa.model.Card;
import com.swsa.service.ConnectionService;

public class CardRepository
{

    private static Connection connection = null;

    private void initConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = new ConnectionService().getConnection();
        }
    }

    public List<Card> retrieveCards()
    {
        List<Card> cards = new ArrayList<>();
        // Use the connection to execute SQL queries and interact awith the database
        try {
            this.initConnection();

            // Your database operations here...
            Statement statement = connection.createStatement();
            ResultSet ResultSet1 = statement.executeQuery("SELECT * FROM card");

            // Iterate over the result set
            while (ResultSet1.next())
            {
                int cardId= ResultSet1.getInt("cardId");
                long cardNo=ResultSet1.getLong("cardNo");
                long accountNo=ResultSet1.getLong("accountNo");
                String accountHolderName=ResultSet1.getString("accountHolderName");
                int cvv=ResultSet1.getInt("cvv");
                String cardType=ResultSet1.getString("cardType");

                // Do something with the data, e.g., print it
                Card card=new Card(cardId,cardNo, accountNo,accountHolderName,cvv,cardType);
                cards.add(card);
            }
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
        } finally {
            // Close the connection when done
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Error closing connection: " + e.getMessage());
                }
            }
        }
        return cards;
    }


    // Method to insert user data into the database
    public boolean insertCard(Card card) throws SQLException {
        this.initConnection();

        String query = "INSERT INTO card VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1,card.getCardId());
            preparedStatement.setLong(2 ,card.getCardNo());
            preparedStatement.setLong(3,card.getAccountNo());
            preparedStatement.setString(4,card.getAccountHolderName());
            preparedStatement.setInt(5,card.getCvv());
            preparedStatement.setString(6,card.getCardType());

            System.out.println("inserting card data  successfully...: " + card);

            int rowsInserted = preparedStatement.executeUpdate();

            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to update user data into the database
    public boolean updateCard(Card card) throws SQLException {
        this.initConnection();

        String query = "UPDATE card SET   CardId= ?, CardNo= ?, AccountNo = ?,AccountHolderName = ?,Cvv= ?,CardType= ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query))
        {
            preparedStatement.setInt(1,card.getCardId());
            preparedStatement.setLong(2 ,card.getCardNo());
            preparedStatement.setLong(3,card.getAccountNo());
            preparedStatement.setString(4,card.getAccountHolderName());
            preparedStatement.setInt(5,card.getCvv());
            preparedStatement.setString(6,card.getCardType());

            System.out.println("updating customer data to table: " + card);

            int rowsInserted = preparedStatement.executeUpdate();

            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to delete user data into the database
    public boolean deleteCard(int cardId) throws SQLException {
        this.initConnection();

        String query = "DELETE FROM card WHERE cardId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1,cardId);

            System.out.println("deleting card data from table: " + cardId);

            int rowsInserted = preparedStatement.executeUpdate();

            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
}
}
