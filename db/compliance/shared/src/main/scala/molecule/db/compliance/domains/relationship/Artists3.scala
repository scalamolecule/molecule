package molecule.db.compliance.domains.relationship

import molecule.DomainStructure


object Artists3 extends DomainStructure {

  trait Person {
    val name = oneString
  }

  // 2 separate 1:N relationships
  trait Work {
    val title      = oneString
    val price      = oneInt
    val painter    = manyToOne[Person]("paintings")
    val sculpturer = manyToOne[Person]("sculptures")
  }
}

//// one-to-many: Artist --> Work
//
//// flat
//Artist.name.Paintings.title // singular Painting?
//Artist.name.Sculptures.title
//
//// nested
//Artist.name.Paintings.*(Work.title) // plural Paintings
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
