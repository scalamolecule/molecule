package molecule.db.compliance.domains.relationship

import molecule.DomainStructure

object many2many_multiple4 extends DomainStructure {

  trait Developer {
    val name = oneString
    val frontends = manyToOne[Project]
  }

  trait Project {
    val title  = oneString
    val budget = oneInt
  }

  trait Frontend extends Join {
    val designer = oneToMany[Developer] // (optional explicit plural reverse name)
    val role     = oneDate
  }

  trait Backend extends Join {
    val engineer = oneToMany[Developer]("engineers")
    val backend  = oneToMany[Project]("backends")
    val role     = oneDate
  }
}

//// Employee --> Project
//
//// Join table property
//Developer.name.Frontend.role.Project.title
//Developer.name.Backend.role.Project.title
//
//// flat - plural names deducted from singular names
//Developer.name.Frontends.title
//Developer.name.Backends.title
//
//// nested
//Developer.name.Frontends.*(Project.title)
//Developer.name.Backends.*(Project.title)
//
//
//// Project --> Developer
//
//// Join table access
//Project.title.Frontend.role.Developer.name
//Project.title.Backend.role.Developer.name
//
//// flat
//Project.title.Designers.name
//Project.title.Engineers.name
//
//// nested
//Project.title.Designers.*(Developer.name)
//Project.title.Engineers.*(Developer.name)
