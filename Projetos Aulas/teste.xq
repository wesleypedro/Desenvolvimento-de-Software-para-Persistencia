let $d := doc("Arquivos/Aula05/livros.xml")

for $livro in $d/livros/livro, $autor in $livro/autores/autor
where $autor/nome = "Thomas Mann"
return $livro