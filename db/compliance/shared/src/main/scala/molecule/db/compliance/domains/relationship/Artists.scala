package molecule.db.compliance.domains.relationship

import molecule.DomainStructure


object Artists extends DomainStructure {

  trait Artist {
    val name = oneString

    // .Works.price
    // .Works.*(Work.price)


    // Artist would most likely exist before works are added
    // Strange to define both from this side...
    // Artist.name.Works.*(Work.title.price).insert(
    //   ("Bob", List(
    //     ("Mona", 14),
    //     ("Hope", 20))
    //   )
    // ).transact

    // Doesn't make sense
    // Artist.name("Bob").Paintings.title("Mona").save
  }


  trait Work {
    val title  = oneString
    val price  = oneInt
    val artist = manyToOne[Artist] // transparently adds reverse ref "Works" (plural of this entity)
    //    val artist = one[Artist].oneToMany("Works") // explicit reverse ref "Works"


    // .painter
    // .Painter.name


    // Work.title("Mona").price(14).artist(artistId).save.transact
    //
    // Work.title.price.artist.insert(
    //   ("Mona", 14, artistId),
    //   ("Hope", 20, artistId),
    // ).transact
  }
}












//// one-to-many: Artist --> Work
//
//// flat
//Artist.name.Paintings.title
//Artist.name.Sculptures.title
//
//// nested
//Artist.name.Paintings.*(Work.title)
//Artist.name.Sculptures.*(Work.title)
//
//
//// many-to-one: Work --> Artist
//
//// flat
//Work.price.>(1000).Painter.name
//Work.price.>(1000).Sculpturer.name
//
//// (nested not applicable)
