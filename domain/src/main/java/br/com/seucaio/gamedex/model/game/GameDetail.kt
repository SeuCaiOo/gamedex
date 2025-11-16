package br.com.seucaio.gamedex.model.game

import br.com.seucaio.gamedex.model.data.GameDataDetail
import br.com.seucaio.gamedex.model.genre.GameGenreDetail
import br.com.seucaio.gamedex.util.extension.EMPTY
import br.com.seucaio.gamedex.util.extension.ZERO
import br.com.seucaio.gamedex.model.platform.GamePlatform

data class GameDetail(
    override val id: Int,
    override val name: String,
    override val imageBackground: String = String.EMPTY,
    val imageBackgroundAdditional: String = String.EMPTY,
    val description: String = String.EMPTY,
    val metacritic: Int = Int.ZERO,
    val released: String = String.EMPTY,
    val website: String = String.EMPTY,
    val rating: Double = Double.ZERO,
    val alternativeNames: List<String> = emptyList(),
    val platformsInfo: List<GamePlatformDetailInfo> = emptyList(),
    val storesInfo: List<GameStoreDetailInfo> = emptyList(),
    val developersInfo: List<GameDeveloperDetail> = emptyList(),
    val publishersInfo: List<GamePublisherDetail> = emptyList(),
    val genresInfo: List<GameGenreDetail> = emptyList(),
    val esrbRatingInfo: GameEsrbRatingDetail? = null,
) : GameDataDetail {
    companion object {
        val sampleList = listOf(
            GameDetail(
                id = 416,
                name = "Grand Theft Auto: San Andreas",
                imageBackground = "https://media.rawg.io/media/games/960/960b601d9541cec776c5fa42a00bf6c4.jpg",
                imageBackgroundAdditional = "https://media.rawg.io/media/screenshots/236/236efd76a15fca0ce4d9129a788e517e.jpg",
                description = "Grand Theft Auto - San Andreas is the seventh entry in the series in the GTA franchise, but only second big title after GTA - Vice City. Setting up in fictional state San Andreas, you follow the story of CJ, a member of one of the multiple gangs in the city. CJ's family is being attacked in drive shooting which resulted in the death of CJ's mother, so he returns to home from Liberty City. Meeting the rest of the family at his mom's funeral, he decides to rebuild the gang and gain control of the state.\n\nThe game makes a brilliant connection with missions and open world actions that you are able to do around the cities. You can steal cars, buy guns, hunt for collectables and do some side quests, while different characters give you specific missions in order to push the plot forward. Streets are filled with people as well as plenty of vehicles to steal. Fictional brands of cars, tanks, bikes are available to be stolen from any place around the city. Armoury contains pistols, rifles, hand-machine guns or a rocket launcher as well as melee weapons giving player freedom in anything he's doing in GTA.",
                metacritic = 93,
                released = "2004-10-26",
                website = "http://www.rockstargames.com/sanandreas/pc",
                rating = 4.5,
                alternativeNames = listOf("GTA SA", "GTA San Andreas", "serise"),
                platformsInfo = listOf(
                    GamePlatformDetailInfo(
                        platformItem = GamePlatform(id = 5, name = "macOS", gamesCount = 107790),
                        releasedAt = "2004-10-26",
                        requirements = GamePlatformDetailInfo.PlatformRequirements(
                            minimum = "OS: Mac OS X Snow Leopard 10.6.8 or higher\nProcessor: Intel Core 2 Duo Processor or better\nMemory: 1GB of RAM\nHard Disk Space: 5.2GB of free hard disk space \nVideo Card: ATI X1600, NVIDIA 7300GT, Intel X3100 integrated graphics chip, or better\nAdditional: This game will NOT run on PowerPC (G3/G4/G5) based Mac systems (PowerMac)"
                        )
                    ),
                    GamePlatformDetailInfo(
                        platformItem = GamePlatform(id = 4, name = "PC", gamesCount = 558895),
                        releasedAt = "2004-10-26",
                        requirements = GamePlatformDetailInfo.PlatformRequirements(
                            minimum = "Minimum:\nOS: Microsoft® Windows® 2000/XP\nProcessor: 1Ghz Pentium III or AMD Athlon Processor\nMemory: 256MB of RAM\nGraphics: 64MB Video Card (Geforce 3 or better)\nHard Drive: 3.6GB of free hard disk space (minimal install)\nOther Requirements: Software installations required including DirectX and Sony DADC SecuROM\nPartner Requirements: Please check the terms of service of this site before purchasing this software.",
                            recommended = "Recommended:\nProcessor: Intel Pentium 4 or AMD Athlon XP Processor\nMemory: 384MB of RAM (the more the better!)\nGraphics: 128MB (or greater) Video Card (Geforce 6 Series Recommended)\nHard Drive: 4.7GB of free hard disk space (full install)\nSound Card: DirectX 9 compatible Sound Card (Sound Blaster Auidgy 2 Recommended)"
                        )
                    ),
                    GamePlatformDetailInfo(
                        platformItem = GamePlatform(id = 3, name = "iOS", gamesCount = 77417),
                        releasedAt = "2004-10-26"
                    ),
                    GamePlatformDetailInfo(
                        platformItem = GamePlatform(id = 80, name = "Xbox", gamesCount = 744),
                        releasedAt = "2004-10-26"
                    ),
                    GamePlatformDetailInfo(
                        platformItem = GamePlatform(id = 21, name = "Android", gamesCount = 52487),
                        releasedAt = "2004-10-26",
                        requirements = GamePlatformDetailInfo.PlatformRequirements(
                            minimum = "7.0 and up"
                        )
                    ),
                    GamePlatformDetailInfo(
                        platformItem = GamePlatform(id = 18, name = "PlayStation 4", gamesCount = 6948),
                        releasedAt = "2004-10-26"
                    ),
                    GamePlatformDetailInfo(
                        platformItem = GamePlatform(id = 16, name = "PlayStation 3", gamesCount = 3165),
                        releasedAt = "2004-10-26"
                    ),
                    GamePlatformDetailInfo(
                        platformItem = GamePlatform(id = 15, name = "PlayStation 2", gamesCount = 2068),
                        releasedAt = "2004-10-26"
                    ),
                    GamePlatformDetailInfo(
                        platformItem = GamePlatform(id = 14, name = "Xbox 360", gamesCount = 2808),
                        releasedAt = "2004-10-26"
                    ),
                    GamePlatformDetailInfo(
                        platformItem = GamePlatform(id = 1, name = "Xbox One", gamesCount = 5713),
                        releasedAt = "2004-10-26"
                    ),
                ),
                storesInfo = listOf(
                    GameStoreDetailInfo(
                        id = 958554,
                        storeItem = GameStoreDetailInfo.StoreItem(
                            id = 11,
                            name = "Epic Games",
                            domain = "epicgames.com",
                            gamesCount = 1424,
                            imageBackground = "https://media.rawg.io/media/games/8cc/8cce7c0e99dcc43d66c8efd42f9d03e3.jpg"
                        )
                    ),
                    GameStoreDetailInfo(
                        id = 3159,
                        storeItem = GameStoreDetailInfo.StoreItem(
                            id = 3,
                            name = "PlayStation Store",
                            domain = "store.playstation.com",
                            gamesCount = 8061,
                            imageBackground = "https://media.rawg.io/media/games/310/3106b0e012271c5ffb16497b070be739.jpg"
                        )
                    ),
                    GameStoreDetailInfo(
                        id = 466983,
                        storeItem = GameStoreDetailInfo.StoreItem(
                            id = 2,
                            name = "Xbox Store",
                            domain = "microsoft.com",
                            gamesCount = 4930,
                            imageBackground = "https://media.rawg.io/media/games/4a0/4a0a1316102366260e6f38fd2a9cfdce.jpg"
                        )
                    ),
                    GameStoreDetailInfo(
                        id = 12383,
                        storeItem = GameStoreDetailInfo.StoreItem(
                            id = 1,
                            name = "Steam",
                            domain = "store.steampowered.com",
                            gamesCount = 121767,
                            imageBackground = "https://media.rawg.io/media/games/f46/f466571d536f2e3ea9e815ad17177501.jpg"
                        )
                    ),
                    GameStoreDetailInfo(
                        id = 33623,
                        storeItem = GameStoreDetailInfo.StoreItem(
                            id = 7,
                            name = "Xbox 360 Store",
                            domain = "marketplace.xbox.com",
                            gamesCount = 1915,
                            imageBackground = "https://media.rawg.io/media/games/995/9951d9d55323d08967640f7b9ab3e342.jpg"
                        )
                    ),
                    GameStoreDetailInfo(
                        id = 420,
                        storeItem = GameStoreDetailInfo.StoreItem(
                            id = 4,
                            name = "App Store",
                            domain = "apps.apple.com",
                            gamesCount = 75588,
                            imageBackground = "https://media.rawg.io/media/games/0bd/0bd5646a3d8ee0ac3314bced91ea306d.jpg"
                        )
                    ),
                    GameStoreDetailInfo(
                        id = 39985,
                        storeItem = GameStoreDetailInfo.StoreItem(
                            id = 8,
                            name = "Google Play",
                            domain = "play.google.com",
                            gamesCount = 17124,
                            imageBackground = "https://media.rawg.io/media/games/b4e/b4e4c73d5aa4ec66bbf75375c4847a2b.jpg"
                        )
                    )
                ),
                developersInfo = listOf(
                    GameDeveloperDetail(
                        id = 3524,
                        name = "Rockstar North",
                        imageBackground = "https://media.rawg.io/media/screenshots/43b/43b00286439d859eaea32b8e269b83f9.jpg",
                        gamesCount = 29
                    ),
                    GameDeveloperDetail(
                        id = 10436,
                        name = "War Drum Studios",
                        imageBackground = "https://media.rawg.io/media/screenshots/878/878193558aca97b9d07d38cee9e16fd4.jpg",
                        gamesCount = 6
                    )
                ),
                publishersInfo = listOf(
                    GamePublisherDetail(
                        id = 2150,
                        name = "Capcom",
                        imageBackground = "https://media.rawg.io/media/games/295/295eb868c241e6ad32ac033b8e6a2ede.jpg",
                        gamesCount = 632
                    ),
                    GamePublisherDetail(
                        id = 2155,
                        name = "Rockstar Games",
                        imageBackground = "https://media.rawg.io/media/games/769/769b7f0f609f44bac86f2c791fee21dd.jpg",
                        gamesCount = 81
                    ),
                    GamePublisherDetail(
                        id = 4879,
                        name = "1C-SoftClub",
                        imageBackground = "https://media.rawg.io/media/games/7ea/7ea3575b85ee2a8097b99583aa7c86f3.jpg",
                        gamesCount = 51
                    )
                ),
                genresInfo = listOf(
                    GameGenreDetail(
                        id = 4,
                        name = "Action",
                        imageBackground = "https://media.rawg.io/media/games/b45/b45575f34285f2c4479c9a5f719d972e.jpg",
                        description = "",
                        gamesCount = 190862
                    )
                ),
                esrbRatingInfo = GameEsrbRatingDetail(id = 4, name = "Mature")
            )
        )
    }
}