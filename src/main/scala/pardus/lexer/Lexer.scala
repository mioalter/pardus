package pardus.lexer

import fastparse._,
NoWhitespace._

object Lexer {

  /**
    * Cool, we can make a P[Token].
    * Now we have to make
    * - one big uberP[Token] by |-ing all of the individual ones together
    * - a helper function `go`
    *   (inputString, P[Token]) => Option[(remainingString, matchedString, Token)]
    *   by taking the index on which the parsing completes and doing a take/drop.
    * - a helper function that finds the longest match
    *   by taking a (remainingString, matchedString, Token)
    *   and doing a
    *   case (r::rs, m::ms, T) => go(m :: ms :+ r, uberP)
    *   to try to keep matching.
    *   That doesn't look quite right, but something like that.
    */
    // We need one parser for each of type of token
//  // Reserved words
//  case object Type extends Token
    def typeP[_ : P]: P[Token] = P("t" ~ "y" ~ "p" ~ "e").map(_ => Token.Type)
//  case object Var extends Token
    def varP[_ : P] = P("v" ~ "a" ~ "r")
//  case object Function extends Token
    def functionP[_ : P] = P("f" ~ "u" ~ "n" ~ "c" ~ "t" ~ "i" ~ "o" ~ "n")
//  case object Break extends Token
    def breakP[_ : P] = P("b" ~ "r" ~ "e" ~ "a" ~ "k")
//  case object Of extends Token
    def ofP[_ : P] = P("o" ~ "f")
//  case object End extends Token
    def endP[_ : P] = P("e" ~ "n" ~ "d")
//  case object In extends Token
    def inP[_ : P] = P("i" ~ "n")
//  case object Nil extends Token
//  case object Let extends Token
//  case object Do extends Token
//  case object To extends Token
//  case object For extends Token
//  case object While extends Token
//  case object Else extends Token
//  case object Then extends Token
//  case object If extends Token
//  case object Array extends Token
//  // Operators
//  case object Assign extends Token
//  case object Or extends Token
//  case object And extends Token
//  case object GE extends Token
//  case object GT extends Token
//  case object LE extends Token
//  case object LT extends Token
//  case object NEQ extends Token
//  case object EQ extends Token
//  case object Divide extends Token
//  case object Times extends Token
//  case object Minus extends Token
//  case object Plus extends Token
//  // Punctuation
//  case object Dot extends Token
//  case object RBrace extends Token
//  case object LBrace extends Token
//  case object RBrack extends Token
//  case object LBrack extends Token
//  case object RParen extends Token
//  case object LParen extends Token
//  case object Semicolon extends Token
//  case object Colon extends Token
//  case object Comma extends Token
//  //
//  case class String(value: String) extends Token
//  case class Int(value: Int) extends Token
//  case class Id(value: String) extends Token
//  case object EOF extends Token
}

