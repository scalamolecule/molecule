package molecule.db.compliance.domains.relationship

import molecule.DomainStructure


object Artists2 extends DomainStructure {

  trait Artist {
    val name       = oneString
    val paintings  = oneToMany[Work]("painter")
    val sculptures = oneToMany[Work]("sculpturer")
  }

  // 2 separate 1:N relationships
  trait Work {
    val title      = oneString
    val price      = oneInt
    val painter    = manyToOne[Artist]("paintings")
    val sculpturer = manyToOne[Artist]("sculptures")
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
