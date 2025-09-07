package molecule.db.compliance.domains.relationship

import molecule.DomainStructure

object WebShop extends DomainStructure {

  trait Developer {
    val name = oneString

    // Developer --> Project via join table
    // flat
    // .Frontends.budget // plural reverse name
    // .Backends.budget
    // nested
    // .Frontends.*(Project.budget)
    // .Backends.*(Project.budget)

    // Developer --> join table
    // .Frontend_.role.Frontend.budget // no nested Frontend.budget!
    // .Backend_.role.Backend.budget
  }

  trait Project {
    val title  = oneString
    val budget = oneInt

    // Project --> Developer via join table
    // flat
    // .Designers.name // plural reverse name
    // .Engineers.name
    // nested
    // .Designers.*(Developer.name)
    // .Engineers.*(Developer.name)

    // Project --> join table
    // .Designer_.role.Designer.name // no nested Designer.name!
    // .Engineer_.role.Engineer.name
  }

  //  trait Frontend extends Join {
  trait Frontend {
    val designer = one[Developer] // (optional explicit plural reverse name)
    val frontend = one[Project]
    val role     = oneDate
    // val project = one[Project] // can't have multiple refs with same name to same entity (Project)

    // Direct access to join table
    // Frontend_.designer.frontend.role.insert(
    //   (1, 7, "Lead"),
    //   (2, 7, "Layout"),
    // ).transact
  }

  //  trait Backend extends Join {
  trait Backend {
    val engineer = one[Developer]
    val backend  = one[Project]
    val role     = oneDate
    // val project = one[Project] // can't have multiple refs with same name to same entity (Project)
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
