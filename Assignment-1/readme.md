Target :- Take a C-code as input, parse it and print the keywords, operators, punctuators, constants, literals, identifiers along with their row and column number.

Assumptions :- If a line starts with "#" then exclude that line.
	      String literal should end in the same line and anything inside it is considered a literal.

Output :-

		  Token                Type       Row    Column
		    int             Keyword         2         1
		      (         Punctuation         2         9
		      )         Punctuation         2        10
		   main          Identifier         2         4
		      {         Punctuation         3         1
		    int             Keyword         3         2
		      =            Operator         3         7
		      ;         Punctuation         3         9
		      i          Identifier         3         5
		      0            Constant         3         8
		      (         Punctuation         4         7
		      "         Punctuation         4         8
	 The slowest=%d             Literal         4         9
		      "         Punctuation         4        23
		      ,         Punctuation         4        24
		      )         Punctuation         4        26
		      ;         Punctuation         4        27
		 printf          Identifier         4         1
		      i          Identifier         4        25
		      }         Punctuation         5         1
