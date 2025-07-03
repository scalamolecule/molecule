package molecule.db.compliance.domains

import molecule.DomainStructure

object Clazzig extends DomainStructure(0) {


  // I/O --------------------------------------------------------------------------------------- 0

  object io {

    trait FileType {
      val name = oneString.unique
    }

    trait File {
      val name = oneString
      val tpe  = one[FileType]
    }
  }


  // Administrative ---------------------------------------------------------------------------- 1

  object adm {

    trait NoteType {
      val name = oneString.unique
    }

    trait Note {
      val txt      = oneString
      val tpe      = one[NoteType]
      val assignee = many[ind.Person]
    }
  }


  // Data mining ------------------------------------------------------------------------------- 2

  object data {

    trait Src {
      val name  = oneString("Name of source: Daniels")
      val descr = oneString("Description of content")
      val lang  = setString("Content language(s)")
      val uuid  = oneString("Source identifier: daniels")
      val uuid2 = oneString("Secondary source identifier")
      val link  = one[loc.Web]
      val link2 = one[loc.Web]
      val data  = mapString("Arbitrary key-value data for source")
    }
  }


  // Community --------------------------------------------------------------------------------- 3

  object com {

    trait TagCat {
      val name = oneString
    }

    trait Tag {
      val name = oneString
      val cat  = many[TagCat]
    }

    trait Ranking {
      val name  = oneString("Ranking subject: Top 100 orchestras")
      val descr = oneString("Description of ranking")
      val min   = oneInt("Ranking range min: 1")
      val max   = oneInt("Ranking range max: 100")
    }

    trait Rank {
      val ranking = one[Ranking]
      val user    = one[ind.User]
      val value   = oneInt
    }

    trait Rating {
      val name   = oneString("Rating subject: Stars")
      val labels = mapString("en_1: Terrible, en_2: Poor, en_3: Okay, en_4: Good, en_5: Fantastic")
      val min    = oneInt("Rating min: 1")
      val max    = oneInt("Rating max: 5")
    }

    trait Rate {
      val rating  = one[Rating]
      val user    = one[ind.User]
      val value   = oneInt
      val comment = oneString("User comment to this rate")
    }
  }


  // Locality ---------------------------------------------------------------------------------- 4

  object loc {

    trait Currency {
      val name     = oneString("US Dollars, Canadian Dollars, Euros ...")
      val code     = oneString("USD, CAD, EUR, SEK, GBP - ISO 4217 codes")
      val num      = oneString("Numeric code")
      val fraction = oneInt
      val sign     = oneString("$, € ...")
      val append   = oneString("US$, CA$ ...")
    }

    trait Money {
      val currency = one[Currency]
      val amount   = oneDouble
    }

    trait Language {
      val iso2 = oneString
      val iso3 = oneString
      val name = oneString
    }

    trait AddressFormat {
      val singleLine     = oneString
      val multiLine      = oneString
      val multiLineShort = oneString
    }

    trait County {
      val name    = oneString.fulltext
      val state   = one[State]
      val country = one[Country]
      val long    = oneString
      val lat     = oneString
    }

    trait Municipality {
      val name    = oneString.fulltext
      val county  = one[County]
      val state   = one[State]
      val country = one[Country]
      val long    = oneString
      val lat     = oneString
    }

    // Europe, Scandinavia (partOf Europe), Middle East
    trait WorldPart {
      val name   = oneString
      val partOf = one[WorldPart]
      val long   = oneString
      val lat    = oneString
    }

    trait Country {
      val continent     = one[WorldPart]
      val worldPart     = one[WorldPart]
      val iso2          = oneString
      val iso3          = oneString
      val name          = oneString
      val nameFull      = oneString
      val specific      = oneString
      val addressFormat = one[AddressFormat]
      val telPrefix     = oneString
      val eu            = oneBoolean
      val priority      = oneInt("8 assigned to historical countries/regions")
      val long          = oneString
      val lat           = oneString
    }

    // State, Territory, Province, countries within countries. Texas, Vancouver, Scotland?
    trait State {
      val iso2    = oneString
      val iso3    = oneString
      val name    = oneString
      val country = one[Country]
      val long    = oneString
      val lat     = oneString
    }

    // Cross-cutting places, metropoles, islands etc. Sjælland, Øresundsregionen, Silicon Valley, etc
    trait Area {
      val name     = oneString.fulltext
      val specific = oneString
      val state    = one[State]
      val country  = one[Country]
      val long     = oneString
      val lat      = oneString
    }

    trait City {
      val name    = oneString
      val area    = one[Area]
      val state   = one[State]
      val country = one[Country]
      val long    = oneString
      val lat     = oneString
    }

    trait Address {
      val name    = oneString
      val street1 = oneString.fulltext
      val street2 = oneString.fulltext
      val zip     = oneString
      val city    = one[City]
      val area    = one[Area]
      val state   = one[State]
      val country = one[Country]
      val long    = oneString
      val lat     = oneString
    }

    trait Tel {
      val intCode = oneString
      val number  = oneString
      val ext     = oneString
    }

    trait WebCat {
      val name = oneString("wiki, official homepage, etc")
    }

    trait Web {
      val uuid    = oneUUID
      val url     = oneString
      val lang    = oneString
      val title   = oneString
      val comment = oneString
      val ip      = oneString
      val cats    = many[WebCat]
      val tags    = many[com.Tag]
    }
  }


  // Individual -------------------------------------------------------------------------------- 5

  object ind {

    trait User {
      val uuid     = oneUUID
      val person   = one[Person]
      val email    = oneString.unique
      val username = oneString.unique
      val password = oneString
    }

    trait Person {
      val uuid = oneUUID

      // Default names
      val title       = oneString("Mr, Mrs, Miss, Doctor ...")
      val firstName   = oneString("Johan")
      val middleNames = oneString("Sebastian")
      val lastName    = oneString("Bach")
      val suffix      = oneString("Jr, Sr ...")
      val name        = oneString("Johan Sebastian Bach (for optimized queries)")
      val nameSort    = oneString("Bach, Johann Sebastian (for optimized queries)")

      // Multilingual names
      val firstNameL   = mapString("First name in various languages")
      val middleNamesL = mapString("Middle names in various languages")
      val lastNameL    = mapString("Last name in various languages")
      val nameL        = mapString("Full name in various languages")
      val nameSortL    = mapString("Full name by last name in various languages")

      val nickName = oneString
      val aliases  = setString("All name variations, nicknames and misspellings (a la MusicBrainz)")

      val gender = oneString.allowedValues("female", "male", "transgender")

      val mother = one[Person]
      val father = one[Person]

      val born      = oneDate
      val bornDay   = oneInt
      val bornMonth = oneInt
      val bornYear  = oneInt

      val dead      = oneDate
      val deadDay   = oneInt
      val deadMonth = oneInt
      val deadYear  = oneInt

      val birthPlace       = oneString("Unresolved birth place")
      val birthAreaStr     = oneString("Unresolved birth area")
      val birthCity        = one[loc.City]
      val birthArea        = one[loc.Area]
      val birthCountry     = one[loc.Country]
      val birthCountryPrev = one[loc.Country]

      val deadPlace       = oneString("Unresolved dead place")
      val deadAreaStr     = oneString("Unresolved dead area")
      val deadCity        = one[loc.City]
      val deadArea        = one[loc.Area]
      val deadCountry     = one[loc.Country]
      val deadCountryPrev = one[loc.Country]

      val nationality  = one[loc.Country]
      val nationality2 = one[loc.Country]
      val nationality3 = one[loc.Country]
      val livesIn      = many[loc.Country]

      val srcs    = many[data.Src]
      val links   = many[loc.Web]
      val ranks   = many[com.Rank]
      val comment = oneString("Internal comments")
    }

    // Profession
    trait ProfCat {
      val name = oneString
    }

    trait Profession {
      val cat  = one[ProfCat]
      val name = oneString
    }

    trait Occupation {
      val person     = one[Person]
      val profession = one[Profession]
      val graduated  = oneDate
      val retired    = oneDate
      val status     = oneString.allowedValues("amateur", "nonactive", "professional", "retired", "semiprofessional", "student")
    }
  }


  // Organization ------------------------------------------------------------------------------ 6

  object org {

    trait OrgType {
      val name = oneString.fulltext
    }

    trait Org {
      val uuid            = oneUUID
      val name            = oneString.fulltext
      val subTitle        = oneString.fulltext
      val nameSorting     = oneString.fulltext
      val nameFormal      = oneString.fulltext
      val nameAlternative = oneString.fulltext
      val localName       = oneString.fulltext
      val localSubTitle   = oneString.fulltext
      val tpes            = many[OrgType]
      val owners          = many[ind.Person]
      val parentOrg       = one[Org]
      val address         = one[loc.Address]
      val tel             = one[loc.Tel]
      val email           = oneString

      val links = many[loc.Web]

      val url = oneString("Official website")
    }

    trait OrgUnit {
      val org    = one[Org]
      val partOf = one[OrgUnit]
    }

    trait OrgRole {
      val name    = oneString.unique
      val email   = oneString
      val orgUnit = one[OrgUnit]
      val from    = oneDate
      val to      = oneDate
    }

    trait OrgAssoc {
      val org        = one[Org]
      val orgRole    = one[OrgRole]
      val person     = one[ind.Person]
      val occupation = one[ind.Occupation]
      val from       = oneDate
      val to         = oneDate
    }
  }


  // Music ------------------------------------------------------------------------------------- 7

  object music {

    trait EnsType {
      val uuid       = oneUUID
      val family     = many[EnsType]
      val abbr       = oneString("English abbreviation")
      val name       = oneString("English name")
      val nameL      = mapString
      val descr      = mapString("English description")
      val descrL     = oneString
      val scoreOrder = oneInt
      val no         = oneInt("No of players (if fixed)")
      val links      = many[loc.Web]
    }

    trait Instr {
      val uuid       = oneUUID
      val family     = many[Instr]
      val scoreOrder = oneInt
      val inOrch     = oneInt
      val in         = oneString.allowedValues("A", "Ab", "Asharp", "B", "Bb", "C", "Cb", "Csharp", "D", "Db", "Dsharp", "E", "Eb", "Esharp", "F", "Fb", "Fsharp", "G", "Gb", "Gsharp")
      val main       = oneString
      val abbr       = oneString("English/international abbreviation")
      val abbrPl     = oneString("English/international abbreviation plural")
      val name       = oneString("English name")
      val namePl     = oneString("English name plural")
      val descr      = oneString("English description")
      val nameL      = mapString("Multilingual name")
      val namePlL    = mapString("Multilingual name plural")
      val descrL     = mapString("Multilingual description")
      val hs         = oneString("Hornbostel–Sachs number")
      val links      = many[loc.Web]
    }

    // Werk Verzeichnis
    trait WvRef {
      val uuid     = oneUUID
      val abbr     = oneString("BWV, KV etc")
      val version  = oneString("1, 2 etc for BWV1, BWV2 etc.")
      val primacy  = oneInt.noHistory("Primacy of this WV")
      val primary  = oneBoolean("true for the currently most authoritative verzeignis")
      val abbrAlt  = setString("Occasionally used alternative abbreviations: BW, BV, B")
      val name     = oneString("Bach Werk Verzeichnis")
      val descr    = oneString("Description of Verzeichnis")
      val composer = one[ind.Person]("Composer of this verzeignis")
      val authors  = many[ind.Person]("Authors of verzeignis")
      val links    = many[loc.Web]("Links to opus/wv sites")
    }

    trait Wv {
      val ref = one[WvRef]("WerkVerzeichnis reference: 'BWV', 'opus' etc")
      val str = oneString("All as text: BWV Anh.II a723b/c13d posth")

      val vol       = oneString("Anh. II, Book 4 etc..")
      val volCardNo = oneInt.noHistory("No. in volume")
      val posth     = oneBoolean("Posthume or not")

      val prefix = oneString("a")
      val no     = oneInt("723 (only if single number)")
      val nos    = oneString("723-725. Alternative for multiple numbers")
      val suffix = oneString("b")

      val cardPrefix = oneString("c")
      val cardNo     = oneInt("13 (only if single number)")
      val cardNos    = oneString("13-15. Alternative for multiple cardinality numbers")
      val cardSuffix = oneString("d")

      val alt = oneString("Anh.II a724b/c14d posth [String without BWV]")
      val n   = oneLong.noHistory("Placeholder for Long")
      val wv1 = oneString("no prefix$ suffix$ cardPrefix$ cardNo$ cardNos$ cardSuffix$ vol$ volCardNo$")
      val wv2 = oneString.noHistory("no prefix$ suffix$ cardPrefix$ cardNo$ cardNos$ cardSuffix$")
      val wv3 = oneString.noHistory("no prefix$ suffix$ cardNo$ cardNos$")
      val wv4 = oneString.noHistory("no cardNo$ cardNos$")
    }

    trait Tonality {
      val key         = oneString("C, C-flat, C-sharp, Any, All, Various etc...")
      val text        = oneString("Tonality as text: Mode VI Hypolydian on F")
      val modePrefix  = oneString("Mode VII")
      val mode        = oneString("Major, Minor, Dorian, Phrygian, Atonal, 12-tone, etc.")
      val modeSuffix  = oneString("on F")
      val keyL        = mapString("Multilingual: C, Ces, Cis, Any, All, Various etc...")
      val textL       = mapString("Multilingual: Tonality as text: Mode VI Hypolydian on F")
      val modePrefixL = mapString("Multilingual: Mode VII")
      val modeL       = mapString("Multilingual: Dur, Moll, Dorian, Phrygian, Atonal, 12-tone, etc.")
      val modeSuffixL = mapString("Multilingual: i F")
      val order       = oneInt("Ordering index (?)")
    }

    trait Meter {
      val top      = oneInt("3 in 3/4")
      val bot      = oneInt("4 in 3/4")
      val compound = many[Meter]("3/4 6/8 compound")
    }

    trait Tempo {
      val name  = oneString("Tempo as text: Allegro, Adagio etc")
      val nameL = mapString("International names")
      val bpm   = oneString("Beats per minute (for bpmUnit)")
      val unit  = oneString("Beat unit: Half note, Quarter note, etc.")
    }

    trait Genre {
      val name  = oneString
      val nameL = mapString("International names")
    }

    trait WorkType {
      val name    = oneString("Default work type name: Arrangement, Compilation, Reduction, etc")
      val namePl  = oneString("Default plural work type name: Arrangements, Compilations, Reductions, etc")
      val nameL   = mapString("International names")
      val namePlL = mapString("International plural names")
    }

    trait WorkTag {
      val name    = oneString("Default work tag name: Symphony, Rondo, Concerto, Hymn, Song, etc")
      val nameL   = mapString("International names")
      val namePl  = oneString("Default plural work tag name: Symphonies, Rondos, Concertos, Hymns, Songs, etc")
      val namePlL = mapString("International plural names")
    }

    trait Libretto {
      val lang      = oneString("Language iso3: eng, dan, etc")
      val authorTxt = oneString("Authors as text")
      val author    = many[ind.Person]("Authors")
      val text      = oneString("Text")
    }

    trait WorkDuration {
      val src  = one[data.Src]
      val raw  = oneString("Duration as text")
      val mins = oneInt("Total duration in minutes")
      val hr   = oneInt("Part of duration: hours")
      val min  = oneInt("Part of duration: minutes")
      val sec  = oneInt("Part of duration: seconds")
    }

    trait WorkSrc {
      val src                = one[Clazzig.data.Src].owner("Id of source like IMSLP")
      val uuid               = oneString("Id in source")
      val link               = one[loc.Web].owner("Link to work at specific source")
      val uuid2              = oneString("Secondary id in source")
      val link2              = one[loc.Web].owner("Secondary link to work at specific source")
      val data               = mapString("key/data pairs from source")
      val subWorksRaw        = oneString("Raw sub work titles")
      val basedOnRaw         = oneString("Raw text references to work(s) that this work is directly based on")
      val relToRaw           = oneString("Raw text references to work(s) that this work is more loosely related to")
      val instrPrefixRaw     = oneString("Raw single instrument prefix text: 'violin' in '6 violin sonatas'")
      val titleRaw           = oneString("Raw full title from source")
      val forInstrsRaw       = oneString("Raw instrumentation suffix text: (for) 'piano und orkester'")
      val instrumentationRaw = oneString("Raw instrumentation text: '3(fl)-2(ob)-p")
      val tonalityRaw        = oneString("Raw tonality text (when messy)")
      val tonalitiesRaw      = oneString("Raw tonalities text (when messy)")
      val firstPublRaw       = oneString("Raw first published text")
      val premiereRaw        = oneString("Raw premiere text")
      val compTime           = oneString("Raw composed time text")
      val s1                 = oneString.noHistory
      val ss1                = setString.noHistory
    }

    // Terms and common titles
    trait Term {
      val en               = oneString.fulltext("English term name (for easy access)")
      val generic          = oneInt.noHistory
      val allNames         = setString.fulltext("Term names in all languages")
      val allNamesWithSubs = setString.fulltext("Term names in all languages for all sub terms")
      val names            = many[TermName].owner("Language variations")
      val relTerms         = many[Term]("Terms relating to this term (Sonatine a sub of Sonata)")
    }

    trait TermName {
      val lang    = oneString("Language code")
      val all     = setString.fulltext("All term name variations in this language")
      val name    = oneString.fulltext("Term name in this language, ex 'Symfoni'")
      val namePl  = oneString.fulltext("Term name in plural, ex 'Symfonie'")
      val name2   = oneString.fulltext("Alternative term name, ex 'Symphoni'")
      val name2Pl = oneString.fulltext("Alternative term name in plural, ex 'Symphonie'")
      val name3   = oneString.fulltext("3rd alternative name")
      val name3Pl = oneString.fulltext("3rd alternative name in plural")
      val wikiUrl = oneString("Wiki page for term name in a specific language")
    }

    // Title language matrix
    trait WorkTitle {
      val lang     = oneString("ISO 639-1 language code (2 letters)")
      val in       = setString("Countries (iso3) using this title. 'Ein Heldenleben' in Denmark: den, swe ... all(?)")
      val original = oneBoolean("Is title original from the composer? Nil if not")
      val default  = oneBoolean("Is title default? Nil if not")

      val term     = one[Term]("Term matching title")
      val termName = one[TermName]("Specific term name matching title in this language (if known)")
      val terms    = many[Term]("Multiple terms related to this work/title")

      val titleArticle    = oneString("'The', 'A' and other non-sorting first articles of title")
      val title           = oneString.fulltext("Main (formal) title")
      val title1          = oneString.noHistory.fulltext("Main title in other language (to be extracted to own entity)")
      val lang1           = oneString.noHistory("Language code of title1")
      val title2          = oneString.noHistory
      val lang2           = oneString.noHistory
      val title3          = oneString.noHistory
      val lang3           = oneString.noHistory
      val title4          = oneString.noHistory
      val lang4           = oneString.noHistory
      val altTitleArticle = oneString("'The', 'A' and other non-sorting first articles of altTitle")
      val altTitle        = oneString.fulltext("Alternative/descriptive title: 'D-day Sonata'")
      val altTitle1       = oneString.noHistory("altTitle in other language (to be extracted)")
      val altTitle2       = oneString.noHistory
      val altTitle3       = oneString.noHistory
      val subTitle        = oneString.fulltext("Subtitle, like 'for the fallen soldiers'")
      val subTitle1       = oneString.noHistory("subTitle in other language (to be extracted)")
      val subTitle2       = oneString.noHistory
      val subTitle3       = oneString.noHistory
      val titleRomanized  = oneString.fulltext("Romanized title of russian etc")
      val vol             = oneString("Volume, cycle etc")
      val version         = oneString("Version referred to, like '1912 version', 'arr. LeFevre' etc")
      val onRaw           = oneString("Based-on work raw text to be extracted")
      val on              = oneString.noHistory("Connecting words to based-on work")
      val onWork          = oneString.noHistory("Based-on work title")
      val from            = oneString.noHistory("Connecting words to from-work")
      val fromWork        = oneString.noHistory("From-work")
      val by              = oneString.noHistory("Connecting words to by-composer")
      val byComposer      = oneString.noHistory("Referred composer/author etc")
      val subworkTitle    = oneString.noHistory("Title of possible sub work to be extracted")
      val forInstrs       = oneString("Short instrumentation text: 'For piano and orchestra'")
      val workType        = oneString("Type of work (transcript, arr etc) to be extracted to Work")
      val workKey         = oneString("Key of work to be extracted")
      val workTempo       = oneString("Tempo (=90, Presto etc) to be extracted to Work")
      val workVersion     = oneString("Version of work (1. fassung etc) to be extracted to Work")
      val workWV          = oneString("Work wv to be extracted to abstract work opus/wv")
      val forEntity       = oneString("Specific entity (event/person/place etc) that this piece is written to")

      val compComments = oneString("Composer comments")
      val dedication   = oneString("Dedication text")
      val subWorksTxt  = oneString("Description of sub works: 'In 4 Movements', etc")

      // Generated combinations (combining with language-independent data in Work) - todo
      val s         = oneString.noHistory.fulltext("Internal filter by string")
      val s2        = oneString.noHistory
      val s3        = oneString.noHistory
      val todo      = oneString.noHistory.fulltext("Check later")
      val todoLater = oneString("Data task to be done later")
      val todos     = setString("Cleanups to do")
      val actions   = setString("Resolved actions")
      val cleared   = oneInt
      val comment   = oneString("Internal comments or raw mixed data")

      val t1 = oneString.noHistory("title FingerPrint")
      val t2 = oneString.noHistory("title NGramFingerprint")
      val t3 = oneString.noHistory("title ColognePhonetic")
      val t4 = oneString.noHistory("title DoubleMetaphone")
      val t5 = oneString.noHistory("title Metaphone")
      val t6 = oneString.noHistory("title Metaphone3")
      val t7 = oneString.noHistory("title Soundex")
    }

    // Hyper edge connecting Work to AbstractWork/Work
    trait SubWork {
      val abstractWork = one[AbstractWork]("AbstractWork of sub work")
      val version      = one[Work].owner("Optional work version (if different from parent work)")
      val subWorks     = many[SubWork].owner("Further sub works")

      val parentSubWork = one[SubWork]("Parent SubWork hyperedge (if any) for upward traversal")
      val topVersion    = one[Work]("Initial work version in this graph (can be multiple levels up)")

      val order = oneInt("Ordering index when multiple sub works")
    }

    // Original/intrinsic work. Can be part of a parent abstract work
    trait AbstractWork {
      val srcs = many[WorkSrc].owner("Work data sources.")

      val uuid = oneUUID
      val opus = one[Wv].owner("Opus number")
      val wvs  = many[Wv].owner("Werk Verzeignis numbers")

      // Composer
      val composer     = one[ind.Person]("Composer of this work")
      val secComposers = many[ind.Person]("Secondary composers like editors, arrangers etc adding original input to the work")

      // Graph/relationships
      val basedOn     = many[AbstractWork]("AbstractWorks that this work is heavily based on (but still has its own compositional integrity)")
      val relTo       = many[AbstractWork]("AbstractWorks that this work is more loosely related to in various ways (InspiredBy, Compilation, UsedIn, etc)")
      val parts       = many[AbstractWork].owner("Parts of this work: Movements, Parts, Acts etc.")
      val partsType   = one[WorkType]("Movements, Parts, Acts, Sections etc.")
      val partOf      = one[AbstractWork]("Parent AbstractWork that this sub work is a part of (redundant with `parts` of parent work)")
      val order       = oneInt("Ordering index of this sub work along other sub-works (like Movements)")
      val orderSuffix = oneString("Suffix to order number, like 'a' in 11a")
      val versions    = many[Work]("Versions of this work like instrumentation, arrangement, piano reductions etc")
      val origVersion = one[Work]("Original version (like Mussorgsky piano suite Pictures at an exhibition)")
      val defVersion  = one[Work]("Popular version (like Ravels orchestration of Pictures at an exhibition)")

      // Meta
      val ctx      = oneString("Context level of work hierarchy: cycle (The Ring), part/act, Nil (original), mvt, section, etc.")
      val ctxLevel = oneInt("-1: Multiple concert cycle size (The Ring), Nil/0: Full work (max concert size), 1: act/part/mvt, 2: mvt/section/aria, 3: section/aria/excerpt")
      val tpe      = one[WorkType]("Type of original: Arrangement, Compilation, Reduction etc. Nil if original work")
      val tags     = many[WorkTag]("Tag of original: Symphony, Rondo, Sonata, Concerto etc")
      val genre    = one[Genre]("Genre?")

      // Title data
      val term             = one[Term]("Specific term representing this abstract work")
      val terms            = many[Term]("Terms relating to this abstract work (if multiple)")
      val titles           = many[WorkTitle].owner("Multilingual title matrix")
      val title            = oneString("Default raw title for easy access")
      val workCount        = oneInt("Count of works in this work collection. 6 in '6 Sonatas'")
      val workCountIsDigit = oneBoolean("Flag if work count was digit(s) in original")
      val titlePrefixNo    = oneInt("Number, first in title (not a workCount). 11 in '11 Gates'")
      val titleNo          = oneInt("Title number (sortable): '7' of 'Sonata No. 7a'")
      val titleNoSuffix    = oneString("Title number suffix: 'a' of 'Sonata No. 7a'")
      val titleNos         = oneString("Multiple title numbers (bad for sorting): '7-14'")
      val titleNosEnd      = oneInt("Title number range end: '14' of 'Sonatas Nos. 7-14'")

      // Creation time of this work (could be unrelated to any specific work version)
      val begYear  = oneInt("Composed begun, year")
      val begMonth = oneInt("Composed begun, month")
      val begDay   = oneInt("Composed begun, day")
      val year     = oneInt("Composed (finished), year")
      val month    = oneInt("Composed (finished), month")
      val day      = oneInt("Composed (finished), day")

      val links = many[loc.Web]
      val notes = many[adm.Note].owner("Internal notes")
      val todo  = oneString.noHistory
      val t1    = oneString.noHistory
      val t2    = oneString.noHistory
    }

    // Concrete versions of 'AbstractWork' (Instrumentation, Arr...)
    trait Work {
      val srcs = many[WorkSrc].owner("Work version data sources.")

      val uuid = oneUUID
      val opus = one[Wv].owner("Opus number")
      val wvs  = many[Wv].owner("Werk Verzeignis numbers")

      val composer  = one[ind.Person]("Composer of this work version - can be other composers making arrangements etc")
      val composers = many[ind.Person]("If more than one composer")

      // Graph/relationships
      val abstractWork = one[AbstractWork]("Abstract work of this child work (redundant to AbstractWork.parts) - for easy upward traversal")
      val subWorks     = many[AbstractWork].owner("Sub works of this parent work")
      val subWorksType = one[WorkType].owner("Movements, Parts, Acts, Sections etc.")

      // Meta
      val original = oneBoolean("Is this version the original (Mussorgskys Pictures...)? Nil/false if not")
      val default  = oneBoolean("Is this version considered the default version (Ravels Pictures orchestration)? Nil/false if not")
      val tpe      = one[WorkType].owner("Arrangement, Compilation, Reduction etc. Nil if original work")
      val tags     = many[WorkTag].owner("Symphony, Rondo, Sonata, Concerto etc")
      val genre    = one[Genre]("Genre (if different from work?)")

      // Version title data
      val term   = one[Term]("Specific term/title representing this work")
      val terms  = many[Term]("Terms relating to this abstract work (if multiple)")
      val title  = oneString("Default raw version title for easy access")
      val titles = many[WorkTitle].owner("Multilingual version title matrix - mostly for instrumentation naming and version titles etc - things that divert from the Work title")

      // Instrumentation
      val ensType    = one[EnsType].owner("Ensemble type of instrumentation")
      val ens        = many[Ens].owner("Ensembles of this instrumentation")
      val altEns     = many[Ens].owner("Alternative ensembles (for alternative instrumentations)")
      val soloInstr  = one[Instr].owner("Solo instrument ref, vn ref in: Violin Concerto No 2")
      val soloInstrs = many[Instr].owner("Solo instruments refs, vn, vcl refs in: Duo concertante for Violin, Cello and orchestra")

      // Musical attributes
      val tonalityRaw = oneString("Tonality text (when messy)")
      val tonality    = one[Tonality]("Main tonality of work")
      val tonalities  = many[Tonality]("Multiple tonalities of work")
      val meter       = one[Meter]("Main meter")
      val meters      = many[Meter]("Multiple meters")
      val tempo       = one[Tempo]("Main tempo: Allegro / 1/4 = 120 etc")
      val tempos      = many[Tempo]
      val durs        = many[WorkDuration].owner("Recorded and stated work durations")
      val origLang    = setString("Original language(s) involved")
      val librettos   = many[Libretto].owner("Librettos/texts for this work")

      // Categorizations
      val rate    = oneInt("Level 3 (of 5)")
      val rateMin = oneInt("5 levels")
      val rateMax = oneInt("5 levels")

      // Creation of this version
      val begYear  = oneInt("Composed begun, year")
      val begMonth = oneInt("Composed begun, month")
      val begDay   = oneInt("Composed begun, day")
      val year     = oneInt("Composed (finished), year")
      val month    = oneInt("Composed (finished), month")
      val day      = oneInt("Composed (finished), day")
      val place    = oneString("Composed place as text")
      val long     = oneString("Composed at location longitude")
      val lat      = oneString("Composed at location latitude")

      // First publishing of this version
      val firstPublYear  = oneInt("First published, year")
      val firstPublMonth = oneInt("First published, month")
      val firstPublDay   = oneInt("First published, day")
      val firstPublPlace = oneString("First published, place")
      val firstPubl      = one[org.Org]("First publisher")

      // Premiere of this version
      val premiere     = one[event.Event]("First concert/performance in ")
      val premiereYear = oneInt("First performed, year")

      val links = many[loc.Web]
      val notes = many[adm.Note].owner("Internal notes")
    }

    trait Compilation {
      val excerpts = many[Excerpt]
    }

    trait Excerpt {
      val workVersion = one[Work]
      val fromBar     = oneInt
      val fromNumber  = oneInt("From big rehearsal number")
      val fromLetter  = oneString("From big rehearsal letter")
      val toBar       = oneInt
      val toNumber    = oneInt("To big rehearsal number")
      val toLetter    = oneString("To big rehearsal letter")
    }

    trait Ens {
      val tpe           = one[EnsType]
      val instrSections = many[InstrSection].owner
      val no            = oneInt("Number of players. Not asserted if unspecified (like choir etc)")
      val min           = oneInt("Minimum number of players")
      val max           = oneInt("Maximum number of players")
      val pos           = oneInt("Position of ensemble (ch1, ch2)")
      val altEns        = many[Ens].owner("Alternative ensembles (instead of this ensemble)")
      val subEns        = many[Ens].owner("Sub ensembles like temporary offstage orchestra (orch musicians go out and in)")
      val name          = oneString
      val nameL         = mapString
      val comment       = oneString
      val commentL      = mapString
      val keyws         = setString("'offstage', 'tempOffstage', 'ampd' etc...")
      val optional      = oneBoolean("If ensemble is optionally required")
    }

    trait InstrSection {
      val instr            = one[Instr]("Main instrument family of this section")
      val no               = oneInt("Number of players (Preferred)")
      val players          = many[InstrPlayer].owner("Individual musicians playing in this section. Suitable to specify individual requirements.")
      val min              = oneInt("Minimum number of players")
      val max              = oneInt("Maximum number of players")
      val est              = oneBoolean("If number of players is an estimation.")
      val pos              = oneInt("Position of section (vn1, vn2)")
      val instrsPlayed     = many[InstrPlayed].owner("Specific instruments played if not specified through players (typically percussion instruments)")
      val altInstrSections = many[InstrSection].owner("Alternative instrument sections")
      val abbr             = oneString("Abbreviated name of section if not taken from instrument family name. E.g. 'vn1' or 'bassesLeft'.")
      val name             = oneString("Name of section if not taken from instrument family name. E.g. 'Violin 1' or 'Basses on the left' (in symmetric orch for instance).")
      val in               = oneString("'C5', 'Eb' etc...")
      val comment          = oneString
      val keyws            = setString("'offstage', 'tempOffstage', 'ampd', 'wordless' etc..")
      val optional         = oneBoolean("If instrument section is optionally required")
      val confirmation     = oneInt("Level of certainty that this is correct. 0: unknown/to be confirmed")
    }

    trait InstrPlayer {
      val pos             = oneInt("Position in section (1, 2, 3...) if individual part.")
      val no              = oneInt("Number of players if the same part is played. For instance '12 violins'.")
      val instrsPlayed    = many[InstrPlayed].owner("Instruments played by a musician in a work.")
      val min             = oneInt("Minimum number of players per part")
      val max             = oneInt("Maximum number of players per part")
      val altInstrsPlayed = many[InstrPlayed].owner("Group of alternative instruments played")
      val comment         = oneString
      val keyws           = setString("'offstage', 'tempOffstage', 'ampd' etc..")
      val optional        = oneBoolean("If player is optionally required")
    }

    trait InstrPlayed {
      val no              = oneInt("Number of same instruments (only assert if more than 1).")
      val min             = oneInt("Minimum number of instruments per player")
      val max             = oneInt("Maximum number of instruments per player")
      val instr           = one[Instr]
      val in              = oneString("'C5', 'Eb' etc...")
      val altInstrsPlayed = many[InstrPlayed].owner("Alternative instruments played compared to main instr played")
      val comment         = oneString
      val keyws           = setString("'ampd', 'muted' etc..")
      val optional        = oneBoolean("If instrument played is optionally required")
      val confirmation    = oneInt("Level of certainty that this is correct. 0: unknown/to be confirmed")
    }

    trait Part {
      val edition = one[Edition]
      val instrs  = many[Instr]
      val title   = oneString
      val comment = oneString
      val links   = many[loc.Web]
    }

    trait Edition {
      val uuid         = oneUUID
      val works        = many[AbstractWork]("Works in this edition (could be multiple)")
      val workVersions = many[Work]("Work versions in this edition (could be multiple)")
      val editors      = many[ind.Person]
      val editDate     = oneDate("Edited date")
      val publisher    = one[org.Org]
      val published    = oneDate("Published date")
      val publYear     = oneInt("Published year")
      val publMonth    = oneInt("Published month")
      val publDay      = oneInt("Published day")
      val reprintOf    = one[Edition]
      val title        = oneString("'Critical revision', 'Arrangement' etc...")
      val comment      = oneString
      val publRef      = oneString
      val links        = many[loc.Web]
    }
  }


  // Performers -------------------------------------------------------------------------------- 8

  object perf {

    trait EnsembleType {
      val name = oneString
    }

    trait Ensemble {
      val uuid            = oneUUID
      val name            = oneString.fulltext
      val nameShort       = oneString.fulltext
      val nameSorting     = oneString.fulltext
      val subTitle        = oneString.fulltext
      val officialName    = oneString.fulltext
      val nameAlternative = oneString.fulltext
      val localName       = oneString.fulltext
      val localNameShort  = oneString.fulltext
      val localSubTitle   = oneString.fulltext
      val abbr            = oneString.fulltext
      val venue           = one[event.Place]
      val org             = one[Clazzig.org.Org]
      val address         = one[loc.Address]
      val links           = many[loc.Web]

      val tel       = many[loc.Tel]
      val context   = oneString.allowedValues("festival", "opera", "own")
      val frequency = oneString.allowedValues("adHoc", "fulltime", "partTime", "projectBased", "scheduled")
      val size      = oneString.allowedValues("chamber", "chamberOrch", "orch")
      val status    = oneString.allowedValues("amateur", "pro", "semipro", "student", "youth")
      val proLevel  = oneString.allowedValues("A", "B", "C")
    }

    // Real instrument ("Stradivarius "Del Gesu", 1748)
    trait Instrument {
      val uuid             = oneString
      val tpe              = one[music.Instr]
      val uuidentification = oneString
      val maker            = one[org.Org]
      val makerPerson      = one[ind.Person]
      val buildYear        = oneInt
      val owner            = one[InstrumentOwner]
      val pics             = many[io.File]
      val links            = many[loc.Web]
    }

    trait Musician {
      val person = one[ind.Person]
      val instr  = one[music.Instr]
      val start  = oneDate
      val active = oneBoolean
      val end    = oneDate
      val status = oneString.allowedValues("bi", "main", "occasional")
      val level  = oneString.allowedValues("A", "B", "C", "D", "formerPro", "pro", "semipro")
    }

    // "Plays the Stradivarius, Del Gesu from 1748"
    trait InstrumentPlayer {
      val person     = one[ind.Person]
      val instrument = one[Instrument]
      val start      = oneDate
      val end        = oneDate
      val preference = oneInt
    }

    // "Owns the Stradivarius, Del Gesu from 1748"
    trait InstrumentOwner {
      val org          = one[Clazzig.org.Org]
      val person       = one[ind.Person]
      val instrument   = one[Instrument]
      val bought       = oneDate
      val buyingPrice  = one[loc.Money]
      val sold         = oneDate
      val sellingPrice = one[loc.Money]
      val commission   = one[loc.Money]
    }

    trait EnsMember {
      val person   = one[ind.Person]
      val ensemble = one[Ensemble]
      val assoc    = oneString.allowedValues("longterm", "permanent", "shortterm")
      val instr    = one[music.Instr]
      val start    = oneDate
      val end      = oneDate
    }
  }


  // Concert, rehearsal, lesson(?) etc --------------------------------------------------------- 9

  object event {

    // Venue, concert hall, class room, rehearsal studie, park, shopping mall etc
    trait PlaceType {
      val name = oneString.unique
    }

    // Venue, class room
    trait Place {
      val uuid           = oneString
      val parentPlace    = one[Place]("Enclosing place. Rehearsal studio (this child place) of Opera House (parent)")
      val tpe            = one[PlaceType]
      val name           = oneString.fulltext
      val nameShort      = oneString.fulltext
      val nameSort       = oneString.fulltext
      val subTitle       = oneString.fulltext
      val officialName   = oneString.fulltext
      val nameAlt        = oneString.fulltext
      val localName      = oneString.fulltext
      val localNameShort = oneString.fulltext
      val org            = one[Clazzig.org.Org]
      val address        = one[loc.Address]
      val links          = many[loc.Web]
    }

    trait Production {
      val start = oneDate
      val end   = oneDate
      val week  = oneInt
      val title = oneString
    }

    trait EventType {
      val name = oneString
    }

    trait Event {
      val tpe      = many[EventType]
      val placeStr = oneString("Location as text")
      val place    = one[Place]

      val date  = oneDate("Date of event")
      val year  = oneInt("Year of event")
      val month = oneInt("Month of event")
      val day   = oneInt("Day of event")

      val date2  = oneDate("End date if spanning several days")
      val year2  = oneInt("End year of event")
      val month2 = oneInt("End month of event")
      val day2   = oneInt("End day of event")

      val isWorldPremiere = oneBoolean
      val premiereIn      = oneString("")
      val links           = many[loc.Web]
    }
  }


  // Education --------------------------------------------------------------------------------- 10

  object edu {

    // Conservatories/Universities offering master education level
    trait University {
      val uuid         = oneString
      val org          = one[Clazzig.org.Org]
      val officialName = oneString.fulltext
      val localName    = oneString.fulltext
      val name         = oneString.fulltext
      val abbr         = oneString.fulltext
      val cat          = oneString.allowedValues("conservatory", "university")
      val address      = one[loc.Address]
      val tel          = many[loc.Tel]
      val links        = many[loc.Web]
    }

    trait MusicSchool {
      val uuid         = oneString
      val org          = one[Clazzig.org.Org]
      val officialName = oneString.fulltext
      val localName    = oneString.fulltext
      val name         = oneString.fulltext
      val abbr         = oneString.fulltext
      val address      = one[loc.Address]
      val tel          = many[loc.Tel]
      val links        = many[loc.Web]
    }
  }


  // Product ----------------------------------------------------------------------------------- 11

  object prod {

    trait ProdCat {
      val uuid   = oneString
      val name   = oneString
      val parent = one[ProdCat]
    }

    trait Prod {
      val name   = oneString
      val parent = one[Prod]
    }

    trait Product {
      val uuid         = oneString
      val name         = oneString
      val instr        = one[music.Instr]
      val manufacturer = one[org.Org]
    }
  }


  // Instrument business ----------------------------------------------------------------------- 12

  object lut {

    trait InstrMaker {
      val uuid      = oneString
      val org       = one[Clazzig.org.Org]
      val name      = oneString.fulltext
      val parent    = one[ind.Person]
      val maker     = one[ind.Person]
      val employees = many[ind.Person]
      val address   = one[loc.Address]
      val tel       = many[loc.Tel]
      val links     = many[loc.Web]
    }

    trait InstrumentMaker {
      val uuid   = oneUUID
      val shop   = one[InstrMaker]
      val person = one[ind.Person]
    }

    trait Repairer {
      val uuid      = oneString
      val org       = one[Clazzig.org.Org]
      val name      = oneString.fulltext
      val employees = many[ind.Person]
      val address   = one[loc.Address]
      val tel       = many[loc.Tel]
      val links     = many[loc.Web]
    }

    trait Manufacturer {
      val uuid = oneUUID
      val org  = one[Clazzig.org.Org]
      val name = oneString.fulltext
    }
  }


  // Insurance --------------------------------------------------------------------------------- 13

  object ins {

    trait Insurer {
      val name    = oneString
      val address = one[loc.Address]
    }

    trait Insurance {
      val instrument        = one[perf.Instrument]
      val insuranceContract = one[InsuranceContract]
      val valuation         = one[Valuation]
    }

    trait InsuranceContract {
      val insurer        = one[Insurer]
      val insuredPerson  = one[ind.Person]
      val insuredOrg     = one[org.Org]
      val start          = oneDate
      val expires        = oneDate
      val sum            = one[loc.Money]
      val annualFee      = one[loc.Money]
      val insuranceTerms = many[io.File]
      val agency         = one[org.Org]
      val agent          = many[org.OrgAssoc]
    }

    trait Valuation {
      val instrument    = one[perf.Instrument]
      val date          = oneDate
      val valuator      = one[org.OrgAssoc]
      val newValue      = one[loc.Money]
      val currencyDate  = oneDate
      val currencyRate  = oneFloat
      val newValueLocal = one[loc.Money]
    }

    trait Incident {
      val insurerRef    = oneString
      val instrument    = one[perf.Instrument]
      val reported      = oneDate
      val tpe           = oneString.allowedValues("damage", "theft")
      val damage        = oneString.allowedValues("major", "minor", "total")
      val date          = oneDate
      val location      = one[loc.Address]
      val reporter      = one[ind.Person]
      val description   = oneString
      val damagePics    = many[io.File]
      val repairActions = many[RepairAction]
      val status        = oneString.allowedValues("being_repaired", "in_transit_from_repairer", "in_transit_to_repairer", "no_action_yet", "pending_repair_cost_valuation", "received_from_repairer")
    }

    trait RepairAction {
      val date         = oneDate
      val tpe          = oneString.allowedValues("expenses", "other", "repair", "transport", "valuation")
      val luthier      = one[org.Org]
      val repairer     = one[ind.Person]
      val musician     = one[ind.Person]
      val description  = oneString
      val estCost      = one[loc.Money]
      val cost         = one[loc.Money]
      val currencyDate = oneDate
      val currencyRate = oneFloat
      val costLocal    = one[loc.Money]
    }
  }
}
