package pl.javastart.foundones.domain.api;

import pl.javastart.foundones.config.DataSourceProvider;
import pl.javastart.foundones.domain.discovery.Discovery;
import pl.javastart.foundones.domain.discovery.DiscoveryDao;
import pl.javastart.foundones.domain.user.UserDao;
import pl.javastart.foundones.domain.vote.VoteDao;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DiscoveryService {
    private final DiscoveryDao discoveryDao = new DiscoveryDao();
    private final DiscoveryMapper discoveryMapper = new DiscoveryMapper();

    public List<DiscoveryBasicInfo> findAll(){
        return discoveryDao.
                findAll().
                stream().
                map(discoveryMapper::map).
                toList();
    }

    public List<DiscoveryBasicInfo> findByCategory(int category_id){
        return discoveryDao.findByCategory(category_id).stream().map(discoveryMapper::map).toList();
    }

    public void add(DiscoverySaveRequest saveRequest){
        Discovery discovery = discoveryMapper.map(saveRequest);
        discoveryDao.save(discovery);
    }

    private static class DiscoveryMapper{
        private final UserDao userDao  = new UserDao();
        private final VoteDao voteDao = new VoteDao();

        DiscoveryBasicInfo map(Discovery d){
            return new DiscoveryBasicInfo(d.getId(),
                    d.getTitle(),
                    d.getUrl(),
                    d.getDescription(),
                    d.getDateAdded(),
                    voteDao.countByDiscoveryId(d.getId()),
                    userDao.findById(d.getUserId()).orElseThrow().getUsername());
        }
        Discovery map(DiscoverySaveRequest disc){
            return new Discovery(disc.getTitle(),
                    disc.getUrl(),
                    disc.getDescription(),
                    LocalDateTime.now(),
                    disc.getCategoryId(),
                    userDao.findByUsername(disc.getAuthor()).orElseThrow().getId()
            );
        }
    }
}
