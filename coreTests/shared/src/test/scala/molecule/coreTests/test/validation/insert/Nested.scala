package molecule.coreTests.test.validation.insert

import molecule.base.error._
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Validation._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.language.implicitConversions

trait Nested extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "2 levels" - validation { implicit conn =>
      for {
        // bad, List(ok, ok)
        _ <- Type.int.Refs.*(Strings.email).insert(
            (1, List(
              "a@aa.com",
              "b@bb.com",
            ))
          ).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors ==> Seq(
                (
                  0, // Top-level row index
                  Seq(
                    InsertError(
                      0,
                      "Type.int",
                      Seq(
                        s"""Type.int with value `1` doesn't satisfy validation:
                           |  _ > 2
                           |""".stripMargin
                      ),
                      Nil // nested errors
                    )
                  )
                )
              )
          }


        // ok, List(bad, ok)
        _ <- Type.int.Refs.*(Strings.email).insert(
            (3, List(
              "a@aa",
              "b@bb.com",
            ))
          ).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors ==> Seq(
                (
                  0, // Top-level row index
                  Seq(
                    InsertError(
                      0,
                      "Type.refs",
                      Nil, // (no errors for ref itself)
                      Seq(
                        (
                          0, // nested row index
                          Seq(
                            InsertError(
                              0,
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
            (3, List(
              "a@aa.com",
              "b@bb",
            ))
          ).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors ==> Seq(
                (
                  0, // Top-level row index
                  Seq(
                    InsertError(
                      0,
                      "Type.refs",
                      Nil, // (no errors for ref itself)
                      Seq(
                        (
                          1, // nested row index
                          Seq(
                            InsertError(
                              0,
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
          ).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors ==> Seq(
                (
                  0, // Top-level row index
                  Seq(
                    InsertError(
                      0,
                      "Type.int",
                      Seq(
                        s"""Type.int with value `1` doesn't satisfy validation:
                           |  _ > 2
                           |""".stripMargin
                      ),
                      Nil // nested errors
                    ),
                    InsertError(
                      0,
                      "Type.refs",
                      Nil, // (no errors for ref itself)
                      Seq(
                        (
                          0, // nested row index
                          Seq(
                            InsertError(
                              0,
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
          ).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors ==> Seq(
                (
                  0, // Top-level row index
                  Seq(
                    InsertError(
                      0,
                      "Type.int",
                      Seq(
                        s"""Type.int with value `1` doesn't satisfy validation:
                           |  _ > 2
                           |""".stripMargin
                      ),
                      Nil // nested errors
                    ),
                    InsertError(
                      0,
                      "Type.refs",
                      Nil, // (no errors for ref itself)
                      Seq(
                        (
                          0, // nested row index
                          Seq(
                            InsertError(
                              0,
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
            (3, List(
              "a@aa",
              "b@bb",
            ))
          ).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors ==> Seq(
                (
                  0, // Top-level row index
                  Seq(
                    InsertError(
                      0,
                      "Type.refs",
                      Nil, // (no errors for ref itself)
                      Seq(
                        (
                          0, // nested row index
                          Seq(
                            InsertError(
                              0,
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
                            InsertError(
                              0,
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
          ).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors ==> Seq(
                (
                  0, // Top-level row index
                  Seq(
                    InsertError(
                      0,
                      "Type.int",
                      Seq(
                        s"""Type.int with value `1` doesn't satisfy validation:
                           |  _ > 2
                           |""".stripMargin
                      ),
                      Nil // nested errors
                    ),
                    InsertError(
                      0,
                      "Type.refs",
                      Nil, // (no errors for ref itself)
                      Seq(
                        (
                          0, // nested row index
                          Seq(
                            InsertError(
                              0,
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
                            InsertError(
                              0,
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
        _ <- Type.int.Refs.*(Strings.email.Enums.*(Enum.luckyNumber)).insert(
            (1, List(
              ("a@aa.com", List(1, 7)),
              ("b@bb", List(9, 2))
            ))
          ).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors ==> Seq(
                (
                  0, // Top-level row index
                  Seq(
                    InsertError(
                      0, // tuple index
                      "Type.int",
                      Seq(
                        s"""Type.int with value `1` doesn't satisfy validation:
                           |  _ > 2
                           |""".stripMargin
                      ),
                      Seq()
                    ),
                    InsertError(
                      0, // tuple index
                      "Type.refs",
                      Seq(),
                      Seq(
                        (
                          0, // nested row index
                          Seq(
                            InsertError(
                              0, // tuple index
                              "Strings.enums",
                              Seq(),
                              Seq(
                                (
                                  0, // nested row index
                                  Seq(
                                    InsertError(
                                      0, // tuple index
                                      "Enum.luckyNumber",
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
                              0, // tuple index
                              "Strings.email",
                              Seq(
                                """`b@bb` is not a valid email"""
                              ),
                              Seq()
                            ),
                            InsertError(
                              0, // tuple index
                              "Strings.enums",
                              Seq(),
                              Seq(
                                (
                                  1, // nested row index
                                  Seq(
                                    InsertError(
                                      0, // tuple index
                                      "Enum.luckyNumber",
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
  }
}
