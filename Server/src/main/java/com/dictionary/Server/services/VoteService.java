package com.dictionary.Server.services;

import com.dictionary.Server.models.Definition;
import com.dictionary.Server.models.User;
import com.dictionary.Server.models.Vote;
import com.dictionary.Server.models.VoteId;
import com.dictionary.Server.payload.request.VoteRequest;
import com.dictionary.Server.payload.response.MessageResponse;
import com.dictionary.Server.repository.DefinitionRepository;
import com.dictionary.Server.repository.UserRepository;
import com.dictionary.Server.repository.VoteRepository;
import com.dictionary.Server.security.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VoteService{

    private final DefinitionRepository definitionRepository;
    private final UserRepository userRepository;
    private final VoteRepository voteRepository;
    private TokenUtil tokenUtil;

    @Autowired
    public VoteService(DefinitionRepository definitionRepository, UserRepository userRepository, VoteRepository voteRepository, TokenUtil tokenUtil) {
        this.definitionRepository = definitionRepository;
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
        this.tokenUtil = tokenUtil;
    }

    public Object Voting(VoteRequest voteRequest) {
        String token = voteRequest.getToken().substring("Bearer ".length());
        String username = tokenUtil.getUserNameFromToken(token);
        if (username != null) {
            User user = userRepository.findUserByUsername(username).orElseThrow(() -> new RuntimeException("User not found."));
            Definition definition = definitionRepository.findDefinitionById(voteRequest.getDefinition()).orElseThrow(() -> new RuntimeException("Definition not found."));
            if (tokenUtil.isTokenValid(token, user)) {
                VoteId voteId = new VoteId(user.getUsername(), definition.getId());
                Vote vote = this.voteRepository.findVoteByVoteId(voteId);
                if (vote == null) {
                    this.voteRepository.save(new Vote(voteId, voteRequest.getVote()));
                    return new MessageResponse("Vote added");
                } else {
                    if(vote.isVote() == voteRequest.getVote()) {
                        this.voteRepository.deleteById(voteId);
                        return new MessageResponse("Vote Deleted");
                    }
                    else {
                        vote.setVote(voteRequest.getVote());
                        this.voteRepository.save(vote);
                        return new MessageResponse("Vote Changed");
                    }
                }
            }
            return new MessageResponse("User doesn't exist");
        }
        return new MessageResponse("Error");
    }
}
