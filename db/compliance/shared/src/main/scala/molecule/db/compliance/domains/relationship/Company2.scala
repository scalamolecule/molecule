package molecule.db.compliance.domains.relationship

import molecule.DomainStructure


object Company2 extends DomainStructure {

  trait Employee {
    val name    = oneString
    val project = manyToMany[Project]("employee")
  }

  trait Project {
    val name   = oneString
    val budget = oneInt
  }
}


//Employee.name.Assignment.role.Project.name
//Employee.name.Projects.name
//Employee.name.Projects.*(Project.name)
//
//Employee.name.Assignment.role.Projects.name
//Employee.name.Projects.name
//Employee.name.Projects.*(Project.name)


////// direct join table query
////Assignments.employee.role.project.insert(
////  (bob, "lead", scala),
////  (bob, "engineer", java),
////  (liz, "manager", scala)
////).transact
//
//// Using the Assignment join table as any other referenced table (no need for `Project_`
//Employee.name.Assignment.role_("lead").Project.name.query.get ==> List(
//  ("Bob", "Scala")
//)
//
//// or `Projects` to query the joined data (transparently using the join table)
//Employee.name.Projects.name.query.get ==> List(
//  ("Bob", "Scala"),
//  ("Bob", "Java"),
//  ("Liz", "Scala")
//)
//
//// or nested without join property access
//Employee.name.Projects.*(Project.name).query.get ==> List(
//  ("Bob", List("Scala", "Java")),
//  ("Liz", List("Scala"))
//)
//
//
//
//
//
////// direct join table query
////Assignments.employee.role.projects.insert(
////  (bob, "lead", scala),
////  (bob, "engineer", java),
////  (liz, "manager", scala)
////).transact
//
//// Using the Assignment join table as any other referenced table (no need for `Project_`
//Employee.name.Assignment.role_("lead").Projects.name.query.get ==> List(
//  ("Bob", "Scala")
//)
//
//// or `Projects` to query the joined data (transparently using the join table)
//Employee.name.Projects.name.query.get ==> List(
//  ("Bob", "Scala"),
//  ("Bob", "Java"),
//  ("Liz", "Scala")
//)
//
//// or nested without join property access
//Employee.name.Projects.*(Project.name).query.get ==> List(
//  ("Bob", List("Scala", "Java")),
//  ("Liz", List("Scala"))
//)