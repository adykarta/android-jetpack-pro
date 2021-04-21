package com.dicoding.film.utils
import com.dicoding.film.R
import com.dicoding.film.data.FilmEntity

object DataDummy {
    fun generateDummyFilm(): List<FilmEntity> {

        val film = ArrayList<FilmEntity>()

        film.add(FilmEntity("A Star Is Born",
            "Drama, Romance, Music",
            "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
            75,
            2018,
            136,
            R.drawable.poster_a_start_is_born))
        film.add(FilmEntity("Alita: Battle Angel",
            "Action, Science Fiction, Adventure",
            "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
            72,
            2019,
            122,
            R.drawable.poster_alita))
        film.add(FilmEntity("Aquaman",
            "Action, Adventure, Fantasy",
            "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
            69,
            2018,
            143,
            R.drawable.poster_aquaman))
        film.add(FilmEntity("Bohemian Rhapsody",
            "Music, Drama, History",
            "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
            80,
            2018,
            135,
            R.drawable.poster_bohemian))
        film.add(FilmEntity("Creed II",
            "Drama",
            "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
            69,
            2018,
            130,
            R.drawable.poster_creed))
        film.add(FilmEntity("Glass",
            "Thriller, Drama, Science Fiction",
            "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
            67,
            2019,
            129,
            R.drawable.poster_glass))
        film.add(FilmEntity(
                "Avengers: Infinity War",
            "Adventure, Action, Science Fiction",
            "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
            83,
            2018,
            149,
            R.drawable.poster_infinity_war))
        film.add(FilmEntity(
            "Ralph Breaks the Internet",
            "Family, Animation, Comedy, Adventure",
            "Video game bad guy Ralph and fellow misfit Vanellope von Schweetz must risk it all by traveling to the World Wide Web in search of a replacement part to save Vanellope's video game, Sugar Rush. In way over their heads, Ralph and Vanellope rely on the citizens of the internet — the netizens — to help navigate their way, including an entrepreneur named Yesss, who is the head algorithm and the heart and soul of trend-making site BuzzzTube.",
            72,
            2018,
            112,
            R.drawable.poster_ralph))
        film.add(FilmEntity(
            "Serenity",
            "Thriller, Mystery, Drama",
            "The quiet life of Baker Dill, a fishing boat captain who lives on the isolated Plymouth Island, where he spends his days obsessed with capturing an elusive tuna while fighting his personal demons, is interrupted when someone from his past comes to him searching for help.",
            54,
            2019,
            102,
            R.drawable.poster_serenity))
        film.add(FilmEntity(
            "How to Train Your Dragon: The Hidden World",
            "Thriller, Mystery, Drama",
            "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
            78,
            2019,
            104,
            R.drawable.poster_how_to_train))

        return film
    }
    fun generateDummyTvShows(): List<FilmEntity> {

        val tvShows = ArrayList<FilmEntity>()

        tvShows.add(FilmEntity("Arrow",
            "Crime, Drama, Mystery, Action & Adventure",
            "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
            66,
            2012,
            42,
            R.drawable.poster_arrow))
        tvShows.add(FilmEntity("Family Guy",
            "Animation, Comedy",
            "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
            70,
            1999,
            22,
            R.drawable.poster_family_guy))
        tvShows.add(FilmEntity("Doom Patrol",
            "Sci-Fi & Fantasy, Comedy, Drama",
            "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
            76,
            2019,
            49,
            R.drawable.poster_doom_patrol))
        tvShows.add(FilmEntity("NCIS",
            "Crime, Action & Adventure, Drama",
            "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
            74,
            2003,
            45,
            R.drawable.poster_ncis))
        tvShows.add(FilmEntity("Riverdale",
            "Mystery, Drama, Crime",
            "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
            86,
            2017,
            45,
            R.drawable.poster_riverdale))
        tvShows.add(FilmEntity("Supergirl",
            "Drama, Sci-Fi & Fantasy, Action & Adventure",
            "Twenty-four-year-old Kara Zor-El, who was taken in by the Danvers family when she was 13 after being sent away from Krypton, must learn to embrace her powers after previously hiding them. The Danvers teach her to be careful with her powers, until she has to reveal them during an unexpected disaster, setting her on her journey of heroism.",
            72,
            2015,
            42,
            R.drawable.poster_supergirl))
        tvShows.add(FilmEntity(
            "Marvel's Iron Fist",
            "Action & Adventure, Drama, Sci-Fi & Fantasy",
            "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
            66,
            2017,
            55,
            R.drawable.poster_iron_fist))
        tvShows.add(FilmEntity(
            "Grey's Anatomy",
            "Drama",
            "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
            82,
            2005,
            43,
            R.drawable.poster_grey_anatomy))
        tvShows.add(FilmEntity(
            "Gotham",
            "Drama, Crime, Sci-Fi & Fantasy",
            "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
            75,
            2014,
            43,
            R.drawable.poster_gotham))
        tvShows.add(FilmEntity(
            "The Flash",
            "Drama, Sci-Fi & Fantasy",
            "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
            77,
            2014,
            44,
            R.drawable.poster_flash))

        return tvShows
    }


}