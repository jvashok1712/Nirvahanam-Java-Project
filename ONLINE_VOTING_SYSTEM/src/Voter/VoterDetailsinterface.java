package Voter;

import java.sql.Connection;

public interface VoterDetailsinterface {
    public void Voterlist(Connection con);
    public void Canidatelist(Connection con);
    public void Votedpeople(Connection con);
    public void NotVotedpeople(Connection con);
    public int NoofVotesofeachCandidate(Connection con,String CandidateId);
    public void CandidateandNoofvotes(Connection con);
    public void SearchVoter(Connection con,String VoterId);
    
}
