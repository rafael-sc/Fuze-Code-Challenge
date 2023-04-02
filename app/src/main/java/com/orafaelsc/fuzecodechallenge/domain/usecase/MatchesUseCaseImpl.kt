package com.orafaelsc.fuzecodechallenge.domain.usecase

import com.orafaelsc.fuzecodechallenge.domain.repository.MatchesRepository
import com.orafaelsc.fuzecodechallenge.ui.matches.model.Match

class MatchesUseCaseImpl(
    private val matchesRepository: MatchesRepository,
) : MatchesUseCase {

    override suspend fun getMatches(): List<Match> {
        return matchesRepository.getMatches()
    }
}
