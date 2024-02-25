package molecule.coreTests.dataModels.core.dataModel

import molecule.DataModel

object Partitions extends DataModel(5) {

  object gen {
    trait Profession {
      val name = oneString
    }

    trait Person {
      val name        = oneString
      val gender      = oneString.enums("male", "female")
      val professions = many[Profession]
    }
  }

  object lit {
    trait Book {
      val title     = oneString
      val author    = one[gen.Person]
      // To avoid attr/partition name clashes we can prepend the definition object name
      // (in case we would have needed an attribute named `gen` for instance)
      val editor    = one[gen.Person]
      val cat       = oneString.enums("good", "bad")
      val reviewers = many[gen.Person]
    }
  }


  // other domain

  object accounting {
    trait Invoice {
      val no          = oneInt
      val mainProduct = one[warehouse.Item]
      val lines       = many[InvoiceLine]
    }
    trait InvoiceLine {
      val text    = oneString
      val qty     = oneInt
      val product = one[warehouse.Item]
      val invoice = one[Invoice]
    }
  }

  object warehouse {
    trait Item {
      val name     = oneString
      val invoices = many[accounting.Invoice]
    }
  }
}