package pl.javastart.foundones.domain.api;

import pl.javastart.foundones.domain.user.User;
import pl.javastart.foundones.domain.user.UserDao;
import pl.javastart.foundones.domain.vote.Vote;
import pl.javastart.foundones.domain.vote.VoteDao;

import java.time.LocalDateTime;
import java.util.Optional;

public class DiscoveryVoteService {
    private VoteDao voteDao = new VoteDao();
    private DiscoveryVoteMapper voteMapper = new DiscoveryVoteMapper();

    public void addVote(DiscoveryVote vote) {
        Vote mapped = voteMapper.map(vote);
        voteDao.save(mapped);
    }

    private static class DiscoveryVoteMapper{
        private UserDao userDao = new UserDao();
        Vote map(DiscoveryVote vote){
            Optional<User> user = userDao.findByUsername(vote.getUsername());
            return new Vote(vote.getDiscoveryId(),
                    user.orElseThrow().getId(),
                    Vote.Type.valueOf(vote.getType()),
                    LocalDateTime.now()
                    );
        }
    }
}

