grammar BooleanQuery;

query : word=WORD
| scope=SCOPE word=WORD
| scope=SCOPE SB mid=query EB
| SB mid=query EB
| left=query operator=OPERATOR right=query;

fragment QUOTE : '"' | '\'';
fragment SINGLE_WORD : [a-zA-Z0-9_]+;
fragment MULTIPLE_WORDS : [a-zA-Z0-9_ ]+;
fragment QUOTED_WORDS : QUOTE (SINGLE_WORD | MULTIPLE_WORDS) QUOTE;

OPERATOR : 'AND' | 'OR' | 'NOT';
SCOPE : ('abstract' | 'title' | 'keywords' | 'fulltext' | 'all') ':';
WORD : SINGLE_WORD | QUOTED_WORDS;
SB : '(';
EB : ')';
WS : (' ' | '\t')+ -> channel(HIDDEN);
