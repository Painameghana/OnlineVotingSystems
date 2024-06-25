import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class User {
    private String username;
    private String password;
    private boolean eligibilityStatus;

    public User(String username, String password, boolean eligibilityStatus) {
        this.username = username;
        this.password = password;
        this.eligibilityStatus = eligibilityStatus;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEligibilityStatus() {
        return eligibilityStatus;
    }
}

class Ballot {
    private Long electionId;
    private List<String> candidates;

    public Ballot(Long electionId, List<String> candidates) {
        this.electionId = electionId;
        this.candidates = candidates;
    }

    public Long getElectionId() {
        return electionId;
    }

    public List<String> getCandidates() {
        return candidates;
    }
}

class Vote {
    private Long userId;
    private Long ballotId;
    private String selectedOption;

    public Vote(Long userId, Long ballotId, String selectedOption) {
        this.userId = userId;
        this.ballotId = ballotId;
        this.selectedOption = selectedOption;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getBallotId() {
        return ballotId;
    }

    public String getSelectedOption() {
        return selectedOption;
    }
}

class Result {
    private Long electionId;
    private String winner;
    private Long voteCount;

    public Result(Long electionId, String winner, Long voteCount) {
        this.electionId = electionId;
        this.winner = winner;
        this.voteCount = voteCount;
    }

    public Long getElectionId() {
        return electionId;
    }

    public String getWinner() {
        return winner;
    }

    public Long getVoteCount() {
        return voteCount;
    }
}


class UserService {
    private Map<String, User> users;

    public UserService() {
        this.users = new HashMap<>();
    }

    public void registerUser(String username, String password, boolean eligibilityStatus) {
        User user = new User(username, password, eligibilityStatus);
        users.put(username, user);
    }

    public User getUser(String username) {
        return users.get(username);
    }
}

class BallotService {
    private Map<Long, Ballot> ballots;

    public BallotService() {
        this.ballots = new HashMap<>();
    }

    public void createBallot(Long electionId, List<String> candidates) {
        Ballot ballot = new Ballot(electionId, candidates);
        ballots.put(electionId, ballot);
    }

    public Ballot getBallot(Long electionId) {
        return ballots.get(electionId);
    }
}

class VoteService {
    private Map<Long, Vote> votes;

    public VoteService() {
        this.votes = new HashMap<>();
    }

    public void castVote(Long userId, Long ballotId, String selectedOption) {
        Vote vote = new Vote(userId, ballotId, selectedOption);
        votes.put(userId, vote);
    }

    public Vote getVote(Long userId) {
        return votes.get(userId);
    }
}

class ResultService {
    private Map<Long, Result> results;

    public ResultService() {
        this.results = new HashMap<>();
    }

    public void calculateResult(Long electionId, String winner, Long voteCount) {
        Result result = new Result(electionId, winner, voteCount);
        results.put(electionId, result);
    }

    public Result getResult(Long electionId) {
        return results.get(electionId);
    }
}

public class OnlineVotingSystem{
    private UserService userService;
    private BallotService ballotService;
    private VoteService voteService;
    private ResultService resultService;

    public OnlineVotingSystem() {
        this.userService = new UserService();
        this.ballotService = new BallotService();
        this.voteService = new VoteService();
        this.resultService = new ResultService();
    }

    public void registerUser(String username, String password, boolean eligibilityStatus) {
        userService.registerUser(username, password, eligibilityStatus);
    }

    public void createBallot(Long electionId, List<String> candidates) {
        ballotService.createBallot(electionId, candidates);
    }

    public void castVote(Long userId, Long ballotId, String selectedOption) {
        voteService.castVote(userId, ballotId, selectedOption);
    }

    public void calculateResult(Long electionId, String winner, Long voteCount) {
        resultService.calculateResult(electionId, winner, voteCount);
    }

    public static void main(String[] args) {
    OnlineVotingSystem onlineVotingSystem = new OnlineVotingSystem();


    onlineVotingSystem.registerUser("user1", "password1", true);
    onlineVotingSystem.registerUser("user2", "password2", true);
    onlineVotingSystem.registerUser("user3", "password3", true);

 
    List<String> candidates = new ArrayList<>();
    candidates.add("Candidate A");
    candidates.add("Candidate B");
    candidates.add("Candidate C");
    onlineVotingSystem.createBallot(1L, candidates);


    onlineVotingSystem.castVote(1L, 1L, "Candidate A");
    onlineVotingSystem.castVote(2L, 1L, "Candidate B");
    onlineVotingSystem.castVote(3L, 1L, "Candidate C");


    onlineVotingSystem.calculateResult(1L, "Candidate A", 2L);

  
    Result result = onlineVotingSystem.resultService.getResult(1L);
    System.out.println("Election Result:");
    System.out.println("Winner: " + result.getWinner());
    System.out.println("Vote Count: " + result.getVoteCount());
}
}
