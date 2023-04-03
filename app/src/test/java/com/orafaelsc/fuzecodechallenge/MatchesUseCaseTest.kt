package com.orafaelsc.fuzecodechallenge

import com.orafaelsc.fuzecodechallenge.commom.ResourceProvider
import com.orafaelsc.fuzecodechallenge.commom.exceptions.ApiException
import com.orafaelsc.fuzecodechallenge.data.MatchesRepositoryImpl
import com.orafaelsc.fuzecodechallenge.data.network.MatchesApi
import com.orafaelsc.fuzecodechallenge.data.network.model.LeagueResponseItem
import com.orafaelsc.fuzecodechallenge.data.network.model.MatchResponseItem
import com.orafaelsc.fuzecodechallenge.data.network.model.OpponentResponseItem
import com.orafaelsc.fuzecodechallenge.data.network.model.SerieResponseItem
import com.orafaelsc.fuzecodechallenge.domain.repository.MatchesRepository
import com.orafaelsc.fuzecodechallenge.domain.usecase.MatchesUseCase
import com.orafaelsc.fuzecodechallenge.domain.usecase.MatchesUseCaseImpl
import io.kotlintest.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class MatchesUseCaseTest {

    private lateinit var matchesUseCase: MatchesUseCase
    private lateinit var matchesRepository: MatchesRepository

    @MockK
    private lateinit var matchesApi: MatchesApi

    @MockK
    private lateinit var resourceProvider: ResourceProvider

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        matchesRepository = MatchesRepositoryImpl(matchesApi, resourceProvider)
        matchesUseCase = MatchesUseCaseImpl(matchesRepository)
    }

    private val response = MatchResponseItem(
        "2023-04-03T15:11:11Z",
        null,
        0,
        LeagueResponseItem(
            1,
            "league name",
            "league image url"
        ),
        "Match Name",
        listOf(
            OpponentResponseItem(
                OpponentResponseItem.OpponentItem(
                    2,
                    "opponent1 image url",
                    "opponent1 name"
                )
            ),
            OpponentResponseItem(
                OpponentResponseItem.OpponentItem(
                    3,
                    "opponent2 image url",
                    "opponent2 name"
                )
            )
        ),
        SerieResponseItem(
            4,
            name = "serie name"
        ),
        "status"
    )

    @Test
    fun `getMatches() - should return matches list - matches list should have same properties as response`() =
        runTest {
            // arrange
            coEvery { matchesApi.getMatches() } returns Response.success(
                listOf(response)
            )
            // act
            every {
                resourceProvider.getString(
                    R.string.description,
                    any(),
                    any()
                )
            } returns "description"
            every { resourceProvider.getString(R.string.to_be_defined) } returns "to be defined"
            every { resourceProvider.getString(R.string.now) } returns "now"
            val result = matchesUseCase.getMatches()

            // assert
            result.size shouldBe 1
            result[0].firstTeam.name shouldBe "opponent1 name"
        }

    @Test(expected = ApiException.UnableToGetMatchesException::class)
    fun `getMatches() - api result is error - should throw UnableToGetMatchesException`() =
        runTest {
            // arrange
            coEvery { matchesApi.getMatches() } throws ApiException.UnableToGetMatchesException()
            // act
            matchesApi.getMatches()
            //assert
            // test should throw UnableToGetMatchesException
        }

}
