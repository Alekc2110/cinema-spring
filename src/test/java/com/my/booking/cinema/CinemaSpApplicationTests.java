package com.my.booking.cinema;

import com.my.booking.cinema.dao.repository.MovieSessionRepo;
import com.my.booking.cinema.dao.repository.SeatRepository;
import com.my.booking.cinema.model.Seat;
import com.my.booking.cinema.model.dto.SeatDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
class CinemaSpApplicationTests {

//	@Autowired
//	private SeatRepository repository;

@Autowired
	private MovieSessionRepo movieSessionRepo;

	@Test
	void contextLoads() {
//		movieSessionRepo.getAllByShowDate(LocalDate.of(2021,9,22)).forEach(System.out::println);
	}

	@Test
	void searchByDate() {
//		List<SeatDto> allBookedSeatsByDate = repository.getAllBookedSeatsByDate(LocalDate.of(2021, 9, 22));
//		System.out.println(allBookedSeatsByDate.size());
//		allBookedSeatsByDate.forEach(System.out::println);

//		List<Seat> all = repository.findAll();
//		all.forEach(System.out::println);


	}

}
