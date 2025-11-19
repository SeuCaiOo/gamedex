package br.com.seucaio.gamedex.mapper

import br.com.seucaio.gamedex.mapper.GameDataMapper.toPlatformDetailDomain
import br.com.seucaio.gamedex.mapper.GameDataMapper.toPlatformDomain
import br.com.seucaio.gamedex.mapper.GameDataMapper.toTopGameDomain
import br.com.seucaio.gamedex.mapper.GameDataMapper.toTopGameEntity
import br.com.seucaio.gamedex.model.data.TopGameData
import br.com.seucaio.gamedex.remote.dto.GameDataInfoResponse
import br.com.seucaio.gamedex.remote.dto.list.GameDataListInfoGamesResponse
import br.com.seucaio.gamedex.remote.dto.list.GameDataListInfoResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class GameDataMapperTest {

    @Test
    fun `should map GameDataListInfoResponse to GamePlatform`() {
        // Given
        val dto = GameDataListInfoResponse(id = 1, name = "Platform 1", gamesCount = 100)

        // When
        val domain = dto.toPlatformDomain()

        // Then
        assertEquals(1, domain.id)
        assertEquals("Platform 1", domain.name)
        assertEquals(100, domain.gamesCount)
    }

    @Test
    fun `should map list of GameDataListInfoResponse to list of GamePlatform`() {
        // Given
        val dtoList = listOf(
            GameDataListInfoResponse(id = 1, name = "Platform 1", gamesCount = 100),
            GameDataListInfoResponse(id = 2, name = "Platform 2", gamesCount = 200)
        )

        // When
        val domainList = dtoList.toPlatformDomain()

        // Then
        assertEquals(2, domainList.size)
        assertEquals(1, domainList[0].id)
        assertEquals("Platform 1", domainList[0].name)
        assertEquals(100, domainList[0].gamesCount)
        assertEquals(2, domainList[1].id)
        assertEquals("Platform 2", domainList[1].name)
        assertEquals(200, domainList[1].gamesCount)
    }

    @Test
    fun `should map GameDataListInfoResponse to TopGameData`() {
        // Given
        val dto = GameDataListInfoResponse(id = 1, name = "Game 1", gamesCount = 0)

        // When
        val domain = dto.toTopGameDomain()

        // Then
        assertEquals(1, domain.id)
        assertEquals("Game 1", domain.name)
    }

    @Test
    fun `should map GameDataInfoResponse to GamePlatformDetail`() {
        // Given
        val dto = GameDataInfoResponse(
            id = 1,
            name = "Platform 1",
            gamesCount = 100,
            description = "Description",
            imageBackground = "image.jpg"
        )
        val topGames = listOf(TopGameData(id = 10, name = "Top Game 1"))

        // When
        val domain = dto.toPlatformDetailDomain(topGames)

        // Then
        assertEquals(1, domain.id)
        assertEquals("Platform 1", domain.name)
        assertEquals(100, domain.gamesCount)
        assertEquals("Description", domain.description)
        assertEquals("image.jpg", domain.imageBackground)
        assertEquals(1, domain.topGames.size)
        assertEquals(10, domain.topGames[0].id)
        assertEquals("Top Game 1", domain.topGames[0].name)
    }

    @Test
    fun `should map GameDataInfoResponse to GamePlatformDetail with null image and empty top games`() {
        // Given
        val dto = GameDataInfoResponse(
            id = 1,
            name = "Platform 1",
            gamesCount = 100,
            description = "Description",
            imageBackground = null
        )

        // When
        val domain = dto.toPlatformDetailDomain()

        // Then
        assertEquals(1, domain.id)
        assertEquals("Platform 1", domain.name)
        assertEquals(100, domain.gamesCount)
        assertEquals("Description", domain.description)
        assertEquals("", domain.imageBackground)
        assertEquals(0, domain.topGames.size)
    }

    @Test
    fun `should map GameDataListInfoGamesResponse to TopGameData`() {
        // Given
        val dto = GameDataListInfoGamesResponse(id = 1, name = "Game 1")

        // When
        val domain = dto.toTopGameDomain()

        // Then
        assertEquals(1, domain.id)
        assertEquals("Game 1", domain.name)
    }

    @Test
    fun `should map GameDataListInfoGamesResponse to TopGameEntity`() {
        // Given
        val platformId = 10
        val dto = GameDataListInfoGamesResponse(id = 1, name = "Game 1")

        // When
        val entity = dto.toTopGameEntity(platformId)

        // Then
        assertEquals(1, entity.gameId)
        assertEquals("Game 1", entity.name)
        assertEquals(10, entity.platformId)
    }
}
