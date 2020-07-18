package com.tabroadn.bookbrowser.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.tabroadn.bookbrowser.dto.BookInfoDto;
import com.tabroadn.bookbrowser.dto.PersonDto;
import com.tabroadn.bookbrowser.entity.Book;
import com.tabroadn.bookbrowser.entity.Person;
import com.tabroadn.bookbrowser.repository.BookRepository;

@Profile("!mock")
@Component
public class BookServiceImpl implements BookService {
	@Autowired
	private BookRepository repository;
	
	@Override
	public List<Book> getNewBooks() {
		return repository.findAll(PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "uploadDate"))).toList();
	}
	
	@Override
	public List<Book> getPopularBooks() {
		return repository.findAll(PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "pageViews"))).toList();
	}

	@Override
	public Book findById(Long id) {
		return repository.findById(id).get();
	}	
	
	@Override
	public List<BookInfoDto> findByTitleContaining(String title) {
		return repository.findByTitleContainingIgnoreCase(title)
						 .stream().map(BookServiceImpl::convertBookToBookInfo)
						 .collect(Collectors.toList());
	}
	
	private static BookInfoDto convertBookToBookInfo(Book book) {
		BookInfoDto bookInfo = new BookInfoDto();
		bookInfo.setId(book.getId());
		bookInfo.setDescription(book.getDescription());
		bookInfo.setPageViews(book.getPageViews());
		bookInfo.setTitle(book.getTitle());
		bookInfo.setUploadDate(book.getUploadDate());
		bookInfo.setAuthors(
				book.getAuthors().stream()
					.map(BookServiceImpl::convertPersonToPersonDto)
					.collect(Collectors.toList()));
		
		return bookInfo;
	}
	
	private static PersonDto convertPersonToPersonDto(Person person) {
		PersonDto personDto = new PersonDto();
		personDto.setId(person.getId());
		personDto.setFirstName(person.getFirstName());
		personDto.setMiddleName(person.getMiddleName());
		personDto.setLastName(person.getLastName());
		return personDto;
	}
}