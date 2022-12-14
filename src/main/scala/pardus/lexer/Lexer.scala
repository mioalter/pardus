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
    *
    * We need one parser for each of type of token
    * fastparse would write typeP[_ : P]: P[Token], but this gives a compiler
    * warning that wildcards are the top level are not allowed.
    */

    /** Reserved words **/

    /** Type **/
    def typeP[Q : P]: P[Token] = P("t" ~ "y" ~ "p" ~ "e").map(_ => Token.Type)
    /** Var **/
    def varP[Q : P]: P[Token] = P("v" ~ "a" ~ "r").map(_ => Token.Var)
    /** Function **/
    def functionP[Q : P]: P[Token] =
      P("f" ~ "u" ~ "n" ~ "c" ~ "t" ~ "i" ~ "o" ~ "n").map(_ => Token.Function)
    /** Break **/
    def breakP[Q : P]: P[Token] =
      P("b" ~ "r" ~ "e" ~ "a" ~ "k").map(_ => Token.Break)
    /** Of **/
    def ofP[Q : P]: P[Token] = P("o" ~ "f").map(_ => Token.Of)
    /** End **/
    def endP[Q : P]: P[Token] = P("e" ~ "n" ~ "d").map(_ => Token.End)
    /** In **/
    def inP[Q : P]: P[Token] = P("i" ~ "n").map(_ => Token.In)
    /** Nil **/
    def nilP[Q : P]: P[Token] = P("n" ~ "i" ~ "l").map(_ => Token.Nil)
    /** Let **/
    def letP[Q: P]: P[Token] = P("l" ~ "e" ~ "t").map(_ => Token.Let)
    /** Do **/
    def doP[Q: P]: P[Token] = P("d" ~ "o").map(_ => Token.Do)
    /** To **/
    def toP[Q: P]: P[Token] = P("t" ~ "o").map(_ => Token.To)
    /** For **/
    def forP[Q: P]: P[Token] = P("f" ~ "o" ~ "r").map(_ => Token.For)
    /** While **/
    def whileP[Q: P]: P[Token] = P("w" ~ "h" ~ "i" ~ "l" ~ "e").map(_ => Token.While)
    /** Else **/
    def elseP[Q: P]: P[Token] = P("e" ~ "l" ~ "s" ~ "e").map(_ => Token.Else)
    /** Then **/
    def thenP[Q: P]: P[Token] = P("t" ~ "h" ~ "e" ~ "n").map(_ => Token.Then)
    /** If **/
    def ifP[Q: P]: P[Token] = P("i" ~ "f").map(_ => Token.If)
    /** Array **/
    def arrayP[Q: P]: P[Token] = P("a" ~ "r" ~ "r" ~ "a" ~ "y").map(_ => Token.Array)

    /** Operators **/

    /** Assign **/
    def assignP[Q: P]: P[Token] = P("=").map(_ => Token.Assign)
    /** Or **/
    def orP[Q: P]: P[Token] = P("||").map(_ => Token.Or)
    /** And **/
    def andP[Q: P]: P[Token] = P("&&").map(_ => Token.And)
    /** GE **/
    def geP[Q: P]: P[Token] = P(">" ~ "=").map(_ => Token.GE)
    /** GT **/
    def gtP[Q: P]: P[Token] = P(">").map(_ => Token.GT)
    /** LE **/
    def leP[Q: P]: P[Token] = P("<" ~ "=").map(_ => Token.LE)
    /** LT **/
    def ltP[Q: P]: P[Token] = P("<").map(_ => Token.LT)
    /** NEQ **/
    def neqP[Q: P]: P[Token] = P("!" ~ "=").map(_ => Token.NEQ)
    /** EQ **/
    def eqP[Q: P]: P[Token] = P("==").map(_ => Token.EQ)
    /** Divide **/
    def divideP[Q: P]: P[Token] = P("/").map(_ => Token.Divide)
    /** Times **/
    def timesP[Q: P]: P[Token] = P("x").map(_ => Token.Times)
    /** Minus **/
    def minusP[Q: P]: P[Token] = P("-").map(_ => Token.Minus)
    /** Plus **/
    def plusP[Q: P]: P[Token] = P("+").map(_ => Token.Plus)


    /** Punctuation **/
    /** Dot **/
    def dotP[Q: P]: P[Token] = P(".").map(_ => Token.Dot)
    /** RBrace **/
    def rbraceP[Q: P]: P[Token] = P("}").map(_ => Token.RBrace)
    /** LBrace **/
    def lbraceP[Q: P]: P[Token] = P("{").map(_ => Token.LBrace)
    /** RBrack **/
    def rbrackP[Q: P]: P[Token] = P("]").map(_ => Token.RBrack)
    /** LBrack **/
    def lbrackP[Q: P]: P[Token] = P("[").map(_ => Token. LBrack)
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

