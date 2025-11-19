package br.com.seucaio.gamedex.mapper

import br.com.seucaio.gamedex.local.database.entity.GamePlatformEntity
import br.com.seucaio.gamedex.mapper.PlatformsMapper.toDetailDomain
import br.com.seucaio.gamedex.mapper.PlatformsMapper.toDomain
import br.com.seucaio.gamedex.mapper.PlatformsMapper.toEntity
import br.com.seucaio.gamedex.model.data.TopGameData
import br.com.seucaio.gamedex.model.platform.GamePlatform
import org.junit.Assert.assertEquals
import org.junit.Test

class PlatformsMapperTest {

    @Test
    fun `should map GamePlatformEntity to GamePlatform`() {
        // Given
        val entity = GamePlatformEntity(
            platformId = 1,
            name = "Platform 1",
            gamesCount = 100,
            description = "desc"
        )

        // When
        val domain = entity.toDomain()

        // Then
        assertEquals(1, domain.id)
        assertEquals("Platform 1", domain.name)
        assertEquals(100, domain.gamesCount)
    }

    @Test
    fun `should map list of GamePlatformEntity to list of GamePlatform`() {
        // Given
        val entityList = listOf(
            GamePlatformEntity(platformId = 1, name = "Platform 1", gamesCount = 100, description = "desc1"),
            GamePlatformEntity(platformId = 2, name = "Platform 2", gamesCount = 200, description = "desc2")
        )

        // When
        val domainList = entityList.toDomain()

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
    fun `should map GamePlatform to GamePlatformEntity`() {
        // Given
        val domain = GamePlatform(id = 1, name = "Platform 1", gamesCount = 100)

        // When
        val entity = domain.toEntity()

        // Then
        assertEquals(1, entity.platformId)
        assertEquals("Platform 1", entity.name)
        assertEquals(100, entity.gamesCount)
        assertEquals("", entity.description)
    }

    @Test
    fun `should map list of GamePlatform to list of GamePlatformEntity`() {
        // Given
        val domainList = listOf(
            GamePlatform(id = 1, name = "Platform 1", gamesCount = 100),
            GamePlatform(id = 2, name = "Platform 2", gamesCount = 200)
        )

        // When
        val entityList = domainList.toEntity()

        // Then
        assertEquals(2, entityList.size)
        assertEquals(1, entityList[0].platformId)
        assertEquals("Platform 1", entityList[0].name)
        assertEquals(100, entityList[0].gamesCount)
        assertEquals("", entityList[0].description)
        assertEquals(2, entityList[1].platformId)
        assertEquals("Platform 2", entityList[1].name)
        assertEquals(200, entityList[1].gamesCount)
        assertEquals("", entityList[1].description)
    }

    @Test
    fun `should map GamePlatformEntity to GamePlatformDetail`() {
        // Given
        val entity = GamePlatformEntity(
            platformId = 1,
            name = "Platform 1",
            gamesCount = 100,
            description = "Description"
        )
        val topGames = listOf(TopGameData(id = 10, name = "Top Game 1"))

        // When
        val domain = entity.toDetailDomain(topGames)

        // Then
        assertEquals(1, domain.id)
        assertEquals("Platform 1", domain.name)
        assertEquals(100, domain.gamesCount)
        assertEquals("Description", domain.description)
        assertEquals(1, domain.topGames.size)
        assertEquals(10, domain.topGames[0].id)
        assertEquals("Top Game 1", domain.topGames[0].name)
    }

    @Test
    fun `should map GamePlatformEntity to GamePlatformDetail with empty top games`() {
        // Given
        val entity = GamePlatformEntity(
            platformId = 1,
            name = "Platform 1",
            gamesCount = 100,
            description = "Description"
        )

        // When
        val domain = entity.toDetailDomain()

        // Then
        assertEquals(1, domain.id)
        assertEquals("Platform 1", domain.name)
        assertEquals(100, domain.gamesCount)
        assertEquals("Description", domain.description)
        assertEquals(0, domain.topGames.size)
    }

    @Test
    fun `should map list of GamePlatformEntity to list of GamePlatformDetail`() {
        // Given
        val entityList = listOf(
            GamePlatformEntity(platformId = 1, name = "Platform 1", gamesCount = 100, description = "desc1"),
            GamePlatformEntity(platformId = 2, name = "Platform 2", gamesCount = 200, description = "desc2")
        )

        // When
        val detailList = entityList.toDetailDomain()

        // Then
        assertEquals(2, detailList.size)
        assertEquals(1, detailList[0].id)
        assertEquals("Platform 1", detailList[0].name)
        assertEquals("desc1", detailList[0].description)
        assertEquals(0, detailList[0].topGames.size)
        assertEquals(2, detailList[1].id)
        assertEquals("Platform 2", detailList[1].name)
        assertEquals("desc2", detailList[1].description)
        assertEquals(0, detailList[1].topGames.size)
    }
}
