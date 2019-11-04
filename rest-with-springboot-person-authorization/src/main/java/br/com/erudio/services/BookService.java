package br.com.erudio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.data.model.Book;
import br.com.erudio.data.vo.BookVO;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.repository.BookRepository;
import br.com.erudio.serialization.converter.DozerConverter;

@Service
public class BookService {
	
	@Autowired
	BookRepository repo;
	
	public BookVO create(BookVO person) {
		var entity = DozerConverter.parseObject(person, Book.class);
		var vo = DozerConverter.parseObject(repo.save(entity), BookVO.class);
		return vo;
	}
	
	public List<BookVO> findAll() {
		return DozerConverter.parseListObjects(repo.findAll(), BookVO.class);
	}
	
	public BookVO findById(Long id) {
		var entity = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não existe registros para o ID: " + id));
		return DozerConverter.parseObject(entity, BookVO.class);
	}
	
	public BookVO update(BookVO book) {
		var entity = repo.findById(book.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Não existe registros para o ID: " + book.getId()));
		
		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());
		
		var vo = DozerConverter.parseObject(repo.save(entity), BookVO.class);
		return vo;
	}
	
	public void delete(Long id) {
		Book entity = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não existe registros para o ID: " + id));
		repo.delete(entity);
	}
	


	
}
