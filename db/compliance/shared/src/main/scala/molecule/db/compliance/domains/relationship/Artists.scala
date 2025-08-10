package molecule.db.compliance.domains.relationship

import molecule.DomainStructure


object Artists extends DomainStructure {

  trait Artist {
    val name = oneString
  }

  // 2 separate 1:N relationships
  trait Work {
    val title      = oneString
    val price      = oneInt
    val painter    = one[Artist]("paintings")
    val sculpturer = one[Artist]("sculptures")
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
