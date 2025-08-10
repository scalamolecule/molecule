package molecule.db.compliance.domains.relationship

import molecule.DomainStructure


object Accounting extends DomainStructure {

  trait Invoice {
    val no    = oneInt
    val lines = oneToMany[InvoiceLine]("invoice").owned
  }

  trait InvoiceLine {
    val amount = oneInt
  }
}

//Invoice.no.Lines.amount
//Invoice.no.Lines.*(InvoiceLine.amount)
//
//InvoiceLine.amount.Invoice.no


//// Nested retrieval, invoice-to-lines
//// "Invoice number with list of line amounts"
//Invoice.no.Lines.*(InvoiceLine.amount).query.get ==> List(
//  (1, List(10, 20, 30)),
//  (2, List(20, 70)),
//).transact
//
//// Flat retrieval, invoice-to-line
//// "Invoice number to line amount pairs
//Invoice.no.Lines.amount.query.get ==> List(
//  (1, 10),
//  (1, 20),
//  (1, 30),
//  (2, 20),
//  (2, 70),
//).transact
//
//// Flat retrieval, line-to-invoice
//// Line amount to invoice number pairs
////
//InvoiceLine.amount.Invoice.no.query.get ==> List(
//  (10, 1),
//  (20, 1),
//  (20, 2),
//  (30, 1),
//  (70, 2),
//)
//
//// OBS: we can't group by random/multiple values, only by id,
//// and that would always reference a single entity which makes nested obsolete
//// - use flat instead!
//
////// Nested retrieval, line-to-invoices
////// "Line amount with list of invoice numbers"
////InvoiceLine.amount.Invoice.*(Invoice.no).query.get ==> List(
////  (10, List(1)),
////  (20, List(1, 2)),
////  (30, List(1)),
////  (70, List(2)),
////).transact

