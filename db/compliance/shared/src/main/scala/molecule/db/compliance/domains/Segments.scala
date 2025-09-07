package molecule.db.compliance.domains

import molecule.DomainStructure

object Segments extends DomainStructure {

  // General segment
  object gen {
    trait Profession {
      val name   = oneString
      val person = one[Person]
    }

    trait Person {
      val name         = oneString
      val gender       = oneString.allowedValues("male", "female")
      val reviewedBook = one[lit.Book]("Reviewers")
    }
  }


  // Literature segment
  object lit {
    trait Book {
      val title  = oneString
      val author = one[gen.Person]("Authors")

      // To avoid attr/partition name clashes we can prepend the definition object name
      // (in case we would have needed an attribute named `gen` for instance)
      val editor = one[gen.Person]("Editors")
      val cat    = oneString.allowedValues("good", "bad")
    }
  }


  // Accounting segment
  object accounting {
    trait Invoice {
      val no          = oneInt
      val mainProduct = one[warehouse.Item]
    }
    trait InvoiceLine {
      val text    = oneString
      val qty     = oneInt
      val product = one[warehouse.Item]
      val invoice = one[Invoice]("Lines")
    }
  }

  object warehouse {
    trait Item {
      val name = oneString
    }
  }
}