package edu.goit.services;

import edu.goit.databaseutil.Database;
import edu.goit.entity.Client;
import edu.goit.sql.CRUDQuery;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    public long create(String name) {

        try (var perpStAdd = Database.getInstance().getConnection().prepareStatement(CRUDQuery.ADD_CLIENT);
             var perpStGetID = Database.getInstance().getConnection().prepareStatement(CRUDQuery.GET_CLIENT_ID_BY_NAME)) {

            perpStAdd.setString(1, name);
            perpStAdd.execute();
            perpStGetID.setString(1, name);
            var resultSet = perpStGetID.executeQuery();

            if (!resultSet.next()) {
                return 0;
            }

            return Long.parseLong(resultSet.getString("ID"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public String getById(long id) {

        try (var perpStGet = Database.getInstance().getConnection().prepareStatement(CRUDQuery.GET_CLIENT_BY_ID)) {

            perpStGet.setInt(1, (int) id);
            var resultSet = perpStGet.executeQuery();

            if (!resultSet.next()) {
                return null;
            }

            return resultSet.getString("name");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void setName(long id, String name) {

        try (var perpStSetName = Database.getInstance().getConnection().prepareStatement(CRUDQuery.SET_CLIENT_NAME)) {

            perpStSetName.setString(1, name);
            perpStSetName.setInt(2, (int) id);
            perpStSetName.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteById(long id) {

        try (var perpStDelete = Database.getInstance().getConnection().prepareStatement(CRUDQuery.DELETE_CLIENT)) {

            perpStDelete.setInt(1, (int) id);
            perpStDelete.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Client> listAll() {

        List<Client> clients ;

        try (var stGetClients = Database.getInstance().getConnection().createStatement()) {

            var resultSet = stGetClients.executeQuery(CRUDQuery.GET_ALL_CLIENTS);
            clients = new ArrayList<>();

            while (resultSet.next()) {
                clients.add(Client.builder()
                        .ID(resultSet.getInt("ID"))
                        .name(resultSet.getString("name"))
                        .build());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return clients;
    }
}
