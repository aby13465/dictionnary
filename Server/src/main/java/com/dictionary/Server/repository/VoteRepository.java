package com.dictionary.Server.repository;

import com.dictionary.Server.models.Vote;
import com.dictionary.Server.models.VoteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, VoteId> {
    Vote findVoteByVoteId(VoteId voteId);

    @Override
    void deleteById(VoteId voteId);
}
