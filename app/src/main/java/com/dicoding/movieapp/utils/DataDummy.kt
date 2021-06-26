package com.dicoding.movieapp.utils

import com.dicoding.movieapp.data.source.local.entity.MovieEntity
import com.dicoding.movieapp.data.source.local.entity.TvShowEntity
import com.dicoding.movieapp.data.source.remote.response.Movie
import com.dicoding.movieapp.data.source.remote.response.MovieDetailResponse
import com.dicoding.movieapp.data.source.remote.response.TvShow
import com.dicoding.movieapp.data.source.remote.response.TvShowDetailResponse

object DataDummy {

    fun getDummyMovies(): List<MovieEntity> {
        return listOf(
            MovieEntity(
                464052,
                "Wonder Woman 1984",
                "/srYya1ZlI97Au4jUYAktDe3avyA.jpg",
                "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
                "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                false
            ),
            MovieEntity(
                508442,
                "Soul",
                "/kf456ZqeC45XTvo6W9pW5clYKfQ.jpg",
                "Joe Gardner is a middle school teacher with a love for jazz music. After a successful gig at the Half Note Club, he suddenly gets into an accident that separates his soul from his body and is transported to the You Seminar, a center in which souls develop and gain passions before being transported to a newborn child. Joe must enlist help from the other souls-in-training, like 22, a soul who has spent eons in the You Seminar, in order to get back to Earth.",
                "/hm58Jw4Lw8OIeECIq5qyPYhAeRJ.jpg",
                false
            ),
            MovieEntity(
                517096,
                "Cosmoball",
                "/ibwOX4xUndc6E90MYfglghWvO5Z.jpg",
                "Cosmoball is a mesmerizing intergalactic game of future played between humans and aliens at the giant extraterrestrial ship hovering in the sky over Earth. A young man with enormous power of an unknown nature joins the team of hot-headed superheroes in exchange for a cure for his mother’s deadly illness. The Four from Earth will fight not only to defend the honor of their home planet in the spectacular game, but to face the unprecedented threat to the Galaxy and embrace their own destiny.",
                "/eDJYDXRoWoUzxjd52gtz5ODTSU1.jpg",
                false
            )

        )
    }

    fun getDummyDetailMovie(): MovieEntity {
        return MovieEntity(
            464052,
            "Wonder Woman 1984",
            "/srYya1ZlI97Au4jUYAktDe3avyA.jpg",
            "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
            "8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
            false
        )
    }

    fun getDummyTvShows(): List<TvShowEntity> {
        return listOf(
            TvShowEntity(
                456,
                "The Simpsons",
                "/hpU2cHC9tk90hswCFEpf5AtbqoL.jpg",
                "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
                "/2IWouZK4gkgHhJa3oyYuSWfSqbG.jpg",
                false
            ),
            TvShowEntity(
                1399,
                "Game of Thrones",
                "/suopoADq0k8YZr4dQXcU6pToj6s.jpg",
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                "/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                false
            ),
            TvShowEntity(
                1416,
                "Grey's Anatomy",
                "/edmk8xjGBsYVIf4QtLY9WMaMcXZ.jpg",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                false
            )
        )
    }


    fun getDummyDetailTvShow(): TvShowEntity {
        return TvShowEntity(
            77169,
            "Cobra Kai",
            "/gL8myjGc2qrmqVosyGm5CWTir9A.jpg",
            "This Karate Kid sequel series picks up 30 years after the events of the 1984 All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, who has been working to maintain the balance in his life without mentor Mr. Miyagi.",
            "/obLBdhLxheKg8Li1qO11r2SwmYO.jpg",
            false
        )
    }

    fun createDataMovieDummyResponse(): List<Movie> {
        val listMovie = ArrayList<Movie>()

        listMovie.add(
            Movie(
                id = 1,
                name = "A Star Is Born",
                description = "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally\\'s career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                poster = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
                imgPreview = "https://image.tmdb.org/t/p/original/wqtaHWOEZ3rXDJ8c6ZZShulbo18.jpg"
            )
        )

        listMovie.add(
            Movie(
                id = 2,
                name = "Alita: Battle Angel",
                description = "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                poster = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/xRWht48C2V8XNfzvPehyClOvDni.jpg",
                imgPreview = "https://image.tmdb.org/t/p/original/aQXTw3wIWuFMy0beXRiZ1xVKtcf.jpg"
            )
        )

        listMovie.add(
            Movie(
                id = 3,
                name = "Aquaman",
                description = "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm\\'s half-human, half-Atlantean brother and true heir to the throne.",
                poster = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/5Kg76ldv7VxeX9YlcQXiowHgdX6.jpg",
                imgPreview = "https://image.tmdb.org/t/p/original/4IWnPqNu34zY4ku3LQJw56MIHFc.jpg"
            )
        )

        return listMovie
    }

    fun createDataMovieDetailResponse(): MovieDetailResponse{
        return MovieDetailResponse(
            id = 464052,
            backdropPath = "https://image.tmdb.org/t/p/original/wqtaHWOEZ3rXDJ8c6ZZShulbo18.jpg",
            overview = "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally\\'s career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
            posterPath = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
            title = "A Star Is Born"
        )
    }

    fun createDataTvShowDummyResponse(): List<TvShow> {
        val listTvShow = ArrayList<TvShow>()

        listTvShow.add(
            TvShow(
                id = 11,
                name = "Arrow",
                description = "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                poster = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg",
                imgPreview = "https://image.tmdb.org/t/p/original/elbLQbocvW9vwrHRjYTSjXr5BX5.jpg"
            )
        )

        listTvShow.add(
            TvShow(
                id = 12,
                name = "Doom Patrol",
               description = "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                poster = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/nVN7Dt0Xr78gnJepRsRLaLYklbY.jpg",
                imgPreview = "https://image.tmdb.org/t/p/original/sAzw6I1G9JUxm86KokIDdQeWtaq.jpg"
            )
        )

        listTvShow.add(
            TvShow(
                id = 13,
                name = "Dragon Ball",
               description = "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the mystical Dragon Balls brought her to Goku\\'s home. Together, they set off to find all seven and to grant her wish.",
                poster = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/3wx3EAMtqnbSLhGG8NrqXriCUIQ.jpg",
                imgPreview = "https://image.tmdb.org/t/p/original/igXpePfyVeuG50rvSVEay2u4I3R.jpg"
            )
        )

        return listTvShow
    }

    fun createDataTvShowDetailResponse(): TvShowDetailResponse{
        return TvShowDetailResponse(
            id = 77169,
            backdropPath =  "https://image.tmdb.org/t/p/original/ofjZbud67zO2wxQ48VgMVnkECQu.jpg",
            overview = "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally\\'s career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
            posterPath = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/5nSSkcM3TgpllZ7yTyBOQEgAX36.jpg",
            name = "Hanna"
        )
    }

}
