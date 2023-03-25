package molecule.datomic.test.validation.insert

import molecule.base.error._
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Validation._
import molecule.datomic.async._
import molecule.datomic.setup.DatomicTestSuite
import utest._
import scala.language.implicitConversions

object Nested extends DatomicTestSuite {

  override lazy val tests = Tests {

    "2 levels" - validation { implicit conn =>
      for {
        // bad, List(ok, ok)
        _ <- Type.int.Refs.*(Strings.email).insert(
          (1, List(
            "a@aa.com",
            "b@bb.com",
          ))
        ).transact.expect {
          case InsertValidationErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // Top-level row index
                Seq(
                  InsertError(0, 0,
                    "Type.int",
                    Seq(
                      s"""Type.int with value `1` doesn't satisfy validation:
                         |  _ > 1
                         |""".stripMargin
                    ),
                    Nil // composite/nested errors
                  )
                )
              )
            )
        }


        // ok, List(bad, ok)
        _ <- Type.int.Refs.*(Strings.email).insert(
          (2, List(
            "a@aa",
            "b@bb.com",
          ))
        ).transact.expect {
          case InsertValidationErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // Top-level row index
                Seq(
                  InsertError(0, 0,
                    "Type.refs",
                    Nil, // (no errors for ref itself)
                    Seq(
                      (
                        0, // nested row index
                        Seq(
                          InsertError(0, 0,
                            "Strings.email",
                            Seq(
                              "`a@aa` is not a valid email"
                            ),
                            Nil // ( no further nested errors)
                          )
                        )
                      )
                    )
                  )
                )
              )
            )
        }


        // ok, List(ok, bad)
        _ <- Type.int.Refs.*(Strings.email).insert(
          (2, List(
            "a@aa.com",
            "b@bb",
          ))
        ).transact.expect {
          case InsertValidationErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // Top-level row index
                Seq(
                  InsertError(0, 0,
                    "Type.refs",
                    Nil, // (no errors for ref itself)
                    Seq(
                      (
                        1, // nested row index
                        Seq(
                          InsertError(0, 0,
                            "Strings.email",
                            Seq(
                              "`b@bb` is not a valid email"
                            ),
                            Nil // ( no further nested errors)
                          )
                        )
                      )
                    )
                  )
                )
              )
            )
        }


        // bad, List(bad, ok)
        _ <- Type.int.Refs.*(Strings.email).insert(
          (1, List(
            "a@aa",
            "b@bb.com",
          ))
        ).transact.expect {
          case InsertValidationErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // Top-level row index
                Seq(
                  InsertError(0, 0,
                    "Type.int",
                    Seq(
                      s"""Type.int with value `1` doesn't satisfy validation:
                         |  _ > 1
                         |""".stripMargin
                    ),
                    Nil // composite/nested errors
                  ),
                  InsertError(0, 0,
                    "Type.refs",
                    Nil, // (no errors for ref itself)
                    Seq(
                      (
                        0, // nested row index
                        Seq(
                          InsertError(0, 0,
                            "Strings.email",
                            Seq(
                              "`a@aa` is not a valid email"
                            ),
                            Nil // ( no further nested errors)
                          )
                        )
                      )
                    )
                  )
                )
              )
            )
        }


        // bad, List(ok, bad)
        _ <- Type.int.Refs.*(Strings.email).insert(
          (1, List(
            "a@aa",
            "b@bb.com",
          ))
        ).transact.expect {
          case InsertValidationErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // Top-level row index
                Seq(
                  InsertError(0, 0,
                    "Type.int",
                    Seq(
                      s"""Type.int with value `1` doesn't satisfy validation:
                         |  _ > 1
                         |""".stripMargin
                    ),
                    Nil // composite/nested errors
                  ),
                  InsertError(0, 0,
                    "Type.refs",
                    Nil, // (no errors for ref itself)
                    Seq(
                      (
                        0, // nested row index
                        Seq(
                          InsertError(0, 0,
                            "Strings.email",
                            Seq(
                              "`a@aa` is not a valid email"
                            ),
                            Nil // ( no further nested errors)
                          )
                        )
                      )
                    )
                  )
                )
              )
            )
        }


        // ok, List(bad, bad)
        _ <- Type.int.Refs.*(Strings.email).insert(
          (2, List(
            "a@aa",
            "b@bb",
          ))
        ).transact.expect {
          case InsertValidationErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // Top-level row index
                Seq(
                  InsertError(0, 0,
                    "Type.refs",
                    Nil, // (no errors for ref itself)
                    Seq(
                      (
                        0, // nested row index
                        Seq(
                          InsertError(0, 0,
                            "Strings.email",
                            Seq(
                              "`a@aa` is not a valid email"
                            ),
                            Nil // ( no further nested errors)
                          )
                        )
                      ),
                      (
                        1, // nested row index
                        Seq(
                          InsertError(0, 0,
                            "Strings.email",
                            Seq(
                              "`b@bb` is not a valid email"
                            ),
                            Nil // ( no further nested errors)
                          )
                        )
                      )
                    )
                  )
                )
              )
            )
        }


        // bad, List(bad, bad)
        _ <- Type.int.Refs.*(Strings.email).insert(
          (1, List(
            "a@aa",
            "b@bb",
          ))
        ).transact.expect {
          case InsertValidationErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // Top-level row index
                Seq(
                  InsertError(0, 0,
                    "Type.int",
                    Seq(
                      s"""Type.int with value `1` doesn't satisfy validation:
                         |  _ > 1
                         |""".stripMargin
                    ),
                    Nil // composite/nested errors
                  ),
                  InsertError(0, 0,
                    "Type.refs",
                    Nil, // (no errors for ref itself)
                    Seq(
                      (
                        0, // nested row index
                        Seq(
                          InsertError(0, 0,
                            "Strings.email",
                            Seq(
                              "`a@aa` is not a valid email"
                            ),
                            Nil // ( no further nested errors)
                          )
                        )
                      ),
                      (
                        1, // nested row index
                        Seq(
                          InsertError(0, 0,
                            "Strings.email",
                            Seq(
                              "`b@bb` is not a valid email"
                            ),
                            Nil // ( no further nested errors)
                          )
                        )
                      )
                    )
                  )
                )
              )
            )
        }
      } yield ()
    }


    "3 levels" - validation { implicit conn =>
      for {
        _ <- Type.int.Refs.*(Strings.email.Enums.*(Allowed.luckyNumber)).insert(
          (1, List(
            ("a@aa.com", List(1, 7)),
            ("b@bb", List(9, 2))
          ))
        ).transact.expect {
          case InsertValidationErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // Top-level row index
                Seq(
                  InsertError(
                    0, // Outer tuple
                    0, // tuple index
                    "Type.int",
                    Seq(
                      s"""Type.int with value `1` doesn't satisfy validation:
                         |  _ > 1
                         |""".stripMargin
                    ),
                    Seq()
                  ),
                  InsertError(
                    0, // Outer tuple
                    0, // tuple index
                    "Type.refs",
                    Seq(),
                    Seq(
                      (
                        0, // nested row index
                        Seq(
                          InsertError(
                            0, // Outer tuple
                            0, // tuple index
                            "Strings.enums",
                            Seq(),
                            Seq(
                              (
                                0, // nested row index
                                Seq(
                                  InsertError(
                                    0, // Outer tuple
                                    0, // tuple index
                                    "Allowed.luckyNumber",
                                    Seq(
                                      """Value `1` is not one of the allowed values in Seq(7, 9, 13)"""
                                    ),
                                    Seq()
                                  )
                                )
                              )
                            )
                          )
                        )
                      ),
                      (
                        1, // nested row index
                        Seq(
                          InsertError(
                            0, // Outer tuple
                            0, // tuple index
                            "Strings.email",
                            Seq(
                              """`b@bb` is not a valid email"""
                            ),
                            Seq()
                          ),
                          InsertError(
                            0, // Outer tuple
                            0, // tuple index
                            "Strings.enums",
                            Seq(),
                            Seq(
                              (
                                1, // nested row index
                                Seq(
                                  InsertError(
                                    0, // Outer tuple
                                    0, // tuple index
                                    "Allowed.luckyNumber",
                                    Seq(
                                      """Value `2` is not one of the allowed values in Seq(7, 9, 13)"""
                                    ),
                                    Seq()
                                  )
                                )
                              )
                            )
                          )
                        )
                      )
                    )
                  )
                )
              )
            )
        }
      } yield ()
    }


    "Nested + composite" - validation { implicit conn =>
      for {
        _ <- Type.int.Refs.*(Strings.email + Allowed.luckyNumber.luckyNumber2).insert(
          (1, List(
            ("a@aa", (7, 2)),
            ("b@bb.com", (0, 13))
          ))
        ).transact.expect {
          case InsertValidationErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // Top-level row index
                Seq(
                  InsertError(
                    0, // Outer tuple
                    0, // tuple index
                    "Type.int",
                    Seq(
                      s"""Type.int with value `1` doesn't satisfy validation:
                         |  _ > 1
                         |""".stripMargin
                    ),
                    Seq()
                  ),
                  InsertError(
                    0, // Outer tuple
                    0, // tuple index
                    "Type.refs",
                    Seq(),
                    Seq(
                      (
                        0, // nested row index
                        Seq(
                          InsertError(
                            0, // Outer tuple
                            0, // tuple index
                            "Strings.email",
                            Seq(
                              """`a@aa` is not a valid email"""
                            ),
                            Seq()
                          ),
                          InsertError(
                            1, // Outer tuple
                            1, // tuple index
                            "Allowed.luckyNumber2",
                            Seq(
                              """Lucky number can only be 7, 9 or 13"""
                            ),
                            Seq()
                          )
                        )
                      ),
                      (
                        1, // nested row index
                        Seq(
                          InsertError(
                            1, // Outer tuple
                            0, // tuple index
                            "Allowed.luckyNumber",
                            Seq(
                              """Value `0` is not one of the allowed values in Seq(7, 9, 13)"""
                            ),
                            Seq()
                          )
                        )
                      )
                    )
                  )
                )
              )
            )
        }
      } yield ()
    }
  }
}