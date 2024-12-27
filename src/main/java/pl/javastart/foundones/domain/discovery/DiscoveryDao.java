package pl.javastart.foundones.domain.discovery;

import pl.javastart.foundones.common.BaseDao;
import pl.javastart.foundones.config.DataSourceProvider;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DiscoveryDao extends BaseDao {

    
    public List<Discovery> findAll(){
        final String sql = "SELECT * FROM discovery";
        List<Discovery> discoveries = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Discovery discovery = mapRow(resultSet);
                discoveries.add(discovery);
            }
            return discoveries;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Discovery> findByCategory(int categoryId){
        final String sql = "SELECT * FROM discovery WHERE category_id = ?";
        List<Discovery> discoveries = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setInt(1, categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Discovery discovery = mapRow(resultSet);
                discoveries.add(discovery);
            }
            return discoveries;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Discovery discovery){
        final String sql = """
               INSERT INTO discovery(title, url, time_added, description, category_id, user_id)
               VALUES (?, ?, ?, ?, ?, ?)
               """;
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, discovery.getTitle());
            statement.setString(2, discovery.getUrl());
            statement.setObject(3, discovery.getDateAdded());
            statement.setString(4, discovery.getDescription());
            statement.setInt(5, discovery.getCategoryId());
            statement.setInt(6, discovery.getUserId());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()){
                discovery.setId(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Discovery mapRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String url = resultSet.getString("url");
        String description = resultSet.getString("description");
        LocalDateTime dateAdded = resultSet.getTimestamp("time_added").toLocalDateTime();
        int categoryId = resultSet.getInt("category_id");
        int userId = resultSet.getInt("user_id");
        return new Discovery(id,title,url,description,dateAdded,categoryId, userId);
    }
}
