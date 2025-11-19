package br.com.seucaio.gamedex.model.game

import br.com.seucaio.gamedex.model.platform.GamePlatform
import br.com.seucaio.gamedex.util.extension.EMPTY

data class GamePlatformDetailInfo(
    val platformItem: GamePlatform,
    val releasedAt: String = String.EMPTY,
    val requirements: PlatformRequirements? = null,
) {
    companion object {
        val sampleList = listOf(
            GamePlatformDetailInfo(
                platformItem = GamePlatform(id = 5, name = "macOS", gamesCount = 107790),
                releasedAt = "2004-10-26",
                requirements = GamePlatformDetailInfo.PlatformRequirements(
                    minimum = """
                        OS: Mac OS X Snow Leopard 10.6.8 or higher
                        Processor: Intel Core 2 Duo Processor or better
                        Memory: 1GB of RAM
                        Hard Disk Space: 5.2GB of free hard disk space 
                        Video Card: ATI X1600, NVIDIA 7300GT, Intel X3100 integrated graphics chip, or better
                        Additional: This game will NOT run on PowerPC (G3/G4/G5) based Mac systems (PowerMac)
                    """.trimIndent()
                )
            ),
            GamePlatformDetailInfo(
                platformItem = GamePlatform(id = 4, name = "PC", gamesCount = 558895),
                releasedAt = "2004-10-26",
                requirements = GamePlatformDetailInfo.PlatformRequirements(
                    minimum = """
                        Minimum:
                        OS: Microsoft® Windows® 2000/XP
                        Processor: 1Ghz Pentium III or AMD Athlon Processor
                        Memory: 256MB of RAM
                        Graphics: 64MB Video Card (Geforce 3 or better)
                        Hard Drive: 3.6GB of free hard disk space (minimal install)
                        Other Requirements: Software installations required including DirectX and Sony DADC SecuROM
                        Partner Requirements: Please check the terms of service of this site before purchasing this software.
                    """.trimIndent(),
                    recommended = """
                        Recommended:
                        Processor: Intel Pentium 4 or AMD Athlon XP Processor
                        Memory: 384MB of RAM (the more the better!)
                        Graphics: 128MB (or greater) Video Card (Geforce 6 Series Recommended)
                        Hard Drive: 4.7GB of free hard disk space (full install)
                        Sound Card: DirectX 9 compatible Sound Card (Sound Blaster Auidgy 2 Recommended)
                    """.trimIndent()
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
        )
    }

    data class PlatformRequirements(
        val minimum: String = String.EMPTY,
        val recommended: String = String.EMPTY,
    )
}
