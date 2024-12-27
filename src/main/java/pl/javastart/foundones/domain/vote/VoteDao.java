package pl.javastart.foundones.domain.vote;

import pl.javastart.foundones.common.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VoteDao extends BaseDao {

    public void save(Vote vote) {
        final String sql = """
         INSERT INTO 
             vote
                 (discovery_id, user_id, date_added, type) 
         VALUES 
                 (?, ?, ?, ?)
         ON DUPLICATE KEY UPDATE 
            type = ?
         """;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, vote.getDiscoveryId());
            preparedStatement.setInt(2, vote.getUserId());
            preparedStatement.setObject(3, vote.getDateAdded());
            preparedStatement.setString(4, vote.getType().toString());
            preparedStatement.setString(5, vote.getType().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int countByDiscoveryId(int discoveryId) {
        final String sql = """
                SELECT
                                	(SELECT COUNT(discovery_id) FROM vote WHERE discovery_id = ? AND type = 'UP')
                                    -
                                    (SELECT COUNT(discovery_id) FROM vote WHERE discovery_id = ? AND type = 'DOWN')
                                    AS
                                    vote_count;
                """;
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, discoveryId);
            statement.setInt(2, discoveryId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt("vote_count");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
