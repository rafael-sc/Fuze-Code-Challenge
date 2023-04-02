package com.orafaelsc.fuzecodechallenge.domain.usecase

import com.orafaelsc.fuzecodechallenge.domain.repository.MatchesRepository

class MatchesUseCaseImpl(
    private val matchesRepository: MatchesRepository,
) : MatchesUseCase {

    override suspend fun getMatches(): Any {
        return matchesRepository.getMatches()
    }
}
